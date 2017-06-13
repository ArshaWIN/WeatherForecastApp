package com.mihailenko.ilya.weatherforecastapp.data.models.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilya on 11.06.2017.
 */

public class Date {

    @SerializedName("weekday")
    private String weekday;

    @SerializedName("weekday_short")
    private String shortWeekday;

    @SerializedName("day")
    private String day;

    @SerializedName("monthname_short")
    private String month;

    public String getWeekday() {
        return weekday;
    }

    public String getShortWeekday() {
        return shortWeekday;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }
}
