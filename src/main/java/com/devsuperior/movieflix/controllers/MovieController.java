package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> moviesByGenre(
            @RequestParam(name = "genreId") Long genreId, Pageable pageable) {
        Page<MovieCardDTO> movies = service.moviesByGenre(genreId, pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> moviesById(@PathVariable Long id) {
        MovieDetailsDTO dto = service.moviesById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> review(@PathVariable Long id) {
        List<ReviewDTO> dto = reviewService.review(id);
        return ResponseEntity.ok(dto);
    }
}
