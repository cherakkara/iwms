package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.MapsEngineLayerInfo;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.model.internal.j */
public interface IMapsEngineLayerDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.j.a */
    public static abstract class IMapsEngineLayerDelegate extends Binder implements IMapsEngineLayerDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.j.a.a */
        static class IMapsEngineLayerDelegate implements IMapsEngineLayerDelegate {
            private IBinder f7659a;

            IMapsEngineLayerDelegate(IBinder iBinder) {
                this.f7659a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7659a;
            }

            public final void m10923b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final MapsEngineLayerInfo m10925d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    MapsEngineLayerInfo a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = MapsEngineLayerInfo.CREATOR.m11016a(obtain2);
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

            public final void m10920a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    obtain.writeFloat(f);
                    this.f7659a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10926f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10921a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7659a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10927h() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(6, obtain, obtain2, 0);
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

            public final void m10924b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7659a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10928j() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(8, obtain, obtain2, 0);
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

            public final boolean m10922a(IMapsEngineLayerDelegate iMapsEngineLayerDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    obtain.writeStrongBinder(iMapsEngineLayerDelegate != null ? iMapsEngineLayerDelegate.asBinder() : null);
                    this.f7659a.transact(9, obtain, obtain2, 0);
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

            public final int m10929l() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    this.f7659a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IMapsEngineLayerDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
        }

        public static IMapsEngineLayerDelegate m8274a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMapsEngineLayerDelegate)) {
                return new IMapsEngineLayerDelegate(iBinder);
            }
            return (IMapsEngineLayerDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean z;
            boolean h;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    m8267b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    MapsEngineLayerInfo d = m8269d();
                    parcel2.writeNoException();
                    if (d != null) {
                        parcel2.writeInt(1);
                        d.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    m8264a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    float f = m8270f();
                    parcel2.writeNoException();
                    parcel2.writeFloat(f);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8265a(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    h = m8271h();
                    parcel2.writeNoException();
                    if (h) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8268b(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    h = m8272j();
                    parcel2.writeNoException();
                    if (h) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    h = m8266a(IMapsEngineLayerDelegate.m8274a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (h) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    i3 = m8273l();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m8264a(float f);

    void m8265a(boolean z);

    boolean m8266a(IMapsEngineLayerDelegate iMapsEngineLayerDelegate);

    void m8267b();

    void m8268b(boolean z);

    MapsEngineLayerInfo m8269d();

    float m8270f();

    boolean m8271h();

    boolean m8272j();

    int m8273l();
}
