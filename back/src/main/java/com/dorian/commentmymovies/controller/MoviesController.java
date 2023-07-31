package com.dorian.commentmymovies.controller;

import com.dorian.commentmymovies.model.Movie;
import com.dorian.commentmymovies.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/movie")
public class MoviesController {

    private final MovieService movieService;

    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public Movie[] searchMovies(@RequestParam(value = "query") String query) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(query);
        return movieService.getMovies(query);
    }
}
