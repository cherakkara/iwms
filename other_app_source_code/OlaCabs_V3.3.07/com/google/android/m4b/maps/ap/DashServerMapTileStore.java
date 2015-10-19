package com.google.android.m4b.maps.ap;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.ag;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.am;
import com.google.android.m4b.maps.ap.DashServerTileStore.DashServerTileStore;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p058v.Util;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.utils.Constants;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.m4b.maps.ap.a */
public abstract class DashServerMapTileStore extends DashServerTileStore {
    private volatile boolean f3779e;
    private final int f3780f;
    private final List<Integer> f3781g;
    private final int f3782h;
    private final int f3783i;
    private final float f3784j;

    /* renamed from: com.google.android.m4b.maps.ap.a.a */
    public abstract class DashServerMapTileStore extends DashServerTileStore {
        protected int f3747a;
        protected byte[][] f3748b;
        private /* synthetic */ DashServerMapTileStore f3749c;

        protected DashServerMapTileStore(DashServerMapTileStore dashServerMapTileStore) {
            this.f3749c = dashServerMapTileStore;
            super(8);
            this.f3748b = new byte[8][];
        }

        protected int m6167j() {
            return this.f3747a;
        }

        public final int m6166i() {
            return 108;
        }

        public final void m6162a(DataOutput dataOutput) {
            ProtoBuf protoBuf = new ProtoBuf(Tile.f6886b);
            ProtoBuf protoBuf2 = new ProtoBuf(Tile.f6887c);
            protoBuf2.m10196b(1, protoBuf);
            protoBuf.m10210f(1, this.f3749c.f3780f);
            protoBuf.m10210f(4, this.f3749c.f3783i);
            protoBuf.m10210f(5, m6161m().m6168a());
            if (this.f3749c.f3784j > br.DEFAULT_BACKOFF_MULT) {
                protoBuf.m10183a(6, this.f3749c.f3784j);
            }
            for (Integer intValue : this.f3749c.f3781g) {
                protoBuf.m10189a(2, intValue.intValue());
            }
            if (false) {
                protoBuf.m10189a(3, 2);
            }
            if (VectorGlobalState.m7285a()) {
                protoBuf.m10189a(3, 0);
            }
            GmmSettings.m11527a();
            if (GmmSettings.m11532f()) {
                protoBuf.m10189a(3, 4);
            }
            if (ParameterManager.m6641a().m6628d()) {
                protoBuf.m10189a(3, 3);
            }
            if (m6161m() != DashServerMapTileStore.UNKNOWN) {
                protoBuf.m10210f(5, m6161m().m6168a());
            }
            protoBuf.m10189a(3, 6);
            if (this.f3749c.c == ai.f3446x) {
                protoBuf.m10189a(3, 7);
            }
            int k = m6158k();
            for (int i = 0; i < k; i++) {
                DashServerTileStore a = m6151a(i);
                ac acVar = a.f3796a;
                ProtoBuf protoBuf3 = new ProtoBuf(Tile.f6891g);
                protoBuf3.m10184a(30, am.m5576a(acVar.m5440c(), acVar.m5441d(), acVar.m5439b() + this.f3749c.f3782h));
                protoBuf3.m10210f(2, 0);
                protoBuf3.m10210f(3, 0);
                protoBuf3.m10210f(4, 0);
                protoBuf3.m10210f(1, a.f3803h.f3455y);
                protoBuf3.m10210f(7, a.f3803h.f3456z);
                acVar.m5437a(a.f3803h, protoBuf3);
                GmmSettings.m11527a();
                if (GmmSettings.m11532f()) {
                    protoBuf3.m10210f(8, a.f3804i);
                }
                protoBuf2.m10190a(9, protoBuf3);
            }
            ProtoBufUtil.m10228a(dataOutput, protoBuf2);
        }

        private DashServerMapTileStore m6161m() {
            DashServerMapTileStore dashServerMapTileStore = DashServerMapTileStore.UNKNOWN;
            for (int i = 0; i < m6158k(); i++) {
                DashServerMapTileStore dashServerMapTileStore2 = m6151a(i).f3799d;
                if (dashServerMapTileStore == DashServerMapTileStore.UNKNOWN || dashServerMapTileStore2.m6168a() < dashServerMapTileStore.m6168a()) {
                    dashServerMapTileStore = dashServerMapTileStore2;
                }
            }
            return dashServerMapTileStore;
        }

        protected byte[] m6165a(int i, int i2) {
            return new byte[i];
        }

        protected final boolean m6163a(DashServerTileStore dashServerTileStore) {
            if (m6158k() == 0) {
                return true;
            }
            if (m6151a(0).f3796a.m5439b() == dashServerTileStore.f3796a.m5439b() && m6151a(0).f3796a.m5447j() == dashServerTileStore.f3796a.m5447j()) {
                return true;
            }
            return false;
        }

        private void m6160a(InputStream inputStream) {
            int k = m6158k();
            int i = 0;
            while (true) {
                ProtoBuf protoBuf = new ProtoBuf(null);
                if (ProtoBufUtil.m10223a(Tile.f6890f, inputStream, protoBuf) != -1) {
                    ProtoBuf g = protoBuf.m10211g(1);
                    int d = g.m10214j(8) ? g.m10204d(8) : -1;
                    Object c = protoBuf.m10203c(2);
                    int length = c != null ? c.length : 0;
                    Object a = m6165a(length, d);
                    if (c != null) {
                        System.arraycopy(c, 0, a, a.length - length, length);
                    }
                    if (i < k) {
                        ProtoBuf a2;
                        if (g.m10214j(30)) {
                            a2 = am.m5577a(g.m10207e(30));
                        } else {
                            a2 = g;
                        }
                        int d2 = a2.m10204d(2);
                        int d3 = a2.m10204d(3);
                        int d4 = a2.m10204d(4) - this.f3749c.f3782h;
                        ag agVar = new ag();
                        for (TileParameters a3 : TileParameters.values()) {
                            af a4 = a3.m5454a(g);
                            if (a4 != null) {
                                agVar.m5473a(a4);
                            }
                        }
                        Integer a5 = m6152a(Util.m11545a(ai.m5501a(g.m10204d(1)), new ac(d4, d2, d3, agVar)));
                        if (a5 == null) {
                            Util.m11550a(this.f3749c.getName(), "Received wrong tile");
                        } else if (length != 0) {
                            this.f3748b[a5.intValue()] = a;
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }

        public final boolean m6164a(DataInput dataInput) {
            InputStream a = ProtoBufUtil.m10227a(dataInput);
            try {
                ProtoBuf protoBuf = new ProtoBuf(null);
                ProtoBufUtil.m10223a(Tile.f6890f, a, protoBuf);
                this.f3747a = protoBuf.m10204d(1);
                int d = protoBuf.m10204d(2);
                if (d != 0) {
                    Util.m11550a(this.f3749c.getName(), "Received tile response code: " + d);
                }
                m6160a(a);
                return true;
            } finally {
                a.close();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.a.b */
    public enum DashServerMapTileStore {
        UNKNOWN(-1),
        NORMAL(1),
        PREFETCH_OFFLINE_MAP(4),
        PREFETCH_ROUTE(6),
        PREFETCH_AREA(12);
        
        private final int f3756f;

        private DashServerMapTileStore(int i) {
            this.f3756f = i;
        }

        public final int m6168a() {
            return this.f3756f;
        }
    }

    protected DashServerMapTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, String str, ai aiVar, int i, List<Integer> list, int i2, int i3, float f, boolean z, Locale locale, boolean z2, File file, DiskTileCacheListener diskTileCacheListener) {
        super(dataRequestDispatcherInterface, aiVar, str, aiVar.m5512b(), Util.m11556b() ? aiVar.m5510a(str, z2, diskTileCacheListener) : null, aiVar == ai.f3426d ? Constants.MILLIS_IN_A_SECOND : HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE, z, i3, locale, file);
        this.f3779e = false;
        this.f3780f = AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
        this.f3781g = list;
        this.f3783i = i2;
        if (!list.contains(Integer.valueOf(7))) {
            if (!list.contains(Integer.valueOf(10))) {
                if (!list.contains(Integer.valueOf(11))) {
                    if (!list.contains(Integer.valueOf(12))) {
                        if (!list.contains(Integer.valueOf(9))) {
                            int i4 = AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
                            int i5 = 0;
                            while (i4 > AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                                i4 >>= 1;
                                i5++;
                            }
                            while (i4 < AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                                i4 <<= 1;
                                i5--;
                            }
                            this.f3782h = i5;
                            this.f3784j = f;
                        }
                    }
                }
            }
        }
        this.f3782h = 0;
        this.f3784j = f;
    }
}
