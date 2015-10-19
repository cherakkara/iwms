package com.localytics.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class BaseProvider {
    static final String DATABASE_FILE = "com.localytics.android.%s.%s.sqlite";
    private static final String OLD_DATABASE_FILE = "com.localytics.android.%s.sqlite";
    private static final Map<String, String> sCountProjectionMap;
    private static final Set<String> sValidTables;
    SQLiteDatabase mDb;
    LocalyticsDao mLocalyticsDao;

    static abstract class LocalyticsDatabaseHelper extends SQLiteOpenHelper {
        static final String SQLITE_BOOLEAN_FALSE = "0";
        static final String SQLITE_BOOLEAN_TRUE = "1";
        private static int completedMigrations;
        static SQLiteDatabase oldDB;
        private static File oldDBFile;
        LocalyticsDao mLocalyticsDao;

        protected abstract void migrateV2ToV3(SQLiteDatabase sQLiteDatabase);

        static {
            oldDBFile = null;
        }

        LocalyticsDatabaseHelper(String str, int i, LocalyticsDao localyticsDao) {
            super(localyticsDao.getAppContext(), str, null, i);
            this.mLocalyticsDao = localyticsDao;
            synchronized (LocalyticsDatabaseHelper.class) {
                if (oldDBFile == null) {
                    String format = String.format(BaseProvider.OLD_DATABASE_FILE, new Object[]{DatapointHelper.getSha256_buggy(localyticsDao.getApiKey())});
                    oldDBFile = new File(localyticsDao.getAppContext().getDatabasePath(format).getPath());
                    if (oldDBFile.exists()) {
                        completedMigrations = 0;
                        try {
                            oldDB = new MigrationDatabaseHelper(format, 18, localyticsDao).getWritableDatabase();
                        } catch (SQLiteException e) {
                            Log.m12801w("Error opening old database; old data will not be retained.");
                        }
                    }
                }
            }
        }

        static void cleanUpOldDB() {
            completedMigrations++;
            if (completedMigrations == 3) {
                oldDB.close();
                oldDBFile.delete();
            }
        }
    }

    /* renamed from: com.localytics.android.BaseProvider.1 */
    class C06891 implements Runnable {
        C06891() {
        }

        public void run() {
            Throwable th;
            Throwable th2;
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase = BaseProvider.this.mDb;
                String str = "PRAGMA incremental_vacuum(0);";
                Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str, null) : SQLiteInstrumentation.rawQuery(sQLiteDatabase, str, null);
                do {
                    try {
                    } catch (Throwable e) {
                        th = e;
                        cursor = rawQuery;
                        th2 = th;
                    } catch (Throwable e2) {
                        th = e2;
                        cursor = rawQuery;
                        th2 = th;
                    }
                } while (rawQuery.moveToNext());
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (Exception e3) {
                th2 = e3;
                try {
                    Log.m12802w("Auto-vacuum error", th2);
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th2;
                }
            }
        }
    }

    abstract boolean canAddToDB();

    abstract long maxSiloDbSize();

    static {
        sCountProjectionMap = Collections.unmodifiableMap(getCountProjectionMap());
        sValidTables = Collections.unmodifiableSet(getValidTables());
    }

    BaseProvider(LocalyticsDao localyticsDao) {
        this.mLocalyticsDao = localyticsDao;
    }

    private static Set<String> getValidTables() {
        Set hashSet = new HashSet();
        hashSet.add("events");
        hashSet.add("custom_dimensions");
        hashSet.add("info");
        hashSet.add("identifiers");
        hashSet.add("changes");
        hashSet.add("marketing_rules");
        hashSet.add("marketing_ruleevent");
        hashSet.add("marketing_conditions");
        hashSet.add("marketing_condition_values");
        hashSet.add("marketing_displayed");
        return hashSet;
    }

    private static HashMap<String, String> getCountProjectionMap() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("_count", "COUNT(*)");
        return hashMap;
    }

    static void deleteOldFiles(Context context) {
        deleteDirectory(new File(context.getFilesDir(), "localytics"));
    }

    static boolean deleteDirectory(File file) {
        if (file.exists() && file.isDirectory()) {
            for (String file2 : file.list()) {
                if (!deleteDirectory(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private int getNumberOfRows(String str) {
        return query(str, null, null, null, null).getCount();
    }

    Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        Log.m12799v(String.format("Query table: %s, projection: %s, selection: %s, selectionArgs: %s", new Object[]{str, Arrays.toString(strArr), str2, Arrays.toString(strArr2)}));
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str);
        if (strArr != null && 1 == strArr.length && "_count".equals(strArr[0])) {
            sQLiteQueryBuilder.setProjectionMap(sCountProjectionMap);
        }
        Cursor query = sQLiteQueryBuilder.query(this.mDb, strArr, str2, strArr2, null, null, str3);
        Log.m12799v("Query result is: " + DatabaseUtils.dumpCursorToString(query));
        return query;
    }

    private static boolean isValidTable(String str) {
        return str != null && sValidTables.contains(str);
    }

    long insert(String str, ContentValues contentValues) {
        Log.m12799v(String.format("Insert table: %s, values: %s", new Object[]{str, contentValues.toString()}));
        if (canAddToDB()) {
            SQLiteDatabase sQLiteDatabase = this.mDb;
            Log.m12799v(String.format("Inserted row with new id %d", new Object[]{Long.valueOf(!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insertOrThrow(str, null, contentValues) : SQLiteInstrumentation.insertOrThrow(sQLiteDatabase, str, null, contentValues))}));
            return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insertOrThrow(str, null, contentValues) : SQLiteInstrumentation.insertOrThrow(sQLiteDatabase, str, null, contentValues);
        }
        Log.m12799v("Database is full; data not inserted");
        return -1;
    }

    void vacuumIfNecessary() {
        if (((double) new File(this.mDb.getPath()).length()) >= ((double) maxSiloDbSize()) * 0.8d) {
            runBatchTransaction(new C06891());
        }
    }

    int remove(String str, String str2, String[] strArr) {
        int delete;
        Log.m12799v(String.format("Delete table: %s, selection: %s, selectionArgs: %s", new Object[]{str, str2, Arrays.toString(strArr)}));
        SQLiteDatabase sQLiteDatabase;
        if (str2 == null) {
            sQLiteDatabase = this.mDb;
            String str3 = "1";
            delete = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.delete(str, str3, null) : SQLiteInstrumentation.delete(sQLiteDatabase, str, str3, null);
        } else {
            sQLiteDatabase = this.mDb;
            delete = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.delete(str, str2, strArr) : SQLiteInstrumentation.delete(sQLiteDatabase, str, str2, strArr);
        }
        Log.m12799v(String.format("Deleted %d rows", new Object[]{Integer.valueOf(delete)}));
        return delete;
    }

    int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        Log.m12799v(String.format("Update table: %s, values: %s, selection: %s, selectionArgs: %s", new Object[]{str, contentValues.toString(), str2, Arrays.toString(strArr)}));
        SQLiteDatabase sQLiteDatabase = this.mDb;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.update(str, contentValues, str2, strArr) : SQLiteInstrumentation.update(sQLiteDatabase, str, contentValues, str2, strArr);
    }

    public void runBatchTransaction(Runnable runnable) {
        this.mDb.beginTransaction();
        try {
            runnable.run();
            this.mDb.setTransactionSuccessful();
        } finally {
            this.mDb.endTransaction();
        }
    }

    void close() {
        this.mDb.close();
    }
}
