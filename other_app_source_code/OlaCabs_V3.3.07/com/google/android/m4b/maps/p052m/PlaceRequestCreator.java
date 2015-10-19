package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.m.h */
public final class PlaceRequestCreator implements Creator<PlaceRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return PlaceRequestCreator.m10690a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlaceRequest[i];
    }

    public static PlaceRequest m10690a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = PlaceRequest.f7506a;
        int i2 = HttpStatus.SC_PROCESSING;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    placeFilter = (PlaceFilter) SafeParcelReader.m10470a(parcel, readInt, PlaceFilter.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new PlaceRequest(i, placeFilter, j, i2);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10691a(PlaceRequest placeRequest, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, placeRequest.f7507b);
        SafeParcelWriter.m10496a(parcel, 2, placeRequest.m10687a(), i, false);
        SafeParcelWriter.m10493a(parcel, 3, placeRequest.m10688b());
        SafeParcelWriter.m10492a(parcel, 4, placeRequest.m10689c());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
