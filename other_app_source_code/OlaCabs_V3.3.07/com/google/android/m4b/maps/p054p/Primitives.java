package com.google.android.m4b.maps.p054p;

/* renamed from: com.google.android.m4b.maps.p.f */
public final class Primitives {
    private static Primitives f7692a;

    /* renamed from: com.google.android.m4b.maps.p.f.b */
    public static abstract class Primitives {
        public abstract Integer m11080a(int i);

        public abstract Long m11081a(long j);
    }

    /* renamed from: com.google.android.m4b.maps.p.f.a */
    static class Primitives extends Primitives {
        private Long[] f7690a;
        private Integer[] f7691b;

        private Primitives() {
            this.f7690a = new Long[]{new Long(0), new Long(1), new Long(2), new Long(3), new Long(4), new Long(5), new Long(6), new Long(7), new Long(8), new Long(9), new Long(10), new Long(11), new Long(12), new Long(13), new Long(14), new Long(15)};
            this.f7691b = new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15)};
        }

        public final Long m11083a(long j) {
            return (j < 0 || j >= ((long) this.f7690a.length)) ? new Long(j) : this.f7690a[(int) j];
        }

        public final Integer m11082a(int i) {
            return (i < 0 || i >= this.f7691b.length) ? Integer.valueOf(i) : this.f7691b[i];
        }
    }

    static {
        f7692a = new Primitives();
    }

    public static Long m11085a(long j) {
        return f7692a.m11081a(j);
    }

    public static Integer m11084a(int i) {
        return f7692a.m11080a(i);
    }
}
