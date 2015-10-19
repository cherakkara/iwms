package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest.C0153b;
import com.facebook.GraphRequest.C0162e;

/* renamed from: com.facebook.y */
class RequestProgress {
    private final GraphRequest f1306a;
    private final Handler f1307b;
    private final long f1308c;
    private long f1309d;
    private long f1310e;
    private long f1311f;

    /* renamed from: com.facebook.y.1 */
    class RequestProgress implements Runnable {
        final /* synthetic */ C0162e f1302a;
        final /* synthetic */ long f1303b;
        final /* synthetic */ long f1304c;
        final /* synthetic */ RequestProgress f1305d;

        RequestProgress(RequestProgress requestProgress, C0162e c0162e, long j, long j2) {
            this.f1305d = requestProgress;
            this.f1302a = c0162e;
            this.f1303b = j;
            this.f1304c = j2;
        }

        public void run() {
            this.f1302a.m721a(this.f1303b, this.f1304c);
        }
    }

    RequestProgress(Handler handler, GraphRequest graphRequest) {
        this.f1306a = graphRequest;
        this.f1307b = handler;
        this.f1308c = FacebookSdk.m1209g();
    }

    void m1726a(long j) {
        this.f1309d += j;
        if (this.f1309d >= this.f1310e + this.f1308c || this.f1309d >= this.f1311f) {
            m1725a();
        }
    }

    void m1727b(long j) {
        this.f1311f += j;
    }

    void m1725a() {
        if (this.f1309d > this.f1310e) {
            C0153b e = this.f1306a.m781e();
            if (this.f1311f > 0 && (e instanceof C0162e)) {
                long j = this.f1309d;
                long j2 = this.f1311f;
                C0162e c0162e = (C0162e) e;
                if (this.f1307b == null) {
                    c0162e.m721a(j, j2);
                } else {
                    this.f1307b.post(new RequestProgress(this, c0162e, j, j2));
                }
                this.f1310e = this.f1309d;
            }
        }
    }
}
