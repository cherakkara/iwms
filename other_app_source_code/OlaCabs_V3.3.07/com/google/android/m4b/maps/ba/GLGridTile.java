package com.google.android.m4b.maps.ba;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.cm.Clock;
import java.util.Collection;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.e */
public final class GLGridTile implements GLTile {
    private final ac f5079a;
    private final Rectangle2D f5080b;
    private final TexCoordBuffer f5081c;

    public GLGridTile(ac acVar, int i) {
        this.f5081c = new TexCoordBuffer(4);
        this.f5079a = acVar;
        this.f5080b = this.f5079a.m5446i();
        int i2 = (AccessibilityNodeInfoCompat.ACTION_CUT * i) / 32;
        this.f5081c.m7693a(0, 0);
        this.f5081c.m7693a(0, i2);
        this.f5081c.m7693a(i2, 0);
        this.f5081c.m7693a(i2, i2);
    }

    public final int m7853a(Camera camera, com.google.android.m4b.maps.av.ac acVar) {
        return 1;
    }

    public final void m7865b(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() <= 1) {
            GL10 x = gLState.m7541x();
            x.glBlendFunc(1, 771);
            x.glTexEnvx(8960, 8704, 7681);
            gLState.m7533p();
            gLState.f4851e.m7736d(gLState);
            gLState.m7513a().m7630a(20).m7613a(x);
        }
    }

    public final void m7856a(GLState gLState, Camera camera, ad adVar) {
        GL10 x = gLState.m7541x();
        x.glPushMatrix();
        Transform.m7275a(gLState, camera, this.f5080b.m6050c(), (float) this.f5080b.m6053f());
        this.f5081c.m7706d(gLState);
        x.glDrawArrays(5, 0, 4);
        x.glPopMatrix();
    }

    public final boolean m7860a() {
        return true;
    }

    public final boolean m7861a(RankMergingFeatureIterator rankMergingFeatureIterator) {
        return false;
    }

    public final void m7857a(LabelSource labelSource) {
    }

    public final void m7864b(GLState gLState) {
    }

    public final void m7867c(GLState gLState) {
    }

    public final ac m7863b() {
        return this.f5079a;
    }

    public final void m7859a(boolean z) {
    }

    public final boolean m7862a(Clock clock) {
        return false;
    }

    public final boolean m7866b(Clock clock) {
        return false;
    }

    public final boolean m7868c() {
        return false;
    }

    public final void m7869d() {
    }

    public final void m7855a(long j) {
    }

    public final void m7854a(int i, Collection<String> collection) {
    }

    public final void m7858a(Collection<String> collection) {
    }

    public final int m7870e() {
        return -1;
    }

    public final int m7871f() {
        return this.f5081c.m7701b();
    }

    public final int m7872g() {
        return this.f5081c.m7703c();
    }
}
