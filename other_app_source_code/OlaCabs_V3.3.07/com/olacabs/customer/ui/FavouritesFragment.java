package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.aw;
import com.olacabs.customer.p076d.ds;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.l */
public class FavouritesFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f10835r = null;
    private static final JoinPoint f10836s = null;
    private static final JoinPoint f10837t = null;
    public FavouritesFragment f10838a;
    private String f10839b;
    private ListView f10840c;
    private LinearLayout f10841d;
    private ImageView f10842e;
    private TextView f10843f;
    private boolean f10844g;
    private String f10845h;
    private double f10846i;
    private double f10847j;
    private TextView f10848k;
    private LinearLayout f10849l;
    private RelativeLayout f10850m;
    private FavouritesActivity f10851n;
    private ProgressDialog f10852o;
    private DataManager f10853p;
    private aj f10854q;

    /* renamed from: com.olacabs.customer.ui.l.1 */
    class FavouritesFragment implements aj {
        final /* synthetic */ FavouritesFragment f10831a;

        FavouritesFragment(FavouritesFragment favouritesFragment) {
            this.f10831a = favouritesFragment;
        }

        public void onFailure(Throwable th) {
            Sherlock.m13335a("Ins favourite loaded", (VolleyError) th);
            OLog.m13310a("Failed to obtain favourites", th);
            this.f10831a.f10852o.dismiss();
        }

        public void onSuccess(Object obj) {
            ds dsVar = (ds) obj;
            if (dsVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                Ola.f11489f = dsVar.getFavourites();
                if (this.f10831a.isAdded()) {
                    this.f10831a.m14436c();
                } else {
                    return;
                }
            } else if (dsVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13318e("Get favourites failed ", new Object[0]);
            }
            this.f10831a.f10852o.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.l.2 */
    class FavouritesFragment implements OnClickListener {
        final /* synthetic */ Dialog f10832a;
        final /* synthetic */ FavouritesFragment f10833b;

        FavouritesFragment(FavouritesFragment favouritesFragment, Dialog dialog) {
            this.f10833b = favouritesFragment;
            this.f10832a = dialog;
        }

        public void onClick(View view) {
            this.f10832a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.l.3 */
    class FavouritesFragment implements OnItemClickListener {
        final /* synthetic */ FavouritesFragment f10834a;

        FavouritesFragment(FavouritesFragment favouritesFragment) {
            this.f10834a = favouritesFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            aw awVar = (aw) Ola.f11489f.get(i);
            if (this.f10834a.f10844g) {
                this.f10834a.f10851n.m13505a((long) awVar.getId(), URLDecoder.decode(awVar.getAddress()), awVar.getName(), awVar.getType(), awVar.getLat(), awVar.getLng());
                return;
            }
            Ola.f11491h = true;
            Intent intent = new Intent();
            intent.putExtra(Constants.BUNDLE_FAV_ID, awVar.getId());
            intent.putExtra("latitude", awVar.getLat());
            intent.putExtra("longitude", awVar.getLng());
            intent.putExtra(Constants.BUNDLE_ADDRESS, URLDecoder.decode(awVar.getAddress()));
            this.f10834a.getActivity().setResult(-1, intent);
            this.f10834a.getActivity().finish();
        }
    }

    static {
        FavouritesFragment.m14443h();
    }

    private static void m14443h() {
        Factory factory = new Factory("FavouritesFragment.java", FavouritesFragment.class);
        f10835r = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.FavouritesFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 111);
        f10836s = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.FavouritesFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 153);
        f10837t = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.FavouritesFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 177);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public static FavouritesFragment m14430a(String str, double d, double d2) {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_ADDRESS, str);
        bundle.putDouble(Constants.LAT, d);
        bundle.putDouble(Constants.LNG, d2);
        favouritesFragment.setArguments(bundle);
        return favouritesFragment;
    }

    public FavouritesFragment() {
        this.f10839b = FavouritesFragment.class.getSimpleName();
        this.f10854q = new FavouritesFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("l");
        try {
            TraceMachine.enterMethod(this._nr_trace, "l#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "l#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10835r, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f10845h = getArguments().getString(Constants.BUNDLE_ADDRESS);
            this.f10846i = getArguments().getDouble(Constants.LAT);
            this.f10847j = getArguments().getDouble(Constants.LNG);
        }
        this.f10838a = this;
        this.f10851n = (FavouritesActivity) getActivity();
        this.f10853p = ((OlaApp) this.f10851n.getApplication()).m12878a();
        Localytics.tagScreen("Favourites");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f10851n);
        if (defaultSharedPreferences.getBoolean(Constants.PREF_COACHMARK_FAVOURITE_SCREEN, false)) {
            m14431a(getResources().getDrawable(R.drawable.favorite_coach));
            defaultSharedPreferences.edit().putBoolean(Constants.PREF_COACHMARK_FAVOURITE_SCREEN, false).apply();
        }
        TraceMachine.exitMethod();
    }

    private void m14431a(Drawable drawable) {
        Dialog dialog = new Dialog(getActivity(), 16973840);
        dialog.setContentView(R.layout.overlay_view);
        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.overlayLayout);
        ((ImageView) dialog.findViewById(R.id.coach_mark_image)).setBackgroundDrawable(drawable);
        relativeLayout.setOnClickListener(new FavouritesFragment(this, dialog));
        dialog.show();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "l#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "l#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10836s, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_favourite, viewGroup, false);
        m14432a(inflate);
        m14440e();
        this.f10844g = false;
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14432a(View view) {
        this.f10841d = (LinearLayout) view.findViewById(R.id.fav_layout);
        this.f10842e = (ImageView) view.findViewById(R.id.fav_menu_button);
        this.f10842e.setOnClickListener(this);
        this.f10843f = (TextView) view.findViewById(R.id.fav_edit_txt);
        this.f10843f.setOnClickListener(this);
        this.f10848k = (TextView) view.findViewById(R.id.addressNewFavText);
        this.f10848k.setText(this.f10845h);
        this.f10849l = (LinearLayout) view.findViewById(R.id.newFavAddLayout);
        this.f10849l.setOnClickListener(this);
        this.f10850m = (RelativeLayout) view.findViewById(R.id.addNewFavourite);
        this.f10850m.setOnClickListener(this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10837t, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (Utils.m14909a(this.f10851n.getApplicationContext())) {
                m14434b();
            } else {
                Toast.makeText(this.f10851n, "Internet connection is not available", 0).show();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.f10853p.m13169a(this.f10839b);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    private void m14434b() {
        this.f10852o = new ProgressDialog(this.f10851n, R.style.TransparentProgressDialog);
        this.f10852o.setIndeterminateDrawable(this.f10851n.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10852o.setCancelable(false);
        this.f10852o.show();
        if (this.f10851n != null) {
            this.f10853p.m13247j(new WeakReference(this.f10854q), this.f10839b);
        }
    }

    private void m14436c() {
        if (Ola.f11489f == null || Ola.f11489f.isEmpty()) {
            m14444a();
            return;
        }
        m14441f();
        m14439d();
    }

    private void m14439d() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.view_favourite_listview, null);
        this.f10840c = (ListView) inflate.findViewById(R.id.fav_list_view);
        this.f10841d.setGravity(48);
        this.f10841d.removeAllViews();
        this.f10841d.addView(inflate);
        m14433a(this.f10840c);
    }

    private void m14433a(ListView listView) {
        Object favouriteListViewAdapter = new FavouriteListViewAdapter(getActivity(), Ola.f11489f, this.f10844g, this.f10838a);
        listView.setAdapter(favouriteListViewAdapter);
        Sherlock.m13345b("Ins favourite loaded");
        favouriteListViewAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new FavouritesFragment(this));
    }

    public void m14444a() {
        if (getActivity() != null) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.view_emplty_favourite, null);
            ((RelativeLayout) inflate.findViewById(R.id.defaultDisplay)).setLayoutParams(new LayoutParams(-1, -1));
            this.f10841d.removeAllViews();
            this.f10841d.setGravity(17);
            this.f10841d.addView(inflate);
            m14440e();
        }
    }

    private void m14440e() {
        this.f10843f.setVisibility(8);
    }

    private void m14441f() {
        this.f10843f.setVisibility(0);
    }

    private void m14442g() {
        m14433a(this.f10840c);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fav_menu_button:
                this.f10851n.finish();
            case R.id.fav_edit_txt:
                if (this.f10844g) {
                    this.f10844g = false;
                    this.f10843f.setText("Edit");
                    m14442g();
                    return;
                }
                this.f10844g = true;
                this.f10843f.setText("Done");
                m14442g();
            case R.id.newFavAddLayout:
                FavouritesActivity.f9574d = 0;
                FavouritesActivity.f9575e = Trace.NULL;
                FavouritesActivity.f9576f = Trace.NULL;
                this.f10851n.m13504a(this.f10846i, this.f10847j);
            case R.id.addNewFavourite:
                if (!this.f10845h.isEmpty()) {
                    this.f10851n.m13505a(0, this.f10845h, Trace.NULL, Trace.NULL, this.f10846i, this.f10847j);
                }
            default:
        }
    }
}
