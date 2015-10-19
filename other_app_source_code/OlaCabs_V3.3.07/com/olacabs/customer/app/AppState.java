package com.olacabs.customer.app;

import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.entity.ContentLengthStrategy;

/* renamed from: com.olacabs.customer.app.a */
public enum AppState {
    NEED_UPDATE(-1),
    HOME_PAGE(0),
    BEFORE_BOOKING(1),
    BOOKING_CONFIRMED(2),
    REACHED_FOR_PICKUP(3),
    IN_TRIP(4),
    TRIP_END(5),
    LOCAL_TAXI_CANCELLATION(6),
    CITY_TAXI_CANCELLATION(7);
    
    private int f8982j;

    private AppState(int i) {
        this.f8982j = i;
    }

    public int m12882a() {
        return this.f8982j;
    }

    public static AppState m12881a(int i) {
        switch (i) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                return NEED_UPDATE;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return BEFORE_BOOKING;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return BOOKING_CONFIRMED;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return REACHED_FOR_PICKUP;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return IN_TRIP;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return TRIP_END;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return LOCAL_TAXI_CANCELLATION;
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return CITY_TAXI_CANCELLATION;
            default:
                return BEFORE_BOOKING;
        }
    }
}
