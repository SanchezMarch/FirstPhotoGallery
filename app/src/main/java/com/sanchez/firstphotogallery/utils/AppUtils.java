package com.sanchez.firstphotogallery.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by Олександр on 14.01.2017.
 */

public class AppUtils {
    private AppUtils() {
        throw new AssertionError();
    }

    public static void makeToast(Context context, @StringRes int text, boolean isLong) {
        makeToast(context, context.getString(text), isLong);
    }

    public static void makeToast(Context context, String text, boolean isLong) {
        Toast.makeText(
                context,
                text,
                isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT
        ).show();
    }
}
