package com.google.android.m4b.maps.an;

import java.io.DataInput;

/* compiled from: Alignment */
public class ab {
    public static final ab f3378b;
    protected final int f3379a;

    /* renamed from: com.google.android.m4b.maps.an.ab.a */
    public static class Alignment extends ab {
        public static final Alignment f3380c;

        static {
            f3380c = new Alignment(5, 0);
        }

        private Alignment(int i) {
            super(i);
        }

        private Alignment(int i, int i2) {
            super(80);
        }

        public static Alignment m5421b(DataInput dataInput) {
            return new Alignment(dataInput.readUnsignedByte());
        }

        public final int m5422d() {
            return (this.a >> 4) & 15;
        }
    }

    static {
        f3378b = new ab(5);
    }

    public ab(int i) {
        this.f3379a = i;
    }

    public static ab m5417a(DataInput dataInput) {
        return new ab(dataInput.readUnsignedByte());
    }

    public final int m5419a() {
        return this.f3379a & 3;
    }

    public final int m5420b() {
        return (this.f3379a >> 2) & 3;
    }

    public int hashCode() {
        return this.f3379a + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.f3379a != ((ab) obj).f3379a) {
            return false;
        }
        return true;
    }

    public static int m5418c() {
        return 16;
    }
}
