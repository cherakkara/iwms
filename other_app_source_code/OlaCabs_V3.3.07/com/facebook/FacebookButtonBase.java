package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.p023a.AppEventsLogger;

/* renamed from: com.facebook.e */
public abstract class FacebookButtonBase extends Button {
    private String f902a;
    private OnClickListener f903b;
    private OnClickListener f904c;
    private Fragment f905d;
    private int f906e;

    public void setFragment(Fragment fragment) {
        this.f905d = fragment;
    }

    public Fragment getFragment() {
        return this.f905d;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f903b = onClickListener;
    }

    protected void setRequestCode(int i) {
        if (FacebookSdk.m1199a(i)) {
            throw new IllegalArgumentException("Request code " + i + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.f906e = i;
    }

    public int getRequestCode() {
        return this.f906e;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        m1184a(getContext());
    }

    protected Activity getActivity() {
        Context context = getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        throw new FacebookException("Unable to get Activity.");
    }

    protected int getDefaultStyleResource() {
        return 0;
    }

    protected void setInternalOnClickListener(OnClickListener onClickListener) {
        this.f904c = onClickListener;
    }

    private void m1184a(Context context) {
        AppEventsLogger.m830a(context).m856a(this.f902a, null, null);
    }
}
