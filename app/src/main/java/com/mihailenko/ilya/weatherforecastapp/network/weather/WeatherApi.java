package com.mihailenko.ilya.weatherforecastapp.network.weather;

import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Weather;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ilya on 11.06.2017.
 */

public interface WeatherApi {

    @GET(WeatherApiConstans.Route.FORECAST_FOR_CITY)
    Observable<Weather> getWeather(@Path("city") String city);
}
