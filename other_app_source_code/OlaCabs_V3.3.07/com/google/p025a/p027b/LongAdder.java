package com.google.p025a.p027b;

import com.google.p025a.p027b.Striped64.Striped64;
import java.io.Serializable;

/* renamed from: com.google.a.b.i */
final class LongAdder extends Striped64 implements LongAddable, Serializable {
    final long m2268a(long j, long j2) {
        return j + j2;
    }

    public void m2270a(long j) {
        long j2;
        boolean z = true;
        Striped64[] striped64Arr = this.c;
        if (striped64Arr == null) {
            j2 = this.d;
            if (m2266b(j2, j2 + j)) {
                return;
            }
        }
        Striped64 striped64 = (Striped64) a.get();
        int i = striped64.f1579b;
        if (striped64Arr != null) {
            int length = striped64Arr.length;
            if (length >= 1) {
                Striped64 striped642 = striped64Arr[i & (length - 1)];
                if (striped642 != null) {
                    j2 = striped642.f1577a;
                    z = striped642.m2281a(j2, j2 + j);
                    if (z) {
                        return;
                    }
                }
            }
        }
        m2265a(j, striped64, z);
    }

    public void m2269a() {
        m2270a(1);
    }

    public long m2271b() {
        long j = this.d;
        Striped64[] striped64Arr = this.c;
        if (striped64Arr != null) {
            for (Striped64 striped64 : striped64Arr) {
                if (striped64 != null) {
                    j += striped64.f1577a;
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(m2271b());
    }

    public long longValue() {
        return m2271b();
    }

    public int intValue() {
        return (int) m2271b();
    }

    public float floatValue() {
        return (float) m2271b();
    }

    public double doubleValue() {
        return (double) m2271b();
    }
}
