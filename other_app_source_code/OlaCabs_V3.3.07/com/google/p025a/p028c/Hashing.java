package com.google.p025a.p028c;

/* renamed from: com.google.a.c.v */
final class Hashing {
    static int f1869a;

    static int m2971a(int i) {
        return 461845907 * Integer.rotateLeft(-862048943 * i, 15);
    }

    static {
        f1869a = 1073741824;
    }

    static int m2972a(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (((double) max) / ((double) highestOneBit) <= d) {
            return highestOneBit;
        }
        highestOneBit <<= 1;
        if (highestOneBit > 0) {
            return highestOneBit;
        }
        return f1869a;
    }
}
