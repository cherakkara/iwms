package com.google.android.m4b.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.model.g */
public class GroundOverlayOptionsCreator implements Creator<GroundOverlayOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10836a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10837a(i);
    }

    public GroundOverlayOptions m10836a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
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
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    iBinder = SafeParcelReader.m10484l(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    f2 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    latLngBounds = (LatLngBounds) SafeParcelReader.m10470a(parcel, readInt, LatLngBounds.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f3 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    f4 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case HTTP.LF /*10*/:
                    f5 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    f6 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    f7 = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public GroundOverlayOptions[] m10837a(int i) {
        return new GroundOverlayOptions[i];
    }

    static void m10835a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, groundOverlayOptions.m10725b());
        SafeParcelWriter.m10495a(parcel, 2, groundOverlayOptions.m10721a(), false);
        SafeParcelWriter.m10496a(parcel, 3, groundOverlayOptions.m10727d(), i, false);
        SafeParcelWriter.m10491a(parcel, 4, groundOverlayOptions.m10728e());
        SafeParcelWriter.m10491a(parcel, 5, groundOverlayOptions.m10729f());
        SafeParcelWriter.m10496a(parcel, 6, groundOverlayOptions.m10730g(), i, false);
        SafeParcelWriter.m10491a(parcel, 7, groundOverlayOptions.m10731h());
        SafeParcelWriter.m10491a(parcel, 8, groundOverlayOptions.m10732i());
        SafeParcelWriter.m10501a(parcel, 9, groundOverlayOptions.m10736m());
        SafeParcelWriter.m10491a(parcel, 10, groundOverlayOptions.m10733j());
        SafeParcelWriter.m10491a(parcel, 11, groundOverlayOptions.m10734k());
        SafeParcelWriter.m10491a(parcel, 12, groundOverlayOptions.m10735l());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
