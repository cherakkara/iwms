package com.olacabs.customer.p076d;

import java.util.ArrayList;

/* compiled from: PartialStockCheckoutItem */
/* renamed from: com.olacabs.customer.d.bz */
public class bz {
    private String header;
    private String message;
    private ArrayList<ba> partialItems;

    public bz() {
        this.partialItems = new ArrayList();
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public ArrayList<ba> getPartialItems() {
        return this.partialItems;
    }

    public void setPartialItems(ArrayList<ba> arrayList) {
        this.partialItems = arrayList;
    }
}
