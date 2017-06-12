package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import com.mihailenko.ilya.weatherforecastapp.di.ActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentLocationWeatherActivity;

import dagger.Component;

/**
 * Created by Ilya on 12.06.2017.
 */

@PerActivity
@Component(dependencies = {ActivityComponent.class}, modules = {CurrentWeatherModule.class})
public interface CurrentWeatherComponent {

    void inject(CurrentLocationWeatherActivity currentLocationWeatherActivity);
}
