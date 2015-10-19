package com.olacabs.customer.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.olacabs.customer.R;

/* renamed from: com.olacabs.customer.app.d */
public class CallSupportCommand {
    private static final String f8998a;
    private String f8999b;

    static {
        f8998a = CallSupportCommand.class.getSimpleName();
    }

    public CallSupportCommand(String str) {
        this.f8999b = str;
    }

    public void m12889a(Context context) {
        String string;
        OLog.m13311a("CallSupportCommand execute", new Object[0]);
        Intent intent = new Intent("android.intent.action.CALL");
        if (TextUtils.isEmpty(this.f8999b)) {
            string = context.getString(R.string.default_call_center_number);
        } else {
            string = this.f8999b;
        }
        intent.setData(Uri.parse("tel:" + string));
        try {
            context.startActivity(intent);
        } catch (Throwable e) {
            OLog.m13316c(e, "Failed to place call to call center!", new Object[0]);
        }
    }
}
