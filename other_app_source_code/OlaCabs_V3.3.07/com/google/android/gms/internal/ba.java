package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public class ba implements Creator<zzqq> {
    static void m4052a(zzqq com_google_android_gms_internal_zzqq, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzqq.m4302a(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqq.f2579a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zzqq.m4303b(), false);
        C0448b.m3838c(parcel, 5, com_google_android_gms_internal_zzqq.m4306e(), false);
        C0448b.m3838c(parcel, 6, com_google_android_gms_internal_zzqq.m4304c(), false);
        C0448b.m3838c(parcel, 7, com_google_android_gms_internal_zzqq.m4305d(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzqq m4053a(Parcel parcel) {
        List list = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        List list2 = null;
        List list3 = null;
        String str = null;
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
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    list3 = C0447a.m3789c(parcel, a, zzqu.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    list2 = C0447a.m3789c(parcel, a, zzqo.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    list = C0447a.m3789c(parcel, a, zzqm.CREATOR);
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
            return new zzqq(i, str2, str, list3, list2, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzqq[] m4054a(int i) {
        return new zzqq[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4053a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4054a(i);
    }
}
