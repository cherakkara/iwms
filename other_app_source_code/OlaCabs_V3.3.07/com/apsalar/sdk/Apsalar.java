package com.apsalar.sdk;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.utils.Constants;
import java.util.HashMap;
import java.util.Locale;
import org.apache.http.protocol.HttpRequestExecutor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Apsalar {
    private static final String FACEBOOK_ATTRIBUTION_ID_URL = "content://com.facebook.katana.provider.AttributionIdProvider";
    static final String TAG = "Apsalar SDK";
    private static final char[] hexDigits;

    public static void startSession(Context context, String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        instance.registeredReceiver = registerReceiver(context);
        if (context == null) {
            instance.getClass();
            return;
        }
        ApsalarEventConsumerThread.getThreadInstance();
        if (instance.info == null) {
            instance.info = new ApsalarSessionInfo(context, str, str2);
            instance.info.sessionStart = System.currentTimeMillis();
            queueEvent(1);
        }
        ApsalarThread.getThreadInstance(str, str2);
    }

    public static boolean registerReceiver(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        if (instance.registeredReceiver) {
            instance.getClass();
            return true;
        }
        instance.getClass();
        return filterBroadcastIntents(context, "com.apsalar.sdk.ApsalarReceiver");
    }

    public static void restartSession(Context context, String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        if (instance.info != null) {
            instance.info = new ApsalarSessionInfo(context, str, str2);
            instance.info.sessionStart = System.currentTimeMillis();
            queueEvent(1);
        }
        startSession(context, str, str2);
    }

    public static void restartSession() {
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        if (context == null) {
            instance.getClass();
        } else if (instance.info == null || instance.ctx == null) {
            instance.getClass();
        } else {
            restartSession(instance.ctx, instance.info.apiKey, instance.info.secret);
        }
    }

    public static void endSession() {
        ApSingleton.getInstance(ApSingleton.getContext());
        queueEvent(3, "end_session", Trace.NULL);
    }

    public static void unregisterApsalarReceiver() {
        unregisterApsalarReceiver(ApSingleton.getContext());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void unregisterApsalarReceiver(android.content.Context r5) {
        /*
        r4 = 0;
        r3 = 0;
        r1 = com.apsalar.sdk.ApSingleton.getInstance(r5);
        r1.getClass();
        r0 = r1.apsalar_receiver;
        if (r0 == 0) goto L_0x001d;
    L_0x000d:
        r0 = r1.registeredReceiver;
        if (r0 == 0) goto L_0x001d;
    L_0x0011:
        r1.getClass();	 Catch:{ IllegalArgumentException -> 0x0057 }
        r0 = r1.apsalar_receiver;	 Catch:{ IllegalArgumentException -> 0x0057 }
        r5.unregisterReceiver(r0);	 Catch:{ IllegalArgumentException -> 0x0057 }
        r1.apsalar_receiver = r4;
        r1.registeredReceiver = r3;
    L_0x001d:
        r0 = r1.apsalar_safe_receiver;
        if (r0 == 0) goto L_0x0056;
    L_0x0021:
        r0 = r1.registeredSafeReceiver;
        if (r0 == 0) goto L_0x0056;
    L_0x0025:
        r1.getClass();	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = "com.apsalar.sdk";
        if (r5 == 0) goto L_0x0030;
    L_0x002c:
        r0 = r5.getPackageName();	 Catch:{ IllegalArgumentException -> 0x0069 }
    L_0x0030:
        r2 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0069 }
        r2.<init>();	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = r2.append(r0);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r2 = ".SafeApsalarReceiver";
        r0 = r0.append(r2);	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = r0.toString();	 Catch:{ IllegalArgumentException -> 0x0069 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x007b }
        r2 = r1.apsalar_safe_receiver;	 Catch:{ ClassNotFoundException -> 0x007b }
        r0 = r0.cast(r2);	 Catch:{ ClassNotFoundException -> 0x007b }
        r0 = (android.content.BroadcastReceiver) r0;	 Catch:{ ClassNotFoundException -> 0x007b }
        r5.unregisterReceiver(r0);	 Catch:{ ClassNotFoundException -> 0x007b }
    L_0x0052:
        r1.apsalar_safe_receiver = r4;
        r1.registeredSafeReceiver = r3;
    L_0x0056:
        return;
    L_0x0057:
        r0 = move-exception;
        r1.incrExceptionCount();	 Catch:{ all -> 0x0063 }
        r1.getClass();	 Catch:{ all -> 0x0063 }
        r1.apsalar_receiver = r4;
        r1.registeredReceiver = r3;
        goto L_0x001d;
    L_0x0063:
        r0 = move-exception;
        r1.apsalar_receiver = r4;
        r1.registeredReceiver = r3;
        throw r0;
    L_0x0069:
        r0 = move-exception;
        r1.incrExceptionCount();	 Catch:{ all -> 0x0075 }
        r1.getClass();	 Catch:{ all -> 0x0075 }
        r1.apsalar_safe_receiver = r4;
        r1.registeredSafeReceiver = r3;
        goto L_0x0056;
    L_0x0075:
        r0 = move-exception;
        r1.apsalar_safe_receiver = r4;
        r1.registeredSafeReceiver = r3;
        throw r0;
    L_0x007b:
        r0 = move-exception;
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.Apsalar.unregisterApsalarReceiver(android.content.Context):void");
    }

    public static boolean enableHeartbeat(boolean z) {
        ApSingleton.getInstance(ApSingleton.getContext()).doHeartbeat = z;
        return z;
    }

    public static boolean isHeartbeatEnabled() {
        return ApSingleton.getInstance(ApSingleton.getContext()).doHeartbeat;
    }

    public static void event(String str) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        queueEvent(3, str, Trace.NULL);
    }

    public static void event(String str, Object... objArr) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        if (objArr.length % 2 != 0) {
            instance.getClass();
            instance.incrDropEventsCount();
            return;
        }
        Object obj;
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < objArr.length) {
            try {
                jSONObject.put((String) objArr[i], objArr[i + 1]);
                i += 2;
            } catch (JSONException e) {
                instance.incrExceptionCount();
                instance.getClass();
                obj = 1;
            }
        }
        obj = null;
        if (obj != null) {
            instance.getClass();
        }
        queueEvent(3, str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
    }

    public static void eventJSON(String str, JSONObject jSONObject) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        queueEvent(3, str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
    }

    public static void event(String str, JSONObject jSONObject) {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        eventJSON(str, jSONObject);
    }

    public static void setGender(String str) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        Locale locale = instance.ctx.getResources().getConfiguration().locale;
        if (str.toLowerCase(locale).equals("f") || str.toLowerCase(locale).equals("m")) {
            JSONObject build_JSON = build_JSON("gender", str);
            queueEvent(3, "__gender__", !(build_JSON instanceof JSONObject) ? build_JSON.toString() : JSONObjectInstrumentation.toString(build_JSON));
            return;
        }
        instance.getClass();
        instance.incrDropEventsCount();
    }

    public static void setAge(int i) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        if (i <= 0 || i >= 110) {
            instance.getClass();
            instance.incrDropEventsCount();
            return;
        }
        String valueOf = String.valueOf(i);
        JSONObject build_JSON = build_JSON("age", valueOf);
        queueEvent(3, "__age__", !(build_JSON instanceof JSONObject) ? build_JSON.toString() : JSONObjectInstrumentation.toString(build_JSON));
    }

    public static void setFBAppId(String str) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        if (str != null) {
            instance.FBAppId = str;
        }
    }

    public static String getFacebookAttributionId() {
        String str;
        Context context = ApSingleton.getContext();
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        if (context == null) {
            instance.getClass();
            return null;
        }
        Cursor query;
        try {
            str = "aid";
            query = instance.ctx.getContentResolver().query(Uri.parse(FACEBOOK_ATTRIBUTION_ID_URL), new String[]{"aid"}, null, null, null);
            if (query == null) {
                instance.getClass();
                return null;
            } else if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("aid");
                if (columnIndex < 0) {
                    if (query != null) {
                        query.close();
                    }
                    instance.getClass();
                    return null;
                }
                str = query.getString(columnIndex);
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e) {
                        try {
                            instance.incrExceptionCount();
                            instance.getClass();
                            return str;
                        } finally {
                            instance.getClass();
                        }
                    }
                }
                instance.getClass();
                return str;
            } else {
                if (query != null) {
                    query.close();
                }
                instance.getClass();
                return null;
            }
        } catch (Exception e2) {
            str = null;
            instance.incrExceptionCount();
            instance.getClass();
            return str;
        } catch (Throwable th) {
            if (query != null) {
                query.close();
            }
        }
    }

    public static String getSessionId() {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        return (instance.info == null || instance.info.sessionId == null) ? "None" : instance.info.sessionId;
    }

    protected static boolean saveSharedPrefs(Context context, String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(context);
        Editor edit = instance.ctx.getApplicationContext().getSharedPreferences("ApsalarAppPrefs", 0).edit();
        edit.putString(str, str2);
        edit.commit();
        instance.getClass();
        return true;
    }

    protected static boolean saveSharedPrefs(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        Editor edit = instance.ctx.getApplicationContext().getSharedPreferences("ApsalarAppPrefs", 0).edit();
        edit.putBoolean("SQL_config", instance.configTableCreated);
        edit.putBoolean("SQL_backlog", instance.backlogTableCreated);
        edit.putBoolean("SQL_devicekeys", instance.devicekeysTableCreated);
        edit.putInt("EXPIRES", instance.expires);
        edit.putString("HASH", instance.hash);
        edit.putBoolean("DEVICES", instance.devicesAlreadyResolved);
        edit.putBoolean("resolveall", instance.RESOLVE_ALL_AVAILABLE);
        edit.putBoolean("alwayscanon", instance.ALWAYS_REQUEST_CANONICAL);
        edit.putInt("evb4sleep", instance.NUM_EVENTS_B4_SLEEP);
        edit.putInt("qsizemax", instance.QUEUE_SIZE_MAX);
        edit.putInt("bsizemax", instance.BUFFER_SIZE_MAX);
        edit.putInt("hrtbackoff", instance.HEARTBEAT_INTERVAL_BACKOFF);
        edit.putInt("hrtintmax", instance.HEARTBEAT_INTERVAL_MAX);
        edit.putInt("hrtintmin", instance.HEARTBEAT_INTERVAL_MIN);
        edit.putInt("retbackoff", instance.RETRY_INTERVAL_BACKOFF);
        edit.putInt("retintmax", instance.RETRY_INTERVAL_MAX);
        edit.putInt("retintmin", instance.RETRY_INTERVAL_MIN);
        edit.putInt("batmax", instance.BATCHES_MAX);
        edit.putInt("batint", instance.BATCHES_INTERVAL);
        edit.putInt("resmax", instance.RESOLVER_MAX);
        edit.putInt("shtsleep", instance.SHORTSLEEP);
        edit.putInt("medsleep", instance.MEDIUMSLEEP);
        edit.putInt("lngsleep", instance.LONGSLEEP);
        edit.putInt("xlnsleep", instance.VERYLONGSLEEP);
        edit.commit();
        instance.getClass();
        return true;
    }

    protected static boolean loadSharedPrefs(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("ApsalarAppPrefs", 0);
        try {
            instance.configTableCreated = sharedPreferences.getBoolean("SQL_config", false);
            instance.backlogTableCreated = sharedPreferences.getBoolean("SQL_backlog", false);
            instance.devicekeysTableCreated = sharedPreferences.getBoolean("SQL_devicekeys", false);
            instance.expires = sharedPreferences.getInt("EXPIRES", 0);
            instance.hash = sharedPreferences.getString("HASH", Trace.NULL);
            instance.devicesAlreadyResolved = sharedPreferences.getBoolean("DEVICES", false);
            instance.RESOLVE_ALL_AVAILABLE = sharedPreferences.getBoolean("resolveall", false);
            instance.ALWAYS_REQUEST_CANONICAL = sharedPreferences.getBoolean("alwayscanon", false);
            instance.NUM_EVENTS_B4_SLEEP = sharedPreferences.getInt("evb4sleep", 25);
            instance.QUEUE_SIZE_MAX = sharedPreferences.getInt("qsizemax", ActivityTrace.MAX_TRACES);
            instance.BUFFER_SIZE_MAX = sharedPreferences.getInt("bsizemax", Constants.MILLIS_IN_A_SECOND);
            instance.HEARTBEAT_INTERVAL_BACKOFF = sharedPreferences.getInt("hrtbackoff", 2);
            instance.HEARTBEAT_INTERVAL_MAX = sharedPreferences.getInt("hrtintmax", 21600000);
            instance.HEARTBEAT_INTERVAL_MIN = sharedPreferences.getInt("hrtintmin", by.REFRESH_TOKEN_RETRY);
            instance.RETRY_INTERVAL_BACKOFF = sharedPreferences.getInt("retbackoff", 2);
            instance.RETRY_INTERVAL_MAX = sharedPreferences.getInt("retintmax", by.REFRESH_TOKEN_RETRY);
            instance.RETRY_INTERVAL_MIN = sharedPreferences.getInt("retintmin", 15000);
            instance.BATCHES_MAX = sharedPreferences.getInt("batmax", HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
            instance.BATCHES_INTERVAL = sharedPreferences.getInt("batint", 30);
            instance.RESOLVER_MAX = sharedPreferences.getInt("resmax", 3);
            instance.SHORTSLEEP = sharedPreferences.getInt("shtsleep", Constants.MILLIS_IN_A_SECOND);
            instance.MEDIUMSLEEP = sharedPreferences.getInt("medsleep", HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
            instance.LONGSLEEP = sharedPreferences.getInt("lngsleep", TraceMachine.UNHEALTHY_TRACE_TIMEOUT);
            instance.VERYLONGSLEEP = sharedPreferences.getInt("xlnsleep", 900000);
            return true;
        } catch (Exception e) {
            instance.incrExceptionCount();
            instance.getClass();
            instance.getClass();
            return false;
        }
    }

    protected static boolean loadConfig(Context context) {
        Cursor query;
        Throwable th;
        Cursor cursor = null;
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        try {
            instance.database = ApsalarSQLiteHelper.getSQLWritableDatabase(context);
            if (instance.database == null) {
                instance.getClass();
                if (cursor != null) {
                    cursor.close();
                }
                ApsalarSQLiteHelper.closeDatabase();
                return false;
            }
            SQLiteDatabase sQLiteDatabase = instance.database;
            String str = "config";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, null, null, null, null, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, null, null, null, null, null, null, null);
            }
            try {
                if (query.getCount() >= 1) {
                    instance.getClass();
                    query.moveToFirst();
                    while (!query.isAfterLast()) {
                        boolean z;
                        str = query.getString(0);
                        String string = query.getString(1);
                        if (instance.info == null) {
                            instance.ctx = context;
                            instance.info = new ApsalarSessionInfo(context, str, string);
                        } else {
                            instance.info.apiKey = str;
                            instance.info.secret = string;
                        }
                        instance.isLAT = query.getInt(2) > 0;
                        if (query.getInt(3) > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        instance.doHeartbeat = z;
                        if (query.getInt(4) > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        instance.playStoreAvailable = z;
                        instance.ANDI = query.getString(5);
                        instance.AIFA = query.getString(6);
                        instance.canonicalKeyspace = query.getString(11);
                        instance.canonicalDeviceId = query.getString(12);
                        instance.desired = JSONArrayInstrumentation.init(query.getString(13));
                        instance.getClass();
                        query.moveToNext();
                    }
                } else {
                    instance.getClass();
                }
                query.close();
                if (cursor != null) {
                    cursor.close();
                }
                ApsalarSQLiteHelper.closeDatabase();
                return true;
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            query = cursor;
            try {
                instance.incrExceptionCount();
                instance.getClass();
                if (query != null) {
                    query.close();
                }
                ApsalarSQLiteHelper.closeDatabase();
                return false;
            } catch (Throwable th2) {
                cursor = query;
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }
                ApsalarSQLiteHelper.closeDatabase();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            ApsalarSQLiteHelper.closeDatabase();
            throw th;
        }
    }

    protected static boolean updateConfigTable(Context context, String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(context);
        if (instance.database == null) {
            instance.getClass();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        SQLiteDatabase sQLiteDatabase = instance.database;
        String str3 = "config";
        if ((!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update(str3, contentValues, null, null) : SQLiteInstrumentation.update(sQLiteDatabase, str3, contentValues, null, null)) != 1) {
            instance.getClass();
        }
        instance.getClass();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static boolean setInfo(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
        r2 = 1;
        r3 = 0;
        r1 = 0;
        r4 = com.apsalar.sdk.ApSingleton.getInstance(r9);
        r0 = r4.info;
        if (r0 != 0) goto L_0x0010;
    L_0x000b:
        r4.getClass();
        r0 = r1;
    L_0x000f:
        return r0;
    L_0x0010:
        r4.getClass();
        if (r9 == 0) goto L_0x0019;
    L_0x0015:
        if (r10 == 0) goto L_0x0019;
    L_0x0017:
        if (r11 != 0) goto L_0x001e;
    L_0x0019:
        r4.getClass();
        r0 = r1;
        goto L_0x000f;
    L_0x001e:
        if (r12 == 0) goto L_0x0023;
    L_0x0020:
        com.apsalar.sdk.ApsalarSQLiteHelper.clearConfigTables();	 Catch:{ Exception -> 0x00ae, SecurityException -> 0x00b7 }
    L_0x0023:
        r5 = new android.content.ContentValues;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.<init>();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = r4.database;	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        if (r0 != 0) goto L_0x0032;
    L_0x002c:
        r0 = com.apsalar.sdk.ApsalarSQLiteHelper.getSQLWritableDatabase(r9);	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        r4.database = r0;	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
    L_0x0032:
        r0 = r4.database;	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        r6 = "SELECT * FROM config LIMIT 1";
        r7 = 0;
        r8 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        if (r8 != 0) goto L_0x00bf;
    L_0x003b:
        r0 = r0.rawQuery(r6, r7);	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
    L_0x003f:
        r3 = r0.getCount();	 Catch:{ SQLiteException -> 0x0110 }
        if (r0 == 0) goto L_0x0048;
    L_0x0045:
        r0.close();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
    L_0x0048:
        if (r3 >= r2) goto L_0x0106;
    L_0x004a:
        r0 = "apiKey";
        r5.put(r0, r10);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "secret";
        r5.put(r0, r11);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "isLAT";
        r2 = r4.isLAT;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "doHeartbeat";
        r2 = r4.doHeartbeat;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "playStoreAvailable";
        r2 = r4.playStoreAvailable;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "andi";
        r2 = r4.ANDI;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "aifa";
        r2 = r4.AIFA;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "canonicalKeyspace";
        r2 = r4.canonicalKeyspace;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = "canonicalDeviceId";
        r2 = r4.canonicalDeviceId;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r5.put(r0, r2);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r2 = "desired";
        r0 = r4.desired;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        if (r0 != 0) goto L_0x00e6;
    L_0x0097:
        r0 = "[]";
    L_0x0099:
        r5.put(r2, r0);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = r4.database;	 Catch:{ SQLException -> 0x00fe }
        r2 = "config";
        r3 = 0;
        r6 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLException -> 0x00fe }
        if (r6 != 0) goto L_0x00f8;
    L_0x00a5:
        r0.insertOrThrow(r2, r3, r5);	 Catch:{ SQLException -> 0x00fe }
    L_0x00a8:
        r4.getClass();	 Catch:{ SQLException -> 0x00fe }
    L_0x00ab:
        r0 = r1;
        goto L_0x000f;
    L_0x00ae:
        r0 = move-exception;
        r4.incrExceptionCount();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r4.getClass();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        goto L_0x0023;
    L_0x00b7:
        r0 = move-exception;
        r4.incrExceptionCount();
        r4.getClass();
        goto L_0x00ab;
    L_0x00bf:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        r0 = com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.rawQuery(r0, r6, r7);	 Catch:{ SQLiteException -> 0x00c7, all -> 0x00d7 }
        goto L_0x003f;
    L_0x00c7:
        r0 = move-exception;
        r0 = r3;
    L_0x00c9:
        r4.incrExceptionCount();	 Catch:{ all -> 0x010c }
        r4.getClass();	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00d4;
    L_0x00d1:
        r0.close();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
    L_0x00d4:
        r0 = r1;
        goto L_0x000f;
    L_0x00d7:
        r0 = move-exception;
    L_0x00d8:
        if (r3 == 0) goto L_0x00dd;
    L_0x00da:
        r3.close();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
    L_0x00dd:
        throw r0;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
    L_0x00de:
        r0 = move-exception;
        r4.incrExceptionCount();
        r4.getClass();
        goto L_0x00ab;
    L_0x00e6:
        r0 = r4.desired;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r3 = r0 instanceof org.json.JSONArray;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        if (r3 != 0) goto L_0x00f1;
    L_0x00ec:
        r0 = r0.toString();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        goto L_0x0099;
    L_0x00f1:
        r0 = (org.json.JSONArray) r0;	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation.toString(r0);	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        goto L_0x0099;
    L_0x00f8:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLException -> 0x00fe }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.insertOrThrow(r0, r2, r3, r5);	 Catch:{ SQLException -> 0x00fe }
        goto L_0x00a8;
    L_0x00fe:
        r0 = move-exception;
        r4.incrExceptionCount();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r4.getClass();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        goto L_0x00ab;
    L_0x0106:
        r4.getClass();	 Catch:{ SecurityException -> 0x00b7, Exception -> 0x00de }
        r0 = r2;
        goto L_0x000f;
    L_0x010c:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x00d8;
    L_0x0110:
        r2 = move-exception;
        goto L_0x00c9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.Apsalar.setInfo(android.content.Context, java.lang.String, java.lang.String, boolean):boolean");
    }

    protected static boolean filterBroadcastIntents(Context context, String str) {
        ApSingleton instance = ApSingleton.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.apsalar.sdk.SOFT_RESET");
        try {
            if (str.equals("com.apsalar.sdk.ApsalarReceiver")) {
                if (instance.apsalar_receiver == null) {
                    instance.apsalar_receiver = (BroadcastReceiver) Class.forName(str).newInstance();
                }
                context.registerReceiver(instance.apsalar_receiver, intentFilter);
                instance.getClass();
                return true;
            }
            if (instance.apsalar_safe_receiver == null) {
                instance.apsalar_safe_receiver = (BroadcastReceiver) Class.forName(str).newInstance();
            }
            context.registerReceiver(instance.apsalar_safe_receiver, intentFilter);
            instance.getClass();
            return true;
        } catch (ClassNotFoundException e) {
            instance.incrExceptionCount();
            instance.getClass();
            return false;
        } catch (InstantiationException e2) {
            instance.incrExceptionCount();
            instance.getClass();
            return false;
        } catch (IllegalAccessException e3) {
            instance.incrExceptionCount();
            instance.getClass();
            return false;
        }
    }

    protected static void queueEvent(int i, String str, String str2) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (i == 3 && str == null) {
            instance.getClass();
            instance.incrDropEventsCount();
        } else if (instance.info == null) {
            instance.getClass();
            instance.incrDropEventsCount();
        } else if (ApSingleton.eventConsumerThread == null) {
            instance.getClass();
        } else if (!ApSingleton.eventConsumerThread.put(new RawEvent(i, str, str2))) {
            instance.getClass();
            instance.incrDropEventsCount();
        }
    }

    protected static void queueEvent(int i) {
        if (i == 3) {
            Log.w(TAG, "this should only be called for events that are not ApsalarAPI.Type.EVENT");
        } else {
            queueEvent(i, Trace.NULL, Trace.NULL);
        }
    }

    private static JSONObject build_JSON(Object... objArr) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < objArr.length) {
            try {
                jSONObject.put((String) objArr[i], objArr[i + 1]);
                i += 2;
            } catch (JSONException e) {
                instance.incrExceptionCount();
                instance.getClass();
                return null;
            }
        }
        return jSONObject;
    }

    static void sendReferrerInstall(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        HashMap hashMap = (HashMap) ApsalarReceiver.retrieveCSIReferrer(instance.ctx);
        instance.getClass();
        JSONObject jSONObject = new JSONObject();
        for (String str : hashMap.keySet()) {
            if (str.equals("referrer")) {
                try {
                    jSONObject.put("referrer", hashMap.get(str));
                } catch (JSONException e) {
                    instance.incrExceptionCount();
                    instance.getClass();
                }
            }
        }
        if (jSONObject.length() > 0) {
            instance.getClass();
            event("__InstallReferrer", jSONObject);
        }
    }

    static {
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    protected static String hexDigest(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(hexDigits[(bArr[i] & 240) >>> 4]);
            stringBuffer.append(hexDigits[bArr[i] & 15]);
        }
        return stringBuffer.toString();
    }

    protected static String setKeySpace(String str) {
        return ApSingleton.getInstance(ApSingleton.getContext()).canonicalKeyspace;
    }

    protected static String getDeviceId() {
        return getDeviceId(ApSingleton.getInstance(ApSingleton.getContext()).canonicalKeyspace);
    }

    protected static String getDeviceId(String str) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        instance.getClass();
        if (str == null) {
            return "None";
        }
        if (str.equals("ANDI") && instance.ANDI != null) {
            String str2 = instance.ANDI;
            if (str2.equals("9774d56d682e549c")) {
                return "None";
            }
            return str2;
        } else if (!str.equals("AIFA") || instance.AIFA == null) {
            return "None";
        } else {
            return instance.AIFA;
        }
    }

    protected static void sendFBInstall(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        if (instance.FBAppId == null) {
            Log.w(TAG, "Facebook App ID is null. Was Apsalar.setFBAppId() called?");
            return;
        }
        instance.getClass();
        String facebookAttributionId = getFacebookAttributionId();
        if (facebookAttributionId == null) {
            instance.getClass();
            return;
        }
        instance.getClass();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(instance.FBAppId);
        try {
            jSONObject.put("fb_app_attribution", facebookAttributionId);
            jSONObject.put("fb_app_ids", jSONArray);
        } catch (JSONException e) {
            instance.incrExceptionCount();
            instance.getClass();
            e.printStackTrace();
        }
        event("__FBInstall", jSONObject);
    }
}
