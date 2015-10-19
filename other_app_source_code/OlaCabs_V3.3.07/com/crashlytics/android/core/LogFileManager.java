package com.crashlytics.android.core;

import android.content.Context;
import java.io.File;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

class LogFileManager {
    private static final String DIRECTORY_NAME = "log-files";
    private static final String LOGFILE_EXT = ".temp";
    private static final String LOGFILE_PREFIX = "crashlytics-userlog-";
    static final int MAX_LOG_SIZE = 65536;
    private static final NoopLogStore NOOP_LOG_STORE;
    private final Context context;
    private FileLogStore currentLog;
    private final File logFileDir;

    private static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        public void writeToLog(long j, String str) {
        }

        public ByteString getLogAsByteString() {
            return null;
        }

        public void closeLogFile() {
        }

        public void deleteLogFile() {
        }
    }

    static {
        NOOP_LOG_STORE = new NoopLogStore();
    }

    public LogFileManager(Context context, File file) {
        this.context = context;
        this.logFileDir = new File(file, DIRECTORY_NAME);
        this.currentLog = NOOP_LOG_STORE;
    }

    public void onSessionChange(String str) {
        clearLog();
        if (isLoggingEnabled()) {
            setLogFile(getWorkingFileForSession(str), MAX_LOG_SIZE);
            return;
        }
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Preferences requested no custom logs. Aborting log file creation.");
        this.currentLog = NOOP_LOG_STORE;
    }

    public void writeToLog(long j, String str) {
        this.currentLog.writeToLog(j, str);
    }

    public ByteString getByteStringForLog() {
        return this.currentLog.getLogAsByteString();
    }

    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    void setLogFile(File file, int i) {
        this.currentLog.closeLogFile();
        this.currentLog = new QueueFileLogStore(file, i);
    }

    private File getWorkingFileForSession(String str) {
        ensureTargetDirectoryExists();
        return new File(this.logFileDir, LOGFILE_PREFIX + str + LOGFILE_EXT);
    }

    private void ensureTargetDirectoryExists() {
        if (!this.logFileDir.exists()) {
            this.logFileDir.mkdirs();
        }
    }

    private boolean isLoggingEnabled() {
        return CommonUtils.m170a(this.context, "com.crashlytics.CollectCustomLogs", true);
    }
}
