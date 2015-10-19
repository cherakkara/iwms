package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.data.b */
public class C0269b implements Creator<DataHolder> {
    static void m3374a(DataHolder dataHolder, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3832a(parcel, 1, dataHolder.m3365c(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, dataHolder.m3364b());
        C0448b.m3831a(parcel, 2, dataHolder.m3366d(), i, false);
        C0448b.m3819a(parcel, 3, dataHolder.m3367e());
        C0448b.m3821a(parcel, 4, dataHolder.m3368f(), false);
        C0448b.m3815a(parcel, a);
    }

    public DataHolder m3375a(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int b = C0447a.m3786b(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    strArr = C0447a.m3811x(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    cursorWindowArr = (CursorWindow[]) C0447a.m3788b(parcel, a, CursorWindow.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    bundle = C0447a.m3802o(parcel, a);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new C0446a("Overread allowed size end=" + b, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.m3363a();
        return dataHolder;
    }

    public DataHolder[] m3376a(int i) {
        return new DataHolder[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m3375a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m3376a(i);
    }
}
