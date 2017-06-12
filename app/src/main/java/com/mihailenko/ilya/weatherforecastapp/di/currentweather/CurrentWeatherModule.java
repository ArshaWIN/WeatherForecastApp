package com.mihailenko.ilya.weatherforecastapp.di.currentweather;

import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.CurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.common.MyLocationManager;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.CurrentWeatherRepository;
import com.mihailenko.ilya.weatherforecastapp.data.repositories.ICurrentWeatherRepository;
import com.mihailenko.ilya.weatherforecastapp.di.PerActivity;
import com.mihailenko.ilya.weatherforecastapp.network.WeatherApi;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenterImpl;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentLocationWeatherActivity;

import java.util.Collections;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

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
    MyLocationManager provideMyLocationManager(LocationManager locationManager) {
        return new MyLocationManager(activity, locationManager);
    }

    @PerActivity
    @Provides
    CurrentWeatherPresenter provideCurrentWeatherPresenter(ICurrentWeatherInteractor weatherInteractor, MyLocationManager myLocationManager) {
        return new CurrentWeatherPresenterImpl(activity, weatherInteractor, myLocationManager);
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
    ICurrentWeatherRepository provideWeatherRepository(WeatherApi weatherApi) {
        return new CurrentWeatherRepository(weatherApi);
    }

    @PerActivity
    @Provides
    ICurrentWeatherInteractor provideCurrentWeatherInteractor(ICurrentWeatherRepository weatherRepository) {
        return new CurrentWeatherInteractor(weatherRepository);
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
