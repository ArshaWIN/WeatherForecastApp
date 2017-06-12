package com.mihailenko.ilya.weatherforecastapp.business.currentweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.data.models.Weather;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.ICurrentWeatherRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherInteractor implements ICurrentWeatherInteractor {

    private final ICurrentWeatherRepository weatherRepository;

    public CurrentWeatherInteractor(ICurrentWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Observable<List<ForecastDayItem>> getForecast(String city) {
        return weatherRepository.getWeather(city)
                .map(Weather::getForecasts)
                .flatMap(Observable::from)
                .map(ForecastDayItem::new)
                .toList();
    }
}
