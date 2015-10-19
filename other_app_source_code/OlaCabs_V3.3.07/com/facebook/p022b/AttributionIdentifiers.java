package com.facebook.p022b;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookException;
import com.olacabs.customer.utils.Constants;
import java.lang.reflect.Method;

/* renamed from: com.facebook.b.b */
public class AttributionIdentifiers {
    private static final String f731a;
    private static final Uri f732b;
    private static AttributionIdentifiers f733g;
    private String f734c;
    private String f735d;
    private boolean f736e;
    private long f737f;

    static {
        f731a = AttributionIdentifiers.class.getCanonicalName();
        f732b = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    }

    private static AttributionIdentifiers m883b(Context context) {
        AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method a = Utility.m1105a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (a == null) {
                return attributionIdentifiers;
            }
            Object a2 = Utility.m1094a(null, a, context);
            if (!(a2 instanceof Integer) || ((Integer) a2).intValue() != 0) {
                return attributionIdentifiers;
            }
            a = Utility.m1105a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
            if (a == null) {
                return attributionIdentifiers;
            }
            Object a3 = Utility.m1094a(null, a, context);
            if (a3 == null) {
                return attributionIdentifiers;
            }
            a = Utility.m1104a(a3.getClass(), "getId", new Class[0]);
            Method a4 = Utility.m1104a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
            if (a == null || a4 == null) {
                return attributionIdentifiers;
            }
            attributionIdentifiers.f735d = (String) Utility.m1094a(a3, a, new Object[0]);
            attributionIdentifiers.f736e = ((Boolean) Utility.m1094a(a3, a4, new Object[0])).booleanValue();
            return attributionIdentifiers;
        } catch (Exception e) {
            Utility.m1119a("android_id", e);
        }
    }

    public static AttributionIdentifiers m882a(Context context) {
        Cursor query;
        Exception e;
        Throwable th;
        if (f733g != null && System.currentTimeMillis() - f733g.f737f < Constants.MILLIS_IN_AN_HOUR) {
            return f733g;
        }
        AttributionIdentifiers b = AttributionIdentifiers.m883b(context);
        try {
            query = context.getContentResolver().query(f732b, new String[]{"aid", "androidid", "limit_tracking"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("aid");
                        int columnIndex2 = query.getColumnIndex("androidid");
                        int columnIndex3 = query.getColumnIndex("limit_tracking");
                        b.f734c = query.getString(columnIndex);
                        if (columnIndex2 > 0 && columnIndex3 > 0 && b.m885b() == null) {
                            b.f735d = query.getString(columnIndex2);
                            b.f736e = Boolean.parseBoolean(query.getString(columnIndex3));
                        }
                        if (query != null) {
                            query.close();
                        }
                        b.f737f = System.currentTimeMillis();
                        f733g = b;
                        return b;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.d(f731a, "Caught unexpected exception in getAttributionId(): " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return b;
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.d(f731a, "Caught unexpected exception in getAttributionId(): " + e.toString());
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public String m884a() {
        return this.f734c;
    }

    public String m885b() {
        return this.f735d;
    }

    public boolean m886c() {
        return this.f736e;
    }
}
