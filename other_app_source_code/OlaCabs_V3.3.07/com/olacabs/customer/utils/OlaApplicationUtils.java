package com.olacabs.customer.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.google.android.m4b.maps.model.LatLng;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

@Deprecated
/* renamed from: com.olacabs.customer.utils.e */
public class OlaApplicationUtils {
    public static LatLng m14900a(double d, double d2, double d3, double d4) {
        return new LatLng((d - d3) + d, (d2 - d4) + d2);
    }

    public static ArrayList<String> m14901a(Context context, String str) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, null);
        ArrayList<String> arrayList = new ArrayList();
        if (string != null) {
            try {
                JSONArray init = JSONArrayInstrumentation.init(string);
                for (int i = 0; i < init.length(); i++) {
                    arrayList.add(init.optString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static void m14902a(Context context, String str, ArrayList<String> arrayList) {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            jSONArray.put(arrayList.get(i));
        }
        if (arrayList.isEmpty()) {
            edit.putString(str, null);
        } else {
            edit.putString(str, !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray));
        }
        edit.apply();
    }
}
