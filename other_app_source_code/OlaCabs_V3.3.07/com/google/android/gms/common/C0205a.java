package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.a */
public class C0205a implements Creator<ConnectionResult> {
    static void m3163a(ConnectionResult connectionResult, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, connectionResult.f1982b);
        C0448b.m3819a(parcel, 2, connectionResult.m3161c());
        C0448b.m3824a(parcel, 3, connectionResult.m3162d(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public ConnectionResult m3164a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        PendingIntent pendingIntent = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    pendingIntent = (PendingIntent) C0447a.m3782a(parcel, a, PendingIntent.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new ConnectionResult(i2, i, pendingIntent);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public ConnectionResult[] m3165a(int i) {
        return new ConnectionResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3164a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3165a(i);
    }
}
