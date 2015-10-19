package com.facebook.share.internal;

import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.share.internal.j */
public final class OpenGraphJSONUtility {

    /* renamed from: com.facebook.share.internal.j.a */
    public interface OpenGraphJSONUtility {
        JSONObject m1560a(SharePhoto sharePhoto);
    }

    public static JSONObject m1563a(ShareOpenGraphAction shareOpenGraphAction, OpenGraphJSONUtility openGraphJSONUtility) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphAction.m1665c()) {
            jSONObject.put(str, OpenGraphJSONUtility.m1561a(shareOpenGraphAction.m1662a(str), openGraphJSONUtility));
        }
        return jSONObject;
    }

    private static JSONObject m1564a(ShareOpenGraphObject shareOpenGraphObject, OpenGraphJSONUtility openGraphJSONUtility) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphObject.m1665c()) {
            jSONObject.put(str, OpenGraphJSONUtility.m1561a(shareOpenGraphObject.m1662a(str), openGraphJSONUtility));
        }
        return jSONObject;
    }

    private static JSONArray m1562a(List list, OpenGraphJSONUtility openGraphJSONUtility) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object a : list) {
            jSONArray.put(OpenGraphJSONUtility.m1561a(a, openGraphJSONUtility));
        }
        return jSONArray;
    }

    public static Object m1561a(Object obj, OpenGraphJSONUtility openGraphJSONUtility) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            if (openGraphJSONUtility != null) {
                return openGraphJSONUtility.m1560a((SharePhoto) obj);
            }
            return null;
        } else if (obj instanceof ShareOpenGraphObject) {
            return OpenGraphJSONUtility.m1564a((ShareOpenGraphObject) obj, openGraphJSONUtility);
        } else {
            if (obj instanceof List) {
                return OpenGraphJSONUtility.m1562a((List) obj, openGraphJSONUtility);
            }
            throw new IllegalArgumentException("Invalid object found for JSON serialization: " + obj.toString());
        }
    }
}
