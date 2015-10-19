package p004b.p005a.p006a.p007a.p008a.p016g;

/* renamed from: b.a.a.a.a.g.t */
public class SettingsData {
    public final AppSettingsData f379a;
    public final SessionSettingsData f380b;
    public final PromptSettingsData f381c;
    public final FeaturesSettingsData f382d;
    public final AnalyticsSettingsData f383e;
    public final BetaSettingsData f384f;
    public final long f385g;
    public final int f386h;
    public final int f387i;

    public SettingsData(long j, AppSettingsData appSettingsData, SessionSettingsData sessionSettingsData, PromptSettingsData promptSettingsData, FeaturesSettingsData featuresSettingsData, AnalyticsSettingsData analyticsSettingsData, BetaSettingsData betaSettingsData, int i, int i2) {
        this.f385g = j;
        this.f379a = appSettingsData;
        this.f380b = sessionSettingsData;
        this.f381c = promptSettingsData;
        this.f382d = featuresSettingsData;
        this.f386h = i;
        this.f387i = i2;
        this.f383e = analyticsSettingsData;
        this.f384f = betaSettingsData;
    }

    public boolean m469a(long j) {
        return this.f385g < j;
    }
}
