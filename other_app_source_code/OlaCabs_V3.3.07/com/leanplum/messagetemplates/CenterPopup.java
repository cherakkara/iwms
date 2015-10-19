package com.leanplum.messagetemplates;

import android.app.Activity;
import android.content.Context;
import com.leanplum.Leanplum;

public class CenterPopup extends BaseMessageDialog {
    public CenterPopup(Activity activity, CenterPopupOptions centerPopupOptions) {
        super(activity, false, centerPopupOptions, null);
        this.options = centerPopupOptions;
    }

    public static void register(Context context) {
        Leanplum.defineAction("Center Popup", Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION, CenterPopupOptions.toArgs(context), new C0650k());
    }
}
