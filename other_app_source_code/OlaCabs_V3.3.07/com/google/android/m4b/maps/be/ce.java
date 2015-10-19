package com.google.android.m4b.maps.be;

import android.os.IBinder;
import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate.IIndoorBuildingDelegate;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.ar;
import java.util.List;

/* compiled from: IndoorBuildingImpl */
public final class ce extends IIndoorBuildingDelegate {
    private final IndoorStateInterface f6006a;
    private final cf f6007b;
    private final be f6008c;

    public ce(IndoorStateInterface indoorStateInterface, cf cfVar, be beVar) {
        Preconditions.m1818a(cfVar.m5783a(), (Object) "Building must have an id");
        this.f6006a = indoorStateInterface;
        this.f6007b = cfVar;
        this.f6008c = beVar;
    }

    private String m9361f() {
        return this.f6007b.m5783a().toString();
    }

    public final int m9362a() {
        this.f6008c.m8835b(UsageLog.INDOOR_GET_ACTIVE_LEVEL);
        return this.f6006a.m9368a(this.f6007b);
    }

    public final int m9364b() {
        this.f6008c.m8835b(UsageLog.INDOOR_GET_DEFAULT_LEVEL);
        return this.f6006a.m9373b(this.f6007b);
    }

    public final boolean m9365c() {
        this.f6008c.m8835b(UsageLog.INDOOR_IS_UNDERGROUND);
        return this.f6006a.m9376c(this.f6007b);
    }

    public final List<IBinder> m9366d() {
        List<IndoorLevelInterface> b = this.f6007b.m5785b();
        List<IBinder> b2 = ar.m2523b(b.size());
        for (IndoorLevelInterface indoorLevelImpl : b) {
            b2.add(new IndoorLevelImpl(this.f6006a, indoorLevelImpl, this.f6008c));
        }
        return b2;
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("id", m9361f()).m1804a("number of level: ", this.f6007b.m5785b().size()).toString();
    }

    public final int hashCode() {
        return Objects.m1808a(m9361f());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ce)) {
            return false;
        }
        return this.f6007b.m5783a().equals(((ce) obj).f6007b.m5783a());
    }

    public final boolean m9363a(com.google.android.m4b.maps.model.internal.IIndoorBuildingDelegate iIndoorBuildingDelegate) {
        return equals(iIndoorBuildingDelegate);
    }

    public final int m9367e() {
        return hashCode();
    }
}
