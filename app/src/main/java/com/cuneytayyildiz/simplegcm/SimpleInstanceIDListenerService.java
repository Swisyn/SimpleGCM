/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cuneytayyildiz.simplegcm;

import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

public class SimpleInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        Log.d("IDListenerService","Received token refresh broadcast");
        SimpleGcm.removeRegistrationId(getApplicationContext());
        if (Utils.checkCanAndShouldRegister(getApplicationContext())) {
            startService(GcmRegistrationService.createGcmRegistrationIntent(this));
        }
    }
}
