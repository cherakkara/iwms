package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.m.l */
public final class UserAddedPlaceCreator implements Creator<UserAddedPlace> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        String str2 = null;
        List list = null;
        String str3 = null;
        LatLng latLng = null;
        String str4 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str4 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str3 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    list = SafeParcelReader.m10474c(parcel, readInt, PlaceType.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
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
            return new UserAddedPlace(i, str4, latLng, str3, list, str2, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new UserAddedPlace[i];
    }

    static void m10700a(UserAddedPlace userAddedPlace, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10498a(parcel, 1, userAddedPlace.m10694a(), false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, userAddedPlace.f7513a);
        SafeParcelWriter.m10496a(parcel, 2, userAddedPlace.m10695b(), i, false);
        SafeParcelWriter.m10498a(parcel, 3, userAddedPlace.m10696c(), false);
        SafeParcelWriter.m10508b(parcel, 4, userAddedPlace.m10697d(), false);
        SafeParcelWriter.m10498a(parcel, 5, userAddedPlace.m10698e(), false);
        SafeParcelWriter.m10498a(parcel, 6, userAddedPlace.m10699f(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
