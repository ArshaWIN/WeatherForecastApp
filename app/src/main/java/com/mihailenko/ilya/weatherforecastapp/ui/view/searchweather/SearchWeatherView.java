package com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather;

import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.interfaces.CanShowMessage;
import com.mihailenko.ilya.weatherforecastapp.interfaces.HasProgressIndication;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseView;

import java.util.List;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface SearchWeatherView extends BaseView, HasProgressIndication, CanShowMessage {
    void showForecast(List<ForecastDayItem> forecastDayItems);

    void setToolbarTittle(int title);

    void showCitySuggestions(List<String> cities);
}
