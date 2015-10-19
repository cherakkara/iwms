package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMarkerClickListener */
/* renamed from: com.google.android.m4b.maps.r.al */
public interface al extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.al.a */
    public static abstract class IOnMarkerClickListener extends Binder implements al {

        /* renamed from: com.google.android.m4b.maps.r.al.a.a */
        static class IOnMarkerClickListener implements al {
            private IBinder f7708a;

            IOnMarkerClickListener(IBinder iBinder) {
                this.f7708a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7708a;
            }

            public final boolean m11109a(IMarkerDelegate iMarkerDelegate) {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMarkerClickListener");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7708a.transact(1, obtain, obtain2, 0);
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

        public IOnMarkerClickListener() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IOnMarkerClickListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMarkerClickListener");
                    boolean a = m10058a(IMarkerDelegate.IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMarkerClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m10058a(IMarkerDelegate iMarkerDelegate);
}
