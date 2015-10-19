package com.google.android.m4b.maps.p038a;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.a.s */
public class VolleyLog {
    public static boolean f2942a;
    private static String f2943b;

    /* renamed from: com.google.android.m4b.maps.a.s.a */
    static class VolleyLog {
        public static final boolean f2939a;
        private final List<VolleyLog> f2940b;
        private boolean f2941c;

        /* renamed from: com.google.android.m4b.maps.a.s.a.a */
        static class VolleyLog {
            public final String f2936a;
            public final long f2937b;
            public final long f2938c;

            public VolleyLog(String str, long j, long j2) {
                this.f2936a = str;
                this.f2937b = j;
                this.f2938c = j2;
            }
        }

        VolleyLog() {
            this.f2940b = new ArrayList();
            this.f2941c = false;
        }

        static {
            f2939a = VolleyLog.f2942a;
        }

        public final synchronized void m4734a(String str, long j) {
            if (this.f2941c) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.f2940b.add(new VolleyLog(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void m4733a(String str) {
            long j;
            this.f2941c = true;
            if (this.f2940b.size() == 0) {
                j = 0;
            } else {
                j = ((VolleyLog) this.f2940b.get(this.f2940b.size() - 1)).f2938c - ((VolleyLog) this.f2940b.get(0)).f2938c;
            }
            if (j > 0) {
                long j2 = ((VolleyLog) this.f2940b.get(0)).f2938c;
                VolleyLog.m4737b("(%-4d ms) %s", Long.valueOf(j), str);
                j = j2;
                for (VolleyLog volleyLog : this.f2940b) {
                    VolleyLog.m4737b("(+%-4d) [%2d] %s", Long.valueOf(volleyLog.f2938c - j), Long.valueOf(volleyLog.f2937b), volleyLog.f2936a);
                    j = volleyLog.f2938c;
                }
            }
        }

        protected final void finalize() {
            if (!this.f2941c) {
                m4733a("Request on the loose");
                VolleyLog.m4738c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
    }

    static {
        String str = "Volley";
        f2943b = str;
        f2942a = Log.isLoggable(str, 2);
    }

    public static void m4735a(String str, Object... objArr) {
        if (f2942a) {
            String str2 = f2943b;
            VolleyLog.m4739d(str, objArr);
        }
    }

    public static void m4737b(String str, Object... objArr) {
        String str2 = f2943b;
        VolleyLog.m4739d(str, objArr);
    }

    public static void m4738c(String str, Object... objArr) {
        String str2 = f2943b;
        VolleyLog.m4739d(str, objArr);
    }

    public static void m4736a(Throwable th, String str, Object... objArr) {
        String str2 = f2943b;
        VolleyLog.m4739d(str, objArr);
    }

    private static String m4739d(String str, Object... objArr) {
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
