package ru.smedialink.eagleviewer.ui.interfaces;

public interface HasProgressIndication {
    void onStartProgress();

    void onEndProgress();

    void setProgressText(String progressText);
}
