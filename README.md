# SimpleGCM

Simple Google Cloud Messaging(GCM) integration library for Android.

# Why
Sometimes we want to send notifications to our users to notify them about something. 
If we don't have a perfectly working example in our code library, we are always digging stackoverflow or etc. to see which permissions, classes, broadcast receivers, services, listeners that we need to implement our apps with google cloud messaging functionality. 
But sometimes, things get messy.

With this library we can easily integrate GCM to our application with simpliest steps. 

Receiving notification is only thing we care about, right?

# Library Features

* Receive GCM push messages.
* Get registrationId in background

# Usage
Add dependency and plugin to project level build.gradle.
```
dependencies {
    compile 'com.cuneytayyildiz:simplegcm:1.0'
}
apply plugin: 'com.google.gms.google-services'
```

Add classpath and repository to root level build.gradle.
```
dependencies {
    classpath 'com.google.gms:google-services:2.+'
}

repositories {
  mavenCentral()
}

```
* In your `Application` class implement [GcmListener](https://github.com/Swisyn/SimpleGCM/blob/master/app/src/main/java/com/cuneytayyildiz/simplegcm/GcmListener.java) interface with two methods:
  * `onMessage()`
  * `sendRegistrationIdToBackend()`

* In your `MainActivity` `onCreate()`, call `SimpleGcm.init(this);`

If you want to access the `registrationId` after the registration has finished, you can use `SimpleGcm.isRegistered(Context context)`, `SimpleGcm.getRegistrationId(Context context)`


**IMPORTANT STEP**  Download your google-services.json file and copy to app module. GCM will use this .json file to get senderID and other informations. ([Download it from here](https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fclient&cntlbl=Continue%20Adding%20GCM%20Support&%3Fconfigured%3Dtrue))


# References

[Developers Google GCM](https://developers.google.com/cloud-messaging/android/client)

[GCM github page](https://github.com/google/gcm)
