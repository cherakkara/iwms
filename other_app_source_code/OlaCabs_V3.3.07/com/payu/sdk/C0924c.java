package com.payu.sdk;

import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.payu.sdk.c */
class C0924c implements Comparator<JSONObject> {
    final /* synthetic */ String f11524a;
    final /* synthetic */ C0923b f11525b;

    C0924c(C0923b c0923b, String str) {
        this.f11525b = c0923b;
        this.f11524a = str;
    }

    public int m14946a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        JSONException e;
        String str2 = new String();
        String str3 = new String();
        try {
            str = (String) jSONObject.get(this.f11524a);
            try {
                str2 = (String) jSONObject2.get(this.f11524a);
            } catch (JSONException e2) {
                e = e2;
                this.f11525b.f11523c = e.getMessage();
                str2 = str3;
                return str.compareTo(str2);
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            str = str2;
            e = jSONException;
            this.f11525b.f11523c = e.getMessage();
            str2 = str3;
            return str.compareTo(str2);
        }
        return str.compareTo(str2);
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m14946a((JSONObject) obj, (JSONObject) obj2);
    }
}
