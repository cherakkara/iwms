package com.google.android.m4b.maps.be;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.m4b.maps.be.t */
public final class MapViewUtils {

    /* renamed from: com.google.android.m4b.maps.be.t.1 */
    static class MapViewUtils implements Executor {
        private /* synthetic */ Handler f6076a;

        MapViewUtils(Handler handler) {
            this.f6076a = handler;
        }

        public final void execute(Runnable runnable) {
            this.f6076a.post(runnable);
        }
    }

    public static Executor m9533a() {
        return MapViewUtils.m9534a(new Handler(Looper.getMainLooper()));
    }

    public static Executor m9534a(Handler handler) {
        return new MapViewUtils(handler);
    }
}
