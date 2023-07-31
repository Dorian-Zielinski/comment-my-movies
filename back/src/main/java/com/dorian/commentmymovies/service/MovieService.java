package com.dorian.commentmymovies.service;

import com.dorian.commentmymovies.model.Movie;
import com.dorian.commentmymovies.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie[] getMovies(String movieName) throws IOException, URISyntaxException, InterruptedException {
        return movieRepository.getMovies(movieName);
    }
}
