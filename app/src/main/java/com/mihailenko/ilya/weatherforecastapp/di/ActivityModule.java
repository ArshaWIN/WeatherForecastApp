package com.mihailenko.ilya.weatherforecastapp.di;


import android.content.Context;
import android.location.LocationManager;

import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;

    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return activity;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }
}
