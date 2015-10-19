package com.newrelic.agent.android.analytics;

import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Set;

public class AnalyticsEventFactory {

    /* renamed from: com.newrelic.agent.android.analytics.AnalyticsEventFactory.1 */
    static /* synthetic */ class C07311 {
        static final /* synthetic */ int[] f8900x9155d312;

        static {
            f8900x9155d312 = new int[AnalyticsEventCategory.values().length];
            try {
                f8900x9155d312[AnalyticsEventCategory.Session.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8900x9155d312[AnalyticsEventCategory.Interaction.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8900x9155d312[AnalyticsEventCategory.Crash.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8900x9155d312[AnalyticsEventCategory.Custom.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static AnalyticsEvent createEvent(String str, AnalyticsEventCategory analyticsEventCategory, String str2, Set<AnalyticAttribute> set) {
        switch (C07311.f8900x9155d312[analyticsEventCategory.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return new SessionEvent(set);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return new InteractionEvent(str, set);
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return new CrashEvent(str, set);
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return new CustomEvent(str, str2, set);
            default:
                return null;
        }
    }

    private AnalyticsEventFactory() {
    }
}
