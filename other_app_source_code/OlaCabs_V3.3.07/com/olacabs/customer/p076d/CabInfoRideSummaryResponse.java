package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.p076d.au.FareValue;
import com.olacabs.customer.utils.Utils;
import java.util.ArrayList;

/* renamed from: com.olacabs.customer.d.s */
public class CabInfoRideSummaryResponse implements dw {
    @SerializedName(a = "booking_id")
    private String bookingId;
    @SerializedName(a = "booking_status")
    private String bookingStatus;
    @SerializedName(a = "delivery_recharge_header")
    private String deliveryRechargeHeader;
    @SerializedName(a = "delivery_recharge_screen")
    private String deliveryRechargeScreen;
    @SerializedName(a = "delivery_recharge_text")
    private String deliveryRechargeText;
    @SerializedName(a = "disable_ride_later_delivery")
    private String disableRideLaterDelivery;
    @SerializedName(a = "display_text")
    private String displayText;
    @SerializedName(a = "emergency_number_verified")
    private String emergencyNumberVerified;
    @SerializedName(a = "fare_breakup")
    private ArrayList<FareValue> fareBreakUp;
    @SerializedName(a = "force_logout")
    boolean isForceLogout;
    @SerializedName(a = "is_surcharge_applicable")
    private boolean isSurchargeApplicable;
    @SerializedName(a = "low_rating_reasons")
    private ArrayList<String> lowRatingReasons;
    @SerializedName(a = "middle_display_text")
    private String middleDisplayText;
    @SerializedName(a = "ola_money_balance")
    private float olaMoneyBalance;
    @SerializedName(a = "recharge_screen")
    private String rechargeScreen;
    @SerializedName(a = "recharge_text")
    private String rechargeText;
    @SerializedName(a = "request_type")
    private String requestType;
    private String server;
    @SerializedName(a = "service_type")
    private String serviceType;
    @SerializedName(a = "state_id")
    private int stateId;
    private String status;
    @SerializedName(a = "sub_display_text")
    private String subDisplayText;
    @SerializedName(a = "surcharge_reason")
    private String surchargeReason;
    @SerializedName(a = "trip_info")
    private dl tripInfo;

    public boolean isSurchargeApplicable() {
        return this.isSurchargeApplicable;
    }

    public String getSurchargeReason() {
        return this.surchargeReason;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public int getStateId() {
        return this.stateId;
    }

    public void setStateId(int i) {
        this.stateId = i;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(String str) {
        this.bookingId = str;
    }

    public int getOlaMoneyBalance() {
        return Math.round(this.olaMoneyBalance);
    }

    public dl getTripInfo() {
        return this.tripInfo;
    }

    public void setTripInfo(dl dlVar) {
        this.tripInfo = dlVar;
    }

    public ArrayList<FareValue> getFareBreakUp() {
        return this.fareBreakUp;
    }

    public void setFareBreakUp(ArrayList<FareValue> arrayList) {
        this.fareBreakUp = arrayList;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String str) {
        this.bookingStatus = str;
    }

    public String getEmergencyNumberVerified() {
        return this.emergencyNumberVerified;
    }

    public void setEmergencyNumberVerified(String str) {
        this.emergencyNumberVerified = str;
    }

    public String getRechargeScreen() {
        return this.rechargeScreen;
    }

    public void setRechargeScreen(String str) {
        this.rechargeScreen = str;
    }

    public String getRechargeText() {
        return this.rechargeText;
    }

    public void setRechargeText(String str) {
        this.rechargeText = str;
    }

    public String getDeliveryRechargeScreen() {
        return this.deliveryRechargeScreen;
    }

    public void setDeliveryRechargeScreen(String str) {
        this.deliveryRechargeScreen = str;
    }

    public String getDeliveryRechargeText() {
        return this.deliveryRechargeText;
    }

    public void setDeliveryRechargeText(String str) {
        this.deliveryRechargeText = str;
    }

    public String getDisableRideLaterDelivery() {
        return this.disableRideLaterDelivery;
    }

    public void setDisableRideLaterDelivery(String str) {
        this.disableRideLaterDelivery = str;
    }

    public String getDeliveryRechargeHeader() {
        return this.deliveryRechargeHeader;
    }

    public void setDeliveryRechargeHeader(String str) {
        this.deliveryRechargeHeader = str;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String str) {
        this.server = str;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public void setDisplayText(String str) {
        this.displayText = str;
    }

    public String getMiddleDisplayText() {
        return this.middleDisplayText;
    }

    public void setMiddleDisplayText(String str) {
        this.middleDisplayText = str;
    }

    public String getSubDisplayText() {
        return this.subDisplayText;
    }

    public void setSubDisplayText(String str) {
        this.subDisplayText = str;
    }

    public ArrayList<String> getLowRatingReasons() {
        return this.lowRatingReasons;
    }

    public void setLowRatingReasons(ArrayList<String> arrayList) {
        this.lowRatingReasons = arrayList;
    }

    public boolean isForceLogout() {
        return this.isForceLogout;
    }

    public void setForceLogout(boolean z) {
        this.isForceLogout = z;
    }

    public boolean isValid() {
        return Utils.m14924g(this.status);
    }
}
