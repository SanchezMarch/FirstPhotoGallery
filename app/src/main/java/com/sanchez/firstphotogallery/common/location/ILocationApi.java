package com.sanchez.firstphotogallery.common.location;

import android.location.Location;

import com.google.android.gms.maps.LocationSource;

/**
 * Created by Олександр on 31.01.2017.
 */

public interface ILocationApi {
    void startLocationUpdates(Accuracy accuracy);

    void stopLocationUpdates();

    boolean isGPSTurnedOn();

    boolean isGPSExists();

    Location getLastLocation();

    void setLocationListener(LocationChangedListener locationListener);
}
