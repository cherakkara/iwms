package com.crashlytics.android.core;

public class CrashlyticsMissingDependencyException extends RuntimeException {
    private static final long serialVersionUID = -1151536370019872859L;

    CrashlyticsMissingDependencyException(String str) {
        super(buildExceptionMessage(str));
    }

    private static String buildExceptionMessage(String str) {
        return "\n" + str + "\n";
    }
}
