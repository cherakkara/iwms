package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.bx.ClientProperties;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import java.io.DataInput;
import java.io.DataOutput;

/* renamed from: com.google.android.m4b.maps.u.d */
public final class ClientPropertiesRequest extends BaseDataRequest {
    private final ProtoBuf f7861a;

    public ClientPropertiesRequest(ProtoBuf protoBuf) {
        this.f7861a = protoBuf;
        if (!protoBuf.m10214j(1)) {
            String a = Config.m11320a().m11330d().m10155a("Cohort");
            if (a != null) {
                protoBuf.m10197b(1, a);
            }
        }
    }

    public final int m11319i() {
        return 62;
    }

    public final boolean m11315a() {
        return false;
    }

    public final boolean m11317b() {
        return false;
    }

    public final boolean m11318d() {
        return false;
    }

    public final void m11314a(DataOutput dataOutput) {
        ProtoBufUtil.m10228a(dataOutput, this.f7861a);
    }

    public final boolean m11316a(DataInput dataInput) {
        ProtoBuf a = ProtoBufUtil.m10226a(ClientProperties.f7003b, dataInput);
        if (a.m10214j(1)) {
            String h = a.m10212h(1);
            this.f7861a.m10197b(1, h);
            Config.m11320a().m11330d().m10156a("Cohort", h);
        }
        if (a.m10214j(2)) {
            DataRequestDispatcher.m11393a().m11442a(a.m10211g(2));
        }
        return true;
    }
}
