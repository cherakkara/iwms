package com.olacabs.customer.p076d;

/* renamed from: com.olacabs.customer.d.n */
public class Bill {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "bill";
    public static final String TAG;
    private int discount;
    private String discount_code;
    private String ola_money_credit;
    private int total;

    static {
        TAG = Bill.class.getSimpleName();
    }

    public int getTotal() {
        return this.total;
    }

    public int getDiscount() {
        return this.discount;
    }

    public String getDiscount_code() {
        return this.discount_code;
    }

    public String getOla_money_credit() {
        return this.ola_money_credit;
    }

    public String getJsonObjectTag() {
        return null;
    }

    public String getJsonArrayTag() {
        return null;
    }
}
