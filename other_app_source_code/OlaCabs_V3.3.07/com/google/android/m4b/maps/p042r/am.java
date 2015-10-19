package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMarkerDragListener */
/* renamed from: com.google.android.m4b.maps.r.am */
public interface am extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.am.a */
    public static abstract class IOnMarkerDragListener extends Binder implements am {

        /* renamed from: com.google.android.m4b.maps.r.am.a.a */
        static class IOnMarkerDragListener implements am {
            private IBinder f7709a;

            IOnMarkerDragListener(IBinder iBinder) {
                this.f7709a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7709a;
            }

            public final void m11113a(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7709a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11114b(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7709a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11115c(IMarkerDelegate iMarkerDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7709a.transact(3, obtain, obtain2, 0);
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    m11110a(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    m11111b(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    m11112c(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11110a(IMarkerDelegate iMarkerDelegate);

    void m11111b(IMarkerDelegate iMarkerDelegate);

    void m11112c(IMarkerDelegate iMarkerDelegate);
}
