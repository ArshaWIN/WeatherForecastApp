package com.mihailenko.ilya.weatherforecastapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilya on 11.06.2017.
 */

public class Date {

    @SerializedName("weekday")
    private String weekday;

    @SerializedName("weekday_short")
    private String shortWeekday;

    public String getWeekday() {
        return weekday;
    }

    public String getShortWeekday() {
        return shortWeekday;
    }
}
