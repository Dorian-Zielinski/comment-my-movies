package com.dorian.commentmymovies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieSearchResponse(int page, Movie[] results) implements Serializable {
}
