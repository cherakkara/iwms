package com.facebook.p022b;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.facebook.b.m */
public class Logger {
    private static final HashMap<String, String> f806a;
    private final LoggingBehavior f807b;
    private final String f808c;
    private StringBuilder f809d;
    private int f810e;

    static {
        f806a = new HashMap();
    }

    public static synchronized void m1002a(String str, String str2) {
        synchronized (Logger.class) {
            f806a.put(str, str2);
        }
    }

    public static synchronized void m1001a(String str) {
        synchronized (Logger.class) {
            if (!FacebookSdk.m1200a(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                Logger.m1002a(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    public static void m999a(LoggingBehavior loggingBehavior, String str, String str2) {
        Logger.m998a(loggingBehavior, 3, str, str2);
    }

    public static void m1000a(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
        if (FacebookSdk.m1200a(loggingBehavior)) {
            Logger.m998a(loggingBehavior, 3, str, String.format(str2, objArr));
        }
    }

    public static void m998a(LoggingBehavior loggingBehavior, int i, String str, String str2) {
        if (FacebookSdk.m1200a(loggingBehavior)) {
            String d = Logger.m1004d(str2);
            if (!str.startsWith("FacebookSDK.")) {
                str = "FacebookSDK." + str;
            }
            Log.println(i, str, d);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    private static synchronized String m1004d(String str) {
        synchronized (Logger.class) {
            for (Entry entry : f806a.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public Logger(LoggingBehavior loggingBehavior, String str) {
        this.f810e = 3;
        Validate.m1147a(str, "tag");
        this.f807b = loggingBehavior;
        this.f808c = "FacebookSDK." + str;
        this.f809d = new StringBuilder();
    }

    public void m1005a() {
        m1008b(this.f809d.toString());
        this.f809d = new StringBuilder();
    }

    public void m1008b(String str) {
        Logger.m998a(this.f807b, this.f810e, this.f808c, str);
    }

    public void m1009c(String str) {
        if (m1003b()) {
            this.f809d.append(str);
        }
    }

    public void m1007a(String str, Object... objArr) {
        if (m1003b()) {
            this.f809d.append(String.format(str, objArr));
        }
    }

    public void m1006a(String str, Object obj) {
        m1007a("  %s:\t%s\n", str, obj);
    }

    private boolean m1003b() {
        return FacebookSdk.m1200a(this.f807b);
    }
}
