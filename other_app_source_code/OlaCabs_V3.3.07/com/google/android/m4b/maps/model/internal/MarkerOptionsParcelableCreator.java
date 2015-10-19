package com.google.android.m4b.maps.model.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.p */
public class MarkerOptionsParcelableCreator implements Creator<MarkerOptionsParcelable> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11008a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11009a(i);
    }

    public MarkerOptionsParcelable m11008a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        BitmapDescriptorParcelable bitmapDescriptorParcelable = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    bitmapDescriptorParcelable = (BitmapDescriptorParcelable) SafeParcelReader.m10470a(parcel, readInt, BitmapDescriptorParcelable.CREATOR);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new MarkerOptionsParcelable(i, bitmapDescriptorParcelable);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public MarkerOptionsParcelable[] m11009a(int i) {
        return new MarkerOptionsParcelable[i];
    }

    static void m11007a(MarkerOptionsParcelable markerOptionsParcelable, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, markerOptionsParcelable.m10851a());
        SafeParcelWriter.m10496a(parcel, 2, markerOptionsParcelable.m10852b(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
