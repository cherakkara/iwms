package com.leanplum;

import android.content.res.Configuration;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Map;

final class as extends aH {
    as() {
    }

    public final Object m12747a(String str) {
        if (str.equals("ldpi")) {
            return Integer.valueOf(120);
        }
        if (str.equals("mdpi")) {
            return Integer.valueOf(160);
        }
        if (str.equals("hdpi")) {
            return Integer.valueOf(240);
        }
        if (str.equals("xhdpi")) {
            return Integer.valueOf(320);
        }
        if (str.equals("nodpi")) {
            return Integer.valueOf(0);
        }
        if (str.equals("tvdpi")) {
            return Integer.valueOf(213);
        }
        if (str.equals("xxhigh")) {
            return Integer.valueOf(480);
        }
        return null;
    }

    public final boolean m12749a(Object obj, Configuration configuration) {
        return true;
    }

    final Map<String, Object> m12748a(Map<String, Object> map, DisplayMetrics displayMetrics) {
        Map<String, Object> hashMap = new HashMap();
        int i = Integer.MAX_VALUE;
        for (String str : map.keySet()) {
            Integer num = (Integer) map.get(str);
            if (num.intValue() < i && num.intValue() >= displayMetrics.densityDpi) {
                i = num.intValue();
                hashMap.clear();
            }
            if (num.intValue() == i) {
                hashMap.put(str, num);
            }
        }
        if (hashMap.size() == 0) {
            i = ExploreByTouchHelper.INVALID_ID;
            for (String str2 : map.keySet()) {
                num = (Integer) map.get(str2);
                if (num.intValue() > i) {
                    i = num.intValue();
                    hashMap.clear();
                }
                if (num.intValue() == i) {
                    hashMap.put(str2, num);
                }
            }
        }
        return hashMap;
    }
}
