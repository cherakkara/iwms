package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.ArrayList;

/* compiled from: GoogleSearchResponse */
/* renamed from: com.olacabs.customer.d.bi */
public class bi {
    @SerializedName(a = "results")
    ArrayList<bn> results;
    String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public ArrayList<bn> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<bn> arrayList) {
        this.results = arrayList;
    }
}
