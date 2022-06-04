package com.example.movieapi.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.movieapi.R;
import com.example.movieapi.adapters.MoviesAdapter;
import com.example.movieapi.adapters.SliderPagerAdapter;
import com.example.movieapi.models.Slide;
import com.example.movieapi.presenter.MovieConstract;
import com.example.movieapi.presenter.MoviePresenter;
import com.example.movieapi.response.MovieResponse;
import com.example.movieapi.response.Movies;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.example.movieapi.utils.Config.*;

public class HomeActivity extends AppCompatActivity implements MovieConstract.IView {

    private List<Slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView rcv ;
    private List<Movies> listMovies;
    private MoviesAdapter adapter;
    private MovieConstract.IPresenter mPresenter;
    private SwitchCompat aSwitch;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.ThemeDark);
        }else {
            setTheme(R.style.Theme_MovieAPI);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sliderpager = findViewById(R.id.slider_pager) ;
        indicator = findViewById(R.id.indicator);
        rcv = findViewById(R.id.Rv_movies);
        aSwitch = findViewById(R.id.mode);
        imageView = findViewById(R.id.img_Profile_Home);


        // Cicle Animation
        Runnable runnableCycle = new Runnable() {
            @Override
            public void run() {
                imageView.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
                imageView.animate().rotationBy(360).withEndAction(runnableCycle).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, Profile.class);
                        startActivity(intent);
                    }
                });


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecKed) {
                if (isChecKed){
                    // defaul true
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.bgconan,"Detective Conan"));
        lstSlides.add(new Slide(R.drawable.bgyourname," Your Name"));
        lstSlides.add(new Slide(R.drawable.bgmarvel," Avengers"));
        lstSlides.add(new Slide(R.drawable.bgsagiri," Eromanga Sensei"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlides);
        sliderpager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        // Recyclerview Setup
        // ini data
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        mPresenter = new MoviePresenter(this);
        mPresenter.getListMovie();
    }

    @Override
    public void setMovieUi(List<Movies> moviesList) {
        adapter = new MoviesAdapter(getApplicationContext(), moviesList,listens);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    MoviesAdapter.MovieClickListen listens = movie -> {
        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        intent.putExtras(bundle);
        startActivity(intent);
    };

    private class SliderTimer extends TimerTask {
        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < lstSlides.size() -1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() +1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });
        }
    }
}
