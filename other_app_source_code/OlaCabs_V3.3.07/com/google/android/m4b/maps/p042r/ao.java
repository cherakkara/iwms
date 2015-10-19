package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMyLocationButtonClickListener */
/* renamed from: com.google.android.m4b.maps.r.ao */
public interface ao extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ao.a */
    public static abstract class IOnMyLocationButtonClickListener extends Binder implements ao {

        /* renamed from: com.google.android.m4b.maps.r.ao.a.a */
        static class IOnMyLocationButtonClickListener implements ao {
            private IBinder f7712a;

            IOnMyLocationButtonClickListener(IBinder iBinder) {
                this.f7712a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7712a;
            }

            public final boolean m11119a() {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMyLocationButtonClickListener");
                    this.f7712a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMyLocationButtonClickListener");
                    boolean a = m11118a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMyLocationButtonClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m11118a();
}
