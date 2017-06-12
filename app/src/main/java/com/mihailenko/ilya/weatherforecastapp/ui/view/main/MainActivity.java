package com.mihailenko.ilya.weatherforecastapp.ui.view.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivityMainBinding;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentLocationWeatherActivity;

public class MainActivity extends AppCompatActivity implements ChooseActivityViewModel {

    private ActivityMainBinding binding;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(this);
        setupActionBar();
    }

    private void setupActionBar() {
        setSupportActionBar(binding.toolbar.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.choose_mode);
        }
    }

    @Override
    public void onCurrentWeatherClick(View view) {
        CurrentLocationWeatherActivity.start(this);
    }

    @Override
    public void onSearchCityClickClick(View view) {

    }
}
