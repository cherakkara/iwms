package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.o */
public class C0539o implements Creator<zzh> {
    static void m4379a(zzh com_google_android_gms_location_zzh, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3838c(parcel, 1, com_google_android_gms_location_zzh.f2705b, false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_zzh.m4436b());
        C0448b.m3815a(parcel, a);
    }

    public zzh m4380a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        List list = zzh.f2704a;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list = C0447a.m3789c(parcel, a, Location.CREATOR);
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
            return new zzh(i, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzh[] m4381a(int i) {
        return new zzh[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4380a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4381a(i);
    }
}
