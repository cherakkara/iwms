package com.leanplum;

import java.util.ArrayList;
import java.util.List;

public class ActionArgs {
    private List<C0625a<?>> f8527a;

    public ActionArgs() {
        this.f8527a = new ArrayList();
    }

    public <T> ActionArgs with(String str, T t) {
        this.f8527a.add(C0625a.m12594a(str, (Object) t));
        return this;
    }

    public ActionArgs withColor(String str, int i) {
        this.f8527a.add(C0625a.m12593a(str, i));
        return this;
    }

    public ActionArgs withFile(String str, String str2) {
        this.f8527a.add(C0625a.m12607c(str, str2));
        return this;
    }

    public ActionArgs withAsset(String str, String str2) {
        this.f8527a.add(C0625a.m12608d(str, str2));
        return this;
    }

    public ActionArgs withAction(String str, String str2) {
        this.f8527a.add(C0625a.m12609e(str, str2));
        return this;
    }

    final List<C0625a<?>> m12398a() {
        return this.f8527a;
    }
}
