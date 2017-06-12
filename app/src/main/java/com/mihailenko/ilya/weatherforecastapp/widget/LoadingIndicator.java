package com.mihailenko.ilya.weatherforecastapp.widget;

import android.app.Activity;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.databinding.LoadingIndicatorBinding;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;


public class LoadingIndicator extends Dialog {
    private static final int DELAY_SHOW = 300;
    private static final int DELAY_DISMISS = 600;
    private final Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            LoadingIndicator.super.dismiss();
        }
    };
    private final Activity activity;
    private int loadingIndicatorCounter = 0;
    private boolean isDismissed = true;
    private long showedTime;

    private LoadingIndicatorBinding binding;

    private final Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isDismissed) {
                showedTime = System.currentTimeMillis();
                LoadingIndicator.super.show();
            }
        }
    };
    private Timer timer;

    public LoadingIndicator(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void show() {
        isDismissed = false;

        loadingIndicatorCounter++;

        postDelayed(showRunnable, DELAY_SHOW);
    }

    @Override
    public void dismiss() {
        long showingTime = System.currentTimeMillis() - showedTime;
        isDismissed = true;

        loadingIndicatorCounter--;
        if (loadingIndicatorCounter <= 0) {
            if (showingTime < DELAY_DISMISS) {
                postDelayed(dismissRunnable, DELAY_DISMISS - showingTime);
            } else {
                super.dismiss();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getWindow() == null) {
            return;
        }

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.loading_indicator, null, false);
        setContentView(binding.getRoot());

        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    @Override
    public void onDetachedFromWindow() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDetachedFromWindow();
    }

    private void postDelayed(final Runnable runnable, long millis) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();

        timer.schedule(new RunOnUiTimerTask(activity, runnable), millis);
    }

    private static class RunOnUiTimerTask extends TimerTask {
        private final WeakReference<Runnable> runnableRef;
        private final WeakReference<Activity> activityRef;

        public RunOnUiTimerTask(Activity activity, Runnable runnable) {
            runnableRef = new WeakReference<>(runnable);
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            if (activityRef.get() != null && runnableRef.get() != null) {
                activityRef.get().runOnUiThread(runnableRef.get());
            }
        }
    }
}
