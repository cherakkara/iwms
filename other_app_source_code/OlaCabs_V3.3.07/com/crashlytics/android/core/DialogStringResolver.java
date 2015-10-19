package com.crashlytics.android.core;

import android.content.Context;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p016g.PromptSettingsData;

class DialogStringResolver {
    private static final String PROMPT_MESSAGE_RES_NAME = "com.crashlytics.CrashSubmissionPromptMessage";
    private static final String PROMPT_TITLE_RES_NAME = "com.crashlytics.CrashSubmissionPromptTitle";
    private static final String SUBMISSION_ALWAYS_SEND_RES_NAME = "com.crashlytics.CrashSubmissionAlwaysSendTitle";
    private static final String SUBMISSION_CANCEL_RES_NAME = "com.crashlytics.CrashSubmissionCancelTitle";
    private static final String SUBMISSION_SEND_RES_NAME = "com.crashlytics.CrashSubmissionSendTitle";
    private final Context context;
    private final PromptSettingsData promptData;

    public DialogStringResolver(Context context, PromptSettingsData promptSettingsData) {
        this.context = context;
        this.promptData = promptSettingsData;
    }

    public String getTitle() {
        return resourceOrFallbackValue(PROMPT_TITLE_RES_NAME, this.promptData.f357a);
    }

    public String getMessage() {
        return resourceOrFallbackValue(PROMPT_MESSAGE_RES_NAME, this.promptData.f358b);
    }

    public String getSendButtonTitle() {
        return resourceOrFallbackValue(SUBMISSION_SEND_RES_NAME, this.promptData.f359c);
    }

    public String getAlwaysSendButtonTitle() {
        return resourceOrFallbackValue(SUBMISSION_ALWAYS_SEND_RES_NAME, this.promptData.f363g);
    }

    public String getCancelButtonTitle() {
        return resourceOrFallbackValue(SUBMISSION_CANCEL_RES_NAME, this.promptData.f361e);
    }

    private String resourceOrFallbackValue(String str, String str2) {
        return stringOrFallback(CommonUtils.m175b(this.context, str), str2);
    }

    private String stringOrFallback(String str, String str2) {
        return isNullOrEmpty(str) ? str2 : str;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
