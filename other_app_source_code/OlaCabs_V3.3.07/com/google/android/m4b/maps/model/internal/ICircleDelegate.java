package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.model.internal.e */
public interface ICircleDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.e.a */
    public static abstract class ICircleDelegate extends Binder implements ICircleDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.e.a.a */
        static class ICircleDelegate implements ICircleDelegate {
            private IBinder f7654a;

            ICircleDelegate(IBinder iBinder) {
                this.f7654a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7654a;
            }

            public final void m10871j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10862a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10866a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7654a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLng m10872k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLng a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(4, obtain, obtain2, 0);
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

            public final void m10863a(double d) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeDouble(d);
                    this.f7654a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final double m10873l() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    double readDouble = obtain2.readDouble();
                    return readDouble;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10864a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.f7654a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10876o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10865a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.f7654a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10874m() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10870b(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.f7654a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10875n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10869b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.f7654a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10877p() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10867a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7654a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10878q() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(16, obtain, obtain2, 0);
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

            public final boolean m10868a(ICircleDelegate iCircleDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    obtain.writeStrongBinder(iCircleDelegate != null ? iCircleDelegate.asBinder() : null);
                    this.f7654a.transact(17, obtain, obtain2, 0);
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

            public final int m10879r() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    this.f7654a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public ICircleDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.ICircleDelegate");
        }

        public static ICircleDelegate m9002a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICircleDelegate)) {
                return new ICircleDelegate(iBinder);
            }
            return (ICircleDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            float o;
            boolean q;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8993j();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    String a = m8984a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    LatLng a2;
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a2 = null;
                    }
                    m8988a(a2);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    LatLng k = m8994k();
                    parcel2.writeNoException();
                    if (k != null) {
                        parcel2.writeInt(1);
                        k.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8985a(parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    double l = m8995l();
                    parcel2.writeNoException();
                    parcel2.writeDouble(l);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8986a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    o = m8998o();
                    parcel2.writeNoException();
                    parcel2.writeFloat(o);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8987a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    i3 = m8996m();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8992b(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    i3 = m8997n();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    m8991b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    o = m8999p();
                    parcel2.writeNoException();
                    parcel2.writeFloat(o);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    boolean z;
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8989a(z);
                    parcel2.writeNoException();
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    q = m9000q();
                    parcel2.writeNoException();
                    if (q) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    q = m8990a(ICircleDelegate.m9002a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (q) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    i3 = m9001r();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.ICircleDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String m8984a();

    void m8985a(double d);

    void m8986a(float f);

    void m8987a(int i);

    void m8988a(LatLng latLng);

    void m8989a(boolean z);

    boolean m8990a(ICircleDelegate iCircleDelegate);

    void m8991b(float f);

    void m8992b(int i);

    void m8993j();

    LatLng m8994k();

    double m8995l();

    int m8996m();

    int m8997n();

    float m8998o();

    float m8999p();

    boolean m9000q();

    int m9001r();
}
