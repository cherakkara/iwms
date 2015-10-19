package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

public class bj implements Creator<zzut> {
    static void m4075a(zzut com_google_android_gms_internal_zzut, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzut.f2600a);
        C0448b.m3815a(parcel, a);
    }

    public zzut m4076a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzut(i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzut[] m4077a(int i) {
        return new zzut[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4076a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4077a(i);
    }
}
