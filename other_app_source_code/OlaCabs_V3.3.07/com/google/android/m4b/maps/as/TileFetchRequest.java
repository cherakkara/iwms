package com.google.android.m4b.maps.as;

import com.google.android.m4b.maps.an.ac;

/* renamed from: com.google.android.m4b.maps.as.b */
public final class TileFetchRequest {
    public final ac f4021a;
    public final boolean f4022b;
    public final long f4023c;

    public TileFetchRequest(ac acVar, long j, boolean z) {
        this.f4021a = acVar;
        this.f4022b = z;
        this.f4023c = j;
    }

    public final String toString() {
        return this.f4021a + ", isLocalRequest=" + this.f4022b + ", fetchToken=" + this.f4023c;
    }
}
