package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* compiled from: IOAuthTokenProvider */
/* renamed from: com.google.android.m4b.maps.r.ab */
public interface ab extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.ab.a */
    public static abstract class IOAuthTokenProvider extends Binder implements ab {

        /* renamed from: com.google.android.m4b.maps.r.ab.a.a */
        static class IOAuthTokenProvider implements ab {
            private IBinder f7698a;

            IOAuthTokenProvider(IBinder iBinder) {
                this.f7698a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7698a;
            }

            public final String m11089i() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                    this.f7698a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11088a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                    obtain.writeString(str);
                    this.f7698a.transact(2, obtain, obtain2, 0);
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
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                    String i3 = m9186i();
                    parcel2.writeNoException();
                    parcel2.writeString(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                    m9185a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m9185a(String str);

    String m9186i();
}
