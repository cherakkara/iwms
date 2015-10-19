package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Base64;
import com.facebook.p022b.BoltsMeasurementEventListener;
import com.facebook.p022b.Utility;
import com.facebook.p022b.Validate;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.facebook.j */
public final class FacebookSdk {
    private static final String f931a;
    private static final HashSet<LoggingBehavior> f932b;
    private static volatile Executor f933c;
    private static volatile String f934d;
    private static volatile String f935e;
    private static volatile String f936f;
    private static volatile String f937g;
    private static AtomicLong f938h;
    private static volatile boolean f939i;
    private static boolean f940j;
    private static File f941k;
    private static Context f942l;
    private static int f943m;
    private static final Object f944n;
    private static final Uri f945o;
    private static final BlockingQueue<Runnable> f946p;
    private static final ThreadFactory f947q;
    private static Boolean f948r;

    /* renamed from: com.facebook.j.1 */
    static class FacebookSdk implements ThreadFactory {
        private final AtomicInteger f930a;

        FacebookSdk() {
            this.f930a = new AtomicInteger(0);
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "FacebookSdk #" + this.f930a.incrementAndGet());
        }
    }

    /* renamed from: com.facebook.j.2 */
    static class FacebookSdk implements Callable<Void> {
        FacebookSdk() {
        }

        public /* synthetic */ Object call() throws Exception {
            return m1196a();
        }

        public Void m1196a() throws Exception {
            AccessTokenManager.m1171a().m1178c();
            ProfileManager.m1708a().m1713c();
            if (AccessToken.m690a() != null && Profile.m793a() == null) {
                Profile.m795b();
            }
            return null;
        }
    }

    static {
        f931a = FacebookSdk.class.getCanonicalName();
        f932b = new HashSet(Arrays.asList(new LoggingBehavior[]{LoggingBehavior.DEVELOPER_ERRORS}));
        f937g = "facebook.com";
        f938h = new AtomicLong(65536);
        f939i = false;
        f940j = false;
        f943m = 64206;
        f944n = new Object();
        f945o = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
        f946p = new LinkedBlockingQueue(10);
        f947q = new FacebookSdk();
        f948r = Boolean.valueOf(false);
    }

    public static synchronized void m1197a(Context context) {
        synchronized (FacebookSdk.class) {
            if (!f948r.booleanValue()) {
                Validate.m1146a((Object) context, "applicationContext");
                f942l = context.getApplicationContext();
                FacebookSdk.m1203c(f942l);
                Utility.m1112a(f942l, f934d);
                BoltsMeasurementEventListener.m887a(f942l);
                f941k = f942l.getCacheDir();
                Executors.newSingleThreadExecutor().execute(new FutureTask(new FacebookSdk()));
                f948r = Boolean.valueOf(true);
            }
        }
    }

    public static synchronized boolean m1198a() {
        boolean booleanValue;
        synchronized (FacebookSdk.class) {
            booleanValue = f948r.booleanValue();
        }
        return booleanValue;
    }

    public static boolean m1200a(LoggingBehavior loggingBehavior) {
        boolean z;
        synchronized (f932b) {
            z = FacebookSdk.m1201b() && f932b.contains(loggingBehavior);
        }
        return z;
    }

    public static boolean m1201b() {
        return f939i;
    }

    public static boolean m1204c() {
        return f940j;
    }

    public static Executor m1206d() {
        synchronized (f944n) {
            if (f933c == null) {
                Executor m = FacebookSdk.m1215m();
                if (m == null) {
                    m = new ThreadPoolExecutor(5, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, 1, TimeUnit.SECONDS, f946p, f947q);
                }
                f933c = m;
            }
        }
        return f933c;
    }

    public static String m1207e() {
        return f937g;
    }

    public static Context m1208f() {
        Validate.m1145a();
        return f942l;
    }

    private static Executor m1215m() {
        try {
            try {
                Object obj = AsyncTask.class.getField("THREAD_POOL_EXECUTOR").get(null);
                if (obj == null) {
                    return null;
                }
                if (obj instanceof Executor) {
                    return (Executor) obj;
                }
                return null;
            } catch (IllegalAccessException e) {
                return null;
            }
        } catch (NoSuchFieldException e2) {
            return null;
        }
    }

    public static boolean m1202b(Context context) {
        Validate.m1145a();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }

    public static long m1209g() {
        Validate.m1145a();
        return f938h.get();
    }

    static void m1203c(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (f934d == null) {
                        f934d = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationId");
                    }
                    if (f935e == null) {
                        f935e = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                    }
                    if (f936f == null) {
                        f936f = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    public static String m1205d(Context context) {
        String str = null;
        Validate.m1145a();
        if (context == null) {
            return str;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            } catch (NoSuchAlgorithmException e) {
                return str;
            }
        } catch (NameNotFoundException e2) {
            return str;
        }
    }

    public static String m1210h() {
        Validate.m1145a();
        return f934d;
    }

    public static String m1211i() {
        Validate.m1145a();
        return f935e;
    }

    public static String m1212j() {
        Validate.m1145a();
        return f936f;
    }

    public static File m1213k() {
        Validate.m1145a();
        return f941k;
    }

    public static int m1214l() {
        Validate.m1145a();
        return f943m;
    }

    public static boolean m1199a(int i) {
        return i >= f943m && i < f943m + 100;
    }
}
