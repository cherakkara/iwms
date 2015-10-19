package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.location.LocationRequest;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public class al implements Creator<zzpg> {
    static void m4005a(zzpg com_google_android_gms_internal_zzpg, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3824a(parcel, 1, com_google_android_gms_internal_zzpg.f2496b, i, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpg.m4254a());
        C0448b.m3829a(parcel, 2, com_google_android_gms_internal_zzpg.f2497c);
        C0448b.m3829a(parcel, 3, com_google_android_gms_internal_zzpg.f2498d);
        C0448b.m3829a(parcel, 4, com_google_android_gms_internal_zzpg.f2499e);
        C0448b.m3838c(parcel, 5, com_google_android_gms_internal_zzpg.f2500f, false);
        C0448b.m3826a(parcel, 6, com_google_android_gms_internal_zzpg.f2501g, false);
        C0448b.m3815a(parcel, a);
    }

    public zzpg m4006a(Parcel parcel) {
        String str = null;
        boolean z = true;
        boolean z2 = false;
        int b = C0447a.m3786b(parcel);
        List list = zzpg.f2495a;
        boolean z3 = true;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    locationRequest = (LocationRequest) C0447a.m3782a(parcel, a, LocationRequest.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z3 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    list = C0447a.m3789c(parcel, a, zzox.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
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
            return new zzpg(i, locationRequest, z2, z3, z, list, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzpg[] m4007a(int i) {
        return new zzpg[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4006a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4007a(i);
    }
}
