package com.google.android.m4b.maps.ay;

import android.util.FloatMath;
import com.google.android.m4b.maps.az.IndexBufferInterface;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.ay.o */
public final class VertexDataCache {
    static {
        float[] fArr = new float[]{0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f};
        fArr = new float[]{0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT};
        fArr = new float[]{-1.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, 0.0f, br.DEFAULT_BACKOFF_MULT, -1.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, br.DEFAULT_BACKOFF_MULT};
    }

    public static void m7638a(VertexBufferInterface vertexBufferInterface, IndexBufferInterface indexBufferInterface) {
        float tan = (float) Math.tan(0.06283185631036758d);
        float cos = FloatMath.cos(0.06283186f);
        float f = br.DEFAULT_BACKOFF_MULT;
        float f2 = 0.0f;
        for (int i = 0; i < 100; i++) {
            vertexBufferInterface.m7566a(f + 0.0f, f2 + 0.0f, 0.0f);
            if (indexBufferInterface != null) {
                indexBufferInterface.m7558d((short) i);
            }
            float f3 = ((-f2) * tan) + f;
            f2 += f * tan;
            f = f3 * cos;
            f2 *= cos;
        }
    }

    public static void m7639b(VertexBufferInterface vertexBufferInterface, IndexBufferInterface indexBufferInterface) {
        int i = 0;
        float tan = (float) Math.tan(0.06283185631036758d);
        float cos = FloatMath.cos(0.06283186f);
        vertexBufferInterface.m7566a(0.0f, 0.0f, 0.0f);
        if (indexBufferInterface != null) {
            indexBufferInterface.m7558d(0);
        }
        float f = 0.0f;
        float f2 = br.DEFAULT_BACKOFF_MULT;
        while (i < 100) {
            vertexBufferInterface.m7566a(f2 + 0.0f, f + 0.0f, 0.0f);
            if (indexBufferInterface != null) {
                indexBufferInterface.m7558d((short) (i + 1));
            }
            float f3 = ((-f) * tan) + f2;
            f += f2 * tan;
            f2 = f3 * cos;
            f *= cos;
            i++;
        }
        if (indexBufferInterface != null) {
            indexBufferInterface.m7558d(1);
        } else {
            vertexBufferInterface.m7566a((float) br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
        }
    }
}
