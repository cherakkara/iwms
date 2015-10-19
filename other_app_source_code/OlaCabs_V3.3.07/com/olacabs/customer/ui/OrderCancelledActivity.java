package com.olacabs.customer.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.CallSupportCommand;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
public class OrderCancelledActivity extends Activity implements OnClickListener, TraceFieldInterface {
    private static final JoinPoint f9781g = null;
    private TextView f9782a;
    private String f9783b;
    private ImageView f9784c;
    private View f9785d;
    private DataManager f9786e;
    private OlaApp f9787f;

    static {
        m13660b();
    }

    private static void m13660b() {
        Factory factory = new Factory("OrderCancelledActivity.java", OrderCancelledActivity.class);
        f9781g = factory.m15070a("method-execution", factory.m15071a("4", "onCreate", "com.olacabs.customer.ui.OrderCancelledActivity", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 30);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    protected void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OrderCancelledActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OrderCancelledActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OrderCancelledActivity#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12825a(Factory.m15068a(f9781g, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f9783b = extras.getString(Constants.ORDER_ID, Trace.NULL);
        }
        setContentView(R.layout.food_order_cancelled_activity);
        this.f9787f = (OlaApp) getApplication();
        this.f9786e = this.f9787f.m12878a();
        m13659a();
        TraceMachine.exitMethod();
    }

    private void m13659a() {
        this.f9782a = (TextView) findViewById(R.id.order_cancelled_subtext);
        this.f9784c = (ImageView) findViewById(R.id.food_cancelled_back);
        this.f9785d = findViewById(R.id.order_cancelled_call_support);
        this.f9785d.setOnClickListener(this);
        this.f9784c.setOnClickListener(this);
        if (this.f9783b != null) {
            this.f9782a.setText("Your order #" + this.f9783b + " couldn't be delivered. We apologize for this and promise to improve our processes to serve you better.");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.food_cancelled_back:
                finish();
            case R.id.order_cancelled_call_support:
                if (this.f9786e.m13218d() != null) {
                    new CallSupportCommand(this.f9786e.m13218d().getFoodCallCenterNumber()).m12889a(this);
                }
            default:
        }
    }
}
