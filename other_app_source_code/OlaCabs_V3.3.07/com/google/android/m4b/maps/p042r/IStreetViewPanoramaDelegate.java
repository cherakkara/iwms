package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.StreetViewPanoramaCamera;
import com.google.android.m4b.maps.model.StreetViewPanoramaLocation;
import com.google.android.m4b.maps.model.StreetViewPanoramaOrientation;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaCameraChangeListener.IOnStreetViewPanoramaCameraChangeListener.IOnStreetViewPanoramaCameraChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaChangeListener.IOnStreetViewPanoramaChangeListener.IOnStreetViewPanoramaChangeListener;
import com.google.android.m4b.maps.p042r.IOnStreetViewPanoramaClickListener.IOnStreetViewPanoramaClickListener.IOnStreetViewPanoramaClickListener;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.r.h */
public interface IStreetViewPanoramaDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.h.a */
    public static abstract class IStreetViewPanoramaDelegate extends Binder implements IStreetViewPanoramaDelegate {

        /* renamed from: com.google.android.m4b.maps.r.h.a.a */
        static class IStreetViewPanoramaDelegate implements IStreetViewPanoramaDelegate {
            private IBinder f7720a;

            IStreetViewPanoramaDelegate(IBinder iBinder) {
                this.f7720a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7720a;
            }

            public final void m11151a(boolean z) {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.f7720a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11152b(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7720a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11153c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7720a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11154d(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7720a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11155f() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(5, obtain, obtain2, 0);
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

            public final boolean m11156g() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(6, obtain, obtain2, 0);
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

            public final boolean m11157h() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(7, obtain, obtain2, 0);
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

            public final boolean m11158i() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(8, obtain, obtain2, 0);
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

            public final void m11146a(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (streetViewPanoramaCamera != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaCamera.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j);
                    this.f7720a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final StreetViewPanoramaCamera m11159j() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    StreetViewPanoramaCamera a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = StreetViewPanoramaCamera.CREATOR.m11034a(obtain2);
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

            public final void m11150a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeString(str);
                    this.f7720a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11144a(LatLng latLng) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7720a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11145a(LatLng latLng, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f7720a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final StreetViewPanoramaLocation m11160k() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    StreetViewPanoramaLocation a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    this.f7720a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = StreetViewPanoramaLocation.CREATOR.m11040a(obtain2);
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

            public final void m11148a(IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeStrongBinder(iOnStreetViewPanoramaChangeListener != null ? iOnStreetViewPanoramaChangeListener.asBinder() : null);
                    this.f7720a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11147a(IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeStrongBinder(iOnStreetViewPanoramaCameraChangeListener != null ? iOnStreetViewPanoramaCameraChangeListener.asBinder() : null);
                    this.f7720a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11149a(IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeStrongBinder(iOnStreetViewPanoramaClickListener != null ? iOnStreetViewPanoramaClickListener.asBinder() : null);
                    this.f7720a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final StreetViewPanoramaOrientation m11143a(IObjectWrapper iObjectWrapper) {
                StreetViewPanoramaOrientation streetViewPanoramaOrientation = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7720a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        streetViewPanoramaOrientation = StreetViewPanoramaOrientation.CREATOR.m11043a(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return streetViewPanoramaOrientation;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IObjectWrapper m11142a(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (streetViewPanoramaOrientation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOrientation.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7720a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    IObjectWrapper a = IObjectWrapper.IObjectWrapper.m10120a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IStreetViewPanoramaDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
        }

        public static IStreetViewPanoramaDelegate m8666a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IStreetViewPanoramaDelegate)) {
                return new IStreetViewPanoramaDelegate(iBinder);
            }
            return (IStreetViewPanoramaDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            int i3 = 0;
            boolean z;
            boolean f;
            LatLng a;
            IBinder readStrongBinder;
            IInterface queryLocalInterface;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8656a(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8657b(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8658c(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m8659d(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    f = m8660f();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    f = m8661g();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    f = m8662h();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    f = m8663i();
                    parcel2.writeNoException();
                    if (f) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.HT /*9*/:
                    StreetViewPanoramaCamera a2;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        a2 = StreetViewPanoramaCamera.CREATOR.m11034a(parcel);
                    } else {
                        a2 = null;
                    }
                    m8651a(a2, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    StreetViewPanoramaCamera j = m8664j();
                    parcel2.writeNoException();
                    if (j != null) {
                        parcel2.writeInt(1);
                        j.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    m8655a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    }
                    m8649a(a);
                    parcel2.writeNoException();
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        a = LatLng.CREATOR.m11012a(parcel);
                    }
                    m8650a(a, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    StreetViewPanoramaLocation k = m8665k();
                    parcel2.writeNoException();
                    if (k != null) {
                        parcel2.writeInt(1);
                        k.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaChangeListener");
                        iOnStreetViewPanoramaChangeListener = (queryLocalInterface == null || !(queryLocalInterface instanceof IOnStreetViewPanoramaChangeListener)) ? new IOnStreetViewPanoramaChangeListener(readStrongBinder) : (IOnStreetViewPanoramaChangeListener) queryLocalInterface;
                    }
                    m8653a(iOnStreetViewPanoramaChangeListener);
                    parcel2.writeNoException();
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                        iOnStreetViewPanoramaCameraChangeListener = (queryLocalInterface == null || !(queryLocalInterface instanceof IOnStreetViewPanoramaCameraChangeListener)) ? new IOnStreetViewPanoramaCameraChangeListener(readStrongBinder) : (IOnStreetViewPanoramaCameraChangeListener) queryLocalInterface;
                    }
                    m8652a(iOnStreetViewPanoramaCameraChangeListener);
                    parcel2.writeNoException();
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnStreetViewPanoramaClickListener");
                        iOnStreetViewPanoramaClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof IOnStreetViewPanoramaClickListener)) ? new IOnStreetViewPanoramaClickListener(readStrongBinder) : (IOnStreetViewPanoramaClickListener) queryLocalInterface;
                    }
                    m8654a(iOnStreetViewPanoramaClickListener);
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    StreetViewPanoramaOrientation a3 = m8648a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a3 != null) {
                        parcel2.writeInt(1);
                        a3.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    StreetViewPanoramaOrientation a4;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    if (parcel.readInt() != 0) {
                        a4 = StreetViewPanoramaOrientation.CREATOR.m11043a(parcel);
                    } else {
                        a4 = null;
                    }
                    IObjectWrapper a5 = m8647a(a4);
                    parcel2.writeNoException();
                    if (a5 != null) {
                        iBinder = a5.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IStreetViewPanoramaDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IObjectWrapper m8647a(StreetViewPanoramaOrientation streetViewPanoramaOrientation);

    StreetViewPanoramaOrientation m8648a(IObjectWrapper iObjectWrapper);

    void m8649a(LatLng latLng);

    void m8650a(LatLng latLng, int i);

    void m8651a(StreetViewPanoramaCamera streetViewPanoramaCamera, long j);

    void m8652a(IOnStreetViewPanoramaCameraChangeListener iOnStreetViewPanoramaCameraChangeListener);

    void m8653a(IOnStreetViewPanoramaChangeListener iOnStreetViewPanoramaChangeListener);

    void m8654a(IOnStreetViewPanoramaClickListener iOnStreetViewPanoramaClickListener);

    void m8655a(String str);

    void m8656a(boolean z);

    void m8657b(boolean z);

    void m8658c(boolean z);

    void m8659d(boolean z);

    boolean m8660f();

    boolean m8661g();

    boolean m8662h();

    boolean m8663i();

    StreetViewPanoramaCamera m8664j();

    StreetViewPanoramaLocation m8665k();
}
