package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.q */
public class C0548q implements Creator<LocationSettingsStates> {
    static void m4427a(LocationSettingsStates locationSettingsStates, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3829a(parcel, 1, locationSettingsStates.m4340b());
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationSettingsStates.m4339a());
        C0448b.m3829a(parcel, 2, locationSettingsStates.m4342d());
        C0448b.m3829a(parcel, 3, locationSettingsStates.m4346h());
        C0448b.m3829a(parcel, 4, locationSettingsStates.m4341c());
        C0448b.m3829a(parcel, 5, locationSettingsStates.m4343e());
        C0448b.m3829a(parcel, 6, locationSettingsStates.m4347i());
        C0448b.m3815a(parcel, a);
    }

    public LocationSettingsStates m4428a(Parcel parcel) {
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    z6 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    z5 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z4 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    z3 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    z = C0447a.m3790c(parcel, a);
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
            return new LocationSettingsStates(i, z6, z5, z4, z3, z2, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsStates[] m4429a(int i) {
        return new LocationSettingsStates[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4428a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4429a(i);
    }
}
