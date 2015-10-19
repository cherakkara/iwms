package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.v */
public class C0586v implements Creator<StreetViewPanoramaOrientation> {
    static void m4617a(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, streetViewPanoramaOrientation.m4536a());
        C0448b.m3818a(parcel, 2, streetViewPanoramaOrientation.f2812a);
        C0448b.m3818a(parcel, 3, streetViewPanoramaOrientation.f2813b);
        C0448b.m3815a(parcel, a);
    }

    public StreetViewPanoramaOrientation m4618a(Parcel parcel) {
        float f = 0.0f;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f2 = C0447a.m3797j(parcel, a);
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
            return new StreetViewPanoramaOrientation(i, f2, f);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaOrientation[] m4619a(int i) {
        return new StreetViewPanoramaOrientation[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4618a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4619a(i);
    }
}
