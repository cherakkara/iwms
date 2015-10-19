package com.crashlytics.android.core;

import android.util.Log;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

class BuildIdValidator {
    private static final String MESSAGE = "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    private final String buildId;
    private final boolean requiringBuildId;

    public BuildIdValidator(String str, boolean z) {
        this.buildId = str;
        this.requiringBuildId = z;
    }

    public void validate(String str, String str2) {
        if (CommonUtils.m180c(this.buildId) && this.requiringBuildId) {
            String message = getMessage(str, str2);
            Log.e(CrashlyticsCore.TAG, ".");
            Log.e(CrashlyticsCore.TAG, ".     |  | ");
            Log.e(CrashlyticsCore.TAG, ".     |  |");
            Log.e(CrashlyticsCore.TAG, ".     |  |");
            Log.e(CrashlyticsCore.TAG, ".   \\ |  | /");
            Log.e(CrashlyticsCore.TAG, ".    \\    /");
            Log.e(CrashlyticsCore.TAG, ".     \\  /");
            Log.e(CrashlyticsCore.TAG, ".      \\/");
            Log.e(CrashlyticsCore.TAG, ".");
            Log.e(CrashlyticsCore.TAG, message);
            Log.e(CrashlyticsCore.TAG, ".");
            Log.e(CrashlyticsCore.TAG, ".      /\\");
            Log.e(CrashlyticsCore.TAG, ".     /  \\");
            Log.e(CrashlyticsCore.TAG, ".    /    \\");
            Log.e(CrashlyticsCore.TAG, ".   / |  | \\");
            Log.e(CrashlyticsCore.TAG, ".     |  |");
            Log.e(CrashlyticsCore.TAG, ".     |  |");
            Log.e(CrashlyticsCore.TAG, ".     |  |");
            Log.e(CrashlyticsCore.TAG, ".");
            throw new CrashlyticsMissingDependencyException(message);
        } else if (!this.requiringBuildId) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Configured not to require a build ID.");
        }
    }

    protected String getMessage(String str, String str2) {
        return MESSAGE;
    }
}
