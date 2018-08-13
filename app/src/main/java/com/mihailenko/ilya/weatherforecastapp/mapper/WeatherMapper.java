package com.mihailenko.ilya.weatherforecastapp.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.mihailenko.ilya.weatherforecastapp.models.weather.Forecast;
import com.mihailenko.ilya.weatherforecastapp.models.weather.Weather;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ilya on 11.06.2017.
 */

@Singleton
public class WeatherMapper implements JsonDeserializer<Weather> {

    private static final String WEEK = "$..simpleforecast.forecastday[0:7]";
    private final Configuration configuration;
    private final TypeRef<List<Forecast>> listForecastType;

    @Inject
    public WeatherMapper(Configuration configuration) {
        this.configuration = configuration;
        listForecastType = new TypeRef<List<Forecast>>() {
        };
    }

    @Override
    public Weather deserialize(JsonElement jsonElement,
                               Type typeOfT,
                               JsonDeserializationContext context) throws JsonParseException {
        final String json = jsonElement.toString();
        final Weather weather = new Weather();

        List<Forecast> forecasts = JsonPath
                .using(configuration)
                .parse(json)
                .read(WEEK, listForecastType);

        weather.setForecasts(forecasts);

        return weather;
    }
}
