package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import com.google.android.gms.internal.bi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.common.internal.h */
public final class C0419h {
    private final Account f2235a;
    private final List<String> f2236b;
    private final int f2237c;
    private final View f2238d;
    private final String f2239e;
    private final String f2240f;
    private final bi f2241g;
    private Integer f2242h;

    public C0419h(Account account, Collection<String> collection, int i, View view, String str, String str2, bi biVar) {
        this.f2235a = account;
        this.f2236b = collection == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(new ArrayList(collection));
        this.f2238d = view;
        this.f2237c = i;
        this.f2239e = str;
        this.f2240f = str2;
        this.f2241g = biVar;
    }

    @Deprecated
    public String m3573a() {
        return this.f2235a != null ? this.f2235a.name : null;
    }

    public void m3574a(Integer num) {
        this.f2242h = num;
    }

    public Account m3575b() {
        return this.f2235a;
    }

    public Account m3576c() {
        return this.f2235a != null ? this.f2235a : new Account("<<default account>>", "com.google");
    }

    public List<String> m3577d() {
        return this.f2236b;
    }

    public String m3578e() {
        return this.f2239e;
    }

    public String m3579f() {
        return this.f2240f;
    }

    public bi m3580g() {
        return this.f2241g;
    }

    public Integer m3581h() {
        return this.f2242h;
    }
}
