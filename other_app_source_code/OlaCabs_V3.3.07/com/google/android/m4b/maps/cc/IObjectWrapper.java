package com.google.android.m4b.maps.cc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: com.google.android.m4b.maps.cc.b */
public interface IObjectWrapper extends IInterface {

    /* renamed from: com.google.android.m4b.maps.cc.b.a */
    public static abstract class IObjectWrapper extends Binder implements IObjectWrapper {

        /* renamed from: com.google.android.m4b.maps.cc.b.a.a */
        static class IObjectWrapper implements IObjectWrapper {
            private IBinder f7261a;

            IObjectWrapper(IBinder iBinder) {
                this.f7261a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7261a;
            }
        }

        public IObjectWrapper() {
            attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static IObjectWrapper m10120a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IObjectWrapper)) {
                return new IObjectWrapper(iBinder);
            }
            return (IObjectWrapper) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
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
