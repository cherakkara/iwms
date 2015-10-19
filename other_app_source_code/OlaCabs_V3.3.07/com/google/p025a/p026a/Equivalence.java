package com.google.p025a.p026a;

import java.io.Serializable;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.d */
public abstract class Equivalence<T> {

    /* renamed from: com.google.a.a.d.a */
    static final class Equivalence extends Equivalence<Object> implements Serializable {
        static final Equivalence f1342a;

        Equivalence() {
        }

        static {
            f1342a = new Equivalence();
        }

        protected boolean m1775b(Object obj, Object obj2) {
            return obj.equals(obj2);
        }

        public int m1774b(Object obj) {
            return obj.hashCode();
        }
    }

    /* renamed from: com.google.a.a.d.b */
    static final class Equivalence extends Equivalence<Object> implements Serializable {
        static final Equivalence f1343a;

        Equivalence() {
        }

        static {
            f1343a = new Equivalence();
        }

        protected boolean m1777b(Object obj, Object obj2) {
            return false;
        }

        protected int m1776b(Object obj) {
            return System.identityHashCode(obj);
        }
    }

    protected abstract int m1772b(T t);

    protected abstract boolean m1773b(T t, T t2);

    protected Equivalence() {
    }

    public final boolean m1771a(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return true;
        }
        if (t == null || t2 == null) {
            return false;
        }
        return m1773b(t, t2);
    }

    public final int m1770a(@Nullable T t) {
        if (t == null) {
            return 0;
        }
        return m1772b(t);
    }

    public static Equivalence<Object> m1768a() {
        return Equivalence.f1342a;
    }

    public static Equivalence<Object> m1769b() {
        return Equivalence.f1343a;
    }
}
