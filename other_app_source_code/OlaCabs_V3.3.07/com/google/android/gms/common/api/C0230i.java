package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.api.i */
public class C0230i implements Creator<Status> {
    static void m3236a(Status status, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, status.m3176g());
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, status.m3173d());
        C0448b.m3826a(parcel, 2, status.m3172c(), false);
        C0448b.m3824a(parcel, 3, status.m3171b(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public Status m3237a(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    pendingIntent = (PendingIntent) C0447a.m3782a(parcel, a, PendingIntent.CREATOR);
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
            return new Status(i2, i, str, pendingIntent);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public Status[] m3238a(int i) {
        return new Status[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3237a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3238a(i);
    }
}
