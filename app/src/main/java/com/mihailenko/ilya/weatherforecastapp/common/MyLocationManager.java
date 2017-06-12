package com.mihailenko.ilya.weatherforecastapp.common;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationRequest;
import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.interfaces.LocationListener;
import com.mihailenko.ilya.weatherforecastapp.utils.CheckPermissionUtils;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Ilya on 12.06.2017.
 */

public class MyLocationManager {
    private static final long LOCATION_UPDATE_INTERVAL = 5000;

    private Activity activity;

    private LocationListener locationListener;
    private ReactiveLocationProvider reactiveLocationProvider;


    private Location myLocation;


    public MyLocationManager(Activity activity) {
        this.activity = activity;
        reactiveLocationProvider = new ReactiveLocationProvider(activity);

        listenToLocationUpdates();

    }

    @SuppressWarnings("MissingPermission")
    private void getLastKnownLocation() {
        reactiveLocationProvider.getLastKnownLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> {
                    myLocation = location;
                    onLocationGet();
                }, throwable -> onGetLocationError());
    }

    @SuppressWarnings("MissingPermission")
    private void listenToLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(LOCATION_UPDATE_INTERVAL);

        reactiveLocationProvider
                .getUpdatedLocation(locationRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> myLocation = location, throwable -> onGetLocationError());
    }

    @SuppressWarnings("MissingPermission")
    public void needLocation(LocationListener locationListener) {
        if (locationListener == null) {
            return;
        }

        this.locationListener = locationListener;
        if (!CheckPermissionUtils.hasGPSPermissions(activity)) {
            locationListener.onPermissionNeed();
            return;
        }
        if (!isGPSEnabled()) {
            locationListener.onGPSDisabled();
            return;
        }

        if (myLocation == null) {
            getLastKnownLocation();
            return;
        }

        onLocationGet();
    }

    private void onGetLocationError() {
        if (locationListener != null) {
            locationListener.onGPSError();
        }
    }

    private void onLocationGet() {
        if (locationListener != null) {
            locationListener.onLocationGet(myLocation);
        }

        locationListener = null;
    }


    private boolean isGPSEnabled() {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }
}
