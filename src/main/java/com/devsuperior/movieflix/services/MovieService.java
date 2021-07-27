package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.mappers.MovieMapper;
import com.devsuperior.movieflix.mappers.ReviewMapper;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private static final MovieMapper MAPPER = MovieMapper.INSTANCE;
    private static final ReviewMapper REVIEW_MAPPER = ReviewMapper.INSTANCE;
    private final MovieRepository repository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<MovieMinDTO> find(Long genreId, Pageable pageable) {
        List<Genre> genres = (genreId == 0) ? null : List.of(genreRepository.getOne(genreId));

        Page<Movie> pageList = repository.find(genres, pageable);

        return pageList.map(MAPPER::entityToMinDTO);
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return MAPPER.entityToDTO(movie);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviews(Long id) {
        Movie movie = repository.getOne(id);
        return reviewRepository.findByMovie(movie)
                .stream().map(REVIEW_MAPPER::entityToDTO)
                .collect(Collectors.toList());
    }
}
