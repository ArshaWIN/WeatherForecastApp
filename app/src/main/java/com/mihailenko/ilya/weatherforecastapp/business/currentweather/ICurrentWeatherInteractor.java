package com.mihailenko.ilya.weatherforecastapp.business.currentweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public interface ICurrentWeatherInteractor {
    Single<List<ForecastDayItem>> getForecast(String city);
}
