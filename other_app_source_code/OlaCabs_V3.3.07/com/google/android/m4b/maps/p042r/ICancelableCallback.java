package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.t */
public interface ICancelableCallback extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.t.a */
    public static abstract class ICancelableCallback extends Binder implements ICancelableCallback {

        /* renamed from: com.google.android.m4b.maps.r.t.a.a */
        static class ICancelableCallback implements ICancelableCallback {
            private IBinder f7725a;

            ICancelableCallback(IBinder iBinder) {
                this.f7725a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7725a;
            }

            public final void m11217a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ICancelableCallback");
                    this.f7725a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11218b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ICancelableCallback");
                    this.f7725a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ICancelableCallback m11219a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.ICancelableCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICancelableCallback)) {
                return new ICancelableCallback(iBinder);
            }
            return (ICancelableCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICancelableCallback");
                    m11215a();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ICancelableCallback");
                    m11216b();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.ICancelableCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11215a();

    void m11216b();
}
