package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.p042r.IStreetViewPanoramaDelegate.IStreetViewPanoramaDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.e */
public interface IOnStreetViewPanoramaReadyCallback extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.e.a */
    public static abstract class IOnStreetViewPanoramaReadyCallback extends Binder implements IOnStreetViewPanoramaReadyCallback {

        /* renamed from: com.google.android.m4b.maps.r.e.a.a */
        static class IOnStreetViewPanoramaReadyCallback implements IOnStreetViewPanoramaReadyCallback {
            private IBinder f7717a;

            IOnStreetViewPanoramaReadyCallback(IBinder iBinder) {
                this.f7717a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7717a;
            }

            public final void m11131a(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaReadyCallback");
                    obtain.writeStrongBinder(iStreetViewPanoramaDelegate != null ? iStreetViewPanoramaDelegate.asBinder() : null);
                    this.f7717a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IOnStreetViewPanoramaReadyCallback m11132a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaReadyCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnStreetViewPanoramaReadyCallback)) {
                return new IOnStreetViewPanoramaReadyCallback(iBinder);
            }
            return (IOnStreetViewPanoramaReadyCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaReadyCallback");
                    m11130a(IStreetViewPanoramaDelegate.m8666a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaReadyCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11130a(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate);
}
