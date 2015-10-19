package com.olacabs.customer.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.C0226d;
import com.google.android.m4b.maps.CameraUpdateFactory;
import com.google.android.m4b.maps.GoogleMap.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.model.BitmapDescriptorFactory;
import com.google.android.m4b.maps.model.CameraPosition;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.LatLngBounds.C0595a;
import com.google.android.m4b.maps.model.Marker;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.leanplum.Leanplum;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.AppState;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.DataUpdaterManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AllotedCabDirection;
import com.olacabs.customer.p076d.BookingCabInfoResponse;
import com.olacabs.customer.p076d.CabCategory;
import com.olacabs.customer.p076d.CabImages;
import com.olacabs.customer.p076d.CabInfo;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.ab;
import com.olacabs.customer.p076d.ac;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.ak;
import com.olacabs.customer.p076d.ar;
import com.olacabs.customer.p076d.aw;
import com.olacabs.customer.p076d.bb;
import com.olacabs.customer.p076d.bm;
import com.olacabs.customer.p076d.bs;
import com.olacabs.customer.p076d.ct;
import com.olacabs.customer.p076d.dg;
import com.olacabs.customer.p078c.FetchAddress.FetchAddress;
import com.olacabs.customer.p078c.MapDragListener;
import com.olacabs.customer.p078c.OlaMapFragment;
import com.olacabs.customer.tfs.p081a.NearestCabs;
import com.olacabs.customer.tfs.p081a.TFSCabInfoResponse;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.tfs.ui.TFSConformationFragment;
import com.olacabs.customer.tfs.ui.TFSTrackRideActivity;
import com.olacabs.customer.ui.h.AnonymousClass10;
import com.olacabs.customer.ui.h.AnonymousClass15;
import com.olacabs.customer.ui.h.AnonymousClass16;
import com.olacabs.customer.ui.h.AnonymousClass17;
import com.olacabs.customer.ui.h.AnonymousClass18;
import com.olacabs.customer.ui.h.AnonymousClass19;
import com.olacabs.customer.ui.h.AnonymousClass21;
import com.olacabs.customer.ui.h.AnonymousClass24;
import com.olacabs.customer.ui.h.AnonymousClass25;
import com.olacabs.customer.ui.h.AnonymousClass26;
import com.olacabs.customer.ui.h.AnonymousClass32;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.ui.widgets.EtaStripHelper;
import com.olacabs.customer.ui.widgets.LocationButton;
import com.olacabs.customer.ui.widgets.YellowStripHelper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.OlaApplicationUtils;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.h */
public class BookingFragment extends Fragment implements OnClickListener, OnMapReadyCallback, FetchAddress, BackKeyHandler, TraceFieldInterface {
    public static final String f10693a;
    private static final JoinPoint aA = null;
    private static final JoinPoint aB = null;
    private static final JoinPoint ax = null;
    private static final JoinPoint ay = null;
    private static final JoinPoint az = null;
    private static int f10694e;
    private static int f10695f;
    private static int f10696g;
    private static int f10697h;
    private static int f10698i;
    private boolean f10699A;
    private String f10700B;
    private String f10701C;
    private long f10702D;
    private boolean f10703E;
    private String f10704F;
    private String f10705G;
    private String f10706H;
    private String f10707I;
    private boolean f10708J;
    private String f10709K;
    private String f10710L;
    private String f10711M;
    private String f10712N;
    private String f10713O;
    private Double f10714P;
    private Double f10715Q;
    private ProgressDialog f10716R;
    private DataManager f10717S;
    private ImageView f10718T;
    private int f10719U;
    private C0226d f10720V;
    private RelativeLayout f10721W;
    private Animation f10722X;
    private Animation f10723Y;
    private boolean f10724Z;
    private boolean aa;
    private AlertDialog ab;
    private boolean ac;
    private Handler ad;
    private Handler ae;
    private BookingFragment af;
    private List<NearestCabs> ag;
    private int ah;
    private Bitmap ai;
    private aj aj;
    private aj ak;
    private Runnable al;
    private aj am;
    private AnimationListener an;
    private AnimationListener ao;
    private boolean ap;
    private aj aq;
    private aj ar;
    private aj as;
    private GoogleMap at;
    private MapDragListener au;
    private Observer av;
    private Runnable aw;
    SharedPreferences f10725b;
    ProgressDialog f10726c;
    private int f10727d;
    private MainActivity f10728j;
    private Handler f10729k;
    private OlaMapFragment f10730l;
    private BookingCabCategoryFragment f10731m;
    private com.google.android.m4b.maps.GoogleMap f10732n;
    private String f10733o;
    private TextView f10734p;
    private boolean f10735q;
    private String f10736r;
    private String f10737s;
    private boolean f10738t;
    private int f10739u;
    private String f10740v;
    private LocationButton f10741w;
    private View f10742x;
    private YellowStripHelper f10743y;
    private EtaStripHelper f10744z;

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ AlertDialog f10637a;
        final /* synthetic */ BookingFragment f10638b;

        AnonymousClass10(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10638b = bookingFragment;
            this.f10637a = alertDialog;
        }

        public void onClick(View view) {
            this.f10637a.dismiss();
            this.f10638b.f10728j.m13608e().m13650a(3);
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.15 */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ long f10643a;
        final /* synthetic */ BookingFragment f10644b;

        AnonymousClass15(BookingFragment bookingFragment, long j) {
            this.f10644b = bookingFragment;
            this.f10643a = j;
        }

        public void run() {
            if (Ola.f11487d == null) {
                OLog.m13313b("Fares are null", new Object[0]);
            }
            if (Ola.f11487d != null) {
                this.f10644b.f10700B = Ola.f11487d.getFaresForCity(this.f10644b.f10736r, this.f10644b.f10731m.m14239f().getId()).getFareBreakUp(0).getValue() + Trace.NULL;
                this.f10644b.f10701C = Ola.f11487d.getFaresForCity(this.f10644b.f10736r, this.f10644b.f10731m.m14239f().getId()).getFareBreakUp(1).getValue() + Trace.NULL;
            }
            if (this.f10644b.f10717S.m13218d().isB() && this.f10644b.m14281R() && this.f10643a < System.currentTimeMillis()) {
                this.f10644b.m14349a(BookingCabConfirmationNewFlowFragment.m14250a(this.f10644b.f10731m.m14239f().getId(), this.f10644b.f10731m.m14239f().getName(), this.f10644b.f10700B, this.f10644b.f10701C, this.f10644b.f10736r, this.f10644b.f10737s, this.f10643a, this.f10643a < System.currentTimeMillis()));
            } else {
                this.f10644b.m14349a(BookingCabConfirmationOldFlowFragment.m14254a(this.f10644b.f10731m.m14239f().getId(), this.f10644b.f10731m.m14239f().getName(), this.f10644b.f10700B, this.f10644b.f10701C, this.f10644b.f10736r, this.f10644b.f10737s, this.f10643a, this.f10643a < System.currentTimeMillis()));
            }
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.16 */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ boolean f10645a;
        final /* synthetic */ BookingFragment f10646b;

        AnonymousClass16(BookingFragment bookingFragment, boolean z) {
            this.f10646b = bookingFragment;
            this.f10645a = z;
        }

        public void run() {
            if (Ola.f11487d == null) {
                OLog.m13313b("Fares are null", new Object[0]);
            }
            this.f10646b.m14370h();
            LatLng g = this.f10646b.f10730l.m13401g();
            if (g != null) {
                String str;
                String str2;
                BookingFragment bookingFragment = this.f10646b;
                double d = g.f7554a;
                double d2 = g.f7555b;
                if (Ola.f11495l != null) {
                    str = Ola.f11495l;
                } else {
                    str = this.f10646b.getResources().getString(R.string.kp_text);
                }
                if (Ola.f11496m != null) {
                    str2 = Ola.f11496m;
                } else {
                    str2 = this.f10646b.getResources().getString(R.string.kp_sub_text);
                }
                bookingFragment.m14349a(KaaliPeeliConformationFragment.m14464a(d, d2, str, str2, this.f10646b.f10736r, this.f10645a));
            }
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.17 */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ boolean f10647a;
        final /* synthetic */ BookingFragment f10648b;

        AnonymousClass17(BookingFragment bookingFragment, boolean z) {
            this.f10648b = bookingFragment;
            this.f10647a = z;
        }

        public void run() {
            OLog.m13313b("City : " + this.f10648b.f10736r, new Object[0]);
            if (Ola.f11487d == null) {
                OLog.m13313b("Fares are null", new Object[0]);
            }
            this.f10648b.m14370h();
            LatLng g = this.f10648b.f10730l.m13401g();
            if (g != null) {
                String str;
                String str2;
                BookingFragment bookingFragment = this.f10648b;
                double d = g.f7554a;
                double d2 = g.f7555b;
                if (Ola.f11497n != null) {
                    str = Ola.f11497n;
                } else {
                    str = this.f10648b.getResources().getString(R.string.auto_text);
                }
                if (Ola.f11498o != null) {
                    str2 = Ola.f11498o;
                } else {
                    str2 = this.f10648b.getResources().getString(R.string.auto_sub_text);
                }
                bookingFragment.m14349a(AutoRickshawBookingConfirmationFragment.m14133a(d, d2, str, str2, this.f10648b.f10736r, this.f10647a));
            }
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.18 */
    class AnonymousClass18 implements Runnable {
        final /* synthetic */ int f10649a;
        final /* synthetic */ boolean f10650b;
        final /* synthetic */ BookingFragment f10651c;

        AnonymousClass18(BookingFragment bookingFragment, int i, boolean z) {
            this.f10651c = bookingFragment;
            this.f10649a = i;
            this.f10650b = z;
        }

        public void run() {
            OLog.m13313b("City : " + this.f10651c.f10736r, new Object[0]);
            this.f10651c.m14349a(TFSConformationFragment.m13458a(this.f10651c.f10731m.m14239f().getId(), this.f10651c.f10731m.m14239f().getName(), this.f10651c.f10736r, this.f10649a, this.f10650b));
            this.f10651c.f10731m.m14246m();
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.19 */
    class AnonymousClass19 implements Runnable {
        final /* synthetic */ long f10652a;
        final /* synthetic */ BookingFragment f10653b;

        AnonymousClass19(BookingFragment bookingFragment, long j) {
            this.f10653b = bookingFragment;
            this.f10652a = j;
        }

        public void run() {
            OLog.m13313b("City : " + this.f10653b.f10736r, new Object[0]);
            this.f10653b.m14349a(DeliveryConformationFragment.m14394a(this.f10653b.f10731m.m14239f().getId(), this.f10653b.f10731m.m14239f().getName(), Trace.NULL, Trace.NULL, this.f10653b.f10736r, this.f10653b.f10737s, this.f10652a, this.f10652a < System.currentTimeMillis(), this.f10653b.f10740v != null ? this.f10653b.f10740v : Trace.NULL, Ola.f11499p != null ? Ola.f11499p : Trace.NULL, Ola.f11500q != null ? Ola.f11500q : Trace.NULL, Ola.f11501r != null ? Ola.f11501r : Trace.NULL));
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.1 */
    class BookingFragment implements aj {
        final /* synthetic */ BookingFragment f10654a;

        BookingFragment(BookingFragment bookingFragment) {
            this.f10654a = bookingFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to fetch tfs cab info", th);
            this.f10654a.m14387y();
            this.f10654a.ag = null;
            this.f10654a.f10731m.m14226a(0);
            this.f10654a.m14344C();
            this.f10654a.m14345D();
        }

        public void onSuccess(Object obj) {
            OLog.m13313b("Fetched tfs cab info successfully", new Object[0]);
            this.f10654a.m14387y();
            TFSCabInfoResponse tFSCabInfoResponse = (TFSCabInfoResponse) obj;
            this.f10654a.ag = tFSCabInfoResponse.getNearestCabs();
            if (this.f10654a.ag != null && this.f10654a.ag.size() < 1) {
                this.f10654a.m14371i();
            }
            if (!tFSCabInfoResponse.isCabAvailable() || this.f10654a.ag.size() <= 0) {
                this.f10654a.f10731m.m14226a(0);
            } else {
                this.f10654a.ah = ((NearestCabs) this.f10654a.ag.get(0)).getDuration() > 0 ? ((NearestCabs) this.f10654a.ag.get(0)).getDuration() : 1;
                this.f10654a.f10731m.m14226a(this.f10654a.ah);
            }
            if (this.f10654a.f10731m.m14239f() != null && this.f10654a.f10731m.m14239f().isTFS()) {
                this.f10654a.aa = this.f10654a.f10731m.m14239f().isRideLaterEnable();
                this.f10654a.f10724Z = this.f10654a.f10731m.m14239f().isRideNowEnable();
                this.f10654a.m14344C();
                this.f10654a.m14345D();
            }
            if (Utils.m14924g(tFSCabInfoResponse.getCity()) && this.f10654a.f10717S.m13218d().getConfigurationResponse() != null && this.f10654a.f10717S.m13218d().getConfigurationResponse().getTfsContactNumbers() != null && this.f10654a.f10717S.m13218d().getConfigurationResponse().getTfsContactNumbers().containsKey(tFSCabInfoResponse.getCity()) && this.f10654a.f10717S != null && this.f10654a.f10717S.m13218d() != null) {
                this.f10654a.f10717S.m13218d().setTfsCallCenterNumber((String) this.f10654a.f10717S.m13218d().getConfigurationResponse().getTfsContactNumbers().get(tFSCabInfoResponse.getCity()));
            }
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.21 */
    class AnonymousClass21 implements DialogInterface.OnClickListener {
        final /* synthetic */ DatePicker f10656a;
        final /* synthetic */ TimePicker f10657b;
        final /* synthetic */ BookingFragment f10658c;

        AnonymousClass21(BookingFragment bookingFragment, DatePicker datePicker, TimePicker timePicker) {
            this.f10658c = bookingFragment;
            this.f10656a = datePicker;
            this.f10657b = timePicker;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Calendar instance = Calendar.getInstance();
            instance.set(1, this.f10656a.getYear());
            instance.set(2, this.f10656a.getMonth());
            instance.set(5, this.f10656a.getDayOfMonth());
            instance.set(11, this.f10657b.getCurrentHour().intValue());
            instance.set(12, this.f10657b.getCurrentMinute().intValue());
            if (instance.getTimeInMillis() < System.currentTimeMillis() + Constants.MILLIS_IN_AN_HOUR) {
                this.f10658c.m14282S();
                return;
            }
            dialogInterface.dismiss();
            this.f10658c.m14372j();
            this.f10658c.m14348a(instance.getTimeInMillis());
            this.f10658c.m14377o();
            Localytics.tagEvent("Pickup time selected");
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.24 */
    class AnonymousClass24 implements DialogInterface.OnClickListener {
        final /* synthetic */ DatePicker f10661a;
        final /* synthetic */ BookingFragment f10662b;

        AnonymousClass24(BookingFragment bookingFragment, DatePicker datePicker) {
            this.f10662b = bookingFragment;
            this.f10661a = datePicker;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Calendar instance = Calendar.getInstance();
            instance.set(1, this.f10661a.getYear());
            instance.set(2, this.f10661a.getMonth());
            instance.set(5, this.f10661a.getDayOfMonth());
            if (instance.getTimeInMillis() < System.currentTimeMillis() + Constants.MILLIS_IN_AN_HOUR) {
                this.f10662b.m14282S();
                return;
            }
            dialogInterface.dismiss();
            this.f10662b.m14372j();
            this.f10662b.m14359b(instance.getTimeInMillis());
            this.f10662b.m14377o();
            Localytics.tagEvent("Pickup time selected");
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.25 */
    class AnonymousClass25 implements OnClickListener {
        final /* synthetic */ AlertDialog f10663a;
        final /* synthetic */ BookingFragment f10664b;

        AnonymousClass25(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10664b = bookingFragment;
            this.f10663a = alertDialog;
        }

        public void onClick(View view) {
            this.f10663a.dismiss();
            this.f10664b.m14372j();
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.26 */
    class AnonymousClass26 implements OnClickListener {
        final /* synthetic */ long f10665a;
        final /* synthetic */ BookingFragment f10666b;

        AnonymousClass26(BookingFragment bookingFragment, long j) {
            this.f10666b = bookingFragment;
            this.f10665a = j;
        }

        public void onClick(View view) {
            float currentTimeMillis = (float) ((System.currentTimeMillis() - this.f10665a) / 1000);
            Map hashMap = new HashMap();
            hashMap.put("duration", String.valueOf(currentTimeMillis));
            Localytics.tagEvent("driver cancelled popup shown", hashMap);
            this.f10666b.ab.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.2 */
    class BookingFragment implements aj {
        final /* synthetic */ BookingFragment f10670a;

        BookingFragment(BookingFragment bookingFragment) {
            this.f10670a = bookingFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13318e("Failed to fetch food config", new Object[0]);
        }

        public void onSuccess(Object obj) {
            bb bbVar = (bb) obj;
            if (bbVar != null) {
                this.f10670a.f10717S.m13218d().setFoodDeliveryConfigurations(bbVar);
                for (bs bsVar : bbVar.getContactNumbers()) {
                    if (this.f10670a.f10717S.m13218d().getConfigurationResponse() != null && bsVar.getCityName().equalsIgnoreCase(this.f10670a.f10717S.m13218d().getConfigurationResponse().getCityTag())) {
                        this.f10670a.f10717S.m13218d().setFoodCallCenterNumber(bsVar.getContactNumber());
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: BookingFragment */
    /* renamed from: com.olacabs.customer.ui.h.32 */
    static /* synthetic */ class AnonymousClass32 {
        static final /* synthetic */ int[] f10673a;

        static {
            f10673a = new int[AppState.values().length];
            try {
                f10673a[AppState.BEFORE_BOOKING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f10673a[AppState.BOOKING_CONFIRMED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f10673a[AppState.REACHED_FOR_PICKUP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f10673a[AppState.IN_TRIP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f10673a[AppState.TRIP_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f10673a[AppState.NEED_UPDATE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.3 */
    class BookingFragment implements GoogleMap {
        final /* synthetic */ BookingFragment f10680a;

        BookingFragment(BookingFragment bookingFragment) {
            this.f10680a = bookingFragment;
        }

        public boolean m14265a(Marker marker) {
            return true;
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.4 */
    class BookingFragment implements GoogleMap {
        final /* synthetic */ BookingFragment f10681a;

        BookingFragment(BookingFragment bookingFragment) {
            this.f10681a = bookingFragment;
        }

        public void m14266a(CameraPosition cameraPosition) {
            OLog.m13313b("onCameraChange", new Object[0]);
            if (!this.f10681a.f10717S.m13218d().isCabInfoTriggered()) {
                this.f10681a.m14272I();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.5 */
    class BookingFragment implements MapDragListener {
        final /* synthetic */ BookingFragment f10682a;

        BookingFragment(BookingFragment bookingFragment) {
            this.f10682a = bookingFragment;
        }

        public void o_() {
        }

        public void m14267b() {
            OLog.m13313b("onDragEnd", new Object[0]);
            this.f10682a.m14272I();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.6 */
    class BookingFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10683a;
        final /* synthetic */ BookingFragment f10684b;

        BookingFragment(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10684b = bookingFragment;
            this.f10683a = alertDialog;
        }

        public void onClick(View view) {
            this.f10683a.dismiss();
            this.f10684b.m14275L();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.7 */
    class BookingFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10685a;
        final /* synthetic */ BookingFragment f10686b;

        BookingFragment(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10686b = bookingFragment;
            this.f10685a = alertDialog;
        }

        public void onClick(View view) {
            this.f10685a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.8 */
    class BookingFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10687a;
        final /* synthetic */ BookingFragment f10688b;

        BookingFragment(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10688b = bookingFragment;
            this.f10687a = alertDialog;
        }

        public void onClick(View view) {
            this.f10687a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.9 */
    class BookingFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10689a;
        final /* synthetic */ BookingFragment f10690b;

        BookingFragment(BookingFragment bookingFragment, AlertDialog alertDialog) {
            this.f10690b = bookingFragment;
            this.f10689a = alertDialog;
        }

        public void onClick(View view) {
            this.f10689a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.h.a */
    class BookingFragment implements Runnable {
        boolean f10691a;
        final /* synthetic */ BookingFragment f10692b;

        public BookingFragment(BookingFragment bookingFragment, boolean z) {
            this.f10692b = bookingFragment;
            this.f10691a = false;
            this.f10691a = z;
        }

        public void run() {
            this.f10692b.m14349a(this.f10692b.f10731m);
            this.f10692b.m14376n();
            this.f10692b.m14344C();
            this.f10692b.m14277N();
            this.f10692b.f10732n.m10077d().m10565d(true);
            this.f10692b.f10732n.m10077d().m10566e(true);
            if (this.f10691a) {
                this.f10692b.m14386x();
            }
        }
    }

    private static void ad() {
        Factory factory = new Factory("BookingFragment.java", BookingFragment.class);
        ax = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.BookingFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 464);
        ay = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.BookingFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 494);
        az = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.BookingFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 713);
        aA = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.BookingFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 725);
        aB = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.BookingFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 749);
    }

    static {
        BookingFragment.ad();
        f10693a = BookingFragment.class.getSimpleName();
        f10694e = 11;
        f10695f = f10694e * Constants.MILLIS_IN_A_SECOND;
        f10696g = 30;
        f10697h = f10696g * Constants.MILLIS_IN_A_SECOND;
        f10698i = 20000;
    }

    public BookingFragment() {
        this.f10727d = 0;
        this.f10735q = false;
        this.f10738t = true;
        this.f10739u = 0;
        this.f10699A = false;
        this.f10700B = null;
        this.f10701C = null;
        this.f10702D = -1;
        this.f10703E = false;
        this.f10706H = "null";
        this.f10708J = false;
        this.f10719U = 0;
        this.f10724Z = false;
        this.aa = false;
        this.ac = false;
        this.aj = new BookingFragment(this);
        this.ak = new aj() {
            final /* synthetic */ BookingFragment f10640a;

            {
                this.f10640a = r1;
            }

            public void onFailure(Throwable th) {
                Sherlock.m13335a("Ins track ride shown", (VolleyError) th);
                OLog.m13310a("TrackRideRequest failed", th);
                this.f10640a.f10726c.dismiss();
            }

            public void onSuccess(Object obj) {
                this.f10640a.f10726c.dismiss();
                ct ctVar = (ct) obj;
                if (ctVar.getStatus().equalsIgnoreCase("FAILURE")) {
                    new ErrorView(this.f10640a.f10728j).m14824a(ctVar.getHeader()).m14827b(ctVar.getText()).m14826b(this.f10640a.getView()).m14825a().m14831a(this.f10640a.f10734p);
                    return;
                }
                Intent intent = new Intent(this.f10640a.f10728j, TrackRideActivity.class);
                intent.putExtra(Constants.ARG_BOOKING_ID, this.f10640a.f10744z.m14844d());
                this.f10640a.startActivity(intent);
            }
        };
        this.al = new Runnable() {
            final /* synthetic */ BookingFragment f10660a;

            {
                this.f10660a = r1;
            }

            public void run() {
                OLog.m13313b("Running Cab update", new Object[0]);
                if (this.f10660a.f10730l.m13399e()) {
                    this.f10660a.m14296a(this.f10660a.f10730l.m13401g());
                }
                this.f10660a.f10729k.postDelayed(this, (long) BookingFragment.f10695f);
            }
        };
        this.am = new aj() {
            final /* synthetic */ BookingFragment f10674a;

            {
                this.f10674a = r1;
            }

            public void onFailure(Throwable th) {
                if (this.f10674a.isAdded()) {
                    Sherlock.m13336a("Ins app launch time", (VolleyError) th, "cabinfo failed", false);
                    HashMap hashMap = new HashMap();
                    hashMap.put("Booking city", Sherlock.m13349d(this.f10674a.f10736r));
                    Sherlock.m13342a("Ins categories switched", hashMap, (VolleyError) th);
                    OLog.m13310a("Fetch Configuration Request failed", th);
                    this.f10674a.m14387y();
                    this.f10674a.f10719U = this.f10674a.f10719U + 1;
                    if (this.f10674a.f10719U > 2) {
                        this.f10674a.m14319e(false);
                    }
                }
            }

            public void onSuccess(Object obj) {
                if (this.f10674a.isAdded()) {
                    this.f10674a.f10717S.m13169a("cabinfoRequestTag");
                    if (this.f10674a.f10719U > 2) {
                        this.f10674a.m14319e(true);
                    }
                    this.f10674a.f10719U = 0;
                    this.f10674a.m14300a(obj);
                }
            }
        };
        this.an = new AnimationListener() {
            final /* synthetic */ BookingFragment f10675a;

            {
                this.f10675a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.f10675a.m14369g();
                this.f10675a.f10742x.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
        this.ao = new AnimationListener() {
            final /* synthetic */ BookingFragment f10676a;

            {
                this.f10676a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.f10676a.m14369g();
            }

            public void onAnimationEnd(Animation animation) {
                this.f10676a.f10742x.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        };
        this.ap = false;
        this.aq = new aj() {
            final /* synthetic */ BookingFragment f10677a;

            {
                this.f10677a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("Fetching City Wise Data failed", th);
            }

            public void onSuccess(Object obj) {
                OLog.m13313b("Fetching City Wise Data Successful", new Object[0]);
                ab abVar = (ab) obj;
                if (abVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f10677a.f10717S.m13218d().setCityBasedFareModels(abVar);
                }
            }
        };
        this.ar = new aj() {
            final /* synthetic */ BookingFragment f10678a;

            {
                this.f10678a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("Fetching Tfs fare failed", th);
            }

            public void onSuccess(Object obj) {
                OLog.m13313b("Fetching Tfs fare Successful", new Object[0]);
                TFSFareResponse tFSFareResponse = (TFSFareResponse) obj;
                if (tFSFareResponse != null && tFSFareResponse.getStatus() != null && tFSFareResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f10678a.f10717S.m13218d().setTfsFareModel(tFSFareResponse);
                }
            }
        };
        this.as = new BookingFragment(this);
        this.at = new BookingFragment(this);
        this.au = new BookingFragment(this);
        this.av = new Observer() {
            final /* synthetic */ BookingFragment f10669a;

            {
                this.f10669a = r1;
            }

            public void update(Observable observable, Object obj) {
                Object a = DataUpdaterManager.m13261a(this.f10669a.f10728j.getApplication()).m13263a().m13320a("cab_info");
                if (a == null) {
                    this.f10669a.m14386x();
                } else if (a instanceof BookingCabInfoResponse) {
                    this.f10669a.m14300a(a);
                }
                this.f10669a.ac();
            }
        };
        this.aw = new Runnable() {
            final /* synthetic */ BookingFragment f10671a;

            {
                this.f10671a = r1;
            }

            public void run() {
                if (this.f10671a.f10730l.m13399e()) {
                    this.f10671a.m14343B();
                }
            }
        };
    }

    public static BookingFragment m14305b() {
        BookingFragment bookingFragment = new BookingFragment();
        bookingFragment.setArguments(new Bundle());
        return bookingFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("h");
        try {
            TraceMachine.enterMethod(this._nr_trace, "h#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "h#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(ax, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Sherlock.m13345b("Ins OTP verify clicked");
        Sherlock.m13345b("Ins Login");
        Sherlock.m13334a("Ins search bar address");
        Sherlock.m13334a("Ins location fix obtained");
        Sherlock.m13334a("Ins category panel shown");
        this.f10717S = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagEvent("Home Page");
        this.f10736r = new String();
        this.f10731m = BookingCabCategoryFragment.m14186a();
        this.f10722X = AnimationUtils.loadAnimation(getActivity(), R.anim.slideup);
        this.f10722X.setDuration(300);
        this.f10722X.setAnimationListener(this.an);
        this.f10723Y = AnimationUtils.loadAnimation(getActivity(), R.anim.slidedown);
        this.f10723Y.setDuration(300);
        this.f10723Y.setAnimationListener(this.ao);
        this.ad = new Handler();
        this.ae = new Handler();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "h#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "h#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(ay, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_booking, viewGroup, false);
        inflate.findViewById(R.id.button_navigation_drawer).setOnClickListener(this);
        inflate.findViewById(R.id.button_map_favorite).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_now).setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_later).setOnClickListener(this);
        this.f10741w = (LocationButton) inflate.findViewById(R.id.button_move_to_user_location);
        inflate.findViewById(R.id.layout_eta_strip).setOnClickListener(this);
        this.f10742x = inflate.findViewById(R.id.layout_ride_buttons);
        this.f10734p = (TextView) inflate.findViewById(R.id.textView_location);
        this.f10734p.setOnClickListener(this);
        this.f10718T = (ImageView) inflate.findViewById(R.id.sos_image);
        this.f10718T.setOnClickListener(this);
        this.f10716R = new ProgressDialog(this.f10728j, R.style.TransparentProgressDialog);
        this.f10716R.setIndeterminateDrawable(this.f10728j.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10716R.setCancelable(false);
        this.f10721W = (RelativeLayout) inflate.findViewById(R.id.view_no_location_overlay);
        this.f10721W.setOnClickListener(this);
        inflate.findViewById(R.id.button_ride_now).setEnabled(false);
        inflate.findViewById(R.id.button_ride_later).setEnabled(false);
        this.f10743y = new YellowStripHelper(getChildFragmentManager(), inflate.findViewById(R.id.textView_message), R.id.container_sub_panel);
        this.f10744z = new EtaStripHelper(getChildFragmentManager(), inflate.findViewById(R.id.layout_eta_strip), R.id.container_sub_panel);
        this.f10744z.m14838a(new EtaStripHelper.EtaStripHelper() {
            final /* synthetic */ BookingFragment f10679a;

            {
                this.f10679a = r1;
            }

            public void m14263a(int i) {
                this.f10679a.m14347a(this.f10679a.f10739u + i);
            }

            public void m14264b(int i) {
                this.f10679a.m14347a(this.f10679a.f10739u - i);
            }
        });
        this.f10728j = (MainActivity) getActivity();
        this.f10725b = PreferenceManager.getDefaultSharedPreferences(this.f10728j);
        this.f10720V = this.f10717S.m13242i();
        m14271H();
        m14278O();
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14270G() {
        this.f10717S.m13219d(new WeakReference(this.as));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f10743y.m14862a(view.findViewById(R.id.layout_ride_buttons));
    }

    private void m14301a(List<CabInfo> list, LatLng latLng, LatLng latLng2) {
        if (!this.f10731m.m14239f().isTFS()) {
            this.f10732n.m10076c();
        }
        this.f10732n.m10073a(new BookingFragment(this));
        if (list != null && list.size() > 0) {
            for (CabInfo cabInfo : list) {
                if (cabInfo != null) {
                    if (cabInfo.getCategoryId().equalsIgnoreCase(this.f10706H != null ? this.f10706H : Trace.NULL) && cabInfo.getId() != null) {
                        Bitmap c = m14362c(cabInfo.getId());
                        if (c != null) {
                            MarkerOptions a = new MarkerOptions().m10759a(new LatLng(cabInfo.getLatitude(), cabInfo.getlongitude()));
                            a.m10760a(BitmapDescriptorFactory.m10824a(c));
                            this.f10732n.m10068a(a);
                        }
                    }
                }
            }
        }
        if (!this.f10699A && latLng != null && latLng2 != null) {
            C0595a c0595a = new C0595a();
            c0595a.m10738a(new LatLng(latLng.f7554a, latLng.f7555b));
            c0595a.m10738a(latLng2);
            this.f10730l.m13391a(CameraUpdateFactory.m7792a(c0595a.m10739a(), 50));
            this.f10699A = true;
        }
    }

    private void m14271H() {
        float f;
        LatLng latLng;
        Location userLocation = this.f10717S.m13209c().getUserLocation();
        if (userLocation != null) {
            f = 16.0f;
            latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        } else {
            f = 6.0f;
            latLng = new LatLng(Constants.DEFAULT_MAP_LAT, Constants.DEFAULT_MAP_LONG);
        }
        ak o = this.f10717S.m13259o();
        boolean isConfPanelPending = o.isConfPanelPending();
        if (isConfPanelPending) {
            String deepLinkLat = o.getDeepLinkLat();
            String deepLinkLng = o.getDeepLinkLng();
            if (Utils.m14924g(deepLinkLat) && Utils.m14924g(deepLinkLng)) {
                latLng = new LatLng(Double.parseDouble(deepLinkLat), Double.parseDouble(deepLinkLng));
            }
        }
        this.f10730l = new OlaMapFragment.OlaMapFragment().m13360a(latLng).m13355a(f).m13363a(this.f10741w).m13356a((OnClickListener) this).m13357a(this.f10720V).m13359a((OnMapReadyCallback) this).m13362a(this.au).m13358a(this.at).m13361a((FetchAddress) this).m13366b(isConfPanelPending).m13365a();
        getChildFragmentManager().beginTransaction().replace(R.id.container_map, this.f10730l).commit();
    }

    private void m14272I() {
        if (isVisible()) {
            if (!Ola.f11491h || this.f10702D <= 0) {
                this.f10702D = -1;
            } else {
                Ola.f11491h = false;
            }
            m14277N();
            m14386x();
            if (!(this.f10731m == null || this.f10731m.m14239f() == null || !this.f10731m.m14239f().isTFS())) {
                this.ac = false;
            }
            this.f10728j.findViewById(R.id.button_ride_now).setClickable(false);
            this.f10728j.findViewById(R.id.button_ride_later).setClickable(false);
            if (this.f10717S.m13218d().isB()) {
                this.f10728j.m13611h();
            }
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(az, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        EventBus.m3a().m17b(this);
        ac();
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(aA, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            ak o = this.f10717S.m13259o();
            if (o.isConfPanelPending()) {
                if (!(o.getAddress() == null || o.getDeepLinkLat() == null || o.getDeepLinkLng() == null)) {
                    m14351a(new LatLng(Double.parseDouble(o.getDeepLinkLat()), Double.parseDouble(o.getDeepLinkLng())), o.getAddress());
                }
            } else if (m14346E()) {
                ab();
            } else {
                aa();
            }
            OLog.m13313b("iscabinfo triggered " + this.f10717S.m13218d().isCabInfoTriggered(), new Object[0]);
            if (this.f10731m != null && this.f10731m.isVisible()) {
                m14386x();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(aB, (Object) this, (Object) this));
        super.onPause();
        m14384v();
        m14287X();
        this.ac = false;
        this.f10717S.m13218d().cabInfoTriggered(false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f10728j = (MainActivity) activity;
    }

    private void m14273J() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f10728j);
        if (defaultSharedPreferences.getBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, false)) {
            m14274K();
        } else if (Trace.NULL.equalsIgnoreCase(defaultSharedPreferences.getString(Constants.PREF_EMERGENCY_PHONE, Trace.NULL))) {
            if (Ola.f11479C != null) {
                r0 = Ola.f11479C;
            } else {
                r0 = this.f10728j.getResources().getString(R.string.sos_ec_header);
            }
            if (Ola.f11480D != null) {
                r1 = Ola.f11480D;
            } else {
                r1 = this.f10728j.getResources().getString(R.string.sos_ec_add_contact);
            }
            m14309b(r0, r1);
        } else {
            if (Ola.f11477A != null) {
                r0 = Ola.f11477A;
            } else {
                r0 = this.f10728j.getResources().getString(R.string.sos_ec_header);
            }
            if (Ola.f11478B != null) {
                r1 = Ola.f11478B;
            } else {
                r1 = this.f10728j.getResources().getString(R.string.sos_ec_verify_contact);
            }
            m14354a(r0, r1);
        }
    }

    private void m14274K() {
        startActivity(new Intent(this.f10728j, SosTimerActivity.class));
    }

    private void m14309b(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10728j.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this.f10728j).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.button_yes)).setText(getString(R.string.sos_add_now));
        ((TextView) inflate.findViewById(R.id.button_no)).setText(getString(R.string.sos_later));
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_yes).setOnClickListener(new BookingFragment(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new BookingFragment(this, create));
        create.setCancelable(false);
        create.show();
    }

    private void m14275L() {
        this.f10728j.f9697b.m13650a(6);
        this.f10728j.m13595a(6);
    }

    public void m14354a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10728j.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10728j).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new BookingFragment(this, create));
        create.setCancelable(false);
        create.show();
    }

    public void onClick(View view) {
        CabCategory f;
        Map hashMap;
        switch (view.getId()) {
            case R.id.sos_image:
                m14273J();
            case R.id.view_no_location_overlay:
                ab();
            case R.id.button_navigation_drawer:
                Localytics.tagEvent("Menu Viewed");
                this.f10728j.m13607d();
            case R.id.textView_location:
                if (Utils.m14909a(this.f10728j.getApplicationContext()) && this.f10730l.m13399e()) {
                    m14355a(false);
                    LatLng g = this.f10730l.m13401g();
                    if (g != null) {
                        this.f10728j.m13594a(g.f7554a, g.f7555b);
                    } else {
                        this.f10728j.m13594a((double) Constants.DEFAULT_MAP_LAT, (double) Constants.DEFAULT_MAP_LONG);
                    }
                }
            case R.id.button_map_favorite:
                Sherlock.m13334a("Ins favourite loaded");
                if (Utils.m14909a(this.f10728j.getApplicationContext())) {
                    m14276M();
                }
            case R.id.layout_eta_strip:
                Sherlock.m13334a("Ins track ride shown");
                m14286W();
            case R.id.button_ride_later:
                if (!this.f10703E || !"delivery".equalsIgnoreCase(this.f10731m.m14239f().getId())) {
                    m14373k();
                    if (this.f10731m.m14239f().isDelivery()) {
                        m14308b(view.getContext());
                    } else {
                        m14295a(view.getContext());
                    }
                    f = this.f10731m.m14239f();
                    hashMap = new HashMap();
                    hashMap.put("Category Selected", f.getName());
                    Localytics.tagEvent("Ride later selected", hashMap);
                    hashMap = new HashMap();
                    hashMap.put("Category Selected", f.getName());
                    hashMap.put("Ride Type", "Ride later");
                    Localytics.tagEvent("Ride selected", hashMap);
                } else if (this.f10704F != null && this.f10705G != null) {
                    m14313c(this.f10705G, this.f10704F);
                }
            case R.id.button_ride_now:
                if (!this.f10703E || !"delivery".equalsIgnoreCase(this.f10731m.m14239f().getId())) {
                    m14371i();
                    m14373k();
                    f = this.f10731m.m14239f();
                    if ((f != null && f.isCabAvailableNow()) || (f != null && f.isTFS() && this.ag != null && this.ag.size() > 0)) {
                        hashMap = new HashMap();
                        hashMap.put("Category Selected", f.getName());
                        Localytics.tagEvent("Ride now selected", hashMap);
                        Map hashMap2 = new HashMap();
                        hashMap2.put("Category Selected", f.getName());
                        hashMap2.put("Ride Type", "Ride now");
                        Localytics.tagEvent("Ride selected", hashMap2);
                        hashMap.put("ab_type", this.f10717S.m13218d().isA() ? "A" : "B");
                        Leanplum.track("Ride now selected", hashMap);
                        if (this.f10731m.m14239f().isKaaliPeeliCab()) {
                            m14364c(true);
                        } else if (this.f10731m.m14239f().isAutoRickshaw()) {
                            m14366d(true);
                        } else if (this.f10731m.m14239f().isDelivery()) {
                            m14359b((long) Integer.parseInt(this.f10731m.m14239f().getEta()));
                        } else if ("tfs".equalsIgnoreCase(this.f10731m.m14239f().getId())) {
                            m14356a(true, this.ah);
                        } else {
                            m14380r();
                        }
                        m14377o();
                    }
                } else if (this.f10704F != null && this.f10705G != null) {
                    m14313c(this.f10705G, this.f10704F);
                }
            default:
        }
    }

    private void m14313c(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        ((TextView) inflate.findViewById(R.id.button_no)).setText("CANCEL");
        ((TextView) inflate.findViewById(R.id.button_yes)).setText("RECHARGE");
        inflate.findViewById(R.id.button_no).setOnClickListener(new BookingFragment(this, create));
        inflate.findViewById(R.id.button_yes).setOnClickListener(new AnonymousClass10(this, create));
        create.show();
    }

    private void m14276M() {
        Localytics.tagEvent("Favorite button pressed");
        LatLng g = this.f10730l.m13401g();
        if (g != null && Utils.m14924g(this.f10733o)) {
            this.f10728j.m13598a(this.f10733o, g.f7554a, g.f7555b);
        }
    }

    public boolean m14358a() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.container_sub_panel);
        if ((findFragmentById instanceof BookingCabCategoryFragment) && ((BookingCabCategoryFragment) findFragmentById).m14247n()) {
            ((BookingCabCategoryFragment) findFragmentById).m14248o();
            return true;
        } else if (findFragmentById instanceof BaseBookingCabConfirmationFragment) {
            ((BaseBookingCabConfirmationFragment) findFragmentById).m14176a();
            return true;
        } else if (findFragmentById instanceof KaaliPeeliConformationFragment) {
            ((KaaliPeeliConformationFragment) findFragmentById).m14481a();
            return true;
        } else if (findFragmentById instanceof AutoRickshawBookingConfirmationFragment) {
            ((AutoRickshawBookingConfirmationFragment) findFragmentById).m14144a();
            return true;
        } else if (findFragmentById instanceof DeliveryConformationFragment) {
            ((DeliveryConformationFragment) findFragmentById).m14420a();
            return true;
        } else if (!(findFragmentById instanceof TFSConformationFragment)) {
            return false;
        } else {
            ((TFSConformationFragment) findFragmentById).m13471b();
            return true;
        }
    }

    public void onEventMainThread(ah ahVar) {
        boolean isConnected = ahVar.isConnected();
        OLog.m13311a("Received DataConnectivityEvent. Connected? - " + isConnected, new Object[0]);
        m14319e(isConnected);
    }

    private void m14319e(boolean z) {
        OLog.m13311a("Server availability changed. Available? - " + z, new Object[0]);
        if (z) {
            if (!(getChildFragmentManager().findFragmentById(R.id.container_sub_panel) instanceof BaseBookingCabConfirmationFragment) && !(getChildFragmentManager().findFragmentById(R.id.container_sub_panel) instanceof DeliveryConformationFragment) && !(getChildFragmentManager().findFragmentById(R.id.container_sub_panel) instanceof KaaliPeeliConformationFragment) && !(getChildFragmentManager().findFragmentById(R.id.container_sub_panel) instanceof AutoRickshawBookingConfirmationFragment) && !this.f10738t) {
                m14376n();
                m14378p();
                m14355a(true);
                m14374l();
                m14279P();
                this.f10728j.m13608e().m13652a(true);
                new Handler().postDelayed(new Runnable() {
                    final /* synthetic */ BookingFragment f10639a;

                    {
                        this.f10639a = r1;
                    }

                    public void run() {
                        this.f10639a.m14379q();
                    }
                }, 300);
                m14386x();
            }
        } else if (this.f10738t) {
            this.f10743y.m14868b(false);
            m14378p();
            m14377o();
            new Handler().postDelayed(new Runnable() {
                final /* synthetic */ BookingFragment f10641a;

                {
                    this.f10641a = r1;
                }

                public void run() {
                    this.f10641a.m14349a(CallUsFragment.m14389a());
                    this.f10641a.m14355a(false);
                    this.f10641a.m14375m();
                    this.f10641a.m14280Q();
                    this.f10641a.f10728j.m13608e().m13652a(false);
                }
            }, 300);
        }
    }

    public BookingCabCategoryFragment m14363c() {
        return this.f10731m;
    }

    public void m14353a(String str, long j) {
        if (str != null) {
            this.f10702D = j;
        }
    }

    public String m14365d() {
        if (!(Ola.f11489f == null || this.f10732n == null)) {
            for (aw awVar : Ola.f11489f) {
                if (((long) awVar.getId()) == this.f10702D) {
                    return awVar.getAddress();
                }
            }
        }
        return Trace.NULL;
    }

    public LatLng m14367e() {
        return this.f10730l.m13401g();
    }

    public void m14351a(LatLng latLng, String str) {
        OLog.m13313b("searchFinished", new Object[0]);
        this.f10730l.m13393a(latLng, true);
        m14360b(str);
        if (!m14346E()) {
            m14384v();
            m14383u();
        }
        ab();
    }

    public boolean m14368f() {
        return this.f10730l.m13398d();
    }

    public void m14360b(String str) {
        HashMap hashMap = new HashMap();
        if (Utils.m14917d(getActivity().getApplicationContext())) {
            hashMap.put("Location status", "ON");
        } else {
            hashMap.put("Location status", "OFF");
        }
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("No address found")) {
            if (Utils.m14909a(getActivity().getApplicationContext())) {
                hashMap.put("Failure type", "Google Map error");
            } else {
                hashMap.put("Failure type", "No Network");
            }
            Sherlock.m13342a("Ins search bar address", hashMap, null);
        } else {
            Sherlock.m13340a("Ins search bar address", hashMap);
        }
        this.f10733o = str;
        this.f10734p.setText(str);
        this.f10717S.m13209c().setUserAddress(str);
    }

    public void m14347a(int i) {
        if (this.f10730l.m13399e()) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.f10727d, i});
            this.f10727d = i;
            ofInt.setDuration(300);
            ofInt.addUpdateListener(new AnimatorUpdateListener() {
                final /* synthetic */ BookingFragment f10642a;

                {
                    this.f10642a = r1;
                }

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int parseInt = Integer.parseInt(valueAnimator.getAnimatedValue().toString());
                    this.f10642a.f10732n.m10070a(0, parseInt, 0, parseInt);
                }
            });
            ofInt.start();
        }
    }

    private boolean m14316d(String str) {
        boolean equalsIgnoreCase = this.f10736r.equalsIgnoreCase(str);
        this.f10736r = str;
        this.f10731m.m14232b(str);
        return !equalsIgnoreCase;
    }

    public void m14369g() {
        m14371i();
        m14373k();
    }

    public void m14370h() {
        if (getView() != null) {
            getView().findViewById(R.id.button_ride_now).setEnabled(true);
            getView().findViewById(R.id.button_ride_now).setClickable(true);
        }
    }

    public void m14371i() {
        if (getView() != null) {
            getView().findViewById(R.id.button_ride_now).setEnabled(false);
        }
    }

    public void m14372j() {
        if (getView() != null) {
            getView().findViewById(R.id.button_ride_later).setEnabled(true);
            getView().findViewById(R.id.button_ride_later).setClickable(true);
        }
    }

    public void m14373k() {
        if (getView() != null) {
            getView().findViewById(R.id.button_ride_later).setEnabled(false);
        }
    }

    private void m14277N() {
        if (getView() != null) {
            this.f10741w.setVisibility(0);
            this.f10741w.setEnabled(true);
        }
    }

    private void m14278O() {
        this.f10741w.setEnabled(false);
        this.f10741w.setVisibility(4);
    }

    public void m14355a(boolean z) {
        this.f10734p.setEnabled(z);
    }

    public void m14374l() {
        if (getView() != null) {
            getView().findViewById(R.id.button_map_favorite).setEnabled(true);
        }
    }

    public void m14375m() {
        if (getView() != null) {
            getView().findViewById(R.id.button_map_favorite).setEnabled(false);
        }
    }

    private void m14279P() {
        if (getView() != null) {
            getView().findViewById(R.id.button_navigation_drawer).setEnabled(true);
        }
    }

    private void m14280Q() {
        if (getView() != null) {
            getView().findViewById(R.id.button_navigation_drawer).setEnabled(false);
        }
    }

    public void m14376n() {
        if (!this.f10738t) {
            this.f10738t = true;
            this.f10742x.startAnimation(this.f10722X);
        }
    }

    public void m14377o() {
        if (this.f10738t) {
            this.f10738t = false;
            this.f10742x.startAnimation(this.f10723Y);
        }
    }

    public void m14349a(Fragment fragment) {
        if (isResumed()) {
            this.f10743y.m14861a();
            getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.slideup, R.anim.slidedown, R.anim.slideup, R.anim.slidedown).replace(R.id.container_sub_panel, fragment).addToBackStack(null).commit();
        }
    }

    public void m14378p() {
        if (isAdded()) {
            this.f10744z.m14837a();
            FragmentManager childFragmentManager = getChildFragmentManager();
            Fragment findFragmentById = childFragmentManager.findFragmentById(R.id.container_sub_panel);
            if (findFragmentById != null) {
                if (childFragmentManager.getBackStackEntryCount() > 0) {
                    childFragmentManager.popBackStack();
                }
                childFragmentManager.beginTransaction().remove(findFragmentById).commit();
            }
        }
    }

    public void m14379q() {
        m14361b(true);
    }

    public void m14357a(boolean z, String str) {
        m14361b(z);
        m14349a(this.f10731m);
        if (str != null) {
            Intent intent = new Intent(this.f10728j, TFSTrackRideActivity.class);
            intent.putExtra(Constants.ARG_BOOKING_ID, str);
            intent.putExtra(Constants.CITY, this.f10736r);
            startActivity(intent);
            this.f10731m.m14246m();
        }
    }

    public void m14361b(boolean z) {
        int i = getChildFragmentManager().findFragmentById(R.id.container_sub_panel) == null ? 0 : 550;
        m14378p();
        m14355a(true);
        m14374l();
        this.af = new BookingFragment(this, z);
        this.ae.postDelayed(this.af, (long) i);
    }

    public void m14380r() {
        m14348a((long) Integer.parseInt(this.f10731m.m14239f().getEta()));
    }

    public void m14348a(long j) {
        int i = 0;
        OLog.m13313b("replaceCabConfirmationFragment", new Object[0]);
        m14384v();
        m14287X();
        m14355a(false);
        m14375m();
        this.f10732n.m10077d().m10565d(false);
        this.f10732n.m10077d().m10569h(false);
        m14278O();
        this.f10744z.m14842b();
        if (getChildFragmentManager().findFragmentById(R.id.container_sub_panel) != null) {
            i = 550;
        }
        m14378p();
        new Handler().postDelayed(new AnonymousClass15(this, j), (long) i);
    }

    private boolean m14281R() {
        CabCategory f = this.f10731m.m14239f();
        if (f == null) {
            return false;
        }
        CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse = null;
        if (!(this.f10717S.m13218d().getCityBasedCarModels() == null || this.f10717S.m13218d().getCityBasedCarModels().getCarModels() == null)) {
            cityBaseCarModelDetailsResponse = this.f10717S.m13218d().getCityBasedCarModels().getCarModels().getCategoryDetails(f.getId());
        }
        if (cityBaseCarModelDetailsResponse == null) {
            return false;
        }
        return cityBaseCarModelDetailsResponse.isSurchargeApplicable();
    }

    public Fragment m14381s() {
        return getChildFragmentManager().findFragmentById(R.id.container_sub_panel);
    }

    public void m14364c(boolean z) {
        int i = 0;
        OLog.m13313b("replacing conformation fragment", new Object[0]);
        m14384v();
        m14287X();
        m14355a(false);
        m14375m();
        this.f10732n.m10077d().m10565d(false);
        this.f10732n.m10077d().m10569h(false);
        m14278O();
        this.f10744z.m14842b();
        if (getChildFragmentManager().findFragmentById(R.id.container_sub_panel) != null) {
            i = 550;
        }
        m14378p();
        m14377o();
        new Handler().postDelayed(new AnonymousClass16(this, z), (long) i);
    }

    public void m14366d(boolean z) {
        int i = 0;
        OLog.m13313b("replaceAutorickshawConformationFragment", new Object[0]);
        m14384v();
        m14287X();
        m14355a(false);
        m14375m();
        this.f10732n.m10077d().m10565d(false);
        this.f10732n.m10077d().m10569h(false);
        m14278O();
        this.f10744z.m14842b();
        if (getChildFragmentManager().findFragmentById(R.id.container_sub_panel) != null) {
            i = 550;
        }
        m14378p();
        m14377o();
        new Handler().postDelayed(new AnonymousClass17(this, z), (long) i);
    }

    public void m14356a(boolean z, int i) {
        int i2 = 0;
        OLog.m13313b("replacing conformation fragment", new Object[0]);
        m14384v();
        m14287X();
        m14355a(false);
        m14375m();
        this.f10732n.m10077d().m10565d(false);
        this.f10732n.m10077d().m10569h(false);
        m14278O();
        this.f10744z.m14842b();
        if (getChildFragmentManager().findFragmentById(R.id.container_sub_panel) != null) {
            i2 = 550;
        }
        m14378p();
        new Handler().postDelayed(new AnonymousClass18(this, i, z), (long) i2);
    }

    public void m14359b(long j) {
        int i = 0;
        OLog.m13313b("replacing conformation fragment", new Object[0]);
        m14384v();
        m14287X();
        m14355a(false);
        m14375m();
        this.f10732n.m10077d().m10565d(false);
        this.f10732n.m10077d().m10569h(false);
        m14278O();
        this.f10744z.m14842b();
        if (getChildFragmentManager().findFragmentById(R.id.container_sub_panel) != null) {
            i = 550;
        }
        m14378p();
        new Handler().postDelayed(new AnonymousClass19(this, j), (long) i);
    }

    private void m14295a(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_date_time_picker, null, false);
        DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        TimePicker timePicker = (TimePicker) inflate.findViewById(R.id.time_picker);
        Calendar instance = Calendar.getInstance();
        instance.add(10, 1);
        instance.add(12, 15);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, instance.getMinimum(11));
        instance2.set(12, instance.getMinimum(12));
        instance2.set(13, instance.getMinimum(13));
        instance2.set(14, instance.getMinimum(14));
        datePicker.setMinDate(instance2.getTimeInMillis());
        instance2 = Calendar.getInstance();
        instance2.add(2, 1);
        datePicker.setMaxDate(instance2.getTime().getTime());
        datePicker.updateDate(instance.get(1), instance.get(2), instance.get(5));
        timePicker.setCurrentHour(Integer.valueOf(instance.get(11)));
        timePicker.setCurrentMinute(Integer.valueOf(instance.get(12)));
        AlertDialog create = new Builder(this.f10728j, 16973935).setView(inflate).setPositiveButton("Done", new AnonymousClass21(this, datePicker, timePicker)).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            final /* synthetic */ BookingFragment f10655a;

            {
                this.f10655a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.f10655a.m14372j();
            }
        }).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14308b(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_date_time_picker, null, false);
        DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        ((TimePicker) inflate.findViewById(R.id.time_picker)).setVisibility(8);
        Calendar instance = Calendar.getInstance();
        instance.add(5, 1);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(new Date());
        datePicker.setMinDate(instance2.getTime().getTime() - 1000);
        instance2.add(2, 1);
        datePicker.setMaxDate(instance2.getTime().getTime());
        datePicker.updateDate(instance.get(1), instance.get(2), instance.get(5));
        AlertDialog create = new Builder(this.f10728j, 16973935).setView(inflate).setPositiveButton("Done", new AnonymousClass24(this, datePicker)).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            final /* synthetic */ BookingFragment f10659a;

            {
                this.f10659a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.f10659a.m14372j();
            }
        }).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14282S() {
        View inflate = ((LayoutInflater) this.f10728j.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10728j).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid pickup time");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Please make sure that pickup time is after atleast an hour from now");
        inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass25(this, create));
        create.show();
    }

    public YellowStripHelper m14382t() {
        return this.f10743y;
    }

    public void m14383u() {
        OLog.m13313b("-----> started cab info ", new Object[0]);
        if (this.f10729k == null) {
            this.f10729k = new Handler();
        }
        this.f10729k.post(this.al);
    }

    public void m14384v() {
        OLog.m13313b("-----> stopped cab info ", new Object[0]);
        this.f10717S.m13169a("cabinfoRequestTag");
        if (this.f10729k != null) {
            this.f10729k.removeCallbacks(this.al);
        }
    }

    public void m14385w() {
        this.f10699A = false;
    }

    public void m14386x() {
        OLog.m13313b("refreshCabUpdate : actualLocation - " + this.f10730l.m13400f(), new Object[0]);
        if (this.f10730l.m13400f() != null) {
            m14384v();
            this.f10729k = null;
            m14383u();
        }
    }

    private void m14296a(LatLng latLng) {
        if (latLng != null) {
            String e;
            if (this.f10731m.m14239f() != null) {
                e = this.f10731m.m14238e();
            } else {
                e = null;
            }
            this.f10717S.m13192a(new WeakReference(this.am), this.f10730l.m13398d(), latLng, this.f10730l.m13400f(), this.f10731m.m14234c(), e, "cabinfoRequestTag");
        }
    }

    private void m14283T() {
        OLog.m13313b("navigateToCancellationScreen", new Object[0]);
        m14287X();
        if (getFragmentManager().findFragmentById(R.id.container) instanceof LocalTaxiBookingRequestFragment) {
            m14384v();
            return;
        }
        LatLng latLng = new LatLng(this.f10714P.doubleValue(), this.f10715Q.doubleValue());
        this.f10728j.f9699d = false;
        this.f10728j.f9698c = true;
        m14384v();
        this.f10728j.m13601a(this.f10709K, latLng, this.f10710L, this.f10711M, this.f10712N, this.f10713O, this.f10707I, this.f10708J, this.f10736r);
    }

    private void m14321f(boolean z) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f10728j).edit();
        edit.putBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, z);
        edit.apply();
    }

    private void m14284U() {
        LatLng g = this.f10730l.m13401g();
        if (this.f10730l.m13399e() && g != null) {
            this.f10717S.m13212c(new WeakReference(this.aq), Double.valueOf(g.f7554a), Double.valueOf(g.f7555b), "cityBasedRequestFareTag");
        }
    }

    private void m14285V() {
        LatLng g = this.f10730l.m13401g();
        if (this.f10730l.m13399e() && g != null) {
            this.f10717S.m13201b(new WeakReference(this.ar), Double.valueOf(g.f7554a), Double.valueOf(g.f7555b), "tfsFareRequestTag");
        }
    }

    private void m14286W() {
        Localytics.tagEvent("Tracking Initiated");
        this.f10726c = new ProgressDialog(this.f10728j, R.style.TransparentProgressDialog);
        this.f10726c.setIndeterminateDrawable(this.f10728j.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10726c.setCancelable(false);
        this.f10726c.show();
        this.f10717S.m13240h(new WeakReference(this.ak), this.f10744z.m14844d(), f10693a);
    }

    protected Bitmap m14362c(String str) {
        Bitmap bitmap;
        List<CabImages> list = Ola.f11488e;
        Bitmap bitmap2 = null;
        if (list == null || list.size() == 0 || str == null) {
            bitmap = null;
        } else {
            for (CabImages cabImages : list) {
                if (str.equalsIgnoreCase(cabImages.getId()) || (str.equalsIgnoreCase("ANY") && cabImages.getId().equalsIgnoreCase("economy_sedan"))) {
                    byte[] decode = Base64.decode(cabImages.getData().getBytes(), 0);
                    bitmap = BitmapFactoryInstrumentation.decodeByteArray(decode, 0, decode.length);
                } else {
                    bitmap = bitmap2;
                }
                bitmap2 = bitmap;
            }
            bitmap = bitmap2;
        }
        if (bitmap != null) {
            return bitmap;
        }
        int i;
        if (this.f10731m.m14239f().isKaaliPeeliCab()) {
            i = R.drawable.ic_kp_car_front;
        } else if (this.f10731m.m14239f().isAutoRickshaw()) {
            i = R.drawable.ic_booking_auto_map;
        } else if (!this.f10731m.m14239f().isDelivery()) {
            i = R.drawable.ic_cab_top_view;
        } else if ("bg_style_myntra".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.style_myntra_map;
        } else if ("bg_delivery_electronic".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.electronic_map;
        } else if ("bg_xmas_1".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.xmas_1_map;
        } else if ("bg_xmas_2".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.xmas_2_map;
        } else if ("bg_movie".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.movie_map;
        } else if ("bg_marketing_default".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.ic_cab_top_view;
        } else if ("bg_comicon".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.comic_map;
        } else if ("bg_ipl".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.ipl_map;
        } else if ("bg_fathersday".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.fathersday;
        } else if ("bg_olanight".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.olanight;
        } else if ("bg_envday".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.envday;
        } else if ("bg_carshare".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.ic_cab_top_view;
        } else if ("bg_donorsday".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.donorsday;
        } else if ("bg_myntra".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.myntra_map;
        } else if ("bg_access".equalsIgnoreCase(this.f10731m.m14239f().getImage_name())) {
            i = R.drawable.ic_cab_top_view;
        } else {
            i = R.drawable.ic_cab_top_view;
        }
        return m14304b(i);
    }

    private Bitmap m14304b(int i) {
        if (i != R.drawable.ic_cab_top_view) {
            return BitmapFactoryInstrumentation.decodeResource(this.f10728j.getResources(), i);
        }
        if (this.ai != null) {
            return this.ai;
        }
        Bitmap decodeResource = BitmapFactoryInstrumentation.decodeResource(this.f10728j.getResources(), i);
        this.ai = decodeResource;
        return decodeResource;
    }

    private void m14318e(String str) {
        Intent intent;
        switch (AnonymousClass32.f10673a[this.f10717S.m13218d().getAppState().ordinal()]) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                if (!isAdded()) {
                    return;
                }
                if (!Utils.m14924g(this.f10728j.getIntent().getStringExtra(Constants.PUSH_LANDING))) {
                    m14384v();
                    intent = new Intent(this.f10728j, TrackRideActivity.class);
                    intent.putExtra(Constants.ARG_BOOKING_ID, str);
                    startActivity(intent);
                } else if (!TextUtils.equals(this.f10728j.getIntent().getStringExtra(Constants.PUSH_LANDING), Constants.LANDING_PAGE_TRACK_RIDE)) {
                    DataManager.m13137a(this.f10728j).m13243i(null, Constants.PUSH_LANDING);
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                intent = new Intent(this.f10728j, RideSummaryActivity.class);
                if (isAdded()) {
                    try {
                        m14384v();
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                m14288Y();
            default:
        }
    }

    private CabCategory m14291a(ArrayList<CabCategory> arrayList, String str) {
        if (arrayList == null || arrayList.size() == 0 || str == null) {
            return null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CabCategory cabCategory = (CabCategory) it.next();
            if (cabCategory.getId().equalsIgnoreCase(str)) {
                return cabCategory;
            }
        }
        return null;
    }

    public void m14387y() {
        if (this.f10716R != null && this.f10716R.isShowing()) {
            this.f10716R.dismiss();
        }
    }

    public void m14388z() {
        if (!this.f10716R.isShowing()) {
            this.f10716R.show();
        }
    }

    public OlaMapFragment m14342A() {
        return this.f10730l;
    }

    private void m14300a(Object obj) {
        OLog.m13313b("parseCabQueryResponse", new Object[0]);
        this.f10724Z = false;
        this.aa = false;
        if (this.f10730l.m13399e()) {
            m14387y();
            BookingCabInfoResponse bookingCabInfoResponse = (BookingCabInfoResponse) obj;
            if (bookingCabInfoResponse != null && bookingCabInfoResponse.isForceLogout()) {
                new ForceLogoutCommand(true).m13270a(getActivity());
            }
            if (bookingCabInfoResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                if (bookingCabInfoResponse.getNextCallAfter() != null) {
                    f10694e = bookingCabInfoResponse.getNextCallAfter().intValue();
                    f10695f = f10694e * Constants.MILLIS_IN_A_SECOND;
                }
                HashMap hashMap = new HashMap();
                if (Utils.m14924g(this.f10736r)) {
                    hashMap.put("Booking city", this.f10736r);
                } else {
                    hashMap.put("Booking city", "NA");
                }
                Sherlock.m13340a("Ins category panel shown", hashMap);
                if (!(bookingCabInfoResponse == null || bookingCabInfoResponse.getBookingStatus() == null)) {
                    if (bookingCabInfoResponse.getBookingStatus().equalsIgnoreCase(Constants.RIDE_STATUS_IN_PROGRESS)) {
                        Context context;
                        if (bookingCabInfoResponse.isEmergencyNumberVerified()) {
                            this.f10718T.setImageResource(R.drawable.sos_button_bg);
                        } else {
                            this.f10718T.setImageResource(R.drawable.sos_button_emergency_bg);
                        }
                        m14321f(bookingCabInfoResponse.isEmergencyNumberVerified());
                        if (this.f10728j != null) {
                            context = this.f10728j;
                        } else {
                            context = getActivity();
                        }
                        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                        if (PreferenceManager.getDefaultSharedPreferences(this.f10728j != null ? this.f10728j : getActivity()).getBoolean(Constants.PREF_SOS_EMERGENCY_CONTACT, false) != bookingCabInfoResponse.isEmergencyNumberVerified()) {
                            edit.putBoolean(Constants.PREF_SOS_EMERGENCY_CONTACT, bookingCabInfoResponse.isEmergencyNumberVerified()).apply();
                        }
                        this.f10718T.setVisibility(0);
                    } else {
                        this.f10718T.setVisibility(4);
                    }
                }
            }
            if (bookingCabInfoResponse.getStatus().equalsIgnoreCase("FAILURE")) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("Booking city", Sherlock.m13349d(this.f10736r));
                Sherlock.m13338a("Ins category panel shown", "Failure", null, bookingCabInfoResponse.getReason(), false, hashMap2);
                Sherlock.m13338a("Ins categories switched", "Failure", null, bookingCabInfoResponse.getReason(), false, hashMap2);
                if (this.f10743y.m14869b()) {
                    this.f10743y.m14866b(bookingCabInfoResponse.getReason());
                }
                if (Constants.USER_OUT_OF_SERVICE_AREA.equalsIgnoreCase(bookingCabInfoResponse.getReason())) {
                    OLog.m13313b("USER_OUT_OF_SERVICE_AREA", new Object[0]);
                    this.f10732n.m10076c();
                    m14384v();
                    m14287X();
                    Localytics.tagEvent("User out of service area");
                    this.f10717S.m13259o().setIsConfPanelPending(false);
                }
                this.f10743y.m14865a(false);
                this.f10743y.m14864a(bookingCabInfoResponse.getText() != null ? bookingCabInfoResponse.getText() : bookingCabInfoResponse.getReason(), (int) HttpStatus.SC_MULTIPLE_CHOICES);
                this.f10743y.m14868b(true);
                m14378p();
                this.f10731m.m14241h();
                m14369g();
                try {
                    m14347a((int) getResources().getDimension(R.dimen.booking_buttons_panel_height));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            String str;
            dg booking = bookingCabInfoResponse.getBooking();
            if (bookingCabInfoResponse.getStateId() != AppState.HOME_PAGE.ordinal()) {
                str = null;
                AppState a = AppState.m12881a(bookingCabInfoResponse.getStateId());
                this.f10717S.m13218d().setAppState(a);
                if (booking != null) {
                    str = booking.getBookingId();
                }
                if (AppState.LOCAL_TAXI_CANCELLATION.equals(a)) {
                    try {
                        this.f10707I = booking.getBookingId();
                        this.f10708J = bookingCabInfoResponse.isEnableRetry();
                        Ola.f11492i = bookingCabInfoResponse.getCancelReason();
                        Ola.f11493j = bookingCabInfoResponse.getCancelKey();
                        ar driverCancellation = bookingCabInfoResponse.getDriverCancellation();
                        this.f10709K = driverCancellation.getCategoryId();
                        this.f10714P = driverCancellation.getUserLat();
                        this.f10715Q = driverCancellation.getUserLng();
                        this.f10710L = driverCancellation.getDropLocality();
                        this.f10711M = driverCancellation.getDropLocalityId();
                        m14283T();
                    } catch (NullPointerException e2) {
                        e2.printStackTrace();
                    }
                } else if (AppState.CITY_TAXI_CANCELLATION.equals(a)) {
                    m14315d(bookingCabInfoResponse.getHeader(), bookingCabInfoResponse.getMessage());
                } else {
                    m14318e(str);
                }
                if (!Utils.m14924g(getActivity().getIntent().getStringExtra(Constants.PUSH_LANDING))) {
                    return;
                }
            }
            if (bookingCabInfoResponse.getAllottedCabDirection() != null) {
                AllotedCabDirection allottedCabDirection = bookingCabInfoResponse.getAllottedCabDirection();
                if (allottedCabDirection.isCabAlloted()) {
                    allottedCabDirection.getAllotedCabId();
                    this.f10744z.m14839a(allottedCabDirection.getLastBookingId());
                    this.f10744z.m14840a(allottedCabDirection.getDuration(), 600, allottedCabDirection.getCab_type());
                } else {
                    this.f10744z.m14837a();
                }
            } else {
                this.f10744z.m14837a();
            }
            if (bookingCabInfoResponse.getKpCategories() != null) {
                this.f10731m.m14229a(bookingCabInfoResponse.getKpCategories());
            }
            ArrayList cabCategories = bookingCabInfoResponse.getCabCategories();
            if (cabCategories == null || cabCategories.size() <= 0) {
                OLog.m13317d("cabCategories is null, skipping the update response", new Object[0]);
                return;
            }
            CabCategory cabCategory;
            String str2;
            String deeplinkCategory;
            ArrayList arrayList = new ArrayList(bookingCabInfoResponse.getCabCategories().size());
            Iterator it = bookingCabInfoResponse.getCabCategories().iterator();
            while (it.hasNext()) {
                cabCategory = (CabCategory) it.next();
                if (cabCategory.isRideLaterEnable()) {
                    arrayList.add(new ac(cabCategory.getId(), cabCategory.getDisplayName()));
                }
            }
            this.f10731m.m14233b(arrayList);
            String str3 = null;
            String str4 = this.f10736r;
            if (bookingCabInfoResponse.getResponseCategory() != null) {
                str = bookingCabInfoResponse.getResponseCategory();
                this.f10706H = str;
                str2 = str;
            } else {
                str2 = null;
            }
            if (bookingCabInfoResponse.getCityTag() != null) {
                str = bookingCabInfoResponse.getCityTag();
                if (m14316d(str)) {
                    this.f10731m.m14240g();
                    m14284U();
                    m14285V();
                }
                str3 = str;
            }
            Object string = this.f10725b.getString(Constants.CITY, Trace.NULL);
            if ((TextUtils.isEmpty(string) || string.equalsIgnoreCase(WanType.UNKNOWN)) && bookingCabInfoResponse.getUserCity() != null) {
                Editor edit2 = this.f10725b.edit();
                edit2.putString(Constants.CITY, bookingCabInfoResponse.getUserCity());
                edit2.apply();
            }
            this.f10737s = bookingCabInfoResponse.getLocationTag();
            if (this.f10743y.m14871d()) {
                this.f10743y.m14868b(false);
            }
            boolean isConfPanelPending = this.f10717S.m13259o().isConfPanelPending();
            if (isConfPanelPending) {
                ArrayList arrayList2 = new ArrayList();
                deeplinkCategory = this.f10717S.m13259o().getDeeplinkCategory();
                this.f10731m.m14236d();
                if (cabCategories != null && cabCategories.size() > 0) {
                    Iterator it2 = cabCategories.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((CabCategory) it2.next()).getId());
                    }
                    if (arrayList2.indexOf(deeplinkCategory) != -1) {
                        str2 = deeplinkCategory;
                    }
                }
                this.f10717S.m13259o().setIsConfPanelPending(false);
            }
            deeplinkCategory = str2;
            if (!(cabCategories == null || this.f10735q)) {
                Iterator it3 = cabCategories.iterator();
                while (it3.hasNext()) {
                    if (((CabCategory) it3.next()).getId().equalsIgnoreCase("food_delivery")) {
                        this.f10735q = true;
                        m14270G();
                        break;
                    }
                }
            }
            HashMap hashMap3 = new HashMap();
            if (!this.f10731m.m14234c() || this.f10731m.m14239f() == null) {
                hashMap3.put("Cab Category", String.valueOf(deeplinkCategory));
                str2 = deeplinkCategory;
            } else {
                str = this.f10731m.m14239f().getId();
                hashMap3.put("Cab Category", String.valueOf(this.f10731m.m14239f().getDisplayName()));
                str2 = str;
            }
            hashMap3.put("Booking city", Sherlock.m13349d(this.f10736r));
            Sherlock.m13340a("Ins categories switched", hashMap3);
            CabCategory a2 = m14291a(cabCategories, str2);
            if (a2 == null) {
                a2 = m14291a(cabCategories, this.f10706H);
                if (a2 == null && cabCategories != null && cabCategories.size() > 0) {
                    cabCategory = (CabCategory) cabCategories.get(0);
                    deeplinkCategory = cabCategory.getId();
                    this.f10706H = deeplinkCategory;
                    a2 = cabCategory;
                }
            }
            if (a2 != null && (!a2.isCabAvailableNow() || a2.isSurchargeApplicable())) {
                cabCategory = m14291a(this.f10731m.m14243j(), str2);
                if (cabCategory == null || cabCategory.isCabAvailableNow() != a2.isCabAvailableNow()) {
                    this.f10743y.m14865a(false);
                }
                this.f10743y.m14864a(a2.getDisplayText() != null ? a2.getDisplayText() : Trace.NULL, str3.equalsIgnoreCase(str4) ? 0 : 600);
            }
            Iterator it4 = cabCategories.iterator();
            Object obj2 = null;
            while (it4.hasNext()) {
                cabCategory = (CabCategory) it4.next();
                if (cabCategory.isTFS()) {
                    obj2 = 1;
                }
                if (!cabCategory.isCabAvailable()) {
                    Map hashMap4 = new HashMap();
                    hashMap4.put("City name", str3);
                    hashMap4.put("Cab category", cabCategory.getDisplayName());
                    Localytics.tagEvent("No Cabs Available", hashMap4);
                }
            }
            if (obj2 == null) {
                m14287X();
            } else if (!this.ac) {
                this.ac = true;
                m14343B();
            }
            if (cabCategories == null || cabCategories.size() == 0) {
                m14378p();
            }
            if (this.f10731m.m14230a(cabCategories, true)) {
                m14379q();
            }
            if (!this.f10731m.m14234c() || isConfPanelPending) {
                this.f10731m.m14227a(deeplinkCategory);
            }
            cabCategory = this.f10731m.m14239f();
            if (m14291a(cabCategories, cabCategory.getId()) == null) {
                cabCategory = a2;
            }
            this.aa = cabCategory.isRideLaterEnable();
            this.f10724Z = cabCategory.isRideNowEnable();
            if ((cabCategory.isKaaliPeeliCab() || cabCategory.isAutoRickshaw()) && cabCategory.isCabAvailable() && cabCategory.getDisplayText() != null) {
                this.f10743y.m14867b(cabCategory.getDisplayText(), str3.equalsIgnoreCase(str4) ? 0 : 600);
            }
            if (cabCategory.isDelivery()) {
                this.f10740v = cabCategory.getCampaign_tag();
                this.f10703E = bookingCabInfoResponse.isDeliveryRechargeScreen();
                if (!(!this.f10703E || TextUtils.isEmpty(bookingCabInfoResponse.getDeliveryRechargeText()) || TextUtils.isEmpty(bookingCabInfoResponse.getDeliveryRechargeText()))) {
                    this.f10704F = bookingCabInfoResponse.getDeliveryRechargeText();
                    this.f10705G = bookingCabInfoResponse.getDeliveryRechargeHeader();
                }
            }
            List cabs = bookingCabInfoResponse.getCabs();
            if (!a2.isCabAvailable()) {
                this.f10699A = true;
            }
            if (a2.isCabAvailable() && cabs != null && cabs.size() != 0) {
                LatLng latLng = new LatLng(((CabInfo) cabs.get(0)).getLatitude(), ((CabInfo) cabs.get(0)).getlongitude());
                LatLng g = this.f10730l.m13401g();
                LatLng latLng2 = null;
                if (!(latLng == null || g == null)) {
                    latLng2 = OlaApplicationUtils.m14900a(g.f7554a, g.f7555b, latLng.f7554a, latLng.f7555b);
                }
                m14301a(cabs, latLng2, latLng);
            } else if (!a2.isTFS()) {
                this.f10732n.m10076c();
            }
            m14344C();
            if (isConfPanelPending && a2.isCabAvailable() && this.f10724Z) {
                if (!(this.ae == null || this.af == null)) {
                    this.ae.removeCallbacks(this.af);
                    this.af = null;
                }
                getView().findViewById(R.id.button_ride_now).performClick();
            }
        }
    }

    private void m14287X() {
        this.ad.removeCallbacks(this.aw);
        this.f10717S.m13169a("TFS_CAB_INFO");
    }

    public void m14343B() {
        m14287X();
        if (this.f10717S.m13218d().getConfigurationResponse() != null && this.f10717S.m13218d().getConfigurationResponse().getTfsCabInfoPolling() > 0) {
            f10698i = this.f10717S.m13218d().getConfigurationResponse().getTfsCabInfoPolling() * Constants.MILLIS_IN_A_SECOND;
        }
        this.ad.postDelayed(this.aw, (long) f10698i);
        if (this.f10730l.m13401g() != null) {
            this.f10717S.m13200b(new WeakReference(this.aj), this.f10730l.m13401g(), "TFS_CAB_INFO");
        }
    }

    private void m14315d(String str, String str2) {
        this.f10717S.m13225e(null);
        if (this.ab == null || !this.ab.isShowing()) {
            long currentTimeMillis = System.currentTimeMillis();
            View inflate = ((LayoutInflater) this.f10728j.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
            this.ab = new Builder(this.f10728j).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
            ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
            inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass26(this, currentTimeMillis));
            this.ab.setCancelable(false);
            this.ab.show();
        }
    }

    public void m14344C() {
        if (this.aa) {
            m14372j();
        } else {
            m14373k();
        }
        if (this.f10724Z) {
            m14370h();
        } else {
            m14371i();
        }
        if (this.f10731m != null && this.f10731m.m14239f() != null && this.f10731m.m14239f().isTFS()) {
            if (this.ag == null || this.ag.size() <= 0) {
                m14369g();
            } else {
                m14370h();
            }
        }
    }

    private void m14288Y() {
        if (!this.ap) {
            Builder builder = new Builder(this.f10728j);
            builder.setTitle(getString(R.string.app_needs_update_title));
            builder.setMessage(getString(R.string.app_needs_update_desc));
            builder.setCancelable(false);
            builder.setPositiveButton(getString(R.string.app_needs_update_pos_btn), new DialogInterface.OnClickListener() {
                final /* synthetic */ BookingFragment f10667a;

                {
                    this.f10667a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("market://details?id=" + this.f10667a.f10728j.getPackageName()));
                    this.f10667a.startActivity(intent);
                    dialogInterface.dismiss();
                    this.f10667a.f10728j.finish();
                }
            });
            builder.setNegativeButton(getString(R.string.app_needs_update_neg_btn), new DialogInterface.OnClickListener() {
                final /* synthetic */ BookingFragment f10668a;

                {
                    this.f10668a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.f10668a.f10728j.finish();
                }
            });
            builder.create().show();
            this.ap = true;
        }
    }

    public void m14350a(com.google.android.m4b.maps.GoogleMap googleMap) {
        this.f10732n = googleMap;
        HashMap hashMap = new HashMap();
        if (this.f10730l.m13401g() != null) {
            hashMap.put("Actual location", "yes");
            Sherlock.m13340a("Ins location fix obtained", hashMap);
        } else {
            hashMap.put("Actual location", "no");
            Sherlock.m13342a("Ins location fix obtained", hashMap, null);
        }
        m14277N();
        m14289Z();
        m14355a(true);
        this.f10734p.setEnabled(true);
        Object a = DataUpdaterManager.m13261a(getActivity().getApplication()).m13263a().m13320a("cab_info");
        if (a == null || !(a instanceof BookingCabInfoResponse)) {
            DataUpdaterManager.m13261a(this.f10728j.getApplication()).m13263a().addObserver(this.av);
            return;
        }
        m14300a(a);
        ac();
    }

    public void m14352a(String str) {
        if (m14346E()) {
            this.f10734p.setText(str);
            m14360b(str);
            return;
        }
        this.f10734p.setText(this.f10728j.getString(R.string.pickup_address));
    }

    private void m14289Z() {
        switch (Utils.m14921f(this.f10728j.getApplicationContext())) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                aa();
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                if (this.f10717S.m13209c().getUserLocation() == null) {
                    aa();
                    break;
                }
                break;
        }
        OLog.m13311a("UserInfo " + this.f10717S.m13209c(), new Object[0]);
        OLog.m13311a("UserLocation" + this.f10717S.m13209c().getUserLocation(), new Object[0]);
        Object valueOf = this.f10717S.m13209c().getUserLocation() != null ? String.valueOf(this.f10717S.m13209c().getUserLocation().getAccuracy()) : "NA";
        Map hashMap = new HashMap();
        hashMap.put("android_location_mode", Utils.m14923g(this.f10728j.getApplicationContext()));
        hashMap.put("android_gps_accuracy", valueOf);
        hashMap.put("android_device", this.f10717S.m13224e().getPhoneModel());
        hashMap.put("android_version", this.f10717S.m13224e().getOsVersion());
        Localytics.tagEvent("location_mode_tracking", hashMap);
    }

    private void aa() {
        if (!(getChildFragmentManager().findFragmentById(R.id.container_sub_panel) instanceof BaseBookingCabConfirmationFragment)) {
            Sherlock.m13336a("Ins app launch time", null, "location not found", false);
            Sherlock.m13336a("Ins search bar address", null, "location not found", false);
            Sherlock.m13336a("Ins location fix obtained", null, "location not found", false);
            Sherlock.m13336a("Ins category panel shown", null, "location not found", false);
            this.f10721W.setVisibility(0);
            this.f10728j.findViewById(R.id.layout_top).setBackgroundColor(getResources().getColor(R.color.ola_black_semi_transparency));
        }
    }

    private void ab() {
        this.f10721W.setVisibility(8);
        this.f10728j.findViewById(R.id.layout_top).setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    public void onEvent(bm bmVar) {
        OLog.m13311a("Location Changed", new Object[0]);
        ab();
        m14386x();
    }

    public void m14345D() {
        if (this.ag == null || this.ag.size() <= 0) {
            this.f10732n.m10076c();
            return;
        }
        LatLng latLng = new LatLng(Double.valueOf(((NearestCabs) this.ag.get(0)).getLat()).doubleValue(), Double.valueOf(((NearestCabs) this.ag.get(0)).getLng()).doubleValue());
        LatLng g = this.f10730l.m13401g();
        LatLng latLng2 = null;
        if (g != null) {
            latLng2 = OlaApplicationUtils.m14900a(g.f7554a, g.f7555b, latLng.f7554a, latLng.f7555b);
        }
        m14297a(latLng2, latLng);
    }

    private void m14297a(LatLng latLng, LatLng latLng2) {
        this.f10732n.m10076c();
        this.f10732n.m10073a(new GoogleMap() {
            final /* synthetic */ BookingFragment f10672a;

            {
                this.f10672a = r1;
            }

            public boolean m14260a(Marker marker) {
                return true;
            }
        });
        if (this.ag != null && this.ag.size() > 0) {
            for (NearestCabs nearestCabs : this.ag) {
                if (nearestCabs != null) {
                    MarkerOptions a = new MarkerOptions().m10759a(new LatLng(Double.valueOf(nearestCabs.getLat()).doubleValue(), Double.valueOf(nearestCabs.getLng()).doubleValue()));
                    a.m10760a(BitmapDescriptorFactory.m10823a((int) R.drawable.tfs_map_icon));
                    this.f10732n.m10068a(a);
                }
            }
        }
        if (!this.f10699A && latLng != null && latLng2 != null) {
            C0595a c0595a = new C0595a();
            c0595a.m10738a(new LatLng(latLng.f7554a, latLng.f7555b));
            c0595a.m10738a(latLng2);
            this.f10730l.m13391a(CameraUpdateFactory.m7792a(c0595a.m10739a(), 50));
            this.f10699A = true;
        }
    }

    boolean m14346E() {
        return this.f10717S.m13209c().getUserLocation() != null;
    }

    private void ac() {
        DataUpdaterManager.m13261a(this.f10728j.getApplication()).m13263a().deleteObserver(this.av);
        DataUpdaterManager.m13261a(this.f10728j.getApplication()).m13263a().m13324b("cab_info");
    }
}
