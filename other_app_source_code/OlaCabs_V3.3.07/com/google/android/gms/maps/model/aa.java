package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

public class aa implements Creator<VisibleRegion> {
    static void m4546a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, visibleRegion.m4544a());
        C0448b.m3824a(parcel, 2, visibleRegion.f2828a, i, false);
        C0448b.m3824a(parcel, 3, visibleRegion.f2829b, i, false);
        C0448b.m3824a(parcel, 4, visibleRegion.f2830c, i, false);
        C0448b.m3824a(parcel, 5, visibleRegion.f2831d, i, false);
        C0448b.m3824a(parcel, 6, visibleRegion.f2832e, i, false);
        C0448b.m3815a(parcel, a);
    }

    public VisibleRegion m4547a(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng4 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng3 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    latLng2 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    latLngBounds = (LatLngBounds) C0447a.m3782a(parcel, a, LatLngBounds.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public VisibleRegion[] m4548a(int i) {
        return new VisibleRegion[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4547a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4548a(i);
    }
}
