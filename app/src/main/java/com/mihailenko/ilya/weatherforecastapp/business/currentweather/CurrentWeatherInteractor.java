package com.mihailenko.ilya.weatherforecastapp.business.currentweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.IWeatherForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.errors.ForecastNotFoundThrowable;

import java.util.List;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherInteractor implements ICurrentWeatherInteractor {

    private final IWeatherForecastRepository weatherRepository;

    public CurrentWeatherInteractor(IWeatherForecastRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Observable<List<ForecastDayItem>> getForecast(String city) {
        return weatherRepository.getWeather(city)
                .map(Weather::getForecasts)
                .flatMap(Observable::from)
                .map(ForecastDayItem::new)
                .toList()
                .flatMap(forecastDayItems -> {
                    if (forecastDayItems.isEmpty()) {
                        return Observable.error(new ForecastNotFoundThrowable());
                    } else {
                        return Observable.just(forecastDayItems);
                    }
                });
    }
}