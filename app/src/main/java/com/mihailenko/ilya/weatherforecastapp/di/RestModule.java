package com.mihailenko.ilya.weatherforecastapp.di;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.mihailenko.ilya.weatherforecastapp.BuildConfig;
import com.mihailenko.ilya.weatherforecastapp.data.network.weather.WeatherNetworkService;
import com.mihailenko.ilya.weatherforecastapp.models.places.Place;
import com.mihailenko.ilya.weatherforecastapp.models.weather.Weather;
import com.mihailenko.ilya.weatherforecastapp.mapper.PlacesMapper;
import com.mihailenko.ilya.weatherforecastapp.mapper.WeatherMapper;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesNetworkService;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApiConstans;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.NetworkErrorInterceptor;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.QueryInterceptor;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;


@Singleton
@Module
public class RestModule {

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor.Level logLevel = BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE;

        return new HttpLoggingInterceptor().setLevel(logLevel);
    }

    @Singleton
    @Provides
    NetworkErrorInterceptor provideNetworkErrorInterceptor(ConnectivityManager connectivityManager) {
        return new NetworkErrorInterceptor(connectivityManager);
    }

    @Singleton
    @Provides
    QueryInterceptor provideQueryInterceptor() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(GooglePlacesApiConstans.GOOGLE_API_KEY, GooglePlacesApiConstans.GOOGLE_API_KEY_VALUE);
        hashMap.put(GooglePlacesApiConstans.TYPE_NAME, GooglePlacesApiConstans.TYPE_VALUE_CITIES);

        return new QueryInterceptor(hashMap);
    }


    @Singleton
    @Provides
    Gson provideGson(WeatherMapper weatherMapper, PlacesMapper placesMapper) {
        return new GsonBuilder()
                .registerTypeAdapter(Weather.class, weatherMapper)
                .registerTypeAdapter(Place.class, placesMapper)
                .create();
    }

    @Singleton
    @Provides
    WeatherApi provideWeatherApi(WeatherNetworkService weatherNetworkService) {
        return weatherNetworkService.getWeatherApi();
    }

    @Singleton
    @Provides
    GooglePlacesApi providePlacesApi(GooglePlacesNetworkService googlePlacesNetworkService) {
        return googlePlacesNetworkService.getPlacesApi();
    }

    @Provides
    @Singleton
    Configuration provideGsonConfiguration() {
        return Configuration.builder()
                .mappingProvider(new GsonMappingProvider())
                .jsonProvider(new GsonJsonProvider())
                .build();
    }


}
