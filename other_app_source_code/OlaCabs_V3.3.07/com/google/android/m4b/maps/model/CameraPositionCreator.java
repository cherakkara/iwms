package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.c */
public class CameraPositionCreator implements Creator<CameraPosition> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10828a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10829a(i);
    }

    public CameraPosition m10828a(Parcel parcel) {
        float f = 0.0f;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
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
                    f3 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public CameraPosition[] m10829a(int i) {
        return new CameraPosition[i];
    }

    static void m10827a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, cameraPosition.m10712a());
        SafeParcelWriter.m10496a(parcel, 2, cameraPosition.f7529a, i, false);
        SafeParcelWriter.m10491a(parcel, 3, cameraPosition.f7530b);
        SafeParcelWriter.m10491a(parcel, 4, cameraPosition.f7531c);
        SafeParcelWriter.m10491a(parcel, 5, cameraPosition.f7532d);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
