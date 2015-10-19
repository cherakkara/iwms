package com.olacabs.customer.p076d;

import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;

/* compiled from: DeepLinkInfo */
/* renamed from: com.olacabs.customer.d.ak */
public class ak {
    String mAddress;
    String mDeepLinkLat;
    String mDeepLinkLng;
    String mDeeplinkCategory;
    boolean mIsConfPanelPending;
    boolean mIsDeeplinked;
    String mLandingPage;
    String mUtmSource;

    /* renamed from: com.olacabs.customer.d.ak.a */
    public static class DeepLinkInfo {
        private String address;
        private boolean confPanelPending;
        private String deepLinkLat;
        private String deepLinkLng;
        private String deeplinkCategory;
        private boolean deeplinked;
        private String landingPage;
        private String utmSource;

        public DeepLinkInfo() {
            this.deeplinked = false;
            this.confPanelPending = false;
        }

        public DeepLinkInfo latitude(String str) {
            this.deepLinkLat = str;
            return this;
        }

        public DeepLinkInfo longitude(String str) {
            this.deepLinkLng = str;
            return this;
        }

        public DeepLinkInfo cabCategory(String str) {
            this.deeplinkCategory = str;
            return this;
        }

        public DeepLinkInfo utmSource(String str) {
            this.utmSource = str;
            return this;
        }

        public DeepLinkInfo landingPage(String str) {
            this.landingPage = str;
            return this;
        }

        public DeepLinkInfo address(String str) {
            this.address = str;
            return this;
        }

        public DeepLinkInfo deepLinked(boolean z) {
            this.deeplinked = z;
            return this;
        }

        public DeepLinkInfo confPanelPending(boolean z) {
            this.confPanelPending = z;
            return this;
        }

        public ak build() {
            if (this.deepLinkLat == null) {
                this.deepLinkLat = Trace.NULL;
            }
            if (this.deepLinkLng == null) {
                this.deepLinkLng = Trace.NULL;
            }
            if (TextUtils.isEmpty(this.deepLinkLat) || TextUtils.isEmpty(this.deepLinkLng)) {
                this.confPanelPending = false;
            }
            if (this.landingPage == null || this.landingPage.isEmpty()) {
                this.landingPage = Constants.LANDING_PAGE_BOOKING_SCREEN;
            }
            ak akVar = new ak();
            akVar.mDeepLinkLat = this.deepLinkLat;
            akVar.mDeepLinkLng = this.deepLinkLng;
            akVar.mAddress = this.address;
            akVar.mLandingPage = this.landingPage;
            akVar.mDeeplinkCategory = this.deeplinkCategory;
            akVar.mUtmSource = this.utmSource;
            akVar.mIsDeeplinked = this.deeplinked;
            akVar.mIsConfPanelPending = this.confPanelPending;
            return akVar;
        }
    }

    private ak() {
        this.mIsDeeplinked = false;
        this.mIsConfPanelPending = false;
    }

    public String getDeepLinkLng() {
        return this.mDeepLinkLng;
    }

    public String getDeepLinkLat() {
        return this.mDeepLinkLat;
    }

    public String getDeeplinkCategory() {
        return this.mDeeplinkCategory;
    }

    public String getUtmSource() {
        return this.mUtmSource;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getLandingPage() {
        return this.mLandingPage;
    }

    public boolean isDeeplinked() {
        return this.mIsDeeplinked;
    }

    public void setIsConfPanelPending(boolean z) {
        this.mIsConfPanelPending = z;
    }

    public boolean isConfPanelPending() {
        return this.mIsConfPanelPending;
    }
}
