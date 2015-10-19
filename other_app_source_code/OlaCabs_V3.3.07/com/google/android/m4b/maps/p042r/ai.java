package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOnMapLongClickListener */
/* renamed from: com.google.android.m4b.maps.r.ai */
public interface ai extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ai.a */
    public static abstract class IOnMapLongClickListener extends Binder implements ai {

        /* renamed from: com.google.android.m4b.maps.r.ai.a.a */
        static class IOnMapLongClickListener implements ai {
            private IBinder f7705a;

            IOnMapLongClickListener(IBinder iBinder) {
                this.f7705a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7705a;
            }

            public final void m11103a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapLongClickListener");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7705a.transact(1, obtain, obtain2, 0);
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
                    LatLng a;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapLongClickListener");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a = null;
                    }
                    m11102a(a);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMapLongClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11102a(LatLng latLng);
}
