package com.localytics.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.localytics.android.Localytics.ProfileScope;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.io.File;
import org.json.JSONObject;

class ProfileProvider extends BaseProvider {
    static final int DATABASE_VERSION = 1;

    static final class ProfileDatabaseHelper extends LocalyticsDatabaseHelper {
        ProfileDatabaseHelper(String str, int i, LocalyticsDao localyticsDao) {
            super(str, i, localyticsDao);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (sQLiteDatabase == null) {
                throw new IllegalArgumentException("db cannot be null");
            }
            String str = "PRAGMA auto_vacuum = INCREMENTAL;";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            onUpgrade(sQLiteDatabase, 0, ProfileProvider.DATABASE_VERSION);
        }

        protected void migrateV2ToV3(SQLiteDatabase sQLiteDatabase) {
            Throwable e;
            String format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", new Object[]{"changes", "_id", "scope", "change", "customer_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            if (oldDB != null) {
                synchronized (oldDB) {
                    Cursor query;
                    try {
                        ContentValues contentValues = new ContentValues();
                        SQLiteDatabase sQLiteDatabase2 = oldDB;
                        format = "profile";
                        String str = "_id ASC";
                        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                            query = SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str);
                        } else {
                            query = sQLiteDatabase2.query(format, null, null, null, null, null, str);
                        }
                        while (query.moveToNext()) {
                            try {
                                JSONObject init = JSONObjectInstrumentation.init(query.getString(query.getColumnIndexOrThrow("attribute")));
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("op", ProfileOperation.ASSIGN.getOperationString());
                                String str2 = (String) init.keys().next();
                                jSONObject.put("attr", str2);
                                jSONObject.put("value", init.get(str2));
                                contentValues.put("scope", ProfileScope.APPLICATION.getScope());
                                String str3 = "change";
                                if (jSONObject instanceof JSONObject) {
                                    str2 = JSONObjectInstrumentation.toString(jSONObject);
                                } else {
                                    str2 = jSONObject.toString();
                                }
                                contentValues.put(str3, str2);
                                contentValues.put("customer_id", query.getString(query.getColumnIndexOrThrow("customer_id")));
                                format = "changes";
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    SQLiteInstrumentation.insert(sQLiteDatabase, format, null, contentValues);
                                } else {
                                    sQLiteDatabase.insert(format, null, contentValues);
                                }
                                contentValues.clear();
                            } catch (Throwable e2) {
                                Log.m12802w("Caught JSON exception", e2);
                            } catch (Throwable th) {
                                e2 = th;
                            }
                        }
                        LocalyticsDatabaseHelper.cleanUpOldDB();
                        if (query != null) {
                            query.close();
                        }
                    } catch (Throwable th2) {
                        e2 = th2;
                        query = null;
                        if (query != null) {
                            query.close();
                        }
                        throw e2;
                    }
                }
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < ProfileProvider.DATABASE_VERSION) {
                migrateV2ToV3(sQLiteDatabase);
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            Object[] objArr = new Object[ProfileProvider.DATABASE_VERSION];
            objArr[0] = DatabaseUtils.stringForQuery(sQLiteDatabase, "select sqlite_version()", null);
            Log.m12799v(String.format("SQLite library version is: %s", objArr));
            if (!sQLiteDatabase.isReadOnly()) {
                String str = "PRAGMA foreign_keys = ON;";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
            }
        }
    }

    static final class ProfileV3Columns implements BaseColumns {
        static final String CHANGE = "change";
        static final String CUSTOMER_ID = "customer_id";
        static final String DATABASE = "scope";
        static final String TABLE_NAME = "changes";

        private ProfileV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    ProfileProvider(String str, LocalyticsDao localyticsDao) {
        super(localyticsDao);
        this.mDb = new ProfileDatabaseHelper(String.format("com.localytics.android.%s.%s.sqlite", new Object[]{DatapointHelper.getSha256_buggy(localyticsDao.getApiKey()), str}), DATABASE_VERSION, localyticsDao).getWritableDatabase();
    }

    ProfileProvider(LocalyticsDao localyticsDao) {
        super(localyticsDao);
    }

    long maxSiloDbSize() {
        return Constants.dbMaxSizeForProfiles;
    }

    boolean canAddToDB() {
        return new File(this.mDb.getPath()).length() < maxSiloDbSize();
    }
}
