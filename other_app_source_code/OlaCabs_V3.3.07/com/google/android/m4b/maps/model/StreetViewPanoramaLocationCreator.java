package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.y */
public class StreetViewPanoramaLocationCreator implements Creator<StreetViewPanoramaLocation> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11040a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11041a(i);
    }

    public StreetViewPanoramaLocation m11040a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        LatLng latLng = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    streetViewPanoramaLinkArr = (StreetViewPanoramaLink[]) SafeParcelReader.m10473b(parcel, readInt, StreetViewPanoramaLink.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new StreetViewPanoramaLocation(i, streetViewPanoramaLinkArr, latLng, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public StreetViewPanoramaLocation[] m11041a(int i) {
        return new StreetViewPanoramaLocation[i];
    }

    static void m11039a(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, streetViewPanoramaLocation.m10794a());
        SafeParcelWriter.m10503a(parcel, 2, streetViewPanoramaLocation.f7613a, i, false);
        SafeParcelWriter.m10496a(parcel, 3, streetViewPanoramaLocation.f7614b, i, false);
        SafeParcelWriter.m10498a(parcel, 4, streetViewPanoramaLocation.f7615c, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
