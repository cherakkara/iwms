package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate.IMapsEngineLayerDelegate;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.model.internal.i */
public interface IMapsEngineFeatureDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.i.a */
    public static abstract class IMapsEngineFeatureDelegate extends Binder implements IMapsEngineFeatureDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.i.a.a */
        static class IMapsEngineFeatureDelegate implements IMapsEngineFeatureDelegate {
            private IBinder f7658a;

            IMapsEngineFeatureDelegate(IBinder iBinder) {
                this.f7658a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7658a;
            }

            public final IMapsEngineLayerDelegate m10914b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    IMapsEngineLayerDelegate a = IMapsEngineLayerDelegate.m8274a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10915c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLng m10916d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLng a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(obtain2);
                    } else {
                        a = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return a;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10917e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10918f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10913a(IMapsEngineFeatureDelegate iMapsEngineFeatureDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    obtain.writeStrongBinder(iMapsEngineFeatureDelegate != null ? iMapsEngineFeatureDelegate.asBinder() : null);
                    this.f7658a.transact(6, obtain, obtain2, 0);
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

            public final int m10919g() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    this.f7658a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IMapsEngineFeatureDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
        }

        public static IMapsEngineFeatureDelegate m9547a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMapsEngineFeatureDelegate)) {
                return new IMapsEngineFeatureDelegate(iBinder);
            }
            return (IMapsEngineFeatureDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            String c;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    IMapsEngineLayerDelegate b = m9541b();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    c = m9542c();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    LatLng d = m9543d();
                    parcel2.writeNoException();
                    if (d != null) {
                        parcel2.writeInt(1);
                        d.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    c = m9544e();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    c = m9545f();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    boolean a = m9540a(IMapsEngineFeatureDelegate.m9547a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    i3 = m9546g();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IMapsEngineFeatureDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m9540a(IMapsEngineFeatureDelegate iMapsEngineFeatureDelegate);

    IMapsEngineLayerDelegate m9541b();

    String m9542c();

    LatLng m9543d();

    String m9544e();

    String m9545f();

    int m9546g();
}
