package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzld.zza;
import com.google.android.gms.internal.zzlh.zzb;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.internal.j */
public class C0504j implements Creator<zzb> {
    static void m4128a(zzb com_google_android_gms_internal_zzlh_zzb, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzlh_zzb.f2468a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzlh_zzb.f2469b, false);
        C0448b.m3824a(parcel, 3, com_google_android_gms_internal_zzlh_zzb.f2470c, i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzb m4129a(Parcel parcel) {
        zza com_google_android_gms_internal_zzld_zza = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    com_google_android_gms_internal_zzld_zza = (zza) C0447a.m3782a(parcel, a, zza.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzb(i, str, com_google_android_gms_internal_zzld_zza);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzb[] m4130a(int i) {
        return new zzb[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4129a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4130a(i);
    }
}
