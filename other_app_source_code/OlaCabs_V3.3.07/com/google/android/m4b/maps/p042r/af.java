package com.google.android.m4b.maps.p042r;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnLocationChangeListener */
/* renamed from: com.google.android.m4b.maps.r.af */
public interface af extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.af.a */
    public static abstract class IOnLocationChangeListener extends Binder implements af {

        /* renamed from: com.google.android.m4b.maps.r.af.a.a */
        static class IOnLocationChangeListener implements af {
            private IBinder f7702a;

            IOnLocationChangeListener(IBinder iBinder) {
                this.f7702a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7702a;
            }

            public final void m11098a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7702a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11097a(Location location) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7702a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IOnLocationChangeListener() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IOnLocationChangeListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                    m8418a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    Location location;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    m8417a(location);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m8417a(Location location);

    void m8418a(IObjectWrapper iObjectWrapper);
}
