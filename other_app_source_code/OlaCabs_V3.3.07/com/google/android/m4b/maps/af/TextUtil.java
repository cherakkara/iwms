package com.google.android.m4b.maps.af;

/* renamed from: com.google.android.m4b.maps.af.a */
public final class TextUtil {
    public static String[] m4871a(String str, char c) {
        int i = 0;
        String valueOf = String.valueOf(c);
        int length = str.length();
        int indexOf = str.indexOf(valueOf, 0);
        int i2 = 0;
        while (indexOf != -1 && indexOf < length) {
            i2++;
            if (indexOf >= 0) {
                indexOf += valueOf.length();
            }
            indexOf = str.indexOf(valueOf, indexOf);
        }
        String[] strArr = new String[(i2 + 1)];
        for (indexOf = 0; indexOf < i2; indexOf++) {
            int indexOf2 = str.indexOf(valueOf, i);
            strArr[indexOf] = str.substring(i, indexOf2);
            i = valueOf.length() + indexOf2;
        }
        strArr[i2] = str.substring(i);
        return strArr;
    }

    public static String m4870a(String str, int i, char c) {
        if (str.length() >= 6) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        while (stringBuilder.length() < 6) {
            stringBuilder.append('0');
        }
        return stringBuilder.toString();
    }

    public static String m4869a(int i) {
        int i2 = i / 1000000;
        int abs = Math.abs(i - (i2 * 1000000));
        StringBuilder stringBuilder = new StringBuilder();
        if (i < 0 && i2 == 0) {
            stringBuilder.append("-");
        }
        stringBuilder.append(i2);
        if (abs > 0) {
            stringBuilder.append(".");
            stringBuilder.append(String.valueOf(abs + 1000000).substring(1));
        }
        return stringBuilder.toString();
    }
}
