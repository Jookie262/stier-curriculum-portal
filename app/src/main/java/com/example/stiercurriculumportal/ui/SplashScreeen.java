package com.example.stiercurriculumportal.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.ui.login.LoginActivity;
import com.example.stiercurriculumportal.ui.signup.SignupActivity;

public class SplashScreeen extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreeen.this, LoginActivity.class));
                finish();
            }
        },2000);
    }
}