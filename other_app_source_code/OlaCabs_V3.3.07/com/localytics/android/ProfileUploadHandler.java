package com.localytics.android;

import android.net.Uri.Builder;
import com.apsalar.sdk.Constants;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

final class ProfileUploadHandler extends BaseUploadThread {
    private static final String PROFILE_URL = "v1/apps/%s/profiles";

    ProfileUploadHandler(BaseHandler baseHandler, TreeMap<Integer, Object> treeMap, String str, LocalyticsDao localyticsDao) {
        super(baseHandler, treeMap, str, localyticsDao);
    }

    int uploadData() {
        try {
            if (!this.mData.isEmpty()) {
                Iterator it = this.mData.entrySet().iterator();
                StringBuilder stringBuilder = new StringBuilder();
                String apiKey = getApiKey();
                String str = null;
                while (it.hasNext()) {
                    Object[] objArr = (Object[]) ((Entry) it.next()).getValue();
                    if (stringBuilder.length() == 0) {
                        str = (String) objArr[0];
                        stringBuilder.append('{').append('\"').append("customer_id").append('\"').append(':').append('\"').append(str).append('\"').append(',').append('\"').append("database").append('\"').append(':').append('\"').append((String) objArr[1]).append('\"').append(',').append('\"').append("changes").append('\"').append(':').append('[');
                    }
                    stringBuilder.append((String) objArr[2]);
                    if (it.hasNext()) {
                        stringBuilder.append(',');
                    }
                }
                stringBuilder.append("]}");
                if (upload(UploadType.PROFILES, new Builder().scheme(Constants.API_PROTOCOL).authority(Constants.PROFILES_HOST).encodedPath(String.format(PROFILE_URL, new Object[]{apiKey})).appendPath(str).toString(), stringBuilder.toString(), 0)) {
                    return ((Integer) this.mData.lastKey()).intValue();
                }
            }
            return 0;
        } catch (Throwable th) {
            return 0;
        }
    }
}
