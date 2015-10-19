package com.google.android.m4b.maps.an;

import android.support.v4.widget.ExploreByTouchHelper;
import com.google.p025a.p028c.ar;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* compiled from: CollidableSet2D */
public final class az implements ay {
    private static Rectangle2D f3558c;
    private List<ay> f3559a;
    private Rectangle2D f3560b;

    static {
        Point point = new Point(ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID);
        f3558c = new Rectangle2D(point, point);
    }

    public az() {
        this.f3559a = ar.m2515a();
        this.f3560b = f3558c;
    }

    public az(int i) {
        this.f3559a = ar.m2527c(i);
        this.f3560b = f3558c;
    }

    public az(ay... ayVarArr) {
        this(Arrays.asList(ayVarArr));
    }

    private az(Collection<? extends ay> collection) {
        this(collection.size());
        for (ay a : collection) {
            m5728a(a);
        }
    }

    public final void m5728a(ay ayVar) {
        Rectangle2D a = ayVar.m5313a();
        if (a != f3558c) {
            if (this.f3560b == f3558c) {
                this.f3560b = new Rectangle2D(Point.m5925a(a.f3679a), Point.m5925a(a.f3680b));
            } else {
                this.f3560b.m6045a(a);
            }
            this.f3559a.add(ayVar);
        }
    }

    public final Rectangle2D m5727a() {
        return this.f3560b;
    }

    public final boolean m5729a(Point point) {
        if (!this.f3560b.m6046a(point)) {
            return false;
        }
        for (int i = 0; i < this.f3559a.size(); i++) {
            if (((ay) this.f3559a.get(i)).m5314a(point)) {
                return true;
            }
        }
        return false;
    }

    public final boolean m5730a(Region2D region2D) {
        if (!this.f3560b.m6047a(region2D.m5747a())) {
            return false;
        }
        for (int i = 0; i < this.f3559a.size(); i++) {
            if (((ay) this.f3559a.get(i)).m5315a(region2D)) {
                return true;
            }
        }
        return false;
    }
}
