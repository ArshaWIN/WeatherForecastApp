package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.ui.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.common.MyLocationManager;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.currentweather.CurrentLocationWeatherActivity;
import com.mihailenko.ilya.weatherforecastapp.ui.widget.ItemDivider;

import java.util.Collections;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by Ilya on 12.06.2017.
 */

@Module
public class CurrentWeatherModule {

    private CurrentLocationWeatherActivity activity;

    public CurrentWeatherModule(CurrentLocationWeatherActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    MyLocationManager provideMyLocationManager(ReactiveLocationProvider reactiveLocationProvider) {
        return new MyLocationManager(activity, reactiveLocationProvider);
    }

    @PerActivity
    @Provides
    ReactiveLocationProvider provideReactiveLocationProvider() {
        return new ReactiveLocationProvider(activity);
    }

    @PerActivity
    @Provides
    CurrentWeatherPresenter provideCurrentWeatherPresenter(WeatherInteractor weatherInteractor, MyLocationManager myLocationManager, ReactiveLocationProvider reactiveLocationProvider) {
        return new CurrentWeatherPresenter(activity, weatherInteractor, myLocationManager, reactiveLocationProvider);
    }

    @PerActivity
    @Provides
    ItemDivider provideItemDivider() {
        return new ItemDivider(activity, R.drawable.item_divider);
    }

    @PerActivity
    @Provides
    ForecastAdapter provideForecastAdapter() {
        return new ForecastAdapter(Collections.emptyList());
    }

    @PerActivity
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @PerActivity
    @Provides
    @Named("GPS_INTENT")
    Intent provideIntent() {
        return new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    @PerActivity
    @Provides
    @Named("GPS_DIALOG")
    MaterialDialog provideMaterialDialog(@Named("GPS_INTENT") Intent intent) {
        return new MaterialDialog.Builder(activity)
                .content(R.string.gps_disable_text)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive((dialog, which) -> activity.startActivity(intent))
                .build();
    }
}
