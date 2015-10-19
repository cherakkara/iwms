package com.google.android.m4b.maps.p052m;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.m.n */
public final class UserDataTypeCreator implements Creator<UserDataType> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int a = SafeParcelReader.m10468a(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
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
            return new UserDataType(i2, str, i);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new UserDataType[i];
    }

    static void m10702a(UserDataType userDataType, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10498a(parcel, 1, userDataType.f7523b, false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, userDataType.f7522a);
        SafeParcelWriter.m10492a(parcel, 2, userDataType.f7524c);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
