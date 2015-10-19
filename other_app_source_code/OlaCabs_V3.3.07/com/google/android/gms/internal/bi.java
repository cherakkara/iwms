package com.google.android.gms.internal;

import com.google.android.gms.common.api.C0218b.C0210a.C0214d;
import com.google.android.gms.common.api.C0226d.C0224d;

public final class bi implements C0214d {
    public static final bi f2393a;
    private final boolean f2394b;
    private final boolean f2395c;
    private final String f2396d;
    private final C0224d f2397e;

    /* renamed from: com.google.android.gms.internal.bi.a */
    public static final class C0489a {
        private boolean f2389a;
        private boolean f2390b;
        private String f2391c;
        private C0224d f2392d;

        public bi m4070a() {
            return new bi(this.f2390b, this.f2391c, this.f2392d, null);
        }
    }

    static {
        f2393a = new C0489a().m4070a();
    }

    private bi(boolean z, boolean z2, String str, C0224d c0224d) {
        this.f2394b = z;
        this.f2395c = z2;
        this.f2396d = str;
        this.f2397e = c0224d;
    }

    public boolean m4071a() {
        return this.f2394b;
    }

    public boolean m4072b() {
        return this.f2395c;
    }

    public String m4073c() {
        return this.f2396d;
    }

    public C0224d m4074d() {
        return this.f2397e;
    }
}
