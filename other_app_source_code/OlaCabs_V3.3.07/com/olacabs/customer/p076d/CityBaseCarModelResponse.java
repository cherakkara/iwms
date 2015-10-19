package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.List;

/* renamed from: com.olacabs.customer.d.z */
public class CityBaseCarModelResponse {
    @SerializedName(a = "car_model_details")
    public List<CityBaseCarModelDetailsResponse> carModelDetails;
    @SerializedName(a = "city_name")
    public String cityName;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;

    public List<CityBaseCarModelDetailsResponse> getCarModelDetails() {
        return this.carModelDetails;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public CityBaseCarModelDetailsResponse getCategoryDetails(String str) {
        for (CityBaseCarModelDetailsResponse cityBaseCarModelDetailsResponse : getCarModelDetails()) {
            if (cityBaseCarModelDetailsResponse.categoryId.equalsIgnoreCase(str)) {
                return cityBaseCarModelDetailsResponse;
            }
        }
        return null;
    }
}
