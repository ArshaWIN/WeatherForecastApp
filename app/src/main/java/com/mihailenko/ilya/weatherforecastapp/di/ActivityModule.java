package ru.smedialink.eagleviewer.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.smedialink.eagleviewer.common.MaterialDialogsShower;
import ru.smedialink.eagleviewer.ui.activities.base.BaseActivityMVP;
import ru.smedialink.eagleviewer.ui.interfaces.DialogShower;
import ru.smedialink.eagleviewer.ui.widgets.LoadingIndicator;

@Module
public class ActivityModule {
    private BaseActivityMVP activity;

    public ActivityModule(BaseActivityMVP activity) {
        this.activity = activity;

    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return activity;
    }

    @PerBaseActivity
    @Provides
    LoadingIndicator provideLoadingIndicator() {
        return new LoadingIndicator(activity);
    }

    @PerBaseActivity
    @Provides
    DialogShower provideDialogShower() {
        return new MaterialDialogsShower(activity);
    }
}
