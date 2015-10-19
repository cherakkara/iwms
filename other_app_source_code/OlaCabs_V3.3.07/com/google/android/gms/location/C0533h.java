package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.h */
public class C0533h implements Creator<LocationSettingsResult> {
    static void m4365a(LocationSettingsResult locationSettingsResult, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3824a(parcel, 1, locationSettingsResult.m4335a(), i, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationSettingsResult.m4336b());
        C0448b.m3824a(parcel, 2, locationSettingsResult.m4337c(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public LocationSettingsResult m4366a(Parcel parcel) {
        LocationSettingsStates locationSettingsStates = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < b) {
            int i2;
            LocationSettingsStates locationSettingsStates2;
            Status status2;
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = i;
                    Status status3 = (Status) C0447a.m3782a(parcel, a, Status.CREATOR);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status3;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    locationSettingsStates2 = (LocationSettingsStates) C0447a.m3782a(parcel, a, LocationSettingsStates.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    LocationSettingsStates locationSettingsStates3 = locationSettingsStates;
                    status2 = status;
                    i2 = C0447a.m3793f(parcel, a);
                    locationSettingsStates2 = locationSettingsStates3;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            locationSettingsStates = locationSettingsStates2;
        }
        if (parcel.dataPosition() == b) {
            return new LocationSettingsResult(i, status, locationSettingsStates);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public LocationSettingsResult[] m4367a(int i) {
        return new LocationSettingsResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4366a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4367a(i);
    }
}
