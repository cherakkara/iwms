package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* compiled from: FoodItem */
/* renamed from: com.olacabs.customer.d.bc */
public class bc implements dw {
    private String address;
    private int available_qty;
    private String description;
    private String image_url;
    private long merchant_id;
    private String merchant_name;
    private long merchant_product_id;
    private String name;
    private long order_date;
    private int price;
    private int quantity;
    private int sku;
    private String status;
    private String title;
    private String type;

    public bc() {
        this.order_date = System.currentTimeMillis();
    }

    public String getImage_url() {
        return this.image_url;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public long getOrder_date() {
        return this.order_date;
    }

    public void setOrder_date(long j) {
        this.order_date = j;
    }

    public long getMerchant_product_id() {
        return this.merchant_product_id;
    }

    public void setMerchant_product_id(long j) {
        this.merchant_product_id = j;
    }

    public int getSku() {
        return this.sku;
    }

    public void setSku(int i) {
        this.sku = i;
    }

    public long getMerchant_id() {
        return this.merchant_id;
    }

    public void setMerchant_id(long j) {
        this.merchant_id = j;
    }

    public String getMerchant_name() {
        return this.merchant_name;
    }

    public void setMerchant_name(String str) {
        this.merchant_name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public int getAvailable_qty() {
        return this.available_qty;
    }

    public void setAvailable_qty(int i) {
        this.available_qty = i;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public long getMerchantProductId() {
        return this.merchant_product_id;
    }

    public void setMerchant_product_id(int i) {
        this.merchant_product_id = (long) i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public boolean isValid() {
        return this.quantity != 0 && Utils.m14924g(this.title);
    }
}
