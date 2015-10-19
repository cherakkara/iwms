package com.google.android.m4b.maps.ab;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ab.c */
public abstract class ChunkArrayManager<T> {
    private final int f3042a;
    private final LinkedList<SoftReference<T>> f3043b;
    private int f3044c;
    private int f3045d;

    protected abstract T m4834a();

    public ChunkArrayManager(int i, String str) {
        this.f3043b = new LinkedList();
        this.f3042a = i;
    }

    public final void m4835a(List<T> list) {
        synchronized (this.f3043b) {
            for (Object next : list) {
                if (this.f3043b.size() == this.f3042a) {
                    this.f3043b.removeFirst();
                }
                this.f3043b.add(new SoftReference(next));
            }
        }
    }

    public final T m4836b() {
        synchronized (this.f3043b) {
            if (!this.f3043b.isEmpty()) {
                T t = ((SoftReference) this.f3043b.removeLast()).get();
                if (t != null) {
                    this.f3045d++;
                    return t;
                }
            }
            this.f3044c++;
            return m4834a();
        }
    }
}
