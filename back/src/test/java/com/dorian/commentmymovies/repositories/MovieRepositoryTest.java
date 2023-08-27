package com.dorian.commentmymovies.repositories;

import com.dorian.commentmymovies.model.Movie;
import com.dorian.commentmymovies.model.MovieSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieRepositoryTest {

    @InjectMocks
    private MovieRepository movieRepository;

    @Mock
    private HttpClient httpClient;

    @Test
    void test() throws IOException, InterruptedException, URISyntaxException {
        // Setup
        Movie movie1 = new Movie("111", "Scarface", "en");
        Movie movie2 = new Movie("877", "Scarface", "en");
        Movie[] results = {movie1, movie2};
        MovieSearchResponse movieSearchResponse = new MovieSearchResponse(1, results);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(movieSearchResponse);

        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        when(response.body()).thenReturn(body);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);

        // Test
        MovieSearchResponse result = movieRepository.getMovies("test");

        // Assert
        verify(httpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertThat(result).isEqualToComparingFieldByField(movieSearchResponse);
    }
}