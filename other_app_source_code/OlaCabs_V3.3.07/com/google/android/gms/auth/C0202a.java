package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.auth.a */
public class C0202a implements Creator<AccountChangeEvent> {
    static void m3148a(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, accountChangeEvent.f1969a);
        C0448b.m3820a(parcel, 2, accountChangeEvent.f1970b);
        C0448b.m3826a(parcel, 3, accountChangeEvent.f1971c, false);
        C0448b.m3819a(parcel, 4, accountChangeEvent.f1972d);
        C0448b.m3819a(parcel, 5, accountChangeEvent.f1973e);
        C0448b.m3826a(parcel, 6, accountChangeEvent.f1974f, false);
        C0448b.m3815a(parcel, a);
    }

    public AccountChangeEvent m3149a(Parcel parcel) {
        String str = null;
        int i = 0;
        int b = C0447a.m3786b(parcel);
        long j = 0;
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new AccountChangeEvent(i3, j, str2, i2, i, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public AccountChangeEvent[] m3150a(int i) {
        return new AccountChangeEvent[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3149a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3150a(i);
    }
}
