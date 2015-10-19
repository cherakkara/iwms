package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.C0453u;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;

@Instrumented
/* renamed from: com.google.android.gms.common.f */
public class C0271f extends DialogFragment implements TraceFieldInterface {
    private Dialog f2130a;
    private OnCancelListener f2131b;

    public C0271f() {
        this.f2130a = null;
        this.f2131b = null;
    }

    public static C0271f m3400a(Dialog dialog, OnCancelListener onCancelListener) {
        C0271f c0271f = new C0271f();
        Dialog dialog2 = (Dialog) C0453u.m3847a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        c0271f.f2130a = dialog2;
        if (onCancelListener != null) {
            c0271f.f2131b = onCancelListener;
        }
        return c0271f;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2131b != null) {
            this.f2131b.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.f2130a == null) {
            setShowsDialog(false);
        }
        return this.f2130a;
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
