package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.z */
public class StreetViewPanoramaOrientationCreator implements Creator<StreetViewPanoramaOrientation> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11043a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11044a(i);
    }

    public StreetViewPanoramaOrientation m11043a(Parcel parcel) {
        float f = 0.0f;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        float f2 = 0.0f;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new StreetViewPanoramaOrientation(i, f2, f);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public StreetViewPanoramaOrientation[] m11044a(int i) {
        return new StreetViewPanoramaOrientation[i];
    }

    static void m11042a(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, streetViewPanoramaOrientation.m10798a());
        SafeParcelWriter.m10491a(parcel, 2, streetViewPanoramaOrientation.f7619a);
        SafeParcelWriter.m10491a(parcel, 3, streetViewPanoramaOrientation.f7620b);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
