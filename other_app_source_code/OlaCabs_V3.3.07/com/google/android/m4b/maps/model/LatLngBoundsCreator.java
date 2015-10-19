package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.i */
public class LatLngBoundsCreator implements Creator<LatLngBounds> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10840a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10841a(i);
    }

    public LatLngBounds m10840a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        LatLng latLng = null;
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng2 = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new LatLngBounds(i, latLng, latLng2);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public LatLngBounds[] m10841a(int i) {
        return new LatLngBounds[i];
    }

    static void m10839a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, latLngBounds.m10745a());
        SafeParcelWriter.m10496a(parcel, 2, latLngBounds.f7561a, i, false);
        SafeParcelWriter.m10496a(parcel, 3, latLngBounds.f7562b, i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
