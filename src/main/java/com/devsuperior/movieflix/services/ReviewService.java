package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;


    @Transactional(readOnly = true)
    public List<ReviewDTO> review(Long movieId) {
        List<Review> list = repository.findByMovieId(movieId);
        return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {

        User user = authService.authenticated();
        Review entity = new Review();

        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
        entity.setUser(user);
        entity = repository.save(entity);

        return new ReviewDTO(entity);

    }

}
