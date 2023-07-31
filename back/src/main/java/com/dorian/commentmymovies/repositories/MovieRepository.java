package com.dorian.commentmymovies.repositories;

import com.dorian.commentmymovies.model.Movie;
import com.dorian.commentmymovies.model.MovieSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MovieRepository {

    @Value("${movieDB.token}")
    private String token;

    public Movie[] getMovies(String movieName) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.themoviedb.org/3/search/movie?query=" + movieName))
                .GET()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        MovieSearchResponse movieSearchResponse = objectMapper.readValue(response.body(), MovieSearchResponse.class);

        return movieSearchResponse.results();
    }
}
