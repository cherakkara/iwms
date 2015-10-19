package com.google.android.m4b.maps.av;

import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.m4b.maps.ag.TileCoordGeneratorProvider;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.bp;
import com.google.android.m4b.maps.an.bq;
import com.google.android.m4b.maps.an.br;
import com.google.android.m4b.maps.as.TileFetcher;
import com.google.android.m4b.maps.au.FeatureMapRasterParser;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ba.GLImageTile;
import com.google.android.m4b.maps.ba.GLTile;
import com.google.android.m4b.maps.be.MapsEngineFeatureImpl;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p061y.ConversionUtils;
import com.google.android.m4b.maps.p061y.MapsEngineLayerRendererImpl;
import com.google.p025a.p026a.Function;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.az;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/* compiled from: FeatureMapTileOverlay */
public final class ag extends TileOverlay {
    private static final Comparator<GLTile> f4190d;
    private final int f4191e;
    private final int f4192f;

    /* renamed from: com.google.android.m4b.maps.av.ag.1 */
    static class FeatureMapTileOverlay implements Function<GLTile, ac> {
        FeatureMapTileOverlay() {
        }

        public final /* synthetic */ Object m6714a(Object obj) {
            return ((GLTile) obj).m7845b();
        }
    }

    static {
        f4190d = az.m2824b().m2827a(new FeatureMapTileOverlay()).m2826a();
    }

    ag(ai aiVar, TileFetcher tileFetcher, TileCoordGeneratorProvider tileCoordGeneratorProvider, int i, int i2, int i3, GLOverlay gLOverlay, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Resources resources) {
        super(aiVar, tileFetcher, tileCoordGeneratorProvider, i, i2, 4, gLOverlay, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, 384, false, true, false, false, false, false);
        m6742a(new br(new ProtoBuf(Tile.f6893i)));
        this.f4192f = 31 - Integer.numberOfLeadingZeros(AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
        this.f4191e = (int) TypedValue.applyDimension(1, 10.0f, resources.getDisplayMetrics());
    }

    public final List<MapsEngineFeatureImpl> m6752a(Point point, Camera camera, MapsEngineLayerRendererImpl mapsEngineLayerRendererImpl) {
        List<MapsEngineFeatureImpl> a = ar.m2515a();
        Set a2 = bk.m2870a();
        Region2D a3 = Rectangle2D.m6040a(point, (int) (((float) this.f4191e) * camera.m7450r()));
        List<GLTile> n = m6750n();
        List<GLImageTile> a4 = ar.m2515a();
        for (GLTile gLTile : n) {
            if ((gLTile instanceof GLImageTile) && gLTile.m7845b().m5446i().m6047a(a3)) {
                a4.add((GLImageTile) gLTile);
            }
        }
        Collections.sort(a4, f4190d);
        int i = 0;
        while (i < this.f4191e) {
            int r = (int) camera.m7450r();
            a3 = Rectangle2D.m6040a(point, i * r);
            for (GLImageTile h : a4) {
                for (bp bpVar : h.m7894h()) {
                    if (!bpVar.m5870a().isEmpty()) {
                        for (bq bqVar : bpVar.m5870a()) {
                            if (!(bqVar.m5874c() == null || !bqVar.m5874c().m6047a(a3) || a2.contains(bqVar.m5872a()))) {
                                a.add(new MapsEngineFeatureImpl(mapsEngineLayerRendererImpl.m11775g(), bqVar.m5872a(), ConversionUtils.m11635a(bqVar.m5873b())));
                                a2.add(bqVar.m5872a());
                            }
                        }
                    }
                }
            }
            for (GLImageTile gLImageTile : a4) {
                for (int i2 = -i; i2 <= i; i2++) {
                    int i3 = -i;
                    while (i3 <= i) {
                        if (Math.abs(i2) == i || Math.abs(i3) == i) {
                            Point point2 = new Point(point.m5958f() + (r * i2), point.m5960g() + (r * i3));
                            for (bp bpVar2 : gLImageTile.m7894h()) {
                                FeatureMapRasterParser b = bpVar2.m5871b();
                                ac b2 = gLImageTile.m7884b();
                                if (b != null && b2.m5446i().m6046a(point2)) {
                                    try {
                                        for (String str : b.m6617a((point2.m5958f() - b2.m5442e()) >> ((30 - b2.m5439b()) - this.f4192f), (b2.m5445h().m5960g() - point2.m5960g()) >> ((30 - b2.m5439b()) - this.f4192f))) {
                                            if (!a2.contains(str)) {
                                                a.add(new MapsEngineFeatureImpl(mapsEngineLayerRendererImpl.m11775g(), str, ConversionUtils.m11635a(point2)));
                                                a2.add(str);
                                            }
                                        }
                                    } catch (FeatureMapRasterParser.FeatureMapRasterParser e) {
                                    }
                                }
                            }
                        }
                        i3++;
                    }
                }
            }
            i++;
        }
        return a;
    }
}
