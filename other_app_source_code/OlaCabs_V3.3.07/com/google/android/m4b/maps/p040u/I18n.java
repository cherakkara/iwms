package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.p054p.Assert;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.android.m4b.maps.u.l */
public final class I18n {
    private String f7940a;
    private String f7941b;

    public static String m11468a(String str) {
        String str2 = "en";
        if (str == null) {
            return str2;
        }
        String[] split = str.replace('-', '_').split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        if (split[0].length() != 2 && split[0].length() != 3) {
            return str2;
        }
        str2 = split[0].toLowerCase();
        if (split.length < 2 || split[1].length() != 2) {
            return str2;
        }
        return str2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + split[1].toUpperCase();
    }

    I18n(String str) {
        String a = I18n.m11468a(str);
        String a2 = I18n.m11468a(System.getProperty("microedition.locale"));
        if ("en".equals(a2) || (a2.length() == 2 && a.startsWith(a2))) {
            a2 = a;
        }
        m11474b(a2);
        m11475c(null);
    }

    public final String m11473a() {
        return this.f7941b;
    }

    public final void m11474b(String str) {
        this.f7940a = I18n.m11468a(str);
    }

    public final void m11475c(String str) {
        this.f7941b = str != null ? I18n.m11468a(str) : this.f7940a;
        I18n.m11470d(this.f7941b);
    }

    public static String m11470d(String str) {
        int f = I18n.m11472f(str);
        return f < 0 ? str : str.substring(0, f);
    }

    public static String m11471e(String str) {
        int f = I18n.m11472f(str);
        if (f < 0) {
            return null;
        }
        String substring = str.substring(f + 1);
        int f2 = I18n.m11472f(substring);
        if (f2 >= 0) {
            substring = substring.substring(0, f2);
        }
        return substring.length() <= 0 ? null : substring;
    }

    private static int m11472f(String str) {
        int indexOf = str.indexOf(95);
        int indexOf2 = str.indexOf(45);
        if (indexOf < 0) {
            return indexOf2;
        }
        if (indexOf2 < 0) {
            return indexOf;
        }
        return indexOf < indexOf2 ? indexOf : indexOf2;
    }

    public static String m11469a(String str, String[] strArr) {
        Assert.m11064a(strArr.length > 0);
        if (str == null) {
            str = "en";
        } else if (str.equals("en_AU") || str.equals("en_NZ")) {
            str = "en_GB";
        } else if (str.startsWith("nb")) {
            str = "no";
        }
        String d = I18n.m11470d(str);
        Object obj = -1;
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            Object obj2;
            int i3;
            String str2 = strArr[i];
            if (i == 0) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (str.equals(str2)) {
                obj2 = 4;
            } else if (str.startsWith(str2)) {
                obj2 = 3;
            } else if (str2.startsWith(d)) {
                obj2 = 2;
            } else if (obj2 != null) {
                int i4 = 1;
            } else {
                obj2 = null;
            }
            if (obj2 > obj) {
                i3 = i;
            } else {
                obj2 = obj;
                i3 = i2;
            }
            i++;
            i2 = i3;
            obj = obj2;
        }
        return strArr[i2];
    }
}
