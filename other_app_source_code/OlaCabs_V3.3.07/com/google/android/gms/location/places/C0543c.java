package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collection;

/* renamed from: com.google.android.gms.location.places.c */
public class C0543c implements Creator<AutocompleteFilter> {
    static void m4403a(AutocompleteFilter autocompleteFilter, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3829a(parcel, 1, autocompleteFilter.m4391a());
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, autocompleteFilter.f2665a);
        C0448b.m3827a(parcel, 2, autocompleteFilter.f2667c, false);
        C0448b.m3815a(parcel, a);
    }

    public AutocompleteFilter m4404a(Parcel parcel) {
        boolean z = false;
        int b = C0447a.m3786b(parcel);
        Collection collection = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    collection = C0447a.m3812y(parcel, a);
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
            return new AutocompleteFilter(i, z, collection);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public AutocompleteFilter[] m4405a(int i) {
        return new AutocompleteFilter[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4404a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4405a(i);
    }
}
