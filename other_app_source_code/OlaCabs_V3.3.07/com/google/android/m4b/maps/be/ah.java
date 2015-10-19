package com.google.android.m4b.maps.be;

import com.google.p025a.p028c.ar;
import java.util.List;

/* compiled from: OverlayManager */
public final class ah {
    private final List<OverlayManager> f5614a;

    /* renamed from: com.google.android.m4b.maps.be.ah.a */
    interface OverlayManager {
        void m8275c();
    }

    public ah() {
        this.f5614a = ar.m2515a();
    }

    final void m8434a(OverlayManager overlayManager) {
        this.f5614a.add(overlayManager);
    }

    final void m8433a() {
        for (OverlayManager c : this.f5614a) {
            c.m8275c();
        }
        this.f5614a.clear();
    }

    final void m8435b(OverlayManager overlayManager) {
        this.f5614a.remove(overlayManager);
    }
}
