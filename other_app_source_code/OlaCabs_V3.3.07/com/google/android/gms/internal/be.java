package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

public class be implements Creator<zztj> {
    static void m4061a(zztj com_google_android_gms_internal_zztj, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zztj.f2592a);
        C0448b.m3826a(parcel, 2, com_google_android_gms_internal_zztj.f2593b, false);
        C0448b.m3819a(parcel, 3, com_google_android_gms_internal_zztj.f2594c);
        C0448b.m3819a(parcel, 4, com_google_android_gms_internal_zztj.f2595d);
        C0448b.m3826a(parcel, 5, com_google_android_gms_internal_zztj.f2596e, false);
        C0448b.m3826a(parcel, 6, com_google_android_gms_internal_zztj.f2597f, false);
        C0448b.m3829a(parcel, 7, com_google_android_gms_internal_zztj.f2598g);
        C0448b.m3826a(parcel, 8, com_google_android_gms_internal_zztj.f2599h, false);
        C0448b.m3815a(parcel, a);
    }

    public zztj m4062a(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = C0447a.m3786b(parcel);
        boolean z = true;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        String str4 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str4 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zztj(i3, str4, i2, i, str3, str2, z, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zztj[] m4063a(int i) {
        return new zztj[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4062a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4063a(i);
    }
}
