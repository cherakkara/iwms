package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.Tile;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.o */
public interface ITileProviderDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.o.a */
    public static abstract class ITileProviderDelegate extends Binder implements ITileProviderDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.o.a.a */
        static class ITileProviderDelegate implements ITileProviderDelegate {
            private IBinder f7664a;

            ITileProviderDelegate(IBinder iBinder) {
                this.f7664a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7664a;
            }

            public final Tile m11005a(int i, int i2, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Tile a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ITileProviderDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.f7664a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = Tile.CREATOR.m10812a(obtain2);
                    } else {
                        a = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ITileProviderDelegate m11006a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.ITileProviderDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ITileProviderDelegate)) {
                return new ITileProviderDelegate(iBinder);
            }
            return (ITileProviderDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ITileProviderDelegate");
                    Tile a = m11004a(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.ITileProviderDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Tile m11004a(int i, int i2, int i3);
}
