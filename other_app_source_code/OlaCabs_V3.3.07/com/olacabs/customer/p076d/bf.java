package com.olacabs.customer.p076d;

import java.util.ArrayList;

/* compiled from: FoodNonHistoricalStatusResponse */
/* renamed from: com.olacabs.customer.d.bf */
public class bf implements dw {
    private long id;
    private ArrayList<bc> items;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = (long) i;
    }

    public ArrayList<bc> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<bc> arrayList) {
        this.items = arrayList;
    }

    public boolean isValid() {
        return this.id != 0;
    }
}
