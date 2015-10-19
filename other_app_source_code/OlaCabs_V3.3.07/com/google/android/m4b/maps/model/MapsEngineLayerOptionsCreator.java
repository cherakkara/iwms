package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.n */
public class MapsEngineLayerOptionsCreator implements Creator<MapsEngineLayerOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11019a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11020a(i);
    }

    public MapsEngineLayerOptions m11019a(Parcel parcel) {
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        MapsEngineLayerInfo mapsEngineLayerInfo = null;
        float f = 0.0f;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    mapsEngineLayerInfo = (MapsEngineLayerInfo) SafeParcelReader.m10470a(parcel, readInt, MapsEngineLayerInfo.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z2 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new MapsEngineLayerOptions(i, mapsEngineLayerInfo, z2, f, z);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public MapsEngineLayerOptions[] m11020a(int i) {
        return new MapsEngineLayerOptions[i];
    }

    static void m11018a(MapsEngineLayerOptions mapsEngineLayerOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, mapsEngineLayerOptions.m10753a());
        SafeParcelWriter.m10496a(parcel, 2, mapsEngineLayerOptions.m10754b(), i, false);
        SafeParcelWriter.m10501a(parcel, 3, mapsEngineLayerOptions.m10756d());
        SafeParcelWriter.m10491a(parcel, 4, mapsEngineLayerOptions.m10755c());
        SafeParcelWriter.m10501a(parcel, 5, mapsEngineLayerOptions.m10757e());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
