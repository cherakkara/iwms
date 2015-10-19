package com.olacabs.customer.app;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.localytics.android.Localytics;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.at;
import com.olacabs.customer.p076d.ce;
import com.olacabs.customer.p076d.ci;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

/* renamed from: com.olacabs.customer.app.n */
public class ProfileDataUpdater extends DataUpdater {
    private aj f9394i;

    /* renamed from: com.olacabs.customer.app.n.1 */
    class ProfileDataUpdater implements aj {
        final /* synthetic */ ProfileDataUpdater f9393a;

        ProfileDataUpdater(ProfileDataUpdater profileDataUpdater) {
            this.f9393a = profileDataUpdater;
        }

        public void onFailure(Throwable th) {
            OLog.m13312a(th, "Failed to fetch profile details", new Object[0]);
        }

        public void onSuccess(Object obj) {
            OLog.m13311a("ProfileDetails OnSuccess", new Object[0]);
            ci ciVar = (ci) obj;
            if (ciVar != null && ciVar.isForceLogout()) {
                new ForceLogoutCommand(true).m13270a(this.f9393a.b);
            }
            if (ciVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Fetched profile data", new Object[0]);
                ce personalDetails = ciVar.getPersonalDetails();
                dt c = this.f9393a.e.m13209c();
                c.updateUserInfoOnAutoLogin(personalDetails.getReferralCode(), personalDetails.getMobile(), personalDetails.getBalance(), personalDetails.getEmail(), true, personalDetails.getName(), personalDetails.isVerified());
                this.f9393a.m13328b();
                c.setReferralSchemeForUser(ciVar.isReferralSchemeOn(), ciVar.getReferredEarns(), ciVar.getReferrerEarns());
                this.f9393a.e.m13218d().setFirstTimeOfferDetails(ciVar.getOfferHeader(), ciVar.getOfferText(), ciVar.getOfferId(), ciVar.getOfferTarget());
                c.storeReferralUrls(ciVar.getReferralUrls());
                if (ciVar.getPersonalDetails().getEmergencyContact() != null) {
                    this.f9393a.m13327a(ciVar.getPersonalDetails().getEmergencyContact());
                }
                if (System.currentTimeMillis() - ciVar.getOrigTimeStamp() < 5000) {
                    this.f9393a.e.m13218d().setAppState(AppState.m12881a(ciVar.getStateId()));
                }
                DataUpdaterManager.m13261a(this.f9393a.b).m13263a().m13322a("profile_data", ciVar);
            } else if (ciVar.getStatus().equalsIgnoreCase("FAILURE")) {
                OLog.m13317d("Failed to fetch profile data", new Object[0]);
            }
        }
    }

    public ProfileDataUpdater(Context context) {
        super(context);
        this.f9394i = new ProfileDataUpdater(this);
    }

    public void m13330a() {
        OLog.m13311a("ProfileDataUpdater called : " + this.d.isPreviouslyLoggedIn(), new Object[0]);
        if (this.d.isPreviouslyLoggedIn()) {
            this.e.m13220d(new WeakReference(this.f9394i), a);
        }
    }

    private void m13328b() {
        Map hashMap = new HashMap();
        hashMap.put("OM Amount", m13329c());
        Localytics.tagEvent("current ola money balance", hashMap);
    }

    private String m13329c() {
        dt c = this.e.m13209c();
        String str = "N/A";
        if (c != null) {
            int olaBalance = c.getOlaBalance();
            if (olaBalance == 0) {
                str = "ola money zero";
            } else if (olaBalance > 0 && olaBalance < 100) {
                str = "ola money zero to 100";
            } else if (olaBalance >= 100 && olaBalance < HttpStatus.SC_OK) {
                str = "ola money 100 to 200";
            } else if (olaBalance >= HttpStatus.SC_OK && olaBalance < HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                str = "ola money 200 to 500";
            } else if (olaBalance >= HttpStatus.SC_INTERNAL_SERVER_ERROR && olaBalance <= Constants.MILLIS_IN_A_SECOND) {
                str = "ola money 500 to 1000";
            } else if (olaBalance > Constants.MILLIS_IN_A_SECOND) {
                str = "ola money greater than 1000";
            }
        }
        OLog.m13311a("olaMoney " + str, new Object[0]);
        return str;
    }

    private void m13327a(at atVar) {
        Editor edit = this.g.edit();
        if (atVar != null) {
            edit.putString(Constants.PREF_EMERGENCY_NAME, atVar.getEmergencyContactName());
            edit.putString(Constants.PREF_EMERGENCY_EMAIL, atVar.getEmergencyEmail());
            edit.putString(Constants.PREF_EMERGENCY_PHONE, atVar.getEmergencyMobile());
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_EMAIL_VERIFIED, atVar.isEmailVerified());
            edit.putBoolean(Constants.PREF_EMERGENCY_IS_PHONE_VERIFIED, atVar.isMobileVerified());
            edit.putBoolean(Constants.PREF_IS_AUTO_SHARE, atVar.isEnableAutoShare());
        }
        edit.apply();
    }
}
