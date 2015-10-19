package com.google.android.m4b.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.b */
public class CameraUpdateParcelableCreator implements Creator<CameraUpdateParcelable> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10857a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10858a(i);
    }

    public CameraUpdateParcelable m10857a(Parcel parcel) {
        int i = 0;
        int a = SafeParcelReader.m10468a(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    bundle = SafeParcelReader.m10485m(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new CameraUpdateParcelable(i2, i, bundle);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public CameraUpdateParcelable[] m10858a(int i) {
        return new CameraUpdateParcelable[i];
    }

    static void m10856a(CameraUpdateParcelable cameraUpdateParcelable, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, cameraUpdateParcelable.m10846a());
        SafeParcelWriter.m10492a(parcel, 2, cameraUpdateParcelable.m10847b());
        SafeParcelWriter.m10494a(parcel, 3, cameraUpdateParcelable.m10848c(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
