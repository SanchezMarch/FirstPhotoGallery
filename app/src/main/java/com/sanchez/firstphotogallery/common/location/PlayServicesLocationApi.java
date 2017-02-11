package com.sanchez.firstphotogallery.common.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Олександр on 31.01.2017.
 */

public class PlayServicesLocationApi extends LocationApi
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static String TAG = LocationManagerApi.class.getCanonicalName();
    private static final int RETRY_TIMEOUT = 5000; // 5 secs

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    public PlayServicesLocationApi(Context context) {
        super(context);

        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void startLocationUpdates(final Accuracy accuracy) {
        switch (accuracy) {
            case LOW:
                locationRequest = new LocationRequest();
                locationRequest.setInterval(30 * 1000); // 30 secs
                locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
                break;
            case HIGH:
                locationRequest = new LocationRequest();
                locationRequest.setInterval(2000);  // 2 sec
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                break;
        }

        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(googleApiClient, locationRequest, this);
        } catch (IllegalStateException e) {
            googleApiClient.connect();
            doWithDelay(new Runnable() {
                @Override
                public void run() {
                    startLocationUpdates(accuracy);
                }
            }, RETRY_TIMEOUT);
        }
    }

    @Override
    public void stopLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        } catch (IllegalStateException e) {
            doWithDelay(new Runnable() {
                @Override
                public void run() {
                    stopLocationUpdates();
                }
            }, RETRY_TIMEOUT);  // Try after 10sec
        }
    }

    @Override
    public boolean isGPSTurnedOn() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }
        LocationAvailability availability = LocationServices
                .FusedLocationApi.getLocationAvailability(googleApiClient);
        return availability.isLocationAvailable();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (locationListener != null) {
            locationListener.onLocationChanged(location);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    private void doWithDelay(Runnable runnable, int delay) {
        new Handler().postDelayed(runnable, delay);
    }
}
