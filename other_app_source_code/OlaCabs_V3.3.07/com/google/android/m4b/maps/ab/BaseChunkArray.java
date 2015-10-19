package com.google.android.m4b.maps.ab;

import java.util.LinkedList;

/* renamed from: com.google.android.m4b.maps.ab.a */
public abstract class BaseChunkArray<T> {
    protected final LinkedList<T> f3033a;
    protected int f3034b;
    public T f3035c;
    public int f3036d;
    private final ChunkArrayManager<T> f3037e;
    private final int f3038f;
    private final int f3039g;
    private final int f3040h;
    private int f3041i;

    public BaseChunkArray(int i, int i2, ChunkArrayManager<T> chunkArrayManager) {
        this.f3033a = new LinkedList();
        this.f3040h = i2;
        this.f3038f = 1 << i2;
        this.f3039g = this.f3038f - 1;
        this.f3037e = chunkArrayManager;
        this.f3041i = m4826d(i);
        m4828a();
    }

    public final void m4828a() {
        this.f3034b = 0;
        this.f3036d = 0;
        this.f3035c = m4827a(0);
    }

    private int m4826d(int i) {
        return ((this.f3039g & i) != 0 ? 1 : 0) + (i >> this.f3040h);
    }

    public final T m4827a(int i) {
        if (i > this.f3041i) {
            throw new IndexOutOfBoundsException("Index out of bound : " + i + "(index) > " + this.f3041i + "(size)");
        }
        while (i >= this.f3033a.size()) {
            this.f3033a.add(this.f3037e.m4836b());
        }
        return this.f3033a.get(i);
    }

    public final void m4831b(int i) {
        if (i >= this.f3038f) {
            int i2 = this.f3039g & i;
            this.f3034b++;
            if (this.f3034b != this.f3041i) {
                Object obj = this.f3035c;
                this.f3035c = m4827a(this.f3034b);
                if (i2 != 0) {
                    System.arraycopy(obj, this.f3038f, this.f3035c, 0, i2);
                }
            }
            this.f3036d = i2;
            return;
        }
        this.f3036d = i;
    }

    public final void m4829a(T t, int i) {
        m4828a();
        int i2 = 0;
        while (i2 < i) {
            int i3 = this.f3038f;
            if (this.f3038f + i2 > i) {
                i3 = i - i2;
            }
            System.arraycopy(t, i2, m4827a(this.f3034b), 0, i3);
            i2 += i3;
            if (i3 == this.f3038f) {
                this.f3034b++;
                this.f3036d = 0;
            } else {
                this.f3036d = i3;
            }
        }
    }

    public final void m4833c(int i) {
        this.f3041i = Math.max(m4826d(i), this.f3041i);
    }

    public final int m4830b() {
        return this.f3033a.size() << this.f3040h;
    }

    public final void m4832c() {
        this.f3037e.m4835a(this.f3033a);
        this.f3033a.clear();
    }
}
