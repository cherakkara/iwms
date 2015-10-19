package com.google.android.m4b.maps.cd;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.cd.a */
public interface IApiTokenService extends IInterface {

    /* renamed from: com.google.android.m4b.maps.cd.a.a */
    public static abstract class IApiTokenService extends Binder implements IApiTokenService {

        /* renamed from: com.google.android.m4b.maps.cd.a.a.a */
        static class IApiTokenService implements IApiTokenService {
            private IBinder f7263a;

            IApiTokenService(IBinder iBinder) {
                this.f7263a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7263a;
            }

            public final Bundle m10133a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Bundle bundle2;
                    obtain.writeInterfaceToken("com.google.android.gms.maps.auth.IApiTokenService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7263a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                    } else {
                        bundle2 = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IApiTokenService m10134a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.auth.IApiTokenService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IApiTokenService)) {
                return new IApiTokenService(iBinder);
            }
            return (IApiTokenService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    Bundle bundle;
                    parcel.enforceInterface("com.google.android.gms.maps.auth.IApiTokenService");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    bundle = m10132a(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.auth.IApiTokenService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Bundle m10132a(Bundle bundle);
}
