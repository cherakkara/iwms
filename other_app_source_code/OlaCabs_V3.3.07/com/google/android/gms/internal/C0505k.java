package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzlh.zza;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;

/* renamed from: com.google.android.gms.internal.k */
public class C0505k implements Creator<zzlh> {
    static void m4131a(zzlh com_google_android_gms_internal_zzlh, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzlh.m4225b());
        C0448b.m3838c(parcel, 2, com_google_android_gms_internal_zzlh.m4226c(), false);
        C0448b.m3826a(parcel, 3, com_google_android_gms_internal_zzlh.m4227d(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzlh m4132a(Parcel parcel) {
        String str = null;
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
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzlh(i, arrayList, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzlh[] m4133a(int i) {
        return new zzlh[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4132a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4133a(i);
    }
}
