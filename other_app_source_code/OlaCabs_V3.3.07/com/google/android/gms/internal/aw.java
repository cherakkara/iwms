package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzpo.zza;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class aw implements Creator<zza> {
    static void m4037a(zza com_google_android_gms_internal_zzpo_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzpo_zza.f2520b);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpo_zza.f2519a);
        C0448b.m3819a(parcel, 2, com_google_android_gms_internal_zzpo_zza.f2521c);
        C0448b.m3815a(parcel, a);
    }

    public zza m4038a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zza(i3, i2, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m4039a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4038a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4039a(i);
    }
}
