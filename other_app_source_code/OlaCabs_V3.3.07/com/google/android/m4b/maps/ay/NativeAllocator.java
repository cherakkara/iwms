package com.google.android.m4b.maps.ay;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import com.google.android.m4b.maps.av.Renderer;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.p040u.StaticUtil;
import com.google.android.m4b.maps.p058v.Util;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.nio.ByteBuffer;

/* renamed from: com.google.android.m4b.maps.ay.h */
public final class NativeAllocator {
    private final Renderer f4912a;

    public NativeAllocator(Renderer renderer) {
        this.f4912a = renderer;
    }

    public final Bitmap m7591a(int i, int i2, Config config) {
        try {
            return Bitmap.createBitmap(i, i2, config);
        } catch (OutOfMemoryError e) {
            m7590a("OutOfMemory in createBitmap");
            return Bitmap.createBitmap(i, i2, config);
        }
    }

    public final Bitmap m7592a(byte[] bArr, Options options) {
        try {
            return BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (OutOfMemoryError e) {
            m7590a("OutOfMemory in decodeByteArray");
            return BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
        }
    }

    public final ByteBuffer m7593a(int i) {
        try {
            return ByteBuffer.allocateDirect(i);
        } catch (OutOfMemoryError e) {
            m7590a("OutOfMemory in allocateDirectByteBuffer");
            return ByteBuffer.allocateDirect(i);
        }
    }

    private void m7590a(String str) {
        Util.m11550a("NativeAllocator", str);
        VectorGlobalState.m7287c();
        StaticUtil.m11486a();
        this.f4912a.m7255c(true);
        System.gc();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
    }
}
