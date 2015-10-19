package com.google.android.m4b.maps.an;

import java.io.DataInput;
import java.util.ArrayList;

/* renamed from: com.google.android.m4b.maps.an.v */
public final class StyleEntryTable {
    private final ArrayList<StyleEntry> f3713a;

    public StyleEntryTable() {
        this.f3713a = new ArrayList();
    }

    public static StyleEntryTable m6109a(DataInput dataInput, StyleTable styleTable) {
        StyleEntryTable styleEntryTable = new StyleEntryTable();
        int a = an.m5578a(dataInput);
        for (int i = 0; i < a; i++) {
            styleEntryTable.f3713a.add(new StyleEntry(dataInput.readUTF().intern(), styleTable.m6116a(an.m5578a(dataInput))));
        }
        return styleEntryTable;
    }

    public final StyleEntry m6110a(int i) {
        if (i >= this.f3713a.size()) {
            return null;
        }
        return (StyleEntry) this.f3713a.get(i);
    }
}
