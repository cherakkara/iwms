package com.google.android.m4b.maps.ba;

import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.an.MutableVectorTile;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointOfInterest;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.aq;
import com.google.android.m4b.maps.an.aq.VectorTile;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bh;
import com.google.android.m4b.maps.an.bi;
import com.google.android.m4b.maps.an.bj;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.au.Bitmask;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.Transform;
import com.google.android.m4b.maps.av.ZoomTable;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.RoadShaderState;
import com.google.android.m4b.maps.ba.GLAreaGroup.GLAreaGroup;
import com.google.android.m4b.maps.ba.GLLineGroup.GLLineGroup;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.bc.LabelableFeature;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.android.m4b.maps.p059w.DeviceSpecificInfo;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ba.t */
public final class GLVectorTile implements GLTile {
    private static float[] f5362I;
    public static final Bitmask f5363a;
    private static final GLAreaGroup f5364b;
    private static final GLAreaGroup f5365c;
    private static final GLAreaGroup f5366d;
    private static final GLAreaGroup f5367e;
    private static final GLAreaGroup f5368f;
    private static final GLAreaGroup f5369g;
    private int f5370A;
    private float[] f5371B;
    private long f5372C;
    private volatile int f5373D;
    private LabelSource f5374E;
    private Boolean f5375F;
    private long f5376G;
    private long f5377H;
    private boolean f5378J;
    private RoadShaderState f5379h;
    private RoadShaderState f5380i;
    private GLRaster[] f5381j;
    private GLFeature[] f5382k;
    private GLLineGroup[] f5383l;
    private GLRoadGroup[][] f5384m;
    private GLFeature[] f5385n;
    private GLLineGroup[] f5386o;
    private GLBuildingGroup[] f5387p;
    private GLTrafficRoadGroup[] f5388q;
    private GLLabel f5389r;
    private ArrayList<LabelableFeature> f5390s;
    private Set<FeatureId> f5391t;
    private final ac f5392u;
    private final Rectangle2D f5393v;
    private final HashSet<String> f5394w;
    private int f5395x;
    private ZoomTable f5396y;
    private ai f5397z;

    /* renamed from: com.google.android.m4b.maps.ba.t.1 */
    class GLVectorTile implements Comparator<LabelableFeature> {
        GLVectorTile(GLVectorTile gLVectorTile) {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((LabelableFeature) obj2).m8186a().m5549f() - ((LabelableFeature) obj).m8186a().m5549f();
        }
    }

    static {
        f5364b = new GLAreaGroup(0.0f, 120.0f, false, -48.0f, 48.0f, true, 0.0f, 1879048191);
        f5366d = new GLAreaGroup(0.0f, 60.0f, false, -24.0f, 24.0f, true, 0.0f, 1879048191);
        f5368f = new GLAreaGroup(0.0f, 30.0f, false, -6.0f, 6.0f, true, 0.0f, 1879048191);
        f5365c = new GLAreaGroup(48.0f, 120.0f, true, -0.0f, 0.0f, false, 0.0f, -855638017);
        f5367e = new GLAreaGroup(24.0f, 60.0f, true, -0.0f, 0.0f, false, 0.0f, -855638017);
        f5369g = new GLAreaGroup(16.0f, 40.0f, true, -0.0f, 0.0f, false, 0.0f, -855638017);
        f5363a = Bitmask.f4072a;
        f5362I = new float[8];
    }

    public static GLVectorTile m8126a(aa aaVar, Bitmask bitmask, GLState gLState) {
        GLVectorTile gLVectorTile = new GLVectorTile(aaVar.m5409a());
        if (aaVar instanceof aq) {
            int i;
            GLAreaGroup gLAreaGroup;
            VectorTile i2;
            long uptimeMillis;
            Object obj;
            bc a;
            GLRoadGroup a2;
            Object obj2;
            long uptimeMillis2;
            List list;
            GLLineGroup[] gLLineGroupArr;
            int h;
            int i3;
            bc a3;
            PointOfInterest pointOfInterest;
            float d;
            aq aqVar = (aq) aaVar;
            gLVectorTile.f5395x = aqVar.m5646m();
            for (Object add : aqVar.m5640f()) {
                gLVectorTile.f5394w.add(add);
            }
            gLVectorTile.f5397z = aqVar.m5633b();
            gLVectorTile.f5370A = aqVar.m5638d();
            String[] g = aqVar.m5641g();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            GLLineGroup gLLineGroup = new GLLineGroup(gLVectorTile.f5392u, g);
            gLLineGroup = new GLLineGroup(gLVectorTile.f5392u, g);
            arrayList3.add(arrayList4);
            bj bjVar = (bj) aqVar.m5630a().m5436a(TileParameters.INDOOR);
            if (bjVar != null) {
                FeatureId.FeatureId b = bjVar.m5821b();
                IndoorBuildingStore c = IndoorBuildingStore.m6260c();
                if (c != null) {
                    bh a4 = c.m6262a(b);
                    if (a4 != null) {
                        bi a5 = a4.m5790a(b);
                        if (a5 != null) {
                            if (a5.m5813f() > 0) {
                                gLAreaGroup = ((float) aqVar.m5630a().m5439b()) > 18.5f ? f5368f : ((float) aqVar.m5630a().m5439b()) > 16.5f ? f5366d : f5364b;
                            } else if (a5.m5813f() < 0) {
                                gLAreaGroup = ((float) aqVar.m5630a().m5439b()) > 18.5f ? f5369g : ((float) aqVar.m5630a().m5439b()) > 16.5f ? f5367e : f5365c;
                            }
                            i2 = aqVar.m5643i();
                            uptimeMillis = SystemClock.uptimeMillis();
                            obj = 1;
                            while (i2.hasNext()) {
                                a = i2.m5613a();
                                if (bitmask.m6611a(a.m5546b())) {
                                    i2.next();
                                } else {
                                    switch (a.m5546b()) {
                                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                                            a2 = GLRoadGroup.m8098a(gLVectorTile.f5392u, g, i2, null, null, null, gLState);
                                            if (obj != null && a2.m8113c()) {
                                                obj = null;
                                            }
                                            arrayList4.add(a2);
                                            obj2 = obj;
                                            break;
                                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                                            if (obj == null) {
                                                arrayList5.add(GLAreaGroup.m7814a(gLVectorTile.f5392u, g, i2, gLLineGroup, gLAreaGroup, 9, gLState));
                                                obj2 = obj;
                                                break;
                                            }
                                            arrayList2.add(GLAreaGroup.m7814a(gLVectorTile.f5392u, g, i2, gLLineGroup, gLAreaGroup, 2, gLState));
                                            obj2 = obj;
                                            break;
                                        case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                                            arrayList6.add(GLBuildingGroup.m7827a(gLVectorTile.f5392u, g, i2));
                                            obj2 = obj;
                                            break;
                                        case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                                            if (obj == null) {
                                                arrayList5.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 10, gLState));
                                                obj2 = obj;
                                                break;
                                            }
                                            arrayList2.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 3, gLState));
                                            obj2 = obj;
                                            break;
                                        case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                                            arrayList.add(GLRaster.m8082a(gLVectorTile.f5392u, g, i2, gLState));
                                            obj2 = obj;
                                            break;
                                        case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                                            if (!GLLineMesh.m8035a((bm) a)) {
                                                if (obj == null) {
                                                    arrayList5.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 10, gLState));
                                                    obj2 = obj;
                                                    break;
                                                }
                                                arrayList2.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 3, gLState));
                                                obj2 = obj;
                                                break;
                                            } else if (obj == null) {
                                                gLLineGroup.m7986a(i2);
                                                obj2 = obj;
                                                break;
                                            } else {
                                                gLLineGroup.m7986a(i2);
                                                obj2 = obj;
                                                break;
                                            }
                                        case HTTP.HT /*9*/:
                                            arrayList7.add(GLTrafficRoadGroup.m8119a(gLVectorTile.f5392u, g, i2));
                                            obj2 = obj;
                                            break;
                                        default:
                                            i2.next();
                                            obj2 = obj;
                                            break;
                                    }
                                    uptimeMillis2 = SystemClock.uptimeMillis();
                                    if (uptimeMillis2 - uptimeMillis <= 10) {
                                        Thread.yield();
                                    } else {
                                        uptimeMillis2 = uptimeMillis;
                                    }
                                    uptimeMillis = uptimeMillis2;
                                    obj = obj2;
                                }
                            }
                            if (arrayList.size() > 0) {
                                gLVectorTile.f5381j = (GLRaster[]) arrayList.toArray(new GLRaster[arrayList.size()]);
                            }
                            if (arrayList2.size() > 0) {
                                gLVectorTile.f5382k = (GLFeature[]) arrayList2.toArray(new GLFeature[arrayList2.size()]);
                            }
                            if (((ArrayList) arrayList3.get(0)).size() > 0) {
                                gLVectorTile.f5384m = new GLRoadGroup[arrayList3.size()][];
                                for (i = 0; i < gLVectorTile.f5384m.length; i++) {
                                    list = (List) arrayList3.get(i);
                                    gLVectorTile.f5384m[i] = (GLRoadGroup[]) list.toArray(new GLRoadGroup[list.size()]);
                                }
                            }
                            if (arrayList5.size() > 0) {
                                gLVectorTile.f5385n = (GLFeature[]) arrayList5.toArray(new GLFeature[arrayList5.size()]);
                            }
                            if (arrayList6.size() > 0) {
                                gLVectorTile.f5387p = (GLBuildingGroup[]) arrayList6.toArray(new GLBuildingGroup[arrayList6.size()]);
                            }
                            if (arrayList7.size() > 0) {
                                gLVectorTile.f5388q = (GLTrafficRoadGroup[]) arrayList7.toArray(new GLTrafficRoadGroup[arrayList7.size()]);
                            }
                            gLVectorTile.f5383l = gLLineGroup.m7988a(3);
                            gLLineGroupArr = gLVectorTile.f5383l;
                            gLVectorTile.f5386o = gLLineGroup.m7988a(10);
                            gLLineGroupArr = gLVectorTile.f5386o;
                            h = aqVar.m5642h();
                            gLVectorTile.f5390s = new ArrayList(h);
                            for (i3 = 0; i3 < h; i3++) {
                                a3 = aqVar.m5631a(i3);
                                switch (a3.m5546b()) {
                                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                                        if (((Road) a3).m6060c() <= 0) {
                                            break;
                                        }
                                        gLVectorTile.m8127a(a3);
                                        break;
                                    case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                                        gLVectorTile.m8127a(a3);
                                        pointOfInterest = (PointOfInterest) a3;
                                        if (gLVectorTile.f5396y == null) {
                                            gLVectorTile.f5396y = ParameterManager.m6650c().m7365a(gLVectorTile.f5393v.m6052e(), gLVectorTile.f5397z);
                                        }
                                        d = gLVectorTile.f5396y.m7357d(pointOfInterest.m5976g());
                                        if (d < 0.0f) {
                                            d = (float) pointOfInterest.m5976g();
                                        }
                                        pointOfInterest.m5969a(d);
                                        d = gLVectorTile.f5396y.m7358e(pointOfInterest.m5977h());
                                        if (d < 0.0f) {
                                            d = (float) Math.min(22, pointOfInterest.m5977h() + 1);
                                        }
                                        pointOfInterest.m5971b(d);
                                        for (int i4 : a3.m5550k()) {
                                            if (i4 >= 0 && i4 < g.length) {
                                                gLVectorTile.f5394w.add(g[i4]);
                                            }
                                        }
                                        break;
                                    case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                                    case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                                        if (((bm) a3).m5853c() <= 0) {
                                            break;
                                        }
                                        gLVectorTile.m8127a(a3);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            Collections.sort(gLVectorTile.f5390s, new GLVectorTile(gLVectorTile));
                            if (aqVar instanceof MutableVectorTile) {
                                gLVectorTile.f5391t = ((MutableVectorTile) aqVar).m5901j();
                            }
                            gLVectorTile.m8140b(aqVar.m5644k());
                        }
                    }
                }
            }
            gLAreaGroup = null;
            i2 = aqVar.m5643i();
            uptimeMillis = SystemClock.uptimeMillis();
            obj = 1;
            while (i2.hasNext()) {
                a = i2.m5613a();
                if (bitmask.m6611a(a.m5546b())) {
                    switch (a.m5546b()) {
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            a2 = GLRoadGroup.m8098a(gLVectorTile.f5392u, g, i2, null, null, null, gLState);
                            obj = null;
                            arrayList4.add(a2);
                            obj2 = obj;
                            break;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            if (obj == null) {
                                arrayList2.add(GLAreaGroup.m7814a(gLVectorTile.f5392u, g, i2, gLLineGroup, gLAreaGroup, 2, gLState));
                                obj2 = obj;
                                break;
                            }
                            arrayList5.add(GLAreaGroup.m7814a(gLVectorTile.f5392u, g, i2, gLLineGroup, gLAreaGroup, 9, gLState));
                            obj2 = obj;
                            break;
                        case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                            arrayList6.add(GLBuildingGroup.m7827a(gLVectorTile.f5392u, g, i2));
                            obj2 = obj;
                            break;
                        case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                            if (obj == null) {
                                arrayList2.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 3, gLState));
                                obj2 = obj;
                                break;
                            }
                            arrayList5.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 10, gLState));
                            obj2 = obj;
                            break;
                        case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                            arrayList.add(GLRaster.m8082a(gLVectorTile.f5392u, g, i2, gLState));
                            obj2 = obj;
                            break;
                        case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                            if (!GLLineMesh.m8035a((bm) a)) {
                                if (obj == null) {
                                    gLLineGroup.m7986a(i2);
                                    obj2 = obj;
                                    break;
                                }
                                gLLineGroup.m7986a(i2);
                                obj2 = obj;
                                break;
                            } else if (obj == null) {
                                arrayList2.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 3, gLState));
                                obj2 = obj;
                                break;
                            } else {
                                arrayList5.add(GLLineMesh.m8030a(gLVectorTile.f5392u, g, i2, 10, gLState));
                                obj2 = obj;
                                break;
                            }
                        case HTTP.HT /*9*/:
                            arrayList7.add(GLTrafficRoadGroup.m8119a(gLVectorTile.f5392u, g, i2));
                            obj2 = obj;
                            break;
                        default:
                            i2.next();
                            obj2 = obj;
                            break;
                    }
                    uptimeMillis2 = SystemClock.uptimeMillis();
                    if (uptimeMillis2 - uptimeMillis <= 10) {
                        uptimeMillis2 = uptimeMillis;
                    } else {
                        Thread.yield();
                    }
                    uptimeMillis = uptimeMillis2;
                    obj = obj2;
                } else {
                    i2.next();
                }
            }
            if (arrayList.size() > 0) {
                gLVectorTile.f5381j = (GLRaster[]) arrayList.toArray(new GLRaster[arrayList.size()]);
            }
            if (arrayList2.size() > 0) {
                gLVectorTile.f5382k = (GLFeature[]) arrayList2.toArray(new GLFeature[arrayList2.size()]);
            }
            if (((ArrayList) arrayList3.get(0)).size() > 0) {
                gLVectorTile.f5384m = new GLRoadGroup[arrayList3.size()][];
                for (i = 0; i < gLVectorTile.f5384m.length; i++) {
                    list = (List) arrayList3.get(i);
                    gLVectorTile.f5384m[i] = (GLRoadGroup[]) list.toArray(new GLRoadGroup[list.size()]);
                }
            }
            if (arrayList5.size() > 0) {
                gLVectorTile.f5385n = (GLFeature[]) arrayList5.toArray(new GLFeature[arrayList5.size()]);
            }
            if (arrayList6.size() > 0) {
                gLVectorTile.f5387p = (GLBuildingGroup[]) arrayList6.toArray(new GLBuildingGroup[arrayList6.size()]);
            }
            if (arrayList7.size() > 0) {
                gLVectorTile.f5388q = (GLTrafficRoadGroup[]) arrayList7.toArray(new GLTrafficRoadGroup[arrayList7.size()]);
            }
            gLVectorTile.f5383l = gLLineGroup.m7988a(3);
            gLLineGroupArr = gLVectorTile.f5383l;
            gLVectorTile.f5386o = gLLineGroup.m7988a(10);
            gLLineGroupArr = gLVectorTile.f5386o;
            h = aqVar.m5642h();
            gLVectorTile.f5390s = new ArrayList(h);
            for (i3 = 0; i3 < h; i3++) {
                a3 = aqVar.m5631a(i3);
                switch (a3.m5546b()) {
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        if (((Road) a3).m6060c() <= 0) {
                            gLVectorTile.m8127a(a3);
                            break;
                        }
                        break;
                    case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                        gLVectorTile.m8127a(a3);
                        pointOfInterest = (PointOfInterest) a3;
                        if (gLVectorTile.f5396y == null) {
                            gLVectorTile.f5396y = ParameterManager.m6650c().m7365a(gLVectorTile.f5393v.m6052e(), gLVectorTile.f5397z);
                        }
                        d = gLVectorTile.f5396y.m7357d(pointOfInterest.m5976g());
                        if (d < 0.0f) {
                            d = (float) pointOfInterest.m5976g();
                        }
                        pointOfInterest.m5969a(d);
                        d = gLVectorTile.f5396y.m7358e(pointOfInterest.m5977h());
                        if (d < 0.0f) {
                            d = (float) Math.min(22, pointOfInterest.m5977h() + 1);
                        }
                        pointOfInterest.m5971b(d);
                        while (r2 < r5) {
                            gLVectorTile.f5394w.add(g[i4]);
                            break;
                        }
                        break;
                    case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                        if (((bm) a3).m5853c() <= 0) {
                            gLVectorTile.m8127a(a3);
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            Collections.sort(gLVectorTile.f5390s, new GLVectorTile(gLVectorTile));
            if (aqVar instanceof MutableVectorTile) {
                gLVectorTile.f5391t = ((MutableVectorTile) aqVar).m5901j();
            }
            gLVectorTile.m8140b(aqVar.m5644k());
        }
        return gLVectorTile;
    }

    private GLVectorTile(ac acVar) {
        this.f5379h = null;
        this.f5380i = null;
        this.f5371B = new float[4];
        this.f5372C = 0;
        this.f5373D = -1;
        this.f5374E = null;
        this.f5375F = null;
        this.f5376G = -1;
        this.f5377H = -1;
        this.f5378J = false;
        this.f5392u = acVar;
        this.f5393v = this.f5392u.m5446i();
        this.f5394w = new HashSet();
    }

    public final void m8134a(boolean z) {
        if (this.f5378J != z) {
            this.f5378J = z;
        }
    }

    public final boolean m8135a() {
        return this.f5375F == null || this.f5375F.booleanValue();
    }

    public final boolean m8136a(RankMergingFeatureIterator rankMergingFeatureIterator) {
        if (this.f5390s.isEmpty()) {
            return false;
        }
        rankMergingFeatureIterator.m7197a(this.f5390s.iterator());
        return true;
    }

    public final boolean m8138a(Set<FeatureId> set) {
        if (this.f5391t == null) {
            return false;
        }
        set.addAll(this.f5391t);
        this.f5391t = null;
        return true;
    }

    private void m8127a(bc bcVar) {
        this.f5390s.add(new LabelableFeature(bcVar, null));
    }

    public final void m8132a(LabelSource labelSource) {
        if (!LabelSource.m8183a(labelSource, this.f5374E)) {
            this.f5374E = labelSource;
            for (int i = 0; i < this.f5390s.size(); i++) {
                this.f5390s.set(i, new LabelableFeature(((LabelableFeature) this.f5390s.get(i)).m8186a(), labelSource));
            }
            this.f5373D = -1;
        }
    }

    public final int m8128a(Camera camera, com.google.android.m4b.maps.av.ac acVar) {
        int i = 0;
        if (this.f5381j != null) {
            i = 2;
        }
        if (this.f5382k != null) {
            i |= 4;
        }
        if (this.f5383l != null) {
            i |= 8;
        }
        if (this.f5384m != null) {
            i |= GLRoadGroup.m8097a(camera, acVar);
        }
        if (this.f5385n != null) {
            i |= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        }
        if (this.f5386o != null) {
            i |= Defaults.RESPONSE_BODY_LIMIT;
        }
        if (this.f5387p != null) {
            if (camera.m7443k() > 0.0f) {
                i |= AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT;
            }
            i |= AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH;
        }
        if (this.f5388q != null) {
            return i | AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD;
        }
        return i;
    }

    public final void m8142b(GLState gLState, Camera camera, ad adVar) {
        com.google.android.m4b.maps.av.ac a = adVar.m6701a();
        int b = adVar.m6704b();
        switch (b) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                GLRaster.m8084a(gLState);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                GLAreaGroup.m7816a(gLState, adVar);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                GLLineGroup.m8003a(gLState);
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                GLRoadGroup.m8102a(gLState, camera.m7444l(), this.f5392u.m5439b());
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                GLRoadGroup.m8103a(gLState, camera.m7444l(), this.f5392u.m5439b(), a);
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                camera.m7444l();
                GLRoadGroup.m8101a(gLState);
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                GLRoadGroup.m8106b(gLState, camera.m7444l(), this.f5392u.m5439b());
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                camera.m7444l();
                GLRoadGroup.m8107d(gLState);
            case HTTP.HT /*9*/:
                GLAreaGroup.m7816a(gLState, adVar);
            case HTTP.LF /*10*/:
                GLLineGroup.m8003a(gLState);
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                GLBuildingGroup.m7830a(gLState, b);
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                GLBuildingGroup.m7830a(gLState, b);
            case HTTP.CR /*13*/:
                GLTrafficRoadGroup.m8120a(gLState);
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                GLTileBounds.m8115a(gLState, adVar);
            default:
        }
    }

    public final void m8131a(GLState gLState, Camera camera, ad adVar) {
        Object obj;
        if (gLState.m7509G() <= 0 || !gLState.m7510H()) {
            obj = null;
        } else {
            obj = 1;
        }
        GL10 x = gLState.m7541x();
        x.glPushMatrix();
        if (camera.m7421a() != this.f5372C) {
            this.f5372C = camera.m7421a();
            Point c = this.f5393v.m6050c();
            if (!camera.m7436d() && camera.m7443k() == 0.0f && camera.m7442j() == 0.0f && camera.m7444l() == ((float) ((int) camera.m7444l()))) {
                camera.m7426a(c, f5362I);
                c = camera.m7435d((float) Math.round(f5362I[0]), (float) Math.round(f5362I[1]));
            }
            Transform.m7277a(gLState, camera, c, (float) this.f5393v.m6053f(), this.f5371B);
        }
        Transform.m7279a(x, this.f5371B);
        int a;
        switch (adVar.m6704b()) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                if (this.f5381j != null) {
                    for (GLRaster a2 : this.f5381j) {
                        a2.m8088a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.f5382k != null) {
                    for (GLDrawable a3 : this.f5382k) {
                        a3.m6659a(gLState, camera, adVar);
                    }
                }
                if (this.f5384m != null) {
                    for (GLRoadGroup[] gLRoadGroupArr : this.f5384m) {
                        for (GLRoadGroup a4 : r3[r1]) {
                            a4.m8109a(gLState, camera, adVar);
                        }
                    }
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.f5383l != null) {
                    for (GLLineGroup a5 : this.f5383l) {
                        a5.m8010a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                if (obj == null && this.f5384m != null) {
                    for (GLRoadGroup[] gLRoadGroupArr2 : this.f5384m) {
                        for (GLRoadGroup a42 : r3[r1]) {
                            a42.m8109a(gLState, camera, adVar);
                        }
                    }
                    break;
                }
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                if (obj == null || this.f5384m == null) {
                    if (this.f5384m != null) {
                        for (GLRoadGroup[] gLRoadGroupArr3 : this.f5384m) {
                            for (GLRoadGroup a422 : r3[r1]) {
                                a422.m8109a(gLState, camera, adVar);
                            }
                        }
                        break;
                    }
                }
                com.google.android.m4b.maps.av.ac a6 = adVar.m6701a();
                a = GLRoadGroup.m8097a(camera, a6);
                ad adVar2 = new ad(a6, 4);
                ad adVar3 = new ad(a6, 6);
                for (GLRoadGroup[] gLRoadGroupArr4 : this.f5384m) {
                    if ((a & 16) != 0) {
                        m8142b(gLState, camera, adVar2);
                        for (GLRoadGroup a7 : gLRoadGroupArr4) {
                            a7.m8109a(gLState, camera, adVar2);
                        }
                    }
                    if ((a & 32) != 0) {
                        m8142b(gLState, camera, adVar);
                        for (GLRoadGroup a72 : gLRoadGroupArr4) {
                            a72.m8109a(gLState, camera, adVar);
                        }
                    }
                    if ((a & 64) != 0) {
                        gLState.m7532o();
                        m8142b(gLState, camera, adVar3);
                        for (GLRoadGroup a722 : gLRoadGroupArr4) {
                            a722.m8109a(gLState, camera, adVar3);
                        }
                    }
                    if ((a & 384) != 0) {
                        gLState.m7531n();
                        m8142b(gLState, camera, adVar);
                        for (GLRoadGroup a7222 : gLRoadGroupArr4) {
                            a7222.m8109a(gLState, camera, adVar);
                        }
                    }
                }
                break;
                break;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                if (obj == null && this.f5384m != null) {
                    for (GLRoadGroup[] gLRoadGroupArr5 : this.f5384m) {
                        for (GLRoadGroup a4222 : r3[r1]) {
                            a4222.m8109a(gLState, camera, adVar);
                        }
                    }
                    break;
                }
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                if (obj == null && this.f5384m != null) {
                    for (GLRoadGroup[] gLRoadGroupArr6 : this.f5384m) {
                        for (GLRoadGroup a42222 : r3[r1]) {
                            a42222.m8109a(gLState, camera, adVar);
                        }
                    }
                    break;
                }
            case HTTP.HT /*9*/:
                if (this.f5385n != null) {
                    for (GLDrawable a32 : this.f5385n) {
                        a32.m6659a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case HTTP.LF /*10*/:
                if (this.f5386o != null) {
                    for (GLLineGroup a52 : this.f5386o) {
                        a52.m8010a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                if (this.f5387p != null) {
                    for (GLBuildingGroup a8 : this.f5387p) {
                        a8.m7832a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case HTTP.CR /*13*/:
                if (this.f5388q != null) {
                    for (GLTrafficRoadGroup a9 : this.f5388q) {
                        a9.m8122a(gLState, camera, adVar);
                    }
                    break;
                }
                break;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                GLTileBounds.f5350a.m8116a(gLState, camera, adVar);
                break;
        }
        x.glPopMatrix();
        if (this.f5375F != null) {
            return;
        }
        if (!gLState.m7508F() || DeviceSpecificInfo.f8006a) {
            this.f5375F = Boolean.TRUE;
            return;
        }
        boolean z = this.f5382k == null && this.f5384m == null && this.f5383l == null && this.f5385n == null && this.f5386o == null && this.f5387p == null && this.f5388q == null;
        this.f5375F = Boolean.valueOf(z);
    }

    public final ac m8139b() {
        return this.f5392u;
    }

    public final void m8141b(GLState gLState) {
        int length;
        int i = 0;
        if (this.f5381j != null) {
            for (GLRaster b : this.f5381j) {
                b.m8090b(gLState);
            }
        }
        if (this.f5382k != null) {
            for (GLDrawable b2 : this.f5382k) {
                b2.m6660b(gLState);
            }
        }
        if (this.f5383l != null) {
            for (GLLineGroup b3 : this.f5383l) {
                b3.m8012b(gLState);
            }
        }
        if (this.f5384m != null) {
            for (GLRoadGroup[] gLRoadGroupArr : this.f5384m) {
                for (GLRoadGroup b4 : r3[length]) {
                    b4.m8111b(gLState);
                }
            }
        }
        if (this.f5385n != null) {
            for (GLDrawable b22 : this.f5385n) {
                b22.m6660b(gLState);
            }
        }
        if (this.f5386o != null) {
            for (GLLineGroup b32 : this.f5386o) {
                b32.m8012b(gLState);
            }
        }
        if (this.f5387p != null) {
            for (GLBuildingGroup b5 : this.f5387p) {
                b5.m7834b(gLState);
            }
        }
        if (this.f5389r != null) {
            this.f5389r.m7904b(gLState);
            this.f5389r = null;
        }
        if (this.f5388q != null) {
            GLTrafficRoadGroup[] gLTrafficRoadGroupArr = this.f5388q;
            length = gLTrafficRoadGroupArr.length;
            while (i < length) {
                gLTrafficRoadGroupArr[i].m8124b(gLState);
                i++;
            }
        }
    }

    public final void m8144c(GLState gLState) {
        int length;
        int i = 0;
        this.f5375F = Boolean.FALSE;
        if (this.f5381j != null) {
            for (GLRaster c : this.f5381j) {
                c.m8091c(gLState);
            }
        }
        if (this.f5382k != null) {
            for (GLDrawable c2 : this.f5382k) {
                c2.m6661c(gLState);
            }
        }
        if (this.f5383l != null) {
            for (GLLineGroup c3 : this.f5383l) {
                c3.m8013c(gLState);
            }
        }
        if (this.f5384m != null) {
            for (GLRoadGroup[] gLRoadGroupArr : this.f5384m) {
                for (GLRoadGroup c4 : r3[length]) {
                    c4.m8112c(gLState);
                }
            }
        }
        if (this.f5385n != null) {
            for (GLDrawable c22 : this.f5385n) {
                c22.m6661c(gLState);
            }
        }
        if (this.f5386o != null) {
            for (GLLineGroup c32 : this.f5386o) {
                c32.m8013c(gLState);
            }
        }
        if (this.f5387p != null) {
            for (GLBuildingGroup c5 : this.f5387p) {
                c5.m7835c(gLState);
            }
        }
        if (this.f5389r != null) {
            this.f5389r.m7907c(gLState);
            this.f5389r = null;
        }
        if (this.f5388q != null) {
            GLTrafficRoadGroup[] gLTrafficRoadGroupArr = this.f5388q;
            length = gLTrafficRoadGroupArr.length;
            while (i < length) {
                gLTrafficRoadGroupArr[i].m8125c(gLState);
                i++;
            }
        }
    }

    public final boolean m8137a(Clock clock) {
        return this.f5376G >= 0 && clock.m10152b() > this.f5376G;
    }

    public final boolean m8143b(Clock clock) {
        return this.f5377H >= 0 && clock.m10152b() > this.f5377H;
    }

    public final void m8140b(long j) {
        if (j >= 0) {
            if (this.f5376G < 0 || j < this.f5376G) {
                this.f5376G = j;
                this.f5377H = Constants.MILLIS_IN_A_MINUTE + j;
            }
        }
    }

    public final boolean m8145c() {
        if (this.f5381j == null) {
            return false;
        }
        for (GLRaster c : this.f5381j) {
            if (c.m8092c()) {
                return true;
            }
        }
        return false;
    }

    public final void m8146d() {
        if (this.f5381j != null) {
            for (GLRaster e : this.f5381j) {
                e.m8093e();
            }
        }
    }

    public final void m8130a(long j) {
        if (this.f5381j != null) {
            for (GLRaster a : this.f5381j) {
                a.m8087a(3000);
            }
        }
    }

    public final void m8129a(int i, Collection<String> collection) {
        int length;
        int i2 = 0;
        collection.addAll(this.f5394w);
        if (this.f5381j != null) {
            for (GLRaster d : this.f5381j) {
                collection.addAll(d.m7812d());
            }
        }
        if (this.f5382k != null) {
            for (GLFeature d2 : this.f5382k) {
                collection.addAll(d2.m7812d());
            }
        }
        if (this.f5383l != null) {
            for (GLLineGroup d3 : this.f5383l) {
                collection.addAll(d3.m7812d());
            }
        }
        if (this.f5384m != null) {
            for (GLRoadGroup[] gLRoadGroupArr : this.f5384m) {
                for (GLRoadGroup d4 : r3[length]) {
                    collection.addAll(d4.m7812d());
                }
            }
        }
        if (this.f5385n != null) {
            for (GLFeature d22 : this.f5385n) {
                collection.addAll(d22.m7812d());
            }
        }
        if (this.f5386o != null) {
            for (GLLineGroup d32 : this.f5386o) {
                collection.addAll(d32.m7812d());
            }
        }
        if (this.f5387p != null) {
            for (GLBuildingGroup d5 : this.f5387p) {
                collection.addAll(d5.m7812d());
            }
        }
        if (this.f5388q != null) {
            GLTrafficRoadGroup[] gLTrafficRoadGroupArr = this.f5388q;
            length = gLTrafficRoadGroupArr.length;
            while (i2 < length) {
                collection.addAll(gLTrafficRoadGroupArr[i2].m7812d());
                i2++;
            }
        }
    }

    public final void m8133a(Collection<String> collection) {
    }

    public final int m8147e() {
        return this.f5395x;
    }

    public final int m8148f() {
        int i;
        int i2;
        GLLineGroup[] gLLineGroupArr;
        int i3 = 0;
        if (this.f5381j != null) {
            GLRaster[] gLRasterArr = this.f5381j;
            i = 0;
            i2 = 0;
            while (i < gLRasterArr.length) {
                int a = gLRasterArr[i].m8086a() + i2;
                i++;
                i2 = a;
            }
        } else {
            i2 = 0;
        }
        if (this.f5383l != null) {
            gLLineGroupArr = this.f5383l;
            i = 0;
            while (i < gLLineGroupArr.length) {
                a = gLLineGroupArr[i].m8009a() + i2;
                i++;
                i2 = a;
            }
        }
        if (this.f5384m != null) {
            GLRoadGroup[][] gLRoadGroupArr = this.f5384m;
            int length = gLRoadGroupArr.length;
            a = 0;
            while (a < length) {
                i = i2;
                for (GLRoadGroup a2 : gLRoadGroupArr[a]) {
                    i += a2.m8108a();
                }
                a++;
                i2 = i;
            }
        }
        if (this.f5386o != null) {
            gLLineGroupArr = this.f5386o;
            i = 0;
            while (i < gLLineGroupArr.length) {
                a = gLLineGroupArr[i].m8009a() + i2;
                i++;
                i2 = a;
            }
        }
        if (this.f5387p != null) {
            GLBuildingGroup[] gLBuildingGroupArr = this.f5387p;
            i = 0;
            while (i < gLBuildingGroupArr.length) {
                a = gLBuildingGroupArr[i].m7831a() + i2;
                i++;
                i2 = a;
            }
        }
        if (this.f5388q != null) {
            GLTrafficRoadGroup[] gLTrafficRoadGroupArr = this.f5388q;
            i = 0;
            while (i < gLTrafficRoadGroupArr.length) {
                a = gLTrafficRoadGroupArr[i].m8121a() + i2;
                i++;
                i2 = a;
            }
        }
        if (this.f5382k != null) {
            GLFeature[] gLFeatureArr = this.f5382k;
            i = 0;
            while (i < gLFeatureArr.length) {
                a = gLFeatureArr[i].m7810a() + i2;
                i++;
                i2 = a;
            }
        }
        if (this.f5385n != null) {
            GLFeature[] gLFeatureArr2 = this.f5385n;
            while (i3 < gLFeatureArr2.length) {
                i2 += gLFeatureArr2[i3].m7810a();
                i3++;
            }
        }
        return i2;
    }

    public final int m8149g() {
        int i;
        GLLineGroup[] gLLineGroupArr;
        int i2 = 0;
        int i3 = AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
        if (this.f5381j != null) {
            i3 = 272;
            GLRaster[] gLRasterArr = this.f5381j;
            i = 0;
            while (i < gLRasterArr.length) {
                int b = gLRasterArr[i].m8089b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5383l != null) {
            i3 += 16;
            gLLineGroupArr = this.f5383l;
            i = 0;
            while (i < gLLineGroupArr.length) {
                b = gLLineGroupArr[i].m8011b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5384m != null) {
            i3 += 16;
            GLRoadGroup[][] gLRoadGroupArr = this.f5384m;
            int length = gLRoadGroupArr.length;
            b = 0;
            while (b < length) {
                i = i3;
                for (GLRoadGroup b2 : gLRoadGroupArr[b]) {
                    i += b2.m8110b();
                }
                b++;
                i3 = i;
            }
        }
        if (this.f5386o != null) {
            i3 += 16;
            gLLineGroupArr = this.f5386o;
            i = 0;
            while (i < gLLineGroupArr.length) {
                b = gLLineGroupArr[i].m8011b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5387p != null) {
            i3 += 16;
            GLBuildingGroup[] gLBuildingGroupArr = this.f5387p;
            i = 0;
            while (i < gLBuildingGroupArr.length) {
                b = gLBuildingGroupArr[i].m7833b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5388q != null) {
            i3 += 16;
            GLTrafficRoadGroup[] gLTrafficRoadGroupArr = this.f5388q;
            i = 0;
            while (i < gLTrafficRoadGroupArr.length) {
                b = gLTrafficRoadGroupArr[i].m8123b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5382k != null) {
            i3 += 16;
            GLFeature[] gLFeatureArr = this.f5382k;
            i = 0;
            while (i < gLFeatureArr.length) {
                b = gLFeatureArr[i].m7811b() + i3;
                i++;
                i3 = b;
            }
        }
        if (this.f5385n != null) {
            i3 += 16;
            GLFeature[] gLFeatureArr2 = this.f5385n;
            while (i2 < gLFeatureArr2.length) {
                i = gLFeatureArr2[i2].m7811b() + i3;
                i2++;
                i3 = i;
            }
        }
        i2 = i3;
        i = this.f5373D;
        if (i == -1) {
            Iterator it = this.f5390s.iterator();
            i = 24;
            while (it.hasNext()) {
                i = ((LabelableFeature) it.next()).m8189d() + i;
            }
            this.f5373D = i;
        }
        return i2 + i;
    }
}
