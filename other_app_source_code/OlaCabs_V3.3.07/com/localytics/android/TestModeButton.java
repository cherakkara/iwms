package com.localytics.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Instrumented
public final class TestModeButton extends DialogFragment implements TraceFieldInterface {
    static final String TEST_MODE_BUTTON_TAG = "marketing_test_mode_button";
    private Map<Integer, MarketingCallable> mCallbacks;
    private final AtomicBoolean mEnterAnimatable;

    final class TestModeDialog extends Dialog {
        private static final int X_OFFSET = 0;
        private static final int Y_OFFSET = 85;
        private TranslateAnimation mAnimIn;
        private TranslateAnimation mAnimOut;
        private RelativeLayout mDialogLayout;
        private DisplayMetrics mMetrics;

        /* renamed from: com.localytics.android.TestModeButton.TestModeDialog.1 */
        class C07191 implements OnClickListener {
            C07191() {
            }

            public void onClick(View view) {
                TestModeDialog.this.mDialogLayout.startAnimation(TestModeDialog.this.mAnimOut);
                if (TestModeButton.this.mCallbacks != null) {
                    MarketingCallable marketingCallable = (MarketingCallable) TestModeButton.this.mCallbacks.get(Integer.valueOf(9));
                    if (marketingCallable != null) {
                        marketingCallable.call(null);
                    }
                }
            }
        }

        /* renamed from: com.localytics.android.TestModeButton.TestModeDialog.2 */
        class C07202 implements AnimationListener {
            C07202() {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                TestModeButton.this.dismiss();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        final class Button extends View {
            private static final int HEIGHT = 60;
            private static final int WIDTH = 60;
            private final int BACKGROUND_COLOR;
            private final int BAR1_COLOR;
            private final int BAR2_COLOR;
            private final int BAR3_COLOR;
            private final int OVAL_COLOR;
            private final Paint mPaint;
            private final RectF mRectF;

            @TargetApi(11)
            Button(Context context, AttributeSet attributeSet) {
                super(context, attributeSet);
                this.BACKGROUND_COLOR = Color.argb(MotionEventCompat.ACTION_MASK, 51, 51, 51);
                this.BAR1_COLOR = Color.argb(MotionEventCompat.ACTION_MASK, 138, 138, 138);
                this.BAR2_COLOR = Color.argb(MotionEventCompat.ACTION_MASK, 217, 217, 217);
                this.BAR3_COLOR = Color.argb(MotionEventCompat.ACTION_MASK, 220, 220, 220);
                this.OVAL_COLOR = Color.argb(MotionEventCompat.ACTION_MASK, 70, 70, 70);
                if (DatapointHelper.getApiLevel() >= 19) {
                    setLayerType(1, null);
                }
                float f = getResources().getDisplayMetrics().density;
                this.mRectF = new RectF(55.0f * f, 0.0f, 65.0f * f, 60.0f * f);
                setLayoutParams(new LayoutParams((int) ((65.0f * f) + 0.5f), (int) ((f * 60.0f) + 0.5f)));
                this.mPaint = new Paint(1);
                this.mPaint.setColor(this.BACKGROUND_COLOR);
                this.mPaint.setStyle(Style.FILL);
            }

            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                float f = getResources().getDisplayMetrics().density;
                this.mPaint.setColor(this.BACKGROUND_COLOR);
                canvas.drawRect(0.0f, 0.0f, 60.0f * f, 60.0f * f, this.mPaint);
                canvas.drawRoundRect(this.mRectF, 5.0f * f, 5.0f * f, this.mPaint);
                this.mPaint.setColor(this.BAR1_COLOR);
                canvas.drawRect(7.0f * f, 42.0f * f, 13.0f * f, 48.0f * f, this.mPaint);
                this.mPaint.setColor(this.BAR2_COLOR);
                canvas.drawRect(16.0f * f, 36.0f * f, 22.0f * f, 48.0f * f, this.mPaint);
                this.mPaint.setColor(this.BAR3_COLOR);
                canvas.drawRect(25.0f * f, 27.0f * f, 31.0f * f, 48.0f * f, this.mPaint);
                this.mPaint.setColor(-1);
                canvas.drawRect(34.0f * f, 12.0f * f, 40.0f * f, 48.0f * f, this.mPaint);
                this.mPaint.setColor(-1);
                canvas.drawRect(37.0f * f, 42.0f * f, 55.0f * f, 48.0f * f, this.mPaint);
                this.mPaint.setColor(this.OVAL_COLOR);
                canvas.drawOval(new RectF(59.0f * f, 40.0f * f, 63.0f * f, 44.0f * f), this.mPaint);
                canvas.drawOval(new RectF(59.0f * f, 30.0f * f, 63.0f * f, 34.0f * f), this.mPaint);
                canvas.drawOval(new RectF(59.0f * f, 20.0f * f, 63.0f * f, 24.0f * f), this.mPaint);
                canvas.drawOval(new RectF(59.0f * f, 10.0f * f, 63.0f * f, 14.0f * f), this.mPaint);
                canvas.drawOval(new RectF(54.0f * f, 35.0f * f, 58.0f * f, 39.0f * f), this.mPaint);
                canvas.drawOval(new RectF(54.0f * f, 25.0f * f, 58.0f * f, 29.0f * f), this.mPaint);
                canvas.drawOval(new RectF(54.0f * f, 15.0f * f, 58.0f * f, 19.0f * f), this.mPaint);
            }
        }

        public TestModeDialog(Context context, int i) {
            super(context, i);
            setupViews();
            createAnimations();
            adjustLayout();
        }

        private void setupViews() {
            this.mDialogLayout = new RelativeLayout(getContext());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mDialogLayout.setLayoutParams(layoutParams);
            View button = new Button(getContext(), null);
            button.setOnClickListener(new C07191());
            this.mDialogLayout.addView(button);
            requestWindowFeature(1);
            setContentView(this.mDialogLayout);
        }

        private void createAnimations() {
            this.mAnimIn = new TranslateAnimation(2, -1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
            this.mAnimIn.setDuration(250);
            this.mAnimOut = new TranslateAnimation(2, 0.0f, 2, -1.0f, 2, 0.0f, 2, 0.0f);
            this.mAnimOut.setDuration(250);
            this.mAnimOut.setAnimationListener(new C07202());
        }

        @SuppressLint({"NewApi"})
        private void adjustLayout() {
            this.mMetrics = new DisplayMetrics();
            ((WindowManager) TestModeButton.this.getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(this.mMetrics);
            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(X_OFFSET));
            window.setGravity(51);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = (int) TypedValue.applyDimension(1, 0.0f, this.mMetrics);
            attributes.y = (int) TypedValue.applyDimension(1, 85.0f, this.mMetrics);
            attributes.dimAmount = 0.0f;
            window.setAttributes(attributes);
            window.clearFlags(2);
            window.setFlags(32, 32);
            if (TestModeButton.this.mEnterAnimatable.getAndSet(false)) {
                this.mDialogLayout.startAnimation(this.mAnimIn);
            }
            window.setFlags(Defaults.RESPONSE_BODY_LIMIT, Defaults.RESPONSE_BODY_LIMIT);
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (i == 4) {
                this.mDialogLayout.startAnimation(this.mAnimOut);
                Constants.setTestModeEnabled(false);
            }
            return super.onKeyDown(i, keyEvent);
        }
    }

    public TestModeButton() {
        this.mEnterAnimatable = new AtomicBoolean(true);
    }

    static TestModeButton newInstance() {
        TestModeButton testModeButton = new TestModeButton();
        testModeButton.setRetainInstance(true);
        return testModeButton;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "TestModeButton#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TestModeButton#onCreateView", null);
                break;
            }
        }
        Log.m12793d("[TestModeButton]: onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onViewStateRestored(Bundle bundle) {
        Log.m12793d("[TestModeButton]: onViewStateRestored");
        super.onViewStateRestored(bundle);
    }

    public void onResume() {
        Log.m12793d("[TestModeButton]: onResume");
        super.onResume();
    }

    public void onPause() {
        Log.m12793d("[TestModeButton]: onPause");
        super.onPause();
    }

    public void onDestroy() {
        Log.m12793d("[TestModeButton]: onDestroy");
        super.onDestroy();
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.mEnterAnimatable.set(true);
        super.show(fragmentManager, str);
    }

    public void onAttach(Activity activity) {
        Log.m12793d("[TestModeButton]: onAttach");
        super.onAttach(activity);
    }

    public void onDetach() {
        Log.m12793d("[TestModeButton]: onDetach");
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TestModeButton");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TestModeButton#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TestModeButton#onCreate", null);
                break;
            }
        }
        Log.m12793d("[TestModeButton]: onCreate");
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Log.m12793d("[TestModeButton]: onCreateDialog");
        return new TestModeDialog(getActivity(), 16973835);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Log.m12793d("[TestModeButton]: onDismiss");
        super.onDismiss(dialogInterface);
    }

    public void onActivityCreated(Bundle bundle) {
        Log.m12793d("[TestModeButton]: onActivityCreated");
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        Log.m12793d("[TestModeButton]: onStart");
        super.onStart();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.m12793d("[TestModeButton]: onSaveInstanceState");
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        Log.m12793d("[TestModeButton]: onStop");
        super.onStop();
    }

    public void onDestroyView() {
        Log.m12793d("[TestModeButton]: onDestroyView");
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setOnDismissListener(null);
        }
        super.onDestroyView();
    }

    TestModeButton setCallbacks(Map<Integer, MarketingCallable> map) {
        this.mCallbacks = map;
        return this;
    }
}
