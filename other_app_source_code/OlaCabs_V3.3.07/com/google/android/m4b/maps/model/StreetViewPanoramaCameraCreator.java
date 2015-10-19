package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.w */
public class StreetViewPanoramaCameraCreator implements Creator<StreetViewPanoramaCamera> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11034a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11035a(i);
    }

    public StreetViewPanoramaCamera m11034a(Parcel parcel) {
        float f = 0.0f;
        int a = SafeParcelReader.m10468a(parcel);
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
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
                    f3 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new StreetViewPanoramaCamera(i, f2, f3, f);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public StreetViewPanoramaCamera[] m11035a(int i) {
        return new StreetViewPanoramaCamera[i];
    }

    static void m11033a(StreetViewPanoramaCamera streetViewPanoramaCamera, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, streetViewPanoramaCamera.m10792a());
        SafeParcelWriter.m10491a(parcel, 2, streetViewPanoramaCamera.f7605a);
        SafeParcelWriter.m10491a(parcel, 3, streetViewPanoramaCamera.f7606b);
        SafeParcelWriter.m10491a(parcel, 4, streetViewPanoramaCamera.f7607c);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
