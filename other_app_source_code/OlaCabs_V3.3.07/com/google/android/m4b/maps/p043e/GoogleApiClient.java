package com.google.android.m4b.maps.p043e;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.p043e.Api.Api;
import com.google.android.m4b.maps.p043e.BaseImplementation.BaseImplementation;
import com.google.android.m4b.maps.p047g.ClientSettings;
import com.google.android.m4b.maps.p047g.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.e.c */
public interface GoogleApiClient {

    /* renamed from: com.google.android.m4b.maps.e.c.b */
    public interface GoogleApiClient {
        void m9431a(int i);

        void m9432a(Bundle bundle);
    }

    /* renamed from: com.google.android.m4b.maps.e.c.a */
    public static final class GoogleApiClient {
        private final Set<String> f7325a;
        private String f7326b;
        private final Context f7327c;
        private final Map f7328d;
        private Looper f7329e;
        private final Set<GoogleApiClient> f7330f;
        private final Set<GooglePlayServicesClient> f7331g;

        public GoogleApiClient(Context context) {
            this.f7325a = new HashSet();
            this.f7328d = new HashMap();
            this.f7330f = new HashSet();
            this.f7331g = new HashSet();
            this.f7327c = context;
            this.f7329e = context.getMainLooper();
            this.f7326b = context.getPackageName();
        }

        public final GoogleApiClient m10264a(GoogleApiClient googleApiClient) {
            this.f7330f.add(googleApiClient);
            return this;
        }

        public final GoogleApiClient m10262a(GooglePlayServicesClient googlePlayServicesClient) {
            this.f7331g.add(googlePlayServicesClient);
            return this;
        }

        public final GoogleApiClient m10263a(Api api) {
            this.f7328d.put(api, null);
            List b = api.m10241b();
            int size = b.size();
            for (int i = 0; i < size; i++) {
                this.f7325a.add(((Scope) b.get(i)).m10297a());
            }
            return this;
        }

        public final GoogleApiClient m10265a() {
            Preconditions.m10466b(!this.f7328d.isEmpty(), "must call addApi() to add at least one API");
            return new GoogleApiClientImpl(this.f7327c, this.f7329e, new ClientSettings(null, this.f7325a, 0, null, this.f7326b), this.f7328d, null, this.f7330f, this.f7331g);
        }
    }

    <A extends Api, T extends BaseImplementation<? extends Result, A>> T m10266a(T t);

    void m10267a();

    void m10268b();
}
