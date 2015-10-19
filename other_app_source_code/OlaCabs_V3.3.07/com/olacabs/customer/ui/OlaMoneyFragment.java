package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.apsalar.sdk.Apsalar;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.ForceLogoutCommand;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.p076d.AccountBalanceResponse;
import com.olacabs.customer.p076d.ah;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.an;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.p076d.cb;
import com.olacabs.customer.p076d.cd;
import com.olacabs.customer.p076d.cj;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.di;
import com.olacabs.customer.p076d.dp;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import com.olacabs.customer.utils.Utils;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import com.payu.p084a.Bank;
import com.payu.sdk.C0927e;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;
import p000a.p001a.p002a.EventBus;

@Instrumented
/* renamed from: com.olacabs.customer.ui.u */
public class OlaMoneyFragment extends Fragment implements TextWatcher, OnClickListener, OnCheckedChangeListener, TraceFieldInterface {
    private static final Map<String, String> f11104J;
    private static final JoinPoint f11105S = null;
    private static final JoinPoint f11106T = null;
    private static final JoinPoint f11107U = null;
    private static final JoinPoint f11108V = null;
    private static final JoinPoint f11109W = null;
    private static final String f11110c;
    private ImageButton f11111A;
    private ProgressDialog f11112B;
    private String f11113C;
    private AlertDialog f11114D;
    private TextView f11115E;
    private TextView f11116F;
    private TextView f11117G;
    private ViewStub f11118H;
    private di f11119I;
    private aj f11120K;
    private aj f11121L;
    private aj f11122M;
    private TextWatcher f11123N;
    private List<an> f11124O;
    private aj f11125P;
    private aj f11126Q;
    private aj f11127R;
    LinearLayout f11128a;
    da f11129b;
    private String f11130d;
    private String f11131e;
    private MainActivity f11132f;
    private dt f11133g;
    private DataManager f11134h;
    private TextView f11135i;
    private RelativeLayout f11136j;
    private TextView f11137k;
    private TextView f11138l;
    private TextView f11139m;
    private TextView f11140n;
    private CheckBox f11141o;
    private TextView f11142p;
    private TextView f11143q;
    private RelativeLayout f11144r;
    private EditText f11145s;
    private TextView f11146t;
    private TextView f11147u;
    private EditText f11148v;
    private Button f11149w;
    private TextView f11150x;
    private TextView f11151y;
    private ScrollView f11152z;

    /* renamed from: com.olacabs.customer.ui.u.1 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11093a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11093a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to update profile details", th);
            if (this.f11093a.isAdded()) {
                this.f11093a.m14658b();
                this.f11093a.m14632c(this.f11093a.getString(R.string.generic_failure_desc), this.f11093a.getString(R.string.generic_failure_header));
            }
        }

        public void onSuccess(Object obj) {
            this.f11093a.m14658b();
            if (this.f11093a.isAdded()) {
                dp dpVar = (dp) obj;
                String verificationId;
                if (dpVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    OLog.m13311a("Update profile data success", new Object[0]);
                    verificationId = dpVar.getVerificationId();
                    Intent intent = new Intent(this.f11093a.getActivity(), MobileVerificationActivity.class);
                    intent.putExtra("verification_id", verificationId);
                    intent.putExtra(Constants.BUNDLE_TYPE, "update");
                    intent.putExtra(Constants.BUNDLE_NAME, this.f11093a.f11133g.getName());
                    intent.putExtra(dt.USER_PHONE_KEY, this.f11093a.f11133g.getPhoneNumber());
                    this.f11093a.startActivity(intent);
                } else if (dpVar.getStatus().equalsIgnoreCase("FAILURE")) {
                    OLog.m13311a("Update profile data failure", new Object[0]);
                    verificationId = this.f11093a.getString(R.string.generic_failure_desc);
                    String string = this.f11093a.getString(R.string.failure);
                    if (dpVar.getReason().equals("INVALID_PARAMETERS")) {
                        verificationId = this.f11093a.getString(R.string.profile_update_failed_desc);
                    }
                    this.f11093a.m14632c(verificationId, string);
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.2 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11094a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11094a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13311a("Payment Transaction Response Failure", new Object[0]);
        }

        public void onSuccess(Object obj) {
            cb cbVar = (cb) obj;
            if (cbVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                String a = this.f11094a.m14630c(cbVar.getErrorCode());
                if (cbVar.getErrorCode().equalsIgnoreCase("success") || cbVar.getErrorCode().equalsIgnoreCase("E000")) {
                    Localytics.tagEvent("Payment Successful");
                    Apsalar.event("Payment Successful");
                } else if (!TextUtils.isEmpty(a)) {
                    this.f11094a.m14635d(a);
                }
                this.f11094a.m14632c(cbVar.getText(), cbVar.getHeader());
                this.f11094a.m14645i();
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.3 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11095a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11095a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed to obtain account balance", th);
            this.f11095a.m14658b();
            Sherlock.m13337a("Ins ola money shown", (VolleyError) th, true);
            this.f11095a.m14632c(this.f11095a.getString(R.string.connection_time_out_error_desc), this.f11095a.getString(R.string.connection_time_out_error_title));
        }

        public void onSuccess(Object obj) {
            this.f11095a.m14658b();
            if (this.f11095a.isAdded()) {
                AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) obj;
                if (accountBalanceResponse != null && accountBalanceResponse.isForceLogout()) {
                    new ForceLogoutCommand(true).m13270a(this.f11095a.getActivity());
                }
                if (accountBalanceResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                    OLog.m13311a("Get account balance succeeded", new Object[0]);
                    this.f11095a.f11133g.setOlaBalance(accountBalanceResponse.getTotalBalance());
                    this.f11095a.f11137k.setText(String.format(this.f11095a.getString(R.string.ola_amount), new Object[]{String.valueOf(r0)}));
                    if (!(this.f11095a.f11132f == null || this.f11095a.f11132f.m13608e() == null)) {
                        this.f11095a.f11132f.m13608e().m13649a();
                    }
                    this.f11095a.f11124O = accountBalanceResponse.getDenominationsList();
                    if (this.f11095a.f11124O != null) {
                        CharSequence denomination = ((an) this.f11095a.f11124O.get(0)).getDenomination();
                        CharSequence headerText = ((an) this.f11095a.f11124O.get(0)).getHeaderText();
                        if (!TextUtils.isEmpty(denomination)) {
                            this.f11095a.f11138l.setText(String.format(this.f11095a.getString(R.string.ola_amount), new Object[]{denomination}));
                        }
                        if (headerText == null || TextUtils.isEmpty(headerText)) {
                            this.f11095a.f11115E.setVisibility(8);
                        } else {
                            this.f11095a.f11115E.setVisibility(0);
                            this.f11095a.f11115E.setText(headerText);
                        }
                        denomination = ((an) this.f11095a.f11124O.get(1)).getDenomination();
                        headerText = ((an) this.f11095a.f11124O.get(1)).getHeaderText();
                        if (!TextUtils.isEmpty(denomination)) {
                            this.f11095a.f11139m.setText(String.format(this.f11095a.getString(R.string.ola_amount), new Object[]{denomination}));
                        }
                        if (headerText == null || TextUtils.isEmpty(headerText)) {
                            this.f11095a.f11116F.setVisibility(8);
                        } else {
                            this.f11095a.f11116F.setVisibility(0);
                            this.f11095a.f11116F.setText(headerText);
                        }
                        denomination = ((an) this.f11095a.f11124O.get(2)).getDenomination();
                        headerText = ((an) this.f11095a.f11124O.get(2)).getHeaderText();
                        if (!TextUtils.isEmpty(denomination)) {
                            this.f11095a.f11140n.setText(String.format(this.f11095a.getString(R.string.ola_amount), new Object[]{denomination}));
                        }
                        if (headerText == null || TextUtils.isEmpty(headerText)) {
                            this.f11095a.f11117G.setVisibility(8);
                        } else {
                            this.f11095a.f11117G.setVisibility(0);
                            this.f11095a.f11117G.setText(headerText);
                        }
                        Sherlock.m13345b("Ins ola money shown");
                    }
                    if (accountBalanceResponse.isOfferApplicable()) {
                        this.f11095a.f11143q.setText(accountBalanceResponse.getOfferText());
                    } else {
                        this.f11095a.f11143q.setText(this.f11095a.getActivity().getResources().getString(R.string.ola_money_description));
                    }
                    if (accountBalanceResponse.getOfferSelectDenom() == 0) {
                        this.f11095a.m14613a(this.f11095a.f11138l);
                    } else if (accountBalanceResponse.getOfferSelectDenom() == 1) {
                        this.f11095a.m14613a(this.f11095a.f11139m);
                    } else if (accountBalanceResponse.getOfferSelectDenom() == 2) {
                        this.f11095a.m14613a(this.f11095a.f11140n);
                    }
                } else if (accountBalanceResponse.getStatus().equalsIgnoreCase("FAILURE")) {
                    Sherlock.m13337a("Ins ola money shown", null, true);
                    OLog.m13318e("Get account balance failed - " + accountBalanceResponse.getReason(), new Object[0]);
                    this.f11095a.m14632c(this.f11095a.getString(R.string.connection_time_out_error_desc), this.f11095a.getString(R.string.connection_time_out_error_title));
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.4 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11096a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11096a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Failed verify promo code", th);
            this.f11096a.m14658b();
            this.f11096a.m14632c(this.f11096a.getString(R.string.connection_time_out_error_desc), this.f11096a.getString(R.string.connection_time_out_error_title));
        }

        public void onSuccess(Object obj) {
            if (this.f11096a.isAdded()) {
                Map hashMap = new HashMap();
                this.f11096a.m14658b();
                cj cjVar = (cj) obj;
                this.f11096a.f11147u.setVisibility(0);
                if (!TextUtils.isEmpty(this.f11096a.f11113C)) {
                    hashMap.put(NotificationCompatApi21.CATEGORY_STATUS, cjVar.getStatus());
                    hashMap.put("promo code", this.f11096a.f11113C);
                    this.f11096a.m14622a("applied promo code", hashMap);
                }
                if (cjVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    OLog.m13311a("Successfully verified promo code", new Object[0]);
                    this.f11096a.f11147u.setText(cjVar.getText());
                    this.f11096a.f11147u.setTextColor(this.f11096a.getResources().getColor(R.color.ola_black_text));
                    this.f11096a.f11146t.setText(this.f11096a.getString(R.string.text_remove_caps));
                    this.f11096a.f11146t.setTextColor(this.f11096a.getResources().getColor(R.color.ola_red_dark));
                    this.f11096a.f11145s.setFocusable(false);
                    this.f11096a.f11145s.setEnabled(false);
                } else if (cjVar.getStatus().equalsIgnoreCase("FAILURE")) {
                    OLog.m13311a("Failed verify promo code", new Object[0]);
                    this.f11096a.f11147u.setText(cjVar.getText());
                    this.f11096a.f11147u.setTextColor(this.f11096a.getResources().getColor(R.color.ola_red_dark));
                    this.f11096a.f11145s.setText(Trace.NULL);
                    this.f11096a.f11145s.setClickable(true);
                    this.f11096a.f11145s.setFocusableInTouchMode(true);
                    this.f11096a.f11145s.setFocusable(true);
                    this.f11096a.f11145s.setEnabled(true);
                    this.f11096a.f11145s.requestFocus();
                }
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.5 */
    class OlaMoneyFragment implements TextWatcher {
        final /* synthetic */ OlaMoneyFragment f11097a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11097a = olaMoneyFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().length() > 0) {
                this.f11097a.f11146t.setTextColor(this.f11097a.getResources().getColor(R.color.ola_yellow_light_header_line));
                this.f11097a.f11146t.setClickable(true);
                return;
            }
            this.f11097a.f11146t.setTextColor(this.f11097a.getResources().getColor(R.color.ola_grey_dark_splash_screen));
            this.f11097a.f11146t.setClickable(false);
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.6 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11098a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11098a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            Sherlock.m13337a("Ins add ola money", (VolleyError) th, false);
            this.f11098a.m14658b();
        }

        public void onSuccess(Object obj) {
            this.f11098a.m14658b();
            di diVar = (di) obj;
            if (diVar != null) {
                if (diVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                    if (diVar.getOrderId() != null) {
                        this.f11098a.f11119I = diVar;
                        this.f11098a.m14619a(diVar.getOrderId(), diVar.getAmount());
                    }
                } else if (diVar.getStatus().equalsIgnoreCase("FAILURE")) {
                    Sherlock.m13336a("Ins add ola money", null, diVar.getText(), true);
                    this.f11098a.m14632c(diVar.getText(), diVar.getHeader());
                }
                this.f11098a.m14628b(String.valueOf(this.f11098a.f11133g.getOlaBalance()), this.f11098a.m14637e());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.7 */
    class OlaMoneyFragment implements aj {
        final /* synthetic */ OlaMoneyFragment f11099a;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment) {
            this.f11099a = olaMoneyFragment;
        }

        public void onFailure(Throwable th) {
            this.f11099a.m14658b();
        }

        public void onSuccess(Object obj) {
            this.f11099a.m14658b();
            cd cdVar = (cd) obj;
            if (cdVar.isValid() && cdVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                C0927e.f11551o = cdVar.getDeleteCardHash();
                C0927e.f11557u = cdVar.getNbListHash();
                C0927e.f11550n = cdVar.getPaymentHash();
                if (this.f11099a.f11119I == null) {
                    return;
                }
                if (this.f11099a.f11119I.getStatus().equalsIgnoreCase("SUCCESS")) {
                    String str;
                    String str2;
                    OlaMoneyFragment olaMoneyFragment = this.f11099a;
                    String amount = this.f11099a.f11119I.getAmount();
                    String orderId = this.f11099a.f11119I.getOrderId();
                    if (this.f11099a.f11119I.getSuccessUrl() != null) {
                        str = "https://apps.olacabs.com/" + this.f11099a.f11119I.getSuccessUrl();
                    } else {
                        str = this.f11099a.getResources().getString(R.string.pay_u_surl);
                    }
                    if (this.f11099a.f11119I.getFailureUrl() != null) {
                        str2 = "https://apps.olacabs.com/" + this.f11099a.f11119I.getFailureUrl();
                    } else {
                        str2 = this.f11099a.getResources().getString(R.string.pay_u_furl);
                    }
                    olaMoneyFragment.m14621a(amount, orderId, str, str2);
                    this.f11099a.f11134h.m13218d().setPayUOrderId(this.f11099a.f11119I.getOrderId());
                } else if (this.f11099a.f11119I.getStatus().equalsIgnoreCase("FAILURE")) {
                    this.f11099a.m14632c(this.f11099a.f11119I.getText(), this.f11099a.f11119I.getHeader());
                }
            } else if (this.f11099a.f11119I.getText() == null || this.f11099a.f11119I.getHeader() == null) {
                this.f11099a.m14632c(this.f11099a.getString(R.string.generic_failure_desc), this.f11099a.getString(R.string.generic_failure_header));
            } else {
                this.f11099a.m14632c(this.f11099a.f11119I.getText(), this.f11099a.f11119I.getHeader());
            }
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.8 */
    class OlaMoneyFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11100a;
        final /* synthetic */ OlaMoneyFragment f11101b;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment, AlertDialog alertDialog) {
            this.f11101b = olaMoneyFragment;
            this.f11100a = alertDialog;
        }

        public void onClick(View view) {
            this.f11100a.dismiss();
            Ola.f11483G = true;
            this.f11101b.f11132f.m13595a(0);
        }
    }

    /* renamed from: com.olacabs.customer.ui.u.9 */
    class OlaMoneyFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f11102a;
        final /* synthetic */ OlaMoneyFragment f11103b;

        OlaMoneyFragment(OlaMoneyFragment olaMoneyFragment, AlertDialog alertDialog) {
            this.f11103b = olaMoneyFragment;
            this.f11102a = alertDialog;
        }

        public void onClick(View view) {
            this.f11102a.dismiss();
        }
    }

    private static void m14647j() {
        Factory factory = new Factory("OlaMoneyFragment.java", OlaMoneyFragment.class);
        f11105S = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.OlaMoneyFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 480);
        f11106T = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.OlaMoneyFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), (int) HttpStatus.SC_INTERNAL_SERVER_ERROR);
        f11107U = factory.m15070a("method-execution", factory.m15071a("1", "onStart", "com.olacabs.customer.ui.OlaMoneyFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 1036);
        f11108V = factory.m15070a("method-execution", factory.m15071a("1", "onResume", "com.olacabs.customer.ui.OlaMoneyFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 1042);
        f11109W = factory.m15070a("method-execution", factory.m15071a("1", "onPause", "com.olacabs.customer.ui.OlaMoneyFragment", Trace.NULL, Trace.NULL, Trace.NULL, "void"), 1084);
    }

    static {
        OlaMoneyFragment.m14647j();
        f11110c = OlaMoneyFragment.class.getSimpleName();
        f11104J = new HashMap();
        f11104J.put("success", "TRANSACTION_SUCCESSFUL");
        f11104J.put("E000", "NO_ERROR");
        f11104J.put("E201", "BRAND_INVALID");
        f11104J.put("E202", "TRANSACTION_INVALID");
        f11104J.put("E205", "CURL_ERROR_ENROLLED");
        f11104J.put("E206", "CUTOFF_ERROR");
        f11104J.put("E207", "INVALID_TRANSACTION_TYPE");
        f11104J.put("E208", "BANK_SERVER_ERROR");
        f11104J.put("E209", "NO_BANK_RESPONSE");
        f11104J.put("E210", "COMMUNICATION_ERROR");
        f11104J.put("E211", "NETWORK_ERROR");
        f11104J.put("E214", "CURL_CALL_FAILURE");
        f11104J.put("E216", "BATCH_ERROR");
        f11104J.put("E217", "TRANPORTAL_ID_ERROR");
        f11104J.put("E218", "CARD_ISSUER_TIMED_OUT");
        f11104J.put("E219", "INCOMPLETE_BANK_RESPONSE");
        f11104J.put("E300", "SECURE_3D_PASSWORD_ERROR");
        f11104J.put("E301", "SECURE_3D_INCORRECT");
        f11104J.put("E302", "SECURE_3D_CANCELLED");
        f11104J.put("E303", "AUTHENTICATION_ERROR");
        f11104J.put("E304", "ADDRESS_INVALID");
        f11104J.put("E305", "CARD_NUMBER_INVALID");
        f11104J.put("E306", "TRANSACTION_INVALID_PG");
        f11104J.put("E307", "RISK_DENIED_PG");
        f11104J.put("E308", "TRANSACTION_FAILED");
        f11104J.put("E309", "SYSTEM_ERROR_PG");
        f11104J.put("E310", "LOST_CARD");
        f11104J.put("E311", "EXPIRED_CARD");
        f11104J.put("E312", "BANK_DENIED");
        f11104J.put("E313", "CVC_FAILURE");
        f11104J.put("E314", "ADDRESS_FAILURE");
        f11104J.put("E315", "CVC_ADDRESS_FAILURE");
        f11104J.put("E316", "SECURE_3D_NOT_ENROLLED");
        f11104J.put("E317", "SECURE_3D_AUTHENTICATION_ERROR");
        f11104J.put("E318", "SECURE_3D_NOT_SUPPORTED");
        f11104J.put("E319", "SECURE_3D_FORMAT_ERROR");
        f11104J.put("E320", "SECURE_3D_SIGNATURE_ERROR");
        f11104J.put("E321", "SECURE_3D_SERVER_ERROR");
        f11104J.put("E322", "SECURE_3D_CARD_TYPE");
        f11104J.put("E323", "INVALID_EXPIRY_DATE");
        f11104J.put("E324", "CARD_FRAUD_SUSPECTED");
        f11104J.put("E325", "RESTRICTED_CARD");
        f11104J.put("E326", "PASSWORD_ERROR");
        f11104J.put("E327", "INVALID_LOGIN");
        f11104J.put("E328", "PARAMETERS_MISMATCH");
        f11104J.put("E329", "ISSUER_DECLINED_LOW_FUNDS");
        f11104J.put("E330", "PAYMENT_GATEWAY_VALIDATION_FAILURE");
        f11104J.put("E331", "INVALID_EMAIL_ID");
        f11104J.put("E332", "INVALID_FAX");
        f11104J.put("E333", "INVALID_CONTACT");
        f11104J.put("E334", "AUTHENTICATION_SERVICE_UNAVAILABLE");
        f11104J.put("E335", "AUTHENTICATION_INCOMPLETE");
        f11104J.put("E336", "EXPIRY_DATE_LOW_FUNDS");
        f11104J.put("E337", "NOT_CAPTURED");
        f11104J.put("E338", "RISK_RULE_FAILED");
        f11104J.put("E500", "UNKNOWN_ERROR_PG");
        f11104J.put("E502", "TRANSACTION_ABORTED");
        f11104J.put("E504", "DUPLICATE_TRANSACTION");
        f11104J.put("E505", "AWAITING_PROCESSING");
        f11104J.put("E600", "PAYU_API_ERROR");
        f11104J.put("E700", "SECURE_HASH_FAILURE");
        f11104J.put("E702", "AMOUNT_DIFFERENCE");
        f11104J.put("E703", "TRANSACTION_NUMBER_ERROR");
        f11104J.put("E704", "RECEIPT_NUMBER_ERROR");
        f11104J.put("E705", "USER_PROFILE_SETTINGS_ERROR");
        f11104J.put("E706", "INSUFFICIENT_FUNDS");
        f11104J.put("E707", "INVALID_PAN");
        f11104J.put("E708", "PIN_RETRIES_EXCEEDED");
        f11104J.put("E709", "INVALID_CARD_NAME");
        f11104J.put("E710", "INVALID_PIN");
        f11104J.put("E711", "INVALID_USER_DEFINED_DATA");
        f11104J.put("E712", "INCOMPLETE_DATA");
        f11104J.put("E713", "INSUFFICIENT_FUNDS_EXPIRY_INVALID");
        f11104J.put("E714", "INVALID_ZIP");
        f11104J.put("E715", "INVALID_AMOUNT");
        f11104J.put("E717", "INVALID_ACCOUNT_NUMBER");
        f11104J.put("E718", "INSUFFICIENT_FUNDS_INVALID_CVV");
        f11104J.put("E719", "INSUFFICIENT_FUNDS_AUTHENTICATION_FAILURE");
        f11104J.put("E720", "MAX_AMOUNT_EXCEEDED_FOR_PAYMENT_TYPE");
        f11104J.put("E800", "PREFERED_GATEWAY_NOT_SET");
        f11104J.put("E801", "NETBANKING_GATEWAY_DOWN");
        f11104J.put("E802", "CC_DC_ISSUING_BANK_DOWN");
        f11104J.put("E803", "NO_ELIGIBLE_PG");
        f11104J.put("E804", "DISABLE_PG_NOT_AVAILABLE_HANDLING");
        f11104J.put("E901", "RETRY_LIMIT_EXCEEDED");
        f11104J.put("E902", "INVALID_CARD_TYPE");
        f11104J.put("E903", "INTERNATIONAL_CARD_NOT_ALLOWED");
        f11104J.put("E905", "USER_DECLINED");
        f11104J.put("E906", "TIMER_EXPIRED");
        f11104J.put("E907", "WRONG_PAYMENT_METHOD");
        f11104J.put("E908", "UNKNOWN_BINS_NO_ACTIVE_PG_ASSIGNED");
        f11104J.put("E1000", "SECURE_3D_AUTHENTICATION_ERROR_S3A");
        f11104J.put("E1001", "AUTHENTICATION_SERVICE_UNAVAILABLE_ASU");
        f11104J.put("E1020", "INVALID_TRANSACTION_ID");
        f11104J.put("E1101", "TXN_DETAIL_INVALID_REDIRECTING_TO_MERCHANT");
        f11104J.put("E1201", "SERVICE_AUTHORIZATION_ERROR");
        f11104J.put("E1202", "FACILITY_UNAVAILABLE");
        f11104J.put("E1203", "LIMIT_EXCEED");
        f11104J.put("E1204", "NETBANKING_AUTHENTICATION_ERROR");
        f11104J.put("E1205", "REDIRECTED_BY_RETRY_LINK");
        f11104J.put("E1206", "REDIRECTED_BY_BACK_BUTTON");
        f11104J.put("E1208", "INSUFFICIENT_FUNDS_INCORRECT_EXPIRY");
    }

    private void m14622a(String str, Map map) {
        Localytics.tagEvent(str, map);
    }

    public OlaMoneyFragment() {
        this.f11119I = null;
        this.f11120K = new OlaMoneyFragment(this);
        this.f11121L = new OlaMoneyFragment(this);
        this.f11122M = new OlaMoneyFragment(this);
        this.f11123N = new OlaMoneyFragment(this);
        this.f11125P = new OlaMoneyFragment(this);
        this.f11126Q = new OlaMoneyFragment(this);
        this.f11127R = new OlaMoneyFragment(this);
    }

    public static OlaMoneyFragment m14609a() {
        return new OlaMoneyFragment();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("u");
        try {
            TraceMachine.enterMethod(this._nr_trace, "u#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "u#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f11105S, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        OLog.m13311a("onCreate+", new Object[0]);
        this.f11132f = (MainActivity) getActivity();
        this.f11134h = ((OlaApp) this.f11132f.getApplication()).m12878a();
        this.f11133g = this.f11134h.m13209c();
        Localytics.tagScreen("Ola Money Screen Viewed");
        this.f11129b = this.f11134h.m13218d();
        this.f11112B = new ProgressDialog(this.f11132f, R.style.TransparentProgressDialog);
        this.f11112B.setIndeterminateDrawable(this.f11132f.getResources().getDrawable(R.drawable.custom_progress_background));
        this.f11112B.setCancelable(false);
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "u#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "u#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f11106T, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        OLog.m13311a("onCreate+", new Object[0]);
        View inflate = this.f11132f.getLayoutInflater().inflate(R.layout.ola_money_initial_screen, null);
        this.f11151y = (TextView) inflate.findViewById(R.id.actionbar_Faq);
        this.f11151y.setOnClickListener(this);
        this.f11135i = (TextView) inflate.findViewById(R.id.payment_error_text);
        this.f11118H = (ViewStub) inflate.findViewById(R.id.stub_sad_error);
        this.f11136j = (RelativeLayout) inflate.findViewById(R.id.current_balance);
        this.f11137k = (TextView) inflate.findViewById(R.id.ola_money_balance_amount);
        int olaBalance = this.f11133g.getOlaBalance();
        this.f11137k.setText(String.format(getString(R.string.ola_amount), new Object[]{String.valueOf(olaBalance)}));
        this.f11136j.setOnClickListener(this);
        this.f11138l = (TextView) inflate.findViewById(R.id.amount1);
        this.f11138l.setOnClickListener(this);
        this.f11139m = (TextView) inflate.findViewById(R.id.amount2);
        this.f11139m.setOnClickListener(this);
        this.f11140n = (TextView) inflate.findViewById(R.id.amount3);
        this.f11140n.setOnClickListener(this);
        this.f11115E = (TextView) inflate.findViewById(R.id.amount_offer_1);
        this.f11116F = (TextView) inflate.findViewById(R.id.amount_offer_2);
        this.f11117G = (TextView) inflate.findViewById(R.id.amount_offer_3);
        this.f11143q = (TextView) inflate.findViewById(R.id.ola_money_description);
        this.f11142p = (TextView) inflate.findViewById(R.id.offer_status);
        this.f11148v = (EditText) inflate.findViewById(R.id.enter_amount);
        this.f11148v.addTextChangedListener(this);
        this.f11141o = (CheckBox) inflate.findViewById(R.id.promo_code_checkbox);
        this.f11141o.setOnCheckedChangeListener(this);
        this.f11152z = (ScrollView) inflate.findViewById(R.id.scroll_container);
        this.f11111A = (ImageButton) inflate.findViewById(R.id.button_navigation_drawer);
        this.f11111A.setOnClickListener(this);
        this.f11144r = (RelativeLayout) inflate.findViewById(R.id.promo_code_layout);
        this.f11145s = (EditText) inflate.findViewById(R.id.enter_promo_code);
        this.f11145s.addTextChangedListener(this.f11123N);
        this.f11146t = (TextView) inflate.findViewById(R.id.promo_code_apply);
        this.f11146t.setOnClickListener(this);
        this.f11147u = (TextView) inflate.findViewById(R.id.promo_code_resp_text);
        this.f11149w = (Button) inflate.findViewById(R.id.add_ola_money_button);
        this.f11149w.setOnClickListener(this);
        this.f11150x = (TextView) inflate.findViewById(R.id.use_recharge_code);
        this.f11150x.setOnClickListener(this);
        ((Button) inflate.findViewById(R.id.mobileVerifyButton)).setOnClickListener(this);
        this.f11128a = (LinearLayout) inflate.findViewById(R.id.layout_mobile_not_verified);
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m14634d() {
        m14659c();
        this.f11134h.m13216c(new WeakReference(this.f11125P), this.f11148v.getText().toString(), this.f11131e, this.f11145s.getText().toString(), f11110c);
    }

    private void m14619a(String str, String str2) {
        m14659c();
        try {
            String str3 = str;
            String str4 = str2;
            this.f11134h.m13223d(new WeakReference(this.f11126Q), str3, this.f11132f.getPackageManager().getApplicationInfo(this.f11132f.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData.getString("payu_merchant_id"), str4, f11110c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String m14637e() {
        String str = "less than 5000";
        int parseInt = Integer.parseInt(this.f11130d);
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

    private void m14628b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("current balance", str);
        hashMap.put("RechargeAmount", str2);
        Localytics.tagEvent("Add ola money clicked", hashMap);
    }

    public void onClick(View view) {
        OLog.m13311a("onClick in ola money initial frag", new Object[0]);
        switch (view.getId()) {
            case R.id.button_navigation_drawer:
                ((MainActivity) getActivity()).m13607d();
            case R.id.actionbar_Faq:
                Localytics.tagEvent("How this works pressed");
                startActivity(FaqActivity.m13493a(this.f11132f, "https://apps.olacabs.com/v3/social/faq"));
            case R.id.current_balance:
                Sherlock.m13334a("Ins ola money tx shown");
                startActivity(new Intent(this.f11132f, RecentTransationsActivity.class));
            case R.id.amount1:
            case R.id.amount2:
            case R.id.amount3:
                m14613a(view);
            case R.id.promo_code_apply:
                this.f11113C = this.f11145s.getText().toString();
                if (!TextUtils.isEmpty(this.f11145s.getText())) {
                    ((InputMethodManager) this.f11132f.getSystemService("input_method")).hideSoftInputFromWindow(this.f11145s.getWindowToken(), 0);
                    if (this.f11146t.getText().toString().equalsIgnoreCase(getString(R.string.text_remove_caps))) {
                        this.f11146t.setText(getString(R.string.text_apply_caps));
                        this.f11146t.setClickable(false);
                        this.f11145s.setFocusable(true);
                        this.f11145s.setFocusableInTouchMode(true);
                        this.f11145s.requestFocus();
                        this.f11145s.setEnabled(true);
                        this.f11145s.setText(Trace.NULL);
                    } else {
                        m14659c();
                        this.f11145s.setClickable(false);
                        this.f11145s.setFocusable(false);
                        this.f11134h.m13237g(new WeakReference(this.f11122M), this.f11145s.getText().toString(), this.f11148v.getText().toString(), f11110c);
                    }
                    this.f11147u.setText(Trace.NULL);
                    this.f11147u.setVisibility(8);
                }
            case R.id.add_ola_money_button:
                Sherlock.m13334a("Ins add ola money");
                this.f11130d = this.f11148v.getText().toString();
                if (TextUtils.isEmpty(this.f11130d)) {
                    this.f11135i.setVisibility(0);
                    this.f11135i.setText(this.f11132f.getString(R.string.ola_recharge_invalid_amount_error_msg));
                    this.f11148v.requestFocus();
                } else if ((Integer.parseInt(this.f11130d) >= 100 && Integer.parseInt(this.f11130d) <= by.DEFAULT_TIMEOUT_MS) || Integer.parseInt(this.f11130d) == Integer.parseInt(((an) this.f11124O.get(0)).getDenomination()) || Integer.parseInt(this.f11130d) == Integer.parseInt(((an) this.f11124O.get(1)).getDenomination()) || Integer.parseInt(this.f11130d) == Integer.parseInt(((an) this.f11124O.get(2)).getDenomination())) {
                    if (this.f11141o.isChecked()) {
                        this.f11113C = this.f11145s.getText().toString();
                        if (this.f11113C == null || TextUtils.isEmpty(this.f11113C)) {
                            this.f11135i.setVisibility(0);
                            this.f11135i.setText("Please enter promo code");
                            this.f11145s.requestFocus();
                            return;
                        }
                    }
                    this.f11135i.setVisibility(8);
                    m14634d();
                } else {
                    this.f11135i.setVisibility(0);
                    this.f11135i.setText(this.f11132f.getString(R.string.ola_recharge_invalid_amount_error_msg));
                    this.f11148v.requestFocus();
                }
            case R.id.use_recharge_code:
                startActivityForResult(new Intent(getActivity(), RechargeWithCodeActivity.class), 1);
            case R.id.mobileVerifyButton:
                m14618a("OlaMoney Verify Number clicked");
                if (TextUtils.isEmpty(this.f11133g.getName()) || TextUtils.isEmpty(this.f11133g.getPhoneNumber())) {
                    View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_messsage_yes_no, null, false);
                    AlertDialog create = new Builder(getActivity()).setView(inflate).create();
                    ((TextView) inflate.findViewById(R.id.item_header)).setText("Invalid Mobile No!");
                    ((TextView) inflate.findViewById(R.id.item_message)).setText("Please update your profile with valid details ");
                    ((Button) inflate.findViewById(R.id.button_yes)).setText("Go to Profile");
                    ((Button) inflate.findViewById(R.id.button_no)).setText("Cancel");
                    inflate.findViewById(R.id.button_yes).setOnClickListener(new OlaMoneyFragment(this, create));
                    inflate.findViewById(R.id.button_no).setOnClickListener(new OlaMoneyFragment(this, create));
                    create.show();
                    return;
                }
                Ola.f11483G = true;
                m14639f();
            default:
        }
    }

    private void m14618a(String str) {
        if (this.f11133g.getOlaBalance() >= 0) {
            Map hashMap = new HashMap();
            hashMap.put("OlaMoney balance", String.valueOf(this.f11133g.getOlaBalance()));
            Localytics.tagEvent(str, hashMap);
        }
    }

    private void m14639f() {
        m14659c();
        this.f11134h.m13204b(new WeakReference(this.f11120K), this.f11133g.getName(), this.f11133g.getPhoneNumber(), f11110c);
    }

    private void m14621a(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            str = this.f11148v.getText().toString();
        }
        Intent intent = new Intent(this.f11132f, RechargePayUActivity.class);
        intent.putExtra(Constants.ARG_AMOUNT, Float.valueOf(str));
        intent.putExtra(Bank.TXN_ID, str2);
        intent.putExtra("surl", str3);
        intent.putExtra(Constants.PRODUCT_INFO, "olaproduct");
        intent.putExtra("furl", str4);
        intent.putExtra(Constants.FIRST_NAME, this.f11133g.getFirstName());
        intent.putExtra(Constants.EMAIL, this.f11133g.getUserLoginEmail());
        intent.putExtra(Constants.PHONE, this.f11133g.getPhoneNumber());
        intent.putExtra(Constants.EXTRA_OLA_DENOMINATION, str);
        intent.putExtra(Constants.EXTRA_OLA_OFFER_TEXT, this.f11142p.getText().toString());
        try {
            Bundle bundle = this.f11132f.getPackageManager().getApplicationInfo(this.f11132f.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS).metaData;
            intent.putExtra("key", bundle.getString("payu_merchant_id"));
            intent.putExtra(Constants.ARG_USER_CREDENTIALS, bundle.getString("payu_merchant_id") + ":" + this.f11133g.getClearUserId());
        } catch (Throwable e) {
            OLog.m13314b(e, "navigateToPayURechargeScreen", new Object[0]);
        }
        startActivityForResult(intent, 100);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        OLog.m13311a("Check box checked? - " + z, new Object[0]);
        if (z) {
            String obj = this.f11148v.getText().toString();
            if (TextUtils.isEmpty(obj) || Integer.parseInt(obj) < 100 || Integer.parseInt(obj) > by.DEFAULT_TIMEOUT_MS) {
                this.f11135i.setVisibility(0);
                this.f11135i.setText(this.f11132f.getString(R.string.ola_recharge_invalid_amount_error_msg));
                this.f11148v.requestFocus();
                this.f11141o.setChecked(false);
                return;
            }
            this.f11144r.setVisibility(0);
            this.f11145s.setVisibility(0);
            this.f11146t.setVisibility(0);
            this.f11145s.requestFocus();
            this.f11145s.setEnabled(true);
            this.f11145s.setClickable(true);
            this.f11145s.setFocusable(true);
            this.f11145s.setFocusableInTouchMode(true);
            this.f11152z.scrollTo(0, this.f11144r.getBottom());
            return;
        }
        if (this.f11132f.getCurrentFocus() != null) {
            ((InputMethodManager) this.f11132f.getSystemService("input_method")).hideSoftInputFromWindow(this.f11132f.getCurrentFocus().getWindowToken(), 0);
        }
        this.f11144r.setVisibility(8);
        this.f11145s.setVisibility(8);
        this.f11146t.setVisibility(8);
        this.f11147u.setText(Trace.NULL);
        this.f11146t.setText(getString(R.string.text_apply_caps));
        this.f11145s.setText(Trace.NULL);
        this.f11145s.setEnabled(false);
        this.f11147u.setVisibility(8);
    }

    private void m14620a(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("min amt", str2);
        hashMap.put("discount", str3);
        Localytics.tagEvent(str, hashMap);
    }

    private void m14613a(View view) {
        if (view.isSelected()) {
            view.setSelected(false);
            this.f11148v.setText(Trace.NULL);
        } else {
            view.setSelected(true);
            String b = m14624b(view.getId());
            this.f11148v.setText(b);
            if (view.getId() == R.id.amount1) {
                this.f11139m.setSelected(false);
                this.f11140n.setSelected(false);
                if (this.f11124O != null) {
                    m14620a("Button One", b, !TextUtils.isEmpty(((an) this.f11124O.get(2)).getOfferText()) ? ((an) this.f11124O.get(2)).getOfferText() : "N/A");
                }
                m14612a(0);
            } else if (view.getId() == R.id.amount2) {
                this.f11138l.setSelected(false);
                this.f11140n.setSelected(false);
                if (this.f11124O != null) {
                    m14620a("Button Two", b, !TextUtils.isEmpty(((an) this.f11124O.get(2)).getOfferText()) ? ((an) this.f11124O.get(2)).getOfferText() : "N/A");
                }
                m14612a(1);
            } else if (view.getId() == R.id.amount3) {
                this.f11138l.setSelected(false);
                this.f11139m.setSelected(false);
                if (this.f11124O != null) {
                    m14620a("Button Three", b, !TextUtils.isEmpty(((an) this.f11124O.get(2)).getOfferText()) ? ((an) this.f11124O.get(2)).getOfferText() : "N/A");
                }
                m14612a(2);
            }
        }
        this.f11135i.setText(Trace.NULL);
        this.f11135i.setVisibility(8);
    }

    private void m14612a(int i) {
        if (i <= 2) {
            try {
                CharSequence offerText = ((an) this.f11124O.get(i)).getOfferText();
                if (offerText == null || TextUtils.isEmpty(offerText)) {
                    this.f11142p.setVisibility(8);
                    this.f11142p.setText(Trace.NULL);
                    return;
                }
                this.f11142p.setVisibility(0);
                this.f11142p.setText(offerText);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.f11142p.setVisibility(8);
        this.f11142p.setText(Trace.NULL);
    }

    private String m14624b(int i) {
        CharSequence denomination;
        switch (i) {
            case R.id.amount1:
                if (this.f11124O != null) {
                    denomination = ((an) this.f11124O.get(0)).getDenomination();
                    this.f11131e = ((an) this.f11124O.get(0)).getOfferId();
                    if (!TextUtils.isEmpty(denomination)) {
                        return denomination;
                    }
                }
                return String.valueOf(Constants.MILLIS_IN_A_SECOND);
            case R.id.amount2:
                if (this.f11124O != null) {
                    denomination = ((an) this.f11124O.get(1)).getDenomination();
                    this.f11131e = ((an) this.f11124O.get(1)).getOfferId();
                    if (!TextUtils.isEmpty(denomination)) {
                        return denomination;
                    }
                }
                return String.valueOf(ActivityTrace.MAX_TRACES);
            case R.id.amount3:
                if (this.f11124O != null && this.f11124O.size() > 2) {
                    denomination = ((an) this.f11124O.get(2)).getDenomination();
                    this.f11131e = ((an) this.f11124O.get(2)).getOfferId();
                    if (!TextUtils.isEmpty(denomination)) {
                        return denomination;
                    }
                }
                return String.valueOf(by.DEFAULT_TIMEOUT_MS);
            default:
                throw new IllegalArgumentException("Unknown view for returning amount");
        }
    }

    private void m14632c(String str, String str2) {
        View inflate = ((LayoutInflater) this.f11132f.getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        if (this.f11114D == null || !this.f11114D.isShowing()) {
            this.f11114D = new Builder(this.f11132f).setView(inflate).create();
            ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
            ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
            inflate.findViewById(R.id.button_ok).setOnClickListener(new OnClickListener() {
                final /* synthetic */ OlaMoneyFragment f11092a;

                {
                    this.f11092a = r1;
                }

                public void onClick(View view) {
                    this.f11092a.f11114D.dismiss();
                }
            });
            this.f11114D.show();
        }
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11107U, (Object) this, (Object) this));
        super.onStart();
        EventBus.m3a().m15a((Object) this);
    }

    public void onResume() {
        org.p087a.p088a.JoinPoint a = Factory.m15067a(f11108V, (Object) this, (Object) this);
        try {
            ActivityTraceAspect.m12823a().m12826b(a);
            super.onResume();
            if (Utils.m14909a(this.f11132f)) {
                m14659c();
                this.f11134h.m13203b(new WeakReference(this.f11121L), "summary", f11110c);
                m14643h();
            } else {
                m14641g();
            }
            if (PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getBoolean(Constants.PREF_IS_VERIFIED, false)) {
                this.f11128a.setVisibility(8);
            } else {
                this.f11128a.setVisibility(0);
            }
            ActivityTraceAspect.m12823a().m12827c(a);
        } catch (Throwable th) {
            ActivityTraceAspect.m12823a().m12827c(a);
        }
    }

    public void onEvent(ah ahVar) {
        if (ahVar.isConnected()) {
            m14643h();
            this.f11134h.m13203b(new WeakReference(this.f11121L), "summary", f11110c);
            return;
        }
        m14641g();
    }

    private void m14641g() {
        this.f11118H.setVisibility(0);
        m14658b();
    }

    private void m14643h() {
        m14658b();
        this.f11118H.setVisibility(8);
    }

    public void onPause() {
        ActivityTraceAspect.m12823a().m12826b(Factory.m15067a(f11109W, (Object) this, (Object) this));
        super.onPause();
        this.f11134h.m13169a(f11110c);
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EventBus.m3a().m17b(this);
        super.onStop();
        if (Utils.m14924g(getActivity().getIntent().getStringExtra(Constants.PUSH_LANDING))) {
            getActivity().getIntent().removeExtra(Constants.PUSH_LANDING);
        }
    }

    private void m14627b(String str) {
        this.f11134h.m13232f(new WeakReference(this.f11127R), str, f11110c);
    }

    public void m14658b() {
        if (this.f11112B.isShowing()) {
            this.f11112B.dismiss();
        }
    }

    public void m14659c() {
        if (!this.f11112B.isShowing()) {
            this.f11112B.show();
        }
    }

    private void m14645i() {
        this.f11144r.setVisibility(8);
        this.f11145s.setVisibility(8);
        this.f11146t.setVisibility(8);
        this.f11141o.setChecked(false);
        this.f11145s.setFocusableInTouchMode(true);
        this.f11144r.setFocusableInTouchMode(true);
        this.f11144r.setFocusable(true);
        this.f11145s.setClickable(true);
        this.f11145s.setFocusable(true);
        this.f11145s.setEnabled(true);
        this.f11146t.setText(getString(R.string.text_apply_caps));
        this.f11145s.setText(Trace.NULL);
    }

    private String m14630c(String str) {
        if (f11104J.containsKey(str)) {
            return (String) f11104J.get(str);
        }
        return "UNKNOWN";
    }

    private void m14635d(String str) {
        Map hashMap = new HashMap();
        hashMap.put("Failure_reason", str);
        Localytics.tagEvent("Payment Failure", hashMap);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        OLog.m13311a("onActivityResult. Req code - " + i + "; res code - " + i2, new Object[0]);
        if (Utils.m14909a(this.f11132f)) {
            this.f11134h.m13203b(new WeakReference(this.f11121L), "summary", f11110c);
        }
        if (i == 100 && (i2 == -1 || i2 == 0)) {
            m14627b(this.f11134h.m13218d().getPayUOrderId());
        }
        super.onActivityResult(i, i2, intent);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = this.f11148v.getText().toString();
        if (obj != null && !TextUtils.isEmpty(obj)) {
            if (obj.equals(m14624b((int) R.id.amount1))) {
                m14612a(0);
                this.f11138l.setSelected(true);
                this.f11139m.setSelected(false);
                this.f11140n.setSelected(false);
            } else if (obj.equals(m14624b((int) R.id.amount2))) {
                m14612a(1);
                this.f11139m.setSelected(true);
                this.f11138l.setSelected(false);
                this.f11140n.setSelected(false);
            } else if (obj.equals(m14624b((int) R.id.amount3))) {
                m14612a(2);
                this.f11140n.setSelected(true);
                this.f11138l.setSelected(false);
                this.f11139m.setSelected(false);
            } else {
                m14612a(3);
                this.f11138l.setSelected(false);
                this.f11139m.setSelected(false);
                this.f11140n.setSelected(false);
            }
        }
    }
}
