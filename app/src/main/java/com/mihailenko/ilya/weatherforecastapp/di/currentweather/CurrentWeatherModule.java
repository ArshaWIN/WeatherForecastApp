package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.CurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.CurrentWeatherRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.ICurrentWeatherRepository;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;
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
    CurrentWeatherPresenter provideCurrentWeatherPresenter(ICurrentWeatherInteractor weatherInteractor) {
        return new CurrentWeatherPresenterImpl(activity,weatherInteractor);
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

    @PerActivity
    @Provides
    ICurrentWeatherRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new CurrentWeatherRepository(weatherApi);
    }

    @PerActivity
    @Provides
    ICurrentWeatherInteractor provideCurrentWeatherInteractor(ICurrentWeatherRepository weatherRepository) {
        return new CurrentWeatherInteractor(weatherRepository);
    }
}
