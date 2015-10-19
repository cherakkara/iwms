package com.google.p025a.p028c;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: com.google.a.c.h */
final class ByFunctionOrdering<F, T> extends az<F> implements Serializable {
    final Function<F, ? extends T> f1856a;
    final az<T> f1857b;

    ByFunctionOrdering(Function<F, ? extends T> function, az<T> azVar) {
        this.f1856a = (Function) Preconditions.m1817a((Object) function);
        this.f1857b = (az) Preconditions.m1817a((Object) azVar);
    }

    public int compare(F f, F f2) {
        return this.f1857b.compare(this.f1856a.m1778a(f), this.f1856a.m1778a(f2));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByFunctionOrdering)) {
            return false;
        }
        ByFunctionOrdering byFunctionOrdering = (ByFunctionOrdering) obj;
        if (this.f1856a.equals(byFunctionOrdering.f1856a) && this.f1857b.equals(byFunctionOrdering.f1857b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.m1808a(this.f1856a, this.f1857b);
    }

    public String toString() {
        return this.f1857b + ".onResultOf(" + this.f1856a + ")";
    }
}
