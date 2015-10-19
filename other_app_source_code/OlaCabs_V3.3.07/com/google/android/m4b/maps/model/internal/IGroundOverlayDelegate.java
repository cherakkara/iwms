package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.model.internal.f */
public interface IGroundOverlayDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.f.a */
    public static abstract class IGroundOverlayDelegate extends Binder implements IGroundOverlayDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.f.a.a */
        static class IGroundOverlayDelegate implements IGroundOverlayDelegate {
            private IBinder f7655a;

            IGroundOverlayDelegate(IBinder iBinder) {
                this.f7655a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7655a;
            }

            public final void m10889b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10880a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10884a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7655a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLng m10895h() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLng a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(4, obtain, obtain2, 0);
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

            public final void m10890b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f7655a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10882a(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f7655a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10897j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10896i() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10885a(LatLngBounds latLngBounds) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7655a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLngBounds m10894f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLngBounds a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = LatLngBounds.CREATOR.m10840a(obtain2);
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

            public final void m10881a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f7655a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10892d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10893d(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f7655a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10899o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10887a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7655a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10900q() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(16, obtain, obtain2, 0);
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

            public final void m10891c(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeFloat(f);
                    this.f7655a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10898m() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10888a(IGroundOverlayDelegate iGroundOverlayDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeStrongBinder(iGroundOverlayDelegate != null ? iGroundOverlayDelegate.asBinder() : null);
                    this.f7655a.transact(19, obtain, obtain2, 0);
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

            public final int m10901t() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    this.f7655a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10883a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7655a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10886a(BitmapDescriptorParcelable bitmapDescriptorParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (bitmapDescriptorParcelable != null) {
                        obtain.writeInt(1);
                        bitmapDescriptorParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7655a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IGroundOverlayDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
        }

        public static IGroundOverlayDelegate m9316a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGroundOverlayDelegate)) {
                return new IGroundOverlayDelegate(iBinder);
            }
            return (IGroundOverlayDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            BitmapDescriptorParcelable bitmapDescriptorParcelable = null;
            int i3 = 0;
            LatLng a;
            float j;
            LatLngBounds a2;
            boolean z;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9303b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    String a3 = m9294a();
                    parcel2.writeNoException();
                    parcel2.writeString(a3);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    }
                    m9298a(a);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    a = m9309h();
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9304b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9296a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    j = m9311j();
                    parcel2.writeNoException();
                    parcel2.writeFloat(j);
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    j = m9310i();
                    parcel2.writeNoException();
                    parcel2.writeFloat(j);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = LatLngBounds.CREATOR.m10840a(parcel);
                    }
                    m9299a(a2);
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    a2 = m9308f();
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9295a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    j = m9306d();
                    parcel2.writeNoException();
                    parcel2.writeFloat(j);
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9307d(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    j = m9313o();
                    parcel2.writeNoException();
                    parcel2.writeFloat(j);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    m9301a(z);
                    parcel2.writeNoException();
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    z = m9314q();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9305c(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    j = m9312m();
                    parcel2.writeNoException();
                    parcel2.writeFloat(j);
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    z = m9302a(IGroundOverlayDelegate.m9316a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize0 /*20*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    int t = m9315t();
                    parcel2.writeNoException();
                    parcel2.writeInt(t);
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    m9297a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize1 /*22*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    if (parcel.readInt() != 0) {
                        bitmapDescriptorParcelable = BitmapDescriptorParcelable.CREATOR.m10854a(parcel);
                    }
                    m9300a(bitmapDescriptorParcelable);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String m9294a();

    void m9295a(float f);

    void m9296a(float f, float f2);

    void m9297a(IObjectWrapper iObjectWrapper);

    void m9298a(LatLng latLng);

    void m9299a(LatLngBounds latLngBounds);

    void m9300a(BitmapDescriptorParcelable bitmapDescriptorParcelable);

    void m9301a(boolean z);

    boolean m9302a(IGroundOverlayDelegate iGroundOverlayDelegate);

    void m9303b();

    void m9304b(float f);

    void m9305c(float f);

    float m9306d();

    void m9307d(float f);

    LatLngBounds m9308f();

    LatLng m9309h();

    float m9310i();

    float m9311j();

    float m9312m();

    float m9313o();

    boolean m9314q();

    int m9315t();
}
