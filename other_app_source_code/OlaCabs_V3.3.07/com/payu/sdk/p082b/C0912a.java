package com.payu.sdk.p082b;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.payu.sdk.C0925d;
import com.payu.sdk.C0927e;
import com.payu.sdk.C0927e.C0926a;
import com.payu.sdk.C0930g;
import com.payu.sdk.C0930g.C0929a;
import com.payu.sdk.ProcessPaymentActivity;
import com.payu.sdk.p085a.C0920a;
import com.payu.sdk.p085a.C0921b;

@Instrumented
/* renamed from: com.payu.sdk.b.a */
public class C0912a extends Fragment implements TraceFieldInterface {
    protected void m14699a(C0926a c0926a, C0925d c0925d) {
        try {
            C0930g c0930g = new C0930g();
            c0930g.getClass();
            C0929a c0929a = new C0929a(c0930g);
            c0929a.m14952a("mode", String.valueOf(c0926a));
            for (String str : getActivity().getIntent().getExtras().keySet()) {
                c0929a.m14952a(str, String.valueOf(getActivity().getIntent().getExtras().get(str)));
                c0925d.put(str, c0929a.m14954a(str));
            }
            String str2 = C0927e.m14948a(getActivity()).m14950a(c0929a.m14953a(), c0925d);
            Intent intent = new Intent(getActivity(), ProcessPaymentActivity.class);
            intent.putExtra("postData", str2);
            intent.addFlags(67108864);
            getActivity().startActivityForResult(intent, 100);
        } catch (C0921b e) {
            e.printStackTrace();
        } catch (C0920a e2) {
            e2.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop() {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }
}
