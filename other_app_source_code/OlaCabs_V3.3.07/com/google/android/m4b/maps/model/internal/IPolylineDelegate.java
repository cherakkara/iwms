package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.m4b.maps.model.LatLng;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.model.internal.m */
public interface IPolylineDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.m.a */
    public static abstract class IPolylineDelegate extends Binder implements IPolylineDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.m.a.a */
        static class IPolylineDelegate implements IPolylineDelegate {
            private IBinder f7662a;

            IPolylineDelegate(IBinder iBinder) {
                this.f7662a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7662a;
            }

            public final void m10985j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10977a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10980a(List<LatLng> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    obtain.writeTypedList(list);
                    this.f7662a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List<LatLng> m10986k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    List<LatLng> createTypedArrayList = obtain2.createTypedArrayList(LatLng.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10978a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.f7662a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10988m() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10979a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(i);
                    this.f7662a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10987l() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10983b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.f7662a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10989n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10984b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7662a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10991p() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(12, obtain, obtain2, 0);
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

            public final void m10981a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7662a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10990o() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(14, obtain, obtain2, 0);
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

            public final boolean m10982a(IPolylineDelegate iPolylineDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    obtain.writeStrongBinder(iPolylineDelegate != null ? iPolylineDelegate.asBinder() : null);
                    this.f7662a.transact(15, obtain, obtain2, 0);
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

            public final int m10992q() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    this.f7662a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IPolylineDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IPolylineDelegate");
        }

        public static IPolylineDelegate m8522a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPolylineDelegate)) {
                return new IPolylineDelegate(iBinder);
            }
            return (IPolylineDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            float m;
            boolean z;
            boolean p;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m8514j();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    String a = m8506a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m8509a(parcel.createTypedArrayList(LatLng.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    List k = m8515k();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(k);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m8507a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m = m8517m();
                    parcel2.writeNoException();
                    parcel2.writeFloat(m);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m8508a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    i3 = m8516l();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m8512b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    m = m8518n();
                    parcel2.writeNoException();
                    parcel2.writeFloat(m);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8513b(z);
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    p = m8520p();
                    parcel2.writeNoException();
                    if (p) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8510a(z);
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    p = m8519o();
                    parcel2.writeNoException();
                    if (p) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    p = m8511a(IPolylineDelegate.m8522a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (p) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    i3 = m8521q();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IPolylineDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String m8506a();

    void m8507a(float f);

    void m8508a(int i);

    void m8509a(List<LatLng> list);

    void m8510a(boolean z);

    boolean m8511a(IPolylineDelegate iPolylineDelegate);

    void m8512b(float f);

    void m8513b(boolean z);

    void m8514j();

    List<LatLng> m8515k();

    int m8516l();

    float m8517m();

    float m8518n();

    boolean m8519o();

    boolean m8520p();

    int m8521q();
}
