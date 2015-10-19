package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

public class bl implements Creator<zzuw> {
    static void m4078a(zzuw com_google_android_gms_internal_zzuw, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzuw.f2601a);
        C0448b.m3829a(parcel, 2, com_google_android_gms_internal_zzuw.f2602b);
        C0448b.m3838c(parcel, 3, com_google_android_gms_internal_zzuw.f2603c, false);
        C0448b.m3815a(parcel, a);
    }

    public zzuw m4079a(Parcel parcel) {
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    list = C0447a.m3789c(parcel, a, Scope.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzuw(i, z, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzuw[] m4080a(int i) {
        return new zzuw[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4079a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4080a(i);
    }
}
