package com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather;

import android.location.Location;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.common.MyLocationManager;
import com.mihailenko.ilya.weatherforecastapp.interfaces.LocationListener;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherPresenterImpl extends CurrentWeatherPresenter
        implements LocationListener {

    private final ICurrentWeatherInteractor weatherInteractor;
    private final MyLocationManager myLocationManager;

    public CurrentWeatherPresenterImpl(CurrentWeatherView view, ICurrentWeatherInteractor weatherInteractor, MyLocationManager myLocationManager) {
        super(view);
        this.weatherInteractor = weatherInteractor;
        this.myLocationManager = myLocationManager;

    }

    @Override
    public void needWeather() {
        myLocationManager.needLocation(this);

    }

    private void getWeather(String city) {
        addSubscription(weatherInteractor.getForecast(city)
                .compose(RxSchedulers.getIOToMainTransformer())
                .subscribe(forecastDayItems -> view.showForecast(forecastDayItems),
                        view::showMessage));
    }

    @Override
    public void onLocationGet(Location location) {
        Timber.d("Get location %s", location);
    }

    @Override
    public void onGPSDisabled() {
        view.onGPSDisabled();
    }

    @Override
    public void onPermissionNeed() {
        view.onPermissionNeed();
    }

    @Override
    public void onGPSError() {
        view.showGPSError(R.string.error_location);
    }
}
