package com.mihailenko.ilya.weatherforecastapp.di.app;

import android.content.Context;

import com.mihailenko.ilya.weatherforecastapp.di.RestModule;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ilya on 11.06.2017.
 */

@Component(modules = {AppModule.class, RestModule.class})
@Singleton
public interface AppComponent {
    Context getContext();

    MessageShower provideToastMessageShower();

    WeatherApi provideWeatherApi();

    GooglePlacesApi providePlacesApi();

}
