package com.mihailenko.ilya.weatherforecastapp.common;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;


import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MyLocationManager {
/*
    public static final String GPS_STATUS_CHANGE = "GPS_STATUS_CHANGE";
    private static final long LOCATION_UPDATE_INTERVAL = 500;

    private Location myLocation;

    private ReactiveLocationProvider reactiveLocationProvider;
    private Subscription locationSubscription;


    public MyLocationManager() {

        reactiveLocationProvider = new ReactiveLocationProvider();

    }

    @SuppressWarnings("MissingPermission")
    private void getLastKnownLocation() {
        reactiveLocationProvider.getLastKnownLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> myLocation = location, Throwable::printStackTrace);
    }

    @SuppressWarnings("MissingPermission")
    private void listenToLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(LOCATION_UPDATE_INTERVAL);

        locationSubscription = reactiveLocationProvider
                .getUpdatedLocation(locationRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> {
                    onLocationChangeListener.onLocationChanged(location);
                    myLocation = location;
                }, Throwable::printStackTrace);
    }

    @Override
    public void startListen(Activity activity) {
        if (!isGPSEnabled()) {
            showDialog();
        }
        listenToLocationUpdates();
        lastKnownLocationProvider = (LastKnownLocationProvider) activity;
        registerReceiver();
    }

    @Override
    public void stopListen() {

        if (locationSubscription != null)
            locationSubscription.unsubscribe();

        lastKnownLocationProvider = null;
        unregisterReceiver();
    }


    public Location getLocation() {
        if (!isGPSEnabled()) {
            showDialog();
        }

        if (myLocation == null) {
            getLastKnownLocation();
        }

        return myLocation;
    }


    private boolean isGPSEnabled() {
        final LocationManager manager = (LocationManager) currentActivity.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }


    private void goToEnableGPS() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        currentActivity.startActivity(intent);
    }

    public static class GpsLocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(GPS_STATUS_CHANGE));
            }
        }
    }*/
}