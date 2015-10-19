package com.google.android.m4b.maps.p053n;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.p048f.DataHolder;
import com.google.android.m4b.maps.p048f.DataHolderCreator;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.n.a */
public interface IPlacesCallbacks extends IInterface {

    /* renamed from: com.google.android.m4b.maps.n.a.a */
    public static abstract class IPlacesCallbacks extends Binder implements IPlacesCallbacks {

        /* renamed from: com.google.android.m4b.maps.n.a.a.a */
        static class IPlacesCallbacks implements IPlacesCallbacks {
            private IBinder f7666a;

            IPlacesCallbacks(IBinder iBinder) {
                this.f7666a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7666a;
            }

            public final void m11048a(DataHolder dataHolder) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7666a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11049b(DataHolder dataHolder) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7666a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11050c(DataHolder dataHolder) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7666a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IPlacesCallbacks m11051a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPlacesCallbacks)) {
                return new IPlacesCallbacks(iBinder);
            }
            return (IPlacesCallbacks) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            DataHolder dataHolder = null;
            DataHolderCreator dataHolderCreator;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        dataHolderCreator = DataHolder.CREATOR;
                        dataHolder = DataHolderCreator.m10313a(parcel);
                    }
                    m11045a(dataHolder);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        dataHolderCreator = DataHolder.CREATOR;
                        dataHolder = DataHolderCreator.m10313a(parcel);
                    }
                    m11046b(dataHolder);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        dataHolderCreator = DataHolder.CREATOR;
                        dataHolder = DataHolderCreator.m10313a(parcel);
                    }
                    m11047c(dataHolder);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11045a(DataHolder dataHolder);

    void m11046b(DataHolder dataHolder);

    void m11047c(DataHolder dataHolder);
}
