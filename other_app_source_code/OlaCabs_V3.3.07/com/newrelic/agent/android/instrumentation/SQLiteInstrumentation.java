package com.newrelic.agent.android.instrumentation;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.CancellationSignal;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLiteInstrumentation {
    private static final ArrayList<String> categoryParams;

    static {
        categoryParams = new ArrayList(Arrays.asList(new String[]{AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, MetricCategory.class.getName(), "DATABASE"}));
    }

    SQLiteInstrumentation() {
    }

    @ReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        TraceMachine.enterMethod("SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        TraceMachine.exitMethod();
        return query;
    }

    @ReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        TraceMachine.enterMethod("SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
        TraceMachine.exitMethod();
        return query;
    }

    @ReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        TraceMachine.enterMethod("SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5);
        TraceMachine.exitMethod();
        return query;
    }

    @ReplaceCallSite
    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        TraceMachine.enterMethod("SQLiteDatabase#query", categoryParams);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        TraceMachine.exitMethod();
        return query;
    }

    @ReplaceCallSite
    public static Cursor queryWithFactory(SQLiteDatabase sQLiteDatabase, CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        TraceMachine.enterMethod("SQLiteDatabase#queryWithFactory", categoryParams);
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        TraceMachine.exitMethod();
        return queryWithFactory;
    }

    @ReplaceCallSite
    public static Cursor queryWithFactory(SQLiteDatabase sQLiteDatabase, CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        TraceMachine.enterMethod("SQLiteDatabase#queryWithFactory", categoryParams);
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
        TraceMachine.exitMethod();
        return queryWithFactory;
    }

    @ReplaceCallSite
    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        TraceMachine.enterMethod("SQLiteDatabase#rawQuery", categoryParams);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
        TraceMachine.exitMethod();
        return rawQuery;
    }

    @ReplaceCallSite
    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, CancellationSignal cancellationSignal) {
        TraceMachine.enterMethod("SQLiteDatabase#rawQuery", categoryParams);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr, cancellationSignal);
        TraceMachine.exitMethod();
        return rawQuery;
    }

    @ReplaceCallSite
    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, CursorFactory cursorFactory, String str, String[] strArr, String str2) {
        TraceMachine.enterMethod("SQLiteDatabase#rawQueryWithFactory", categoryParams);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2);
        TraceMachine.exitMethod();
        return rawQueryWithFactory;
    }

    @ReplaceCallSite
    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, CursorFactory cursorFactory, String str, String[] strArr, String str2, CancellationSignal cancellationSignal) {
        TraceMachine.enterMethod("SQLiteDatabase#rawQueryWithFactory", categoryParams);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2, cancellationSignal);
        TraceMachine.exitMethod();
        return rawQueryWithFactory;
    }

    @ReplaceCallSite
    public static long insert(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        TraceMachine.enterMethod("SQLiteDatabase#insert", categoryParams);
        long insert = sQLiteDatabase.insert(str, str2, contentValues);
        TraceMachine.exitMethod();
        return insert;
    }

    @ReplaceCallSite
    public static long insertOrThrow(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) throws SQLException {
        TraceMachine.enterMethod("SQLiteDatabase#insertOrThrow", categoryParams);
        long insertOrThrow = sQLiteDatabase.insertOrThrow(str, str2, contentValues);
        TraceMachine.exitMethod();
        return insertOrThrow;
    }

    @ReplaceCallSite
    public static long insertWithOnConflict(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues, int i) {
        TraceMachine.enterMethod("SQLiteDatabase#insertWithOnConflict", categoryParams);
        long insertWithOnConflict = sQLiteDatabase.insertWithOnConflict(str, str2, contentValues, i);
        TraceMachine.exitMethod();
        return insertWithOnConflict;
    }

    @ReplaceCallSite
    public static long replace(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        TraceMachine.enterMethod("SQLiteDatabase#replace", categoryParams);
        long replace = sQLiteDatabase.replace(str, str2, contentValues);
        TraceMachine.exitMethod();
        return replace;
    }

    @ReplaceCallSite
    public static long replaceOrThrow(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) throws SQLException {
        TraceMachine.enterMethod("SQLiteDatabase#replaceOrThrow", categoryParams);
        long replaceOrThrow = sQLiteDatabase.replaceOrThrow(str, str2, contentValues);
        TraceMachine.exitMethod();
        return replaceOrThrow;
    }

    @ReplaceCallSite
    public static int delete(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        TraceMachine.enterMethod("SQLiteDatabase#delete", categoryParams);
        int delete = sQLiteDatabase.delete(str, str2, strArr);
        TraceMachine.exitMethod();
        return delete;
    }

    @ReplaceCallSite
    public static int update(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        TraceMachine.enterMethod("SQLiteDatabase#update", categoryParams);
        int update = sQLiteDatabase.update(str, contentValues, str2, strArr);
        TraceMachine.exitMethod();
        return update;
    }

    @ReplaceCallSite
    public static int updateWithOnConflict(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        TraceMachine.enterMethod("SQLiteDatabase#updateWithOnConflict", categoryParams);
        int updateWithOnConflict = sQLiteDatabase.updateWithOnConflict(str, contentValues, str2, strArr, i);
        TraceMachine.exitMethod();
        return updateWithOnConflict;
    }

    @ReplaceCallSite
    public static void execSQL(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        TraceMachine.enterMethod("SQLiteDatabase#execSQL", categoryParams);
        sQLiteDatabase.execSQL(str);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite
    public static void execSQL(SQLiteDatabase sQLiteDatabase, String str, Object[] objArr) throws SQLException {
        TraceMachine.enterMethod("SQLiteDatabase#execSQL", categoryParams);
        sQLiteDatabase.execSQL(str, objArr);
        TraceMachine.exitMethod();
    }
}
