package com.cuneytayyildiz.simplegcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class NetworkStateReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.d(Constants.TAG,"Connection state changed event received...");

        if (Utils.checkCanAndShouldRegister(context)) {
            Intent serviceIntent = GcmRegistrationService.createGcmRegistrationIntent(context, true);
            startWakefulService(context, serviceIntent);
        }
    }
}
