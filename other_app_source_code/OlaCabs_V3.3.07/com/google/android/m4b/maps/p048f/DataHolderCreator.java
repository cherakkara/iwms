package com.google.android.m4b.maps.p048f;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.f.b */
public final class DataHolderCreator implements Creator<DataHolder> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return DataHolderCreator.m10313a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DataHolder[i];
    }

    public static DataHolder m10313a(Parcel parcel) {
        int i = 0;
        int a = SafeParcelReader.m10468a(parcel);
        Bundle bundle = null;
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    readInt = SafeParcelReader.m10469a(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (readInt != 0) {
                        strArr = parcel.createStringArray();
                        parcel.setDataPosition(readInt + dataPosition);
                        break;
                    }
                    strArr = null;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    cursorWindowArr = (CursorWindow[]) SafeParcelReader.m10473b(parcel, readInt, CursorWindow.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bundle = SafeParcelReader.m10485m(parcel, readInt);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() != a) {
            throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.m10307a();
        return dataHolder;
    }

    static void m10314a(DataHolder dataHolder, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10504a(parcel, 1, dataHolder.m10309c(), false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, dataHolder.m10308b());
        SafeParcelWriter.m10503a(parcel, 2, dataHolder.m10310d(), i, false);
        SafeParcelWriter.m10492a(parcel, 3, dataHolder.m10311e());
        SafeParcelWriter.m10494a(parcel, 4, dataHolder.m10312f(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
