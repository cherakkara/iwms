package com.olacabs.customer.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.apsalar.sdk.Apsalar;
import com.google.android.m4b.maps.model.LatLng;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.activities.LeanplumFragmentActivity;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.CallSupportCommand;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.DataUpdaterManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p075a.AnalyticsHelper;
import com.olacabs.customer.p075a.AnalyticsManager;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.ab;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.db;
import com.olacabs.customer.ui.LocalTaxiBookingRequestFragment.LocalTaxiBookingRequestFragment;
import com.olacabs.customer.ui.NavigationDrawerFragment.C0840b;
import com.olacabs.customer.ui.ai.SearchFragment;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.RootUtil;
import com.olacabs.customer.utils.Utils;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

public class MainActivity extends LeanplumFragmentActivity implements C0840b, SearchFragment, LocalTaxiBookingRequestFragment {
    public static final String f9691a;
    @Variable
    public static String abCampaignName;
    public static final String f9692f;
    private static final JoinPoint f9693s = null;
    private static final JoinPoint f9694t = null;
    private static final JoinPoint f9695u = null;
    private static final JoinPoint f9696v = null;
    public NavigationDrawerFragment f9697b;
    public boolean f9698c;
    public boolean f9699d;
    public boolean f9700e;
    private boolean f9701g;
    private String f9702h;
    private String f9703i;
    private String f9704j;
    private SharedPreferences f9705k;
    private DataManager f9706l;
    private AnalyticsManager f9707m;
    private VariablesChangedCallback f9708n;
    private Handler f9709o;
    private CharSequence f9710p;
    private Runnable f9711q;
    private aj f9712r;

    /* renamed from: com.olacabs.customer.ui.MainActivity.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ Intent f9656a;
        final /* synthetic */ MainActivity f9657b;

        AnonymousClass12(MainActivity mainActivity, Intent intent) {
            this.f9657b = mainActivity;
            this.f9656a = intent;
        }

        public void run() {
            Fragment findFragmentById = this.f9657b.getSupportFragmentManager().findFragmentById(R.id.container);
            LatLng latLng = new LatLng(this.f9656a.getDoubleExtra("latitude", 0.0d), this.f9656a.getDoubleExtra("longitude", 0.0d));
            if (findFragmentById != null && (findFragmentById instanceof BookingFragment)) {
                ((BookingFragment) findFragmentById).m14351a(latLng, this.f9656a.getStringExtra(Constants.BUNDLE_ADDRESS));
                ((BookingFragment) findFragmentById).m14353a(this.f9656a.getStringExtra(Constants.BUNDLE_ADDRESS), (long) this.f9656a.getIntExtra(Constants.BUNDLE_FAV_ID, 0));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.14 */
    class AnonymousClass14 implements OnClickListener {
        final /* synthetic */ AlertDialog f9659a;
        final /* synthetic */ MainActivity f9660b;

        AnonymousClass14(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9660b = mainActivity;
            this.f9659a = alertDialog;
        }

        public void onClick(View view) {
            this.f9659a.dismiss();
            new CallSupportCommand(this.f9660b.f9702h).m12889a(this.f9660b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.15 */
    class AnonymousClass15 implements OnClickListener {
        final /* synthetic */ AlertDialog f9661a;
        final /* synthetic */ MainActivity f9662b;

        AnonymousClass15(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9662b = mainActivity;
            this.f9661a = alertDialog;
        }

        public void onClick(View view) {
            this.f9661a.dismiss();
            new CallSupportCommand(this.f9662b.f9703i).m12889a(this.f9662b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.16 */
    class AnonymousClass16 implements OnClickListener {
        final /* synthetic */ AlertDialog f9663a;
        final /* synthetic */ MainActivity f9664b;

        AnonymousClass16(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9664b = mainActivity;
            this.f9663a = alertDialog;
        }

        public void onClick(View view) {
            this.f9663a.dismiss();
            new CallSupportCommand(this.f9664b.f9704j).m12889a(this.f9664b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.17 */
    class AnonymousClass17 implements OnClickListener {
        final /* synthetic */ AlertDialog f9665a;
        final /* synthetic */ MainActivity f9666b;

        AnonymousClass17(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9666b = mainActivity;
            this.f9665a = alertDialog;
        }

        public void onClick(View view) {
            this.f9665a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.18 */
    class AnonymousClass18 implements OnClickListener {
        final /* synthetic */ AlertDialog f9667a;
        final /* synthetic */ MainActivity f9668b;

        AnonymousClass18(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9668b = mainActivity;
            this.f9667a = alertDialog;
        }

        public void onClick(View view) {
            this.f9667a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.19 */
    class AnonymousClass19 implements OnClickListener {
        final /* synthetic */ AlertDialog f9669a;
        final /* synthetic */ MainActivity f9670b;

        AnonymousClass19(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9670b = mainActivity;
            this.f9669a = alertDialog;
        }

        public void onClick(View view) {
            this.f9669a.dismiss();
            this.f9670b.m13587m();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.1 */
    class C08311 implements Runnable {
        final /* synthetic */ MainActivity f9671a;

        C08311(MainActivity mainActivity) {
            this.f9671a = mainActivity;
        }

        public void run() {
            this.f9671a.m13584j();
            Localytics.registerPush(this.f9671a.getString(R.string.google_project_number));
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.20 */
    class AnonymousClass20 implements OnClickListener {
        final /* synthetic */ AlertDialog f9672a;
        final /* synthetic */ MainActivity f9673b;

        AnonymousClass20(MainActivity mainActivity, AlertDialog alertDialog) {
            this.f9673b = mainActivity;
            this.f9672a = alertDialog;
        }

        public void onClick(View view) {
            this.f9672a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.2 */
    class C08322 implements OnClickListener {
        final /* synthetic */ Drawable f9674a;
        final /* synthetic */ Dialog f9675b;
        final /* synthetic */ MainActivity f9676c;

        C08322(MainActivity mainActivity, Drawable drawable, Dialog dialog) {
            this.f9676c = mainActivity;
            this.f9674a = drawable;
            this.f9675b = dialog;
        }

        public void onClick(View view) {
            if (this.f9674a.getConstantState().equals(this.f9676c.getResources().getDrawable(R.drawable.booking_coach).getConstantState())) {
                this.f9675b.dismiss();
                this.f9676c.m13565a(this.f9676c.getResources().getDrawable(R.drawable.booking_long_press));
                return;
            }
            this.f9675b.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.3 */
    class C08333 implements OnClickListener {
        final /* synthetic */ Dialog f9677a;
        final /* synthetic */ MainActivity f9678b;

        C08333(MainActivity mainActivity, Dialog dialog) {
            this.f9678b = mainActivity;
            this.f9677a = dialog;
        }

        public void onClick(View view) {
            this.f9677a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.4 */
    class C08344 implements Runnable {
        final /* synthetic */ LatLng f9679a;
        final /* synthetic */ String f9680b;
        final /* synthetic */ MainActivity f9681c;

        C08344(MainActivity mainActivity, LatLng latLng, String str) {
            this.f9681c = mainActivity;
            this.f9679a = latLng;
            this.f9680b = str;
        }

        public void run() {
            Fragment findFragmentById = this.f9681c.getSupportFragmentManager().findFragmentById(R.id.container);
            if (findFragmentById != null && (findFragmentById instanceof BookingFragment)) {
                ((BookingFragment) findFragmentById).m14351a(this.f9679a, this.f9680b);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.5 */
    class C08355 implements Runnable {
        final /* synthetic */ MainActivity f9682a;

        C08355(MainActivity mainActivity) {
            this.f9682a = mainActivity;
        }

        public void run() {
            Fragment findFragmentById = this.f9682a.getSupportFragmentManager().findFragmentById(R.id.container);
            if (findFragmentById != null && (findFragmentById instanceof BookingFragment)) {
                ((BookingFragment) findFragmentById).m14355a(true);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.6 */
    class C08366 implements Runnable {
        final /* synthetic */ String f9683a;
        final /* synthetic */ int f9684b;
        final /* synthetic */ MainActivity f9685c;

        C08366(MainActivity mainActivity, String str, int i) {
            this.f9685c = mainActivity;
            this.f9683a = str;
            this.f9684b = i;
        }

        public void run() {
            Fragment findFragmentById = this.f9685c.getSupportFragmentManager().findFragmentById(R.id.container);
            if (findFragmentById != null && (findFragmentById instanceof BookingFragment)) {
                findFragmentById = ((BookingFragment) findFragmentById).m14381s();
                if (findFragmentById == null) {
                    return;
                }
                if (findFragmentById instanceof KaaliPeeliConformationFragment) {
                    ((KaaliPeeliConformationFragment) findFragmentById).m14482a(this.f9683a, this.f9684b);
                } else if (findFragmentById instanceof AutoRickshawBookingConfirmationFragment) {
                    ((AutoRickshawBookingConfirmationFragment) findFragmentById).m14145a(this.f9683a, this.f9684b);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.7 */
    class C08377 implements Runnable {
        final /* synthetic */ String f9686a;
        final /* synthetic */ MainActivity f9687b;

        C08377(MainActivity mainActivity, String str) {
            this.f9687b = mainActivity;
            this.f9686a = str;
        }

        public void run() {
            Intent intent = new Intent(this.f9687b, TrackRideActivity.class);
            intent.putExtra(Constants.ARG_BOOKING_ID, this.f9686a);
            this.f9687b.startActivity(intent);
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.8 */
    class C08388 implements Runnable {
        final /* synthetic */ Context f9688a;
        final /* synthetic */ MainActivity f9689b;

        C08388(MainActivity mainActivity, Context context) {
            this.f9689b = mainActivity;
            this.f9688a = context;
        }

        public void run() {
            da d = this.f9689b.f9706l.m13218d();
            Map analyticsEvents = d.getAnalyticsEvents();
            this.f9689b.m13572a(analyticsEvents);
            if (analyticsEvents != null) {
                analyticsEvents.clear();
            }
            analyticsEvents = d.getFBAnalyticsEvents();
            this.f9689b.m13566a(this.f9689b.f9707m.m12867b(this.f9688a), analyticsEvents);
            if (analyticsEvents != null) {
                analyticsEvents.clear();
            }
            List<db> sherlockEvent = d.getSherlockEvent();
            if (sherlockEvent != null && sherlockEvent.size() > 0) {
                for (db dbVar : sherlockEvent) {
                    Sherlock.m13339a(dbVar.getEventName(), "Failure", dbVar.getError(), dbVar.getErrorReason(), dbVar.isPopupShown(), dbVar.getInstrumentationDetails(), dbVar.getEndTime());
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.MainActivity.9 */
    class C08399 implements LeanplumPushNotificationCustomizer {
        final /* synthetic */ MainActivity f9690a;

        C08399(MainActivity mainActivity) {
            this.f9690a = mainActivity;
        }

        public void customize(Builder builder, Bundle bundle) {
            builder.setSmallIcon(R.drawable.ola_push);
            builder.setVisibility(1);
            builder.setPriority(2);
            builder.setLargeIcon(BitmapFactoryInstrumentation.decodeResource(this.f9690a.getResources(), R.drawable.ola_icon));
        }
    }

    private static void m13592r() {
        Factory factory = new Factory("MainActivity.java", MainActivity.class);
        f9693s = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.MainActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 143);
        f9694t = factory.m15070a("method-execution", factory.m15071a("4", "onStart", "com.olacabs.customer.ui.MainActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 241);
        f9695u = factory.m15070a("method-execution", factory.m15071a("4", "onResume", "com.olacabs.customer.ui.MainActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 723);
        f9696v = factory.m15070a("method-execution", factory.m15071a("4", "onPause", "com.olacabs.customer.ui.MainActivity", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 805);
    }

    public MainActivity() {
        this.f9698c = false;
        this.f9701g = false;
        this.f9699d = false;
        this.f9700e = false;
        this.f9702h = null;
        this.f9703i = null;
        this.f9704j = null;
        this.f9708n = null;
        this.f9711q = new Runnable() {
            final /* synthetic */ MainActivity f9655a;

            {
                this.f9655a = r1;
            }

            public void run() {
                this.f9655a.m13591q();
                this.f9655a.f9709o.postDelayed(this, 120000);
            }
        };
        this.f9712r = new aj() {
            final /* synthetic */ MainActivity f9658a;

            {
                this.f9658a = r1;
            }

            public void onFailure(Throwable th) {
                OLog.m13310a("Fetching City Wise Data failed", th);
            }

            public void onSuccess(Object obj) {
                OLog.m13313b("Fetching City Wise Data Successful", new Object[0]);
                ab abVar = (ab) obj;
                if (abVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f9658a.f9706l.m13218d().setCityBasedCarModels(abVar);
                    OLog.m13311a("got surcharge info preopley", new Object[0]);
                }
            }
        };
    }

    static {
        m13592r();
        f9691a = MainActivity.class.getSimpleName();
        abCampaignName = "A";
        f9692f = MainActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9693s, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        OlaApp olaApp = (OlaApp) getApplication();
        this.f9706l = olaApp.m12878a();
        this.f9707m = olaApp.m12879b();
        this.f9707m.m12868b();
        m13610g();
        setContentView(R.layout.activity_main);
        m13590p();
        this.f9697b = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        this.f9710p = getTitle();
        this.f9697b.m13651a((int) R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        if (!(getIntent() == null || getIntent().getExtras() == null || !getIntent().getExtras().getBoolean("trigger_apsalar", false))) {
            Apsalar.startSession(this, getString(R.string.apsalar_app_id), getString(R.string.apsalar_secrect));
        }
        this.f9705k = PreferenceManager.getDefaultSharedPreferences(this);
        if (!(this.f9705k.contains(String.valueOf(this.f9706l.m13218d().getFirstTimeOfferId())) || this.f9706l.m13218d().getFirstTimeOfferHeader() == null)) {
            m13585k();
            this.f9705k.edit().putBoolean(String.valueOf(this.f9706l.m13218d().getFirstTimeOfferId()), false).apply();
        }
        new Handler(BackgroundLooper.m14896a()).postDelayed(new C08311(this), 5000);
    }

    private void m13584j() {
        boolean z = true;
        boolean z2 = false;
        if (!isFinishing()) {
            Editor edit;
            boolean a = RootUtil.m14903a();
            boolean z3 = this.f9705k.getBoolean(Constants.PREF_IS_ROOTED, false);
            String property = System.getProperty("http.proxyHost", Trace.NULL);
            String string = this.f9705k.getString(Constants.PREF_SYSTEM_PROXY_HOST, Trace.NULL);
            String str = property + ":" + System.getProperty("http.proxyPort", Trace.NULL);
            if (!property.equals(string)) {
                edit = this.f9705k.edit();
                edit.putString(Constants.PREF_SYSTEM_PROXY_HOST, property);
                edit.apply();
                z2 = true;
            }
            if (z3 != a) {
                edit = this.f9705k.edit();
                edit.putBoolean(Constants.PREF_IS_ROOTED, a);
                edit.apply();
            } else {
                z = z2;
            }
            if (z) {
                DataManager dataManager = this.f9706l;
                if (!Utils.m14924g(property)) {
                    str = "None";
                }
                dataManager.m13190a(null, str, a);
            }
        }
    }

    private void m13585k() {
        startActivityForResult(new Intent(this, FirstTimeOfferActivity.class), 3);
    }

    public void m13594a(double d, double d2) {
        Localytics.tagEvent("Search initiated");
        this.f9697b.m13652a(false);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slideup, R.anim.slidedown, R.anim.slidedown, R.anim.slidedown).add((int) R.id.container, ai.m14062a(d, d2, 0)).addToBackStack(null).commit();
        supportFragmentManager.executePendingTransactions();
    }

    protected void onStart() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9694t, (Object) this, (Object) this));
        try {
            m13608e().m13649a();
        } catch (NullPointerException e) {
        }
        super.onStart();
    }

    public void m13604b(double d, double d2) {
        this.f9697b.m13652a(false);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slideup, R.anim.slidedown, R.anim.slidedown, R.anim.slidedown).add((int) R.id.container, KaliPeeliDropLocationFragment.m14483a(Double.valueOf(d), Double.valueOf(d2))).addToBackStack(null).commit();
    }

    public void m13600a(String str, LatLng latLng, String str2, String str3, String str4, String str5, String str6) {
        if (!this.f9699d) {
            this.f9699d = true;
            this.f9697b.m13652a(false);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slideup, R.anim.slidedown, R.anim.slidedown, R.anim.slidedown).add((int) R.id.container, LocalTaxiBookingRequestFragment.m14496a(str, latLng, str2, str3, str4, str5, str6)).addToBackStack(null).commit();
        }
    }

    public void m13601a(String str, LatLng latLng, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        if (!this.f9699d) {
            this.f9699d = true;
            this.f9697b.m13652a(false);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slideup, R.anim.slidedown, R.anim.slidedown, R.anim.slidedown).add((int) R.id.container, LocalTaxiBookingRequestFragment.m14497a(str, latLng, str2, str3, str4, str5, this.f9698c, str6, z, str7)).addToBackStack(null).commit();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (findFragmentById != null) {
            findFragmentById.onActivityResult(i, i2, intent);
        }
        if (i == 2 && i2 == -1) {
            this.f9697b.m13652a(true);
            getSupportFragmentManager().popBackStack();
            new Handler().postDelayed(new AnonymousClass12(this, intent), 300);
        }
        if (i != 3) {
            return;
        }
        if (i2 == 1) {
            this.f9706l.m13218d().setOfferFlow(true);
            this.f9697b.m13650a(3);
            return;
        }
        this.f9697b.m13650a(1);
    }

    public void m13598a(String str, double d, double d2) {
        Intent intent = new Intent(this, FavouritesActivity.class);
        intent.putExtra(Constants.BUNDLE_ADDRESS, str);
        intent.putExtra("lattitude", d);
        intent.putExtra("longitude", d2);
        startActivityForResult(intent, 2);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.f9701g = extras.getBoolean(Constants.SHOW_EC);
        }
        if (this.f9701g) {
            this.f9701g = false;
            this.f9697b.m13650a(6);
        }
        setIntent(intent);
    }

    public void m13595a(int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                m13575b("My Profile");
                supportFragmentManager.beginTransaction().replace(R.id.container, ProfileDetailsFragment.m14873a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                if (defaultSharedPreferences.getBoolean(Constants.PREF_COACHMARK_BOOKING_SCREEN, false)) {
                    m13565a(getResources().getDrawable(R.drawable.booking_coach));
                    defaultSharedPreferences.edit().putBoolean(Constants.PREF_COACHMARK_BOOKING_SCREEN, false).apply();
                }
                m13575b("Book My Ride");
                supportFragmentManager.beginTransaction().replace(R.id.container, BookingFragment.m14305b()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                m13575b("My Rides");
                supportFragmentManager.beginTransaction().replace(R.id.container, ab.m13996a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                Sherlock.m13334a("Ins ola money shown");
                m13575b("Ola Money");
                supportFragmentManager.beginTransaction().replace(R.id.container, OlaMoneyFragment.m14609a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                m13575b("Invite & Earn");
                supportFragmentManager.beginTransaction().replace(R.id.container, InvitesFragment.m14452a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                m13575b("Rate Card");
                supportFragmentManager.beginTransaction().replace(R.id.container, RateCardFragment.m14888a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                m13575b("Emergency Contact");
                supportFragmentManager.beginTransaction().replace(R.id.container, OlaSafetyFragment.m14660a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                m13575b("Report Issue");
                if (Utils.m14909a(getApplicationContext())) {
                    Localytics.tagEvent("Rides Issue Reported");
                    Account[] accountsByType = AccountManager.get(getApplicationContext()).getAccountsByType("com.google");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\n\n\n\n\n--------------------\nOla Android ").append(AppInfo.sVersionName).append(" on ").append(Build.MODEL).append(" running Android ").append(VERSION.RELEASE).append(" user ");
                    if (accountsByType.length > 0) {
                        stringBuilder.append(accountsByType[0].name);
                    }
                    String[] strArr = new String[]{getString(R.string.support_email_addr_main)};
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setData(Uri.parse("mailto:"));
                    intent.setType("message/rfc822");
                    intent.putExtra("android.intent.extra.EMAIL", strArr);
                    intent.putExtra("android.intent.extra.SUBJECT", "Feedback from " + PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.PREF_NAME, Trace.NULL));
                    OLog.m13313b(PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.UTM_SOURCE, "NOPE"), new Object[0]);
                    intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                    startActivity(Intent.createChooser(intent, "Send mail ..."));
                    if (this.f9697b != null) {
                        this.f9697b.m13657e();
                        return;
                    }
                    return;
                }
                m13571a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                if (this.f9706l.m13218d().getFoodDeliveryConfigurations() != null || Utils.m14924g(this.f9706l.m13218d().getTfsCallCenterNumber())) {
                    m13586l();
                } else {
                    new CallSupportCommand(this.f9706l.m13218d() != null ? this.f9706l.m13218d().getCallCenterNumber() : Ola.f11486c).m12889a(this);
                }
                Localytics.tagEvent("Called Ola");
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            case HTTP.HT /*9*/:
                m13575b("About Us");
                supportFragmentManager.beginTransaction().replace(R.id.container, AboutUsFragment.m13981a()).commit();
                if (this.f9697b != null) {
                    this.f9697b.m13657e();
                }
            default:
                supportFragmentManager.beginTransaction().replace(R.id.container, BookingFragment.m14305b()).commit();
        }
    }

    private void m13586l() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_call_support, null, false);
        AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        if (!(this.f9706l == null || this.f9706l.m13218d() == null)) {
            this.f9702h = this.f9706l.m13218d().getCallCenterNumber();
            this.f9703i = this.f9706l.m13218d().getFoodCallCenterNumber();
            this.f9704j = this.f9706l.m13218d().getTfsCallCenterNumber();
        }
        if (TextUtils.isEmpty(this.f9702h)) {
            this.f9702h = Ola.f11486c;
        }
        if (TextUtils.isEmpty(this.f9703i)) {
            this.f9706l.m13218d();
            this.f9703i = da.OFD_CC_NUMBER;
        }
        if (Utils.m14924g(this.f9702h)) {
            ((TextView) inflate.findViewById(R.id.cabs_number)).setText(this.f9702h);
        } else {
            inflate.findViewById(R.id.cabs_number).setVisibility(8);
            inflate.findViewById(R.id.text_cabs).setVisibility(8);
            inflate.findViewById(R.id.seperator_view).setVisibility(8);
        }
        if (Utils.m14924g(this.f9703i)) {
            ((TextView) inflate.findViewById(R.id.food_deliveries_number)).setText(this.f9703i);
        } else {
            inflate.findViewById(R.id.food_deliveries_number).setVisibility(8);
            inflate.findViewById(R.id.text_food_deliveries).setVisibility(8);
            inflate.findViewById(R.id.seperator_view).setVisibility(8);
        }
        if (Utils.m14924g(this.f9704j)) {
            ((TextView) inflate.findViewById(R.id.tfs_number)).setText(this.f9704j);
        } else {
            inflate.findViewById(R.id.tfs_number).setVisibility(8);
            inflate.findViewById(R.id.text_tfs).setVisibility(8);
            inflate.findViewById(R.id.seperator_views).setVisibility(8);
        }
        inflate.findViewById(R.id.cabs_number).setOnClickListener(new AnonymousClass14(this, create));
        inflate.findViewById(R.id.food_deliveries_number).setOnClickListener(new AnonymousClass15(this, create));
        inflate.findViewById(R.id.tfs_number).setOnClickListener(new AnonymousClass16(this, create));
        inflate.findViewById(R.id.button_cancel).setOnClickListener(new AnonymousClass17(this, create));
        create.show();
    }

    private void m13575b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Menu item", str);
        if (!(this.f9706l.m13209c() == null || this.f9706l.m13209c().getCity() == null)) {
            hashMap.put("City", this.f9706l.m13209c().getCity());
        }
        Localytics.tagEvent("Menu item clicked", hashMap);
    }

    private void m13571a(String str, String str2) {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(Constants.CONNECTION_TIME_OUT_HEADER);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(Constants.NO_NETWORK_TEXT);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new AnonymousClass18(this, create));
        create.show();
    }

    public void m13603b() {
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText("Sign out");
        ((TextView) inflate.findViewById(R.id.item_message)).setText("Are you sure?");
        inflate.findViewById(R.id.button_yes).setOnClickListener(new AnonymousClass19(this, create));
        inflate.findViewById(R.id.button_no).setOnClickListener(new AnonymousClass20(this, create));
        create.show();
        m13608e().m13654b(-1);
    }

    private void m13565a(Drawable drawable) {
        Dialog dialog = new Dialog(this, 16973840);
        dialog.setContentView(R.layout.overlay_view);
        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.overlayLayout);
        ((ImageView) dialog.findViewById(R.id.coach_mark_image)).setBackgroundDrawable(drawable);
        relativeLayout.setOnClickListener(new C08322(this, drawable, dialog));
        dialog.show();
    }

    private void m13574b(Drawable drawable) {
        Dialog dialog = new Dialog(this, 16973840);
        dialog.setContentView(R.layout.overlay_view);
        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.overlayLayout);
        ((ImageView) dialog.findViewById(R.id.coach_mark_image)).setBackgroundDrawable(drawable);
        relativeLayout.setOnClickListener(new C08333(this, dialog));
        dialog.show();
    }

    public void m13606c() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(0);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(this.f9710p);
        }
    }

    public void m13607d() {
        this.f9697b.m13656d();
    }

    public NavigationDrawerFragment m13608e() {
        return this.f9697b;
    }

    private void m13587m() {
        Localytics.tagEvent("Signed out");
        new ForceLogoutCommand().m13270a((Context) this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f9697b.m13655c()) {
            return super.onCreateOptionsMenu(menu);
        }
        m13606c();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return menuItem.getItemId() == R.id.action_settings || super.onOptionsItemSelected(menuItem);
    }

    protected void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9695u, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12825a(a);
            super.onResume();
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (defaultSharedPreferences.getBoolean(Constants.PREF_COACHMARK_TFS, false)) {
                m13574b(getResources().getDrawable(R.drawable.coach_tfs));
                defaultSharedPreferences.edit().putBoolean(Constants.PREF_COACHMARK_TFS, false).apply();
            }
            if (TrackRideActivity.f10169b) {
                TrackRideActivity.f10169b = false;
                this.f9697b.m13650a(3);
            }
            m13588n();
            da d = this.f9706l.m13218d();
            if (!d.isConfigurationLoaded()) {
                DataUpdaterManager.m13261a(getApplicationContext()).m13264a("app_config", "profile_data");
                d.setConfigurationLoaded(true);
            }
            new Handler(BackgroundLooper.m14896a()).postDelayed(m13589o(), 3000);
        } finally {
            ActivityTraceAspect.m12823a().m12828d(a);
        }
    }

    private void m13588n() {
        String toLowerCase;
        if (this.f9706l.m13259o() != null && this.f9706l.m13259o().isConfPanelPending()) {
            toLowerCase = this.f9706l.m13259o().getLandingPage().toLowerCase();
            this.f9706l.m13259o().setIsConfPanelPending(false);
            m13577c(toLowerCase);
        }
        toLowerCase = getIntent().getStringExtra(Constants.PUSH_LANDING);
        OLog.m13313b("Landing page " + toLowerCase, new Object[0]);
        if (Utils.m14924g(toLowerCase)) {
            m13577c(toLowerCase);
        }
    }

    private void m13577c(String str) {
        int i = -1;
        switch (str.hashCode()) {
            case 3145:
                if (str.equals(Constants.LANDING_PAGE_BOOKING_SCREEN)) {
                    i = 0;
                    break;
                }
                break;
            case 3168:
                if (str.equals(Constants.LANDING_PAGE_CUSTOMER_CARE)) {
                    i = 7;
                    break;
                }
                break;
            case 3230:
                if (str.equals(Constants.LANDING_PAGE_EMERGENCY_CONTACT)) {
                    i = 5;
                    break;
                }
                break;
            case 3356:
                if (str.equals(Constants.LANDING_PAGE_INVITE_EARN)) {
                    i = 3;
                    break;
                }
                break;
            case 3493:
                if (str.equals(Constants.LANDING_PAGE_MY_RIDES)) {
                    i = 1;
                    break;
                }
                break;
            case 3633:
                if (str.equals(Constants.LANDING_PAGE_RATE_CARD)) {
                    i = 4;
                    break;
                }
                break;
            case 3639:
                if (str.equals(Constants.LANDING_PAGE_REPORT_ISSUE)) {
                    i = 6;
                    break;
                }
                break;
            case 3710:
                if (str.equals(Constants.LANDING_PAGE_TRACK_RIDE)) {
                    i = 8;
                    break;
                }
                break;
            case 110154:
                if (str.equals(Constants.LANDING_PAGE_OLA_MONEY)) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f9697b.m13650a(1);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f9697b.m13650a(2);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f9697b.m13650a(3);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f9697b.m13650a(4);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                this.f9697b.m13650a(5);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.f9697b.m13650a(6);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                this.f9697b.m13650a(7);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                this.f9697b.m13650a(8);
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                String stringExtra = getIntent().getStringExtra(Constants.PUSH_BOOKING_ID);
                if (Utils.m14924g(stringExtra)) {
                    Intent intent = new Intent(this, TrackRideActivity.class);
                    intent.putExtra(Constants.ARG_BOOKING_ID, stringExtra);
                    startActivity(intent);
                }
            default:
        }
    }

    protected void onPause() {
        ActivityTraceAspect.m12823a().m12825a(Factory.m15067a(f9696v, (Object) this, (Object) this));
        Apsalar.unregisterApsalarReceiver();
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        this.f9706l.m13169a(f9691a);
        getIntent().removeExtra(Constants.PUSH_MESSAGE);
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (findFragmentById instanceof LocalTaxiBookingRequestFragment) {
            if (((LocalTaxiBookingRequestFragment) findFragmentById).m14539d()) {
                super.onBackPressed();
            }
        } else if (!(findFragmentById instanceof BackKeyHandler)) {
            this.f9697b.m13650a(1);
        } else if (!((BackKeyHandler) findFragmentById).m14058a()) {
            super.onBackPressed();
        }
    }

    public void m13596a(LatLng latLng, String str) {
        this.f9697b.m13652a(true);
        getSupportFragmentManager().popBackStack();
        new Handler().postDelayed(new C08344(this, latLng, str), 300);
    }

    public void m13593a() {
        this.f9697b.m13652a(true);
        new Handler().postDelayed(new C08355(this), 300);
    }

    public void m13599a(String str, int i) {
        new Handler().postDelayed(new C08366(this, str, i), 300);
    }

    public void m13609f() {
        this.f9697b.m13658f();
    }

    public void m13597a(String str) {
        if (getSupportFragmentManager().findFragmentById(R.id.container) instanceof LocalTaxiBookingRequestFragment) {
            try {
                this.f9700e = true;
                super.onBackPressed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        new Handler().postDelayed(new C08377(this, str), 500);
    }

    public void m13602a(boolean z) {
        this.f9697b.m13652a(z);
    }

    public void m13605b(int i) {
        this.f9697b.m13650a(i);
    }

    private Runnable m13589o() {
        return new C08388(this, getApplicationContext());
    }

    private void m13566a(AnalyticsHelper analyticsHelper, Map<String, Map<String, String>> map) {
        if (map != null && !map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    analyticsHelper.m12858a((String) entry.getKey());
                } else {
                    analyticsHelper.m12859a((String) entry.getKey(), (Map) entry.getValue());
                }
            }
        }
    }

    private void m13572a(Map<String, Map<String, String>> map) {
        if (map != null && !map.isEmpty()) {
            for (Entry entry : map.entrySet()) {
                if (((String) entry.getKey()).equals(Constants.SCREEN_TAG)) {
                    for (String tagScreen : ((Map) entry.getValue()).keySet()) {
                        Localytics.tagScreen(tagScreen);
                    }
                } else if (entry.getValue() == null) {
                    Localytics.tagEvent((String) entry.getKey());
                } else {
                    Localytics.tagEvent((String) entry.getKey(), (Map) entry.getValue());
                }
            }
        }
    }

    public void m13610g() {
        if (AppInfo.sRunningMode == ApplicationMode.PROD) {
            Leanplum.setAppIdForProductionMode(getString(R.string.leanplum_api_key), getString(R.string.leanplum_access_key));
        } else {
            Leanplum.setAppIdForDevelopmentMode(getString(R.string.leanplum_api_key), getString(R.string.leanplum_access_key));
        }
        LeanplumPushService.setGcmSenderId(getString(R.string.gcm_sender_id));
        LeanplumPushService.setCustomizer(new C08399(this));
        Leanplum.start(this);
    }

    private void m13590p() {
        if (this.f9708n != null) {
            Leanplum.removeVariablesChangedAndNoDownloadsPendingHandler(this.f9708n);
        }
        this.f9708n = new VariablesChangedCallback() {
            final /* synthetic */ MainActivity f9654a;

            {
                this.f9654a = r1;
            }

            public void variablesChanged() {
                this.f9654a.f9706l.m13218d().setCampaignName(MainActivity.abCampaignName);
                if (this.f9654a.f9706l.m13218d() == null) {
                    return;
                }
                if (this.f9654a.f9706l.m13218d().isB()) {
                    OLog.m13311a("startSurchargeUpdate", new Object[0]);
                    this.f9654a.m13611h();
                    return;
                }
                this.f9654a.m13612i();
            }
        };
        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(this.f9708n);
    }

    void m13611h() {
        m13612i();
        if (this.f9709o == null) {
            this.f9709o = new Handler();
        }
        this.f9709o.post(this.f9711q);
    }

    void m13612i() {
        OLog.m13311a("stopSurchargeUpdate", new Object[0]);
        this.f9706l.m13169a(f9692f);
        if (this.f9709o != null) {
            this.f9709o.removeCallbacks(this.f9711q);
        }
    }

    private void m13591q() {
        LatLng latLng;
        OLog.m13311a("fetchCityBasedCabModel in MainActivity", new Object[0]);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (findFragmentById == null || !(findFragmentById instanceof BookingFragment)) {
            latLng = null;
        } else {
            latLng = ((BookingFragment) findFragmentById).m14367e();
        }
        if (latLng != null) {
            this.f9706l.m13174a(new WeakReference(this.f9712r), Double.valueOf(latLng.f7554a), Double.valueOf(latLng.f7555b), "cityBasedRequestFareTag");
            OLog.m13311a("lat and long %f %f", Double.valueOf(latLng.f7554a), Double.valueOf(latLng.f7555b));
        }
    }
}
