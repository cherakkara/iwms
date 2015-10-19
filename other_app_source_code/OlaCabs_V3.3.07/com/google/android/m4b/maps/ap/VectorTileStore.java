package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.ModelUtil;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.ap.DashServerMapTileStore.DashServerMapTileStore;
import com.google.android.m4b.maps.ap.DashServerTileStore.DashServerTileStore;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.au.SharedBufferHolder;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.android.m4b.maps.ap.n */
public class VectorTileStore extends DashServerMapTileStore {
    private static final List<Integer> f3841e;
    protected long f3842f;

    /* renamed from: com.google.android.m4b.maps.ap.n.1 */
    class VectorTileStore extends DashServerMapTileStore {
        private /* synthetic */ VectorTileStore f3846c;

        VectorTileStore(VectorTileStore vectorTileStore) {
            this.f3846c = vectorTileStore;
            super(vectorTileStore);
        }

        protected final aa m6320b(int i) {
            if (this.b[i] == null) {
                return null;
            }
            long b;
            if (this.f3846c.f3842f > 0) {
                b = Config.m11320a().m11334h().m10152b() + this.f3846c.f3842f;
            } else {
                b = -1;
            }
            aa a = aq.m5622a(m6151a(i).f3796a, this.b[i], 0, m6151a(i).f3803h, b, -1);
            a.m5414c(this.f3846c.b);
            return a;
        }

        protected final byte[] m6319a(int i, int i2) {
            byte[] bArr = new byte[(i + 8)];
            ModelUtil.m5886a(this.a, bArr, 0);
            ModelUtil.m5886a(i2, bArr, 4);
            return bArr;
        }

        protected final byte[] m6321c(int i) {
            return this.b[i];
        }
    }

    static {
        f3841e = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(12)});
    }

    public VectorTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, ai aiVar, int i, float f, Locale locale, boolean z, File file, DiskTileCacheListener diskTileCacheListener) {
        String str;
        StringBuilder append = new StringBuilder("vts").append(aiVar.f3449C);
        if (dataRequestDispatcherInterface == null || dataRequestDispatcherInterface.m11377m() == null || "DriveAbout".equals(dataRequestDispatcherInterface.m11377m())) {
            str = Trace.NULL;
        } else {
            str = new StringBuilder(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).append(dataRequestDispatcherInterface.m11377m().replace(':', '_')).toString();
        }
        super(dataRequestDispatcherInterface, append.append(str).toString(), aiVar, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, f3841e, 1, i, f, false, locale, z, file, diskTileCacheListener);
        this.f3842f = -1;
    }

    public final void m6315f() {
        SharedBufferHolder.m6654a();
        try {
            super.m6218f();
        } finally {
            SharedBufferHolder.m6657b();
        }
    }

    protected final DashServerTileStore m6316g() {
        return new VectorTileStore(this);
    }

    public final void m6314a(long j) {
        this.f3842f = 120000;
    }
}
