package com.google.android.m4b.maps.au;

import java.util.zip.Inflater;

/* renamed from: com.google.android.m4b.maps.au.e */
public final class Inflate {
    private static final byte[] f4078a;

    /* renamed from: com.google.android.m4b.maps.au.e.a */
    public static class Inflate {
        private final byte[] f4076a;
        private final int f4077b;

        public Inflate(byte[] bArr, int i) {
            this.f4076a = bArr;
            this.f4077b = i;
        }

        public final byte[] m6620a() {
            return this.f4076a;
        }

        public final int m6621b() {
            return this.f4077b;
        }
    }

    static {
        f4078a = new byte[]{(byte) 0};
    }

    public static Inflate m6622a(byte[] bArr, int i, int i2) {
        Inflate inflate = true;
        Object obj = null;
        Inflater inflater = new Inflater(true);
        try {
            byte[] bArr2;
            inflater.setInput(bArr, i, i2);
            int i3 = i2 * 4;
            Object a = SharedBufferHolder.m6656a(i3);
            if (a == null) {
                a = new byte[i3];
            }
            int inflate2 = inflater.inflate(a);
            while (!inflater.finished()) {
                Object obj2 = new byte[(a.length * 2)];
                System.arraycopy(a, 0, obj2, 0, inflate2);
                int inflate3 = inflater.inflate(obj2, inflate2, obj2.length - inflate2);
                if (inflate3 == 0) {
                    if (!inflater.needsInput() || r0 != null) {
                        bArr2 = obj2;
                        break;
                    }
                    inflater.setInput(f4078a);
                    obj = 1;
                }
                inflate2 = inflate3 + inflate2;
                a = obj2;
            }
            obj = a;
            inflate = new Inflate(bArr2, inflate2);
            return inflate;
        } finally {
            inflater.end();
        }
    }
}
