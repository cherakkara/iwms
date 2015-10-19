package com.apsalar.sdk;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApThread */
class ApsalarEventConsumerThread extends Thread {
    static final String TAG = "Apsalar SDK/EventConsumerThread";
    protected ArrayBlockingQueue<RawEvent> eventQueue;

    private ApsalarEventConsumerThread() {
        this.eventQueue = new ArrayBlockingQueue(5);
    }

    private boolean isActiveThread() {
        boolean z;
        ApSingleton.getInstance(ApSingleton.getContext());
        if (ApSingleton.eventConsumerThread == null || ApSingleton.eventConsumerThread != this) {
            z = false;
        } else {
            z = true;
        }
        boolean z2;
        if (ApSingleton.eventConsumerThread == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    static ApsalarEventConsumerThread getThreadInstance() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (ApSingleton.eventConsumerThread == null) {
            instance.getClass();
            ApSingleton.eventConsumerThread = new ApsalarEventConsumerThread();
            ApSingleton.eventConsumerThread.setDaemon(true);
            ApSingleton.eventConsumerThread.setName("Event Consumer Thread");
        }
        return ApSingleton.eventConsumerThread;
    }

    public boolean put(RawEvent rawEvent) {
        return this.eventQueue.offer(rawEvent);
    }

    private void signalIncomingEvent() {
        ApSingleton.getInstance(ApSingleton.getContext());
        ApsalarThread apsalarThread = ApSingleton.apsalar_thread;
        apsalarThread.incomingEventLock.lock();
        apsalarThread.incomingEvent.signal();
        apsalarThread.incomingEventLock.unlock();
    }

    private int nextHeartbeatInterval() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        int i = instance.heartbeatInterval * instance.HEARTBEAT_INTERVAL_BACKOFF;
        if (i > instance.HEARTBEAT_INTERVAL_MAX) {
            return instance.HEARTBEAT_INTERVAL_MAX;
        }
        return i;
    }

    private RawEvent heartbeatEvent() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.heartbeatInterval = nextHeartbeatInterval();
        if (!instance.doHeartbeat) {
            return null;
        }
        instance.getClass();
        return new RawEvent(2);
    }

    private int sessionHeartbeatEvent() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        SQLiteDatabase sQLiteDatabase = instance.database;
        instance.getClass();
        String str = "SELECT id, session_json FROM events WHERE type=? ORDER BY id DESC LIMIT 1";
        String[] strArr = new String[]{String.valueOf(2)};
        Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, strArr) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, strArr);
        if (rawQuery == null) {
            instance.getClass();
            return -1;
        }
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            int i = rawQuery.getInt(0);
            String string = rawQuery.getString(1);
            if (string == null) {
                instance.getClass();
                rawQuery.close();
                return -1;
            }
            try {
                string = JSONObjectInstrumentation.init(string).getString(AnalyticAttribute.SESSION_ID_ATTRIBUTE);
                instance.getClass();
                if (string.equals(instance.info.sessionId)) {
                    rawQuery.close();
                    return i;
                }
            } catch (JSONException e) {
                instance.getClass();
                rawQuery.close();
                return -1;
            }
        }
        rawQuery.close();
        return -1;
    }

    private void handleHeartbeat(ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (sessionHeartbeatEvent() != -1) {
            instance.getClass();
            sQLiteDatabase = instance.database;
            String str = "events";
            String str2 = "id=?";
            String[] strArr = new String[]{String.valueOf(r2)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, str2, strArr);
            } else {
                sQLiteDatabase.delete(str, str2, strArr);
            }
        }
        sQLiteDatabase = instance.database;
        String str3 = "events";
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            SQLiteInstrumentation.insert(sQLiteDatabase, str3, null, contentValues);
        } else {
            sQLiteDatabase.insert(str3, null, contentValues);
        }
    }

    public void run() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        while (isActiveThread()) {
            try {
                RawEvent rawEvent = (RawEvent) this.eventQueue.poll((long) instance.heartbeatInterval, TimeUnit.MILLISECONDS);
                if (rawEvent == null) {
                    rawEvent = heartbeatEvent();
                    if (rawEvent == null) {
                    }
                } else {
                    instance.heartbeatInterval = instance.HEARTBEAT_INTERVAL_MIN;
                }
                RawEvent rawEvent2 = rawEvent;
                instance.getClass();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Constants.BUNDLE_TYPE, Integer.valueOf(rawEvent2.eventType));
                contentValues.put("unix_t", Long.valueOf(rawEvent2.eventTime));
                String str = "session_json";
                JSONObject toJSON = instance.info.toJSON();
                contentValues.put(str, !(toJSON instanceof JSONObject) ? toJSON.toString() : JSONObjectInstrumentation.toString(toJSON));
                switch (rawEvent2.eventType) {
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        contentValues.put(Constants.BUNDLE_NAME, rawEvent2.eventName);
                        contentValues.put("args", rawEvent2.eventData);
                        break;
                    default:
                        Log.wtf(TAG, "Invalid event received on queue.");
                        continue;
                }
                if (instance.database != null) {
                    switch (rawEvent2.eventType) {
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            handleHeartbeat(contentValues);
                            break;
                        default:
                            SQLiteDatabase sQLiteDatabase = instance.database;
                            String str2 = "events";
                            if (sQLiteDatabase instanceof SQLiteDatabase) {
                                SQLiteInstrumentation.insert(sQLiteDatabase, str2, null, contentValues);
                            } else {
                                sQLiteDatabase.insert(str2, null, contentValues);
                            }
                            instance.getClass();
                            break;
                    }
                    signalIncomingEvent();
                } else {
                    instance.getClass();
                }
            } catch (InterruptedException e) {
            }
        }
        instance.getClass();
    }
}
