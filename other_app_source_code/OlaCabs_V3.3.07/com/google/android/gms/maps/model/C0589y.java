package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.y */
public class C0589y implements Creator<TileOverlayOptions> {
    static void m4624a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, tileOverlayOptions.m4539a());
        C0448b.m3822a(parcel, 2, tileOverlayOptions.m4540b(), false);
        C0448b.m3829a(parcel, 3, tileOverlayOptions.m4542d());
        C0448b.m3818a(parcel, 4, tileOverlayOptions.m4541c());
        C0448b.m3829a(parcel, 5, tileOverlayOptions.m4543e());
        C0448b.m3815a(parcel, a);
    }

    public TileOverlayOptions m4625a(Parcel parcel) {
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new TileOverlayOptions(i, iBinder, z, f, z2);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public TileOverlayOptions[] m4626a(int i) {
        return new TileOverlayOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4625a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4626a(i);
    }
}
