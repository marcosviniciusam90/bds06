package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie dtoToEntity(MovieDTO dto);

    MovieDTO entityToDTO(Movie entity);

    MovieMinDTO entityToMinDTO(Movie entity);
}
