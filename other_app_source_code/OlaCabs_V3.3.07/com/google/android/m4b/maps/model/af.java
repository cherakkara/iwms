package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: VisibleRegionCreator */
public class af implements Creator<VisibleRegion> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10820a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10821a(i);
    }

    public VisibleRegion m10820a(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng4 = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng3 = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    latLng2 = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    latLngBounds = (LatLngBounds) SafeParcelReader.m10470a(parcel, readInt, LatLngBounds.CREATOR);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public VisibleRegion[] m10821a(int i) {
        return new VisibleRegion[i];
    }

    static void m10819a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, visibleRegion.m10809a());
        SafeParcelWriter.m10496a(parcel, 2, visibleRegion.f7635a, i, false);
        SafeParcelWriter.m10496a(parcel, 3, visibleRegion.f7636b, i, false);
        SafeParcelWriter.m10496a(parcel, 4, visibleRegion.f7637c, i, false);
        SafeParcelWriter.m10496a(parcel, 5, visibleRegion.f7638d, i, false);
        SafeParcelWriter.m10496a(parcel, 6, visibleRegion.f7639e, i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
