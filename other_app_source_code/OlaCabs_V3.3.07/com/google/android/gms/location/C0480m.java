package com.google.android.gms.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.m */
public interface C0480m extends IInterface {

    /* renamed from: com.google.android.gms.location.m.a */
    public static abstract class C0481a extends Binder implements C0480m {

        /* renamed from: com.google.android.gms.location.m.a.a */
        private static class C0537a implements C0480m {
            private IBinder f2657a;

            C0537a(IBinder iBinder) {
                this.f2657a = iBinder;
            }

            public void m4375a(zzh com_google_android_gms_location_zzh) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationResultListener");
                    if (com_google_android_gms_location_zzh != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_location_zzh.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2657a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2657a;
            }
        }

        public static C0480m m3990a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationResultListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0480m)) ? new C0537a(iBinder) : (C0480m) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.ILocationResultListener");
                    m3989a(parcel.readInt() != 0 ? zzh.CREATOR.m4380a(parcel) : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.ILocationResultListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m3989a(zzh com_google_android_gms_location_zzh) throws RemoteException;
}
