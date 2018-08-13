package com.mihailenko.ilya.weatherforecastapp.di;


import android.content.Context;

import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.base.BaseActivity;
import com.mihailenko.ilya.weatherforecastapp.ui.widget.LoadingIndicator;

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

    @PerBaseActivity
    @Provides
    LoadingIndicator provideLoadingIndicator() {
        return new LoadingIndicator(activity);
    }
}
