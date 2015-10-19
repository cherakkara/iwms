package com.apsalar.sdk;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.C0263c;
import com.google.android.gms.common.C0265d;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.IOException;

class AIFA_Helper {
    static final String TAG = "Apsalar SDK/AIFA_Helper";

    AIFA_Helper() {
    }

    public static String getAIFA() {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.playStoreAvailable = googleServicesOk(context);
        if (!instance.playStoreAvailable) {
            return Trace.NULL;
        }
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            instance.isLAT = advertisingIdInfo.isLimitAdTrackingEnabled();
            return advertisingIdInfo.getId();
        } catch (IOException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return Trace.NULL;
        } catch (C0263c e2) {
            instance.incrExceptionCount();
            instance.getClass();
            return Trace.NULL;
        } catch (C0265d e3) {
            instance.incrExceptionCount();
            instance.getClass();
            return Trace.NULL;
        } catch (ReceiverCallNotAllowedException e4) {
            instance.incrExceptionCount();
            instance.getClass();
            if (instance.AIFA != null) {
                return instance.AIFA;
            }
            return Trace.NULL;
        } catch (Exception e5) {
            instance.incrExceptionCount();
            instance.getClass();
            if (instance.AIFA != null) {
                return instance.AIFA;
            }
            return Trace.NULL;
        }
    }

    public static boolean googleServicesOk(Context context) {
        boolean z;
        boolean z2 = true;
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        try {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            instance.getClass();
            z = true;
        } catch (ClassNotFoundException e) {
            instance.getClass();
            z = false;
        } catch (Exception e2) {
            instance.getClass();
            z = false;
        }
        instance.ctx = context;
        if (!z) {
            z2 = false;
        }
        instance.playStoreAvailable = z2;
        instance.getClass();
        if (!instance.playStoreAvailable && instance.canonicalKeyspace.equals("AIFA")) {
            instance.getClass();
            instance.canonicalKeyspace = "ANDI";
        }
        return instance.playStoreAvailable;
    }
}
