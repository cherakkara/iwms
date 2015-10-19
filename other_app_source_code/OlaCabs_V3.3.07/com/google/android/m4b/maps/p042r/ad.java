package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnIndoorStateChangeListener */
/* renamed from: com.google.android.m4b.maps.r.ad */
public interface ad extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ad.a */
    public static abstract class IOnIndoorStateChangeListener extends Binder implements ad {

        /* renamed from: com.google.android.m4b.maps.r.ad.a.a */
        static class IOnIndoorStateChangeListener implements ad {
            private IBinder f7700a;

            IOnIndoorStateChangeListener(IBinder iBinder) {
                this.f7700a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7700a;
            }

            public final void m11093a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                    this.f7700a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11094a(IIndoorBuildingDelegate iIndoorBuildingDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                    obtain.writeStrongBinder(iIndoorBuildingDelegate != null ? iIndoorBuildingDelegate.asBinder() : null);
                    this.f7700a.transact(2, obtain, obtain2, 0);
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                    m11091a();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                    m11092a(IIndoorBuildingDelegate.IIndoorBuildingDelegate.m9360a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11091a();

    void m11092a(IIndoorBuildingDelegate iIndoorBuildingDelegate);
}
