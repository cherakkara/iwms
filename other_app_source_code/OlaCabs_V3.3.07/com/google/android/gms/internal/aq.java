package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzpo.zza;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public class aq implements Creator<zzpo> {
    static void m4021a(zzpo com_google_android_gms_internal_zzpo, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzpo.f2523b, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpo.f2522a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzpo.f2524c, false);
        C0448b.m3827a(parcel, 3, com_google_android_gms_internal_zzpo.f2525d, false);
        C0448b.m3838c(parcel, 4, com_google_android_gms_internal_zzpo.f2526e, false);
        C0448b.m3819a(parcel, 5, com_google_android_gms_internal_zzpo.f2527f);
        C0448b.m3815a(parcel, a);
    }

    public zzpo m4022a(Parcel parcel) {
        int i = 0;
        List list = null;
        int b = C0447a.m3786b(parcel);
        List list2 = null;
        String str = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    list2 = C0447a.m3812y(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    list = C0447a.m3789c(parcel, a, zza.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i = C0447a.m3793f(parcel, a);
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
            return new zzpo(i2, str2, str, list2, list, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzpo[] m4023a(int i) {
        return new zzpo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4022a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4023a(i);
    }
}
