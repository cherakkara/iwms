package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.v */
public class C0454v implements Creator<zzy> {
    static void m3857a(zzy com_google_android_gms_common_internal_zzy, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_common_internal_zzy.f2337a);
        C0448b.m3824a(parcel, 2, com_google_android_gms_common_internal_zzy.m3874a(), i, false);
        C0448b.m3819a(parcel, 3, com_google_android_gms_common_internal_zzy.m3875b());
        C0448b.m3815a(parcel, a);
    }

    public zzy m3858a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        Account account = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            Account account2;
            int f;
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    int i3 = i;
                    account2 = account;
                    f = C0447a.m3793f(parcel, a);
                    a = i3;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    f = i2;
                    Account account3 = (Account) C0447a.m3782a(parcel, a, Account.CREATOR);
                    a = i;
                    account2 = account3;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    a = C0447a.m3793f(parcel, a);
                    account2 = account;
                    f = i2;
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    a = i;
                    account2 = account;
                    f = i2;
                    break;
            }
            i2 = f;
            account = account2;
            i = a;
        }
        if (parcel.dataPosition() == b) {
            return new zzy(i2, account, i);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzy[] m3859a(int i) {
        return new zzy[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3858a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3859a(i);
    }
}
