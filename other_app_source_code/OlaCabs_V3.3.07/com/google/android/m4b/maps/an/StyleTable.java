package com.google.android.m4b.maps.an;

import java.io.DataInput;
import java.util.ArrayList;

/* renamed from: com.google.android.m4b.maps.an.x */
public final class StyleTable {
    private final ArrayList<Style> f3717a;

    public StyleTable() {
        this.f3717a = new ArrayList();
    }

    public static StyleTable m6115a(DataInput dataInput, int i) {
        StyleTable styleTable = new StyleTable();
        int a = an.m5578a(dataInput);
        for (int i2 = 0; i2 < a; i2++) {
            styleTable.f3717a.add(Style.m6091a(i2, dataInput, i));
        }
        return styleTable;
    }

    public final Style m6116a(int i) {
        if (i >= this.f3717a.size() || i < 0) {
            return Style.m6090a();
        }
        return (Style) this.f3717a.get(i);
    }
}
