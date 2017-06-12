package com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather;

import com.mihailenko.ilya.weatherforecastapp.ui.presenter.base.BasePresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather.CurrentWeatherView;

/**
 * Created by Ilya on 12.06.2017.
 */

public abstract class CurrentWeatherPresenter extends BasePresenter<CurrentWeatherView> {

    public CurrentWeatherPresenter(CurrentWeatherView view) {
        super(view);
    }

    public abstract void getWeather(String city);
}
