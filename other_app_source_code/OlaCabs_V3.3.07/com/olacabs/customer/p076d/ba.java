package com.olacabs.customer.p076d;

import com.olacabs.customer.p076d.bd.FoodListItem;

/* compiled from: FoodCheckoutItem */
/* renamed from: com.olacabs.customer.d.ba */
public class ba {
    private FoodListItem attributes;
    private String description;
    private long listing_id;
    private long merchant_id;
    private String merchant_name;
    private long order_date;
    private double price;
    private int quantity;
    private String title;

    public ba(long j, long j2, String str, String str2, String str3, int i, double d, FoodListItem foodListItem) {
        this.listing_id = j;
        this.merchant_id = j2;
        this.merchant_name = str;
        this.title = str2;
        this.description = str3;
        this.quantity = i;
        this.price = d;
        this.order_date = System.currentTimeMillis();
        this.attributes = foodListItem;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getMerchant_name() {
        return this.merchant_name;
    }

    public String getTitle() {
        return this.title;
    }

    public double getPrice() {
        return this.price;
    }

    public long getMerchant_id() {
        return this.merchant_id;
    }

    public long getListing_id() {
        return this.listing_id;
    }

    public FoodListItem getAttributes() {
        return this.attributes;
    }

    public String getDescription() {
        return this.description;
    }
}
