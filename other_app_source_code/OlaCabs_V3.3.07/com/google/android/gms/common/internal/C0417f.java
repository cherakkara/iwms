package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.f */
public class C0417f implements Creator<zzc> {
    static void m3569a(zzc com_google_android_gms_common_internal_zzc, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_internal_zzc.f2322a);
        C0448b.m3822a(parcel, 2, com_google_android_gms_common_internal_zzc.f2323b, false);
        C0448b.m3831a(parcel, 3, com_google_android_gms_common_internal_zzc.f2324c, i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzc m3570a(Parcel parcel) {
        Scope[] scopeArr = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        IBinder iBinder = null;
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
                    scopeArr = (Scope[]) C0447a.m3788b(parcel, a, Scope.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzc(i, iBinder, scopeArr);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzc[] m3571a(int i) {
        return new zzc[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3570a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3571a(i);
    }
}
