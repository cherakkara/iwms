package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzpk;

/* renamed from: com.google.android.gms.location.d */
public interface C0523d {

    /* renamed from: com.google.android.gms.location.d.a */
    public static final class C0529a {
        private String f2641a;
        private int f2642b;
        private long f2643c;
        private short f2644d;
        private double f2645e;
        private double f2646f;
        private float f2647g;
        private int f2648h;
        private int f2649i;

        public C0529a() {
            this.f2641a = null;
            this.f2642b = 0;
            this.f2643c = Long.MIN_VALUE;
            this.f2644d = (short) -1;
            this.f2648h = 0;
            this.f2649i = -1;
        }

        public C0529a m4354a(double d, double d2, float f) {
            this.f2644d = (short) 1;
            this.f2645e = d;
            this.f2646f = d2;
            this.f2647g = f;
            return this;
        }

        public C0529a m4355a(int i) {
            this.f2642b = i;
            return this;
        }

        public C0529a m4356a(long j) {
            if (j < 0) {
                this.f2643c = -1;
            } else {
                this.f2643c = SystemClock.elapsedRealtime() + j;
            }
            return this;
        }

        public C0529a m4357a(String str) {
            this.f2641a = str;
            return this;
        }

        public C0523d m4358a() {
            if (this.f2641a == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.f2642b == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.f2642b & 4) != 0 && this.f2649i < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.f2643c == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.f2644d == (short) -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.f2648h >= 0) {
                return new zzpk(this.f2641a, this.f2642b, (short) 1, this.f2645e, this.f2646f, this.f2647g, this.f2643c, this.f2648h, this.f2649i);
            } else {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
        }
    }

    String m4261f();
}
