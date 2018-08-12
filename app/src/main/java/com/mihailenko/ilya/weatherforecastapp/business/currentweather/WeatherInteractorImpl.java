package com.mihailenko.ilya.weatherforecastapp.business.currentweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.weather.WeatherForecastRepository;
import com.mihailenko.ilya.weatherforecastapp.errors.ForecastNotFoundThrowable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Ilya on 12.06.2017.
 */

public class WeatherInteractorImpl implements WeatherInteractor {

    private final WeatherForecastRepository weatherRepository;

    public WeatherInteractorImpl(WeatherForecastRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Single<List<ForecastDayItem>> getForecast(String city) {
        return weatherRepository.getWeatherByCity(city)
                .map(Weather::getForecasts)
                .flatMapObservable(Observable::fromIterable)
                .map(ForecastDayItem::new)
                .toList()
                .flatMap(forecastDayItems -> {
                    if (forecastDayItems.isEmpty()) {
                        return Single.error(new ForecastNotFoundThrowable());
                    }
                    return Single.just(forecastDayItems);
                });
    }
}