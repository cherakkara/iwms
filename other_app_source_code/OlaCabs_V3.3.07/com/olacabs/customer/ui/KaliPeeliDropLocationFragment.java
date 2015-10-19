package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.m4b.maps.model.LatLng;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.CitiesLocalitiesResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.bl;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.p */
public class KaliPeeliDropLocationFragment extends Fragment implements TextWatcher, OnItemClickListener, BackKeyHandler, TraceFieldInterface {
    public static final String f10939a;
    private static final JoinPoint f10940k = null;
    private static final JoinPoint f10941l = null;
    private static final JoinPoint f10942m = null;
    private static final JoinPoint f10943n = null;
    List<String> f10944b;
    private LatLng f10945c;
    private EditText f10946d;
    private ListView f10947e;
    private boolean f10948f;
    private MainActivity f10949g;
    private ArrayList<bl> f10950h;
    private DataManager f10951i;
    private aj f10952j;

    /* renamed from: com.olacabs.customer.ui.p.1 */
    class KaliPeeliDropLocationFragment implements aj {
        final /* synthetic */ KaliPeeliDropLocationFragment f10931a;

        KaliPeeliDropLocationFragment(KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment) {
            this.f10931a = kaliPeeliDropLocationFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to get city localities", th);
        }

        public void onSuccess(Object obj) {
            CitiesLocalitiesResponse citiesLocalitiesResponse = (CitiesLocalitiesResponse) obj;
            if (citiesLocalitiesResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Success fetching city localities", new Object[0]);
                this.f10931a.f10950h = citiesLocalitiesResponse.getLocalities();
                if (this.f10931a.f10950h == null) {
                    this.f10931a.f10950h = new ArrayList();
                }
                Ola.f11490g = this.f10931a.f10950h;
            } else if (citiesLocalitiesResponse.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13311a("Failed fetching city localities", new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.p.2 */
    class KaliPeeliDropLocationFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10932a;
        final /* synthetic */ KaliPeeliDropLocationFragment f10933b;

        KaliPeeliDropLocationFragment(KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment, AlertDialog alertDialog) {
            this.f10933b = kaliPeeliDropLocationFragment;
            this.f10932a = alertDialog;
        }

        public void onClick(View view) {
            this.f10932a.dismiss();
            this.f10933b.m14494b();
            this.f10933b.f10948f = false;
            this.f10933b.f10949g.onBackPressed();
        }
    }

    /* renamed from: com.olacabs.customer.ui.p.a */
    private class KaliPeeliDropLocationFragment extends BaseAdapter {
        final /* synthetic */ KaliPeeliDropLocationFragment f10936a;
        private Context f10937b;
        private List<bl> f10938c;

        /* renamed from: com.olacabs.customer.ui.p.a.a */
        public class KaliPeeliDropLocationFragment {
            public TextView f10934a;
            final /* synthetic */ KaliPeeliDropLocationFragment f10935b;

            public KaliPeeliDropLocationFragment(KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment) {
                this.f10935b = kaliPeeliDropLocationFragment;
            }
        }

        public KaliPeeliDropLocationFragment(KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment, Context context, List<bl> list) {
            this.f10936a = kaliPeeliDropLocationFragment;
            this.f10937b = context;
            this.f10938c = list;
        }

        public int getCount() {
            return this.f10938c.size();
        }

        public Object getItem(int i) {
            return this.f10938c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean isEmpty() {
            return this.f10938c.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment = new KaliPeeliDropLocationFragment(this);
                view = ((Activity) this.f10937b).getLayoutInflater().inflate(R.layout.view_kp_drop_loc_list_item, viewGroup, false);
                kaliPeeliDropLocationFragment.f10934a = (TextView) view.findViewById(R.id.itemText);
                view.setTag(kaliPeeliDropLocationFragment);
            }
            ((KaliPeeliDropLocationFragment) view.getTag()).f10934a.setText(((bl) this.f10938c.get(i)).getName());
            return view;
        }
    }

    private static void m14492d() {
        Factory factory = new Factory("KaliPeeliDropLocationFragment.java", KaliPeeliDropLocationFragment.class);
        f10940k = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.KaliPeeliDropLocationFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 99);
        f10941l = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.KaliPeeliDropLocationFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 114);
        f10942m = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.KaliPeeliDropLocationFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 137);
        f10943n = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.KaliPeeliDropLocationFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 149);
    }

    static {
        KaliPeeliDropLocationFragment.m14492d();
        f10939a = KaliPeeliDropLocationFragment.class.getSimpleName();
    }

    public static KaliPeeliDropLocationFragment m14483a(Double d, Double d2) {
        KaliPeeliDropLocationFragment kaliPeeliDropLocationFragment = new KaliPeeliDropLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", d.doubleValue());
        bundle.putDouble("longitude", d2.doubleValue());
        kaliPeeliDropLocationFragment.setArguments(bundle);
        return kaliPeeliDropLocationFragment;
    }

    public KaliPeeliDropLocationFragment() {
        this.f10948f = false;
        this.f10950h = new ArrayList();
        this.f10944b = new ArrayList();
        this.f10952j = new KaliPeeliDropLocationFragment(this);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("p");
        try {
            TraceMachine.enterMethod(this._nr_trace, "p#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "p#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10940k, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10951i = ((OlaApp) getActivity().getApplication()).m12878a();
        if (getArguments() != null) {
            this.f10945c = new LatLng(getArguments().getDouble("latitude"), getArguments().getDouble("longitude"));
            this.f10949g = (MainActivity) getActivity();
            m14491c();
        }
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "p#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "p#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10941l, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_kali_peeli_drop_location, viewGroup, false);
        m14486a(inflate);
        ((InputMethodManager) this.f10949g.getSystemService("input_method")).toggleSoftInput(2, 1);
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14486a(View view) {
        this.f10946d = (EditText) view.findViewById(R.id.kp_searchEdit);
        this.f10946d.addTextChangedListener(this);
        this.f10946d.requestFocus();
        this.f10947e = (ListView) view.findViewById(R.id.kp_searchList);
        this.f10947e.setOnItemClickListener(this);
    }

    private void m14491c() {
        this.f10951i.m13241h(new WeakReference(this.f10952j), String.valueOf(this.f10945c.f7554a), String.valueOf(this.f10945c.f7555b), f10939a);
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10942m, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10943n, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (!(Utils.m14909a(this.f10949g.getApplicationContext()) || this.f10948f)) {
                this.f10948f = true;
                m14488a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onEventMainThread(ah ahVar) {
        boolean isConnected = ahVar.isConnected();
        OLog.m13311a("Received DataConnectivityEvent. Connected? - " + isConnected, new Object[0]);
        if (!isConnected && !this.f10948f) {
            this.f10948f = true;
            m14488a(Constants.CONNECTION_TIME_OUT_HEADER, Constants.NO_NETWORK_TEXT);
        }
    }

    public boolean m14493a() {
        ((MainActivity) getActivity()).m13602a(true);
        return false;
    }

    private void m14488a(String str, String str2) {
        View inflate = ((LayoutInflater) this.f10949g.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(this.f10949g).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new KaliPeeliDropLocationFragment(this, create));
        create.show();
    }

    public void m14494b() {
        if (this.f10949g.getCurrentFocus() != null) {
            ((InputMethodManager) this.f10949g.getSystemService("input_method")).hideSoftInputFromWindow(this.f10949g.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        bl blVar = (bl) adapterView.getAdapter().getItem(i);
        ((MainActivity) getActivity()).m13599a(blVar.getName(), blVar.getId());
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f10946d.getWindowToken(), 0);
        this.f10949g.onBackPressed();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.f10944b.clear();
        if (editable.toString() != null) {
            m14487a(editable.toString());
        }
    }

    private void m14487a(String str) {
        if (this.f10950h != null && this.f10950h.size() != 0) {
            List arrayList = new ArrayList();
            Iterator it = this.f10950h.iterator();
            while (it.hasNext()) {
                bl blVar = (bl) it.next();
                if (blVar.getName().toLowerCase().startsWith(str.toLowerCase())) {
                    arrayList.add(blVar);
                }
            }
            Object kaliPeeliDropLocationFragment = new KaliPeeliDropLocationFragment(this, getActivity(), arrayList);
            this.f10947e.setAdapter(kaliPeeliDropLocationFragment);
            kaliPeeliDropLocationFragment.notifyDataSetChanged();
        }
    }
}
