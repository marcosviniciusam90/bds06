package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.mappers.GenreMapper;
import com.devsuperior.movieflix.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GenreService {

    private static final GenreMapper MAPPER = GenreMapper.INSTANCE;
    private final GenreRepository repository;

    @Transactional(readOnly = true)
    public List<GenreDTO> findAll() {

        List<Genre> list = repository.findAll();

        return list.stream().map(MAPPER::entityToDTO).collect(Collectors.toList());
    }
}
