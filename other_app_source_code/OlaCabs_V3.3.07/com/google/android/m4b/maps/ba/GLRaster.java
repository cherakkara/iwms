package com.google.android.m4b.maps.ba;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.Raster;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.p060x.Fade;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.google.android.m4b.maps.ba.o */
public final class GLRaster extends GLFeature {
    private static final Map<Float, TexCoordBuffer> f5301h;
    private volatile Texture f5302b;
    private TexCoordBuffer f5303c;
    private final byte[] f5304d;
    private Fade f5305e;
    private boolean f5306f;
    private long f5307g;

    static {
        f5301h = Collections.synchronizedMap(new TreeMap());
    }

    public static GLRaster m8082a(ac acVar, String[] strArr, VectorTile vectorTile, GLState gLState) {
        Set hashSet = new HashSet();
        Raster raster = (Raster) vectorTile.next();
        for (int i : raster.m6038k()) {
            if (i >= 0 && i < strArr.length) {
                hashSet.add(strArr[i]);
            }
        }
        return new GLRaster(raster.m6033a(), hashSet);
    }

    public static GLRaster m8083a(byte[] bArr, ac acVar, GLState gLState) {
        return new GLRaster(bArr, new HashSet());
    }

    private GLRaster(byte[] bArr, Set<String> set) {
        super(set);
        this.f5304d = bArr;
        this.f5306f = true;
        this.f5307g = 0;
    }

    private Bitmap m8085d(GLState gLState) {
        Options options = new Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inPreferredConfig = Config.RGB_565;
        try {
            return gLState.m7529l().m7592a(this.f5304d, options);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    public final void m8090b(GLState gLState) {
        if (this.f5302b != null) {
            this.f5302b.m7626f();
            this.f5302b = null;
            this.f5306f = true;
        }
    }

    public final void m8091c(GLState gLState) {
        m8090b(gLState);
        this.a.clear();
    }

    public static void m8084a(GLState gLState) {
        GL10 x = gLState.m7541x();
        x.glBlendFunc(1, 771);
        x.glTexEnvx(8960, 8704, 8448);
        gLState.m7533p();
        gLState.f4851e.m7736d(gLState);
    }

    public final void m8088a(GLState gLState, Camera camera, ad adVar) {
        Texture texture = this.f5302b;
        if (texture == null) {
            Bitmap d;
            if (!this.f5306f) {
                gLState.m7518b(20000);
                d = m8085d(gLState);
            } else if (gLState.m7516a(20000)) {
                Bitmap d2 = m8085d(gLState);
                this.f5305e = new Fade(this.f5307g, 250, Fade.Fade.FADE_IN);
                d = d2;
            } else {
                d = null;
            }
            if (d != null) {
                TexCoordBuffer texCoordBuffer;
                Texture texture2 = new Texture(gLState);
                texture2.m7621c(true);
                texture2.m7617b(d);
                this.f5302b = texture2;
                float b = texture2.m7615b();
                if (f5301h.containsKey(Float.valueOf(b))) {
                    texCoordBuffer = (TexCoordBuffer) f5301h.get(Float.valueOf(b));
                } else {
                    texCoordBuffer = new TexCoordBuffer(8);
                    int i = (int) (65536.0f * b);
                    texCoordBuffer.m7693a(0, 0);
                    texCoordBuffer.m7693a(0, i);
                    texCoordBuffer.m7693a(i, 0);
                    texCoordBuffer.m7693a(i, i);
                    f5301h.put(Float.valueOf(b), texCoordBuffer);
                }
                this.f5303c = texCoordBuffer;
                d.recycle();
                texture = texture2;
            }
        }
        if (texture != null) {
            int a;
            GL10 x = gLState.m7541x();
            this.f5303c.m7706d(gLState);
            texture.m7613a(x);
            if (this.f5305e != null) {
                a = this.f5305e.m11584a(gLState);
                if (a == AccessibilityNodeInfoCompat.ACTION_CUT) {
                    this.f5305e = null;
                    this.f5306f = false;
                }
            } else {
                a = AccessibilityNodeInfoCompat.ACTION_CUT;
            }
            x.glColor4x(a, a, a, a);
            x.glDrawArrays(5, 0, 4);
        }
    }

    public final boolean m8092c() {
        return this.f5306f;
    }

    public final void m8093e() {
        this.f5306f = false;
    }

    public final void m8087a(long j) {
        this.f5307g = j;
    }

    public final int m8086a() {
        Texture texture = this.f5302b;
        if (texture != null) {
            return texture.m7628h() + 0;
        }
        return 0;
    }

    public final int m8089b() {
        if (this.f5304d != null) {
            return (this.f5304d.length + 16) + 96;
        }
        return 96;
    }
}
