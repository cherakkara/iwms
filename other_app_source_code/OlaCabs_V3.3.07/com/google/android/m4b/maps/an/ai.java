package com.google.android.m4b.maps.an;

import android.content.res.Resources;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.ao.RoadGraphPiece;
import com.google.android.m4b.maps.ap.ImageTileStore;
import com.google.android.m4b.maps.ap.LayerTileStore;
import com.google.android.m4b.maps.ap.RoadGraphTileStore;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.ap.VectorModifierTileStore;
import com.google.android.m4b.maps.ap.VectorTileStore;
import com.google.android.m4b.maps.ar.DiskTileCache;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.ar.SDCardTileCache.SDCardTileCache;
import com.google.android.m4b.maps.ar.SoftInMemoryTileCache;
import com.google.android.m4b.maps.ar.TileCache;
import com.google.android.m4b.maps.av.TileOverlay;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.av.ac;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p049i.ByteArrayDataInput;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p058v.Stats;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: TileType */
public abstract class ai implements Comparable<ai> {
    private static final Map<Integer, ai> f3414D;
    private static final Stroke f3415J;
    private static final TextBoxStyle f3416K;
    private static final TextBoxStyle f3417L;
    private static final TextBoxStyle f3418M;
    private static final TextStyle f3419N;
    private static final Style f3420O;
    private static final Style f3421P;
    private static final Style f3422Q;
    public static final ai f3423a;
    public static final ai f3424b;
    public static final ai f3425c;
    public static final ai f3426d;
    public static final ai f3427e;
    public static final ai f3428f;
    public static final ai f3429g;
    public static final ai f3430h;
    public static final ai f3431i;
    public static final ai f3432j;
    public static final ai f3433k;
    public static final ai f3434l;
    public static final ai f3435m;
    public static final ai f3436n;
    public static final ai f3437o;
    public static final ai f3438p;
    public static final ai f3439q;
    public static final ai f3440r;
    public static final ai f3441s;
    public static final ai f3442t;
    public static final ai f3443u;
    public static final ai f3444v;
    public static final ai f3445w;
    public static final ai f3446x;
    public final boolean f3447A;
    public final int f3448B;
    public final String f3449C;
    private final int f3450E;
    private final boolean f3451F;
    private final boolean f3452G;
    private final boolean f3453H;
    private final SDCardTileCache f3454I;
    public final int f3455y;
    public final int f3456z;

    /* renamed from: com.google.android.m4b.maps.an.ai.b */
    static abstract class TileType {
        private final int f3405a;
        private int f3406b;
        private String f3407c;
        private boolean f3408d;
        private boolean f3409e;
        private boolean f3410f;
        private boolean f3411g;

        abstract ai m5490a();

        private TileType(int i) {
            this.f3406b = 0;
            this.f3407c = Trace.NULL;
            this.f3408d = false;
            this.f3409e = true;
            this.f3410f = false;
            this.f3411g = false;
            this.f3405a = i;
        }

        final TileType m5488a(int i) {
            this.f3406b = AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS;
            return this;
        }

        final TileType m5489a(String str) {
            this.f3407c = str;
            return this;
        }

        final TileType m5491b(boolean z) {
            this.f3408d = true;
            return this;
        }

        final TileType m5492c(boolean z) {
            this.f3409e = false;
            return this;
        }

        final TileType m5493d(boolean z) {
            this.f3410f = true;
            return this;
        }

        final TileType m5494e(boolean z) {
            this.f3411g = z;
            return this;
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.i */
    static class TileType extends ai {
        private final boolean f3457D;

        /* renamed from: com.google.android.m4b.maps.an.ai.i.a */
        static class TileType extends TileType {
            private boolean f3412a;

            private TileType(int i) {
                super((byte) 0);
                this.f3412a = false;
            }

            final TileType m5497f(boolean z) {
                this.f3412a = true;
                return this;
            }

            ai m5496a() {
                return new TileType();
            }
        }

        public /* synthetic */ int compareTo(Object obj) {
            return super.m5508a((ai) obj);
        }

        public final TileStore m5518a(DataRequestDispatcherInterface dataRequestDispatcherInterface, Resources resources, Locale locale, File file, boolean z, boolean z2) {
            if (this == n && !GmmSettings.m11533g()) {
                return null;
            }
            float f = this.f3451F ? resources.getDisplayMetrics().density : br.DEFAULT_BACKOFF_MULT;
            int a = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
            if (this == ai.f3443u) {
                return null;
            }
            boolean z3 = z && this.f3452G;
            if (this.f3447A) {
                return new VectorModifierTileStore(dataRequestDispatcherInterface, this, a, f, locale, z3, file, VectorModifierTileStore.f3843e, null);
            }
            TileStore vectorTileStore = new VectorTileStore(dataRequestDispatcherInterface, this, a, f, locale, z3, file, null);
            if (this == ai.f3428f || this == ai.f3429g) {
                vectorTileStore.m6314a(120000);
            }
            if (!this.f3457D || !z2) {
                return vectorTileStore;
            }
            vectorTileStore.m6211a(Stats.m11537a((byte) 1));
            return vectorTileStore;
        }

        public final SDCardTileCache m5519g() {
            return new TileType(this);
        }

        final int m5517a() {
            return AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
        }

        private TileType(TileType tileType) {
            super((byte) 0);
            this.f3457D = tileType.f3412a;
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.a */
    static class TileType extends TileType {
        private final boolean f3458D;

        /* renamed from: com.google.android.m4b.maps.an.ai.a.a */
        static class TileType extends TileType {
            private boolean f3413a;

            private TileType(int i) {
                super((byte) 0);
                this.f3413a = false;
            }

            final TileType m5499a(boolean z) {
                this.f3413a = true;
                return this;
            }

            final ai m5500a() {
                return new TileType();
            }
        }

        private TileType(TileType tileType) {
            super((byte) 0);
            this.f3458D = tileType.f3413a;
        }

        public final boolean m5522e() {
            return true;
        }

        public final int m5520a(int i, ac acVar) {
            if (this.f3458D && acVar != ac.HYBRID) {
                return i;
            }
            if (acVar == ac.RASTER_ONLY || acVar == ac.TERRAIN) {
                return 0;
            }
            if (acVar == ac.NORMAL || acVar == ac.NIGHT) {
                return i;
            }
            return i & -6663;
        }

        public final boolean m5523f() {
            return true;
        }

        public final void m5521a(GL10 gl10) {
            gl10.glColor4f(0.0f, 0.0f, 0.0f, Defaults.ACTIVITY_TRACE_MIN_UTILIZATION);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.c */
    static class TileType extends ai {

        /* renamed from: com.google.android.m4b.maps.an.ai.c.a */
        static class TileType extends TileType {
            private TileType(int i) {
                super((byte) 0);
            }

            final ai m5524a() {
                return new TileType();
            }
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return super.m5508a((ai) obj);
        }

        public final TileStore m5526a(DataRequestDispatcherInterface dataRequestDispatcherInterface, Resources resources, Locale locale, File file, boolean z, boolean z2) {
            int b;
            float f;
            float f2 = 3.0f;
            int b2 = ai.m5502b(resources.getDisplayMetrics().densityDpi);
            if (this == ai.f3426d) {
                b = TileOverlay.m6722b(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
            } else {
                b = TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
            }
            if (this.f3451F) {
                f = resources.getDisplayMetrics().density;
            } else {
                f = br.DEFAULT_BACKOFF_MULT;
            }
            if (this != x) {
                f2 = f;
            } else if (f != 4.0f) {
                f2 = f == 3.0f ? dm.DEFAULT_BACKOFF_MULT : f == dm.DEFAULT_BACKOFF_MULT ? 1.5f : br.DEFAULT_BACKOFF_MULT;
            }
            return new ImageTileStore(dataRequestDispatcherInterface, this, b2, b, f2, locale, file, null);
        }

        public final SDCardTileCache m5529g() {
            return new TileType(this);
        }

        public final boolean m5528f() {
            return true;
        }

        public final void m5527a(GL10 gl10) {
            gl10.glColor4f(0.0f, 0.0f, br.DEFAULT_BACKOFF_MULT, Defaults.ACTIVITY_TRACE_MIN_UTILIZATION);
        }

        final int m5525a() {
            return AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT;
        }

        private TileType(TileType tileType) {
            super((byte) 0);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.d */
    static class TileType implements SDCardTileCache {
        private ai f3459a;

        public TileType(ai aiVar) {
            this.f3459a = aiVar;
        }

        public final aa m5531a(ac acVar, byte[] bArr, int i, long j, long j2) {
            DataInput byteArrayDataInput = new ByteArrayDataInput(bArr);
            byteArrayDataInput.skipBytes(i);
            ai aiVar = this.f3459a;
            int readInt = byteArrayDataInput.readInt();
            if (readInt != 1146241364) {
                throw new IOException("TILE_MAGIC expected: " + readInt);
            }
            readInt = an.m5578a(byteArrayDataInput);
            if (readInt == 7 || readInt == 8) {
                ac a = ac.m5425a(byteArrayDataInput);
                if (a.m5440c() == acVar.m5440c() && a.m5441d() == acVar.m5441d() && a.m5439b() == acVar.m5439b()) {
                    int a2 = an.m5578a(byteArrayDataInput);
                    int a3 = an.m5578a(byteArrayDataInput);
                    int a4 = an.m5578a(byteArrayDataInput);
                    byte[] bArr2 = new byte[an.m5578a(byteArrayDataInput)];
                    byteArrayDataInput.readFully(bArr2);
                    return new bg(acVar, a2, a3, a4, bArr2, aiVar);
                }
                throw new IOException("Expected tile coords: " + acVar + " but received " + a);
            }
            throw new IOException("Version mismatch: 7 or 8 expected, " + readInt + " found");
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.e */
    static class TileType extends TileType {

        /* renamed from: com.google.android.m4b.maps.an.ai.e.a */
        static class TileType extends TileType {
            private TileType(int i) {
                super((byte) 0);
            }

            final ai m5532a() {
                return new TileType();
            }
        }

        private TileType(TileType tileType) {
            super((byte) 0);
        }

        public final boolean m5534f() {
            return true;
        }

        public final void m5533a(GL10 gl10) {
            gl10.glColor4f(br.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f, Defaults.ACTIVITY_TRACE_MIN_UTILIZATION);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.f */
    static class TileType extends ai {

        /* renamed from: com.google.android.m4b.maps.an.ai.f.a */
        static class TileType extends TileType {
            private TileType(int i) {
                super((byte) 0);
            }

            final ai m5535a() {
                return new TileType();
            }
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return super.m5508a((ai) obj);
        }

        public final TileStore m5537a(DataRequestDispatcherInterface dataRequestDispatcherInterface, Resources resources, Locale locale, File file, boolean z, boolean z2) {
            return new LayerTileStore(dataRequestDispatcherInterface, TileOverlay.m6715a(resources, (int) AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH), locale, file);
        }

        public final SDCardTileCache m5538g() {
            return null;
        }

        final int m5536a() {
            return 0;
        }

        private TileType(TileType tileType) {
            super((byte) 0);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.g */
    static class TileType extends ai {

        /* renamed from: com.google.android.m4b.maps.an.ai.g.a */
        static class TileType extends TileType {
            private TileType(int i) {
                super((byte) 0);
            }

            final ai m5539a() {
                return new TileType();
            }
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return super.m5508a((ai) obj);
        }

        public final TileStore m5541a(DataRequestDispatcherInterface dataRequestDispatcherInterface, Resources resources, Locale locale, File file, boolean z, boolean z2) {
            return new RoadGraphTileStore(dataRequestDispatcherInterface, this, locale, file, null);
        }

        public final SDCardTileCache m5543g() {
            return new TileType();
        }

        final int m5540a() {
            return AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT;
        }

        public final TileCache m5542b() {
            return new SoftInMemoryTileCache(Math.max(Math.min(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS, (VectorGlobalState.m7290f() >> 3) * 18), 36));
        }

        private TileType(TileType tileType) {
            super((byte) 0);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.h */
    static class TileType implements SDCardTileCache {
        private TileType() {
        }

        public final aa m5544a(ac acVar, byte[] bArr, int i, long j, long j2) {
            return RoadGraphPiece.m6129a(acVar, bArr, i, j);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ai.j */
    static class TileType implements SDCardTileCache {
        private ai f3460a;

        public TileType(ai aiVar) {
            this.f3460a = aiVar;
        }

        public final aa m5545a(ac acVar, byte[] bArr, int i, long j, long j2) {
            return aq.m5622a(acVar, bArr, i, this.f3460a, j, j2);
        }
    }

    abstract int m5506a();

    public abstract TileStore m5509a(DataRequestDispatcherInterface dataRequestDispatcherInterface, Resources resources, Locale locale, File file, boolean z, boolean z2);

    abstract SDCardTileCache m5516g();

    static /* synthetic */ int m5502b(int i) {
        return i > 160 ? 3 : 1;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m5508a((ai) obj);
    }

    static {
        f3414D = new HashMap();
        f3423a = new TileType((byte) 0).m5497f(true).m5493d(true).m5494e(true).m5490a();
        f3424b = new TileType((byte) 0).m5497f(true).m5493d(true).m5494e(true).m5490a();
        f3425c = new TileType((byte) 0).m5497f(true).m5489a("_tran_base").m5493d(true).m5494e(true).m5490a();
        f3426d = new TileType((byte) 0).m5492c(false).m5494e(true).m5490a();
        f3427e = new TileType((byte) 0).m5489a("_ter").m5492c(false).m5494e(true).m5490a();
        f3428f = new TileType((byte) 0).m5489a("_traf").m5490a();
        f3429g = new TileType((byte) 0).m5489a("_traf").m5490a();
        f3430h = new TileType((byte) 0).m5535a();
        f3431i = new TileType((byte) 0).m5494e(true).m5490a();
        f3432j = new TileType((byte) 0).m5499a(true).m5489a("_vec_bic").m5494e(true).m5490a();
        f3433k = new TileType((byte) 0).m5488a((int) AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).m5489a("_ter_bic").m5490a();
        f3434l = new TileType((byte) 0).m5488a((int) AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).m5489a("_hy_bic").m5490a();
        f3435m = new TileType((byte) 0).m5489a("_tran").m5494e(true).m5490a();
        f3436n = new TileType((byte) 0).m5494e(true).m5489a("_inaka").m5490a();
        f3437o = new TileType((byte) 0).m5489a("_labl").m5493d(true).m5494e(true).m5490a();
        f3438p = new TileType((byte) 0).m5489a("_tran_labl").m5493d(true).m5494e(true).m5490a();
        f3439q = new TileType((byte) 0).m5489a("_psm").m5491b(true).m5490a();
        f3440r = new TileType((byte) 0).m5489a("_related").m5491b(true).m5490a();
        f3441s = new TileType((byte) 0).m5489a("_high").m5491b(true).m5494e(true).m5490a();
        f3442t = new TileType((byte) 0).m5489a("_api").m5490a();
        f3443u = new TileType((byte) 0).m5489a("_star").m5491b(true).m5494e(false).m5490a();
        f3444v = new TileType((byte) 0).m5489a("_spotlight").m5490a();
        f3445w = new TileType((byte) 0).m5489a("_maps_engine_vector").m5494e(true).m5490a();
        f3446x = new TileType((byte) 0).m5489a("_maps_engine_image").m5494e(true).m5490a();
        f3415J = new Stroke(0, 0.0f, new int[0], 0);
        f3416K = new TextBoxStyle(ViewCompat.MEASURED_STATE_MASK, f3415J);
        f3417L = new TextBoxStyle(-16776961, f3415J);
        f3418M = new TextBoxStyle(SupportMenu.CATEGORY_MASK, f3415J);
        f3419N = new TextStyle(-1, 0, 10, 1.2f, br.DEFAULT_BACKOFF_MULT, 0);
        f3420O = new Style(-1, 12, null, null, f3419N, f3416K, null, null);
        f3421P = new Style(-1, 12, null, null, f3419N, f3418M, null, null);
        f3422Q = new Style(-1, 12, null, null, f3419N, f3417L, null, null);
    }

    private ai(TileType tileType) {
        this.f3455y = tileType.f3405a;
        this.f3456z = tileType.f3406b;
        this.f3449C = tileType.f3407c;
        this.f3447A = tileType.f3408d;
        this.f3451F = tileType.f3409e;
        this.f3452G = tileType.f3410f;
        this.f3453H = tileType.f3411g;
        this.f3454I = this.f3453H ? m5516g() : null;
        this.f3448B = this.f3455y + (this.f3456z << 8);
        this.f3450E = f3414D.size();
        f3414D.put(Integer.valueOf(this.f3455y + this.f3456z), this);
    }

    public final int m5508a(ai aiVar) {
        return this.f3450E - aiVar.f3450E;
    }

    public final DiskTileCache m5510a(String str, boolean z, DiskTileCacheListener diskTileCacheListener) {
        if (!this.f3453H) {
            return null;
        }
        int i;
        if (z) {
            i = -1;
        } else {
            i = m5506a();
        }
        return new com.google.android.m4b.maps.ar.SDCardTileCache(str, i, this.f3454I, this, diskTileCacheListener);
    }

    public TileCache m5512b() {
        return new SoftInMemoryTileCache(Math.max(Math.min(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, (VectorGlobalState.m7290f() >> 3) * 32), 64));
    }

    public static ai m5501a(int i) {
        return (ai) f3414D.get(Integer.valueOf(i));
    }

    public static Iterable<ai> m5504c() {
        return f3414D.values();
    }

    public final int m5513d() {
        return this.f3450E;
    }

    public String toString() {
        Field[] fields = getClass().getFields();
        int length = fields.length;
        int i = 0;
        while (i < length) {
            Field field = fields[i];
            try {
                if (this == field.get(this)) {
                    return field.getName();
                }
                i++;
            } catch (IllegalAccessException e) {
            }
        }
        return WanType.UNKNOWN;
    }

    public int m5507a(int i, ac acVar) {
        return i;
    }

    public boolean m5514e() {
        return false;
    }

    public boolean m5515f() {
        return false;
    }

    public void m5511a(GL10 gl10) {
    }
}
