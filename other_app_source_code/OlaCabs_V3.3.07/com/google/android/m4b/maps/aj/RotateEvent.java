package com.google.android.m4b.maps.aj;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.aj.j */
public abstract class RotateEvent {
    private final int f3161a;

    public abstract float m5094a();

    public abstract void m5095a(float f, float f2);

    public abstract float m5096b();

    public abstract float m5097c();

    public RotateEvent(int i) {
        this.f3161a = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RotateEvent{eventType=");
        switch (this.f3161a) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                stringBuilder.append("EVENT_TYPE_ON_ROTATE");
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                stringBuilder.append("EVENT_TYPE_ON_ROTATE_BEGIN");
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                stringBuilder.append("EVENT_TYPE_ON_ROTATE_END");
                break;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
