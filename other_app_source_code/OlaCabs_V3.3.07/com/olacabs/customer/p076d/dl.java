package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: TripInfo */
/* renamed from: com.olacabs.customer.d.dl */
public class dl implements dw {
    private double advance;
    private double amount;
    private double discount;
    private ap distance;
    @SerializedName(a = "payable_amount")
    private int payableAmount;
    private as waitTime;

    public dl(ap apVar, as asVar, int i) {
        this.distance = apVar;
        this.waitTime = asVar;
        this.amount = (double) i;
    }

    public int getPayableAmount() {
        return this.payableAmount;
    }

    public ap getDistance() {
        return this.distance;
    }

    public as getWaitTime() {
        return this.waitTime;
    }

    public double getAmount() {
        return this.amount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public double getAdvance() {
        return this.advance;
    }

    public boolean isValid() {
        return (this.distance == null || this.waitTime == null) ? false : true;
    }
}
