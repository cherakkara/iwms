package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.m4b.maps.model.LatLng;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.model.internal.l */
public interface IPolygonDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.l.a */
    public static abstract class IPolygonDelegate extends Binder implements IPolygonDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.l.a.a */
        static class IPolygonDelegate implements IPolygonDelegate {
            private IBinder f7661a;

            IPolygonDelegate(IBinder iBinder) {
                this.f7661a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7661a;
            }

            public final void m10967j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10957a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10960a(List<LatLng> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeTypedList(list);
                    this.f7661a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List<LatLng> m10968k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    List<LatLng> createTypedArrayList = obtain2.createTypedArrayList(LatLng.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10965b(List list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeList(list);
                    this.f7661a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final List m10969l() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    List readArrayList = obtain2.readArrayList(getClass().getClassLoader());
                    return readArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10958a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeFloat(f);
                    this.f7661a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10972o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10959a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeInt(i);
                    this.f7661a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10970m() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10964b(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeInt(i);
                    this.f7661a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m10971n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10963b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeFloat(f);
                    this.f7661a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10973p() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10966b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7661a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10975r() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(16, obtain, obtain2, 0);
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

            public final void m10961a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7661a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10974q() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(18, obtain, obtain2, 0);
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

            public final boolean m10962a(IPolygonDelegate iPolygonDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    obtain.writeStrongBinder(iPolygonDelegate != null ? iPolygonDelegate.asBinder() : null);
                    this.f7661a.transact(19, obtain, obtain2, 0);
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

            public final int m10976s() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    this.f7661a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IPolygonDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IPolygonDelegate");
        }

        public static IPolygonDelegate m8472a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPolygonDelegate)) {
                return new IPolygonDelegate(iBinder);
            }
            return (IPolygonDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            List k;
            float o;
            boolean z;
            boolean r;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8462j();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    String a = m8452a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8455a(parcel.createTypedArrayList(LatLng.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    k = m8463k();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(k);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8460b(parcel.readArrayList(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    k = m8464l();
                    parcel2.writeNoException();
                    parcel2.writeList(k);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8453a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    o = m8467o();
                    parcel2.writeNoException();
                    parcel2.writeFloat(o);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8454a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    i3 = m8465m();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8459b(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    i3 = m8466n();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    m8458b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    o = m8468p();
                    parcel2.writeNoException();
                    parcel2.writeFloat(o);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8461b(z);
                    parcel2.writeNoException();
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    r = m8470r();
                    parcel2.writeNoException();
                    if (r) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8456a(z);
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    r = m8469q();
                    parcel2.writeNoException();
                    if (r) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    r = m8457a(IPolygonDelegate.m8472a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (r) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize0 /*20*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    i3 = m8471s();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IPolygonDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String m8452a();

    void m8453a(float f);

    void m8454a(int i);

    void m8455a(List<LatLng> list);

    void m8456a(boolean z);

    boolean m8457a(IPolygonDelegate iPolygonDelegate);

    void m8458b(float f);

    void m8459b(int i);

    void m8460b(List list);

    void m8461b(boolean z);

    void m8462j();

    List<LatLng> m8463k();

    List m8464l();

    int m8465m();

    int m8466n();

    float m8467o();

    float m8468p();

    boolean m8469q();

    boolean m8470r();

    int m8471s();
}
