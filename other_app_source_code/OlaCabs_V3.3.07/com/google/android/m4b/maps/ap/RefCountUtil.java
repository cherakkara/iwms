package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.ap.DashServerMapTileStore.DashServerMapTileStore;
import com.google.p025a.p028c.au;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.ap.g */
public final class RefCountUtil {
    private static final Map<DashServerMapTileStore, Integer> f3824a;

    static {
        Map a = au.m2808a(5);
        f3824a = a;
        a.put(DashServerMapTileStore.PREFETCH_OFFLINE_MAP, Integer.valueOf(1));
    }

    private static void m6292a(int i) {
        if ((i >> 10) != 0) {
            throw new IllegalArgumentException("the modifier [" + i + "] is out of bound");
        } else if (((i & 31) & (i >> 5)) != 0) {
            throw new IllegalArgumentException("the modifier [" + i + "] has conflict bits");
        }
    }

    public static int m6290a(int i, int i2) {
        RefCountUtil.m6292a(i);
        RefCountUtil.m6292a(i2);
        int i3 = i2 & 31;
        int i4 = i2 >> 5;
        return (((i & 31) & (i4 ^ 31)) | i3) + ((((i >> 5) & (i3 ^ 31)) | i4) << 5);
    }

    public static boolean m6293a(DashServerMapTileStore dashServerMapTileStore) {
        return f3824a.containsKey(dashServerMapTileStore);
    }

    public static int m6291a(DashServerMapTileStore dashServerMapTileStore, boolean z) {
        Integer num = (Integer) f3824a.get(dashServerMapTileStore);
        if (num == null) {
            return 0;
        }
        return z ? num.intValue() : num.intValue() << 5;
    }
}
