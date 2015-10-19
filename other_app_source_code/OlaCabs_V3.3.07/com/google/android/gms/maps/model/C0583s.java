package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.s */
public class C0583s implements Creator<StreetViewPanoramaCamera> {
    static void m4608a(StreetViewPanoramaCamera streetViewPanoramaCamera, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, streetViewPanoramaCamera.m4530a());
        C0448b.m3818a(parcel, 2, streetViewPanoramaCamera.f2798a);
        C0448b.m3818a(parcel, 3, streetViewPanoramaCamera.f2799b);
        C0448b.m3818a(parcel, 4, streetViewPanoramaCamera.f2800c);
        C0448b.m3815a(parcel, a);
    }

    public StreetViewPanoramaCamera m4609a(Parcel parcel) {
        float f = 0.0f;
        int b = C0447a.m3786b(parcel);
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
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
                    f3 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaCamera(i, f2, f3, f);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaCamera[] m4610a(int i) {
        return new StreetViewPanoramaCamera[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4609a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4610a(i);
    }
}
