package com.cuneytayyildiz.simplegcm;

/**
 * Created by Cuneyt AYYILDIZ on 002, 2.03.2016.
 */
public class Constants {
    public static final String TAG = "SimpleGCM";
    public static final String EXTRA_ACTION_CODE = "actionCode";
    public static final String EXTRA_HAS_WAKELOCK = "hasWakeLock";

    public static final int ACTION_REGISTER_GCM = 1;

    public static final int MAX_RETRIES = 5;
    public static final int DEFAULT_BACKOFF_MS = 2000;
}
