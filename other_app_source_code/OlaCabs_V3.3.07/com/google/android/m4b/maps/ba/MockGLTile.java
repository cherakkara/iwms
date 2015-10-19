package com.google.android.m4b.maps.ba;

import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.cm.Clock;
import java.util.Collection;

/* renamed from: com.google.android.m4b.maps.ba.u */
public final class MockGLTile implements GLTile {
    private final ac f5398a;

    public MockGLTile() {
        this.f5398a = new ac(0, 0, 0);
    }

    public final void m8161b(GLState gLState) {
    }

    public final void m8164c(GLState gLState) {
    }

    public final boolean m8157a() {
        return false;
    }

    public final ac m8160b() {
        return this.f5398a;
    }

    public final void m8153a(GLState gLState, Camera camera, ad adVar) {
    }

    public final boolean m8159a(Clock clock) {
        return false;
    }

    public final boolean m8163b(Clock clock) {
        return false;
    }

    public final boolean m8165c() {
        return false;
    }

    public final void m8166d() {
    }

    public final void m8152a(long j) {
    }

    public final int m8150a(Camera camera, com.google.android.m4b.maps.av.ac acVar) {
        return 0;
    }

    public final void m8162b(GLState gLState, Camera camera, ad adVar) {
    }

    public final void m8151a(int i, Collection<String> collection) {
    }

    public final void m8155a(Collection<String> collection) {
    }

    public final int m8167e() {
        return -1;
    }

    public final int m8169g() {
        return 0;
    }

    public final boolean m8158a(RankMergingFeatureIterator rankMergingFeatureIterator) {
        return false;
    }

    public final void m8154a(LabelSource labelSource) {
    }

    public final int m8168f() {
        return 0;
    }

    public final void m8156a(boolean z) {
    }
}
