package com.google.android.m4b.maps.p040u;

import java.io.DataInput;
import java.io.DataOutput;

/* renamed from: com.google.android.m4b.maps.u.o */
public class SimpleDataRequest extends BaseDataRequest {
    private final int f7943a;
    private final byte[] f7944b;
    private final boolean f7945c;
    private final boolean f7946d;
    private final boolean f7947e;
    private final Object f7948f;

    public SimpleDataRequest(int i, byte[] bArr, boolean z, boolean z2, boolean z3, Object obj) {
        this.f7943a = i;
        this.f7944b = bArr;
        this.f7945c = z;
        this.f7946d = z2;
        this.f7947e = z3;
        this.f7948f = obj;
    }

    public final int m11485i() {
        return this.f7943a;
    }

    public final boolean m11481a() {
        return this.f7945c;
    }

    public final boolean m11483b() {
        return this.f7946d;
    }

    public final boolean m11484e() {
        return this.f7947e;
    }

    public final void m11480a(DataOutput dataOutput) {
        dataOutput.write(this.f7944b);
        if (this.f7948f != null) {
            synchronized (this.f7948f) {
                this.f7948f.notifyAll();
            }
        }
    }

    public final boolean m11482a(DataInput dataInput) {
        if (this.f7948f != null) {
            synchronized (this.f7948f) {
                this.f7948f.notifyAll();
            }
        }
        return true;
    }
}
