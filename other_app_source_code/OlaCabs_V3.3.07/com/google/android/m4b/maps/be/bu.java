package com.google.android.m4b.maps.be;

/* compiled from: CsiReporter */
public interface bu {

    /* renamed from: com.google.android.m4b.maps.be.bu.a */
    public static class CsiReporter {
        protected final String f5901a;
        protected long f5902b;
        protected long f5903c;

        protected CsiReporter(String str) {
            this.f5901a = str;
            this.f5902b = -1;
            this.f5903c = -1;
        }

        protected final long m9050a() {
            return this.f5903c - this.f5902b;
        }
    }

    CsiReporter m9051a(String str);

    void m9052a();

    void m9053a(CsiReporter csiReporter);

    void m9054b();
}
