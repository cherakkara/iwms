package com.google.android.gms.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.C0453u;
import java.util.List;

/* renamed from: com.google.android.gms.internal.z */
public final class C0521z {
    public static Scope[] m4181a(List<String> list) {
        C0453u.m3847a((Object) list, (Object) "scopeStrings can't be null.");
        Scope[] scopeArr = new Scope[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            scopeArr[i] = new Scope((String) list.get(i));
        }
        return scopeArr;
    }
}
