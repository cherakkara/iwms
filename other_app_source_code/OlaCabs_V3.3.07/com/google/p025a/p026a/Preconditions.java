package com.google.p025a.p026a;

import javax.annotation.Nullable;

/* renamed from: com.google.a.a.i */
public final class Preconditions {
    public static void m1822a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void m1823a(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void m1824a(boolean z, @Nullable String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(Preconditions.m1820a(str, objArr));
        }
    }

    public static void m1828b(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void m1829b(boolean z, @Nullable Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void m1830b(boolean z, @Nullable String str, @Nullable Object... objArr) {
        if (!z) {
            throw new IllegalStateException(Preconditions.m1820a(str, objArr));
        }
    }

    public static <T> T m1817a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T m1818a(T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> T m1819a(T t, @Nullable String str, @Nullable Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Preconditions.m1820a(str, objArr));
    }

    public static int m1815a(int i, int i2) {
        return Preconditions.m1816a(i, i2, "index");
    }

    public static int m1816a(int i, int i2, @Nullable String str) {
        if (i >= 0 && i < i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(Preconditions.m1831c(i, i2, str));
    }

    private static String m1831c(int i, int i2, String str) {
        if (i < 0) {
            return Preconditions.m1820a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        } else {
            return Preconditions.m1820a("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public static int m1825b(int i, int i2) {
        return Preconditions.m1826b(i, i2, "index");
    }

    public static int m1826b(int i, int i2, @Nullable String str) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(Preconditions.m1832d(i, i2, str));
    }

    private static String m1832d(int i, int i2, String str) {
        if (i < 0) {
            return Preconditions.m1820a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new IllegalArgumentException("negative size: " + i2);
        } else {
            return Preconditions.m1820a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public static void m1821a(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(Preconditions.m1827b(i, i2, i3));
        }
    }

    private static String m1827b(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return Preconditions.m1832d(i, i3, "start index");
        }
        if (i2 < 0 || i2 > i3) {
            return Preconditions.m1832d(i2, i3, "end index");
        }
        return Preconditions.m1820a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    static String m1820a(String str, @Nullable Object... objArr) {
        int i = 0;
        String valueOf = String.valueOf(str);
        StringBuilder stringBuilder = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i2 = 0;
        while (i < objArr.length) {
            int indexOf = valueOf.indexOf("%s", i2);
            if (indexOf == -1) {
                break;
            }
            stringBuilder.append(valueOf.substring(i2, indexOf));
            i2 = i + 1;
            stringBuilder.append(objArr[i]);
            int i3 = i2;
            i2 = indexOf + 2;
            i = i3;
        }
        stringBuilder.append(valueOf.substring(i2));
        if (i < objArr.length) {
            stringBuilder.append(" [");
            i2 = i + 1;
            stringBuilder.append(objArr[i]);
            i = i2;
            while (i < objArr.length) {
                stringBuilder.append(", ");
                i2 = i + 1;
                stringBuilder.append(objArr[i]);
                i = i2;
            }
            stringBuilder.append(']');
        }
        return stringBuilder.toString();
    }
}
