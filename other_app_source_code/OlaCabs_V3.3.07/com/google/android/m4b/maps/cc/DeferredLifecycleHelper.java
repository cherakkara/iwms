package com.google.android.m4b.maps.cc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.m4b.maps.ca.GooglePlayServicesUtil;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.google.android.m4b.maps.cc.a */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    private T f7257a;
    private Bundle f7258b;
    private LinkedList<DeferredLifecycleHelper> f7259c;
    private final OnDelegateCreatedListener<T> f7260d;

    /* renamed from: com.google.android.m4b.maps.cc.a.1 */
    class DeferredLifecycleHelper implements OnDelegateCreatedListener<T> {
        private /* synthetic */ DeferredLifecycleHelper f7242a;

        DeferredLifecycleHelper(DeferredLifecycleHelper deferredLifecycleHelper) {
            this.f7242a = deferredLifecycleHelper;
        }

        public final void m10091a(T t) {
            this.f7242a.f7257a = t;
            Iterator it = this.f7242a.f7259c.iterator();
            while (it.hasNext()) {
                DeferredLifecycleHelper deferredLifecycleHelper = (DeferredLifecycleHelper) it.next();
                this.f7242a.f7257a;
                deferredLifecycleHelper.m10093b();
            }
            this.f7242a.f7259c.clear();
            this.f7242a.f7258b = null;
        }
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.a */
    interface DeferredLifecycleHelper {
        int m10092a();

        void m10093b();
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.2 */
    class DeferredLifecycleHelper implements DeferredLifecycleHelper {
        private /* synthetic */ Activity f7243a;
        private /* synthetic */ Bundle f7244b;
        private /* synthetic */ Bundle f7245c;
        private /* synthetic */ DeferredLifecycleHelper f7246d;

        DeferredLifecycleHelper(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
            this.f7246d = deferredLifecycleHelper;
            this.f7243a = activity;
            this.f7244b = bundle;
            this.f7245c = bundle2;
        }

        public final int m10094a() {
            return 0;
        }

        public final void m10095b() {
            this.f7246d.f7257a.m10123a(this.f7243a, this.f7244b, this.f7245c);
        }
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.3 */
    class DeferredLifecycleHelper implements DeferredLifecycleHelper {
        private /* synthetic */ Bundle f7247a;
        private /* synthetic */ DeferredLifecycleHelper f7248b;

        DeferredLifecycleHelper(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
            this.f7248b = deferredLifecycleHelper;
            this.f7247a = bundle;
        }

        public final int m10096a() {
            return 1;
        }

        public final void m10097b() {
            this.f7248b.f7257a.m10124a(this.f7247a);
        }
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.4 */
    class DeferredLifecycleHelper implements DeferredLifecycleHelper {
        private /* synthetic */ FrameLayout f7249a;
        private /* synthetic */ LayoutInflater f7250b;
        private /* synthetic */ ViewGroup f7251c;
        private /* synthetic */ Bundle f7252d;
        private /* synthetic */ DeferredLifecycleHelper f7253e;

        DeferredLifecycleHelper(DeferredLifecycleHelper deferredLifecycleHelper, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            this.f7253e = deferredLifecycleHelper;
            this.f7249a = frameLayout;
            this.f7250b = layoutInflater;
            this.f7251c = viewGroup;
            this.f7252d = bundle;
        }

        public final int m10098a() {
            return 2;
        }

        public final void m10099b() {
            this.f7249a.removeAllViews();
            this.f7249a.addView(this.f7253e.f7257a.m10121a(this.f7250b, this.f7251c, this.f7252d));
        }
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.5 */
    static class DeferredLifecycleHelper implements OnClickListener {
        private /* synthetic */ Context f7254a;
        private /* synthetic */ int f7255b;

        DeferredLifecycleHelper(Context context, int i) {
            this.f7254a = context;
            this.f7255b = i;
        }

        public final void onClick(View view) {
            Context context = this.f7254a;
            Context context2 = this.f7254a;
            context.startActivity(GooglePlayServicesUtil.m10079a(this.f7255b));
        }
    }

    /* renamed from: com.google.android.m4b.maps.cc.a.6 */
    class DeferredLifecycleHelper implements DeferredLifecycleHelper {
        private /* synthetic */ DeferredLifecycleHelper f7256a;

        DeferredLifecycleHelper(DeferredLifecycleHelper deferredLifecycleHelper) {
            this.f7256a = deferredLifecycleHelper;
        }

        public final int m10100a() {
            return 5;
        }

        public final void m10101b() {
            this.f7256a.f7257a.m10122a();
        }
    }

    protected abstract void m10112a(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public DeferredLifecycleHelper() {
        this.f7260d = new DeferredLifecycleHelper(this);
    }

    public final T m10113b() {
        return this.f7257a;
    }

    private void m10105a(int i) {
        while (!this.f7259c.isEmpty() && ((DeferredLifecycleHelper) this.f7259c.getLast()).m10092a() >= i) {
            this.f7259c.removeLast();
        }
    }

    private void m10106a(Bundle bundle, DeferredLifecycleHelper deferredLifecycleHelper) {
        if (this.f7257a != null) {
            LifecycleDelegate lifecycleDelegate = this.f7257a;
            deferredLifecycleHelper.m10093b();
            return;
        }
        if (this.f7259c == null) {
            this.f7259c = new LinkedList();
        }
        this.f7259c.add(deferredLifecycleHelper);
        if (bundle != null) {
            if (this.f7258b == null) {
                this.f7258b = (Bundle) bundle.clone();
            } else {
                this.f7258b.putAll(bundle);
            }
        }
        m10112a(this.f7260d);
    }

    public final void m10110a(Activity activity, Bundle bundle, Bundle bundle2) {
        m10106a(bundle2, new DeferredLifecycleHelper(this, activity, bundle, bundle2));
    }

    public final void m10111a(Bundle bundle) {
        m10106a(bundle, new DeferredLifecycleHelper(this, bundle));
    }

    public final View m10109a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        m10106a(bundle, new DeferredLifecycleHelper(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.f7257a == null) {
            DeferredLifecycleHelper.m10107a(frameLayout);
        }
        return frameLayout;
    }

    public static void m10107a(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        int a = GooglePlayServicesUtil.m10078a(context);
        CharSequence a2 = GooglePlayServicesUtil.m10080a(context, a);
        CharSequence b = GooglePlayServicesUtil.m10083b(context, a);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(a2);
        linearLayout.addView(textView);
        if (b != null) {
            View button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(b);
            linearLayout.addView(button);
            button.setOnClickListener(new DeferredLifecycleHelper(context, a));
        }
    }

    public final void m10115c() {
        m10106a(null, new DeferredLifecycleHelper(this));
    }

    public final void m10116d() {
        if (this.f7257a != null) {
            this.f7257a.m10125b();
        } else {
            m10105a(5);
        }
    }

    public final void m10117e() {
        if (this.f7257a != null) {
            this.f7257a.m10127c();
        } else {
            m10105a(2);
        }
    }

    public final void m10118f() {
        if (this.f7257a != null) {
            this.f7257a.m10128d();
        } else {
            m10105a(1);
        }
    }

    public final void m10114b(Bundle bundle) {
        if (this.f7257a != null) {
            this.f7257a.m10126b(bundle);
        } else if (this.f7258b != null) {
            bundle.putAll(this.f7258b);
        }
    }

    public final void m10119g() {
        if (this.f7257a != null) {
            this.f7257a.m10129e();
        }
    }
}
