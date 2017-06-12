package com.mihailenko.ilya.weatherforecastapp.di;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.mihailenko.ilya.weatherforecastapp.BuildConfig;
import com.mihailenko.ilya.weatherforecastapp.data.models.Weather;
import com.mihailenko.ilya.weatherforecastapp.mapper.WeatherMapper;
import com.mihailenko.ilya.weatherforecastapp.network.NetworkService;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.network.interceptors.NetworkErrorInterceptor;

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
    Gson provideGson(WeatherMapper weatherMapper) {
        return new GsonBuilder()
                .registerTypeAdapter(Weather.class, weatherMapper)
                .create();
    }

    @Singleton
    @Provides
    WeatherApi provideWeatherApi(NetworkService networkService) {
        return networkService.getWeatherApi();
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
