package com.leanplum;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.location.C0523d;
import com.google.android.gms.location.C0523d.C0529a;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationClient.OnAddGeofencesResultListener;
import com.google.android.gms.location.LocationClient.OnRemoveGeofencesResultListener;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

class LocationManagerImpl implements ConnectionCallbacks, C0222c, OnAddGeofencesResultListener, OnRemoveGeofencesResultListener, af {
    private Map<String, Object> f8589a;
    private Map<String, Object> f8590b;
    private LocationClient f8591c;
    private List<C0523d> f8592d;
    private List<C0523d> f8593e;
    private List<String> f8594f;
    private boolean f8595g;

    private LocationManagerImpl() {
        this.f8594f = new ArrayList();
        if (this.f8589a == null) {
            String string = Leanplum.m12431a().getSharedPreferences("__leanplum__location", 0).getString("regionState", null);
            if (string == null) {
                this.f8589a = new HashMap();
            } else {
                this.f8589a = C0625a.m12599a(string);
            }
        }
        this.f8595g = Util.m12579j();
    }

    public final void m12511a(Map<String, Object> map, Set<String> set, Set<String> set2) {
        if (Util.m12578i()) {
            this.f8592d = new ArrayList();
            this.f8593e = new ArrayList();
            for (String str : map.keySet()) {
                boolean contains = set.contains(str);
                boolean contains2 = set2.contains(str);
                if (contains || contains2) {
                    C0523d c0523d;
                    Map map2 = (Map) map.get(str);
                    Number number = (Number) map2.get(Constants.LAT);
                    Number number2 = (Number) map2.get("lon");
                    Number number3 = (Number) map2.get("radius");
                    Number number4 = (Number) map2.get(AppInfo.APP_VERSION_KEY);
                    if (number == null) {
                        c0523d = null;
                    } else {
                        C0529a c0529a = new C0529a();
                        c0529a.m4354a((double) number.floatValue(), (double) number2.floatValue(), number3.floatValue());
                        c0529a.m4357a("__leanplum" + str + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Integer.valueOf(number4.intValue()).toString());
                        c0529a.m4355a(3);
                        c0529a.m4356a(-1);
                        c0523d = c0529a.m4358a();
                    }
                    if (c0523d != null) {
                        if (contains2) {
                            this.f8593e.add(c0523d);
                        }
                        this.f8592d.add(c0523d);
                        if (this.f8589a.get(c0523d.m4261f()) == null) {
                            this.f8589a.put(c0523d.m4261f(), Integer.valueOf(1));
                        }
                    }
                }
            }
            m12506b();
        }
    }

    public final void m12509a() {
        if (this.f8592d != null && this.f8593e != null) {
            m12506b();
        }
    }

    private void m12506b() {
        if ((Leanplum.m12431a().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 ? 1 : null) == null || !m12507c()) {
            Log.d("Leanplum", "You have to set the application meta data and location permission to use geofencing.");
            return;
        }
        Context a = Leanplum.m12431a();
        if (this.f8591c == null) {
            this.f8591c = new LocationClient(a, this, this);
        }
        if (!this.f8591c.isConnected() && !this.f8591c.isConnecting()) {
            this.f8591c.connect();
        } else if (this.f8591c.isConnected()) {
            m12508d();
        }
    }

    private static boolean m12507c() {
        Context a = Leanplum.m12431a();
        try {
            ApplicationInfo applicationInfo = a.getPackageManager().getApplicationInfo(a.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo == null || applicationInfo.metaData == null || applicationInfo.metaData.get("com.google.android.gms.version") == null) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private void m12508d() {
        if (this.f8592d != null) {
            if (!this.f8595g && Util.m12579j()) {
                this.f8590b = new HashMap();
                for (String str : this.f8589a.keySet()) {
                    this.f8590b.put(str, this.f8589a.get(str));
                }
            }
            List<C0523d> list = Util.m12579j() ? this.f8593e : this.f8592d;
            if (this.f8594f.size() > 0) {
                this.f8591c.removeGeofences(this.f8594f, this);
            }
            this.f8594f = new ArrayList();
            if (list.size() > 0) {
                LocationClient locationClient = this.f8591c;
                Context a = Leanplum.m12431a();
                locationClient.addGeofences(list, PendingIntent.getService(a, 0, new Intent(a, ReceiveTransitionsIntentService.class), 134217728), this);
                for (C0523d c0523d : list) {
                    this.f8594f.add(c0523d.m4261f());
                    if (!(!this.f8595g || Util.m12579j() || this.f8590b == null)) {
                        Number number = (Number) this.f8590b.get(c0523d.m4261f());
                        Number number2 = (Number) this.f8589a.get(c0523d.m4261f());
                        if (!(number2 == null || number == null)) {
                            if (C0625a.m12602a(number, number2)) {
                                m12505a(c0523d, "enterRegion");
                            }
                            if (C0625a.m12606b(number, number2)) {
                                m12505a(c0523d, "exitRegion");
                            }
                        }
                    }
                }
            }
            if (this.f8595g && !Util.m12579j()) {
                this.f8590b = null;
            }
            this.f8595g = Util.m12579j();
        }
    }

    public final void m12510a(List<C0523d> list, int i) {
        for (C0523d c0523d : list) {
            if (this.f8594f.contains(c0523d.m4261f()) || !c0523d.m4261f().startsWith("__leanplum")) {
                Number number = (Number) this.f8589a.get(c0523d.m4261f());
                if (number != null) {
                    if (C0625a.m12602a(number, Integer.valueOf(m12504a(i)))) {
                        m12505a(c0523d, "enterRegion");
                    }
                    if (C0625a.m12606b(number, Integer.valueOf(m12504a(i)))) {
                        m12505a(c0523d, "exitRegion");
                    }
                }
                this.f8589a.put(c0523d.m4261f(), Integer.valueOf(m12504a(i)));
            } else {
                List arrayList = new ArrayList();
                arrayList.add(c0523d.m4261f());
                if (this.f8591c != null && this.f8591c.isConnected()) {
                    this.f8591c.removeGeofences(arrayList, this);
                }
            }
        }
        if (this.f8589a != null) {
            Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum__location", 0).edit();
            edit.putString("regionState", C0625a.m12597a(this.f8589a));
            try {
                edit.apply();
            } catch (NoSuchMethodError e) {
                edit.commit();
            }
        }
    }

    private void m12505a(C0523d c0523d, String str) {
        String f = c0523d.m4261f();
        String substring = f.startsWith("__leanplum") ? f.substring(10, f.lastIndexOf(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) : null;
        if (substring != null) {
            int i;
            if (Util.m12579j()) {
                i = 2;
            } else {
                i = 3;
            }
            Leanplum.m12442a(str, substring, i, null, null);
        }
    }

    private static int m12504a(int i) {
        if (i == 1 || i == 4) {
            return 2;
        }
        return 4;
    }

    public void onConnected(Bundle bundle) {
        m12508d();
    }

    public void onDisconnected() {
        this.f8591c = null;
        this.f8591c = new LocationClient(Leanplum.m12431a(), this, this);
        if (!this.f8591c.isConnected() && !this.f8591c.isConnecting()) {
            this.f8591c.connect();
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        connectionResult.m3159a();
    }

    public void onAddGeofencesResult(int i, String[] strArr) {
    }

    public void onRemoveGeofencesByPendingIntentResult(int i, PendingIntent pendingIntent) {
    }

    public void onRemoveGeofencesByRequestIdsResult(int i, String[] strArr) {
    }
}
