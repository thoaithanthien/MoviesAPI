package com.example.movieapi.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.movieapi.R;
import com.example.movieapi.ui.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    TextView tv_appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv_appname = findViewById(R.id.tv_appname);
        lottie = findViewById(R.id.lottie);

//        tv_appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(0).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}