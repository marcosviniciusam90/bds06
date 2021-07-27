package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieResource {

    private final MovieService service;

    @GetMapping
    public Page<MovieMinDTO> find(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable
    ) {

        return service.find(genreId, pageable);
    }

    @GetMapping("/{id}")
    public MovieDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> findReviews(@PathVariable Long id) {
        return service.findReviews(id);
    }
}
