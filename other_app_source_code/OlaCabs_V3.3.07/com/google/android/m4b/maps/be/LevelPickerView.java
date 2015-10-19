package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.p057t.IndoorLevelReference;
import com.google.p025a.p028c.bk;
import com.olacabs.customer.p076d.br;
import java.text.MessageFormat;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.be.l */
public final class LevelPickerView extends ListView implements IndoorStateListener {
    private int f6042a;
    private cf f6043b;
    private IndoorStateInterface f6044c;
    private int f6045d;
    private LevelPickerView f6046e;
    private final Set<IndoorLevelReference> f6047f;
    private final Resources f6048g;
    private final ba f6049h;

    /* renamed from: com.google.android.m4b.maps.be.l.1 */
    class LevelPickerView implements AnimationListener {
        private /* synthetic */ LevelPickerView f6031a;

        LevelPickerView(LevelPickerView levelPickerView) {
            this.f6031a = levelPickerView;
        }

        public final void onAnimationEnd(Animation animation) {
            this.f6031a.m9414b();
        }

        public final void onAnimationRepeat(Animation animation) {
        }

        public final void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.2 */
    class LevelPickerView implements AnimationListener {
        private /* synthetic */ LevelPickerView f6032a;

        LevelPickerView(LevelPickerView levelPickerView) {
            this.f6032a = levelPickerView;
        }

        public final void onAnimationEnd(Animation animation) {
            this.f6032a.setVisibility(8);
        }

        public final void onAnimationRepeat(Animation animation) {
        }

        public final void onAnimationStart(Animation animation) {
            this.f6032a.setVisibility(0);
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.3 */
    class LevelPickerView implements Runnable {
        private /* synthetic */ LevelPickerView f6033a;

        LevelPickerView(LevelPickerView levelPickerView) {
            this.f6033a = levelPickerView;
        }

        public final void run() {
            if (this.f6033a.f6044c != null) {
                this.f6033a.m9425b(this.f6033a.f6044c);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.4 */
    class LevelPickerView implements Runnable {
        private /* synthetic */ cf f6034a;
        private /* synthetic */ LevelPickerView f6035b;

        LevelPickerView(LevelPickerView levelPickerView, cf cfVar) {
            this.f6035b = levelPickerView;
            this.f6034a = cfVar;
        }

        public final void run() {
            if (this.f6035b.f6044c != null) {
                IndoorLevelReference a = this.f6035b.f6044c.m9369a(this.f6034a.m5783a());
                if (LevelPickerView.m9415b(this.f6035b.f6043b, this.f6034a)) {
                    this.f6035b.m9410a(this.f6035b.f6043b, a);
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.5 */
    class LevelPickerView implements Runnable {
        private /* synthetic */ LevelPickerView f6036a;

        LevelPickerView(LevelPickerView levelPickerView) {
            this.f6036a = levelPickerView;
        }

        public final void run() {
            this.f6036a.m9414b();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.a */
    class LevelPickerView extends ArrayAdapter<LevelPickerView> {
        private /* synthetic */ LevelPickerView f6037a;

        LevelPickerView(LevelPickerView levelPickerView, Context context, cf cfVar) {
            this.f6037a = levelPickerView;
            super(context, -1);
            if (cfVar.m5788e()) {
                add(new LevelPickerView(null));
            }
            for (IndoorLevelInterface levelPickerView2 : cfVar.m5785b()) {
                add(new LevelPickerView(levelPickerView2));
            }
        }

        public final boolean hasStableIds() {
            return true;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            LevelPickerView levelPickerView;
            View view3 = (RelativeLayout) view;
            if (view == null) {
                view3 = new RelativeLayout(getContext());
                view3.setLayoutParams(new LayoutParams(-1, m9406a(44)));
                view2 = view3;
            } else {
                view2 = view3;
            }
            LevelPickerView levelPickerView2 = (LevelPickerView) view2.getTag();
            if (levelPickerView2 == null) {
                View textView = new TextView(getContext());
                textView.setPadding(0, 0, 0, 0);
                textView.setClickable(false);
                textView.setTextSize(2, 18.0f);
                textView.setTextColor(this.f6037a.f6048g.getColor(R.floorpicker_text));
                textView.setGravity(17);
                ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, m9406a(36));
                layoutParams.addRule(10);
                view2.addView(textView, layoutParams);
                view3 = new LinearLayout(getContext());
                ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(12);
                layoutParams2.addRule(14);
                view2.addView(view3, layoutParams2);
                levelPickerView2 = new LevelPickerView(textView, m9407a(view3, this.f6037a.f6048g.getDrawable(R.floorpicker_mylocation)), m9407a(view3, this.f6037a.f6048g.getDrawable(R.floorpicker_search)));
                view2.setTag(levelPickerView2);
                levelPickerView = levelPickerView2;
            } else {
                levelPickerView = levelPickerView2;
            }
            levelPickerView.f6039a.setText(((LevelPickerView) getItem(i)).toString());
            levelPickerView.f6039a.setContentDescription(MessageFormat.format(this.f6037a.f6048g.getString(R.LEVEL_ALT_TEXT), new Object[]{r0}));
            if (i == this.f6037a.f6042a) {
                levelPickerView.f6039a.setTextColor(this.f6037a.f6048g.getColor(R.black));
                view2.setBackgroundDrawable(this.f6037a.f6048g.getDrawable(R.floorpicker_bg_selected));
                view2.destroyDrawingCache();
            } else {
                levelPickerView.f6039a.setTextColor(this.f6037a.f6048g.getColor(R.floorpicker_text));
                if (view2.getBackground() != null) {
                    view2.setBackgroundDrawable(null);
                    view2.destroyDrawingCache();
                }
            }
            if (i == this.f6037a.f6045d) {
                levelPickerView.f6040b.setVisibility(0);
            } else {
                levelPickerView.f6040b.setVisibility(8);
            }
            IndoorLevelInterface a = ((LevelPickerView) getItem(i)).m9408a();
            int i2 = (a == null || !this.f6037a.f6047f.contains(a.m5802a())) ? 8 : 0;
            levelPickerView.f6041c.setVisibility(i2);
            return view2;
        }

        private ImageView m9407a(ViewGroup viewGroup, Drawable drawable) {
            View imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(-2, m9406a(16)));
            imageView.setPadding(m9406a(1), 0, m9406a(1), 0);
            imageView.setImageDrawable(drawable);
            viewGroup.addView(imageView);
            return imageView;
        }

        private int m9406a(int i) {
            return Math.round(TypedValue.applyDimension(1, (float) i, this.f6037a.getResources().getDisplayMetrics()));
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.b */
    static class LevelPickerView {
        private final IndoorLevelInterface f6038a;

        LevelPickerView(IndoorLevelInterface indoorLevelInterface) {
            this.f6038a = indoorLevelInterface;
        }

        public final IndoorLevelInterface m9408a() {
            return this.f6038a;
        }

        public final String toString() {
            if (this.f6038a == null) {
                return "1";
            }
            return this.f6038a.m5805e();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.l.c */
    static class LevelPickerView {
        public final TextView f6039a;
        public final View f6040b;
        public final View f6041c;

        LevelPickerView(TextView textView, View view, View view2) {
            this.f6039a = textView;
            this.f6040b = view;
            this.f6041c = view2;
        }
    }

    public LevelPickerView(Context context, Resources resources) {
        this(context, null, resources);
    }

    private LevelPickerView(Context context, AttributeSet attributeSet, Resources resources) {
        super(context, null);
        this.f6042a = -1;
        this.f6045d = -1;
        this.f6047f = bk.m2870a();
        this.f6049h = bb.m8775b();
        this.f6048g = resources;
    }

    public final void m9424a(IndoorStateInterface indoorStateInterface) {
        if (this.f6044c != null) {
            this.f6044c.m9374b((IndoorStateListener) this);
        }
        if (indoorStateInterface != null) {
            m9421a();
            indoorStateInterface.m9370a((IndoorStateListener) this);
        }
        this.f6044c = indoorStateInterface;
    }

    private static boolean m9415b(cf cfVar, cf cfVar2) {
        if (cfVar == cfVar2) {
            return true;
        }
        if (cfVar == null || cfVar2 == null) {
            return false;
        }
        return cfVar.m5783a().equals(cfVar2.m5783a());
    }

    public final void m9421a() {
        post(new LevelPickerView(this));
    }

    public final void m9423a(cf cfVar) {
        post(new LevelPickerView(this, cfVar));
    }

    final void m9425b(IndoorStateInterface indoorStateInterface) {
        cf c = indoorStateInterface.m9375c();
        IndoorLevelReference indoorLevelReference = null;
        if (c != null) {
            indoorLevelReference = indoorStateInterface.m9369a(c.m5783a());
        }
        m9410a(c, indoorLevelReference);
    }

    private void m9410a(cf cfVar, IndoorLevelReference indoorLevelReference) {
        this.f6049h.m8774a();
        if (!LevelPickerView.m9415b(cfVar, this.f6043b)) {
            Animation alphaAnimation;
            clearAnimation();
            this.f6043b = null;
            this.f6045d = -1;
            this.f6042a = -1;
            if (cfVar != null) {
                boolean z;
                if (cfVar != null) {
                    if (cfVar.m5785b().size() >= (cfVar.m5788e() ? true : true)) {
                        z = true;
                        if (z) {
                            this.f6043b = cfVar;
                            setVisibility(0);
                            alphaAnimation = new AlphaAnimation(0.0f, br.DEFAULT_BACKOFF_MULT);
                            alphaAnimation.setFillAfter(true);
                            alphaAnimation.setDuration(500);
                            alphaAnimation.setAnimationListener(new LevelPickerView(this));
                            startAnimation(alphaAnimation);
                            this.f6046e = new LevelPickerView(this, getContext(), this.f6043b);
                            setAdapter(this.f6046e);
                            this.f6049h.m8774a();
                            if (!(this.f6043b == null || -1 == this.f6045d)) {
                                this.f6045d = -1;
                                this.f6046e.notifyDataSetChanged();
                            }
                        }
                    }
                }
                z = false;
                if (z) {
                    this.f6043b = cfVar;
                    setVisibility(0);
                    alphaAnimation = new AlphaAnimation(0.0f, br.DEFAULT_BACKOFF_MULT);
                    alphaAnimation.setFillAfter(true);
                    alphaAnimation.setDuration(500);
                    alphaAnimation.setAnimationListener(new LevelPickerView(this));
                    startAnimation(alphaAnimation);
                    this.f6046e = new LevelPickerView(this, getContext(), this.f6043b);
                    setAdapter(this.f6046e);
                    this.f6049h.m8774a();
                    this.f6045d = -1;
                    this.f6046e.notifyDataSetChanged();
                }
            }
            if (this.f6043b == null && getVisibility() == 0) {
                setVisibility(8);
                alphaAnimation = new AlphaAnimation(br.DEFAULT_BACKOFF_MULT, 0.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setAnimationListener(new LevelPickerView(this));
                startAnimation(alphaAnimation);
            }
        }
        if (this.f6043b != null) {
            int i;
            cf cfVar2 = this.f6043b;
            if (cfVar2 == null) {
                i = -1;
            } else {
                if (indoorLevelReference == null) {
                    i = cfVar2.m5788e() ? 0 : -1;
                } else {
                    i = cfVar2.m5784b(indoorLevelReference);
                    if (i >= 0 && cfVar2.m5788e()) {
                        i++;
                    }
                }
                if (i < 0) {
                    i = -1;
                }
            }
            m9422a(i);
            m9414b();
        }
    }

    private void m9414b() {
        if (this.f6042a != -1) {
            smoothScrollToPosition(this.f6042a);
        }
    }

    public final void m9422a(int i) {
        if (i != this.f6042a) {
            this.f6042a = i;
            this.f6046e.notifyDataSetChanged();
            if (i != -1) {
                LevelPickerView levelPickerView = (LevelPickerView) getItemAtPosition(i);
                if (levelPickerView != null) {
                    IndoorLevelInterface a = levelPickerView.m9408a();
                    if (this.f6044c == null) {
                        return;
                    }
                    if (a == null) {
                        this.f6044c.m9377d(this.f6043b);
                    } else {
                        this.f6044c.m9372a(a.m5802a());
                    }
                }
            }
        }
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        post(new LevelPickerView(this));
    }
}
