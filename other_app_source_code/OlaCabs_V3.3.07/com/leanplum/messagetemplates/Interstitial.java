package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.Context;
import com.leanplum.Leanplum;

public class Interstitial extends BaseMessageDialog {
    public Interstitial(Activity activity, InterstitialOptions interstitialOptions) {
        super(activity, true, interstitialOptions, null);
        this.options = interstitialOptions;
    }

    public static void register(Context context) {
        Leanplum.defineAction("Interstitial", Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, InterstitialOptions.toArgs(context), new C0658s());
    }
}
