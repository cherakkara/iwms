package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: EmergencyContact */
/* renamed from: com.olacabs.customer.d.at */
public class at {
    @SerializedName(a = "name")
    private String emergencyContactName;
    @SerializedName(a = "email")
    private String emergencyEmail;
    @SerializedName(a = "phone")
    private String emergencyMobile;
    @SerializedName(a = "enable_auto_share")
    private boolean enableAutoShare;
    @SerializedName(a = "footer_text")
    private String footerText;
    @SerializedName(a = "email_verified")
    private boolean isEmailVerified;
    @SerializedName(a = "phone_verified")
    private boolean isMobileVerified;
    @SerializedName(a = "emergency_status_text")
    private String statusText;

    public String getEmergencyContactName() {
        return this.emergencyContactName;
    }

    public String getEmergencyMobile() {
        return this.emergencyMobile;
    }

    public String getEmergencyEmail() {
        return this.emergencyEmail;
    }

    public boolean isMobileVerified() {
        return this.isMobileVerified;
    }

    public boolean isEmailVerified() {
        return this.isEmailVerified;
    }

    public boolean isEnableAutoShare() {
        return this.enableAutoShare;
    }

    public void setEnableAutoShare(boolean z) {
        this.enableAutoShare = z;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public String getFooterText() {
        return this.footerText;
    }
}
