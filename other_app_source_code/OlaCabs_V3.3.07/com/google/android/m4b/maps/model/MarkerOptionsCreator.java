package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.model.p */
public class MarkerOptionsCreator implements Creator<MarkerOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m11022a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m11023a(i);
    }

    public MarkerOptions m11022a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
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
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    iBinder = SafeParcelReader.m10484l(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    z2 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case HTTP.LF /*10*/:
                    z3 = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    f3 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    f4 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case HTTP.CR /*13*/:
                    f5 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    f6 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public MarkerOptions[] m11023a(int i) {
        return new MarkerOptions[i];
    }

    static void m11021a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, markerOptions.m10758a());
        SafeParcelWriter.m10496a(parcel, 2, markerOptions.m10762c(), i, false);
        SafeParcelWriter.m10498a(parcel, 3, markerOptions.m10763d(), false);
        SafeParcelWriter.m10498a(parcel, 4, markerOptions.m10764e(), false);
        SafeParcelWriter.m10495a(parcel, 5, markerOptions.m10761b(), false);
        SafeParcelWriter.m10491a(parcel, 6, markerOptions.m10766g());
        SafeParcelWriter.m10491a(parcel, 7, markerOptions.m10767h());
        SafeParcelWriter.m10501a(parcel, 8, markerOptions.m10768i());
        SafeParcelWriter.m10501a(parcel, 9, markerOptions.m10769j());
        SafeParcelWriter.m10501a(parcel, 10, markerOptions.m10770k());
        SafeParcelWriter.m10491a(parcel, 11, markerOptions.m10771l());
        SafeParcelWriter.m10491a(parcel, 12, markerOptions.m10772m());
        SafeParcelWriter.m10491a(parcel, 13, markerOptions.m10773n());
        SafeParcelWriter.m10491a(parcel, 14, markerOptions.m10774o());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
