package com.google.android.m4b.maps.p041b;

import java.io.IOException;

/* renamed from: com.google.android.m4b.maps.b.i */
public interface PersistentStore {

    /* renamed from: com.google.android.m4b.maps.b.i.a */
    public static class PersistentStore extends IOException {
        private final int f5027a;

        public PersistentStore(String str, int i) {
            super(str);
            this.f5027a = -1;
        }

        public final int m7782a() {
            return this.f5027a;
        }
    }

    int m7752a(byte[] bArr, String str);

    void m7753a();

    boolean m7754a(String str, byte[] bArr);

    byte[] m7755a(String str);

    boolean m7756b(String str);

    byte[] m7757c(String str);
}
