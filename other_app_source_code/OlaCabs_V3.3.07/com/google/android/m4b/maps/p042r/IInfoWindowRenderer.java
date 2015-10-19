package com.google.android.m4b.maps.p042r;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.r.x */
public interface IInfoWindowRenderer extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.x.a */
    public static abstract class IInfoWindowRenderer extends Binder implements IInfoWindowRenderer {

        /* renamed from: com.google.android.m4b.maps.r.x.a.a */
        static class IInfoWindowRenderer implements IInfoWindowRenderer {
            private IBinder f7728a;

            IInfoWindowRenderer(IBinder iBinder) {
                this.f7728a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7728a;
            }

            public final Bitmap m11286a(IMarkerDelegate iMarkerDelegate, int i, int i2) {
                Bitmap bitmap = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IInfoWindowRenderer");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.f7728a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bitmap;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IInfoWindowRenderer() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IInfoWindowRenderer");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IInfoWindowRenderer");
                    Bitmap a = m9383a(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IInfoWindowRenderer");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Bitmap m9383a(IMarkerDelegate iMarkerDelegate, int i, int i2);
}
