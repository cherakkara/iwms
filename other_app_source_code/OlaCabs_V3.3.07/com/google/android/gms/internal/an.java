package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

public class an implements Creator<zzpk> {
    static void m4011a(zzpk com_google_android_gms_internal_zzpk, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzpk.m4272f(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpk.m4267a());
        C0448b.m3820a(parcel, 2, com_google_android_gms_internal_zzpk.m4273g());
        C0448b.m3828a(parcel, 3, com_google_android_gms_internal_zzpk.m4268b());
        C0448b.m3817a(parcel, 4, com_google_android_gms_internal_zzpk.m4269c());
        C0448b.m3817a(parcel, 5, com_google_android_gms_internal_zzpk.m4270d());
        C0448b.m3818a(parcel, 6, com_google_android_gms_internal_zzpk.m4271e());
        C0448b.m3819a(parcel, 7, com_google_android_gms_internal_zzpk.m4274h());
        C0448b.m3819a(parcel, 8, com_google_android_gms_internal_zzpk.m4275i());
        C0448b.m3819a(parcel, 9, com_google_android_gms_internal_zzpk.m4276j());
        C0448b.m3815a(parcel, a);
    }

    public zzpk m4012a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    s = C0447a.m3792e(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    d = C0447a.m3798k(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    d2 = C0447a.m3798k(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    i4 = C0447a.m3793f(parcel, a);
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
            return new zzpk(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzpk[] m4013a(int i) {
        return new zzpk[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4012a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4013a(i);
    }
}
