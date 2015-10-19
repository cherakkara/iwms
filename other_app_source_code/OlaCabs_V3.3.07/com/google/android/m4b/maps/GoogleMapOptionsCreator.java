package com.google.android.m4b.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.d */
public class GoogleMapOptionsCreator implements Creator<GoogleMapOptions> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m10233a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m10234a(i);
    }

    public GoogleMapOptions m10233a(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        byte b = (byte) 0;
        byte b2 = (byte) 0;
        int i2 = 0;
        CameraPosition cameraPosition = null;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        byte b7 = (byte) 0;
        byte b8 = (byte) 0;
        byte b9 = (byte) 0;
        byte b10 = (byte) 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    b = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    b2 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    cameraPosition = (CameraPosition) SafeParcelReader.m10470a(parcel, readInt, CameraPosition.CREATOR);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    b3 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    b4 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    b5 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    b6 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case HTTP.LF /*10*/:
                    b7 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    b8 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    b9 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    b10 = SafeParcelReader.m10476d(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new GoogleMapOptions(i, b, b2, i2, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public GoogleMapOptions[] m10234a(int i) {
        return new GoogleMapOptions[i];
    }

    static void m10232a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, googleMapOptions.m4629a());
        SafeParcelWriter.m10489a(parcel, 2, googleMapOptions.m4633b());
        SafeParcelWriter.m10489a(parcel, 3, googleMapOptions.m4635c());
        SafeParcelWriter.m10492a(parcel, 4, googleMapOptions.m4654n());
        SafeParcelWriter.m10496a(parcel, 5, googleMapOptions.m4655o(), i, false);
        SafeParcelWriter.m10489a(parcel, 6, googleMapOptions.m4637d());
        SafeParcelWriter.m10489a(parcel, 7, googleMapOptions.m4639e());
        SafeParcelWriter.m10489a(parcel, 8, googleMapOptions.m4641f());
        SafeParcelWriter.m10489a(parcel, 9, googleMapOptions.m4643g());
        SafeParcelWriter.m10489a(parcel, 10, googleMapOptions.m4645h());
        SafeParcelWriter.m10489a(parcel, 11, googleMapOptions.m4647i());
        SafeParcelWriter.m10489a(parcel, 12, googleMapOptions.m4649j());
        SafeParcelWriter.m10489a(parcel, 14, googleMapOptions.m4651k());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
