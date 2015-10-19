package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.internal.m */
public class C0507m implements Creator<zzlk> {
    static void m4137a(zzlk com_google_android_gms_internal_zzlk, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzlk.m4240d());
        C0448b.m3823a(parcel, 2, com_google_android_gms_internal_zzlk.m4241e(), false);
        C0448b.m3824a(parcel, 3, com_google_android_gms_internal_zzlk.m4242f(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzlk m4138a(Parcel parcel) {
        zzlh com_google_android_gms_internal_zzlh = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel2 = C0447a.m3777A(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    com_google_android_gms_internal_zzlh = (zzlh) C0447a.m3782a(parcel, a, zzlh.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzlk(i, parcel2, com_google_android_gms_internal_zzlh);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzlk[] m4139a(int i) {
        return new zzlk[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4138a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4139a(i);
    }
}
