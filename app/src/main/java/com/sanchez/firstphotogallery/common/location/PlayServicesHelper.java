package com.sanchez.firstphotogallery.common.location;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Олександр on 31.01.2017.
 */

final class PlayServicesHelper {

    private PlayServicesHelper() {
        throw new AssertionError();
    }

    public static boolean isAvailable(Context context) {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int code = api.isGooglePlayServicesAvailable(context);
        return code == ConnectionResult.SUCCESS;
    }
}
