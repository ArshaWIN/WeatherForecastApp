package com.mihailenko.ilya.weatherforecastapp.errors;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.interfaces.HasLocalizedMessage;

import java.io.IOException;

/**
 * Created by Ilya on 13.06.2017.
 */

public class ForecastNotFoundError extends Exception implements HasLocalizedMessage {
    @Override
    public String getMessage(@NonNull Context context) {
        return context.getString(R.string.error_forecast);
    }
}
