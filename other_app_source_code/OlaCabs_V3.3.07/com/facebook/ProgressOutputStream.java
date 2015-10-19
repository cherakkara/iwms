package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequestBatch.GraphRequestBatch;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/* renamed from: com.facebook.v */
class ProgressOutputStream extends FilterOutputStream implements RequestOutputStream {
    private final Map<GraphRequest, RequestProgress> f1295a;
    private final GraphRequestBatch f1296b;
    private final long f1297c;
    private long f1298d;
    private long f1299e;
    private long f1300f;
    private RequestProgress f1301g;

    /* renamed from: com.facebook.v.1 */
    class ProgressOutputStream implements Runnable {
        final /* synthetic */ GraphRequestBatch f1293a;
        final /* synthetic */ ProgressOutputStream f1294b;

        ProgressOutputStream(ProgressOutputStream progressOutputStream, GraphRequestBatch graphRequestBatch) {
            this.f1294b = progressOutputStream;
            this.f1293a = graphRequestBatch;
        }

        public void run() {
            this.f1293a.m1328a(this.f1294b.f1296b, this.f1294b.f1298d, this.f1294b.f1300f);
        }
    }

    ProgressOutputStream(OutputStream outputStream, GraphRequestBatch graphRequestBatch, Map<GraphRequest, RequestProgress> map, long j) {
        super(outputStream);
        this.f1296b = graphRequestBatch;
        this.f1295a = map;
        this.f1300f = j;
        this.f1297c = FacebookSdk.m1209g();
    }

    private void m1721a(long j) {
        if (this.f1301g != null) {
            this.f1301g.m1726a(j);
        }
        this.f1298d += j;
        if (this.f1298d >= this.f1299e + this.f1297c || this.f1298d >= this.f1300f) {
            m1720a();
        }
    }

    private void m1720a() {
        if (this.f1298d > this.f1299e) {
            for (GraphRequestBatch graphRequestBatch : this.f1296b.m1340e()) {
                if (graphRequestBatch instanceof GraphRequestBatch) {
                    Handler c = this.f1296b.m1338c();
                    GraphRequestBatch graphRequestBatch2 = (GraphRequestBatch) graphRequestBatch;
                    if (c == null) {
                        graphRequestBatch2.m1328a(this.f1296b, this.f1298d, this.f1300f);
                    } else {
                        c.post(new ProgressOutputStream(this, graphRequestBatch2));
                    }
                }
            }
            this.f1299e = this.f1298d;
        }
    }

    public void m1724a(GraphRequest graphRequest) {
        this.f1301g = graphRequest != null ? (RequestProgress) this.f1295a.get(graphRequest) : null;
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        m1721a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        m1721a((long) i2);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        m1721a(1);
    }

    public void close() throws IOException {
        super.close();
        for (RequestProgress a : this.f1295a.values()) {
            a.m1725a();
        }
        m1720a();
    }
}
