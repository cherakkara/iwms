package com.facebook.share.p024a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.FacebookException;
import com.facebook.p022b.NativeProtocol;
import com.facebook.p022b.Utility;
import com.facebook.share.internal.LikeActionController;
import com.facebook.share.internal.LikeBoxCountView;
import com.facebook.share.internal.LikeButton;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.facebook.share.a.a */
public class LikeView extends FrameLayout {
    private String f1078a;
    private LikeView f1079b;
    private LinearLayout f1080c;
    private LikeButton f1081d;
    private LikeBoxCountView f1082e;
    private TextView f1083f;
    private LikeActionController f1084g;
    private LikeView f1085h;
    private BroadcastReceiver f1086i;
    private LikeView f1087j;
    private LikeView f1088k;
    private LikeView f1089l;
    private LikeView f1090m;
    private int f1091n;
    private int f1092o;
    private int f1093p;
    private Fragment f1094q;
    private boolean f1095r;
    private boolean f1096s;

    /* renamed from: com.facebook.share.a.a.1 */
    class LikeView implements OnClickListener {
        final /* synthetic */ LikeView f1045a;

        public void onClick(View view) {
            this.f1045a.m1372a();
        }
    }

    /* renamed from: com.facebook.share.a.a.2 */
    static /* synthetic */ class LikeView {
        static final /* synthetic */ int[] f1046a;

        static {
            f1046a = new int[LikeView.values().length];
            try {
                f1046a[LikeView.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1046a[LikeView.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1046a[LikeView.INLINE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.facebook.share.a.a.a */
    public enum LikeView {
        BOTTOM("bottom", 0),
        INLINE("inline", 1),
        TOP("top", 2);
        
        static LikeView f1050d;
        private String f1052e;
        private int f1053f;

        static {
            f1050d = BOTTOM;
        }

        private LikeView(String str, int i) {
            this.f1052e = str;
            this.f1053f = i;
        }

        public String toString() {
            return this.f1052e;
        }
    }

    /* renamed from: com.facebook.share.a.a.b */
    public enum LikeView {
        CENTER("center", 0),
        LEFT("left", 1),
        RIGHT("right", 2);
        
        static LikeView f1057d;
        private String f1059e;
        private int f1060f;

        static {
            f1057d = CENTER;
        }

        private LikeView(String str, int i) {
            this.f1059e = str;
            this.f1060f = i;
        }

        public String toString() {
            return this.f1059e;
        }
    }

    /* renamed from: com.facebook.share.a.a.c */
    private class LikeView implements LikeActionController.LikeActionController {
        final /* synthetic */ LikeView f1061a;
        private boolean f1062b;

        private LikeView(LikeView likeView) {
            this.f1061a = likeView;
        }

        public void m1366a() {
            this.f1062b = true;
        }

        public void m1367a(LikeActionController likeActionController, FacebookException facebookException) {
            if (!this.f1062b) {
                if (likeActionController != null) {
                    if (likeActionController.m1527e()) {
                        facebookException = new FacebookException("Cannot use LikeView. The device may not be supported.");
                    }
                    this.f1061a.m1376a(likeActionController);
                    this.f1061a.m1380c();
                }
                if (!(facebookException == null || this.f1061a.f1085h == null)) {
                    this.f1061a.f1085h.m1370a(facebookException);
                }
                this.f1061a.f1087j = null;
            }
        }
    }

    /* renamed from: com.facebook.share.a.a.d */
    private class LikeView extends BroadcastReceiver {
        final /* synthetic */ LikeView f1063a;

        private LikeView(LikeView likeView) {
            this.f1063a = likeView;
        }

        public void onReceive(Context context, Intent intent) {
            Object obj = 1;
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object string = extras.getString("com.facebook.sdk.LikeActionController.OBJECT_ID");
                if (!(Utility.m1126a((String) string) || Utility.m1125a(this.f1063a.f1078a, string))) {
                    obj = null;
                }
            }
            if (obj != null) {
                if ("com.facebook.sdk.LikeActionController.UPDATED".equals(action)) {
                    this.f1063a.m1380c();
                } else if ("com.facebook.sdk.LikeActionController.DID_ERROR".equals(action)) {
                    if (this.f1063a.f1085h != null) {
                        this.f1063a.f1085h.m1370a(NativeProtocol.m1047a(extras));
                    }
                } else if ("com.facebook.sdk.LikeActionController.DID_RESET".equals(action)) {
                    this.f1063a.m1379b(this.f1063a.f1078a, this.f1063a.f1079b);
                    this.f1063a.m1380c();
                }
            }
        }
    }

    /* renamed from: com.facebook.share.a.a.e */
    public enum LikeView {
        UNKNOWN(WanType.UNKNOWN, 0),
        OPEN_GRAPH("open_graph", 1),
        PAGE("page", 2);
        
        public static LikeView f1067d;
        private String f1069e;
        private int f1070f;

        static {
            f1067d = UNKNOWN;
        }

        public static LikeView m1368a(int i) {
            for (LikeView likeView : LikeView.values()) {
                if (likeView.m1369a() == i) {
                    return likeView;
                }
            }
            return null;
        }

        private LikeView(String str, int i) {
            this.f1069e = str;
            this.f1070f = i;
        }

        public String toString() {
            return this.f1069e;
        }

        public int m1369a() {
            return this.f1070f;
        }
    }

    /* renamed from: com.facebook.share.a.a.f */
    public interface LikeView {
        void m1370a(FacebookException facebookException);
    }

    /* renamed from: com.facebook.share.a.a.g */
    public enum LikeView {
        STANDARD("standard", 0),
        BUTTON("button", 1),
        BOX_COUNT("box_count", 2);
        
        static LikeView f1074d;
        private String f1076e;
        private int f1077f;

        static {
            f1074d = STANDARD;
        }

        private LikeView(String str, int i) {
            this.f1076e = str;
            this.f1077f = i;
        }

        public String toString() {
            return this.f1076e;
        }
    }

    public void m1386a(String str, LikeView likeView) {
        Object a = Utility.m1099a(str, null);
        if (likeView == null) {
            likeView = LikeView.f1067d;
        }
        if (!Utility.m1125a(a, this.f1078a) || likeView != this.f1079b) {
            m1379b(a, likeView);
            m1380c();
        }
    }

    public void setLikeViewStyle(LikeView likeView) {
        if (likeView == null) {
            likeView = LikeView.f1074d;
        }
        if (this.f1088k != likeView) {
            this.f1088k = likeView;
            m1383d();
        }
    }

    public void setAuxiliaryViewPosition(LikeView likeView) {
        if (likeView == null) {
            likeView = LikeView.f1050d;
        }
        if (this.f1090m != likeView) {
            this.f1090m = likeView;
            m1383d();
        }
    }

    public void setHorizontalAlignment(LikeView likeView) {
        if (likeView == null) {
            likeView = LikeView.f1057d;
        }
        if (this.f1089l != likeView) {
            this.f1089l = likeView;
            m1383d();
        }
    }

    public void setForegroundColor(int i) {
        if (this.f1091n != i) {
            this.f1083f.setTextColor(i);
        }
    }

    public void setOnErrorListener(LikeView likeView) {
        this.f1085h = likeView;
    }

    public LikeView getOnErrorListener() {
        return this.f1085h;
    }

    public void setFragment(Fragment fragment) {
        this.f1094q = fragment;
    }

    public void setEnabled(boolean z) {
        this.f1095r = !z;
        m1380c();
    }

    protected void onDetachedFromWindow() {
        m1386a(null, LikeView.UNKNOWN);
        super.onDetachedFromWindow();
    }

    private void m1372a() {
        if (this.f1084g != null) {
            Activity activity;
            if (this.f1094q == null) {
                Context context = getContext();
                if (context instanceof Activity) {
                    activity = (Activity) context;
                } else if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    }
                }
                this.f1084g.m1523a(activity, this.f1094q, getAnalyticsParameters());
            }
            activity = null;
            this.f1084g.m1523a(activity, this.f1094q, getAnalyticsParameters());
        }
    }

    private Bundle getAnalyticsParameters() {
        Bundle bundle = new Bundle();
        bundle.putString("style", this.f1088k.toString());
        bundle.putString("auxiliary_position", this.f1090m.toString());
        bundle.putString("horizontal_alignment", this.f1089l.toString());
        bundle.putString("object_id", Utility.m1099a(this.f1078a, Trace.NULL));
        bundle.putString("object_type", this.f1079b.toString());
        return bundle;
    }

    private void m1379b(String str, LikeView likeView) {
        m1378b();
        this.f1078a = str;
        this.f1079b = likeView;
        if (!Utility.m1126a(str)) {
            this.f1087j = new LikeView();
            LikeActionController.m1473a(str, likeView, this.f1087j);
        }
    }

    private void m1376a(LikeActionController likeActionController) {
        this.f1084g = likeActionController;
        this.f1096s = likeActionController.m1527e();
        this.f1086i = new LikeView();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.LikeActionController.UPDATED");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_ERROR");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_RESET");
        instance.registerReceiver(this.f1086i, intentFilter);
    }

    private void m1378b() {
        if (this.f1086i != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.f1086i);
            this.f1086i = null;
        }
        if (this.f1087j != null) {
            this.f1087j.m1366a();
            this.f1087j = null;
        }
        this.f1084g = null;
    }

    private void m1380c() {
        boolean z = (this.f1096s || this.f1095r) ? false : true;
        super.setEnabled(z);
        this.f1081d.setEnabled(z);
        if (this.f1084g == null) {
            this.f1081d.setSelected(false);
            this.f1083f.setText(null);
            this.f1082e.setText(null);
        } else {
            this.f1081d.setSelected(this.f1084g.m1526d());
            this.f1083f.setText(this.f1084g.m1525c());
            this.f1082e.setText(this.f1084g.m1524b());
        }
        m1383d();
    }

    private void m1383d() {
        View view;
        int i = 1;
        LayoutParams layoutParams = (LayoutParams) this.f1080c.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1081d.getLayoutParams();
        int i2 = this.f1089l == LikeView.LEFT ? 3 : this.f1089l == LikeView.CENTER ? 1 : 5;
        layoutParams.gravity = i2 | 48;
        layoutParams2.gravity = i2;
        this.f1083f.setVisibility(8);
        this.f1082e.setVisibility(8);
        if (this.f1088k == LikeView.STANDARD && this.f1084g != null && !Utility.m1126a(this.f1084g.m1525c())) {
            view = this.f1083f;
        } else if (this.f1088k == LikeView.BOX_COUNT && this.f1084g != null && !Utility.m1126a(this.f1084g.m1524b())) {
            m1385e();
            view = this.f1082e;
        } else {
            return;
        }
        view.setVisibility(0);
        ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity = i2;
        LinearLayout linearLayout = this.f1080c;
        if (this.f1090m == LikeView.INLINE) {
            i = 0;
        }
        linearLayout.setOrientation(i);
        if (this.f1090m == LikeView.TOP || (this.f1090m == LikeView.INLINE && this.f1089l == LikeView.RIGHT)) {
            this.f1080c.removeView(this.f1081d);
            this.f1080c.addView(this.f1081d);
        } else {
            this.f1080c.removeView(view);
            this.f1080c.addView(view);
        }
        switch (LikeView.f1046a[this.f1090m.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                view.setPadding(this.f1092o, this.f1092o, this.f1092o, this.f1093p);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                view.setPadding(this.f1092o, this.f1093p, this.f1092o, this.f1092o);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.f1089l == LikeView.RIGHT) {
                    view.setPadding(this.f1092o, this.f1092o, this.f1093p, this.f1092o);
                } else {
                    view.setPadding(this.f1093p, this.f1092o, this.f1092o, this.f1092o);
                }
            default:
        }
    }

    private void m1385e() {
        switch (LikeView.f1046a[this.f1090m.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f1082e.setCaretPosition(LikeBoxCountView.LikeBoxCountView.BOTTOM);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.f1082e.setCaretPosition(LikeBoxCountView.LikeBoxCountView.TOP);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.f1082e.setCaretPosition(this.f1089l == LikeView.RIGHT ? LikeBoxCountView.LikeBoxCountView.RIGHT : LikeBoxCountView.LikeBoxCountView.LEFT);
            default:
        }
    }
}
