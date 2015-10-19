package com.google.android.m4b.maps.bj;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import java.util.BitSet;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.bj.f */
final class TextureCache extends LRUCache<ac, Integer> {
    private final BitSet f6496b;
    private final BitSet f6497c;
    private final int[] f6498d;
    private GL10 f6499e;

    protected final /* synthetic */ void m9930a(Object obj, Object obj2) {
        Integer num = (Integer) obj2;
        if (this.f6496b.get(num.intValue())) {
            this.f6496b.clear(num.intValue());
            return;
        }
        throw new IllegalArgumentException("Ejecting unused texture " + num);
    }

    public TextureCache(int i) {
        super(17);
        this.f6496b = new BitSet(17);
        this.f6497c = new BitSet(17);
        this.f6498d = new int[17];
    }

    public final void m9931a(GL10 gl10) {
        this.f6499e = gl10;
        gl10.glGenTextures(this.a, this.f6498d, 0);
    }

    public final void m9928a() {
        int[] iArr = this.f6498d;
        if (this.f6499e != null) {
            this.f6499e.glDeleteTextures(this.a, this.f6498d, 0);
        }
        m9924b();
        this.f6497c.clear();
        this.f6499e = null;
    }

    public final void m9929a(ac acVar, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (m9921a((Object) acVar) != null) {
                    throw new IllegalArgumentException("Already cached " + acVar);
                }
                m9926c();
                int nextClearBit = this.f6496b.nextClearBit(0);
                if (nextClearBit >= this.a) {
                    throw new RuntimeException("Ran out of texture cache slots.");
                }
                GL10 gl10 = this.f6499e;
                if (gl10 != null) {
                    gl10.glBindTexture(3553, this.f6498d[nextClearBit]);
                    if (this.f6497c.get(nextClearBit)) {
                        GLUtils.texImage2D(3553, 0, bitmap, 0);
                    } else {
                        gl10.glTexParameterf(3553, 10241, 9728.0f);
                        gl10.glTexParameterf(3553, 10240, 9729.0f);
                        gl10.glTexParameterf(3553, 10242, 33071.0f);
                        gl10.glTexParameterf(3553, 10243, 33071.0f);
                        gl10.glTexEnvf(8960, 8704, 7681.0f);
                        GLUtils.texImage2D(3553, 0, bitmap, 0);
                        this.f6497c.set(nextClearBit);
                    }
                    int glGetError = gl10.glGetError();
                    if (glGetError != 0) {
                        throw new RuntimeException("glError: " + Integer.toString(glGetError));
                    }
                    this.f6496b.set(nextClearBit);
                    m9925b(acVar, Integer.valueOf(nextClearBit));
                }
                bitmap.recycle();
            } catch (Throwable th) {
                bitmap.recycle();
            }
        }
    }

    public final int m9927a(ac acVar) {
        Integer num = (Integer) m9921a((Object) acVar);
        if (num == null) {
            return 0;
        }
        return this.f6498d[num.intValue()];
    }
}
