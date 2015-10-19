package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.internal.c */
public class C0569c implements Creator<zze> {
    static void m4571a(zze com_google_android_gms_maps_model_internal_zze, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_maps_model_internal_zze.m4587a());
        C0448b.m3824a(parcel, 2, com_google_android_gms_maps_model_internal_zze.m4588b(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zze m4572a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        zza com_google_android_gms_maps_model_internal_zza = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    com_google_android_gms_maps_model_internal_zza = (zza) C0447a.m3782a(parcel, a, zza.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zze(i, com_google_android_gms_maps_model_internal_zza);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zze[] m4573a(int i) {
        return new zze[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4572a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4573a(i);
    }
}
