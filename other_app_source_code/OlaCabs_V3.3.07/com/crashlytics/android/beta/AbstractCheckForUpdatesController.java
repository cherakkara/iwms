package com.crashlytics.android.beta;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p010b.ApiKey;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p016g.BetaSettingsData;

abstract class AbstractCheckForUpdatesController implements UpdatesController {
    static final long LAST_UPDATE_CHECK_DEFAULT = 0;
    static final String LAST_UPDATE_CHECK_KEY = "last_update_check";
    private static final long MILLIS_PER_SECOND = 1000;
    private Beta beta;
    private BetaSettingsData betaSettings;
    private BuildProperties buildProps;
    private Context context;
    private CurrentTimeProvider currentTimeProvider;
    private final AtomicBoolean externallyReady;
    private HttpRequestFactory httpRequestFactory;
    private IdManager idManager;
    private final AtomicBoolean initialized;
    private long lastCheckTimeMillis;
    private PreferenceStore preferenceStore;

    public AbstractCheckForUpdatesController() {
        this(false);
    }

    public AbstractCheckForUpdatesController(boolean z) {
        this.initialized = new AtomicBoolean();
        this.lastCheckTimeMillis = LAST_UPDATE_CHECK_DEFAULT;
        this.externallyReady = new AtomicBoolean(z);
    }

    public void initialize(Context context, Beta beta, IdManager idManager, BetaSettingsData betaSettingsData, BuildProperties buildProperties, PreferenceStore preferenceStore, CurrentTimeProvider currentTimeProvider, HttpRequestFactory httpRequestFactory) {
        this.context = context;
        this.beta = beta;
        this.idManager = idManager;
        this.betaSettings = betaSettingsData;
        this.buildProps = buildProperties;
        this.preferenceStore = preferenceStore;
        this.currentTimeProvider = currentTimeProvider;
        this.httpRequestFactory = httpRequestFactory;
        if (signalInitialized()) {
            checkForUpdates();
        }
    }

    protected boolean signalExternallyReady() {
        this.externallyReady.set(true);
        return this.initialized.get();
    }

    boolean signalInitialized() {
        this.initialized.set(true);
        return this.externallyReady.get();
    }

    @SuppressLint({"CommitPrefEdits"})
    protected void checkForUpdates() {
        synchronized (this.preferenceStore) {
            if (this.preferenceStore.m415a().contains(LAST_UPDATE_CHECK_KEY)) {
                this.preferenceStore.m416a(this.preferenceStore.m417b().remove(LAST_UPDATE_CHECK_KEY));
            }
        }
        long a = this.currentTimeProvider.m193a();
        long j = ((long) this.betaSettings.f340b) * MILLIS_PER_SECOND;
        Fabric.m512h().m474a(Beta.TAG, "Check for updates delay: " + j);
        Fabric.m512h().m474a(Beta.TAG, "Check for updates last check time: " + getLastCheckTimeMillis());
        j += getLastCheckTimeMillis();
        Fabric.m512h().m474a(Beta.TAG, "Check for updates current time: " + a + ", next check time: " + j);
        if (a >= j) {
            try {
                performUpdateCheck();
            } finally {
                setLastCheckTimeMillis(a);
            }
        } else {
            Fabric.m512h().m474a(Beta.TAG, "Check for updates next check time was not passed");
        }
    }

    private void performUpdateCheck() {
        Fabric.m512h().m474a(Beta.TAG, "Performing update check");
        String a = new ApiKey().m141a(this.context);
        new CheckForUpdatesRequest(this.beta, this.beta.getOverridenSpiEndpoint(), this.betaSettings.f339a, this.httpRequestFactory, new CheckForUpdatesResponseTransform()).invoke(a, this.idManager.m210a(a, this.buildProps.packageName), this.buildProps);
    }

    void setLastCheckTimeMillis(long j) {
        this.lastCheckTimeMillis = j;
    }

    long getLastCheckTimeMillis() {
        return this.lastCheckTimeMillis;
    }
}
