package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.places.a */
public class C0541a implements Creator<PlaceReport> {
    static void m4397a(PlaceReport placeReport, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, placeReport.f2677a);
        C0448b.m3826a(parcel, 2, placeReport.m4394a(), false);
        C0448b.m3826a(parcel, 3, placeReport.m4395b(), false);
        C0448b.m3826a(parcel, 4, placeReport.m4396c(), false);
        C0448b.m3815a(parcel, a);
    }

    public PlaceReport m4398a(Parcel parcel) {
        String str = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new PlaceReport(i, str3, str2, str);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public PlaceReport[] m4399a(int i) {
        return new PlaceReport[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4398a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4399a(i);
    }
}
