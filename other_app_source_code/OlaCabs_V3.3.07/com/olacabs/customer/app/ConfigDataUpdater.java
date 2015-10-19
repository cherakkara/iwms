package com.olacabs.customer.app;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.apsalar.sdk.Apsalar;
import com.localytics.android.Localytics;
import com.olacabs.customer.R;
import com.olacabs.customer.p076d.ad;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.p076d.bj;
import com.olacabs.customer.p076d.bv;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.Ola;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.json.JSONObject;

/* renamed from: com.olacabs.customer.app.e */
public class ConfigDataUpdater extends DataUpdater {
    private DataUpdater f9004i;
    private boolean f9005j;
    private aj f9006k;
    private aj f9007l;

    /* renamed from: com.olacabs.customer.app.e.1 */
    class ConfigDataUpdater implements aj {
        final /* synthetic */ ConfigDataUpdater f9000a;

        ConfigDataUpdater(ConfigDataUpdater configDataUpdater) {
            this.f9000a = configDataUpdater;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetch Configuration Request failed", th);
            this.f9000a.e.m13198b(new WeakReference(this.f9000a.f9007l));
        }

        public void onSuccess(Object obj) {
            OLog.m13311a("Head On Success ", new Object[0]);
            String str = (String) ((bj) obj).getHeaders().get(HttpHeaders.LAST_MODIFIED);
            CharSequence string = this.f9000a.g.getString(bj.CONFIG_LAST_MODIFIED_TIME_KEY, null);
            OLog.m13311a("Last Modified Time : " + str, new Object[0]);
            OLog.m13311a("Stored Last time : " + string, new Object[0]);
            if (TextUtils.equals(str, string)) {
                this.f9000a.m12896b();
                return;
            }
            OLog.m13311a("Time has changed ", new Object[0]);
            this.f9000a.e.m13198b(new WeakReference(this.f9000a.f9007l));
        }
    }

    /* renamed from: com.olacabs.customer.app.e.2 */
    class ConfigDataUpdater implements aj {
        final /* synthetic */ ConfigDataUpdater f9001a;

        ConfigDataUpdater(ConfigDataUpdater configDataUpdater) {
            this.f9001a = configDataUpdater;
        }

        public void onFailure(Throwable th) {
            OLog.m13310a("Fetch Configuration Request failed", th);
        }

        public void onSuccess(Object obj) {
            OLog.m13311a("App Config OnSuccess", new Object[0]);
            this.f9001a.m12892a(obj);
        }
    }

    /* renamed from: com.olacabs.customer.app.e.3 */
    class ConfigDataUpdater implements Runnable {
        final /* synthetic */ Map f9002a;
        final /* synthetic */ ConfigDataUpdater f9003b;

        ConfigDataUpdater(ConfigDataUpdater configDataUpdater, Map map) {
            this.f9003b = configDataUpdater;
            this.f9002a = map;
        }

        public void run() {
            OLog.m13311a("Read from the cache", new Object[0]);
            bv readFromCache = this.f9003b.h.readFromCache("v3/config/new", ad.class, this.f9002a);
            if (readFromCache == null || !readFromCache.isValid(this.f9002a)) {
                OLog.m13311a("Cache is null", new Object[0]);
                this.f9003b.e.m13198b(new WeakReference(this.f9003b.f9007l));
                return;
            }
            OLog.m13311a("Found cache data for v3/config/new", new Object[0]);
            this.f9003b.m12892a((Object) readFromCache);
        }
    }

    public void m12894a(DataUpdater dataUpdater) {
        this.f9004i = dataUpdater;
    }

    public ConfigDataUpdater(Context context) {
        super(context);
        this.f9006k = new ConfigDataUpdater(this);
        this.f9007l = new ConfigDataUpdater(this);
    }

    public void m12893a() {
        OLog.m13311a("ConfigDataUpdate called", new Object[0]);
        this.e.m13170a(new WeakReference(this.f9006k));
    }

    private void m12892a(Object obj) {
        OLog.m13311a("updateConfig", new Object[0]);
        ad adVar = (ad) obj;
        if (adVar.getStatus().equalsIgnoreCase("SUCCESS")) {
            da d = this.e.m13218d();
            if (!(this.e == null || d == null)) {
                d.setConfigurations(adVar);
                d.setBookingCancelReasons(adVar.getCancelReasons());
                d.setIsAuthEnable(adVar.isEnableOAuth());
                d.setAuthRefreshTokenThreshold(adVar.getAuthRefreshThreshold());
            }
            Ola.f11504u = adVar.getCityTag();
            Ola.f11488e = adVar.getCabImagesAvailable();
            Ola.f11495l = adVar.getKpPanelText();
            Ola.f11496m = adVar.getKpPanelSubText();
            Ola.f11497n = adVar.getAutoPanelText();
            Ola.f11498o = adVar.getAutoPanelSubText();
            Ola.f11499p = adVar.getDiveliryPanelText();
            Ola.f11500q = adVar.getDeliveryPanelSubText();
            Ola.f11501r = adVar.getDateName();
            Ola.f11502s = adVar.getOlaAutoTimerText();
            Ola.f11503t = adVar.getOlaKPTimerText();
            Ola.f11505v = adVar.getReportIssueText();
            Ola.f11507x = adVar.getLinkText();
            Ola.f11506w = adVar.getLinkUrl();
            Ola.f11508y = adVar.getRemoveECHeader();
            Ola.f11509z = adVar.getRemoveECTEXT();
            Ola.f11480D = adVar.getSosContactNotEnteredText();
            Ola.f11479C = adVar.getSosContactNotEnteredHeader();
            Ola.f11477A = adVar.getSosContactEnteredNotVerifiedHeader();
            Ola.f11478B = adVar.getSosContactEnteredNotVerifiedText();
            Ola.f11481E = adVar.getEmergencyMissedCall();
            Ola.f11482F = adVar.getSosScreenText();
            if (this.f9005j) {
                Map hashMap = new HashMap();
                hashMap.put("City name", adVar.getCityTag());
                Localytics.tagEvent("City At App Launch", hashMap);
                Apsalar.event("City At App Launch", new JSONObject(hashMap));
            }
            Constants.MAP_REFRESH_INTERVAL = adVar.getBackgroundUpdateInterval();
            if (adVar.getContactNumbers().containsKey(adVar.getCityTag())) {
                Ola.f11486c = (String) adVar.getContactNumbers().get(adVar.getCityTag());
                if (!(this.e == null || d == null)) {
                    d.setCallCenterNumber((String) adVar.getContactNumbers().get(adVar.getCityTag()));
                }
                if (this.e.m13209c() != null) {
                    this.e.m13209c().setCity(adVar.getCityTag());
                    Sherlock.m13344b(this.b);
                }
            } else {
                d.setCallCenterNumber(this.b.getString(R.string.default_call_center_number));
                Ola.f11486c = this.b.getString(R.string.default_call_center_number);
            }
            if (d.isAuthEnabled() && this.f9004i != null) {
                this.f9004i.m12883a();
            }
            d.setConfigurationLoaded(true);
        }
    }

    public void m12896b() {
        String str = "v3/config/new";
        OlaApp olaApp = (OlaApp) this.b.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.c.getUserId());
        Location userLocation = olaApp.m12878a().m13209c().getUserLocation();
        if (userLocation != null) {
            hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
        }
        hashMap.put(ao.DEVICE_RESOLUTION_KEY, olaApp.m12878a().m13224e().getScreenSize());
        this.f.post(new ConfigDataUpdater(this, hashMap));
    }

    public void m12895a(boolean z) {
        this.f9005j = z;
    }
}
