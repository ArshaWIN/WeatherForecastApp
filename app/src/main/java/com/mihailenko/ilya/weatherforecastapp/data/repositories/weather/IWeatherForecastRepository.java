package com.mihailenko.ilya.weatherforecastapp.data.repositories.weather;

import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;

import rx.Observable;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface IWeatherForecastRepository {
    Observable<Weather> getWeather(String city);
}
