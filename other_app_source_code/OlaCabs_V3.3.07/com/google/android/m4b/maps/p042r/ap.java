package com.google.android.m4b.maps.p042r;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMyLocationChangeListener */
/* renamed from: com.google.android.m4b.maps.r.ap */
public interface ap extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ap.a */
    public static abstract class IOnMyLocationChangeListener extends Binder implements ap {

        /* renamed from: com.google.android.m4b.maps.r.ap.a.a */
        static class IOnMyLocationChangeListener implements ap {
            private IBinder f7713a;

            IOnMyLocationChangeListener(IBinder iBinder) {
                this.f7713a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7713a;
            }

            public final void m11123a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7713a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11122a(Location location) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7713a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                    m11121a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    Location location;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    m11120a(location);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11120a(Location location);

    void m11121a(IObjectWrapper iObjectWrapper);
}
