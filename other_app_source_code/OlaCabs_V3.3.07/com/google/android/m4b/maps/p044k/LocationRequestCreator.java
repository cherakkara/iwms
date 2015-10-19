package com.google.android.m4b.maps.p044k;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.k.f */
public final class LocationRequestCreator implements Creator<LocationRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return LocationRequestCreator.m10554a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }

    public static LocationRequest m10554a(Parcel parcel) {
        boolean z = false;
        int a = SafeParcelReader.m10468a(parcel);
        int i = HttpStatus.SC_PROCESSING;
        long j = Constants.MILLIS_IN_AN_HOUR;
        long j2 = 600000;
        long j3 = Long.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        float f = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    j2 = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    z = SafeParcelReader.m10475c(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    j3 = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new LocationRequest(i3, i, j, j2, z, j3, i2, f);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    static void m10555a(LocationRequest locationRequest, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, locationRequest.f7448a);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, locationRequest.m10552b());
        SafeParcelWriter.m10493a(parcel, 2, locationRequest.f7449b);
        SafeParcelWriter.m10493a(parcel, 3, locationRequest.f7450c);
        SafeParcelWriter.m10501a(parcel, 4, locationRequest.f7451d);
        SafeParcelWriter.m10493a(parcel, 5, locationRequest.f7452e);
        SafeParcelWriter.m10492a(parcel, 6, locationRequest.f7453f);
        SafeParcelWriter.m10491a(parcel, 7, locationRequest.f7454g);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
