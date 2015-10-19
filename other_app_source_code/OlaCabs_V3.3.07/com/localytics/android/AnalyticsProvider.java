package com.localytics.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.da;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

class AnalyticsProvider extends BaseProvider {
    static final int DATABASE_VERSION = 2;

    static class AnalyticsDatabaseHelper extends LocalyticsDatabaseHelper {
        AnalyticsDatabaseHelper(String str, int i, LocalyticsDao localyticsDao) {
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
            onUpgrade(sQLiteDatabase, 0, AnalyticsProvider.DATABASE_VERSION);
        }

        protected void migrateV2ToV3(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            String string;
            String string2;
            String str;
            Throwable th;
            Cursor cursor2;
            JSONObject jSONObject;
            String str2;
            JSONObject jSONObject2;
            String format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER NOT NULL);", new Object[]{"events", "_id", "blob", "upload_format"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT NOT NULL);", new Object[]{"identifiers", "key", "value"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY, %s TEXT NOT NULL);", new Object[]{"custom_dimensions", "custom_dimension_key", "custom_dimension_value"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT UNIQUE NOT NULL, %s INTEGER NOT NULL CHECK (%s >= 0), %s INTEGER NOT NULL CHECK(%s IN (%s, %s)), %s INTEGER NOT NULL CHECK(%s IN (%s, %s)), %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER CHECK (%s >= 0), %s INTEGER CHECK (%s >= 0), %s INTEGER NOT NULL CHECK (%s >= 0), %s INTEGER NOT NULL CHECK (%s >= 0), %s TEXT, %s INTEGER);", new Object[]{"info", "_id", "api_key", AnalyticAttribute.UUID_ATTRIBUTE, "created_time", "created_time", "opt_out", "opt_out", "0", "1", "push_disabled", "push_disabled", "0", "1", "sender_id", da.GCM_REG_ID_KEY, "registration_version", "customer_id", "user_type", "fb_attribution", "play_attribution", "first_android_id", "first_advertising_id", "package_name", AppInfo.APP_VERSION_KEY_HEADER, "current_session_uuid", "last_session_open_time", "last_session_open_time", "last_session_close_time", "last_session_close_time", "next_session_number", "next_session_number", "next_header_number", "next_header_number", "queued_close_session_blob", "queued_close_session_blob_upload_format"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            if (oldDB != null) {
                synchronized (oldDB) {
                    ContentValues contentValues = new ContentValues();
                    long j = 1;
                    long j2 = 1;
                    Cursor query;
                    try {
                        SQLiteDatabase sQLiteDatabase2 = oldDB;
                        format = "api_keys";
                        query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, null);
                        try {
                            boolean z;
                            long j3;
                            String string3;
                            String string4;
                            if (query.moveToFirst()) {
                                z = query.getInt(query.getColumnIndexOrThrow("opt_out")) == Integer.valueOf("1").intValue();
                                j3 = query.getLong(query.getColumnIndexOrThrow("created_time"));
                                string3 = query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE));
                                string4 = query.getString(query.getColumnIndexOrThrow("api_key"));
                            } else {
                                string3 = null;
                                string4 = null;
                                j3 = 0;
                                z = false;
                            }
                            if (query != null) {
                                query.close();
                                cursor = null;
                            } else {
                                cursor = query;
                            }
                            try {
                                sQLiteDatabase2 = oldDB;
                                format = "identifiers";
                                query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, null);
                                String str3 = null;
                                while (query.moveToNext()) {
                                    string = query.getString(query.getColumnIndexOrThrow("key"));
                                    string2 = query.getString(query.getColumnIndexOrThrow("value"));
                                    if (string.equals("customer_id")) {
                                        str = string2;
                                    } else {
                                        str = str3;
                                    }
                                    contentValues.put("key", string);
                                    contentValues.put("value", string2);
                                    string = "identifiers";
                                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                                        try {
                                            SQLiteInstrumentation.insert(sQLiteDatabase, string, null, contentValues);
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    } else {
                                        sQLiteDatabase.insert(string, null, contentValues);
                                    }
                                    contentValues.clear();
                                    str3 = str;
                                }
                                if (query != null) {
                                    query.close();
                                    cursor = null;
                                } else {
                                    cursor = query;
                                }
                                try {
                                    long j4;
                                    long j5;
                                    String str4;
                                    String str5;
                                    String str6;
                                    sQLiteDatabase2 = oldDB;
                                    format = "custom_dimensions";
                                    query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, null);
                                    while (query.moveToNext()) {
                                        contentValues.put("custom_dimension_key", query.getString(query.getColumnIndexOrThrow("custom_dimension_key")).replace("com.localytics.android:", Trace.NULL));
                                        contentValues.put("custom_dimension_value", query.getString(query.getColumnIndexOrThrow("custom_dimension_value")));
                                        str = "custom_dimensions";
                                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                                            try {
                                                SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                            } catch (Throwable th3) {
                                                th = th3;
                                            }
                                        } else {
                                            sQLiteDatabase.insert(str, null, contentValues);
                                        }
                                        contentValues.clear();
                                    }
                                    if (query != null) {
                                        query.close();
                                        cursor2 = null;
                                    } else {
                                        cursor2 = query;
                                    }
                                    if (string4 != null) {
                                        MigrationDatabaseHelper.preUploadBuildBlobs(oldDB);
                                        JSONObject jSONObject3 = null;
                                        string2 = null;
                                        format = null;
                                        String str7 = null;
                                        long j6 = 1;
                                        j4 = 0;
                                        long j7 = 1;
                                        while (true) {
                                            List<JSONObject> convertDatabaseToJson = MigrationDatabaseHelper.convertDatabaseToJson(this.mLocalyticsDao.getAppContext(), oldDB, string4);
                                            if (convertDatabaseToJson.isEmpty()) {
                                                break;
                                            }
                                            StringBuilder stringBuilder = new StringBuilder();
                                            long j8 = j7;
                                            j7 = j6;
                                            j6 = j4;
                                            string = string2;
                                            JSONObject jSONObject4 = jSONObject3;
                                            String str8 = str7;
                                            str7 = format;
                                            for (JSONObject jSONObject5 : convertDatabaseToJson) {
                                                long j9;
                                                try {
                                                    if (jSONObject5.getString("dt").equals("h")) {
                                                        if (jSONObject4 == null) {
                                                            j5 = jSONObject5.getLong("seq");
                                                        } else {
                                                            j5 = j8;
                                                        }
                                                        format = string;
                                                        str = str7;
                                                        string = str8;
                                                        j9 = j6;
                                                        j6 = j7;
                                                        j7 = j5;
                                                    } else {
                                                        j5 = 1 + j8;
                                                        try {
                                                            jSONObject4.put("seq", j8);
                                                            jSONObject4.put("u", UUID.randomUUID().toString());
                                                            if (jSONObject4 instanceof JSONObject) {
                                                                format = JSONObjectInstrumentation.toString(jSONObject4);
                                                            } else {
                                                                format = jSONObject4.toString();
                                                            }
                                                            StringBuilder append = stringBuilder.append(format).append("\n");
                                                            if (jSONObject5 instanceof JSONObject) {
                                                                format = JSONObjectInstrumentation.toString(jSONObject5);
                                                            } else {
                                                                format = jSONObject5.toString();
                                                            }
                                                            append.append(format);
                                                            if (jSONObject5.getString("dt").equals("c")) {
                                                                String stringBuilder2 = stringBuilder.toString();
                                                                j8 = ((Long) jSONObject5.get("ct")).longValue() * 1000;
                                                                if (str8 != null) {
                                                                    ContentValues contentValues2 = new ContentValues();
                                                                    contentValues2.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                                                                    if (j8 > j6) {
                                                                        contentValues2.put("blob", str8);
                                                                        String str9 = "events";
                                                                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                                            SQLiteInstrumentation.insert(sQLiteDatabase, str9, null, contentValues2);
                                                                        } else {
                                                                            sQLiteDatabase.insert(str9, null, contentValues2);
                                                                        }
                                                                        j6 = j8;
                                                                        str8 = stringBuilder2;
                                                                    } else {
                                                                        contentValues2.put("blob", stringBuilder2);
                                                                        str4 = "events";
                                                                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                                            SQLiteInstrumentation.insert(sQLiteDatabase, str4, null, contentValues2);
                                                                        } else {
                                                                            sQLiteDatabase.insert(str4, null, contentValues2);
                                                                        }
                                                                    }
                                                                } else {
                                                                    j6 = j8;
                                                                    str8 = stringBuilder2;
                                                                }
                                                                if (jSONObject5.has("fl")) {
                                                                    string = jSONObject5.getJSONArray("fl").join(",");
                                                                }
                                                                string2 = string;
                                                                format = str7;
                                                                string = str8;
                                                                j9 = j6;
                                                                j6 = j7;
                                                            } else {
                                                                if (jSONObject5.getString("dt").equals("s")) {
                                                                    j7 = jSONObject5.getLong("nth");
                                                                    str7 = jSONObject5.getString("u");
                                                                }
                                                                contentValues.put("blob", stringBuilder.toString());
                                                                contentValues.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                                                                format = "events";
                                                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                                    SQLiteInstrumentation.insert(sQLiteDatabase, format, null, contentValues);
                                                                } else {
                                                                    sQLiteDatabase.insert(format, null, contentValues);
                                                                }
                                                                string2 = string;
                                                                format = str7;
                                                                string = str8;
                                                                j9 = j6;
                                                                j6 = j7;
                                                            }
                                                            try {
                                                                contentValues.clear();
                                                                stringBuilder.delete(0, stringBuilder.length());
                                                                j7 = j5;
                                                                str2 = string2;
                                                                jSONObject5 = jSONObject4;
                                                                str = format;
                                                                format = str2;
                                                            } catch (Exception e) {
                                                                j7 = j5;
                                                                str2 = format;
                                                                format = string2;
                                                                string2 = str2;
                                                                jSONObject2 = jSONObject4;
                                                                str = string2;
                                                                jSONObject5 = jSONObject2;
                                                                j8 = j7;
                                                                j7 = j6;
                                                                j6 = j9;
                                                                str7 = str;
                                                                str8 = string;
                                                                string = format;
                                                                jSONObject4 = jSONObject5;
                                                            }
                                                        } catch (Exception e2) {
                                                            format = string;
                                                            string2 = str7;
                                                            string = str8;
                                                            j9 = j6;
                                                            j6 = j7;
                                                            j7 = j5;
                                                            jSONObject2 = jSONObject4;
                                                            str = string2;
                                                            jSONObject5 = jSONObject2;
                                                            j8 = j7;
                                                            j7 = j6;
                                                            j6 = j9;
                                                            str7 = str;
                                                            str8 = string;
                                                            string = format;
                                                            jSONObject4 = jSONObject5;
                                                        }
                                                    }
                                                } catch (Exception e3) {
                                                    format = string;
                                                    string2 = str7;
                                                    string = str8;
                                                    j9 = j6;
                                                    j6 = j7;
                                                    j7 = j8;
                                                    jSONObject2 = jSONObject4;
                                                    str = string2;
                                                    jSONObject5 = jSONObject2;
                                                    j8 = j7;
                                                    j7 = j6;
                                                    j6 = j9;
                                                    str7 = str;
                                                    str8 = string;
                                                    string = format;
                                                    jSONObject4 = jSONObject5;
                                                }
                                                j8 = j7;
                                                j7 = j6;
                                                j6 = j9;
                                                str7 = str;
                                                str8 = string;
                                                string = format;
                                                jSONObject4 = jSONObject5;
                                            }
                                            string2 = string;
                                            format = str7;
                                            str7 = str8;
                                            jSONObject3 = jSONObject4;
                                            j4 = j6;
                                            j6 = j7;
                                            j7 = j8;
                                        }
                                        j5 = j4;
                                        str4 = str7;
                                        j2 = j6;
                                        j = j7;
                                        str5 = string2;
                                        str6 = format;
                                    } else {
                                        str5 = null;
                                        str6 = null;
                                        str4 = null;
                                        j5 = 0;
                                    }
                                    try {
                                        sQLiteDatabase2 = oldDB;
                                        format = "info";
                                        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                                            query = SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, null);
                                        } else {
                                            query = sQLiteDatabase2.query(format, null, null, null, null, null, null);
                                        }
                                        try {
                                            if (query.moveToFirst()) {
                                                contentValues.put("api_key", string4);
                                                contentValues.put(AnalyticAttribute.UUID_ATTRIBUTE, string3);
                                                contentValues.put("created_time", Long.valueOf(j3));
                                                contentValues.put("opt_out", Boolean.valueOf(z));
                                                contentValues.put("push_disabled", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("push_disabled"))));
                                                contentValues.put("sender_id", query.getString(query.getColumnIndexOrThrow("sender_id")));
                                                contentValues.put(da.GCM_REG_ID_KEY, query.getString(query.getColumnIndexOrThrow(da.GCM_REG_ID_KEY)));
                                                contentValues.put("registration_version", query.getString(query.getColumnIndexOrThrow("registration_version")));
                                                if (str3 != null) {
                                                    contentValues.put("customer_id", str3);
                                                    contentValues.put("user_type", "known");
                                                } else {
                                                    contentValues.put("customer_id", string3);
                                                    contentValues.put("user_type", "anonymous");
                                                }
                                                contentValues.put("fb_attribution", query.getString(query.getColumnIndexOrThrow("fb_attribution")));
                                                contentValues.put("play_attribution", query.getString(query.getColumnIndexOrThrow("play_attribution")));
                                                contentValues.put("first_android_id", query.getString(query.getColumnIndexOrThrow("first_android_id")));
                                                contentValues.put("first_advertising_id", query.getString(query.getColumnIndexOrThrow("first_advertising_id")));
                                                contentValues.put(AppInfo.APP_VERSION_KEY_HEADER, DatapointHelper.getAppVersion(this.mLocalyticsDao.getAppContext()));
                                                contentValues.put("package_name", query.getString(query.getColumnIndexOrThrow("package_name")));
                                                contentValues.put("current_session_uuid", str6);
                                                j4 = query.getLong(query.getColumnIndexOrThrow("last_session_open_time"));
                                                contentValues.put("last_session_open_time", Long.valueOf(j4));
                                                contentValues.put("last_session_close_time", Long.valueOf(j5));
                                                contentValues.put("next_header_number", Long.valueOf(1 + j));
                                                contentValues.put("next_session_number", Long.valueOf(1 + j2));
                                                if (j5 > j4) {
                                                    contentValues.put("queued_close_session_blob", str4);
                                                    contentValues.put("queued_close_session_blob_upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                                                    if (str5 != null) {
                                                        this.mLocalyticsDao.setScreenFlow(Arrays.asList(str5.split(",")));
                                                    }
                                                } else if (str4 != null) {
                                                    ContentValues contentValues3 = new ContentValues();
                                                    contentValues3.put("blob", str4);
                                                    contentValues3.put("upload_format", Integer.valueOf(UploadFormat.V2.getValue()));
                                                    string = "events";
                                                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                        SQLiteInstrumentation.insert(sQLiteDatabase, string, null, contentValues3);
                                                    } else {
                                                        sQLiteDatabase.insert(string, null, contentValues3);
                                                    }
                                                }
                                                string2 = "info";
                                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                    SQLiteInstrumentation.insert(sQLiteDatabase, string2, null, contentValues);
                                                } else {
                                                    sQLiteDatabase.insert(string2, null, contentValues);
                                                }
                                                contentValues.clear();
                                            }
                                            if (query != null) {
                                                query.close();
                                            }
                                            LocalyticsDatabaseHelper.cleanUpOldDB();
                                        } catch (Throwable th4) {
                                            th = th4;
                                            if (query != null) {
                                                query.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        query = cursor2;
                                        if (query != null) {
                                            query.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    query = cursor;
                                    if (query != null) {
                                        query.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                query = cursor;
                                if (query != null) {
                                    query.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        query = null;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < 1) {
                migrateV2ToV3(sQLiteDatabase);
            }
            if (i < AnalyticsProvider.DATABASE_VERSION) {
                addFirstOpenEventToInfoTable(sQLiteDatabase);
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            Log.m12799v(String.format("SQLite library version is: %s", new Object[]{DatabaseUtils.stringForQuery(sQLiteDatabase, "select sqlite_version()", null)}));
            if (!sQLiteDatabase.isReadOnly()) {
                String str = "PRAGMA foreign_keys = ON;";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
                } else {
                    sQLiteDatabase.execSQL(str);
                }
            }
        }

        protected void addFirstOpenEventToInfoTable(SQLiteDatabase sQLiteDatabase) {
            Object[] objArr = new Object[AnalyticsProvider.DATABASE_VERSION];
            objArr[0] = "info";
            objArr[1] = "first_open_event_blob";
            String format = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", objArr);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
        }
    }

    static final class CustomDimensionsV3Columns implements BaseColumns {
        static final String CUSTOM_DIMENSION_KEY = "custom_dimension_key";
        static final String CUSTOM_DIMENSION_VALUE = "custom_dimension_value";
        static final String TABLE_NAME = "custom_dimensions";

        private CustomDimensionsV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class EventsV3Columns implements BaseColumns {
        static final String BLOB = "blob";
        static final String TABLE_NAME = "events";
        static final String UPLOAD_FORMAT = "upload_format";

        enum UploadFormat {
            V2(AnalyticsProvider.DATABASE_VERSION),
            V3(3);
            
            private final int value;

            private UploadFormat(int i) {
                this.value = i;
            }

            public int getValue() {
                return this.value;
            }
        }

        private EventsV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class IdentifiersV3Columns implements BaseColumns {
        static final String KEY = "key";
        static final String TABLE_NAME = "identifiers";
        static final String VALUE = "value";

        IdentifiersV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class InfoV3Columns implements BaseColumns {
        static final String API_KEY = "api_key";
        static final String APP_VERSION = "app_version";
        static final String CREATED_TIME = "created_time";
        static final String CURRENT_SESSION_UUID = "current_session_uuid";
        static final String CUSTOMER_ID = "customer_id";
        static final String FB_ATTRIBUTION = "fb_attribution";
        static final String FIRST_ADVERTISING_ID = "first_advertising_id";
        static final String FIRST_ANDROID_ID = "first_android_id";
        static final String FIRST_OPEN_EVENT_BLOB = "first_open_event_blob";
        static final String LAST_SESSION_CLOSE_TIME = "last_session_close_time";
        static final String LAST_SESSION_OPEN_TIME = "last_session_open_time";
        static final String NEXT_HEADER_NUMBER = "next_header_number";
        static final String NEXT_SESSION_NUMBER = "next_session_number";
        static final String OPT_OUT = "opt_out";
        static final String PACKAGE_NAME = "package_name";
        static final String PLAY_ATTRIBUTION = "play_attribution";
        static final String PUSH_DISABLED = "push_disabled";
        static final String QUEUED_CLOSE_SESSION_BLOB = "queued_close_session_blob";
        static final String QUEUED_CLOSE_SESSION_BLOB_UPLOAD_FORMAT = "queued_close_session_blob_upload_format";
        static final String REGISTRATION_ID = "registration_id";
        static final String REGISTRATION_VERSION = "registration_version";
        static final String SENDER_ID = "sender_id";
        static final String TABLE_NAME = "info";
        static final String USER_TYPE = "user_type";
        static final String UUID = "uuid";

        private InfoV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    AnalyticsProvider(String str, LocalyticsDao localyticsDao) {
        super(localyticsDao);
        Object[] objArr = new Object[DATABASE_VERSION];
        objArr[0] = DatapointHelper.getSha256_buggy(localyticsDao.getApiKey());
        objArr[1] = str;
        this.mDb = new AnalyticsDatabaseHelper(String.format("com.localytics.android.%s.%s.sqlite", objArr), DATABASE_VERSION, localyticsDao).getWritableDatabase();
    }

    AnalyticsProvider(LocalyticsDao localyticsDao) {
        super(localyticsDao);
    }

    long maxSiloDbSize() {
        return Constants.dbMaxSizeForAnalytics;
    }

    boolean canAddToDB() {
        return new File(this.mDb.getPath()).length() < maxSiloDbSize();
    }
}
