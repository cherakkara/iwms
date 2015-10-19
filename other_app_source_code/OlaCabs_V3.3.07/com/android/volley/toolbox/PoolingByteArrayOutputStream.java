package com.android.volley.toolbox;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: com.android.volley.toolbox.l */
public class PoolingByteArrayOutputStream extends ByteArrayOutputStream {
    private final ByteArrayPool f595a;

    public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool, int i) {
        this.f595a = byteArrayPool;
        this.buf = this.f595a.m615a(Math.max(i, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH));
    }

    public void close() throws IOException {
        this.f595a.m614a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.f595a.m614a(this.buf);
    }

    private void m684a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.f595a.m615a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.f595a.m614a(this.buf);
            this.buf = a;
        }
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        m684a(i2);
        super.write(bArr, i, i2);
    }

    public synchronized void write(int i) {
        m684a(1);
        super.write(i);
    }
}
