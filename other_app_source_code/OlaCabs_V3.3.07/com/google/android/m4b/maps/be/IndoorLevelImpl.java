package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.be.be.UsageLog;
import com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate.IIndoorLevelDelegate;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p026a.Preconditions;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.google.android.m4b.maps.be.b */
public final class IndoorLevelImpl extends IIndoorLevelDelegate {
    private final IndoorStateInterface f5708a;
    private final IndoorLevelInterface f5709b;
    private final be f5710c;

    public IndoorLevelImpl(IndoorStateInterface indoorStateInterface, IndoorLevelInterface indoorLevelInterface, be beVar) {
        Preconditions.m1818a(indoorLevelInterface.m5803b(), (Object) "Level must have an id");
        this.f5708a = indoorStateInterface;
        this.f5709b = indoorLevelInterface;
        this.f5710c = beVar;
    }

    private String m8768e() {
        return this.f5709b.m5803b().toString();
    }

    public final String m8769a() {
        return this.f5709b.m5804d();
    }

    public final String m8771b() {
        return this.f5709b.m5805e();
    }

    public final void m8772c() {
        this.f5710c.m8835b(UsageLog.INDOOR_ACTIVATE_LEVEL);
        this.f5708a.m9372a(this.f5709b.m5802a());
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("id", m8768e()).m1806a(Constants.BUNDLE_NAME, m8769a()).m1806a("shortName", m8771b()).toString();
    }

    public final int hashCode() {
        return Objects.m1808a(m8768e());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndoorLevelImpl)) {
            return false;
        }
        return this.f5709b.m5803b().equals(((IndoorLevelImpl) obj).f5709b.m5803b());
    }

    public final boolean m8770a(com.google.android.m4b.maps.model.internal.IIndoorLevelDelegate iIndoorLevelDelegate) {
        return equals(iIndoorLevelDelegate);
    }

    public final int m8773d() {
        return hashCode();
    }
}
