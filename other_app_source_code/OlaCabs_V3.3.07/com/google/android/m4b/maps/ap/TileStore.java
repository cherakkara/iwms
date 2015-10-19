package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.aq.TileCallback;

/* renamed from: com.google.android.m4b.maps.ap.j */
public interface TileStore {
    public static final ac f3757d;

    /* renamed from: com.google.android.m4b.maps.ap.j.a */
    public interface TileStore {
        void m5319a();

        void m5320a(aa aaVar);
    }

    aa m6171a(ac acVar, boolean z);

    void m6172a(ac acVar, TileCallback tileCallback);

    void m6173a(TileStore tileStore);

    void m6174b(ac acVar, TileCallback tileCallback);

    void m6175c();

    void m6176d();

    void m6177e();

    ai m6178h();

    static {
        f3757d = new ac(-1, -1, -1);
    }
}
