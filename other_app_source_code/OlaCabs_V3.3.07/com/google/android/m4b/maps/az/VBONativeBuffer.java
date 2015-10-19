package com.google.android.m4b.maps.az;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* renamed from: com.google.android.m4b.maps.az.j */
public final class VBONativeBuffer {
    private ByteBuffer f4996a;
    private ShortBuffer f4997b;
    private IntBuffer f4998c;

    public final ByteBuffer m7721a() {
        if (this.f4996a == null) {
            this.f4996a = ByteBuffer.allocateDirect(196608);
            this.f4996a.order(ByteOrder.nativeOrder());
        }
        this.f4996a.position(0);
        this.f4996a.limit(196608);
        return this.f4996a;
    }

    public final ShortBuffer m7722b() {
        if (this.f4997b == null) {
            this.f4997b = m7721a().asShortBuffer();
        }
        this.f4997b.position(0);
        this.f4997b.limit(98304);
        return this.f4997b;
    }

    public final IntBuffer m7723c() {
        if (this.f4998c == null) {
            this.f4998c = m7721a().asIntBuffer();
        }
        this.f4998c.position(0);
        this.f4998c.limit(49152);
        return this.f4998c;
    }
}
