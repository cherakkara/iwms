package com.apsalar.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApThread */
class ApsalarThread extends Thread {
    static final String TAG = "Apsalar SDK/ApsalarThread";
    private static String apikey;
    private static String secret;
    private int currentBackoff;
    final Condition incomingEvent;
    final ReentrantLock incomingEventLock;

    protected static ApsalarThread getThreadInstance(String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        apikey = str;
        secret = str2;
        if (ApSingleton.apsalar_thread == null) {
            instance.getClass();
            ApSingleton.apsalar_thread = new ApsalarThread();
            ApSingleton.apsalar_thread.setDaemon(true);
            ApSingleton.apsalar_thread.setName("ApsalarHTTPThread");
        }
        if (!ApSingleton.apsalar_thread.isAlive()) {
            instance.getClass();
            ApSingleton.apsalar_thread.start();
        }
        return ApSingleton.apsalar_thread;
    }

    private ApsalarThread() {
        this.incomingEventLock = new ReentrantLock();
        this.incomingEvent = this.incomingEventLock.newCondition();
    }

    private boolean isActiveThread() {
        boolean z;
        ApSingleton.getInstance(ApSingleton.getContext());
        if (ApSingleton.apsalar_thread == null || ApSingleton.apsalar_thread != this) {
            z = false;
        } else {
            z = true;
        }
        boolean z2;
        if (ApSingleton.apsalar_thread == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    private boolean syncAIFA() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        String str = instance.AIFA;
        instance.AIFA = AIFA_Helper.getAIFA();
        return instance.AIFA != str;
    }

    private int networkBackoff(int i) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        AlarmClock.start(i);
        int i2 = instance.RETRY_INTERVAL_BACKOFF * i;
        if (i2 > instance.RETRY_INTERVAL_MAX) {
            return instance.RETRY_INTERVAL_MAX;
        }
        return i2;
    }

    private void processEventsForever() {
        Object instance = ApSingleton.getInstance(ApSingleton.getContext());
        this.currentBackoff = instance.BACKOFF_INTERVAL;
        while (isActiveThread()) {
            instance = ApSingleton.getInstance(ApSingleton.getContext());
            switch (instance.state) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    instance.state = nominal();
                    if (instance.state != 2) {
                        break;
                    }
                    this.currentBackoff = instance.BACKOFF_INTERVAL;
                    break;
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    instance.state = backoff();
                    break;
                default:
                    instance.getClass();
                    instance.state = 2;
                    break;
            }
        }
        instance.getClass();
    }

    public void run() {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        if (!isActiveThread()) {
            instance.getClass();
        } else if (ApsalarSQLiteHelper.createApsalarTables()) {
            if (Apsalar.setInfo(context, apikey, secret, false)) {
                Apsalar.loadConfig(context);
            }
            Apsalar.loadSharedPrefs(context);
            instance.getClass();
            if (!(ApSingleton.eventConsumerThread == null || ApSingleton.eventConsumerThread.isAlive())) {
                instance.getClass();
                ApSingleton.eventConsumerThread.start();
            }
            if (instance.ANDI == null) {
                instance.getClass();
                instance.ANDI = Secure.getString(instance.ctx.getContentResolver(), "android_id");
            }
            if (syncAIFA()) {
                Apsalar.updateConfigTable(context, "aifa", instance.AIFA);
            }
            ApsalarEvent apsalarCanonical = new ApsalarCanonical(context, instance.info);
            int i = instance.BACKOFF_INTERVAL;
            while (!handleCanonical(apsalarCanonical)) {
                i = networkBackoff(i);
            }
            if (isActiveThread()) {
                apsalarCanonical = new ApsalarResolve(context, instance.info, instance.playStoreAvailable);
                i = instance.BACKOFF_INTERVAL;
                while (!handleResolve(apsalarCanonical)) {
                    i = networkBackoff(i);
                }
                processEventsForever();
            }
        } else {
            instance.getClass();
        }
    }

    private ApsalarAPI createApsalarEvent(Cursor cursor) {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        int i = cursor.getInt(0);
        String string = cursor.getString(1);
        String string2 = cursor.getString(2);
        String string3 = cursor.getString(3);
        long j = cursor.getLong(4);
        try {
            ApsalarSessionInfo apsalarSessionInfo = new ApsalarSessionInfo(JSONObjectInstrumentation.init(string), instance.info.apiKey, instance.info.secret);
            instance.getClass();
            switch (i) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return new ApsalarSession(context, apsalarSessionInfo, j);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    return new ApsalarHeartbeat(context, apsalarSessionInfo, j);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    return new ApsalarEvent(context, apsalarSessionInfo, string2, j, string3);
                default:
                    instance.getClass();
                    return null;
            }
        } catch (JSONException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return null;
        }
    }

    private void waitForIncomingEvent() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        this.incomingEventLock.lock();
        try {
            this.incomingEvent.await();
            instance.getClass();
        } catch (InterruptedException e) {
        } finally {
            this.incomingEventLock.unlock();
        }
    }

    private Cursor getOldestEventsFromDatabase() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.database = ApsalarSQLiteHelper.getSQLWritableDatabase(instance.ctx);
        if (instance.database == null) {
            return null;
        }
        SQLiteDatabase sQLiteDatabase = instance.database;
        instance.getClass();
        String str = "SELECT type, session_json, name, args, unix_t, id FROM events ORDER BY id ASC LIMIT 1";
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
    }

    private void deleteEventIds(Vector<String> vector) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        for (int i = 0; i < vector.size(); i++) {
            String str = (String) vector.elementAt(i);
            SQLiteDatabase sQLiteDatabase = instance.database;
            String str2 = "events";
            String str3 = "id=" + str;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str2, str3, null);
            } else {
                sQLiteDatabase.delete(str2, str3, null);
            }
            instance.getClass();
        }
    }

    private boolean handleCanonical(ApsalarEvent apsalarEvent) {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        switch (apsalarEvent.REST()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return false;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                Apsalar.saveSharedPrefs(context);
                Apsalar.setInfo(context, instance.info.apiKey, instance.info.secret, true);
                return true;
            default:
                return true;
        }
    }

    private boolean handleResolve(ApsalarEvent apsalarEvent) {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        switch (apsalarEvent.REST()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return false;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                instance.getClass();
                Apsalar.saveSharedPrefs(context);
                Apsalar.setInfo(context, instance.info.apiKey, instance.info.secret, true);
                return true;
            default:
                return true;
        }
    }

    private boolean handleSession(ApsalarEvent apsalarEvent) {
        Context context = ApSingleton.getContext();
        ApSingleton.getInstance(context);
        switch (apsalarEvent.REST()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return false;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                JSONObject jSONObject = apsalarEvent.returnDataJSON;
                if (jSONObject == null || !jSONObject.has("first_time")) {
                    return true;
                }
                Apsalar.sendFBInstall(context);
                Apsalar.sendReferrerInstall(context);
                return true;
            default:
                return true;
        }
    }

    private boolean handleEvent(ApsalarEvent apsalarEvent) {
        ApSingleton.getInstance(ApSingleton.getContext());
        switch (apsalarEvent.REST()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return false;
            default:
                return true;
        }
    }

    private int nominal() {
        boolean z = false;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        Cursor oldestEventsFromDatabase = getOldestEventsFromDatabase();
        if (oldestEventsFromDatabase == null) {
            instance.getClass();
            SystemClock.sleep(5000);
            return 2;
        } else if (oldestEventsFromDatabase.getCount() == 0) {
            waitForIncomingEvent();
            oldestEventsFromDatabase.close();
            return 2;
        } else {
            Vector vector = new Vector(oldestEventsFromDatabase.getCount());
            try {
                oldestEventsFromDatabase.moveToFirst();
                while (true) {
                    boolean handleSession;
                    int i;
                    int i2 = oldestEventsFromDatabase.getInt(0);
                    int i3 = oldestEventsFromDatabase.getInt(5);
                    ApsalarEvent apsalarEvent = (ApsalarEvent) createApsalarEvent(oldestEventsFromDatabase);
                    switch (i2) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            handleSession = handleSession(apsalarEvent);
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            handleSession = handleEvent(apsalarEvent);
                            break;
                        default:
                            instance.getClass();
                            handleSession = z;
                            break;
                    }
                    if (handleSession) {
                        vector.add(String.valueOf(i3));
                        if (oldestEventsFromDatabase.moveToNext()) {
                            z = handleSession;
                        }
                    }
                    oldestEventsFromDatabase.close();
                    deleteEventIds(vector);
                    if (handleSession) {
                        i = 2;
                    } else {
                        i = 4;
                    }
                    return i;
                }
            } catch (Throwable th) {
                oldestEventsFromDatabase.close();
            }
        }
    }

    private int backoff() {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        this.currentBackoff = networkBackoff(this.currentBackoff);
        return 2;
    }
}
