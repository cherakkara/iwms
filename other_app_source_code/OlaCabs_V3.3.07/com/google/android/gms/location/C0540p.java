package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.p */
public class C0540p implements Creator<LocationSettingsRequest> {
    static void m4382a(LocationSettingsRequest locationSettingsRequest, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3838c(parcel, 1, locationSettingsRequest.m4332b(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationSettingsRequest.m4331a());
        C0448b.m3829a(parcel, 2, locationSettingsRequest.m4333c());
        C0448b.m3829a(parcel, 3, locationSettingsRequest.m4334d());
        C0448b.m3815a(parcel, a);
    }

    public LocationSettingsRequest m4383a(Parcel parcel) {
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list = C0447a.m3789c(parcel, a, LocationRequest.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
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
            return new LocationSettingsRequest(i, list, z2, z);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsRequest[] m4384a(int i) {
        return new LocationSettingsRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4383a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4384a(i);
    }
}
