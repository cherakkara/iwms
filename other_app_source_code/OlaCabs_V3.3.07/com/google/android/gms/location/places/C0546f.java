package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.gms.location.places.f */
public class C0546f implements Creator<zzf> {
    static void m4412a(zzf com_google_android_gms_location_places_zzf, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, com_google_android_gms_location_places_zzf.f2686b);
        C0448b.m3824a(parcel, 2, com_google_android_gms_location_places_zzf.m4422a(), i, false);
        C0448b.m3820a(parcel, 3, com_google_android_gms_location_places_zzf.m4423b());
        C0448b.m3819a(parcel, 4, com_google_android_gms_location_places_zzf.m4424c());
        C0448b.m3820a(parcel, 5, com_google_android_gms_location_places_zzf.m4425d());
        C0448b.m3815a(parcel, a);
    }

    public zzf m4413a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = zzf.f2685a;
        int i2 = HttpStatus.SC_PROCESSING;
        long j2 = Long.MAX_VALUE;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    placeFilter = (PlaceFilter) C0447a.m3782a(parcel, a, PlaceFilter.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j = C0447a.m3795h(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    j2 = C0447a.m3795h(parcel, a);
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
            return new zzf(i, placeFilter, j, i2, j2);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public zzf[] m4414a(int i) {
        return new zzf[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4413a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4414a(i);
    }
}
