package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.C0249s;
import com.google.android.gms.common.internal.C0249s.C0250a;
import com.google.android.gms.common.internal.C0411p;
import com.google.android.gms.common.internal.C0411p.C0412a;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.internal.bn.C0247a;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

public interface bo extends IInterface {

    /* renamed from: com.google.android.gms.internal.bo.a */
    public static abstract class C0493a extends Binder implements bo {

        /* renamed from: com.google.android.gms.internal.bo.a.a */
        private static class C0492a implements bo {
            private IBinder f2399a;

            C0492a(IBinder iBinder) {
                this.f2399a = iBinder;
            }

            public void m4092a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    this.f2399a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4093a(int i, Account account, bn bnVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(bnVar != null ? bnVar.asBinder() : null);
                    this.f2399a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4094a(C0411p c0411p, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeStrongBinder(c0411p != null ? c0411p.asBinder() : null);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.f2399a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4095a(zzc com_google_android_gms_common_internal_zzc, bn bnVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (com_google_android_gms_common_internal_zzc != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_common_internal_zzc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(bnVar != null ? bnVar.asBinder() : null);
                    this.f2399a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4096a(zzy com_google_android_gms_common_internal_zzy, C0249s c0249s) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (com_google_android_gms_common_internal_zzy != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_common_internal_zzy.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(c0249s != null ? c0249s.asBinder() : null);
                    this.f2399a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4097a(zzuw com_google_android_gms_internal_zzuw) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (com_google_android_gms_internal_zzuw != null) {
                        obtain.writeInt(1);
                        com_google_android_gms_internal_zzuw.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f2399a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m4098a(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f2399a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2399a;
            }
        }

        public static bo m4099a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof bo)) ? new C0492a(iBinder) : (bo) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z = false;
            Account account = null;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    zzc com_google_android_gms_common_internal_zzc;
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    if (parcel.readInt() != 0) {
                        com_google_android_gms_common_internal_zzc = (zzc) zzc.CREATOR.createFromParcel(parcel);
                    }
                    m4088a(com_google_android_gms_common_internal_zzc, C0247a.m3267a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    zzuw com_google_android_gms_internal_zzuw;
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    if (parcel.readInt() != 0) {
                        com_google_android_gms_internal_zzuw = (zzuw) zzuw.CREATOR.createFromParcel(parcel);
                    }
                    m4090a(com_google_android_gms_internal_zzuw);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    m4091a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    zzy com_google_android_gms_common_internal_zzy;
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    if (parcel.readInt() != 0) {
                        com_google_android_gms_common_internal_zzy = (zzy) zzy.CREATOR.createFromParcel(parcel);
                    }
                    m4089a(com_google_android_gms_common_internal_zzy, C0250a.m3272a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    m4085a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    int readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        account = (Account) Account.CREATOR.createFromParcel(parcel);
                    }
                    m4086a(readInt, account, C0247a.m3267a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
                    C0411p a = C0412a.m3557a(parcel.readStrongBinder());
                    int readInt2 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m4087a(a, readInt2, z);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.signin.internal.ISignInService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m4085a(int i) throws RemoteException;

    void m4086a(int i, Account account, bn bnVar) throws RemoteException;

    void m4087a(C0411p c0411p, int i, boolean z) throws RemoteException;

    void m4088a(zzc com_google_android_gms_common_internal_zzc, bn bnVar) throws RemoteException;

    void m4089a(zzy com_google_android_gms_common_internal_zzy, C0249s c0249s) throws RemoteException;

    void m4090a(zzuw com_google_android_gms_internal_zzuw) throws RemoteException;

    void m4091a(boolean z) throws RemoteException;
}
