package com.leanplum;

import android.content.res.Configuration;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Map;

final class aD extends aH {
    aD() {
    }

    public final Object m12623a(String str) {
        if (str.startsWith("sw") && str.endsWith("dp")) {
            return Integer.getInteger(str.substring(2, str.length() - 2));
        }
        return null;
    }

    public final boolean m12625a(Object obj, Configuration configuration) {
        try {
            if (((Integer) configuration.getClass().getField("smallestScreenWidthDp").get(configuration)).intValue() >= ((Integer) obj).intValue()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    final Map<String, Object> m12624a(Map<String, Object> map, DisplayMetrics displayMetrics) {
        Map<String, Object> hashMap = new HashMap();
        int i = ExploreByTouchHelper.INVALID_ID;
        for (String str : map.keySet()) {
            Integer num = (Integer) map.get(str);
            if (num.intValue() > i) {
                i = num.intValue();
                hashMap.clear();
            }
            if (num.intValue() == i) {
                hashMap.put(str, num);
            }
        }
        return hashMap;
    }
}
