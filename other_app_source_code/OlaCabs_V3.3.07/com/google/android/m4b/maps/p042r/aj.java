package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.p042r.IGoogleMapDelegate.IGoogleMapDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMapReadyCallback */
/* renamed from: com.google.android.m4b.maps.r.aj */
public interface aj extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.aj.a */
    public static abstract class IOnMapReadyCallback extends Binder implements aj {

        /* renamed from: com.google.android.m4b.maps.r.aj.a.a */
        static class IOnMapReadyCallback implements aj {
            private IBinder f7706a;

            IOnMapReadyCallback(IBinder iBinder) {
                this.f7706a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7706a;
            }

            public final void m11104a(IGoogleMapDelegate iGoogleMapDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapReadyCallback");
                    obtain.writeStrongBinder(iGoogleMapDelegate != null ? iGoogleMapDelegate.asBinder() : null);
                    this.f7706a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IOnMapReadyCallback() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IOnMapReadyCallback");
        }

        public static aj m10517a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMapReadyCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof aj)) {
                return new IOnMapReadyCallback(iBinder);
            }
            return (aj) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapReadyCallback");
                    m10516a(IGoogleMapDelegate.m9176a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMapReadyCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10516a(IGoogleMapDelegate iGoogleMapDelegate);
}
