package com.google.android.m4b.maps.aj;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.aj.l */
public abstract class ScaleEvent {
    private final int f3163a;

    public abstract float m5102a();

    public abstract float m5103b();

    public abstract float m5104c();

    public ScaleEvent(int i) {
        this.f3163a = i;
    }

    public final boolean m5105d() {
        return this.f3163a == 0;
    }

    public final boolean m5106e() {
        return this.f3163a == 3;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ScaleEvent{eventType=");
        switch (this.f3163a) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                stringBuilder.append("EVENT_TYPE_ON_SCALING");
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                stringBuilder.append("EVENT_TYPE_ON_SCALE_BEGIN");
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                stringBuilder.append("EVENT_TYPE_ON_SCALE_END");
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                stringBuilder.append("EVENT_TYPE_ON_TWO_FINGER_TAP");
                break;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
