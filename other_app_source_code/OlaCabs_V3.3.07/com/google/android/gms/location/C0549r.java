package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.r */
public class C0549r implements Creator<zzl> {
    static void m4430a(zzl com_google_android_gms_location_zzl, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_location_zzl.f2707a);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_zzl.m4438a());
        C0448b.m3819a(parcel, 2, com_google_android_gms_location_zzl.f2708b);
        C0448b.m3820a(parcel, 3, com_google_android_gms_location_zzl.f2709c);
        C0448b.m3815a(parcel, a);
    }

    public zzl m4431a(Parcel parcel) {
        int i = 1;
        int b = C0447a.m3786b(parcel);
        int i2 = 0;
        long j = 0;
        int i3 = 1;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j = C0447a.m3795h(parcel, a);
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
            return new zzl(i2, i3, i, j);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzl[] m4432a(int i) {
        return new zzl[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4431a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4432a(i);
    }
}
