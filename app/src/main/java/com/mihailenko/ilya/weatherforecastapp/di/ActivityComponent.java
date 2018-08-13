package com.mihailenko.ilya.weatherforecastapp.di;


import android.content.Context;

import com.mihailenko.ilya.weatherforecastapp.di.app.AppComponent;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.MessageShower;
import com.mihailenko.ilya.weatherforecastapp.network.places.GooglePlacesApi;
import com.mihailenko.ilya.weatherforecastapp.network.weather.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.ui.widget.LoadingIndicator;

import dagger.Component;


@PerBaseActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    Context getContext();

    MessageShower provideToastMessageShower();

    WeatherApi provideWeatherApi();

    LoadingIndicator provideLoadingIndicator();

    GooglePlacesApi providePlacesApi();

}
