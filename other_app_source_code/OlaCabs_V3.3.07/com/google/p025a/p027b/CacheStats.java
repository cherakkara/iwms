package com.google.p025a.p027b;

import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import javax.annotation.Nullable;

/* renamed from: com.google.a.b.d */
public final class CacheStats {
    private final long f1419a;
    private final long f1420b;
    private final long f1421c;
    private final long f1422d;
    private final long f1423e;
    private final long f1424f;

    public CacheStats(long j, long j2, long j3, long j4, long j5, long j6) {
        Preconditions.m1822a(j >= 0);
        Preconditions.m1822a(j2 >= 0);
        Preconditions.m1822a(j3 >= 0);
        Preconditions.m1822a(j4 >= 0);
        Preconditions.m1822a(j5 >= 0);
        Preconditions.m1822a(j6 >= 0);
        this.f1419a = j;
        this.f1420b = j2;
        this.f1421c = j3;
        this.f1422d = j4;
        this.f1423e = j5;
        this.f1424f = j6;
    }

    public int hashCode() {
        return Objects.m1808a(Long.valueOf(this.f1419a), Long.valueOf(this.f1420b), Long.valueOf(this.f1421c), Long.valueOf(this.f1422d), Long.valueOf(this.f1423e), Long.valueOf(this.f1424f));
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof CacheStats)) {
            return false;
        }
        CacheStats cacheStats = (CacheStats) obj;
        if (this.f1419a == cacheStats.f1419a && this.f1420b == cacheStats.f1420b && this.f1421c == cacheStats.f1421c && this.f1422d == cacheStats.f1422d && this.f1423e == cacheStats.f1423e && this.f1424f == cacheStats.f1424f) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Objects.m1809a((Object) this).m1805a("hitCount", this.f1419a).m1805a("missCount", this.f1420b).m1805a("loadSuccessCount", this.f1421c).m1805a("loadExceptionCount", this.f1422d).m1805a("totalLoadTime", this.f1423e).m1805a("evictionCount", this.f1424f).toString();
    }
}
