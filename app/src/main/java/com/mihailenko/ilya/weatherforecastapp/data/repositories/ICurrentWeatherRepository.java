package com.mihailenko.ilya.weatherforecastapp.data.repositories;

import com.mihailenko.ilya.weatherforecastapp.data.models.Weather;

import rx.Observable;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface ICurrentWeatherRepository {
    Observable<Weather> getWeather(String city);
}
