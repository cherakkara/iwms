package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.bx.ClientProperties;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

/* renamed from: com.google.android.m4b.maps.u.n */
public final class ShortbreadToken {
    private ProtoBuf f7942a;

    public final ProtoBuf m11478a() {
        if (this.f7942a == null) {
            try {
                byte[] a = Config.m11320a().m10149q().m7755a("ShortbreadToken");
                if (a != null && a.length > 0) {
                    this.f7942a = ProtoBufUtil.m10226a(ClientProperties.f7004c, new DataInputStream(new ByteArrayInputStream(a)));
                }
            } catch (IOException e) {
            }
            if (this.f7942a == null) {
                ProtoBuf protoBuf = new ProtoBuf(ClientProperties.f7004c);
                protoBuf.m10197b(1, Trace.NULL);
                this.f7942a = protoBuf;
            }
        }
        return this.f7942a;
    }

    public final void m11479a(ProtoBuf protoBuf) {
        int i = 1;
        if (this.f7942a != null && this.f7942a.m10212h(1).equals(protoBuf.m10212h(1)) && this.f7942a.m10207e(2) == protoBuf.m10207e(2)) {
            i = 0;
        }
        if (i != 0) {
            this.f7942a = protoBuf;
            try {
                DataOutput byteArrayDataOutput = new ByteArrayDataOutput();
                ProtoBufUtil.m10228a(byteArrayDataOutput, this.f7942a);
                Config.m11320a().m10149q().m7754a("ShortbreadToken", byteArrayDataOutput.m11313a());
                PersistentStoreUtil.m11477a();
            } catch (IOException e) {
                this.f7942a = null;
            }
        }
    }
}
