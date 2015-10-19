package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: RideDetails */
/* renamed from: com.olacabs.customer.d.cp */
public class cp {
    private Bill bill;
    @SerializedName(a = "can_add_coupon")
    private boolean can_add_coupon;
    @SerializedName(a = "can_send_invoice")
    private boolean can_send_invoice;
    private String category_booked;
    private String category_id;
    private String coupon_code;
    private String created_at;
    private String crn;
    private String drop_landmark;
    private String drop_location;
    private String fav_name;
    private String id;
    private String krn;
    @SerializedName(a = "ola_money_paid")
    private Integer olaMoneyPaid;
    private cc payment;
    private cf pickup;
    private String pickup_address;
    private String pickup_landmark;
    private String pickup_time;
    private cw route;
    private String service_city;
    private cz service_type;
    private String status;
    private dk traveller;

    public String getStatus() {
        return this.status;
    }

    public String getCrn() {
        return this.crn;
    }

    public String getKrn() {
        return this.krn;
    }

    public String getId() {
        return this.id;
    }

    public String getService_city() {
        return this.service_city;
    }

    public String getPickup_time() {
        return this.pickup_time;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public String getPickup_address() {
        return this.pickup_address;
    }

    public String getCategory_booked() {
        return this.category_booked;
    }

    public String getCoupon_code() {
        return this.coupon_code;
    }

    public boolean isCan_add_coupon() {
        return this.can_add_coupon;
    }

    public boolean isCan_send_invoice() {
        return this.can_send_invoice;
    }

    public String getFav_name() {
        return this.fav_name;
    }

    public cz getService_type() {
        if (this.service_type == null) {
            this.service_type = new cz();
        }
        return this.service_type;
    }

    public cf getPickup() {
        if (this.pickup == null) {
            this.pickup = new cf();
        }
        return this.pickup;
    }

    public dk getTraveller() {
        if (this.traveller == null) {
            this.traveller = new dk();
        }
        return this.traveller;
    }

    public cw getRoute() {
        return this.route;
    }

    public Bill getBill() {
        if (this.bill == null) {
            this.bill = new Bill();
        }
        return this.bill;
    }

    public cc getPayment() {
        if (this.payment == null) {
            this.payment = new cc();
        }
        return this.payment;
    }

    public String getPickup_landmark() {
        return this.pickup_landmark;
    }

    public String getDrop_landmark() {
        return this.drop_landmark;
    }

    public String getDrop_address() {
        return this.drop_location;
    }

    public String getCategory_id() {
        return this.category_id;
    }

    public Integer getOlaMoneyPaid() {
        return this.olaMoneyPaid;
    }
}
