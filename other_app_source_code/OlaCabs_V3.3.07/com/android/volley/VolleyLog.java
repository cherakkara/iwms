package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.android.volley.t */
public class VolleyLog {
    public static String f532a;
    public static boolean f533b;

    /* renamed from: com.android.volley.t.a */
    static class VolleyLog {
        public static final boolean f529a;
        private final List<VolleyLog> f530b;
        private boolean f531c;

        /* renamed from: com.android.volley.t.a.a */
        private static class VolleyLog {
            public final String f526a;
            public final long f527b;
            public final long f528c;

            public VolleyLog(String str, long j, long j2) {
                this.f526a = str;
                this.f527b = j;
                this.f528c = j2;
            }
        }

        VolleyLog() {
            this.f530b = new ArrayList();
            this.f531c = false;
        }

        static {
            f529a = VolleyLog.f533b;
        }

        public synchronized void m591a(String str, long j) {
            if (this.f531c) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.f530b.add(new VolleyLog(str, j, SystemClock.elapsedRealtime()));
        }

        public synchronized void m590a(String str) {
            this.f531c = true;
            if (m589a() > 0) {
                long j = ((VolleyLog) this.f530b.get(0)).f528c;
                VolleyLog.m594b("(%-4d ms) %s", Long.valueOf(r2), str);
                long j2 = j;
                for (VolleyLog volleyLog : this.f530b) {
                    VolleyLog.m594b("(+%-4d) [%2d] %s", Long.valueOf(volleyLog.f528c - j2), Long.valueOf(volleyLog.f527b), volleyLog.f526a);
                    j2 = volleyLog.f528c;
                }
            }
        }

        protected void finalize() throws Throwable {
            if (!this.f531c) {
                m590a("Request on the loose");
                VolleyLog.m595c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        private long m589a() {
            if (this.f530b.size() == 0) {
                return 0;
            }
            return ((VolleyLog) this.f530b.get(this.f530b.size() - 1)).f528c - ((VolleyLog) this.f530b.get(0)).f528c;
        }
    }

    static {
        f532a = "Volley";
        f533b = Log.isLoggable(f532a, 2);
    }

    public static void m592a(String str, Object... objArr) {
        if (f533b) {
            Log.v(f532a, VolleyLog.m597e(str, objArr));
        }
    }

    public static void m594b(String str, Object... objArr) {
        Log.d(f532a, VolleyLog.m597e(str, objArr));
    }

    public static void m595c(String str, Object... objArr) {
        Log.e(f532a, VolleyLog.m597e(str, objArr));
    }

    public static void m593a(Throwable th, String str, Object... objArr) {
        Log.e(f532a, VolleyLog.m597e(str, objArr), th);
    }

    public static void m596d(String str, Object... objArr) {
        Log.wtf(f532a, VolleyLog.m597e(str, objArr));
    }

    private static String m597e(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str3 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(VolleyLog.class)) {
                str3 = stackTrace[i].getClassName();
                str3 = str3.substring(str3.lastIndexOf(46) + 1);
                str2 = str3.substring(str3.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
        }
        str2 = str3;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }
}
