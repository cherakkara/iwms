package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.i */
public class C0566i implements Creator<LatLngBounds> {
    static void m4562a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, latLngBounds.m4499a());
        C0448b.m3824a(parcel, 2, latLngBounds.f2765a, i, false);
        C0448b.m3824a(parcel, 3, latLngBounds.f2766b, i, false);
        C0448b.m3815a(parcel, a);
    }

    public LatLngBounds m4563a(Parcel parcel) {
        LatLng latLng = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < b) {
            int f;
            LatLng latLng3;
            int a = C0447a.m3780a(parcel);
            LatLng latLng4;
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    latLng4 = latLng;
                    latLng = latLng2;
                    f = C0447a.m3793f(parcel, a);
                    latLng3 = latLng4;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f = i;
                    latLng4 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng3 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    latLng = latLng2;
                    f = i;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    latLng3 = latLng;
                    latLng = latLng2;
                    f = i;
                    break;
            }
            i = f;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == b) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LatLngBounds[] m4564a(int i) {
        return new LatLngBounds[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4563a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4564a(i);
    }
}
