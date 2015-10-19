package com.google.android.m4b.maps.p042r;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.g */
public interface ISnapshotReadyCallback extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.g.a */
    public static abstract class ISnapshotReadyCallback extends Binder implements ISnapshotReadyCallback {

        /* renamed from: com.google.android.m4b.maps.r.g.a.a */
        static class ISnapshotReadyCallback implements ISnapshotReadyCallback {
            private IBinder f7719a;

            ISnapshotReadyCallback(IBinder iBinder) {
                this.f7719a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7719a;
            }

            public final void m11140a(Bitmap bitmap) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                    if (bitmap != null) {
                        obtain.writeInt(1);
                        bitmap.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7719a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11141a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7719a.transact(2, obtain, obtain2, 0);
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
                    Bitmap bitmap;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                    if (parcel.readInt() != 0) {
                        bitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(parcel);
                    } else {
                        bitmap = null;
                    }
                    m11138a(bitmap);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                    m11139a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11138a(Bitmap bitmap);

    void m11139a(IObjectWrapper iObjectWrapper);
}
