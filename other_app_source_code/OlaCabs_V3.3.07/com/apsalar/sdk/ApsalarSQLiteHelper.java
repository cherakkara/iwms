package com.apsalar.sdk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.http.protocol.HTTP;

class ApsalarSQLiteHelper extends SQLiteOpenHelper {
    static final String TAG = "Apsalar SDK/SQLiteHelper";

    public ApsalarSQLiteHelper(Context context, String str) {
        super(context, str, null, 1);
        ApSingleton.getInstance(context).getClass();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static SQLiteDatabase getSQLWritableDatabase(Context context) {
        ApSingleton instance = ApSingleton.getInstance(context);
        instance.getClass();
        if (context == null) {
            instance.getClass();
            return null;
        } else if (instance.database != null) {
            return instance.database;
        } else {
            if (instance.hash == null || instance.hash.length() == 0) {
                String packageName = context.getPackageName();
                instance.hash = Trace.NULL;
                try {
                    if (instance.info == null || instance.info.secret == null) {
                        Apsalar.loadSharedPrefs(context);
                    } else {
                        String str = instance.info.secret;
                        MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
                        instance2.update(str.getBytes(HTTP.UTF_8));
                        instance2.update(packageName.getBytes(HTTP.UTF_8));
                        instance.hash = Apsalar.hexDigest(instance2.digest());
                        Apsalar.saveSharedPrefs(context, "HASH", instance.hash);
                    }
                    if (instance.hash == null || instance.hash.length() == 0) {
                        instance.getClass();
                        return null;
                    } else if (instance.info == null) {
                        Apsalar.loadConfig(context);
                    }
                } catch (NoSuchAlgorithmException e) {
                    instance.getClass();
                    return null;
                } catch (UnsupportedEncodingException e2) {
                    instance.getClass();
                    return null;
                } catch (IndexOutOfBoundsException e3) {
                    instance.getClass();
                    return null;
                } catch (Exception e4) {
                    instance.getClass();
                    if (instance.hash == null || instance.hash.length() == 0) {
                        instance.getClass();
                        return null;
                    }
                }
            }
            if (instance.dbOpener == null) {
                instance.dbOpener = new ApsalarSQLiteHelper(context, "Apsalar.sqlite_" + instance.hash);
            }
            if (instance.database == null) {
                try {
                    instance.database = instance.dbOpener.getWritableDatabase();
                    instance.openCount++;
                } catch (SQLiteException e5) {
                    instance.getClass();
                } catch (IllegalStateException e6) {
                    instance.getClass();
                } catch (Exception e7) {
                    instance.getClass();
                }
            }
            return instance.database;
        }
    }

    private static boolean truncateTable(String str) {
        ApSingleton instance = ApSingleton.getInstance(ApSingleton.getContext());
        if (str.length() == 0) {
            instance.getClass();
            return false;
        }
        boolean z;
        if (instance.database == null) {
            instance.database = getSQLWritableDatabase(instance.ctx);
        }
        try {
            instance.getClass();
            SQLiteDatabase sQLiteDatabase = instance.database;
            String str2 = "DELETE FROM " + str;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
            z = true;
        } catch (Exception e) {
            instance.getClass();
            z = false;
        }
        return z;
    }

    public static boolean clearConfigTables() {
        ApSingleton.getInstance(ApSingleton.getContext());
        boolean z = true;
        if (!truncateTable("config")) {
            z = false;
        }
        if (truncateTable("device_keys")) {
            return z;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean createEventsTable() {
        /*
        r2 = 1;
        r1 = 0;
        r0 = com.apsalar.sdk.ApSingleton.getContext();
        r3 = com.apsalar.sdk.ApSingleton.getInstance(r0);
        r0 = r3.eventsTableCreated;
        if (r0 != 0) goto L_0x0059;
    L_0x000e:
        r0 = r3.ctx;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r0 = getSQLWritableDatabase(r0);	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r3.database = r0;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        if (r0 != 0) goto L_0x0020;
    L_0x001a:
        r0 = r1;
    L_0x001b:
        closeDatabase();
        r1 = r0;
    L_0x001f:
        return r1;
    L_0x0020:
        r3.getClass();	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r3.getClass();	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r4 = "CREATE TABLE IF NOT EXISTS events ( id INTEGER PRIMARY KEY, unix_t INTEGER NOT NULL, session_json TEXT, type INTEGER NOT NULL, name VARCHAR(32), args TEXT)";
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        if (r5 != 0) goto L_0x0036;
    L_0x002e:
        r0.execSQL(r4);	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
    L_0x0031:
        r0 = 1;
        r3.eventsTableCreated = r0;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        r0 = r2;
        goto L_0x001b;
    L_0x0036:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r0, r4);	 Catch:{ SQLiteException -> 0x003c, IllegalStateException -> 0x0044, Exception -> 0x004c }
        goto L_0x0031;
    L_0x003c:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0054 }
        closeDatabase();
        goto L_0x001f;
    L_0x0044:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0054 }
        closeDatabase();
        goto L_0x001f;
    L_0x004c:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0054 }
        closeDatabase();
        goto L_0x001f;
    L_0x0054:
        r0 = move-exception;
        closeDatabase();
        throw r0;
    L_0x0059:
        r3.getClass();
        r1 = r2;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.ApsalarSQLiteHelper.createEventsTable():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean createConfigTable() {
        /*
        r2 = 1;
        r1 = 0;
        r0 = com.apsalar.sdk.ApSingleton.getContext();
        r3 = com.apsalar.sdk.ApSingleton.getInstance(r0);
        r0 = r3.configTableCreated;
        if (r0 != 0) goto L_0x005c;
    L_0x000e:
        r0 = r3.ctx;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = getSQLWritableDatabase(r0);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r3.database = r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        if (r0 != 0) goto L_0x0023;
    L_0x001a:
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r1;
    L_0x001e:
        closeDatabase();
        r1 = r0;
    L_0x0022:
        return r1;
    L_0x0023:
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r4 = "CREATE TABLE IF NOT EXISTS config ( apiKey TEXT primary key, secret TEXT, isLAT BOOLEAN, doHeartbeat BOOLEAN, playStoreAvailable BOOLEAN, andi TEXT NULL, aifa TEXT NULL, imei TEXT NULL, mac1 TEXT NULL, bmac TEXT NULL, apid TEXT NULL, canonicalKeyspace TEXT NULL, canonicalDeviceId TEXT NULL, desired TEXT NULL)";
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        if (r5 != 0) goto L_0x0039;
    L_0x0031:
        r0.execSQL(r4);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
    L_0x0034:
        r0 = 1;
        r3.configTableCreated = r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r2;
        goto L_0x001e;
    L_0x0039:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r0, r4);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        goto L_0x0034;
    L_0x003f:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x0047:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x004f:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x0057:
        r0 = move-exception;
        closeDatabase();
        throw r0;
    L_0x005c:
        r3.getClass();
        r1 = r2;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.ApsalarSQLiteHelper.createConfigTable():boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean createDevicekeysTable() {
        /*
        r2 = 1;
        r1 = 0;
        r0 = com.apsalar.sdk.ApSingleton.getContext();
        r3 = com.apsalar.sdk.ApSingleton.getInstance(r0);
        r0 = r3.devicekeysTableCreated;
        if (r0 != 0) goto L_0x005c;
    L_0x000e:
        r0 = r3.ctx;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = getSQLWritableDatabase(r0);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r3.database = r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        if (r0 != 0) goto L_0x0023;
    L_0x001a:
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r1;
    L_0x001e:
        closeDatabase();
        r1 = r0;
    L_0x0022:
        return r1;
    L_0x0023:
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r3.database;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r3.getClass();	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r4 = "CREATE TABLE IF NOT EXISTS device_keys ( keyspace CHAR(4), val TEXT, canonical BOOLEAN NULL, PRIMARY KEY (keyspace))";
        r5 = r0 instanceof android.database.sqlite.SQLiteDatabase;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        if (r5 != 0) goto L_0x0039;
    L_0x0031:
        r0.execSQL(r4);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
    L_0x0034:
        r0 = 1;
        r3.devicekeysTableCreated = r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        r0 = r2;
        goto L_0x001e;
    L_0x0039:
        r0 = (android.database.sqlite.SQLiteDatabase) r0;	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        com.newrelic.agent.android.instrumentation.SQLiteInstrumentation.execSQL(r0, r4);	 Catch:{ SQLiteException -> 0x003f, IllegalStateException -> 0x0047, Exception -> 0x004f }
        goto L_0x0034;
    L_0x003f:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x0047:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x004f:
        r0 = move-exception;
        r3.getClass();	 Catch:{ all -> 0x0057 }
        closeDatabase();
        goto L_0x0022;
    L_0x0057:
        r0 = move-exception;
        closeDatabase();
        throw r0;
    L_0x005c:
        r3.getClass();
        r1 = r2;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apsalar.sdk.ApsalarSQLiteHelper.createDevicekeysTable():boolean");
    }

    public static boolean createApsalarTables() {
        ApSingleton.getInstance(ApSingleton.getContext()).getClass();
        boolean z = true;
        if (!createConfigTable()) {
            z = false;
        }
        if (!createDevicekeysTable()) {
            z = false;
        }
        if (createEventsTable()) {
            return z;
        }
        return false;
    }

    public static void closeDatabase() {
    }
}
