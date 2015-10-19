package com.olacabs.customer.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.olacabs.customer.R;

/* renamed from: com.olacabs.customer.app.c */
public class CallEmergencyContactCommand {
    private static final String f8996a;
    private String f8997b;

    static {
        f8996a = CallEmergencyContactCommand.class.getSimpleName();
    }

    public CallEmergencyContactCommand(String str) {
        this.f8997b = str;
    }

    public void m12888a(Context context) {
        OLog.m13311a("CallEmergencyContactCommand execute", new Object[0]);
        Intent intent = new Intent("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + this.f8997b));
        try {
            context.startActivity(intent);
        } catch (Throwable e) {
            OLog.m13316c(e, "Failed to place call to emergency contact!", new Object[0]);
            Toast.makeText(context, context.getString(R.string.toast_failed_to_call_emergency), 0).show();
        }
    }
}
