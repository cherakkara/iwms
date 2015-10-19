package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.ArrayList;

/* compiled from: FareValue */
/* renamed from: com.olacabs.customer.d.au */
public class au {
    private ArrayList<FareValue> fare_breakup;
    @SerializedName(a = "header")
    private String header;
    private String id;
    @SerializedName(a = "note")
    private String note;

    /* renamed from: com.olacabs.customer.d.au.a */
    public class FareValue {
        private String display_text;
        private String value;

        public String getDisplayText() {
            return this.display_text;
        }

        public String getValue() {
            return this.value;
        }
    }

    public String getId() {
        return this.id;
    }

    public FareValue getFareBreakUp(int i) {
        if (this.fare_breakup == null || i < 0 || i >= this.fare_breakup.size()) {
            return null;
        }
        return (FareValue) this.fare_breakup.get(i);
    }

    public String getHeader() {
        return this.header;
    }

    public String getNote() {
        return this.note;
    }
}
