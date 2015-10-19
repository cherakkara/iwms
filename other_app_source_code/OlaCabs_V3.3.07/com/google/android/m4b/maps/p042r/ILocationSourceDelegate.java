package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.p042r.af.IOnLocationChangeListener.IOnLocationChangeListener;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.y */
public interface ILocationSourceDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.y.a */
    public static abstract class ILocationSourceDelegate extends Binder implements ILocationSourceDelegate {

        /* renamed from: com.google.android.m4b.maps.r.y.a.a */
        static class ILocationSourceDelegate implements ILocationSourceDelegate {
            private IBinder f7729a;

            ILocationSourceDelegate(IBinder iBinder) {
                this.f7729a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7729a;
            }

            public final void m11287a(af afVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                    obtain.writeStrongBinder(afVar != null ? afVar.asBinder() : null);
                    this.f7729a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11288c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                    this.f7729a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public ILocationSourceDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.ILocationSourceDelegate");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    af afVar;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        afVar = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnLocationChangeListener");
                        afVar = (queryLocalInterface == null || !(queryLocalInterface instanceof af)) ? new IOnLocationChangeListener(readStrongBinder) : (af) queryLocalInterface;
                    }
                    m9426a(afVar);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                    m9427c();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9426a(af afVar);

    void m9427c();
}
