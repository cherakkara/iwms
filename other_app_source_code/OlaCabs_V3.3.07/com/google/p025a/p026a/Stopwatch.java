package com.google.p025a.p026a;

import com.sothree.slidinguppanel.p086a.R.R;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.a.a.m */
public final class Stopwatch {
    private final Ticker f1382a;
    private boolean f1383b;
    private long f1384c;
    private long f1385d;

    /* renamed from: com.google.a.a.m.1 */
    static /* synthetic */ class Stopwatch {
        static final /* synthetic */ int[] f1381a;

        static {
            f1381a = new int[TimeUnit.values().length];
            try {
                f1381a[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1381a[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1381a[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1381a[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public Stopwatch() {
        this(Ticker.m1870b());
    }

    public Stopwatch(Ticker ticker) {
        this.f1382a = (Ticker) Preconditions.m1818a((Object) ticker, (Object) "ticker");
    }

    public Stopwatch m1863a() {
        Preconditions.m1829b(!this.f1383b, (Object) "This stopwatch is already running; it cannot be started more than once.");
        this.f1383b = true;
        this.f1385d = this.f1382a.m1871a();
        return this;
    }

    private long m1860b() {
        return this.f1383b ? (this.f1382a.m1871a() - this.f1385d) + this.f1384c : this.f1384c;
    }

    public long m1862a(TimeUnit timeUnit) {
        return timeUnit.convert(m1860b(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        return m1864a(4);
    }

    @Deprecated
    public String m1864a(int i) {
        long b = m1860b();
        double convert = ((double) b) / ((double) TimeUnit.NANOSECONDS.convert(1, Stopwatch.m1859a(b)));
        return String.format("%." + i + "g %s", new Object[]{Double.valueOf(convert), Stopwatch.m1861b(r2)});
    }

    private static TimeUnit m1859a(long j) {
        if (TimeUnit.SECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.SECONDS;
        }
        if (TimeUnit.MILLISECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MILLISECONDS;
        }
        if (TimeUnit.MICROSECONDS.convert(j, TimeUnit.NANOSECONDS) > 0) {
            return TimeUnit.MICROSECONDS;
        }
        return TimeUnit.NANOSECONDS;
    }

    private static String m1861b(TimeUnit timeUnit) {
        switch (Stopwatch.f1381a[timeUnit.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return "ns";
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return "\u03bcs";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return "ms";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return "s";
            default:
                throw new AssertionError();
        }
    }
}
