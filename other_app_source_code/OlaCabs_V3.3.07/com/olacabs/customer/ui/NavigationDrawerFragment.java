package com.olacabs.customer.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataUpdaterManager;
import com.olacabs.customer.app.ObservableData;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.util.Observable;
import java.util.Observer;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class NavigationDrawerFragment extends Fragment implements TraceFieldInterface {
    public static final String f9764a;
    private static final JoinPoint f9765o = null;
    private static final JoinPoint f9766p = null;
    private static final JoinPoint f9767q = null;
    private C0840b f9768b;
    private ActionBarDrawerToggle f9769c;
    private DrawerLayout f9770d;
    private ListView f9771e;
    private View f9772f;
    private int f9773g;
    private boolean f9774h;
    private boolean f9775i;
    private TextView f9776j;
    private TextView f9777k;
    private ImageView f9778l;
    private MainActivity f9779m;
    private C0852a f9780n;

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.b */
    public interface C0840b {
        void m13563a(int i);
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.1 */
    class C08471 implements OnItemClickListener {
        final /* synthetic */ NavigationDrawerFragment f9753a;

        C08471(NavigationDrawerFragment navigationDrawerFragment) {
            this.f9753a = navigationDrawerFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != this.f9753a.f9773g) {
                this.f9753a.m13650a(i);
            } else if (this.f9753a.f9770d != null) {
                this.f9753a.f9770d.closeDrawer(this.f9753a.f9772f);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.2 */
    class C08482 extends ActionBarDrawerToggle {
        final /* synthetic */ NavigationDrawerFragment f9754a;

        C08482(NavigationDrawerFragment navigationDrawerFragment, Activity activity, DrawerLayout drawerLayout, int i, int i2, int i3) {
            this.f9754a = navigationDrawerFragment;
            super(activity, drawerLayout, i, i2, i3);
        }

        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            if (this.f9754a.isAdded()) {
                this.f9754a.getActivity().invalidateOptionsMenu();
            }
        }

        public void onDrawerOpened(View view) {
            super.onDrawerOpened(view);
            if (this.f9754a.isAdded()) {
                if (!this.f9754a.f9775i) {
                    this.f9754a.f9775i = true;
                    PreferenceManager.getDefaultSharedPreferences(this.f9754a.getActivity()).edit().putBoolean("navigation_drawer_learned", true).apply();
                }
                this.f9754a.getActivity().invalidateOptionsMenu();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.3 */
    class C08493 implements Runnable {
        final /* synthetic */ NavigationDrawerFragment f9755a;

        C08493(NavigationDrawerFragment navigationDrawerFragment) {
            this.f9755a = navigationDrawerFragment;
        }

        public void run() {
            this.f9755a.f9769c.syncState();
        }
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.4 */
    class C08504 implements Runnable {
        final /* synthetic */ NavigationDrawerFragment f9756a;

        C08504(NavigationDrawerFragment navigationDrawerFragment) {
            this.f9756a = navigationDrawerFragment;
        }

        public void run() {
            this.f9756a.f9770d.closeDrawer(this.f9756a.f9772f);
        }
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.5 */
    class C08515 implements Observer {
        final /* synthetic */ ObservableData f9757a;
        final /* synthetic */ NavigationDrawerFragment f9758b;

        C08515(NavigationDrawerFragment navigationDrawerFragment, ObservableData observableData) {
            this.f9758b = navigationDrawerFragment;
            this.f9757a = observableData;
        }

        public void update(Observable observable, Object obj) {
            if (this.f9757a.m13320a("profile_data") != null && this.f9758b.isAdded()) {
                this.f9758b.m13658f();
                this.f9757a.m13324b("profile_data");
                this.f9757a.deleteObserver(this);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.NavigationDrawerFragment.a */
    private class C0852a extends BaseAdapter {
        SharedPreferences f9759a;
        final /* synthetic */ NavigationDrawerFragment f9760b;
        private LayoutInflater f9761c;
        private String[] f9762d;
        private int[] f9763e;

        public C0852a(NavigationDrawerFragment navigationDrawerFragment, Context context) {
            this.f9760b = navigationDrawerFragment;
            this.f9759a = PreferenceManager.getDefaultSharedPreferences(this.f9760b.f9779m);
            this.f9763e = new int[]{R.drawable.drawable_nav_book_my_ride, R.drawable.drawable_nav_my_rides, R.drawable.drawable_nav_ola_money, R.drawable.drawable_nav_invite, R.drawable.drawable_nav_rate_card, R.drawable.drawable_nav_emergency_contact, R.drawable.drawable_nav_report_issue, R.drawable.drawable_nav_call_cc, R.drawable.drawable_nav_about};
            this.f9761c = (LayoutInflater) context.getSystemService("layout_inflater");
            if (this.f9759a.getBoolean(Constants.PREF_IS_SCHEME_ON, Ola.f11485b ? Ola.f11485b : false)) {
                this.f9762d = context.getResources().getStringArray(R.array.navigation_drawer_items);
            } else {
                this.f9762d = context.getResources().getStringArray(R.array.navigation_drawer_items_no_earn);
            }
        }

        public boolean isEmpty() {
            return false;
        }

        public int getCount() {
            return this.f9762d.length;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f9761c.inflate(R.layout.item_navigation_drawer, viewGroup, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            TextView textView = (TextView) view.findViewById(R.id.item_value);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.item_alert);
            ((TextView) view.findViewById(R.id.item_name)).setText(this.f9762d[i]);
            imageView.setImageResource(this.f9763e[i]);
            int i2;
            if (getItemViewType(i) == 1) {
                int i3 = this.f9759a.getInt(Constants.PREF_OLA_MONEY_BALANCE, ((OlaApp) this.f9760b.getActivity().getApplication()).m12878a().m13209c().getOlaBalance());
                i2 = this.f9759a.getInt(Constants.PREF_BALANCE_NOTIFY_LIMIT, -1);
                if (i2 == -1 || i2 == 0) {
                    i2 = HttpStatus.SC_OK;
                }
                textView.setText(this.f9760b.getResources().getString(R.string.rs_symbol) + Trace.NULL + i3);
                if (i3 > i2) {
                    textView.setEnabled(true);
                } else {
                    textView.setEnabled(false);
                }
                textView.setVisibility(0);
            } else if (getItemViewType(i) != 2) {
                textView.setVisibility(8);
            } else if (this.f9759a.getBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, false)) {
                if (!Trace.NULL.equalsIgnoreCase(this.f9759a.getString(Constants.PREF_EMERGENCY_EMAIL, Trace.NULL))) {
                    if (this.f9759a.getBoolean(Constants.PREF_EMERGENCY_IS_EMAIL_VERIFIED, false)) {
                        i2 = 8;
                    } else {
                        i2 = 0;
                    }
                    imageView2.setVisibility(i2);
                }
                imageView2.setVisibility(8);
            } else {
                imageView2.setVisibility(0);
            }
            return view;
        }

        public int getItemViewType(int i) {
            if (i == 2) {
                return 1;
            }
            if (i != 5) {
                return 0;
            }
            return 2;
        }
    }

    private static void m13648i() {
        Factory factory = new Factory("NavigationDrawerFragment.java", NavigationDrawerFragment.class);
        f9765o = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.NavigationDrawerFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 99);
        f9766p = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.NavigationDrawerFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), (int) TransportMediator.KEYCODE_MEDIA_PLAY);
        f9767q = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.NavigationDrawerFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 172);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    static {
        m13648i();
        f9764a = NavigationDrawerFragment.class.getSimpleName();
    }

    public NavigationDrawerFragment() {
        this.f9773g = 1;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("NavigationDrawerFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "NavigationDrawerFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "NavigationDrawerFragment#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f9765o, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f9775i = PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean("navigation_drawer_learned", false);
        this.f9779m = (MainActivity) getActivity();
        if (bundle != null) {
            this.f9773g = bundle.getInt("selected_navigation_drawer_position");
            this.f9774h = true;
        }
        m13650a(this.f9773g);
        TraceMachine.exitMethod();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "NavigationDrawerFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "NavigationDrawerFragment#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f9766p, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_navigation_drawer, viewGroup, false);
        View inflate2 = layoutInflater.inflate(R.layout.view_header_navigation_drawer, null, false);
        this.f9771e = (ListView) inflate.findViewById(R.id.listView);
        this.f9771e.addHeaderView(inflate2);
        this.f9771e.setOnItemClickListener(new C08471(this));
        this.f9780n = new C0852a(this, getActivity());
        this.f9771e.setAdapter(this.f9780n);
        this.f9771e.setItemChecked(this.f9773g, true);
        this.f9776j = (TextView) inflate2.findViewById(R.id.item_name);
        this.f9778l = (ImageView) inflate2.findViewById(R.id.alert_item);
        this.f9777k = (TextView) inflate2.findViewById(R.id.item_mobile_number);
        m13658f();
        TraceMachine.exitMethod();
        return inflate;
    }

    public void m13649a() {
        if (this.f9780n != null) {
            this.f9780n.notifyDataSetChanged();
        }
    }

    public void m13653b() {
        this.f9776j.setText("My Profile");
        this.f9777k.setText("Incomplete");
        this.f9778l.setVisibility(0);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f9767q, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            m13647h();
        } finally {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public boolean m13655c() {
        return this.f9770d != null && this.f9770d.isDrawerOpen(this.f9772f);
    }

    public void m13651a(int i, DrawerLayout drawerLayout) {
        this.f9772f = getActivity().findViewById(i);
        this.f9770d = drawerLayout;
        this.f9770d.setDrawerShadow((int) R.drawable.drawer_shadow, (int) GravityCompat.START);
        this.f9769c = new C08482(this, getActivity(), this.f9770d, R.drawable.ic_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.f9770d.post(new C08493(this));
        this.f9770d.setDrawerListener(this.f9769c);
    }

    public void m13650a(int i) {
        if (i == 8 || i == 7) {
            this.f9771e.setItemChecked(this.f9773g, true);
        } else {
            this.f9773g = i;
            if (this.f9771e != null) {
                this.f9771e.setItemChecked(i, true);
            }
        }
        if (this.f9768b != null) {
            this.f9768b.m13563a(i);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f9768b = (C0840b) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.f9768b = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("selected_navigation_drawer_position", this.f9773g);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f9769c.onConfigurationChanged(configuration);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.f9770d != null && m13655c()) {
            menuInflater.inflate(R.menu.global, menu);
            m13646g();
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.f9769c.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void m13646g() {
    }

    public void m13656d() {
        if (this.f9770d.isDrawerOpen(this.f9772f)) {
            this.f9770d.closeDrawer(this.f9772f);
        } else {
            this.f9770d.openDrawer(this.f9772f);
        }
    }

    public void m13657e() {
        if (this.f9770d.isDrawerOpen(this.f9772f)) {
            new Handler().postDelayed(new C08504(this), 20);
        }
    }

    public void m13652a(boolean z) {
        if (z) {
            this.f9770d.setDrawerLockMode(0);
        } else {
            this.f9770d.setDrawerLockMode(1);
        }
    }

    public void m13658f() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f9779m);
        String str = Trace.NULL;
        if (defaultSharedPreferences.contains(Constants.PREF_NAME)) {
            str = defaultSharedPreferences.getString(Constants.PREF_NAME, Trace.NULL);
        }
        String str2 = Trace.NULL;
        if (defaultSharedPreferences.contains(Constants.PREF_MOBILE)) {
            str2 = defaultSharedPreferences.getString(Constants.PREF_MOBILE, Trace.NULL);
        }
        this.f9778l.setVisibility(8);
        this.f9777k.setText(str2 != null ? str2 : Trace.NULL);
        this.f9776j.setText(str != null ? str : Trace.NULL);
        if (m13640a(str, str2, Boolean.valueOf(defaultSharedPreferences.getBoolean(Constants.PREF_IS_VERIFIED, false)))) {
            m13653b();
        }
    }

    private boolean m13640a(String str, String str2, Boolean bool) {
        return !bool.booleanValue() || str2 == null || Trace.NULL.equalsIgnoreCase(str2) || str == null || Trace.NULL.equalsIgnoreCase(str);
    }

    public void m13654b(int i) {
        if (i >= 0 && i != 7) {
            this.f9773g = i;
            this.f9771e.setSelection(i);
        }
    }

    private void m13647h() {
        ObservableData a = DataUpdaterManager.m13261a(getActivity()).m13263a();
        if (a.m13320a("profile_data") != null) {
            m13658f();
            a.m13324b("profile_data");
            return;
        }
        a.addObserver(new C08515(this, a));
    }
}
