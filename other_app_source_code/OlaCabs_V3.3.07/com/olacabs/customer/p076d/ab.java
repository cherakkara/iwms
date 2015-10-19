package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: CityBasedCarModelFareBreakUpResponse */
/* renamed from: com.olacabs.customer.d.ab */
public class ab {
    @SerializedName(a = "car_models")
    public CityBaseCarModelResponse carModels;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;

    public CityBaseCarModelResponse getCarModels() {
        return this.carModels;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }
}
