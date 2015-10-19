package com.google.android.m4b.maps.p044k;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.k.c */
public interface ILocationListener extends IInterface {

    /* renamed from: com.google.android.m4b.maps.k.c.a */
    public static abstract class ILocationListener extends Binder implements ILocationListener {

        /* renamed from: com.google.android.m4b.maps.k.c.a.a */
        static class ILocationListener implements ILocationListener {
            private IBinder f7447a;

            ILocationListener(IBinder iBinder) {
                this.f7447a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7447a;
            }

            public final void m10546a(Location location) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationListener");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7447a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public ILocationListener() {
            attachInterface(this, "com.google.android.gms.location.ILocationListener");
        }

        public static ILocationListener m10547a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationListener)) {
                return new ILocationListener(iBinder);
            }
            return (ILocationListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    Location location;
                    parcel.enforceInterface("com.google.android.gms.location.ILocationListener");
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    m10545a(location);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.ILocationListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10545a(Location location);
}
