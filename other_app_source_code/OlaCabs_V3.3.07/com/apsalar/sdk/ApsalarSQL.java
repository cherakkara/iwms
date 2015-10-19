package com.apsalar.sdk;

/* compiled from: ApsalarSQLiteHelper */
class ApsalarSQL {
    static final String selectMostRecentEventByType = "SELECT id, session_json FROM events WHERE type=? ORDER BY id DESC LIMIT 1";
    static final String selectOldestEvent = "SELECT type, session_json, name, args, unix_t, id FROM events ORDER BY id ASC LIMIT 1";
    static final String tableConfig = "CREATE TABLE IF NOT EXISTS config ( apiKey TEXT primary key, secret TEXT, isLAT BOOLEAN, doHeartbeat BOOLEAN, playStoreAvailable BOOLEAN, andi TEXT NULL, aifa TEXT NULL, imei TEXT NULL, mac1 TEXT NULL, bmac TEXT NULL, apid TEXT NULL, canonicalKeyspace TEXT NULL, canonicalDeviceId TEXT NULL, desired TEXT NULL)";
    static final String tableDeviceKeys = "CREATE TABLE IF NOT EXISTS device_keys ( keyspace CHAR(4), val TEXT, canonical BOOLEAN NULL, PRIMARY KEY (keyspace))";
    static final String tableEvents = "CREATE TABLE IF NOT EXISTS events ( id INTEGER PRIMARY KEY, unix_t INTEGER NOT NULL, session_json TEXT, type INTEGER NOT NULL, name VARCHAR(32), args TEXT)";

    ApsalarSQL() {
    }
}
