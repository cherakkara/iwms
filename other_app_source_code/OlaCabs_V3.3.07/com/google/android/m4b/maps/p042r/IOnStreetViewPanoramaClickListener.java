package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.d */
public interface IOnStreetViewPanoramaClickListener extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.d.a */
    public static abstract class IOnStreetViewPanoramaClickListener extends Binder implements IOnStreetViewPanoramaClickListener {

        /* renamed from: com.google.android.m4b.maps.r.d.a.a */
        static class IOnStreetViewPanoramaClickListener implements IOnStreetViewPanoramaClickListener {
            private IBinder f7716a;

            IOnStreetViewPanoramaClickListener(IBinder iBinder) {
                this.f7716a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7716a;
            }

            public final void m11129a(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaClickListener");
                    if (streetViewPanoramaOrientation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOrientation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7716a.transact(1, obtain, obtain2, 0);
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
                    StreetViewPanoramaOrientation a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaClickListener");
                    if (parcel.readInt() != 0) {
                        a = StreetViewPanoramaOrientation.CREATOR.m11043a(parcel);
                    } else {
                        a = null;
                    }
                    m11128a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11128a(StreetViewPanoramaOrientation streetViewPanoramaOrientation);
}
