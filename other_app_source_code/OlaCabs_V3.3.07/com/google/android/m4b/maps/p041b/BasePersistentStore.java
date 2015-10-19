package com.google.android.m4b.maps.p041b;

/* renamed from: com.google.android.m4b.maps.b.c */
public abstract class BasePersistentStore implements PersistentStore {
    private final PreferenceStore f5014a;

    public BasePersistentStore() {
        this.f5014a = new PreferenceStore(this);
    }

    public final boolean m7759a(String str, byte[] bArr) {
        return this.f5014a.m7786a(str, bArr);
    }

    public final byte[] m7760a(String str) {
        return this.f5014a.m7787a(str);
    }

    public final void m7758a() {
        this.f5014a.m7785a();
    }
}
