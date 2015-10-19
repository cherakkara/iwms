package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.m.b */
public final class NearbyAlertRequestCreator implements Creator<NearbyAlertRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return NearbyAlertRequestCreator.m10675a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new NearbyAlertRequest[i];
    }

    public static NearbyAlertRequest m10675a(Parcel parcel) {
        int i = 0;
        int a = SafeParcelReader.m10468a(parcel);
        int i2 = -1;
        PlaceFilter placeFilter = null;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    placeFilter = (PlaceFilter) SafeParcelReader.m10470a(parcel, readInt, PlaceFilter.CREATOR);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new NearbyAlertRequest(i3, i, i2, placeFilter);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10676a(NearbyAlertRequest nearbyAlertRequest, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, nearbyAlertRequest.m10672b());
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, nearbyAlertRequest.m10671a());
        SafeParcelWriter.m10492a(parcel, 2, nearbyAlertRequest.m10673c());
        SafeParcelWriter.m10496a(parcel, 3, nearbyAlertRequest.m10674d(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
