package com.cuneytayyildiz.simplegcm;

import android.os.Bundle;
/**
 * Created by Cuneyt AYYILDIZ on 002, 2.03.2016.
 */
public interface GcmListener {

    void onMessage(String from, Bundle data);

    void sendRegistrationIdToBackend(String registrationId);
}
