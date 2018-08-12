package com.mihailenko.ilya.weatherforecastapp.data.repositories.weather;

import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;

import io.reactivex.Single;


/**
 * Created by Ilya on 12.06.2017.
 */

public class WeatherForecastRepositoryImpl implements WeatherForecastRepository {

    private final WeatherApi weatherApi;

    public WeatherForecastRepositoryImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<Weather> getWeatherByCity(String city) {
        return weatherApi.getWeather(city);
    }
}
