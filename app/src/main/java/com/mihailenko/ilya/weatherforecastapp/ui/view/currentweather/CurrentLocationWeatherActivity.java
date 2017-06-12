package com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivityCurrentWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.CurrentWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.DaggerCurrentWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentLocationWeatherActivity extends BaseActivity<ActivityCurrentWeatherBinding, CurrentWeatherPresenter>
        implements CurrentWeatherView {

    @Inject
    CurrentWeatherPresenter presenter;
    @Inject
    ForecastAdapter forecastAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;

    public static void start(Context context) {
        Intent starter = new Intent(context, CurrentLocationWeatherActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void buildActivityComponentAndInject() {
        DaggerCurrentWeatherComponent.builder()
                .activityComponent(getActivityComponent())
                .currentWeatherModule(new CurrentWeatherModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAdapter();
        setupActionBar();
    }

    private void createAdapter() {
        binding.weatherList.setAdapter(forecastAdapter);
        binding.weatherList.setLayoutManager(layoutManager);
    }

    @Override
    protected ActivityCurrentWeatherBinding inflateBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_current_weather);
    }

    @Nullable
    @Override
    public CurrentWeatherPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showForecast(List<ForecastDayItem> forecastDayItems) {
        forecastAdapter.setForecast(forecastDayItems);
    }

    private void setupActionBar() {
        setSupportActionBar(binding.toolbar.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.current_weather);
        }
    }
}
