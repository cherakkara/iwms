package com.olacabs.customer.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.google.android.m4b.maps.model.LatLng;
import com.google.gson.Gson;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.bi;
import com.olacabs.customer.p076d.bn;
import com.olacabs.customer.p076d.cg;
import com.olacabs.customer.p076d.ch;
import com.olacabs.customer.p076d.cx;
import com.olacabs.customer.p076d.cy;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.OlaApplicationUtils;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* compiled from: SearchFragment */
public class ai extends Fragment implements TextWatcher, OnClickListener, BackKeyHandler, TraceFieldInterface {
    private static final JoinPoint f10399A = null;
    public static final String f10400a;
    private static final JoinPoint f10401w = null;
    private static final JoinPoint f10402x = null;
    private static final JoinPoint f10403y = null;
    private static final JoinPoint f10404z = null;
    ProgressDialog f10405b;
    private SearchFragment f10406c;
    private EditText f10407d;
    private ListView f10408e;
    private SearchFragment f10409f;
    private cx f10410g;
    private TextView f10411h;
    private ProgressBar f10412i;
    private ArrayList<cg> f10413j;
    private double f10414k;
    private double f10415l;
    private int f10416m;
    private DataManager f10417n;
    private boolean f10418o;
    private int f10419p;
    private ViewStub f10420q;
    private RelativeLayout f10421r;
    private ImageView f10422s;
    private aj f10423t;
    private aj f10424u;
    private Handler f10425v;

    /* renamed from: com.olacabs.customer.ui.ai.a */
    public interface SearchFragment {
        void m13500a();

        void m13501a(LatLng latLng, String str);
    }

    /* renamed from: com.olacabs.customer.ui.ai.1 */
    class SearchFragment implements aj {
        final /* synthetic */ ai f10376a;

        SearchFragment(ai aiVar) {
            this.f10376a = aiVar;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to get city localities", th);
        }

        public void onSuccess(Object obj) {
            cy cyVar = (cy) obj;
            if (cyVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Success fetching search result", new Object[0]);
                cx results = cyVar.getResults();
                if (results != null) {
                    if (this.f10376a.f10413j.size() > 0) {
                        results.getHeaderSection().add("Recent");
                    }
                    if (results.getPopulars().size() > 0) {
                        results.getHeaderSection().add("Popular");
                    }
                    if (this.f10376a.f10408e.getAdapter() == null) {
                        this.f10376a.f10409f = new SearchFragment(this.f10376a, this.f10376a.getActivity(), results);
                        this.f10376a.f10408e.setAdapter(this.f10376a.f10409f);
                    } else {
                        this.f10376a.f10409f.m14057a(results);
                    }
                    this.f10376a.f10410g = results;
                    if (this.f10376a.f10408e.getVisibility() == 8) {
                        this.f10376a.f10408e.setVisibility(0);
                        this.f10376a.f10412i.setVisibility(8);
                    }
                }
            } else if (cyVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13311a("Failed fetching search result", new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.2 */
    class SearchFragment implements aj {
        final /* synthetic */ ai f10377a;

        SearchFragment(ai aiVar) {
            this.f10377a = aiVar;
        }

        public void onFailure(Throwable th) {
            OLog.m13315c("Failed response from google", new Object[0]);
            this.f10377a.m14090d();
        }

        public void onSuccess(Object obj) {
            bi biVar = (bi) obj;
            if (biVar.getStatus().equalsIgnoreCase("OK")) {
                OLog.m13315c("Success response from google", new Object[0]);
                this.f10377a.f10414k = ((bn) biVar.getResults().get(0)).getGeometry().getLocation().getLat();
                this.f10377a.f10415l = ((bn) biVar.getResults().get(0)).getGeometry().getLocation().getLng();
                if (this.f10377a.f10418o) {
                    this.f10377a.f10406c.m13501a(new LatLng(this.f10377a.f10414k, this.f10377a.f10415l), this.f10377a.f10407d.getText().toString());
                } else {
                    this.f10377a.m14064a(this.f10377a.f10419p, this.f10377a.f10414k, this.f10377a.f10415l);
                }
            } else {
                Toast.makeText(this.f10377a.getActivity(), this.f10377a.getActivity().getString(R.string.geocode_failed), 0).show();
            }
            this.f10377a.m14090d();
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.3 */
    class SearchFragment implements OnItemClickListener {
        final /* synthetic */ ai f10378a;

        SearchFragment(ai aiVar) {
            this.f10378a = aiVar;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f10378a.f10409f.getItemViewType(i) == 1) {
                this.f10378a.f10407d.setText(Trace.NULL);
                SearchFragment searchFragment;
                String[] strArr;
                if (i < this.f10378a.f10410g.getPlaces().size()) {
                    this.f10378a.f10407d.setText(((cg) this.f10378a.f10410g.getPlaces().get(i)).getName() + " " + ((cg) this.f10378a.f10410g.getPlaces().get(i)).getAddress());
                    this.f10378a.m14068a("Search result");
                    searchFragment = new SearchFragment(this.f10378a, i, this.f10378a.f10407d.getText().toString(), true);
                    strArr = new String[]{r1};
                    if (searchFragment instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(searchFragment, strArr);
                    } else {
                        searchFragment.execute(strArr);
                    }
                } else if (i < (this.f10378a.f10410g.getPlaces().size() + this.f10378a.f10413j.size()) + 1) {
                    r1 = i - (this.f10378a.f10410g.getPlaces().size() + 1);
                    this.f10378a.f10407d.setText(((cg) this.f10378a.f10413j.get(r1)).getName() + " " + ((cg) this.f10378a.f10413j.get(r1)).getAddress());
                    this.f10378a.m14068a("Rescent");
                    searchFragment = new SearchFragment(this.f10378a, i, this.f10378a.f10407d.getText().toString(), false);
                    strArr = new String[]{r1};
                    if (searchFragment instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(searchFragment, strArr);
                    } else {
                        searchFragment.execute(strArr);
                    }
                } else {
                    if (this.f10378a.f10413j.size() > 0) {
                        r1 = i - ((this.f10378a.f10410g.getPlaces().size() + this.f10378a.f10413j.size()) + 2);
                    } else {
                        r1 = i - (this.f10378a.f10410g.getPlaces().size() + 1);
                    }
                    this.f10378a.f10407d.setText(((ch) this.f10378a.f10410g.getPopulars().get(r1)).getAddress());
                    this.f10378a.m14068a("Popular");
                    this.f10378a.f10406c.m13501a(new LatLng(Double.parseDouble(((ch) this.f10378a.f10410g.getPopulars().get(r1)).getLat()), Double.parseDouble(((ch) this.f10378a.f10410g.getPopulars().get(r1)).getLng())), this.f10378a.f10407d.getText().toString());
                }
                ((InputMethodManager) this.f10378a.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f10378a.f10407d.getWindowToken(), 0);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.4 */
    class SearchFragment implements OnEditorActionListener {
        final /* synthetic */ ai f10379a;

        SearchFragment(ai aiVar) {
            this.f10379a = aiVar;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 3) {
                return false;
            }
            ((InputMethodManager) this.f10379a.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f10379a.f10407d.getWindowToken(), 0);
            return true;
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.5 */
    class SearchFragment implements OnClickListener {
        final /* synthetic */ ai f10380a;

        SearchFragment(ai aiVar) {
            this.f10380a = aiVar;
        }

        public void onClick(View view) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.6 */
    class SearchFragment implements Runnable {
        final /* synthetic */ ai f10381a;

        SearchFragment(ai aiVar) {
            this.f10381a = aiVar;
        }

        public void run() {
            this.f10381a.m14075b(Trace.NULL);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.7 */
    class SearchFragment extends Handler {
        final /* synthetic */ ai f10382a;

        SearchFragment(ai aiVar) {
            this.f10382a = aiVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    OLog.m13311a("Received QUERY_SEARCH_API", new Object[0]);
                    this.f10382a.m14075b((String) message.obj);
                default:
                    OLog.m13317d("Unknown message on mSearchHandler. Ignoring!", new Object[0]);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.b */
    private class SearchFragment extends AsyncTask<String, Void, Address> implements TraceFieldInterface {
        public com.newrelic.agent.android.tracing.Trace _nr_trace;
        final /* synthetic */ ai f10383a;
        private int f10384b;
        private String f10385c;
        private boolean f10386d;

        public void _nr_setTrace(com.newrelic.agent.android.tracing.Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ai$b#doInBackground", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ai$b#doInBackground", null);
                    break;
                }
            }
            Address a = m14055a((String[]) objArr);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return a;
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ai$b#onPostExecute", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ai$b#onPostExecute", null);
                    break;
                }
            }
            m14056a((Address) obj);
            TraceMachine.exitMethod();
        }

        public SearchFragment(ai aiVar, int i, String str, boolean z) {
            this.f10383a = aiVar;
            this.f10384b = i;
            this.f10385c = str;
            this.f10386d = z;
        }

        protected void onPreExecute() {
            this.f10383a.m14091e();
        }

        protected Address m14055a(String[] strArr) {
            Throwable e;
            if (this.f10383a.isAdded()) {
                Address address;
                try {
                    List fromLocationName = new Geocoder(this.f10383a.getActivity()).getFromLocationName(strArr[0], 5);
                    if (!(fromLocationName == null || fromLocationName.isEmpty())) {
                        address = (Address) fromLocationName.get(0);
                        try {
                            if (this.f10383a.m14070a(address)) {
                                return address;
                            }
                            for (int i = 1; i < fromLocationName.size(); i++) {
                                if (this.f10383a.m14070a((Address) fromLocationName.get(i))) {
                                    return (Address) fromLocationName.get(i);
                                }
                            }
                        } catch (IOException e2) {
                            e = e2;
                            OLog.m13312a(e, "Error in ReverseGeoCode " + this.f10385c, new Object[0]);
                            return address;
                        }
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    address = null;
                    e = th;
                }
            }
            return null;
        }

        protected void m14056a(Address address) {
            if (!this.f10383a.isAdded()) {
                return;
            }
            if (address != null) {
                this.f10383a.m14090d();
                if (this.f10386d) {
                    this.f10383a.m14064a(this.f10384b, address.getLatitude(), address.getLongitude());
                    return;
                } else {
                    this.f10383a.f10406c.m13501a(new LatLng(address.getLatitude(), address.getLongitude()), this.f10385c);
                    return;
                }
            }
            this.f10383a.m14069a(this.f10385c, this.f10384b, false);
        }
    }

    /* renamed from: com.olacabs.customer.ui.ai.c */
    public class SearchFragment extends BaseAdapter {
        final /* synthetic */ ai f10394a;
        private Context f10395b;
        private cx f10396c;
        private final int f10397d;
        private final int f10398e;

        /* renamed from: com.olacabs.customer.ui.ai.c.a */
        public class SearchFragment {
            final /* synthetic */ SearchFragment f10387a;

            /* renamed from: com.olacabs.customer.ui.ai.c.a.a */
            public class SearchFragment extends SearchFragment {
                TextView f10388b;
                ImageView f10389c;
                TextView f10390d;
                final /* synthetic */ SearchFragment f10391e;

                public SearchFragment(com.olacabs.customer.ui.ai.SearchFragment.SearchFragment r2) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r1.f10391e = r2;
                    r0 = r2.f10387a;
                    r1.<init>(r0);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.a.a.<init>(com.olacabs.customer.ui.ai$c$a):void");
                }
            }

            /* renamed from: com.olacabs.customer.ui.ai.c.a.b */
            public class SearchFragment extends SearchFragment {
                public TextView f10392b;
                final /* synthetic */ SearchFragment f10393c;

                public SearchFragment(com.olacabs.customer.ui.ai.SearchFragment.SearchFragment r2) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r1.f10393c = r2;
                    r0 = r2.f10387a;
                    r1.<init>(r0);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.a.b.<init>(com.olacabs.customer.ui.ai$c$a):void");
                }
            }

            public SearchFragment(com.olacabs.customer.ui.ai.SearchFragment r1) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.f10387a = r1;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.a.<init>(com.olacabs.customer.ui.ai$c):void");
            }
        }

        public SearchFragment(com.olacabs.customer.ui.ai r2, android.content.Context r3, com.olacabs.customer.p076d.cx r4) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r1.f10394a = r2;
            r1.<init>();
            r0 = 0;
            r1.f10397d = r0;
            r0 = 1;
            r1.f10398e = r0;
            r1.f10395b = r3;
            r1.f10396c = r4;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.<init>(com.olacabs.customer.ui.ai, android.content.Context, com.olacabs.customer.d.cx):void");
        }

        public void m14057a(com.olacabs.customer.p076d.cx r1) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = this;
            r0.f10396c = r1;
            r0.notifyDataSetChanged();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.a(com.olacabs.customer.d.cx):void");
        }

        public int getItemViewType(int r5) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r4 = this;
            r1 = 1;
            r0 = 0;
            r2 = r4.f10394a;
            r2 = r2.f10413j;
            r2 = r2.size();
            if (r2 <= 0) goto L_0x001b;
        L_0x000e:
            r2 = r4.f10396c;
            r2 = r2.getPlaces();
            r2 = r2.size();
            if (r5 != r2) goto L_0x001b;
        L_0x001a:
            return r0;
        L_0x001b:
            r2 = r4.f10396c;
            r2 = r2.getPopulars();
            r2 = r2.size();
            if (r2 <= 0) goto L_0x005c;
        L_0x0027:
            r2 = r4.f10394a;
            r2 = r2.f10413j;
            r2 = r2.size();
            if (r2 <= 0) goto L_0x004e;
        L_0x0033:
            r2 = r4.f10396c;
            r2 = r2.getPlaces();
            r2 = r2.size();
            r3 = r4.f10394a;
            r3 = r3.f10413j;
            r3 = r3.size();
            r2 = r2 + r3;
            r2 = r2 + 1;
            if (r5 == r2) goto L_0x001a;
        L_0x004c:
            r0 = r1;
            goto L_0x001a;
        L_0x004e:
            r2 = r4.f10396c;
            r2 = r2.getPlaces();
            r2 = r2.size();
            if (r5 == r2) goto L_0x001a;
        L_0x005a:
            r0 = r1;
            goto L_0x001a;
        L_0x005c:
            r0 = r1;
            goto L_0x001a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getItemViewType(int):int");
        }

        public int getViewTypeCount() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = 2;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getViewTypeCount():int");
        }

        public int getCount() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r2 = this;
            r0 = r2.f10396c;
            if (r0 != 0) goto L_0x0006;
        L_0x0004:
            r0 = 0;
        L_0x0005:
            return r0;
        L_0x0006:
            r0 = r2.f10396c;
            r0 = r0.getHeaderSection();
            r0 = r0.size();
            r1 = r2.f10396c;
            r1 = r1.getPlaces();
            r1 = r1.size();
            r0 = r0 + r1;
            r1 = r2.f10394a;
            r1 = r1.f10413j;
            r1 = r1.size();
            r0 = r0 + r1;
            r1 = r2.f10396c;
            r1 = r1.getPopulars();
            r1 = r1.size();
            r0 = r0 + r1;
            goto L_0x0005;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getCount():int");
        }

        public java.lang.Object getItem(int r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = 0;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getItem(int):java.lang.Object");
        }

        public long getItemId(int r3) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r2 = this;
            r0 = (long) r3;
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getItemId(int):long");
        }

        public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r6 = this;
            r5 = 2130837950; // 0x7f0201be float:1.7280869E38 double:1.052773828E-314;
            r4 = 1;
            r3 = 0;
            r0 = r6.getItemViewType(r7);
            if (r0 != 0) goto L_0x008e;
        L_0x000b:
            if (r8 != 0) goto L_0x0075;
        L_0x000d:
            r0 = r6.f10395b;
            r0 = (android.app.Activity) r0;
            r0 = r0.getLayoutInflater();
            r1 = 2130903115; // 0x7f03004b float:1.7413039E38 double:1.0528060237E-314;
            r8 = r0.inflate(r1, r9, r3);
            r1 = new com.olacabs.customer.ui.ai$c$a$b;
            r0 = new com.olacabs.customer.ui.ai$c$a;
            r0.<init>(r6);
            r0.getClass();
            r1.<init>(r0);
            r0 = 2131755589; // 0x7f100245 float:1.9142062E38 double:1.053227202E-314;
            r0 = r8.findViewById(r0);
            r0 = (android.widget.TextView) r0;
            r1.f10392b = r0;
            r8.setTag(r1);
            r0 = r1;
        L_0x0038:
            r1 = r6.f10396c;
            r1 = r1.getHeaderSection();
            if (r1 == 0) goto L_0x0074;
        L_0x0040:
            r1 = r6.f10396c;
            r1 = r1.getHeaderSection();
            r1 = r1.isEmpty();
            if (r1 != 0) goto L_0x0074;
        L_0x004c:
            r1 = r6.f10396c;
            r1 = r1.getPlaces();
            r1 = r1.size();
            r2 = r6.f10394a;
            r2 = r2.f10413j;
            r2 = r2.size();
            r1 = r1 + r2;
            if (r7 <= r1) goto L_0x007c;
        L_0x0063:
            r1 = r0.f10392b;
            r0 = r6.f10396c;
            r0 = r0.getHeaderSection();
            r0 = r0.get(r4);
            r0 = (java.lang.CharSequence) r0;
            r1.setText(r0);
        L_0x0074:
            return r8;
        L_0x0075:
            r0 = r8.getTag();
            r0 = (com.olacabs.customer.ui.ai.SearchFragment.SearchFragment.SearchFragment) r0;
            goto L_0x0038;
        L_0x007c:
            r1 = r0.f10392b;
            r0 = r6.f10396c;
            r0 = r0.getHeaderSection();
            r0 = r0.get(r3);
            r0 = (java.lang.CharSequence) r0;
            r1.setText(r0);
            goto L_0x0074;
        L_0x008e:
            if (r0 != r4) goto L_0x0074;
        L_0x0090:
            if (r8 != 0) goto L_0x010f;
        L_0x0092:
            r0 = r6.f10395b;
            r0 = (android.app.Activity) r0;
            r0 = r0.getLayoutInflater();
            r1 = 2130903120; // 0x7f030050 float:1.741305E38 double:1.052806026E-314;
            r8 = r0.inflate(r1, r9, r3);
            r1 = new com.olacabs.customer.ui.ai$c$a$a;
            r0 = new com.olacabs.customer.ui.ai$c$a;
            r0.<init>(r6);
            r0.getClass();
            r1.<init>(r0);
            r0 = 2131755605; // 0x7f100255 float:1.9142094E38 double:1.05322721E-314;
            r0 = r8.findViewById(r0);
            r0 = (android.widget.TextView) r0;
            r1.f10388b = r0;
            r0 = 2131755603; // 0x7f100253 float:1.914209E38 double:1.053227209E-314;
            r0 = r8.findViewById(r0);
            r0 = (android.widget.ImageView) r0;
            r1.f10389c = r0;
            r0 = 2131755606; // 0x7f100256 float:1.9142096E38 double:1.0532272103E-314;
            r0 = r8.findViewById(r0);
            r0 = (android.widget.TextView) r0;
            r1.f10390d = r0;
            r8.setTag(r1);
        L_0x00d2:
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.size();
            if (r7 >= r0) goto L_0x0117;
        L_0x00de:
            r2 = r1.f10388b;
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.get(r7);
            r0 = (com.olacabs.customer.p076d.cg) r0;
            r0 = r0.getName();
            r2.setText(r0);
            r2 = r1.f10390d;
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.get(r7);
            r0 = (com.olacabs.customer.p076d.cg) r0;
            r0 = r0.getAddress();
            r2.setText(r0);
            r0 = r1.f10389c;
            r0.setImageResource(r5);
            goto L_0x0074;
        L_0x010f:
            r0 = r8.getTag();
            r0 = (com.olacabs.customer.ui.ai.SearchFragment.SearchFragment.SearchFragment) r0;
            r1 = r0;
            goto L_0x00d2;
        L_0x0117:
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.size();
            r2 = r6.f10394a;
            r2 = r2.f10413j;
            r2 = r2.size();
            r0 = r0 + r2;
            r0 = r0 + 1;
            if (r7 >= r0) goto L_0x016f;
        L_0x0130:
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.size();
            r0 = r0 + 1;
            r2 = r7 - r0;
            r3 = r1.f10388b;
            r0 = r6.f10394a;
            r0 = r0.f10413j;
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.cg) r0;
            r0 = r0.getName();
            r3.setText(r0);
            r3 = r1.f10390d;
            r0 = r6.f10394a;
            r0 = r0.f10413j;
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.cg) r0;
            r0 = r0.getAddress();
            r3.setText(r0);
            r0 = r1.f10389c;
            r0.setImageResource(r5);
            goto L_0x0074;
        L_0x016f:
            r0 = r6.f10394a;
            r0 = r0.f10413j;
            r0 = r0.size();
            if (r0 <= 0) goto L_0x01f3;
        L_0x017b:
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.size();
            r2 = r6.f10394a;
            r2 = r2.f10413j;
            r2 = r2.size();
            r0 = r0 + r2;
            r0 = r0 + 2;
            r0 = r7 - r0;
            r2 = r0;
        L_0x0195:
            r3 = r1.f10388b;
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getName();
            r3.setText(r0);
            r3 = r1.f10390d;
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getAddress();
            r3.setText(r0);
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getTag();
            if (r0 == 0) goto L_0x0074;
        L_0x01d1:
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getTag();
            r3 = "AIRPORT";
            r0 = r0.equals(r3);
            if (r0 == 0) goto L_0x0203;
        L_0x01e9:
            r0 = r1.f10389c;
            r1 = 2130837941; // 0x7f0201b5 float:1.728085E38 double:1.0527738235E-314;
            r0.setImageResource(r1);
            goto L_0x0074;
        L_0x01f3:
            r0 = r6.f10396c;
            r0 = r0.getPlaces();
            r0 = r0.size();
            r0 = r0 + 1;
            r0 = r7 - r0;
            r2 = r0;
            goto L_0x0195;
        L_0x0203:
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getTag();
            r3 = "BUS_STAND";
            r0 = r0.equals(r3);
            if (r0 == 0) goto L_0x0225;
        L_0x021b:
            r0 = r1.f10389c;
            r1 = 2130837943; // 0x7f0201b7 float:1.7280854E38 double:1.0527738245E-314;
            r0.setImageResource(r1);
            goto L_0x0074;
        L_0x0225:
            r0 = r6.f10396c;
            r0 = r0.getPopulars();
            r0 = r0.get(r2);
            r0 = (com.olacabs.customer.p076d.ch) r0;
            r0 = r0.getTag();
            r2 = "RAILWAY";
            r0 = r0.equals(r2);
            if (r0 == 0) goto L_0x0074;
        L_0x023d:
            r0 = r1.f10389c;
            r1 = 2130837949; // 0x7f0201bd float:1.7280867E38 double:1.0527738275E-314;
            r0.setImageResource(r1);
            goto L_0x0074;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.ui.ai.c.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
        }
    }

    private static void m14082g() {
        Factory factory = new Factory("SearchFragment.java", ai.class);
        f10401w = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.SearchFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 180);
        f10402x = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.SearchFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 210);
        f10403y = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.SearchFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 349);
        f10404z = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.SearchFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 493);
        f10399A = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.SearchFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), (int) HttpStatus.SC_INSUFFICIENT_STORAGE);
    }

    public ai() {
        this.f10423t = new SearchFragment(this);
        this.f10424u = new SearchFragment(this);
        this.f10425v = new SearchFragment(this);
    }

    static {
        m14082g();
        f10400a = ai.class.getSimpleName();
    }

    public static ai m14062a(double d, double d2, int i) {
        ai aiVar = new ai();
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", d);
        bundle.putDouble("longitude", d2);
        bundle.putInt("intentFlag", i);
        aiVar.setArguments(bundle);
        return aiVar;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("ai");
        try {
            TraceMachine.enterMethod(this._nr_trace, "ai#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ai#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10401w, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f10414k = arguments.getDouble("latitude");
            this.f10415l = arguments.getDouble("longitude");
            this.f10416m = arguments.getInt("intentFlag");
        }
        this.f10417n = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen(Constants.DEFAULT_SEARCH_HINT);
        this.f10410g = null;
        if (this.f10413j == null || this.f10413j.isEmpty()) {
            m14080f();
        }
        TraceMachine.exitMethod();
    }

    public boolean m14087a() {
        Localytics.tagEvent("Search cancelled");
        ((MainActivity) getActivity()).m13602a(true);
        return false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "ai#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "ai#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10402x, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
        this.f10412i = (ProgressBar) inflate.findViewById(R.id.progressBar_loading);
        this.f10408e = (ListView) inflate.findViewById(R.id.searchList);
        this.f10420q = (ViewStub) inflate.findViewById(R.id.stub_sad_error);
        this.f10422s = (ImageView) inflate.findViewById(R.id.googleLogo);
        this.f10421r = (RelativeLayout) inflate.findViewById(R.id.main_search_layout);
        this.f10408e.setOnItemClickListener(new SearchFragment(this));
        this.f10407d = (EditText) inflate.findViewById(R.id.searchEdit);
        if (this.f10416m == 1) {
            this.f10407d.setHint(Constants.ESTIMATES_SEARCH_HINT);
        } else if (this.f10416m == 2) {
            this.f10407d.setHint("Search for your favorite pickup");
        } else {
            this.f10407d.setHint(Constants.DEFAULT_SEARCH_HINT);
        }
        this.f10411h = (TextView) inflate.findViewById(R.id.searchCross);
        this.f10411h.setOnClickListener(this);
        this.f10407d.setOnClickListener(this);
        this.f10407d.addTextChangedListener(this);
        this.f10407d.setOnEditorActionListener(new SearchFragment(this));
        inflate.setOnClickListener(new SearchFragment(this));
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14064a(int i, double d, double d2) {
        try {
            if (!this.f10413j.contains(new cg(((cg) this.f10410g.getPlaces().get(i)).getId(), ((cg) this.f10410g.getPlaces().get(i)).getAddress(), ((cg) this.f10410g.getPlaces().get(i)).getName(), ((cg) this.f10410g.getPlaces().get(i)).getReference()))) {
                this.f10413j.add(0, new cg(((cg) this.f10410g.getPlaces().get(i)).getId(), ((cg) this.f10410g.getPlaces().get(i)).getAddress(), ((cg) this.f10410g.getPlaces().get(i)).getName(), ((cg) this.f10410g.getPlaces().get(i)).getReference()));
            }
            if (this.f10413j.size() > 5) {
                this.f10413j = new ArrayList(this.f10413j.subList(0, 5));
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        this.f10406c.m13501a(new LatLng(d, d2), this.f10407d.getText().toString());
    }

    private void m14068a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Item source", str);
        Localytics.tagEvent("Search item selected", hashMap);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (Utils.m14909a(getActivity().getApplicationContext())) {
            new Handler().postDelayed(new SearchFragment(this), 400);
        } else {
            m14088b();
        }
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f10403y, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (Utils.m14909a(getActivity().getApplicationContext())) {
                m14089c();
            } else {
                m14088b();
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m14089c();
        } else {
            m14088b();
        }
    }

    public void m14088b() {
        this.f10408e.setVisibility(8);
        this.f10407d.setEnabled(false);
        this.f10420q.setVisibility(0);
        this.f10422s.setVisibility(8);
    }

    public void m14089c() {
        this.f10407d.setEnabled(true);
        this.f10420q.setVisibility(8);
        this.f10422s.setVisibility(0);
        this.f10408e.setVisibility(0);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f10406c = (SearchFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.f10406c = null;
    }

    private void m14075b(String str) {
        this.f10417n.m13206b(new WeakReference(this.f10423t), String.valueOf(this.f10414k), String.valueOf(this.f10415l), "PICKUP", str, f10400a);
    }

    private void m14069a(String str, int i, boolean z) {
        this.f10418o = z;
        this.f10419p = i;
        this.f10417n.m13236g(new WeakReference(this.f10424u), str, f10400a);
    }

    public void m14090d() {
        if (this.f10405b != null && this.f10405b.isShowing()) {
            this.f10405b.dismiss();
        }
    }

    public void m14091e() {
        this.f10405b = new ProgressDialog(getActivity(), R.style.TransparentProgressDialog);
        this.f10405b.setIndeterminateDrawable(getActivity().getResources().getDrawable(R.drawable.custom_progress_background));
        this.f10405b.setCancelable(false);
        if (!this.f10405b.isShowing()) {
            this.f10405b.show();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchCross:
                this.f10407d.setText(Trace.NULL);
            case R.id.searchEdit:
                if (!Utils.m14909a(getActivity().getApplicationContext())) {
                    m14088b();
                }
            default:
        }
    }

    private void m14080f() {
        ArrayList a = OlaApplicationUtils.m14901a(getActivity(), Constants.RECENT_SEARCH);
        this.f10413j = new ArrayList();
        Gson gson = new Gson();
        Iterator it = a.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            try {
                this.f10413j.add(gson.m12343a((String) it.next(), cg.class));
                i2 = i + 1;
            } catch (Exception e) {
                i2 = i;
            }
            i = i2;
        }
        OLog.m13313b(" loaded recent list from sp " + i, new Object[0]);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().length() > 0) {
            this.f10411h.setVisibility(0);
        } else {
            this.f10411h.setVisibility(8);
        }
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10404z, (Object) this, (Object) this));
        super.onPause();
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f10413j.iterator();
        while (it.hasNext()) {
            arrayList.add(gson.m12346a((cg) it.next()));
            OlaApplicationUtils.m14902a(getActivity(), Constants.RECENT_SEARCH, arrayList);
        }
        this.f10406c.m13500a();
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f10399A, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
    }

    public void afterTextChanged(Editable editable) {
        this.f10417n.m13169a(f10400a);
        this.f10425v.removeMessages(1);
        Message obtainMessage = this.f10425v.obtainMessage(1);
        obtainMessage.obj = editable.toString();
        this.f10425v.sendMessageDelayed(obtainMessage, 500);
    }

    private boolean m14070a(Address address) {
        return "IN".equals(address.getCountryCode());
    }
}
