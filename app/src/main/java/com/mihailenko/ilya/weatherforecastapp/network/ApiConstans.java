package com.mihailenko.ilya.weatherforecastapp.network;

/**
 * Created by Ilya on 11.06.2017.
 */

final class ApiConstans {
    static final String WEATHER_API_KEY = "2cdcb84e92cc5749";
    static final String BASE_URL = "http://api.wunderground.com/api/";

    final static class Route {
        static final String FORECAST_FOR_CITY = "forecast10day/q/CA/{city}.json";
    }
}
