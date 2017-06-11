package com.mihailenko.ilya.weatherforecastapp.di.app;

import com.mihailenko.ilya.weatherforecastapp.di.RestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ilya on 11.06.2017.
 */

@Component(modules = {AppModule.class, RestModule.class})
@Singleton
public interface AppComponent {

}
