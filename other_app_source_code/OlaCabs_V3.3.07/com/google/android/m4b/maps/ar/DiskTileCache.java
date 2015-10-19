package com.google.android.m4b.maps.ar;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.aq.TileCallback;
import java.io.File;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ar.c */
public interface DiskTileCache extends TileCache {
    void m6353a(ac acVar, aa aaVar, byte[] bArr);

    void m6354a(ac acVar, TileCallback tileCallback, int i);

    boolean m6355a(int i);

    boolean m6356a(File file);

    boolean m6357a(Locale locale);

    byte[] m6358a(ac acVar);

    boolean m6359b();

    int m6360c();

    Locale m6361d();

    void m6362e();

    void f_();
}
