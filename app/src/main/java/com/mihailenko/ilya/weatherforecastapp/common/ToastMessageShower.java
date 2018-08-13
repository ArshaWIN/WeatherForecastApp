package com.mihailenko.ilya.weatherforecastapp.common;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;


import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.HasLocalizedMessage;
import com.mihailenko.ilya.weatherforecastapp.ui.interfaces.MessageShower;


/**
 * Created by ru.smedialink on 23.01.17.
 */

public class ToastMessageShower implements MessageShower {

    private final Context context;

    public ToastMessageShower(Context context) {
        this.context = context;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int messageRes) {
        showMessage(context.getString(messageRes));
    }

    @Override
    public void showMessage(Throwable throwable) {
        if (throwable instanceof HasLocalizedMessage) {
            showMessage(((HasLocalizedMessage) throwable).getMessage(context));
            return;
        }
        showMessage(R.string.unexpected_error);
    }

}
