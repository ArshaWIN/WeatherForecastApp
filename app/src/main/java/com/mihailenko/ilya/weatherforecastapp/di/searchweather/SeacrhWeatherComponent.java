package com.mihailenko.ilya.weatherforecastapp.di.searchweather;

import com.mihailenko.ilya.weatherforecastapp.di.ActivityComponent;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;

import dagger.Component;

/**
 * Created by Ilya on 12.06.2017.
 */

@PerActivity
@Component(dependencies = {ActivityComponent.class}, modules = {SearchWeatherModule.class})
public interface SeacrhWeatherComponent {
}
