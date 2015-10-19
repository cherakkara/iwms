package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

public class bd implements Creator<zztf> {
    static void m4058a(zztf com_google_android_gms_internal_zztf, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zztf.f2587a);
        C0448b.m3820a(parcel, 2, com_google_android_gms_internal_zztf.f2588b);
        C0448b.m3826a(parcel, 3, com_google_android_gms_internal_zztf.f2589c, false);
        C0448b.m3830a(parcel, 4, com_google_android_gms_internal_zztf.f2590d, false);
        C0448b.m3821a(parcel, 5, com_google_android_gms_internal_zztf.f2591e, false);
        C0448b.m3815a(parcel, a);
    }

    public zztf m4059a(Parcel parcel) {
        Bundle bundle = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        long j = 0;
        byte[] bArr = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bArr = C0447a.m3803p(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zztf(i, j, str, bArr, bundle);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zztf[] m4060a(int i) {
        return new zztf[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4059a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4060a(i);
    }
}
