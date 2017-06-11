package ru.smedialink.eagleviewer.common;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import ru.smedialink.eagleviewer.R;
import ru.smedialink.eagleviewer.exceptions.RxRuntimeException;
import ru.smedialink.eagleviewer.managers.UserSessionManager;
import ru.smedialink.eagleviewer.network.exceptions.UnauthorizedException;
import ru.smedialink.eagleviewer.ui.activities.login.LoginActivity;
import ru.smedialink.eagleviewer.ui.interfaces.MessageShower;

/**
 * Created by ru.smedialink on 23.01.17.
 */

public class ToastMessageShower implements MessageShower {

    private final Context context;
    private final UserSessionManager userSessionManager;

    public ToastMessageShower(Context context, UserSessionManager userSessionManager) {
        this.context = context;
        this.userSessionManager = userSessionManager;
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
        if (throwable.getLocalizedMessage().contains(context.getString(R.string.unauthorized_error_message))) {
            onUnauthorized();
        } else {
            showMessage(throwable.getLocalizedMessage());
        }
    }

    private void onUnauthorized() {
        Toast.makeText(context, R.string.unauthorized_error_message, Toast.LENGTH_SHORT).show();
        userSessionManager.clearData();
        LoginActivity.start(context);
    }
}
