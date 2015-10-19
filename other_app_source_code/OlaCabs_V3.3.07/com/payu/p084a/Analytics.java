package com.payu.p084a;

import android.app.Activity;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.payu.a.a */
public class Analytics {
    private static final String FILE_NAME = "local_cache";
    private static Analytics INSTANCE = null;
    private static final String PRODUCTION_URL = "https://info.payu.in/merchant/postservice.php";
    private static final String TEST_URL = "https://mobiletest.payu.in/merchant/postservice.php";
    private static final long TIMER_DELAY = 5000;
    private String ANALYTICS_URL;
    private final Activity mActivity;
    private ArrayList<String> mBuffer;
    private boolean mIsLocked;
    private Timer mTimer;

    /* renamed from: com.payu.a.a.1 */
    class Analytics implements UncaughtExceptionHandler {
        final /* synthetic */ UncaughtExceptionHandler val$defaultUEH;

        Analytics(UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.val$defaultUEH = uncaughtExceptionHandler;
        }

        public void uncaughtException(Thread thread, Throwable th) {
            do {
            } while (Analytics.this.mIsLocked);
            Analytics.this.setLock();
            try {
                FileOutputStream openFileOutput = Analytics.this.mActivity.openFileOutput(Analytics.FILE_NAME, 0);
                int size = Analytics.this.mBuffer.size();
                for (int i = 0; i < size; i++) {
                    openFileOutput.write((((String) Analytics.this.mBuffer.get(i)) + "\r\n").getBytes());
                }
                openFileOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Analytics.this.releaseLock();
            this.val$defaultUEH.uncaughtException(thread, th);
        }
    }

    /* renamed from: com.payu.a.a.2 */
    class Analytics extends TimerTask {
        Analytics() {
        }

        public void run() {
            String str;
            String str2;
            HttpClient defaultHttpClient;
            FileOutputStream openFileOutput;
            do {
            } while (Analytics.this.mIsLocked);
            Analytics.this.setLock();
            String str3 = Trace.NULL;
            int read;
            int size;
            int i;
            HttpUriRequest httpPost;
            List arrayList;
            try {
                if (!new File(Analytics.this.mActivity.getFilesDir(), Analytics.FILE_NAME).exists()) {
                    Analytics.this.mActivity.openFileOutput(Analytics.FILE_NAME, 0);
                }
                FileInputStream openFileInput = Analytics.this.mActivity.openFileInput(Analytics.FILE_NAME);
                while (true) {
                    read = openFileInput.read();
                    if (read == -1) {
                        break;
                    }
                    str3 = str3 + Character.toString((char) read);
                }
                openFileInput.close();
                size = Analytics.this.mBuffer.size();
                str = str3;
                while (size > 0) {
                    i = size - 1;
                    str2 = str + ((String) Analytics.this.mBuffer.get(i)) + "\r\n";
                    Analytics.this.mBuffer.remove(i);
                    str = str2;
                    size = i;
                }
                str3 = str.trim();
                if (str3.length() > 0) {
                    defaultHttpClient = new DefaultHttpClient();
                    httpPost = new HttpPost(Analytics.this.ANALYTICS_URL);
                    arrayList = new ArrayList();
                    arrayList.add(new BasicNameValuePair("command", "sdkWs"));
                    arrayList.add(new BasicNameValuePair("var1", str3));
                    try {
                        httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
                        if ((!(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost)).getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                            Analytics.this.mActivity.deleteFile(Analytics.FILE_NAME);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            openFileOutput = Analytics.this.mActivity.openFileOutput(Analytics.FILE_NAME, 0);
                            openFileOutput.write(str3.getBytes());
                            openFileOutput.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } catch (IOException e22) {
                e22.printStackTrace();
                size = Analytics.this.mBuffer.size();
                str = str3;
                while (size > 0) {
                    i = size - 1;
                    str2 = str + ((String) Analytics.this.mBuffer.get(i)) + "\r\n";
                    Analytics.this.mBuffer.remove(i);
                    str = str2;
                    size = i;
                }
                str3 = str.trim();
                if (str3.length() > 0) {
                    defaultHttpClient = new DefaultHttpClient();
                    httpPost = new HttpPost(Analytics.this.ANALYTICS_URL);
                    arrayList = new ArrayList();
                    arrayList.add(new BasicNameValuePair("command", "sdkWs"));
                    arrayList.add(new BasicNameValuePair("var1", str3));
                    try {
                        HttpResponse execute;
                        httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
                        if (defaultHttpClient instanceof HttpClient) {
                            execute = HttpInstrumentation.execute(defaultHttpClient, httpPost);
                        } else {
                            execute = defaultHttpClient.execute(httpPost);
                        }
                        if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                            Analytics.this.mActivity.deleteFile(Analytics.FILE_NAME);
                        }
                    } catch (IOException e222) {
                        e222.printStackTrace();
                        try {
                            openFileOutput = Analytics.this.mActivity.openFileOutput(Analytics.FILE_NAME, 0);
                            openFileOutput.write(str3.getBytes());
                            openFileOutput.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                str2 = str3;
                Throwable th3 = th2;
                String str4 = str2;
                size = Analytics.this.mBuffer.size();
                while (size > 0) {
                    read = size - 1;
                    str2 = str4 + ((String) Analytics.this.mBuffer.get(read)) + "\r\n";
                    Analytics.this.mBuffer.remove(read);
                    str4 = str2;
                    size = read;
                }
                str = str4.trim();
                if (str.length() > 0) {
                    defaultHttpClient = new DefaultHttpClient();
                    HttpUriRequest httpPost2 = new HttpPost(Analytics.this.ANALYTICS_URL);
                    List arrayList2 = new ArrayList();
                    arrayList2.add(new BasicNameValuePair("command", "sdkWs"));
                    arrayList2.add(new BasicNameValuePair("var1", str));
                    try {
                        httpPost2.setEntity(new UrlEncodedFormEntity(arrayList2));
                        if ((!(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost2) : HttpInstrumentation.execute(defaultHttpClient, httpPost2)).getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                            Analytics.this.mActivity.deleteFile(Analytics.FILE_NAME);
                        }
                    } catch (IOException e22222) {
                        e22222.printStackTrace();
                        try {
                            openFileOutput = Analytics.this.mActivity.openFileOutput(Analytics.FILE_NAME, 0);
                            openFileOutput.write(str.getBytes());
                            openFileOutput.close();
                        } catch (IOException e222222) {
                            e222222.printStackTrace();
                        }
                    }
                }
            }
            if (Analytics.this.mBuffer.size() > 0) {
                Analytics.this.resetTimer();
            }
            Analytics.this.releaseLock();
        }
    }

    public Analytics(Activity activity) {
        this.ANALYTICS_URL = PRODUCTION_URL;
        this.mIsLocked = false;
        this.mBuffer = new ArrayList();
        this.mActivity = activity;
        Thread.setDefaultUncaughtExceptionHandler(new Analytics(Thread.getDefaultUncaughtExceptionHandler()));
    }

    public static synchronized Analytics getInstance(Activity activity) {
        Analytics analytics;
        synchronized (Analytics.class) {
            if (INSTANCE == null) {
                INSTANCE = new Analytics(activity);
            }
            analytics = INSTANCE;
        }
        return analytics;
    }

    public void log(String str) {
        resetTimer();
        if (this.mIsLocked) {
            this.mBuffer.add(str);
            return;
        }
        setLock();
        try {
            String str2 = Trace.NULL;
            if (!new File(this.mActivity.getFilesDir(), FILE_NAME).exists()) {
                this.mActivity.openFileOutput(FILE_NAME, 0);
            }
            FileInputStream openFileInput = this.mActivity.openFileInput(FILE_NAME);
            while (true) {
                int read = openFileInput.read();
                if (read == -1) {
                    break;
                }
                str2 = str2 + Character.toString((char) read);
            }
            openFileInput.close();
            FileOutputStream openFileOutput = this.mActivity.openFileOutput(FILE_NAME, 0);
            openFileOutput.write((str2 + str + "\r\n").getBytes());
            openFileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
            this.mBuffer.add(str);
        }
        releaseLock();
    }

    private void resetTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new Analytics(), TIMER_DELAY);
    }

    synchronized void setLock() {
        this.mIsLocked = true;
    }

    synchronized void releaseLock() {
        this.mIsLocked = false;
    }
}
