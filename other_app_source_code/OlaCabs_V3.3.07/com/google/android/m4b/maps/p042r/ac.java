package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.CameraPosition;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnCameraChangeListener */
/* renamed from: com.google.android.m4b.maps.r.ac */
public interface ac extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ac.a */
    public static abstract class IOnCameraChangeListener extends Binder implements ac {

        /* renamed from: com.google.android.m4b.maps.r.ac.a.a */
        static class IOnCameraChangeListener implements ac {
            private IBinder f7699a;

            IOnCameraChangeListener(IBinder iBinder) {
                this.f7699a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7699a;
            }

            public final void m11090a(CameraPosition cameraPosition) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnCameraChangeListener");
                    if (cameraPosition != null) {
                        obtain.writeInt(1);
                        cameraPosition.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7699a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IOnCameraChangeListener() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IOnCameraChangeListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    CameraPosition a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnCameraChangeListener");
                    if (parcel.readInt() != 0) {
                        a = CameraPosition.CREATOR.m10828a(parcel);
                    } else {
                        a = null;
                    }
                    m9031a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnCameraChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9031a(CameraPosition cameraPosition);
}
