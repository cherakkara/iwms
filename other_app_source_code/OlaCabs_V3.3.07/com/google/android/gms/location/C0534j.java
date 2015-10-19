package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.internal.zzpk;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.j */
public class C0534j implements Creator<GeofencingRequest> {
    static void m4368a(GeofencingRequest geofencingRequest, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3838c(parcel, 1, geofencingRequest.m4316b(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, geofencingRequest.m4315a());
        C0448b.m3819a(parcel, 2, geofencingRequest.m4317c());
        C0448b.m3815a(parcel, a);
    }

    public GeofencingRequest m4369a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    list = C0447a.m3789c(parcel, a, zzpk.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GeofencingRequest(i2, list, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public GeofencingRequest[] m4370a(int i) {
        return new GeofencingRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4369a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4370a(i);
    }
}
