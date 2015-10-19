package com.crashlytics.android.core;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.core.internal.models.SessionEventData;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.Logger;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;
import p004b.p005a.p006a.p007a.p008a.p010b.DeliveryMechanism;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p016g.SessionSettingsData;
import p004b.p005a.p006a.p007a.p008a.p016g.Settings;

class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static final int ANALYZER_VERSION = 1;
    static final FilenameFilter ANY_SESSION_FILENAME_FILTER;
    static final String CLS_CRASH_MARKER_FILE_NAME = "crash_marker";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
    static final Comparator<File> LARGEST_FILE_NAME_FIRST;
    private static final int MAX_COMPLETE_SESSIONS_COUNT = 4;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER;
    static final String SESSION_APP_TAG = "SessionApp";
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER;
    private static final Pattern SESSION_FILE_PATTERN;
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST;
    private final CrashlyticsCore crashlyticsCore;
    private final UncaughtExceptionHandler defaultHandler;
    private final AtomicInteger eventCounter;
    private final CrashlyticsExecutorServiceWrapper executorServiceWrapper;
    private final File filesDir;
    private final IdManager idManager;
    private final AtomicBoolean isHandlingException;
    private final LogFileManager logFileManager;
    private boolean powerConnected;
    private final BroadcastReceiver powerConnectedReceiver;
    private final BroadcastReceiver powerDisconnectedReceiver;
    private final AtomicBoolean receiversRegistered;
    private final SessionDataWriter sessionDataWriter;

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.10 */
    class AnonymousClass10 implements Callable<Void> {
        final /* synthetic */ String val$userEmail;
        final /* synthetic */ String val$userId;
        final /* synthetic */ String val$userName;

        AnonymousClass10(String str, String str2, String str3) {
            this.val$userId = str;
            this.val$userName = str2;
            this.val$userEmail = str3;
        }

        public Void call() throws Exception {
            new MetaDataStore(CrashlyticsUncaughtExceptionHandler.this.filesDir).writeUserData(CrashlyticsUncaughtExceptionHandler.this.getCurrentSessionId(), new UserMetaData(this.val$userId, this.val$userName, this.val$userEmail));
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.11 */
    class AnonymousClass11 implements Callable<Void> {
        final /* synthetic */ Map val$keyData;

        AnonymousClass11(Map map) {
            this.val$keyData = map;
        }

        public Void call() throws Exception {
            new MetaDataStore(CrashlyticsUncaughtExceptionHandler.this.filesDir).writeKeyData(CrashlyticsUncaughtExceptionHandler.this.getCurrentSessionId(), this.val$keyData);
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.15 */
    class AnonymousClass15 implements FilenameFilter {
        final /* synthetic */ String val$sessionId;

        AnonymousClass15(String str) {
            this.val$sessionId = str;
        }

        public boolean accept(File file, String str) {
            return str.startsWith(this.val$sessionId);
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.16 */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ File val$toSend;

        AnonymousClass16(File file) {
            this.val$toSend = file;
        }

        public void run() {
            if (CommonUtils.m191n(CrashlyticsUncaughtExceptionHandler.this.crashlyticsCore.getContext())) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Attempting to send crash report at time of crash...");
                CreateReportSpiCall createReportSpiCall = CrashlyticsUncaughtExceptionHandler.this.crashlyticsCore.getCreateReportSpiCall(Settings.m462a().m466b());
                if (createReportSpiCall != null) {
                    new ReportUploader(createReportSpiCall).forceUpload(new SessionReport(this.val$toSend, CrashlyticsUncaughtExceptionHandler.SEND_AT_CRASHTIME_HEADER));
                }
            }
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.1 */
    static class C01341 implements FilenameFilter {
        C01341() {
        }

        public boolean accept(File file, String str) {
            return str.length() == ClsFileOutputStream.SESSION_FILE_EXTENSION.length() + CrashlyticsUncaughtExceptionHandler.SESSION_ID_LENGTH && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.2 */
    static class C01352 implements Comparator<File> {
        C01352() {
        }

        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.3 */
    static class C01363 implements Comparator<File> {
        C01363() {
        }

        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.4 */
    static class C01374 implements FilenameFilter {
        C01374() {
        }

        public boolean accept(File file, String str) {
            return CrashlyticsUncaughtExceptionHandler.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.5 */
    class C01385 extends BroadcastReceiver {
        C01385() {
        }

        public void onReceive(Context context, Intent intent) {
            CrashlyticsUncaughtExceptionHandler.this.powerConnected = true;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.6 */
    class C01396 extends BroadcastReceiver {
        C01396() {
        }

        public void onReceive(Context context, Intent intent) {
            CrashlyticsUncaughtExceptionHandler.this.powerConnected = false;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.7 */
    class C01407 implements Callable<Void> {
        final /* synthetic */ Throwable val$ex;
        final /* synthetic */ Date val$now;
        final /* synthetic */ Thread val$thread;

        C01407(Date date, Thread thread, Throwable th) {
            this.val$now = date;
            this.val$thread = thread;
            this.val$ex = th;
        }

        public Void call() throws Exception {
            CrashlyticsUncaughtExceptionHandler.this.handleUncaughtException(this.val$now, this.val$thread, this.val$ex);
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.8 */
    class C01418 implements Callable<Void> {
        final /* synthetic */ String val$msg;
        final /* synthetic */ long val$timestamp;

        C01418(long j, String str) {
            this.val$timestamp = j;
            this.val$msg = str;
        }

        public Void call() throws Exception {
            if (!CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                CrashlyticsUncaughtExceptionHandler.this.logFileManager.writeToLog(this.val$timestamp, this.val$msg);
            }
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.9 */
    class C01429 implements Runnable {
        final /* synthetic */ Throwable val$ex;
        final /* synthetic */ Date val$now;
        final /* synthetic */ Thread val$thread;

        C01429(Date date, Thread thread, Throwable th) {
            this.val$now = date;
            this.val$thread = thread;
            this.val$ex = th;
        }

        public void run() {
            if (!CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                CrashlyticsUncaughtExceptionHandler.this.doWriteNonFatal(this.val$now, this.val$thread, this.val$ex);
            }
        }
    }

    private static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsUncaughtExceptionHandler.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsUncaughtExceptionHandler.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            if (str.equals(this.sessionId + ClsFileOutputStream.SESSION_FILE_EXTENSION) || !str.contains(this.sessionId) || str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) {
                return false;
            }
            return true;
        }
    }

    static {
        SESSION_FILE_FILTER = new C01341();
        LARGEST_FILE_NAME_FIRST = new C01352();
        SMALLEST_FILE_NAME_FIRST = new C01363();
        ANY_SESSION_FILENAME_FILTER = new C01374();
        SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
        SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    }

    CrashlyticsUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, CrashlyticsExecutorServiceWrapper crashlyticsExecutorServiceWrapper, IdManager idManager, SessionDataWriter sessionDataWriter, CrashlyticsCore crashlyticsCore) {
        this.eventCounter = new AtomicInteger(0);
        this.receiversRegistered = new AtomicBoolean(false);
        this.defaultHandler = uncaughtExceptionHandler;
        this.executorServiceWrapper = crashlyticsExecutorServiceWrapper;
        this.idManager = idManager;
        this.crashlyticsCore = crashlyticsCore;
        this.sessionDataWriter = sessionDataWriter;
        this.isHandlingException = new AtomicBoolean(false);
        this.filesDir = crashlyticsCore.getSdkDirectory();
        this.logFileManager = new LogFileManager(crashlyticsCore.getContext(), this.filesDir);
        notifyCrashlyticsListenerOfPreviousCrash(crashlyticsListener);
        this.powerConnectedReceiver = new C01385();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.powerDisconnectedReceiver = new C01396();
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        Context context = crashlyticsCore.getContext();
        context.registerReceiver(this.powerConnectedReceiver, intentFilter);
        context.registerReceiver(this.powerDisconnectedReceiver, intentFilter2);
        this.receiversRegistered.set(true);
    }

    public synchronized void uncaughtException(Thread thread, Throwable th) {
        this.isHandlingException.set(true);
        try {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
            if (!this.receiversRegistered.getAndSet(true)) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Unregistering power receivers.");
                Context context = this.crashlyticsCore.getContext();
                context.unregisterReceiver(this.powerConnectedReceiver);
                context.unregisterReceiver(this.powerDisconnectedReceiver);
            }
            this.executorServiceWrapper.executeSyncLoggingException(new C01407(new Date(), thread, th));
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        } catch (Throwable e) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        } catch (Throwable th2) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
    }

    private void handleUncaughtException(Date date, Thread thread, Throwable th) throws Exception {
        writeFatal(date, thread, th);
        doCloseSessions();
        doOpenSession();
        trimSessionFiles();
        if (!this.crashlyticsCore.shouldPromptUserBeforeSendingCrashReports()) {
            sendSessionReports();
        }
    }

    boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    private void notifyCrashlyticsListenerOfPreviousCrash(CrashlyticsListener crashlyticsListener) {
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for previous crash marker.");
        File file = new File(this.crashlyticsCore.getSdkDirectory(), CLS_CRASH_MARKER_FILE_NAME);
        if (file.exists()) {
            file.delete();
            if (crashlyticsListener != null) {
                try {
                    crashlyticsListener.crashlyticsDidDetectCrashDuringPreviousExecution();
                } catch (Throwable e) {
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
                }
            }
        }
    }

    void writeToLog(long j, String str) {
        this.executorServiceWrapper.executeAsync(new C01418(j, str));
    }

    void writeNonFatalException(Thread thread, Throwable th) {
        this.executorServiceWrapper.executeAsync(new C01429(new Date(), thread, th));
    }

    private void writeFatal(Date date, Thread thread, Throwable th) {
        Throwable e;
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        try {
            Closeable closeable;
            new File(this.filesDir, CLS_CRASH_MARKER_FILE_NAME).createNewFile();
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId != null) {
                CrashlyticsCore.recordFatalExceptionEvent(currentSessionId);
                clsFileOutputStream = new ClsFileOutputStream(this.filesDir, currentSessionId + SESSION_FATAL_TAG);
                try {
                    flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                    writeSessionEvent(flushable, date, thread, th, EVENT_TYPE_CRASH, true);
                    closeable = clsFileOutputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
                        ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
                        CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m167a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th2) {
                        e = th2;
                        CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m167a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw e;
                    }
                }
            }
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Tried to write a fatal exception while no session was open.", null);
            closeable = null;
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
            ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw e;
        }
    }

    private void writeExternalCrashEvent(SessionEventData sessionEventData) throws IOException {
        Closeable clsFileOutputStream;
        Throwable th;
        Closeable closeable;
        Flushable flushable;
        Throwable th2;
        Flushable flushable2;
        Flushable flushable3 = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId != null) {
                CrashlyticsCore.recordFatalExceptionEvent(currentSessionId);
                clsFileOutputStream = new ClsFileOutputStream(this.filesDir, currentSessionId + SESSION_FATAL_TAG);
                try {
                    flushable3 = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                } catch (Throwable e) {
                    th = e;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", th2);
                        ExceptionUtils.writeStackTraceIfNotNull(th2, closeable);
                        CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th3) {
                        th2 = th3;
                        CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
                        throw th2;
                    }
                } catch (Throwable e2) {
                    th = e2;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
                    throw th2;
                }
                try {
                    NativeCrashWriter.writeNativeCrash(sessionEventData, this.logFileManager, new MetaDataStore(this.filesDir).readKeyData(currentSessionId), flushable3);
                } catch (Throwable e22) {
                    th = e22;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", th2);
                    ExceptionUtils.writeStackTraceIfNotNull(th2, closeable);
                    CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
                } catch (Throwable e222) {
                    th = e222;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
                    throw th2;
                }
            }
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Tried to write a native crash while no session was open.", null);
            Object obj = flushable3;
            CommonUtils.m168a(flushable3, "Failed to flush to session begin file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable e3) {
            closeable = flushable3;
            flushable2 = flushable3;
            th2 = e3;
            flushable = flushable2;
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the native crash logger", th2);
            ExceptionUtils.writeStackTraceIfNotNull(th2, closeable);
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Throwable e32) {
            closeable = flushable3;
            flushable2 = flushable3;
            th2 = e32;
            flushable = flushable2;
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(closeable, "Failed to close fatal exception file output stream.");
            throw th2;
        }
    }

    void cacheUserData(String str, String str2, String str3) {
        this.executorServiceWrapper.executeAsync(new AnonymousClass10(str, str2, str3));
    }

    void cacheKeyData(Map<String, String> map) {
        this.executorServiceWrapper.executeAsync(new AnonymousClass11(map));
    }

    void ensureOpenSessionExists() {
        this.executorServiceWrapper.executeAsync(new Callable<Void>() {
            public Void call() throws Exception {
                if (CrashlyticsUncaughtExceptionHandler.this.hasOpenSession()) {
                    CrashlyticsUncaughtExceptionHandler.this.logFileManager.onSessionChange(CrashlyticsUncaughtExceptionHandler.this.getCurrentSessionId());
                } else {
                    CrashlyticsUncaughtExceptionHandler.this.doOpenSession();
                }
                return null;
            }
        });
    }

    private String getCurrentSessionId() {
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(SESSION_BEGIN_TAG));
        Arrays.sort(listFilesMatching, LARGEST_FILE_NAME_FIRST);
        return listFilesMatching.length > 0 ? getSessionIdFromSessionFile(listFilesMatching[0]) : null;
    }

    private String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, SESSION_ID_LENGTH);
    }

    boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    boolean finalizeSessions() {
        return ((Boolean) this.executorServiceWrapper.executeSyncLoggingException(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                if (CrashlyticsUncaughtExceptionHandler.this.isHandlingException.get()) {
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, "Skipping session finalization because a crash has already occurred.");
                    return Boolean.valueOf(false);
                }
                SessionEventData externalCrashEventData = CrashlyticsUncaughtExceptionHandler.this.crashlyticsCore.getExternalCrashEventData();
                if (externalCrashEventData != null) {
                    CrashlyticsUncaughtExceptionHandler.this.writeExternalCrashEvent(externalCrashEventData);
                }
                CrashlyticsUncaughtExceptionHandler.this.doCloseSessions();
                CrashlyticsUncaughtExceptionHandler.this.doOpenSession();
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Open sessions were closed and a new session was opened.");
                return Boolean.valueOf(true);
            }
        })).booleanValue();
    }

    private void doOpenSession() throws Exception {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.idManager).toString();
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Opening an new session with ID " + clsuuid);
        this.logFileManager.onSessionChange(clsuuid);
        writeBeginSession(clsuuid, date);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
    }

    private void doCloseSessions() throws Exception {
        trimOpenSessions(MAX_OPEN_SESSIONS);
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId != null) {
            writeSessionUser(currentSessionId);
            SessionSettingsData sessionSettingsData = this.crashlyticsCore.getSessionSettingsData();
            if (sessionSettingsData != null) {
                int i = sessionSettingsData.f366c;
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Closing all open sessions.");
                File[] listSessionBeginFiles = listSessionBeginFiles();
                if (listSessionBeginFiles != null && listSessionBeginFiles.length > 0) {
                    int length = listSessionBeginFiles.length;
                    for (int i2 = 0; i2 < length; i2 += ANALYZER_VERSION) {
                        File file = listSessionBeginFiles[i2];
                        String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Closing session: " + sessionIdFromSessionFile);
                        writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i);
                    }
                    return;
                }
                return;
            }
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Unable to close session. Settings are not loaded.");
            return;
        }
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "No open sessions exist.");
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void deleteSessionPartFilesFor(String str) {
        File[] listSessionPartFilesFor = listSessionPartFilesFor(str);
        int length = listSessionPartFilesFor.length;
        for (int i = 0; i < length; i += ANALYZER_VERSION) {
            listSessionPartFilesFor[i].delete();
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    private File[] listCompleteSessionFiles() {
        return listFilesMatching(SESSION_FILE_FILTER);
    }

    File[] listSessionBeginFiles() {
        return listFilesMatching(new FileNameContainsFilter(SESSION_BEGIN_TAG));
    }

    private File[] listFilesMatching(FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(this.filesDir.listFiles(filenameFilter));
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void trimSessionEventFiles(String str, int i) {
        Utils.capFileCount(this.filesDir, new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG), i, SMALLEST_FILE_NAME_FIRST);
    }

    void trimSessionFiles() {
        Utils.capFileCount(this.filesDir, SESSION_FILE_FILTER, MAX_COMPLETE_SESSIONS_COUNT, SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        int i2 = 0;
        Set hashSet = new HashSet();
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        int min = Math.min(i, listSessionBeginFiles.length);
        for (int i3 = 0; i3 < min; i3 += ANALYZER_VERSION) {
            hashSet.add(getSessionIdFromSessionFile(listSessionBeginFiles[i3]));
        }
        File[] listFilesMatching = listFilesMatching(new AnySessionPartFileFilter());
        int length = listFilesMatching.length;
        while (i2 < length) {
            File file = listFilesMatching[i2];
            Object name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(ANALYZER_VERSION))) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Trimming open session file: " + name);
                file.delete();
            }
            i2 += ANALYZER_VERSION;
        }
    }

    void cleanInvalidTempFiles() {
        this.executorServiceWrapper.executeAsync(new Runnable() {
            public void run() {
                CrashlyticsUncaughtExceptionHandler.this.doCleanInvalidTempFiles(CrashlyticsUncaughtExceptionHandler.this.listFilesMatching(ClsFileOutputStream.TEMP_FILENAME_FILTER));
            }
        });
    }

    void doCleanInvalidTempFiles(File[] fileArr) {
        deleteLegacyInvalidCacheDir();
        int length = fileArr.length;
        for (int i = 0; i < length; i += ANALYZER_VERSION) {
            File file = fileArr[i];
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Found invalid session part file: " + file);
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            FilenameFilter anonymousClass15 = new AnonymousClass15(sessionIdFromSessionFile);
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Deleting all part files for invalid session: " + sessionIdFromSessionFile);
            File[] listFilesMatching = listFilesMatching(anonymousClass15);
            int length2 = listFilesMatching.length;
            for (int i2 = 0; i2 < length2; i2 += ANALYZER_VERSION) {
                File file2 = listFilesMatching[i2];
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Deleting session file: " + file2);
                file2.delete();
            }
        }
    }

    private void deleteLegacyInvalidCacheDir() {
        File file = new File(this.crashlyticsCore.getSdkDirectory(), INVALID_CLS_CACHE_DIR);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                int length = listFiles.length;
                for (int i = 0; i < length; i += ANALYZER_VERSION) {
                    listFiles[i].delete();
                }
            }
            file.delete();
        }
    }

    private void writeBeginSession(String str, Date date) throws Exception {
        Closeable clsFileOutputStream;
        Throwable e;
        OutputStream outputStream;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str + SESSION_BEGIN_TAG);
            try {
                flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                Locale locale = Locale.US;
                String str2 = GENERATOR_FORMAT;
                Object[] objArr = new Object[ANALYZER_VERSION];
                objArr[0] = this.crashlyticsCore.getVersion();
                this.sessionDataWriter.writeBeginSession(flushable, str, String.format(locale, str2, objArr), date.getTime() / 1000);
                CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close begin session file.");
            } catch (Exception e2) {
                e = e2;
                Closeable closeable = clsFileOutputStream;
                try {
                    ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    clsFileOutputStream = outputStream;
                    CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close begin session file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close begin session file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            outputStream = null;
            ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close begin session file.");
            throw e;
        }
    }

    private void writeSessionApp(String str) throws Exception {
        Closeable clsFileOutputStream;
        Throwable e;
        OutputStream outputStream;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str + SESSION_APP_TAG);
            try {
                flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                this.sessionDataWriter.writeSessionApp(flushable, this.crashlyticsCore.getPackageName(), this.crashlyticsCore.getVersionCode(), this.crashlyticsCore.getVersionName(), this.idManager.m212b(), DeliveryMechanism.m194a(this.crashlyticsCore.getInstallerPackageName()).m195a());
                CommonUtils.m168a(flushable, "Failed to flush to session app file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close session app file.");
            } catch (Exception e2) {
                e = e2;
                Closeable closeable = clsFileOutputStream;
                try {
                    ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    clsFileOutputStream = outputStream;
                    CommonUtils.m168a(flushable, "Failed to flush to session app file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close session app file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m168a(flushable, "Failed to flush to session app file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close session app file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            outputStream = null;
            ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush to session app file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close session app file.");
            throw e;
        }
    }

    private void writeSessionOS(String str) throws Exception {
        Closeable clsFileOutputStream;
        Throwable e;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str + SESSION_OS_TAG);
            try {
                flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                this.sessionDataWriter.writeSessionOS(flushable, CommonUtils.m184g(this.crashlyticsCore.getContext()));
                CommonUtils.m168a(flushable, "Failed to flush to session OS file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close session OS file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    CommonUtils.m168a(flushable, "Failed to flush to session OS file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close session OS file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            clsFileOutputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush to session OS file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close session OS file.");
            throw e;
        }
    }

    private void writeSessionDevice(String str) throws Exception {
        Throwable e;
        OutputStream outputStream;
        OutputStream outputStream2 = null;
        Flushable flushable = null;
        try {
            OutputStream clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str + SESSION_DEVICE_TAG);
            try {
                flushable = CodedOutputStream.newInstance(clsFileOutputStream);
                Context context = this.crashlyticsCore.getContext();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                this.sessionDataWriter.writeSessionDevice(flushable, this.idManager.m216f(), CommonUtils.m147a(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.m171b(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), CommonUtils.m183f(context), this.idManager.m217g(), CommonUtils.m185h(context), Build.MANUFACTURER, Build.PRODUCT);
                CommonUtils.m168a(flushable, "Failed to flush session device info.");
                CommonUtils.m167a((Closeable) clsFileOutputStream, "Failed to close session device file.");
            } catch (Exception e2) {
                e = e2;
                outputStream2 = clsFileOutputStream;
                try {
                    ExceptionUtils.writeStackTraceIfNotNull(e, outputStream2);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    outputStream = outputStream2;
                    CommonUtils.m168a(flushable, "Failed to flush session device info.");
                    CommonUtils.m167a((Closeable) outputStream, "Failed to close session device file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m168a(flushable, "Failed to flush session device info.");
                CommonUtils.m167a((Closeable) outputStream, "Failed to close session device file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            ExceptionUtils.writeStackTraceIfNotNull(e, outputStream2);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            outputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush session device info.");
            CommonUtils.m167a((Closeable) outputStream, "Failed to close session device file.");
            throw e;
        }
    }

    private void writeSessionUser(String str) throws Exception {
        Closeable clsFileOutputStream;
        Throwable e;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str + SESSION_USER_TAG);
            try {
                flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                UserMetaData userMetaData = getUserMetaData(str);
                if (userMetaData.isEmpty()) {
                    CommonUtils.m168a(flushable, "Failed to flush session user file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close session user file.");
                    return;
                }
                this.sessionDataWriter.writeSessionUser(flushable, userMetaData.id, userMetaData.name, userMetaData.email);
                CommonUtils.m168a(flushable, "Failed to flush session user file.");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close session user file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    CommonUtils.m168a(flushable, "Failed to flush session user file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close session user file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            ExceptionUtils.writeStackTraceIfNotNull(e, clsFileOutputStream);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            clsFileOutputStream = null;
            CommonUtils.m168a(flushable, "Failed to flush session user file.");
            CommonUtils.m167a(clsFileOutputStream, "Failed to close session user file.");
            throw e;
        }
    }

    private UserMetaData getUserMetaData(String str) {
        return isHandlingException() ? new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail()) : new MetaDataStore(this.filesDir).readUserData(str);
    }

    private void writeSessionEvent(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) throws Exception {
        Thread[] threadArr;
        Map map;
        Context context = this.crashlyticsCore.getContext();
        long time = date.getTime() / 1000;
        float c = CommonUtils.m177c(context);
        int a = CommonUtils.m149a(context, this.powerConnected);
        boolean d = CommonUtils.m181d(context);
        int i = context.getResources().getConfiguration().orientation;
        long b = CommonUtils.m171b() - CommonUtils.m172b(context);
        long b2 = CommonUtils.m173b(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo a2 = CommonUtils.m151a(context.getPackageName(), context);
        List linkedList = new LinkedList();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            threadArr = new Thread[allStackTraces.size()];
            int i2 = 0;
            for (Entry entry : allStackTraces.entrySet()) {
                threadArr[i2] = (Thread) entry.getKey();
                linkedList.add(entry.getValue());
                i2 += ANALYZER_VERSION;
            }
        } else {
            threadArr = new Thread[0];
        }
        if (CommonUtils.m170a(context, "com.crashlytics.CollectCustomKeys", true)) {
            Map attributes = this.crashlyticsCore.getAttributes();
            if (attributes == null || attributes.size() <= ANALYZER_VERSION) {
                map = attributes;
            } else {
                Map treeMap = new TreeMap(attributes);
            }
        } else {
            map = new TreeMap();
        }
        CodedOutputStream codedOutputStream2 = codedOutputStream;
        Thread thread2 = thread;
        Throwable th2 = th;
        String str2 = str;
        this.sessionDataWriter.writeSessionEvent(codedOutputStream2, time, thread2, th2, str2, threadArr, c, a, d, i, b, b2, a2, linkedList, stackTrace, this.logFileManager, map);
    }

    private void doWriteNonFatal(Date date, Thread thread, Throwable th) {
        Throwable e;
        Closeable closeable;
        Flushable flushable = null;
        String currentSessionId = getCurrentSessionId();
        if (currentSessionId != null) {
            CrashlyticsCore.recordLoggedExceptionEvent(currentSessionId);
            try {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
                Closeable clsFileOutputStream = new ClsFileOutputStream(this.filesDir, currentSessionId + SESSION_NON_FATAL_TAG + CommonUtils.m153a(this.eventCounter.getAndIncrement()));
                try {
                    flushable = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                    writeSessionEvent(flushable, date, thread, th, EVENT_TYPE_LOGGED, false);
                    CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                } catch (Exception e2) {
                    e = e2;
                    closeable = clsFileOutputStream;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
                        ExceptionUtils.writeStackTraceIfNotNull(e, closeable);
                        CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.m167a(closeable, "Failed to close non-fatal file output stream.");
                        trimSessionEventFiles(currentSessionId, MAX_LOCAL_LOGGED_EXCEPTIONS);
                        return;
                    } catch (Throwable th2) {
                        e = th2;
                        CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.m167a(closeable, "Failed to close non-fatal file output stream.");
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    closeable = clsFileOutputStream;
                    CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                    CommonUtils.m167a(closeable, "Failed to close non-fatal file output stream.");
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                closeable = null;
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
                ExceptionUtils.writeStackTraceIfNotNull(e, closeable);
                CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                CommonUtils.m167a(closeable, "Failed to close non-fatal file output stream.");
                trimSessionEventFiles(currentSessionId, MAX_LOCAL_LOGGED_EXCEPTIONS);
                return;
            } catch (Throwable th4) {
                e = th4;
                closeable = null;
                CommonUtils.m168a(flushable, "Failed to flush to non-fatal file.");
                CommonUtils.m167a(closeable, "Failed to close non-fatal file output stream.");
                throw e;
            }
            try {
                trimSessionEventFiles(currentSessionId, MAX_LOCAL_LOGGED_EXCEPTIONS);
                return;
            } catch (Throwable e4) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "An error occurred when trimming non-fatal files.", e4);
                return;
            }
        }
        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Tried to write a non-fatal exception while no session was open.", null);
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        boolean z;
        Flushable newInstance;
        Throwable e;
        Closeable closeable;
        OutputStream outputStream = null;
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Collecting session parts for ID " + str);
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + SESSION_FATAL_TAG));
        boolean z2 = listFilesMatching != null && listFilesMatching.length > 0;
        Fabric.m512h().m474a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z2)}));
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
        if (listFilesMatching2 == null || listFilesMatching2.length <= 0) {
            z = false;
        } else {
            z = true;
        }
        Fabric.m512h().m474a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z)}));
        if (z2 || z) {
            Closeable clsFileOutputStream;
            try {
                clsFileOutputStream = new ClsFileOutputStream(this.filesDir, str);
                try {
                    newInstance = CodedOutputStream.newInstance((OutputStream) clsFileOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    newInstance = null;
                    closeable = clsFileOutputStream;
                    try {
                        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                        ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
                        CommonUtils.m168a(newInstance, "Error flushing session file stream");
                        closeWithoutRenamingOrLog(outputStream);
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
                        deleteSessionPartFilesFor(str);
                    } catch (Throwable th) {
                        e = th;
                        Object obj = outputStream;
                        CommonUtils.m168a(newInstance, "Error flushing session file stream");
                        CommonUtils.m167a(clsFileOutputStream, "Failed to close CLS file");
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    newInstance = null;
                    CommonUtils.m168a(newInstance, "Error flushing session file stream");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close CLS file");
                    throw e;
                }
                try {
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, "Collecting SessionStart data for session ID " + str);
                    writeToCosFromFile(newInstance, file);
                    newInstance.writeUInt64(MAX_COMPLETE_SESSIONS_COUNT, new Date().getTime() / 1000);
                    newInstance.writeBool(5, z2);
                    writeInitialPartsTo(newInstance, str);
                    if (z) {
                        File[] listFilesMatching3;
                        if (listFilesMatching2.length > i) {
                            Logger h = Fabric.m512h();
                            String str2 = CrashlyticsCore.TAG;
                            Object[] objArr = new Object[ANALYZER_VERSION];
                            objArr[0] = Integer.valueOf(i);
                            h.m474a(str2, String.format(Locale.US, "Trimming down to %d logged exceptions.", objArr));
                            trimSessionEventFiles(str, i);
                            listFilesMatching3 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
                        } else {
                            listFilesMatching3 = listFilesMatching2;
                        }
                        writeNonFatalEventsTo(newInstance, listFilesMatching3, str);
                    }
                    if (z2) {
                        writeToCosFromFile(newInstance, listFilesMatching[0]);
                    }
                    newInstance.writeUInt32(11, ANALYZER_VERSION);
                    newInstance.writeEnum(12, 3);
                    CommonUtils.m168a(newInstance, "Error flushing session file stream");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close CLS file");
                } catch (Exception e3) {
                    e = e3;
                    closeable = clsFileOutputStream;
                    Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                    ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
                    CommonUtils.m168a(newInstance, "Error flushing session file stream");
                    closeWithoutRenamingOrLog(outputStream);
                    Fabric.m512h().m474a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
                    deleteSessionPartFilesFor(str);
                } catch (Throwable th3) {
                    e = th3;
                    CommonUtils.m168a(newInstance, "Error flushing session file stream");
                    CommonUtils.m167a(clsFileOutputStream, "Failed to close CLS file");
                    throw e;
                }
            } catch (Exception e4) {
                e = e4;
                newInstance = null;
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                ExceptionUtils.writeStackTraceIfNotNull(e, outputStream);
                CommonUtils.m168a(newInstance, "Error flushing session file stream");
                closeWithoutRenamingOrLog(outputStream);
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
                deleteSessionPartFilesFor(str);
            } catch (Throwable th4) {
                e = th4;
                newInstance = null;
                clsFileOutputStream = null;
                CommonUtils.m168a(newInstance, "Error flushing session file stream");
                CommonUtils.m167a(clsFileOutputStream, "Failed to close CLS file");
                throw e;
            }
        }
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "No events present for session ID " + str);
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
        deleteSessionPartFilesFor(str);
    }

    private void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.f138a);
        int length = fileArr.length;
        for (int i = 0; i < length; i += ANALYZER_VERSION) {
            File file = fileArr[i];
            try {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, file.getName()}));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) throws IOException {
        String[] strArr = new String[MAX_COMPLETE_SESSIONS_COUNT];
        strArr[0] = SESSION_USER_TAG;
        strArr[ANALYZER_VERSION] = SESSION_APP_TAG;
        strArr[2] = SESSION_OS_TAG;
        strArr[3] = SESSION_DEVICE_TAG;
        int length = strArr.length;
        for (int i = 0; i < length; i += ANALYZER_VERSION) {
            String str2 = strArr[i];
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + str2));
            if (listFilesMatching.length == 0) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Collecting " + str2 + " data for session ID " + str);
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    private void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) throws IOException {
        Throwable th;
        if (file.exists()) {
            byte[] bArr = new byte[((int) file.length())];
            Closeable fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                int i = 0;
                while (i < bArr.length) {
                    try {
                        int read = fileInputStream.read(bArr, i, bArr.length - i);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                CommonUtils.m167a(fileInputStream, "Failed to close file input stream.");
                codedOutputStream.writeRawBytes(bArr);
                return;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                CommonUtils.m167a(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        }
        Fabric.m512h().m482e(CrashlyticsCore.TAG, "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private void sendSessionReports() {
        File[] listCompleteSessionFiles = listCompleteSessionFiles();
        int length = listCompleteSessionFiles.length;
        for (int i = 0; i < length; i += ANALYZER_VERSION) {
            this.executorServiceWrapper.executeAsync(new AnonymousClass16(listCompleteSessionFiles[i]));
        }
    }
}
