package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.gms.location.n */
public class C0538n implements Creator<LocationRequest> {
    static void m4376a(LocationRequest locationRequest, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, locationRequest.f2615a);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationRequest.m4326b());
        C0448b.m3820a(parcel, 2, locationRequest.f2616b);
        C0448b.m3820a(parcel, 3, locationRequest.f2617c);
        C0448b.m3829a(parcel, 4, locationRequest.f2618d);
        C0448b.m3820a(parcel, 5, locationRequest.f2619e);
        C0448b.m3819a(parcel, 6, locationRequest.f2620f);
        C0448b.m3818a(parcel, 7, locationRequest.f2621g);
        C0448b.m3820a(parcel, 8, locationRequest.f2622h);
        C0448b.m3815a(parcel, a);
    }

    public LocationRequest m4377a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        int i2 = HttpStatus.SC_PROCESSING;
        long j = Constants.MILLIS_IN_AN_HOUR;
        long j2 = 600000;
        boolean z = false;
        long j3 = Long.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j2 = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    j3 = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    j4 = C0447a.m3795h(parcel, a);
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
            return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LocationRequest[] m4378a(int i) {
        return new LocationRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4377a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4378a(i);
    }
}
