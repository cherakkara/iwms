package com.google.android.m4b.maps.bc;

import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.bc.a */
public final class KeyedLabelSource<T> extends LabelSource {
    private final T f5407a;
    private final LabelSource f5408b;

    public KeyedLabelSource(LabelSource labelSource, T t, boolean z) {
        super(t != null ? t.toString() : null, true);
        Preconditions.m1817a((Object) t);
        this.f5408b = labelSource;
        this.f5407a = t;
    }

    public final T m8185a() {
        return this.f5407a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyedLabelSource keyedLabelSource = (KeyedLabelSource) obj;
        if (LabelSource.m8183a(this.f5408b, keyedLabelSource.f5408b) && this.f5407a.equals(keyedLabelSource.f5407a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f5408b != null ? this.f5408b.hashCode() : 0) * 31) + this.f5407a.hashCode();
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[KeyedLabelSource: ");
        if (this.f5408b != null) {
            stringBuilder.append(this.f5408b);
            stringBuilder.append(", ");
        }
        stringBuilder.append(this.f5407a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
