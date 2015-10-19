package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.maps.c */
public class C0552c implements Creator<StreetViewPanoramaOptions> {
    static void m4466a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, streetViewPanoramaOptions.m4452a());
        C0448b.m3824a(parcel, 2, streetViewPanoramaOptions.m4458g(), i, false);
        C0448b.m3826a(parcel, 3, streetViewPanoramaOptions.m4461j(), false);
        C0448b.m3824a(parcel, 4, streetViewPanoramaOptions.m4459h(), i, false);
        C0448b.m3825a(parcel, 5, streetViewPanoramaOptions.m4460i(), false);
        C0448b.m3816a(parcel, 6, streetViewPanoramaOptions.m4453b());
        C0448b.m3816a(parcel, 7, streetViewPanoramaOptions.m4454c());
        C0448b.m3816a(parcel, 8, streetViewPanoramaOptions.m4455d());
        C0448b.m3816a(parcel, 9, streetViewPanoramaOptions.m4456e());
        C0448b.m3816a(parcel, 10, streetViewPanoramaOptions.m4457f());
        C0448b.m3815a(parcel, a);
    }

    public StreetViewPanoramaOptions m4467a(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int b2 = C0447a.m3786b(parcel);
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < b2) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) C0447a.m3782a(parcel, a, StreetViewPanoramaCamera.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    num = C0447a.m3794g(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    b6 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    b5 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    b4 = C0447a.m3791d(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    b3 = C0447a.m3791d(parcel, a);
                    break;
                case HTTP.LF /*10*/:
                    b = C0447a.m3791d(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b2) {
            return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b6, b5, b4, b3, b);
        }
        throw new C0446a("Overread allowed size end=" + b2, parcel);
    }

    public StreetViewPanoramaOptions[] m4468a(int i) {
        return new StreetViewPanoramaOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4467a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4468a(i);
    }
}
