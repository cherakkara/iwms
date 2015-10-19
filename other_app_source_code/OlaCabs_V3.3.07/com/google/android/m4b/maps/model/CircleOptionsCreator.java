package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.e */
public class CircleOptionsCreator implements Creator<CircleOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10832a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10833a(i);
    }

    public CircleOptions m10832a(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    d = SafeParcelReader.m10482j(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public CircleOptions[] m10833a(int i) {
        return new CircleOptions[i];
    }

    static void m10831a(CircleOptions circleOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, circleOptions.m10713a());
        SafeParcelWriter.m10496a(parcel, 2, circleOptions.m10714b(), i, false);
        SafeParcelWriter.m10490a(parcel, 3, circleOptions.m10715c());
        SafeParcelWriter.m10491a(parcel, 4, circleOptions.m10716d());
        SafeParcelWriter.m10492a(parcel, 5, circleOptions.m10717e());
        SafeParcelWriter.m10492a(parcel, 6, circleOptions.m10718f());
        SafeParcelWriter.m10491a(parcel, 7, circleOptions.m10719g());
        SafeParcelWriter.m10501a(parcel, 8, circleOptions.m10720h());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
