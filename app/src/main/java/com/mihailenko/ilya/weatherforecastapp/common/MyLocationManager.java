package com.mihailenko.ilya.weatherforecastapp.common;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.mihailenko.ilya.weatherforecastapp.R;
import com.mihailenko.ilya.weatherforecastapp.interfaces.LocationListener;
import com.mihailenko.ilya.weatherforecastapp.utils.CheckPermissionUtils;

/**
 * Created by Ilya on 12.06.2017.
 */

public class MyLocationManager implements android.location.LocationListener {
    private Activity activity;

    private LocationManager locationManager;
    private LocationListener locationListener;

    public MyLocationManager(Activity activity, LocationManager locationManager) {
        this.activity = activity;
        this.locationManager = locationManager;
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
        // check if gps enabled
        final String gpsProvider = LocationManager.GPS_PROVIDER;
        if (!locationManager.isProviderEnabled(gpsProvider)) {
            locationListener.onGPSDisabled();
            return;
        }
        // check location
        final Location location = locationManager.getLastKnownLocation(gpsProvider);
        if (null == location) {
            locationListener.onGPSError();
            locationManager.requestLocationUpdates(gpsProvider, 0, 0, this);
            return;
        }

        locationListener.onLocationGet(location);

    }


    @Override
    public void onLocationChanged(Location location) {
        if (locationListener == null || CheckPermissionUtils.hasGPSPermissions(activity)) {
            return;
        }

        locationManager.removeUpdates(this);
        locationListener.onLocationGet(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (locationListener == null) {
            return;
        }

        locationListener.onGPSDisabled();
    }
}
