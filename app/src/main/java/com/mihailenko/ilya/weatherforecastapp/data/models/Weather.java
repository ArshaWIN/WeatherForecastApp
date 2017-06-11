package com.mihailenko.ilya.weatherforecastapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ilya on 11.06.2017.
 */

public class Weather {

    @SerializedName("conditions")
    private String conditions;

    @SerializedName("high")
    private Temperature highTemperature;

    @SerializedName("low")
    private Temperature lowTemperature;

    @SerializedName("date")
    private Date date;

    @SerializedName("avehumidity")
    private String humidity;

    @SerializedName("icon_url")
    private String iconUrl;

    public String getConditions() {
        return conditions;
    }

    public Temperature getHighTemperature() {
        return highTemperature;
    }

    public Temperature getLowTemperature() {
        return lowTemperature;
    }

    public Date getDate() {
        return date;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
