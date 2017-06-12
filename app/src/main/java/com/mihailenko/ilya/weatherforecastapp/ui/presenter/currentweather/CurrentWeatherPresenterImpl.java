package com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather;

import com.mihailenko.ilya.weatherforecastapp.business.currentweather.ICurrentWeatherInteractor;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentWeatherView;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentWeatherPresenterImpl extends CurrentWeatherPresenter {

    private final ICurrentWeatherInteractor weatherInteractor;

    public CurrentWeatherPresenterImpl(CurrentWeatherView view, ICurrentWeatherInteractor weatherInteractor) {
        super(view);
        this.weatherInteractor = weatherInteractor;
    }

    @Override
    public void getWeather(String city) {
        addSubscription(weatherInteractor.getForecast(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastDayItems -> view.showForecast(forecastDayItems),
                        Throwable::printStackTrace));

    }
}
