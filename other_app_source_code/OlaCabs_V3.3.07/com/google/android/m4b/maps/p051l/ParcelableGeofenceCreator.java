package com.google.android.m4b.maps.p051l;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.l.g */
public final class ParcelableGeofenceCreator implements Creator<ParcelableGeofence> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    j = SafeParcelReader.m10480h(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    s = SafeParcelReader.m10477e(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    d = SafeParcelReader.m10482j(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    d2 = SafeParcelReader.m10482j(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    f = SafeParcelReader.m10481i(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    i3 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case HTTP.HT /*9*/:
                    i4 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new ParcelableGeofence(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ParcelableGeofence[i];
    }

    static void m10670a(ParcelableGeofence parcelableGeofence, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10498a(parcel, 1, parcelableGeofence.m10665f(), false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, parcelableGeofence.m10660a());
        SafeParcelWriter.m10493a(parcel, 2, parcelableGeofence.m10666g());
        SafeParcelWriter.m10500a(parcel, 3, parcelableGeofence.m10661b());
        SafeParcelWriter.m10490a(parcel, 4, parcelableGeofence.m10662c());
        SafeParcelWriter.m10490a(parcel, 5, parcelableGeofence.m10663d());
        SafeParcelWriter.m10491a(parcel, 6, parcelableGeofence.m10664e());
        SafeParcelWriter.m10492a(parcel, 7, parcelableGeofence.m10667h());
        SafeParcelWriter.m10492a(parcel, 8, parcelableGeofence.m10668i());
        SafeParcelWriter.m10492a(parcel, 9, parcelableGeofence.m10669j());
        SafeParcelWriter.m10488a(parcel, a);
    }
}
