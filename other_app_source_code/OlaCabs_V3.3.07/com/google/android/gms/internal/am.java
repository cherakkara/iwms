package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

public class am implements Creator<zzpi> {
    static void m4008a(zzpi com_google_android_gms_internal_zzpi, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_internal_zzpi.f2503a);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_internal_zzpi.m4258a());
        C0448b.m3824a(parcel, 2, com_google_android_gms_internal_zzpi.f2504b, i, false);
        C0448b.m3822a(parcel, 3, com_google_android_gms_internal_zzpi.m4259b(), false);
        C0448b.m3824a(parcel, 4, com_google_android_gms_internal_zzpi.f2506d, i, false);
        C0448b.m3822a(parcel, 5, com_google_android_gms_internal_zzpi.m4260c(), false);
        C0448b.m3815a(parcel, a);
    }

    public zzpi m4009a(Parcel parcel) {
        IBinder iBinder = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        int i2 = 1;
        PendingIntent pendingIntent = null;
        IBinder iBinder2 = null;
        zzpg com_google_android_gms_internal_zzpg = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    com_google_android_gms_internal_zzpg = (zzpg) C0447a.m3782a(parcel, a, zzpg.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    iBinder2 = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    pendingIntent = (PendingIntent) C0447a.m3782a(parcel, a, PendingIntent.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    iBinder = C0447a.m3801n(parcel, a);
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
            return new zzpi(i, i2, com_google_android_gms_internal_zzpg, iBinder2, pendingIntent, iBinder);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzpi[] m4010a(int i) {
        return new zzpi[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4009a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4010a(i);
    }
}
