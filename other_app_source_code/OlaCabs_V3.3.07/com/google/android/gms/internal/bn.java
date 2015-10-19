package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.sothree.slidinguppanel.p086a.R.R;

public interface bn extends IInterface {

    /* renamed from: com.google.android.gms.internal.bn.a */
    public static abstract class C0247a extends Binder implements bn {

        /* renamed from: com.google.android.gms.internal.bn.a.a */
        private static class C0491a implements bn {
            private IBinder f2398a;

            C0491a(IBinder iBinder) {
                this.f2398a = iBinder;
            }

            public void m4083a(ConnectionResult connectionResult, zzut com_google_android_gms_internal_zzut) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (connectionResult != null) {
                        obtain.writeInt(1);
                        connectionResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (com_google_android_gms_internal_zzut != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_internal_zzut.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2398a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4084a(Status status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2398a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2398a;
            }
        }

        public C0247a() {
            attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static bn m3267a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof bn)) ? new C0491a(iBinder) : (bn) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Status status = null;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    m3265a(parcel.readInt() != 0 ? ConnectionResult.CREATOR.m3164a(parcel) : null, parcel.readInt() != 0 ? (zzut) zzut.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (parcel.readInt() != 0) {
                        status = Status.CREATOR.m3237a(parcel);
                    }
                    m3266a(status);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.signin.internal.ISignInCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m3265a(ConnectionResult connectionResult, zzut com_google_android_gms_internal_zzut) throws RemoteException;

    void m3266a(Status status) throws RemoteException;
}
