package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.gms.common.internal.p */
public interface C0411p extends IInterface {

    /* renamed from: com.google.android.gms.common.internal.p.a */
    public static abstract class C0412a extends Binder implements C0411p {

        /* renamed from: com.google.android.gms.common.internal.p.a.a */
        private static class C0440a implements C0411p {
            private IBinder f2305a;

            C0440a(IBinder iBinder) {
                this.f2305a = iBinder;
            }

            public Account m3682a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
                    this.f2305a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    Account account = obtain2.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return account;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f2305a;
            }
        }

        public static C0411p m3557a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0411p)) ? new C0440a(iBinder) : (C0411p) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
                    Account a = m3556a();
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.common.internal.IAccountAccessor");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Account m3556a() throws RemoteException;
}
