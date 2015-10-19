package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.s */
public interface C0249s extends IInterface {

    /* renamed from: com.google.android.gms.common.internal.s.a */
    public static abstract class C0250a extends Binder implements C0249s {

        /* renamed from: com.google.android.gms.common.internal.s.a.a */
        private static class C0445a implements C0249s {
            private IBinder f2308a;

            C0445a(IBinder iBinder) {
                this.f2308a = iBinder;
            }

            public void m3776a(zzaa com_google_android_gms_common_internal_zzaa) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    if (com_google_android_gms_common_internal_zzaa != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_common_internal_zzaa.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2308a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2308a;
            }
        }

        public C0250a() {
            attachInterface(this, "com.google.android.gms.common.internal.IResolveAccountCallbacks");
        }

        public static C0249s m3272a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0249s)) ? new C0445a(iBinder) : (C0249s) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    m3271a(parcel.readInt() != 0 ? (zzaa) zzaa.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.common.internal.IResolveAccountCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m3271a(zzaa com_google_android_gms_common_internal_zzaa) throws RemoteException;
}
