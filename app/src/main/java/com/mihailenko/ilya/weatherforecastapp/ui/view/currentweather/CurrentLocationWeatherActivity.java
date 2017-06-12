package com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivityCurrentWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.CurrentWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.DaggerCurrentWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Ilya on 12.06.2017.
 */

public class CurrentLocationWeatherActivity extends BaseActivity<ActivityCurrentWeatherBinding, CurrentWeatherPresenter>
        implements CurrentWeatherView {

    @Inject
    CurrentWeatherPresenter presenter;

    @Override
    public void buildActivityComponentAndInject() {
        DaggerCurrentWeatherComponent.builder()
                .activityComponent(getActivityComponent())
                .currentWeatherModule(new CurrentWeatherModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ActivityCurrentWeatherBinding inflateBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_current_weather);
    }

    @Nullable
    @Override
    public CurrentWeatherPresenter getPresenter() {
        return presenter;
    }
}
