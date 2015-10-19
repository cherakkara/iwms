package com.payu.sdk;

import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.olacabs.customer.utils.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.payu.sdk.b */
public class C0923b extends AsyncTask<List<NameValuePair>, String, String> implements TraceFieldInterface {
    public Trace _nr_trace;
    C0913h f11521a;
    JSONObject f11522b;
    private String f11523c;

    public C0923b(C0913h c0913h) {
        this.f11521a = null;
        this.f11523c = com.newrelic.agent.android.instrumentation.Trace.NULL;
        this.f11521a = c0913h;
    }

    private void m14942a(JSONObject jSONObject) {
        C0927e.f11544h = new JSONArray();
        if (jSONObject.has("user_cards")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("user_cards");
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    C0927e.f11544h.put(jSONObject2.getJSONObject((String) keys.next()));
                }
            } catch (JSONException e) {
                this.f11523c = e.getMessage();
            }
        }
    }

    public void _nr_setTrace(Trace trace) {
        try {
            this._nr_trace = trace;
        } catch (Exception e) {
        }
    }

    protected String m14943a(List<NameValuePair>[] listArr) {
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpUriRequest httpPost = new HttpPost("https://info.payu.in/merchant/postservice.php?form=2");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(listArr[0]));
            this.f11522b = JSONObjectInstrumentation.init(EntityUtils.toString((!(defaultHttpClient instanceof HttpClient) ? defaultHttpClient.execute(httpPost) : HttpInstrumentation.execute(defaultHttpClient, httpPost)).getEntity()));
            if (this.f11522b.has(NotificationCompatApi21.CATEGORY_STATUS) && this.f11522b.getInt(NotificationCompatApi21.CATEGORY_STATUS) == 0) {
                return this.f11522b.has(NotificationCompatApi21.CATEGORY_MESSAGE) ? this.f11522b.getString(NotificationCompatApi21.CATEGORY_MESSAGE) : this.f11522b.getString(NotificationCompatApi21.CATEGORY_STATUS);
            } else {
                int i;
                JSONObject jSONObject;
                String str;
                if (this.f11522b.length() > 0 && ((NameValuePair) listArr[0].get(1)).getValue().contentEquals("payment_related_details_for_mobile_sdk")) {
                    try {
                        Boolean bool;
                        Iterator keys;
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2 = new JSONObject();
                        JSONObject jSONObject3 = this.f11522b.getJSONObject("userCards");
                        JSONObject jSONObject4 = this.f11522b.getJSONObject("ibiboCodes");
                        C0927e.f11537a = new JSONArray();
                        C0927e.f11538b = new JSONArray();
                        C0927e.f11539c = new JSONArray();
                        C0927e.f11540d = new JSONArray();
                        C0927e.f11541e = new JSONArray();
                        C0927e.f11542f = new JSONArray();
                        Boolean valueOf = Boolean.valueOf(false);
                        Boolean valueOf2 = Boolean.valueOf(false);
                        Boolean valueOf3 = Boolean.valueOf(false);
                        Boolean valueOf4 = Boolean.valueOf(false);
                        Boolean valueOf5 = Boolean.valueOf(false);
                        Set hashSet = new HashSet();
                        Set hashSet2 = new HashSet();
                        if (C0927e.f11548l != null) {
                            String[] split = C0927e.f11548l.split("\\|");
                            for (Object add : split) {
                                hashSet.add(add);
                            }
                        }
                        if (C0927e.f11547k != null) {
                            String[] split2 = C0927e.f11547k.split(",");
                            for (String split3 : split2) {
                                String[] split4 = split3.split("\\|");
                                for (Object add2 : split4) {
                                    hashSet2.add(add2);
                                }
                            }
                        }
                        if (jSONObject4.has("netbanking")) {
                            jSONObject = new JSONObject();
                            jSONObject.put("code", " ");
                            jSONObject.put("title", " ");
                            C0927e.f11537a.put(jSONObject);
                            Iterator keys2 = jSONObject4.getJSONObject("netbanking").keys();
                            if (C0927e.f11548l == null || hashSet.contains("netbanking")) {
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    if (!hashSet2.contains(str)) {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put("title", jSONObject4.getJSONObject("netbanking").getJSONObject(str).get("title"));
                                        C0927e.f11537a.put(jSONObject);
                                    }
                                }
                                valueOf3 = Boolean.valueOf(true);
                            } else {
                                bool = valueOf3;
                                while (keys2.hasNext()) {
                                    str = (String) keys2.next();
                                    if (!hashSet.contains(str) || hashSet2.contains(str)) {
                                        valueOf3 = bool;
                                    } else {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put("title", jSONObject4.getJSONObject("netbanking").getJSONObject(str).get("title"));
                                        C0927e.f11537a.put(jSONObject);
                                        valueOf3 = Boolean.valueOf(true);
                                    }
                                    bool = valueOf3;
                                }
                                valueOf3 = bool;
                            }
                            C0927e.f11537a = m14944a(C0927e.f11537a, "title");
                            jSONObject = new JSONObject();
                            jSONObject.put("code", "default");
                            jSONObject.put("title", "Select your bank");
                            C0927e.f11537a.put(0, jSONObject);
                        }
                        Boolean bool2 = valueOf3;
                        if (jSONObject4.has("emi")) {
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("emiCode", " ");
                            jSONObject2.put("emiInterval", " ");
                            jSONObject2.put("bankName", " ");
                            C0927e.f11538b.put(jSONObject2);
                            keys = jSONObject4.getJSONObject("emi").keys();
                            if (C0927e.f11548l == null || hashSet.contains("Emi")) {
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet2.contains(str)) {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("emiCode", str);
                                        jSONObject.put("emiInterval", jSONObject4.getJSONObject("emi").getJSONObject(str).get("title").toString());
                                        jSONObject.put("bankName", jSONObject4.getJSONObject("emi").getJSONObject(str).get("bank").toString());
                                        C0927e.f11538b.put(jSONObject);
                                    }
                                }
                                valueOf3 = Boolean.valueOf(true);
                            } else {
                                bool = valueOf4;
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet.contains(str) || hashSet2.contains(str)) {
                                        valueOf3 = bool;
                                    } else {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("emiCode", str);
                                        jSONObject.put("emiInterval", jSONObject4.getJSONObject("emi").getJSONObject(str).get("title").toString());
                                        jSONObject.put("bankName", jSONObject4.getJSONObject("emi").getJSONObject(str).get("bank").toString());
                                        C0927e.f11538b.put(jSONObject);
                                        valueOf3 = Boolean.valueOf(true);
                                    }
                                    bool = valueOf3;
                                }
                                valueOf3 = bool;
                            }
                            C0927e.f11538b = m14944a(C0927e.f11538b, "bankName");
                            jSONObject = new JSONObject();
                            jSONObject.put("emiCode", "default");
                            jSONObject.put("emiInterval", "Select Duration");
                            jSONObject.put("bankName", "Select Bank");
                            C0927e.f11538b.put(0, jSONObject);
                            valueOf4 = valueOf3;
                        }
                        if (jSONObject4.has("cashcard")) {
                            keys = jSONObject4.getJSONObject("cashcard").keys();
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("code", " ");
                            jSONObject2.put(Constants.BUNDLE_NAME, " ");
                            C0927e.f11539c.put(jSONObject2);
                            if (C0927e.f11548l == null || hashSet.contains("cashcard")) {
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet2.contains(str)) {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("cashcard").getJSONObject(str).getString("title"));
                                        C0927e.f11539c.put(jSONObject);
                                    }
                                }
                                valueOf3 = Boolean.valueOf(true);
                            } else {
                                bool = valueOf5;
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    JSONObject jSONObject5 = new JSONObject();
                                    if (!hashSet.contains(str) || hashSet2.contains(str)) {
                                        valueOf3 = bool;
                                    } else {
                                        jSONObject5.put("code", str);
                                        jSONObject5.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("cashcard").getJSONObject(str).getString("title"));
                                        C0927e.f11539c.put(jSONObject5);
                                        valueOf3 = Boolean.valueOf(true);
                                    }
                                    bool = valueOf3;
                                }
                                valueOf3 = bool;
                            }
                            C0927e.f11539c = m14944a(C0927e.f11539c, Constants.BUNDLE_NAME);
                            jSONObject = new JSONObject();
                            jSONObject.put("code", "default");
                            jSONObject.put(Constants.BUNDLE_NAME, "Select your cash card");
                            C0927e.f11539c.put(0, jSONObject);
                            valueOf5 = valueOf3;
                        }
                        if (jSONObject4.has("creditcard")) {
                            keys = jSONObject4.getJSONObject("creditcard").keys();
                            if (C0927e.f11548l == null || hashSet.contains("creditcard")) {
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet2.contains(str)) {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("creditcard").getJSONObject(str).getString("title"));
                                        C0927e.f11542f.put(jSONObject);
                                    }
                                }
                                valueOf = Boolean.valueOf(true);
                            } else {
                                bool = valueOf;
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet.contains(str) || hashSet2.contains(str)) {
                                        valueOf3 = bool;
                                    } else {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("creditcard").getJSONObject(str).getString("title"));
                                        C0927e.f11542f.put(jSONObject);
                                        valueOf3 = Boolean.valueOf(true);
                                    }
                                    bool = valueOf3;
                                }
                                valueOf = bool;
                            }
                        }
                        if (jSONObject4.has("debitcard")) {
                            keys = jSONObject4.getJSONObject("debitcard").keys();
                            if (C0927e.f11548l == null || hashSet.contains("debitcard")) {
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet2.contains(str)) {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("debitcard").getJSONObject(str).getString("title"));
                                        C0927e.f11541e.put(jSONObject);
                                    }
                                }
                                bool = Boolean.valueOf(true);
                            } else {
                                bool = valueOf2;
                                while (keys.hasNext()) {
                                    str = (String) keys.next();
                                    if (!hashSet.contains(str) || hashSet2.contains(str)) {
                                        valueOf3 = bool;
                                    } else {
                                        jSONObject = new JSONObject();
                                        jSONObject.put("code", str);
                                        jSONObject.put(Constants.BUNDLE_NAME, jSONObject4.getJSONObject("debitcard").getJSONObject(str).getString("title"));
                                        C0927e.f11541e.put(jSONObject);
                                        valueOf3 = Boolean.valueOf(true);
                                    }
                                    bool = valueOf3;
                                }
                            }
                        } else {
                            bool = valueOf2;
                        }
                        if (C0927e.f11549m != null) {
                            C0927e.f11540d.put("STORED_CARDS");
                        }
                        if (valueOf.booleanValue() || r1.booleanValue()) {
                            C0927e.f11540d.put("CC");
                        }
                        if (bool2.booleanValue()) {
                            C0927e.f11540d.put("NB");
                        }
                        if (valueOf4.booleanValue()) {
                            C0927e.f11540d.put("EMI");
                        }
                        if (valueOf5.booleanValue()) {
                            C0927e.f11540d.put("CASH");
                        }
                        Iterator keys3 = jSONObject4.keys();
                        if (((C0927e.f11548l == null || hashSet.contains("paisawallet")) && C0927e.f11547k == null) || !hashSet2.contains("paisawallet")) {
                            while (keys3.hasNext()) {
                                if (((String) keys3.next()).contentEquals("paisawallet")) {
                                    C0927e.f11540d.put("PAYU_MONEY");
                                }
                            }
                        }
                        if (jSONObject3.length() > 0) {
                            m14942a(jSONObject3);
                        }
                        C0927e.f11559w = System.currentTimeMillis();
                        return null;
                    } catch (JSONException e) {
                        this.f11523c = e.getMessage();
                    }
                } else if (this.f11522b.length() > 0 && ((NameValuePair) listArr[0].get(1)).getValue().contentEquals("vas_for_mobile_sdk")) {
                    C0927e.f11545i = new HashMap();
                    if (this.f11522b.has("netBankingStatus")) {
                        try {
                            jSONObject = this.f11522b.getJSONObject("netBankingStatus");
                            Iterator keys4 = jSONObject.keys();
                            while (keys4.hasNext()) {
                                str = (String) keys4.next();
                                C0927e.f11545i.put(str, Integer.valueOf(jSONObject.getJSONObject(str).getInt("up_status")));
                            }
                        } catch (JSONException e2) {
                            this.f11523c = e2.getMessage();
                        }
                    }
                    if (this.f11522b.has("issuingBankDownBins")) {
                        int i2;
                        C0927e.f11546j = new JSONObject();
                        try {
                            JSONArray jSONArray = this.f11522b.getJSONArray("issuingBankDownBins");
                            int length = jSONArray.length();
                            for (i = 0; i < length; i++) {
                                if (jSONArray.getJSONObject(i).getInt(NotificationCompatApi21.CATEGORY_STATUS) == 0) {
                                    int length2 = jSONArray.getJSONObject(i).getJSONArray("bins_arr").length();
                                    for (i2 = 0; i2 < length2; i2++) {
                                        C0927e.f11546j.put(jSONArray.getJSONObject(i).getJSONArray("bins_arr").getString(i2), jSONArray.getJSONObject(i).getString("title"));
                                    }
                                }
                            }
                        } catch (JSONException e22) {
                            this.f11523c = e22.getMessage();
                        }
                        if (this.f11522b.has("sbiMaesBins")) {
                            try {
                                JSONArray jSONArray2 = this.f11522b.getJSONArray("sbiMaesBins");
                                int length3 = jSONArray2.length();
                                for (i2 = 0; i2 < length3; i2++) {
                                    if (!C0922a.f11520a.contains(jSONArray2.getString(i2))) {
                                        C0922a.f11520a.add(jSONArray2.getString(i2));
                                    }
                                }
                            } catch (JSONException e222) {
                                e222.printStackTrace();
                            }
                        }
                    }
                } else if (this.f11522b.length() > 0 && ((NameValuePair) listArr[0].get(1)).getValue().contentEquals("get_user_cards")) {
                    m14942a(this.f11522b);
                } else if (this.f11522b.length() > 0 && ((NameValuePair) listArr[0].get(1)).getValue().contentEquals("check_offer_status") && this.f11522b.has("offer_key")) {
                    C0927e.f11543g = new JSONArray();
                    C0927e.f11543g.put(this.f11522b);
                }
                if (this.f11522b.has(NotificationCompatApi21.CATEGORY_MESSAGE)) {
                    try {
                        return this.f11522b.getString(NotificationCompatApi21.CATEGORY_MESSAGE);
                    } catch (JSONException e2222) {
                        this.f11523c = e2222.getMessage();
                    }
                }
                return this.f11523c.length() > 1 ? this.f11523c : "Data fetched successfully.";
            }
        } catch (UnsupportedEncodingException e3) {
            return e3.getMessage();
        } catch (ClientProtocolException e4) {
            e4.printStackTrace();
            return e4.getMessage();
        } catch (JSONException e22222) {
            e22222.printStackTrace();
            return e22222.getMessage();
        } catch (IOException e5) {
            e5.printStackTrace();
            return e5.getMessage();
        }
    }

    public JSONArray m14944a(JSONArray jSONArray, String str) throws JSONException {
        int i = 0;
        JSONArray jSONArray2 = new JSONArray();
        List arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.getJSONObject(i2));
        }
        Collections.sort(arrayList, new C0924c(this, str));
        while (i < jSONArray.length()) {
            jSONArray2.put(arrayList.get(i));
            i++;
        }
        return jSONArray2;
    }

    protected void m14945a(String str) {
        this.f11521a.m14700a(str);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#doInBackground", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#doInBackground", null);
                break;
            }
        }
        String a = m14943a((List[]) objArr);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return a;
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "b#onPostExecute", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "b#onPostExecute", null);
                break;
            }
        }
        m14945a((String) obj);
        TraceMachine.exitMethod();
    }
}
