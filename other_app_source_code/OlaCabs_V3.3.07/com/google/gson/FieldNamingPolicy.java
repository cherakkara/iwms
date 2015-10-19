package com.google.gson;

import java.lang.reflect.Field;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.gson.d */
public enum FieldNamingPolicy implements FieldNamingStrategy {
    IDENTITY {
        public String m12308a(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE {
        public String m12309a(Field field) {
            return FieldNamingPolicy.m12306b(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES {
        public String m12310a(Field field) {
            return FieldNamingPolicy.m12306b(FieldNamingPolicy.m12307b(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES {
        public String m12311a(Field field) {
            return FieldNamingPolicy.m12307b(field.getName(), EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).toLowerCase();
        }
    },
    LOWER_CASE_WITH_DASHES {
        public String m12312a(Field field) {
            return FieldNamingPolicy.m12307b(field.getName(), "-").toLowerCase();
        }
    };

    private static String m12307b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && stringBuilder.length() != 0) {
                stringBuilder.append(str2);
            }
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }

    private static String m12306b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        while (i < str.length() - 1 && !Character.isLetter(charAt)) {
            stringBuilder.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (i == str.length()) {
            return stringBuilder.toString();
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return stringBuilder.append(FieldNamingPolicy.m12303a(Character.toUpperCase(charAt), str, i + 1)).toString();
    }

    private static String m12303a(char c, String str, int i) {
        return i < str.length() ? c + str.substring(i) : String.valueOf(c);
    }
}
