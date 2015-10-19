package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.maps.model.q */
public class C0581q implements Creator<PolylineOptions> {
    static void m4604a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, polylineOptions.m4523a());
        C0448b.m3838c(parcel, 2, polylineOptions.m4524b(), false);
        C0448b.m3818a(parcel, 3, polylineOptions.m4525c());
        C0448b.m3819a(parcel, 4, polylineOptions.m4526d());
        C0448b.m3818a(parcel, 5, polylineOptions.m4527e());
        C0448b.m3829a(parcel, 6, polylineOptions.m4528f());
        C0448b.m3829a(parcel, 7, polylineOptions.m4529g());
        C0448b.m3815a(parcel, a);
    }

    public PolylineOptions m4605a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = C0447a.m3789c(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public PolylineOptions[] m4606a(int i) {
        return new PolylineOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4605a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4606a(i);
    }
}
