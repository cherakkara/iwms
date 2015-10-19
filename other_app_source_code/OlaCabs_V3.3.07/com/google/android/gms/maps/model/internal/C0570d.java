package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.maps.model.internal.d */
public interface C0570d extends IInterface {

    /* renamed from: com.google.android.gms.maps.model.internal.d.a */
    public static abstract class C0572a extends Binder implements C0570d {

        /* renamed from: com.google.android.gms.maps.model.internal.d.a.a */
        private static class C0571a implements C0570d {
            private IBinder f2835a;

            C0571a(IBinder iBinder) {
                this.f2835a = iBinder;
            }

            public Tile m4575a(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.f2835a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    Tile a = obtain2.readInt() != 0 ? Tile.CREATOR.m4621a(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2835a;
            }
        }

        public static C0570d m4576a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0570d)) ? new C0571a(iBinder) : (C0570d) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    Tile a = m4574a(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Tile m4574a(int i, int i2, int i3) throws RemoteException;
}
