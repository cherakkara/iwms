package com.google.android.m4b.maps.p053n;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.n.d */
public final class PlacesParamsCreator implements Creator<PlacesParams> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return PlacesParamsCreator.m11052a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlacesParams[i];
    }

    public static PlacesParams m11052a(Parcel parcel) {
        String str = null;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
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
            return new PlacesParams(i, str2, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m11053a(PlacesParams placesParams, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10498a(parcel, 1, placesParams.f7669b, false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, placesParams.f7668a);
        SafeParcelWriter.m10498a(parcel, 2, placesParams.f7670c, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
