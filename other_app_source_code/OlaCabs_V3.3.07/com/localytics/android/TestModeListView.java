package com.localytics.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
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
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.protocol.HTTP;

@Instrumented
final class TestModeListView extends DialogFragment implements TraceFieldInterface {
    static final String TEST_MODE_LIST_TAG = "marketing_test_mode_list";
    private ListAdapter mAdapter;
    private Map<Integer, MarketingCallable> mCallbacks;
    private final AtomicBoolean mEnterAnimatable;

    final class CancelItemAdapter extends BaseAdapter {
        private final String[] MENU_ITEMS;
        private final Context mContext;

        CancelItemAdapter(Context context) {
            this.MENU_ITEMS = new String[]{"Cancel"};
            this.mContext = context;
        }

        public int getCount() {
            return this.MENU_ITEMS.length;
        }

        public String getItem(int i) {
            return this.MENU_ITEMS[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @TargetApi(16)
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new LinearLayout(this.mContext);
                view.setLayoutParams(new LayoutParams(-1, -2));
                view.setOrientation(1);
                int i2 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 6.0f) + 0.5f);
                view.setPadding(0, i2, 0, i2);
                View textView = new TextView(this.mContext);
                textView.setId(1);
                textView.setTextSize(26.0f);
                textView.setTextColor(Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK));
                textView.setGravity(17);
                view.addView(textView);
                float f = TestModeListView.this.getResources().getDisplayMetrics().density * 8.0f;
                Drawable gradientDrawable = new GradientDrawable(Orientation.TL_BR, new int[]{-1, -1, -1});
                gradientDrawable.setGradientType(0);
                gradientDrawable.setCornerRadii(new float[]{f, f, f, f, f, f, f, f});
                if (DatapointHelper.getApiLevel() >= 16) {
                    view.setBackground(gradientDrawable);
                } else {
                    view.setBackgroundColor(-1);
                }
            }
            TextView textView2 = (TextView) view.findViewById(1);
            textView2.setText(this.MENU_ITEMS[i]);
            textView2.setTypeface(null, 1);
            return view;
        }
    }

    class MenuDialog extends Dialog {
        private LinearLayout mDialogLayout;
        private DisplayMetrics mMetrics;

        /* renamed from: com.localytics.android.TestModeListView.MenuDialog.1 */
        class C07211 implements OnItemClickListener {
            C07211() {
            }

            @TargetApi(11)
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    Constants.setTestModeEnabled(false);
                    TestModeListView.this.dismiss();
                } else if (i == 1) {
                    if (TestModeListView.this.mCallbacks != null) {
                        r0 = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(12));
                        if (r0 != null) {
                            r0.call(null);
                        }
                    }
                } else if (i == 2) {
                    if (TestModeListView.this.mCallbacks != null) {
                        r0 = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(13));
                        if (r0 != null) {
                            r0.call(null);
                        }
                    }
                } else if (i == 3 && TestModeListView.this.mCallbacks != null) {
                    r0 = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(14));
                    if (r0 != null) {
                        r0.call(null);
                    }
                }
                MenuDialog.this.dismiss();
            }
        }

        /* renamed from: com.localytics.android.TestModeListView.MenuDialog.2 */
        class C07222 implements OnItemClickListener {
            C07222() {
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MenuDialog.this.dismiss();
            }
        }

        public MenuDialog(Context context, int i) {
            super(context, i);
            setupViews();
            adjustLayout();
        }

        @TargetApi(16)
        private void setupViews() {
            this.mDialogLayout = new LinearLayout(getContext());
            this.mDialogLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.mDialogLayout.setGravity(16);
            this.mDialogLayout.setOrientation(1);
            int color = getContext().getResources().getColor(17170445);
            ListAdapter menuItemAdapter = new MenuItemAdapter(TestModeListView.this.getActivity());
            View listView = new ListView(TestModeListView.this.getActivity());
            listView.setAdapter(menuItemAdapter);
            if (DatapointHelper.getApiLevel() >= 16) {
                listView.setBackground(new ColorDrawable(color));
            } else {
                listView.setBackgroundColor(color);
            }
            listView.setDividerHeight(1);
            listView.setOnItemClickListener(new C07211());
            this.mDialogLayout.addView(listView);
            View view = new View(TestModeListView.this.getActivity());
            if (DatapointHelper.getApiLevel() >= 16) {
                view.setBackground(new ColorDrawable(color));
            } else {
                view.setBackgroundColor(color);
            }
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((TestModeListView.this.getResources().getDisplayMetrics().density * 10.0f) + 0.5f)));
            this.mDialogLayout.addView(view);
            ListAdapter cancelItemAdapter = new CancelItemAdapter(TestModeListView.this.getActivity());
            View listView2 = new ListView(TestModeListView.this.getActivity());
            listView2.setAdapter(cancelItemAdapter);
            if (DatapointHelper.getApiLevel() >= 16) {
                listView2.setBackground(new ColorDrawable(color));
            } else {
                view.setBackgroundColor(color);
            }
            listView2.setOnItemClickListener(new C07222());
            this.mDialogLayout.addView(listView2);
            requestWindowFeature(1);
            setContentView(this.mDialogLayout);
        }

        private void adjustLayout() {
            this.mMetrics = new DisplayMetrics();
            ((WindowManager) TestModeListView.this.getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(this.mMetrics);
            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(window.getAttributes());
            window.setFlags(Defaults.RESPONSE_BODY_LIMIT, Defaults.RESPONSE_BODY_LIMIT);
        }
    }

    final class MenuItemAdapter extends BaseAdapter {
        private final String[] MENU_ITEMS;
        private final Context mContext;

        public MenuItemAdapter(Context context) {
            this.MENU_ITEMS = new String[]{"Disable Test Mode", "Refresh", "Copy Push Token", "Copy Install ID"};
            this.mContext = context;
        }

        public int getCount() {
            return this.MENU_ITEMS.length;
        }

        public String getItem(int i) {
            return this.MENU_ITEMS[i];
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private Drawable getShape(int i) {
            float f = TestModeListView.this.getResources().getDisplayMetrics().density * 8.0f;
            Drawable gradientDrawable = new GradientDrawable(Orientation.TL_BR, new int[]{-1, -1, -1});
            gradientDrawable.setGradientType(0);
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    gradientDrawable.setCornerRadii(new float[]{f, f, f, f, 0.0f, 0.0f, 0.0f, 0.0f});
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, f, f});
                    break;
            }
            return gradientDrawable;
        }

        @TargetApi(16)
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new LinearLayout(this.mContext);
                view.setLayoutParams(new LayoutParams(-1, -2));
                view.setOrientation(1);
                int i2 = (int) ((this.mContext.getResources().getDisplayMetrics().density * 6.0f) + 0.5f);
                view.setPadding(0, i2, 0, i2);
                View textView = new TextView(this.mContext);
                textView.setId(1);
                textView.setTextSize(26.0f);
                textView.setTextColor(Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK));
                textView.setGravity(17);
                view.addView(textView);
                if (DatapointHelper.getApiLevel() >= 16) {
                    view.setBackground(getShape(i));
                } else {
                    view.setBackgroundColor(-1);
                }
            }
            TextView textView2 = (TextView) view.findViewById(1);
            textView2.setText(this.MENU_ITEMS[i]);
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    textView2.setTextColor(SupportMenu.CATEGORY_MASK);
                    break;
            }
            return view;
        }
    }

    final class TestModeDialog extends Dialog {
        private TranslateAnimation mAnimIn;
        private TranslateAnimation mAnimOut;
        private RelativeLayout mDialogLayout;
        private DisplayMetrics mMetrics;

        /* renamed from: com.localytics.android.TestModeListView.TestModeDialog.1 */
        class C07231 implements OnClickListener {
            C07231() {
            }

            public void onClick(View view) {
                TestModeDialog.this.mDialogLayout.startAnimation(TestModeDialog.this.mAnimOut);
                if (TestModeListView.this.mCallbacks != null) {
                    MarketingCallable marketingCallable = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(10));
                    if (marketingCallable != null) {
                        marketingCallable.call(null);
                    }
                }
            }
        }

        /* renamed from: com.localytics.android.TestModeListView.TestModeDialog.2 */
        class C07242 implements OnClickListener {
            C07242() {
            }

            public void onClick(View view) {
                if (TestModeListView.this.mCallbacks != null) {
                    new MenuDialog(TestModeListView.this.getActivity(), 16973835).show();
                }
            }
        }

        /* renamed from: com.localytics.android.TestModeListView.TestModeDialog.3 */
        class C07253 implements OnItemClickListener {
            C07253() {
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (TestModeListView.this.mCallbacks != null) {
                    MarketingCallable marketingCallable = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(11));
                    if (marketingCallable != null) {
                        marketingCallable.call(new Object[]{adapterView.getItemAtPosition(i)});
                    }
                }
            }
        }

        /* renamed from: com.localytics.android.TestModeListView.TestModeDialog.4 */
        class C07264 implements AnimationListener {
            C07264() {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                TestModeListView.this.dismiss();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        public TestModeDialog(Context context, int i) {
            super(context, i);
            setupViews();
            createAnimations();
            adjustLayout();
        }

        private void setupViews() {
            float f = TestModeListView.this.getResources().getDisplayMetrics().density;
            this.mDialogLayout = new RelativeLayout(getContext());
            this.mDialogLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.mDialogLayout.setBackgroundColor(-1);
            View relativeLayout = new RelativeLayout(getContext());
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10);
            relativeLayout.setId(1);
            relativeLayout.setLayoutParams(layoutParams);
            relativeLayout.setBackgroundColor(Color.argb(MotionEventCompat.ACTION_MASK, 73, 73, 73));
            relativeLayout.setPadding((int) ((8.0f * f) + 0.5f), (int) ((12.0f * f) + 0.5f), (int) ((8.0f * f) + 0.5f), (int) ((f * 12.0f) + 0.5f));
            this.mDialogLayout.addView(relativeLayout);
            View textView = new TextView(getContext());
            textView.setText(HTTP.CONN_CLOSE);
            textView.setTextSize(22.0f);
            r3 = new int[3][];
            r3[0] = new int[]{16842919};
            r3[1] = new int[]{16842908};
            r3[2] = new int[0];
            textView.setTextColor(new ColorStateList(r3, new int[]{Color.argb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, 0, 0), Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK), Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK)}));
            textView.setOnClickListener(new C07231());
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(9);
            textView.setLayoutParams(layoutParams);
            relativeLayout.addView(textView);
            textView = new TextView(getContext());
            textView.setText("Localytics");
            textView.setTextSize(22.0f);
            textView.setTextColor(-1);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            textView.setLayoutParams(layoutParams);
            relativeLayout.addView(textView);
            textView = new TextView(getContext());
            textView.setText("Menu");
            textView.setTextSize(22.0f);
            r3 = new int[3][];
            r3[0] = new int[]{16842919};
            r3[1] = new int[]{16842908};
            r3[2] = new int[0];
            textView.setTextColor(new ColorStateList(r3, new int[]{Color.argb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, 0, 0), Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK), Color.argb(MotionEventCompat.ACTION_MASK, 0, 91, MotionEventCompat.ACTION_MASK)}));
            textView.setOnClickListener(new C07242());
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(11);
            textView.setLayoutParams(layoutParams);
            relativeLayout.addView(textView);
            textView = new RelativeLayout(getContext());
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(3, 1);
            textView.setLayoutParams(layoutParams2);
            this.mDialogLayout.addView(textView);
            relativeLayout = new ListView(getContext());
            relativeLayout.setAdapter(TestModeListView.this.mAdapter);
            relativeLayout.setOnItemClickListener(new C07253());
            textView.addView(relativeLayout);
            requestWindowFeature(1);
            setContentView(this.mDialogLayout);
        }

        private void createAnimations() {
            this.mAnimIn = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, br.DEFAULT_BACKOFF_MULT, 2, 0.0f);
            this.mAnimIn.setDuration(250);
            this.mAnimOut = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, br.DEFAULT_BACKOFF_MULT);
            this.mAnimOut.setDuration(250);
            this.mAnimOut.setAnimationListener(new C07264());
        }

        @SuppressLint({"NewApi"})
        private void adjustLayout() {
            this.mMetrics = new DisplayMetrics();
            ((WindowManager) TestModeListView.this.getActivity().getSystemService("window")).getDefaultDisplay().getMetrics(this.mMetrics);
            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.0f;
            attributes.width = this.mMetrics.widthPixels;
            window.setAttributes(attributes);
            if (TestModeListView.this.mEnterAnimatable.getAndSet(false)) {
                this.mDialogLayout.startAnimation(this.mAnimIn);
            }
            window.setFlags(Defaults.RESPONSE_BODY_LIMIT, Defaults.RESPONSE_BODY_LIMIT);
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (i != 4) {
                return super.onKeyDown(i, keyEvent);
            }
            this.mDialogLayout.startAnimation(this.mAnimOut);
            if (TestModeListView.this.mCallbacks != null) {
                MarketingCallable marketingCallable = (MarketingCallable) TestModeListView.this.mCallbacks.get(Integer.valueOf(10));
                if (marketingCallable != null) {
                    marketingCallable.call(null);
                }
            }
            return super.onKeyDown(i, keyEvent);
        }
    }

    private TestModeListView() {
        this.mEnterAnimatable = new AtomicBoolean(true);
    }

    public static TestModeListView newInstance() {
        TestModeListView testModeListView = new TestModeListView();
        testModeListView.setRetainInstance(true);
        return testModeListView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "TestModeListView#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TestModeListView#onCreateView", null);
                break;
            }
        }
        Log.m12793d("[TestModeListView]: onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onViewStateRestored(Bundle bundle) {
        Log.m12793d("[TestModeListView]: onViewStateRestored");
        super.onViewStateRestored(bundle);
    }

    public void onResume() {
        Log.m12793d("[TestModeListView]: onResume");
        super.onResume();
    }

    public void onPause() {
        Log.m12793d("[TestModeListView]: onPause");
        super.onPause();
    }

    public void onDestroy() {
        Log.m12793d("[TestModeListView]: onDestroy");
        super.onDestroy();
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.mEnterAnimatable.set(true);
        super.show(fragmentManager, str);
    }

    public void onAttach(Activity activity) {
        Log.m12793d("[TestModeListView]: onAttach");
        super.onAttach(activity);
    }

    public void onDetach() {
        Log.m12793d("[TestModeListView]: onDetach");
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TestModeListView");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TestModeListView#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TestModeListView#onCreate", null);
                break;
            }
        }
        Log.m12793d("[TestModeListView]: onCreate");
        super.onCreate(bundle);
        TraceMachine.exitMethod();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Log.m12793d("[TestModeListView]: onCreateDialog");
        return new TestModeDialog(getActivity(), 16973835);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Log.m12793d("[TestModeListView]: onDismiss");
        super.onDismiss(dialogInterface);
    }

    public void onActivityCreated(Bundle bundle) {
        Log.m12793d("[TestModeListView]: onActivityCreated");
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        Log.m12793d("[TestModeListView]: onStart");
        super.onStart();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.m12793d("[TestModeListView]: onSaveInstanceState");
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        Log.m12793d("[TestModeListView]: onStop");
        super.onStop();
    }

    public void onDestroyView() {
        Log.m12793d("[TestModeListView]: onDestroyView");
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setOnDismissListener(null);
        }
        super.onDestroyView();
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
    }

    public TestModeListView setCallbacks(Map<Integer, MarketingCallable> map) {
        this.mCallbacks = map;
        return this;
    }
}
