package com.example.movieapi.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    public int page;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("total_results")
    public int total_results;

    @SerializedName("results")
    public List<Movies> movies;
}
