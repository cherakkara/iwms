package com.localytics.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.olacabs.customer.p076d.AppInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MarketingProvider extends BaseProvider {
    static final int DATABASE_VERSION = 3;

    static final class CamapignsDisplayedV3Columns implements BaseColumns {
        static final String CAMPAIGN_ID = "campaign_id";
        static final String DATE = "date";
        static final String IGNORE_GLOBAL = "ignore_global";
        static final String TABLE_NAME = "campaigns_displayed";

        private CamapignsDisplayedV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class FrequencyCappingBlackoutDateV3Columns implements BaseColumns {
        static final String END_DATE = "end";
        static final String FREQUENCY_ID = "frequency_id";
        static final String RULE_GROUP_ID = "rule_group_id";
        static final String START_DATE = "start";
        static final String TABLE_NAME = "frequency_capping_blackout_dates";

        private FrequencyCappingBlackoutDateV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class FrequencyCappingBlackoutTimeV3Columns implements BaseColumns {
        static final String END_TIME = "end";
        static final String FREQUENCY_ID = "frequency_id";
        static final String RULE_GROUP_ID = "rule_group_id";
        static final String START_TIME = "start";
        static final String TABLE_NAME = "frequency_capping_blackout_times";

        private FrequencyCappingBlackoutTimeV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class FrequencyCappingBlackoutWeekdayV3Columns implements BaseColumns {
        static final String DAY = "day";
        static final String FREQUENCY_ID = "frequency_id";
        static final String RULE_GROUP_ID = "rule_group_id";
        static final String TABLE_NAME = "frequency_capping_blackout_weekdays";

        private FrequencyCappingBlackoutWeekdayV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class FrequencyCappingDisplayFrequencyV3Columns implements BaseColumns {
        static final String DISPLAY_FREQUENCY_COUNT = "count";
        static final String DISPLAY_FREQUENCY_DAYS = "days";
        static final String FREQUENCY_ID = "frequency_id";
        static final String TABLE_NAME = "frequency_capping_display_frequencies";

        private FrequencyCappingDisplayFrequencyV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class FrequencyCappingV3Columns implements BaseColumns {
        static final String CAMPAIGN_ID = "campaign_id";
        static final String IGNORE_GLOBAL = "ignore_global";
        static final String MAX_DISPLAY_COUNT = "max_display_count";
        static final String TABLE_NAME = "frequency_capping_rules";

        private FrequencyCappingV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class MarketingConditionValuesV3Columns implements BaseColumns {
        static final String CONDITION_ID_REF = "condition_id_ref";
        static final String TABLE_NAME = "marketing_condition_values";
        static final String VALUE = "value";

        private MarketingConditionValuesV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class MarketingConditionsV3Columns implements BaseColumns {
        static final String ATTRIBUTE_NAME = "attribute_name";
        static final String OPERATOR = "operator";
        static final String RULE_ID_REF = "rule_id_ref";
        static final String TABLE_NAME = "marketing_conditions";

        private MarketingConditionsV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static class MarketingDatabaseHelper extends LocalyticsDatabaseHelper {
        MarketingDatabaseHelper(String str, int i, LocalyticsDao localyticsDao) {
            super(str, i, localyticsDao);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (sQLiteDatabase == null) {
                throw new IllegalArgumentException("db cannot be null");
            }
            onUpgrade(sQLiteDatabase, 0, MarketingProvider.DATABASE_VERSION);
        }

        protected void migrateV2ToV3(SQLiteDatabase sQLiteDatabase) {
            Throwable th;
            Cursor cursor;
            Cursor cursor2 = null;
            String format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER, %s INTEGER, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER, %s INTEGER NOT NULL, %s TEXT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", new Object[]{"marketing_rules", "_id", "campaign_id", "expiration", "display_seconds", "display_session", AppInfo.APP_VERSION_KEY, "phone_location", "phone_size_width", "phone_size_height", "tablet_location", "tablet_size_width", "tablet_size_height", "time_to_display", "internet_required", "ab_test", "rule_name", "location", "devices"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"marketing_ruleevent", "_id", "event_name", "rule_id_ref", "marketing_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY);", new Object[]{"marketing_displayed", "campaign_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"marketing_conditions", "_id", "attribute_name", "operator", "rule_id_ref", "marketing_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"marketing_condition_values", "_id", "value", "condition_id_ref", "marketing_conditions", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            if (oldDB != null) {
                synchronized (oldDB) {
                    ContentValues contentValues = new ContentValues();
                    Cursor query;
                    try {
                        String str;
                        SQLiteDatabase sQLiteDatabase2 = oldDB;
                        format = "amp_rules";
                        String str2 = "_id ASC";
                        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                            query = SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str2);
                        } else {
                            query = sQLiteDatabase2.query(format, null, null, null, null, null, str2);
                        }
                        while (query.moveToNext()) {
                            try {
                                contentValues.put("_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("_id"))));
                                contentValues.put("campaign_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))));
                                contentValues.put("expiration", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("expiration"))));
                                contentValues.put("display_seconds", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_seconds"))));
                                contentValues.put("display_session", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("display_session"))));
                                contentValues.put(AppInfo.APP_VERSION_KEY, query.getString(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY)));
                                contentValues.put("phone_location", query.getString(query.getColumnIndexOrThrow("phone_location")));
                                contentValues.put("phone_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_width"))));
                                contentValues.put("phone_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("phone_size_height"))));
                                contentValues.put("tablet_location", query.getString(query.getColumnIndexOrThrow("tablet_location")));
                                contentValues.put("tablet_size_width", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_width"))));
                                contentValues.put("tablet_size_height", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("tablet_size_height"))));
                                contentValues.put("time_to_display", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("time_to_display"))));
                                contentValues.put("internet_required", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("internet_required"))));
                                contentValues.put("ab_test", query.getString(query.getColumnIndexOrThrow("ab_test")));
                                contentValues.put("rule_name", query.getString(query.getColumnIndexOrThrow("rule_name")));
                                contentValues.put("location", query.getString(query.getColumnIndexOrThrow("location")));
                                contentValues.put("devices", query.getString(query.getColumnIndexOrThrow("devices")));
                                str = "marketing_rules";
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                } else {
                                    sQLiteDatabase.insert(str, null, contentValues);
                                }
                                contentValues.clear();
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                        if (query != null) {
                            query.close();
                            cursor = null;
                        } else {
                            cursor = query;
                        }
                        try {
                            sQLiteDatabase2 = oldDB;
                            format = "amp_ruleevent";
                            str2 = "_id ASC";
                            query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, str2) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str2);
                            while (query.moveToNext()) {
                                try {
                                    contentValues.put("_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("_id"))));
                                    contentValues.put("event_name", query.getString(query.getColumnIndexOrThrow("event_name")));
                                    contentValues.put("rule_id_ref", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("rule_id_ref"))));
                                    str = "marketing_ruleevent";
                                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                                        SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                    } else {
                                        sQLiteDatabase.insert(str, null, contentValues);
                                    }
                                    contentValues.clear();
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                            if (query != null) {
                                query.close();
                                cursor = null;
                            } else {
                                cursor = query;
                            }
                            try {
                                sQLiteDatabase2 = oldDB;
                                format = "amp_displayed";
                                str2 = "_id ASC";
                                query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, str2) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str2);
                                while (query.moveToNext()) {
                                    if (query.getInt(query.getColumnIndexOrThrow("displayed")) == 1) {
                                        contentValues.put("campaign_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))));
                                        str = "marketing_displayed";
                                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                                            try {
                                                SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                            } catch (Throwable th4) {
                                                th = th4;
                                            }
                                        } else {
                                            sQLiteDatabase.insert(str, null, contentValues);
                                        }
                                        contentValues.clear();
                                    }
                                }
                                if (query != null) {
                                    query.close();
                                    cursor = null;
                                } else {
                                    cursor = query;
                                }
                                try {
                                    sQLiteDatabase2 = oldDB;
                                    format = "amp_conditions";
                                    str2 = "_id ASC";
                                    query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, str2) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str2);
                                    while (query.moveToNext()) {
                                        contentValues.put("_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("_id"))));
                                        contentValues.put("attribute_name", query.getString(query.getColumnIndexOrThrow("attribute_name")));
                                        contentValues.put("operator", query.getString(query.getColumnIndexOrThrow("operator")));
                                        contentValues.put("rule_id_ref", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("rule_id_ref"))));
                                        str = "marketing_conditions";
                                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                                            try {
                                                SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                            } catch (Throwable th5) {
                                                th = th5;
                                            }
                                        } else {
                                            sQLiteDatabase.insert(str, null, contentValues);
                                        }
                                        contentValues.clear();
                                    }
                                    if (query != null) {
                                        query.close();
                                    } else {
                                        cursor2 = query;
                                    }
                                    try {
                                        sQLiteDatabase2 = oldDB;
                                        format = "amp_condition_values";
                                        str2 = "_id ASC";
                                        query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query(format, null, null, null, null, null, str2) : SQLiteInstrumentation.query(sQLiteDatabase2, format, null, null, null, null, null, str2);
                                        while (query.moveToNext()) {
                                            contentValues.put("_id", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("_id"))));
                                            contentValues.put("value", query.getString(query.getColumnIndexOrThrow("value")));
                                            contentValues.put("condition_id_ref", Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("condition_id_ref"))));
                                            str = "marketing_condition_values";
                                            if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                try {
                                                    SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues);
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                }
                                            } else {
                                                sQLiteDatabase.insert(str, null, contentValues);
                                            }
                                            contentValues.clear();
                                        }
                                        if (query != null) {
                                            query.close();
                                        }
                                        LocalyticsDatabaseHelper.cleanUpOldDB();
                                    } catch (Throwable th7) {
                                        th = th7;
                                        query = cursor2;
                                        if (query != null) {
                                            query.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                    query = cursor;
                                    if (query != null) {
                                        query.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                query = cursor;
                                if (query != null) {
                                    query.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            query = cursor;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th11) {
                        th = th11;
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
            if (i < 2) {
                addNonUniqueRuleName(sQLiteDatabase);
            }
            if (i < MarketingProvider.DATABASE_VERSION) {
                setUpFrequencyCappingTables(sQLiteDatabase);
                addControlGroup(sQLiteDatabase);
                addSchemaVersion(sQLiteDatabase);
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

        protected void addNonUniqueRuleName(SQLiteDatabase sQLiteDatabase) {
            String format = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"marketing_rules", "rule_name_non_unique"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            Object[] objArr = new Object[MarketingProvider.DATABASE_VERSION];
            objArr[0] = "marketing_rules";
            objArr[1] = "rule_name_non_unique";
            objArr[2] = "rule_name";
            String format2 = String.format("UPDATE %s SET %s = %s;", objArr);
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format2);
            } else {
                sQLiteDatabase.execSQL(format2);
            }
        }

        protected void addControlGroup(SQLiteDatabase sQLiteDatabase) {
            String format = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER DEFAULT 0;", new Object[]{"marketing_rules", "control_group"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
        }

        protected void addSchemaVersion(SQLiteDatabase sQLiteDatabase) {
            String format = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER DEFAULT 1;", new Object[]{"marketing_rules", "schema_version"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
        }

        protected void setUpFrequencyCappingTables(SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            Throwable th;
            String format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER UNIQUE, %s INTEGER, %s INTEGER);", new Object[]{"frequency_capping_rules", "_id", "campaign_id", "max_display_count", "ignore_global"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE);", new Object[]{"frequency_capping_display_frequencies", "_id", "frequency_id", "count", "days", "frequency_id", "frequency_capping_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE);", new Object[]{"frequency_capping_blackout_dates", "frequency_id", "rule_group_id", "start", "end", "frequency_id", "frequency_capping_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE);", new Object[]{"frequency_capping_blackout_weekdays", "frequency_id", "rule_group_id", "day", "frequency_id", "frequency_capping_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            format = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE);", new Object[]{"frequency_capping_blackout_times", "frequency_id", "rule_group_id", "start", "end", "frequency_id", "frequency_capping_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
            } else {
                sQLiteDatabase.execSQL(format);
            }
            List<Integer> arrayList = new ArrayList();
            try {
                Cursor query;
                format = "marketing_displayed";
                String[] strArr = new String[]{"campaign_id"};
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    query = SQLiteInstrumentation.query(sQLiteDatabase, format, strArr, null, null, null, null, null);
                } else {
                    query = sQLiteDatabase.query(format, strArr, null, null, null, null, null);
                }
                while (query.moveToNext()) {
                    try {
                        arrayList.add(Integer.valueOf(query.getInt(query.getColumnIndexOrThrow("campaign_id"))));
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        cursor = query;
                        th = th3;
                    }
                }
                if (query != null) {
                    query.close();
                }
                format = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL);", new Object[]{"campaigns_displayed", "campaign_id", "date", "ignore_global"});
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
                } else {
                    sQLiteDatabase.execSQL(format);
                }
                for (Integer num : arrayList) {
                    String format2 = String.format("INSERT INTO %s VALUES (?, datetime(0,'unixepoch'), ?);", new Object[]{"campaigns_displayed"});
                    Integer[] numArr = new Integer[]{num, Integer.valueOf(1)};
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.execSQL(sQLiteDatabase, format2, numArr);
                    } else {
                        sQLiteDatabase.execSQL(format2, numArr);
                    }
                }
                format = String.format("DROP TABLE %s", new Object[]{"marketing_displayed"});
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, format);
                } else {
                    sQLiteDatabase.execSQL(format);
                }
                String format3 = String.format("DELETE FROM %s", new Object[]{"marketing_rules"});
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.execSQL(sQLiteDatabase, format3);
                } else {
                    sQLiteDatabase.execSQL(format3);
                }
                BaseProvider.deleteDirectory(new File(MarketingDownloader.getMarketingDataDirectory(this.mLocalyticsDao.getAppContext(), this.mLocalyticsDao.getApiKey())));
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    static final class MarketingDisplayedV3Columns implements BaseColumns {
        static final String CAMPAIGN_ID = "campaign_id";
        static final String TABLE_NAME = "marketing_displayed";

        private MarketingDisplayedV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class MarketingRuleEventV3Columns implements BaseColumns {
        static final String EVENT_NAME = "event_name";
        static final String RULE_ID_REF = "rule_id_ref";
        static final String TABLE_NAME = "marketing_ruleevent";

        private MarketingRuleEventV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class MarketingRulesV3Columns implements BaseColumns {
        static final String AB_TEST = "ab_test";
        static final String CAMPAIGN_ID = "campaign_id";
        static final String CONTROL_GROUP = "control_group";
        static final String DEVICES = "devices";
        static final String DISPLAY_SECONDS = "display_seconds";
        static final String DISPLAY_SESSION = "display_session";
        static final String EXPIRATION = "expiration";
        static final String INTERNET_REQUIRED = "internet_required";
        static final String LOCATION = "location";
        static final String PHONE_LOCATION = "phone_location";
        static final String PHONE_SIZE_HEIGHT = "phone_size_height";
        static final String PHONE_SIZE_WIDTH = "phone_size_width";
        static final String RULE_NAME = "rule_name_non_unique";
        static final String RULE_NAME_UNIQUE = "rule_name";
        static final String SCHEMA_VERSION = "schema_version";
        static final String TABLET_LOCATION = "tablet_location";
        static final String TABLET_SIZE_HEIGHT = "tablet_size_height";
        static final String TABLET_SIZE_WIDTH = "tablet_size_width";
        static final String TABLE_NAME = "marketing_rules";
        static final String TIME_TO_DISPLAY = "time_to_display";
        static final String VERSION = "version";

        private MarketingRulesV3Columns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    MarketingProvider(String str, LocalyticsDao localyticsDao) {
        super(localyticsDao);
        this.mDb = new MarketingDatabaseHelper(String.format("com.localytics.android.%s.%s.sqlite", new Object[]{DatapointHelper.getSha256_buggy(localyticsDao.getApiKey()), str}), DATABASE_VERSION, localyticsDao).getWritableDatabase();
    }

    MarketingProvider(LocalyticsDao localyticsDao) {
        super(localyticsDao);
    }

    long maxSiloDbSize() {
        return Long.MAX_VALUE;
    }

    boolean canAddToDB() {
        return true;
    }
}
