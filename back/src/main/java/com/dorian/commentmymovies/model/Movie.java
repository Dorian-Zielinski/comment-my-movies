package com.dorian.commentmymovies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Movie(String id, String title, String original_language) implements Serializable {
}
