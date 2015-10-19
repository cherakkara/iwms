package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* compiled from: IOnMapsEngineFeatureClickListener */
/* renamed from: com.google.android.m4b.maps.r.ak */
public interface ak extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ak.a */
    public static abstract class IOnMapsEngineFeatureClickListener extends Binder implements ak {

        /* renamed from: com.google.android.m4b.maps.r.ak.a.a */
        static class IOnMapsEngineFeatureClickListener implements ak {
            private IBinder f7707a;

            IOnMapsEngineFeatureClickListener(IBinder iBinder) {
                this.f7707a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7707a;
            }

            public final void m11107a(List<IBinder> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                    obtain.writeBinderList(list);
                    this.f7707a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11108b(List<IBinder> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                    obtain.writeBinderList(list);
                    this.f7707a.transact(2, obtain, obtain2, 0);
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                    m11105a(parcel.createBinderArrayList());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                    m11106b(parcel.createBinderArrayList());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m11105a(List<IBinder> list);

    void m11106b(List<IBinder> list);
}
