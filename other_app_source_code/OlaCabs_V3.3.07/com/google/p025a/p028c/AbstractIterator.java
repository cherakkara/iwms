package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.NoSuchElementException;

/* renamed from: com.google.a.c.b */
public abstract class AbstractIterator<T> extends bs<T> {
    private AbstractIterator f1643a;
    private T f1644b;

    /* renamed from: com.google.a.c.b.1 */
    static /* synthetic */ class AbstractIterator {
        static final /* synthetic */ int[] f1789a;

        static {
            f1789a = new int[AbstractIterator.values().length];
            try {
                f1789a[AbstractIterator.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1789a[AbstractIterator.READY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.google.a.c.b.a */
    private enum AbstractIterator {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    protected abstract T m2491a();

    protected AbstractIterator() {
        this.f1643a = AbstractIterator.NOT_READY;
    }

    protected final T m2492b() {
        this.f1643a = AbstractIterator.DONE;
        return null;
    }

    public final boolean hasNext() {
        Preconditions.m1828b(this.f1643a != AbstractIterator.FAILED);
        switch (AbstractIterator.f1789a[this.f1643a.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return false;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return true;
            default:
                return m2490c();
        }
    }

    private boolean m2490c() {
        this.f1643a = AbstractIterator.FAILED;
        this.f1644b = m2491a();
        if (this.f1643a == AbstractIterator.DONE) {
            return false;
        }
        this.f1643a = AbstractIterator.READY;
        return true;
    }

    public final T next() {
        if (hasNext()) {
            this.f1643a = AbstractIterator.NOT_READY;
            return this.f1644b;
        }
        throw new NoSuchElementException();
    }
}
