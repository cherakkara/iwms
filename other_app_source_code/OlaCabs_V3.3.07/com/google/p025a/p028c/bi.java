package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;
import javax.annotation.Nullable;

/* compiled from: ReverseOrdering */
/* renamed from: com.google.a.c.bi */
final class bi<T> extends az<T> implements Serializable {
    final az<? super T> f1808a;

    bi(az<? super T> azVar) {
        this.f1808a = (az) Preconditions.m1817a((Object) azVar);
    }

    public int compare(T t, T t2) {
        return this.f1808a.compare(t2, t);
    }

    public <S extends T> az<S> m2868a() {
        return this.f1808a;
    }

    public int hashCode() {
        return -this.f1808a.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof bi)) {
            return false;
        }
        return this.f1808a.equals(((bi) obj).f1808a);
    }

    public String toString() {
        return this.f1808a + ".reverse()";
    }
}
