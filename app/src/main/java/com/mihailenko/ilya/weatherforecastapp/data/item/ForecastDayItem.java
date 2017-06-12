package com.mihailenko.ilya.weatherforecastapp.data.item;

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

    public ForecastDayItem(String day, String dayShort, String highTemperature, String lowTemperature, String humidity, String iconUrl, String windSpeed, String conditions) {
        this.day = day;
        this.dayShort = dayShort;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.humidity = humidity;
        this.iconUrl = iconUrl;
        this.windSpeed = windSpeed;
        this.conditions = conditions;
    }
}
