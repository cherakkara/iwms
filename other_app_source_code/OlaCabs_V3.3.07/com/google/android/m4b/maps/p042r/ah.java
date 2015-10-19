package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMapLoadedCallback */
/* renamed from: com.google.android.m4b.maps.r.ah */
public interface ah extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ah.a */
    public static abstract class IOnMapLoadedCallback extends Binder implements ah {

        /* renamed from: com.google.android.m4b.maps.r.ah.a.a */
        static class IOnMapLoadedCallback implements ah {
            private IBinder f7704a;

            IOnMapLoadedCallback(IBinder iBinder) {
                this.f7704a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7704a;
            }

            public final void m11101a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapLoadedCallback");
                    this.f7704a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IOnMapLoadedCallback() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IOnMapLoadedCallback");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapLoadedCallback");
                    m9072a();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMapLoadedCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9072a();
}
