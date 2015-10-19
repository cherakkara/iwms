package com.leanplum;

import android.content.res.Configuration;

final class aA extends aH {
    aA() {
    }

    public final Object m12617a(String str) {
        return str.length() == 2 ? str : null;
    }

    public final boolean m12618a(Object obj, Configuration configuration) {
        return configuration.locale.getLanguage().equals(obj);
    }
}
