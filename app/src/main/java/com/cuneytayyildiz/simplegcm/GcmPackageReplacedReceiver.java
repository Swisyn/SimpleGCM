package com.cuneytayyildiz.simplegcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
/**
 * Created by Cuneyt AYYILDIZ on 002, 2.03.2016.
 */
public class GcmPackageReplacedReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        if (intent != null && intent.getAction().equals(Intent.ACTION_MY_PACKAGE_REPLACED)) {
          Log.d(Constants.TAG,"Received application update broadcast");

            if (Utils.checkCanAndShouldRegister(context)) {
                Intent serviceIntent = GcmRegistrationService.createGcmRegistrationIntent(context, true);
                startWakefulService(context, serviceIntent);
            }
        }
    }
}
