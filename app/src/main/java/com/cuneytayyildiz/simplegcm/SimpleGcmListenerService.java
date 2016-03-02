package com.cuneytayyildiz.simplegcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class SimpleGcmListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d(Constants.TAG, "Received message from: " + from);
        SimpleGcm.getInstance().getGcmListener(getApplication()).onMessage(from, data);
    }
}
