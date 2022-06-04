package com.example.movieapi.presenter;

import android.view.View;

import com.example.movieapi.response.Movies;

import java.util.List;

public interface MovieConstract {
    interface IPresenter {
         void getListMovie();
    }

    interface IView {
         void setMovieUi(List<Movies> moviesList);
    }
}
