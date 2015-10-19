package com.google.android.m4b.maps.p044k;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.k.i */
public final class LocationStatusCreator implements Creator<LocationStatus> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return LocationStatusCreator.m10560a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new LocationStatus[i];
    }

    public static LocationStatus m10560a(Parcel parcel) {
        int i = 1;
        int a = SafeParcelReader.m10468a(parcel);
        int i2 = 0;
        long j = 0;
        int i3 = 1;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new LocationStatus(i2, i3, i, j);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10561a(LocationStatus locationStatus, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, locationStatus.f7460a);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationStatus.m10559a());
        SafeParcelWriter.m10492a(parcel, 2, locationStatus.f7461b);
        SafeParcelWriter.m10493a(parcel, 3, locationStatus.f7462c);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
