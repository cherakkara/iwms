package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.maps.model.LatLng;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.gms.location.places.b */
public class C0542b implements Creator<AddPlaceRequest> {
    static void m4400a(AddPlaceRequest addPlaceRequest, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3826a(parcel, 1, addPlaceRequest.m4385a(), false);
        C0448b.m3819a(parcel, (int) Constants.MILLIS_IN_A_SECOND, addPlaceRequest.f2658a);
        C0448b.m3824a(parcel, 2, addPlaceRequest.m4386b(), i, false);
        C0448b.m3826a(parcel, 3, addPlaceRequest.m4387c(), false);
        C0448b.m3827a(parcel, 4, addPlaceRequest.m4388d(), false);
        C0448b.m3826a(parcel, 5, addPlaceRequest.m4389e(), false);
        C0448b.m3824a(parcel, 6, addPlaceRequest.m4390f(), i, false);
        C0448b.m3815a(parcel, a);
    }

    public AddPlaceRequest m4401a(Parcel parcel) {
        Uri uri = null;
        int b = C0447a.m3786b(parcel);
        int i = 0;
        String str = null;
        List list = null;
        String str2 = null;
        LatLng latLng = null;
        String str3 = null;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str3 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    list = C0447a.m3812y(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    uri = (Uri) C0447a.m3782a(parcel, a, Uri.CREATOR);
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
            return new AddPlaceRequest(i, str3, latLng, str2, list, str, uri);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public AddPlaceRequest[] m4402a(int i) {
        return new AddPlaceRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4401a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4402a(i);
    }
}
