package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.ui.BookingCabCategoryFragment;
import org.apache.http.HttpStatus;

/* renamed from: com.olacabs.customer.ui.widgets.e */
public class YellowStripHelper {
    public static final String f11406a;
    private final int f11407b;
    private final int f11408c;
    private Context f11409d;
    private FragmentManager f11410e;
    private TextView f11411f;
    private Handler f11412g;
    private YellowStripHelper f11413h;
    private int f11414i;
    private boolean f11415j;
    private boolean f11416k;
    private boolean f11417l;
    private int f11418m;
    private boolean f11419n;
    private int f11420o;
    private View f11421p;
    private Runnable f11422q;

    /* renamed from: com.olacabs.customer.ui.widgets.e.1 */
    class YellowStripHelper implements Runnable {
        final /* synthetic */ String f11401a;
        final /* synthetic */ YellowStripHelper f11402b;

        YellowStripHelper(YellowStripHelper yellowStripHelper, String str) {
            this.f11402b = yellowStripHelper;
            this.f11401a = str;
        }

        public void run() {
            this.f11402b.m14863a(this.f11401a);
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.e.2 */
    class YellowStripHelper implements Runnable {
        final /* synthetic */ YellowStripHelper f11403a;

        YellowStripHelper(YellowStripHelper yellowStripHelper) {
            this.f11403a = yellowStripHelper;
        }

        public void run() {
            this.f11403a.f11416k = false;
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.e.3 */
    class YellowStripHelper implements Runnable {
        final /* synthetic */ YellowStripHelper f11404a;

        YellowStripHelper(YellowStripHelper yellowStripHelper) {
            this.f11404a = yellowStripHelper;
        }

        public void run() {
            this.f11404a.f11416k = false;
            this.f11404a.f11411f.setText(Trace.NULL);
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.e.4 */
    class YellowStripHelper implements Runnable {
        final /* synthetic */ YellowStripHelper f11405a;

        YellowStripHelper(YellowStripHelper yellowStripHelper) {
            this.f11405a = yellowStripHelper;
        }

        public void run() {
            this.f11405a.m14861a();
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.e.a */
    public interface YellowStripHelper {
        void m14855a(int i);

        void m14856b(int i);
    }

    static {
        f11406a = YellowStripHelper.class.getSimpleName();
    }

    public YellowStripHelper(FragmentManager fragmentManager, View view, int i) {
        this.f11407b = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        this.f11408c = 4000;
        this.f11415j = false;
        this.f11416k = false;
        this.f11417l = false;
        this.f11419n = true;
        this.f11420o = 0;
        this.f11422q = new YellowStripHelper(this);
        this.f11411f = (TextView) view;
        this.f11409d = view.getContext();
        this.f11414i = i;
        this.f11410e = fragmentManager;
        this.f11412g = new Handler();
        view.measure(0, 0);
        this.f11418m = view.getMeasuredHeight();
        view.animate().alpha(0.0f).setDuration(0).start();
    }

    public void m14864a(String str, int i) {
        if (!m14870c()) {
            m14865a(true);
            m14867b(str, i);
        }
    }

    public void m14867b(String str, int i) {
        OLog.m13313b("Showing", new Object[0]);
        new Handler().postDelayed(new YellowStripHelper(this, str), (long) (i + 100));
    }

    public void m14863a(String str) {
        int i = 0;
        OLog.m13313b("Same message reeating", new Object[0]);
        this.f11415j = true;
        if (this.f11416k) {
            OLog.m13313b("Already Animating", new Object[0]);
            return;
        }
        TextView textView = this.f11411f;
        if (textView.getText().toString().equalsIgnoreCase(str)) {
            OLog.m13313b("Same message reeating", new Object[0]);
            return;
        }
        m14860f();
        textView.setText(str);
        if (textView.getMeasuredHeight() == 0) {
            textView.measure(0, 0);
        }
        textView.setSelected(true);
        this.f11416k = true;
        Fragment findFragmentById = this.f11410e.findFragmentById(this.f11414i);
        if (findFragmentById != null) {
            float f;
            OLog.m13313b("Fragment is not null", new Object[0]);
            View view = findFragmentById.getView();
            if (view != null && (findFragmentById instanceof BookingCabCategoryFragment)) {
                view = view.findViewById(R.id.hsv);
            }
            if (this.f11411f.getMeasuredHeight() == 0) {
                this.f11411f.measure(0, 0);
            }
            if (view != null) {
                i = view.getTop() - textView.getMeasuredHeight();
            }
            ViewPropertyAnimator alpha = textView.animate().alpha(br.DEFAULT_BACKOFF_MULT);
            if (view == null) {
                f = 0.0f;
            } else {
                f = (float) (view.getTop() - (textView.getMeasuredHeight() / 2));
            }
            alpha.translationY(f).scaleY(0.0f).setDuration(0).start();
        } else {
            OLog.m13313b("fragment is null , mess ht : " + textView.getMeasuredHeight(), new Object[0]);
            i = m14859e() - textView.getMeasuredHeight();
            textView.animate().alpha(br.DEFAULT_BACKOFF_MULT).translationY((float) this.f11409d.getResources().getDisplayMetrics().heightPixels).scaleY(0.0f).setDuration(0).start();
        }
        if (this.f11413h != null) {
            this.f11413h.m14855a(this.f11418m);
        }
        textView.animate().translationY((float) i).scaleY(br.DEFAULT_BACKOFF_MULT).setDuration(500).start();
        new Handler().postDelayed(new YellowStripHelper(this), 500);
    }

    public void m14861a() {
        OLog.m13313b("Dismissing", new Object[0]);
        if (this.f11416k) {
            OLog.m13313b("Already Animating", new Object[0]);
        } else if (!this.f11415j) {
            OLog.m13313b("Yellow strip not visible", new Object[0]);
        } else if (this.f11417l) {
            OLog.m13313b("Should show no matter what", new Object[0]);
        } else {
            OLog.m13313b("DISMISSING YELLOW STRIP", new Object[0]);
            this.f11415j = false;
            this.f11416k = true;
            if (this.f11413h != null) {
                this.f11413h.m14856b(this.f11418m);
            }
            this.f11411f.animate().alpha(0.0f).setDuration(500).start();
            new Handler().postDelayed(new YellowStripHelper(this), 500);
        }
    }

    public void m14866b(String str) {
        if (this.f11411f.getText().toString().equalsIgnoreCase(str)) {
            OLog.m13313b("Not dismissing, Same Text", new Object[0]);
        } else {
            m14861a();
        }
    }

    public boolean m14869b() {
        return this.f11415j;
    }

    public boolean m14870c() {
        return this.f11419n;
    }

    public void m14865a(boolean z) {
        this.f11419n = z;
    }

    public void m14862a(View view) {
        this.f11421p = view;
    }

    private int m14859e() {
        if (this.f11421p == null) {
            OLog.m13313b("Default Position : 0", new Object[0]);
            return 0;
        }
        OLog.m13313b("Default Position : " + this.f11421p.getTop(), new Object[0]);
        return this.f11421p.getTop();
    }

    private void m14860f() {
        this.f11412g.removeCallbacks(this.f11422q);
        this.f11412g.postDelayed(this.f11422q, 4000);
    }

    public void m14868b(boolean z) {
        this.f11417l = z;
    }

    public boolean m14871d() {
        return this.f11417l;
    }
}
