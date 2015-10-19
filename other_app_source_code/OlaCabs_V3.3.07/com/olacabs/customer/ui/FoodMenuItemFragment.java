package com.olacabs.customer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.bd;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.util.HashMap;
import java.util.Map;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.m */
public class FoodMenuItemFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f10855l = null;
    private static final JoinPoint f10856m = null;
    private bd f10857a;
    private int f10858b;
    private FoodMenuActivity f10859c;
    private ImageView f10860d;
    private ImageView f10861e;
    private TextView f10862f;
    private LinearLayout f10863g;
    private ImageLoader f10864h;
    private ImageView f10865i;
    private DataManager f10866j;
    private int f10867k;

    static {
        FoodMenuItemFragment.m14449d();
    }

    private static void m14449d() {
        Factory factory = new Factory("FoodMenuItemFragment.java", FoodMenuItemFragment.class);
        f10855l = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.FoodMenuItemFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 61);
        f10856m = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.FoodMenuItemFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 71);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public static FoodMenuItemFragment m14445a(int i) {
        FoodMenuItemFragment foodMenuItemFragment = new FoodMenuItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.EXTRA_POSITION, i);
        foodMenuItemFragment.setArguments(bundle);
        return foodMenuItemFragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("m");
        try {
            TraceMachine.enterMethod(this._nr_trace, "m#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "m#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10855l, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10859c = (FoodMenuActivity) getActivity();
        this.f10864h = DataManager.m13137a(getActivity()).m13252l();
        this.f10866j = ((OlaApp) getActivity().getApplication()).m12878a();
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "m#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "m#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10856m, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.food_order_item, viewGroup, false);
        m14447b();
        if (this.f10857a != null) {
            m14446a(inflate);
        } else {
            this.f10859c.m13550c();
        }
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14447b() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f10858b = arguments.getInt(Constants.EXTRA_POSITION, -1);
        }
        if (this.f10858b != -1) {
            this.f10857a = this.f10859c.m13552d(this.f10858b);
        }
        if (this.f10866j.m13218d() != null) {
            this.f10867k = this.f10866j.m13218d().getMaxFoodItem();
        }
    }

    private void m14446a(View view) {
        TextView textView = (TextView) view.findViewById(R.id.food_name);
        Sherlock.m13345b("Ins OFD menu shown");
        NetworkImageView networkImageView = (NetworkImageView) view.findViewById(R.id.food_order_pic);
        NetworkImageView networkImageView2 = (NetworkImageView) view.findViewById(R.id.food_vendor_logo);
        networkImageView.setDefaultImageResId(R.drawable.food_pic_placeholder);
        if (Utils.m14924g(this.f10857a.getImage_url())) {
            networkImageView.m604a(this.f10857a.getImage_url(), this.f10864h);
        }
        if (this.f10857a.getMerchant() != null && Utils.m14924g(this.f10857a.getMerchant().getLogo_url())) {
            networkImageView2.m604a(this.f10857a.getMerchant().getLogo_url(), this.f10864h);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.food_restaurant);
        TextView textView3 = (TextView) view.findViewById(R.id.food_restaurant_address);
        TextView textView4 = (TextView) view.findViewById(R.id.food_price);
        this.f10860d = (ImageView) view.findViewById(R.id.food_order_increase_count);
        this.f10861e = (ImageView) view.findViewById(R.id.food_order_decrease_count);
        this.f10862f = (TextView) view.findViewById(R.id.food_item_count);
        this.f10863g = (LinearLayout) view.findViewById(R.id.quantity_layout);
        this.f10865i = (ImageView) view.findViewById(R.id.veg_nonveg_icon);
        this.f10860d.setOnClickListener(this);
        this.f10861e.setOnClickListener(this);
        if (this.f10857a.isVeg()) {
            this.f10865i.setImageResource(R.drawable.veg_icon);
        } else {
            this.f10865i.setImageResource(R.drawable.non_veg_icon);
        }
        textView.setText(this.f10857a.getName());
        if (this.f10857a.getMerchant() != null) {
            if (Utils.m14924g(this.f10857a.getMerchant().getName())) {
                textView2.setText(this.f10857a.getMerchant().getName());
            }
            if (this.f10857a.getMerchant().getLocation() != null && Utils.m14924g(this.f10857a.getMerchant().getLocation().getAddress_line_one())) {
                textView3.setText(this.f10857a.getMerchant().getLocation().getAddress_line_one());
            }
        }
        textView4.setText(String.valueOf(Math.round(this.f10857a.getSale_price())));
        textView = (TextView) view.findViewById(R.id.food_sold_out);
        textView2 = (TextView) view.findViewById(R.id.food_try_again);
        if (!this.f10859c.m13554e()) {
            this.f10863g.setVisibility(4);
            textView.setVisibility(4);
            textView2.setVisibility(4);
        } else if (this.f10857a.getQuantity() < 1) {
            this.f10863g.setVisibility(4);
            textView.setVisibility(0);
            textView2.setVisibility(0);
        } else {
            this.f10863g.setVisibility(0);
            this.f10860d.setEnabled(true);
            this.f10861e.setEnabled(false);
            m14451a(false);
            if (m14448c()) {
                this.f10860d.setEnabled(false);
            }
        }
    }

    private boolean m14448c() {
        if (this.f10859c.m13549c(this.f10857a.getId()) >= this.f10867k) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.food_order_decrease_count:
                this.f10860d.setEnabled(true);
                this.f10859c.m13548b(this.f10857a.getId());
                this.f10859c.m13542a(-this.f10857a.getSale_price());
                m14451a(true);
            case R.id.food_order_increase_count:
                if (m14448c()) {
                    this.f10860d.setEnabled(false);
                    return;
                }
                m14450a();
                this.f10861e.setEnabled(true);
                this.f10859c.m13557h();
                this.f10859c.m13544a(this.f10857a.getId());
                this.f10859c.m13542a(this.f10857a.getSale_price());
                m14451a(true);
            default:
        }
    }

    public void m14450a() {
        try {
            if (this.f10866j.m13209c() != null && this.f10866j.m13209c().getCity() != null) {
                Map hashMap = new HashMap();
                hashMap.put("Restaurant", this.f10857a.getMerchant().getName());
                hashMap.put("Category", this.f10859c.m13553d());
                hashMap.put(Constants.BUNDLE_TYPE, this.f10857a.getAttributes().getFood_type());
                hashMap.put("Cuisine", this.f10857a.getAttributes().getCuisine());
                hashMap.put("Screen #", String.valueOf(this.f10858b));
                hashMap.put("City name", this.f10866j.m13209c().getCity());
                Localytics.tagEvent("Menu Item Added", hashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m14451a(boolean z) {
        int c = this.f10859c.m13549c(this.f10857a.getId());
        if (c > 0) {
            if (z) {
                this.f10862f.setAnimation(AnimationUtils.loadAnimation(this.f10859c, R.anim.bounce_anim));
            }
            this.f10862f.setVisibility(0);
            this.f10861e.setEnabled(true);
        } else {
            this.f10862f.setVisibility(4);
            this.f10860d.setEnabled(true);
            this.f10861e.setEnabled(false);
        }
        this.f10862f.setText(String.valueOf(c));
    }
}
