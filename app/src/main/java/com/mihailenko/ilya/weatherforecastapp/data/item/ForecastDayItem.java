package com.mihailenko.ilya.weatherforecastapp.data.item;

import com.mihailenko.ilya.weatherforecastapp.data.models.weather.Forecast;

/**
 * Created by Ilya on 12.06.2017.
 */

public class ForecastDayItem {
    public String day;
    public String dayShort;
    public String highTemperature;
    public String lowTemperature;
    public String humidity;
    public String conditions;
    public String iconUrl;
    public String windSpeed;

    public ForecastDayItem(Forecast forecast) {
        this.day = forecast.getDate().getWeekday();
        this.dayShort = forecast.getDate().getShortWeekday();
        this.highTemperature = forecast.getHighTemperature().getTemperature();
        this.lowTemperature = forecast.getLowTemperature().getTemperature();
        this.humidity = forecast.getHumidity();
        this.iconUrl = forecast.getIconUrl();
        this.windSpeed = forecast.getWind().getSpeed();
        this.conditions = forecast.getConditions();
    }
}
