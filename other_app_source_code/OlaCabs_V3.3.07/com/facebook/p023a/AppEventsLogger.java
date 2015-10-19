package com.facebook.p023a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C0153b;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.p022b.AttributionIdentifiers;
import com.facebook.p022b.Logger;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.a.a */
public class AppEventsLogger {
    private static final String f699a;
    private static Map<AppEventsLogger, AppEventsLogger> f700d;
    private static ScheduledThreadPoolExecutor f701e;
    private static AppEventsLogger f702f;
    private static boolean f703g;
    private static Context f704h;
    private static Object f705i;
    private static String f706j;
    private final Context f707b;
    private final AppEventsLogger f708c;

    /* renamed from: com.facebook.a.a.1 */
    static class AppEventsLogger implements Runnable {
        final /* synthetic */ AppEventsLogger f650a;
        final /* synthetic */ long f651b;
        final /* synthetic */ String f652c;

        public void run() {
            this.f650a.m832a(this.f651b, this.f652c);
        }
    }

    /* renamed from: com.facebook.a.a.2 */
    static class AppEventsLogger implements Runnable {
        AppEventsLogger() {
        }

        public void run() {
            if (AppEventsLogger.m826a() != AppEventsLogger.EXPLICIT_ONLY) {
                AppEventsLogger.m844c(AppEventsLogger.TIMER);
            }
        }
    }

    /* renamed from: com.facebook.a.a.3 */
    static class AppEventsLogger implements Runnable {
        AppEventsLogger() {
        }

        public void run() {
            Set<String> hashSet = new HashSet();
            synchronized (AppEventsLogger.f705i) {
                for (AppEventsLogger b : AppEventsLogger.f700d.keySet()) {
                    hashSet.add(b.m800b());
                }
            }
            for (String a : hashSet) {
                Utility.m1093a(a, true);
            }
        }
    }

    /* renamed from: com.facebook.a.a.4 */
    static class AppEventsLogger implements Runnable {
        final /* synthetic */ Context f653a;
        final /* synthetic */ AppEventsLogger f654b;
        final /* synthetic */ AppEventsLogger f655c;

        AppEventsLogger(Context context, AppEventsLogger appEventsLogger, AppEventsLogger appEventsLogger2) {
            this.f653a = context;
            this.f654b = appEventsLogger;
            this.f655c = appEventsLogger2;
        }

        public void run() {
            AppEventsLogger.m838b(this.f653a, this.f654b).m821a(this.f655c);
            AppEventsLogger.m850i();
        }
    }

    /* renamed from: com.facebook.a.a.5 */
    static class AppEventsLogger implements Runnable {
        final /* synthetic */ AppEventsLogger f656a;

        AppEventsLogger(AppEventsLogger appEventsLogger) {
            this.f656a = appEventsLogger;
        }

        public void run() {
            AppEventsLogger.m844c(this.f656a);
        }
    }

    /* renamed from: com.facebook.a.a.6 */
    static class AppEventsLogger implements C0153b {
        final /* synthetic */ AppEventsLogger f657a;
        final /* synthetic */ GraphRequest f658b;
        final /* synthetic */ AppEventsLogger f659c;
        final /* synthetic */ AppEventsLogger f660d;

        AppEventsLogger(AppEventsLogger appEventsLogger, GraphRequest graphRequest, AppEventsLogger appEventsLogger2, AppEventsLogger appEventsLogger3) {
            this.f657a = appEventsLogger;
            this.f658b = graphRequest;
            this.f659c = appEventsLogger2;
            this.f660d = appEventsLogger3;
        }

        public void m798a(GraphResponse graphResponse) {
            AppEventsLogger.m841b(this.f657a, this.f658b, graphResponse, this.f659c, this.f660d);
        }
    }

    /* renamed from: com.facebook.a.a.a */
    private static class AppEventsLogger implements Serializable {
        private final String f661a;
        private final String f662b;

        AppEventsLogger(AccessToken accessToken) {
            this(accessToken.m698b(), FacebookSdk.m1210h());
        }

        AppEventsLogger(String str, String str2) {
            if (Utility.m1126a(str)) {
                str = null;
            }
            this.f661a = str;
            this.f662b = str2;
        }

        String m799a() {
            return this.f661a;
        }

        String m800b() {
            return this.f662b;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f661a == null ? 0 : this.f661a.hashCode();
            if (this.f662b != null) {
                i = this.f662b.hashCode();
            }
            return hashCode ^ i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AppEventsLogger)) {
                return false;
            }
            AppEventsLogger appEventsLogger = (AppEventsLogger) obj;
            if (Utility.m1125a(appEventsLogger.f661a, this.f661a) && Utility.m1125a(appEventsLogger.f662b, this.f662b)) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.facebook.a.a.b */
    static class AppEventsLogger implements Serializable {
        private static final HashSet<String> f663c;
        private JSONObject f664a;
        private boolean f665b;
        private String f666d;

        static {
            f663c = new HashSet();
        }

        public AppEventsLogger(Context context, String str, Double d, Bundle bundle, boolean z) {
            try {
                m801a(str);
                this.f666d = str;
                this.f665b = z;
                this.f664a = new JSONObject();
                this.f664a.put("_eventName", str);
                this.f664a.put("_logTime", System.currentTimeMillis() / 1000);
                this.f664a.put("_ui", Utility.m1137c(context));
                if (d != null) {
                    this.f664a.put("_valueToSum", d.doubleValue());
                }
                if (this.f665b) {
                    this.f664a.put("_implicitlyLogged", "1");
                }
                if (bundle != null) {
                    for (String str2 : bundle.keySet()) {
                        m801a(str2);
                        Object obj = bundle.get(str2);
                        if ((obj instanceof String) || (obj instanceof Number)) {
                            this.f664a.put(str2, obj.toString());
                        } else {
                            throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{obj, str2}));
                        }
                    }
                }
                if (!this.f665b) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
                    String str3 = "AppEvents";
                    String str4 = "Created app event '%s'";
                    Object[] objArr = new Object[1];
                    JSONObject jSONObject = this.f664a;
                    objArr[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                    Logger.m1000a(loggingBehavior, str3, str4, objArr);
                }
            } catch (JSONException e) {
                Logger.m1000a(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
                this.f664a = null;
            } catch (FacebookException e2) {
                Logger.m1000a(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", e2.toString());
                this.f664a = null;
            }
        }

        public boolean m802a() {
            return this.f665b;
        }

        public JSONObject m803b() {
            return this.f664a;
        }

        private void m801a(String str) throws FacebookException {
            String str2 = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
            if (str == null || str.length() == 0 || str.length() > 40) {
                if (str == null) {
                    str = "<None Provided>";
                }
                throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{str, Integer.valueOf(40)}));
            }
            synchronized (f663c) {
                boolean contains = f663c.contains(str);
            }
            if (!contains) {
                if (str.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                    synchronized (f663c) {
                        f663c.add(str);
                    }
                    return;
                }
                throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{str}));
            }
        }

        public String toString() {
            String str = "\"%s\", implicit: %b, json: %s";
            Object[] objArr = new Object[3];
            objArr[0] = this.f664a.optString("_eventName");
            objArr[1] = Boolean.valueOf(this.f665b);
            JSONObject jSONObject = this.f664a;
            objArr[2] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
            return String.format(str, objArr);
        }
    }

    /* renamed from: com.facebook.a.a.c */
    public enum AppEventsLogger {
        AUTO,
        EXPLICIT_ONLY
    }

    /* renamed from: com.facebook.a.a.d */
    private enum AppEventsLogger {
        EXPLICIT,
        TIMER,
        SESSION_CHANGE,
        PERSISTED_EVENTS,
        EVENT_THRESHOLD,
        EAGER_FLUSHING_EVENT
    }

    /* renamed from: com.facebook.a.a.e */
    private enum AppEventsLogger {
        SUCCESS,
        SERVER_ERROR,
        NO_CONNECTIVITY,
        UNKNOWN_ERROR
    }

    /* renamed from: com.facebook.a.a.f */
    private static class AppEventsLogger {
        public int f682a;
        public AppEventsLogger f683b;

        private AppEventsLogger() {
            this.f682a = 0;
            this.f683b = AppEventsLogger.SUCCESS;
        }
    }

    /* renamed from: com.facebook.a.a.g */
    static class AppEventsLogger {
        private static final Object f684a;
        private static boolean f685b;
        private static boolean f686c;
        private static Map<AppEventsLogger, FacebookTimeSpentData> f687d;
        private static final Runnable f688e;

        /* renamed from: com.facebook.a.a.g.1 */
        static class AppEventsLogger implements Runnable {
            AppEventsLogger() {
            }

            public void run() {
                AppEventsLogger.m806a(AppEventsLogger.f704h);
            }
        }

        static {
            f684a = new Object();
            f685b = false;
            f686c = false;
            f688e = new AppEventsLogger();
        }

        private static void m808b(Context context) {
            Exception e;
            Closeable closeable = null;
            synchronized (f684a) {
                if (!f686c) {
                    Closeable objectInputStream;
                    try {
                        objectInputStream = new ObjectInputStream(context.openFileInput("AppEventsLogger.persistedsessioninfo"));
                        try {
                            f687d = (HashMap) objectInputStream.readObject();
                            Logger.m999a(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info loaded");
                            Utility.m1116a(objectInputStream);
                            context.deleteFile("AppEventsLogger.persistedsessioninfo");
                            if (f687d == null) {
                                f687d = new HashMap();
                            }
                            f686c = true;
                            f685b = false;
                        } catch (FileNotFoundException e2) {
                            closeable = objectInputStream;
                            Utility.m1116a(closeable);
                            context.deleteFile("AppEventsLogger.persistedsessioninfo");
                            if (f687d == null) {
                                f687d = new HashMap();
                            }
                            f686c = true;
                            f685b = false;
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                                Utility.m1116a(objectInputStream);
                                context.deleteFile("AppEventsLogger.persistedsessioninfo");
                                if (f687d == null) {
                                    f687d = new HashMap();
                                }
                                f686c = true;
                                f685b = false;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                Utility.m1116a(objectInputStream);
                                context.deleteFile("AppEventsLogger.persistedsessioninfo");
                                if (f687d == null) {
                                    f687d = new HashMap();
                                }
                                f686c = true;
                                f685b = false;
                                throw th2;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        Utility.m1116a(closeable);
                        context.deleteFile("AppEventsLogger.persistedsessioninfo");
                        if (f687d == null) {
                            f687d = new HashMap();
                        }
                        f686c = true;
                        f685b = false;
                    } catch (Exception e5) {
                        Exception exception = e5;
                        objectInputStream = null;
                        e = exception;
                        Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                        Utility.m1116a(objectInputStream);
                        context.deleteFile("AppEventsLogger.persistedsessioninfo");
                        if (f687d == null) {
                            f687d = new HashMap();
                        }
                        f686c = true;
                        f685b = false;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        objectInputStream = null;
                        th2 = th4;
                        Utility.m1116a(objectInputStream);
                        context.deleteFile("AppEventsLogger.persistedsessioninfo");
                        if (f687d == null) {
                            f687d = new HashMap();
                        }
                        f686c = true;
                        f685b = false;
                        throw th2;
                    }
                }
            }
        }

        static void m806a(Context context) {
            Closeable objectOutputStream;
            Exception e;
            Throwable th;
            synchronized (f684a) {
                if (f685b) {
                    try {
                        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput("AppEventsLogger.persistedsessioninfo", 0)));
                        try {
                            objectOutputStream.writeObject(f687d);
                            f685b = false;
                            Logger.m999a(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info saved");
                            Utility.m1116a(objectOutputStream);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                                Utility.m1116a(objectOutputStream);
                            } catch (Throwable th2) {
                                th = th2;
                                Utility.m1116a(objectOutputStream);
                                throw th;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        objectOutputStream = null;
                        Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                        Utility.m1116a(objectOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream = null;
                        Utility.m1116a(objectOutputStream);
                        throw th;
                    }
                }
            }
        }

        static void m807a(Context context, AppEventsLogger appEventsLogger, AppEventsLogger appEventsLogger2, long j, String str) {
            synchronized (f684a) {
                AppEventsLogger.m804a(context, appEventsLogger).m862a(appEventsLogger2, j, str);
                AppEventsLogger.m805a();
            }
        }

        private static FacebookTimeSpentData m804a(Context context, AppEventsLogger appEventsLogger) {
            AppEventsLogger.m808b(context);
            FacebookTimeSpentData facebookTimeSpentData = (FacebookTimeSpentData) f687d.get(appEventsLogger);
            if (facebookTimeSpentData != null) {
                return facebookTimeSpentData;
            }
            facebookTimeSpentData = new FacebookTimeSpentData();
            f687d.put(appEventsLogger, facebookTimeSpentData);
            return facebookTimeSpentData;
        }

        private static void m805a() {
            if (!f685b) {
                f685b = true;
                AppEventsLogger.f701e.schedule(f688e, 30, TimeUnit.SECONDS);
            }
        }
    }

    /* renamed from: com.facebook.a.a.h */
    static class AppEventsLogger {
        private static Object f689a;
        private Context f690b;
        private HashMap<AppEventsLogger, List<AppEventsLogger>> f691c;

        static {
            f689a = new Object();
        }

        private AppEventsLogger(Context context) {
            this.f691c = new HashMap();
            this.f690b = context;
        }

        public static AppEventsLogger m809a(Context context) {
            AppEventsLogger appEventsLogger;
            synchronized (f689a) {
                appEventsLogger = new AppEventsLogger(context);
                appEventsLogger.m813c();
            }
            return appEventsLogger;
        }

        public static void m810a(Context context, AppEventsLogger appEventsLogger, AppEventsLogger appEventsLogger2) {
            Map hashMap = new HashMap();
            hashMap.put(appEventsLogger, appEventsLogger2);
            AppEventsLogger.m811a(context, hashMap);
        }

        public static void m811a(Context context, Map<AppEventsLogger, AppEventsLogger> map) {
            synchronized (f689a) {
                AppEventsLogger a = AppEventsLogger.m809a(context);
                for (Entry entry : map.entrySet()) {
                    List b = ((AppEventsLogger) entry.getValue()).m824b();
                    if (b.size() != 0) {
                        a.m816a((AppEventsLogger) entry.getKey(), b);
                    }
                }
                a.m812b();
            }
        }

        public Set<AppEventsLogger> m815a() {
            return this.f691c.keySet();
        }

        public List<AppEventsLogger> m814a(AppEventsLogger appEventsLogger) {
            return (List) this.f691c.get(appEventsLogger);
        }

        private void m812b() {
            Exception e;
            Throwable th;
            Closeable objectOutputStream;
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(this.f690b.openFileOutput("AppEventsLogger.persistedevents", 0)));
                try {
                    objectOutputStream.writeObject(this.f691c);
                    Utility.m1116a(objectOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                        Utility.m1116a(objectOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.m1116a(objectOutputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                objectOutputStream = null;
                Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                Utility.m1116a(objectOutputStream);
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = null;
                Utility.m1116a(objectOutputStream);
                throw th;
            }
        }

        private void m813c() {
            Closeable objectInputStream;
            Exception e;
            Throwable th;
            Closeable closeable = null;
            try {
                objectInputStream = new ObjectInputStream(new BufferedInputStream(this.f690b.openFileInput("AppEventsLogger.persistedevents")));
                try {
                    HashMap hashMap = (HashMap) objectInputStream.readObject();
                    this.f690b.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    this.f691c = hashMap;
                    Utility.m1116a(objectInputStream);
                } catch (FileNotFoundException e2) {
                    closeable = objectInputStream;
                    Utility.m1116a(closeable);
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                        Utility.m1116a(objectInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        Utility.m1116a(objectInputStream);
                        throw th;
                    }
                }
            } catch (FileNotFoundException e4) {
                Utility.m1116a(closeable);
            } catch (Exception e5) {
                Exception exception = e5;
                objectInputStream = null;
                e = exception;
                Log.d(AppEventsLogger.f699a, "Got unexpected exception: " + e.toString());
                Utility.m1116a(objectInputStream);
            } catch (Throwable th3) {
                Throwable th4 = th3;
                objectInputStream = null;
                th = th4;
                Utility.m1116a(objectInputStream);
                throw th;
            }
        }

        public void m816a(AppEventsLogger appEventsLogger, List<AppEventsLogger> list) {
            if (!this.f691c.containsKey(appEventsLogger)) {
                this.f691c.put(appEventsLogger, new ArrayList());
            }
            ((List) this.f691c.get(appEventsLogger)).addAll(list);
        }
    }

    /* renamed from: com.facebook.a.a.i */
    static class AppEventsLogger {
        private List<AppEventsLogger> f692a;
        private List<AppEventsLogger> f693b;
        private int f694c;
        private AttributionIdentifiers f695d;
        private String f696e;
        private String f697f;
        private final int f698g;

        public AppEventsLogger(AttributionIdentifiers attributionIdentifiers, String str, String str2) {
            this.f692a = new ArrayList();
            this.f693b = new ArrayList();
            this.f698g = Constants.MILLIS_IN_A_SECOND;
            this.f695d = attributionIdentifiers;
            this.f696e = str;
            this.f697f = str2;
        }

        public synchronized void m821a(AppEventsLogger appEventsLogger) {
            if (this.f692a.size() + this.f693b.size() >= Constants.MILLIS_IN_A_SECOND) {
                this.f694c++;
            } else {
                this.f692a.add(appEventsLogger);
            }
        }

        public synchronized int m819a() {
            return this.f692a.size();
        }

        public synchronized void m823a(boolean z) {
            if (z) {
                this.f692a.addAll(this.f693b);
            }
            this.f693b.clear();
            this.f694c = 0;
        }

        public int m820a(GraphRequest graphRequest, boolean z, boolean z2) {
            synchronized (this) {
                int i = this.f694c;
                this.f693b.addAll(this.f692a);
                this.f692a.clear();
                JSONArray jSONArray = new JSONArray();
                for (AppEventsLogger appEventsLogger : this.f693b) {
                    if (z || !appEventsLogger.m802a()) {
                        jSONArray.put(appEventsLogger.m803b());
                    }
                }
                if (jSONArray.length() == 0) {
                    return 0;
                }
                m817a(graphRequest, i, jSONArray, z2);
                return jSONArray.length();
            }
        }

        public synchronized List<AppEventsLogger> m824b() {
            List<AppEventsLogger> list;
            list = this.f692a;
            this.f692a = new ArrayList();
            return list;
        }

        public synchronized void m822a(List<AppEventsLogger> list) {
            this.f692a.addAll(list);
        }

        private void m817a(GraphRequest graphRequest, int i, JSONArray jSONArray, boolean z) {
            Object jSONArrayInstrumentation;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constants.PUSH_ACK_TYPE, "CUSTOM_APP_EVENTS");
                if (this.f694c > 0) {
                    jSONObject.put("num_skipped_events", i);
                }
                Utility.m1123a(jSONObject, this.f695d, this.f697f, z);
                try {
                    Utility.m1122a(jSONObject, AppEventsLogger.f704h);
                } catch (Exception e) {
                }
                jSONObject.put("application_package_name", this.f696e);
            } catch (JSONException e2) {
            }
            graphRequest.m776a(jSONObject);
            Bundle c = graphRequest.m779c();
            if (c == null) {
                c = new Bundle();
            }
            if (jSONArray instanceof JSONArray) {
                jSONArrayInstrumentation = JSONArrayInstrumentation.toString(jSONArray);
            } else {
                jSONArrayInstrumentation = jSONArray.toString();
            }
            if (jSONArrayInstrumentation != null) {
                c.putByteArray("custom_events_file", m818a((String) jSONArrayInstrumentation));
                graphRequest.m774a(jSONArrayInstrumentation);
            }
            graphRequest.m771a(c);
        }

        private byte[] m818a(String str) {
            byte[] bArr = null;
            try {
                bArr = str.getBytes(HTTP.UTF_8);
            } catch (Exception e) {
                Utility.m1119a("Encoding exception: ", e);
            }
            return bArr;
        }
    }

    static {
        f699a = AppEventsLogger.class.getCanonicalName();
        f700d = new ConcurrentHashMap();
        f702f = AppEventsLogger.AUTO;
        f705i = new Object();
    }

    private void m832a(long j, String str) {
        AppEventsLogger.m807a(f704h, this.f708c, this, j, str);
    }

    public static AppEventsLogger m830a(Context context) {
        return new AppEventsLogger(context, null, null);
    }

    public static AppEventsLogger m831a(Context context, String str) {
        return new AppEventsLogger(context, str, null);
    }

    public static AppEventsLogger m826a() {
        AppEventsLogger appEventsLogger;
        synchronized (f705i) {
            appEventsLogger = f702f;
        }
        return appEventsLogger;
    }

    public void m853a(String str) {
        m855a(str, null);
    }

    public void m855a(String str, Bundle bundle) {
        m837a(str, null, bundle, false);
    }

    public void m854a(String str, double d, Bundle bundle) {
        m837a(str, Double.valueOf(d), bundle, false);
    }

    public void m856a(String str, Double d, Bundle bundle) {
        m837a(str, d, bundle, true);
    }

    private AppEventsLogger(Context context, String str, AccessToken accessToken) {
        Validate.m1146a((Object) context, "context");
        this.f707b = context;
        if (accessToken == null) {
            accessToken = AccessToken.m690a();
        }
        if (accessToken == null || !(str == null || str.equals(accessToken.m703g()))) {
            if (str == null) {
                str = Utility.m1096a(context);
            }
            this.f708c = new AppEventsLogger(null, str);
        } else {
            this.f708c = new AppEventsLogger(accessToken);
        }
        synchronized (f705i) {
            if (f704h == null) {
                f704h = context.getApplicationContext();
            }
        }
        AppEventsLogger.m849h();
    }

    private static void m849h() {
        synchronized (f705i) {
            if (f701e != null) {
                return;
            }
            f701e = new ScheduledThreadPoolExecutor(1);
            f701e.scheduleAtFixedRate(new AppEventsLogger(), 0, 15, TimeUnit.SECONDS);
            f701e.scheduleAtFixedRate(new AppEventsLogger(), 0, 86400, TimeUnit.SECONDS);
        }
    }

    private void m837a(String str, Double d, Bundle bundle, boolean z) {
        AppEventsLogger.m833a(this.f707b, new AppEventsLogger(this.f707b, str, d, bundle, z), this.f708c);
    }

    private static void m833a(Context context, AppEventsLogger appEventsLogger, AppEventsLogger appEventsLogger2) {
        FacebookSdk.m1206d().execute(new AppEventsLogger(context, appEventsLogger2, appEventsLogger));
    }

    private static void m850i() {
        synchronized (f705i) {
            if (AppEventsLogger.m826a() != AppEventsLogger.EXPLICIT_ONLY && AppEventsLogger.m851j() > 100) {
                AppEventsLogger.m842b(AppEventsLogger.EVENT_THRESHOLD);
            }
        }
    }

    private static int m851j() {
        int i;
        synchronized (f705i) {
            i = 0;
            for (AppEventsLogger a : f700d.values()) {
                i = a.m819a() + i;
            }
        }
        return i;
    }

    private static AppEventsLogger m838b(Context context, AppEventsLogger appEventsLogger) {
        AppEventsLogger appEventsLogger2;
        AttributionIdentifiers attributionIdentifiers = null;
        if (((AppEventsLogger) f700d.get(appEventsLogger)) == null) {
            attributionIdentifiers = AttributionIdentifiers.m882a(context);
        }
        synchronized (f705i) {
            appEventsLogger2 = (AppEventsLogger) f700d.get(appEventsLogger);
            if (appEventsLogger2 == null) {
                appEventsLogger2 = new AppEventsLogger(attributionIdentifiers, context.getPackageName(), AppEventsLogger.m840b(context));
                f700d.put(appEventsLogger, appEventsLogger2);
            }
        }
        return appEventsLogger2;
    }

    private static AppEventsLogger m829a(AppEventsLogger appEventsLogger) {
        AppEventsLogger appEventsLogger2;
        synchronized (f705i) {
            appEventsLogger2 = (AppEventsLogger) f700d.get(appEventsLogger);
        }
        return appEventsLogger2;
    }

    private static void m842b(AppEventsLogger appEventsLogger) {
        FacebookSdk.m1206d().execute(new AppEventsLogger(appEventsLogger));
    }

    private static void m844c(AppEventsLogger appEventsLogger) {
        synchronized (f705i) {
            if (f703g) {
                return;
            }
            f703g = true;
            Set hashSet = new HashSet(f700d.keySet());
            AppEventsLogger.m852k();
            AppEventsLogger appEventsLogger2 = null;
            try {
                appEventsLogger2 = AppEventsLogger.m827a(appEventsLogger, hashSet);
            } catch (Throwable e) {
                Utility.m1120a(f699a, "Caught unexpected exception while flushing: ", e);
            }
            synchronized (f705i) {
                f703g = false;
            }
            if (appEventsLogger2 != null) {
                Intent intent = new Intent("com.facebook.sdk.APP_EVENTS_FLUSHED");
                intent.putExtra("com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED", appEventsLogger2.f682a);
                intent.putExtra("com.facebook.sdk.APP_EVENTS_FLUSH_RESULT", appEventsLogger2.f683b);
                LocalBroadcastManager.getInstance(f704h).sendBroadcast(intent);
            }
        }
    }

    private static AppEventsLogger m827a(AppEventsLogger appEventsLogger, Set<AppEventsLogger> set) {
        GraphRequest a;
        AppEventsLogger appEventsLogger2 = new AppEventsLogger();
        boolean b = FacebookSdk.m1202b(f704h);
        List<GraphRequest> arrayList = new ArrayList();
        for (AppEventsLogger appEventsLogger3 : set) {
            AppEventsLogger a2 = AppEventsLogger.m829a(appEventsLogger3);
            if (a2 != null) {
                a = AppEventsLogger.m825a(appEventsLogger3, a2, b, appEventsLogger2);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        Logger.m1000a(LoggingBehavior.APP_EVENTS, f699a, "Flushing %d events due to %s.", Integer.valueOf(appEventsLogger2.f682a), appEventsLogger.toString());
        for (GraphRequest a3 : arrayList) {
            a3.m783g();
        }
        return appEventsLogger2;
    }

    private static GraphRequest m825a(AppEventsLogger appEventsLogger, AppEventsLogger appEventsLogger2, boolean z, AppEventsLogger appEventsLogger3) {
        Utility.Utility a = Utility.m1093a(appEventsLogger.m800b(), false);
        GraphRequest a2 = GraphRequest.m735a(null, String.format("%s/activities", new Object[]{r0}), null, null);
        Bundle c = a2.m779c();
        if (c == null) {
            c = new Bundle();
        }
        c.putString("access_token", appEventsLogger.m799a());
        a2.m771a(c);
        if (a == null) {
            return null;
        }
        int a3 = appEventsLogger2.m820a(a2, a.m1084a(), z);
        if (a3 == 0) {
            return null;
        }
        appEventsLogger3.f682a = a3 + appEventsLogger3.f682a;
        a2.m772a(new AppEventsLogger(appEventsLogger, a2, appEventsLogger2, appEventsLogger3));
        return a2;
    }

    private static void m841b(AppEventsLogger appEventsLogger, GraphRequest graphRequest, GraphResponse graphResponse, AppEventsLogger appEventsLogger2, AppEventsLogger appEventsLogger3) {
        AppEventsLogger appEventsLogger4;
        FacebookRequestError a = graphResponse.m1352a();
        String str = Constants.SOS_SUCCESS_HEADER_TEXT;
        AppEventsLogger appEventsLogger5 = AppEventsLogger.SUCCESS;
        String str2;
        if (a == null) {
            str2 = str;
            appEventsLogger4 = appEventsLogger5;
        } else if (a.m1191b() == -1) {
            Object obj = "Failed: No Connectivity";
            appEventsLogger4 = AppEventsLogger.NO_CONNECTIVITY;
        } else {
            str2 = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{graphResponse.toString(), a.toString()});
            appEventsLogger4 = AppEventsLogger.SERVER_ERROR;
        }
        if (FacebookSdk.m1200a(LoggingBehavior.APP_EVENTS)) {
            Object jSONArray;
            try {
                JSONArray init = JSONArrayInstrumentation.init((String) graphRequest.m782f());
                jSONArray = !(init instanceof JSONArray) ? init.toString(2) : JSONArrayInstrumentation.toString(init, 2);
            } catch (JSONException e) {
                String str3 = "<Can't encode events for debug logging>";
            }
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str4 = f699a;
            String str5 = "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s";
            Object[] objArr = new Object[3];
            JSONObject a2 = graphRequest.m770a();
            objArr[0] = !(a2 instanceof JSONObject) ? a2.toString() : JSONObjectInstrumentation.toString(a2);
            objArr[1] = obj;
            objArr[2] = jSONArray;
            Logger.m1000a(loggingBehavior, str4, str5, objArr);
        }
        appEventsLogger2.m823a(a != null);
        if (appEventsLogger4 == AppEventsLogger.NO_CONNECTIVITY) {
            AppEventsLogger.m810a(f704h, appEventsLogger, appEventsLogger2);
        }
        if (appEventsLogger4 != AppEventsLogger.SUCCESS && appEventsLogger3.f683b != AppEventsLogger.NO_CONNECTIVITY) {
            appEventsLogger3.f683b = appEventsLogger4;
        }
    }

    private static int m852k() {
        AppEventsLogger a = AppEventsLogger.m809a(f704h);
        int i = 0;
        for (AppEventsLogger appEventsLogger : a.m815a()) {
            AppEventsLogger b = AppEventsLogger.m838b(f704h, appEventsLogger);
            List a2 = a.m814a(appEventsLogger);
            b.m822a(a2);
            i = a2.size() + i;
        }
        return i;
    }

    public static String m840b(Context context) {
        if (f706j == null) {
            synchronized (f705i) {
                if (f706j == null) {
                    f706j = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", null);
                    if (f706j == null) {
                        f706j = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", f706j).apply();
                    }
                }
            }
        }
        return f706j;
    }
}
