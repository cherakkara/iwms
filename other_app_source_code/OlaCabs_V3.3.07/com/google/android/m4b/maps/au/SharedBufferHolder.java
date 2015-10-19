package com.google.android.m4b.maps.au;

import java.lang.ref.SoftReference;

/* renamed from: com.google.android.m4b.maps.au.j */
public final class SharedBufferHolder {
    private static final Object f4109a;
    private static final Object f4110b;
    private static final ThreadLocal<Object> f4111c;
    private static final ThreadLocal<SoftReference<byte[]>> f4112d;

    /* renamed from: com.google.android.m4b.maps.au.j.1 */
    static class SharedBufferHolder extends ThreadLocal<Object> {
        SharedBufferHolder() {
        }

        protected final Object initialValue() {
            return SharedBufferHolder.f4110b;
        }
    }

    static {
        f4109a = new Object();
        f4110b = new Object();
        f4111c = new SharedBufferHolder();
        f4112d = new ThreadLocal();
    }

    public static byte[] m6656a(int i) {
        if (f4111c.get() != f4109a) {
            return null;
        }
        SoftReference softReference = (SoftReference) f4112d.get();
        if (softReference != null) {
            byte[] bArr = (byte[]) softReference.get();
            if (bArr != null && bArr.length >= i) {
                f4112d.remove();
                return bArr;
            }
        }
        return null;
    }

    public static void m6655a(byte[] bArr) {
        if (f4111c.get() == f4109a) {
            f4112d.set(new SoftReference(bArr));
        }
    }

    public static void m6654a() {
        f4111c.set(f4109a);
    }

    public static void m6657b() {
        f4111c.remove();
        f4112d.remove();
    }
}
