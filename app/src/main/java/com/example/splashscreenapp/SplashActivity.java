package com.example.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_background);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Context context = getApplicationContext();
                SharedPreferences preferences =context.getSharedPreferences(Homescreen.myPrefs, MODE_PRIVATE);
                boolean isLogged = preferences.getBoolean("isLogged", false);

                if (isLogged){
                    Intent i = new Intent(SplashActivity.this, Homescreen.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);
    }
}