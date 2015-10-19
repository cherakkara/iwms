package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMapClickListener */
/* renamed from: com.google.android.m4b.maps.r.ag */
public interface ag extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ag.a */
    public static abstract class IOnMapClickListener extends Binder implements ag {

        /* renamed from: com.google.android.m4b.maps.r.ag.a.a */
        static class IOnMapClickListener implements ag {
            private IBinder f7703a;

            IOnMapClickListener(IBinder iBinder) {
                this.f7703a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7703a;
            }

            public final void m11100a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapClickListener");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7703a.transact(1, obtain, obtain2, 0);
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
                    LatLng a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapClickListener");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a = null;
                    }
                    m11099a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMapClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11099a(LatLng latLng);
}
