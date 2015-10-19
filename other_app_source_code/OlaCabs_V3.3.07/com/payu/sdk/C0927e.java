package com.payu.sdk;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import com.payu.sdk.p085a.C0920a;
import com.payu.sdk.p085a.C0921b;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.payu.sdk.e */
public class C0927e {
    private static final String[] f11534C;
    private static final String[] f11535D;
    private static final String[] f11536E;
    public static JSONArray f11537a;
    public static JSONArray f11538b;
    public static JSONArray f11539c;
    public static JSONArray f11540d;
    public static JSONArray f11541e;
    public static JSONArray f11542f;
    public static JSONArray f11543g;
    public static JSONArray f11544h;
    public static HashMap<String, Integer> f11545i;
    public static JSONObject f11546j;
    public static String f11547k;
    public static String f11548l;
    public static String f11549m;
    public static String f11550n;
    public static String f11551o;
    public static String f11552p;
    public static String f11553q;
    public static String f11554r;
    public static String f11555s;
    public static String f11556t;
    public static String f11557u;
    public static C0926a[] f11558v;
    public static long f11559w;
    static final Map<C0926a, String> f11560x;
    private static String f11561y;
    private static C0927e f11562z;
    private Activity f11563A;
    private String f11564B;

    /* renamed from: com.payu.sdk.e.a */
    public enum C0926a {
        CC,
        DC,
        NB,
        EMI,
        PAYU_MONEY,
        STORED_CARDS,
        CASH
    }

    static {
        f11558v = null;
        f11560x = new HashMap();
        f11560x.put(C0926a.CC, "Credit Card");
        f11560x.put(C0926a.DC, "Debit Card");
        f11560x.put(C0926a.NB, "Net Banking");
        f11560x.put(C0926a.EMI, "EMI");
        f11560x.put(C0926a.PAYU_MONEY, "PayUMoney");
        f11560x.put(C0926a.STORED_CARDS, "Stored Cards");
        f11560x.put(C0926a.CASH, "Cash Card");
        f11534C = new String[]{"ccnum", "ccexpmon", "ccexpyr", "ccname"};
        f11535D = new String[]{"ccvv"};
        f11536E = new String[]{"bankcode"};
    }

    private C0927e(Activity activity, String str, String str2) {
        f11561y = str;
        this.f11564B = str2;
        this.f11563A = activity;
    }

    public static synchronized C0927e m14948a(Activity activity) {
        C0927e c0927e;
        synchronized (C0927e.class) {
            f11562z = null;
            try {
                f11562z = new C0927e(activity, activity.getPackageManager().getApplicationInfo(activity.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData.getString("payu_merchant_id"), null);
            } catch (NameNotFoundException e) {
                Log.e("PayU", "Failed to load meta-data, NameNotFound: " + e.getMessage());
            } catch (NullPointerException e2) {
                Log.e("PayU", "Failed to load meta-data, NullPointer: " + e2.getMessage());
            }
            c0927e = f11562z;
        }
        return c0927e;
    }

    private void m14949a(String[] strArr, C0925d c0925d) throws C0921b {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            if (c0925d.containsKey(str)) {
                i++;
            } else {
                throw new C0921b("Parameter " + str + " is missing");
            }
        }
        if (f11550n == null) {
            throw new C0921b("Parameter Hash is missing");
        }
    }

    public String m14950a(C0930g c0930g, C0925d c0925d) throws C0921b, C0920a {
        c0925d.put("pg", c0930g.m14955a().toString());
        c0925d.put("device_type", "1");
        c0925d.put("instrument_id", Secure.getString(this.f11563A.getContentResolver(), "android_id"));
        c0925d.put("instrument_type", "Manufacturer: " + Build.MANUFACTURER + " Model: " + Build.MODEL + "  Product: " + Build.PRODUCT);
        switch (C0928f.f11565a[c0930g.m14955a().ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                c0925d.put("bankcode", "CC");
                m14949a(f11535D, c0925d);
                if (c0925d.m14947a("store_card_token").length() <= 1) {
                    if (c0925d.m14947a("ccvv").length() >= 3) {
                        m14949a(f11534C, c0925d);
                        break;
                    }
                    c0925d.put("ccvv", "123");
                    c0925d.put("ccexpmon", "12");
                    c0925d.put("ccexpyr", "2090");
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                m14949a(f11536E, c0925d);
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                c0925d.put("bankcode", "payuw");
                c0925d.put("pg", "wallet");
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                m14949a(f11534C, c0925d);
                m14949a(f11535D, c0925d);
                break;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String str = Trace.NULL;
            String str2 = str;
            for (String str3 : c0925d.keySet()) {
                str3 = (str3.contentEquals(Constants.ARG_USER_CREDENTIALS) || str3.contentEquals(Constants.EMAIL)) ? str2 + str3 + "=" + c0925d.m14947a(str3) + "&" : str2 + str3 + "=" + URLEncoder.encode(c0925d.m14947a(str3), HTTP.UTF_8) + "&";
                str2 = str3;
            }
            return str2 + "hash=" + f11550n;
        } catch (NoSuchAlgorithmException e) {
            throw new C0920a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return Trace.NULL;
        }
    }

    public List<NameValuePair> m14951a(String str, HashMap<String, String> hashMap) throws NoSuchAlgorithmException {
        StringBuilder stringBuilder = new StringBuilder();
        List<NameValuePair> arrayList = new ArrayList(4);
        arrayList.add(new BasicNameValuePair("key", f11561y));
        arrayList.add(new BasicNameValuePair("command", str));
        for (int i = 1; i <= hashMap.size(); i++) {
            arrayList.add(new BasicNameValuePair("var" + i, String.valueOf(hashMap.get("var" + i))));
        }
        if (str.contentEquals("get_merchant_ibibo_codes")) {
            arrayList.add(new BasicNameValuePair("hash", f11555s));
        } else if (str.contentEquals("save_user_card")) {
            arrayList.add(new BasicNameValuePair("hash", f11554r));
        } else if (str.contentEquals("edit_user_card")) {
            arrayList.add(new BasicNameValuePair("hash", f11553q));
        } else if (str.contentEquals("delete_user_card")) {
            arrayList.add(new BasicNameValuePair("hash", f11551o));
        } else if (str.contentEquals("get_user_cards")) {
            arrayList.add(new BasicNameValuePair("hash", f11552p));
        } else if (str.contentEquals("payment_related_details_for_mobile_sdk")) {
            arrayList.add(new BasicNameValuePair("hash", f11557u));
        } else if (str.contentEquals("vas_for_mobile_sdk")) {
            arrayList.add(new BasicNameValuePair("hash", f11556t));
        }
        arrayList.add(new BasicNameValuePair("device_type", "1"));
        return arrayList;
    }
}
