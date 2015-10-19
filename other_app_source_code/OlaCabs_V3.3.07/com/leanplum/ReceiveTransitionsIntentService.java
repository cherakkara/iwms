package com.leanplum;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.location.LocationClient;
import java.util.List;

public class ReceiveTransitionsIntentService extends IntentService {
    public ReceiveTransitionsIntentService() {
        super("ReceiveTransitionsIntentService");
    }

    protected void onHandleIntent(Intent intent) {
        if (LocationClient.hasError(intent)) {
            Log.d("Leanplum", "Location Client Error with code: " + LocationClient.getErrorCode(intent));
            return;
        }
        int geofenceTransition = LocationClient.getGeofenceTransition(intent);
        List triggeringGeofences = LocationClient.getTriggeringGeofences(intent);
        if (geofenceTransition == 1 || geofenceTransition == 2) {
            LocationManagerImpl locationManagerImpl = (LocationManagerImpl) C0629c.m12768b();
            if (locationManagerImpl != null) {
                locationManagerImpl.m12510a(triggeringGeofences, geofenceTransition);
            }
        }
    }
}
