package com.google.android.m4b.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: TileCreator */
public class aa implements Creator<Tile> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10812a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10813a(i);
    }

    public Tile m10812a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        byte[] bArr = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    readInt = SafeParcelReader.m10469a(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (readInt != 0) {
                        bArr = parcel.createByteArray();
                        parcel.setDataPosition(readInt + dataPosition);
                        break;
                    }
                    bArr = null;
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public Tile[] m10813a(int i) {
        return new Tile[i];
    }

    static void m10811a(Tile tile, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, tile.m10799a());
        SafeParcelWriter.m10492a(parcel, 2, tile.f7622a);
        SafeParcelWriter.m10492a(parcel, 3, tile.f7623b);
        SafeParcelWriter.m10502a(parcel, 4, tile.f7624c, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
