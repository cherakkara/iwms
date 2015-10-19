package com.google.android.gms.maps.model.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.internal.b */
public class C0568b implements Creator<zzc> {
    static void m4568a(zzc com_google_android_gms_maps_model_internal_zzc, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_maps_model_internal_zzc.m4584a());
        C0448b.m3819a(parcel, 2, com_google_android_gms_maps_model_internal_zzc.m4585b());
        C0448b.m3821a(parcel, 3, com_google_android_gms_maps_model_internal_zzc.m4586c(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzc m4569a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzc(i2, i, bundle);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzc[] m4570a(int i) {
        return new zzc[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4569a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4570a(i);
    }
}
