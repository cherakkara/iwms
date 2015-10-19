package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.u */
class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream {
    private final Map<GraphRequest, RequestProgress> f1288a;
    private final Handler f1289b;
    private GraphRequest f1290c;
    private RequestProgress f1291d;
    private int f1292e;

    ProgressNoopOutputStream(Handler handler) {
        this.f1288a = new HashMap();
        this.f1289b = handler;
    }

    public void m1717a(GraphRequest graphRequest) {
        this.f1290c = graphRequest;
        this.f1291d = graphRequest != null ? (RequestProgress) this.f1288a.get(graphRequest) : null;
    }

    int m1715a() {
        return this.f1292e;
    }

    Map<GraphRequest, RequestProgress> m1718b() {
        return this.f1288a;
    }

    void m1716a(long j) {
        if (this.f1291d == null) {
            this.f1291d = new RequestProgress(this.f1289b, this.f1290c);
            this.f1288a.put(this.f1290c, this.f1291d);
        }
        this.f1291d.m1727b(j);
        this.f1292e = (int) (((long) this.f1292e) + j);
    }

    public void write(byte[] bArr) {
        m1716a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        m1716a((long) i2);
    }

    public void write(int i) {
        m1716a(1);
    }
}
