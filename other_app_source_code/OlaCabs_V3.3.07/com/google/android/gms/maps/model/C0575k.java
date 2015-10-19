package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.k */
public class C0575k implements Creator<LatLng> {
    static void m4592a(LatLng latLng, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, latLng.m4498a());
        C0448b.m3817a(parcel, 2, latLng.f2762a);
        C0448b.m3817a(parcel, 3, latLng.f2763b);
        C0448b.m3815a(parcel, a);
    }

    public LatLng m4593a(Parcel parcel) {
        double d = 0.0d;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    d2 = C0447a.m3798k(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d = C0447a.m3798k(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new LatLng(i, d2, d);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LatLng[] m4594a(int i) {
        return new LatLng[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4593a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4594a(i);
    }
}
