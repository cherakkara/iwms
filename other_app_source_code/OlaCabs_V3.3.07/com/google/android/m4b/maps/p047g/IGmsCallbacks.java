package com.google.android.m4b.maps.p047g;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.g.h */
public interface IGmsCallbacks extends IInterface {

    /* renamed from: com.google.android.m4b.maps.g.h.a */
    public static abstract class IGmsCallbacks extends Binder implements IGmsCallbacks {

        /* renamed from: com.google.android.m4b.maps.g.h.a.a */
        static class IGmsCallbacks implements IGmsCallbacks {
            private IBinder f7424a;

            IGmsCallbacks(IBinder iBinder) {
                this.f7424a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7424a;
            }

            public final void m10383a(int i, IBinder iBinder, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsCallbacks");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7424a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IGmsCallbacks() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
        }

        public static IGmsCallbacks m10328a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsCallbacks)) {
                return new IGmsCallbacks(iBinder);
            }
            return (IGmsCallbacks) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    Bundle bundle;
                    parcel.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                    int readInt = parcel.readInt();
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m10327a(readInt, readStrongBinder, bundle);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m10327a(int i, IBinder iBinder, Bundle bundle);
}
