package com.google.android.m4b.maps.p042r;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.o */
public final class PointCreator implements Creator<an> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return PointCreator.m11185a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new an[i];
    }

    public static an m11185a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        Point point = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    point = (Point) SafeParcelReader.m10470a(parcel, readInt, Point.CREATOR);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new an(i, point);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m11186a(an anVar, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, anVar.m11116a());
        SafeParcelWriter.m10496a(parcel, 2, anVar.m11117b(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
