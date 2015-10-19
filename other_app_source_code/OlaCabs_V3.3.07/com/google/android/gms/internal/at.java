package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public class at implements Creator<zzqd> {
    static void m4030a(zzqd com_google_android_gms_internal_zzqd, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzqd.f2557b, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqd.f2556a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzqd.f2558c, false);
        C0448b.m3826a(parcel, 3, com_google_android_gms_internal_zzqd.f2559d, false);
        C0448b.m3826a(parcel, 4, com_google_android_gms_internal_zzqd.f2560e, false);
        C0448b.m3836b(parcel, 5, com_google_android_gms_internal_zzqd.f2561f, false);
        C0448b.m3815a(parcel, a);
    }

    public zzqd m4031a(Parcel parcel) {
        List list = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str4 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    list = C0447a.m3813z(parcel, a);
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
            return new zzqd(i, str4, str3, str2, str, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzqd[] m4032a(int i) {
        return new zzqd[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4031a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4032a(i);
    }
}
