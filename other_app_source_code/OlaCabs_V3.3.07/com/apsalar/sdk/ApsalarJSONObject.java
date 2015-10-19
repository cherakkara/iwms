package com.apsalar.sdk;

import org.json.JSONException;
import org.json.JSONObject;

class ApsalarJSONObject extends JSONObject {
    public ApsalarJSONObject(String str) throws JSONException {
        super(str);
    }

    String getString(String str, String str2) {
        if (has(str)) {
            try {
                str2 = getString(str);
            } catch (JSONException e) {
            }
        }
        return str2;
    }

    int getInt(String str, int i) {
        if (has(str)) {
            try {
                i = getInt(str);
            } catch (JSONException e) {
            }
        }
        return i;
    }
}
