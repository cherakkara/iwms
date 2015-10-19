package com.google.android.m4b.maps.au;

/* renamed from: com.google.android.m4b.maps.au.a */
public final class Bitmask implements Comparable<Bitmask> {
    public static final Bitmask f4072a;
    private final int f4073b;

    public final /* synthetic */ int compareTo(Object obj) {
        return m6610a((Bitmask) obj);
    }

    static {
        f4072a = new Bitmask(-1);
        Bitmask.m6608a(new int[0]);
    }

    public static Bitmask m6608a(int... iArr) {
        return new Bitmask(Bitmask.m6609b(iArr));
    }

    private static int m6609b(int... iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 |= 1 << iArr[i];
            i++;
        }
        return i2;
    }

    private Bitmask(int i) {
        this.f4073b = i;
    }

    public static Bitmask m6607a(Bitmask bitmask, Bitmask bitmask2) {
        return new Bitmask(bitmask.f4073b & (bitmask2.f4073b ^ -1));
    }

    public final boolean m6611a(int i) {
        return (this.f4073b & (1 << i)) != 0;
    }

    public final String toString() {
        Object obj = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i <= 31; i++) {
            if (m6611a(i)) {
                if (obj == null) {
                    stringBuilder.append(", ");
                } else {
                    obj = null;
                }
                stringBuilder.append(i);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Bitmask) && ((Bitmask) obj).f4073b == this.f4073b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f4073b;
    }

    public final int m6610a(Bitmask bitmask) {
        return this.f4073b - bitmask.f4073b;
    }
}
