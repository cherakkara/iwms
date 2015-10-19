package com.google.android.m4b.maps.bc;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.SystemClock;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.PointOfInterest;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.Road;
import com.google.android.m4b.maps.an.Style;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.an.at;
import com.google.android.m4b.maps.an.ax;
import com.google.android.m4b.maps.an.ay;
import com.google.android.m4b.maps.an.az;
import com.google.android.m4b.maps.an.bc;
import com.google.android.m4b.maps.an.bk;
import com.google.android.m4b.maps.an.bk.LabelGroup;
import com.google.android.m4b.maps.an.bm;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.TextGenerator;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture.Texture;
import com.google.android.m4b.maps.ba.GLLabel;
import com.google.android.m4b.maps.ba.GLLineLabel;
import com.google.android.m4b.maps.ba.GLPointLabel;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.ar;
import com.google.p025a.p028c.au;
import com.google.p025a.p029d.EventBus;
import com.newrelic.agent.android.api.v1.Defaults;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.bc.e */
public final class Labeler {
    private static LabelSource f5435a;
    private final List<LabelableFeature> f5436A;
    private Iterator<LabelableFeature> f5437B;
    private final Comparator<LabelableFeature> f5438C;
    private EventBus f5439D;
    private int f5440E;
    private final Map<LabelSource, ay> f5441F;
    private final TextGenerator f5442b;
    private volatile LabelTheme f5443c;
    private final Texture<Bitmap> f5444d;
    private ax f5445e;
    private Camera f5446f;
    private final GLState f5447g;
    private float f5448h;
    private at f5449i;
    private RankMergingFeatureIterator f5450j;
    private Iterator<bc> f5451k;
    private ArrayList<GLLabel> f5452l;
    private int f5453m;
    private ArrayList<GLLabel> f5454n;
    private int f5455o;
    private final Map<String, Integer> f5456p;
    private int f5457q;
    private float f5458r;
    private int f5459s;
    private int f5460t;
    private boolean f5461u;
    private boolean f5462v;
    private volatile boolean f5463w;
    private boolean f5464x;
    private boolean f5465y;
    private Set<LabelSource> f5466z;

    /* renamed from: com.google.android.m4b.maps.bc.e.a */
    static final class Labeler implements Comparator<LabelableFeature> {
        private Labeler() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return Labeler.m8191a(((LabelableFeature) obj2).m8186a()) - Labeler.m8191a(((LabelableFeature) obj).m8186a());
        }
    }

    /* renamed from: com.google.android.m4b.maps.bc.e.b */
    public static final class Labeler implements Iterator<GLLabel> {
        private final ArrayList<GLLabel> f5432a;
        private final int f5433b;
        private int f5434c;

        public final /* synthetic */ Object next() {
            return m8190a();
        }

        public Labeler(ArrayList<GLLabel> arrayList) {
            this.f5432a = arrayList;
            this.f5433b = arrayList.size();
            this.f5434c = 0;
            while (this.f5434c < this.f5433b && this.f5432a.get(this.f5434c) == null) {
                this.f5434c++;
            }
        }

        public final boolean hasNext() {
            return this.f5434c < this.f5433b;
        }

        public final GLLabel m8190a() {
            ArrayList arrayList = this.f5432a;
            int i = this.f5434c;
            this.f5434c = i + 1;
            GLLabel gLLabel = (GLLabel) arrayList.get(i);
            while (this.f5434c < this.f5433b && this.f5432a.get(this.f5434c) == null) {
                this.f5434c++;
            }
            return gLLabel;
        }

        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static {
        f5435a = new LabelSource();
    }

    public Labeler(LabelTheme labelTheme, GLState gLState, Resources resources) {
        this.f5456p = au.m2807a();
        this.f5436A = ar.m2515a();
        this.f5438C = new Labeler();
        this.f5440E = 0;
        this.f5441F = au.m2807a();
        this.f5442b = new TextGenerator(resources.getDisplayMetrics().density);
        this.f5443c = labelTheme;
        this.f5447g = gLState;
        this.f5444d = new Texture(16);
        this.f5452l = new ArrayList();
        this.f5453m = -1;
        this.f5454n = new ArrayList();
        this.f5455o = -1;
    }

    public final void m8206a() {
        for (int i = 0; i < this.f5452l.size(); i++) {
            if (((GLLabel) this.f5452l.get(i)) != null) {
                ((GLLabel) this.f5452l.get(i)).m7907c(this.f5447g);
            }
        }
        this.f5442b.m7265a();
        this.f5444d.m6231a();
    }

    public final void m8212b() {
        this.f5463w = true;
    }

    public final void m8210a(LabelTheme labelTheme) {
        if (labelTheme != this.f5443c) {
            this.f5443c = labelTheme;
            this.f5463w = true;
        }
    }

    public final void m8211a(boolean z) {
        if (z) {
            this.f5442b.m7265a();
            this.f5444d.m6231a();
            return;
        }
        this.f5442b.m7269b();
    }

    public final void m8208a(at atVar) {
        m8204e();
        as a = atVar.m5651a();
        int size = this.f5454n.size();
        for (int i = 0; i < size; i++) {
            GLLabel gLLabel = (GLLabel) this.f5454n.get(i);
            if (gLLabel != null) {
                if (gLLabel.m6672a(this.f5446f, this.f5447g) && a.m5681b(gLLabel.m7911o().m5747a())) {
                    m8202c(gLLabel);
                } else {
                    gLLabel.m7907c(this.f5447g);
                }
            }
        }
        this.f5454n.clear();
        this.f5455o = -1;
        this.f5464x = true;
        this.f5465y = true;
        this.f5462v = false;
    }

    private boolean m8196a(PointOfInterest pointOfInterest) {
        return pointOfInterest.m5968a().m5439b() == this.f5460t && this.f5461u;
    }

    public final void m8207a(int i) {
        if (this.f5452l != null) {
            Iterator it = this.f5452l.iterator();
            while (it.hasNext()) {
                GLLabel gLLabel = (GLLabel) it.next();
                if (gLLabel != null) {
                    gLLabel.m7902a(i);
                }
            }
        }
    }

    public final void m8209a(Camera camera, at atVar, int i, Iterator<bc> it, RankMergingFeatureIterator rankMergingFeatureIterator, Set<FeatureId> set, Set<LabelSource> set2, Map<LabelSource, ay> map, int i2, ai aiVar) {
        int size;
        int i3;
        long elapsedRealtime = SystemClock.elapsedRealtime() + 20;
        this.f5446f = camera;
        this.f5451k = it;
        this.f5450j = rankMergingFeatureIterator;
        this.f5449i = atVar;
        this.f5460t = i;
        this.f5466z = set2;
        this.f5445e = new ax(atVar.m5657c());
        Map map2 = this.f5441F;
        map2.clear();
        if (!map.isEmpty()) {
            size = map.size();
            map2.put(f5435a, new az(size));
            for (LabelSource put : map.keySet()) {
                map2.put(put, new az(size - 1));
            }
            for (LabelSource labelSource : map.keySet()) {
                ay ayVar = (ay) map.get(labelSource);
                for (LabelSource put2 : map2.keySet()) {
                    if (!put2.equals(labelSource)) {
                        ((az) map2.get(put2)).m5728a(ayVar);
                    }
                }
            }
        }
        float g = camera.m7439g();
        g = ((float) (camera.m7438f() * camera.m7437e())) / (g * g);
        if (g > 200000.0f) {
            g = ((g - 200000.0f) * 1.0E-4f) + 44.0f;
        } else {
            g *= 2.2E-4f;
        }
        int i4 = (int) g;
        if (i4 != this.f5457q) {
            this.f5457q = i4;
            this.f5442b.m7266a(i4 * 2);
        }
        this.f5458r = (this.f5446f.m7439g() * this.f5446f.m7439g()) * 200.0f;
        m8204e();
        this.f5436A.clear();
        this.f5437B = null;
        int size2 = this.f5454n.size();
        ArrayList arrayList = new ArrayList(size2);
        this.f5448h = this.f5446f.m7445m();
        this.f5461u = this.f5460t < ParameterManager.m6650c().m7365a(this.f5446f.m7430b(), aiVar).m7354b();
        for (i3 = 0; i3 < size2; i3++) {
            GLLabel gLLabel = (GLLabel) this.f5454n.get(i3);
            if (gLLabel != null) {
                if (!set.contains(gLLabel.m7917u().m5547d())) {
                    Object obj = this.f5448h < gLLabel.m7913q() ? null : !m8198a(gLLabel.m7912p()) ? null : (!((gLLabel.m7917u() instanceof PointOfInterest) && m8196a((PointOfInterest) gLLabel.m7917u())) && gLLabel.m7914r() > 0.0f && this.f5448h >= gLLabel.m7914r()) ? null : 1;
                    if (obj != null) {
                        if (gLLabel.m6672a(this.f5446f, this.f5447g)) {
                            if ((this.f5443c.f5429r ? this.f5449i.m5656b(gLLabel.m7911o().m5747a()) : gLLabel.m7903a(this.f5449i)) && !m8205e(gLLabel)) {
                                if (m8200b(gLLabel)) {
                                    gLLabel.m7907c(this.f5447g);
                                } else {
                                    m8202c(gLLabel);
                                }
                            }
                        }
                        arrayList.add(gLLabel);
                        gLLabel.m7907c(this.f5447g);
                    }
                }
                gLLabel.m7907c(this.f5447g);
            }
        }
        i3 = arrayList.size();
        for (size = 0; size < i3; size++) {
            GLLabel gLLabel2 = (GLLabel) arrayList.get(size);
            m8194a(gLLabel2.m7917u(), gLLabel2.m7912p(), gLLabel2.m7918v(), false);
        }
        this.f5454n.clear();
        this.f5455o = -1;
        this.f5464x = false;
        this.f5465y = false;
        this.f5462v = m8195a(elapsedRealtime);
    }

    public final void m8213b(int i) {
        long elapsedRealtime = 20 + SystemClock.elapsedRealtime();
        if (this.f5464x || this.f5465y) {
            boolean z = this.f5465y;
            m8204e();
            int size = this.f5454n.size();
            for (int i2 = 0; i2 < size; i2++) {
                GLLabel gLLabel = (GLLabel) this.f5454n.get(i2);
                if (gLLabel != null) {
                    if ((!z || (gLLabel.m7903a(this.f5449i) && !m8205e(gLLabel))) && !m8200b(gLLabel)) {
                        m8202c(gLLabel);
                    } else {
                        gLLabel.m7907c(this.f5447g);
                    }
                }
            }
            this.f5454n.clear();
            this.f5455o = -1;
            this.f5464x = false;
            this.f5465y = false;
        }
        this.f5462v = m8195a(elapsedRealtime);
    }

    public final boolean m8214c() {
        return this.f5462v;
    }

    static int m8191a(bc bcVar) {
        int i = 0;
        int i2;
        switch (bcVar.m5546b()) {
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                Road road = (Road) bcVar;
                i2 = 0;
                while (i < road.m6060c()) {
                    i2 = Math.max(i2, Labeler.m8192a(road.m6057a(i), road.m6062e()));
                    i++;
                }
                return i2;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return 0;
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                PointOfInterest pointOfInterest = (PointOfInterest) bcVar;
                return Labeler.m8192a(pointOfInterest.m5983n(), pointOfInterest.m5974e()) + Labeler.m8192a(pointOfInterest.m5982m(), pointOfInterest.m5974e());
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                bm bmVar = (bm) bcVar;
                i2 = 0;
                while (i < bmVar.m5853c()) {
                    i2 = Math.max(i2, Labeler.m8192a(bmVar.m5850a(i), bmVar.m5855e()));
                    i++;
                }
                return i2;
            default:
                return 0;
        }
    }

    private static int m8192a(bk bkVar, Style style) {
        int i = 0;
        if (bkVar == null) {
            return 0;
        }
        int f = (style == null || !style.m6098e()) ? 10 : style.m6102i().m6126f();
        int i2 = 0;
        while (i < bkVar.m5841b()) {
            LabelGroup a = bkVar.m5839a(i);
            if (a.m5827c()) {
                int f2;
                if (a.m5828d() && a.m5834j().m6098e()) {
                    f2 = a.m5834j().m6102i().m6126f();
                } else {
                    f2 = f;
                }
                i2 += f2 * a.m5833i().length();
            }
            if (a.m5826b()) {
                i2 += 8;
            }
            if (a.m5829e()) {
                i2 = (int) (((float) i2) + a.m5835k());
            }
            i++;
        }
        return i2;
    }

    public final Labeler m8215d() {
        return new Labeler(this.f5452l);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m8195a(long r8) {
        /*
        r7 = this;
        r2 = 0;
        r3 = 1;
        r0 = r7.f5454n;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.size();	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 <= 0) goto L_0x0064;
    L_0x000a:
        r0 = new java.lang.IllegalStateException;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = "Cannot run labeler loop until all previous labels have either been copied into new label table or destroyed.";
        r0.<init>(r1);	 Catch:{ RuntimeException -> 0x0012 }
        throw r0;	 Catch:{ RuntimeException -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r1.<init>(r3);
        r3 = "#:";
        r3 = r1.append(r3);
        r4 = r7.f5440E;
        r5 = r4 + 1;
        r7.f5440E = r5;
        r3 = r3.append(r4);
        r4 = " T:";
        r3 = r3.append(r4);
        r4 = java.lang.Thread.currentThread();
        r4 = r4.getName();
        r3 = r3.append(r4);
        r4 = " E:";
        r3 = r3.append(r4);
        r0 = r0.toString();
        r0 = r3.append(r0);
        r3 = " numL:";
        r0 = r0.append(r3);
        r3 = r7.f5452l;
        r3 = r3.size();
        r0.append(r3);
        r0 = "Labeler.runLabeler";
        r1 = r1.toString();
        com.google.android.m4b.maps.p058v.Util.m11555b(r0, r1);
        r0 = r2;
    L_0x0063:
        return r0;
    L_0x0064:
        r0 = r7.f5451k;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 == 0) goto L_0x0085;
    L_0x0068:
        r0 = r7.f5451k;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.hasNext();	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 == 0) goto L_0x0085;
    L_0x0070:
        r0 = r7.f5459s;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5457q;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 >= r1) goto L_0x0085;
    L_0x0076:
        r0 = r7.f5451k;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.next();	 Catch:{ RuntimeException -> 0x0012 }
        r0 = (com.google.android.m4b.maps.an.bc) r0;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = 0;
        r4 = 1;
        r5 = 1;
        r7.m8194a(r0, r1, r4, r5);	 Catch:{ RuntimeException -> 0x0012 }
        goto L_0x0064;
    L_0x0085:
        r4 = r2;
    L_0x0086:
        r0 = r7.f5437B;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 != 0) goto L_0x0092;
    L_0x008a:
        r0 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.hasNext();	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 == 0) goto L_0x0168;
    L_0x0092:
        r0 = r7.f5459s;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5457q;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 < r1) goto L_0x00ba;
    L_0x0098:
        r0 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.hasNext();	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 == 0) goto L_0x0168;
    L_0x00a0:
        r0 = r7.f5452l;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5453m;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.get(r1);	 Catch:{ RuntimeException -> 0x0012 }
        r0 = (com.google.android.m4b.maps.ba.GLLabel) r0;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.m7915s();	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m7198b();	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m8187b();	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 > r1) goto L_0x0168;
    L_0x00ba:
        if (r4 <= 0) goto L_0x00c6;
    L_0x00bc:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ RuntimeException -> 0x0012 }
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 < 0) goto L_0x00c6;
    L_0x00c4:
        r0 = r3;
        goto L_0x0063;
    L_0x00c6:
        r0 = r7.f5437B;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 != 0) goto L_0x0108;
    L_0x00ca:
        r0 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.m7196a();	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5436A;	 Catch:{ RuntimeException -> 0x0012 }
        r1.add(r0);	 Catch:{ RuntimeException -> 0x0012 }
    L_0x00d5:
        r1 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.hasNext();	 Catch:{ RuntimeException -> 0x0012 }
        if (r1 == 0) goto L_0x00f9;
    L_0x00dd:
        r1 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m7198b();	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m8187b();	 Catch:{ RuntimeException -> 0x0012 }
        r5 = r0.m8187b();	 Catch:{ RuntimeException -> 0x0012 }
        if (r1 != r5) goto L_0x00f9;
    L_0x00ed:
        r1 = r7.f5450j;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m7196a();	 Catch:{ RuntimeException -> 0x0012 }
        r5 = r7.f5436A;	 Catch:{ RuntimeException -> 0x0012 }
        r5.add(r1);	 Catch:{ RuntimeException -> 0x0012 }
        goto L_0x00d5;
    L_0x00f9:
        r0 = r7.f5436A;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5438C;	 Catch:{ RuntimeException -> 0x0012 }
        java.util.Collections.sort(r0, r1);	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r7.f5436A;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.listIterator();	 Catch:{ RuntimeException -> 0x0012 }
        r7.f5437B = r0;	 Catch:{ RuntimeException -> 0x0012 }
    L_0x0108:
        r0 = r4;
    L_0x0109:
        r1 = r7.f5437B;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.hasNext();	 Catch:{ RuntimeException -> 0x0012 }
        if (r1 == 0) goto L_0x015d;
    L_0x0111:
        r4 = r0 + 1;
        if (r0 <= 0) goto L_0x0120;
    L_0x0115:
        r0 = android.os.SystemClock.elapsedRealtime();	 Catch:{ RuntimeException -> 0x0012 }
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 < 0) goto L_0x0120;
    L_0x011d:
        r0 = r3;
        goto L_0x0063;
    L_0x0120:
        r0 = r7.f5437B;	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.next();	 Catch:{ RuntimeException -> 0x0012 }
        r0 = (com.google.android.m4b.maps.bc.LabelableFeature) r0;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5459s;	 Catch:{ RuntimeException -> 0x0012 }
        r5 = r7.f5457q;	 Catch:{ RuntimeException -> 0x0012 }
        if (r1 < r5) goto L_0x0142;
    L_0x012e:
        r1 = r7.f5452l;	 Catch:{ RuntimeException -> 0x0012 }
        r5 = r7.f5453m;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.get(r5);	 Catch:{ RuntimeException -> 0x0012 }
        r1 = (com.google.android.m4b.maps.ba.GLLabel) r1;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r1.m7915s();	 Catch:{ RuntimeException -> 0x0012 }
        r5 = r0.m8187b();	 Catch:{ RuntimeException -> 0x0012 }
        if (r1 >= r5) goto L_0x015c;
    L_0x0142:
        r1 = r0.m8186a();	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r0.m8188c();	 Catch:{ RuntimeException -> 0x0012 }
        r5 = 0;
        r6 = 1;
        r7.m8194a(r1, r0, r5, r6);	 Catch:{ RuntimeException -> 0x0012 }
        r0 = r7.f5459s;	 Catch:{ RuntimeException -> 0x0012 }
        r1 = r7.f5457q;	 Catch:{ RuntimeException -> 0x0012 }
        if (r0 <= r1) goto L_0x015a;
    L_0x0155:
        r0 = r7.f5453m;	 Catch:{ RuntimeException -> 0x0012 }
        r7.m8201c(r0);	 Catch:{ RuntimeException -> 0x0012 }
    L_0x015a:
        r0 = r4;
        goto L_0x0109;
    L_0x015c:
        r0 = r4;
    L_0x015d:
        r1 = r7.f5436A;	 Catch:{ RuntimeException -> 0x0012 }
        r1.clear();	 Catch:{ RuntimeException -> 0x0012 }
        r1 = 0;
        r7.f5437B = r1;	 Catch:{ RuntimeException -> 0x0012 }
        r4 = r0;
        goto L_0x0086;
    L_0x0168:
        r0 = r2;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.bc.e.a(long):boolean");
    }

    private void m8194a(bc bcVar, LabelSource labelSource, boolean z, boolean z2) {
        if (!m8198a(labelSource)) {
            return;
        }
        Polyline a;
        if (bcVar instanceof Road) {
            Road road = (Road) bcVar;
            a = road.m6058a();
            if (this.f5449i.m5656b(a.m6015a())) {
                Polyline a2 = m8193a(a);
                if (a2 != null && a2.m6026d() > (this.f5446f.m7450r() * this.f5446f.m7439g()) * 40.0f) {
                    for (int i = 0; i < road.m6060c(); i++) {
                        GLLabel a3 = GLPointLabel.m7922a(road, i, labelSource, a2.m6013a(0.7f), a2.m6013a((float) Defaults.ACTIVITY_TRACE_MIN_UTILIZATION), z, this.f5443c, this.f5446f, this.f5444d);
                        if (a3 == null) {
                            float f = (float) this.f5443c.f5414c;
                            float l = this.f5446f.m7444l() - 14.5f;
                            if (l > 0.0f) {
                                f = (float) this.f5443c.f5413b;
                                if (!this.f5461u) {
                                    f += l;
                                }
                            }
                            a3 = GLLineLabel.m8018a(road, labelSource, road.m6057a(i), a2, this.f5443c.f5426o ? 10 : 0, z, f * this.f5446f.m7439g(), this.f5443c.f5423l, this.f5443c.f5412a, this.f5446f, this.f5442b, this.f5443c.f5428q);
                        }
                        if (m8197a(a3) && z2) {
                            a3.m7905b(true);
                        }
                    }
                }
            }
        } else if (bcVar instanceof bm) {
            bm bmVar = (bm) bcVar;
            if (bmVar.m5853c() != 0) {
                a = bmVar.m5851a();
                if (this.f5449i.m5656b(a.m6015a())) {
                    Polyline a4 = m8193a(a);
                    if (a4 != null) {
                        r2 = GLLineLabel.m8017a(bmVar, labelSource, bmVar.m5850a(0), a4, z, GLLabel.m7895a(bmVar.m5855e(), this.f5443c.f5420i, this.f5443c.f5421j, this.f5443c.f5422k, this.f5446f.m7439g()), this.f5443c.f5419h, this.f5443c.f5423l, this.f5446f, this.f5442b, this.f5443c.f5428q);
                        if (m8197a(r2) && z2) {
                            r2.m7905b(true);
                            return;
                        }
                    }
                }
            }
            r2 = null;
            if (m8197a(r2)) {
            }
        } else if (bcVar instanceof PointOfInterest) {
            Object obj;
            PointOfInterest pointOfInterest = (PointOfInterest) bcVar;
            Point b = pointOfInterest.m5981l()[0].m5406b();
            if (this.f5449i.m5654a(b)) {
                ay b2 = m8199b(labelSource);
                if (!(b2 != null ? b2.m5314a(b) : false)) {
                    obj = 1;
                    r2 = obj == null ? (pointOfInterest.m5978i() <= this.f5448h || (pointOfInterest.m5979j() > -1.0f && pointOfInterest.m5979j() <= this.f5448h && !m8196a(pointOfInterest))) ? null : GLPointLabel.m7921a(pointOfInterest, labelSource, z, this.f5446f, this.f5444d, this.f5442b, this.f5443c, this.f5439D) : null;
                    if (m8197a(r2) && z2) {
                        r2.m7905b(true);
                        return;
                    }
                }
            }
            obj = null;
            if (obj == null) {
            }
            if (m8197a(r2)) {
            }
        }
    }

    private Polyline m8193a(Polyline polyline) {
        ArrayList arrayList = new ArrayList();
        this.f5445e.m5726a(polyline, arrayList);
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return (Polyline) arrayList.get(0);
        }
        Polyline polyline2 = (Polyline) arrayList.get(0);
        float d = polyline2.m6026d();
        int i = 1;
        Polyline polyline3 = polyline2;
        while (i < size) {
            Polyline polyline4;
            float f;
            float d2 = ((Polyline) arrayList.get(i)).m6026d();
            if (d2 > d) {
                float f2 = d2;
                polyline4 = (Polyline) arrayList.get(i);
                f = f2;
            } else {
                f = d;
                polyline4 = polyline3;
            }
            i++;
            polyline3 = polyline4;
            d = f;
        }
        return polyline3;
    }

    private boolean m8197a(GLLabel gLLabel) {
        if (gLLabel == null) {
            return false;
        }
        boolean z;
        if (this.f5456p.containsKey(gLLabel.m7916t())) {
            int intValue = ((Integer) this.f5456p.get(gLLabel.m7916t())).intValue();
            GLLabel gLLabel2 = (GLLabel) this.f5452l.get(intValue);
            if (gLLabel2 != null) {
                z = (gLLabel2 instanceof GLPointLabel) && (gLLabel instanceof GLPointLabel) && ((GLPointLabel) gLLabel).m7932w() && !((GLPointLabel) gLLabel2).m7932w();
                if (z) {
                    m8201c(intValue);
                }
            }
            gLLabel.m7907c(this.f5447g);
            return false;
        }
        gLLabel.m6672a(this.f5446f, this.f5447g);
        z = m8205e(gLLabel) || m8200b(gLLabel);
        while (z && gLLabel.m7906b(this.f5446f, this.f5447g)) {
            if (m8205e(gLLabel) || m8200b(gLLabel)) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            gLLabel.m7907c(this.f5447g);
            return false;
        }
        m8202c(gLLabel);
        return true;
    }

    private boolean m8200b(GLLabel gLLabel) {
        boolean z;
        int s = gLLabel.m7915s();
        if (gLLabel.m7918v()) {
            z = true;
        } else {
            z = false;
        }
        Region2D o = gLLabel.m7911o();
        Region2D a = o.m5747a();
        int size = this.f5452l.size();
        for (int i = 0; i < size; i++) {
            GLLabel gLLabel2 = (GLLabel) this.f5452l.get(i);
            if (gLLabel2 != null) {
                FeatureId d = gLLabel.m7917u().m5547d();
                FeatureId d2 = gLLabel2.m7917u().m5547d();
                boolean z2 = d != null && d2 != null && (d instanceof FeatureId.FeatureId) && (d2 instanceof FeatureId.FeatureId) && !FeatureId.f7848a.equals(d) && d.equals(d2);
                if (z2) {
                    if (gLLabel.m7913q() > gLLabel2.m7913q()) {
                        m8201c(i);
                    } else if (gLLabel.m7913q() < gLLabel2.m7913q()) {
                        return true;
                    }
                }
                Region2D o2 = gLLabel2.m7911o();
                if (o2.m5747a().m6047a(a) && o2.m5749a(o)) {
                    z2 = gLLabel2.m7918v();
                    if (z <= z2 && (z != z2 || s <= gLLabel2.m7915s())) {
                        return true;
                    }
                    m8201c(i);
                }
            }
        }
        return false;
    }

    private void m8202c(GLLabel gLLabel) {
        if (m8203d(gLLabel)) {
            this.f5459s++;
            if (this.f5453m < 0 || gLLabel.m7915s() < ((GLLabel) this.f5452l.get(this.f5453m)).m7915s()) {
                this.f5453m = this.f5452l.size();
            }
        }
        this.f5452l.add(gLLabel);
        this.f5456p.put(gLLabel.m7916t(), Integer.valueOf(this.f5452l.size() - 1));
    }

    private void m8201c(int i) {
        GLLabel gLLabel = (GLLabel) this.f5452l.get(i);
        if (m8203d(gLLabel)) {
            this.f5459s--;
        }
        gLLabel.m7907c(this.f5447g);
        this.f5452l.set(i, null);
        this.f5456p.remove(gLLabel.m7916t());
        if (i == this.f5453m) {
            this.f5453m = -1;
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < this.f5452l.size(); i3++) {
                gLLabel = (GLLabel) this.f5452l.get(i3);
                if (gLLabel != null && gLLabel.m7915s() < r2 && m8203d(gLLabel)) {
                    i2 = gLLabel.m7915s();
                    this.f5453m = i3;
                }
            }
        }
    }

    private boolean m8203d(GLLabel gLLabel) {
        if (gLLabel.m7910n() < this.f5458r) {
            return false;
        }
        LabelSource p = gLLabel.m7912p();
        if (p == null || !p.m8184b()) {
            return true;
        }
        return false;
    }

    private void m8204e() {
        if (this.f5463w) {
            int size = this.f5452l.size();
            for (int i = 0; i < size; i++) {
                GLLabel gLLabel = (GLLabel) this.f5452l.get(i);
                if (gLLabel != null) {
                    gLLabel.m7907c(this.f5447g);
                }
            }
            this.f5452l.clear();
            this.f5453m = -1;
            this.f5463w = false;
            this.f5464x = false;
            this.f5465y = false;
        }
        ArrayList arrayList = this.f5454n;
        this.f5454n = this.f5452l;
        this.f5452l = arrayList;
        int i2 = this.f5455o;
        this.f5455o = this.f5453m;
        this.f5453m = i2;
        this.f5459s = 0;
        this.f5456p.clear();
    }

    private boolean m8198a(LabelSource labelSource) {
        return labelSource == null || this.f5466z.contains(labelSource);
    }

    private boolean m8205e(GLLabel gLLabel) {
        ay b = m8199b(gLLabel.m7912p());
        if (b != null) {
            return b.m5315a(gLLabel.m7911o());
        }
        return false;
    }

    private ay m8199b(LabelSource labelSource) {
        if (this.f5441F.isEmpty()) {
            return null;
        }
        if (labelSource == null) {
            labelSource = f5435a;
        }
        ay ayVar = (ay) this.f5441F.get(labelSource);
        if (ayVar == null) {
            return (ay) this.f5441F.get(f5435a);
        }
        return ayVar;
    }
}
