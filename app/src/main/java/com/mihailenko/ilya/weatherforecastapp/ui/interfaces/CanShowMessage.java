package com.mihailenko.ilya.weatherforecastapp.ui.interfaces;

import android.support.annotation.StringRes;

public interface CanShowMessage {
    void showMessage(String message);

    void showMessage(@StringRes int messageRes);

    void showMessage(Throwable throwable);
}
