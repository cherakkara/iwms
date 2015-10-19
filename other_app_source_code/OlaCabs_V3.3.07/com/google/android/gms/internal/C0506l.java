package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzlh.zza;
import com.google.android.gms.internal.zzlh.zzb;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;

/* renamed from: com.google.android.gms.internal.l */
public class C0506l implements Creator<zza> {
    static void m4134a(zza com_google_android_gms_internal_zzlh_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzlh_zza.f2465a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzlh_zza.f2466b, false);
        C0448b.m3838c(parcel, 3, com_google_android_gms_internal_zzlh_zza.f2467c, false);
        C0448b.m3815a(parcel, a);
    }

    public zza m4135a(Parcel parcel) {
        ArrayList arrayList = null;
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
                    arrayList = C0447a.m3789c(parcel, a, zzb.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zza(i, str, arrayList);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zza[] m4136a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4135a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4136a(i);
    }
}
