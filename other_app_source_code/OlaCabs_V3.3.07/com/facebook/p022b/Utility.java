package com.facebook.p022b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C0153b;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.facebook.b.s */
public final class Utility {
    private static final String[] f853a;
    private static Map<String, Utility> f854b;
    private static AsyncTask<Void, Void, JSONObject> f855c;

    /* renamed from: com.facebook.b.s.c */
    public interface Utility {
        void m787a(FacebookException facebookException);

        void m788a(JSONObject jSONObject);
    }

    /* renamed from: com.facebook.b.s.1 */
    static class Utility extends AsyncTask<Void, Void, JSONObject> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ String f839a;
        final /* synthetic */ Context f840b;
        final /* synthetic */ String f841c;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        Utility(String str, Context context, String str2) {
            this.f839a = str;
            this.f840b = context;
            this.f841c = str2;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "s$1#doInBackground", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "s$1#doInBackground", null);
                    break;
                }
            }
            JSONObject a = m1074a((Void[]) objArr);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return a;
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "s$1#onPostExecute", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "s$1#onPostExecute", null);
                    break;
                }
            }
            m1075a((JSONObject) obj);
            TraceMachine.exitMethod();
        }

        protected JSONObject m1074a(Void... voidArr) {
            return Utility.m1143g(this.f839a);
        }

        protected void m1075a(JSONObject jSONObject) {
            if (jSONObject != null) {
                Utility.m1128b(this.f839a, jSONObject);
                this.f840b.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).edit().putString(this.f841c, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject)).apply();
            }
            Utility.f855c = null;
        }
    }

    /* renamed from: com.facebook.b.s.2 */
    static class Utility implements C0153b {
        final /* synthetic */ Utility f842a;
        final /* synthetic */ String f843b;

        Utility(Utility utility, String str) {
            this.f842a = utility;
            this.f843b = str;
        }

        public void m1076a(GraphResponse graphResponse) {
            if (graphResponse.m1352a() != null) {
                this.f842a.m787a(graphResponse.m1352a().m1195f());
                return;
            }
            ProfileInformationCache.m1068a(this.f843b, graphResponse.m1353b());
            this.f842a.m788a(graphResponse.m1353b());
        }
    }

    /* renamed from: com.facebook.b.s.a */
    public static class Utility {
        private String f844a;
        private String f845b;
        private Uri f846c;
        private int[] f847d;

        private static Utility m1079b(JSONObject jSONObject) {
            Uri uri = null;
            String optString = jSONObject.optString(Constants.BUNDLE_NAME);
            if (Utility.m1126a(optString)) {
                return null;
            }
            String[] split = optString.split("\\|");
            if (split.length != 2) {
                return null;
            }
            String str = split[0];
            String str2 = split[1];
            if (Utility.m1126a(str) || Utility.m1126a(str2)) {
                return null;
            }
            optString = jSONObject.optString("url");
            if (!Utility.m1126a(optString)) {
                uri = Uri.parse(optString);
            }
            return new Utility(str, str2, uri, Utility.m1078a(jSONObject.optJSONArray("versions")));
        }

        private static int[] m1078a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                int optInt = jSONArray.optInt(i, -1);
                if (optInt == -1) {
                    String optString = jSONArray.optString(i);
                    if (!Utility.m1126a(optString)) {
                        try {
                            optInt = Integer.parseInt(optString);
                        } catch (Exception e) {
                            Utility.m1119a("FacebookSDK", e);
                            optInt = -1;
                        }
                    }
                }
                iArr[i] = optInt;
            }
            return iArr;
        }

        private Utility(String str, String str2, Uri uri, int[] iArr) {
            this.f844a = str;
            this.f845b = str2;
            this.f846c = uri;
            this.f847d = iArr;
        }

        public String m1080a() {
            return this.f844a;
        }

        public String m1081b() {
            return this.f845b;
        }

        public Uri m1082c() {
            return this.f846c;
        }

        public int[] m1083d() {
            return this.f847d;
        }
    }

    /* renamed from: com.facebook.b.s.b */
    public static class Utility {
        private boolean f848a;
        private String f849b;
        private boolean f850c;
        private Map<String, Map<String, Utility>> f851d;
        private FacebookRequestErrorClassification f852e;

        private Utility(boolean z, String str, boolean z2, Map<String, Map<String, Utility>> map, FacebookRequestErrorClassification facebookRequestErrorClassification) {
            this.f848a = z;
            this.f849b = str;
            this.f850c = z2;
            this.f851d = map;
            this.f852e = facebookRequestErrorClassification;
        }

        public boolean m1084a() {
            return this.f848a;
        }

        public Map<String, Map<String, Utility>> m1085b() {
            return this.f851d;
        }

        public FacebookRequestErrorClassification m1086c() {
            return this.f852e;
        }
    }

    /* renamed from: com.facebook.b.s.d */
    public interface Utility<T, K> {
        K m1087a(T t);
    }

    static {
        f853a = new String[]{"supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "android_dialog_configs", "android_sdk_error_categories"};
        f854b = new ConcurrentHashMap();
    }

    public static <T> boolean m1127a(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean m1126a(String str) {
        return str == null || str.length() == 0;
    }

    public static String m1099a(String str, String str2) {
        return Utility.m1126a(str) ? str2 : str;
    }

    public static <T> Collection<T> m1106a(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    public static String m1129b(String str) {
        return Utility.m1138c("MD5", str);
    }

    public static String m1103a(byte[] bArr) {
        return Utility.m1100a("SHA-1", bArr);
    }

    private static String m1138c(String str, String str2) {
        return Utility.m1100a(str, str2.getBytes());
    }

    private static String m1100a(String str, byte[] bArr) {
        try {
            return Utility.m1101a(MessageDigest.getInstance(str), bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String m1101a(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b >> 4) & 15));
            stringBuilder.append(Integer.toHexString((b >> 0) & 15));
        }
        return stringBuilder.toString();
    }

    public static Uri m1089a(String str, String str2, Bundle bundle) {
        Builder builder = new Builder();
        builder.scheme(com.apsalar.sdk.Constants.API_PROTOCOL);
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str3, (String) obj);
                }
            }
        }
        return builder.build();
    }

    public static Bundle m1136c(String str) {
        Bundle bundle = new Bundle();
        if (!Utility.m1126a(str)) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], HTTP.UTF_8), URLDecoder.decode(split2[1], HTTP.UTF_8));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], HTTP.UTF_8), com.newrelic.agent.android.instrumentation.Trace.NULL);
                    }
                } catch (Exception e) {
                    Utility.m1119a("FacebookSDK", e);
                }
            }
        }
        return bundle;
    }

    public static void m1114a(Bundle bundle, String str, String str2) {
        if (!Utility.m1126a(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static void m1113a(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            Utility.m1114a(bundle, str, uri.toString());
        }
    }

    public static void m1116a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void m1121a(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static String m1096a(Context context) {
        Validate.m1146a((Object) context, "context");
        FacebookSdk.m1197a(context);
        return FacebookSdk.m1210h();
    }

    public static Object m1095a(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object obj;
        Object opt = jSONObject.opt(str);
        if (opt == null || !(opt instanceof String)) {
            obj = opt;
        } else {
            obj = new JSONTokener((String) opt).nextValue();
        }
        if (obj == null || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, obj);
            return jSONObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    public static String m1098a(InputStream inputStream) throws IOException {
        Closeable inputStreamReader;
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            String stringBuilder2 = stringBuilder.toString();
                            Utility.m1116a(bufferedInputStream);
                            Utility.m1116a(inputStreamReader);
                            return stringBuilder2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = bufferedInputStream;
                    Utility.m1116a(closeable);
                    Utility.m1116a(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeable = bufferedInputStream;
                Utility.m1116a(closeable);
                Utility.m1116a(inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            Utility.m1116a(closeable);
            Utility.m1116a(inputStreamReader);
            throw th;
        }
    }

    public static int m1088a(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        int i = 0;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    i += read;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private static void m1133b(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split : cookie.split(";")) {
                String[] split2 = split.split("=");
                if (split2.length > 0) {
                    instance.setCookie(str, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static void m1132b(Context context) {
        Utility.m1133b(context, "facebook.com");
        Utility.m1133b(context, ".facebook.com");
        Utility.m1133b(context, "https://facebook.com");
        Utility.m1133b(context, "https://.facebook.com");
    }

    public static void m1119a(String str, Exception exception) {
        if (FacebookSdk.m1201b() && str != null && exception != null) {
            Log.d(str, exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
    }

    public static void m1134b(String str, String str2) {
        if (FacebookSdk.m1201b() && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static void m1120a(String str, String str2, Throwable th) {
        if (FacebookSdk.m1201b() && !Utility.m1126a(str)) {
            Log.d(str, str2, th);
        }
    }

    public static <T> boolean m1125a(T t, T t2) {
        if (t == null) {
            return t2 == null;
        } else {
            return t.equals(t2);
        }
    }

    public static void m1112a(Context context, String str) {
        JSONObject jSONObject = null;
        if (!Utility.m1126a(str) && !f854b.containsKey(str) && f855c == null) {
            String format = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[]{str});
            f855c = new Utility(str, context, format);
            AsyncTask asyncTask = f855c;
            Void[] voidArr = (Void[]) jSONObject;
            if (asyncTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(asyncTask, voidArr);
            } else {
                asyncTask.execute(voidArr);
            }
            String string = context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).getString(format, jSONObject);
            if (!Utility.m1126a(string)) {
                try {
                    jSONObject = JSONObjectInstrumentation.init(string);
                } catch (Exception e) {
                    Utility.m1119a("FacebookSDK", e);
                }
                if (jSONObject != null) {
                    Utility.m1128b(str, jSONObject);
                }
            }
        }
    }

    public static Utility m1140d(String str) {
        return str != null ? (Utility) f854b.get(str) : null;
    }

    public static Utility m1093a(String str, boolean z) {
        if (!z && f854b.containsKey(str)) {
            return (Utility) f854b.get(str);
        }
        JSONObject g = Utility.m1143g(str);
        if (g == null) {
            return null;
        }
        return Utility.m1128b(str, g);
    }

    private static Utility m1128b(String str, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("android_sdk_error_categories");
        Utility utility = new Utility(jSONObject.optString("gdpv4_nux_content", com.newrelic.agent.android.instrumentation.Trace.NULL), jSONObject.optBoolean("gdpv4_nux_enabled", false), Utility.m1111a(jSONObject.optJSONObject("android_dialog_configs")), optJSONArray == null ? FacebookRequestErrorClassification.m946a() : FacebookRequestErrorClassification.m947a(optJSONArray), null);
        f854b.put(str, utility);
        return utility;
    }

    private static JSONObject m1143g(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", f853a));
        GraphRequest a = GraphRequest.m734a(null, str, null);
        a.m777a(true);
        a.m771a(bundle);
        return a.m783g().m1353b();
    }

    public static Utility m1091a(String str, String str2, String str3) {
        if (Utility.m1126a(str2) || Utility.m1126a(str3)) {
            return null;
        }
        Utility utility = (Utility) f854b.get(str);
        if (utility != null) {
            Map map = (Map) utility.m1085b().get(str2);
            if (map != null) {
                return (Utility) map.get(str3);
            }
        }
        return null;
    }

    private static Map<String, Map<String, Utility>> m1111a(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Utility a = Utility.m1079b(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        String a2 = a.m1080a();
                        Map map = (Map) hashMap.get(a2);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(a2, map);
                        }
                        map.put(a.m1081b(), a);
                    }
                }
            }
        }
        return hashMap;
    }

    public static String m1102a(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, com.newrelic.agent.android.instrumentation.Trace.NULL) : com.newrelic.agent.android.instrumentation.Trace.NULL;
    }

    public static JSONObject m1131b(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONObject(str) : null;
    }

    public static JSONArray m1139c(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONArray(str) : null;
    }

    public static void m1117a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File a : file.listFiles()) {
                    Utility.m1117a(a);
                }
            }
            file.delete();
        }
    }

    public static <T> List<T> m1130b(T... tArr) {
        List arrayList = new ArrayList();
        for (Object obj : tArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static List<String> m1109a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static void m1123a(JSONObject jSONObject, AttributionIdentifiers attributionIdentifiers, String str, boolean z) throws JSONException {
        boolean z2 = true;
        if (!(attributionIdentifiers == null || attributionIdentifiers.m884a() == null)) {
            jSONObject.put("attribution", attributionIdentifiers.m884a());
        }
        if (!(attributionIdentifiers == null || attributionIdentifiers.m885b() == null)) {
            jSONObject.put("advertiser_id", attributionIdentifiers.m885b());
            jSONObject.put("advertiser_tracking_enabled", !attributionIdentifiers.m886c());
        }
        jSONObject.put("anon_id", str);
        String str2 = "application_tracking_enabled";
        if (z) {
            z2 = false;
        }
        jSONObject.put(str2, z2);
    }

    public static void m1122a(JSONObject jSONObject, Context context) throws JSONException {
        Object jSONArrayInstrumentation;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("a1");
        String packageName = context.getPackageName();
        int i = -1;
        Object obj = com.newrelic.agent.android.instrumentation.Trace.NULL;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i = packageInfo.versionCode;
            obj = packageInfo.versionName;
        } catch (NameNotFoundException e) {
        }
        jSONArray.put(packageName);
        jSONArray.put(i);
        jSONArray.put(obj);
        String str = "extinfo";
        if (jSONArray instanceof JSONArray) {
            jSONArrayInstrumentation = JSONArrayInstrumentation.toString(jSONArray);
        } else {
            jSONArrayInstrumentation = jSONArray.toString();
        }
        jSONObject.put(str, jSONArrayInstrumentation);
    }

    public static Method m1104a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method m1105a(String str, String str2, Class<?>... clsArr) {
        try {
            return Utility.m1104a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object m1094a(Object obj, Method method, Object... objArr) {
        Object obj2 = null;
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return obj2;
    }

    public static String m1137c(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return WanType.UNKNOWN;
        }
        return context.getClass().getSimpleName();
    }

    public static <T, K> List<K> m1108a(List<T> list, Utility<T, K> utility) {
        if (list == null) {
            return null;
        }
        List<K> arrayList = new ArrayList();
        for (T a : list) {
            Object a2 = utility.m1087a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        return null;
    }

    public static String m1097a(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    public static boolean m1135b(Uri uri) {
        return uri != null && (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(uri.getScheme()) || com.apsalar.sdk.Constants.API_PROTOCOL.equalsIgnoreCase(uri.getScheme()));
    }

    public static Date m1107a(Bundle bundle, String str, Date date) {
        if (bundle == null) {
            return null;
        }
        long longValue;
        Object obj = bundle.get(str);
        if (obj instanceof Long) {
            longValue = ((Long) obj).longValue();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                longValue = Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        if (longValue == 0) {
            return new Date(Long.MAX_VALUE);
        }
        return new Date((longValue * 1000) + date.getTime());
    }

    public static void m1115a(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static Map<String, String> m1110a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static boolean m1124a(AccessToken accessToken) {
        return accessToken != null ? accessToken.equals(AccessToken.m690a()) : false;
    }

    public static void m1118a(String str, Utility utility) {
        JSONObject a = ProfileInformationCache.m1067a(str);
        if (a != null) {
            utility.m788a(a);
            return;
        }
        C0153b utility2 = new Utility(utility, str);
        GraphRequest h = Utility.m1144h(str);
        h.m772a(utility2);
        h.m784h();
    }

    public static JSONObject m1141e(String str) {
        JSONObject a = ProfileInformationCache.m1067a(str);
        if (a != null) {
            return a;
        }
        GraphResponse g = Utility.m1144h(str).m783g();
        if (g.m1352a() != null) {
            return null;
        }
        return g.m1353b();
    }

    private static GraphRequest m1144h(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", str);
        return new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
    }
}
