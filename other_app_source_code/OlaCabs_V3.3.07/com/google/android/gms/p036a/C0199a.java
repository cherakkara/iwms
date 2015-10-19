package com.google.android.gms.p036a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.gms.a.a */
public interface C0199a extends IInterface {

    /* renamed from: com.google.android.gms.a.a.a */
    public static abstract class C0201a extends Binder implements C0199a {

        /* renamed from: com.google.android.gms.a.a.a.a */
        private static class C0200a implements C0199a {
            private IBinder f1968a;

            C0200a(IBinder iBinder) {
                this.f1968a = iBinder;
            }

            public IBinder asBinder() {
                return this.f1968a;
            }
        }

        public static C0199a m3147a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0199a)) ? new C0200a(iBinder) : (C0199a) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.dynamic.IObjectWrapper");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
