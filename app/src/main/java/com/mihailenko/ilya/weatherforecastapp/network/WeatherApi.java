package com.mihailenko.ilya.weatherforecastapp.network;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ilya on 11.06.2017.
 */

public interface WeatherApi {

    @GET(ApiConstans.Route.FORECAST_FOR_CITY)
    Observable getForecast(@Path("city") String city);
}
