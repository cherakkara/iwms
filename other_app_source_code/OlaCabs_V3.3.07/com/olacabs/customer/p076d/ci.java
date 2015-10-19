package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Constants;
import java.util.Map;

/* compiled from: ProfileDetailsResponse */
/* renamed from: com.olacabs.customer.d.ci */
public class ci implements bv, dw {
    String header;
    @SerializedName(a = "force_logout")
    boolean isForceLogout;
    @SerializedName(a = "offer_header")
    String offerHeader;
    @SerializedName(a = "offer_id")
    int offerId;
    @SerializedName(a = "offer_target")
    String offerTarget;
    @SerializedName(a = "offer_text")
    String offerText;
    @SerializedName(a = "ola_money_balance")
    String olaMoneyBalance;
    Map<String, String> origRequestParams;
    long origTimeStamp;
    @SerializedName(a = "profile_details")
    ce personalDetails;
    String reason;
    @SerializedName(a = "referral_scheme_status")
    String referralSchemeStatus;
    @SerializedName(a = "referral_urls")
    public Map<String, String> referralUrls;
    @SerializedName(a = "referred_earns")
    int referredEarns;
    @SerializedName(a = "referrer_earns")
    int referrerEarns;
    @SerializedName(a = "request_type")
    String requestType;
    @SerializedName(a = "state_id")
    int stateId;
    String status;
    String text;

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

    public ce getPersonalDetails() {
        return this.personalDetails;
    }

    public void setPersonalDetails(ce ceVar) {
        this.personalDetails = ceVar;
    }

    public void setReferralSchemeStatus(String str) {
        this.referralSchemeStatus = str;
    }

    public int getReferrerEarns() {
        return this.referrerEarns;
    }

    public int getReferredEarns() {
        return this.referredEarns;
    }

    public String getOlaMoneyBalance() {
        return this.olaMoneyBalance;
    }

    public void setOlaMoneyBalance(String str) {
        this.olaMoneyBalance = str;
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

    public boolean isReferralSchemeOn() {
        if (this.referralSchemeStatus == null || this.referralSchemeStatus.equalsIgnoreCase("OFF")) {
            return false;
        }
        return true;
    }

    public boolean isForceLogout() {
        return this.isForceLogout;
    }

    public void setForceLogout(boolean z) {
        this.isForceLogout = z;
    }

    public void setOrigTimeStamp(long j) {
        this.origTimeStamp = j;
    }

    public long getOrigTimeStamp() {
        return this.origTimeStamp;
    }

    public void setOrigParams(Map<String, String> map) {
        this.origRequestParams = map;
    }

    public boolean isValid(Map<String, String> map) {
        if (map == null || map.get(Constants.USER_ID) == null || this.origRequestParams == null || this.origRequestParams.get(Constants.USER_ID) == null) {
            return false;
        }
        if (System.currentTimeMillis() - this.origTimeStamp >= 86400000 || !((String) map.get(Constants.USER_ID)).equals(this.origRequestParams.get(Constants.USER_ID))) {
            return false;
        }
        return true;
    }

    public boolean markAsInvalid() {
        return false;
    }

    public String getOfferTarget() {
        return this.offerTarget;
    }

    public void setOfferTarget(String str) {
        this.offerTarget = str;
    }

    public int getOfferId() {
        return this.offerId;
    }

    public void setOfferId(int i) {
        this.offerId = i;
    }

    public String getOfferHeader() {
        return this.offerHeader;
    }

    public void setOfferHeader(String str) {
        this.offerHeader = str;
    }

    public String getOfferText() {
        return this.offerText;
    }

    public void setOfferText(String str) {
        this.offerText = str;
    }

    public Map<String, String> getReferralUrls() {
        return this.referralUrls;
    }

    public boolean isValid() {
        return true;
    }
}
