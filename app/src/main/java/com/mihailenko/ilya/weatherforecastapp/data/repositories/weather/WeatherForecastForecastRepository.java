package com.mihailenko.ilya.weatherforecastapp.data.repositories.weather;

import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;

import rx.Observable;

/**
 * Created by Ilya on 12.06.2017.
 */

public class WeatherForecastForecastRepository implements IWeatherForecastRepository {

    private final WeatherApi weatherApi;

    public WeatherForecastForecastRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<Weather> getWeather(String city) {
        return weatherApi.getWeather(city);
    }
}
