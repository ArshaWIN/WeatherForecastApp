package com.mihailenko.ilya.weatherforecastapp.models.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilya on 12.06.2017.
 */

public class Wind {

    @SerializedName("kph")
    private String speed;

    public String getSpeed() {
        return speed;
    }
}
