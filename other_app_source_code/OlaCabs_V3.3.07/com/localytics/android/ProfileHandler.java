package com.localytics.android;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Looper;
import android.os.Message;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

class ProfileHandler extends BaseHandler {
    static final String ATTRIBUTE_JSON_KEY_KEY = "attr";
    static final String ATTRIBUTE_JSON_OP_KEY = "op";
    static final String ATTRIBUTE_JSON_VALUE_KEY = "value";
    private static final int MESSAGE_SET_PROFILE_ATTRIBUTE = 301;

    /* renamed from: com.localytics.android.ProfileHandler.1 */
    class C07181 implements Runnable {
        final /* synthetic */ String val$attribute;
        final /* synthetic */ String val$customerID;
        final /* synthetic */ String val$database;

        C07181(String str, String str2, String str3) {
            this.val$attribute = str;
            this.val$database = str2;
            this.val$customerID = str3;
        }

        public void run() {
            ProfileHandler.this._setAttribute(this.val$attribute, this.val$database, this.val$customerID);
        }
    }

    private final class ProfileListenersSet extends ListenersSet {
        private ProfileListenersSet() {
            super();
        }
    }

    enum ProfileOperation {
        ASSIGN("assign"),
        DELETE("delete"),
        SETADD("set-add"),
        SETREMOVE("set-remove"),
        INCREMENT("increment");
        
        private final String operation;

        private ProfileOperation(String str) {
            this.operation = str;
        }

        public String getOperationString() {
            return this.operation;
        }
    }

    ProfileHandler(LocalyticsDao localyticsDao, Looper looper) {
        super(localyticsDao, looper);
        this.siloName = "Profile";
        this.listeners = new ProfileListenersSet();
        queueMessage(obtainMessage(1));
    }

    protected void handleMessageExtended(Message message) throws Exception {
        switch (message.what) {
            case MESSAGE_SET_PROFILE_ATTRIBUTE /*301*/:
                Log.m12793d("Profile handler received MESSAGE_SET_PROFILE_ATTRIBUTE");
                Object[] objArr = (Object[]) message.obj;
                this.mProvider.runBatchTransaction(new C07181((String) objArr[0], (String) objArr[1], (String) objArr[2]));
            default:
                super.handleMessageExtended(message);
        }
    }

    protected void _init() {
        this.mProvider = new ProfileProvider(this.siloName.toLowerCase(), this.mLocalyticsDao);
        this.mProvider.vacuumIfNecessary();
    }

    protected void _deleteUploadedData(int i) {
        this.mProvider.remove("changes", "_id <= " + i, null);
    }

    protected void _onUploadCompleted(String str) {
        this.mProvider.vacuumIfNecessary();
    }

    protected int _getMaxRowToUpload() {
        Cursor cursor;
        try {
            Cursor query = this.mProvider.query("changes", new String[]{"_id"}, null, null, "_id ASC");
            try {
                int i;
                if (query.moveToLast()) {
                    i = query.getInt(query.getColumnIndexOrThrow("_id"));
                } else {
                    i = 0;
                }
                if (query == null) {
                    return i;
                }
                query.close();
                return i;
            } catch (Throwable th) {
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            }
        } catch (Throwable th2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.util.TreeMap<java.lang.Integer, java.lang.Object> _getDataToUpload() {
        /*
        r10 = this;
        r7 = 0;
        r6 = new java.util.TreeMap;
        r6.<init>();
        r0 = r10.mProvider;	 Catch:{ all -> 0x0080 }
        r1 = "changes";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = "_id ASC";
        r0 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0080 }
        r1 = r7;
    L_0x0014:
        r2 = r0.moveToNext();	 Catch:{ all -> 0x0071 }
        if (r2 == 0) goto L_0x0079;
    L_0x001a:
        r2 = r6.size();	 Catch:{ all -> 0x0071 }
        r2 = (double) r2;	 Catch:{ all -> 0x0071 }
        r4 = 4632233691727265792; // 0x4049000000000000 float:0.0 double:50.0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0079;
    L_0x0025:
        r2 = "_id";
        r2 = r0.getColumnIndexOrThrow(r2);	 Catch:{ all -> 0x0071 }
        r4 = r0.getInt(r2);	 Catch:{ all -> 0x0071 }
        r2 = "change";
        r2 = r0.getColumnIndexOrThrow(r2);	 Catch:{ all -> 0x0071 }
        r5 = r0.getString(r2);	 Catch:{ all -> 0x0071 }
        r2 = "customer_id";
        r2 = r0.getColumnIndexOrThrow(r2);	 Catch:{ all -> 0x0071 }
        r3 = r0.getString(r2);	 Catch:{ all -> 0x0071 }
        r2 = "scope";
        r2 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x0071 }
        r2 = r0.getString(r2);	 Catch:{ all -> 0x0071 }
        if (r1 != 0) goto L_0x0051;
    L_0x004f:
        r7 = r2;
        r1 = r3;
    L_0x0051:
        r8 = r1.equals(r3);	 Catch:{ all -> 0x0071 }
        if (r8 == 0) goto L_0x0079;
    L_0x0057:
        r8 = r7.equals(r2);	 Catch:{ all -> 0x0071 }
        if (r8 == 0) goto L_0x0079;
    L_0x005d:
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0071 }
        r8 = 3;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x0071 }
        r9 = 0;
        r8[r9] = r3;	 Catch:{ all -> 0x0071 }
        r3 = 1;
        r8[r3] = r2;	 Catch:{ all -> 0x0071 }
        r2 = 2;
        r8[r2] = r5;	 Catch:{ all -> 0x0071 }
        r6.put(r4, r8);	 Catch:{ all -> 0x0071 }
        goto L_0x0014;
    L_0x0071:
        r1 = move-exception;
    L_0x0072:
        if (r0 == 0) goto L_0x0077;
    L_0x0074:
        r0.close();
    L_0x0077:
        r0 = r6;
    L_0x0078:
        return r0;
    L_0x0079:
        if (r0 == 0) goto L_0x007e;
    L_0x007b:
        r0.close();
    L_0x007e:
        r0 = r6;
        goto L_0x0078;
    L_0x0080:
        r0 = move-exception;
        r0 = r7;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.localytics.android.ProfileHandler._getDataToUpload():java.util.TreeMap<java.lang.Integer, java.lang.Object>");
    }

    protected BaseUploadThread getUploadThread(TreeMap<Integer, Object> treeMap, String str) {
        return new ProfileUploadHandler(this, treeMap, str, this.mLocalyticsDao);
    }

    private void saveAttributeChange(ProfileOperation profileOperation, String str, Object obj, String str2) {
        if (checkAttributeName(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ATTRIBUTE_JSON_OP_KEY, profileOperation.getOperationString());
                jSONObject.put(ATTRIBUTE_JSON_KEY_KEY, str);
                if (obj instanceof Object[]) {
                    JSONArray jSONArray = new JSONArray();
                    for (Object put : (Object[]) obj) {
                        jSONArray.put(put);
                    }
                    jSONObject.put(ATTRIBUTE_JSON_VALUE_KEY, jSONArray);
                } else if (obj != null) {
                    jSONObject.put(ATTRIBUTE_JSON_VALUE_KEY, obj);
                }
                Object obj2 = new Object[3];
                obj2[0] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                obj2[1] = str2;
                obj2[2] = this.mLocalyticsDao.getCustomerIdInMemory();
                queueMessage(obtainMessage(MESSAGE_SET_PROFILE_ATTRIBUTE, obj2));
            } catch (Throwable e) {
                Log.m12802w("Caught JSON exception", e);
            }
        }
    }

    void setProfileAttribute(String str, long j, String str2) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, Long.valueOf(j), str2);
    }

    void setProfileAttribute(String str, long[] jArr, String str2) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, convertToObjectArray(jArr), str2);
    }

    void setProfileAttribute(String str, String str2, String str3) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, str2, str3);
    }

    void setProfileAttribute(String str, String[] strArr, String str2) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, strArr, str2);
    }

    void setProfileAttribute(String str, Date date, String str2) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, convertDateToString(date), str2);
    }

    void setProfileAttribute(String str, Date[] dateArr, String str2) {
        saveAttributeChange(ProfileOperation.ASSIGN, str, convertDateToString(dateArr), str2);
    }

    void deleteProfileAttribute(String str, String str2) {
        saveAttributeChange(ProfileOperation.DELETE, str, null, str2);
    }

    void addProfileAttributesToSet(String str, long[] jArr, String str2) {
        saveAttributeChange(ProfileOperation.SETADD, str, convertToObjectArray(jArr), str2);
    }

    void addProfileAttributesToSet(String str, String[] strArr, String str2) {
        saveAttributeChange(ProfileOperation.SETADD, str, strArr, str2);
    }

    void addProfileAttributesToSet(String str, Date[] dateArr, String str2) {
        saveAttributeChange(ProfileOperation.SETADD, str, convertDateToString(dateArr), str2);
    }

    void removeProfileAttributesFromSet(String str, long[] jArr, String str2) {
        saveAttributeChange(ProfileOperation.SETREMOVE, str, convertToObjectArray(jArr), str2);
    }

    void removeProfileAttributesFromSet(String str, String[] strArr, String str2) {
        saveAttributeChange(ProfileOperation.SETREMOVE, str, strArr, str2);
    }

    void removeProfileAttributesFromSet(String str, Date[] dateArr, String str2) {
        saveAttributeChange(ProfileOperation.SETREMOVE, str, convertDateToString(dateArr), str2);
    }

    void incrementProfileAttribute(String str, long j, String str2) {
        saveAttributeChange(ProfileOperation.INCREMENT, str, Long.valueOf(j), str2);
    }

    private static boolean checkAttributeName(String str) {
        if (str == null) {
            LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attribute name cannot be null");
            return false;
        } else if (str.trim().length() != 0) {
            return true;
        } else {
            LocalyticsManager.throwOrLogError(IllegalArgumentException.class, "attribute name cannot be empty");
            return false;
        }
    }

    private static Object convertDateToString(Object obj) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (obj instanceof Date) {
            return simpleDateFormat.format(obj);
        }
        if (!(obj instanceof Date[])) {
            return null;
        }
        List arrayList = new ArrayList();
        for (Date date : (Date[]) obj) {
            if (date != null) {
                arrayList.add(simpleDateFormat.format(date));
            } else {
                arrayList.add(null);
            }
        }
        return arrayList.toArray();
    }

    private static Long[] convertToObjectArray(long[] jArr) {
        int i = 0;
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        int i2 = 0;
        while (i < length) {
            lArr[i2] = Long.valueOf(jArr[i]);
            i2++;
            i++;
        }
        return lArr;
    }

    private void _setAttribute(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("scope", str2);
        contentValues.put("change", str);
        contentValues.put("customer_id", str3);
        this.mProvider.insert("changes", contentValues);
    }
}
