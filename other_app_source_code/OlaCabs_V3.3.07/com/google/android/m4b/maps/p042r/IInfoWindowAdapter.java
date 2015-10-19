package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.w */
public interface IInfoWindowAdapter extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.w.a */
    public static abstract class IInfoWindowAdapter extends Binder implements IInfoWindowAdapter {

        /* renamed from: com.google.android.m4b.maps.r.w.a.a */
        static class IInfoWindowAdapter implements IInfoWindowAdapter {
            private IBinder f7727a;

            IInfoWindowAdapter(IBinder iBinder) {
                this.f7727a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7727a;
            }

            public final IObjectWrapper m11284a(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7727a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    IObjectWrapper a = IObjectWrapper.IObjectWrapper.m10120a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IObjectWrapper m11285b(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7727a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    IObjectWrapper a = IObjectWrapper.IObjectWrapper.m10120a(obtain2.readStrongBinder());
                    return a;
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
            IBinder iBinder = null;
            IObjectWrapper a;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                    a = m11282a(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                    a = m11283b(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m11282a(IMarkerDelegate iMarkerDelegate);

    IObjectWrapper m11283b(IMarkerDelegate iMarkerDelegate);
}
