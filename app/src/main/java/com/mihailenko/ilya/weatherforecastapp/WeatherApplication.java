package com.mihailenko.ilya.weatherforecastapp;

import android.app.Application;


import com.mihailenko.ilya.weatherforecastapp.di.app.AppComponent;
import com.mihailenko.ilya.weatherforecastapp.di.app.AppModule;
import com.mihailenko.ilya.weatherforecastapp.di.app.DaggerAppComponent;

import timber.log.Timber;

/**
 * Created by Ilya on 11.06.2017.
 */

public class WeatherApplication extends Application {

    private static WeatherApplication weatherApplication;

    private AppComponent appComponent;

    public static WeatherApplication getApplication() {
        return weatherApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        weatherApplication = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        inject();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void inject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
