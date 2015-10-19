package com.google.android.m4b.maps.cf;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;

/* renamed from: com.google.android.m4b.maps.cf.a */
public final class GoogleSettingsContract {

    /* renamed from: com.google.android.m4b.maps.cf.a.a */
    public static class GoogleSettingsContract implements BaseColumns {
    }

    /* renamed from: com.google.android.m4b.maps.cf.a.b */
    public static final class GoogleSettingsContract extends GoogleSettingsContract {
        private static Uri f7264a;

        public static String m10137a(ContentResolver contentResolver, String str) {
            Cursor cursor;
            Throwable th;
            Cursor cursor2 = null;
            try {
                String string;
                ContentResolver contentResolver2 = contentResolver;
                Cursor query = contentResolver2.query(f7264a, new String[]{"value"}, "name=?", new String[]{str}, null);
                if (query != null) {
                    try {
                        if (query.moveToNext()) {
                            string = query.getString(0);
                            if (query != null) {
                                return string;
                            }
                            query.close();
                            return string;
                        }
                    } catch (SQLException e) {
                        cursor = query;
                        try {
                            new StringBuilder("Can't get key ").append(str).append(" from ").append(f7264a);
                            if (cursor != null) {
                                return null;
                            }
                            cursor.close();
                            return null;
                        } catch (Throwable th2) {
                            cursor2 = cursor;
                            th = th2;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = query;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                string = null;
                if (query != null) {
                    return string;
                }
                query.close();
                return string;
            } catch (SQLException e2) {
                cursor = null;
                new StringBuilder("Can't get key ").append(str).append(" from ").append(f7264a);
                if (cursor != null) {
                    return null;
                }
                cursor.close();
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        }

        static {
            f7264a = Uri.parse("content://com.google.settings/partner");
        }
    }
}
