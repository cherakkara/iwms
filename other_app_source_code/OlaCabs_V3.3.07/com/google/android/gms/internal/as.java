package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class as implements Creator<zzqa> {
    static void m4027a(zzqa com_google_android_gms_internal_zzqa, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3824a(parcel, 1, com_google_android_gms_internal_zzqa.f2554b, i, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqa.f2553a);
        C0448b.m3818a(parcel, 2, com_google_android_gms_internal_zzqa.f2555c);
        C0448b.m3815a(parcel, a);
    }

    public zzqa m4028a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        zzpy com_google_android_gms_internal_zzpy = null;
        float f = 0.0f;
        while (parcel.dataPosition() < b) {
            int i2;
            float f2;
            zzpy com_google_android_gms_internal_zzpy2;
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = i;
                    zzpy com_google_android_gms_internal_zzpy3 = (zzpy) C0447a.m3782a(parcel, a, zzpy.CREATOR);
                    f2 = f;
                    com_google_android_gms_internal_zzpy2 = com_google_android_gms_internal_zzpy3;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f2 = C0447a.m3797j(parcel, a);
                    com_google_android_gms_internal_zzpy2 = com_google_android_gms_internal_zzpy;
                    i2 = i;
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    float f3 = f;
                    com_google_android_gms_internal_zzpy2 = com_google_android_gms_internal_zzpy;
                    i2 = C0447a.m3793f(parcel, a);
                    f2 = f3;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    f2 = f;
                    com_google_android_gms_internal_zzpy2 = com_google_android_gms_internal_zzpy;
                    i2 = i;
                    break;
            }
            i = i2;
            com_google_android_gms_internal_zzpy = com_google_android_gms_internal_zzpy2;
            f = f2;
        }
        if (parcel.dataPosition() == b) {
            return new zzqa(i, com_google_android_gms_internal_zzpy, f);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzqa[] m4029a(int i) {
        return new zzqa[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4028a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4029a(i);
    }
}
