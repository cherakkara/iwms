package com.google.android.m4b.maps.p041b;

import android.support.v4.view.MotionEventCompat;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.InputStream;

/* renamed from: com.google.android.m4b.maps.b.d */
public final class BoundInputStream extends InputStream {
    private int f5015a;
    private byte[] f5016b;
    private int f5017c;
    private int f5018d;
    private InputStream f5019e;

    public BoundInputStream(InputStream inputStream, int i) {
        this.f5019e = inputStream;
        this.f5015a = i;
        this.f5016b = new byte[Math.min(i, AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH)];
    }

    private boolean m7761a() {
        if (this.f5015a <= 0) {
            return false;
        }
        if (this.f5017c >= this.f5018d) {
            this.f5018d = this.f5019e.read(this.f5016b, 0, Math.min(this.f5015a, this.f5016b.length));
            if (this.f5018d <= 0) {
                this.f5015a = 0;
                return false;
            }
            this.f5017c = 0;
        }
        return true;
    }

    public final int available() {
        return this.f5018d - this.f5017c;
    }

    public final int read() {
        if (!m7761a()) {
            return -1;
        }
        this.f5015a--;
        byte[] bArr = this.f5016b;
        int i = this.f5017c;
        this.f5017c = i + 1;
        return bArr[i] & MotionEventCompat.ACTION_MASK;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (!m7761a()) {
            return -1;
        }
        int min = Math.min(i2, this.f5018d - this.f5017c);
        System.arraycopy(this.f5016b, this.f5017c, bArr, i, min);
        this.f5017c += min;
        this.f5015a -= min;
        return min;
    }
}
