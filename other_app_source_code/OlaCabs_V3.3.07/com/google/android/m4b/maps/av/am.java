package com.google.android.m4b.maps.av;

import android.graphics.Color;
import com.google.android.m4b.maps.ai.Polygon2dTessellator.Polygon2dTessellator;
import com.google.android.m4b.maps.ai.PolygonConverter;
import com.google.android.m4b.maps.ai.TriangleMesh2d;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Polyline;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.av.al.GLOverlay;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.ColorUtil;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.az.IndexBuffer;
import com.google.android.m4b.maps.az.VertexBuffer;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.olacabs.customer.p076d.dm;
import java.util.Collection;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GLPolygonOverlay */
public final class am extends al {
    private static final Point f4289a;
    private static final Point f4290b;
    private final List<Polyline> f4291c;
    private final Rectangle2D f4292d;
    private final List<Polyline> f4293e;
    private final Object f4294f;
    private TriangleMesh2d f4295g;
    private boolean f4296h;
    private Rectangle2D f4297i;
    private float f4298j;
    private float f4299k;
    private byte f4300l;
    private boolean f4301m;
    private boolean f4302n;
    private VertexBuffer f4303o;
    private IndexBuffer f4304p;
    private Rectangle2D f4305q;
    private int f4306r;
    private final Object f4307s;
    private int f4308t;
    private int f4309u;
    private final List<aj> f4310v;
    private boolean f4311w;
    private boolean f4312x;
    private RepaintCallback f4313y;

    /* renamed from: com.google.android.m4b.maps.av.am.a */
    class GLPolygonOverlay implements Runnable {
        private /* synthetic */ am f4288a;

        private GLPolygonOverlay(am amVar) {
            this.f4288a = amVar;
        }

        public final void run() {
            TriangleMesh2d a;
            int i = 0;
            synchronized (this.f4288a.f4294f) {
                List a2 = ar.m2516a(this.f4288a.f4293e);
            }
            try {
                a = PolygonConverter.m5015a(a2, PolygonConverter.PolygonConverter.AREA_VERIFICATION);
            } catch (Polygon2dTessellator e) {
                try {
                    List<Polyline> a3 = ar.m2516a(this.f4288a.f4291c);
                    int i2 = i;
                    for (Polyline b : a3) {
                        i2 = b.m6020b() + i2;
                    }
                    if (i2 < ActivityTrace.MAX_TRACES) {
                        a = PolygonConverter.m5015a(a3, PolygonConverter.PolygonConverter.AREA_VERIFICATION_WITH_REASON);
                    } else {
                        a = TriangleMesh2d.m5060a();
                    }
                } catch (Polygon2dTessellator e2) {
                    a = TriangleMesh2d.m5060a();
                }
            }
            synchronized (this.f4288a.f4294f) {
                this.f4288a.f4295g = a;
                this.f4288a.f4302n = true;
                this.f4288a.f4296h = false;
                if (this.f4288a.f4313y != null) {
                    this.f4288a.f4313y.m6765a(true, false);
                }
            }
        }
    }

    static {
        f4289a = new Point(-1073741824, 0);
        f4290b = new Point(1073741824, 0);
    }

    public am(Polyline polyline, List<Polyline> list, int i, int i2, int i3, boolean z) {
        int i4 = 1;
        this.f4293e = ar.m2515a();
        this.f4294f = new Object();
        this.f4295g = TriangleMesh2d.m5060a();
        this.f4296h = false;
        this.f4307s = new Object();
        this.f4311w = false;
        this.f4312x = false;
        this.f4291c = ar.m2515a();
        int g = polyline.m6030g();
        this.f4291c.add(polyline.m6025c(g));
        for (Polyline c : list) {
            this.f4291c.add(c.m6025c(g));
        }
        this.f4292d = ((Polyline) this.f4291c.get(0)).m6015a();
        this.f4309u = i;
        this.f4308t = i2;
        this.f4310v = ar.m2515a();
        for (Polyline ajVar : this.f4291c) {
            this.f4310v.add(new aj(ajVar, (float) this.f4309u, this.f4308t, null, true));
        }
        this.f4306r = i3;
        this.f4312x = true;
        this.f4295g = TriangleMesh2d.m5060a();
        StringBuilder stringBuilder = new StringBuilder();
        if (m6853e(i3)) {
            i4 = 0;
        }
        UserEventReporter.m11502a(120, "t", stringBuilder.append(i4).toString());
    }

    public final GLOverlay m6861d() {
        return GLOverlay.UNUSED;
    }

    public final void m6855a(GLState gLState, RepaintCallback repaintCallback) {
        synchronized (this.f4294f) {
            this.f4313y = repaintCallback;
        }
    }

    public final boolean m6857a(Camera camera, GLState gLState) {
        if (m6851c()) {
            boolean z;
            synchronized (this.f4307s) {
                z = this.f4301m;
                this.f4301m = false;
            }
            m6844a(camera, z);
        }
        synchronized (this.f4310v) {
            for (aj a : this.f4310v) {
                a.m6806a(camera, gLState);
            }
        }
        return true;
    }

    private void m6844a(Camera camera, boolean z) {
        Object obj;
        Rectangle2D b;
        float i;
        Collection a;
        int a2;
        int size;
        int i2;
        Object obj2 = 1;
        if (!z) {
            if (this.f4297i != null) {
                if ((m6840a(this.f4292d, this.f4297i) == null ? 1 : (byte) 0) == (m6840a(this.f4292d, m6847b(camera)) == null ? 1 : (byte) 0)) {
                    obj = (byte) 0;
                    if (obj == null) {
                        if ((camera.m7441i() >= this.f4298j / dm.DEFAULT_BACKOFF_MULT ? 1 : (byte) 0) == null) {
                            obj = (byte) 0;
                            if (obj != null) {
                                b = m6847b(camera);
                                synchronized (this.f4294f) {
                                    this.f4293e.clear();
                                    if (m6840a(this.f4292d, b) != null) {
                                        this.f4293e.addAll(this.f4291c);
                                    }
                                    this.f4297i = b;
                                }
                            }
                            if (obj == null) {
                                i = camera.m7441i();
                                if (i <= this.f4298j * dm.DEFAULT_BACKOFF_MULT && i >= this.f4298j / dm.DEFAULT_BACKOFF_MULT) {
                                    obj2 = (byte) 0;
                                }
                                if (obj2 == null) {
                                    return;
                                }
                            }
                            synchronized (this.f4294f) {
                                a = ar.m2516a(this.f4293e);
                            }
                            a2 = m6841a(((int) camera.m7444l()) + 1, this.f4312x);
                            size = a.size();
                            for (i2 = 0; i2 < size; i2++) {
                                a.set(i2, ((Polyline) a.get(i2)).m6021b((float) a2));
                            }
                            synchronized (this.f4294f) {
                                this.f4293e.clear();
                                this.f4293e.addAll(a);
                            }
                            this.f4298j = camera.m7441i();
                            synchronized (this.f4294f) {
                                this.f4296h = true;
                            }
                            new Thread(new GLPolygonOverlay()).start();
                        }
                    }
                }
            }
            obj = 1;
            if (obj == null) {
                if (camera.m7441i() >= this.f4298j / dm.DEFAULT_BACKOFF_MULT) {
                }
                if ((camera.m7441i() >= this.f4298j / dm.DEFAULT_BACKOFF_MULT ? 1 : (byte) 0) == null) {
                    obj = (byte) 0;
                    if (obj != null) {
                        b = m6847b(camera);
                        synchronized (this.f4294f) {
                            this.f4293e.clear();
                            if (m6840a(this.f4292d, b) != null) {
                                this.f4293e.addAll(this.f4291c);
                            }
                            this.f4297i = b;
                        }
                    }
                    if (obj == null) {
                        i = camera.m7441i();
                        obj2 = (byte) 0;
                        if (obj2 == null) {
                            return;
                        }
                    }
                    synchronized (this.f4294f) {
                        a = ar.m2516a(this.f4293e);
                    }
                    a2 = m6841a(((int) camera.m7444l()) + 1, this.f4312x);
                    size = a.size();
                    for (i2 = 0; i2 < size; i2++) {
                        a.set(i2, ((Polyline) a.get(i2)).m6021b((float) a2));
                    }
                    synchronized (this.f4294f) {
                        this.f4293e.clear();
                        this.f4293e.addAll(a);
                    }
                    this.f4298j = camera.m7441i();
                    synchronized (this.f4294f) {
                        this.f4296h = true;
                    }
                    new Thread(new GLPolygonOverlay()).start();
                }
            }
        }
        obj = 1;
        if (obj != null) {
            b = m6847b(camera);
            synchronized (this.f4294f) {
                this.f4293e.clear();
                if (m6840a(this.f4292d, b) != null) {
                    this.f4293e.addAll(this.f4291c);
                }
                this.f4297i = b;
            }
        }
        if (obj == null) {
            i = camera.m7441i();
            obj2 = (byte) 0;
            if (obj2 == null) {
                return;
            }
        }
        synchronized (this.f4294f) {
            a = ar.m2516a(this.f4293e);
        }
        a2 = m6841a(((int) camera.m7444l()) + 1, this.f4312x);
        size = a.size();
        for (i2 = 0; i2 < size; i2++) {
            a.set(i2, ((Polyline) a.get(i2)).m6021b((float) a2));
        }
        synchronized (this.f4294f) {
            this.f4293e.clear();
            this.f4293e.addAll(a);
        }
        this.f4298j = camera.m7441i();
        synchronized (this.f4294f) {
            this.f4296h = true;
        }
        new Thread(new GLPolygonOverlay()).start();
    }

    public final void m6854a(GLState gLState) {
        m6859b(gLState);
    }

    public final void m6859b(GLState gLState) {
        if (this.f4303o != null) {
            this.f4303o.m7734c(gLState);
            this.f4304p.m7673c(gLState);
        }
    }

    public final void m6856a(GLState gLState, Camera camera, ad adVar) {
        if (adVar.m6704b() == 0) {
            if (m6851c()) {
                boolean z;
                synchronized (this.f4307s) {
                    z = this.f4301m;
                    this.f4301m = false;
                }
                if (this.f4297i == null) {
                    m6844a(camera, z);
                }
                if (m6846a(camera)) {
                    TriangleMesh2d triangleMesh2d;
                    Rectangle2D b = m6847b(camera);
                    synchronized (this.f4294f) {
                        triangleMesh2d = this.f4295g;
                    }
                    byte a = m6840a(this.f4292d, b);
                    Object obj = (a & 1) != 0 ? 1 : null;
                    int i = obj != null ? 1 : 0;
                    Object obj2 = (a & 2) != 0 ? 1 : null;
                    if (obj2 != null) {
                        i++;
                    }
                    Object obj3 = (a & 4) != 0 ? 1 : null;
                    if (obj3 != null) {
                        i++;
                    }
                    int e = triangleMesh2d.m5070e();
                    VertexBufferInterface vertexBuffer = new VertexBuffer(e * i);
                    Object indexBuffer = new IndexBuffer(i * (triangleMesh2d.m5069d() * 3));
                    i = 0;
                    Point c = b.m6050c();
                    int f = b.m6053f();
                    if (obj != null) {
                        PolygonConverter.m5016a(triangleMesh2d, indexBuffer, 0);
                        PolygonConverter.m5017a(triangleMesh2d, vertexBuffer, c, f);
                        i = 1;
                    }
                    if (obj2 != null) {
                        PolygonConverter.m5016a(triangleMesh2d, indexBuffer, e * i);
                        PolygonConverter.m5017a(triangleMesh2d, vertexBuffer, c.m5957e(f4289a), f);
                        i++;
                    }
                    if (obj3 != null) {
                        PolygonConverter.m5016a(triangleMesh2d, indexBuffer, i * e);
                        PolygonConverter.m5017a(triangleMesh2d, vertexBuffer, c.m5957e(f4290b), f);
                    }
                    this.f4303o = vertexBuffer;
                    this.f4304p = indexBuffer;
                    this.f4305q = new Rectangle2D(b.m6050c(), b.m6051d());
                    this.f4299k = camera.m7441i();
                    this.f4300l = a;
                }
                GL10 x = gLState.m7541x();
                x.glPushMatrix();
                if (this.f4303o != null && this.f4303o.m7725a() > 0) {
                    GL10 x2 = gLState.m7541x();
                    Transform.m7280b(gLState, camera, this.f4305q.m6050c(), (float) this.f4305q.m6053f());
                    x2.glBlendFunc(1, 771);
                    this.f4303o.m7736d(gLState);
                    synchronized (this.f4307s) {
                        ColorUtil.m7485a(x2, this.f4306r);
                    }
                    this.f4304p.m7665a(gLState, 4);
                }
                x.glPopMatrix();
            }
            gLState.m7503A();
            synchronized (this.f4310v) {
                for (aj ajVar : this.f4310v) {
                    gLState.m7543z();
                    ajVar.m6805a(gLState, camera, adVar);
                    gLState.m7503A();
                }
            }
            gLState.m7543z();
        }
    }

    public final boolean m6863e() {
        boolean z;
        for (aj e : this.f4310v) {
            if (!e.m6681e()) {
                return false;
            }
        }
        synchronized (this.f4294f) {
            if (this.f4296h || this.f4302n) {
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    private static boolean m6853e(int i) {
        return Color.alpha(i) == 0;
    }

    private boolean m6846a(Camera camera) {
        synchronized (this.f4294f) {
            if (this.f4302n) {
                this.f4302n = false;
                return true;
            }
            float i = camera.m7441i();
            if (this.f4300l != m6840a(this.f4292d, m6847b(camera)) || i > this.f4299k * 1.25f || i < this.f4299k / 1.25f) {
                return true;
            }
            return false;
        }
    }

    private boolean m6851c() {
        boolean z;
        synchronized (this.f4307s) {
            z = !m6853e(this.f4306r);
        }
        return z;
    }

    private static byte m6840a(Rectangle2D rectangle2D, Rectangle2D rectangle2D2) {
        byte b = (byte) 0;
        if (rectangle2D2.m6047a((Region2D) rectangle2D)) {
            b = (byte) 1;
        }
        Point point = f4290b;
        if (new Rectangle2D(rectangle2D2.m6050c().m5957e(point), rectangle2D2.m6051d().m5957e(point)).m6047a((Region2D) rectangle2D)) {
            b = (byte) (b | 4);
        }
        if (new Rectangle2D(rectangle2D2.m6050c().m5959f(point), rectangle2D2.m6051d().m5959f(point)).m6047a((Region2D) rectangle2D)) {
            return (byte) (b | 2);
        }
        return b;
    }

    private static Rectangle2D m6847b(Camera camera) {
        Point point;
        Point point2;
        Rectangle2D b = camera.m7453u().m5667b();
        int f = b.m6053f();
        int g = b.m6054g();
        if (f > 119304647 || g > 119304647) {
            point = new Point(b.m6052e().m5958f() - 536870912, -536870912);
            point2 = new Point((b.m6052e().m5958f() + 536870912) - 1, 536870911);
        } else {
            Point point3 = new Point(f * 4, g * 4);
            point = b.m6050c().m5959f(point3);
            point2 = b.m6051d().m5957e(point3);
        }
        return new Rectangle2D(point, point2);
    }

    static int m6841a(int i, boolean z) {
        if (z) {
            return ((1 << (30 - i)) / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH) / 2;
        }
        return (2 << (30 - i)) / AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH;
    }

    public final void m6858b(int i) {
        this.f4308t = i;
        synchronized (this.f4310v) {
            for (aj b : this.f4310v) {
                b.m6807b(i);
            }
        }
    }

    public final void m6860c(int i) {
        synchronized (this.f4307s) {
            if (m6853e(this.f4306r) && !m6853e(i)) {
                synchronized (this.f4294f) {
                    this.f4295g = TriangleMesh2d.m5060a();
                    this.f4302n = true;
                }
                this.f4301m = true;
            }
            this.f4306r = i;
        }
    }

    public final void m6862d(int i) {
        this.f4309u = i;
        synchronized (this.f4310v) {
            for (aj a : this.f4310v) {
                a.m6803a((float) i);
            }
        }
    }
}
