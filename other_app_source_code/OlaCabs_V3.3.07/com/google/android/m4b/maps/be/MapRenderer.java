package com.google.android.m4b.maps.be;

import android.view.View;
import com.google.android.m4b.maps.be.ae.MarkerManagerImpl;
import com.google.android.m4b.maps.be.ag.MyLocationLayerImpl;
import com.google.android.m4b.maps.be.am.ProjectionDelegate;
import com.google.android.m4b.maps.p042r.ag;
import com.google.android.m4b.maps.p042r.ai;

/* renamed from: com.google.android.m4b.maps.be.q */
public interface MapRenderer {
    boolean m9474A();

    boolean m9475B();

    boolean m9476C();

    void m9477a();

    void m9478a(int i);

    void m9479a(ag agVar);

    void m9480a(ai aiVar);

    void m9481a(boolean z);

    void m9482b();

    boolean m9483b(boolean z);

    void m9484c();

    void m9485c(boolean z);

    void m9486e(boolean z);

    View m9487f();

    void m9488f(boolean z);

    bp m9489g();

    void m9490g(boolean z);

    MarkerManagerImpl m9491h();

    void m9492h(boolean z);

    ai m9493i();

    MyLocationLayerImpl m9494j();

    IndoorStateInterface m9495k();

    aq m9496l();

    MapIdleWaiter m9497m();

    boolean m9498n();

    boolean m9499o();

    boolean m9500p();

    String m9501q();

    ProjectionDelegate m9502r();

    void setPadding(int i, int i2, int i3, int i4);

    void m9503y();

    boolean m9504z();
}
