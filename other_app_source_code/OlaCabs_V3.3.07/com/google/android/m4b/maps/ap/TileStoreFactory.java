package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.ai;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.ap.l */
public final class TileStoreFactory {
    private static final Map<ai, TileStore> f3840a;

    static {
        f3840a = new HashMap();
    }

    public static void m6311a(ai aiVar, TileStore tileStore) {
        f3840a.put(aiVar, tileStore);
    }

    public static boolean m6312a(ai aiVar) {
        return f3840a.containsKey(aiVar);
    }

    public static TileStore m6313b(ai aiVar) {
        TileStore tileStore = (TileStore) f3840a.get(aiVar);
        if (tileStore != null) {
            return tileStore;
        }
        throw new IllegalStateException("TileStore: " + aiVar + " has not been registered ");
    }
}
