package com.mihailenko.ilya.weatherforecastapp.ui.view.searchweather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.CitiesAdapter;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivitySearchWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.CurrentWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.DaggerCurrentWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.di.searchweather.DaggerSearchWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.di.searchweather.SearchWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.searchweather.SearchWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.ToolbarActivity;
import com.mihailenko.ilya.weatherforecastapp.widget.LoadingIndicator;

import javax.inject.Inject;

/**
 * Created by Ilya on 12.06.2017.
 */

public class SearchWeatherActivity extends ToolbarActivity<ActivitySearchWeatherBinding, SearchWeatherPresenter>
        implements SearchWeatherView {

    @Inject
    SearchWeatherPresenter presenter;
    @Inject
    CitiesAdapter forecastAdapter;
    @Inject
    LoadingIndicator loadingIndicator;

    @Override
    protected int getToolbarTitle() {
        return R.string.find_city;
    }

    @Override
    public void buildActivityComponentAndInject() {
        DaggerSearchWeatherComponent.builder()
                .activityComponent(getActivityComponent())
                .searchWeatherModule(new SearchWeatherModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ActivitySearchWeatherBinding inflateBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_search_weather);
    }

    @Nullable
    @Override
    public SearchWeatherPresenter getPresenter() {
        return null;
    }

    @Override
    public void onStartProgress() {

    }

    @Override
    public void onEndProgress() {

    }
}
