package com.olacabs.customer.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Telephony.Sms;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.C0186a;
import com.facebook.share.p024a.ShareDialog;
import com.localytics.android.Localytics;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.R;
import com.olacabs.customer.app.DataManager;
import com.olacabs.customer.app.OlaApp;
import com.olacabs.customer.app.Sherlock;
import com.olacabs.customer.ui.widgets.ErrorView.ErrorView;
import com.olacabs.customer.utils.Constants;
import com.olacabs.p067a.p068a.p069a.p073c.ActivityTraceAspect;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p090b.p091a.Factory;

@Instrumented
/* renamed from: com.olacabs.customer.ui.n */
public class InvitesFragment extends Fragment implements OnClickListener, TraceFieldInterface {
    public static final String f10870a;
    private static final JoinPoint f10871t = null;
    private static final JoinPoint f10872u = null;
    final String f10873b;
    final String f10874c;
    final String f10875d;
    final String f10876e;
    private TextView f10877f;
    private TextView f10878g;
    private String f10879h;
    private String f10880i;
    private LinearLayout f10881j;
    private LinearLayout f10882k;
    private LinearLayout f10883l;
    private TextView f10884m;
    private TextView f10885n;
    private TextView f10886o;
    private List<String> f10887p;
    private boolean f10888q;
    private SharedPreferences f10889r;
    private DataManager f10890s;

    /* renamed from: com.olacabs.customer.ui.n.1 */
    class InvitesFragment implements OnClickListener {
        final /* synthetic */ AlertDialog f10868a;
        final /* synthetic */ InvitesFragment f10869b;

        InvitesFragment(InvitesFragment invitesFragment, AlertDialog alertDialog) {
            this.f10869b = invitesFragment;
            this.f10868a = alertDialog;
        }

        public void onClick(View view) {
            this.f10868a.dismiss();
        }
    }

    private static void m14462h() {
        Factory factory = new Factory("InvitesFragment.java", InvitesFragment.class);
        f10871t = factory.m15070a("method-execution", factory.m15071a("1", "onCreate", "com.olacabs.customer.ui.InvitesFragment", "android.os.Bundle", "savedInstanceState", Trace.NULL, "void"), 80);
        f10872u = factory.m15070a("method-execution", factory.m15071a("1", "onCreateView", "com.olacabs.customer.ui.InvitesFragment", "android.view.LayoutInflater:android.view.ViewGroup:android.os.Bundle", "inflater:container:savedInstanceState", Trace.NULL, "android.view.View"), 88);
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    static {
        InvitesFragment.m14462h();
        f10870a = InvitesFragment.class.getSimpleName();
    }

    public InvitesFragment() {
        this.f10873b = "com.twitter.applib.PostActivity";
        this.f10874c = "com.twitter.android.PostActivity";
        this.f10875d = "com.twitter.android.composer.TextFirstComposerActivity";
        this.f10876e = "com.twitter.android.composer.ComposerActivity";
        this.f10887p = Arrays.asList(new String[]{"publish_stream"});
        this.f10888q = false;
    }

    public static InvitesFragment m14452a() {
        return new InvitesFragment();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("n");
        try {
            TraceMachine.enterMethod(this._nr_trace, "n#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "n#onCreate", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15068a(f10871t, (Object) this, (Object) this, (Object) bundle));
        super.onCreate(bundle);
        this.f10890s = ((OlaApp) getActivity().getApplication()).m12878a();
        Localytics.tagScreen("Invite & Earn");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "n#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "n#onCreateView", null);
                break;
            }
        }
        ActivityTraceAspect.m12823a().m12826b(Factory.m15069a(f10872u, (Object) this, (Object) this, new Object[]{layoutInflater, viewGroup, bundle}));
        View inflate = layoutInflater.inflate(R.layout.fragment_invites, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.textView_referal_code);
        this.f10877f = (TextView) inflate.findViewById(R.id.item_referrer_earns);
        this.f10878g = (TextView) inflate.findViewById(R.id.item_referred_earns);
        this.f10881j = (LinearLayout) inflate.findViewById(R.id.layout_friends_earn);
        this.f10882k = (LinearLayout) inflate.findViewById(R.id.layout_you_earn);
        this.f10883l = (LinearLayout) inflate.findViewById(R.id.layout_share_referal_code);
        this.f10884m = (TextView) inflate.findViewById(R.id.no_referral_text_1);
        this.f10885n = (TextView) inflate.findViewById(R.id.no_referral_text_2);
        inflate.findViewById(R.id.button_navigation_drawer).setOnClickListener(this);
        inflate.findViewById(R.id.button_invite_by_sms).setOnClickListener(this);
        inflate.findViewById(R.id.button_invite_by_email).setOnClickListener(this);
        inflate.findViewById(R.id.button_invite_by_whatsapp).setOnClickListener(this);
        inflate.findViewById(R.id.button_invite_by_twitter).setOnClickListener(this);
        this.f10886o = (TextView) inflate.findViewById(R.id.button_earnings);
        this.f10886o.setOnClickListener(this);
        View findViewById = inflate.findViewById(R.id.button_invite_by_facebook);
        findViewById.setOnClickListener(this);
        if (!ShareDialog.m1408a(ShareLinkContent.class)) {
            findViewById.setVisibility(8);
        }
        this.f10889r = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.f10879h = this.f10889r.getString(Constants.PREF_REFERRAL_CODE, Trace.NULL);
        textView.setText(this.f10879h);
        this.f10888q = this.f10889r.getBoolean(Constants.PREF_IS_SCHEME_ON, false);
        this.f10880i = getActivity().getString(R.string.rs_symbol) + this.f10889r.getInt(Constants.PREF_REFERRER_EARNS, 0);
        m14463a(Boolean.valueOf(this.f10888q));
        TraceMachine.exitMethod();
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_navigation_drawer:
                ((MainActivity) getActivity()).m13607d();
            case R.id.button_earnings:
                startActivity(new Intent(getActivity(), InvitedListActivity.class));
            case R.id.button_invite_by_sms:
                if (this.f10879h == null) {
                    m14461g();
                } else {
                    m14460f();
                }
            case R.id.button_invite_by_email:
                if (this.f10879h == null) {
                    m14461g();
                } else {
                    m14459e();
                }
            case R.id.button_invite_by_facebook:
                if (this.f10879h == null) {
                    m14461g();
                } else {
                    m14457c();
                }
            case R.id.button_invite_by_whatsapp:
                if (this.f10879h == null) {
                    m14461g();
                } else {
                    m14458d();
                }
            case R.id.button_invite_by_twitter:
                if (this.f10879h == null) {
                    m14461g();
                } else {
                    m14456b();
                }
            default:
        }
    }

    private void m14453a(String str) {
        Map hashMap = new HashMap();
        if (this.f10890s.m13209c() != null) {
            hashMap.put("Channel", str);
            hashMap.put("City", Sherlock.m13349d(this.f10890s.m13209c().getCity()));
            Localytics.tagEvent("Invite and Earn clicked", hashMap);
        }
    }

    private void m14456b() {
        try {
            Object obj;
            String b = m14455b("twit");
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.putExtra("android.intent.extra.TEXT", b);
            List queryIntentActivities = getActivity().getPackageManager().queryIntentActivities(intent, 0);
            int size = queryIntentActivities.size();
            for (int i = 0; i < size; i++) {
                ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                if ("com.twitter.applib.PostActivity".equals(resolveInfo.activityInfo.name) || "com.twitter.android.PostActivity".equals(resolveInfo.activityInfo.name) || "com.twitter.android.composer.TextFirstComposerActivity".equals(resolveInfo.activityInfo.name) || "com.twitter.android.composer.ComposerActivity".equalsIgnoreCase(resolveInfo.activityInfo.name)) {
                    String str;
                    if ("com.twitter.android.composer.TextFirstComposerActivity".equals(resolveInfo.activityInfo.name)) {
                        str = "com.twitter.android.composer.TextFirstComposerActivity";
                    } else if ("com.twitter.android.composer.ComposerActivity".equalsIgnoreCase(resolveInfo.activityInfo.name)) {
                        str = "com.twitter.android.composer.ComposerActivity";
                    } else {
                        str = "com.twitter.applib.PostActivity".equals(resolveInfo.activityInfo.name) ? "com.twitter.applib.PostActivity" : "com.twitter.android.PostActivity";
                    }
                    obj = 1;
                    intent.addCategory("android.intent.category.LAUNCHER");
                    intent.setClassName("com.twitter.android", str);
                    startActivity(intent);
                    if (obj == null) {
                        m14454a(getActivity().getString(R.string.twitter_not_found), getActivity().getString(R.string.sorry_header));
                    }
                }
            }
            obj = null;
            if (obj == null) {
                m14454a(getActivity().getString(R.string.twitter_not_found), getActivity().getString(R.string.sorry_header));
            }
        } catch (ActivityNotFoundException e) {
            m14454a(getActivity().getString(R.string.twitter_not_found), getActivity().getString(R.string.sorry_header));
        }
    }

    private void m14457c() {
        String str;
        m14453a("fb");
        if (this.f10888q) {
            str = getString(R.string.share_text_1) + getString(R.string.share_text_2) + this.f10879h + getString(R.string.share_text_3) + this.f10880i;
        } else {
            str = getString(R.string.share_text_1);
        }
        new ShareDialog(this).m933a(((C0186a) new C0186a().m1648b(getString(R.string.fb_share_name)).m1634a(Uri.parse(this.f10889r.getString("fb", getString(R.string.fb_share_link))))).m1645a(str).m1647b(Uri.parse(getString(R.string.fb_share_logo))).m1646a());
    }

    private void m14458d() {
        String b = m14455b("wapp");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        intent.setPackage("com.whatsapp");
        if (intent != null) {
            intent.putExtra("android.intent.extra.TEXT", b);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                m14454a(getActivity().getString(R.string.whatsapp_not_installed), getActivity().getString(R.string.sorry_header));
            }
        }
    }

    private void m14459e() {
        String b = m14455b("mail");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.SUBJECT", getActivity().getString(R.string.tried_olacabs));
        intent.putExtra("android.intent.extra.TEXT", b);
        try {
            startActivity(Intent.createChooser(intent, getActivity().getString(R.string.email_chooser)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), getActivity().getString(R.string.no_email_clients_installed), 0).show();
        }
    }

    private void m14460f() {
        String b = m14455b("sms");
        if (VERSION.SDK_INT >= 19) {
            String defaultSmsPackage = Sms.getDefaultSmsPackage(getActivity());
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.putExtra("android.intent.extra.TEXT", b);
            if (defaultSmsPackage != null) {
                intent.setPackage(defaultSmsPackage);
            }
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse("sms:"));
        intent2.putExtra("sms_body", b);
        startActivity(intent2);
    }

    private String m14455b(String str) {
        m14453a(str);
        String string = this.f10889r.getString(str, getString(R.string.share_text_url));
        if (this.f10888q) {
            return getActivity().getString(R.string.share_text_1) + getActivity().getString(R.string.share_text_2) + this.f10879h + getActivity().getString(R.string.share_text_3) + this.f10880i + getActivity().getString(R.string.share_text_prefix) + string;
        }
        return getActivity().getString(R.string.share_text_1) + getActivity().getString(R.string.share_text_prefix) + string;
    }

    private void m14454a(String str, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.view_dialog_ok_button, null, false);
        AlertDialog create = new Builder(getActivity()).setView(inflate).create();
        ((TextView) inflate.findViewById(R.id.item_header)).setText(str2);
        ((TextView) inflate.findViewById(R.id.item_message)).setText(str);
        inflate.findViewById(R.id.button_ok).setOnClickListener(new InvitesFragment(this, create));
        create.show();
    }

    public void m14463a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f10878g.setText(getActivity().getString(R.string.rs_symbol) + this.f10889r.getInt(Constants.PREF_REFERRED_EARNS, 0));
            this.f10877f.setText(getActivity().getString(R.string.rs_symbol) + this.f10889r.getInt(Constants.PREF_REFERRER_EARNS, 0));
            return;
        }
        this.f10881j.setVisibility(8);
        this.f10882k.setVisibility(8);
        this.f10883l.setVisibility(8);
        this.f10886o.setVisibility(8);
        this.f10884m.setVisibility(0);
        this.f10885n.setVisibility(0);
    }

    private void m14461g() {
        new ErrorView(getActivity()).m14824a(getActivity().getString(R.string.sorry_header)).m14827b(getActivity().getString(R.string.referral_scheme_not_available)).m14826b(getView()).m14825a().m14831a(this.f10878g);
    }
}
