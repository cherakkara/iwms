package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.model.internal.g */
public interface IIndoorBuildingDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.g.a */
    public static abstract class IIndoorBuildingDelegate extends Binder implements IIndoorBuildingDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.g.a.a */
        static class IIndoorBuildingDelegate implements IIndoorBuildingDelegate {
            private IBinder f7656a;

            IIndoorBuildingDelegate(IBinder iBinder) {
                this.f7656a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7656a;
            }

            public final int m10902a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    this.f7656a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10904b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    this.f7656a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List<IBinder> m10906d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    this.f7656a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    List<IBinder> createBinderArrayList = obtain2.createBinderArrayList();
                    return createBinderArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10905c() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    this.f7656a.transact(4, obtain, obtain2, 0);
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

            public final boolean m10903a(IIndoorBuildingDelegate iIndoorBuildingDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    obtain.writeStrongBinder(iIndoorBuildingDelegate != null ? iIndoorBuildingDelegate.asBinder() : null);
                    this.f7656a.transact(5, obtain, obtain2, 0);
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

            public final int m10907e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    this.f7656a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IIndoorBuildingDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
        }

        public static IIndoorBuildingDelegate m9360a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IIndoorBuildingDelegate)) {
                return new IIndoorBuildingDelegate(iBinder);
            }
            return (IIndoorBuildingDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean c;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m9354a();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m9356b();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    List d = m9358d();
                    parcel2.writeNoException();
                    parcel2.writeBinderList(d);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    c = m9357c();
                    parcel2.writeNoException();
                    if (c) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    c = m9355a(IIndoorBuildingDelegate.m9360a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (c) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    i3 = m9359e();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int m9354a();

    boolean m9355a(IIndoorBuildingDelegate iIndoorBuildingDelegate);

    int m9356b();

    boolean m9357c();

    List<IBinder> m9358d();

    int m9359e();
}
