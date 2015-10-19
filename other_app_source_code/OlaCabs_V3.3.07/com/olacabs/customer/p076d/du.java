package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: UserOrderDetails */
/* renamed from: com.olacabs.customer.d.du */
public class du implements dw {
    private aq attributes;
    @SerializedName(a = "customer_order_id")
    private String customerOrderId;
    private long id;
    private ArrayList<bc> items;
    private String status;
    @SerializedName(a = "track_id")
    private String trackId;

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

    public String getCustomerOrderId() {
        return this.customerOrderId;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public boolean isValid() {
        int i;
        int i2 = (this.items == null || this.items.isEmpty()) ? 0 : 1;
        if (i2 != 0) {
            Iterator it = this.items.iterator();
            i = i2;
            while (it.hasNext()) {
                i = ((bc) it.next()).isValid() & i;
            }
        } else {
            i = i2;
        }
        return (!Utils.m14924g(this.status) || this.items == null || this.items.isEmpty() || !Utils.m14924g(this.customerOrderId) || i == 0) ? false : true;
    }

    public aq getDriverAttributes() {
        return this.attributes;
    }
}
