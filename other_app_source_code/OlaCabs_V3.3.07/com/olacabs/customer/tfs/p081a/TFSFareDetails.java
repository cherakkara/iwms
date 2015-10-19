package com.olacabs.customer.tfs.p081a;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.p076d.dw;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.tfs.a.e */
public class TFSFareDetails implements dw {
    @SerializedName(a = "base_fare")
    int baseFare;
    @SerializedName(a = "base_km")
    String baseKm;
    @SerializedName(a = "extra_km_fare")
    int extraKmFare;
    @SerializedName(a = "trip_time_pulse_rate")
    float tripTimePulseRate;

    public int getBaseFare() {
        return this.baseFare;
    }

    public void setBaseFare(int i) {
        this.baseFare = i;
    }

    public float getTripTimePulseRate() {
        return this.tripTimePulseRate;
    }

    public void setTripTimePulseRate(float f) {
        this.tripTimePulseRate = f;
    }

    public int getExtraKmFare() {
        return this.extraKmFare;
    }

    public void setExtraKmFare(int i) {
        this.extraKmFare = i;
    }

    public String getBaseKm() {
        return this.baseKm;
    }

    public void setBaseKm(String str) {
        this.baseKm = str;
    }

    public boolean isValid() {
        return Utils.m14924g(this.baseKm);
    }
}
