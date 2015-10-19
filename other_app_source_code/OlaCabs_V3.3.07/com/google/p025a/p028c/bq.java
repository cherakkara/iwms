package com.google.p025a.p028c;

import com.google.p025a.p026a.Preconditions;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.Nullable;

/* compiled from: SortedLists */
/* renamed from: com.google.a.c.bq */
final class bq {

    /* renamed from: com.google.a.c.bq.a */
    public enum SortedLists {
        NEXT_LOWER {
            int m2897a(int i) {
                return i - 1;
            }
        },
        NEXT_HIGHER {
            public int m2898a(int i) {
                return i;
            }
        },
        INVERTED_INSERTION_INDEX {
            public int m2899a(int i) {
                return i ^ -1;
            }
        };

        abstract int m2896a(int i);
    }

    /* renamed from: com.google.a.c.bq.b */
    public enum SortedLists {
        ANY_PRESENT {
            <E> int m2901a(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return i;
            }
        },
        LAST_PRESENT {
            <E> int m2902a(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                int size = list.size() - 1;
                int i2 = i;
                while (i2 < size) {
                    int i3 = ((i2 + size) + 1) >>> 1;
                    if (comparator.compare(list.get(i3), e) > 0) {
                        size = i3 - 1;
                    } else {
                        i2 = i3;
                    }
                }
                return i2;
            }
        },
        FIRST_PRESENT {
            <E> int m2903a(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                int i2 = 0;
                int i3 = i;
                while (i2 < i3) {
                    int i4 = (i2 + i3) >>> 1;
                    if (comparator.compare(list.get(i4), e) < 0) {
                        i4++;
                    } else {
                        i3 = i4;
                        i4 = i2;
                    }
                    i2 = i4;
                }
                return i2;
            }
        },
        FIRST_AFTER {
            public <E> int m2904a(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return b.m2900a(comparator, e, list, i) + 1;
            }
        },
        LAST_BEFORE {
            public <E> int m2905a(Comparator<? super E> comparator, E e, List<? extends E> list, int i) {
                return c.m2900a(comparator, e, list, i) - 1;
            }
        };

        abstract <E> int m2900a(Comparator<? super E> comparator, E e, List<? extends E> list, int i);
    }

    public static <E> int m2906a(List<? extends E> list, @Nullable E e, Comparator<? super E> comparator, SortedLists sortedLists, SortedLists sortedLists2) {
        Preconditions.m1817a((Object) comparator);
        Preconditions.m1817a((Object) list);
        Preconditions.m1817a((Object) sortedLists);
        Preconditions.m1817a((Object) sortedLists2);
        if (!(list instanceof RandomAccess)) {
            list = ar.m2516a((Iterable) list);
        }
        int i = 0;
        int size = list.size() - 1;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            int compare = comparator.compare(e, list.get(i2));
            if (compare < 0) {
                size = i2 - 1;
            } else if (compare <= 0) {
                return sortedLists.m2900a(comparator, e, list.subList(i, size + 1), i2 - i) + i;
            } else {
                i = i2 + 1;
            }
        }
        return sortedLists2.m2896a(i);
    }
}
