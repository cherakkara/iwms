package com.google.android.m4b.maps.bf;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.TextView;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager.ServerControlledParametersManager;
import com.google.android.m4b.maps.aa.VectorMapsParameters;
import com.google.android.m4b.maps.ak.ImageRequest;
import com.google.android.m4b.maps.ak.StringRequest;
import com.google.android.m4b.maps.bh.DummyLayerDescription;
import com.google.android.m4b.maps.bh.DummyPaintStyleOptions;
import com.google.android.m4b.maps.bh.DummyRegionDescription;
import com.google.android.m4b.maps.bh.DummyStylerDescription;
import com.google.android.m4b.maps.cl.Point;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p038a.RequestQueue;
import com.google.android.m4b.maps.p038a.Response.Response;
import com.google.android.m4b.maps.p038a.VolleyError;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p061y.ConversionUtils;
import com.google.p025a.p026a.Joiner;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p026a.Strings;
import com.google.p025a.p030e.BaseEncoding;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Calendar;

/* renamed from: com.google.android.m4b.maps.bf.a */
class BaseMapFetcherLite {
    private static final Response f6115a;
    private final BaseMapRendererLite f6116b;
    private final TextView f6117c;
    private final RequestQueue f6118d;
    private final CameraManagerLite f6119e;
    private final Calendar f6120f;
    private final boolean f6121g;
    private int f6122h;
    private ProtoBuf f6123i;
    private String f6124j;
    private ImageRequest f6125k;
    private StringRequest f6126l;
    private ProjectionLite f6127m;
    private Bitmap f6128n;
    private String f6129o;

    /* renamed from: com.google.android.m4b.maps.bf.a.1 */
    static class BaseMapFetcherLite implements Response {
        BaseMapFetcherLite() {
        }

        public final void m9571a(VolleyError volleyError) {
            Strings.m1866b(volleyError.getMessage());
        }
    }

    /* renamed from: com.google.android.m4b.maps.bf.a.2 */
    class BaseMapFetcherLite implements ServerControlledParametersManager {
        private /* synthetic */ BaseMapFetcherLite f6112a;

        BaseMapFetcherLite(BaseMapFetcherLite baseMapFetcherLite) {
            this.f6112a = baseMapFetcherLite;
        }

        public final void m9572a() {
            this.f6112a.m9588a(com.google.android.m4b.maps.aa.ServerControlledParametersManager.m4808c(), com.google.android.m4b.maps.aa.ServerControlledParametersManager.m4812e());
        }
    }

    /* renamed from: com.google.android.m4b.maps.bf.a.3 */
    class BaseMapFetcherLite implements Response<Bitmap> {
        private /* synthetic */ BaseMapFetcherLite f6113a;

        BaseMapFetcherLite(BaseMapFetcherLite baseMapFetcherLite) {
            this.f6113a = baseMapFetcherLite;
        }

        public final /* bridge */ /* synthetic */ void m9573a(Object obj) {
            this.f6113a.f6128n = (Bitmap) obj;
            BaseMapFetcherLite.m9579a(this.f6113a);
        }
    }

    /* renamed from: com.google.android.m4b.maps.bf.a.4 */
    class BaseMapFetcherLite implements Response<String> {
        private /* synthetic */ BaseMapFetcherLite f6114a;

        BaseMapFetcherLite(BaseMapFetcherLite baseMapFetcherLite) {
            this.f6114a = baseMapFetcherLite;
        }

        public final /* bridge */ /* synthetic */ void m9574a(Object obj) {
            this.f6114a.f6129o = this.f6114a.m9585a((String) obj);
            BaseMapFetcherLite.m9579a(this.f6114a);
        }
    }

    static /* synthetic */ void m9579a(BaseMapFetcherLite baseMapFetcherLite) {
        if (baseMapFetcherLite.f6128n == null) {
            return;
        }
        if (!baseMapFetcherLite.f6121g || baseMapFetcherLite.f6129o != null) {
            baseMapFetcherLite.f6116b.m9591a(baseMapFetcherLite.f6128n, baseMapFetcherLite.f6127m);
            if (baseMapFetcherLite.f6129o != null) {
                baseMapFetcherLite.f6117c.setText(baseMapFetcherLite.f6129o);
            }
        }
    }

    static {
        BaseMapFetcherLite.class.getSimpleName();
        f6115a = new BaseMapFetcherLite();
    }

    BaseMapFetcherLite(BaseMapRendererLite baseMapRendererLite, TextView textView, RequestQueue requestQueue, CameraManagerLite cameraManagerLite, Calendar calendar, boolean z) {
        this.f6122h = 1;
        this.f6116b = baseMapRendererLite;
        this.f6117c = textView;
        this.f6118d = requestQueue;
        this.f6119e = cameraManagerLite;
        this.f6120f = calendar;
        this.f6121g = z;
        com.google.android.m4b.maps.aa.ServerControlledParametersManager.m4796a(new BaseMapFetcherLite(this));
    }

    final void m9588a(VectorMapsParameters vectorMapsParameters, ProtoBuf protoBuf) {
        if (Strings.m1866b(vectorMapsParameters.m4825f())) {
            this.f6124j = "https://www.google.com/maps/vt/";
        } else {
            this.f6124j = vectorMapsParameters.m4825f();
        }
        this.f6123i = protoBuf.m10211g(1);
        m9586a();
    }

    final void m9587a(int i) {
        this.f6122h = i;
        m9586a();
    }

    final void m9586a() {
        Object obj = 1;
        ProjectionLite b = this.f6119e.m9608b();
        Object obj2 = (b.f6194f <= 0 || b.f6195g <= 0) ? null : 1;
        if (obj2 == null || this.f6123i == null || Strings.m1866b(this.f6124j)) {
            obj = null;
        }
        if (obj != null) {
            ProtoBuf g = this.f6123i.m10211g(10);
            this.f6127m = new ProjectionLite(b.f6193e, b.f6194f, b.f6195g, (double) g.m10211g(5).m10209f(5), b.f6189a, b.f6190b, b.f6191c, b.f6192d);
            if (this.f6122h == 0) {
                if (this.f6125k != null) {
                    m9583b();
                }
                this.f6116b.m9591a(null, this.f6127m);
                this.f6117c.setText(Trace.NULL);
                return;
            }
            try {
                ProtoBuf a = ProtoBufUtil.m10225a(g);
                BaseMapFetcherLite.m9581a(a, this.f6127m);
                BaseMapFetcherLite.m9580a(a, this.f6122h);
                String a2 = m9578a(a);
                if (this.f6125k != null) {
                    if (!a2.equals(this.f6125k.m4707c())) {
                        m9583b();
                    } else {
                        return;
                    }
                }
                this.f6125k = (ImageRequest) this.f6118d.m4721a(new ImageRequest(a2, new BaseMapFetcherLite(this), 0, 0, Config.ARGB_8888, f6115a));
                if (this.f6121g) {
                    a = BaseMapFetcherLite.m9582b(g);
                    BaseMapFetcherLite.m9581a(a, this.f6127m);
                    BaseMapFetcherLite.m9580a(a, this.f6122h);
                    this.f6126l = (StringRequest) this.f6118d.m4721a(new StringRequest(m9578a(a), new BaseMapFetcherLite(this), f6115a));
                }
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    final String m9585a(String str) {
        return ("\u00a9" + this.f6120f.get(1) + " " + Joiner.m1780a(", ").m1788a(str.replace("[", Trace.NULL).replace("]", Trace.NULL).replace("\"", Trace.NULL).split(","))).trim();
    }

    private void m9583b() {
        this.f6125k.m4710f();
        this.f6125k = null;
        this.f6128n = null;
        if (this.f6126l != null) {
            this.f6126l.m4710f();
            this.f6126l = null;
            this.f6129o = null;
        }
    }

    private String m9578a(ProtoBuf protoBuf) {
        try {
            return this.f6124j + "bpb=" + BaseEncoding.m2984a().m2987a(protoBuf.m10206d());
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private static void m9581a(ProtoBuf protoBuf, ProjectionLite projectionLite) {
        int i = (int) projectionLite.f6193e.f7530b;
        ProtoBuf protoBuf2 = new ProtoBuf(DummyRegionDescription.f6242a);
        ProtoBuf protoBuf3 = new ProtoBuf(DummyRegionDescription.f6243b);
        protoBuf3.m10210f(2, i);
        ProtoBuf protoBuf4 = new ProtoBuf(DummyRegionDescription.f6244c);
        protoBuf4.m10210f(1, BaseMapFetcherLite.m9575a(projectionLite.f6194f, projectionLite.f6196h));
        protoBuf4.m10210f(2, BaseMapFetcherLite.m9575a(projectionLite.f6195g, projectionLite.f6196h));
        protoBuf3.m10196b(1, protoBuf4);
        ProtoBuf protoBuf5 = new ProtoBuf(Point.f7287a);
        LatLng a = (projectionLite.f6189a == projectionLite.f6191c && projectionLite.f6190b == projectionLite.f6192d) ? projectionLite.f6193e.f7529a : projectionLite.m9698a(new android.graphics.Point(projectionLite.f6194f / 2, projectionLite.f6195g / 2));
        protoBuf5.m10210f(1, ConversionUtils.m11639b(a.f7554a));
        protoBuf5.m10210f(2, ConversionUtils.m11639b(a.f7555b));
        protoBuf3.m10196b(3, protoBuf5);
        protoBuf2.m10196b(4, protoBuf3);
        protoBuf.m10190a(1, protoBuf2);
    }

    private static void m9580a(ProtoBuf protoBuf, int i) {
        Preconditions.m1823a(i != 0, (Object) "Shouldn't fetch for MAP_TYPE_NONE");
        ProtoBuf g = protoBuf.m10211g(3);
        if (g == null) {
            g = new ProtoBuf(DummyPaintStyleOptions.f6236a);
        }
        ProtoBuf protoBuf2;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                BaseMapFetcherLite.m9584b(protoBuf, 1);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                BaseMapFetcherLite.m9584b(protoBuf, 4);
                BaseMapFetcherLite.m9584b(protoBuf, 0);
                protoBuf2 = new ProtoBuf(DummyStylerDescription.f6248a);
                protoBuf2.m10210f(1, 5);
                g.m10190a(12, protoBuf2);
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                BaseMapFetcherLite.m9584b(protoBuf, 1);
                BaseMapFetcherLite.m9584b(protoBuf, 0);
                protoBuf2 = new ProtoBuf(DummyStylerDescription.f6248a);
                protoBuf2.m10210f(1, 4);
                g.m10190a(12, protoBuf2);
            default:
                BaseMapFetcherLite.m9584b(protoBuf, 0);
        }
    }

    private static ProtoBuf m9582b(ProtoBuf protoBuf) {
        try {
            ProtoBuf a = ProtoBufUtil.m10225a(protoBuf);
            a.m10208e(5, 0);
            a.m10210f(4, 5);
            return a;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private static int m9575a(int i, double d) {
        return Math.min((int) Math.ceil(((double) i) / d), (int) Math.floor(2048.0d / d));
    }

    private static void m9584b(ProtoBuf protoBuf, int i) {
        ProtoBuf protoBuf2 = new ProtoBuf(DummyLayerDescription.f6208a);
        protoBuf2.m10210f(1, i);
        protoBuf2.m10210f(3, 999999);
        protoBuf.m10190a(2, protoBuf2);
    }
}
