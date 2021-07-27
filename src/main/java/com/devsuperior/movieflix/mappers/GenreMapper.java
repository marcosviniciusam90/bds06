package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    Genre dtoToEntity(GenreDTO dto);

    GenreDTO entityToDTO(Genre entity);
}
