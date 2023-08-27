package com.dorian.commentmymovies.service;

import com.dorian.commentmymovies.model.Movie;
import com.dorian.commentmymovies.model.MovieSearchResponse;
import com.dorian.commentmymovies.repositories.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void getMovies() throws IOException, URISyntaxException, InterruptedException {
        // Setup
        Movie movie1 = new Movie("111", "Scarface", "en");
        Movie movie2 = new Movie("877", "Scarface", "en");
        Movie[] results = {movie1, movie2};
        MovieSearchResponse movieSearchResponse = new MovieSearchResponse(1, results);

        when(movieRepository.getMovies("scarface")).thenReturn(movieSearchResponse);

        // Test
        Movie[] movies = service.getMovies("scarface");

        assertThat(movies).containsExactly(movie1, movie2);
    }
}