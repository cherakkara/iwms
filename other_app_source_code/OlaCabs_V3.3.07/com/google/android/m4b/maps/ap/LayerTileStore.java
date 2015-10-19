package com.google.android.m4b.maps.ap;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.AbsolutePosition;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointOfInterest;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.StyleTable;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ab;
import com.google.android.m4b.maps.an.ab.Alignment;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ag;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.am;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bk;
import com.google.android.m4b.maps.an.bk.LabelGroup;
import com.google.android.m4b.maps.an.bl;
import com.google.android.m4b.maps.ap.DashServerTileStore.DashServerTileStore;
import com.google.android.m4b.maps.aq.TileCallback;
import com.google.android.m4b.maps.ar.InMemoryTileCache;
import com.google.android.m4b.maps.au.GeometryConverter;
import com.google.android.m4b.maps.bx.Layer;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ap.e */
public final class LayerTileStore extends DashServerTileStore {

    /* renamed from: com.google.android.m4b.maps.ap.e.a */
    static class LayerTileStore extends DashServerTileStore {
        private ProtoBuf[] f3821a;
        private Clock f3822b;

        LayerTileStore(Clock clock) {
            super(8);
            this.f3821a = new ProtoBuf[8];
            this.f3822b = clock;
        }

        protected final boolean m6277a(DashServerTileStore dashServerTileStore) {
            return m6158k() == 0 || ((LayerTileStore) dashServerTileStore.f3796a).m6282a((LayerTileStore) m6151a(0).f3796a);
        }

        public final aa m6279b(int i) {
            ProtoBuf protoBuf = this.f3821a[i];
            if (protoBuf == null) {
                return null;
            }
            StyleTable styleTable = new StyleTable();
            ac acVar = (LayerTileStore) m6151a(i).f3796a;
            int k = protoBuf.m10215k(3);
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < k; i2++) {
                bc a = LayerTileStore.m6284a(protoBuf.m10202c(3, i2), Style.m6090a(), acVar);
                if (a != null) {
                    arrayList.add(a);
                    if (arrayList.size() == 20) {
                        break;
                    }
                }
            }
            bc[] bcVarArr = (bc[]) arrayList.toArray(new bc[arrayList.size()]);
            bl k2 = acVar.m6283k();
            long j = -1;
            if (k2.m5846c()) {
                j = this.f3822b.m10152b() + k2.m5847d();
            }
            return new VectorTile().m5605a(styleTable).m5603a(acVar).m5606a(bcVarArr).m5602a(j).m5608a();
        }

        public final int m6280i() {
            return 36;
        }

        public final void m6276a(DataOutput dataOutput) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            m6275m().m10199b(byteArrayOutputStream);
            dataOutput.writeInt(byteArrayOutputStream.size());
            dataOutput.write(byteArrayOutputStream.toByteArray());
        }

        public final boolean m6278a(DataInput dataInput) {
            ProtoBuf a = ProtoBufUtil.m10226a(Layer.f7075b, dataInput);
            int k = a.m10215k(2);
            if (k == m6158k()) {
                for (int i = 0; i < k; i++) {
                    this.f3821a[i] = a.m10202c(2, i);
                }
            }
            return true;
        }

        private ProtoBuf m6275m() {
            int i;
            ProtoBuf protoBuf = new ProtoBuf(Layer.f7074a);
            protoBuf.m10210f(1, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            bl k = ((LayerTileStore) m6151a(0).f3796a).m6283k();
            ProtoBuf a = protoBuf.m10182a(2);
            a.m10197b(21, k.m5844a());
            String[] b = k.m5845b();
            for (i = 0; i < b.length; i += 2) {
                ProtoBuf a2 = a.m10182a(22);
                a2.m10197b(1, b[i]);
                a2.m10197b(2, b[i + 1]);
                a.m10190a(22, a2);
            }
            protoBuf.m10196b(2, a);
            for (i = 0; i < m6158k(); i++) {
                ac acVar = m6151a(i).f3796a;
                ProtoBuf protoBuf2 = new ProtoBuf(Tile.f6891g);
                protoBuf2.m10210f(1, 8);
                protoBuf2.m10184a(30, am.m5576a(acVar.m5440c(), acVar.m5441d(), acVar.m5439b()));
                protoBuf2.m10210f(2, 0);
                protoBuf2.m10210f(3, 0);
                protoBuf2.m10210f(4, 0);
                protoBuf.m10190a(3, protoBuf2);
            }
            return protoBuf;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ap.e.b */
    public static class LayerTileStore extends ac {
        private final bl f3823d;

        private LayerTileStore(ac acVar, bl blVar) {
            super(acVar.m5439b(), acVar.m5440c(), acVar.m5441d(), acVar.m5447j());
            this.f3823d = blVar;
        }

        public final ac m6281a(ag agVar) {
            return new LayerTileStore(super.m5434a(agVar), this.f3823d);
        }

        public final bl m6283k() {
            return this.f3823d;
        }

        public final int hashCode() {
            return (super.hashCode() * 37) + this.f3823d.hashCode();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof LayerTileStore) && super.equals(obj) && m6282a((LayerTileStore) obj)) {
                return true;
            }
            return false;
        }

        public final boolean m6282a(LayerTileStore layerTileStore) {
            return this.f3823d == layerTileStore.f3823d;
        }

        public final String toString() {
            StringBuilder append = new StringBuilder("[layer: ").append(this.f3823d.m5844a());
            append.append(" params: ");
            String[] b = this.f3823d.m5845b();
            for (int i = 0; i < b.length; i += 2) {
                append.append(b[i]).append('=').append(b[i + 1]);
            }
            append.append(" coords: ").append(super.toString()).append(']');
            return append.toString();
        }
    }

    public LayerTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, int i, Locale locale, File file) {
        super(dataRequestDispatcherInterface, ai.f3430h, "lts", new InMemoryTileCache(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH), null, 0, true, i, locale, file);
    }

    public final void m6287a(ac acVar, TileCallback tileCallback) {
        if (acVar instanceof LayerTileStore) {
            super.m6207a(acVar, tileCallback);
            return;
        }
        throw new ClassCastException("DashServerLayerTileStore only supports LayerCoords");
    }

    public final aa m6286a(ac acVar, boolean z) {
        if (acVar instanceof LayerTileStore) {
            return super.m6204a(acVar, z);
        }
        throw new ClassCastException("DashServerLayerTileStore only supports LayerCoords");
    }

    public final ai m6289h() {
        return ai.f3430h;
    }

    protected final DashServerTileStore m6288g() {
        return new LayerTileStore(this.b);
    }

    static bc m6284a(ProtoBuf protoBuf, Style style, ac acVar) {
        FeatureId featureId;
        if (protoBuf.m10204d(1) != 0 || protoBuf.m10215k(3) == 0 || protoBuf.m10215k(2) == 0) {
            return null;
        }
        ProtoBuf c = protoBuf.m10202c(3, 0);
        if (!c.m10214j(31)) {
            return null;
        }
        Point a = GeometryConverter.m6619a(c.m10211g(31));
        if (!acVar.m5446i().m6046a(a)) {
            return null;
        }
        String valueOf;
        int[] iArr;
        FeatureId a2;
        AbsolutePosition[] absolutePositionArr;
        List arrayList;
        bk bkVar;
        bk bkVar2;
        bk bkVar3;
        Alignment[] alignmentArr;
        String str;
        ProtoBuf c2 = protoBuf.m10202c(2, 0);
        String h = c2.m10212h(2);
        String a3 = LayerTileStore.m6285a(c2, 3);
        String a4 = LayerTileStore.m6285a(c2, 4);
        LayerTileStore.m6285a(c2, 10);
        int d = c2.m10214j(7) ? c2.m10204d(7) / 10 : 0;
        String str2 = Trace.NULL;
        if (c.m10214j(34)) {
            int d2 = c.m10204d(34);
            if (d2 >= 0) {
                valueOf = String.valueOf(d2);
                iArr = new int[0];
                featureId = FeatureId.f7848a;
                a2 = FeatureId.m11291a(h);
                absolutePositionArr = new AbsolutePosition[]{new AbsolutePosition(a, 0, 0.0f, null, 0.0f, 0.0f, 0.0f)};
                if (valueOf == null) {
                    arrayList = new ArrayList();
                    arrayList.add(new LabelGroup(1, valueOf, 4, null, null, 0, null, 0.0f));
                    bkVar = new bk(arrayList, ab.f3378b);
                } else {
                    bkVar = null;
                }
                if (a3 == null) {
                    arrayList = new ArrayList();
                    arrayList.add(new LabelGroup(2, null, 0, a3, Style.m6090a(), 0, "styleid", 0.0f));
                    if (bkVar != null) {
                        bkVar2 = new bk(arrayList, ab.f3378b);
                        bkVar3 = null;
                    } else {
                        bkVar3 = new bk(arrayList, ab.f3378b);
                        bkVar2 = bkVar;
                    }
                } else {
                    bkVar3 = null;
                    bkVar2 = bkVar;
                }
                if (bkVar3 == null) {
                    bkVar3 = new bk(new ArrayList(), ab.f3378b);
                }
                alignmentArr = new Alignment[0];
                str = "styleid";
                if (a3 == null) {
                    a3 = Trace.NULL;
                }
                return new PointOfInterest(-1, acVar, a, a2, absolutePositionArr, bkVar2, bkVar3, alignmentArr, a4, style, 0, str, d, 0, 20, 0, null, a3, Alignment.f3380c, iArr);
            }
        }
        valueOf = str2;
        iArr = new int[0];
        featureId = FeatureId.f7848a;
        try {
            a2 = FeatureId.m11291a(h);
        } catch (IllegalArgumentException e) {
            a2 = featureId;
        }
        absolutePositionArr = new AbsolutePosition[]{new AbsolutePosition(a, 0, 0.0f, null, 0.0f, 0.0f, 0.0f)};
        if (valueOf == null) {
            bkVar = null;
        } else {
            arrayList = new ArrayList();
            arrayList.add(new LabelGroup(1, valueOf, 4, null, null, 0, null, 0.0f));
            bkVar = new bk(arrayList, ab.f3378b);
        }
        if (a3 == null) {
            bkVar3 = null;
            bkVar2 = bkVar;
        } else {
            arrayList = new ArrayList();
            arrayList.add(new LabelGroup(2, null, 0, a3, Style.m6090a(), 0, "styleid", 0.0f));
            if (bkVar != null) {
                bkVar3 = new bk(arrayList, ab.f3378b);
                bkVar2 = bkVar;
            } else {
                bkVar2 = new bk(arrayList, ab.f3378b);
                bkVar3 = null;
            }
        }
        if (bkVar3 == null) {
            bkVar3 = new bk(new ArrayList(), ab.f3378b);
        }
        alignmentArr = new Alignment[0];
        str = "styleid";
        if (a3 == null) {
            a3 = Trace.NULL;
        }
        return new PointOfInterest(-1, acVar, a, a2, absolutePositionArr, bkVar2, bkVar3, alignmentArr, a4, style, 0, str, d, 0, 20, 0, null, a3, Alignment.f3380c, iArr);
    }

    private static String m6285a(ProtoBuf protoBuf, int i) {
        return protoBuf.m10214j(i) ? protoBuf.m10212h(i) : Trace.NULL;
    }
}
