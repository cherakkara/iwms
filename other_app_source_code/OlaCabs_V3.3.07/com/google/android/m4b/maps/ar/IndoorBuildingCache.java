package com.google.android.m4b.maps.ar;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi;
import com.google.android.m4b.maps.ar.DiskProtoBufCache.DiskProtoBufCache;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.br.Indoor;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId.FeatureId;
import com.google.p025a.p028c.ac;
import com.newrelic.agent.android.api.v1.Defaults;
import java.io.File;
import java.util.Locale;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.m4b.maps.ar.h */
public final class IndoorBuildingCache {
    private final LRUCache<FeatureId, bh> f3952a;
    private final LRUCache<FeatureId, FeatureId> f3953b;
    private DiskProtoBufCache f3954c;
    private final Clock f3955d;
    private Locale f3956e;

    /* renamed from: com.google.android.m4b.maps.ar.h.a */
    static class IndoorBuildingCache extends bh {
        private IndoorBuildingCache(FeatureId featureId) {
            super(featureId, ac.m2345g(), -1, false, null, -1);
        }

        public final String toString() {
            return "[NotFoundBuilding: " + m5792a() + "]";
        }
    }

    public IndoorBuildingCache(Locale locale, Clock clock) {
        this.f3952a = new LRUCache(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        this.f3953b = new LRUCache(Defaults.RESPONSE_BODY_LIMIT);
        this.f3955d = clock;
        this.f3956e = locale;
    }

    public final void m6472a(File file) {
        DiskProtoBufCache diskProtoBufCache = new DiskProtoBufCache(this.f3955d, "bd", Indoor.f6734a, HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE, 86400000);
        if (diskProtoBufCache.m6342a(file)) {
            diskProtoBufCache.m6343a(this.f3956e);
            this.f3954c = diskProtoBufCache;
        }
    }

    public final bh m6469a(FeatureId featureId) {
        return m6466a(featureId, true);
    }

    public final bh m6473b(FeatureId featureId) {
        return m6466a(featureId, false);
    }

    private bh m6466a(FeatureId featureId, boolean z) {
        synchronized (this.f3953b) {
            if (this.f3953b.m6230a((Object) featureId) != null) {
                Object obj = (FeatureId) this.f3953b.m6235b((Object) featureId);
            }
        }
        synchronized (this.f3952a) {
            bh bhVar = (bh) this.f3952a.m6235b(obj);
            if (bhVar != null) {
                return bhVar;
            }
            if (!z || this.f3954c == null) {
                return null;
            }
            DiskProtoBufCache a = this.f3954c.m6339a(obj.m11301c());
            if (a == null) {
                return null;
            }
            FeatureId b = FeatureId.m11300b(a.f3858a.m10212h(1));
            if (b == null) {
                return null;
            }
            if (!obj.equals(b)) {
                a = this.f3954c.m6339a(b.m11301c());
                if (a == null) {
                    return null;
                }
                synchronized (this.f3953b) {
                    this.f3953b.m6239c(obj, b);
                }
            }
            bhVar = bh.m5789a(a.f3858a, a.f3859b);
            if (bhVar == null) {
                return bhVar;
            }
            m6468b(bhVar);
            return bhVar;
        }
    }

    public final bh m6470a(FeatureId featureId, ProtoBuf protoBuf) {
        FeatureId b = FeatureId.m11300b(protoBuf.m10212h(1));
        if (featureId == null || b == null) {
            return null;
        }
        long a = this.f3955d.m10151a() + 86400000;
        if (this.f3954c != null) {
            this.f3954c.m6340a(b.m11301c(), protoBuf);
            int k = protoBuf.m10215k(2);
            if (k != 0) {
                ProtoBuf protoBuf2 = new ProtoBuf(Indoor.f6734a);
                protoBuf2.m10197b(1, b.toString());
                for (int i = 0; i < k; i++) {
                    FeatureId b2 = FeatureId.m11300b(protoBuf.m10202c(2, i).m10212h(1));
                    if (b2 != null) {
                        this.f3954c.m6340a(b2.m11301c(), protoBuf2);
                    }
                }
            }
        }
        bh a2 = bh.m5789a(protoBuf, a);
        if (a2 == null) {
            return null;
        }
        m6468b(a2);
        return a2;
    }

    private void m6468b(bh bhVar) {
        FeatureId a = bhVar.m5792a();
        synchronized (this.f3952a) {
            this.f3952a.m6239c(a, bhVar);
        }
        synchronized (this.f3953b) {
            for (bi b : bhVar.m5795b()) {
                this.f3953b.m6239c(b.m5809b(), a);
            }
        }
    }

    public final void m6475c(FeatureId featureId) {
        IndoorBuildingCache indoorBuildingCache = new IndoorBuildingCache((byte) 0);
        synchronized (this.f3952a) {
            this.f3952a.m6239c(featureId, indoorBuildingCache);
        }
    }

    public static boolean m6467a(bh bhVar) {
        return bhVar instanceof IndoorBuildingCache;
    }

    public final void m6471a() {
        synchronized (this.f3952a) {
            this.f3952a.m6231a();
        }
        synchronized (this.f3953b) {
            this.f3953b.m6231a();
        }
    }

    public final void m6474b() {
        synchronized (this.f3952a) {
            this.f3952a.m6231a();
        }
        synchronized (this.f3953b) {
            this.f3953b.m6231a();
        }
        if (this.f3954c != null) {
            this.f3954c.m6341a();
        }
    }
}
