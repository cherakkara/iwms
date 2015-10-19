package com.google.android.m4b.maps.p043e;

import android.content.Context;
import android.os.Looper;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p047g.ClientSettings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.e.a */
public final class Api {
    private final Api<?, O> f7310a;
    private final Api<?> f7311b;
    private final ArrayList<Scope> f7312c;

    /* renamed from: com.google.android.m4b.maps.e.a.a */
    public interface Api {
        void m10235a();

        void m10236b();

        boolean m10237c();

        Looper m10238d();
    }

    /* renamed from: com.google.android.m4b.maps.e.a.b */
    public interface Api<T extends Api, O> {
        T m10239a(Context context, Looper looper, ClientSettings clientSettings, O o, GoogleApiClient googleApiClient, GooglePlayServicesClient googlePlayServicesClient);
    }

    /* renamed from: com.google.android.m4b.maps.e.a.c */
    public static final class Api<C extends Api> {
    }

    public <C extends Api> Api(Api<C, O> api, Api<C> api2, Scope... scopeArr) {
        this.f7310a = api;
        this.f7311b = api2;
        this.f7312c = new ArrayList(Arrays.asList(scopeArr));
    }

    public final Api<?, O> m10240a() {
        return this.f7310a;
    }

    public final List<Scope> m10241b() {
        return this.f7312c;
    }

    public final Api<?> m10242c() {
        return this.f7311b;
    }
}
