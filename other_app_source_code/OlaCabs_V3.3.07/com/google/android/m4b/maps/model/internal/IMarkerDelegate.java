package com.google.android.m4b.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.model.internal.k */
public interface IMarkerDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.model.internal.k.a */
    public static abstract class IMarkerDelegate extends Binder implements IMarkerDelegate {

        /* renamed from: com.google.android.m4b.maps.model.internal.k.a.a */
        static class IMarkerDelegate implements IMarkerDelegate {
            private IBinder f7660a;

            IMarkerDelegate(IBinder iBinder) {
                this.f7660a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7660a;
            }

            public final void m10942b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10933a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10937a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7660a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LatLng m10948d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LatLng a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(4, obtain, obtain2, 0);
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

            public final void m10939a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
                    this.f7660a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10949o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10945b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
                    this.f7660a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final String m10950q() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10940a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7660a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10951s() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(10, obtain, obtain2, 0);
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

            public final void m10952u() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10953v() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10954w() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(13, obtain, obtain2, 0);
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

            public final void m10946b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7660a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10955x() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(15, obtain, obtain2, 0);
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

            public final boolean m10941a(IMarkerDelegate iMarkerDelegate) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(iMarkerDelegate != null ? iMarkerDelegate.asBinder() : null);
                    this.f7660a.transact(16, obtain, obtain2, 0);
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

            public final int m10932F() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10936a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7660a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10935a(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f7660a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10947c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7660a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m10956z() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(21, obtain, obtain2, 0);
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

            public final void m10934a(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.f7660a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10930B() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10944b(float f, float f2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.f7660a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10943b(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.f7660a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m10931D() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    this.f7660a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10938a(BitmapDescriptorParcelable bitmapDescriptorParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (bitmapDescriptorParcelable != null) {
                        obtain.writeInt(1);
                        bitmapDescriptorParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7660a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IMarkerDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.model.internal.IMarkerDelegate");
        }

        public static IMarkerDelegate m8340a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMarkerDelegate)) {
                return new IMarkerDelegate(iBinder);
            }
            return (IMarkerDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            BitmapDescriptorParcelable bitmapDescriptorParcelable = null;
            int i3 = 0;
            String a;
            LatLng a2;
            boolean z;
            boolean z2;
            float B;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8325b();
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    a = m8316a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = LatLng.CREATOR.m11012a(parcel);
                    }
                    m8320a(a2);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    a2 = m8331d();
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8322a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    a = m8332o();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8328b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    a = m8333q();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    m8323a(z);
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    z = m8334s();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8335u();
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8336v();
                    parcel2.writeNoException();
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    z = m8337w();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z2 = true;
                    }
                    m8329b(z2);
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    z = m8338x();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    z = m8324a(IMarkerDelegate.m8340a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    int F = m8315F();
                    parcel2.writeNoException();
                    parcel2.writeInt(F);
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8319a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8318a(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0 /*20*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        z2 = true;
                    }
                    m8330c(z2);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    z = m8339z();
                    parcel2.writeNoException();
                    if (z) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize1 /*22*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8317a(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    B = m8313B();
                    parcel2.writeNoException();
                    parcel2.writeFloat(B);
                    return true;
                case SizeUtil.textSize2 /*24*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8327b(parcel.readFloat(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    m8326b(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    B = m8314D();
                    parcel2.writeNoException();
                    parcel2.writeFloat(B);
                    return true;
                case 28:
                    parcel.enforceInterface("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    if (parcel.readInt() != 0) {
                        bitmapDescriptorParcelable = BitmapDescriptorParcelable.CREATOR.m10854a(parcel);
                    }
                    m8321a(bitmapDescriptorParcelable);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.model.internal.IMarkerDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    float m8313B();

    float m8314D();

    int m8315F();

    String m8316a();

    void m8317a(float f);

    void m8318a(float f, float f2);

    void m8319a(IObjectWrapper iObjectWrapper);

    void m8320a(LatLng latLng);

    void m8321a(BitmapDescriptorParcelable bitmapDescriptorParcelable);

    void m8322a(String str);

    void m8323a(boolean z);

    boolean m8324a(IMarkerDelegate iMarkerDelegate);

    void m8325b();

    void m8326b(float f);

    void m8327b(float f, float f2);

    void m8328b(String str);

    void m8329b(boolean z);

    void m8330c(boolean z);

    LatLng m8331d();

    String m8332o();

    String m8333q();

    boolean m8334s();

    void m8335u();

    void m8336v();

    boolean m8337w();

    boolean m8338x();

    boolean m8339z();
}
