package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.android.m4b.maps.model.LatLng;
import com.google.gson.Gson;
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
import com.olacabs.customer.p076d.CabCategory;
import com.olacabs.customer.p076d.CityBaseCarModelDetailsResponse;
import com.olacabs.customer.p076d.ab;
import com.olacabs.customer.p076d.ac;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.be;
import com.olacabs.customer.p076d.bf;
import com.olacabs.customer.p076d.bk;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.bt;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.tfs.p081a.TFSHasBookingResponse;
import com.olacabs.customer.tfs.ui.TFSTrackRideActivity;
import com.olacabs.customer.ui.e.AnonymousClass10;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.ui.widgets.FareDialogHelper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.e */
public class BookingCabCategoryFragment extends Fragment implements OnClickListener, ErrorView, TraceFieldInterface {
    private static final JoinPoint f10607A = null;
    public static final String f10608a;
    private static final JoinPoint f10609z = null;
    private boolean f10610b;
    private int f10611c;
    private String f10612d;
    private boolean f10613e;
    private String f10614f;
    private int f10615g;
    private int f10616h;
    private ArrayList<CabCategory> f10617i;
    private ArrayList<bk> f10618j;
    private ArrayList<ac> f10619k;
    private BookingFragment f10620l;
    private ViewGroup f10621m;
    private HorizontalScrollView f10622n;
    private MainActivity f10623o;
    private boolean f10624p;
    private DataManager f10625q;
    private RelativeLayout f10626r;
    private FareDialogHelper f10627s;
    private Rect f10628t;
    private TextView f10629u;
    private aj f10630v;
    private aj f10631w;
    private aj f10632x;
    private aj f10633y;

    /* compiled from: BookingCabCategoryFragment */
    /* renamed from: com.olacabs.customer.ui.e.10 */
    class AnonymousClass10 implements OnClickListener {
        final /* synthetic */ AlertDialog f10594a;
        final /* synthetic */ BookingCabCategoryFragment f10595b;

        AnonymousClass10(BookingCabCategoryFragment bookingCabCategoryFragment, AlertDialog alertDialog) {
            this.f10595b = bookingCabCategoryFragment;
            this.f10594a = alertDialog;
        }

        public void onClick(View view) {
            Localytics.tagEvent("TFS First Time Pop Up Clicked");
            this.f10594a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.1 */
    class BookingCabCategoryFragment implements aj {
        final /* synthetic */ BookingCabCategoryFragment f10596a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10596a = bookingCabCategoryFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f10596a.isAdded()) {
                Sherlock.m13336a("Ins OFD menu shown", (VolleyError) th, th.getMessage(), true);
                ((BookingFragment) this.f10596a.getParentFragment()).m14387y();
                OLog.m13310a("PendingOrderStatusRequest failed", th);
                ((BookingFragment) this.f10596a.getParentFragment()).m14383u();
                this.f10596a.m14219r();
                this.f10596a.m14228a(this.f10596a.getActivity().getString(R.string.generic_failure_header), this.f10596a.getActivity().getString(R.string.generic_failure_desc));
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10596a.isAdded()) {
                bf[] bfVarArr = (bf[]) obj;
                if (bfVarArr.length > 0) {
                    Sherlock.m13347c("Ins OFD menu shown");
                    OLog.m13311a("User has a pending food order", new Object[0]);
                    ((BookingFragment) this.f10596a.getParentFragment()).m14387y();
                    this.f10596a.m14219r();
                    this.f10596a.m14187a(bfVarArr[0].getId());
                    return;
                }
                OLog.m13311a("User has no outstanding pending food orders", new Object[0]);
                this.f10596a.m14218q();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.2 */
    class BookingCabCategoryFragment implements OnClickListener {
        final /* synthetic */ BookingCabCategoryFragment f10597a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10597a = bookingCabCategoryFragment;
        }

        public void onClick(View view) {
            this.f10597a.f10623o.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f10597a.f10623o.getString(R.string.url_tfs_knowmore))));
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.3 */
    class BookingCabCategoryFragment implements aj {
        final /* synthetic */ BookingCabCategoryFragment f10598a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10598a = bookingCabCategoryFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetching Tfs fare failed", th);
        }

        public void onSuccess(Object obj) {
            OLog.m13313b("Fetching Tfs fare Successful", new Object[0]);
            TFSFareResponse tFSFareResponse = (TFSFareResponse) obj;
            CabCategory cabCategory = (CabCategory) this.f10598a.f10617i.get(this.f10598a.f10611c);
            if (this.f10598a.f10627s.m14852a() && cabCategory.isTFS() && tFSFareResponse.getFares() != null) {
                this.f10598a.f10627s.m14851a(tFSFareResponse);
            }
            if ("SUCCESS".equalsIgnoreCase(tFSFareResponse.getStatus()) && tFSFareResponse.getFares() != null) {
                this.f10598a.f10625q.m13218d().setTfsFareModel(tFSFareResponse);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.4 */
    class BookingCabCategoryFragment implements aj {
        final /* synthetic */ BookingCabCategoryFragment f10599a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10599a = bookingCabCategoryFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("User does not have a booking", th);
            ((BookingFragment) this.f10599a.getParentFragment()).m14343B();
        }

        public void onSuccess(Object obj) {
            if (this.f10599a.isAdded()) {
                TFSHasBookingResponse tFSHasBookingResponse = (TFSHasBookingResponse) obj;
                if (tFSHasBookingResponse == null || tFSHasBookingResponse.getBookingId() == null || tFSHasBookingResponse.getBookingId().equalsIgnoreCase(Trace.NULL)) {
                    ((BookingFragment) this.f10599a.getParentFragment()).m14343B();
                    return;
                }
                OLog.m13311a("User has a booking", new Object[0]);
                ((BookingFragment) this.f10599a.getParentFragment()).m14387y();
                Intent intent = new Intent(this.f10599a.getActivity(), TFSTrackRideActivity.class);
                intent.putExtra(Constants.ARG_BOOKING_ID, tFSHasBookingResponse.getBookingId());
                intent.putExtra(Constants.CITY, this.f10599a.f10614f);
                this.f10599a.startActivity(intent);
                this.f10599a.m14246m();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.5 */
    class BookingCabCategoryFragment implements aj {
        final /* synthetic */ BookingCabCategoryFragment f10600a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10600a = bookingCabCategoryFragment;
        }

        public void onFailure(Throwable th) {
            if (this.f10600a.isAdded()) {
                this.f10600a.m14219r();
                ((BookingFragment) this.f10600a.getParentFragment()).m14387y();
                ((BookingFragment) this.f10600a.getParentFragment()).m14383u();
                VolleyError volleyError = (VolleyError) th;
                if (volleyError != null && volleyError.f464a != null) {
                    byte[] bArr = volleyError.f464a.f498b;
                    if (bArr != null) {
                        try {
                            bt btVar = (bt) new Gson().m12343a(new String(bArr), bt.class);
                            ((BookingFragment) this.f10600a.getParentFragment()).m14354a(btVar.getHeader(), btVar.getText());
                            Sherlock.m13336a("Ins OFD menu shown", volleyError, btVar.getText(), true);
                            if (this.f10600a.f10625q.m13209c() != null && this.f10600a.f10625q.m13209c().getCity() != null) {
                                Map hashMap = new HashMap();
                                hashMap.put("City name", this.f10600a.f10625q.m13209c().getCity());
                                Localytics.tagEvent("Out of zone clicked", hashMap);
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    }
                    Sherlock.m13336a("Ins OFD menu shown", null, this.f10600a.getString(R.string.generic_failure_desc), true);
                    this.f10600a.m14228a(this.f10600a.getString(R.string.generic_failure_header), this.f10600a.getString(R.string.generic_failure_desc));
                }
            }
        }

        public void onSuccess(Object obj) {
            if (this.f10600a.isAdded()) {
                String str;
                String str2 = Trace.NULL;
                try {
                    str2 = ((be) obj).getFoodCategory().getName();
                    ((BookingFragment) this.f10600a.getParentFragment()).m14387y();
                    str = str2;
                } catch (Exception e) {
                    Exception exception = e;
                    str = str2;
                    exception.printStackTrace();
                }
                this.f10600a.m14219r();
                this.f10600a.m14202c(str);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.6 */
    class BookingCabCategoryFragment implements Runnable {
        final /* synthetic */ BookingCabCategoryFragment f10601a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10601a = bookingCabCategoryFragment;
        }

        public void run() {
            this.f10601a.m14221t();
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.7 */
    class BookingCabCategoryFragment implements OnClickListener {
        final /* synthetic */ View f10602a;
        final /* synthetic */ BookingCabCategoryFragment f10603b;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment, View view) {
            this.f10603b = bookingCabCategoryFragment;
            this.f10602a = view;
        }

        public void onClick(View view) {
            OLog.m13313b("Category Clicked", new Object[0]);
            BookingFragment bookingFragment = (BookingFragment) this.f10603b.getParentFragment();
            this.f10603b.f10613e = true;
            int indexOfChild = ((ViewGroup) view.getParent()).indexOfChild(view);
            this.f10603b.m14197b(indexOfChild);
            CabCategory cabCategory = (CabCategory) this.f10603b.f10617i.get(indexOfChild);
            this.f10603b.m14206e(cabCategory.getDisplayName());
            if (cabCategory.isFoodDelivery()) {
                Sherlock.m13334a("Ins OFD menu shown");
                bookingFragment.m14388z();
                bookingFragment.m14384v();
                this.f10602a.setClickable(false);
                this.f10602a.setFocusable(false);
                this.f10602a.setEnabled(false);
                this.f10603b.m14248o();
                this.f10603b.m14222u();
                return;
            }
            Sherlock.m13334a("Ins categories switched");
            if (this.f10603b.f10627s.m14852a()) {
                if (cabCategory.isTFS()) {
                    this.f10603b.f10627s.m14853b();
                    return;
                } else if (this.f10603b.f10611c == indexOfChild) {
                    this.f10603b.f10627s.m14853b();
                } else {
                    CityBaseCarModelDetailsResponse a = this.f10603b.m14184a(cabCategory);
                    if (a != null) {
                        this.f10603b.f10627s.m14850a(cabCategory, a);
                    }
                }
            } else if (this.f10603b.f10611c != indexOfChild || cabCategory.isTFS()) {
                bookingFragment.m14388z();
                if (cabCategory.isTFS() && !this.f10603b.f10627s.m14852a()) {
                    Sherlock.m13347c("Ins categories switched");
                    if (bookingFragment.f10725b.getBoolean("is clicked first tfs", true)) {
                        bookingFragment.m14387y();
                        this.f10603b.m14249p();
                        bookingFragment.f10725b.edit().putBoolean("is clicked first tfs", false).apply();
                        bookingFragment.m14343B();
                    } else {
                        bookingFragment.m14369g();
                        this.f10603b.f10625q.m13260o(new WeakReference(this.f10603b.f10631w), BookingCabCategoryFragment.f10608a);
                    }
                    this.f10603b.m14188a(view, bookingFragment, cabCategory);
                    return;
                }
            } else {
                CityBaseCarModelDetailsResponse a2 = this.f10603b.m14184a(cabCategory);
                if (a2 != null) {
                    this.f10603b.f10627s.m14850a(cabCategory, a2);
                    return;
                }
                return;
            }
            this.f10603b.m14188a(view, bookingFragment, cabCategory);
            bookingFragment.m14369g();
            bookingFragment.m14386x();
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.8 */
    class BookingCabCategoryFragment implements OnLongClickListener {
        final /* synthetic */ BookingCabCategoryFragment f10604a;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment) {
            this.f10604a = bookingCabCategoryFragment;
        }

        public boolean onLongClick(View view) {
            Fragment findFragmentById;
            OLog.m13313b("Category Long Clicked", new Object[0]);
            if (this.f10604a.getActivity() != null) {
                findFragmentById = this.f10604a.getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
            } else {
                findFragmentById = this.f10604a.f10623o.getSupportFragmentManager().findFragmentById(R.id.container);
            }
            if (findFragmentById != null && (findFragmentById instanceof BookingFragment)) {
                BookingFragment bookingFragment = (BookingFragment) this.f10604a.getParentFragment();
                int indexOfChild = ((ViewGroup) view.getParent()).indexOfChild(view);
                this.f10604a.m14197b(indexOfChild);
                CabCategory cabCategory = (CabCategory) this.f10604a.f10617i.get(indexOfChild);
                if (!cabCategory.isFoodDelivery()) {
                    if (cabCategory.isTFS()) {
                        this.f10604a.f10627s.m14851a(this.f10604a.m14223v());
                        this.f10604a.m14224w();
                    } else {
                        this.f10604a.f10611c = ((ViewGroup) view.getParent()).indexOfChild(view);
                        OLog.m13313b("-----------------2 here", new Object[0]);
                        bookingFragment.m14342A().m10542a().m10076c();
                        bookingFragment.m14369g();
                        this.f10604a.f10612d = ((CabCategory) this.f10604a.f10617i.get(this.f10604a.f10611c)).getId();
                        CityBaseCarModelDetailsResponse a = this.f10604a.m14184a(cabCategory);
                        if (a != null) {
                            this.f10604a.f10627s.m14850a(cabCategory, a);
                        }
                        this.f10604a.m14220s();
                        bookingFragment.m14386x();
                    }
                }
            }
            return true;
        }
    }

    /* renamed from: com.olacabs.customer.ui.e.9 */
    class BookingCabCategoryFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10605a;
        final /* synthetic */ BookingCabCategoryFragment f10606b;

        BookingCabCategoryFragment(BookingCabCategoryFragment bookingCabCategoryFragment, AlertDialog alertDialog) {
            this.f10606b = bookingCabCategoryFragment;
            this.f10605a = alertDialog;
        }

        public void onClick(View view) {
            this.f10605a.dismiss();
        }
    }

    private static void m14225x() {
        Factory factory = new Factory("BookingCabCategoryFragment.java", BookingCabCategoryFragment.class);
        f10609z = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.BookingCabCategoryFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 252);
        f10607A = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.BookingCabCategoryFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 266);
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
        BookingCabCategoryFragment.m14225x();
        f10608a = BookingCabCategoryFragment.class.getSimpleName();
    }

    public static BookingCabCategoryFragment m14186a() {
        return new BookingCabCategoryFragment();
    }

    public BookingCabCategoryFragment() {
        this.f10610b = false;
        this.f10613e = false;
        this.f10624p = true;
        this.f10630v = new BookingCabCategoryFragment(this);
        this.f10631w = new BookingCabCategoryFragment(this);
        this.f10632x = new BookingCabCategoryFragment(this);
        this.f10633y = new BookingCabCategoryFragment(this);
        this.f10617i = new ArrayList();
    }

    private void m14218q() {
        if (this.f10625q != null && this.f10625q.m13209c() != null) {
            this.f10625q.m13253l(new WeakReference(this.f10632x), f10608a);
        }
    }

    private void m14202c(String str) {
        Intent intent = new Intent(this.f10623o != null ? this.f10623o : getActivity(), FoodMenuActivity.class);
        intent.putExtra("CURRENT_SESSION_CATEGORY", str);
        startActivity(intent);
    }

    private void m14219r() {
        if (this.f10621m != null) {
            for (int i = 0; i < this.f10621m.getChildCount(); i++) {
                View childAt = this.f10621m.getChildAt(i);
                childAt.setClickable(true);
                childAt.setFocusable(true);
                childAt.setEnabled(true);
            }
        }
    }

    private void m14187a(long j) {
        Intent intent = new Intent(this.f10623o != null ? this.f10623o : getActivity(), OrderDetailsActivity.class);
        intent.putExtra(Constants.ORDER_ID, j);
        startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing(com.apsalar.sdk.Constants.API_PREFIX);
        try {
            TraceMachine.enterMethod(this._nr_trace, "e#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "e#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10609z, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10623o = (MainActivity) getActivity();
        this.f10625q = ((OlaApp) this.f10623o.getApplication()).m12878a();
        this.f10615g = this.f10625q.m13224e().getScreenWidth();
        this.f10616h = (int) getResources().getDimension(R.dimen.category_width);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "e#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "e#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10607A, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_booking_cab_category, viewGroup, false);
        this.f10621m = (ViewGroup) inflate.findViewById(R.id.category_panel_layout);
        this.f10626r = (RelativeLayout) inflate.findViewById(R.id.rate_card_layout);
        this.f10622n = (HorizontalScrollView) inflate.findViewById(R.id.hsv);
        m14221t();
        Sherlock.m13345b("Ins app launch time");
        this.f10620l = (BookingFragment) getParentFragment();
        this.f10626r.setOnClickListener(this);
        this.f10627s = new FareDialogHelper(this.f10626r, this.f10620l);
        this.f10628t = new Rect();
        this.f10622n.getHitRect(this.f10628t);
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.measure(0, 0);
        ((BookingFragment) getParentFragment()).m14347a(view.getMeasuredHeight());
    }

    public boolean m14234c() {
        return this.f10613e;
    }

    public void m14236d() {
        this.f10613e = true;
    }

    public void m14227a(String str) {
        this.f10612d = str;
        for (int i = 0; i < this.f10617i.size(); i++) {
            if (str.equalsIgnoreCase(((CabCategory) this.f10617i.get(i)).getId())) {
                this.f10611c = i;
            }
        }
        m14220s();
    }

    private void m14220s() {
        if (this.f10621m != null) {
            if (this.f10612d == null) {
                this.f10612d = Trace.NULL;
            }
            if (this.f10617i.size() != 0) {
                int i = 0;
                while (i < this.f10621m.getChildCount() && i < this.f10617i.size()) {
                    this.f10621m.getChildAt(i).setSelected(((CabCategory) this.f10617i.get(i)).getId().equals(this.f10612d));
                    i++;
                }
            }
        }
    }

    public String m14238e() {
        return this.f10612d;
    }

    public CabCategory m14239f() {
        if (this.f10617i == null || this.f10617i.size() <= 0 || this.f10617i.size() <= this.f10611c) {
            return null;
        }
        return (CabCategory) this.f10617i.get(this.f10611c);
    }

    public void m14240g() {
        m14242i();
        m14241h();
    }

    public void m14241h() {
        this.f10617i.clear();
    }

    public void m14242i() {
        this.f10613e = false;
        this.f10612d = null;
        this.f10611c = 0;
    }

    public void m14232b(String str) {
        this.f10614f = str;
    }

    public boolean m14230a(ArrayList<CabCategory> arrayList, boolean z) {
        if (m14235c((ArrayList) arrayList)) {
            if (arrayList != null) {
                this.f10617i = arrayList;
            }
            if (z) {
                new Handler().postDelayed(new BookingCabCategoryFragment(this), 500);
                return true;
            }
            m14221t();
            return true;
        }
        m14237d((ArrayList) arrayList);
        this.f10617i = arrayList;
        return false;
    }

    public ArrayList<CabCategory> m14243j() {
        return this.f10617i;
    }

    public ArrayList<bk> m14244k() {
        return this.f10618j;
    }

    public void m14229a(ArrayList<bk> arrayList) {
        if (arrayList != null) {
            this.f10618j = arrayList;
        }
    }

    public void m14233b(ArrayList<ac> arrayList) {
        if (arrayList != null) {
            this.f10619k = arrayList;
        }
    }

    public ArrayList<ac> m14245l() {
        return this.f10619k;
    }

    boolean m14235c(ArrayList<CabCategory> arrayList) {
        if (arrayList == null) {
            return false;
        }
        if (this.f10617i.size() != arrayList.size()) {
            if (m14207e((ArrayList) arrayList)) {
                m14242i();
            }
            return true;
        }
        for (int i = 0; i < this.f10617i.size(); i++) {
            if (!((CabCategory) this.f10617i.get(i)).getId().equals(((CabCategory) arrayList.get(i)).getId())) {
                return true;
            }
        }
        return false;
    }

    private boolean m14207e(ArrayList<CabCategory> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((CabCategory) it.next()).getId().equalsIgnoreCase(this.f10612d)) {
                return false;
            }
        }
        return true;
    }

    void m14237d(ArrayList<CabCategory> arrayList) {
        OLog.m13318e("Reseting ETA", new Object[0]);
        if (this.f10621m != null && this.f10617i.size() != 0 && arrayList != null && !arrayList.isEmpty()) {
            m14209f((ArrayList) arrayList);
        }
    }

    private void m14209f(ArrayList<CabCategory> arrayList) {
        for (int i = 0; i < this.f10621m.getChildCount(); i++) {
            TextView textView = (TextView) this.f10621m.getChildAt(i).findViewById(R.id.item_eta);
            if (!((CabCategory) arrayList.get(i)).isTFS()) {
                if (((CabCategory) arrayList.get(i)).getEta() != null) {
                    textView.setText(((CabCategory) arrayList.get(i)).getEta() + " min");
                } else if (((CabCategory) arrayList.get(i)).isFoodDelivery()) {
                    textView.setText(!TextUtils.isEmpty(((CabCategory) arrayList.get(i)).getFood_delivery_time()) ? ((CabCategory) arrayList.get(i)).getFood_delivery_time() : Trace.NULL);
                } else {
                    textView.setText(((CabCategory) arrayList.get(i)).isAutoRickshaw() ? "no autos" : "no cabs");
                }
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Booking city", Sherlock.m13349d(this.f10614f));
        Sherlock.m13340a("Ins category panel shown", hashMap);
    }

    private void m14221t() {
        if (getActivity() != null && !isDetached() && this.f10621m != null) {
            this.f10621m.removeAllViews();
            Iterator it = this.f10617i.iterator();
            while (it.hasNext()) {
                m14183a(this.f10621m, (CabCategory) it.next());
            }
            m14220s();
        }
    }

    private View m14183a(ViewGroup viewGroup, CabCategory cabCategory) {
        int round;
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.item_booking_cab_category, viewGroup, false);
        float f = (float) (this.f10615g / this.f10616h);
        if (f <= ((float) this.f10617i.size())) {
            round = (int) Math.round(((double) this.f10615g) / (((double) Math.round(f)) - 0.5d));
        } else {
            round = -1;
        }
        LayoutParams layoutParams = new LinearLayout.LayoutParams(round, -1);
        layoutParams.weight = br.DEFAULT_BACKOFF_MULT;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.item_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.item_eta);
        TextView textView2 = (TextView) inflate.findViewById(R.id.item_name);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.item_surcharge_icon);
        if (cabCategory.isSurchargeApplicable()) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
        textView2.setText(cabCategory.getName());
        if (cabCategory.getEta() != null) {
            textView.setText(cabCategory.getEta() + " min");
        } else if (cabCategory.isFoodDelivery()) {
            textView.setText(!TextUtils.isEmpty(cabCategory.getFood_delivery_time()) ? cabCategory.getFood_delivery_time() : Trace.NULL);
        } else {
            textView.setText(cabCategory.getId().equalsIgnoreCase("local_auto") ? "no autos" : "no cabs");
        }
        viewGroup.addView(inflate, layoutParams);
        if (cabCategory.getId().equalsIgnoreCase("economy_sedan")) {
            imageView.setImageResource(R.drawable.bg_button_category_sedan);
        } else if (cabCategory.getId().equalsIgnoreCase("compact")) {
            imageView.setImageResource(R.drawable.bg_button_category_mini);
        } else if (cabCategory.getId().equalsIgnoreCase("luxury_sedan")) {
            imageView.setImageResource(R.drawable.bg_button_category_luxury);
        } else if (cabCategory.getId().equalsIgnoreCase("pink")) {
            imageView.setImageResource(R.drawable.bg_button_category_pink);
        } else if (cabCategory.getId().equalsIgnoreCase("local_taxi")) {
            imageView.setImageResource(R.drawable.bg_button_category_kp);
        } else if (cabCategory.getId().equalsIgnoreCase("local_auto")) {
            imageView.setImageResource(R.drawable.bg_button_category_auto);
        } else if (cabCategory.getId().equalsIgnoreCase("food_delivery")) {
            imageView.setImageResource(R.drawable.bg_button_category_cafe);
        } else if (cabCategory.getId().equalsIgnoreCase("ola_nano")) {
            imageView.setImageResource(R.drawable.bg_button_category_nano);
        } else if (cabCategory.getId().equalsIgnoreCase("tfs")) {
            imageView.setImageResource(R.drawable.bg_button_category_tfs);
        } else if (cabCategory.getId().equalsIgnoreCase("delivery")) {
            imageView.setImageResource(m14203d(cabCategory.getImage_name()));
        } else {
            imageView.setImageResource(R.drawable.bg_button_category_sedan);
        }
        inflate.setClickable(true);
        inflate.setFocusable(true);
        inflate.setEnabled(true);
        inflate.setOnClickListener(new BookingCabCategoryFragment(this, inflate));
        inflate.setOnLongClickListener(new BookingCabCategoryFragment(this));
        if (cabCategory.isTFS()) {
            this.f10629u = textView;
        }
        return inflate;
    }

    public void m14246m() {
        this.f10611c = 0;
        this.f10612d = ((CabCategory) this.f10617i.get(this.f10611c)).getId();
        m14220s();
    }

    private void m14188a(View view, BookingFragment bookingFragment, CabCategory cabCategory) {
        this.f10611c = ((ViewGroup) view.getParent()).indexOfChild(view);
        this.f10612d = ((CabCategory) this.f10617i.get(this.f10611c)).getId();
        m14220s();
        OLog.m13313b("-----------------1 here", new Object[0]);
        bookingFragment.m14342A().m10542a().m10076c();
        bookingFragment.m14385w();
        if (TextUtils.isEmpty(cabCategory.getDisplayText())) {
            bookingFragment.m14382t().m14861a();
        } else {
            bookingFragment.m14382t().m14863a(cabCategory.getDisplayText());
        }
    }

    private void m14222u() {
        this.f10625q.m13210c(new WeakReference(this.f10630v));
    }

    public void m14226a(int i) {
        if (this.f10629u == null) {
            return;
        }
        if (i != 0) {
            this.f10629u.setText(i + " min");
        } else {
            this.f10629u.setText("no cabs");
        }
    }

    private int m14203d(String str) {
        try {
            int identifier = this.f10623o.getResources().getIdentifier(str, "drawable", this.f10623o.getPackageName());
            if (identifier == 0) {
                return R.drawable.bg_delivery_default;
            }
            return identifier;
        } catch (Exception e) {
            e.printStackTrace();
            return R.drawable.bg_delivery_default;
        }
    }

    private void m14206e(String str) {
        if (this.f10625q.m13209c() != null && this.f10625q.m13209c().getCity() != null) {
            Map hashMap = new HashMap();
            hashMap.put("City name", this.f10625q.m13209c().getCity());
            hashMap.put("Cab category", str);
            Localytics.tagEvent("Category Selected", hashMap);
        }
    }

    public boolean m14247n() {
        return this.f10627s.m14852a();
    }

    public void m14248o() {
        this.f10627s.m14853b();
    }

    public void m14228a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str2);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new BookingCabCategoryFragment(this, create));
        create.setCancelable(false);
        create.show();
    }

    public void m14231b() {
        this.f10610b = false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rate_card_layout:
                this.f10627s.m14853b();
            default:
        }
    }

    private CityBaseCarModelDetailsResponse m14184a(CabCategory cabCategory) {
        ab cityBasedFareModels = this.f10625q.m13218d().getCityBasedFareModels();
        if (cityBasedFareModels == null || cityBasedFareModels.getCarModels() == null) {
            return null;
        }
        return cityBasedFareModels.getCarModels().getCategoryDetails(cabCategory.getId());
    }

    private TFSFareResponse m14223v() {
        return this.f10625q.m13218d().getTfsFareModel();
    }

    private void m14197b(int i) {
        View childAt = this.f10621m.getChildAt(i + 1);
        View childAt2 = this.f10621m.getChildAt(i - 1);
        if (childAt == null || m14194a(childAt, this.f10628t)) {
            this.f10622n.fullScroll(66);
        } else if (childAt2 == null || m14194a(childAt2, this.f10628t)) {
            this.f10622n.fullScroll(17);
        }
    }

    private boolean m14194a(View view, Rect rect) {
        return !view.getLocalVisibleRect(rect);
    }

    public void m14249p() {
        Builder builder = new Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.view_tfs_popup, null);
        Button button = (Button) inflate.findViewById(R.id.tfs_button_got_it);
        TextView textView = (TextView) inflate.findViewById(R.id.tfs_know_more);
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        button.setOnClickListener(new AnonymousClass10(this, create));
        textView.setOnClickListener(new BookingCabCategoryFragment(this));
        create.setCancelable(false);
        create.show();
    }

    private void m14224w() {
        BookingFragment bookingFragment = (BookingFragment) getParentFragment();
        LatLng g = bookingFragment.m14342A().m13401g();
        if (bookingFragment.m14342A().m13399e() && g != null) {
            this.f10625q.m13201b(new WeakReference(this.f10633y), Double.valueOf(g.f7554a), Double.valueOf(g.f7555b), "tfsFareRequestTag");
        }
    }
}
