package p004b.p005a.p006a.p007a;

import com.crashlytics.android.core.CrashlyticsCore;
import p004b.p005a.p006a.p007a.p008a.p010b.TimingMetric;
import p004b.p005a.p006a.p007a.p008a.p011c.Priority;
import p004b.p005a.p006a.p007a.p008a.p011c.PriorityAsyncTask;
import p004b.p005a.p006a.p007a.p008a.p011c.UnmetDependencyException;

/* renamed from: b.a.a.a.h */
class InitializationTask<Result> extends PriorityAsyncTask<Void, Void, Result> {
    final Kit<Result> f430a;

    public InitializationTask(Kit<Result> kit) {
        this.f430a = kit;
    }

    protected void m534a() {
        super.m272a();
        TimingMetric a = m531a("onPreExecute");
        try {
            boolean onPreExecute = this.f430a.onPreExecute();
            a.m258b();
            if (!onPreExecute) {
                m274a(true);
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Throwable e2) {
            Fabric.m512h().m482e(CrashlyticsCore.TAG, "Failure onPreExecute()", e2);
            a.m258b();
            m274a(true);
        } catch (Throwable th) {
            a.m258b();
            m274a(true);
        }
    }

    protected Result m533a(Void... voidArr) {
        TimingMetric a = m531a("doInBackground");
        Result result = null;
        if (!m279d()) {
            result = this.f430a.doInBackground();
        }
        a.m258b();
        return result;
    }

    protected void m535a(Result result) {
        this.f430a.onPostExecute(result);
        this.f430a.initializationCallback.m497a((Object) result);
    }

    protected void m536b(Result result) {
        this.f430a.onCancelled(result);
        this.f430a.initializationCallback.m496a(new InitializationException(this.f430a.getIdentifier() + " Initialization was cancelled"));
    }

    public Priority getPriority() {
        return Priority.HIGH;
    }

    private TimingMetric m531a(String str) {
        TimingMetric timingMetric = new TimingMetric(this.f430a.getIdentifier() + "." + str, "KitInitialization");
        timingMetric.m257a();
        return timingMetric;
    }
}
