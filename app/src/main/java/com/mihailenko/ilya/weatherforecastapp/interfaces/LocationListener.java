package com.mihailenko.ilya.weatherforecastapp.interfaces;

import android.location.Location;

/**
 * Created by Ilya on 12.06.2017.
 */

public interface LocationListener {
    void onLocationGet(Location location);

    void onGPSDisabled();

    void onPermissionNeed();

    void onGPSError();
}
