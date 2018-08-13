package com.mihailenko.ilya.weatherforecastapp.common;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;

import com.mihailenko.ilya.weatherforecastapp.errors.GpsDisabledError;
import com.mihailenko.ilya.weatherforecastapp.errors.NoGpsPermissionsError;
import com.mihailenko.ilya.weatherforecastapp.utils.CheckPermissionUtils;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by Ilya on 12.06.2017.
 */

public class MyLocationManager {

    private WeakReference<Activity> activity;
    private ReactiveLocationProvider reactiveLocationProvider;

    public MyLocationManager(Activity activity, ReactiveLocationProvider reactiveLocationProvider) {
        this.activity = new WeakReference<>(activity);
        this.reactiveLocationProvider = reactiveLocationProvider;
    }

    @SuppressWarnings("MissingPermission")
    public Observable<Location> getLastKnownLocation() {
        if (!CheckPermissionUtils.hasGPSPermissions(activity.get())) {
            return Observable.error(new NoGpsPermissionsError());
        }
        if (!isGPSEnabled()) {
            return Observable.error(new GpsDisabledError());
        }

        return reactiveLocationProvider.getLastKnownLocation();
    }

    public Observable<String> reverseLocationToCity(Location location) {
        return reactiveLocationProvider.getReverseGeocodeObservable(location.getLatitude(), location.getLongitude(), 1)
                .filter(addresses -> addresses != null && !addresses.isEmpty())
                .map(addresses -> addresses.get(0))
                .map(Address::getLocality);
    }

    private boolean isGPSEnabled() {
        final LocationManager manager = (LocationManager) activity.get().getSystemService(Context.LOCATION_SERVICE);
        return manager != null && manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
