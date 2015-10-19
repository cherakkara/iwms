package com.google.android.m4b.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.i */
public class StreetViewPanoramaOptionsCreator implements Creator<StreetViewPanoramaOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10514a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10515a(i);
    }

    public StreetViewPanoramaOptions m10514a(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int a = SafeParcelReader.m10468a(parcel);
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) SafeParcelReader.m10470a(parcel, readInt, StreetViewPanoramaCamera.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    latLng = (LatLng) SafeParcelReader.m10470a(parcel, readInt, LatLng.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    num = SafeParcelReader.m10479g(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    b5 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    b4 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    b3 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    b2 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case HTTP.LF /*10*/:
                    b = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b5, b4, b3, b2, b);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public StreetViewPanoramaOptions[] m10515a(int i) {
        return new StreetViewPanoramaOptions[i];
    }

    static void m10513a(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, streetViewPanoramaOptions.m4664a());
        SafeParcelWriter.m10496a(parcel, 2, streetViewPanoramaOptions.m4670g(), i, false);
        SafeParcelWriter.m10498a(parcel, 3, streetViewPanoramaOptions.m4673j(), false);
        SafeParcelWriter.m10496a(parcel, 4, streetViewPanoramaOptions.m4671h(), i, false);
        SafeParcelWriter.m10497a(parcel, 5, streetViewPanoramaOptions.m4672i(), false);
        SafeParcelWriter.m10489a(parcel, 6, streetViewPanoramaOptions.m4665b());
        SafeParcelWriter.m10489a(parcel, 7, streetViewPanoramaOptions.m4666c());
        SafeParcelWriter.m10489a(parcel, 8, streetViewPanoramaOptions.m4667d());
        SafeParcelWriter.m10489a(parcel, 9, streetViewPanoramaOptions.m4668e());
        SafeParcelWriter.m10489a(parcel, 10, streetViewPanoramaOptions.m4669f());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
