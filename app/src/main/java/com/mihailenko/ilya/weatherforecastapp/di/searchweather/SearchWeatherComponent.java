package com.mihailenko.ilya.weatherforecastapp.di.searchweather;

import com.mihailenko.ilya.weatherforecastapp.di.ActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.WeatherModule;
import com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather.SearchWeatherActivity;

import dagger.Component;

/**
 * Created by Ilya on 12.06.2017.
 */

@PerActivity
@Component(dependencies = {ActivityComponent.class}, modules = {SearchWeatherModule.class, WeatherModule.class})
public interface SearchWeatherComponent {
    void inject(SearchWeatherActivity searchWeatherActivity);
}
