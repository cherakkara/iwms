package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ConfigurationResponse */
/* renamed from: com.olacabs.customer.d.ad */
public class ad implements bv {
    @SerializedName(a = "preemptive_fetch_threshold")
    private long authPreEmptiveFetchThreshold;
    @SerializedName(a = "auto_panel_sub_text")
    private String autoPanelSubText;
    @SerializedName(a = "auto_panel_text")
    private String autoPanelText;
    @SerializedName(a = "background_update")
    private boolean backgroundUpdate;
    @SerializedName(a = "background_update_interval")
    private long backgroundUpdateInterval;
    @SerializedName(a = "images")
    private List<CabImages> cabImagesAvailable;
    @SerializedName(a = "cab_info_interval")
    private int cabInfoInterval;
    @SerializedName(a = "cancel_reasons")
    public HashMap<String, List<String>> cancelReasons;
    @SerializedName(a = "city_tag")
    private String cityTag;
    @SerializedName(a = "contact_numbers")
    public HashMap<String, String> contactNumbers;
    @SerializedName(a = "date_name")
    private String dateName;
    @SerializedName(a = "delivery_panel_sub_text")
    private String deliveryPanelSubText;
    @SerializedName(a = "delivery_panel_text")
    private String diveliryPanelText;
    @SerializedName(a = "sos_missed_call_number")
    public String emergencyMissedCall;
    @SerializedName(a = "enable_auth")
    private boolean enableOAuth;
    @SerializedName(a = "kp_panel_sub_text")
    private String kpPanelSubText;
    @SerializedName(a = "kp_panel_text")
    private String kpPanelText;
    @SerializedName(a = "link_text")
    public String linkText;
    @SerializedName(a = "link_url")
    public String linkUrl;
    @SerializedName(a = "Last_Modified")
    private String mLastModifiedTime;
    @SerializedName(a = "ola_auto_timer_text")
    private String olaAutoTimerText;
    @SerializedName(a = "ola_kp_timer_text")
    private String olaKPTimerText;
    Map<String, String> origRequestParams;
    long origTimeStamp;
    @SerializedName(a = "remove_ec_contact_confirm_header")
    public String removeECHeader;
    @SerializedName(a = "remove_ec_contact_confirm_text")
    public String removeECTEXT;
    @SerializedName(a = "report_issue_text")
    public String reportIssueText;
    @SerializedName(a = "request_timeout")
    private int requestTimeOut;
    @SerializedName(a = "request_type")
    public String requestType;
    @SerializedName(a = "sos_contact_entered_not_verified_header")
    public String sosContactEnteredNotVerifiedHeader;
    @SerializedName(a = "sos_contact_entered_not_verified_text")
    public String sosContactEnteredNotVerifiedText;
    @SerializedName(a = "sos_contact_not_entered_header")
    public String sosContactNotEnteredHeader;
    @SerializedName(a = "sos_contact_not_entered_text")
    public String sosContactNotEnteredText;
    @SerializedName(a = "sos_screen_text")
    public String sosScreenText;
    public String status;
    @SerializedName(a = "tfs_cab_info_polling")
    private int tfsCabInfoPolling;
    @SerializedName(a = "tfs_contact_number")
    public HashMap<String, String> tfsContactNumbers;
    @SerializedName(a = "tfs_timer_1")
    private int tfsTimer1;
    @SerializedName(a = "tfs_timer_2")
    private int tfsTimer2;
    @SerializedName(a = "verify_call_timer")
    private int verifyCallTimer;
    @SerializedName(a = "default_app_viewport")
    private al viewPort;

    public String getSosScreenText() {
        return this.sosScreenText;
    }

    public boolean isBackgroundUpdate() {
        return this.backgroundUpdate;
    }

    public al getViewPort() {
        return this.viewPort;
    }

    public long getBackgroundUpdateInterval() {
        return this.backgroundUpdateInterval;
    }

    public HashMap<String, String> getContactNumbers() {
        return this.contactNumbers;
    }

    public int getCabInfoInterval() {
        return this.cabInfoInterval;
    }

    public int getRequestTimeOut() {
        return this.requestTimeOut;
    }

    public int getVerifyCallTimer() {
        return this.verifyCallTimer;
    }

    public List<CabImages> getCabImagesAvailable() {
        return this.cabImagesAvailable;
    }

    public String getCityTag() {
        return this.cityTag;
    }

    public String getReportIssueText() {
        return this.reportIssueText;
    }

    public String getKpPanelText() {
        return this.kpPanelText;
    }

    public String getKpPanelSubText() {
        return this.kpPanelSubText;
    }

    public String getAutoPanelText() {
        return this.autoPanelText;
    }

    public String getAutoPanelSubText() {
        return this.autoPanelSubText;
    }

    public String getDiveliryPanelText() {
        return this.diveliryPanelText;
    }

    public String getDeliveryPanelSubText() {
        return this.deliveryPanelSubText;
    }

    public String getDateName() {
        return this.dateName;
    }

    public String getOlaAutoTimerText() {
        return this.olaAutoTimerText;
    }

    public String getOlaKPTimerText() {
        return this.olaKPTimerText;
    }

    public String getRemoveECHeader() {
        return this.removeECHeader;
    }

    public String getRemoveECTEXT() {
        return this.removeECTEXT;
    }

    public String getEmergencyMissedCall() {
        return this.emergencyMissedCall;
    }

    public String getLinkText() {
        return this.linkText;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public List<String> getCancelReasonForCategory(String str) {
        if (this.cancelReasons.containsKey(str)) {
            return (List) this.cancelReasons.get(str);
        }
        return new ArrayList();
    }

    public HashMap<String, List<String>> getCancelReasons() {
        return this.cancelReasons;
    }

    public String getSosContactEnteredNotVerifiedHeader() {
        return this.sosContactEnteredNotVerifiedHeader;
    }

    public String getSosContactEnteredNotVerifiedText() {
        return this.sosContactEnteredNotVerifiedText;
    }

    public String getSosContactNotEnteredHeader() {
        return this.sosContactNotEnteredHeader;
    }

    public String getSosContactNotEnteredText() {
        return this.sosContactNotEnteredText;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
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
        return true;
    }

    public boolean markAsInvalid() {
        return false;
    }

    public boolean isEnableOAuth() {
        return this.enableOAuth;
    }

    public long getAuthRefreshThreshold() {
        return this.authPreEmptiveFetchThreshold;
    }

    public HashMap<String, String> getTfsContactNumbers() {
        return this.tfsContactNumbers;
    }

    public int getTfsTimer1() {
        return this.tfsTimer1;
    }

    public int getTfsTimer2() {
        return this.tfsTimer2;
    }

    public int getTfsCabInfoPolling() {
        return this.tfsCabInfoPolling;
    }

    public String getLastModifiedTime() {
        return this.mLastModifiedTime;
    }
}
