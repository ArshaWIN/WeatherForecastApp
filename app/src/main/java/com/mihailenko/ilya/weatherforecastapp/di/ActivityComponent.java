package com.mihailenko.ilya.weatherforecastapp.di;


import android.content.Context;

import com.mihailenko.ilya.weatherforecastapp.di.app.AppComponent;

import dagger.Component;


@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    @ActivityContext
    Context getContext();

}
