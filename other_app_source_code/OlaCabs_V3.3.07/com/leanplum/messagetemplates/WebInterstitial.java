package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.Context;
import com.leanplum.Leanplum;

public class WebInterstitial extends BaseMessageDialog {
    public WebInterstitial(Activity activity, WebInterstitialOptions webInterstitialOptions) {
        super(activity, true, null, webInterstitialOptions);
        this.webOptions = webInterstitialOptions;
    }

    public static void register(Context context) {
        Leanplum.defineAction("Web Interstitial", Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, WebInterstitialOptions.toArgs(context), new C0662w());
    }
}
