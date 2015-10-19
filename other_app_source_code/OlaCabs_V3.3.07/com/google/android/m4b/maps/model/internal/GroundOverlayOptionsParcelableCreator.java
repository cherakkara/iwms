package com.google.android.m4b.maps.model.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.c */
public class GroundOverlayOptionsParcelableCreator implements Creator<GroundOverlayOptionsParcelable> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10860a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10861a(i);
    }

    public GroundOverlayOptionsParcelable m10860a(Parcel parcel) {
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
            return new GroundOverlayOptionsParcelable(i, bitmapDescriptorParcelable);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public GroundOverlayOptionsParcelable[] m10861a(int i) {
        return new GroundOverlayOptionsParcelable[i];
    }

    static void m10859a(GroundOverlayOptionsParcelable groundOverlayOptionsParcelable, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, groundOverlayOptionsParcelable.m10849a());
        SafeParcelWriter.m10496a(parcel, 2, groundOverlayOptionsParcelable.m10850b(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
