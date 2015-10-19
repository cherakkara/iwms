package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.t */
public class C0584t implements Creator<StreetViewPanoramaLink> {
    static void m4611a(StreetViewPanoramaLink streetViewPanoramaLink, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, streetViewPanoramaLink.m4531a());
        C0448b.m3826a(parcel, 2, streetViewPanoramaLink.f2803a, false);
        C0448b.m3818a(parcel, 3, streetViewPanoramaLink.f2804b);
        C0448b.m3815a(parcel, a);
    }

    public StreetViewPanoramaLink m4612a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaLink(i, str, f);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaLink[] m4613a(int i) {
        return new StreetViewPanoramaLink[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4612a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4613a(i);
    }
}
