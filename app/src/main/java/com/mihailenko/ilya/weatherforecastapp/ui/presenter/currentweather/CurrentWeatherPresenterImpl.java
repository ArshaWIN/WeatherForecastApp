package com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather;

import android.location.Location;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.common.MyLocationManager;
import com.mihailenko.ilya.weatherforecastapp.interfaces.LocationListener;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentWeatherView;
import com.mihailenko.ilya.weatherforecastapp.utils.rx.RxSchedulers;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherPresenterImpl extends CurrentWeatherPresenter
        implements LocationListener {

    private final ICurrentWeatherInteractor weatherInteractor;
    private final MyLocationManager myLocationManager;
    private final ReactiveLocationProvider reactiveLocationProvider;

    public CurrentWeatherPresenterImpl(CurrentWeatherView view, ICurrentWeatherInteractor weatherInteractor, MyLocationManager myLocationManager, ReactiveLocationProvider reactiveLocationProvider) {
        super(view);
        this.weatherInteractor = weatherInteractor;
        this.myLocationManager = myLocationManager;
        this.reactiveLocationProvider = reactiveLocationProvider;
    }

    @Override
    public void needWeather() {
        view.onStartProgress();
        myLocationManager.needLocation(this);
    }

    private void getWeather(String city) {
        addSubscription(weatherInteractor.getForecast(city)
                .compose(RxSchedulers.getIOToMainTransformer())
                .doOnSubscribe(view::onStartProgress)
                .doAfterTerminate(view::onEndProgress)
                .subscribe(forecastDayItems -> view.showForecast(forecastDayItems),
                        view::showMessage));
    }

    @Override
    public void onLocationGet(Location location) {
        Timber.d("Get location %s", location);

        reactiveLocationProvider.getReverseGeocodeObservable(location.getLatitude(), location.getLongitude(), 1)
                .compose(RxSchedulers.getIOToMainTransformer())
                .map(addresses -> addresses.get(0))
                .doOnSubscribe(view::onStartProgress)
                .doAfterTerminate(view::onEndProgress)
                .subscribe(address -> onCityRecognized(address.getLocality()), Throwable::printStackTrace);
    }

    private void onCityRecognized(String city) {
        getWeather(city);
        view.setToolbarTittle(city);
    }

    @Override
    public void onGPSDisabled() {
        view.onEndProgress();
        view.onGPSDisabled();
    }

    @Override
    public void onPermissionNeed() {
        view.onEndProgress();
        view.onPermissionNeed();
    }

    @Override
    public void onGPSError() {
        view.onEndProgress();
        view.showGPSError(R.string.error_location);
    }
}
