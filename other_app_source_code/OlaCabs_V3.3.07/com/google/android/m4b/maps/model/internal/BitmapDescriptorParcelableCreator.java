package com.google.android.m4b.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.a */
public class BitmapDescriptorParcelableCreator implements Creator<BitmapDescriptorParcelable> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10854a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10855a(i);
    }

    public BitmapDescriptorParcelable m10854a(Parcel parcel) {
        Bitmap bitmap = null;
        int a = SafeParcelReader.m10468a(parcel);
        byte b = (byte) 0;
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    b = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    bundle = SafeParcelReader.m10485m(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bitmap = (Bitmap) SafeParcelReader.m10470a(parcel, readInt, Bitmap.CREATOR);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new BitmapDescriptorParcelable(i, b, bundle, bitmap);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public BitmapDescriptorParcelable[] m10855a(int i) {
        return new BitmapDescriptorParcelable[i];
    }

    static void m10853a(BitmapDescriptorParcelable bitmapDescriptorParcelable, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, bitmapDescriptorParcelable.m10842a());
        SafeParcelWriter.m10489a(parcel, 2, bitmapDescriptorParcelable.m10843b());
        SafeParcelWriter.m10494a(parcel, 3, bitmapDescriptorParcelable.m10844c(), false);
        SafeParcelWriter.m10496a(parcel, 4, bitmapDescriptorParcelable.m10845d(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
