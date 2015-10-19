package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;
import com.sothree.slidinguppanel.p086a.R.R;

public interface ai extends IInterface {

    /* renamed from: com.google.android.gms.internal.ai.a */
    public static abstract class C0475a extends Binder implements ai {

        /* renamed from: com.google.android.gms.internal.ai.a.a */
        private static class C0474a implements ai {
            private IBinder f2362a;

            C0474a(IBinder iBinder) {
                this.f2362a = iBinder;
            }

            public void m3983a(LocationSettingsResult locationSettingsResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.ISettingsCallbacks");
                    if (locationSettingsResult != null) {
                        obtain.writeInt(1);
                        locationSettingsResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2362a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2362a;
            }
        }

        public C0475a() {
            attachInterface(this, "com.google.android.gms.location.internal.ISettingsCallbacks");
        }

        public static ai m3984a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ai)) ? new C0474a(iBinder) : (ai) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
                    m3982a(parcel.readInt() != 0 ? LocationSettingsResult.CREATOR.m4366a(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.internal.ISettingsCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m3982a(LocationSettingsResult locationSettingsResult) throws RemoteException;
}
