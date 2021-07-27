package com.devsuperior.movieflix.mappers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review dtoToEntity(ReviewDTO dto);

    @Mapping(source = "movie.id", target = "movieId")
    ReviewDTO entityToDTO(Review entity);
}
