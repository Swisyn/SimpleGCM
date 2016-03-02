package com.cuneytayyildiz.simplegcm;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Tomáš Isteník on 22.9.2015.
 */
public class Utils {
  
    
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public static int getAppVersion(Context context) {
        try {
            final PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Constants.TAG, e.getMessage());
        }
        return 0;
    }

    public static boolean checkCanAndShouldRegister(Context context) {
        if (!Utils.isOnline(context)) {
            Log.d(Constants.TAG, "Cannot register. Device is not online.");
            return false;
        }

        if (SimpleGcm.isRegistered(context)) {
            Log.d(Constants.TAG, "Registration was already done: " + SimpleGcm.getRegistrationId(context));
            return false;
        }

         int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            Log.e(Constants.TAG, "Play Services are not available: " + resultCode);
            GoogleApiAvailability.getInstance().getErrorDialog((Activity) context, resultCode, 1234).show();
            return false;
        }

        return true;
    }
}
