package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.places.d */
public class C0544d implements Creator<zzc> {
    static void m4406a(zzc com_google_android_gms_location_places_zzc, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, com_google_android_gms_location_places_zzc.m4419b());
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_places_zzc.m4418a());
        C0448b.m3819a(parcel, 2, com_google_android_gms_location_places_zzc.m4420c());
        C0448b.m3824a(parcel, 3, com_google_android_gms_location_places_zzc.m4421d(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public zzc m4407a(Parcel parcel) {
        int i = 0;
        int b = C0447a.m3786b(parcel);
        int i2 = -1;
        PlaceFilter placeFilter = null;
        int i3 = 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    placeFilter = (PlaceFilter) C0447a.m3782a(parcel, a, PlaceFilter.CREATOR);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i3 = C0447a.m3793f(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new zzc(i3, i, i2, placeFilter);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzc[] m4408a(int i) {
        return new zzc[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4407a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4408a(i);
    }
}
