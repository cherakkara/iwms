package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.j */
public class C0421j implements Creator<zzi> {
    static void m3582a(zzi com_google_android_gms_common_internal_zzi, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_internal_zzi.f2329a);
        C0448b.m3819a(parcel, 2, com_google_android_gms_common_internal_zzi.f2330b);
        C0448b.m3819a(parcel, 3, com_google_android_gms_common_internal_zzi.f2331c);
        C0448b.m3826a(parcel, 4, com_google_android_gms_common_internal_zzi.f2332d, false);
        C0448b.m3822a(parcel, 5, com_google_android_gms_common_internal_zzi.f2333e, false);
        C0448b.m3831a(parcel, 6, com_google_android_gms_common_internal_zzi.f2334f, i, false);
        C0448b.m3821a(parcel, 7, com_google_android_gms_common_internal_zzi.f2335g, false);
        C0448b.m3824a(parcel, 8, com_google_android_gms_common_internal_zzi.f2336h, i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzi m3583a(Parcel parcel) {
        int i = 0;
        Account account = null;
        int b = C0447a.m3786b(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    scopeArr = (Scope[]) C0447a.m3788b(parcel, a, Scope.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    account = (Account) C0447a.m3782a(parcel, a, Account.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzi(i3, i2, i, str, iBinder, scopeArr, bundle, account);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzi[] m3584a(int i) {
        return new zzi[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3583a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3584a(i);
    }
}
