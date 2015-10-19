package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.places.e */
public class C0545e implements Creator<PlaceFilter> {
    static void m4409a(PlaceFilter placeFilter, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3827a(parcel, 1, placeFilter.f2670b, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, placeFilter.f2669a);
        C0448b.m3829a(parcel, 3, placeFilter.f2671c);
        C0448b.m3838c(parcel, 4, placeFilter.f2672d, false);
        C0448b.m3836b(parcel, 6, placeFilter.f2673e, false);
        C0448b.m3815a(parcel, a);
    }

    public PlaceFilter m4410a(Parcel parcel) {
        boolean z = false;
        List list = null;
        int b = C0447a.m3786b(parcel);
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list3 = C0447a.m3812y(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    list = C0447a.m3789c(parcel, a, zzj.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    list2 = C0447a.m3813z(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PlaceFilter(i, list3, z, list2, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public PlaceFilter[] m4411a(int i) {
        return new PlaceFilter[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4410a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4411a(i);
    }
}
