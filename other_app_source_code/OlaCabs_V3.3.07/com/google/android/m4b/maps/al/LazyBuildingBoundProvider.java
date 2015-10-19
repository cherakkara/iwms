package com.google.android.m4b.maps.al;

import com.google.android.m4b.maps.al.BuildingBoundProvider.BuildingBoundProvider;
import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.Rectangle2D;
import com.google.android.m4b.maps.an.Region2D;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.as;
import com.google.android.m4b.maps.at.Resource;
import com.google.android.m4b.maps.at.ResourceListener;
import com.google.android.m4b.maps.at.ResourceManager;
import com.google.android.m4b.maps.p039o.Task;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import com.google.android.m4b.maps.p040u.TaskRunnerManager;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p028c.ai;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.al.j */
public final class LazyBuildingBoundProvider implements BuildingBoundProvider, ResourceListener {
    private final String f3338b;
    private final Point f3339c;
    private final Point f3340d;
    private volatile boolean f3341e;
    private volatile Point f3342f;
    private final CopyOnWriteArrayList<BuildingBoundProvider> f3343g;
    private volatile LazyBuildingBoundProvider f3344h;

    /* renamed from: com.google.android.m4b.maps.al.j.1 */
    class LazyBuildingBoundProvider extends Task {
        private /* synthetic */ LazyBuildingBoundProvider f3334b;

        LazyBuildingBoundProvider(LazyBuildingBoundProvider lazyBuildingBoundProvider, TaskRunner taskRunner) {
            this.f3334b = lazyBuildingBoundProvider;
            super(taskRunner);
        }

        public final void m5368f() {
            LazyBuildingBoundProvider.m5371a(this.f3334b);
        }
    }

    /* renamed from: com.google.android.m4b.maps.al.j.a */
    static class LazyBuildingBoundProvider {
        final IndoorBuildingBoundsHelper f3335a;
        final as f3336b;
        final as f3337c;

        LazyBuildingBoundProvider() {
            this.f3335a = new IndoorBuildingBoundsHelper();
            this.f3336b = as.m5674a(new Rectangle2D(new Point(), new Point()));
            this.f3337c = as.m5674a(new Rectangle2D(new Point(), new Point()));
        }

        LazyBuildingBoundProvider(IndoorBuildingBoundsHelper indoorBuildingBoundsHelper, as asVar, as asVar2) {
            this.f3335a = indoorBuildingBoundsHelper;
            this.f3336b = asVar;
            this.f3337c = asVar2;
        }
    }

    static /* synthetic */ void m5371a(LazyBuildingBoundProvider lazyBuildingBoundProvider) {
        ResourceManager c = ResourceManager.m6599c();
        if (c != null) {
            lazyBuildingBoundProvider.m5374a(c.m6600a(lazyBuildingBoundProvider.f3338b, (ResourceListener) lazyBuildingBoundProvider, false));
        }
    }

    private LazyBuildingBoundProvider(String str, Point point, Point point2) {
        this.f3338b = str;
        this.f3339c = point;
        if (point.m5958f() < point2.m5958f() || point.m5960g() < point2.m5960g()) {
            this.f3340d = this.f3339c;
        } else {
            this.f3340d = point2;
        }
        this.f3343g = new CopyOnWriteArrayList();
        this.f3342f = new Point();
        this.f3344h = new LazyBuildingBoundProvider();
    }

    public LazyBuildingBoundProvider(String str) {
        this(str, new Point(5000000, 5000000), new Point(4000000, 4000000));
    }

    private static as m5370a(Point point, Point point2) {
        Point g = ac.m5429b(15, point.m5959f(point2)).m5444g();
        Point h = ac.m5429b(15, point.m5957e(point2)).m5445h();
        if (g.m5958f() > h.m5958f()) {
            h = h.m5957e(new Point(1073741824, 0));
        }
        return as.m5674a(new Rectangle2D(g, h));
    }

    public final Collection<BuildingBound> m5372a(ac acVar) {
        if (acVar.m5439b() < 15) {
            return ai.m2296g();
        }
        Region2D i = acVar.m5446i();
        LazyBuildingBoundProvider lazyBuildingBoundProvider = this.f3344h;
        if (!(this.f3341e || lazyBuildingBoundProvider.f3337c.m5679a(i))) {
            Point e = i.m6052e();
            synchronized (this) {
                if (this.f3341e) {
                } else {
                    this.f3341e = true;
                    this.f3342f = e;
                    new LazyBuildingBoundProvider(this, TaskRunnerManager.m11489a()).m4754d();
                }
            }
        }
        if (lazyBuildingBoundProvider.f3336b.m5679a(i)) {
            return lazyBuildingBoundProvider.f3335a.m5289a(acVar);
        }
        return a;
    }

    public final boolean m5375a(FeatureId featureId) {
        return this.f3344h.f3335a.m5291a(featureId);
    }

    public final void m5373a(BuildingBoundProvider buildingBoundProvider) {
        if (buildingBoundProvider != null) {
            this.f3343g.add(buildingBoundProvider);
        }
    }

    public final void m5376b(BuildingBoundProvider buildingBoundProvider) {
        if (buildingBoundProvider != null) {
            this.f3343g.remove(buildingBoundProvider);
        }
    }

    public final void m5374a(Resource resource) {
        if (resource != null) {
            synchronized (resource) {
                if (this.f3341e) {
                    if (resource.m6586b()) {
                        byte[] d = resource.m6588d();
                        if (d != null && d.length > 0) {
                            try {
                                as a = LazyBuildingBoundProvider.m5370a(this.f3342f, this.f3339c);
                                this.f3344h = new LazyBuildingBoundProvider(IndoorBuildingBoundsHelper.m5288a(new InputStreamReader(new ByteArrayInputStream(d), HTTP.UTF_8), a), a, LazyBuildingBoundProvider.m5370a(this.f3342f, this.f3340d));
                            } catch (Throwable e) {
                                ExceptionReporter.m11467a("LazyBuildingBoundProvider", e);
                            }
                        }
                        this.f3341e = false;
                        Iterator it = this.f3343g.iterator();
                        while (it.hasNext()) {
                            ((BuildingBoundProvider) it.next()).m5273a();
                        }
                    }
                    return;
                }
            }
        }
    }
}
