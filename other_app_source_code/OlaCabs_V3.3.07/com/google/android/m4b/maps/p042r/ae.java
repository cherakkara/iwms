package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnInfoWindowClickListener */
/* renamed from: com.google.android.m4b.maps.r.ae */
public interface ae extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ae.a */
    public static abstract class IOnInfoWindowClickListener extends Binder implements ae {

        /* renamed from: com.google.android.m4b.maps.r.ae.a.a */
        static class IOnInfoWindowClickListener implements ae {
            private IBinder f7701a;

            IOnInfoWindowClickListener(IBinder iBinder) {
                this.f7701a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7701a;
            }

            public final void m11096a(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnInfoWindowClickListener");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7701a.transact(1, obtain, obtain2, 0);
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnInfoWindowClickListener");
                    m11095a(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnInfoWindowClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11095a(IMarkerDelegate iMarkerDelegate);
}
