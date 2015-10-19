package com.google.android.m4b.maps.p042r;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.r.k */
public interface IUiSettingsDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.k.a */
    public static abstract class IUiSettingsDelegate extends Binder implements IUiSettingsDelegate {

        /* renamed from: com.google.android.m4b.maps.r.k.a.a */
        static class IUiSettingsDelegate implements IUiSettingsDelegate {
            private IBinder f7721a;

            IUiSettingsDelegate(IBinder iBinder) {
                this.f7721a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7721a;
            }

            public final void m11163f(boolean z) {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11165h(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11166i(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11167j(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11168k(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11169l(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11171m(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11172n(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11174u() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(9, obtain, obtain2, 0);
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

            public final boolean m11175v() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(10, obtain, obtain2, 0);
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

            public final boolean m11176w() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(11, obtain, obtain2, 0);
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

            public final boolean m11177x() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(12, obtain, obtain2, 0);
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

            public final boolean m11178y() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(13, obtain, obtain2, 0);
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

            public final boolean m11179z() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(14, obtain, obtain2, 0);
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

            public final boolean m11161A() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(15, obtain, obtain2, 0);
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

            public final void m11162c(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11170l() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(17, obtain, obtain2, 0);
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

            public final void m11164g(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7721a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11173t() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    this.f7721a.transact(19, obtain, obtain2, 0);
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
        }

        public IUiSettingsDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IUiSettingsDelegate");
        }

        public static IUiSettingsDelegate m9093a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUiSettingsDelegate)) {
                return new IUiSettingsDelegate(iBinder);
            }
            return (IUiSettingsDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            boolean z;
            boolean u;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9076f(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9078h(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9079i(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9080j(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9081k(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9082l(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9084m(z);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9085n(z);
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9087u();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9088v();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9089w();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9090x();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9091y();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9092z();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9074A();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9075c(z);
                    parcel2.writeNoException();
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9083l();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9077g(z);
                    parcel2.writeNoException();
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    u = m9086t();
                    parcel2.writeNoException();
                    if (u) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IUiSettingsDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m9074A();

    void m9075c(boolean z);

    void m9076f(boolean z);

    void m9077g(boolean z);

    void m9078h(boolean z);

    void m9079i(boolean z);

    void m9080j(boolean z);

    void m9081k(boolean z);

    void m9082l(boolean z);

    boolean m9083l();

    void m9084m(boolean z);

    void m9085n(boolean z);

    boolean m9086t();

    boolean m9087u();

    boolean m9088v();

    boolean m9089w();

    boolean m9090x();

    boolean m9091y();

    boolean m9092z();
}
