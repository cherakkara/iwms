package com.localytics.android;

import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeMap;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

abstract class BaseUploadThread extends Thread {
    String customerID;
    final TreeMap<Integer, Object> mData;
    LocalyticsDao mLocalyticsDao;
    private final BaseHandler mSessionHandler;
    private String uploadResponseString;

    protected enum UploadType {
        ANALYTICS,
        PROFILES,
        MARKETING
    }

    abstract int uploadData();

    BaseUploadThread(BaseHandler baseHandler, TreeMap<Integer, Object> treeMap, String str, LocalyticsDao localyticsDao) {
        this.uploadResponseString = null;
        this.mSessionHandler = baseHandler;
        this.mData = treeMap;
        this.customerID = str;
        this.mLocalyticsDao = localyticsDao;
    }

    String getApiKey() {
        String apiKey = this.mLocalyticsDao.getApiKey();
        Object localyticsRollupKeyOrNull = DatapointHelper.getLocalyticsRollupKeyOrNull(this.mLocalyticsDao.getAppContext());
        return (localyticsRollupKeyOrNull == null || TextUtils.isEmpty(localyticsRollupKeyOrNull)) ? apiKey : localyticsRollupKeyOrNull;
    }

    public void run() {
        try {
            int uploadData = uploadData();
            this.mSessionHandler.sendMessage(this.mSessionHandler.obtainMessage(4, new Object[]{Integer.valueOf(uploadData), this.uploadResponseString}));
        } catch (Throwable th) {
            this.mSessionHandler.sendMessage(this.mSessionHandler.obtainMessage(4, new Object[]{Integer.valueOf(0), this.uploadResponseString}));
        }
    }

    private static String formatUploadBody(String str) {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            return !(init instanceof JSONObject) ? init.toString(3) : JSONObjectInstrumentation.toString(init, 3);
        } catch (Exception e) {
            return str;
        }
    }

    boolean upload(UploadType uploadType, String str, String str2, int i) {
        return upload(uploadType, str, str2, i, false);
    }

    boolean upload(UploadType uploadType, String str, String str2, int i, boolean z) {
        GZIPOutputStream gZIPOutputStream;
        Throwable e;
        HttpURLConnection httpURLConnection;
        boolean upload;
        Throwable th;
        if (str == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("body cannot be null");
        } else {
            if (uploadType == UploadType.ANALYTICS) {
                Log.m12799v(String.format("Analytics upload body before compression is: \n%s", new Object[]{str2}));
            } else if (uploadType == UploadType.PROFILES) {
                Log.m12799v(String.format("Profile upload body is: \n%s", new Object[]{formatUploadBody(str2)}));
            }
            GZIPOutputStream gZIPOutputStream2 = null;
            try {
                byte[] toByteArray;
                byte[] bytes = str2.getBytes(HTTP.UTF_8);
                if (uploadType == UploadType.ANALYTICS) {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(bytes);
                        gZIPOutputStream.finish();
                        if (DatapointHelper.getApiLevel() < 19) {
                            gZIPOutputStream.flush();
                        }
                        toByteArray = byteArrayOutputStream.toByteArray();
                    } catch (UnsupportedEncodingException e2) {
                        e = e2;
                        try {
                            Log.m12802w("UnsupportedEncodingException", e);
                            if (gZIPOutputStream != null) {
                                return false;
                            }
                            try {
                                gZIPOutputStream.close();
                                return false;
                            } catch (Throwable e3) {
                                Log.m12802w("Caught exception", e3);
                                return false;
                            }
                        } catch (Throwable th2) {
                            e3 = th2;
                            gZIPOutputStream2 = gZIPOutputStream;
                            if (gZIPOutputStream2 != null) {
                                try {
                                    gZIPOutputStream2.close();
                                } catch (Throwable e32) {
                                    Log.m12802w("Caught exception", e32);
                                    return false;
                                }
                            }
                            throw e32;
                        }
                    } catch (IOException e4) {
                        e32 = e4;
                        gZIPOutputStream2 = gZIPOutputStream;
                        try {
                            Log.m12802w("IOException", e32);
                            if (gZIPOutputStream2 != null) {
                                return false;
                            }
                            try {
                                gZIPOutputStream2.close();
                                return false;
                            } catch (Throwable e322) {
                                Log.m12802w("Caught exception", e322);
                                return false;
                            }
                        } catch (Throwable th3) {
                            e322 = th3;
                            if (gZIPOutputStream2 != null) {
                                gZIPOutputStream2.close();
                            }
                            throw e322;
                        }
                    }
                }
                toByteArray = bytes;
                gZIPOutputStream = null;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable e3222) {
                        Log.m12802w("Caught exception", e3222);
                        return false;
                    }
                }
                HttpURLConnection httpURLConnection2 = null;
                try {
                    HttpURLConnection httpURLConnection3 = (HttpURLConnection) createURLConnection(new URL(str), this.mLocalyticsDao.getProxy());
                    OutputStream outputStream;
                    try {
                        httpURLConnection3.setConnectTimeout(TraceMachine.UNHEALTHY_TRACE_TIMEOUT);
                        httpURLConnection3.setReadTimeout(TraceMachine.UNHEALTHY_TRACE_TIMEOUT);
                        httpURLConnection3.setDoOutput(uploadType != UploadType.MARKETING);
                        if (uploadType == UploadType.ANALYTICS) {
                            httpURLConnection3.setRequestProperty(HTTP.CONTENT_TYPE, "application/x-gzip");
                            httpURLConnection3.setRequestProperty(HTTP.CONTENT_ENCODING, "gzip");
                            httpURLConnection3.setRequestProperty("X-DONT-SEND-AMP", "1");
                        } else {
                            httpURLConnection3.setRequestProperty(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
                        }
                        if (uploadType == UploadType.MARKETING && Constants.isTestModeEnabled()) {
                            httpURLConnection3.setRequestProperty("AMP-Test-Mode", "1");
                        }
                        if (z) {
                            httpURLConnection3.setRequestProperty("X-NO-DELAY", "1");
                        }
                        httpURLConnection3.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, Trace.NULL);
                        httpURLConnection3.setRequestProperty("x-upload-time", Long.toString(Math.round(((double) System.currentTimeMillis()) / 1000.0d)));
                        httpURLConnection3.setRequestProperty("x-install-id", this.mLocalyticsDao.getInstallationId());
                        httpURLConnection3.setRequestProperty("x-app-id", this.mLocalyticsDao.getApiKey());
                        httpURLConnection3.setRequestProperty("x-client-version", "androida_3.4.0");
                        httpURLConnection3.setRequestProperty("x-app-version", DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext()));
                        httpURLConnection3.setRequestProperty("x-customer-id", this.customerID);
                        if (uploadType != UploadType.MARKETING) {
                            httpURLConnection3.setFixedLengthStreamingMode(toByteArray.length);
                            outputStream = null;
                            outputStream = httpURLConnection3.getOutputStream();
                            outputStream.write(toByteArray);
                            if (outputStream != null) {
                                outputStream.flush();
                                outputStream.close();
                            }
                        }
                        int responseCode = httpURLConnection3.getResponseCode();
                        Log.m12799v(String.format("%s upload complete with status %d", new Object[]{this.mSessionHandler.siloName, Integer.valueOf(responseCode)}));
                        if (responseCode == 429) {
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            return false;
                        } else if (responseCode >= HttpStatus.SC_BAD_REQUEST && responseCode <= 499) {
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            return true;
                        } else if (responseCode < HttpStatus.SC_INTERNAL_SERVER_ERROR || responseCode > 599) {
                            retrieveHttpResponse(httpURLConnection3.getInputStream());
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            return true;
                        } else {
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            return false;
                        }
                    } catch (Throwable e5) {
                        httpURLConnection = httpURLConnection3;
                        e3222 = e5;
                        if (i != 2) {
                            try {
                                Log.m12802w("ClientProtocolException", e3222);
                                if (httpURLConnection != null) {
                                    return false;
                                }
                                httpURLConnection.disconnect();
                                return false;
                            } catch (Throwable th4) {
                                e3222 = th4;
                                httpURLConnection2 = httpURLConnection;
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw e3222;
                            }
                        }
                        upload = upload(uploadType, str, str2, i + 1, z);
                        if (httpURLConnection != null) {
                            return upload;
                        }
                        httpURLConnection.disconnect();
                        return upload;
                    } catch (Throwable e52) {
                        th = e52;
                        httpURLConnection2 = httpURLConnection3;
                        e3222 = th;
                        try {
                            Log.m12802w("ClientProtocolException", e3222);
                            if (httpURLConnection2 != null) {
                                return false;
                            }
                            httpURLConnection2.disconnect();
                            return false;
                        } catch (Throwable th5) {
                            e3222 = th5;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw e3222;
                        }
                    } catch (Throwable e522) {
                        th = e522;
                        httpURLConnection2 = httpURLConnection3;
                        e3222 = th;
                        Log.m12802w("ClientProtocolException", e3222);
                        if (httpURLConnection2 != null) {
                            return false;
                        }
                        httpURLConnection2.disconnect();
                        return false;
                    } catch (Throwable e5222) {
                        th = e5222;
                        httpURLConnection2 = httpURLConnection3;
                        e3222 = th;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw e3222;
                    }
                } catch (EOFException e6) {
                    e3222 = e6;
                    httpURLConnection = null;
                    if (i != 2) {
                        upload = upload(uploadType, str, str2, i + 1, z);
                        if (httpURLConnection != null) {
                            return upload;
                        }
                        httpURLConnection.disconnect();
                        return upload;
                    }
                    Log.m12802w("ClientProtocolException", e3222);
                    if (httpURLConnection != null) {
                        return false;
                    }
                    httpURLConnection.disconnect();
                    return false;
                } catch (MalformedURLException e7) {
                    e3222 = e7;
                    Log.m12802w("ClientProtocolException", e3222);
                    if (httpURLConnection2 != null) {
                        return false;
                    }
                    httpURLConnection2.disconnect();
                    return false;
                } catch (IOException e8) {
                    e3222 = e8;
                    Log.m12802w("ClientProtocolException", e3222);
                    if (httpURLConnection2 != null) {
                        return false;
                    }
                    httpURLConnection2.disconnect();
                    return false;
                }
            } catch (UnsupportedEncodingException e9) {
                e3222 = e9;
                gZIPOutputStream = null;
                Log.m12802w("UnsupportedEncodingException", e3222);
                if (gZIPOutputStream != null) {
                    return false;
                }
                gZIPOutputStream.close();
                return false;
            } catch (IOException e10) {
                e3222 = e10;
                Log.m12802w("IOException", e3222);
                if (gZIPOutputStream2 != null) {
                    return false;
                }
                gZIPOutputStream2.close();
                return false;
            }
        }
    }

    private void retrieveHttpResponse(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, HTTP.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            stringBuilder.append(readLine);
        }
        Object stringBuilder2 = stringBuilder.toString();
        if (!TextUtils.isEmpty(stringBuilder2)) {
            onUploadResponded(stringBuilder2);
        }
        bufferedReader.close();
    }

    void onUploadResponded(String str) {
        Log.m12801w(String.format("%s upload response: \n%s", new Object[]{this.mSessionHandler.siloName, str}));
        this.uploadResponseString = str;
    }

    static URLConnection createURLConnection(URL url, Proxy proxy) throws IOException {
        if (proxy == null) {
            return HttpInstrumentation.openConnection(url.openConnection());
        }
        return HttpInstrumentation.openConnectionWithProxy(url.openConnection(proxy));
    }
}
