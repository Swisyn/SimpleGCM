package com.cuneytayyildiz.simplegcm;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;

public class GcmServicesHandler {

    protected void onPlayServicesUnavailable(Activity context, int errorCode, boolean recoverable) {
        GoogleApiAvailability.getInstance().getErrorDialog(context, errorCode, 9000).show();
        Log.e(Constants.TAG, "This device is not supported. Error code " + errorCode + " Recoverable - " + recoverable);
    }
}
