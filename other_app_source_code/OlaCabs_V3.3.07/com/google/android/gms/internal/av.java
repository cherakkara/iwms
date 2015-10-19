package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class av implements Creator<zzqh> {
    static void m4034a(zzqh com_google_android_gms_internal_zzqh, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzqh.f2564c, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqh.f2563b);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzqh.f2565d, false);
        C0448b.m3826a(parcel, 3, com_google_android_gms_internal_zzqh.f2566e, false);
        C0448b.m3826a(parcel, 4, com_google_android_gms_internal_zzqh.f2567f, false);
        C0448b.m3826a(parcel, 5, com_google_android_gms_internal_zzqh.f2568g, false);
        C0448b.m3815a(parcel, a);
    }

    public zzqh m4035a(Parcel parcel) {
        String str = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str5 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str4 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
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
            return new zzqh(i, str5, str4, str3, str2, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzqh[] m4036a(int i) {
        return new zzqh[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4035a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4036a(i);
    }
}
