package com.example.movieapi.presenter;

import static com.example.movieapi.utils.Config.BASE_URL_API;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.movieapi.adapters.MoviesAdapter;
import com.example.movieapi.response.MovieResponse;
import com.example.movieapi.response.Movies;
import com.example.movieapi.ui.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoviePresenter implements MovieConstract.IPresenter {
    protected MovieConstract.IView mView;

    public MoviePresenter(MovieConstract.IView view){
        mView = view;
    }

    @Override
    public void getListMovie() {
        OKHTTPReqTask task = new OKHTTPReqTask();
        task.execute();
    }

    private class OKHTTPReqTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            try {
                Request.Builder builder = new Request.Builder();
                builder.url(BASE_URL_API);
                Request request = builder.build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                Log.e("App error", e.toString());
            }

            return "";
        }

        @Override
        protected void onPostExecute(String data) {
            Log.e("finish", "good");
            Gson gson = new Gson();
            Type type = new TypeToken<MovieResponse>(){}.getType();
            MovieResponse movieResponse = gson.fromJson(data, type);
            List<Movies> listMovies = movieResponse.movies;
            mView.setMovieUi(listMovies);
        }
    }
}
