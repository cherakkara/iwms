package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.d */
public class C0415d implements Creator<zzae> {
    static void m3563a(zzae com_google_android_gms_common_internal_zzae, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_internal_zzae.f2316a);
        C0448b.m3819a(parcel, 2, com_google_android_gms_common_internal_zzae.m3864a());
        C0448b.m3822a(parcel, 3, com_google_android_gms_common_internal_zzae.f2317b, false);
        C0448b.m3831a(parcel, 4, com_google_android_gms_common_internal_zzae.m3865b(), i, false);
        C0448b.m3821a(parcel, 5, com_google_android_gms_common_internal_zzae.m3867d(), false);
        C0448b.m3826a(parcel, 6, com_google_android_gms_common_internal_zzae.m3866c(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzae m3564a(Parcel parcel) {
        int i = 0;
        String str = null;
        int b = C0447a.m3786b(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    scopeArr = (Scope[]) C0447a.m3788b(parcel, a, Scope.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzae(i2, i, iBinder, scopeArr, bundle, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzae[] m3565a(int i) {
        return new zzae[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3564a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3565a(i);
    }
}
