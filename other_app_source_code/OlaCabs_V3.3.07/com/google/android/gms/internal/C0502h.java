package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzla.zza;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.internal.h */
public class C0502h implements Creator<zza> {
    static void m4122a(zza com_google_android_gms_internal_zzla_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzla_zza.f2447a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzla_zza.f2448b, false);
        C0448b.m3819a(parcel, 3, com_google_android_gms_internal_zzla_zza.f2449c);
        C0448b.m3815a(parcel, a);
    }

    public zza m4123a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zza(i2, str, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m4124a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4123a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4124a(i);
    }
}
