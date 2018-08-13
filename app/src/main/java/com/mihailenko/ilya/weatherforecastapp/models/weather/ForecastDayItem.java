package com.mihailenko.ilya.weatherforecastapp.models.weather;

/**
 * Created by Ilya on 12.06.2017.
 */

public class ForecastDayItem {
    public String dayOfWeek;
    public String dayOfWeekShort;
    public String highTemperature;
    public String lowTemperature;
    public String humidity;
    public String conditions;
    public String iconUrl;
    public String windSpeed;
    public String month;
    public String day;

    public ForecastDayItem(Forecast forecast) {
        this.dayOfWeek = forecast.getDate().getWeekday();
        this.dayOfWeekShort = forecast.getDate().getShortWeekday();
        this.highTemperature = forecast.getHighTemperature().getTemperature();
        this.lowTemperature = forecast.getLowTemperature().getTemperature();
        this.humidity = forecast.getHumidity();
        this.iconUrl = forecast.getIconUrl();
        this.windSpeed = forecast.getWind().getSpeed();
        this.conditions = forecast.getConditions();
        this.day = forecast.getDate().getDay();
        this.month = forecast.getDate().getMonth();
    }
}
