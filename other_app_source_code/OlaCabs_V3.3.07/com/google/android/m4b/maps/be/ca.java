package com.google.android.m4b.maps.be;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.p025a.p026a.Strings;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.List;

/* compiled from: GoogleMapTouchHelper */
public final class ca extends ExploreByTouchHelper {
    private final MarkerManagerImpl f5976a;
    private List<ad> f5977b;

    public ca(View view, MarkerManagerImpl markerManagerImpl) {
        super(view);
        this.f5976a = markerManagerImpl;
    }

    protected final int getVirtualViewAt(float f, float f2) {
        if (this.f5977b != null) {
            for (int i = 0; i < this.f5977b.size(); i++) {
                Rect H = ((ad) this.f5977b.get(i)).m8349H();
                if (H != null && H.contains((int) f, (int) f2)) {
                    return i;
                }
            }
        }
        return ExploreByTouchHelper.INVALID_ID;
    }

    protected final void getVisibleVirtualViews(List<Integer> list) {
        this.f5977b = this.f5976a.m8392d();
        if (this.f5977b != null) {
            int size = this.f5977b.size();
            for (int i = 0; i < size; i++) {
                list.add(Integer.valueOf(i));
            }
        }
    }

    protected final boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        return false;
    }

    protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
        if (this.f5977b == null || i >= this.f5977b.size()) {
            this.f5977b = this.f5976a.m8392d();
        }
        if (this.f5977b == null || i >= this.f5977b.size()) {
            accessibilityEvent.setContentDescription(Trace.NULL);
        } else {
            accessibilityEvent.setContentDescription(m9290a((ad) this.f5977b.get(i)));
        }
    }

    public final void m9291a() {
        invalidateRoot();
        if (this.f5977b != null) {
            for (int i = 0; i < this.f5977b.size(); i++) {
                invalidateVirtualView(i);
            }
        }
    }

    private static String m9290a(ad adVar) {
        if (adVar == null) {
            return Trace.NULL;
        }
        String str = Trace.NULL;
        String o = adVar.m8379o();
        String q = adVar.m8381q();
        if (!Strings.m1866b(o)) {
            str = o + ". ";
        }
        if (Strings.m1866b(q)) {
            return str;
        }
        return str + q + ".";
    }

    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.f5977b == null || i >= this.f5977b.size()) {
            accessibilityNodeInfoCompat.setContentDescription(Trace.NULL);
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(-2, -2, -1, -1));
            return;
        }
        ad adVar = (ad) this.f5977b.get(i);
        accessibilityNodeInfoCompat.setContentDescription(m9290a(adVar));
        accessibilityNodeInfoCompat.addAction(16);
        accessibilityNodeInfoCompat.setBoundsInParent(adVar.m8349H());
        accessibilityNodeInfoCompat.setFocusable(true);
    }
}
