package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenterImpl;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentLocationWeatherActivity;

import java.util.Collections;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 12.06.2017.
 */

@Module
public class CurrentWeatherModule {

    private CurrentLocationWeatherActivity activity;

    public CurrentWeatherModule(CurrentLocationWeatherActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    CurrentWeatherPresenter provideCurrentWeatherPresenter() {
        return new CurrentWeatherPresenterImpl(activity);
    }

    @PerActivity
    @Provides
    ForecastAdapter provideForecastAdapter() {
        return new ForecastAdapter(Collections.emptyList());
    }

    @PerActivity
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(activity);
    }
}
