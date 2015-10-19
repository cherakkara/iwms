package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.w */
public class C0587w implements Creator<Tile> {
    static void m4620a(Tile tile, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, tile.m4537a());
        C0448b.m3819a(parcel, 2, tile.f2815a);
        C0448b.m3819a(parcel, 3, tile.f2816b);
        C0448b.m3830a(parcel, 4, tile.f2817c, false);
        C0448b.m3815a(parcel, a);
    }

    public Tile m4621a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bArr = C0447a.m3803p(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public Tile[] m4622a(int i) {
        return new Tile[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4621a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4622a(i);
    }
}
