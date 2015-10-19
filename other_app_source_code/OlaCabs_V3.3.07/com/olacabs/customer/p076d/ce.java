package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: PersonalDetails */
/* renamed from: com.olacabs.customer.d.ce */
public class ce {
    float balance;
    @SerializedName(a = "balance_notify_limit")
    int balanceNotifyLimit;
    String email;
    @SerializedName(a = "emergency_contact")
    at emergencyContact;
    String mobile;
    String name;
    @SerializedName(a = "referral_code")
    String referralCode;
    boolean verified;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public int getBalance() {
        return Math.round(this.balance);
    }

    public void setBalance(int i) {
        this.balance = (float) i;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public void setReferralCode(String str) {
        this.referralCode = str;
    }

    public int getBalanceNotifyLimit() {
        return this.balanceNotifyLimit;
    }

    public void setBalanceNotifyLimit(int i) {
        this.balanceNotifyLimit = i;
    }

    public at getEmergencyContact() {
        return this.emergencyContact;
    }

    public void setEmergencyContact(at atVar) {
        this.emergencyContact = atVar;
    }
}
