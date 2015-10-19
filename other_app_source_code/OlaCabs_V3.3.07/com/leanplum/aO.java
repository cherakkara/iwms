package com.leanplum;

import android.util.Log;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.olacabs.customer.utils.Constants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class aO {
    final /* synthetic */ ab f8681a;

    aO(ab abVar) {
        this.f8681a = abVar;
    }

    public void m12651a(Exception exception) {
        Log.e("Leanplum", "Development socket error", exception);
    }

    public void m12653b() {
        Log.i("Leanplum", "Disconnected from development server");
        this.f8681a.f8723d = false;
        this.f8681a.f8724e = false;
        this.f8681a.f8721b = false;
    }

    public void m12650a() {
        if (!this.f8681a.f8721b) {
            Log.i("Leanplum", "Connected to development server");
            Map hashMap = new HashMap();
            hashMap.put(AnalyticAttribute.APP_ID_ATTRIBUTE, C0618S.m12532b());
            hashMap.put("deviceId", C0618S.m12537c());
            try {
                aI b = this.f8681a.f8720a;
                JSONArray jSONArray = new JSONArray(Arrays.asList(new JSONObject[]{new JSONObject(hashMap)}));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.BUNDLE_NAME, "auth");
                jSONObject.put("args", jSONArray);
                b.f8672f.post(new aJ(b, jSONObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f8681a.f8721b = true;
            this.f8681a.f8723d = true;
            this.f8681a.f8724e = false;
        }
    }

    public void m12652a(String str, JSONArray jSONArray) {
        if (str.equals("updateVars")) {
            Leanplum.forceContentUpdate();
        } else if (str.equals("trigger")) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(0);
                JSONObject jSONObject2 = jSONObject.getJSONObject("action");
                if (jSONObject2 != null) {
                    String string = jSONObject.getString("messageId");
                    boolean z = jSONObject.getBoolean("isRooted");
                    String string2 = jSONObject2.getString("__name__");
                    Map map = (Map) aT.m12683k().get(string2);
                    ActionContext actionContext = new ActionContext(string2, (Map) aT.m12657a(map != null ? (Map) map.get("values") : null, C0625a.m12600a(jSONObject2)), string);
                    actionContext.m12402a();
                    actionContext.m12404a(z);
                    actionContext.m12406b(true);
                    actionContext.m12405b();
                    Leanplum.m12435a(actionContext);
                    C0629c.m12764a().m12774b(string);
                }
            } catch (Throwable e) {
                Log.e("Leanplum", "Error getting action info", e);
            }
        } else if (str.equals("registerDevice")) {
            String string3;
            Leanplum.m12458d();
            try {
                string3 = jSONArray.getJSONObject(0).getString(Constants.EMAIL);
            } catch (JSONException e2) {
                string3 = null;
            }
            if (string3 == null) {
                string3 = "a Leanplum account";
            }
            C0612M.m12512a().m12513a(new ac(this, string3));
        }
    }
}
