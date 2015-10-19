package p004b.p005a.p006a.p007a.p008a.p010b;

import android.os.SystemClock;
import android.util.Log;

/* renamed from: b.a.a.a.a.b.t */
public class TimingMetric {
    private final String f196a;
    private final String f197b;
    private final boolean f198c;
    private long f199d;
    private long f200e;

    public TimingMetric(String str, String str2) {
        this.f196a = str;
        this.f197b = str2;
        this.f198c = !Log.isLoggable(str2, 2);
    }

    public synchronized void m257a() {
        if (!this.f198c) {
            this.f199d = SystemClock.elapsedRealtime();
            this.f200e = 0;
        }
    }

    public synchronized void m258b() {
        if (!this.f198c) {
            if (this.f200e == 0) {
                this.f200e = SystemClock.elapsedRealtime() - this.f199d;
                m256c();
            }
        }
    }

    private void m256c() {
        Log.v(this.f197b, this.f196a + ": " + this.f200e + "ms");
    }
}
