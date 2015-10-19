package com.leanplum;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class aK implements aZ {
    final /* synthetic */ aI f8676a;

    aK(aI aIVar) {
        this.f8676a = aIVar;
    }

    public final void m12645a() {
        aI.m12636a(this.f8676a);
        this.f8676a.f8668b.m12651a(new Exception("Unexpected binary data"));
    }

    public final void m12648a(String str) {
        try {
            String[] split = str.split(":", 4);
            switch (Integer.parseInt(split[0])) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    m12649b();
                    return;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f8676a.f8671e.m12702a("2::");
                    return;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    throw new Exception("message type not supported");
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    JSONArray jSONArray;
                    String str2 = split[1];
                    JSONObject init = JSONObjectInstrumentation.init(split[3]);
                    String string = init.getString(Constants.BUNDLE_NAME);
                    try {
                        jSONArray = init.getJSONArray("args");
                    } catch (JSONException e) {
                        jSONArray = new JSONArray();
                    }
                    if (!Trace.NULL.equals(str2)) {
                        this.f8676a.f8672f.post(new aL(this, str2));
                    }
                    this.f8676a.f8668b.m12652a(string, jSONArray);
                    return;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    return;
                case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                    throw new Exception(str);
                default:
                    throw new Exception("unknown code");
            }
        } catch (Exception e2) {
            aI.m12636a(this.f8676a);
            m12647a(e2);
        }
        aI.m12636a(this.f8676a);
        m12647a(e2);
    }

    public final void m12647a(Exception exception) {
        aI.m12636a(this.f8676a);
        this.f8676a.f8668b.m12651a(exception);
    }

    public final void m12646a(int i, String str) {
        aI.m12636a(this.f8676a);
        this.f8676a.f8668b.m12653b();
    }

    public final void m12649b() {
        this.f8676a.f8672f.postDelayed(new aM(this), (long) this.f8676a.f8670d);
        this.f8676a.f8668b.m12650a();
    }
}
