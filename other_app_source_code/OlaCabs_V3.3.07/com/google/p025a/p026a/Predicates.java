package com.google.p025a.p026a;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.k */
public final class Predicates {
    private static final Joiner f1367a;

    /* renamed from: com.google.a.a.k.a */
    private static class Predicates<T> implements Predicate<T>, Serializable {
        private final T f1360a;

        private Predicates(T t) {
            this.f1360a = t;
        }

        public boolean m1833a(T t) {
            return this.f1360a.equals(t);
        }

        public int hashCode() {
            return this.f1360a.hashCode();
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Predicates)) {
                return false;
            }
            return this.f1360a.equals(((Predicates) obj).f1360a);
        }

        public String toString() {
            return "IsEqualTo(" + this.f1360a + ")";
        }
    }

    /* renamed from: com.google.a.a.k.b */
    private static class Predicates<T> implements Predicate<T>, Serializable {
        final Predicate<T> f1361a;

        Predicates(Predicate<T> predicate) {
            this.f1361a = (Predicate) Preconditions.m1817a((Object) predicate);
        }

        public boolean m1834a(@Nullable T t) {
            return !this.f1361a.m1735a(t);
        }

        public int hashCode() {
            return this.f1361a.hashCode() ^ -1;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Predicates)) {
                return false;
            }
            return this.f1361a.equals(((Predicates) obj).f1361a);
        }

        public String toString() {
            return "Not(" + this.f1361a.toString() + ")";
        }
    }

    /* renamed from: com.google.a.a.k.c */
    enum Predicates implements Predicate<Object> {
        ALWAYS_TRUE {
            public boolean m1836a(@Nullable Object obj) {
                return true;
            }
        },
        ALWAYS_FALSE {
            public boolean m1837a(@Nullable Object obj) {
                return false;
            }
        },
        IS_NULL {
            public boolean m1838a(@Nullable Object obj) {
                return obj == null;
            }
        },
        NOT_NULL {
            public boolean m1839a(@Nullable Object obj) {
                return obj != null;
            }
        };

        <T> Predicate<T> m1835a() {
            return this;
        }
    }

    public static <T> Predicate<T> m1840a() {
        return Predicates.IS_NULL.m1835a();
    }

    public static <T> Predicate<T> m1841a(Predicate<T> predicate) {
        return new Predicates(predicate);
    }

    public static <T> Predicate<T> m1842a(@Nullable T t) {
        return t == null ? Predicates.m1840a() : new Predicates(null);
    }

    static {
        f1367a = Joiner.m1780a(",");
    }
}
