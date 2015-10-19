package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzla.zza;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;

/* renamed from: com.google.android.gms.internal.g */
public class C0501g implements Creator<zzla> {
    static void m4119a(zzla com_google_android_gms_internal_zzla, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzla.m4188a());
        C0448b.m3838c(parcel, 2, com_google_android_gms_internal_zzla.m4192b(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzla m4120a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    arrayList = C0447a.m3789c(parcel, a, zza.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzla(i, arrayList);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzla[] m4121a(int i) {
        return new zzla[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4120a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4121a(i);
    }
}
