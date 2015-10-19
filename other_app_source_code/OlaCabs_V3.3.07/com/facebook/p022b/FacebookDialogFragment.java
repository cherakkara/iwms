package com.facebook.p022b;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.p022b.WebDialog.WebDialog;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
/* renamed from: com.facebook.b.i */
public class FacebookDialogFragment extends DialogFragment implements TraceFieldInterface {
    private Dialog f760a;

    /* renamed from: com.facebook.b.i.1 */
    class FacebookDialogFragment implements WebDialog {
        final /* synthetic */ FacebookDialogFragment f758a;

        FacebookDialogFragment(FacebookDialogFragment facebookDialogFragment) {
            this.f758a = facebookDialogFragment;
        }

        public void m939a(Bundle bundle, FacebookException facebookException) {
            this.f758a.m942a(bundle, facebookException);
        }
    }

    /* renamed from: com.facebook.b.i.2 */
    class FacebookDialogFragment implements WebDialog {
        final /* synthetic */ FacebookDialogFragment f759a;

        FacebookDialogFragment(FacebookDialogFragment facebookDialogFragment) {
            this.f759a = facebookDialogFragment;
        }

        public void m940a(Bundle bundle, FacebookException facebookException) {
            this.f759a.m941a(bundle);
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

    public void m945a(Dialog dialog) {
        this.f760a = dialog;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("i");
        try {
            TraceMachine.enterMethod(this._nr_trace, "i#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "i#onCreate", null);
                break;
            }
        }
        super.onCreate(bundle);
        if (this.f760a == null) {
            Dialog facebookWebFallbackDialog;
            Context activity = getActivity();
            Bundle c = NativeProtocol.m1057c(activity.getIntent());
            String string;
            if (c.getBoolean("is_fallback", false)) {
                string = c.getString("url");
                if (Utility.m1126a(string)) {
                    Utility.m1134b("FacebookDialogFragment", "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    TraceMachine.exitMethod();
                    return;
                }
                facebookWebFallbackDialog = new FacebookWebFallbackDialog(activity, string, String.format("fb%s://bridge/", new Object[]{FacebookSdk.m1210h()}));
                facebookWebFallbackDialog.m964a(new FacebookDialogFragment(this));
            } else {
                string = c.getString("action");
                c = c.getBundle("params");
                if (Utility.m1126a(string)) {
                    Utility.m1134b("FacebookDialogFragment", "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    TraceMachine.exitMethod();
                    return;
                }
                facebookWebFallbackDialog = new WebDialog(activity, string, c).m1152a(new FacebookDialogFragment(this)).m1153a();
            }
            this.f760a = facebookWebFallbackDialog;
        }
        TraceMachine.exitMethod();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return this.f760a;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f760a instanceof WebDialog) {
            ((WebDialog) this.f760a).m970d();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void m942a(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        activity.setResult(facebookException == null ? -1 : 0, NativeProtocol.m1045a(activity.getIntent(), bundle, facebookException));
        activity.finish();
    }

    private void m941a(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
