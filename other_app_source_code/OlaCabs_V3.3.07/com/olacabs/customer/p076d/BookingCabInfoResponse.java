package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.olacabs.customer.d.o */
public class BookingCabInfoResponse implements dw {
    @SerializedName(a = "alloted_cab_direction")
    private AllotedCabDirection allottedCabDirection;
    private dg booking;
    @SerializedName(a = "booking_id")
    private String bookingId;
    @SerializedName(a = "booking_status")
    private String bookingStatus;
    @SerializedName(a = "cab_categories")
    private ArrayList<CabCategory> cabCategories;
    private List<CabInfo> cabs;
    @SerializedName(a = "cancel_key")
    private String cancelKey;
    @SerializedName(a = "cancel_reason")
    private String cancelReason;
    @SerializedName(a = "city_tag")
    private String cityTag;
    @SerializedName(a = "city_taxi_categories")
    private ArrayList<ac> cityTaxiCategories;
    @SerializedName(a = "delivery_recharge_header")
    private String deliveryRechargeHeader;
    @SerializedName(a = "delivery_recharge_screen")
    private boolean deliveryRechargeScreen;
    @SerializedName(a = "delivery_recharge_text")
    private String deliveryRechargeText;
    @SerializedName(a = "driver_cancellation")
    private ar driverCancellation;
    @SerializedName(a = "emergency_number_verified")
    private boolean emergencyNumberVerified;
    @SerializedName(a = "enable_retry")
    private boolean enableRetry;
    private String header;
    @SerializedName(a = "force_logout")
    boolean isForceLogout;
    @SerializedName(a = "kp_categories")
    private ArrayList<bk> kpCategories;
    @SerializedName(a = "location_tag")
    private String locationTag;
    @SerializedName(a = "message")
    private String message;
    @SerializedName(a = "next_call_after")
    private Integer nextCallAfter;
    private String reason;
    @SerializedName(a = "recharge_screen")
    private boolean rechargeScreen;
    @SerializedName(a = "recharge_text")
    private String rechargeText;
    @SerializedName(a = "response_category")
    private String responseCategory;
    @SerializedName(a = "state_id")
    private int stateId;
    private String status;
    private String text;
    private String title;
    @SerializedName(a = "user_city")
    private String userCity;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public boolean isRechargeScreen() {
        return this.rechargeScreen;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getNextCallAfter() {
        return this.nextCallAfter;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public boolean isEmergencyNumberVerified() {
        return this.emergencyNumberVerified;
    }

    public int getStateId() {
        return this.stateId;
    }

    public String getRechargeText() {
        return this.rechargeText;
    }

    public dg getBooking() {
        return this.booking;
    }

    public ar getDriverCancellation() {
        return this.driverCancellation;
    }

    public String getCancelKey() {
        return this.cancelKey;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public boolean isEnableRetry() {
        return this.enableRetry;
    }

    public AllotedCabDirection getAllottedCabDirection() {
        return this.allottedCabDirection;
    }

    public ArrayList<bk> getKpCategories() {
        return this.kpCategories;
    }

    public ArrayList<ac> getCityTaxiCategories() {
        return this.cityTaxiCategories;
    }

    public String getResponseCategory() {
        return this.responseCategory;
    }

    public ArrayList<CabCategory> getCabCategories() {
        return this.cabCategories;
    }

    public String getLocationTag() {
        return this.locationTag;
    }

    public String getUserCity() {
        return this.userCity;
    }

    public String getCityTag() {
        return this.cityTag;
    }

    public String getDeliveryRechargeText() {
        return this.deliveryRechargeText;
    }

    public String getDeliveryRechargeHeader() {
        return this.deliveryRechargeHeader;
    }

    public boolean isDeliveryRechargeScreen() {
        return this.deliveryRechargeScreen;
    }

    public List<CabInfo> getCabs() {
        return this.cabs;
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public boolean isForceLogout() {
        return this.isForceLogout;
    }

    public void setForceLogout(boolean z) {
        this.isForceLogout = z;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isValid() {
        boolean z;
        if (this.cabCategories != null) {
            Iterator it = this.cabCategories.iterator();
            z = true;
            while (it.hasNext()) {
                z = ((CabCategory) it.next()).isValid();
            }
        } else {
            z = true;
        }
        if (Utils.m14924g(this.status) && r0) {
            return true;
        }
        return false;
    }
}
