package ru.smedialink.eagleviewer.ui.interfaces;

import android.content.Context;
import android.support.annotation.NonNull;

public interface HasLocalizedMessage {
    String getMessage(@NonNull Context context);
}
