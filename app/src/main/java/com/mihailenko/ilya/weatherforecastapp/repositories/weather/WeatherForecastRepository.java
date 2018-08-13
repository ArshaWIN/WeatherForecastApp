package com.mihailenko.ilya.weatherforecastapp.repositories.weather;

import com.mihailenko.ilya.weatherforecastapp.models.weather.Weather;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface WeatherForecastRepository {
    Single<Weather> getWeatherByCity(String city);
}
