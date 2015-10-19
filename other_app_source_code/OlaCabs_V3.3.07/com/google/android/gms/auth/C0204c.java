package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.auth.c */
public class C0204c implements Creator<AccountChangeEventsResponse> {
    static void m3154a(AccountChangeEventsResponse accountChangeEventsResponse, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, accountChangeEventsResponse.f1979a);
        C0448b.m3838c(parcel, 2, accountChangeEventsResponse.f1980b, false);
        C0448b.m3815a(parcel, a);
    }

    public AccountChangeEventsResponse m3155a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = C0447a.m3789c(parcel, a, AccountChangeEvent.CREATOR);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new AccountChangeEventsResponse(i, list);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public AccountChangeEventsResponse[] m3156a(int i) {
        return new AccountChangeEventsResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3155a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3156a(i);
    }
}
