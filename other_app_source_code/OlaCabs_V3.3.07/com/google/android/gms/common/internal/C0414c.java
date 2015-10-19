package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.c */
public class C0414c implements Creator<zzaa> {
    static void m3560a(zzaa com_google_android_gms_common_internal_zzaa, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_internal_zzaa.f2311a);
        C0448b.m3822a(parcel, 2, com_google_android_gms_common_internal_zzaa.f2312b, false);
        C0448b.m3824a(parcel, 3, com_google_android_gms_common_internal_zzaa.m3861b(), i, false);
        C0448b.m3829a(parcel, 4, com_google_android_gms_common_internal_zzaa.m3862c());
        C0448b.m3829a(parcel, 5, com_google_android_gms_common_internal_zzaa.m3863d());
        C0448b.m3815a(parcel, a);
    }

    public zzaa m3561a(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    connectionResult = (ConnectionResult) C0447a.m3782a(parcel, a, ConnectionResult.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzaa(i, iBinder, connectionResult, z2, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzaa[] m3562a(int i) {
        return new zzaa[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3561a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3562a(i);
    }
}
