package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.model.t */
public class PolylineOptionsCreator implements Creator<PolylineOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11030a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11031a(i);
    }

    public PolylineOptions m11030a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = SafeParcelReader.m10474c(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    z2 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public PolylineOptions[] m11031a(int i) {
        return new PolylineOptions[i];
    }

    static void m11029a(PolylineOptions polylineOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, polylineOptions.m10785a());
        SafeParcelWriter.m10508b(parcel, 2, polylineOptions.m10786b(), false);
        SafeParcelWriter.m10491a(parcel, 3, polylineOptions.m10787c());
        SafeParcelWriter.m10492a(parcel, 4, polylineOptions.m10788d());
        SafeParcelWriter.m10491a(parcel, 5, polylineOptions.m10789e());
        SafeParcelWriter.m10501a(parcel, 6, polylineOptions.m10790f());
        SafeParcelWriter.m10501a(parcel, 7, polylineOptions.m10791g());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
