package com.google.android.m4b.maps.p047g;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import com.google.android.m4b.maps.p037h.SafeParcelReader;
import com.google.android.m4b.maps.p037h.SafeParcelWriter;
import com.google.android.m4b.maps.p047g.ClientSettings.ClientSettings;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.g.k */
public final class ParcelableClientSettingsCreator implements Creator<ClientSettings> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int a = SafeParcelReader.m10468a(parcel);
        List list = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < a) {
            int readInt = parcel.readInt();
            switch (SupportMenu.USER_MASK & readInt) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str2 = SafeParcelReader.m10483k(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    list = SafeParcelReader.m10486n(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    i = SafeParcelReader.m10478f(parcel, readInt);
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    str = SafeParcelReader.m10483k(parcel, readInt);
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
            return new ClientSettings(i2, str2, list, i, str);
        }
        throw new SafeParcelReader.SafeParcelReader("Overread allowed size end=" + a, parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ClientSettings[i];
    }

    static void m10458a(ClientSettings clientSettings, Parcel parcel) {
        int a = SafeParcelWriter.m10487a(parcel);
        SafeParcelWriter.m10498a(parcel, 1, clientSettings.m10317a(), false);
        SafeParcelWriter.m10492a(parcel, (int) Constants.MILLIS_IN_A_SECOND, clientSettings.m10321e());
        SafeParcelWriter.m10499a(parcel, 2, clientSettings.m10320d(), false);
        SafeParcelWriter.m10492a(parcel, 3, clientSettings.m10318b());
        SafeParcelWriter.m10498a(parcel, 4, clientSettings.m10319c(), false);
        SafeParcelWriter.m10488a(parcel, a);
    }
}
