package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.u */
public class C0585u implements Creator<StreetViewPanoramaLocation> {
    static void m4614a(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, streetViewPanoramaLocation.m4532a());
        C0448b.m3831a(parcel, 2, streetViewPanoramaLocation.f2806a, i, false);
        C0448b.m3824a(parcel, 3, streetViewPanoramaLocation.f2807b, i, false);
        C0448b.m3826a(parcel, 4, streetViewPanoramaLocation.f2808c, false);
        C0448b.m3815a(parcel, a);
    }

    public StreetViewPanoramaLocation m4615a(Parcel parcel) {
        String str = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        while (parcel.dataPosition() < b) {
            LatLng latLng2;
            StreetViewPanoramaLink[] streetViewPanoramaLinkArr2;
            int f;
            String str2;
            int a = C0447a.m3780a(parcel);
            String str3;
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str3 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = C0447a.m3793f(parcel, a);
                    str2 = str3;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f = i;
                    LatLng latLng3 = latLng;
                    streetViewPanoramaLinkArr2 = (StreetViewPanoramaLink[]) C0447a.m3788b(parcel, a, StreetViewPanoramaLink.CREATOR);
                    str2 = str;
                    latLng2 = latLng3;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    str3 = str;
                    latLng2 = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    str2 = str3;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str2 = C0447a.m3800m(parcel, a);
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    str2 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    f = i;
                    break;
            }
            i = f;
            streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
            latLng = latLng2;
            str = str2;
        }
        if (parcel.dataPosition() == b) {
            return new StreetViewPanoramaLocation(i, streetViewPanoramaLinkArr, latLng, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public StreetViewPanoramaLocation[] m4616a(int i) {
        return new StreetViewPanoramaLocation[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4615a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4616a(i);
    }
}
