package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.R.R;
import com.facebook.login.LoginClient.C0174a;
import com.facebook.login.LoginClient.C0175b;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.login.LoginClient.Result.C0173a;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
/* renamed from: com.facebook.login.d */
public class LoginFragment extends Fragment implements TraceFieldInterface {
    private String f1005a;
    private LoginClient f1006b;
    private Request f1007c;

    /* renamed from: com.facebook.login.d.1 */
    class LoginFragment implements C0175b {
        final /* synthetic */ LoginFragment f1002a;

        LoginFragment(LoginFragment loginFragment) {
            this.f1002a = loginFragment;
        }

        public void m1314a(Result result) {
            this.f1002a.m1317a(result);
        }
    }

    /* renamed from: com.facebook.login.d.2 */
    class LoginFragment implements C0174a {
        final /* synthetic */ View f1003a;
        final /* synthetic */ LoginFragment f1004b;

        LoginFragment(LoginFragment loginFragment, View view) {
            this.f1004b = loginFragment;
            this.f1003a = view;
        }

        public void m1315a() {
            this.f1003a.findViewById(R.com_facebook_login_activity_progress_bar).setVisibility(0);
        }

        public void m1316b() {
            this.f1003a.findViewById(R.com_facebook_login_activity_progress_bar).setVisibility(8);
        }
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("d");
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        if (bundle != null) {
            this.f1006b = (LoginClient) bundle.getParcelable("loginClient");
            this.f1006b.m1279a((Fragment) this);
        } else {
            this.f1006b = new LoginClient((Fragment) this);
        }
        this.f1005a = getActivity().getCallingActivity().getPackageName();
        this.f1007c = (Request) getActivity().getIntent().getParcelableExtra("request");
        this.f1006b.m1283a(new LoginFragment(this));
        TraceMachine.exitMethod();
    }

    public void onDestroy() {
        this.f1006b.m1291f();
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "d#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "d#onCreateView", null);
                break;
            }
        }
        View inflate = layoutInflater.inflate(R.com_facebook_login_fragment, viewGroup, false);
        this.f1006b.m1282a(new LoginFragment(this, inflate));
        TraceMachine.exitMethod();
        return inflate;
    }

    private void m1317a(Result result) {
        this.f1007c = null;
        int i = result.f969a == C0173a.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.facebook.LoginFragment:Result", result);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        getActivity().setResult(i, intent);
        getActivity().finish();
    }

    public void onResume() {
        super.onResume();
        if (this.f1005a == null) {
            Log.e("LoginActivityFragment", "Cannot call LoginActivity with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            getActivity().finish();
            return;
        }
        this.f1006b.m1280a(this.f1007c);
    }

    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.f1006b.m1284a(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", this.f1006b);
    }
}
