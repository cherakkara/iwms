package com.leanplum;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.json.JSONTokener;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

final class Util {
    private static final Executor f8630a;
    private static boolean f8631b;
    private static boolean f8632c;

    public class DeviceIdInfo {
        String f8628a;
        boolean f8629b;

        public DeviceIdInfo(String str) {
            this.f8628a = str;
        }

        public DeviceIdInfo(String str, boolean z) {
            this.f8628a = str;
            this.f8629b = z;
        }
    }

    @android.annotation.SuppressLint({"DefaultLocale"})
    public static java.net.HttpURLConnection m12566a(java.lang.String r8, java.util.List<java.io.File> r9, java.util.List<java.io.InputStream> r10, java.lang.String r11, java.lang.String r12, java.util.Map<java.lang.String, java.lang.String> r13, java.lang.String r14, boolean r15, int r16) {
        /* JADX: method processing error */
/*
        Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r0 = 60;
        r3 = m12564a(r11, r12, r14, r15, r0);
        r0 = "Content-Type";
        r1 = "multipart/form-data; boundary===================================leanplum";
        r3.setRequestProperty(r0, r1);
        r0 = "Connection";
        r1 = "Keep-Alive";
        r3.setRequestProperty(r0, r1);
        r4 = new java.io.DataOutputStream;
        r0 = r3.getOutputStream();
        r4.<init>(r0);
        r0 = r13.keySet();
        r1 = r0.iterator();
    L_0x0025:
        r0 = r1.hasNext();
        if (r0 != 0) goto L_0x0040;
    L_0x002b:
        r0 = 0;
        r2 = r0;
    L_0x002d:
        r0 = r9.size();
        if (r2 < r0) goto L_0x0075;
    L_0x0033:
        r0 = "--==================================leanplum--\r\n";
        r4.writeBytes(r0);
        r4.flush();
        r4.close();
        r0 = r3;
    L_0x003f:
        return r0;
    L_0x0040:
        r0 = r1.next();
        r0 = (java.lang.String) r0;
        r2 = new java.lang.StringBuilder;
        r5 = "--==================================leanplum\r\nContent-Disposition: form-data; name=\"";
        r2.<init>(r5);
        r2 = r2.append(r0);
        r5 = "\"\r\n";
        r2 = r2.append(r5);
        r5 = "\r\n";
        r2 = r2.append(r5);
        r0 = r13.get(r0);
        r0 = (java.lang.String) r0;
        r0 = r2.append(r0);
        r2 = "\r\n";
        r0 = r0.append(r2);
        r0 = r0.toString();
        r4.writeBytes(r0);
        goto L_0x0025;
    L_0x0075:
        r0 = r9.get(r2);
        r0 = (java.io.File) r0;
        r1 = "Content-Disposition: form-data; name=\"%s%d\";filename=\"%s\"";
        r5 = 3;
        r5 = new java.lang.Object[r5];
        r6 = 0;
        r5[r6] = r8;
        r6 = 1;
        r7 = java.lang.Integer.valueOf(r2);
        r5[r6] = r7;
        r6 = 2;
        r7 = r0.getName();
        r5[r6] = r7;
        r1 = java.lang.String.format(r1, r5);
        r5 = new java.lang.StringBuilder;
        r6 = "--==================================leanplum\r\n";
        r5.<init>(r6);
        r1 = r5.append(r1);
        r5 = "\r\nContent-Type: application/octet-stream";
        r1 = r1.append(r5);
        r5 = "\r\n\r\n";
        r1 = r1.append(r5);
        r1 = r1.toString();
        r4.writeBytes(r1);
        r1 = r10.size();
        if (r2 >= r1) goto L_0x00d7;
    L_0x00b9:
        r0 = r10.get(r2);
        r0 = (java.io.InputStream) r0;
    L_0x00bf:
        r1 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r1 = new byte[r1];
    L_0x00c3:
        r5 = r0.read(r1);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r6 = -1;
        if (r5 != r6) goto L_0x00de;
    L_0x00ca:
        r0.close();
        r0 = "\r\n";
        r4.writeBytes(r0);
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x002d;
    L_0x00d7:
        r1 = new java.io.FileInputStream;
        r1.<init>(r0);
        r0 = r1;
        goto L_0x00bf;
    L_0x00de:
        r6 = 0;
        r4.write(r1, r6, r5);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        goto L_0x00c3;
    L_0x00e3:
        r1 = move-exception;
        r1 = "Leanplum";	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r3 = new java.lang.StringBuilder;	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r4 = "Unable to read file while uploading ";	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r3.<init>(r4);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r2 = r9.get(r2);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r2 = r3.append(r2);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r2 = r2.toString();	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        android.util.Log.e(r1, r2);	 Catch:{ NullPointerException -> 0x00e3, all -> 0x0102 }
        r0.close();
        r0 = 0;
        goto L_0x003f;
    L_0x0102:
        r1 = move-exception;
        r0.close();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.Util.a(java.lang.String, java.util.List, java.util.List, java.lang.String, java.lang.String, java.util.Map, java.lang.String, boolean, int):java.net.HttpURLConnection");
    }

    static {
        f8630a = Executors.newCachedThreadPool();
        f8631b = false;
        f8632c = false;
    }

    private static DeviceIdInfo m12558a(Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            return new DeviceIdInfo((String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), ((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue());
        } catch (Exception e) {
            if (e.getClass().getName().equals("GooglePlayServicesNotAvailableException")) {
                Log.e("Leanplum", "Error getting advertising ID. Google Play services are not available.");
                return null;
            }
            throw e;
        }
    }

    public static DeviceIdInfo m12559a(LeanplumDeviceIdMode leanplumDeviceIdMode) {
        Context a = Leanplum.m12431a();
        if (leanplumDeviceIdMode.equals(LeanplumDeviceIdMode.ADVERTISING_ID)) {
            try {
                DeviceIdInfo a2 = m12558a(a);
                if (a2 != null) {
                    return a2;
                }
            } catch (Throwable e) {
                Log.e("Leanplum", "Error getting advertising ID", e);
            }
        }
        if (m12574e() || leanplumDeviceIdMode.equals(LeanplumDeviceIdMode.ANDROID_ID)) {
            return new DeviceIdInfo(Secure.getString(a.getContentResolver(), "android_id"));
        }
        if (a.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
            try {
                String str;
                WifiInfo connectionInfo = ((WifiManager) a.getSystemService(WanType.WIFI)).getConnectionInfo();
                if (connectionInfo == null || connectionInfo.getMacAddress() == null) {
                    str = null;
                } else {
                    String macAddress = connectionInfo.getMacAddress();
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(macAddress.getBytes());
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b : digest) {
                        stringBuffer.append(Integer.toHexString(b & MotionEventCompat.ACTION_MASK));
                    }
                    str = stringBuffer.toString();
                }
                if (str != null) {
                    return new DeviceIdInfo(str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return new DeviceIdInfo(Secure.getString(a.getContentResolver(), "android_id"));
    }

    public static String m12561a() {
        Context a = Leanplum.m12431a();
        String str = Trace.NULL;
        try {
            return a.getPackageManager().getPackageInfo(a.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return str;
        }
    }

    public static String m12571b() {
        if (m12574e()) {
            return "Android Emulator";
        }
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return m12562a(str2);
        }
        return m12562a(str) + " " + str2;
    }

    private static String m12562a(String str) {
        if (str == null || str.length() == 0) {
            return Trace.NULL;
        }
        char charAt = str.charAt(0);
        return !Character.isUpperCase(charAt) ? Character.toUpperCase(charAt) + str.substring(1) : str;
    }

    public static String m12572c() {
        return "Android OS";
    }

    public static String m12573d() {
        return VERSION.RELEASE;
    }

    public static boolean m12574e() {
        String toLowerCase = Build.MODEL.toLowerCase(Locale.getDefault());
        return toLowerCase.contains("google_sdk") || toLowerCase.contains("emulator") || toLowerCase.contains("sdk");
    }

    public static String m12575f() {
        if (m12574e()) {
            return "Android Emulator";
        }
        return m12571b();
    }

    public static String m12576g() {
        Object language = Locale.getDefault().getLanguage();
        if (language.equals(Trace.NULL)) {
            language = "xx";
        }
        String country = Locale.getDefault().getCountry();
        if (country.equals(Trace.NULL)) {
            country = "XX";
        }
        return new StringBuilder(String.valueOf(language)).append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).append(country).toString();
    }

    private static String m12563a(List<NameValuePair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (NameValuePair nameValuePair : list) {
            if (nameValuePair.getValue() == null) {
                Log.w("Leanplum", "Request param " + nameValuePair.getName() + " is null");
            } else {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(nameValuePair.getName());
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(nameValuePair.getValue(), HTTP.UTF_8));
            }
        }
        return stringBuilder.toString();
    }

    public static HttpURLConnection m12565a(String str, String str2, Map<String, String> map, String str3, boolean z, int i) {
        if (str3.equals("GET")) {
            String str4 = Trace.NULL;
            String str5 = str4;
            for (String str42 : map.keySet()) {
                String str6 = (String) map.get(str42);
                if (str6 == null) {
                    Log.w("Leanplum", "Request param " + str42 + " is null");
                } else {
                    str5 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(str5)).append(str5.length() == 0 ? '?' : '&').toString())).append(str42).append("=").append(URLEncoder.encode(str6, "utf-8")).toString();
                }
            }
            str2 = new StringBuilder(String.valueOf(str2)).append(str5).toString();
        }
        HttpURLConnection a = m12564a(str, str2, str3, z, i);
        if (!str3.equals("GET")) {
            m12570a((Map) map, a);
        }
        if (C0633g.f8806m && C0633g.f8804k) {
            Log.d("Leanplum", "Sending request at path " + str2 + " with parameters " + map);
        }
        return a;
    }

    private static void m12570a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        List arrayList = new ArrayList();
        for (String str : map.keySet()) {
            arrayList.add(new BasicNameValuePair(str, (String) map.get(str)));
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, HTTP.UTF_8));
        bufferedWriter.write(m12563a(arrayList));
        bufferedWriter.close();
        outputStream.close();
    }

    private static HttpURLConnection m12564a(String str, String str2, String str3, boolean z, int i) {
        boolean z2;
        if (!str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            str2 = new StringBuilder(String.valueOf(z ? "https://" : "http://")).append(str).append("/").append(str2).toString();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(str2).openConnection());
        if (z) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
        }
        httpURLConnection.setReadTimeout(i * Constants.MILLIS_IN_A_SECOND);
        httpURLConnection.setConnectTimeout(i * Constants.MILLIS_IN_A_SECOND);
        httpURLConnection.setRequestMethod(str3);
        if (str3.equals("GET")) {
            z2 = false;
        } else {
            z2 = true;
        }
        httpURLConnection.setDoOutput(z2);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        return httpURLConnection;
    }

    public static void m12569a(URLConnection uRLConnection, OutputStream outputStream) {
        InputStream inputStream = uRLConnection.getInputStream();
        byte[] bArr = new byte[AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.close();
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static JSONObject m12567a(HttpURLConnection httpURLConnection) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getResponseCode() < HttpStatus.SC_BAD_REQUEST ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            stringBuilder.append(readLine).append("\n");
        }
        String stringBuilder2 = stringBuilder.toString();
        if (C0633g.f8806m && C0633g.f8804k) {
            Log.d("Leanplum", "Received response " + stringBuilder2);
        }
        return new JSONObject(new JSONTokener(stringBuilder2));
    }

    public static boolean m12577h() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Leanplum.m12431a().getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Log.e("Leanplum", "Error getting connectivity info", e);
            return false;
        }
    }

    public static <T> T m12560a(Map<?, ?> map, Object... objArr) {
        if (map == null) {
            return null;
        }
        T t = map;
        for (Object obj : objArr) {
            if (!((Map) t).containsKey(obj)) {
                return null;
            }
            t = ((Map) t).get(obj);
        }
        return t;
    }

    public static <T> void m12568a(AsyncTask<T, ?, ?> asyncTask, T... tArr) {
        if (VERSION.SDK_INT >= 11) {
            Executor executor = f8630a;
            if (asyncTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(asyncTask, executor, tArr);
            } else {
                asyncTask.executeOnExecutor(executor, tArr);
            }
        } else if (asyncTask instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(asyncTask, tArr);
        } else {
            asyncTask.execute(tArr);
        }
    }

    static boolean m12578i() {
        boolean z = false;
        if (f8631b) {
            return f8632c;
        }
        PackageManager packageManager = Leanplum.m12431a().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            if (packageInfo.versionCode < 4242000) {
                Log.i("Leanplum", "Google Play services version is too old: " + packageInfo.versionCode);
                f8631b = true;
                f8632c = z;
                return z;
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                f8631b = true;
                f8632c = applicationInfo.enabled;
                return applicationInfo.enabled;
            } catch (NameNotFoundException e) {
                f8631b = true;
                f8632c = z;
                return z;
            }
        } catch (NameNotFoundException e2) {
            f8631b = true;
            f8632c = z;
            return z;
        }
    }

    public static boolean m12579j() {
        return LeanplumActivityHelper.f8577b == null || LeanplumActivityHelper.f8576a;
    }
}
