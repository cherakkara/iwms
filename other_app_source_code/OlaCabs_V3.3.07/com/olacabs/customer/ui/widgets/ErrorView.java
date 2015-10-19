package com.olacabs.customer.ui.widgets;

import android.content.Context;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.olacabs.customer.R;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.p076d.br;
import org.apache.http.entity.ContentLengthStrategy;

/* renamed from: com.olacabs.customer.ui.widgets.a */
public class ErrorView extends PopupWindow {
    public static final String f11357a;
    private ErrorView f11358b;
    private View f11359c;
    private View f11360d;
    private int f11361e;

    /* renamed from: com.olacabs.customer.ui.widgets.a.c */
    public interface ErrorView {
        void m14014b();
    }

    /* renamed from: com.olacabs.customer.ui.widgets.a.1 */
    class ErrorView implements OnDismissListener {
        final /* synthetic */ View f11344a;
        final /* synthetic */ ErrorView f11345b;

        ErrorView(ErrorView errorView, View view) {
            this.f11345b = errorView;
            this.f11344a = view;
        }

        public void onDismiss() {
            OLog.m13313b("Dismissing...", new Object[0]);
            this.f11344a.setAlpha(br.DEFAULT_BACKOFF_MULT);
            if (this.f11345b.f11358b != null) {
                this.f11345b.f11358b.m14014b();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.a.2 */
    class ErrorView implements OnClickListener {
        final /* synthetic */ ErrorView f11346a;

        ErrorView(ErrorView errorView) {
            this.f11346a = errorView;
        }

        public void onClick(View view) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.a.3 */
    class ErrorView implements OnClickListener {
        final /* synthetic */ View f11347a;
        final /* synthetic */ ErrorView f11348b;

        ErrorView(ErrorView errorView, View view) {
            this.f11348b = errorView;
            this.f11347a = view;
        }

        public void onClick(View view) {
            OLog.m13313b("Dismissing...", new Object[0]);
            this.f11347a.setAlpha(br.DEFAULT_BACKOFF_MULT);
            if (this.f11348b.f11358b != null) {
                this.f11348b.f11358b.m14014b();
            }
            this.f11348b.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.a.a */
    public static class ErrorView {
        private final ErrorView f11349a;

        public ErrorView(Context context) {
            this.f11349a = new ErrorView();
            this.f11349a.f11350a = context;
        }

        public ErrorView m14824a(String str) {
            this.f11349a.f11353d = str;
            return this;
        }

        public ErrorView m14827b(String str) {
            this.f11349a.f11354e = str;
            return this;
        }

        public ErrorView m14823a(View view) {
            this.f11349a.f11351b = view;
            this.f11349a.f11355f = true;
            return this;
        }

        public ErrorView m14826b(View view) {
            this.f11349a.f11352c = view;
            return this;
        }

        public ErrorView m14825a() {
            int i = this.f11349a.f11350a.getResources().getDisplayMetrics().widthPixels - ((int) (32.0f * this.f11349a.f11350a.getResources().getDisplayMetrics().scaledDensity));
            View inflate = ((LayoutInflater) this.f11349a.f11350a.getSystemService("layout_inflater")).inflate(R.layout.view_error_view, (ViewGroup) this.f11349a.f11352c, false);
            if (this.f11349a.f11355f) {
                ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.view_container);
                View findViewById = inflate.findViewById(R.id.image_arrow_top);
                viewGroup.removeAllViews();
                viewGroup.addView(this.f11349a.f11351b);
                viewGroup.measure(0, 0);
                findViewById.measure(0, 0);
                return new ErrorView(inflate, this.f11349a.f11352c, i, viewGroup.getMeasuredHeight() + findViewById.getMeasuredHeight(), this.f11349a.f11356g, this.f11349a.f11355f);
            }
            this.f11349a.f11351b = inflate;
            View findViewById2 = this.f11349a.f11351b.findViewById(R.id.view_container);
            View findViewById3 = this.f11349a.f11351b.findViewById(R.id.image_arrow_top);
            TextView textView = (TextView) this.f11349a.f11351b.findViewById(R.id.item_title);
            TextView textView2 = (TextView) this.f11349a.f11351b.findViewById(R.id.item_desc);
            Button button = (Button) this.f11349a.f11351b.findViewById(R.id.button_ok);
            View findViewById4 = this.f11349a.f11351b.findViewById(R.id.item_divider);
            textView.setText(this.f11349a.f11353d);
            textView2.setText(this.f11349a.f11354e);
            findViewById3.measure(0, 0);
            button.measure(0, 0);
            findViewById4.measure(0, 0);
            return new ErrorView(this.f11349a.f11351b, this.f11349a.f11352c, i, (((((m14822a(textView, this.f11349a.f11353d, i) + 0) + m14822a(textView2, this.f11349a.f11354e, i)) + findViewById3.getMeasuredHeight()) + ((findViewById4.getMeasuredHeight() + findViewById4.getPaddingTop()) + findViewById4.getPaddingBottom())) + (button.getMeasuredHeight() + (button.getPaddingTop() + button.getPaddingBottom()))) + (findViewById2.getPaddingTop() + findViewById2.getPaddingBottom()), this.f11349a.f11356g, this.f11349a.f11355f);
        }

        private int m14822a(TextView textView, String str, int i) {
            float f = 0.0f;
            int paddingTop = textView.getPaddingTop() + textView.getPaddingBottom();
            if (str == null) {
                return paddingTop;
            }
            Layout staticLayout = new StaticLayout(str, textView.getPaint(), i, Alignment.ALIGN_NORMAL, br.DEFAULT_BACKOFF_MULT, 0.0f, true);
            for (int i2 = 0; i2 < staticLayout.getLineCount(); i2++) {
                f = Math.max(f, staticLayout.getLineWidth(i2));
            }
            return staticLayout.getHeight() + paddingTop;
        }
    }

    /* renamed from: com.olacabs.customer.ui.widgets.a.b */
    private static class ErrorView {
        public Context f11350a;
        public View f11351b;
        public View f11352c;
        public String f11353d;
        public String f11354e;
        public boolean f11355f;
        public int f11356g;

        private ErrorView() {
            this.f11356g = 1;
        }
    }

    static {
        f11357a = ErrorView.class.getSimpleName();
    }

    public ErrorView(View view, View view2, int i, int i2, int i3, boolean z) {
        super(view, i, -2);
        this.f11359c = view2;
        this.f11360d = view;
        this.f11361e = i3;
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(view.getContext().getResources().getDrawable(17170445));
        setOnDismissListener(new ErrorView(this, view2));
        view2.setOnClickListener(new ErrorView(this));
        if (!z) {
            this.f11360d.findViewById(R.id.button_ok).setOnClickListener(new ErrorView(this, view2));
        }
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
    }

    public void m14832a(View view, int i) {
        this.f11359c.setAlpha(0.4f);
        int i2 = view.getResources().getDisplayMetrics().widthPixels / 2;
        this.f11360d.measure(0, 0);
        i2 = (i2 - view.getLeft()) - (getWidth() / 2);
        switch (-1) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                m14829a();
                showAtLocation(view, 17, 0, 0);
            default:
                showAsDropDown(view, i2, 0);
                m14830a(-1);
        }
    }

    public void m14831a(View view) {
        m14832a(view, -1);
    }

    private void m14830a(int i) {
        int i2;
        int i3 = 0;
        this.f11360d.findViewById(R.id.image_arrow_top).setVisibility(isAboveAnchor() ? 8 : 0);
        View findViewById = this.f11360d.findViewById(R.id.image_arrow_bottom);
        if (!isAboveAnchor()) {
            i3 = 8;
        }
        findViewById.setVisibility(i3);
        if (isAboveAnchor()) {
            i2 = R.id.image_arrow_bottom;
        } else {
            i2 = R.id.image_arrow_top;
        }
        View findViewById2 = this.f11360d.findViewById(i2);
        LayoutParams layoutParams = (LayoutParams) findViewById2.getLayoutParams();
        switch (i) {
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                layoutParams.gravity = 17;
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                layoutParams.gravity = 3;
                break;
            case com.sothree.slidinguppanel.p086a.R.R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                layoutParams.gravity = 5;
                break;
        }
        findViewById2.setLayoutParams(layoutParams);
    }

    private void m14829a() {
        this.f11360d.findViewById(R.id.image_arrow_top).setVisibility(8);
        this.f11360d.findViewById(R.id.image_arrow_bottom).setVisibility(8);
    }

    public void m14833a(ErrorView errorView) {
        this.f11358b = errorView;
    }
}
