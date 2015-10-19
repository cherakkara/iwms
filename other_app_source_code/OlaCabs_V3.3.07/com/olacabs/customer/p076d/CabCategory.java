package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.p */
public class CabCategory implements dw {
    private static final String JSON_ARRAY_TAG = "cab_categories";
    private static final String JSON_OBJECT_TAG = "cab_category";
    private static final String SURCHARGE_MULTIPLIER = "multiplier";
    public static final String TAG;
    private String campaign_tag;
    @SerializedName(a = "display_text")
    private String displayText;
    private String display_name;
    private ap distance;
    private as duration;
    private String food_delivery_time;
    private String id;
    private String image_name;
    @SerializedName(a = "is_surcharge_applicable")
    private boolean isSurchargeApplicable;
    private boolean ride_later_enable;
    private boolean ride_now_enable;
    @SerializedName(a = "surcharge_amount")
    private String surchargeAmount;
    @SerializedName(a = "surcharge_type")
    private String surchargeType;

    static {
        TAG = CabCategory.class.getSimpleName();
    }

    public String getFood_delivery_time() {
        return this.food_delivery_time;
    }

    public String getJsonObjectTag() {
        return JSON_OBJECT_TAG;
    }

    public String getJsonArrayTag() {
        return JSON_ARRAY_TAG;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.display_name;
    }

    public String getEta() {
        if (this.duration == null || this.duration.getValue() == null) {
            return null;
        }
        return this.duration.getValue();
    }

    public boolean isCabAvailable() {
        return this.duration != null;
    }

    public boolean isCabAvailableNow() {
        return this.duration != null;
    }

    public boolean isCabAvailableAfterOneHour() {
        if (this.duration == null || Integer.parseInt(this.duration.getValue()) < 60) {
            return false;
        }
        return true;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public boolean isSurchargeApplicable() {
        return this.isSurchargeApplicable;
    }

    public String getSurchargeType() {
        return this.surchargeType;
    }

    public String getSurchargeAmount() {
        return this.surchargeAmount;
    }

    public boolean isSurchargeMultiplier() {
        if (this.surchargeType != null) {
            return this.surchargeType.toLowerCase().contains(SURCHARGE_MULTIPLIER);
        }
        return false;
    }

    public String getDisplayName() {
        return this.display_name;
    }

    public boolean isKaaliPeeliCab() {
        return this.id.equalsIgnoreCase("local_taxi");
    }

    public boolean isAutoRickshaw() {
        return this.id.equalsIgnoreCase("local_auto");
    }

    public boolean isDelivery() {
        return this.id.equalsIgnoreCase("delivery");
    }

    public boolean isFoodDelivery() {
        return this.id.equalsIgnoreCase("food_delivery");
    }

    public boolean isTFS() {
        return this.id.equalsIgnoreCase("tfs");
    }

    public boolean isRideNowEnable() {
        return this.ride_now_enable;
    }

    public boolean isRideLaterEnable() {
        return this.ride_later_enable;
    }

    public String getImage_name() {
        return this.image_name;
    }

    public String getCampaign_tag() {
        return this.campaign_tag;
    }

    public boolean isValid() {
        return Utils.m14924g(this.id) && Utils.m14924g(this.display_name);
    }
}
