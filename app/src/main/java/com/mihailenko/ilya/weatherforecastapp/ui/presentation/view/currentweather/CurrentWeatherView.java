package com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.currentweather;

import com.mihailenko.ilya.weatherforecastapp.models.weather.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.CanShowMessage;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.HasProgressIndication;
import com.mihailenko.ilya.weatherforecastapp.ui.presentation.view.base.BaseView;

import java.util.List;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface CurrentWeatherView extends BaseView, HasProgressIndication, CanShowMessage {
    void showForecast(List<ForecastDayItem> forecastDayItems);

    void setToolbarTittle(String tittle);

    void onGPSDisabled();

    void onPermissionNeed();

}
