package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.p066c.TypeToken;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.bq;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.p076d.dr;
import com.olacabs.customer.ui.PayuRechargeFragment.PayuRechargeFragment;
import com.olacabs.customer.ui.w.AnonymousClass19;
import com.olacabs.customer.ui.w.AnonymousClass21;
import com.olacabs.customer.ui.w.AnonymousClass22;
import com.olacabs.customer.ui.w.AnonymousClass24;
import com.olacabs.customer.ui.w.AnonymousClass25;
import com.olacabs.customer.ui.w.AnonymousClass26;
import com.olacabs.customer.ui.w.AnonymousClass32;
import com.olacabs.customer.ui.widgets.FontButton;
import com.olacabs.customer.ui.widgets.FontTextView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import com.payu.sdk.C0913h;
import com.payu.sdk.C0922a;
import com.payu.sdk.C0923b;
import com.payu.sdk.C0925d;
import com.payu.sdk.C0927e;
import com.payu.sdk.C0927e.C0926a;
import com.payu.sdk.C0930g;
import com.payu.sdk.C0930g.C0929a;
import com.payu.sdk.p082b.C0912a;
import com.sothree.slidinguppanel.p086a.R.R;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

/* renamed from: com.olacabs.customer.ui.w */
public class PayuRechargeFragment extends C0912a implements C0913h {
    public static final String f11255a;
    private static final JoinPoint ak = null;
    private static final JoinPoint al = null;
    private static final JoinPoint am = null;
    private TextWatcher f11256A;
    private TextWatcher f11257B;
    private ArrayList<String> f11258C;
    private EditText f11259D;
    private EditText f11260E;
    private EditText f11261F;
    private EditText f11262G;
    private EditText f11263H;
    private EditText f11264I;
    private EditText f11265J;
    private EditText f11266K;
    private EditText f11267L;
    private EditText f11268M;
    private FontTextView f11269N;
    private String f11270O;
    private OlaApp f11271P;
    private ArrayList<bq> f11272Q;
    private ArrayList<dr> f11273R;
    private ScrollView f11274S;
    private ImageButton f11275T;
    private TextView f11276U;
    private PayuRechargeFragment f11277V;
    private PayuRechargeFragment f11278W;
    private ProgressDialog f11279X;
    private String f11280Y;
    private String f11281Z;
    private SharedPreferences aa;
    private TextView ab;
    private TextView ac;
    private String ad;
    private OnCheckedChangeListener ae;
    private EditText af;
    private EditText ag;
    private EditText ah;
    private EditText ai;
    private EditText aj;
    C0929a f11282b;
    private LinearLayout f11283c;
    private LinearLayout f11284d;
    private LinearLayout f11285e;
    private LinearLayout f11286f;
    private View f11287g;
    private View f11288h;
    private View f11289i;
    private DataManager f11290j;
    private FontButton f11291k;
    private RechargePayUActivity f11292l;
    private int f11293m;
    private RadioButton f11294n;
    private RadioButton f11295o;
    private RadioButton f11296p;
    private float f11297q;
    private View f11298r;
    private CheckBox f11299s;
    private CheckBox f11300t;
    private Typeface f11301u;
    private View f11302v;
    private FontTextView f11303w;
    private TextWatcher f11304x;
    private TextWatcher f11305y;
    private TextWatcher f11306z;

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.19 */
    class AnonymousClass19 implements OnClickListener {
        final /* synthetic */ PayuRechargeFragment f11205a;
        final /* synthetic */ PayuRechargeFragment f11206b;

        AnonymousClass19(PayuRechargeFragment payuRechargeFragment, PayuRechargeFragment payuRechargeFragment2) {
            this.f11206b = payuRechargeFragment;
            this.f11205a = payuRechargeFragment2;
        }

        public void onClick(View view) {
            if (this.f11205a.f11245c.getVisibility() == 8) {
                this.f11206b.m14730b(this.f11205a);
            } else {
                this.f11206b.m14738c(this.f11205a);
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.1 */
    class PayuRechargeFragment implements OnClickListener {
        final /* synthetic */ PayuRechargeFragment f11207a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11207a = payuRechargeFragment;
        }

        public void onClick(View view) {
            this.f11207a.f11292l.onBackPressed();
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.21 */
    class AnonymousClass21 implements DialogInterface.OnClickListener {
        final /* synthetic */ ArrayList f11209a;
        final /* synthetic */ PayuRechargeFragment f11210b;

        AnonymousClass21(PayuRechargeFragment payuRechargeFragment, ArrayList arrayList) {
            this.f11210b = payuRechargeFragment;
            this.f11209a = arrayList;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.f11209a != null) {
                this.f11210b.f11303w.setText(((bq) this.f11209a.get(i)).getTitle());
                this.f11210b.f11303w.setTag(((bq) this.f11209a.get(i)).getCode());
            }
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.22 */
    class AnonymousClass22 implements OnClickListener {
        final /* synthetic */ AlertDialog f11211a;
        final /* synthetic */ PayuRechargeFragment f11212b;
        final /* synthetic */ PayuRechargeFragment f11213c;

        AnonymousClass22(PayuRechargeFragment payuRechargeFragment, AlertDialog alertDialog, PayuRechargeFragment payuRechargeFragment2) {
            this.f11213c = payuRechargeFragment;
            this.f11211a = alertDialog;
            this.f11212b = payuRechargeFragment2;
        }

        public void onClick(View view) {
            this.f11211a.cancel();
            this.f11213c.m14743d(this.f11212b);
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.24 */
    class AnonymousClass24 implements OnClickListener {
        final /* synthetic */ AlertDialog f11215a;
        final /* synthetic */ PayuRechargeFragment f11216b;

        AnonymousClass24(PayuRechargeFragment payuRechargeFragment, AlertDialog alertDialog) {
            this.f11216b = payuRechargeFragment;
            this.f11215a = alertDialog;
        }

        public void onClick(View view) {
            this.f11215a.dismiss();
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.25 */
    class AnonymousClass25 implements OnClickListener {
        final /* synthetic */ AlertDialog f11217a;
        final /* synthetic */ CompoundButton f11218b;
        final /* synthetic */ PayuRechargeFragment f11219c;

        AnonymousClass25(PayuRechargeFragment payuRechargeFragment, AlertDialog alertDialog, CompoundButton compoundButton) {
            this.f11219c = payuRechargeFragment;
            this.f11217a = alertDialog;
            this.f11218b = compoundButton;
        }

        public void onClick(View view) {
            this.f11217a.cancel();
            this.f11218b.setChecked(true);
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.26 */
    class AnonymousClass26 implements OnClickListener {
        final /* synthetic */ AlertDialog f11220a;
        final /* synthetic */ PayuRechargeFragment f11221b;

        AnonymousClass26(PayuRechargeFragment payuRechargeFragment, AlertDialog alertDialog) {
            this.f11221b = payuRechargeFragment;
            this.f11220a = alertDialog;
        }

        public void onClick(View view) {
            this.f11220a.dismiss();
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.2 */
    class PayuRechargeFragment implements TextWatcher {
        final /* synthetic */ PayuRechargeFragment f11225a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11225a = payuRechargeFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str = Trace.NULL;
            View view = null;
            switch (this.f11225a.f11293m) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    str = this.f11225a.f11264I.getText().toString();
                    view = this.f11225a.f11287g;
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = this.f11225a.f11259D.getText().toString();
                    view = this.f11225a.f11288h;
                    break;
            }
            if (str.length() < 16) {
                this.f11225a.m14768o();
            } else if (this.f11225a.m14728a(str, view)) {
                this.f11225a.m14768o();
            } else {
                this.f11225a.f11269N.setVisibility(0);
                this.f11225a.f11269N.setText(this.f11225a.getString(com.olacabs.customer.R.string.text_invalid_card));
            }
        }
    }

    /* compiled from: PayuRechargeFragment */
    /* renamed from: com.olacabs.customer.ui.w.32 */
    class AnonymousClass32 implements OnClickListener {
        final /* synthetic */ PayuRechargeFragment f11228a;
        final /* synthetic */ PayuRechargeFragment f11229b;

        AnonymousClass32(PayuRechargeFragment payuRechargeFragment, PayuRechargeFragment payuRechargeFragment2) {
            this.f11229b = payuRechargeFragment;
            this.f11228a = payuRechargeFragment2;
        }

        public void onClick(View view) {
            OLog.m13313b("Pay u deleting view", new Object[0]);
            this.f11229b.f11278W = this.f11228a;
            this.f11229b.m14720a("Delete", "Are you sure you want to delete the card", this.f11228a);
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.3 */
    class PayuRechargeFragment implements TextWatcher {
        final /* synthetic */ PayuRechargeFragment f11230a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11230a = payuRechargeFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str = Trace.NULL;
            switch (this.f11230a.f11293m) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    str = this.f11230a.f11268M.getText().toString();
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = this.f11230a.f11263H.getText().toString();
                    break;
            }
            if (str.length() == 0) {
                this.f11230a.m14768o();
            } else if (this.f11230a.m14745d(str)) {
                this.f11230a.m14768o();
            } else {
                this.f11230a.f11269N.setVisibility(0);
                this.f11230a.f11269N.setText("Please enter a valid name");
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.4 */
    class PayuRechargeFragment implements TextWatcher {
        final /* synthetic */ PayuRechargeFragment f11231a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11231a = payuRechargeFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str = Trace.NULL;
            String str2 = Trace.NULL;
            switch (this.f11231a.f11293m) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    str = this.f11231a.f11266K.getText().toString();
                    str2 = this.f11231a.f11265J.getText().toString();
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = this.f11231a.f11261F.getText().toString();
                    str2 = this.f11231a.f11260E.getText().toString();
                    break;
            }
            if (str2.length() < 2) {
                this.f11231a.m14768o();
            } else if (str2.length() < 1) {
            } else {
                if (Integer.parseInt(str2) > 12) {
                    this.f11231a.f11269N.setVisibility(0);
                    this.f11231a.f11269N.setText(this.f11231a.getString(com.olacabs.customer.R.string.text_invalid_month));
                    return;
                }
                this.f11231a.m14768o();
                if (str.length() >= 1 && str2.length() >= 1) {
                    if (this.f11231a.m14723a(Integer.parseInt(str), Integer.parseInt(str2))) {
                        this.f11231a.m14768o();
                        return;
                    }
                    this.f11231a.f11269N.setVisibility(0);
                    this.f11231a.f11269N.setText(this.f11231a.getString(com.olacabs.customer.R.string.text_invalid_year));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.5 */
    class PayuRechargeFragment implements TextWatcher {
        final /* synthetic */ PayuRechargeFragment f11232a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11232a = payuRechargeFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str = Trace.NULL;
            String str2 = Trace.NULL;
            switch (this.f11232a.f11293m) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    str = this.f11232a.f11266K.getText().toString();
                    str2 = this.f11232a.f11265J.getText().toString();
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = this.f11232a.f11261F.getText().toString();
                    str2 = this.f11232a.f11260E.getText().toString();
                    break;
            }
            if (str.length() < 4) {
                this.f11232a.m14768o();
            } else if (str.length() < 4 || str2.length() < 1) {
                if (str.length() < 4) {
                    return;
                }
                if (this.f11232a.m14722a(Integer.parseInt(str))) {
                    this.f11232a.m14768o();
                    return;
                }
                this.f11232a.f11269N.setVisibility(0);
                this.f11232a.f11269N.setText(this.f11232a.getString(com.olacabs.customer.R.string.text_invalid_year));
            } else if (this.f11232a.m14723a(Integer.parseInt(str), Integer.parseInt(str2))) {
                this.f11232a.m14768o();
            } else {
                this.f11232a.f11269N.setVisibility(0);
                this.f11232a.f11269N.setText(this.f11232a.getString(com.olacabs.customer.R.string.text_invalid_year));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.6 */
    class PayuRechargeFragment implements TextWatcher {
        final /* synthetic */ PayuRechargeFragment f11233a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11233a = payuRechargeFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str = Trace.NULL;
            String str2 = Trace.NULL;
            switch (this.f11233a.f11293m) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    str = this.f11233a.f11267L.getText().toString();
                    str2 = this.f11233a.f11264I.getText().toString();
                    break;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    str = this.f11233a.f11262G.getText().toString();
                    str2 = this.f11233a.f11259D.getText().toString();
                    break;
            }
            if (str.length() < 3) {
                this.f11233a.m14768o();
            } else if (this.f11233a.m14729a(str, str2)) {
                this.f11233a.m14768o();
            } else {
                this.f11233a.f11269N.setVisibility(0);
                this.f11233a.f11269N.setText(this.f11233a.getString(com.olacabs.customer.R.string.text_invalid_cvv));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.7 */
    class PayuRechargeFragment implements OnFocusChangeListener {
        final /* synthetic */ PayuRechargeFragment f11234a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11234a = payuRechargeFragment;
        }

        public void onFocusChange(View view, boolean z) {
            if (!z) {
                if (this.f11234a.m14728a(this.f11234a.f11264I.getText().toString(), this.f11234a.f11287g)) {
                    this.f11234a.m14768o();
                    return;
                }
                this.f11234a.f11269N.setVisibility(0);
                this.f11234a.f11269N.setText(this.f11234a.getString(com.olacabs.customer.R.string.text_invalid_card));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.8 */
    class PayuRechargeFragment implements OnFocusChangeListener {
        final /* synthetic */ PayuRechargeFragment f11235a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11235a = payuRechargeFragment;
        }

        public void onFocusChange(View view, boolean z) {
            if (!z) {
                if (this.f11235a.m14728a(this.f11235a.f11259D.getText().toString(), this.f11235a.f11287g)) {
                    this.f11235a.m14768o();
                    return;
                }
                this.f11235a.f11269N.setVisibility(0);
                this.f11235a.f11269N.setText(this.f11235a.getString(com.olacabs.customer.R.string.text_invalid_card));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.9 */
    class PayuRechargeFragment implements OnFocusChangeListener {
        final /* synthetic */ PayuRechargeFragment f11236a;

        PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment) {
            this.f11236a = payuRechargeFragment;
        }

        public void onFocusChange(View view, boolean z) {
            if (!z) {
                if (this.f11236a.m14745d(this.f11236a.f11264I.getText().toString())) {
                    this.f11236a.m14768o();
                    return;
                }
                this.f11236a.f11269N.setVisibility(0);
                this.f11236a.f11269N.setText(this.f11236a.getString(com.olacabs.customer.R.string.text_invalid_card));
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.a */
    private enum PayuRechargeFragment {
        STORE_CARD,
        DELETE_CARD
    }

    /* renamed from: com.olacabs.customer.ui.w.b */
    private class PayuRechargeFragment extends BaseAdapter {
        final /* synthetic */ PayuRechargeFragment f11240a;
        private ArrayList<bq> f11241b;
        private LayoutInflater f11242c;

        public PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment, Context context, ArrayList<bq> arrayList) {
            this.f11240a = payuRechargeFragment;
            this.f11241b = arrayList;
            this.f11242c = LayoutInflater.from(payuRechargeFragment.f11292l);
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getCount() {
            return this.f11241b != null ? this.f11241b.size() : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f11242c.inflate(17367043, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(16908308);
            if (this.f11241b != null) {
                textView.setText(((bq) this.f11241b.get(i)).getTitle());
            }
            return view;
        }
    }

    /* renamed from: com.olacabs.customer.ui.w.c */
    private class PayuRechargeFragment {
        View f11243a;
        View f11244b;
        View f11245c;
        EditText f11246d;
        RadioButton f11247e;
        FontTextView f11248f;
        FontTextView f11249g;
        ImageView f11250h;
        FontTextView f11251i;
        dr f11252j;
        FontTextView f11253k;
        final /* synthetic */ PayuRechargeFragment f11254l;

        public PayuRechargeFragment(PayuRechargeFragment payuRechargeFragment, View view) {
            this.f11254l = payuRechargeFragment;
            this.f11243a = view;
            this.f11251i = (FontTextView) view.findViewById(com.olacabs.customer.R.id.bank_name);
            this.f11250h = (ImageView) view.findViewById(com.olacabs.customer.R.id.card_type_image);
            this.f11244b = view.findViewById(com.olacabs.customer.R.id.saved_card_header);
            this.f11245c = view.findViewById(com.olacabs.customer.R.id.saved_card_child);
            this.f11246d = (EditText) view.findViewById(com.olacabs.customer.R.id.cvvEditText);
            this.f11247e = (RadioButton) view.findViewById(com.olacabs.customer.R.id.radio_selectable);
            this.f11248f = (FontTextView) view.findViewById(com.olacabs.customer.R.id.delete_card);
            this.f11249g = (FontTextView) view.findViewById(com.olacabs.customer.R.id.card_number);
            this.f11253k = (FontTextView) view.findViewById(com.olacabs.customer.R.id.card_holder_name);
        }

        public void m14696a(dr drVar) {
            this.f11252j = drVar;
        }

        public void m14695a() {
            this.f11249g.setText(this.f11252j.getCardNo());
            this.f11251i.setText(this.f11252j.getCardBrand());
            this.f11253k.setText(this.f11252j.getNameOnCard());
            m14694a(this.f11252j.getCardBrand());
        }

        public String m14697b() {
            return this.f11252j.getCardToken();
        }

        public dr m14698c() {
            return this.f11252j;
        }

        private void m14694a(String str) {
            if (str.equalsIgnoreCase("MASTERCARD")) {
                m14693a((int) com.olacabs.customer.R.drawable.master);
            } else if (str.equalsIgnoreCase("VISA")) {
                m14693a((int) com.olacabs.customer.R.drawable.visa);
            } else if (str.equalsIgnoreCase("MAESTRO")) {
                m14693a((int) com.olacabs.customer.R.drawable.maestro);
            } else if (str.equalsIgnoreCase("AMEX")) {
                m14693a((int) com.olacabs.customer.R.drawable.amex);
            }
        }

        private void m14693a(int i) {
            this.f11250h.setImageResource(i);
        }
    }

    private static void m14783v() {
        Factory factory = new Factory("PayuRechargeFragment.java", PayuRechargeFragment.class);
        ak = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.PayuRechargeFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 137);
        al = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.PayuRechargeFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 383);
        am = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.PayuRechargeFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 650);
    }

    static {
        PayuRechargeFragment.m14783v();
        f11255a = PayuRechargeFragment.class.getSimpleName();
    }

    public PayuRechargeFragment() {
        this.f11293m = -1;
        C0930g c0930g = new C0930g();
        c0930g.getClass();
        this.f11282b = new C0929a(c0930g);
        this.f11258C = new ArrayList();
        this.f11278W = null;
        this.ae = new OnCheckedChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11227a;

            {
                this.f11227a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z && !this.f11227a.aa.getBoolean(Constants.PREF_DO_NOT_SHOW_UNSAVE_POPUP, false)) {
                    this.f11227a.aa.edit().putBoolean(Constants.PREF_DO_NOT_SHOW_UNSAVE_POPUP, true).apply();
                    this.f11227a.m14713a(compoundButton);
                }
            }
        };
    }

    public static PayuRechargeFragment m14711a(float f, String str, String str2, String str3) {
        PayuRechargeFragment payuRechargeFragment = new PayuRechargeFragment();
        Bundle bundle = new Bundle();
        bundle.putFloat(Constants.ARG_AMOUNT, f);
        bundle.putString(Constants.ARG_USER_CREDENTIALS, str);
        bundle.putString(Constants.EXTRA_OLA_OFFER_TEXT, str2);
        bundle.putString(Constants.EXTRA_OLA_DENOMINATION, str3);
        payuRechargeFragment.setArguments(bundle);
        return payuRechargeFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(ak, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        this.f11298r = layoutInflater.inflate(com.olacabs.customer.R.layout.activity_recharge_amount, viewGroup, false);
        this.f11275T = (ImageButton) this.f11298r.findViewById(com.olacabs.customer.R.id.button_back);
        this.f11275T.setOnClickListener(new PayuRechargeFragment(this));
        this.f11283c = (LinearLayout) this.f11298r.findViewById(com.olacabs.customer.R.id.debitCard);
        this.f11284d = (LinearLayout) this.f11298r.findViewById(com.olacabs.customer.R.id.creditCard);
        this.f11285e = (LinearLayout) this.f11298r.findViewById(com.olacabs.customer.R.id.net_banking_header);
        this.f11287g = this.f11298r.findViewById(com.olacabs.customer.R.id.debit_card_details);
        this.f11288h = this.f11298r.findViewById(com.olacabs.customer.R.id.credit_card_details);
        this.f11289i = this.f11298r.findViewById(com.olacabs.customer.R.id.net_banking_details);
        this.f11303w = (FontTextView) this.f11289i.findViewById(com.olacabs.customer.R.id.selectBankText);
        this.f11264I = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cardNumberEditText);
        this.f11265J = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.monthEditText);
        this.f11268M = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cardNameEditText);
        this.f11267L = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cvvEditText);
        this.f11266K = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.yearEditText);
        this.f11259D = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cardNumberEditText);
        this.f11260E = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.monthEditText);
        this.f11263H = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cardNameEditText);
        this.f11262G = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cvvEditText);
        this.f11261F = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.yearEditText);
        this.ab = (TextView) this.f11298r.findViewById(com.olacabs.customer.R.id.ola_money_text_view);
        this.ab.setText("Rs " + this.f11281Z);
        this.ac = (TextView) this.f11298r.findViewById(com.olacabs.customer.R.id.applicable_offer_text_view);
        this.ac.setText(this.f11280Y);
        this.f11302v = this.f11298r.findViewById(com.olacabs.customer.R.id.saved_divider);
        this.f11274S = (ScrollView) this.f11298r.findViewById(com.olacabs.customer.R.id.scroll_container);
        this.f11269N = (FontTextView) this.f11298r.findViewById(com.olacabs.customer.R.id.error_text);
        this.f11303w.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11198a;

            {
                this.f11198a = r1;
            }

            public void onClick(View view) {
                this.f11198a.m14768o();
                this.f11198a.m14721a(this.f11198a.f11272Q);
            }
        });
        this.f11299s = (CheckBox) this.f11287g.findViewById(com.olacabs.customer.R.id.promocode_checkbox);
        this.f11300t = (CheckBox) this.f11288h.findViewById(com.olacabs.customer.R.id.promocode_checkbox);
        this.f11299s.setOnCheckedChangeListener(this.ae);
        this.f11300t.setOnCheckedChangeListener(this.ae);
        this.f11294n = (RadioButton) this.f11298r.findViewById(com.olacabs.customer.R.id.debit_card_button);
        this.f11295o = (RadioButton) this.f11298r.findViewById(com.olacabs.customer.R.id.credit_card_button);
        this.f11296p = (RadioButton) this.f11298r.findViewById(com.olacabs.customer.R.id.net_banking_button);
        this.f11294n.setTypeface(this.f11301u);
        this.f11295o.setTypeface(this.f11301u);
        this.f11296p.setTypeface(this.f11301u);
        this.f11291k = (FontButton) this.f11298r.findViewById(com.olacabs.customer.R.id.button_pay_now);
        this.f11276U = (TextView) this.f11298r.findViewById(com.olacabs.customer.R.id.termConditionText);
        this.f11276U.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11214a;

            {
                this.f11214a = r1;
            }

            public void onClick(View view) {
                this.f11214a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.olacabs.com/info/faqs#termsAndConditions")));
            }
        });
        this.f11286f = (LinearLayout) this.f11298r.findViewById(com.olacabs.customer.R.id.savedCardsLayout);
        m14753g();
        this.f11283c.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11222a;

            {
                this.f11222a = r1;
            }

            public void onClick(View view) {
                if (this.f11222a.f11287g.getVisibility() == 8) {
                    this.f11222a.f11288h.setVisibility(8);
                    this.f11222a.f11289i.setVisibility(8);
                    this.f11222a.f11287g.setVisibility(0);
                    this.f11222a.f11293m = 0;
                    this.f11222a.f11294n.setChecked(true);
                    this.f11222a.f11295o.setChecked(false);
                    this.f11222a.f11296p.setChecked(false);
                    this.f11222a.m14771p();
                    this.f11222a.m14755h();
                    return;
                }
                this.f11222a.m14712a(this.f11222a.f11292l.getCurrentFocus());
                this.f11222a.f11287g.setVisibility(8);
                this.f11222a.f11294n.setChecked(false);
                this.f11222a.m14768o();
                this.f11222a.m14775r();
                this.f11222a.m14750f();
            }
        });
        this.f11284d.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11223a;

            {
                this.f11223a = r1;
            }

            public void onClick(View view) {
                if (this.f11223a.f11288h.getVisibility() == 8) {
                    this.f11223a.f11287g.setVisibility(8);
                    this.f11223a.f11289i.setVisibility(8);
                    this.f11223a.f11288h.setVisibility(0);
                    this.f11223a.f11293m = 1;
                    this.f11223a.f11295o.setChecked(true);
                    this.f11223a.f11294n.setChecked(false);
                    this.f11223a.f11296p.setChecked(false);
                    this.f11223a.m14773q();
                    this.f11223a.m14755h();
                    return;
                }
                this.f11223a.m14712a(this.f11223a.f11292l.getCurrentFocus());
                this.f11223a.f11288h.setVisibility(8);
                this.f11223a.f11295o.setChecked(false);
                this.f11223a.m14768o();
                this.f11223a.m14777s();
                this.f11223a.m14750f();
            }
        });
        this.f11285e.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11224a;

            {
                this.f11224a = r1;
            }

            public void onClick(View view) {
                if (this.f11224a.f11289i.getVisibility() == 8) {
                    this.f11224a.m14712a(this.f11224a.f11292l.getCurrentFocus());
                    this.f11224a.f11288h.setVisibility(8);
                    this.f11224a.f11287g.setVisibility(8);
                    this.f11224a.f11289i.setVisibility(0);
                    this.f11224a.f11293m = 2;
                    this.f11224a.f11296p.setChecked(true);
                    this.f11224a.f11295o.setChecked(false);
                    this.f11224a.f11294n.setChecked(false);
                    this.f11224a.m14755h();
                    this.f11224a.f11274S.scrollTo(0, this.f11224a.f11289i.getBottom());
                    return;
                }
                this.f11224a.f11289i.setVisibility(8);
                this.f11224a.f11296p.setChecked(false);
                this.f11224a.m14768o();
                this.f11224a.m14750f();
            }
        });
        this.f11291k.setOnClickListener(new OnClickListener() {
            final /* synthetic */ PayuRechargeFragment f11226a;

            {
                this.f11226a = r1;
            }

            public void onClick(View view) {
                if (this.f11226a.m14760k() == -1 && this.f11226a.f11293m == -1) {
                    this.f11226a.f11269N.setVisibility(0);
                    this.f11226a.f11269N.setText("Nothing is selected");
                    return;
                }
                this.f11226a.m14768o();
                if (this.f11226a.m14760k() != -1) {
                    this.f11226a.m14764m();
                }
                if (this.f11226a.f11293m != -1) {
                    this.f11226a.m14766n();
                }
            }
        });
        this.aa = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return this.f11298r;
    }

    private void m14737c() {
        getArguments().putString("store_card", "store_card");
        OLog.m13313b("putting in the args", new Object[0]);
    }

    private void m14742d() {
        getArguments().remove("store_card");
        OLog.m13313b("removing from the args", new Object[0]);
    }

    private void m14733b(String str) {
        if (str.equalsIgnoreCase("CC") && this.f11300t.isChecked()) {
            m14737c();
        } else if (str.equalsIgnoreCase("DC") && this.f11299s.isChecked()) {
            m14737c();
        } else {
            m14742d();
        }
    }

    private void m14715a(PayuRechargeFragment payuRechargeFragment) {
        if (payuRechargeFragment.f11248f.getVisibility() == 8) {
            payuRechargeFragment.f11248f.setVisibility(0);
            payuRechargeFragment.f11248f.setOnClickListener(new AnonymousClass32(this, payuRechargeFragment));
        } else if (payuRechargeFragment.f11248f.getVisibility() == 0) {
            payuRechargeFragment.f11248f.setVisibility(8);
            payuRechargeFragment.f11248f.setClickable(false);
        }
    }

    public void onCreate(Bundle bundle) {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(al, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f11297q = getArguments().getFloat(Constants.ARG_AMOUNT);
        this.f11270O = getArguments().getString(Constants.ARG_USER_CREDENTIALS);
        this.f11280Y = getArguments().getString(Constants.EXTRA_OLA_OFFER_TEXT);
        this.f11281Z = getArguments().getString(Constants.EXTRA_OLA_DENOMINATION);
        this.f11292l = (RechargePayUActivity) getActivity();
        this.f11271P = (OlaApp) this.f11292l.getApplication();
        this.f11290j = this.f11271P.m12878a();
        this.f11301u = Typeface.createFromAsset(this.f11292l.getAssets(), "OpenSans-Regular.ttf");
        this.f11304x = new PayuRechargeFragment(this);
        this.f11305y = new PayuRechargeFragment(this);
        this.f11306z = new PayuRechargeFragment(this);
        this.f11256A = new PayuRechargeFragment(this);
        this.f11257B = new PayuRechargeFragment(this);
        this.f11279X = new ProgressDialog(this.f11292l, com.olacabs.customer.R.style.TransparentProgressDialog);
        this.f11279X.setIndeterminateDrawable(this.f11292l.getResources().getDrawable(com.olacabs.customer.R.drawable.custom_progress_background));
        this.f11279X.setCancelable(false);
        m14747e();
    }

    private void m14740c(String str) {
        C0925d c0925d = new C0925d();
        try {
            c0925d.put("bankcode", str);
            m14699a(C0926a.NB, c0925d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m14747e() {
        m14790b();
        HashMap hashMap = new HashMap();
        Object string = this.f11292l.getIntent().getExtras().getString(Constants.ARG_USER_CREDENTIALS);
        String str = "var1";
        if (string == null) {
            string = this.f11270O;
        }
        hashMap.put(str, string);
        try {
            this.f11277V = PayuRechargeFragment.STORE_CARD;
            List a = C0927e.m14948a(this.f11292l).m14951a("payment_related_details_for_mobile_sdk", hashMap);
            C0923b c0923b = new C0923b(this);
            List[] listArr = new List[]{a};
            if (c0923b instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c0923b, listArr);
            } else {
                c0923b.execute(listArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(am, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
        } finally {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onStop() {
        m14788a();
        super.onStop();
    }

    private void m14750f() {
        if (this.f11287g.getVisibility() == 8 && this.f11288h.getVisibility() == 8 && this.f11289i.getVisibility() == 8) {
            this.f11293m = -1;
            m14762l();
        }
    }

    private void m14753g() {
        this.f11264I.setOnFocusChangeListener(new PayuRechargeFragment(this));
        this.f11259D.setOnFocusChangeListener(new PayuRechargeFragment(this));
        this.f11268M.setOnFocusChangeListener(new PayuRechargeFragment(this));
        this.f11263H.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11196a;

            {
                this.f11196a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    if (this.f11196a.m14745d(this.f11196a.f11263H.getText().toString())) {
                        this.f11196a.m14768o();
                        return;
                    }
                    this.f11196a.f11269N.setVisibility(0);
                    this.f11196a.f11269N.setText(this.f11196a.getString(com.olacabs.customer.R.string.text_invalid_card));
                }
            }
        });
        this.f11265J.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11197a;

            {
                this.f11197a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    String obj = this.f11197a.f11265J.getText().toString();
                    this.f11197a.f11266K.getText().toString();
                    if (obj.length() < 1) {
                        return;
                    }
                    if (Integer.parseInt(obj) > 12) {
                        this.f11197a.f11269N.setVisibility(0);
                        this.f11197a.f11269N.setText(this.f11197a.getString(com.olacabs.customer.R.string.text_invalid_month));
                        return;
                    }
                    this.f11197a.m14768o();
                }
            }
        });
        this.f11260E.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11199a;

            {
                this.f11199a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    String obj = this.f11199a.f11260E.getText().toString();
                    this.f11199a.f11261F.getText().toString();
                    if (obj.length() < 1) {
                        return;
                    }
                    if (Integer.parseInt(obj) > 12) {
                        this.f11199a.f11269N.setVisibility(0);
                        this.f11199a.f11269N.setText(this.f11199a.getString(com.olacabs.customer.R.string.text_invalid_month));
                        return;
                    }
                    this.f11199a.m14768o();
                }
            }
        });
        this.f11266K.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11200a;

            {
                this.f11200a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    String obj = this.f11200a.f11265J.getText().toString();
                    String obj2 = this.f11200a.f11266K.getText().toString();
                    if (obj2.length() < 4 || obj.length() < 1) {
                        if (obj2.length() < 4) {
                            return;
                        }
                        if (this.f11200a.m14722a(Integer.parseInt(obj2))) {
                            this.f11200a.m14768o();
                            return;
                        }
                        this.f11200a.f11269N.setVisibility(0);
                        this.f11200a.f11269N.setText(this.f11200a.getString(com.olacabs.customer.R.string.text_invalid_year));
                    } else if (this.f11200a.m14723a(Integer.parseInt(obj2), Integer.parseInt(obj))) {
                        this.f11200a.m14768o();
                    } else {
                        this.f11200a.f11269N.setVisibility(0);
                        this.f11200a.f11269N.setText(this.f11200a.getString(com.olacabs.customer.R.string.text_invalid_year));
                    }
                }
            }
        });
        this.f11261F.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11201a;

            {
                this.f11201a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    String obj = this.f11201a.f11260E.getText().toString();
                    String obj2 = this.f11201a.f11261F.getText().toString();
                    if (obj2.length() < 4 || obj.length() < 1) {
                        if (obj2.length() < 4) {
                            return;
                        }
                        if (this.f11201a.m14722a(Integer.parseInt(obj2))) {
                            this.f11201a.m14768o();
                            return;
                        }
                        this.f11201a.f11269N.setVisibility(0);
                        this.f11201a.f11269N.setText(this.f11201a.getString(com.olacabs.customer.R.string.text_invalid_year));
                    } else if (this.f11201a.m14723a(Integer.parseInt(obj2), Integer.parseInt(obj))) {
                        this.f11201a.m14768o();
                    } else {
                        this.f11201a.f11269N.setVisibility(0);
                        this.f11201a.f11269N.setText(this.f11201a.getString(com.olacabs.customer.R.string.text_invalid_year));
                    }
                }
            }
        });
        this.f11267L.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11202a;

            {
                this.f11202a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    if (this.f11202a.m14729a(this.f11202a.f11267L.getText().toString(), this.f11202a.f11264I.getText().toString())) {
                        this.f11202a.m14768o();
                        return;
                    }
                    this.f11202a.f11269N.setVisibility(0);
                    this.f11202a.f11269N.setText(this.f11202a.getString(com.olacabs.customer.R.string.text_invalid_cvv));
                }
            }
        });
        this.f11262G.setOnFocusChangeListener(new OnFocusChangeListener() {
            final /* synthetic */ PayuRechargeFragment f11203a;

            {
                this.f11203a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    if (this.f11203a.m14729a(this.f11203a.f11262G.getText().toString(), this.f11203a.f11264I.getText().toString())) {
                        this.f11203a.m14768o();
                        return;
                    }
                    this.f11203a.f11269N.setVisibility(0);
                    this.f11203a.f11269N.setText(this.f11203a.getString(com.olacabs.customer.R.string.text_invalid_cvv));
                }
            }
        });
    }

    public void m14789a(String str) {
        Sherlock.m13345b("Ins add ola money");
        OLog.m13313b(str, new Object[0]);
        m14778t();
        if (this.f11277V.equals(PayuRechargeFragment.DELETE_CARD)) {
            m14788a();
            if (this.f11278W != null) {
                this.f11286f.removeView(this.f11278W.f11243a);
                if (this.f11286f.getChildCount() == 0) {
                    this.f11302v.setVisibility(8);
                } else if (this.f11286f.getChildCount() > 0) {
                    m14730b((PayuRechargeFragment) this.f11286f.getChildAt(0).getTag());
                }
                this.f11278W = null;
            }
        } else if (this.f11277V.equals(PayuRechargeFragment.STORE_CARD)) {
            m14788a();
            if (C0927e.f11544h != null) {
                m14757i();
            }
        }
    }

    private void m14755h() {
        m14768o();
        for (int i = 0; i < this.f11286f.getChildCount(); i++) {
            PayuRechargeFragment payuRechargeFragment = (PayuRechargeFragment) this.f11286f.getChildAt(i).getTag();
            if (payuRechargeFragment.f11245c.getVisibility() == 0) {
                payuRechargeFragment.f11245c.setVisibility(8);
                payuRechargeFragment.f11247e.setChecked(false);
                m14715a(payuRechargeFragment);
            }
        }
    }

    private void m14757i() {
        String jSONArrayInstrumentation;
        this.f11302v.setVisibility(0);
        this.f11286f.setVisibility(0);
        this.f11286f.removeAllViews();
        this.f11273R = null;
        Gson gson = new Gson();
        JSONArray jSONArray = C0927e.f11544h;
        if (jSONArray instanceof JSONArray) {
            jSONArrayInstrumentation = JSONArrayInstrumentation.toString(jSONArray);
        } else {
            jSONArrayInstrumentation = jSONArray.toString();
        }
        this.f11273R = (ArrayList) gson.m12344a(jSONArrayInstrumentation, new TypeToken<List<dr>>() {
            final /* synthetic */ PayuRechargeFragment f11204d;

            {
                this.f11204d = r1;
            }
        }.m12301b());
        if (this.f11273R == null || this.f11273R.isEmpty()) {
            this.f11286f.setVisibility(8);
            this.f11302v.setVisibility(8);
        } else {
            Iterator it = this.f11273R.iterator();
            while (it.hasNext()) {
                dr drVar = (dr) it.next();
                View inflate = this.f11292l.getLayoutInflater().inflate(com.olacabs.customer.R.layout.view_saved_card_group, null);
                PayuRechargeFragment payuRechargeFragment = new PayuRechargeFragment(this, inflate);
                payuRechargeFragment.m14696a(drVar);
                payuRechargeFragment.m14695a();
                payuRechargeFragment.f11244b.setOnClickListener(new AnonymousClass19(this, payuRechargeFragment));
                inflate.setTag(payuRechargeFragment);
                this.f11286f.addView(inflate);
            }
            this.f11286f.setVisibility(0);
            this.f11302v.setVisibility(0);
        }
        if (this.f11286f.getChildCount() > 0) {
            m14730b((PayuRechargeFragment) this.f11286f.getChildAt(0).getTag());
        }
    }

    private void m14730b(PayuRechargeFragment payuRechargeFragment) {
        m14755h();
        m14758j();
        m14762l();
        m14715a(payuRechargeFragment);
        payuRechargeFragment.f11245c.setVisibility(0);
        payuRechargeFragment.f11247e.setChecked(true);
        Sherlock.m13345b("Ins add ola money");
    }

    private void m14738c(PayuRechargeFragment payuRechargeFragment) {
        m14712a(this.f11292l.getCurrentFocus());
        payuRechargeFragment.f11245c.setVisibility(8);
        payuRechargeFragment.f11247e.setChecked(false);
        m14768o();
        m14715a(payuRechargeFragment);
    }

    private void m14758j() {
        this.f11287g.setVisibility(8);
        this.f11288h.setVisibility(8);
        this.f11289i.setVisibility(8);
        m14750f();
    }

    private int m14760k() {
        for (int i = 0; i < this.f11286f.getChildCount(); i++) {
            if (((PayuRechargeFragment) this.f11286f.getChildAt(i).getTag()).f11245c.getVisibility() == 0) {
                return i;
            }
        }
        return -1;
    }

    private void m14762l() {
        m14768o();
        this.f11294n.setChecked(false);
        this.f11295o.setChecked(false);
        this.f11296p.setChecked(false);
    }

    private void m14764m() {
        PayuRechargeFragment payuRechargeFragment = (PayuRechargeFragment) this.f11286f.getChildAt(m14760k()).getTag();
        String obj = payuRechargeFragment.f11246d.getText().toString();
        String charSequence = payuRechargeFragment.f11249g.getText().toString();
        if (TextUtils.isEmpty(obj) && !m14748e(charSequence)) {
            this.f11269N.setVisibility(0);
            this.f11269N.setText("CVV is empty");
        } else if (m14729a(obj, charSequence)) {
            m14768o();
            m14734b("Saved Card", "N/A");
            m14714a(payuRechargeFragment.m14698c(), obj);
        } else {
            this.f11269N.setVisibility(0);
            this.f11269N.setText("Invalid CVV");
        }
    }

    private void m14766n() {
        switch (this.f11293m) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.af = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cardNumberEditText);
                this.ag = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.monthEditText);
                this.ah = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.yearEditText);
                this.ai = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cvvEditText);
                this.aj = (EditText) this.f11287g.findViewById(com.olacabs.customer.R.id.cardNameEditText);
                if (TextUtils.isEmpty(this.af.getText().toString()) || TextUtils.isEmpty(this.ag.getText().toString()) || TextUtils.isEmpty(this.ah.getText().toString()) || TextUtils.isEmpty(this.aj.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("No field should be empty");
                } else if (TextUtils.isEmpty(this.ai.getText().toString()) && !m14748e(this.af.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("CVV is empty");
                } else if (!m14728a(this.af.getText().toString(), this.f11287g)) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid card number");
                } else if (!m14723a(Integer.parseInt(this.ah.getText().toString()), Integer.parseInt(this.ag.getText().toString()))) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid expiry month/year");
                } else if (!m14745d(this.aj.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid name");
                } else if (m14729a(this.ai.getText().toString(), this.af.getText().toString())) {
                    m14768o();
                    m14734b("Debit card", "N/A");
                    m14751f("DC");
                    OLog.m13313b("Card Detail " + this.af.getText().toString(), new Object[0]);
                } else {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid CVV");
                }
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.af = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cardNumberEditText);
                this.af = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cardNumberEditText);
                this.ag = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.monthEditText);
                this.ah = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.yearEditText);
                this.ai = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cvvEditText);
                this.aj = (EditText) this.f11288h.findViewById(com.olacabs.customer.R.id.cardNameEditText);
                if (TextUtils.isEmpty(this.af.getText().toString()) || TextUtils.isEmpty(this.ag.getText().toString()) || TextUtils.isEmpty(this.ah.getText().toString()) || TextUtils.isEmpty(this.aj.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("No field should be empty");
                } else if (TextUtils.isEmpty(this.ai.getText().toString()) && !m14748e(this.af.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("CVV is empty");
                } else if (!m14728a(this.af.getText().toString(), this.f11287g)) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid card number");
                } else if (!m14723a(Integer.parseInt(this.ah.getText().toString()), Integer.parseInt(this.ag.getText().toString()))) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid expiry month/year");
                } else if (!m14745d(this.aj.getText().toString())) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid name");
                } else if (m14729a(this.ai.getText().toString(), this.af.getText().toString())) {
                    m14768o();
                    m14734b("Credit card", "N/A");
                    m14751f("CC");
                    OLog.m13313b("Card Detail " + this.af.getText().toString(), new Object[0]);
                } else {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Invalid CVV");
                }
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                this.ad = this.f11303w.getText().toString();
                if (this.ad == null || TextUtils.isEmpty(this.ad) || this.ad.equalsIgnoreCase(getString(com.olacabs.customer.R.string.text_select_bank))) {
                    this.f11269N.setVisibility(0);
                    this.f11269N.setText("Please select a bank");
                    return;
                }
                m14768o();
                m14734b("Net Banking", this.ad);
                m14740c((String) this.f11303w.getTag());
            default:
        }
    }

    private void m14712a(View view) {
        if (view != null) {
            ((InputMethodManager) this.f11292l.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void m14768o() {
        this.f11269N.setVisibility(8);
        this.f11269N.setText(Trace.NULL);
    }

    private void m14771p() {
        this.f11264I.addTextChangedListener(this.f11304x);
        this.f11268M.addTextChangedListener(this.f11305y);
        this.f11265J.addTextChangedListener(this.f11306z);
        this.f11266K.addTextChangedListener(this.f11256A);
        this.f11267L.addTextChangedListener(this.f11257B);
    }

    private void m14773q() {
        this.f11259D.addTextChangedListener(this.f11304x);
        this.f11263H.addTextChangedListener(this.f11305y);
        this.f11260E.addTextChangedListener(this.f11306z);
        this.f11261F.addTextChangedListener(this.f11256A);
        this.f11262G.addTextChangedListener(this.f11257B);
    }

    private void m14775r() {
        this.f11264I.removeTextChangedListener(this.f11304x);
        this.f11268M.removeTextChangedListener(this.f11305y);
        this.f11265J.removeTextChangedListener(this.f11306z);
        this.f11266K.removeTextChangedListener(this.f11256A);
        this.f11267L.removeTextChangedListener(this.f11257B);
    }

    private void m14777s() {
        this.f11259D.removeTextChangedListener(this.f11304x);
        this.f11263H.removeTextChangedListener(this.f11305y);
        this.f11260E.removeTextChangedListener(this.f11306z);
        this.f11261F.removeTextChangedListener(this.f11256A);
        this.f11262G.removeTextChangedListener(this.f11257B);
    }

    private boolean m14723a(int i, int i2) {
        if (i > Calendar.getInstance().get(1)) {
            return true;
        }
        if (i != Calendar.getInstance().get(1) || i2 - 1 < Calendar.getInstance().get(2)) {
            return false;
        }
        return true;
    }

    private boolean m14722a(int i) {
        if (i > Calendar.getInstance().get(1)) {
            return true;
        }
        return false;
    }

    private boolean m14745d(String str) {
        if (str.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean m14748e(String str) {
        return C0922a.m14940c(str).equalsIgnoreCase("MAES") && str.length() >= 12 && str.length() <= 19;
    }

    private boolean m14728a(String str, View view) {
        if (str.startsWith("34") || str.startsWith("37")) {
            ((EditText) view.findViewById(com.olacabs.customer.R.id.cvvEditText)).setFilters(new InputFilter[]{new LengthFilter(4)});
        } else {
            ((EditText) view.findViewById(com.olacabs.customer.R.id.cvvEditText)).setFilters(new InputFilter[]{new LengthFilter(3)});
        }
        if (str.length() <= 11 || !C0922a.m14938a(str).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean m14729a(String str, String str2) {
        if (C0922a.m14940c(str2).equalsIgnoreCase("MAES") && str2.length() >= 12 && str2.length() <= 19) {
            return true;
        }
        if (str2.startsWith("34") || str2.startsWith("37")) {
            if (str.length() != 4) {
                return false;
            }
            return true;
        } else if (str.length() != 3) {
            return false;
        } else {
            return true;
        }
    }

    private void m14714a(dr drVar, String str) {
        C0925d c0925d = new C0925d();
        try {
            c0925d.put("ccvv", str);
            c0925d.put("store_card_token", drVar.getCardToken());
            c0925d.put(Constants.FIRST_NAME, drVar.getNameOnCard());
            m14699a(C0926a.valueOf(drVar.getCardMode()), c0925d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m14751f(String str) {
        m14733b(str);
        C0925d c0925d = new C0925d();
        c0925d.put("ccnum", this.af.getText().toString().trim());
        c0925d.put("ccexpmon", String.valueOf(this.ag.getText().toString().trim()));
        c0925d.put("ccexpyr", String.valueOf(this.ah.getText().toString().trim()));
        c0925d.put("ccname", this.aj.getText().toString().trim());
        c0925d.put("ccvv", this.ai.getText().toString().trim());
        c0925d.put("bankcode", "CC");
        if (getArguments().getString("store_card") != null) {
            OLog.m13313b("Checked and added args", new Object[0]);
            if (getArguments().getString("store_card").equalsIgnoreCase("store_card")) {
                c0925d.put("store_card", "1");
            }
        }
        try {
            c0925d.put(Constants.ARG_USER_CREDENTIALS, this.f11292l.getPackageManager().getApplicationInfo(this.f11292l.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData.getString("payu_merchant_id") + ":" + this.f11290j.m13209c().getClearUserId());
            OLog.m13313b("added user credentials", new Object[0]);
        } catch (Throwable e) {
            OLog.m13314b(e, "startPaymentProcess", new Object[0]);
        }
        m14699a(C0926a.CC, c0925d);
    }

    private void m14778t() {
        if (C0927e.f11537a != null) {
            try {
                Gson gson = new Gson();
                JSONArray jSONArray = C0927e.f11537a;
                this.f11272Q = (ArrayList) gson.m12344a(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray), new TypeToken<List<bq>>() {
                    final /* synthetic */ PayuRechargeFragment f11208d;

                    {
                        this.f11208d = r1;
                    }
                }.m12301b());
                this.f11272Q.remove(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void m14721a(ArrayList<bq> arrayList) {
        AlertDialog create = new Builder(this.f11292l, 16973935).setTitle(getResources().getString(com.olacabs.customer.R.string.select_your_bank)).setAdapter(new PayuRechargeFragment(this, this.f11292l, arrayList), new AnonymousClass21(this, arrayList)).create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    private void m14720a(String str, String str2, PayuRechargeFragment payuRechargeFragment) {
        View inflate = ((LayoutInflater) this.f11292l.getSystemService("layout_inflater")).inflate(com.olacabs.customer.R.layout.view_dialog_messsage_yes_no, null, false);
        AlertDialog create = new Builder(this.f11292l).setView(inflate).create();
        ((TextView) inflate.findViewById(com.olacabs.customer.R.id.item_header)).setText(str);
        ((TextView) inflate.findViewById(com.olacabs.customer.R.id.item_message)).setText(str2);
        inflate.findViewById(com.olacabs.customer.R.id.button_yes).setOnClickListener(new AnonymousClass22(this, create, payuRechargeFragment));
        inflate.findViewById(com.olacabs.customer.R.id.button_no).setOnClickListener(new AnonymousClass24(this, create));
        create.setCancelable(false);
        create.show();
    }

    private void m14713a(CompoundButton compoundButton) {
        View inflate = ((LayoutInflater) this.f11292l.getSystemService("layout_inflater")).inflate(com.olacabs.customer.R.layout.view_dialog_do_not_show_again, null, false);
        AlertDialog create = new Builder(this.f11292l).setView(inflate).create();
        inflate.findViewById(com.olacabs.customer.R.id.button_yes).setOnClickListener(new AnonymousClass25(this, create, compoundButton));
        inflate.findViewById(com.olacabs.customer.R.id.button_no).setOnClickListener(new AnonymousClass26(this, create));
        create.setCancelable(false);
        create.show();
    }

    public void m14788a() {
        if (this.f11279X != null && this.f11279X.isShowing()) {
            this.f11279X.dismiss();
        }
    }

    public void m14790b() {
        if (this.f11279X != null && !this.f11279X.isShowing()) {
            this.f11279X.show();
        }
    }

    private void m14743d(PayuRechargeFragment payuRechargeFragment) {
        Localytics.tagEvent("Delete saved card");
        m14790b();
        HashMap hashMap = new HashMap();
        hashMap.put("var1", getArguments().getString(Constants.ARG_USER_CREDENTIALS) != null ? getArguments().getString(Constants.ARG_USER_CREDENTIALS) : this.f11270O);
        hashMap.put("var2", payuRechargeFragment.m14697b());
        try {
            this.f11277V = PayuRechargeFragment.DELETE_CARD;
            List a = C0927e.m14948a(this.f11292l).m14951a("delete_user_card", hashMap);
            C0923b c0923b = new C0923b(this);
            List[] listArr = new List[]{a};
            if (c0923b instanceof AsyncTask) {
                AsyncTaskInstrumentation.execute(c0923b, listArr);
            } else {
                c0923b.execute(listArr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m14712a(this.f11292l.getCurrentFocus());
    }

    private String m14780u() {
        String str = "less than 5000";
        if (this.f11281Z == null) {
            return "N/A";
        }
        int parseInt = Integer.parseInt(this.f11281Z);
        if (parseInt <= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            return "less than 500";
        }
        if (HttpStatus.SC_INTERNAL_SERVER_ERROR < parseInt && parseInt <= Constants.MILLIS_IN_A_SECOND) {
            return "less than 1000";
        }
        if (Constants.MILLIS_IN_A_SECOND < parseInt && parseInt <= ActivityTrace.MAX_TRACES) {
            return "less than 2000";
        }
        if (ActivityTrace.MAX_TRACES >= parseInt || parseInt > by.DEFAULT_TIMEOUT_MS) {
            return str;
        }
        return "less than 5000";
    }

    private void m14734b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("Net Banking Bank Name", str2);
        hashMap.put("Payment mode", str);
        hashMap.put("RechargeAmount", m14780u());
        Localytics.tagEvent("Pay Now Clicked", hashMap);
    }
}
