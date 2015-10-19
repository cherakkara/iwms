package com.payu.p084a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.payu.p084a.R.R;

@Instrumented
/* renamed from: com.payu.a.d */
public class ProgressDialogTestValue extends Activity implements TraceFieldInterface {

    /* renamed from: com.payu.a.d.1 */
    static class ProgressDialogTestValue implements Runnable {
        boolean forthImage;
        final /* synthetic */ Animation val$animation;
        final /* synthetic */ LinearLayout val$fifthInnerCircle;
        final /* synthetic */ LinearLayout val$firstInnerCircle;
        final /* synthetic */ LinearLayout val$forthInnerCircle;
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ LinearLayout val$secondInnerCircle;
        final /* synthetic */ LinearLayout val$thirdInnerCircle;
        final /* synthetic */ View val$view;

        /* renamed from: com.payu.a.d.1.1 */
        class ProgressDialogTestValue implements Runnable {
            ProgressDialogTestValue() {
            }

            public void run() {
                ProgressDialogTestValue.this.val$firstInnerCircle.startAnimation(ProgressDialogTestValue.this.val$animation);
                ProgressDialogTestValue.this.val$firstInnerCircle.setBackgroundResource(R.inner_circle);
            }
        }

        ProgressDialogTestValue(View view, LinearLayout linearLayout, LinearLayout linearLayout2, Animation animation, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, Handler handler) {
            this.val$view = view;
            this.val$firstInnerCircle = linearLayout;
            this.val$secondInnerCircle = linearLayout2;
            this.val$animation = animation;
            this.val$thirdInnerCircle = linearLayout3;
            this.val$forthInnerCircle = linearLayout4;
            this.val$fifthInnerCircle = linearLayout5;
            this.val$handler = handler;
        }

        public void run() {
            if (this.val$view.getVisibility() == 0) {
                if (this.val$firstInnerCircle.getAnimation() != null) {
                    this.val$firstInnerCircle.setBackgroundResource(R.outer_circle);
                    this.val$firstInnerCircle.clearAnimation();
                    this.val$secondInnerCircle.startAnimation(this.val$animation);
                    this.val$secondInnerCircle.setBackgroundResource(R.inner_circle);
                } else if (this.val$secondInnerCircle.getAnimation() != null) {
                    this.val$secondInnerCircle.setBackgroundResource(R.outer_circle);
                    this.val$secondInnerCircle.clearAnimation();
                    this.val$thirdInnerCircle.startAnimation(this.val$animation);
                    this.val$thirdInnerCircle.setBackgroundResource(R.inner_circle);
                } else if (this.val$thirdInnerCircle.getAnimation() != null) {
                    this.val$thirdInnerCircle.setBackgroundResource(R.outer_circle);
                    this.val$thirdInnerCircle.clearAnimation();
                    this.val$forthInnerCircle.startAnimation(this.val$animation);
                    this.val$forthInnerCircle.setBackgroundResource(R.inner_circle);
                } else if (this.val$forthInnerCircle.getAnimation() != null) {
                    this.val$forthInnerCircle.setBackgroundResource(R.outer_circle);
                    this.val$forthInnerCircle.clearAnimation();
                    this.val$fifthInnerCircle.startAnimation(this.val$animation);
                    this.val$fifthInnerCircle.setBackgroundResource(R.inner_circle);
                } else if (this.val$fifthInnerCircle.getAnimation() != null) {
                    this.val$fifthInnerCircle.setBackgroundResource(R.outer_circle);
                    this.val$fifthInnerCircle.clearAnimation();
                    this.forthImage = true;
                    new Handler().postDelayed(new ProgressDialogTestValue(), 200);
                }
                if (this.forthImage) {
                    this.forthImage = false;
                    this.val$handler.postDelayed(this, 400);
                    return;
                }
                this.val$handler.postDelayed(this, 200);
            }
        }
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
        TraceMachine.startTracing("d");
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        setContentView(R.mainprogress);
        ProgressDialogTestValue.showDialog(this, findViewById(R.progress));
        TraceMachine.exitMethod();
    }

    public static void showDialog(Context context, View view) {
        view.setVisibility(0);
        ProgressDialogTestValue.showProgressDialog(context, view);
    }

    public static void removeDialog(View view) {
        view.setVisibility(8);
    }

    public static void showProgressDialog(Context context, View view) {
        Handler handler = new Handler();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.firstOuterCircle);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.secondOuterCircle);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.thirdOuterCircle);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.forthOuterCircle);
        LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.fifthOuterCircle);
        linearLayout.setBackgroundResource(R.inner_circle);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.animation);
        linearLayout.startAnimation(loadAnimation);
        handler.postDelayed(new ProgressDialogTestValue(view, linearLayout, linearLayout2, loadAnimation, linearLayout3, linearLayout4, linearLayout5, handler), 200);
    }
}
