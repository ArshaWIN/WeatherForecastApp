package com.mihailenko.ilya.weatherforecastapp.business.currentweather;

import com.mihailenko.ilya.weatherforecastapp.models.weather.ForecastDayItem;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface WeatherInteractor {
    Single<List<ForecastDayItem>> getForecast(String city);
}
