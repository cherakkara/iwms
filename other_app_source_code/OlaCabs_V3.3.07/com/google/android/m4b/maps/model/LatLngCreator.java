package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.k */
public class LatLngCreator implements Creator<LatLng> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11012a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11013a(i);
    }

    public LatLng m11012a(Parcel parcel) {
        double d = 0.0d;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    d2 = SafeParcelReader.m10482j(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d = SafeParcelReader.m10482j(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new LatLng(i, d2, d);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public LatLng[] m11013a(int i) {
        return new LatLng[i];
    }

    static void m11011a(LatLng latLng, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, latLng.m10737a());
        SafeParcelWriter.m10490a(parcel, 2, latLng.f7554a);
        SafeParcelWriter.m10490a(parcel, 3, latLng.f7555b);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
