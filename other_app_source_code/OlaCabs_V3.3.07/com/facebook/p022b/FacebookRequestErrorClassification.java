package com.facebook.p022b;

import com.facebook.FacebookRequestError.FacebookRequestError;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.b.j */
public final class FacebookRequestErrorClassification {
    private static FacebookRequestErrorClassification f762g;
    private final Map<Integer, Set<Integer>> f763a;
    private final Map<Integer, Set<Integer>> f764b;
    private final Map<Integer, Set<Integer>> f765c;
    private final String f766d;
    private final String f767e;
    private final String f768f;

    /* renamed from: com.facebook.b.j.1 */
    static class FacebookRequestErrorClassification extends HashMap<Integer, Set<Integer>> {
        FacebookRequestErrorClassification() {
            put(Integer.valueOf(2), null);
            put(Integer.valueOf(4), null);
            put(Integer.valueOf(9), null);
            put(Integer.valueOf(17), null);
            put(Integer.valueOf(341), null);
        }
    }

    /* renamed from: com.facebook.b.j.2 */
    static class FacebookRequestErrorClassification extends HashMap<Integer, Set<Integer>> {
        FacebookRequestErrorClassification() {
            put(Integer.valueOf(HttpStatus.SC_PROCESSING), null);
            put(Integer.valueOf(190), null);
        }
    }

    /* renamed from: com.facebook.b.j.3 */
    static /* synthetic */ class FacebookRequestErrorClassification {
        static final /* synthetic */ int[] f761a;

        static {
            f761a = new int[FacebookRequestError.values().length];
            try {
                f761a[FacebookRequestError.OTHER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f761a[FacebookRequestError.LOGIN_RECOVERABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f761a[FacebookRequestError.TRANSIENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    FacebookRequestErrorClassification(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.f763a = map;
        this.f764b = map2;
        this.f765c = map3;
        this.f766d = str;
        this.f767e = str2;
        this.f768f = str3;
    }

    public String m951a(FacebookRequestError facebookRequestError) {
        switch (FacebookRequestErrorClassification.f761a[facebookRequestError.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return this.f766d;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return this.f768f;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return this.f767e;
            default:
                return null;
        }
    }

    public FacebookRequestError m950a(int i, int i2, boolean z) {
        if (z) {
            return FacebookRequestError.TRANSIENT;
        }
        Set set;
        if (this.f763a != null && this.f763a.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f763a.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return FacebookRequestError.OTHER;
            }
        }
        if (this.f765c != null && this.f765c.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f765c.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return FacebookRequestError.LOGIN_RECOVERABLE;
            }
        }
        if (this.f764b != null && this.f764b.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f764b.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return FacebookRequestError.TRANSIENT;
            }
        }
        return FacebookRequestError.OTHER;
    }

    public static synchronized FacebookRequestErrorClassification m946a() {
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        synchronized (FacebookRequestErrorClassification.class) {
            if (f762g == null) {
                f762g = FacebookRequestErrorClassification.m949b();
            }
            facebookRequestErrorClassification = f762g;
        }
        return facebookRequestErrorClassification;
    }

    private static FacebookRequestErrorClassification m949b() {
        return new FacebookRequestErrorClassification(null, new FacebookRequestErrorClassification(), new FacebookRequestErrorClassification(), null, null, null);
    }

    private static Map<Integer, Set<Integer>> m948a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray.length() == 0) {
            return null;
        }
        Map<Integer, Set<Integer>> hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("code");
                if (optInt != 0) {
                    Object obj;
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        obj = null;
                    } else {
                        Set hashSet = new HashSet();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                        obj = hashSet;
                    }
                    hashMap.put(Integer.valueOf(optInt), obj);
                }
            }
        }
        return hashMap;
    }

    public static FacebookRequestErrorClassification m947a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String str = null;
        String str2 = null;
        String str3 = null;
        Map map = null;
        Map map2 = null;
        Map map3 = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString(Constants.BUNDLE_NAME);
                if (optString != null) {
                    if (optString.equalsIgnoreCase("other")) {
                        str3 = optJSONObject.optString("recovery_message", null);
                        map3 = FacebookRequestErrorClassification.m948a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("transient")) {
                        str2 = optJSONObject.optString("recovery_message", null);
                        map2 = FacebookRequestErrorClassification.m948a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("login_recoverable")) {
                        str = optJSONObject.optString("recovery_message", null);
                        map = FacebookRequestErrorClassification.m948a(optJSONObject);
                    }
                }
            }
        }
        return new FacebookRequestErrorClassification(map3, map2, map, str3, str2, str);
    }
}
