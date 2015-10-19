package com.leanplum;

import java.util.HashMap;
import java.util.Map;

final class al {
    Map<am, Object> f8749a;

    al() {
        this.f8749a = new HashMap();
    }

    public static al m12733a(String str) {
        al alVar = new al();
        String[] split = str.toLowerCase().split("-");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str2 = split[i];
            int i3 = i2;
            Object obj = null;
            while (obj == null && i3 < am.values().length) {
                am amVar = am.values()[i3];
                Object a = amVar.m12734a().m12614a(str2);
                if (a != null) {
                    alVar.f8749a.put(amVar, a);
                    obj = 1;
                }
                i3++;
            }
            i++;
            i2 = i3;
        }
        return alVar;
    }
}
