package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.internal.a */
public class C0567a implements Creator<zza> {
    static void m4565a(zza com_google_android_gms_maps_model_internal_zza, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_maps_model_internal_zza.m4580a());
        C0448b.m3816a(parcel, 2, com_google_android_gms_maps_model_internal_zza.m4581b());
        C0448b.m3821a(parcel, 3, com_google_android_gms_maps_model_internal_zza.m4582c(), false);
        C0448b.m3824a(parcel, 4, com_google_android_gms_maps_model_internal_zza.m4583d(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zza m4566a(Parcel parcel) {
        Bitmap bitmap = null;
        byte b = (byte) 0;
        int b2 = C0447a.m3786b(parcel);
        Bundle bundle = null;
        int i = 0;
        while (parcel.dataPosition() < b2) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    b = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bitmap = (Bitmap) C0447a.m3782a(parcel, a, Bitmap.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b2) {
            return new zza(i, b, bundle, bitmap);
        }
        throw new C0446a("Overread allowed size end=" + b2, parcel);
    }

    public zza[] m4567a(int i) {
        return new zza[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4566a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4567a(i);
    }
}
