package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.gms.common.internal.t */
public final class C0452t {

    /* renamed from: com.google.android.gms.common.internal.t.a */
    public static final class C0451a {
        private final List<String> f2309a;
        private final Object f2310b;

        private C0451a(Object obj) {
            this.f2310b = C0453u.m3846a(obj);
            this.f2309a = new ArrayList();
        }

        public C0451a m3842a(String str, Object obj) {
            this.f2309a.add(((String) C0453u.m3846a((Object) str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.f2310b.getClass().getSimpleName()).append('{');
            int size = this.f2309a.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.f2309a.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static int m3843a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static C0451a m3844a(Object obj) {
        return new C0451a(null);
    }

    public static boolean m3845a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
