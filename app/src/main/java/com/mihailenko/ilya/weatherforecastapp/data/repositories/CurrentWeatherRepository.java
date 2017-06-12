package com.mihailenko.ilya.weatherforecastapp.data.repositories;

import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.data.models.Weather;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherRepository implements ICurrentWeatherRepository {

    private final WeatherApi weatherApi;

    public CurrentWeatherRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<Weather> getWeather(String city) {
        return weatherApi.getWeather(city);
    }
}
