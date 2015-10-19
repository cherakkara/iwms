package com.google.android.m4b.maps.p043e;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.e.k */
public final class StatusCreator implements Creator<Status> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int a = SafeParcelReader.m10468a(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    pendingIntent = (PendingIntent) SafeParcelReader.m10470a(parcel, readInt, PendingIntent.CREATOR);
                    break;
                case Constants.MILLIS_IN_A_SECOND /*1000*/:
                    i2 = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                default:
                    SafeParcelReader.m10472b(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == a) {
            return new Status(i2, i, str, pendingIntent);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }

    static void m10303a(Status status, Parcel parcel, int i) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10492a(parcel, 1, status.m10302e());
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, status.m10300c());
        SafeParcelWriter.m10498a(parcel, 2, status.m10299b(), false);
        SafeParcelWriter.m10496a(parcel, 3, status.m10298a(), i, false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
