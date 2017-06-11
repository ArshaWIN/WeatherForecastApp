package com.mihailenko.ilya.weatherforecastapp.di.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mihailenko.ilya.weatherforecastapp.common.ToastMessageShower;
import com.mihailenko.ilya.weatherforecastapp.interfaces.MessageShower;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilya on 11.06.2017.
 */

@Module
public class AppModule {

    private final Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    @Singleton
    MessageShower provideToastMessageShower() {
        return new ToastMessageShower(appContext);
    }

}