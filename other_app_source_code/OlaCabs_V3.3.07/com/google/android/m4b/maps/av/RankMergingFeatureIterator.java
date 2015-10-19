package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.bc.LabelableFeature;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.google.android.m4b.maps.av.f */
public final class RankMergingFeatureIterator implements Iterator<LabelableFeature> {
    private ArrayList<Iterator<LabelableFeature>> f4539a;
    private LabelableFeature[] f4540b;
    private int f4541c;

    public final /* synthetic */ Object next() {
        return m7196a();
    }

    public RankMergingFeatureIterator() {
        this.f4539a = new ArrayList();
        this.f4541c = 0;
    }

    public final void m7197a(Iterator<LabelableFeature> it) {
        if (this.f4540b != null) {
            throw new IllegalStateException("Cannot call addIterator after next has been called");
        } else if (it.hasNext()) {
            this.f4539a.add(it);
        }
    }

    public final boolean hasNext() {
        return this.f4541c < this.f4539a.size();
    }

    public final LabelableFeature m7196a() {
        int i = -1;
        if (this.f4540b == null) {
            m7195c();
        }
        int i2 = 0;
        int i3 = -1;
        while (i2 < this.f4540b.length) {
            int i4;
            if (this.f4540b[i2] == null || this.f4540b[i2].m8187b() <= i) {
                i4 = i3;
                i3 = i;
                i = i4;
            } else {
                i3 = this.f4540b[i2].m8187b();
                i = i2;
            }
            i2++;
            i4 = i;
            i = i3;
            i3 = i4;
        }
        LabelableFeature labelableFeature = this.f4540b[i3];
        Iterator it = (Iterator) this.f4539a.get(i3);
        if (it != null) {
            this.f4540b[i3] = (LabelableFeature) it.next();
            if (!it.hasNext()) {
                this.f4539a.set(i3, null);
            }
        } else {
            this.f4540b[i3] = null;
            this.f4541c++;
        }
        return labelableFeature;
    }

    public final LabelableFeature m7198b() {
        int i = -1;
        if (this.f4540b == null) {
            m7195c();
        }
        int i2 = 0;
        int i3 = -1;
        while (i2 < this.f4540b.length) {
            if (this.f4540b[i2] != null && this.f4540b[i2].m8187b() > r2) {
                i3 = this.f4540b[i2].m8187b();
                i = i2;
            }
            i2++;
        }
        return this.f4540b[i];
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private void m7195c() {
        this.f4540b = new LabelableFeature[this.f4539a.size()];
        for (int i = 0; i < this.f4539a.size(); i++) {
            Iterator it = (Iterator) this.f4539a.get(i);
            this.f4540b[i] = (LabelableFeature) it.next();
            if (!it.hasNext()) {
                this.f4539a.set(i, null);
            }
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("[RankMergingFeatureIterator");
        for (int i = 0; i < this.f4539a.size(); i++) {
            stringBuilder.append('|').append(this.f4539a.get(i));
        }
        return stringBuilder.append(']').toString();
    }
}
