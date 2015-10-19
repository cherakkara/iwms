package com.google.p025a.p026a;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.p */
public final class Suppliers {

    /* renamed from: com.google.a.a.p.a */
    private static class Suppliers<T> implements Supplier<T>, Serializable {
        final T f1386a;

        Suppliers(@Nullable T t) {
            this.f1386a = t;
        }

        public T m1868a() {
            return this.f1386a;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Suppliers)) {
                return false;
            }
            return Objects.m1811a(this.f1386a, ((Suppliers) obj).f1386a);
        }

        public int hashCode() {
            return Objects.m1808a(this.f1386a);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.f1386a + ")";
        }
    }

    public static <T> Supplier<T> m1869a(@Nullable T t) {
        return new Suppliers(t);
    }
}
