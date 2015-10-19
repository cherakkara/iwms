package com.olacabs.customer.p075a;

import android.content.Context;
import android.os.Bundle;
import com.facebook.p023a.AppEventsLogger;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.olacabs.customer.a.c */
public class FBAnalyticsHelper implements AnalyticsHelper {
    private Context f8964a;

    protected FBAnalyticsHelper(Context context) {
        this.f8964a = context.getApplicationContext();
    }

    public void m12870a(String str) {
        AppEventsLogger.m830a(this.f8964a).m853a(str);
    }

    public void m12871a(String str, Map map) {
        Bundle bundle = new Bundle();
        for (Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        AppEventsLogger.m830a(this.f8964a).m855a(str, bundle);
    }
}
