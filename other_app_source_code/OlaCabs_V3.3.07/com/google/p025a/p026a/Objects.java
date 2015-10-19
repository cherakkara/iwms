package com.google.p025a.p026a;

import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.g */
public final class Objects {

    /* renamed from: com.google.a.a.g.a */
    public static final class Objects {
        private final String f1355a;
        private Objects f1356b;
        private Objects f1357c;
        private boolean f1358d;

        /* renamed from: com.google.a.a.g.a.a */
        private static final class Objects {
            String f1352a;
            Object f1353b;
            Objects f1354c;

            private Objects() {
            }
        }

        private Objects(String str) {
            this.f1356b = new Objects();
            this.f1357c = this.f1356b;
            this.f1358d = false;
            this.f1355a = (String) Preconditions.m1817a((Object) str);
        }

        public Objects m1806a(String str, @Nullable Object obj) {
            return m1801b(str, obj);
        }

        public Objects m1807a(String str, boolean z) {
            return m1801b(str, String.valueOf(z));
        }

        public Objects m1803a(String str, float f) {
            return m1801b(str, String.valueOf(f));
        }

        public Objects m1804a(String str, int i) {
            return m1801b(str, String.valueOf(i));
        }

        public Objects m1805a(String str, long j) {
            return m1801b(str, String.valueOf(j));
        }

        public Objects m1802a(@Nullable Object obj) {
            return m1800b(obj);
        }

        public String toString() {
            boolean z = this.f1358d;
            String str = Trace.NULL;
            StringBuilder append = new StringBuilder(32).append(this.f1355a).append('{');
            String str2 = str;
            Objects objects = this.f1356b.f1354c;
            while (objects != null) {
                if (!z || objects.f1353b != null) {
                    append.append(str2);
                    str2 = ", ";
                    if (objects.f1352a != null) {
                        append.append(objects.f1352a).append('=');
                    }
                    append.append(objects.f1353b);
                }
                objects = objects.f1354c;
            }
            return append.append('}').toString();
        }

        private Objects m1799a() {
            Objects objects = new Objects();
            this.f1357c.f1354c = objects;
            this.f1357c = objects;
            return objects;
        }

        private Objects m1800b(@Nullable Object obj) {
            m1799a().f1353b = obj;
            return this;
        }

        private Objects m1801b(String str, @Nullable Object obj) {
            Objects a = m1799a();
            a.f1353b = obj;
            a.f1352a = (String) Preconditions.m1817a((Object) str);
            return this;
        }
    }

    public static boolean m1811a(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int m1808a(@Nullable Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Objects m1809a(Object obj) {
        return new Objects(null);
    }

    private static String m1810a(Class<?> cls) {
        String replaceAll = cls.getName().replaceAll("\\$[0-9]+", "\\$");
        int lastIndexOf = replaceAll.lastIndexOf(36);
        if (lastIndexOf == -1) {
            lastIndexOf = replaceAll.lastIndexOf(46);
        }
        return replaceAll.substring(lastIndexOf + 1);
    }

    public static <T> T m1812b(@Nullable T t, @Nullable T t2) {
        return t != null ? t : Preconditions.m1817a((Object) t2);
    }
}
