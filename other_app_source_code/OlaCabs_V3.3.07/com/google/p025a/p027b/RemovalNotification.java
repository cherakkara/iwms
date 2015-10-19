package com.google.p025a.p027b;

import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import java.util.Map.Entry;
import javax.annotation.Nullable;

/* renamed from: com.google.a.b.l */
public final class RemovalNotification<K, V> implements Entry<K, V> {
    @Nullable
    private final K f1572a;
    @Nullable
    private final V f1573b;
    private final RemovalCause f1574c;

    RemovalNotification(@Nullable K k, @Nullable V v, RemovalCause removalCause) {
        this.f1572a = k;
        this.f1573b = v;
        this.f1574c = (RemovalCause) Preconditions.m1817a((Object) removalCause);
    }

    @Nullable
    public K getKey() {
        return this.f1572a;
    }

    @Nullable
    public V getValue() {
        return this.f1573b;
    }

    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (Objects.m1811a(getKey(), entry.getKey()) && Objects.m1811a(getValue(), entry.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        Object key = getKey();
        Object value = getValue();
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
