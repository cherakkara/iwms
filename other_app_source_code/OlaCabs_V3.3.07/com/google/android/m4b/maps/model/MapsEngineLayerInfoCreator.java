package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.m */
public class MapsEngineLayerInfoCreator implements Creator<MapsEngineLayerInfo> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11016a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11017a(i);
    }

    public MapsEngineLayerInfo m11016a(Parcel parcel) {
        boolean z = false;
        String str = null;
        int a = SafeParcelReader.m10468a(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str4 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str3 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new MapsEngineLayerInfo(i, str4, str3, str2, z, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public MapsEngineLayerInfo[] m11017a(int i) {
        return new MapsEngineLayerInfo[i];
    }

    static void m11015a(MapsEngineLayerInfo mapsEngineLayerInfo, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, mapsEngineLayerInfo.m10747a());
        SafeParcelWriter.m10498a(parcel, 2, mapsEngineLayerInfo.m10748b(), false);
        SafeParcelWriter.m10498a(parcel, 3, mapsEngineLayerInfo.m10749c(), false);
        SafeParcelWriter.m10498a(parcel, 4, mapsEngineLayerInfo.m10751e(), false);
        SafeParcelWriter.m10501a(parcel, 5, mapsEngineLayerInfo.m10752f());
        SafeParcelWriter.m10498a(parcel, 6, mapsEngineLayerInfo.m10750d(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
