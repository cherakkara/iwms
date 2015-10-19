package com.crashlytics.android.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import p004b.p005a.p006a.p007a.Fabric;
import p004b.p005a.p006a.p007a.p008a.p010b.ApiKey;
import p004b.p005a.p006a.p007a.p008a.p010b.BackgroundPriorityRunnable;

class ReportUploader {
    private static final String CLS_FILE_EXT = ".cls";
    static final Map<String, String> HEADER_INVALID_CLS_FILE;
    private static final short[] RETRY_INTERVALS;
    private static final FilenameFilter crashFileFilter;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock;
    private Thread uploadThread;

    /* renamed from: com.crashlytics.android.core.ReportUploader.1 */
    static class C01461 implements FilenameFilter {
        C01461() {
        }

        public boolean accept(File file, String str) {
            return str.endsWith(ReportUploader.CLS_FILE_EXT) && !str.contains("Session");
        }
    }

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;

        Worker(float f) {
            this.delay = f;
        }

        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry() {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Starting report processing in " + this.delay + " second(s)...");
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep((long) (this.delay * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            CrashlyticsCore instance = CrashlyticsCore.getInstance();
            CrashlyticsUncaughtExceptionHandler handler = instance.getHandler();
            List<Report> findReports = ReportUploader.this.findReports();
            if (!handler.isHandlingException()) {
                if (findReports.isEmpty() || instance.canSendWithUserApproval()) {
                    List list = findReports;
                    int i = 0;
                    while (!r0.isEmpty() && !CrashlyticsCore.getInstance().getHandler().isHandlingException()) {
                        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Attempting to send " + r0.size() + " report(s)");
                        for (Report forceUpload : r0) {
                            ReportUploader.this.forceUpload(forceUpload);
                        }
                        List findReports2 = ReportUploader.this.findReports();
                        if (findReports2.isEmpty()) {
                            list = findReports2;
                        } else {
                            int i2 = i + 1;
                            long j = (long) ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
                            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                                list = findReports2;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "User declined to send. Removing " + findReports.size() + " Report(s).");
                for (Report forceUpload2 : findReports) {
                    forceUpload2.remove();
                }
            }
        }
    }

    static {
        crashFileFilter = new C01461();
        HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
        RETRY_INTERVALS = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    }

    public ReportUploader(CreateReportSpiCall createReportSpiCall) {
        this.fileAccessLock = new Object();
        if (createReportSpiCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.createReportCall = createReportSpiCall;
    }

    public void uploadReports() {
        uploadReports(0.0f);
    }

    public synchronized void uploadReports(float f) {
        if (this.uploadThread == null) {
            this.uploadThread = new Thread(new Worker(f), "Crashlytics Report Uploader");
            this.uploadThread.start();
        }
    }

    boolean isUploading() {
        return this.uploadThread != null;
    }

    boolean forceUpload(Report report) {
        boolean z = false;
        synchronized (this.fileAccessLock) {
            try {
                boolean invoke = this.createReportCall.invoke(new CreateReportRequest(new ApiKey().m141a(CrashlyticsCore.getInstance().getContext()), report));
                Fabric.m512h().m478c(CrashlyticsCore.TAG, "Crashlytics report upload " + (invoke ? "complete: " : "FAILED: ") + report.getFileName());
                if (invoke) {
                    report.remove();
                    z = true;
                }
            } catch (Throwable e) {
                Fabric.m512h().m482e(CrashlyticsCore.TAG, "Error occurred sending report " + report, e);
            }
        }
        return z;
    }

    List<Report> findReports() {
        Fabric.m512h().m474a(CrashlyticsCore.TAG, "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            File[] listFiles = CrashlyticsCore.getInstance().getSdkDirectory().listFiles(crashFileFilter);
        }
        List<Report> linkedList = new LinkedList();
        for (File file : listFiles) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "Found crash report " + file.getPath());
            linkedList.add(new SessionReport(file));
        }
        if (linkedList.isEmpty()) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "No reports found.");
        }
        return linkedList;
    }
}
