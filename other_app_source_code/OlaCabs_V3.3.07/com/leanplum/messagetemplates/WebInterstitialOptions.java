package com.leanplum.messagetemplates;

import android.content.Context;
import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;

public class WebInterstitialOptions {
    private String f8834a;
    private String f8835b;
    private boolean f8836c;

    protected WebInterstitialOptions(ActionContext actionContext) {
        this.f8834a = actionContext.stringNamed("URL");
        this.f8836c = actionContext.booleanNamed("Has dismiss button");
        this.f8835b = actionContext.stringNamed("Close URL");
    }

    public String getUrl() {
        return this.f8834a;
    }

    public boolean hasDismissButton() {
        return this.f8836c;
    }

    public String getCloseUrl() {
        return this.f8835b;
    }

    public static ActionArgs toArgs(Context context) {
        return new ActionArgs().with("URL", "http://www.example.com").with("Close URL", "http://leanplum:close").with("Has dismiss button", Boolean.valueOf(true));
    }
}
