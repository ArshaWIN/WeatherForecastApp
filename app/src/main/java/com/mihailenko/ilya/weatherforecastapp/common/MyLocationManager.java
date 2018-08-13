package com.mihailenko.ilya.weatherforecastapp.common;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.mihailenko.ilya.weatherforecastapp.errors.GpsDisabledError;
import com.mihailenko.ilya.weatherforecastapp.errors.NoGpsPermissionsError;
import com.mihailenko.ilya.weatherforecastapp.utils.CheckPermissionUtils;

import io.reactivex.Observable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by Ilya on 12.06.2017.
 */

public class MyLocationManager {

    private Activity activity;
    private ReactiveLocationProvider reactiveLocationProvider;

    public MyLocationManager(Activity activity, ReactiveLocationProvider reactiveLocationProvider) {
        this.activity = activity;
        this.reactiveLocationProvider = reactiveLocationProvider;
    }

    @SuppressWarnings("MissingPermission")
    public Observable<Location> getLastKnownLocation() {
        if (!CheckPermissionUtils.hasGPSPermissions(activity)) {
            return Observable.error(new NoGpsPermissionsError());
        }
        if (!isGPSEnabled()) {
            return Observable.error(new GpsDisabledError());
        }

        return reactiveLocationProvider.getLastKnownLocation();
    }

    private boolean isGPSEnabled() {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return manager != null && manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
