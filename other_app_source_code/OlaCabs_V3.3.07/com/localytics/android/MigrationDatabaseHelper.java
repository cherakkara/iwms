package com.localytics.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.CursorJoiner.Result;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class MigrationDatabaseHelper extends SQLiteOpenHelper {
    private static final String CLOSE_EVENT;
    private static final String EVENTS_SORT_ORDER;
    private static final String EVENT_FORMAT = "%s:%s";
    private static final String FLOW_EVENT;
    private static final String[] JOINER_ARG_UPLOAD_EVENTS_COLUMNS;
    private static final String OPEN_EVENT;
    private static final String OPT_IN_EVENT;
    private static final String OPT_OUT_EVENT;
    private static final String[] PROJECTION_UPLOAD_BLOBS;
    private static final String[] PROJECTION_UPLOAD_EVENTS;
    private static final String SELECTION_UPLOAD_NULL_BLOBS;
    private static final String UPLOAD_BLOBS_EVENTS_SORT_ORDER;
    private LocalyticsDao mLocalyticsDao;

    /* renamed from: com.localytics.android.MigrationDatabaseHelper.1 */
    static /* synthetic */ class C07171 {
        static final /* synthetic */ int[] $SwitchMap$android$database$CursorJoiner$Result;

        static {
            $SwitchMap$android$database$CursorJoiner$Result = new int[Result.values().length];
            try {
                $SwitchMap$android$database$CursorJoiner$Result[Result.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$database$CursorJoiner$Result[Result.BOTH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$database$CursorJoiner$Result[Result.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    static final class AmpConditionValuesDbColumns implements BaseColumns {
        static final String CONDITION_ID_REF = "condition_id_ref";
        static final String TABLE_NAME = "amp_condition_values";
        static final String VALUE = "value";

        private AmpConditionValuesDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class AmpConditionsDbColumns implements BaseColumns {
        static final String ATTRIBUTE_NAME = "attribute_name";
        static final String OPERATOR = "operator";
        static final String RULE_ID_REF = "rule_id_ref";
        static final String TABLE_NAME = "amp_conditions";

        private AmpConditionsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class AmpDisplayedDbColumns implements BaseColumns {
        static final String CAMPAIGN_ID = "campaign_id";
        static final String DISPLAYED = "displayed";
        static final String TABLE_NAME = "amp_displayed";

        private AmpDisplayedDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class AmpRuleEventDbColumns implements BaseColumns {
        static final String EVENT_NAME = "event_name";
        static final String RULE_ID_REF = "rule_id_ref";
        static final String TABLE_NAME = "amp_ruleevent";

        private AmpRuleEventDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class AmpRulesDbColumns implements BaseColumns {
        static final String AB_TEST = "ab_test";
        static final String CAMPAIGN_ID = "campaign_id";
        static final String DEVICES = "devices";
        static final String DISPLAY_SECONDS = "display_seconds";
        static final String DISPLAY_SESSION = "display_session";
        static final String EXPIRATION = "expiration";
        static final String INTERNET_REQUIRED = "internet_required";
        static final String LOCATION = "location";
        static final String PHONE_LOCATION = "phone_location";
        static final String PHONE_SIZE_HEIGHT = "phone_size_height";
        static final String PHONE_SIZE_WIDTH = "phone_size_width";
        static final String RULE_NAME = "rule_name";
        static final String TABLET_LOCATION = "tablet_location";
        static final String TABLET_SIZE_HEIGHT = "tablet_size_height";
        static final String TABLET_SIZE_WIDTH = "tablet_size_width";
        static final String TABLE_NAME = "amp_rules";
        static final String TIME_TO_DISPLAY = "time_to_display";
        static final String VERSION = "version";

        private AmpRulesDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class ApiKeysDbColumns implements BaseColumns {
        static final String API_KEY = "api_key";
        static final String CREATED_TIME = "created_time";
        static final String OPT_OUT = "opt_out";
        static final String TABLE_NAME = "api_keys";
        static final String UUID = "uuid";

        private ApiKeysDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class AttributesDbColumns implements BaseColumns {
        static final String ATTRIBUTE_CUSTOM_DIMENSION_1;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_10;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_2;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_3;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_4;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_5;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_6;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_7;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_8;
        static final String ATTRIBUTE_CUSTOM_DIMENSION_9;
        static final String ATTRIBUTE_FORMAT = "%s:%s";
        static final String ATTRIBUTE_KEY = "attribute_key";
        static final String ATTRIBUTE_VALUE = "attribute_value";
        static final String EVENTS_KEY_REF = "events_key_ref";
        static final String TABLE_NAME = "attributes";

        static {
            ATTRIBUTE_CUSTOM_DIMENSION_1 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_0"});
            ATTRIBUTE_CUSTOM_DIMENSION_2 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_1"});
            ATTRIBUTE_CUSTOM_DIMENSION_3 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_2"});
            ATTRIBUTE_CUSTOM_DIMENSION_4 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_3"});
            ATTRIBUTE_CUSTOM_DIMENSION_5 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_4"});
            ATTRIBUTE_CUSTOM_DIMENSION_6 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_5"});
            ATTRIBUTE_CUSTOM_DIMENSION_7 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_6"});
            ATTRIBUTE_CUSTOM_DIMENSION_8 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_7"});
            ATTRIBUTE_CUSTOM_DIMENSION_9 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_8"});
            ATTRIBUTE_CUSTOM_DIMENSION_10 = String.format(ATTRIBUTE_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_9"});
        }

        private AttributesDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class CustomDimensionsDbColumns implements BaseColumns {
        static final String CUSTOM_DIMENSION_1;
        static final String CUSTOM_DIMENSION_10;
        static final String CUSTOM_DIMENSION_2;
        static final String CUSTOM_DIMENSION_3;
        static final String CUSTOM_DIMENSION_4;
        static final String CUSTOM_DIMENSION_5;
        static final String CUSTOM_DIMENSION_6;
        static final String CUSTOM_DIMENSION_7;
        static final String CUSTOM_DIMENSION_8;
        static final String CUSTOM_DIMENSION_9;
        static final String CUSTOM_DIMENSION_FORMAT = "%s:%s";
        static final String CUSTOM_DIMENSION_KEY = "custom_dimension_key";
        static final String CUSTOM_DIMENSION_VALUE = "custom_dimension_value";
        static final String TABLE_NAME = "custom_dimensions";

        static {
            CUSTOM_DIMENSION_1 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_0"});
            CUSTOM_DIMENSION_2 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_1"});
            CUSTOM_DIMENSION_3 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_2"});
            CUSTOM_DIMENSION_4 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_3"});
            CUSTOM_DIMENSION_5 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_4"});
            CUSTOM_DIMENSION_6 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_5"});
            CUSTOM_DIMENSION_7 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_6"});
            CUSTOM_DIMENSION_8 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_7"});
            CUSTOM_DIMENSION_9 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_8"});
            CUSTOM_DIMENSION_10 = String.format(CUSTOM_DIMENSION_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "custom_dimension_9"});
        }

        private CustomDimensionsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class EventFlow {
        static final String KEY_DATA_TYPE = "dt";
        static final String KEY_EVENT_UUID = "u";
        static final String KEY_FLOW_NEW = "nw";
        static final String KEY_FLOW_OLD = "od";
        static final String KEY_SESSION_START_TIME = "ss";
        static final String VALUE_DATA_TYPE = "f";

        static final class Element {
            static final String TYPE_EVENT = "e";
            static final String TYPE_SCREEN = "s";

            private Element() {
                throw new UnsupportedOperationException("This class is non-instantiable");
            }
        }

        private EventFlow() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class EventHistoryDbColumns implements BaseColumns {
        static final String NAME = "name";
        static final String PROCESSED_IN_BLOB = "processed_in_blob";
        static final String SESSION_KEY_REF = "session_key_ref";
        static final String TABLE_NAME = "event_history";
        static final String TYPE = "type";
        static final int TYPE_EVENT = 0;
        static final int TYPE_SCREEN = 1;

        private EventHistoryDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class EventsDbColumns implements BaseColumns {
        static final String CLV_INCREASE = "clv_increase";
        static final String CUST_ID = "customer_id";
        static final String EVENT_NAME = "event_name";
        static final String IDENTIFIERS = "ids";
        static final String LAT_NAME = "event_lat";
        static final String LNG_NAME = "event_lng";
        static final String REAL_TIME = "real_time";
        static final String SESSION_KEY_REF = "session_key_ref";
        static final String TABLE_NAME = "events";
        static final String USER_TYPE = "user_type";
        static final String UUID = "uuid";
        static final String WALL_TIME = "wall_time";

        private EventsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class IdentifiersDbColumns implements BaseColumns {
        static final String KEY = "key";
        static final String TABLE_NAME = "identifiers";
        static final String VALUE = "value";

        private IdentifiersDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class InfoDbColumns implements BaseColumns {
        static final String FB_ATTRIBUTION = "fb_attribution";
        static final String FIRST_ADVERTISING_ID = "first_advertising_id";
        static final String FIRST_ANDROID_ID = "first_android_id";
        static final String FIRST_RUN = "first_run";
        static final String FIRST_TELEPHONY_ID = "first_telephony_id";
        static final String LAST_SESSION_OPEN_TIME = "last_session_open_time";
        static final String PACKAGE_NAME = "package_name";
        static final String PLAY_ATTRIBUTION = "play_attribution";
        static final String PUSH_DISABLED = "push_disabled";
        static final String REGISTRATION_ID = "registration_id";
        static final String REGISTRATION_VERSION = "registration_version";
        static final String SENDER_ID = "sender_id";
        static final String TABLE_NAME = "info";

        private InfoDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class ProfileDbColumns implements BaseColumns {
        static final String ACTION = "action";
        static final String ATTRIBUTE = "attribute";
        static final String CUSTOMER_ID = "customer_id";
        static final String TABLE_NAME = "profile";
        static final String USER_ID = "id";

        private ProfileDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class SessionsDbColumns implements BaseColumns {
        static final String ANDROID_SDK = "android_sdk";
        static final String ANDROID_VERSION = "android_version";
        static final String API_KEY_REF = "api_key_ref";
        static final String APP_VERSION = "app_version";
        static final String DEVICE_ADVERTISING_ID = "device_advertising_id";
        static final String DEVICE_ANDROID_ID = "device_android_id";
        static final String DEVICE_ANDROID_ID_HASH = "device_android_id_hash";
        static final String DEVICE_COUNTRY = "device_country";
        static final String DEVICE_MANUFACTURER = "device_manufacturer";
        static final String DEVICE_MODEL = "device_model";
        static final String DEVICE_SERIAL_NUMBER_HASH = "device_serial_number_hash";
        static final String DEVICE_TELEPHONY_ID = "device_telephony_id";
        static final String DEVICE_TELEPHONY_ID_HASH = "device_telephony_id_hash";
        static final String DEVICE_WIFI_MAC_HASH = "device_wifi_mac_hash";
        static final String ELAPSED_TIME_SINCE_LAST_SESSION = "elapsed";
        static final String LATITUDE = "latitude";
        static final String LOCALE_COUNTRY = "locale_country";
        static final String LOCALE_LANGUAGE = "locale_language";
        static final String LOCALYTICS_INSTALLATION_ID = "iu";
        static final String LOCALYTICS_LIBRARY_VERSION = "localytics_library_version";
        static final String LONGITUDE = "longitude";
        static final String NETWORK_CARRIER = "network_carrier";
        static final String NETWORK_COUNTRY = "network_country";
        static final String NETWORK_TYPE = "network_type";
        static final String SESSION_START_WALL_TIME = "session_start_wall_time";
        static final String TABLE_NAME = "sessions";
        static final String UUID = "uuid";

        private SessionsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class UploadBlobEventsDbColumns implements BaseColumns {
        static final String EVENTS_KEY_REF = "events_key_ref";
        static final String TABLE_NAME = "upload_blob_events";
        static final String UPLOAD_BLOBS_KEY_REF = "upload_blobs_key_ref";

        private UploadBlobEventsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static final class UploadBlobsDbColumns implements BaseColumns {
        static final String TABLE_NAME = "upload_blobs";
        static final String UUID = "uuid";

        private UploadBlobsDbColumns() {
            throw new UnsupportedOperationException("This class is non-instantiable");
        }
    }

    static {
        OPEN_EVENT = String.format(EVENT_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, Constants.PUSH_ACK_TYPE_OPEN});
        CLOSE_EVENT = String.format(EVENT_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "close"});
        OPT_IN_EVENT = String.format(EVENT_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "opt_in"});
        OPT_OUT_EVENT = String.format(EVENT_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "opt_out"});
        FLOW_EVENT = String.format(EVENT_FORMAT, new Object[]{BuildConfig.APPLICATION_ID, "flow"});
        EVENTS_SORT_ORDER = String.format("CAST(%s as TEXT)", new Object[]{"_id"});
        PROJECTION_UPLOAD_BLOBS = new String[]{"events_key_ref"};
        UPLOAD_BLOBS_EVENTS_SORT_ORDER = String.format("CAST(%s AS TEXT)", new Object[]{"events_key_ref"});
        JOINER_ARG_UPLOAD_EVENTS_COLUMNS = new String[]{"_id"};
        SELECTION_UPLOAD_NULL_BLOBS = String.format("%s IS NULL", new Object[]{"processed_in_blob"});
        PROJECTION_UPLOAD_EVENTS = new String[]{"_id", "event_name", "wall_time"};
    }

    MigrationDatabaseHelper(String str, int i, LocalyticsDao localyticsDao) {
        super(localyticsDao.getAppContext(), str, null, i);
        this.mLocalyticsDao = localyticsDao;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            throw new IllegalArgumentException("db cannot be null");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str;
        Cursor query;
        Throwable th;
        ContentValues contentValues;
        String str2;
        if (i < 3) {
            str = "upload_blob_events";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
            str = "event_history";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
            str = "upload_blobs";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
            str = "attributes";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
            str = "events";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
            str = "sessions";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.delete(sQLiteDatabase, str, null, null);
            } else {
                sQLiteDatabase.delete(str, null, null);
            }
        }
        if (i < 4) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"sessions", "iu"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 5) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"sessions", "device_wifi_mac_hash"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 6) {
            try {
                str = "attributes";
                String[] strArr = new String[]{"_id", "attribute_key"};
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, null, null, null, null, null);
                } else {
                    query = sQLiteDatabase.query(str, strArr, null, null, null, null, null);
                }
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_id");
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow("attribute_key");
                    ContentValues contentValues2 = new ContentValues();
                    String format = String.format("%s = ?", new Object[]{"_id"});
                    String[] strArr2 = new String[1];
                    query.moveToPosition(-1);
                    while (query.moveToNext()) {
                        contentValues2.put("attribute_key", String.format(EVENT_FORMAT, new Object[]{this.mLocalyticsDao.getAppContext().getPackageName(), query.getString(columnIndexOrThrow2)}));
                        strArr2[0] = Long.toString(query.getLong(columnIndexOrThrow));
                        String str3 = "attributes";
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update(sQLiteDatabase, str3, contentValues2, format, strArr2);
                        } else {
                            sQLiteDatabase.update(str3, contentValues2, format, strArr2);
                        }
                        contentValues2.clear();
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        if (i < 7) {
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s TEXT, %s INTEGER);", new Object[]{"info", "fb_attribution", "first_run"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            contentValues = new ContentValues();
            contentValues.putNull("fb_attribution");
            contentValues.put("first_run", Boolean.FALSE);
            str2 = "info";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.insertOrThrow(sQLiteDatabase, str2, null, contentValues);
            } else {
                sQLiteDatabase.insertOrThrow(str2, null, contentValues);
            }
        }
        if (i < 8) {
            str = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL);", new Object[]{"identifiers", "_id", "key", "value"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 9) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER NOT NULL DEFAULT 0;", new Object[]{"events", "clv_increase"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 10) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "play_attribution"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 11) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", da.GCM_REG_ID_KEY});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "registration_version"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 12) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "first_android_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "first_telephony_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "package_name"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            contentValues = new ContentValues();
            contentValues.put("first_android_id", DatapointHelper.getAndroidIdOrNull(this.mLocalyticsDao.getAppContext()));
            contentValues.put("first_telephony_id", DatapointHelper.getTelephonyDeviceIdOrNull(this.mLocalyticsDao.getAppContext()));
            contentValues.put("package_name", this.mLocalyticsDao.getAppContext().getPackageName());
            str2 = "info";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.update(sQLiteDatabase, str2, contentValues, null, null);
            } else {
                sQLiteDatabase.update(str2, contentValues, null, null);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"sessions", "device_android_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 13) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s REAL;", new Object[]{"events", "event_lat"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s REAL;", new Object[]{"events", "event_lng"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 14) {
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER, %s INTEGER, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER, %s INTEGER NOT NULL, %s TEXT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)", new Object[]{"amp_rules", "_id", "campaign_id", "expiration", "display_seconds", "display_session", AppInfo.APP_VERSION_KEY, "phone_location", "phone_size_width", "phone_size_height", "tablet_location", "tablet_size_width", "tablet_size_height", "time_to_display", "internet_required", "ab_test", "rule_name", "location", "devices"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"amp_ruleevent", "_id", "event_name", "rule_id_ref", "amp_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL DEFAULT 0, %s INTEGER NOT NULL);", new Object[]{"amp_displayed", "_id", "displayed", "campaign_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"amp_conditions", "_id", "attribute_name", "operator", "rule_id_ref", "amp_rules", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER REFERENCES %s(%s) NOT NULL);", new Object[]{"amp_condition_values", "_id", "value", "condition_id_ref", "amp_conditions", "_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 15) {
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT NOT NULL);", new Object[]{"custom_dimensions", "_id", "custom_dimension_key", "custom_dimension_value"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 16) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "first_advertising_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            contentValues = new ContentValues();
            AdvertisingInfo advertisingInfo = DatapointHelper.getAdvertisingInfo(this.mLocalyticsDao.getAppContext());
            contentValues.put("first_advertising_id", advertisingInfo == null ? null : advertisingInfo.id);
            str2 = "info";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.update(sQLiteDatabase, str2, contentValues, null, null);
            } else {
                sQLiteDatabase.update(str2, contentValues, null, null);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"sessions", "device_advertising_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER;", new Object[]{"info", "push_disabled"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"info", "sender_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 17) {
            str = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER)", new Object[]{"profile", "_id", "attribute", "action"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"events", "customer_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"events", "user_type"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT;", new Object[]{"events", "ids"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER", new Object[]{"info", "last_session_open_time"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            str = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER NOT NULL CHECK (%s >= 0) DEFAULT 0", new Object[]{"sessions", "elapsed", "elapsed"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
        }
        if (i < 18) {
            str = String.format("ALTER TABLE %s ADD COLUMN %s TEXT", new Object[]{"profile", "customer_id"});
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            ContentValues contentValues3 = new ContentValues();
            try {
                str = "profile";
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    query = SQLiteInstrumentation.query(sQLiteDatabase, str, null, null, null, null, null, null);
                } else {
                    query = sQLiteDatabase.query(str, null, null, null, null, null, null);
                }
                while (query.moveToNext()) {
                    str2 = String.valueOf(query.getInt(query.getColumnIndexOrThrow("_id")));
                    String str4;
                    try {
                        JSONObject init = JSONObjectInstrumentation.init(query.getString(query.getColumnIndexOrThrow("attribute")));
                        contentValues3.put("attribute", init.getString("attributes"));
                        contentValues3.put("customer_id", init.getString("id"));
                        str4 = "profile";
                        String format2 = String.format("%s = %s", new Object[]{"_id", str2});
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.update(sQLiteDatabase, str4, contentValues3, format2, null);
                        } else {
                            sQLiteDatabase.update(str4, contentValues3, format2, null);
                        }
                        contentValues3.clear();
                    } catch (Exception e) {
                        str4 = "profile";
                        str2 = String.format("%s = %s", new Object[]{"_id", str2});
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.delete(sQLiteDatabase, str4, str2, null);
                        } else {
                            sQLiteDatabase.delete(str4, str2, null);
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th5) {
                th = th5;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
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

    private static JSONObject convertEventToJson(SQLiteDatabase sQLiteDatabase, Context context, long j, long j2, String str) throws JSONException {
        Cursor query;
        Cursor query2;
        Throwable th;
        JSONObject jSONObject = new JSONObject();
        try {
            String str2 = "events";
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr = new String[]{Long.toString(j)};
            String str3 = "_id";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, str3);
            } else {
                query = sQLiteDatabase.query(str2, null, format, strArr, null, null, str3);
            }
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndexOrThrow("event_name"));
                    long sessionIdForEventId = getSessionIdForEventId(sQLiteDatabase, j);
                    str2 = getSessionUuid(sQLiteDatabase, sessionIdForEventId);
                    long sessionStartTime = getSessionStartTime(sQLiteDatabase, sessionIdForEventId);
                    long elapsedTimeSinceLastSession;
                    double d;
                    double d2;
                    int columnIndexOrThrow;
                    int columnIndexOrThrow2;
                    String string2;
                    if (OPEN_EVENT.equals(string)) {
                        jSONObject.put("dt", "s");
                        jSONObject.put("ct", Math.round(((double) query.getLong(query.getColumnIndex("wall_time"))) / 1000.0d));
                        jSONObject.put("u", str2);
                        elapsedTimeSinceLastSession = getElapsedTimeSinceLastSession(sQLiteDatabase, sessionIdForEventId);
                        if (elapsedTimeSinceLastSession > 0) {
                            jSONObject.put("sl", Math.round((float) (elapsedTimeSinceLastSession / 1000)));
                        }
                        jSONObject.put("nth", sessionIdForEventId);
                        if (!(query.isNull(query.getColumnIndexOrThrow("event_lat")) || query.isNull(query.getColumnIndexOrThrow("event_lng")))) {
                            d = query.getDouble(query.getColumnIndexOrThrow("event_lat"));
                            d2 = query.getDouble(query.getColumnIndexOrThrow("event_lng"));
                            if (!(d == 0.0d || d2 == 0.0d)) {
                                jSONObject.put(Constants.LAT, d);
                                jSONObject.put(Constants.LNG, d2);
                            }
                        }
                        if (!query.isNull(query.getColumnIndexOrThrow("customer_id"))) {
                            string = query.getString(query.getColumnIndexOrThrow("customer_id"));
                            str2 = query.getString(query.getColumnIndexOrThrow("user_type"));
                            jSONObject.put("cid", string);
                            jSONObject.put("utp", str2);
                        }
                        if (!query.isNull(query.getColumnIndexOrThrow("ids"))) {
                            jSONObject.put("ids", JSONObjectInstrumentation.init(query.getString(query.getColumnIndexOrThrow("ids"))));
                        }
                        try {
                            str2 = "attributes";
                            format = String.format("%s = ?", new Object[]{"events_key_ref"});
                            strArr = new String[]{Long.toString(j)};
                            if (sQLiteDatabase instanceof SQLiteDatabase) {
                                query2 = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, null);
                            } else {
                                query2 = sQLiteDatabase.query(str2, null, format, strArr, null, null, null);
                            }
                            try {
                                columnIndexOrThrow = query2.getColumnIndexOrThrow("attribute_key");
                                columnIndexOrThrow2 = query2.getColumnIndexOrThrow("attribute_value");
                                while (query2.moveToNext()) {
                                    format = query2.getString(columnIndexOrThrow);
                                    string2 = query2.getString(columnIndexOrThrow2);
                                    if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(1), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(2), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(3), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(4), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(5), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(6), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(7), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(8), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(9), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(10), string2);
                                    }
                                }
                                if (query2 != null) {
                                    query2.close();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            query2 = null;
                            if (query2 != null) {
                                query2.close();
                            }
                            throw th;
                        }
                    } else if (CLOSE_EVENT.equals(string)) {
                        jSONObject.put("dt", "c");
                        jSONObject.put("u", query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE)));
                        jSONObject.put("su", str2);
                        jSONObject.put("ss", Math.round(((double) sessionStartTime) / 1000.0d));
                        jSONObject.put("ct", Math.round(((double) query.getLong(query.getColumnIndex("wall_time"))) / 1000.0d));
                        try {
                            str2 = "sessions";
                            r6 = new String[]{"session_start_wall_time"};
                            format = String.format("%s = ?", new Object[]{"_id"});
                            strArr = new String[]{Long.toString(query.getLong(query.getColumnIndexOrThrow("session_key_ref")))};
                            query2 = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(str2, r6, format, strArr, null, null, null) : SQLiteInstrumentation.query(sQLiteDatabase, str2, r6, format, strArr, null, null, null);
                            try {
                                if (query2.moveToFirst()) {
                                    jSONObject.put("ctl", Math.round(((double) query.getLong(query.getColumnIndex("wall_time"))) / 1000.0d) - Math.round(((double) query2.getLong(query2.getColumnIndexOrThrow("session_start_wall_time"))) / 1000.0d));
                                    if (query2 != null) {
                                        query2.close();
                                    }
                                    try {
                                        str2 = "event_history";
                                        r6 = new String[]{Constants.BUNDLE_NAME};
                                        format = String.format("%s = ? AND %s = ?", new Object[]{"session_key_ref", Constants.BUNDLE_TYPE});
                                        strArr = new String[]{Long.toString(sessionIdForEventId), Integer.toString(1)};
                                        str3 = "_id";
                                        query2 = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(str2, r6, format, strArr, null, null, str3) : SQLiteInstrumentation.query(sQLiteDatabase, str2, r6, format, strArr, null, null, str3);
                                        try {
                                            JSONArray jSONArray = new JSONArray();
                                            while (query2.moveToNext()) {
                                                jSONArray.put(query2.getString(query2.getColumnIndexOrThrow(Constants.BUNDLE_NAME)));
                                            }
                                            if (jSONArray.length() > 0) {
                                                jSONObject.put("fl", jSONArray);
                                            }
                                            if (query2 != null) {
                                                query2.close();
                                            }
                                            if (!(query.isNull(query.getColumnIndexOrThrow("event_lat")) || query.isNull(query.getColumnIndexOrThrow("event_lng")))) {
                                                d = query.getDouble(query.getColumnIndexOrThrow("event_lat"));
                                                d2 = query.getDouble(query.getColumnIndexOrThrow("event_lng"));
                                                if (!(d == 0.0d || d2 == 0.0d)) {
                                                    jSONObject.put(Constants.LAT, d);
                                                    jSONObject.put(Constants.LNG, d2);
                                                }
                                            }
                                            if (!query.isNull(query.getColumnIndexOrThrow("customer_id"))) {
                                                string = query.getString(query.getColumnIndexOrThrow("customer_id"));
                                                str2 = query.getString(query.getColumnIndexOrThrow("user_type"));
                                                jSONObject.put("cid", string);
                                                jSONObject.put("utp", str2);
                                            }
                                            if (!query.isNull(query.getColumnIndexOrThrow("ids"))) {
                                                jSONObject.put("ids", JSONObjectInstrumentation.init(query.getString(query.getColumnIndexOrThrow("ids"))));
                                            }
                                            try {
                                                str2 = "attributes";
                                                format = String.format("%s = ?", new Object[]{"events_key_ref"});
                                                strArr = new String[]{Long.toString(j)};
                                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                                    query2 = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, null);
                                                } else {
                                                    query2 = sQLiteDatabase.query(str2, null, format, strArr, null, null, null);
                                                }
                                                try {
                                                    columnIndexOrThrow = query2.getColumnIndexOrThrow("attribute_key");
                                                    columnIndexOrThrow2 = query2.getColumnIndexOrThrow("attribute_value");
                                                    while (query2.moveToNext()) {
                                                        format = query2.getString(columnIndexOrThrow);
                                                        string2 = query2.getString(columnIndexOrThrow2);
                                                        if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(1), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(2), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(3), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(4), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(5), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(6), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(7), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(8), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(9), string2);
                                                        } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(format)) {
                                                            jSONObject.put(getCustomDimensionKey(10), string2);
                                                        }
                                                    }
                                                    if (query2 != null) {
                                                        query2.close();
                                                    }
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                query2 = null;
                                                if (query2 != null) {
                                                    query2.close();
                                                }
                                                throw th;
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            if (query2 != null) {
                                                query2.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        query2 = null;
                                        if (query2 != null) {
                                            query2.close();
                                        }
                                        throw th;
                                    }
                                }
                                throw new RuntimeException("Session didn't exist");
                            } catch (Throwable th8) {
                                th = th8;
                                if (query2 != null) {
                                    query2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            query2 = null;
                            if (query2 != null) {
                                query2.close();
                            }
                            throw th;
                        }
                    } else if (OPT_IN_EVENT.equals(string) || OPT_OUT_EVENT.equals(string)) {
                        jSONObject.put("dt", "o");
                        jSONObject.put("u", str);
                        jSONObject.put("out", OPT_OUT_EVENT.equals(string) ? Boolean.TRUE.toString() : Boolean.FALSE.toString());
                        jSONObject.put("ct", Math.round(((double) query.getLong(query.getColumnIndex("wall_time"))) / 1000.0d));
                    } else if (FLOW_EVENT.equals(string)) {
                        jSONObject.put("dt", "f");
                        jSONObject.put("u", query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE)));
                        jSONObject.put("ss", Math.round(((double) sessionStartTime) / 1000.0d));
                        try {
                            str2 = "event_history";
                            r6 = new String[]{Constants.BUNDLE_TYPE, "processed_in_blob", Constants.BUNDLE_NAME};
                            format = String.format("%s = ? AND %s <= ?", new Object[]{"session_key_ref", "processed_in_blob"});
                            strArr = new String[]{Long.toString(sessionIdForEventId), Long.toString(j2)};
                            str3 = "_id";
                            if (sQLiteDatabase instanceof SQLiteDatabase) {
                                query2 = SQLiteInstrumentation.query(sQLiteDatabase, str2, r6, format, strArr, null, null, str3);
                            } else {
                                query2 = sQLiteDatabase.query(str2, r6, format, strArr, null, null, str3);
                            }
                            try {
                                JSONArray jSONArray2 = new JSONArray();
                                JSONArray jSONArray3 = new JSONArray();
                                while (query2.moveToNext()) {
                                    string2 = query2.getString(query2.getColumnIndexOrThrow(Constants.BUNDLE_NAME));
                                    if (query2.getInt(query2.getColumnIndexOrThrow(Constants.BUNDLE_TYPE)) == 0) {
                                        string = com.apsalar.sdk.Constants.API_PREFIX;
                                    } else {
                                        string = "s";
                                    }
                                    if (j2 == query2.getLong(query2.getColumnIndexOrThrow("processed_in_blob"))) {
                                        jSONArray2.put(new JSONObject().put(string, string2));
                                    } else {
                                        jSONArray3.put(new JSONObject().put(string, string2));
                                    }
                                }
                                jSONObject.put("nw", jSONArray2);
                                jSONObject.put("od", jSONArray3);
                                if (query2 != null) {
                                    query2.close();
                                }
                            } catch (Throwable th10) {
                                th = th10;
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            query2 = null;
                            if (query2 != null) {
                                query2.close();
                            }
                            throw th;
                        }
                    } else {
                        jSONObject.put("dt", com.apsalar.sdk.Constants.API_PREFIX);
                        jSONObject.put("ct", Math.round(((double) query.getLong(query.getColumnIndex("wall_time"))) / 1000.0d));
                        jSONObject.put("u", query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE)));
                        jSONObject.put("su", str2);
                        jSONObject.put("n", string.substring(context.getPackageName().length() + 1, string.length()));
                        elapsedTimeSinceLastSession = query.getLong(query.getColumnIndex("clv_increase"));
                        if (elapsedTimeSinceLastSession != 0) {
                            jSONObject.put("v", elapsedTimeSinceLastSession);
                        }
                        if (!(query.isNull(query.getColumnIndexOrThrow("event_lat")) || query.isNull(query.getColumnIndexOrThrow("event_lng")))) {
                            d = query.getDouble(query.getColumnIndexOrThrow("event_lat"));
                            d2 = query.getDouble(query.getColumnIndexOrThrow("event_lng"));
                            if (!(d == 0.0d || d2 == 0.0d)) {
                                jSONObject.put(Constants.LAT, d);
                                jSONObject.put(Constants.LNG, d2);
                            }
                        }
                        if (!query.isNull(query.getColumnIndexOrThrow("customer_id"))) {
                            string = query.getString(query.getColumnIndexOrThrow("customer_id"));
                            str2 = query.getString(query.getColumnIndexOrThrow("user_type"));
                            jSONObject.put("cid", string);
                            jSONObject.put("utp", str2);
                        }
                        if (!query.isNull(query.getColumnIndexOrThrow("ids"))) {
                            jSONObject.put("ids", JSONObjectInstrumentation.init(query.getString(query.getColumnIndexOrThrow("ids"))));
                        }
                        try {
                            str2 = "attributes";
                            format = String.format("%s = ?", new Object[]{"events_key_ref"});
                            strArr = new String[]{Long.toString(j)};
                            if (sQLiteDatabase instanceof SQLiteDatabase) {
                                query2 = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, null);
                            } else {
                                query2 = sQLiteDatabase.query(str2, null, format, strArr, null, null, null);
                            }
                            try {
                                columnIndexOrThrow = query2.getColumnIndexOrThrow("attribute_key");
                                columnIndexOrThrow2 = query2.getColumnIndexOrThrow("attribute_value");
                                while (query2.moveToNext()) {
                                    format = query2.getString(columnIndexOrThrow);
                                    string2 = query2.getString(columnIndexOrThrow2);
                                    if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(1), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(2), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(3), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(4), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(5), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(6), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(7), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(8), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(9), string2);
                                    } else if (AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10.equals(format)) {
                                        jSONObject.put(getCustomDimensionKey(10), string2);
                                    }
                                }
                                if (query2 != null) {
                                    query2.close();
                                }
                                JSONObject convertAttributesToJson = convertAttributesToJson(sQLiteDatabase, context, j);
                                if (convertAttributesToJson != null) {
                                    jSONObject.put("attrs", convertAttributesToJson);
                                }
                            } catch (Throwable th12) {
                                th = th12;
                            }
                        } catch (Throwable th13) {
                            th = th13;
                            query2 = null;
                            if (query2 != null) {
                                query2.close();
                            }
                            throw th;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                    return jSONObject;
                }
                throw new RuntimeException();
            } catch (Throwable th14) {
                th = th14;
                query2 = query;
                if (query2 != null) {
                    query2.close();
                }
                throw th;
            }
        } catch (Throwable th15) {
            th = th15;
            query2 = null;
            if (query2 != null) {
                query2.close();
            }
            throw th;
        }
    }

    static List<JSONObject> convertDatabaseToJson(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Cursor query2;
        Throwable th;
        Cursor cursor;
        List<JSONObject> linkedList = new LinkedList();
        try {
            String str2 = "upload_blobs";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, null, null, null, null, null);
            } else {
                query = sQLiteDatabase.query(str2, null, null, null, null, null, null);
            }
            try {
                long apiKeyCreationTime = getApiKeyCreationTime(sQLiteDatabase, str);
                int columnIndexOrThrow = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE);
                while (query.moveToNext()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("dt", "h");
                    jSONObject.put("pa", apiKeyCreationTime);
                    jSONObject.put("seq", query.getLong(columnIndexOrThrow));
                    jSONObject.put("u", query.getString(columnIndexOrThrow2));
                    JSONObject attributesFromSession = getAttributesFromSession(sQLiteDatabase, str, getSessionIdForBlobId(sQLiteDatabase, query.getLong(columnIndexOrThrow)));
                    if (attributesFromSession == null) {
                        break;
                    }
                    jSONObject.put("attrs", attributesFromSession);
                    attributesFromSession = getIdentifiers(sQLiteDatabase);
                    if (attributesFromSession != null) {
                        jSONObject.put("ids", attributesFromSession);
                    }
                    linkedList.add(jSONObject);
                    Log.m12801w(linkedList.toString());
                    try {
                        str2 = "upload_blob_events";
                        String[] strArr = new String[]{"_id", "events_key_ref"};
                        String format = String.format("%s = ?", new Object[]{"upload_blobs_key_ref"});
                        String[] strArr2 = new String[]{Long.toString(query.getLong(columnIndexOrThrow))};
                        String str3 = "events_key_ref";
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            query2 = SQLiteInstrumentation.query(sQLiteDatabase, str2, strArr, format, strArr2, null, null, str3);
                        } else {
                            query2 = sQLiteDatabase.query(str2, strArr, format, strArr2, null, null, str3);
                        }
                        try {
                            int columnIndexOrThrow3 = query2.getColumnIndexOrThrow("events_key_ref");
                            while (query2.moveToNext() && linkedList.size() < 100) {
                                linkedList.add(convertEventToJson(sQLiteDatabase, context, query2.getLong(columnIndexOrThrow3), query.getLong(columnIndexOrThrow), str));
                                str2 = "upload_blob_events";
                                String format2 = String.format("%s = ?", new Object[]{"_id"});
                                String[] strArr3 = new String[]{Integer.toString(query2.getInt(query2.getColumnIndexOrThrow("_id")))};
                                if (sQLiteDatabase instanceof SQLiteDatabase) {
                                    SQLiteInstrumentation.delete(sQLiteDatabase, str2, format2, strArr3);
                                } else {
                                    sQLiteDatabase.delete(str2, format2, strArr3);
                                }
                            }
                            if (query2 != null) {
                                query2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = query2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = null;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                Log.m12799v(String.format("JSON result is %s", new Object[]{linkedList.toString()}));
                return linkedList;
            } catch (Throwable th4) {
                Log.m12802w("Caught exception", th4);
            } catch (Throwable th5) {
                th4 = th5;
                cursor = query;
            }
        } catch (Throwable th6) {
            th4 = th6;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th4;
        }
    }

    private static JSONObject convertAttributesToJson(SQLiteDatabase sQLiteDatabase, Context context, long j) throws JSONException {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor query;
            String str = "attributes";
            String format = String.format("%s = ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ? AND %s != ?", new Object[]{"events_key_ref", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key", "attribute_key"});
            String[] strArr = new String[]{Long.toString(j), AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_1, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_2, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_3, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_4, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_5, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_6, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_7, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_8, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_9, AttributesDbColumns.ATTRIBUTE_CUSTOM_DIMENSION_10};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, null, format, strArr, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, null, format, strArr, null, null, null);
            }
            try {
                if (query.getCount() == 0) {
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                JSONObject jSONObject = new JSONObject();
                int columnIndexOrThrow = query.getColumnIndexOrThrow("attribute_key");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("attribute_value");
                while (query.moveToNext()) {
                    String string = query.getString(columnIndexOrThrow);
                    jSONObject.put(string.substring(context.getPackageName().length() + 1, string.length()), query.getString(columnIndexOrThrow2));
                }
                if (query == null) {
                    return jSONObject;
                }
                query.close();
                return jSONObject;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static long getApiKeyCreationTime(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Throwable th;
        try {
            String str2 = "api_keys";
            String format = String.format("%s = ?", new Object[]{"api_key"});
            String[] strArr = new String[]{str};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, null);
            } else {
                query = sQLiteDatabase.query(str2, null, format, strArr, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    long round = (long) Math.round(((float) query.getLong(query.getColumnIndexOrThrow("created_time"))) / 1000.0f);
                    if (query != null) {
                        query.close();
                    }
                    return round;
                }
                throw new RuntimeException("API key entry couldn't be found");
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static JSONObject getAttributesFromSession(SQLiteDatabase sQLiteDatabase, String str, long j) throws JSONException {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor query;
            String str2 = "sessions";
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, format, strArr, null, null, null);
            } else {
                query = sQLiteDatabase.query(str2, null, format, strArr, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    Object obj;
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("av", query.getString(query.getColumnIndexOrThrow(AppInfo.APP_VERSION_KEY_HEADER)));
                    jSONObject.put("dac", query.getString(query.getColumnIndexOrThrow("network_type")));
                    String string = query.getString(query.getColumnIndexOrThrow("device_android_id_hash"));
                    if (!Trace.NULL.equals(string)) {
                        jSONObject.put("du", string);
                    }
                    jSONObject.put("dc", query.getString(query.getColumnIndexOrThrow("device_country")));
                    jSONObject.put("dma", query.getString(query.getColumnIndexOrThrow("device_manufacturer")));
                    jSONObject.put("dmo", query.getString(query.getColumnIndexOrThrow(Constants.DEVICE_MODEL)));
                    jSONObject.put("dov", query.getString(query.getColumnIndexOrThrow("android_version")));
                    jSONObject.put("dp", "Android");
                    jSONObject.put("dms", query.isNull(query.getColumnIndexOrThrow("device_serial_number_hash")) ? JSONObject.NULL : query.getString(query.getColumnIndexOrThrow("device_serial_number_hash")));
                    jSONObject.put("dsdk", query.getString(query.getColumnIndexOrThrow("android_sdk")));
                    jSONObject.put("au", str);
                    jSONObject.put("lv", query.getString(query.getColumnIndexOrThrow("localytics_library_version")));
                    jSONObject.put("dt", "a");
                    jSONObject.put("caid", query.isNull(query.getColumnIndexOrThrow("device_android_id")) ? JSONObject.NULL : query.getString(query.getColumnIndexOrThrow("device_android_id")));
                    format = "gcadid";
                    if (query.isNull(query.getColumnIndexOrThrow("device_advertising_id"))) {
                        obj = JSONObject.NULL;
                    } else {
                        obj = query.getString(query.getColumnIndexOrThrow("device_advertising_id"));
                    }
                    jSONObject.put(format, obj);
                    string = query.getString(query.getColumnIndexOrThrow("iu"));
                    if (string != null) {
                        jSONObject.put("iu", string);
                    }
                    jSONObject.put("dlc", query.getString(query.getColumnIndexOrThrow("locale_country")));
                    jSONObject.put("dll", query.getString(query.getColumnIndexOrThrow("locale_language")));
                    jSONObject.put("nca", query.getString(query.getColumnIndexOrThrow("network_carrier")));
                    jSONObject.put("nc", query.getString(query.getColumnIndexOrThrow("network_country")));
                    string = getStringFromAppInfo(sQLiteDatabase, "fb_attribution");
                    if (string != null) {
                        jSONObject.put("fbat", string);
                    }
                    string = getStringFromAppInfo(sQLiteDatabase, "play_attribution");
                    if (string != null) {
                        jSONObject.put("aurl", string);
                    }
                    string = getStringFromAppInfo(sQLiteDatabase, da.GCM_REG_ID_KEY);
                    if (string != null) {
                        jSONObject.put("push", string);
                    }
                    string = getStringFromAppInfo(sQLiteDatabase, "first_android_id");
                    if (string != null) {
                        jSONObject.put("aid", string);
                    }
                    string = getStringFromAppInfo(sQLiteDatabase, "first_advertising_id");
                    if (string != null) {
                        jSONObject.put("gadid", string);
                    }
                    string = getStringFromAppInfo(sQLiteDatabase, "package_name");
                    if (string != null) {
                        jSONObject.put("pkg", string);
                    }
                    if (query == null) {
                        return jSONObject;
                    }
                    query.close();
                    return jSONObject;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static long getSessionIdForBlobId(SQLiteDatabase sQLiteDatabase, long j) {
        Throwable th;
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            Cursor query;
            String str = "upload_blob_events";
            String[] strArr = new String[]{"events_key_ref"};
            String format = String.format("%s = ?", new Object[]{"upload_blobs_key_ref"});
            String[] strArr2 = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
            }
            try {
                long j2;
                if (query.moveToFirst()) {
                    long j3 = query.getLong(query.getColumnIndexOrThrow("events_key_ref"));
                    if (query != null) {
                        query.close();
                    }
                    try {
                        str = "events";
                        strArr = new String[]{"session_key_ref"};
                        format = String.format("%s = ?", new Object[]{"_id"});
                        strArr2 = new String[]{Long.toString(j3)};
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            cursor2 = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
                        } else {
                            cursor2 = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
                        }
                        if (cursor2.moveToFirst()) {
                            j2 = cursor2.getLong(cursor2.getColumnIndexOrThrow("session_key_ref"));
                            return j2;
                        }
                        throw new RuntimeException("No session associated with event");
                    } finally {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                    }
                } else {
                    j2 = -1;
                    if (query != null) {
                        query.close();
                    }
                    return j2;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static JSONObject getIdentifiers(SQLiteDatabase sQLiteDatabase) throws JSONException {
        Cursor cursor;
        Throwable th;
        JSONObject jSONObject = null;
        try {
            Cursor query;
            String str = "identifiers";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, null, null, null, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, null, null, null, null, null, null);
            }
            while (query.moveToNext()) {
                try {
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put(query.getString(query.getColumnIndexOrThrow("key")), query.getString(query.getColumnIndexOrThrow("value")));
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    cursor = query;
                    th = th3;
                }
            }
            if (query != null) {
                query.close();
            }
            return jSONObject;
        } catch (Throwable th4) {
            th = th4;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static String getStringFromAppInfo(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Throwable th;
        Cursor cursor = null;
        try {
            String str2 = "info";
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str2, null, null, null, null, null, null);
            } else {
                query = sQLiteDatabase.query(str2, null, null, null, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndexOrThrow(str));
                    if (query == null) {
                        return string;
                    }
                    query.close();
                    return string;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static long getSessionIdForEventId(SQLiteDatabase sQLiteDatabase, long j) {
        Throwable th;
        Cursor query;
        try {
            String str = "events";
            String[] strArr = new String[]{"session_key_ref"};
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr2 = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    long j2 = query.getLong(query.getColumnIndexOrThrow("session_key_ref"));
                    if (query != null) {
                        query.close();
                    }
                    return j2;
                }
                throw new RuntimeException();
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static String getSessionUuid(SQLiteDatabase sQLiteDatabase, long j) {
        Cursor query;
        Throwable th;
        try {
            String str = "sessions";
            String[] strArr = new String[]{AnalyticAttribute.UUID_ATTRIBUTE};
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr2 = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndexOrThrow(AnalyticAttribute.UUID_ATTRIBUTE));
                    if (query != null) {
                        query.close();
                    }
                    return string;
                }
                throw new RuntimeException();
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static long getSessionStartTime(SQLiteDatabase sQLiteDatabase, long j) {
        Cursor query;
        Throwable th;
        try {
            String str = "sessions";
            String[] strArr = new String[]{"session_start_wall_time"};
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr2 = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    long j2 = query.getLong(query.getColumnIndexOrThrow("session_start_wall_time"));
                    if (query != null) {
                        query.close();
                    }
                    return j2;
                }
                throw new RuntimeException();
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static long getElapsedTimeSinceLastSession(SQLiteDatabase sQLiteDatabase, long j) {
        Throwable th;
        Cursor query;
        try {
            String str = "sessions";
            String[] strArr = new String[]{"elapsed"};
            String format = String.format("%s = ?", new Object[]{"_id"});
            String[] strArr2 = new String[]{Long.toString(j)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, format, strArr2, null, null, null);
            } else {
                query = sQLiteDatabase.query(str, strArr, format, strArr2, null, null, null);
            }
            try {
                if (query.moveToFirst()) {
                    long j2 = query.getLong(query.getColumnIndexOrThrow("elapsed"));
                    if (query != null) {
                        query.close();
                    }
                    return j2;
                }
                throw new RuntimeException();
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static String getCustomDimensionKey(int i) {
        return String.format("%s%s", new Object[]{"c", String.valueOf(i - 1)});
    }

    static void preUploadBuildBlobs(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor cursor = null;
        Set<Long> hashSet = new HashSet();
        Cursor query;
        try {
            String str = "events";
            String[] strArr = PROJECTION_UPLOAD_EVENTS;
            String str2 = EVENTS_SORT_ORDER;
            Cursor query2 = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query(str, strArr, null, null, null, null, str2) : SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, null, null, null, null, str2);
            try {
                str = "upload_blob_events";
                strArr = PROJECTION_UPLOAD_BLOBS;
                str2 = UPLOAD_BLOBS_EVENTS_SORT_ORDER;
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    query = SQLiteInstrumentation.query(sQLiteDatabase, str, strArr, null, null, null, null, str2);
                } else {
                    query = sQLiteDatabase.query(str, strArr, null, null, null, null, str2);
                }
            } catch (Throwable th2) {
                th = th2;
                query = null;
                cursor = query2;
                if (cursor != null) {
                    cursor.close();
                }
                if (query != null) {
                    query.close();
                }
                throw th;
            }
            try {
                int columnIndexOrThrow = query2.getColumnIndexOrThrow("_id");
                Iterator it = new CursorJoiner(query2, JOINER_ARG_UPLOAD_EVENTS_COLUMNS, query, PROJECTION_UPLOAD_BLOBS).iterator();
                while (it.hasNext()) {
                    switch (C07171.$SwitchMap$android$database$CursorJoiner$Result[((Result) it.next()).ordinal()]) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            if (CLOSE_EVENT.equals(query2.getString(query2.getColumnIndexOrThrow("event_name"))) && System.currentTimeMillis() - query2.getLong(query2.getColumnIndexOrThrow("wall_time")) < Constants.SESSION_EXPIRATION) {
                                break;
                            }
                            hashSet.add(Long.valueOf(query2.getLong(columnIndexOrThrow)));
                            break;
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            break;
                        default:
                            break;
                    }
                }
                if (query2 != null) {
                    query2.close();
                }
                if (query != null) {
                    query.close();
                }
                if (hashSet.size() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(AnalyticAttribute.UUID_ATTRIBUTE, UUID.randomUUID().toString());
                    str = "upload_blobs";
                    Long valueOf = Long.valueOf(!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert(str, null, contentValues) : SQLiteInstrumentation.insert(sQLiteDatabase, str, null, contentValues));
                    contentValues.clear();
                    for (Long l : hashSet) {
                        contentValues.put("upload_blobs_key_ref", valueOf);
                        contentValues.put("events_key_ref", l);
                        String str3 = "upload_blob_events";
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            SQLiteInstrumentation.insert(sQLiteDatabase, str3, null, contentValues);
                        } else {
                            sQLiteDatabase.insert(str3, null, contentValues);
                        }
                        contentValues.clear();
                    }
                    contentValues.put("processed_in_blob", valueOf);
                    String str4 = "event_history";
                    str = SELECTION_UPLOAD_NULL_BLOBS;
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        SQLiteInstrumentation.update(sQLiteDatabase, str4, contentValues, str, null);
                    } else {
                        sQLiteDatabase.update(str4, contentValues, str, null);
                    }
                    contentValues.clear();
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = query2;
                if (cursor != null) {
                    cursor.close();
                }
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (cursor != null) {
                cursor.close();
            }
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
