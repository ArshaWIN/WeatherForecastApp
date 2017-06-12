package com.mihailenko.ilya.weatherforecastapp.di;


import android.content.Context;
import android.location.LocationManager;

import com.mihailenko.ilya.weatherforecastapp.di.app.AppComponent;
import com.mihailenko.ilya.weatherforecastapp.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;

import dagger.Component;


@PerBaseActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    Context getContext();

    MessageShower provideToastMessageShower();

    WeatherApi provideWeatherApi();

    LocationManager provideLocationManager();

}
