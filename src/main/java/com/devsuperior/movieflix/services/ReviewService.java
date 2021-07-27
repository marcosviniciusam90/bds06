package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.mappers.ReviewMapper;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewService {

    private static final ReviewMapper MAPPER = ReviewMapper.INSTANCE;
    private final ReviewRepository repository;
    private final MovieRepository movieRepository;
    private final AuthService authService;

    @Transactional
    public ReviewDTO create(ReviewDTO dto) {
        try {
            Review entity = new Review();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return MAPPER.entityToDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException(dto.getMovieId());
        }

    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity) {
        entity.setText(dto.getText());
        entity.setUser(authService.getAuthenticatedUser());
        entity.setMovie(movieRepository.getOne(dto.getMovieId()));
    }
}
