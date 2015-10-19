package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.C0447a;
import com.google.android.gms.common.internal.safeparcel.C0447a.C0446a;
import com.google.android.gms.common.internal.safeparcel.C0448b;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.maps.model.m */
public class C0577m implements Creator<MarkerOptions> {
    static void m4596a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = C0448b.m3814a(parcel);
        C0448b.m3819a(parcel, 1, markerOptions.m4500a());
        C0448b.m3824a(parcel, 2, markerOptions.m4502c(), i, false);
        C0448b.m3826a(parcel, 3, markerOptions.m4503d(), false);
        C0448b.m3826a(parcel, 4, markerOptions.m4504e(), false);
        C0448b.m3822a(parcel, 5, markerOptions.m4501b(), false);
        C0448b.m3818a(parcel, 6, markerOptions.m4505f());
        C0448b.m3818a(parcel, 7, markerOptions.m4506g());
        C0448b.m3829a(parcel, 8, markerOptions.m4507h());
        C0448b.m3829a(parcel, 9, markerOptions.m4508i());
        C0448b.m3829a(parcel, 10, markerOptions.m4509j());
        C0448b.m3818a(parcel, 11, markerOptions.m4510k());
        C0448b.m3818a(parcel, 12, markerOptions.m4511l());
        C0448b.m3818a(parcel, 13, markerOptions.m4512m());
        C0448b.m3818a(parcel, 14, markerOptions.m4513n());
        C0448b.m3815a(parcel, a);
    }

    public MarkerOptions m4597a(Parcel parcel) {
        int b = C0447a.m3786b(parcel);
        int i = 0;
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = br.DEFAULT_BACKOFF_MULT;
        while (parcel.dataPosition() < b) {
            int a = C0447a.m3780a(parcel);
            switch (C0447a.m3779a(a)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = C0447a.m3793f(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) C0447a.m3782a(parcel, a, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str2 = C0447a.m3800m(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    iBinder = C0447a.m3801n(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    f = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f2 = C0447a.m3797j(parcel, a);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z = C0447a.m3790c(parcel, a);
                    break;
                case HTTP.HT /*9*/:
                    z2 = C0447a.m3790c(parcel, a);
                    break;
                case HTTP.LF /*10*/:
                    z3 = C0447a.m3790c(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    f3 = C0447a.m3797j(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    f4 = C0447a.m3797j(parcel, a);
                    break;
                case HTTP.CR /*13*/:
                    f5 = C0447a.m3797j(parcel, a);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    f6 = C0447a.m3797j(parcel, a);
                    break;
                default:
                    C0447a.m3787b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
        }
        throw new C0446a("Overread allowed size end=" + b, parcel);
    }

    public MarkerOptions[] m4598a(int i) {
        return new MarkerOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4597a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4598a(i);
    }
}
