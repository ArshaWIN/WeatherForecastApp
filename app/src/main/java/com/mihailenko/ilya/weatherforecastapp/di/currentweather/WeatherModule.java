package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractorImpl;
import com.mihailenko.ilya.weatherforecastapp.repositories.weather.WeatherForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.repositories.weather.WeatherForecastRepositoryImpl;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya Mihailenko on 12/08/2018.
 * i.mihailenko@fasten.com
 * Last edit by Ilya Mihailenko on 12/08/2018.
 */

@Module
public class WeatherModule {

    @PerActivity
    @Provides
    WeatherForecastRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new WeatherForecastRepositoryImpl(weatherApi);
    }

    @PerActivity
    @Provides
    WeatherInteractor provideWeatherInteractor(WeatherForecastRepository weatherRepository) {
        return new WeatherInteractorImpl(weatherRepository);
    }
}
