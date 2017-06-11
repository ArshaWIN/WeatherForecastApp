package com.mihailenko.ilya.weatherforecastapp.di;

import com.mihailenko.ilya.weatherforecastapp.BuildConfig;
import com.mihailenko.ilya.weatherforecastapp.network.NetworkService;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;


@Singleton
@Module
public class RestModule {

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
    WeatherApi provideWeatherApi(NetworkService networkService) {
        return networkService.getWeatherApi();
    }
}
