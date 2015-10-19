package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.ui.BookingCabCategoryFragment;
import org.apache.http.HttpStatus;

/* renamed from: com.olacabs.customer.ui.widgets.b */
public class EtaStripHelper {
    public static final String f11368a;
    public final int f11369b;
    private Context f11370c;
    private View f11371d;
    private FragmentManager f11372e;
    private EtaStripHelper f11373f;
    private int f11374g;
    private String f11375h;
    private boolean f11376i;
    private boolean f11377j;
    private boolean f11378k;
    private int f11379l;

    /* renamed from: com.olacabs.customer.ui.widgets.b.a */
    public interface EtaStripHelper {
        void m14261a(int i);

        void m14262b(int i);
    }

    /* renamed from: com.olacabs.customer.ui.widgets.b.1 */
    class EtaStripHelper implements Runnable {
        final /* synthetic */ EtaStripHelper f11362a;

        EtaStripHelper(EtaStripHelper etaStripHelper) {
            this.f11362a = etaStripHelper;
        }

        public void run() {
            this.f11362a.f11377j = false;
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.b.2 */
    class EtaStripHelper implements Runnable {
        final /* synthetic */ String f11363a;
        final /* synthetic */ String f11364b;
        final /* synthetic */ EtaStripHelper f11365c;

        EtaStripHelper(EtaStripHelper etaStripHelper, String str, String str2) {
            this.f11365c = etaStripHelper;
            this.f11363a = str;
            this.f11364b = str2;
        }

        public void run() {
            this.f11365c.m14841a(this.f11363a, this.f11364b);
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.b.3 */
    class EtaStripHelper implements Runnable {
        final /* synthetic */ EtaStripHelper f11366a;

        EtaStripHelper(EtaStripHelper etaStripHelper) {
            this.f11366a = etaStripHelper;
        }

        public void run() {
            this.f11366a.f11377j = false;
            if (this.f11366a.f11373f != null) {
                this.f11366a.f11373f.m14262b(this.f11366a.f11379l);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.b.4 */
    class EtaStripHelper implements Runnable {
        final /* synthetic */ EtaStripHelper f11367a;

        EtaStripHelper(EtaStripHelper etaStripHelper) {
            this.f11367a = etaStripHelper;
        }

        public void run() {
            this.f11367a.f11377j = false;
        }
    }

    static {
        f11368a = EtaStripHelper.class.getSimpleName();
    }

    public EtaStripHelper(FragmentManager fragmentManager, View view, int i) {
        this.f11369b = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        this.f11376i = false;
        this.f11377j = false;
        this.f11378k = false;
        this.f11371d = view;
        this.f11374g = i;
        this.f11372e = fragmentManager;
        this.f11370c = view.getContext();
        view.measure(0, 0);
        this.f11379l = view.getMeasuredHeight();
        view.animate().alpha(0.0f).setDuration(0).start();
        view.setClickable(false);
    }

    public void m14841a(String str, String str2) {
        OLog.m13313b("Same message reeating", new Object[0]);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        try {
            CharSequence spannableString = new SpannableString("ETA of your booked " + str2 + ": ");
            spannableString.setSpan(new ForegroundColorSpan(this.f11370c.getResources().getColor(R.color.ola_black)), 0, spannableString.length(), 0);
            spannableStringBuilder.append(spannableString);
            spannableString = new SpannableString(Integer.parseInt(str) + " mins");
            spannableString.setSpan(new ForegroundColorSpan(this.f11370c.getResources().getColor(R.color.ola_red_dark)), 0, spannableString.length(), 0);
            spannableStringBuilder.append(spannableString);
        } catch (NumberFormatException e) {
            spannableStringBuilder.clear();
            spannableStringBuilder.append(str);
        }
        if (this.f11371d.getMeasuredHeight() == 0) {
            this.f11371d.measure(0, 0);
        }
        if (this.f11377j) {
            OLog.m13313b("Already Animating", new Object[0]);
            return;
        }
        if (str != null) {
            ((TextView) this.f11371d.findViewById(R.id.item_eta)).setText(spannableStringBuilder, BufferType.SPANNABLE);
        }
        if (!m14843c()) {
            this.f11371d.setClickable(true);
            this.f11377j = true;
            this.f11376i = true;
            Fragment findFragmentById = this.f11372e.findFragmentById(this.f11374g);
            if (findFragmentById != null) {
                OLog.m13313b("Fragment is not null", new Object[0]);
                View view = findFragmentById.getView();
                if (view != null && (findFragmentById instanceof BookingCabCategoryFragment)) {
                    view = view.findViewById(R.id.hsv);
                }
                if (view == null) {
                    this.f11376i = false;
                    this.f11377j = false;
                    m14840a(str, 100, "cab");
                    return;
                }
                if (this.f11371d.getMeasuredHeight() == 0) {
                    this.f11371d.measure(0, 0);
                }
                int top = view.getTop() - this.f11371d.getMeasuredHeight();
                this.f11371d.animate().alpha(br.DEFAULT_BACKOFF_MULT).translationY((float) (view.getTop() - (this.f11371d.getMeasuredHeight() / 2))).scaleY(0.0f).setDuration(0).start();
                if (this.f11373f != null) {
                    this.f11373f.m14261a(this.f11379l);
                }
                this.f11371d.animate().translationY((float) top).scaleY(br.DEFAULT_BACKOFF_MULT).setDuration(500).start();
                new Handler().postDelayed(new EtaStripHelper(this), 500);
            }
        }
    }

    public void m14840a(String str, int i, String str2) {
        OLog.m13313b("Showing", new Object[0]);
        new Handler().postDelayed(new EtaStripHelper(this, str, str2), (long) (i + 100));
    }

    public void m14837a() {
        OLog.m13313b("Dismissing", new Object[0]);
        if (this.f11377j) {
            OLog.m13313b("Already Animating", new Object[0]);
        } else if (this.f11376i) {
            OLog.m13313b("DISMISSING YELLOW STRIP", new Object[0]);
            this.f11376i = false;
            this.f11371d.setClickable(false);
            this.f11377j = true;
            this.f11371d.animate().alpha(0.0f).setDuration(500).start();
            new Handler().postDelayed(new EtaStripHelper(this), 500);
        } else {
            OLog.m13313b("Yellow strip not visible", new Object[0]);
        }
    }

    public void m14842b() {
        if (this.f11376i) {
            OLog.m13313b("DISMISSING YELLOW STRIP", new Object[0]);
            this.f11376i = false;
            this.f11371d.setClickable(false);
            this.f11377j = true;
            this.f11371d.animate().alpha(0.0f).setDuration(500).start();
            new Handler().postDelayed(new EtaStripHelper(this), 500);
            return;
        }
        OLog.m13313b("Yellow strip not visible", new Object[0]);
    }

    public boolean m14843c() {
        OLog.m13313b("Is SHowing : " + this.f11376i, new Object[0]);
        return this.f11376i;
    }

    public void m14839a(String str) {
        this.f11375h = str;
    }

    public String m14844d() {
        return this.f11375h;
    }

    public void m14838a(EtaStripHelper etaStripHelper) {
        this.f11373f = etaStripHelper;
    }
}
