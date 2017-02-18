package com.sanchez.firstphotogallery.common.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Олександр on 31.01.2017.
 */

public class LocationManagerApi extends LocationApi implements LocationListener {

    private static String TAG = LocationManagerApi.class.getCanonicalName();

    private LocationManager locationManager;
    private String provider = LocationManager.GPS_PROVIDER;

    public LocationManagerApi(Context context) {
        super(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation == null) {
            lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    public void startLocationUpdates(Accuracy accuracy) {
        int minTimeToUpdate = 0, minDistance = 0;
        switch (accuracy) {
            case LOW:
                minTimeToUpdate = 30 * 1000; // 30 secs
                minDistance = 20;
                provider = LocationManager.NETWORK_PROVIDER;
                break;

            case HIGH:
                minTimeToUpdate = 2 * 1000; // 2 sec
                minDistance = 5;
                provider = LocationManager.GPS_PROVIDER;
                break;
        }
        locationManager.requestLocationUpdates(provider, minTimeToUpdate, minDistance, this);
    }

    @Override
    public void stopLocationUpdates() {
        locationManager.removeUpdates(this);
    }

    @Override
    public boolean isGPSTurnedOn() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (locationListener != null) {
            locationListener.onLocationChanged(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "Provider " + s + "was enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "Provider " + s + "was disabled");
    }

}
