package com.google.android.m4b.maps.ak;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayOutputStream;

/* renamed from: com.google.android.m4b.maps.ak.h */
public final class PoolingByteArrayOutputStream extends ByteArrayOutputStream {
    private final ByteArrayPool f3271a;

    public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool, int i) {
        this.f3271a = byteArrayPool;
        this.buf = this.f3271a.m5219a(Math.max(i, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH));
    }

    public final void close() {
        this.f3271a.m5218a(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.f3271a.m5218a(this.buf);
    }

    private void m5252a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.f3271a.m5219a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.f3271a.m5218a(this.buf);
            this.buf = a;
        }
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        m5252a(i2);
        super.write(bArr, i, i2);
    }

    public final synchronized void write(int i) {
        m5252a(1);
        super.write(i);
    }
}
