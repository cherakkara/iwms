package com.leanplum;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.utils.Constants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.leanplum.S */
class C0618S {
    private static long f8605a;
    private static String f8606b;
    private static String f8607c;
    private static String f8608d;
    private static String f8609e;
    private static Map<String, Boolean> f8610f;
    private static int f8611g;
    private static C0624Z f8612h;
    private static String f8613i;
    private static Object f8614j;
    private static Map<File, Long> f8615k;
    private static Map<File, Double> f8616l;
    private static String f8617m;
    private static long f8618n;
    private static Object f8619o;
    private String f8620p;
    private String f8621q;
    private Map<String, String> f8622r;
    private aa f8623s;
    private C0602Y f8624t;
    private boolean f8625u;

    static {
        f8605a = 5000;
        f8610f = new HashMap();
        f8613i = null;
        f8614j = C0618S.class;
        f8615k = new HashMap();
        f8616l = new HashMap();
        f8617m = Trace.NULL;
        f8619o = new Object();
    }

    public static void m12526a(String str, String str2) {
        f8606b = str;
        f8607c = str2;
    }

    public static void m12525a(String str) {
        f8608d = str;
    }

    public static void m12534b(String str) {
        f8609e = str;
    }

    public static void m12538c(String str) {
        f8613i = str;
    }

    public static String m12519a() {
        return f8613i;
    }

    public static String m12532b() {
        return f8606b;
    }

    public static String m12537c() {
        return f8608d;
    }

    public static String m12540d() {
        return f8609e;
    }

    C0618S(String str, String str2, Map<String, String> map) {
        this.f8620p = str;
        this.f8621q = str2;
        if (map == null) {
            map = new HashMap();
        }
        this.f8622r = map;
    }

    public static C0618S m12518a(String str, Map<String, String> map) {
        ak.m12732a();
        return ak.m12731a("GET", str, null);
    }

    public static C0618S m12530b(String str, Map<String, String> map) {
        ak.m12732a();
        return ak.m12731a("POST", str, map);
    }

    public final void m12551a(aa aaVar) {
        this.f8623s = aaVar;
    }

    public final void m12550a(C0602Y c0602y) {
        this.f8624t = c0602y;
    }

    private Map<String, String> m12546m() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("deviceId", f8608d);
        hashMap.put(Constants.PREF_USER_ID, f8609e);
        hashMap.put("action", this.f8621q);
        hashMap.put("sdkVersion", C0633g.f8800g);
        hashMap.put("devMode", C0633g.f8804k);
        hashMap.put("time", (((double) new Date().getTime()) / 1000.0d));
        if (f8613i != null) {
            hashMap.put("token", f8613i);
        }
        hashMap.putAll(this.f8622r);
        return hashMap;
    }

    private static void m12529a(Map<String, String> map) {
        synchronized (f8614j) {
            SharedPreferences sharedPreferences = Leanplum.m12431a().getSharedPreferences("__leanplum__", 0);
            Editor edit = sharedPreferences.edit();
            edit.putString(String.format(Locale.US, "__leanplum_unsynced_%d", new Object[]{Integer.valueOf(sharedPreferences.getInt("__leanplum_unsynced", 0))}), C0625a.m12597a((Map) map));
            edit.putInt("__leanplum_unsynced", r0 + 1);
            try {
                edit.apply();
            } catch (NoSuchMethodError e) {
                edit.commit();
            }
        }
    }

    final void m12554e() {
        m12547n();
        if (C0633g.f8804k) {
            long currentTimeMillis = System.currentTimeMillis();
            if (f8618n == 0 || currentTimeMillis - f8618n > f8605a) {
                currentTimeMillis = 100;
            } else {
                currentTimeMillis = (f8618n + f8605a) - currentTimeMillis;
            }
            C0612M.m12512a().m12514a(new C0619T(this), currentTimeMillis);
        }
    }

    final void m12555f() {
        m12547n();
        C0612M.m12512a().m12514a(new C0620U(this), 1000);
    }

    final void m12556g() {
        if (C0633g.f8804k) {
            m12554e();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (f8618n == 0 || currentTimeMillis - f8618n > Constants.MILLIS_IN_A_MINUTE) {
            m12557h();
        }
    }

    final void m12557h() {
        if (!Util.m12577h()) {
            m12547n();
            Log.i("Leanplum", "Device is offline, will send later");
            if (this.f8624t != null) {
                C0602Y c0602y = this.f8624t;
                Exception exception = new Exception("Not connected to the Internet");
                c0602y.m12411a();
            }
        } else if (!C0633g.f8805l) {
            if (f8606b == null) {
                Log.e("Leanplum", "Cannot send request. appId is not set");
            } else if (f8607c == null) {
                Log.e("Leanplum", "Cannot send request. accessKey is not set");
            } else {
                m12547n();
                List o = C0618S.m12548o();
                if (o.size() != 0) {
                    Map hashMap = new HashMap();
                    Map hashMap2 = new HashMap();
                    hashMap2.put("data", o);
                    hashMap.put("data", C0625a.m12597a(hashMap2));
                    hashMap.put("sdkVersion", C0633g.f8800g);
                    hashMap.put("action", "multi");
                    hashMap.put("time", (((double) new Date().getTime()) / 1000.0d));
                    if (C0618S.m12535b(hashMap)) {
                        Util.m12568a(new C0621V(this, hashMap, o), new Void[0]);
                    }
                }
            }
        }
    }

    private static boolean m12535b(Map<String, String> map) {
        if (f8606b == null || f8607c == null) {
            Log.e("Leanplum", "API keys are not set. Please use Leanplum.setAppIdForDevelopmentMode or Leanplum.setAppIdForProductionMode");
            return false;
        }
        map.put(AnalyticAttribute.APP_ID_ATTRIBUTE, f8606b);
        map.put("clientKey", f8607c);
        map.put(ao.CLIENT_ID_KEY, C0633g.f8801h);
        return true;
    }

    private void m12547n() {
        if (!C0633g.f8805l && !this.f8625u) {
            this.f8625u = true;
            C0618S.m12529a(m12546m());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.util.Map<java.lang.String, java.lang.String>> m12548o() {
        /*
        r1 = 0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r3 = f8614j;
        monitor-enter(r3);
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x003b }
        f8618n = r4;	 Catch:{ all -> 0x003b }
        r2 = com.leanplum.Leanplum.m12431a();	 Catch:{ all -> 0x003b }
        r4 = "__leanplum__";
        r5 = 0;
        r4 = r2.getSharedPreferences(r4, r5);	 Catch:{ all -> 0x003b }
        r5 = r4.edit();	 Catch:{ all -> 0x003b }
        r2 = "__leanplum_unsynced";
        r6 = 0;
        r6 = r4.getInt(r2, r6);	 Catch:{ all -> 0x003b }
        if (r6 != 0) goto L_0x002e;
    L_0x0027:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x003b }
        r0.<init>();	 Catch:{ all -> 0x003b }
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
    L_0x002d:
        return r0;
    L_0x002e:
        r2 = "__leanplum_unsynced";
        r5.remove(r2);	 Catch:{ all -> 0x003b }
        r2 = r1;
    L_0x0034:
        if (r2 < r6) goto L_0x003e;
    L_0x0036:
        r5.apply();	 Catch:{ NoSuchMethodError -> 0x006f }
    L_0x0039:
        monitor-exit(r3);	 Catch:{ all -> 0x003b }
        goto L_0x002d;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x003e:
        r1 = java.util.Locale.US;	 Catch:{ all -> 0x003b }
        r7 = "__leanplum_unsynced_%d";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x003b }
        r9 = 0;
        r10 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x003b }
        r8[r9] = r10;	 Catch:{ all -> 0x003b }
        r7 = java.lang.String.format(r1, r7, r8);	 Catch:{ all -> 0x003b }
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x006a }
        r1 = "{}";
        r1 = r4.getString(r7, r1);	 Catch:{ JSONException -> 0x006a }
        r1 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r1);	 Catch:{ JSONException -> 0x006a }
        r1 = com.leanplum.C0625a.m12600a(r1);	 Catch:{ JSONException -> 0x006a }
        r0.add(r1);	 Catch:{ JSONException -> 0x006a }
    L_0x0063:
        r5.remove(r7);	 Catch:{ all -> 0x003b }
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x0034;
    L_0x006a:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0063;
    L_0x006f:
        r1 = move-exception;
        r5.commit();	 Catch:{ all -> 0x003b }
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.S.o():java.util.List<java.util.Map<java.lang.String, java.lang.String>>");
    }

    static void m12528a(List<Map<String, String>> list) {
        for (Map a : list) {
            C0618S.m12529a(a);
        }
    }

    private static String m12520a(int i) {
        if (i < Defaults.RESPONSE_BODY_LIMIT) {
            return new StringBuilder(String.valueOf(i)).append(" B").toString();
        }
        if (i < AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START) {
            return (i >> 10) + " KB";
        }
        return (i >> 20) + " MB";
    }

    private static void m12549p() {
        int size = f8615k.size();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (File file : f8615k.keySet()) {
            long longValue = ((Long) f8615k.get(file)).longValue();
            double doubleValue = ((Double) f8616l.get(file)).doubleValue();
            if (doubleValue == 1.0d) {
                i3++;
            }
            i = ((int) (doubleValue * ((double) longValue))) + i;
            i2 = (int) (((long) i2) + longValue);
        }
        String str = "Uploading resources. " + i3 + '/' + size + " files completed; " + C0618S.m12520a(i) + '/' + C0618S.m12520a(i2) + " transferred.";
        if (!f8617m.equals(str)) {
            f8617m = str;
            Log.i("Leanplum", str);
        }
    }

    public final void m12552a(List<String> list, List<InputStream> list2) {
        if (!C0633g.f8805l) {
            Map m = m12546m();
            if (C0618S.m12535b(m)) {
                List arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    String str = (String) list.get(i);
                    if (!Boolean.TRUE.equals(f8610f.get(str))) {
                        long available;
                        File file = new File(str);
                        try {
                            available = (long) ((InputStream) list2.get(i)).available();
                        } catch (IOException e) {
                            available = file.length();
                        } catch (NullPointerException e2) {
                            Log.e("Leanplum", "Unable to read file " + str);
                        }
                        f8610f.put(str, Boolean.valueOf(true));
                        arrayList.add(file);
                        f8615k.put(file, Long.valueOf(available));
                        f8616l.put(file, Double.valueOf(0.0d));
                    }
                }
                if (arrayList.size() != 0) {
                    C0618S.m12549p();
                    Util.m12568a(new C0622W(this, arrayList, list2, m), new Void[0]);
                }
            }
        }
    }

    final void m12553d(String str) {
        if (!C0633g.f8805l && !Boolean.TRUE.equals(f8610f.get(str))) {
            f8611g++;
            Log.i("Leanplum", "Downloading resource " + str);
            f8610f.put(str, Boolean.valueOf(true));
            Map m = m12546m();
            m.put("filename", str);
            if (C0618S.m12535b(m)) {
                Util.m12568a(new C0623X(this, str, m), new Void[0]);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m12527a(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
        r7 = this;
        r6 = 0;
        r3 = r7.f8620p;	 Catch:{ Exception -> 0x0109, all -> 0x0103 }
        r4 = com.leanplum.C0633g.f8797d;	 Catch:{ Exception -> 0x0109, all -> 0x0103 }
        r5 = com.leanplum.C0633g.f8799f;	 Catch:{ Exception -> 0x0109, all -> 0x0103 }
        r0 = r8;
        r1 = r9;
        r2 = r11;
        r2 = com.leanplum.Util.m12565a(r0, r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0109, all -> 0x0103 }
        r1 = r2.getURL();	 Catch:{ Exception -> 0x010d, all -> 0x00b2 }
        r2.connect();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r2.getResponseCode();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 == r3) goto L_0x0063;
    L_0x001d:
        r3 = new java.lang.Exception;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r5 = "Leanplum: Error sending request to: ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4 = r4.append(r8);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r5 = ", HTTP status code: ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        throw r3;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
    L_0x003c:
        r0 = move-exception;
        r6 = r2;
    L_0x003e:
        r2 = r0 instanceof java.io.EOFException;	 Catch:{ all -> 0x0106 }
        if (r2 == 0) goto L_0x00cc;
    L_0x0042:
        r2 = r6.getURL();	 Catch:{ all -> 0x0106 }
        r1 = r2.equals(r1);	 Catch:{ all -> 0x0106 }
        if (r1 != 0) goto L_0x00cc;
    L_0x004c:
        r0 = 0;
        r1 = r6.getURL();	 Catch:{ all -> 0x0106 }
        r1 = r1.toString();	 Catch:{ all -> 0x0106 }
        r2 = new java.util.HashMap;	 Catch:{ all -> 0x0106 }
        r2.<init>();	 Catch:{ all -> 0x0106 }
        r7.m12527a(r0, r1, r10, r2);	 Catch:{ all -> 0x0106 }
        if (r6 == 0) goto L_0x0062;
    L_0x005f:
        r6.disconnect();
    L_0x0062:
        return;
    L_0x0063:
        r3 = new java.util.Stack;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3.<init>();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r10;
    L_0x0069:
        r4 = new java.io.File;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r4.getParent();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        if (r0 != 0) goto L_0x00ae;
    L_0x0074:
        r0 = r3.isEmpty();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        if (r0 == 0) goto L_0x00b9;
    L_0x007a:
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4 = com.leanplum.FileManager.m12427c(r10);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        com.leanplum.Util.m12569a(r2, r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = f8611g;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r0 + -1;
        f8611g = r0;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r7.f8623s;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        if (r0 == 0) goto L_0x009b;
    L_0x0095:
        r0 = r7.f8623s;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r3 = 0;
        r0.m12409a(r3);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
    L_0x009b:
        r0 = f8611g;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        if (r0 != 0) goto L_0x00a8;
    L_0x009f:
        r0 = f8612h;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        if (r0 == 0) goto L_0x00a8;
    L_0x00a3:
        r0 = f8612h;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0.m12592a();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
    L_0x00a8:
        if (r2 == 0) goto L_0x0062;
    L_0x00aa:
        r2.disconnect();
        goto L_0x0062;
    L_0x00ae:
        r3.push(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        goto L_0x0069;
    L_0x00b2:
        r0 = move-exception;
    L_0x00b3:
        if (r2 == 0) goto L_0x00b8;
    L_0x00b5:
        r2.disconnect();
    L_0x00b8:
        throw r0;
    L_0x00b9:
        r4 = new java.io.File;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = r3.pop();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r0 = com.leanplum.FileManager.m12427c(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        r4.mkdir();	 Catch:{ Exception -> 0x003c, all -> 0x00b2 }
        goto L_0x0074;
    L_0x00cc:
        r1 = "Leanplum";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0106 }
        r3 = "Error downloading resource:";
        r2.<init>(r3);	 Catch:{ all -> 0x0106 }
        r2 = r2.append(r10);	 Catch:{ all -> 0x0106 }
        r2 = r2.toString();	 Catch:{ all -> 0x0106 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0106 }
        r0 = f8611g;	 Catch:{ all -> 0x0106 }
        r0 = r0 + -1;
        f8611g = r0;	 Catch:{ all -> 0x0106 }
        r0 = r7.f8624t;	 Catch:{ all -> 0x0106 }
        if (r0 == 0) goto L_0x00ef;
    L_0x00ea:
        r0 = r7.f8624t;	 Catch:{ all -> 0x0106 }
        r0.m12411a();	 Catch:{ all -> 0x0106 }
    L_0x00ef:
        r0 = f8611g;	 Catch:{ all -> 0x0106 }
        if (r0 != 0) goto L_0x00fc;
    L_0x00f3:
        r0 = f8612h;	 Catch:{ all -> 0x0106 }
        if (r0 == 0) goto L_0x00fc;
    L_0x00f7:
        r0 = f8612h;	 Catch:{ all -> 0x0106 }
        r0.m12592a();	 Catch:{ all -> 0x0106 }
    L_0x00fc:
        if (r6 == 0) goto L_0x0062;
    L_0x00fe:
        r6.disconnect();
        goto L_0x0062;
    L_0x0103:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b3;
    L_0x0106:
        r0 = move-exception;
        r2 = r6;
        goto L_0x00b3;
    L_0x0109:
        r0 = move-exception;
        r1 = r6;
        goto L_0x003e;
    L_0x010d:
        r0 = move-exception;
        r1 = r6;
        r6 = r2;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.S.a(java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    static int m12542i() {
        return f8611g;
    }

    static void m12524a(C0624Z c0624z) {
        f8612h = c0624z;
    }

    static int m12517a(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("response").length();
        } catch (Throwable e) {
            Log.e("Leanplum", "Could not parse JSON response", e);
            return 0;
        }
    }

    static JSONObject m12522a(JSONObject jSONObject, int i) {
        try {
            return jSONObject.getJSONArray("response").getJSONObject(i);
        } catch (Throwable e) {
            Log.e("Leanplum", "Could not parse JSON response", e);
            return null;
        }
    }

    static JSONObject m12533b(JSONObject jSONObject) {
        int a = C0618S.m12517a(jSONObject);
        if (a > 0) {
            return C0618S.m12522a(jSONObject, a - 1);
        }
        return null;
    }

    static boolean m12539c(JSONObject jSONObject) {
        try {
            return jSONObject.getBoolean("success");
        } catch (Throwable e) {
            Log.e("Leanplum", "Could not parse JSON response", e);
            return false;
        }
    }

    static String m12541d(JSONObject jSONObject) {
        String str = null;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("error");
            if (jSONObject2 != null) {
                str = jSONObject2.getString("message");
            }
        } catch (Throwable e) {
            Log.e("Leanplum", "Could not parse JSON response", e);
        }
        return str;
    }
}
