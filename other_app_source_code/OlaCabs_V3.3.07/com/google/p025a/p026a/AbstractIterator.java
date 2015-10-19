package com.google.p025a.p026a;

import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.google.a.a.a */
abstract class AbstractIterator<T> implements Iterator<T> {
    private AbstractIterator f1318a;
    private T f1319b;

    /* renamed from: com.google.a.a.a.1 */
    static /* synthetic */ class AbstractIterator {
        static final /* synthetic */ int[] f1312a;

        static {
            f1312a = new int[AbstractIterator.values().length];
            try {
                f1312a[AbstractIterator.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1312a[AbstractIterator.READY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.google.a.a.a.a */
    private enum AbstractIterator {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    protected abstract T m1729a();

    protected AbstractIterator() {
        this.f1318a = AbstractIterator.NOT_READY;
    }

    protected final T m1730b() {
        this.f1318a = AbstractIterator.DONE;
        return null;
    }

    public final boolean hasNext() {
        Preconditions.m1828b(this.f1318a != AbstractIterator.FAILED);
        switch (AbstractIterator.f1312a[this.f1318a.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return false;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return true;
            default:
                return m1728c();
        }
    }

    private boolean m1728c() {
        this.f1318a = AbstractIterator.FAILED;
        this.f1319b = m1729a();
        if (this.f1318a == AbstractIterator.DONE) {
            return false;
        }
        this.f1318a = AbstractIterator.READY;
        return true;
    }

    public final T next() {
        if (hasNext()) {
            this.f1318a = AbstractIterator.NOT_READY;
            return this.f1319b;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
