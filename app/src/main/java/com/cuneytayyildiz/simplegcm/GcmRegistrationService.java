package com.cuneytayyildiz.simplegcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class GcmRegistrationService extends IntentService {

    private Intent gcmIntent;

    public GcmRegistrationService() {
        super(GcmRegistrationService.class.getName());
    }

    public static Intent createGcmRegistrationIntent(Context context) {
        Intent intent = new Intent(context, GcmRegistrationService.class);
        intent.putExtra(Constants.EXTRA_ACTION_CODE, Constants.ACTION_REGISTER_GCM);
        return intent;
    }

    public static Intent createGcmRegistrationIntent(Context context, boolean hasWakeLock) {
        Intent intent = createGcmRegistrationIntent(context);
        intent.putExtra(Constants.EXTRA_HAS_WAKELOCK, hasWakeLock);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        gcmIntent = intent;
        int actionCode = intent.getIntExtra(Constants.EXTRA_ACTION_CODE, 0);
        switch (actionCode) {
            case Constants.ACTION_REGISTER_GCM:
                if (isAlreadyRegistered(getApplicationContext())) {
                    Log.w(Constants.TAG, "The application was registered already before this registration could start.");
                    releaseWakeLock();
                    return;
                }
                registerGcm();
                releaseWakeLock();
                break;
        }
    }

    private void registerGcm() {
        String regId = null;
        String gcmHelperId = SimpleGcm.getGcmSenderId(getApplicationContext());

        int currentBackoff = Constants.DEFAULT_BACKOFF_MS;

        for (int i = 0; i < Constants.MAX_RETRIES; i++) {
            try {
                InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
                regId = instanceID.getToken(gcmHelperId,
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                break;
            } catch (IOException ex) {
                Log.w(Constants.TAG, "Failed to register. Error :" + ex.getMessage());

                if (i < Constants.MAX_RETRIES - 1) {
                    try {
                        Thread.sleep(currentBackoff);
                    } catch (InterruptedException ignored) {
                    }
                    currentBackoff *= 2;
                }
            } catch (SecurityException ex) {
                Log.w(Constants.TAG, "Failed to register. Error :" + ex.getMessage());
            }
        }

        if (regId != null) {
            Log.d(Constants.TAG, "New registration ID=[" + regId + "]");
            SimpleGcm.getInstance().onSuccessfulRegistration(getApplicationContext(), regId);

        } else {
            Log.w(Constants.TAG, "Definitely failed to register after " + Constants.MAX_RETRIES + " retries");
        }
    }

    private boolean isAlreadyRegistered(Context context) {
        return SimpleGcm.isRegistered(context);
    }

    private void releaseWakeLock() {
        if (gcmIntent.getBooleanExtra(Constants.EXTRA_HAS_WAKELOCK, false)) {
            Intent intent = gcmIntent;
            gcmIntent = null;
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        } else {
            gcmIntent = null;
        }
    }
}
