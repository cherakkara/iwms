package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.google.android.gms.maps.model.CameraPosition;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.maps.a */
public class C0550a implements Creator<GoogleMapOptions> {
    static void m4462a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, googleMapOptions.m4439a());
        C0448b.m3816a(parcel, 2, googleMapOptions.m4440b());
        C0448b.m3816a(parcel, 3, googleMapOptions.m4441c());
        C0448b.m3819a(parcel, 4, googleMapOptions.m4450l());
        C0448b.m3824a(parcel, 5, googleMapOptions.m4451m(), i, false);
        C0448b.m3816a(parcel, 6, googleMapOptions.m4442d());
        C0448b.m3816a(parcel, 7, googleMapOptions.m4443e());
        C0448b.m3816a(parcel, 8, googleMapOptions.m4444f());
        C0448b.m3816a(parcel, 9, googleMapOptions.m4445g());
        C0448b.m3816a(parcel, 10, googleMapOptions.m4446h());
        C0448b.m3816a(parcel, 11, googleMapOptions.m4447i());
        C0448b.m3816a(parcel, 12, googleMapOptions.m4448j());
        C0448b.m3816a(parcel, 14, googleMapOptions.m4449k());
        C0448b.m3815a(parcel, a);
    }

    public GoogleMapOptions m4463a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        int i2 = 0;
        CameraPosition cameraPosition = null;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        byte b7 = (byte) 0;
        byte b8 = (byte) 0;
        byte b9 = (byte) 0;
        byte b10 = (byte) 0;
        byte b11 = (byte) 0;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    b2 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    b3 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    cameraPosition = (CameraPosition) C0447a.m3782a(parcel, a, CameraPosition.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    b4 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    b5 = C0447a.m3791d(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    b6 = C0447a.m3791d(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    b7 = C0447a.m3791d(parcel, a);
                    break;
                case HTTP.LF /*10*/:
                    b8 = C0447a.m3791d(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    b9 = C0447a.m3791d(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    b10 = C0447a.m3791d(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    b11 = C0447a.m3791d(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GoogleMapOptions(i, b2, b3, i2, cameraPosition, b4, b5, b6, b7, b8, b9, b10, b11);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public GoogleMapOptions[] m4464a(int i) {
        return new GoogleMapOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4463a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4464a(i);
    }
}
