package com.google.android.m4b.maps.p060x;

import android.view.animation.LinearInterpolator;
import com.google.android.m4b.maps.an.MyLocationMarker;
import com.google.android.m4b.maps.an.Point;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.olacabs.customer.p076d.br;

/* renamed from: com.google.android.m4b.maps.x.h */
public class MyLocationMarkerAnimation {
    private final PointAnimation f8048a;
    private final FloatAnimation f8049b;
    private final IntegerAnimation f8050c;
    private final IntegerAnimation f8051d;

    public MyLocationMarkerAnimation() {
        this.f8048a = new PointAnimation(new ExpAccelerateInterpolator(0.99f));
        this.f8050c = new IntegerAnimation(new ExpAccelerateInterpolator(0.99f));
        this.f8049b = new FloatAnimation(new ConstantInterpolator(br.DEFAULT_BACKOFF_MULT));
        this.f8048a.setDuration(5000);
        this.f8049b.setDuration(5000);
        this.f8050c.setDuration(5000);
        this.f8051d = new IntegerAnimation(new LinearInterpolator());
        this.f8051d.m11590a(0);
        this.f8051d.m11590a(2);
        this.f8051d.setDuration(1000);
        this.f8051d.setRepeatCount(-1);
        this.f8051d.start();
    }

    public synchronized boolean m11593a(MyLocationMarker myLocationMarker) {
        boolean z;
        if (this.f8048a.isInitialized()) {
            Preconditions.m1817a((Object) myLocationMarker);
            myLocationMarker.m5908a((Point) this.f8048a.m11575b(), this.f8049b.m11588b(), this.f8050c.m11592b());
            myLocationMarker.m5905a((float) this.f8051d.m11592b());
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void m11594b(MyLocationMarker myLocationMarker) {
        if (!(this.f8048a.isInitialized() && Objects.m1811a(myLocationMarker.m5904a(), this.f8048a.m11571a()))) {
            this.f8048a.m11579d(myLocationMarker.m5904a());
            this.f8048a.start();
        }
        if (!(this.f8049b.isInitialized() && myLocationMarker.m5910b() == this.f8049b.m11586a())) {
            this.f8049b.m11587a(myLocationMarker.m5910b());
            this.f8049b.start();
        }
        if (!(this.f8050c.isInitialized() && myLocationMarker.m5911c() == this.f8050c.m11589a())) {
            this.f8050c.m11590a(myLocationMarker.m5911c());
            this.f8050c.start();
        }
    }
}
