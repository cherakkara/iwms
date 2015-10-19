package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzqm.zza;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class ax implements Creator<zza> {
    static void m4040a(zza com_google_android_gms_internal_zzqm_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzqm_zza.m4297a(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqm_zza.f2569a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzqm_zza.m4298b(), false);
        C0448b.m3815a(parcel, a);
    }

    public zza m4041a(Parcel parcel) {
        String str = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
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
            return new zza(i, str2, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m4042a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4041a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4042a(i);
    }
}
