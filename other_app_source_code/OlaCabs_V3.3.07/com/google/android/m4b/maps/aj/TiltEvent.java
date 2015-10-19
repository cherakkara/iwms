package com.google.android.m4b.maps.aj;

import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.aj.p */
public abstract class TiltEvent {
    private final int f3165a;

    public abstract float m5110a();

    public TiltEvent(int i) {
        this.f3165a = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TiltEvent{eventType=");
        switch (this.f3165a) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                stringBuilder.append("EVENT_TYPE_ON_TILTING");
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                stringBuilder.append("EVENT_TYPE_ON_TILT_BEGIN");
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                stringBuilder.append("EVENT_TYPE_ON_TILT_END");
                break;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
