package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.BitmapDescriptor;
import com.google.android.m4b.maps.model.GroundOverlayOptions;
import com.google.android.m4b.maps.model.LatLngBounds;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.be.y */
public final class MapsEngineInfocardManager {
    private final FrameLayout f6094a;
    private final Resources f6095b;
    private final Context f6096c;
    private final ai f6097d;
    private final ah f6098e;
    private final bl f6099f;
    private final bj f6100g;
    private final be f6101h;
    private final ba f6102i;
    private MapsEngineInfocardLayout f6103j;
    private cb f6104k;
    private List<MapsEngineFeatureImpl> f6105l;
    private MapsEngineFeatureImpl f6106m;

    public MapsEngineInfocardManager(Context context, Resources resources, ai aiVar, ah ahVar, bl blVar, bj bjVar, be beVar, ba baVar) {
        this.f6094a = new FrameLayout(context);
        this.f6094a.setLayoutParams(new LayoutParams(-1, -1));
        this.f6094a.setTag("GoogleMapMapsEngineInfocardManagerView");
        this.f6096c = context;
        this.f6095b = resources;
        this.f6097d = aiVar;
        this.f6098e = ahVar;
        this.f6099f = blVar;
        this.f6100g = bjVar;
        this.f6101h = beVar;
        this.f6102i = baVar;
    }

    public final void m9564a(LatLngBounds latLngBounds, List<MapsEngineFeatureImpl> list) {
        if (this.f6105l != null) {
            m9566b();
        }
        this.f6105l = list;
        this.f6104k = new cb(new GroundOverlayOptions().m10724a(new BitmapDescriptor(ObjectWrapper.m10130a(bk.m8891b(this.f6105l.size() > 1 ? R.circle_active_grouped : R.circle_active)))).m10723a(latLngBounds).m10722a((float) AutoScrollHelper.NO_MAX), this.f6098e, this.f6099f, this.f6100g, this.f6101h, this.f6102i);
        this.f6104k.m9323a(this.f6097d.m8439a(this.f6104k));
    }

    public final void m9565a(List<MapsEngineFeatureImpl> list) {
        if (this.f6105l == list) {
            if (list.size() == 1) {
                m9563a(0);
            } else {
                m9562a();
            }
        }
    }

    final void m9562a() {
        m9561e();
        this.f6103j = new MapsEngineInfolist(this.f6096c, this.f6095b, this, this.f6105l);
        this.f6103j.setTag("GoogleMapMapsEngineInfolist");
        this.f6094a.addView(this.f6103j);
    }

    final void m9563a(int i) {
        boolean z = true;
        m9561e();
        this.f6106m = (MapsEngineFeatureImpl) this.f6105l.get(i);
        Context context = this.f6096c;
        Resources resources = this.f6095b;
        if (this.f6105l == null || this.f6105l.size() <= 1) {
            z = false;
        }
        this.f6103j = new MapsEngineInfocard(context, resources, this, z, this.f6106m);
        this.f6103j.setTag("GoogleMapMapsEngineInfocard");
        this.f6094a.addView(this.f6103j);
    }

    private void m9561e() {
        if (this.f6103j != null) {
            this.f6094a.removeView(this.f6103j);
            this.f6094a.invalidate();
            this.f6106m = null;
            this.f6103j = null;
        }
    }

    public final void m9566b() {
        m9561e();
        if (this.f6104k != null) {
            this.f6104k.m9330b();
            this.f6104k = null;
        }
        this.f6105l = null;
    }

    public final MapsEngineFeatureImpl m9567c() {
        return this.f6106m;
    }

    public final View m9568d() {
        return this.f6094a;
    }
}
