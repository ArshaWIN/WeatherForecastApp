package com.mihailenko.ilya.weatherforecastapp.ui.view.currentweather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.adapter.ForecastAdapter;
import com.mihailenko.ilya.weatherforecastapp.data.item.ForecastDayItem;
import com.mihailenko.ilya.weatherforecastapp.databinding.ActivityCurrentWeatherBinding;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.CurrentWeatherModule;
import com.mihailenko.ilya.weatherforecastapp.di.currentweather.DaggerCurrentWeatherComponent;
import com.mihailenko.ilya.weatherforecastapp.ui.presenter.currentweather.CurrentWeatherPresenter;
import com.mihailenko.ilya.weatherforecastapp.ui.view.base.ToolbarActivity;
import com.mihailenko.ilya.weatherforecastapp.widget.ItemDivider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Ilya on 12.06.2017.
 */
@RuntimePermissions
public class CurrentLocationWeatherActivity extends ToolbarActivity<ActivityCurrentWeatherBinding, CurrentWeatherPresenter>
        implements CurrentWeatherView {

    @Inject
    CurrentWeatherPresenter presenter;
    @Inject
    ForecastAdapter forecastAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    @Named("GPS_DIALOG")
    MaterialDialog gpsDialog;
    @Inject
    ItemDivider itemDivider;


    public static void start(Context context) {
        Intent starter = new Intent(context, CurrentLocationWeatherActivity.class);
        context.startActivity(starter);
    }

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
        initAdapter();

        binding.setHasData(false);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> CurrentLocationWeatherActivityPermissionsDispatcher.needWeatherWithCheck(this));
        CurrentLocationWeatherActivityPermissionsDispatcher.needWeatherWithCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void needWeather() {
        presenter.needWeather();
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void askGPSPermission() {
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    void onNeverAskAgainGPSPermission() {
        showMessage(R.string.never_ask_gps);
        finish();
    }

    @Override
    public void setToolbarTittle(String tittle) {
        super.setToolbarTittle(tittle);
    }

    @Override
    public void onGPSDisabled() {
        gpsDialog.show();
    }

    @Override
    public void onPermissionNeed() {
        CurrentLocationWeatherActivityPermissionsDispatcher.askGPSPermissionWithCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CurrentLocationWeatherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.current_weather;
    }


    private void initAdapter() {
        binding.weatherList.setLayoutManager(layoutManager);
        binding.weatherList.addItemDecoration(itemDivider);
        binding.weatherList.setAdapter(forecastAdapter);
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

    @Override
    public void showForecast(List<ForecastDayItem> forecastDayItems) {
        forecastAdapter.setForecast(forecastDayItems);
        binding.setHasData(!forecastDayItems.isEmpty());
    }

    @Override
    public void startProgress() {
        binding.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void finishProgress() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }
}
