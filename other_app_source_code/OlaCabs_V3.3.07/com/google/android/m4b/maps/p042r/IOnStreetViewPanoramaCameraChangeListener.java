package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.b */
public interface IOnStreetViewPanoramaCameraChangeListener extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.b.a */
    public static abstract class IOnStreetViewPanoramaCameraChangeListener extends Binder implements IOnStreetViewPanoramaCameraChangeListener {

        /* renamed from: com.google.android.m4b.maps.r.b.a.a */
        static class IOnStreetViewPanoramaCameraChangeListener implements IOnStreetViewPanoramaCameraChangeListener {
            private IBinder f7714a;

            IOnStreetViewPanoramaCameraChangeListener(IBinder iBinder) {
                this.f7714a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7714a;
            }

            public final void m11125a(StreetViewPanoramaCamera streetViewPanoramaCamera) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    if (streetViewPanoramaCamera != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaCamera.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7714a.transact(1, obtain, obtain2, 0);
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
                    StreetViewPanoramaCamera a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    if (parcel.readInt() != 0) {
                        a = StreetViewPanoramaCamera.CREATOR.m11034a(parcel);
                    } else {
                        a = null;
                    }
                    m11124a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11124a(StreetViewPanoramaCamera streetViewPanoramaCamera);
}
