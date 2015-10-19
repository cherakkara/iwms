package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.places.g */
public class C0547g implements Creator<zzj> {
    static void m4415a(zzj com_google_android_gms_location_places_zzj, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_location_places_zzj.f2696f, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_places_zzj.f2695e);
        C0448b.m3819a(parcel, 2, com_google_android_gms_location_places_zzj.f2697g);
        C0448b.m3815a(parcel, a);
    }

    public zzj m4416a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzj(i2, str, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzj[] m4417a(int i) {
        return new zzj[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4416a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4417a(i);
    }
}
