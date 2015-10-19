package com.leanplum;

import android.content.res.Configuration;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Map;

final class aG extends aH {
    aG() {
    }

    public final Object m12632a(String str) {
        if (str.equals("small")) {
            return Integer.valueOf(1);
        }
        if (str.equals("normal")) {
            return Integer.valueOf(2);
        }
        if (str.equals("large")) {
            return Integer.valueOf(3);
        }
        if (str.equals("xlarge")) {
            return Integer.valueOf(4);
        }
        return null;
    }

    public final boolean m12634a(Object obj, Configuration configuration) {
        return (configuration.screenLayout & 15) <= ((Integer) obj).intValue();
    }

    final Map<String, Object> m12633a(Map<String, Object> map, DisplayMetrics displayMetrics) {
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
