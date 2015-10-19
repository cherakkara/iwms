package com.google.android.m4b.maps.ch;

/* renamed from: com.google.android.m4b.maps.ch.b */
public final class QuickSorter implements Sorter {
    private static final Sorter f7274a;

    static {
        f7274a = new QuickSorter();
    }

    private QuickSorter() {
    }

    public static Sorter m10142a() {
        return f7274a;
    }

    public final void m10144a(Sortable sortable, int i, int i2) {
        int i3 = i2;
        int i4 = i;
        while (i3 - i4 >= 8) {
            int i5;
            if ((i3 - i4) + 1 > 100) {
                i5 = (i3 - i4) / 8;
                i5 = QuickSorter.m10141a(sortable, QuickSorter.m10141a(sortable, (i5 * 0) + i4, (i5 * 1) + i4, (i5 * 2) + i4), QuickSorter.m10141a(sortable, (i5 * 3) + i4, (i5 * 4) + i4, (i5 * 5) + i4), QuickSorter.m10141a(sortable, (i5 * 6) + i4, (i5 * 7) + i4, (i5 * 8) + i4));
            } else {
                i5 = QuickSorter.m10141a(sortable, i4, (i4 + i3) / 2, i3);
            }
            int i6 = i3;
            int i7 = i3;
            int i8 = i4;
            int i9 = i4;
            while (true) {
                int i10;
                if (i8 > i7 || sortable.m4984c(i5, i8)) {
                    while (i8 <= i7 && !sortable.m4984c(i7, i5)) {
                        if (sortable.m4984c(i5, i7)) {
                            i10 = i6;
                            i6 = i5;
                            i5 = i10;
                        } else {
                            sortable.m4985d(i7, i6);
                            i5 = i6 - 1;
                        }
                        i7--;
                        i10 = i5;
                        i5 = i6;
                        i6 = i10;
                    }
                    if (i8 > i7) {
                        break;
                    }
                    if (i8 == i5) {
                        i5 = i7;
                    } else if (i7 == i5) {
                        i5 = i8;
                    }
                    sortable.m4985d(i8, i7);
                    i8++;
                    i7--;
                } else {
                    if (sortable.m4984c(i8, i5)) {
                        i10 = i9;
                        i9 = i5;
                        i5 = i10;
                    } else {
                        sortable.m4985d(i9, i8);
                        i5 = i9 + 1;
                    }
                    i8++;
                    i10 = i5;
                    i5 = i9;
                    i9 = i10;
                }
            }
            i5 = Math.min(i9 - i4, i8 - i9);
            QuickSorter.m10143b(sortable, i4, i8 - i5, i5);
            i5 = Math.min(i6 - i7, i3 - i6);
            QuickSorter.m10143b(sortable, i8, (i3 + 1) - i5, i5);
            i5 = ((i8 - i9) + i4) - 1;
            i9 = (i3 + 1) - (i6 - i7);
            if (i5 - i4 < i3 - i9) {
                i10 = i4;
                i4 = i9;
                i9 = i10;
            } else {
                i10 = i3;
                i3 = i5;
                i5 = i10;
            }
            if (i9 < i5) {
                m10144a(sortable, i9, i5);
            }
        }
        InsertionSorter.m10139a().m10138a(sortable, i4, i3);
    }

    private static int m10141a(Sortable sortable, int i, int i2, int i3) {
        if (sortable.m4984c(i, i2)) {
            if (sortable.m4984c(i2, i3)) {
                return i2;
            }
            return sortable.m4984c(i, i3) ? i3 : i;
        } else if (sortable.m4984c(i3, i2)) {
            return i2;
        } else {
            return sortable.m4984c(i3, i) ? i3 : i;
        }
    }

    private static void m10143b(Sortable sortable, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            sortable.m4985d(i + i4, i2 + i4);
        }
    }
}
