package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.internal.f */
public class C0500f implements Creator<zzky> {
    static void m4116a(zzky com_google_android_gms_internal_zzky, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzky.m4183a());
        C0448b.m3824a(parcel, 2, com_google_android_gms_internal_zzky.m4184b(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzky m4117a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        zzla com_google_android_gms_internal_zzla = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    com_google_android_gms_internal_zzla = (zzla) C0447a.m3782a(parcel, a, zzla.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzky(i, com_google_android_gms_internal_zzla);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzky[] m4118a(int i) {
        return new zzky[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4117a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4118a(i);
    }
}
