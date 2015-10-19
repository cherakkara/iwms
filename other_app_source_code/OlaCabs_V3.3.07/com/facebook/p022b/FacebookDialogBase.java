package com.facebook.p022b;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.util.List;

/* renamed from: com.facebook.b.h */
public abstract class FacebookDialogBase<CONTENT, RESULT> {
    protected static final Object f753a;
    private final Activity f754b;
    private final Fragment f755c;
    private List<FacebookDialogBase> f756d;
    private int f757e;

    /* renamed from: com.facebook.b.h.a */
    protected abstract class FacebookDialogBase {
        final /* synthetic */ FacebookDialogBase f752a;

        public abstract boolean m928a(CONTENT content);

        public abstract AppCall m929b(CONTENT content);

        protected FacebookDialogBase(FacebookDialogBase facebookDialogBase) {
            this.f752a = facebookDialogBase;
        }

        public Object m927a() {
            return FacebookDialogBase.f753a;
        }
    }

    protected abstract List<FacebookDialogBase> m936c();

    protected abstract AppCall m937d();

    static {
        f753a = new Object();
    }

    protected FacebookDialogBase(Activity activity, int i) {
        Validate.m1146a((Object) activity, "activity");
        this.f754b = activity;
        this.f755c = null;
        this.f757e = i;
    }

    protected FacebookDialogBase(Fragment fragment, int i) {
        Validate.m1146a((Object) fragment, "fragment");
        this.f755c = fragment;
        this.f754b = null;
        this.f757e = i;
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    public int m932a() {
        return this.f757e;
    }

    public void m933a(CONTENT content) {
        m934a(content, f753a);
    }

    protected void m934a(CONTENT content, Object obj) {
        AppCall b = m930b(content, obj);
        if (b == null) {
            String str = "No code path should ever result in a null appCall";
            Log.e("FacebookDialog", str);
            if (FacebookSdk.m1201b()) {
                throw new IllegalStateException(str);
            }
        } else if (this.f755c != null) {
            DialogPresenter.m917a(b, this.f755c);
        } else {
            DialogPresenter.m915a(b, this.f754b);
        }
    }

    protected Activity m935b() {
        if (this.f754b != null) {
            return this.f754b;
        }
        if (this.f755c != null) {
            return this.f755c.getActivity();
        }
        return null;
    }

    private AppCall m930b(CONTENT content, Object obj) {
        Object obj2;
        AppCall b;
        if (obj == f753a) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        for (FacebookDialogBase facebookDialogBase : m931e()) {
            if ((obj2 != null || Utility.m1125a(facebookDialogBase.m927a(), obj)) && facebookDialogBase.m928a(content)) {
                try {
                    b = facebookDialogBase.m929b(content);
                    break;
                } catch (FacebookException e) {
                    b = m937d();
                    DialogPresenter.m919a(b, e);
                }
            }
        }
        b = null;
        if (b != null) {
            return b;
        }
        b = m937d();
        DialogPresenter.m914a(b);
        return b;
    }

    private List<FacebookDialogBase> m931e() {
        if (this.f756d == null) {
            this.f756d = m936c();
        }
        return this.f756d;
    }
}
