package com.example.splashscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.et_login_prompt_1);
        password = (EditText) findViewById(R.id.et_login_prompt_2);
        login = (Button) findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences(Homescreen.myPrefs, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.putBoolean("isLogged", true);
                editor.commit();
                Intent i = new Intent(MainActivity.this, Homescreen.class);
                startActivity(i);
            }
        });
    }
}