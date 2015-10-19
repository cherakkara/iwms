package com.google.android.gms.location;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.location.l */
public interface C0477l extends IInterface {

    /* renamed from: com.google.android.gms.location.l.a */
    public static abstract class C0478a extends Binder implements C0477l {

        /* renamed from: com.google.android.gms.location.l.a.a */
        private static class C0536a implements C0477l {
            private IBinder f2656a;

            C0536a(IBinder iBinder) {
                this.f2656a = iBinder;
            }

            public void m4374a(Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationListener");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2656a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2656a;
            }
        }

        public C0478a() {
            attachInterface(this, "com.google.android.gms.location.ILocationListener");
        }

        public static C0477l m3986a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0477l)) ? new C0536a(iBinder) : (C0477l) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.ILocationListener");
                    m3985a(parcel.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.ILocationListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m3985a(Location location) throws RemoteException;
}
