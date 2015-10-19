package com.leanplum;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;

final class ab {
    private aI f8720a;
    private boolean f8721b;
    private Timer f8722c;
    private boolean f8723d;
    private boolean f8724e;

    public ab() {
        this.f8723d = false;
        this.f8724e = false;
        try {
            this.f8720a = new aI(new URI("http://" + C0633g.f8795b + ":" + C0633g.f8796c), new aO(this));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        m12706a();
        this.f8722c = new Timer();
        this.f8722c.schedule(new ae(this), 0, 5000);
    }

    private void m12706a() {
        this.f8724e = true;
        aI aIVar = this.f8720a;
        if (aIVar.f8671e == null) {
            new aN(aIVar).start();
        }
    }

    static /* synthetic */ void m12711c(ab abVar) {
        if (!abVar.f8723d && !abVar.f8724e) {
            abVar.m12706a();
        }
    }
}
