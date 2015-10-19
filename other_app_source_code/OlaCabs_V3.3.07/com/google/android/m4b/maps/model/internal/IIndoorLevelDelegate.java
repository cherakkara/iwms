package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.h */
public interface IIndoorLevelDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.h.a */
    public static abstract class IIndoorLevelDelegate extends Binder implements IIndoorLevelDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.h.a.a */
        static class IIndoorLevelDelegate implements IIndoorLevelDelegate {
            private IBinder f7657a;

            IIndoorLevelDelegate(IBinder iBinder) {
                this.f7657a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7657a;
            }

            public final String m10908a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    this.f7657a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10910b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    this.f7657a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10911c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    this.f7657a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10909a(IIndoorLevelDelegate iIndoorLevelDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    obtain.writeStrongBinder(iIndoorLevelDelegate != null ? iIndoorLevelDelegate.asBinder() : null);
                    this.f7657a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10912d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    this.f7657a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IIndoorLevelDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
        }

        public static IIndoorLevelDelegate m8767a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IIndoorLevelDelegate)) {
                return new IIndoorLevelDelegate(iBinder);
            }
            return (IIndoorLevelDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String a;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    a = m8762a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    a = m8764b();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    m8765c();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    boolean a2 = m8763a(IIndoorLevelDelegate.m8767a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    int d = m8766d();
                    parcel2.writeNoException();
                    parcel2.writeInt(d);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String m8762a();

    boolean m8763a(IIndoorLevelDelegate iIndoorLevelDelegate);

    String m8764b();

    void m8765c();

    int m8766d();
}
