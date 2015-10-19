package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.c */
public class C0560c implements Creator<CameraPosition> {
    static void m4550a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, cameraPosition.m4477a());
        C0448b.m3824a(parcel, 2, cameraPosition.f2737a, i, false);
        C0448b.m3818a(parcel, 3, cameraPosition.f2738b);
        C0448b.m3818a(parcel, 4, cameraPosition.f2739c);
        C0448b.m3818a(parcel, 5, cameraPosition.f2740d);
        C0448b.m3815a(parcel, a);
    }

    public CameraPosition m4551a(Parcel parcel) {
        float f = 0.0f;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    f3 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public CameraPosition[] m4552a(int i) {
        return new CameraPosition[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4551a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4552a(i);
    }
}
