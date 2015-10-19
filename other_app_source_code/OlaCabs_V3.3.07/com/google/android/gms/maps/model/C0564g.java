package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.maps.model.g */
public class C0564g implements Creator<GroundOverlayOptions> {
    static void m4558a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, groundOverlayOptions.m4487b());
        C0448b.m3822a(parcel, 2, groundOverlayOptions.m4486a(), false);
        C0448b.m3824a(parcel, 3, groundOverlayOptions.m4488c(), i, false);
        C0448b.m3818a(parcel, 4, groundOverlayOptions.m4489d());
        C0448b.m3818a(parcel, 5, groundOverlayOptions.m4490e());
        C0448b.m3824a(parcel, 6, groundOverlayOptions.m4491f(), i, false);
        C0448b.m3818a(parcel, 7, groundOverlayOptions.m4492g());
        C0448b.m3818a(parcel, 8, groundOverlayOptions.m4493h());
        C0448b.m3829a(parcel, 9, groundOverlayOptions.m4497l());
        C0448b.m3818a(parcel, 10, groundOverlayOptions.m4494i());
        C0448b.m3818a(parcel, 11, groundOverlayOptions.m4495j());
        C0448b.m3818a(parcel, 12, groundOverlayOptions.m4496k());
        C0448b.m3815a(parcel, a);
    }

    public GroundOverlayOptions m4559a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    latLngBounds = (LatLngBounds) C0447a.m3782a(parcel, a, LatLngBounds.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f3 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    f4 = C0447a.m3797j(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case HTTP.LF /*10*/:
                    f5 = C0447a.m3797j(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    f6 = C0447a.m3797j(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    f7 = C0447a.m3797j(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public GroundOverlayOptions[] m4560a(int i) {
        return new GroundOverlayOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4559a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4560a(i);
    }
}
