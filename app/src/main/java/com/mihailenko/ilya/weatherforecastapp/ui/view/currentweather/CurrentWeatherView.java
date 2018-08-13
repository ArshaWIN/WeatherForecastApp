package com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.interfaces.CanShowMessage;
import com.mihailenko.ilya.weatherforecastapp.interfaces.HasProgressIndication;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseView;

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
