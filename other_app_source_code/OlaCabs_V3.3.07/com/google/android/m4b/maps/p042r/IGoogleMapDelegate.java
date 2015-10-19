package com.google.android.m4b.maps.p042r;

import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.CircleOptions;
import com.google.android.m4b.maps.model.GroundOverlayOptions;
import com.google.android.m4b.maps.model.MapsEngineLayerOptions;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.PolygonOptions;
import com.google.android.m4b.maps.model.PolylineOptions;
import com.google.android.m4b.maps.model.TileOverlayOptions;
import com.google.android.m4b.maps.model.internal.CameraUpdateParcelable;
import com.google.android.m4b.maps.model.internal.GroundOverlayOptionsParcelable;
import com.google.android.m4b.maps.model.internal.ICircleDelegate;
import com.google.android.m4b.maps.model.internal.IGroundOverlayDelegate;
import com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate;
import com.google.android.m4b.maps.model.internal.IMapsEngineLayerDelegate;
import com.google.android.m4b.maps.model.internal.IMarkerDelegate;
import com.google.android.m4b.maps.model.internal.IPolygonDelegate;
import com.google.android.m4b.maps.model.internal.IPolylineDelegate;
import com.google.android.m4b.maps.model.internal.ITileOverlayDelegate;
import com.google.android.m4b.maps.model.internal.MarkerOptionsParcelable;
import com.google.android.m4b.maps.p042r.ICancelableCallback.ICancelableCallback;
import com.google.android.m4b.maps.p042r.IInfoWindowAdapter.IInfoWindowAdapter.IInfoWindowAdapter;
import com.google.android.m4b.maps.p042r.IInfoWindowRenderer.IInfoWindowRenderer.IInfoWindowRenderer;
import com.google.android.m4b.maps.p042r.ILocationSourceDelegate.ILocationSourceDelegate.ILocationSourceDelegate;
import com.google.android.m4b.maps.p042r.IProjectionDelegate.IProjectionDelegate;
import com.google.android.m4b.maps.p042r.ISnapshotReadyCallback.ISnapshotReadyCallback.ISnapshotReadyCallback;
import com.google.android.m4b.maps.p042r.IUiSettingsDelegate.IUiSettingsDelegate;
import com.google.android.m4b.maps.p042r.ab.IOAuthTokenProvider.IOAuthTokenProvider;
import com.google.android.m4b.maps.p042r.ac.IOnCameraChangeListener.IOnCameraChangeListener;
import com.google.android.m4b.maps.p042r.ad.IOnIndoorStateChangeListener.IOnIndoorStateChangeListener;
import com.google.android.m4b.maps.p042r.ae.IOnInfoWindowClickListener.IOnInfoWindowClickListener;
import com.google.android.m4b.maps.p042r.ag.IOnMapClickListener.IOnMapClickListener;
import com.google.android.m4b.maps.p042r.ah.IOnMapLoadedCallback.IOnMapLoadedCallback;
import com.google.android.m4b.maps.p042r.ai.IOnMapLongClickListener.IOnMapLongClickListener;
import com.google.android.m4b.maps.p042r.aj.IOnMapReadyCallback;
import com.google.android.m4b.maps.p042r.ak.IOnMapsEngineFeatureClickListener.IOnMapsEngineFeatureClickListener;
import com.google.android.m4b.maps.p042r.al.IOnMarkerClickListener.IOnMarkerClickListener;
import com.google.android.m4b.maps.p042r.am.IOnMarkerDragListener.IOnMarkerDragListener;
import com.google.android.m4b.maps.p042r.ao.IOnMyLocationButtonClickListener.IOnMyLocationButtonClickListener;
import com.google.android.m4b.maps.p042r.ap.IOnMyLocationChangeListener.IOnMyLocationChangeListener;
import com.leanplum.utils.SizeUtil;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.r.v */
public interface IGoogleMapDelegate extends IInterface {

    /* renamed from: com.google.android.m4b.maps.r.v.a */
    public static abstract class IGoogleMapDelegate extends Binder implements IGoogleMapDelegate {

        /* renamed from: com.google.android.m4b.maps.r.v.a.a */
        static class IGoogleMapDelegate implements IGoogleMapDelegate {
            private IBinder f7726a;

            IGoogleMapDelegate(IBinder iBinder) {
                this.f7726a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7726a;
            }

            public final CameraPosition m11268e() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    CameraPosition a;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        a = CameraPosition.CREATOR.m10828a(obtain2);
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

            public final float m11270f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final float m11271g() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    float readFloat = obtain2.readFloat();
                    return readFloat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11235a(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7726a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11261b(IObjectWrapper iObjectWrapper) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.f7726a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11237a(IObjectWrapper iObjectWrapper, ICancelableCallback iCancelableCallback) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (iCancelableCallback != null) {
                        iBinder = iCancelableCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f7726a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11236a(IObjectWrapper iObjectWrapper, int i, ICancelableCallback iCancelableCallback) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeInt(i);
                    if (iCancelableCallback != null) {
                        iBinder = iCancelableCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f7726a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11272h() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IPolylineDelegate m11229a(PolylineOptions polylineOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (polylineOptions != null) {
                        obtain.writeInt(1);
                        polylineOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    IPolylineDelegate a = IPolylineDelegate.IPolylineDelegate.m8522a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IPolygonDelegate m11228a(PolygonOptions polygonOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (polygonOptions != null) {
                        obtain.writeInt(1);
                        polygonOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    IPolygonDelegate a = IPolygonDelegate.IPolygonDelegate.m8472a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IMarkerDelegate m11226a(MarkerOptions markerOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        obtain.writeInt(1);
                        markerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    IMarkerDelegate a = IMarkerDelegate.IMarkerDelegate.m8340a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IGroundOverlayDelegate m11223a(GroundOverlayOptions groundOverlayOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        obtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    IGroundOverlayDelegate a = IGroundOverlayDelegate.IGroundOverlayDelegate.m9316a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final ITileOverlayDelegate m11230a(TileOverlayOptions tileOverlayOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (tileOverlayOptions != null) {
                        obtain.writeInt(1);
                        tileOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    ITileOverlayDelegate a = ITileOverlayDelegate.ITileOverlayDelegate.m8813a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11279q() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int m11278p() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11232a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    this.f7726a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11273j() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(17, obtain, obtain2, 0);
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

            public final void m11258a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7726a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11274k() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(19, obtain, obtain2, 0);
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

            public final boolean m11264b(boolean z) {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.f7726a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11276n() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(21, obtain, obtain2, 0);
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

            public final void m11269e(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7726a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Location m11277o() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Location location;
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(obtain2);
                    } else {
                        location = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return location;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11257a(ILocationSourceDelegate iLocationSourceDelegate) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null);
                    this.f7726a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IUiSettingsDelegate m11281s() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    IUiSettingsDelegate a = IUiSettingsDelegate.m9093a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IProjectionDelegate m11280r() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    IProjectionDelegate a = IProjectionDelegate.m8558a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11242a(ac acVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(acVar != null ? acVar.asBinder() : null);
                    this.f7726a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11245a(ag agVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.f7726a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11247a(ai aiVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(aiVar != null ? aiVar.asBinder() : null);
                    this.f7726a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11250a(al alVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(alVar != null ? alVar.asBinder() : null);
                    this.f7726a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11251a(am amVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(amVar != null ? amVar.asBinder() : null);
                    this.f7726a.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11244a(ae aeVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.f7726a.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11255a(IInfoWindowAdapter iInfoWindowAdapter) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iInfoWindowAdapter != null ? iInfoWindowAdapter.asBinder() : null);
                    this.f7726a.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final ICircleDelegate m11222a(CircleOptions circleOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (circleOptions != null) {
                        obtain.writeInt(1);
                        circleOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    ICircleDelegate a = ICircleDelegate.ICircleDelegate.m9002a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11253a(ap apVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(apVar != null ? apVar.asBinder() : null);
                    this.f7726a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11252a(ao aoVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(aoVar != null ? aoVar.asBinder() : null);
                    this.f7726a.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11254a(ISnapshotReadyCallback iSnapshotReadyCallback, IObjectWrapper iObjectWrapper) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iSnapshotReadyCallback != null ? iSnapshotReadyCallback.asBinder() : null);
                    if (iObjectWrapper != null) {
                        iBinder = iObjectWrapper.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f7726a.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11233a(int i, int i2, int i3, int i4) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.f7726a.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11275m() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(40, obtain, obtain2, 0);
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

            public final void m11267d(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7726a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11246a(ah ahVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(ahVar != null ? ahVar.asBinder() : null);
                    this.f7726a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IIndoorBuildingDelegate m11221D() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    IIndoorBuildingDelegate a = IIndoorBuildingDelegate.IIndoorBuildingDelegate.m9360a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11243a(ad adVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(adVar != null ? adVar.asBinder() : null);
                    this.f7726a.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11241a(ab abVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.f7726a.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IMapsEngineLayerDelegate m11225a(MapsEngineLayerOptions mapsEngineLayerOptions) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (mapsEngineLayerOptions != null) {
                        obtain.writeInt(1);
                        mapsEngineLayerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                    IMapsEngineLayerDelegate a = IMapsEngineLayerDelegate.IMapsEngineLayerDelegate.m8274a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11249a(ak akVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(akVar != null ? akVar.asBinder() : null);
                    this.f7726a.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11248a(aj ajVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(ajVar != null ? ajVar.asBinder() : null);
                    this.f7726a.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11234a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11259b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11265c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11231a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11266d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final boolean m11220C() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    this.f7726a.transact(59, obtain, obtain2, 0);
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

            public final void m11260b(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11263b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeString(str);
                    this.f7726a.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11238a(CameraUpdateParcelable cameraUpdateParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (cameraUpdateParcelable != null) {
                        obtain.writeInt(1);
                        cameraUpdateParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11262b(CameraUpdateParcelable cameraUpdateParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (cameraUpdateParcelable != null) {
                        obtain.writeInt(1);
                        cameraUpdateParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11240a(CameraUpdateParcelable cameraUpdateParcelable, ICancelableCallback iCancelableCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (cameraUpdateParcelable != null) {
                        obtain.writeInt(1);
                        cameraUpdateParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iCancelableCallback != null ? iCancelableCallback.asBinder() : null);
                    this.f7726a.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11239a(CameraUpdateParcelable cameraUpdateParcelable, int i, ICancelableCallback iCancelableCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (cameraUpdateParcelable != null) {
                        obtain.writeInt(1);
                        cameraUpdateParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iCancelableCallback != null ? iCancelableCallback.asBinder() : null);
                    this.f7726a.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IMarkerDelegate m11227a(MarkerOptions markerOptions, MarkerOptionsParcelable markerOptionsParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        obtain.writeInt(1);
                        markerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (markerOptionsParcelable != null) {
                        obtain.writeInt(1);
                        markerOptionsParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                    IMarkerDelegate a = IMarkerDelegate.IMarkerDelegate.m8340a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m11256a(IInfoWindowRenderer iInfoWindowRenderer) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iInfoWindowRenderer != null ? iInfoWindowRenderer.asBinder() : null);
                    this.f7726a.transact(69, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IGroundOverlayDelegate m11224a(GroundOverlayOptions groundOverlayOptions, GroundOverlayOptionsParcelable groundOverlayOptionsParcelable) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        obtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (groundOverlayOptionsParcelable != null) {
                        obtain.writeInt(1);
                        groundOverlayOptionsParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7726a.transact(70, obtain, obtain2, 0);
                    obtain2.readException();
                    IGroundOverlayDelegate a = IGroundOverlayDelegate.IGroundOverlayDelegate.m9316a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IGoogleMapDelegate() {
            attachInterface(this, "com.google.android.m4b.maps.internal.IGoogleMapDelegate");
        }

        public static IGoogleMapDelegate m9176a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGoogleMapDelegate)) {
                return new IGoogleMapDelegate(iBinder);
            }
            return (IGoogleMapDelegate) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int i3 = 0;
            IBinder iBinder = null;
            float f;
            MarkerOptions a;
            IMarkerDelegate a2;
            GroundOverlayOptions a3;
            IGroundOverlayDelegate a4;
            boolean j;
            boolean z;
            IBinder readStrongBinder;
            IInterface queryLocalInterface;
            Bundle bundle;
            CameraUpdateParcelable a5;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    CameraPosition e = m9162e();
                    parcel2.writeNoException();
                    if (e != null) {
                        parcel2.writeInt(1);
                        e.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    f = m9164f();
                    parcel2.writeNoException();
                    parcel2.writeFloat(f);
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    f = m9165g();
                    parcel2.writeNoException();
                    parcel2.writeFloat(f);
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9129a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9155b(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9131a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()), ICancelableCallback.m11219a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9130a(IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()), parcel.readInt(), ICancelableCallback.m11219a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9166h();
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    PolylineOptions a6;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a6 = PolylineOptions.CREATOR.m11030a(parcel);
                    } else {
                        a6 = null;
                    }
                    IPolylineDelegate a7 = m9123a(a6);
                    parcel2.writeNoException();
                    if (a7 != null) {
                        iBinder = a7.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.LF /*10*/:
                    PolygonOptions a8;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a8 = PolygonOptions.CREATOR.m11026a(parcel);
                    } else {
                        a8 = null;
                    }
                    IPolygonDelegate a9 = m9122a(a8);
                    parcel2.writeNoException();
                    if (a9 != null) {
                        iBinder = a9.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a = MarkerOptions.CREATOR.m11022a(parcel);
                    } else {
                        a = null;
                    }
                    a2 = m9120a(a);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a3 = GroundOverlayOptions.CREATOR.m10836a(parcel);
                    } else {
                        a3 = null;
                    }
                    a4 = m9117a(a3);
                    parcel2.writeNoException();
                    if (a4 != null) {
                        iBinder = a4.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case HTTP.CR /*13*/:
                    TileOverlayOptions a10;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a10 = TileOverlayOptions.CREATOR.m10816a(parcel);
                    } else {
                        a10 = null;
                    }
                    ITileOverlayDelegate a11 = m9124a(a10);
                    parcel2.writeNoException();
                    if (a11 != null) {
                        iBinder = a11.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9173q();
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    int p = m9172p();
                    parcel2.writeNoException();
                    parcel2.writeInt(p);
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9126a(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    j = m9167j();
                    parcel2.writeNoException();
                    parcel2.writeInt(j ? 1 : 0);
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9152a(z);
                    parcel2.writeNoException();
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    j = m9168k();
                    parcel2.writeNoException();
                    if (j) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize0 /*20*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        j = true;
                    } else {
                        j = false;
                    }
                    j = m9158b(j);
                    parcel2.writeNoException();
                    if (j) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    j = m9170n();
                    parcel2.writeNoException();
                    if (j) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case SizeUtil.textSize1 /*22*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9163e(z);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    Location o = m9171o();
                    parcel2.writeNoException();
                    if (o != null) {
                        parcel2.writeInt(1);
                        o.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case SizeUtil.textSize2 /*24*/:
                    ILocationSourceDelegate iLocationSourceDelegate;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.ILocationSourceDelegate");
                        iLocationSourceDelegate = (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationSourceDelegate)) ? new ILocationSourceDelegate(readStrongBinder) : (ILocationSourceDelegate) queryLocalInterface;
                    }
                    m9151a(iLocationSourceDelegate);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    IUiSettingsDelegate s = m9175s();
                    parcel2.writeNoException();
                    if (s != null) {
                        iBinder = s.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 26:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    IProjectionDelegate r = m9174r();
                    parcel2.writeNoException();
                    if (r != null) {
                        iBinder = r.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 27:
                    ac iOnCameraChangeListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnCameraChangeListener");
                        iOnCameraChangeListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ac)) ? new IOnCameraChangeListener(readStrongBinder) : (ac) queryLocalInterface;
                    }
                    m9136a(iOnCameraChangeListener);
                    parcel2.writeNoException();
                    return true;
                case 28:
                    ag iOnMapClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMapClickListener");
                        iOnMapClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ag)) ? new IOnMapClickListener(readStrongBinder) : (ag) queryLocalInterface;
                    }
                    m9139a(iOnMapClickListener);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    ai iOnMapLongClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMapLongClickListener");
                        iOnMapLongClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ai)) ? new IOnMapLongClickListener(readStrongBinder) : (ai) queryLocalInterface;
                    }
                    m9141a(iOnMapLongClickListener);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    al iOnMarkerClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMarkerClickListener");
                        iOnMarkerClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof al)) ? new IOnMarkerClickListener(readStrongBinder) : (al) queryLocalInterface;
                    }
                    m9144a(iOnMarkerClickListener);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    am iOnMarkerDragListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMarkerDragListener");
                        iOnMarkerDragListener = (queryLocalInterface == null || !(queryLocalInterface instanceof am)) ? new IOnMarkerDragListener(readStrongBinder) : (am) queryLocalInterface;
                    }
                    m9145a(iOnMarkerDragListener);
                    parcel2.writeNoException();
                    return true;
                case HTTP.SP /*32*/:
                    ae iOnInfoWindowClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnInfoWindowClickListener");
                        iOnInfoWindowClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ae)) ? new IOnInfoWindowClickListener(readStrongBinder) : (ae) queryLocalInterface;
                    }
                    m9138a(iOnInfoWindowClickListener);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    IInfoWindowAdapter iInfoWindowAdapter;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IInfoWindowAdapter");
                        iInfoWindowAdapter = (queryLocalInterface == null || !(queryLocalInterface instanceof IInfoWindowAdapter)) ? new IInfoWindowAdapter(readStrongBinder) : (IInfoWindowAdapter) queryLocalInterface;
                    }
                    m9149a(iInfoWindowAdapter);
                    parcel2.writeNoException();
                    return true;
                case 35:
                    CircleOptions a12;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a12 = CircleOptions.CREATOR.m10832a(parcel);
                    } else {
                        a12 = null;
                    }
                    ICircleDelegate a13 = m9116a(a12);
                    parcel2.writeNoException();
                    if (a13 != null) {
                        iBinder = a13.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 36:
                    ap iOnMyLocationChangeListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMyLocationChangeListener");
                        iOnMyLocationChangeListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ap)) ? new IOnMyLocationChangeListener(readStrongBinder) : (ap) queryLocalInterface;
                    }
                    m9147a(iOnMyLocationChangeListener);
                    parcel2.writeNoException();
                    return true;
                case LangUtils.HASH_OFFSET /*37*/:
                    ao iOnMyLocationButtonClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMyLocationButtonClickListener");
                        iOnMyLocationButtonClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ao)) ? new IOnMyLocationButtonClickListener(readStrongBinder) : (ao) queryLocalInterface;
                    }
                    m9146a(iOnMyLocationButtonClickListener);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    ISnapshotReadyCallback iSnapshotReadyCallback;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.ISnapshotReadyCallback");
                        iSnapshotReadyCallback = (queryLocalInterface == null || !(queryLocalInterface instanceof ISnapshotReadyCallback)) ? new ISnapshotReadyCallback(readStrongBinder) : (ISnapshotReadyCallback) queryLocalInterface;
                    }
                    m9148a(iSnapshotReadyCallback, IObjectWrapper.IObjectWrapper.m10120a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9127a(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    j = m9169m();
                    parcel2.writeNoException();
                    if (j) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 41:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m9161d(z);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    ah iOnMapLoadedCallback;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMapLoadedCallback");
                        iOnMapLoadedCallback = (queryLocalInterface == null || !(queryLocalInterface instanceof ah)) ? new IOnMapLoadedCallback(readStrongBinder) : (ah) queryLocalInterface;
                    }
                    m9140a(iOnMapLoadedCallback);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    IIndoorBuildingDelegate D = m9115D();
                    parcel2.writeNoException();
                    if (D != null) {
                        iBinder = D.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 45:
                    ad iOnIndoorStateChangeListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnIndoorStateChangeListener");
                        iOnIndoorStateChangeListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ad)) ? new IOnIndoorStateChangeListener(readStrongBinder) : (ad) queryLocalInterface;
                    }
                    m9137a(iOnIndoorStateChangeListener);
                    parcel2.writeNoException();
                    return true;
                case 48:
                    ab iOAuthTokenProvider;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOAuthTokenProvider");
                        iOAuthTokenProvider = (queryLocalInterface == null || !(queryLocalInterface instanceof ab)) ? new IOAuthTokenProvider(readStrongBinder) : (ab) queryLocalInterface;
                    }
                    m9135a(iOAuthTokenProvider);
                    parcel2.writeNoException();
                    return true;
                case 49:
                    MapsEngineLayerOptions a14;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a14 = MapsEngineLayerOptions.CREATOR.m11019a(parcel);
                    } else {
                        a14 = null;
                    }
                    IMapsEngineLayerDelegate a15 = m9119a(a14);
                    parcel2.writeNoException();
                    if (a15 != null) {
                        iBinder = a15.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case Defaults.STACK_TRACE_LIMIT /*50*/:
                    ak iOnMapsEngineFeatureClickListener;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IOnMapsEngineFeatureClickListener");
                        iOnMapsEngineFeatureClickListener = (queryLocalInterface == null || !(queryLocalInterface instanceof ak)) ? new IOnMapsEngineFeatureClickListener(readStrongBinder) : (ak) queryLocalInterface;
                    }
                    m9143a(iOnMapsEngineFeatureClickListener);
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9142a(IOnMapReadyCallback.m10517a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m9128a(bundle);
                    parcel2.writeNoException();
                    return true;
                case 55:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9153b();
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9159c();
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9125a();
                    parcel2.writeNoException();
                    return true;
                case 58:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9160d();
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    j = m9114C();
                    parcel2.writeNoException();
                    if (j) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 60:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    m9154b(bundle);
                    parcel2.writeNoException();
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 61:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    m9157b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case AccessibilityNodeInfoCompat.ACTION_ACCESSIBILITY_FOCUS /*64*/:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a5 = CameraUpdateParcelable.CREATOR.m10857a(parcel);
                    }
                    m9132a(a5);
                    parcel2.writeNoException();
                    return true;
                case 65:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a5 = CameraUpdateParcelable.CREATOR.m10857a(parcel);
                    }
                    m9156b(a5);
                    parcel2.writeNoException();
                    return true;
                case 66:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a5 = CameraUpdateParcelable.CREATOR.m10857a(parcel);
                    }
                    m9134a(a5, ICancelableCallback.m11219a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 67:
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a5 = CameraUpdateParcelable.CREATOR.m10857a(parcel);
                    }
                    m9133a(a5, parcel.readInt(), ICancelableCallback.m11219a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 68:
                    MarkerOptionsParcelable a16;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a = MarkerOptions.CREATOR.m11022a(parcel);
                    } else {
                        a = null;
                    }
                    if (parcel.readInt() != 0) {
                        a16 = MarkerOptionsParcelable.CREATOR.m11008a(parcel);
                    } else {
                        a16 = null;
                    }
                    a2 = m9121a(a, a16);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 69:
                    IInfoWindowRenderer iInfoWindowRenderer;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.m4b.maps.internal.IInfoWindowRenderer");
                        iInfoWindowRenderer = (queryLocalInterface == null || !(queryLocalInterface instanceof IInfoWindowRenderer)) ? new IInfoWindowRenderer(readStrongBinder) : (IInfoWindowRenderer) queryLocalInterface;
                    }
                    m9150a(iInfoWindowRenderer);
                    parcel2.writeNoException();
                    return true;
                case 70:
                    GroundOverlayOptionsParcelable a17;
                    parcel.enforceInterface("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        a3 = GroundOverlayOptions.CREATOR.m10836a(parcel);
                    } else {
                        a3 = null;
                    }
                    if (parcel.readInt() != 0) {
                        a17 = GroundOverlayOptionsParcelable.CREATOR.m10860a(parcel);
                    } else {
                        a17 = null;
                    }
                    a4 = m9118a(a3, a17);
                    parcel2.writeNoException();
                    if (a4 != null) {
                        iBinder = a4.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.m4b.maps.internal.IGoogleMapDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    boolean m9114C();

    IIndoorBuildingDelegate m9115D();

    ICircleDelegate m9116a(CircleOptions circleOptions);

    IGroundOverlayDelegate m9117a(GroundOverlayOptions groundOverlayOptions);

    IGroundOverlayDelegate m9118a(GroundOverlayOptions groundOverlayOptions, GroundOverlayOptionsParcelable groundOverlayOptionsParcelable);

    IMapsEngineLayerDelegate m9119a(MapsEngineLayerOptions mapsEngineLayerOptions);

    IMarkerDelegate m9120a(MarkerOptions markerOptions);

    IMarkerDelegate m9121a(MarkerOptions markerOptions, MarkerOptionsParcelable markerOptionsParcelable);

    IPolygonDelegate m9122a(PolygonOptions polygonOptions);

    IPolylineDelegate m9123a(PolylineOptions polylineOptions);

    ITileOverlayDelegate m9124a(TileOverlayOptions tileOverlayOptions);

    void m9125a();

    void m9126a(int i);

    void m9127a(int i, int i2, int i3, int i4);

    void m9128a(Bundle bundle);

    void m9129a(IObjectWrapper iObjectWrapper);

    void m9130a(IObjectWrapper iObjectWrapper, int i, ICancelableCallback iCancelableCallback);

    void m9131a(IObjectWrapper iObjectWrapper, ICancelableCallback iCancelableCallback);

    void m9132a(CameraUpdateParcelable cameraUpdateParcelable);

    void m9133a(CameraUpdateParcelable cameraUpdateParcelable, int i, ICancelableCallback iCancelableCallback);

    void m9134a(CameraUpdateParcelable cameraUpdateParcelable, ICancelableCallback iCancelableCallback);

    void m9135a(ab abVar);

    void m9136a(ac acVar);

    void m9137a(ad adVar);

    void m9138a(ae aeVar);

    void m9139a(ag agVar);

    void m9140a(ah ahVar);

    void m9141a(ai aiVar);

    void m9142a(aj ajVar);

    void m9143a(ak akVar);

    void m9144a(al alVar);

    void m9145a(am amVar);

    void m9146a(ao aoVar);

    void m9147a(ap apVar);

    void m9148a(ISnapshotReadyCallback iSnapshotReadyCallback, IObjectWrapper iObjectWrapper);

    void m9149a(IInfoWindowAdapter iInfoWindowAdapter);

    void m9150a(IInfoWindowRenderer iInfoWindowRenderer);

    void m9151a(ILocationSourceDelegate iLocationSourceDelegate);

    void m9152a(boolean z);

    void m9153b();

    void m9154b(Bundle bundle);

    void m9155b(IObjectWrapper iObjectWrapper);

    void m9156b(CameraUpdateParcelable cameraUpdateParcelable);

    void m9157b(String str);

    boolean m9158b(boolean z);

    void m9159c();

    void m9160d();

    void m9161d(boolean z);

    CameraPosition m9162e();

    void m9163e(boolean z);

    float m9164f();

    float m9165g();

    void m9166h();

    boolean m9167j();

    boolean m9168k();

    boolean m9169m();

    boolean m9170n();

    Location m9171o();

    int m9172p();

    void m9173q();

    IProjectionDelegate m9174r();

    IUiSettingsDelegate m9175s();
}
