package com.localytics.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

abstract class BaseHandler extends Handler {
    private static final String CANCEL_UPLOAD = "cancel";
    private static final int MESSAGE_GET_VALUE = 2;
    static final int MESSAGE_INIT = 1;
    private static final int MESSAGE_UPLOAD = 3;
    static final int MESSAGE_UPLOAD_CALLBACK = 4;
    private static final String UPLOAD_WAKE_LOCK = "UPLOAD_WAKE_LOCK";
    protected boolean doesRetry;
    ListenersSet listeners;
    LocalyticsDao mLocalyticsDao;
    BaseProvider mProvider;
    private WakeLock mWakeLock;
    private int maxRowToUpload;
    private int numberOfRetries;
    private Message pendingUploadMessage;
    private boolean queuePriorityMessages;
    String siloName;
    private BaseUploadThread uploadThread;

    abstract class ListenersSet extends HashSet<BaseListener> {
        ListenersSet() {
        }

        synchronized void callListeners(String str, Class<?>[] clsArr, Object[] objArr) {
            synchronized (BaseHandler.this) {
                BaseHandler.this.queuePriorityMessages = true;
                Iterator it = iterator();
                while (it.hasNext()) {
                    try {
                        ReflectionUtils.tryInvokeInstance(it.next(), str, clsArr, objArr);
                    } catch (Exception e) {
                    }
                }
                BaseHandler.this.queuePriorityMessages = false;
            }
        }
    }

    public interface BaseListener {
    }

    /* renamed from: com.localytics.android.BaseHandler.1 */
    class C06861 implements Runnable {
        final /* synthetic */ FutureTask val$fTask;

        C06861(FutureTask futureTask) {
            this.val$fTask = futureTask;
        }

        public void run() {
            this.val$fTask.run();
        }
    }

    /* renamed from: com.localytics.android.BaseHandler.2 */
    class C06872 implements Runnable {
        final /* synthetic */ Boolean val$adjustMaxRowToUpload;
        final /* synthetic */ String val$customerId;

        C06872(Boolean bool, String str) {
            this.val$adjustMaxRowToUpload = bool;
            this.val$customerId = str;
        }

        public void run() {
            BaseHandler.this._upload(this.val$adjustMaxRowToUpload.booleanValue(), this.val$customerId);
        }
    }

    /* renamed from: com.localytics.android.BaseHandler.3 */
    class C06883 implements Runnable {
        final /* synthetic */ String val$responseString;
        final /* synthetic */ int val$rowsToDelete;

        C06883(int i, String str) {
            this.val$rowsToDelete = i;
            this.val$responseString = str;
        }

        public void run() {
            BaseHandler.this._uploadCallback(this.val$rowsToDelete, this.val$responseString);
        }
    }

    protected abstract void _deleteUploadedData(int i);

    protected abstract TreeMap<Integer, Object> _getDataToUpload();

    protected abstract int _getMaxRowToUpload();

    abstract void _init();

    protected abstract void _onUploadCompleted(String str);

    protected abstract BaseUploadThread getUploadThread(TreeMap<Integer, Object> treeMap, String str);

    BaseHandler(LocalyticsDao localyticsDao, Looper looper) {
        super(looper);
        this.maxRowToUpload = 0;
        this.numberOfRetries = 0;
        this.queuePriorityMessages = false;
        this.uploadThread = null;
        this.pendingUploadMessage = null;
        this.doesRetry = true;
        this.mLocalyticsDao = localyticsDao;
    }

    public void handleMessage(Message message) {
        Object[] objArr;
        try {
            super.handleMessage(message);
            Object[] objArr2 = new Object[MESSAGE_GET_VALUE];
            objArr2[0] = this.siloName;
            objArr2[MESSAGE_INIT] = message;
            Log.m12799v(String.format("%s handler received %s", objArr2));
            Object[] objArr3;
            switch (message.what) {
                case MESSAGE_INIT /*1*/:
                    _init();
                    return;
                case MESSAGE_GET_VALUE /*2*/:
                    this.mProvider.runBatchTransaction(new C06861((FutureTask) message.obj));
                    return;
                case MESSAGE_UPLOAD /*3*/:
                    objArr2 = new Object[MESSAGE_INIT];
                    objArr2[0] = this.siloName;
                    Log.m12793d(String.format("%s handler received MESSAGE_UPLOAD", objArr2));
                    objArr3 = (Object[]) message.obj;
                    Boolean bool = (Boolean) objArr3[0];
                    String str = (String) objArr3[MESSAGE_INIT];
                    if (!message.getData().getBoolean(CANCEL_UPLOAD)) {
                        this.mProvider.runBatchTransaction(new C06872(bool, str));
                        return;
                    }
                    return;
                case MESSAGE_UPLOAD_CALLBACK /*4*/:
                    objArr2 = new Object[MESSAGE_INIT];
                    objArr2[0] = this.siloName;
                    Log.m12793d(String.format("%s handler received MESSAGE_UPLOAD_CALLBACK", objArr2));
                    objArr3 = (Object[]) message.obj;
                    this.mProvider.runBatchTransaction(new C06883(((Integer) objArr3[0]).intValue(), (String) objArr3[MESSAGE_INIT]));
                    return;
                default:
                    handleMessageExtended(message);
                    return;
            }
        } catch (Throwable e) {
            objArr = new Object[MESSAGE_GET_VALUE];
            objArr[0] = this.siloName;
            objArr[MESSAGE_INIT] = String.valueOf(message.what);
            Log.m12796e(String.format("%s handler can't handle message %s", objArr), e);
        }
        objArr = new Object[MESSAGE_GET_VALUE];
        objArr[0] = this.siloName;
        objArr[MESSAGE_INIT] = String.valueOf(message.what);
        Log.m12796e(String.format("%s handler can't handle message %s", objArr), e);
    }

    void handleMessageExtended(Message message) throws Exception {
        throw new Exception("Fell through switch statement");
    }

    boolean getBool(Callable<Boolean> callable) {
        return ((Boolean) getType(callable, Boolean.valueOf(false))).booleanValue();
    }

    private <T> FutureTask<T> getFuture(Callable<T> callable) {
        FutureTask<T> futureTask = new FutureTask(callable);
        queueMessage(obtainMessage(MESSAGE_GET_VALUE, futureTask));
        return futureTask;
    }

    <T> T getFutureTaskValue(FutureTask<T> futureTask, T t) {
        try {
            t = futureTask.get();
        } catch (Exception e) {
        } catch (Throwable th) {
        }
        return t;
    }

    private <T> T getType(Callable<T> callable, T t) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return getFutureTaskValue(getFuture(callable), t);
        }
        throw new RuntimeException("Cannot be called on the main thread.");
    }

    boolean queueMessage(Message message) {
        if (!this.queuePriorityMessages || getLooper().getThread() != Thread.currentThread()) {
            return sendMessage(message);
        }
        handleMessage(message);
        return true;
    }

    String getString(Callable<String> callable) {
        return (String) getType(callable, null);
    }

    Map getMap(Callable<Map> callable) {
        return (Map) getType(callable, null);
    }

    List getList(Callable<List> callable) {
        return (List) getType(callable, null);
    }

    void addListener(BaseListener baseListener) {
        synchronized (this.listeners) {
            this.listeners.add(baseListener);
        }
    }

    void removeListener(BaseListener baseListener) {
        synchronized (this.listeners) {
            this.listeners.remove(baseListener);
        }
    }

    final boolean queueMessageDelayed(Message message, long j) {
        if (j == 0) {
            return queueMessage(message);
        }
        return sendMessageDelayed(message, j);
    }

    void upload(String str) {
        upload(true, 0, str);
    }

    void upload(boolean z, long j, String str) {
        Object obj = new Object[MESSAGE_GET_VALUE];
        obj[0] = Boolean.valueOf(z);
        obj[MESSAGE_INIT] = str;
        Message obtainMessage = obtainMessage(MESSAGE_UPLOAD, obj);
        if (j == 0) {
            cancelPendingUpload();
            queueMessage(obtainMessage);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(CANCEL_UPLOAD, false);
        this.pendingUploadMessage = obtainMessage;
        this.pendingUploadMessage.setData(bundle);
        queueMessageDelayed(this.pendingUploadMessage, j);
    }

    void _upload(boolean z, String str) {
        if (!(this.maxRowToUpload == 0 || this.uploadThread == null || this.uploadThread.isAlive())) {
            this.maxRowToUpload = 0;
            this.uploadThread = null;
        }
        int _getMaxRowToUpload = _getMaxRowToUpload();
        if (!z || this.maxRowToUpload == 0) {
            try {
                TreeMap _getDataToUpload = _getDataToUpload();
                if (_getDataToUpload.size() == 0) {
                    this.maxRowToUpload = 0;
                    return;
                }
                if (z) {
                    this.maxRowToUpload = _getMaxRowToUpload;
                }
                enterWakeLock();
                BaseUploadThread uploadThread = getUploadThread(_getDataToUpload, str);
                this.uploadThread = uploadThread;
                uploadThread.start();
                return;
            } catch (Throwable e) {
                Log.m12802w("Error occurred during upload", e);
                this.maxRowToUpload = 0;
                return;
            }
        }
        this.maxRowToUpload = _getMaxRowToUpload;
        Object[] objArr = new Object[MESSAGE_INIT];
        objArr[0] = this.siloName.toLowerCase();
        Log.m12793d(String.format("Already uploading %s", objArr));
    }

    private void _uploadCallback(int i, String str) {
        String str2 = this.uploadThread.customerID;
        this.uploadThread = null;
        if (i > 0) {
            _deleteUploadedData(i);
            this.numberOfRetries = 0;
        } else {
            this.numberOfRetries += MESSAGE_INIT;
        }
        if (!this.doesRetry || i == this.maxRowToUpload || this.numberOfRetries > MESSAGE_UPLOAD) {
            if (i == this.maxRowToUpload) {
                _onUploadCompleted(str);
            }
            cancelPendingUpload();
            this.numberOfRetries = 0;
            this.maxRowToUpload = 0;
            exitWakeLock();
            return;
        }
        upload(false, 10000, str2);
    }

    private void cancelPendingUpload() {
        if (this.pendingUploadMessage != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(CANCEL_UPLOAD, true);
            this.pendingUploadMessage.setData(bundle);
            this.pendingUploadMessage = null;
            this.numberOfRetries = 0;
            this.maxRowToUpload = 0;
        }
    }

    private void enterWakeLock() {
        Context appContext = this.mLocalyticsDao.getAppContext();
        if (appContext.getPackageManager().checkPermission("android.permission.WAKE_LOCK", appContext.getPackageName()) != 0) {
            Log.m12799v("android.permission.WAKE_LOCK is missing from the Manifest file.");
        } else if (this.mWakeLock == null) {
            this.mWakeLock = ((PowerManager) appContext.getSystemService("power")).newWakeLock(MESSAGE_INIT, UPLOAD_WAKE_LOCK);
            if (this.mWakeLock.isHeld()) {
                Log.m12801w("Wake lock will be acquired but is held when shouldn't be.");
            }
            this.mWakeLock.acquire();
            if (this.mWakeLock.isHeld()) {
                Log.m12799v("Wake lock acquired.");
            } else {
                Log.m12801w("Localytics library failed to get wake lock");
            }
        }
    }

    private void exitWakeLock() {
        Context appContext = this.mLocalyticsDao.getAppContext();
        if (appContext.getPackageManager().checkPermission("android.permission.WAKE_LOCK", appContext.getPackageName()) != 0) {
            Log.m12799v("android.permission.WAKE_LOCK is missing from the Manifest file.");
        } else if (this.mWakeLock != null) {
            if (!this.mWakeLock.isHeld()) {
                Log.m12801w("Wake lock will be released but not held when should be.");
            }
            this.mWakeLock.release();
            if (this.mWakeLock.isHeld()) {
                Log.m12801w("Wake lock was not released when it should have been.");
            } else {
                Log.m12799v("Wake lock released.");
            }
            this.mWakeLock = null;
        }
    }
}
