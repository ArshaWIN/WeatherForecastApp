package com.mihailenko.ilya.weatherforecastapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilya on 11.06.2017.
 */

public class Temperature {
    @SerializedName("celsius")
    private String temperature;

    public String getTemperature() {
        return temperature;
    }
}