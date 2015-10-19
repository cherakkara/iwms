package com.leanplum;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.leanplum.F */
final class C0605F implements aa {
    private final /* synthetic */ Context f8544a;
    private final /* synthetic */ boolean f8545b;

    C0605F(Context context, boolean z) {
        this.f8544a = context;
        this.f8545b = z;
    }

    public final void m12413a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        Leanplum.f8565l = true;
        Leanplum.f8566m = true;
        boolean z = false;
        JSONObject b = C0618S.m12533b(jSONObject);
        if (b == null) {
            Log.e("Leanplum", "No response received from the server. Please contact us to investigate.");
            b = new JSONObject();
            z = true;
        }
        JSONObject optJSONObject = b.optJSONObject("vars");
        if (optJSONObject == null) {
            if (!z) {
                Log.e("Leanplum", "No variable values were received from the server. Please contact us to investigate.");
            }
            jSONObject2 = new JSONObject();
        } else {
            jSONObject2 = optJSONObject;
        }
        optJSONObject = b.optJSONObject("messages");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject optJSONObject2 = b.optJSONObject("regions");
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        }
        JSONArray optJSONArray = b.optJSONArray("variants");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        C0618S.m12538c(b.optString("token", null));
        aT.m12668a(C0625a.m12600a(jSONObject2), C0625a.m12600a(optJSONObject), C0625a.m12600a(optJSONObject2), C0625a.m12598a(optJSONArray));
        Leanplum.m12460d(true);
        if (C0633g.f8804k) {
            Context context;
            if (LeanplumActivityHelper.f8577b == this.f8544a || LeanplumActivityHelper.f8577b == null) {
                context = this.f8544a;
            } else {
                context = LeanplumActivityHelper.f8577b;
            }
            if (!(b.optBoolean("isRegistered") || Leanplum.f8562i == null)) {
                Leanplum.f8562i.setResponseHandler(new C0606G(this));
                C0612M.m12512a().m12513a(Leanplum.f8562i);
            }
            if (b.optBoolean("isRegisteredFromOtherApp")) {
                C0612M.m12512a().m12513a(new C0608I(this, context));
            }
            boolean optBoolean = b.optBoolean("isRegistered");
            String optString = b.optString("latestVersion", null);
            if (optBoolean && optString != null) {
                Log.i("Leanplum", "An update to Leanplum Android SDK, " + optString + ", is available. Go to leanplum.com to download it.");
            }
            jSONObject2 = b.optJSONObject("varsFromCode");
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            JSONObject optJSONObject3 = b.optJSONObject("actionDefinitions");
            if (optJSONObject3 == null) {
                optJSONObject3 = new JSONObject();
            }
            b = b.optJSONObject("fileAttributes");
            if (b == null) {
                b = new JSONObject();
            }
            aT.m12667a(C0625a.m12600a(jSONObject2), C0625a.m12600a(b), C0625a.m12600a(optJSONObject3));
            if (optBoolean) {
                Leanplum.m12458d();
            }
        }
        if (!this.f8545b) {
            Leanplum.m12473q();
        }
    }
}
