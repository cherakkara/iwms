package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: TileOverlayOptionsCreator */
public class ac implements Creator<TileOverlayOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10816a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10817a(i);
    }

    public TileOverlayOptions m10816a(Parcel parcel) {
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    iBinder = SafeParcelReader.m10484l(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z2 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new TileOverlayOptions(i, iBinder, z, f, z2);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public TileOverlayOptions[] m10817a(int i) {
        return new TileOverlayOptions[i];
    }

    static void m10815a(TileOverlayOptions tileOverlayOptions, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, tileOverlayOptions.m10803a());
        SafeParcelWriter.m10495a(parcel, 2, tileOverlayOptions.m10804b(), false);
        SafeParcelWriter.m10501a(parcel, 3, tileOverlayOptions.m10807e());
        SafeParcelWriter.m10491a(parcel, 4, tileOverlayOptions.m10806d());
        SafeParcelWriter.m10501a(parcel, 5, tileOverlayOptions.m10808f());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
