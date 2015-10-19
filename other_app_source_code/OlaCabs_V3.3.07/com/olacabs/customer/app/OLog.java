package com.olacabs.customer.app;

import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.olacabs.customer.app.l */
public class OLog {
    private static final OLog f9391a;

    /* renamed from: com.olacabs.customer.app.l.c */
    public interface OLog {
        void m13277a(String str, Object... objArr);

        void m13278a(Throwable th, String str, Object... objArr);

        void m13279b(String str, Object... objArr);

        void m13280b(Throwable th, String str, Object... objArr);

        void m13281c(String str, Object... objArr);

        void m13282c(Throwable th, String str, Object... objArr);

        void m13283d(String str, Object... objArr);

        void m13284e(String str, Object... objArr);

        void m13285f(String str, Object... objArr);
    }

    /* renamed from: com.olacabs.customer.app.l.a */
    public static class OLog implements OLog {
        public void m13287a(String str, Object... objArr) {
        }

        public void m13289b(String str, Object... objArr) {
        }

        public void m13291c(String str, Object... objArr) {
        }

        public void m13293d(String str, Object... objArr) {
            Crashlytics.log(5, "WARN", OLog.m13286g(str, objArr));
        }

        public void m13288a(Throwable th, String str, Object... objArr) {
            Crashlytics.log(5, "WARN", OLog.m13286g(str + " -- " + th.toString(), objArr));
        }

        public void m13294e(String str, Object... objArr) {
            Crashlytics.logException(new Exception("ERROR : " + OLog.m13286g(str, objArr)));
        }

        public void m13290b(Throwable th, String str, Object... objArr) {
            Crashlytics.logException(new Exception("ERROR : " + OLog.m13286g(str, objArr), th));
        }

        public void m13295f(String str, Object... objArr) {
            Crashlytics.logException(new Exception("WTF : " + OLog.m13286g(str, objArr)));
        }

        public void m13292c(Throwable th, String str, Object... objArr) {
            Crashlytics.logException(new Exception("WTF : " + OLog.m13286g(str, objArr), th));
        }

        private static String m13286g(String str, Object... objArr) {
            return objArr.length == 0 ? str : String.format(str, objArr);
        }
    }

    /* renamed from: com.olacabs.customer.app.l.d */
    public interface C0800d extends OLog {
    }

    /* renamed from: com.olacabs.customer.app.l.b */
    public static class OLog implements C0800d {
        private static final Pattern f9389a;
        private static final ThreadLocal<String> f9390b;

        static {
            f9389a = Pattern.compile("\\$\\d+$");
            f9390b = new ThreadLocal();
        }

        protected final String m13298a() {
            String str = (String) f9390b.get();
            if (str != null) {
                f9390b.remove();
            }
            return str;
        }

        protected String m13302b() {
            String a = m13298a();
            if (a != null) {
                return a;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length < 5) {
                return "TAG";
            }
            a = stackTrace[4].getClassName();
            Matcher matcher = f9389a.matcher(a);
            if (matcher.find()) {
                a = matcher.replaceAll(Trace.NULL);
            }
            return a.substring(a.lastIndexOf(46) + 1);
        }

        private static String m13297g(String str, Object... objArr) {
            return objArr.length == 0 ? str : String.format(str, objArr);
        }

        public final void m13300a(String str, Object... objArr) {
            m13296a(2, OLog.m13297g(str, objArr), null);
        }

        public final void m13303b(String str, Object... objArr) {
            m13296a(3, OLog.m13297g(str, objArr), null);
        }

        public final void m13305c(String str, Object... objArr) {
            m13296a(4, OLog.m13297g(str, objArr), null);
        }

        public final void m13307d(String str, Object... objArr) {
            m13296a(5, OLog.m13297g(str, objArr), null);
        }

        public final void m13301a(Throwable th, String str, Object... objArr) {
            m13296a(5, OLog.m13297g(str, objArr), th);
        }

        public final void m13308e(String str, Object... objArr) {
            m13296a(6, OLog.m13297g(str, objArr), null);
        }

        public final void m13304b(Throwable th, String str, Object... objArr) {
            m13296a(6, OLog.m13297g(str, objArr), th);
        }

        public void m13309f(String str, Object... objArr) {
            m13296a(7, OLog.m13297g(str, objArr), null);
        }

        public void m13306c(Throwable th, String str, Object... objArr) {
            m13296a(7, OLog.m13297g(str, objArr), th);
        }

        private void m13296a(int i, String str, Throwable th) {
            if (str == null || str.length() == 0) {
                if (th != null) {
                    str = Log.getStackTraceString(th);
                } else {
                    return;
                }
            } else if (th != null) {
                str = str + "\n" + Log.getStackTraceString(th);
            }
            m13299a(i, m13302b(), str);
        }

        protected void m13299a(int i, String str, String str2) {
            if (str2.length() < 4000) {
                Log.println(i, str, str2);
                return;
            }
            int i2 = 0;
            int length = str2.length();
            while (i2 < length) {
                int min;
                int indexOf = str2.indexOf(10, i2);
                if (indexOf == -1) {
                    indexOf = length;
                }
                while (true) {
                    min = Math.min(indexOf, i2 + 4000);
                    Log.println(i, str, str2.substring(i2, min));
                    if (min >= indexOf) {
                        break;
                    }
                    i2 = min;
                }
                i2 = min + 1;
            }
        }
    }

    static {
        if (AppInfo.sRunningMode == ApplicationMode.PROD) {
            f9391a = new OLog();
        } else {
            f9391a = new OLog();
        }
    }

    public static void m13311a(String str, Object... objArr) {
        f9391a.m13277a(str, objArr);
    }

    public static void m13313b(String str, Object... objArr) {
        f9391a.m13279b(str, objArr);
    }

    public static void m13315c(String str, Object... objArr) {
        f9391a.m13281c(str, objArr);
    }

    public static void m13317d(String str, Object... objArr) {
        f9391a.m13283d(str, objArr);
    }

    public static void m13312a(Throwable th, String str, Object... objArr) {
        f9391a.m13278a(th, str, objArr);
    }

    @Deprecated
    public static void m13310a(String str, Throwable th) {
        f9391a.m13280b(th, str, new Object[0]);
    }

    public static void m13318e(String str, Object... objArr) {
        f9391a.m13284e(str, objArr);
    }

    public static void m13314b(Throwable th, String str, Object... objArr) {
        f9391a.m13280b(th, str, objArr);
    }

    public static void m13319f(String str, Object... objArr) {
        f9391a.m13285f(str, objArr);
    }

    public static void m13316c(Throwable th, String str, Object... objArr) {
        f9391a.m13282c(th, str, objArr);
    }
}
