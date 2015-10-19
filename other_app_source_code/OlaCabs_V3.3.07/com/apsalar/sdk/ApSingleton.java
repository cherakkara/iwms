package com.apsalar.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.utils.Constants;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.http.protocol.HttpRequestExecutor;
import org.json.JSONArray;

public class ApSingleton {
    static final String TAG = "Apsalar SDK/ApSingleton";
    public static volatile ApsalarThread apsalar_thread = null;
    public static volatile ApsalarEventConsumerThread eventConsumerThread = null;
    public static volatile ArrayBlockingQueue<RawEvent> eventQueue = null;
    private static volatile ApSingleton instance = null;
    public static final int maxUrlSize = 1999;
    public volatile String AIFA;
    public boolean AIFA_changed;
    public boolean ALWAYS_REQUEST_CANONICAL;
    public volatile String ANDI;
    public int BACKOFF_INTERVAL;
    public int BATCHES_INTERVAL;
    public int BATCHES_MAX;
    public int BUFFER_SIZE_MAX;
    public final boolean DEBUG;
    public final boolean ERROR;
    public volatile String FBAppId;
    public int HEARTBEAT_INTERVAL_BACKOFF;
    public int HEARTBEAT_INTERVAL_MAX;
    public int HEARTBEAT_INTERVAL_MIN;
    public int LONGSLEEP;
    public int MEDIUMSLEEP;
    public int NUM_EVENTS_B4_SLEEP;
    public int QUEUE_SIZE_MAX;
    public int RESOLVER_MAX;
    public boolean RESOLVE_ALL_AVAILABLE;
    public int RETRY_INTERVAL_BACKOFF;
    public int RETRY_INTERVAL_MAX;
    public int RETRY_INTERVAL_MIN;
    public int SHORTSLEEP;
    public int VERYLONGSLEEP;
    public boolean already_did_SQL;
    public volatile BroadcastReceiver apsalar_receiver;
    public BroadcastReceiver apsalar_safe_receiver;
    public boolean backlogTableCreated;
    private volatile int bootstrapCount;
    public volatile String canonicalDeviceId;
    public volatile String canonicalKeyspace;
    public boolean configTableCreated;
    public Context ctx;
    public volatile SQLiteDatabase database;
    public volatile SQLiteOpenHelper dbOpener;
    public volatile JSONArray desired;
    public boolean devicekeysTableCreated;
    public boolean devicesAlreadyResolved;
    public volatile String dk;
    public boolean doHeartbeat;
    private volatile int dropEventsCount;
    public boolean eventsTableCreated;
    private volatile int exceptionCount;
    public int expires;
    public volatile String hash;
    public int heartbeatInterval;
    public volatile ApsalarSessionInfo info;
    public boolean isLAT;
    private volatile int networkErrorCount;
    public volatile String old_AIFA;
    public int openCount;
    public boolean playStoreAvailable;
    public volatile boolean registeredReceiver;
    public boolean registeredSafeReceiver;
    public boolean resolved_AIFA;
    public boolean resolved_ANDI;
    public final String selectMostRecentEventByTypeSQL;
    public final String selectOldestEventSQL;
    private volatile int sentEventsCount;
    public int state;
    public final String tableConfigSQL;
    public final String tableDeviceKeysSQL;
    public final String tableEventsSQL;

    public static class State {
        static final int BACKOFF = 4;
        static final int BUFFERING = 3;
        static final int NOMINAL = 2;
        static final int RECOVERY = 1;
    }

    public ApSingleton() {
        this.DEBUG = false;
        this.ERROR = false;
        this.tableDeviceKeysSQL = "CREATE TABLE IF NOT EXISTS device_keys ( keyspace CHAR(4), val TEXT, canonical BOOLEAN NULL, PRIMARY KEY (keyspace))";
        this.tableConfigSQL = "CREATE TABLE IF NOT EXISTS config ( apiKey TEXT primary key, secret TEXT, isLAT BOOLEAN, doHeartbeat BOOLEAN, playStoreAvailable BOOLEAN, andi TEXT NULL, aifa TEXT NULL, imei TEXT NULL, mac1 TEXT NULL, bmac TEXT NULL, apid TEXT NULL, canonicalKeyspace TEXT NULL, canonicalDeviceId TEXT NULL, desired TEXT NULL)";
        this.tableEventsSQL = "CREATE TABLE IF NOT EXISTS events ( id INTEGER PRIMARY KEY, unix_t INTEGER NOT NULL, session_json TEXT, type INTEGER NOT NULL, name VARCHAR(32), args TEXT)";
        this.selectMostRecentEventByTypeSQL = "SELECT id, session_json FROM events WHERE type=? ORDER BY id DESC LIMIT 1";
        this.selectOldestEventSQL = "SELECT type, session_json, name, args, unix_t, id FROM events ORDER BY id ASC LIMIT 1";
    }

    static {
        instance = null;
        eventConsumerThread = null;
        eventQueue = null;
    }

    public static void log() {
        Log.d(TAG, "Current state of `instance`:");
        if (instance == null) {
            Log.w(TAG, "> instance is null");
            return;
        }
        Log.d(TAG, "> instance.configTableCreated=" + instance.configTableCreated);
        Log.d(TAG, "> instance.backlogTableCreated=" + instance.backlogTableCreated);
        Log.d(TAG, "> instance.devicekeysTableCreated=" + instance.devicekeysTableCreated);
        Log.d(TAG, "> instance.expires=" + instance.expires);
        Log.d(TAG, "> instance.hash=" + instance.hash);
        Log.d(TAG, "> instance.devicesAlreadyResolved=" + instance.devicesAlreadyResolved);
        Log.d(TAG, "> instance.RESOLVE_ALL_AVAILABLE=" + instance.RESOLVE_ALL_AVAILABLE);
        Log.d(TAG, "> instance.ALWAYS_REQUEST_CANONICAL=" + instance.ALWAYS_REQUEST_CANONICAL);
        Log.d(TAG, "> instance.NUM_EVENTS_B4_SLEEP=" + instance.NUM_EVENTS_B4_SLEEP);
        Log.d(TAG, "> instance.QUEUE_SIZE_MAX=" + instance.QUEUE_SIZE_MAX);
        Log.d(TAG, "> instance.BUFFER_SIZE_MAX=" + instance.BUFFER_SIZE_MAX);
        Log.d(TAG, "> instance.HEARTBEAT_INTERVAL_BACKOFF=" + instance.HEARTBEAT_INTERVAL_BACKOFF);
        Log.d(TAG, "> instance.HEARTBEAT_INTERVAL_MAX=" + instance.HEARTBEAT_INTERVAL_MAX);
        Log.d(TAG, "> instance.HEARTBEAT_INTERVAL_MIN=" + instance.HEARTBEAT_INTERVAL_MIN);
        Log.d(TAG, "> instance.RETRY_INTERVAL_BACKOFF=" + instance.RETRY_INTERVAL_BACKOFF);
        Log.d(TAG, "> instance.RETRY_INTERVAL_MAX=" + instance.RETRY_INTERVAL_MAX);
        Log.d(TAG, "> instance.RETRY_INTERVAL_MIN=" + instance.RETRY_INTERVAL_MIN);
        Log.d(TAG, "> instance.BATCHES_MAX=" + instance.BATCHES_MAX);
        Log.d(TAG, "> instance.BATCHES_INTERVAL=" + instance.BATCHES_INTERVAL);
        Log.d(TAG, "> instance.RESOLVER_MAX=" + instance.RESOLVER_MAX);
        Log.d(TAG, "> instance.SHORTSLEEP=" + instance.SHORTSLEEP);
        Log.d(TAG, "> instance.MEDIUMSLEEP=" + instance.MEDIUMSLEEP);
        Log.d(TAG, "> instance.LONGSLEEP=" + instance.LONGSLEEP);
        Log.d(TAG, "> instance.VERYLONGSLEEP=" + instance.VERYLONGSLEEP);
    }

    public static Context getContext() {
        if (instance != null) {
            return instance.ctx;
        }
        return null;
    }

    public static int getQueueSize() {
        if (instance == null) {
            instance = getInstance(null);
        }
        return instance.QUEUE_SIZE_MAX;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public static boolean testInstance() {
        return instance != null;
    }

    private static ApSingleton reinitializeInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = new ApSingleton();
        instance.getClass();
        instance.RESOLVE_ALL_AVAILABLE = false;
        instance.ALWAYS_REQUEST_CANONICAL = false;
        instance.NUM_EVENTS_B4_SLEEP = 25;
        instance.QUEUE_SIZE_MAX = ActivityTrace.MAX_TRACES;
        instance.BUFFER_SIZE_MAX = Constants.MILLIS_IN_A_SECOND;
        instance.HEARTBEAT_INTERVAL_BACKOFF = 2;
        instance.HEARTBEAT_INTERVAL_MAX = 21600000;
        instance.HEARTBEAT_INTERVAL_MIN = by.REFRESH_TOKEN_RETRY;
        instance.RETRY_INTERVAL_BACKOFF = 2;
        instance.RETRY_INTERVAL_MAX = by.REFRESH_TOKEN_RETRY;
        instance.RETRY_INTERVAL_MIN = 15000;
        instance.BACKOFF_INTERVAL = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        instance.BATCHES_MAX = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        instance.BATCHES_INTERVAL = 0;
        instance.RESOLVER_MAX = 9;
        instance.SHORTSLEEP = Constants.MILLIS_IN_A_SECOND;
        instance.MEDIUMSLEEP = HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE;
        instance.LONGSLEEP = TraceMachine.UNHEALTHY_TRACE_TIMEOUT;
        instance.VERYLONGSLEEP = 900000;
        instance.ctx = context;
        instance.apsalar_receiver = null;
        ApSingleton apSingleton = instance;
        apsalar_thread = null;
        instance.isLAT = false;
        instance.doHeartbeat = true;
        instance.registeredReceiver = false;
        instance.devicesAlreadyResolved = false;
        instance.playStoreAvailable = false;
        instance.configTableCreated = false;
        instance.backlogTableCreated = false;
        instance.devicekeysTableCreated = false;
        instance.eventsTableCreated = false;
        instance.AIFA_changed = false;
        instance.resolved_ANDI = false;
        instance.resolved_AIFA = false;
        instance.FBAppId = Trace.NULL;
        instance.old_AIFA = "None";
        instance.ANDI = null;
        instance.AIFA = null;
        instance.desired = new JSONArray();
        instance.dk = null;
        instance.openCount = 0;
        instance.already_did_SQL = false;
        instance.expires = 0;
        instance.canonicalKeyspace = "AIFA";
        instance.canonicalDeviceId = "None";
        instance.database = null;
        instance.dbOpener = null;
        instance.hash = null;
        instance.sentEventsCount = 0;
        instance.dropEventsCount = 0;
        instance.networkErrorCount = 0;
        instance.exceptionCount = 0;
        instance.bootstrapCount = 0;
        instance.state = 2;
        instance.heartbeatInterval = instance.HEARTBEAT_INTERVAL_MIN;
        instance.info = null;
        if (instance.ctx != null) {
            instance.getClass();
            Intent intent = new Intent();
            intent.setAction("com.apsalar.sdk.INITIALIZE");
            intent.setPackage("com.apsalar.sdk");
            intent.putExtra("Apsalar", "initialize");
            instance.ctx.sendBroadcast(intent);
        } else {
            instance.getClass();
        }
        return instance;
    }

    public static ApSingleton getInstance(Context context) {
        if (instance == null) {
            instance = reinitializeInstance(context);
        }
        if (context != null) {
            instance.ctx = context;
        }
        return instance;
    }

    public synchronized int incrSentEventsCount() {
        return 0;
    }

    public synchronized int incrDropEventsCount() {
        return 0;
    }

    public synchronized int incrNetworkErrorCount() {
        return 0;
    }

    public synchronized int incrExceptionCount() {
        return 0;
    }

    public synchronized int incrBootstrapCount() {
        return 0;
    }

    public int getSentEventsCount() {
        return this.sentEventsCount;
    }

    public int getDropEventsCount() {
        return this.dropEventsCount;
    }

    public int getNetworkErrorCount() {
        return this.networkErrorCount;
    }

    public int getExceptionCount() {
        return this.exceptionCount;
    }

    public int getBootstrapCount() {
        return this.bootstrapCount;
    }
}
