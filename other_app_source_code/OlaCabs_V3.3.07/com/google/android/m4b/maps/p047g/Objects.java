package com.google.android.m4b.maps.p047g;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.g.j */
public final class Objects {

    /* renamed from: com.google.android.m4b.maps.g.j.a */
    public static final class Objects {
        private final List<String> f7426a;
        private final Object f7427b;

        private Objects(Object obj) {
            this.f7427b = Preconditions.m10459a(obj);
            this.f7426a = new ArrayList();
        }

        public final Objects m10455a(String str, Object obj) {
            this.f7426a.add(((String) Preconditions.m10459a((Object) str)) + "=" + String.valueOf(obj));
            return this;
        }

        public final String toString() {
            StringBuilder append = new StringBuilder(100).append(this.f7427b.getClass().getSimpleName()).append('{');
            int size = this.f7426a.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.f7426a.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static boolean m10457a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Objects m10456a(Object obj) {
        return new Objects((byte) 0);
    }
}
