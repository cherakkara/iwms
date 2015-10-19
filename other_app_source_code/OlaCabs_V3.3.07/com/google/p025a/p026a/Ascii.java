package com.google.p025a.p026a;

/* renamed from: com.google.a.a.b */
public final class Ascii {
    public static String m1733a(String str) {
        return Ascii.m1732a((CharSequence) str);
    }

    public static String m1732a(CharSequence charSequence) {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(Ascii.m1731a(charSequence.charAt(i)));
        }
        return stringBuilder.toString();
    }

    public static char m1731a(char c) {
        return Ascii.m1734b(c) ? (char) (c ^ 32) : c;
    }

    public static boolean m1734b(char c) {
        return c >= 'A' && c <= 'Z';
    }
}
