package com.google.android.m4b.maps.ch;

/* renamed from: com.google.android.m4b.maps.ch.a */
public final class InsertionSorter implements Sorter {
    private static final Sorter f7273a;

    static {
        f7273a = new InsertionSorter();
    }

    private InsertionSorter() {
    }

    public static Sorter m10139a() {
        return f7273a;
    }

    public final void m10140a(Sortable sortable, int i, int i2) {
        for (int i3 = i; i3 <= i2; i3++) {
            int i4 = i3;
            while (i4 > i && sortable.m4984c(i4, i4 - 1)) {
                sortable.m4985d(i4, i4 - 1);
                i4--;
            }
        }
    }
}
