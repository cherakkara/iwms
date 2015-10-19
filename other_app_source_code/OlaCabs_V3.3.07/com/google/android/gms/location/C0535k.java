package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.k */
public class C0535k implements Creator<zzb> {
    static void m4371a(zzb com_google_android_gms_location_zzb, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3827a(parcel, 1, com_google_android_gms_location_zzb.m4434b(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_zzb.m4433a());
        C0448b.m3815a(parcel, a);
    }

    public zzb m4372a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list = C0447a.m3812y(parcel, a);
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
            return new zzb(i, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzb[] m4373a(int i) {
        return new zzb[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4372a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4373a(i);
    }
}
