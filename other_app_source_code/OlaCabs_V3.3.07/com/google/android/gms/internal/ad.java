package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class ad implements Creator<zzox> {
    static void m3909a(zzox com_google_android_gms_internal_zzox, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzox.f2492a);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzox.m4251a());
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzox.f2493b, false);
        C0448b.m3815a(parcel, a);
    }

    public zzox m3910a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
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
            return new zzox(i2, i, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzox[] m3911a(int i) {
        return new zzox[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3910a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3911a(i);
    }
}
