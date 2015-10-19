package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.c */
public interface IOnStreetViewPanoramaChangeListener extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.c.a */
    public static abstract class IOnStreetViewPanoramaChangeListener extends Binder implements IOnStreetViewPanoramaChangeListener {

        /* renamed from: com.google.android.m4b.maps.r.c.a.a */
        static class IOnStreetViewPanoramaChangeListener implements IOnStreetViewPanoramaChangeListener {
            private IBinder f7715a;

            IOnStreetViewPanoramaChangeListener(IBinder iBinder) {
                this.f7715a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7715a;
            }

            public final void m11127a(StreetViewPanoramaLocation streetViewPanoramaLocation) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaChangeListener");
                    if (streetViewPanoramaLocation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaLocation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7715a.transact(1, obtain, obtain2, 0);
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
                    StreetViewPanoramaLocation a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaChangeListener");
                    if (parcel.readInt() != 0) {
                        a = StreetViewPanoramaLocation.CREATOR.m11040a(parcel);
                    } else {
                        a = null;
                    }
                    m11126a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11126a(StreetViewPanoramaLocation streetViewPanoramaLocation);
}
