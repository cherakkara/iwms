package com.crashlytics.android.beta;

import android.content.Context;
import p004b.p005a.p006a.p007a.p008a.p010b.CurrentTimeProvider;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p014e.HttpRequestFactory;
import p004b.p005a.p006a.p007a.p008a.p015f.PreferenceStore;
import p004b.p005a.p006a.p007a.p008a.p016g.BetaSettingsData;

interface UpdatesController {
    void initialize(Context context, Beta beta, IdManager idManager, BetaSettingsData betaSettingsData, BuildProperties buildProperties, PreferenceStore preferenceStore, CurrentTimeProvider currentTimeProvider, HttpRequestFactory httpRequestFactory);

    boolean isActivityLifecycleTriggered();
}
