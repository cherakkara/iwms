package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.e */
public class C0562e implements Creator<CircleOptions> {
    static void m4554a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, circleOptions.m4478a());
        C0448b.m3824a(parcel, 2, circleOptions.m4479b(), i, false);
        C0448b.m3817a(parcel, 3, circleOptions.m4480c());
        C0448b.m3818a(parcel, 4, circleOptions.m4481d());
        C0448b.m3819a(parcel, 5, circleOptions.m4482e());
        C0448b.m3819a(parcel, 6, circleOptions.m4483f());
        C0448b.m3818a(parcel, 7, circleOptions.m4484g());
        C0448b.m3829a(parcel, 8, circleOptions.m4485h());
        C0448b.m3815a(parcel, a);
    }

    public CircleOptions m4555a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d = C0447a.m3798k(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public CircleOptions[] m4556a(int i) {
        return new CircleOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4555a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4556a(i);
    }
}
