package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(
            "SELECT obj FROM Movie obj " +
            "WHERE (COALESCE(:genres) IS NULL OR obj.genre IN :genres) " +
            "ORDER BY obj.title"
    )
    Page<Movie> find(List<Genre> genres, Pageable pageable);
}
