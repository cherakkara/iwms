package com.google.android.m4b.maps.p051l;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.util.TimeUtils;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds;
import com.google.android.m4b.maps.p044k.ILocationListener;
import com.google.android.m4b.maps.p044k.LocationRequest;
import com.google.android.m4b.maps.p044k.LocationRequestCreator;
import com.google.android.m4b.maps.p044k.LocationStatus;
import com.google.android.m4b.maps.p044k.LocationStatusCreator;
import com.google.android.m4b.maps.p051l.IGeofencerCallbacks.IGeofencerCallbacks;
import com.google.android.m4b.maps.p052m.NearbyAlertRequest;
import com.google.android.m4b.maps.p052m.NearbyAlertRequestCreator;
import com.google.android.m4b.maps.p052m.PlaceFilter;
import com.google.android.m4b.maps.p052m.PlaceFilterCreator;
import com.google.android.m4b.maps.p052m.PlaceReport;
import com.google.android.m4b.maps.p052m.PlaceReportCreator;
import com.google.android.m4b.maps.p052m.PlaceRequest;
import com.google.android.m4b.maps.p052m.PlaceRequestCreator;
import com.google.android.m4b.maps.p052m.UserAddedPlace;
import com.google.android.m4b.maps.p052m.UserDataType;
import com.google.android.m4b.maps.p053n.IPlacesCallbacks;
import com.google.android.m4b.maps.p053n.PlacesParams;
import com.google.android.m4b.maps.p053n.PlacesParamsCreator;
import com.leanplum.utils.SizeUtil;
import com.newrelic.agent.android.api.v1.Defaults;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.l.c */
public interface IGoogleLocationManagerService extends IInterface {

    /* renamed from: com.google.android.m4b.maps.l.c.a */
    public static abstract class IGoogleLocationManagerService extends Binder implements IGoogleLocationManagerService {

        /* renamed from: com.google.android.m4b.maps.l.c.a.a */
        static class IGoogleLocationManagerService implements IGoogleLocationManagerService {
            private IBinder f7468a;

            IGoogleLocationManagerService(IBinder iBinder) {
                this.f7468a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f7468a;
            }

            public final void m10636a(List<ParcelableGeofence> list, PendingIntent pendingIntent, IGeofencerCallbacks iGeofencerCallbacks, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeTypedList(list);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iGeofencerCallbacks != null ? iGeofencerCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.f7468a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10616a(PendingIntent pendingIntent, IGeofencerCallbacks iGeofencerCallbacks, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iGeofencerCallbacks != null ? iGeofencerCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.f7468a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10638a(String[] strArr, IGeofencerCallbacks iGeofencerCallbacks, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStringArray(strArr);
                    obtain.writeStrongBinder(iGeofencerCallbacks != null ? iGeofencerCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.f7468a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10623a(IGeofencerCallbacks iGeofencerCallbacks, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStrongBinder(iGeofencerCallbacks != null ? iGeofencerCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.f7468a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10614a(long j, boolean z, PendingIntent pendingIntent) {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeLong(j);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10615a(PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Location m10612a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Location location;
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.f7468a.transact(7, obtain, obtain2, 0);
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

            public final void m10621a(LocationRequest locationRequest, ILocationListener iLocationListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    this.f7468a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10620a(LocationRequest locationRequest, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10619a(ILocationListener iLocationListener) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    this.f7468a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10641b(PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10637a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f7468a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10617a(Location location) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10630a(LatLngBounds latLngBounds, int i, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (placeFilter != null) {
                        obtain.writeInt(1);
                        placeFilter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10631a(LatLngBounds latLngBounds, int i, String str, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (placeFilter != null) {
                        obtain.writeInt(1);
                        placeFilter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10634a(String str, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10629a(LatLng latLng, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placeFilter != null) {
                        obtain.writeInt(1);
                        placeFilter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10625a(PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (placeFilter != null) {
                        obtain.writeInt(1);
                        placeFilter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10643b(String str, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10635a(String str, List<String> list, List<UserDataType> list2, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeTypedList(list2);
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10627a(PlaceRequest placeRequest, PlacesParams placesParams, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (placeRequest != null) {
                        obtain.writeInt(1);
                        placeRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10632a(PlacesParams placesParams, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10624a(NearbyAlertRequest nearbyAlertRequest, PlacesParams placesParams, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (nearbyAlertRequest != null) {
                        obtain.writeInt(1);
                        nearbyAlertRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10642b(PlacesParams placesParams, PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10633a(String str, LatLngBounds latLngBounds, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placeFilter != null) {
                        obtain.writeInt(1);
                        placeFilter.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10628a(UserAddedPlace userAddedPlace, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (userAddedPlace != null) {
                        obtain.writeInt(1);
                        userAddedPlace.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPlacesCallbacks != null ? iPlacesCallbacks.asBinder() : null);
                    this.f7468a.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m10622a(LocationRequest locationRequest, ILocationListener iLocationListener, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    obtain.writeString(str);
                    this.f7468a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final Location m10613a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Location location;
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    this.f7468a.transact(21, obtain, obtain2, 0);
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

            public final void m10626a(PlaceReport placeReport, PlacesParams placesParams) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (placeReport != null) {
                        obtain.writeInt(1);
                        placeReport.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (placesParams != null) {
                        obtain.writeInt(1);
                        placesParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f7468a.transact(25, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public final void m10618a(Location location, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f7468a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final LocationStatus m10640b(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    LocationStatus a;
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    this.f7468a.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        LocationStatusCreator locationStatusCreator = LocationStatus.CREATOR;
                        a = LocationStatusCreator.m10560a(obtain2);
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

            public final IBinder m10639b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.f7468a.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    IBinder readStrongBinder = obtain2.readStrongBinder();
                    return readStrongBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IGoogleLocationManagerService m10644a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGoogleLocationManagerService)) {
                return new IGoogleLocationManagerService(iBinder);
            }
            return (IGoogleLocationManagerService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            boolean z = false;
            PlacesParams placesParams = null;
            PendingIntent pendingIntent;
            Location a;
            LocationRequestCreator locationRequestCreator;
            LocationRequest a2;
            Location location;
            LatLngBounds a3;
            int readInt;
            PlaceFilterCreator placeFilterCreator;
            PlaceFilter a4;
            PlacesParamsCreator placesParamsCreator;
            PlacesParams a5;
            String readString;
            PlacesParamsCreator placesParamsCreator2;
            PlacesParams a6;
            PlacesParams a7;
            String readString2;
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    List createTypedArrayList = parcel.createTypedArrayList(ParcelableGeofence.CREATOR);
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10604a(createTypedArrayList, pendingIntent, IGeofencerCallbacks.m10579a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10584a(pendingIntent, IGeofencerCallbacks.m10579a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    m10606a(parcel.createStringArray(), IGeofencerCallbacks.m10579a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    m10591a(IGeofencerCallbacks.m10579a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    long readLong = parcel.readLong();
                    boolean z2 = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10582a(readLong, z2, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10583a(pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    a = m10580a();
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        locationRequestCreator = LocationRequest.CREATOR;
                        a2 = LocationRequestCreator.m10554a(parcel);
                    }
                    m10589a(a2, ILocationListener.ILocationListener.m10547a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case HTTP.HT /*9*/:
                    LocationRequest a8;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        locationRequestCreator = LocationRequest.CREATOR;
                        a8 = LocationRequestCreator.m10554a(parcel);
                    } else {
                        a8 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10588a(a8, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case HTTP.LF /*10*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    m10587a(ILocationListener.ILocationListener.m10547a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10609b(pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    m10605a(z);
                    parcel2.writeNoException();
                    return true;
                case HTTP.CR /*13*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    m10585a(location);
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        a3 = LatLngBounds.CREATOR.m10840a(parcel);
                    } else {
                        a3 = null;
                    }
                    readInt = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        placeFilterCreator = PlaceFilter.CREATOR;
                        a4 = PlaceFilterCreator.m10681a(parcel);
                    } else {
                        a4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a5 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a5 = null;
                    }
                    m10598a(a3, readInt, a4, a5, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        placesParamsCreator2 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10602a(readString, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                    LatLng a9;
                    PlaceFilter a10;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        a9 = LatLng.CREATOR.m11012a(parcel);
                    } else {
                        a9 = null;
                    }
                    if (parcel.readInt() != 0) {
                        PlaceFilterCreator placeFilterCreator2 = PlaceFilter.CREATOR;
                        a10 = PlaceFilterCreator.m10681a(parcel);
                    } else {
                        a10 = null;
                    }
                    if (parcel.readInt() != 0) {
                        PlacesParamsCreator placesParamsCreator3 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10597a(a9, a10, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    PlaceFilter a11;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        placeFilterCreator = PlaceFilter.CREATOR;
                        a11 = PlaceFilterCreator.m10681a(parcel);
                    } else {
                        a11 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator2 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10593a(a11, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0_1 /*18*/:
                    PlaceRequest a12;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        PlaceRequestCreator placeRequestCreator = PlaceRequest.CREATOR;
                        a12 = PlaceRequestCreator.m10690a(parcel);
                    } else {
                        a12 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a6 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a6 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10595a(a12, a6, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a7 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a7 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10600a(a7, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case SizeUtil.textSize0 /*20*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        locationRequestCreator = LocationRequest.CREATOR;
                        a2 = LocationRequestCreator.m10554a(parcel);
                    }
                    m10590a(a2, ILocationListener.ILocationListener.m10547a(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    a = m10581a(parcel.readString());
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 25:
                    PlaceReport a13;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        PlaceReportCreator placeReportCreator = PlaceReport.CREATOR;
                        a13 = PlaceReportCreator.m10685a(parcel);
                    } else {
                        a13 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator2 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10594a(a13, placesParams);
                    return true;
                case 26:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    m10586a(location, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    LocationStatus b = m10608b(parcel.readString());
                    parcel2.writeNoException();
                    if (b != null) {
                        parcel2.writeInt(1);
                        b.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 42:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        placesParamsCreator2 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10611b(readString, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    LatLngBounds a14;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        a14 = LatLngBounds.CREATOR.m10840a(parcel);
                    } else {
                        a14 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placeFilterCreator = PlaceFilter.CREATOR;
                        a4 = PlaceFilterCreator.m10681a(parcel);
                    } else {
                        a4 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a5 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a5 = null;
                    }
                    m10601a(readString2, a14, a4, a5, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    UserAddedPlace userAddedPlace;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        userAddedPlace = (UserAddedPlace) UserAddedPlace.CREATOR.createFromParcel(parcel);
                    } else {
                        userAddedPlace = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator2 = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10596a(userAddedPlace, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 47:
                    PlaceFilter a15;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        a3 = LatLngBounds.CREATOR.m10840a(parcel);
                    } else {
                        a3 = null;
                    }
                    readInt = parcel.readInt();
                    String readString3 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        placeFilterCreator = PlaceFilter.CREATOR;
                        a15 = PlaceFilterCreator.m10681a(parcel);
                    } else {
                        a15 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        placesParams = PlacesParamsCreator.m11052a(parcel);
                    }
                    m10599a(a3, readInt, readString3, a15, placesParams, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 48:
                    NearbyAlertRequest a16;
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        NearbyAlertRequestCreator nearbyAlertRequestCreator = NearbyAlertRequest.CREATOR;
                        a16 = NearbyAlertRequestCreator.m10675a(parcel);
                    } else {
                        a16 = null;
                    }
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a6 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a6 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10592a(a16, a6, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case 49:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a7 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a7 = null;
                    }
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    } else {
                        pendingIntent = null;
                    }
                    m10610b(a7, pendingIntent);
                    parcel2.writeNoException();
                    return true;
                case Defaults.STACK_TRACE_LIMIT /*50*/:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    readString2 = parcel.readString();
                    List createStringArrayList = parcel.createStringArrayList();
                    List createTypedArrayList2 = parcel.createTypedArrayList(UserDataType.CREATOR);
                    if (parcel.readInt() != 0) {
                        placesParamsCreator = PlacesParams.CREATOR;
                        a5 = PlacesParamsCreator.m11052a(parcel);
                    } else {
                        a5 = null;
                    }
                    m10603a(readString2, createStringArrayList, createTypedArrayList2, a5, IPlacesCallbacks.IPlacesCallbacks.m11051a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    IBinder b2 = m10607b();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(b2);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Location m10580a();

    Location m10581a(String str);

    void m10582a(long j, boolean z, PendingIntent pendingIntent);

    void m10583a(PendingIntent pendingIntent);

    void m10584a(PendingIntent pendingIntent, IGeofencerCallbacks iGeofencerCallbacks, String str);

    void m10585a(Location location);

    void m10586a(Location location, int i);

    void m10587a(ILocationListener iLocationListener);

    void m10588a(LocationRequest locationRequest, PendingIntent pendingIntent);

    void m10589a(LocationRequest locationRequest, ILocationListener iLocationListener);

    void m10590a(LocationRequest locationRequest, ILocationListener iLocationListener, String str);

    void m10591a(IGeofencerCallbacks iGeofencerCallbacks, String str);

    void m10592a(NearbyAlertRequest nearbyAlertRequest, PlacesParams placesParams, PendingIntent pendingIntent);

    void m10593a(PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10594a(PlaceReport placeReport, PlacesParams placesParams);

    void m10595a(PlaceRequest placeRequest, PlacesParams placesParams, PendingIntent pendingIntent);

    void m10596a(UserAddedPlace userAddedPlace, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10597a(LatLng latLng, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10598a(LatLngBounds latLngBounds, int i, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10599a(LatLngBounds latLngBounds, int i, String str, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10600a(PlacesParams placesParams, PendingIntent pendingIntent);

    void m10601a(String str, LatLngBounds latLngBounds, PlaceFilter placeFilter, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10602a(String str, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10603a(String str, List<String> list, List<UserDataType> list2, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);

    void m10604a(List<ParcelableGeofence> list, PendingIntent pendingIntent, IGeofencerCallbacks iGeofencerCallbacks, String str);

    void m10605a(boolean z);

    void m10606a(String[] strArr, IGeofencerCallbacks iGeofencerCallbacks, String str);

    IBinder m10607b();

    LocationStatus m10608b(String str);

    void m10609b(PendingIntent pendingIntent);

    void m10610b(PlacesParams placesParams, PendingIntent pendingIntent);

    void m10611b(String str, PlacesParams placesParams, IPlacesCallbacks iPlacesCallbacks);
}
