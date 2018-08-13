package com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.currentweather;

import com.mihailenko.ilya.weatherforecastapp.business.currentweather.WeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.common.MyLocationManager;
import com.mihailenko.ilya.weatherforecastapp.errors.GpsDisabledError;
import com.mihailenko.ilya.weatherforecastapp.errors.NoGpsPermissionsError;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.presenter.base.BasePresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.currentweather.CurrentWeatherView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherPresenter extends BasePresenter<CurrentWeatherView> {

    private final WeatherInteractor weatherInteractor;
    private final MyLocationManager myLocationManager;

    public CurrentWeatherPresenter(CurrentWeatherView view,
                                   WeatherInteractor weatherInteractor,
                                   MyLocationManager myLocationManager) {
        super(view);
        this.weatherInteractor = weatherInteractor;
        this.myLocationManager = myLocationManager;
    }

    public void needWeather() {
        addDisposable(myLocationManager.getLastKnownLocation()
                .flatMap(myLocationManager::reverseLocationToCity)
                .doOnNext(view::setToolbarTittle)
                .observeOn(Schedulers.io())
                .flatMapSingle(weatherInteractor::getForecast)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(any -> view.startProgress())
                .doFinally(view::finishProgress)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showForecast, this::handleGpsError));
    }

    private void handleGpsError(Throwable throwable) {
        if (throwable instanceof GpsDisabledError) {
            view.onGPSDisabled();
            return;
        }

        if (throwable instanceof NoGpsPermissionsError) {
            view.onPermissionNeed();
            return;
        }

        view.showMessage(throwable);
    }
}
