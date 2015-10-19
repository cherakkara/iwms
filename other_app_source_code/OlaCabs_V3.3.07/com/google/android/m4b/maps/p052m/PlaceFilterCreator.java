package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.m.d */
public final class PlaceFilterCreator implements Creator<PlaceFilter> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return PlaceFilterCreator.m10681a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlaceFilter[i];
    }

    public static PlaceFilter m10681a(Parcel parcel) {
        boolean z = false;
        List list = null;
        int a = SafeParcelReader.m10468a(parcel);
        String str = null;
        List list2 = null;
        String str2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list3 = SafeParcelReader.m10474c(parcel, readInt, PlaceType.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    list2 = SafeParcelReader.m10474c(parcel, readInt, UserDataType.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    list = SafeParcelReader.m10486n(parcel, readInt);
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
            return new PlaceFilter(i, list3, str2, z, list2, str, list);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10682a(PlaceFilter placeFilter, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10508b(parcel, 1, placeFilter.f7494b, false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, placeFilter.f7493a);
        SafeParcelWriter.m10498a(parcel, 2, placeFilter.m10678a(), false);
        SafeParcelWriter.m10501a(parcel, 3, placeFilter.m10679b());
        SafeParcelWriter.m10508b(parcel, 4, placeFilter.f7495c, false);
        SafeParcelWriter.m10498a(parcel, 5, placeFilter.m10680c(), false);
        SafeParcelWriter.m10499a(parcel, 6, placeFilter.f7496d, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
