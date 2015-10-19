package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.C0452t;

/* renamed from: com.google.android.gms.internal.c */
public final class C0498c extends C0401d<C0497a, Drawable> {

    /* renamed from: com.google.android.gms.internal.c.a */
    public static final class C0497a {
        public final int f2414a;
        public final int f2415b;

        public C0497a(int i, int i2) {
            this.f2414a = i;
            this.f2415b = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0497a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0497a c0497a = (C0497a) obj;
            return c0497a.f2414a == this.f2414a && c0497a.f2415b == this.f2415b;
        }

        public int hashCode() {
            return C0452t.m3843a(Integer.valueOf(this.f2414a), Integer.valueOf(this.f2415b));
        }
    }
}
