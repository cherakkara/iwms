package com.google.p025a.p028c;

import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: ImmutableMapEntrySet */
/* renamed from: com.google.a.c.ae */
abstract class ae<K, V> extends ai<Entry<K, V>> {
    abstract ad<K, V> m2304e();

    ae() {
    }

    public int size() {
        return m2304e().size();
    }

    public boolean contains(@Nullable Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        Object obj2 = m2304e().get(entry.getKey());
        if (obj2 == null || !obj2.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    boolean m2303a() {
        return m2304e().m2316e();
    }
}
