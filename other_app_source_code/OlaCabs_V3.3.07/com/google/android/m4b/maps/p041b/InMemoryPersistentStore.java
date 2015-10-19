package com.google.android.m4b.maps.p041b;

import java.util.Hashtable;

/* renamed from: com.google.android.m4b.maps.b.f */
public final class InMemoryPersistentStore implements PersistentStore {
    private final Hashtable<String, byte[]> f5020a;
    private Hashtable<String, byte[]> f5021b;

    public InMemoryPersistentStore() {
        this.f5020a = new Hashtable();
        this.f5021b = new Hashtable();
    }

    public final synchronized boolean m7766b(String str) {
        boolean z;
        if (this.f5021b.containsKey(str)) {
            this.f5021b.remove(str);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public final synchronized int m7762a(byte[] bArr, String str) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        this.f5021b.put(str, bArr);
        return bArr.length;
    }

    public final byte[] m7767c(String str) {
        return (byte[]) this.f5021b.get(str);
    }

    public final boolean m7764a(String str, byte[] bArr) {
        if (bArr == null) {
            this.f5020a.remove(str);
        } else {
            this.f5020a.put(str, bArr);
        }
        return true;
    }

    public final byte[] m7765a(String str) {
        return (byte[]) this.f5020a.get(str);
    }

    public final void m7763a() {
    }
}
