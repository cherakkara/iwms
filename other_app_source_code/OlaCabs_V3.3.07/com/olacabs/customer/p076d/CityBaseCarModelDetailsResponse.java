package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Utils;
import java.util.List;

/* renamed from: com.olacabs.customer.d.x */
public class CityBaseCarModelDetailsResponse implements dw {
    private static final String SURCHARGE_MULTIPLIER = "multiplier";
    @SerializedName(a = "category_id")
    public String categoryId;
    @SerializedName(a = "car_model_names")
    public List<CityBaseCarModelNamesResponse> cityCarModelNames;
    @SerializedName(a = "fare_breakup")
    public List<aa> cityFareBreakUp;
    public String header;
    @SerializedName(a = "is_surcharge_applicable")
    private boolean isSurchargeApplicable;
    public String note;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;
    @SerializedName(a = "sub_text")
    public String subText;
    @SerializedName(a = "surcharge_amount")
    private String surchargeAmount;
    @SerializedName(a = "surcharge_header")
    private String surchargeHeader;
    @SerializedName(a = "surcharge_reason")
    private String surchargeReason;
    @SerializedName(a = "surcharge_text")
    private String surchargeText;
    @SerializedName(a = "surcharge_type")
    private String surchargeType;
    public String text;

    public String getNote() {
        return this.note;
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

    public String getSurchargeReason() {
        return this.surchargeReason;
    }

    public String getSurchargeText() {
        return this.surchargeText;
    }

    public String getSurchargeHeader() {
        return this.surchargeHeader;
    }

    public boolean isSurchargeMultiplier() {
        if (this.surchargeType != null) {
            return this.surchargeType.toLowerCase().contains(SURCHARGE_MULTIPLIER);
        }
        return false;
    }

    public List<CityBaseCarModelNamesResponse> getCityBaseCarModelNames() {
        return this.cityCarModelNames;
    }

    public List<aa> getCityBaseFareBreakUp() {
        return this.cityFareBreakUp;
    }

    public String getHeader() {
        return this.header;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String carName() {
        return null;
    }

    public String getText() {
        return this.text;
    }

    public String getSubText() {
        return this.subText;
    }

    public List<CityBaseCarModelNamesResponse> getCityCarModelNames() {
        return this.cityCarModelNames;
    }

    public List<aa> getCityFareBreakUp() {
        return this.cityFareBreakUp;
    }

    public boolean isKaliPeeliCab() {
        return getCategoryId().equalsIgnoreCase("local_taxi");
    }

    public boolean isAutoRickshaw() {
        return getCategoryId().equalsIgnoreCase("local_auto");
    }

    public boolean isDelivery() {
        return getCategoryId().equalsIgnoreCase("delivery");
    }

    public String getCarNames() {
        String str = Trace.NULL;
        String str2 = str;
        for (CityBaseCarModelNamesResponse name : getCityCarModelNames()) {
            str2 = name.getName() + "," + " " + str2;
        }
        if (Utils.m14924g(str2)) {
            return str2.substring(0, str2.length() - 2);
        }
        return str2;
    }

    public boolean isValid() {
        boolean z = this.cityFareBreakUp != null && this.cityFareBreakUp.size() > 2;
        if (!z) {
            return z;
        }
        boolean z2 = z;
        for (aa isValid : this.cityFareBreakUp) {
            z2 = isValid.isValid() & z2;
        }
        return z2;
    }
}
