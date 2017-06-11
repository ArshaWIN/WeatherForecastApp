package com.mihailenko.ilya.weatherforecastapp.ui.splashscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mihailenko.ilya.weatherforecastapp.ui.main.MainActivity;

/**
 * Created by Ilya on 11.06.2017.
 */

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.start(this);
        finish();
    }
}
