package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.b */
public class C0528b implements Creator<DetectedActivity> {
    static void m4351a(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, detectedActivity.f2609b);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, detectedActivity.m4314c());
        C0448b.m3819a(parcel, 2, detectedActivity.f2610c);
        C0448b.m3815a(parcel, a);
    }

    public DetectedActivity m4352a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new DetectedActivity(i3, i2, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public DetectedActivity[] m4353a(int i) {
        return new DetectedActivity[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4352a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4353a(i);
    }
}
