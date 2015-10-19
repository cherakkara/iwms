package com.google.android.m4b.maps.be;

import android.content.SharedPreferences;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.olacabs.customer.utils.Constants;

/* compiled from: CacheExpirationManager */
public final class bn implements DataRequestListener {
    private SharedPreferences f5839a;
    private CacheExpirationManager f5840b;
    private bt f5841c;
    private long f5842d;

    /* renamed from: com.google.android.m4b.maps.be.bn.a */
    public interface CacheExpirationManager {
        void m8917a();
    }

    public bn(bt btVar, SharedPreferences sharedPreferences, CacheExpirationManager cacheExpirationManager) {
        this.f5842d = 0;
        this.f5841c = btVar;
        this.f5839a = sharedPreferences;
        this.f5840b = cacheExpirationManager;
    }

    public final void m8920a(long j) {
        if (!this.f5841c.m9046a(false)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f5839a.getLong("LAST_FETCH_PERSISTENT_TAG", currentTimeMillis) > j) {
                this.f5840b.m8917a();
            }
        }
    }

    public final void m8921a(DataRequest dataRequest) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f5842d > Constants.MILLIS_IN_AN_HOUR) {
            this.f5839a.edit().putLong("LAST_FETCH_PERSISTENT_TAG", currentTimeMillis).commit();
            this.f5842d = currentTimeMillis;
        }
    }

    public final void m8919a(int i, boolean z, String str) {
    }

    public final void m8923b(DataRequest dataRequest) {
    }

    public final void m8918a() {
    }

    public final void m8922b() {
    }
}
