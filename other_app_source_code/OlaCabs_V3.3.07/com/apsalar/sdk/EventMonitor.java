package com.apsalar.sdk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

public class EventMonitor {
    static String TAG;
    static long anyEvent;
    static long endEvent;
    static long eventQ_count;
    static long eventsFileQ_count;
    static long facebookEvent;
    static long installEvent;
    static long sqliteQ_count;
    static long startEvent;

    static {
        TAG = "Apsalar EventMonitor";
        eventQ_count = 0;
        sqliteQ_count = 0;
        eventsFileQ_count = 0;
        startEvent = 0;
        facebookEvent = 0;
        installEvent = 0;
        anyEvent = 0;
        endEvent = 0;
    }

    public static long getSentEvents_count() {
        return (long) ApSingleton.getInstance(ApSingleton.getContext()).getSentEventsCount();
    }

    public static long getNetworkError_count() {
        return (long) ApSingleton.getInstance(ApSingleton.getContext()).getNetworkErrorCount();
    }

    public static long getException_count() {
        return (long) ApSingleton.getInstance(ApSingleton.getContext()).getExceptionCount();
    }

    public static long getDropEvent_count() {
        return (long) ApSingleton.getInstance(ApSingleton.getContext()).getDropEventsCount();
    }

    public static long getLastEventQ_count() {
        return eventQ_count;
    }

    public static long getLastSqliteQ_count() {
        return sqliteQ_count;
    }

    public static long getLastEventsFile_count() {
        return eventsFileQ_count;
    }

    public static long getBootstrap_count() {
        return (long) ApSingleton.getInstance(ApSingleton.getContext()).getBootstrapCount();
    }

    public static long getLastStartEvent() {
        return startEvent;
    }

    public static long getLastFacebookInstall() {
        return facebookEvent;
    }

    public static long getLastInstall() {
        return installEvent;
    }

    public static long getLastEvent() {
        return anyEvent;
    }

    public static long getLastEndEvent() {
        return endEvent;
    }

    public static void markStart() {
        startEvent = System.currentTimeMillis();
    }

    public static void markFacebook() {
        facebookEvent = System.currentTimeMillis();
    }

    public static void markINSTALL_REFERRER() {
        installEvent = System.currentTimeMillis();
    }

    public static void markEvent() {
        anyEvent = System.currentTimeMillis();
    }

    public static void markEnd() {
        endEvent = System.currentTimeMillis();
    }

    public static long countEventQ() {
        ApSingleton.getInstance(ApSingleton.getContext());
        if (ApSingleton.apsalar_thread != null) {
            eventQ_count = (long) ApSingleton.eventConsumerThread.eventQueue.size();
        } else {
            eventQ_count = 0;
        }
        return eventQ_count;
    }

    public static long countSQLiteQ() {
        Cursor rawQuery;
        Throwable th;
        Cursor cursor = null;
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (instance.hash == null) {
            instance.getClass();
            sqliteQ_count = 0;
            return sqliteQ_count;
        }
        try {
            if (instance.database != null) {
                SQLiteDatabase sQLiteDatabase = instance.database;
                String str = "select count(*) from events;";
                rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
                if (rawQuery != null) {
                    try {
                        rawQuery.moveToFirst();
                        sqliteQ_count = rawQuery.getLong(0);
                    } catch (Exception e) {
                        try {
                            instance.getClass();
                            instance.incrExceptionCount();
                            if (rawQuery != null) {
                                rawQuery.close();
                            }
                            return sqliteQ_count;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            cursor = rawQuery;
                            th = th3;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                sqliteQ_count = 0;
            } else {
                instance.getClass();
                sqliteQ_count = 0;
                rawQuery = null;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e2) {
            rawQuery = null;
            instance.getClass();
            instance.incrExceptionCount();
            if (rawQuery != null) {
                rawQuery.close();
            }
            return sqliteQ_count;
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return sqliteQ_count;
    }
}
