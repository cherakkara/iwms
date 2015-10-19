package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.p046d.ProtoBuf;

/* renamed from: com.google.android.m4b.maps.aa.c */
public final class EnableFeatureParameters {
    private volatile boolean f2948a;
    private volatile boolean f2949b;
    private volatile boolean f2950c;
    private volatile boolean f2951d;
    private volatile boolean f2952e;
    private volatile boolean f2953f;
    private volatile boolean f2954g;
    private volatile boolean f2955h;
    private volatile boolean f2956i;
    private volatile boolean f2957j;
    private volatile boolean f2958k;
    private volatile boolean f2959l;
    private volatile boolean f2960m;
    private volatile boolean f2961n;
    private volatile boolean f2962o;
    private volatile boolean f2963p;
    private volatile boolean f2964q;
    private volatile boolean f2965r;
    private volatile boolean f2966s;
    private volatile boolean f2967t;
    private volatile boolean f2968u;
    private volatile boolean f2969v;
    private volatile boolean f2970w;
    private volatile boolean f2971x;

    public EnableFeatureParameters(ProtoBuf protoBuf) {
        this.f2948a = true;
        this.f2949b = true;
        this.f2950c = true;
        this.f2951d = true;
        this.f2952e = true;
        this.f2953f = true;
        this.f2954g = true;
        this.f2955h = true;
        this.f2956i = true;
        this.f2957j = true;
        this.f2958k = true;
        this.f2959l = true;
        this.f2960m = true;
        this.f2961n = true;
        this.f2962o = true;
        this.f2963p = true;
        this.f2964q = true;
        this.f2965r = true;
        this.f2966s = true;
        this.f2967t = true;
        this.f2968u = true;
        this.f2969v = true;
        this.f2970w = true;
        this.f2971x = true;
        m4743b(protoBuf);
    }

    public final void m4744a(ProtoBuf protoBuf) {
        m4743b(protoBuf);
    }

    private void m4743b(ProtoBuf protoBuf) {
        boolean z = true;
        boolean z2 = this.f2948a && protoBuf.m10200b(1);
        this.f2948a = z2;
        if (this.f2949b && protoBuf.m10200b(2)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2949b = z2;
        if (this.f2950c && protoBuf.m10200b(3)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2950c = z2;
        if (this.f2951d && protoBuf.m10200b(4)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2951d = z2;
        if (this.f2952e && protoBuf.m10200b(5)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2952e = z2;
        if (this.f2953f && protoBuf.m10200b(6)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2953f = z2;
        if (this.f2954g && protoBuf.m10200b(10)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2954g = z2;
        if (this.f2955h && protoBuf.m10200b(7)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2955h = z2;
        if (this.f2956i && protoBuf.m10200b(8)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2956i = z2;
        if (this.f2957j && protoBuf.m10200b(9)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2957j = z2;
        if (this.f2958k && protoBuf.m10200b(11)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2958k = z2;
        if (this.f2959l && protoBuf.m10200b(12)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2959l = z2;
        if (this.f2960m && protoBuf.m10200b(13)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2960m = z2;
        if (this.f2961n && protoBuf.m10200b(14)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2961n = z2;
        if (this.f2962o && protoBuf.m10200b(15)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2962o = z2;
        if (this.f2963p && protoBuf.m10200b(16)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2963p = z2;
        this.f2965r = protoBuf.m10200b(17);
        if (this.f2964q && protoBuf.m10200b(18)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2964q = z2;
        if (this.f2966s && protoBuf.m10200b(19)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2966s = z2;
        if (this.f2968u && protoBuf.m10200b(20)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2968u = z2;
        this.f2969v = protoBuf.m10200b(21);
        if (this.f2970w && protoBuf.m10200b(22)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f2970w = z2;
        if (!(this.f2971x && protoBuf.m10200b(23))) {
            z = false;
        }
        this.f2971x = z;
    }

    public final boolean m4745a() {
        return this.f2966s;
    }
}
