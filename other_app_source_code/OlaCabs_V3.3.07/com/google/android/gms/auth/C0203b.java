package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.auth.b */
public class C0203b implements Creator<AccountChangeEventsRequest> {
    static void m3151a(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, accountChangeEventsRequest.f1975a);
        C0448b.m3819a(parcel, 2, accountChangeEventsRequest.f1976b);
        C0448b.m3826a(parcel, 3, accountChangeEventsRequest.f1977c, false);
        C0448b.m3824a(parcel, 4, accountChangeEventsRequest.f1978d, i, false);
        C0448b.m3815a(parcel, a);
    }

    public AccountChangeEventsRequest m3152a(Parcel parcel) {
        Account account = null;
        int i = 0;
        int b = C0447a.m3786b(parcel);
        String str = null;
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
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    account = (Account) C0447a.m3782a(parcel, a, Account.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new AccountChangeEventsRequest(i2, i, str, account);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public AccountChangeEventsRequest[] m3153a(int i) {
        return new AccountChangeEventsRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3152a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3153a(i);
    }
}
