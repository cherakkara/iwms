package com.leanplum.messagetemplates;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.leanplum.ActionContext;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;

/* renamed from: com.leanplum.messagetemplates.v */
final class C0661v extends ActionCallback {
    C0661v() {
    }

    public final boolean onResponse(ActionContext actionContext) {
        String stringNamed = actionContext.stringNamed("URL");
        try {
            LeanplumActivityHelper.getCurrentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringNamed)));
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("Leanplum", "Unable to handle URL " + stringNamed);
            return false;
        }
    }
}
