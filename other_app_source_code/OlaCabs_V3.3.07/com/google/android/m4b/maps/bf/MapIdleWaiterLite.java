package com.google.android.m4b.maps.bf;

import android.os.Handler;
import com.google.android.m4b.maps.be.MapIdleWaiter;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.p042r.ah;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.bf.d */
public final class MapIdleWaiterLite implements MapIdleWaiter {
    private MapIdleWaiterLite(Handler handler) {
        Preconditions.m1818a((Object) handler, (Object) "Handler is null");
    }

    public static MapIdleWaiterLite m9615a(Handler handler) {
        return new MapIdleWaiterLite(handler);
    }

    public final void m9616a(ah ahVar) {
        az.m8757a(5, "Map Loaded callback is not supported in Lite Mode");
    }

    public final void m9617b(ah ahVar) {
        az.m8757a(5, "Map Loaded callback is not supported in Lite Mode");
    }
}
