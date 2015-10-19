package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class bc implements Creator<zzqu> {
    static void m4055a(zzqu com_google_android_gms_internal_zzqu, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, com_google_android_gms_internal_zzqu.m4307a(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzqu.f2585a);
        C0448b.m3815a(parcel, a);
    }

    public zzqu m4056a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzqu(i, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzqu[] m4057a(int i) {
        return new zzqu[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4056a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4057a(i);
    }
}
