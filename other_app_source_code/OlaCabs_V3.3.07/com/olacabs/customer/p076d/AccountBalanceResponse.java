package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.olacabs.customer.d.a */
public class AccountBalanceResponse {
    @SerializedName(a = "offer_data")
    List<an> denominationsList;
    @SerializedName(a = "detail_text")
    String detailText;
    String header;
    ArrayList<AccountBalanceResponse> invitees;
    @SerializedName(a = "force_logout")
    boolean isForceLogout;
    @SerializedName(a = "offer_applicable")
    boolean offerApplicable;
    @SerializedName(a = "offer_select_denom")
    int offerSelectDenom;
    @SerializedName(a = "offer_text")
    String offerText;
    String reason;
    @SerializedName(a = "referral_balance")
    int referralBalance;
    @SerializedName(a = "referral_code")
    String referralCode;
    @SerializedName(a = "request_type")
    String requestType;
    String status;
    String text;
    @SerializedName(a = "total_balance")
    float totalBalance;
    boolean verified;

    /* renamed from: com.olacabs.customer.d.a.a */
    public class AccountBalanceResponse {
        private int credit_gain;
        private String email;
        private String name;
        private String src;
        private String status;

        public String getName() {
            return this.name;
        }

        public String getStatus() {
            return this.status;
        }

        public boolean isTravelled() {
            return this.status.equalsIgnoreCase("TRAVELLED");
        }

        public int getCreditGain() {
            return this.credit_gain;
        }
    }

    public boolean isOfferApplicable() {
        return this.offerApplicable;
    }

    public void setOfferApplicable(boolean z) {
        this.offerApplicable = z;
    }

    public String getOfferText() {
        return this.offerText;
    }

    public void setOfferText(String str) {
        this.offerText = str;
    }

    public int getOfferSelectDenom() {
        return this.offerSelectDenom;
    }

    public List<an> getDenominationsList() {
        return this.denominationsList;
    }

    public void setDenominationsList(List<an> list) {
        this.denominationsList = list;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public int getReferralBalance() {
        return this.referralBalance;
    }

    public int getTotalBalance() {
        return Math.round(this.totalBalance);
    }

    public boolean isVerified() {
        return this.verified;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public ArrayList<AccountBalanceResponse> getInvitees() {
        return this.invitees;
    }

    public boolean isForceLogout() {
        return this.isForceLogout;
    }

    public void setForceLogout(boolean z) {
        this.isForceLogout = z;
    }
}
