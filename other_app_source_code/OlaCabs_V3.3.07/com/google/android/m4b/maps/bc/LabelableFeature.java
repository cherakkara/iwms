package com.google.android.m4b.maps.bc;

import com.google.android.m4b.maps.an.bc;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.bc.d */
public final class LabelableFeature {
    private final bc f5430a;
    private final LabelSource f5431b;

    public LabelableFeature(bc bcVar, LabelSource labelSource) {
        Preconditions.m1817a((Object) bcVar);
        this.f5430a = bcVar;
        this.f5431b = labelSource;
    }

    public final bc m8186a() {
        return this.f5430a;
    }

    public final int m8187b() {
        return this.f5430a.m5549f();
    }

    public final LabelSource m8188c() {
        return this.f5431b;
    }

    public final int hashCode() {
        int hashCode = this.f5430a.hashCode();
        if (this.f5431b != null) {
            return (hashCode * 31) + this.f5431b.hashCode();
        }
        return hashCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LabelableFeature labelableFeature = (LabelableFeature) obj;
        if (this.f5430a.equals(labelableFeature.f5430a) && LabelSource.m8183a(this.f5431b, labelableFeature.f5431b)) {
            return true;
        }
        return false;
    }

    public final int m8189d() {
        return this.f5430a.m5551q() + 16;
    }
}
