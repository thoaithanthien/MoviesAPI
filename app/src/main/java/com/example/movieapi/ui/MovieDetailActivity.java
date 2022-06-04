package com.example.movieapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import static com.example.movieapi.utils.Config.*;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movieapi.R;
import com.example.movieapi.response.Movies;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public TextView tvId, tvTitle , tvOverView, tvPopular, tv_poster;
    public ImageView img, imgCover, imgBack;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        // ini views
        play_fab = findViewById(R.id.play_fab);
        tvTitle = findViewById(R.id.detail_movie_title);
        tvOverView = findViewById(R.id.detail_movie_desc);
        tvPopular = findViewById(R.id.tv_popularity);
        imgCover = findViewById(R.id.detail_movie_cover);
        imgBack = findViewById(R.id.img_back);
        img = findViewById(R.id.detail_movie_img);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Movies mMovie = (Movies) bundle.get("movie");
        tvTitle.setText(mMovie.title);
//        tvHomePage.setText(mMovie.title);
        tvOverView.setText(mMovie.overview);
        tvPopular.setText(mMovie.popularity + "");
        Picasso.with(getApplicationContext()).load(BASE_BACKDROP_PATH + mMovie.backdropPath).into(imgCover);
        Picasso.with(getApplicationContext()).load(BASE_BACKDROP_PATH + mMovie.poster_path).into(img);
        // setup animation
        imgCover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        img.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }
}
