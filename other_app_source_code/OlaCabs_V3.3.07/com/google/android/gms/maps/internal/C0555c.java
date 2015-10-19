package com.google.android.gms.maps.internal;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.internal.c */
public class C0555c implements Creator<zzy> {
    static void m4472a(zzy com_google_android_gms_maps_internal_zzy, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_maps_internal_zzy.m4475a());
        C0448b.m3824a(parcel, 2, com_google_android_gms_maps_internal_zzy.m4476b(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzy m4473a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        Point point = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    point = (Point) C0447a.m3782a(parcel, a, Point.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzy(i, point);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzy[] m4474a(int i) {
        return new zzy[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4473a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4474a(i);
    }
}
