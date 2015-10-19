package com.facebook.p022b;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.b.d */
public class BundleJSONConverter {
    private static final Map<Class<?>, BundleJSONConverter> f740a;

    /* renamed from: com.facebook.b.d.a */
    public interface BundleJSONConverter {
        void m890a(Bundle bundle, String str, Object obj) throws JSONException;

        void m891a(JSONObject jSONObject, String str, Object obj) throws JSONException;
    }

    /* renamed from: com.facebook.b.d.1 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m892a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        }

        public void m893a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.b.d.2 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m894a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putInt(str, ((Integer) obj).intValue());
        }

        public void m895a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.b.d.3 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m896a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putLong(str, ((Long) obj).longValue());
        }

        public void m897a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.b.d.4 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m898a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        }

        public void m899a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.b.d.5 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m900a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putString(str, (String) obj);
        }

        public void m901a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.b.d.6 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m902a(Bundle bundle, String str, Object obj) throws JSONException {
            throw new IllegalArgumentException("Unexpected type from JSON");
        }

        public void m903a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            JSONArray jSONArray = new JSONArray();
            for (Object put : (String[]) obj) {
                jSONArray.put(put);
            }
            jSONObject.put(str, jSONArray);
        }
    }

    /* renamed from: com.facebook.b.d.7 */
    static class BundleJSONConverter implements BundleJSONConverter {
        BundleJSONConverter() {
        }

        public void m904a(Bundle bundle, String str, Object obj) throws JSONException {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() == 0) {
                bundle.putStringArrayList(str, arrayList);
                return;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                Object obj2 = jSONArray.get(i);
                if (obj2 instanceof String) {
                    arrayList.add((String) obj2);
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected type in an array: " + obj2.getClass());
                }
            }
            bundle.putStringArrayList(str, arrayList);
        }

        public void m905a(JSONObject jSONObject, String str, Object obj) throws JSONException {
            throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
        }
    }

    static {
        f740a = new HashMap();
        f740a.put(Boolean.class, new BundleJSONConverter());
        f740a.put(Integer.class, new BundleJSONConverter());
        f740a.put(Long.class, new BundleJSONConverter());
        f740a.put(Double.class, new BundleJSONConverter());
        f740a.put(String.class, new BundleJSONConverter());
        f740a.put(String[].class, new BundleJSONConverter());
        f740a.put(JSONArray.class, new BundleJSONConverter());
    }

    public static JSONObject m907a(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof List) {
                    JSONArray jSONArray = new JSONArray();
                    for (String put : (List) obj) {
                        jSONArray.put(put);
                    }
                    jSONObject.put(str, jSONArray);
                } else if (obj instanceof Bundle) {
                    jSONObject.put(str, BundleJSONConverter.m907a((Bundle) obj));
                } else {
                    BundleJSONConverter bundleJSONConverter = (BundleJSONConverter) f740a.get(obj.getClass());
                    if (bundleJSONConverter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                    bundleJSONConverter.m891a(jSONObject, str, obj);
                }
            }
        }
        return jSONObject;
    }

    public static Bundle m906a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (!(obj == null || obj == JSONObject.NULL)) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(str, BundleJSONConverter.m906a((JSONObject) obj));
                } else {
                    BundleJSONConverter bundleJSONConverter = (BundleJSONConverter) f740a.get(obj.getClass());
                    if (bundleJSONConverter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                    bundleJSONConverter.m890a(bundle, str, obj);
                }
            }
        }
        return bundle;
    }
}
