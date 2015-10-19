package com.leanplum.messagetemplates;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.leanplum.Leanplum;

/* renamed from: com.leanplum.messagetemplates.h */
final class C0648h extends WebViewClient {
    private /* synthetic */ BaseMessageDialog f8842a;

    C0648h(BaseMessageDialog baseMessageDialog) {
        this.f8842a = baseMessageDialog;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.contains(this.f8842a.webOptions.getCloseUrl())) {
            return false;
        }
        this.f8842a.cancel();
        String[] split = str.split("\\?");
        if (split.length <= 1) {
            return true;
        }
        for (String split2 : split[1].split("&")) {
            String[] split3 = split2.split("=");
            if (split3.length > 1 && split3[0].equals("result")) {
                Leanplum.track(split3[1]);
            }
        }
        return true;
    }
}
