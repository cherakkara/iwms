package p004b.p005a.p006a.p007a.p008a.p011c;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import p004b.p005a.p006a.p007a.p008a.p011c.AsyncTask.AsyncTask;

/* renamed from: b.a.a.a.a.c.f */
public abstract class PriorityAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements Dependency<Task>, PriorityProvider, Task {
    private final PriorityTask f247a;

    /* renamed from: b.a.a.a.a.c.f.a */
    private static class PriorityAsyncTask<Result> implements Executor {
        private final Executor f245a;
        private final PriorityAsyncTask f246b;

        /* renamed from: b.a.a.a.a.c.f.a.1 */
        class PriorityAsyncTask extends PriorityFutureTask<Result> {
            final /* synthetic */ PriorityAsyncTask f244a;

            PriorityAsyncTask(PriorityAsyncTask priorityAsyncTask, Runnable runnable, Object obj) {
                this.f244a = priorityAsyncTask;
                super(runnable, obj);
            }

            public <T extends Dependency<Task> & PriorityProvider & Task> T m295a() {
                return this.f244a.f246b;
            }
        }

        public PriorityAsyncTask(Executor executor, PriorityAsyncTask priorityAsyncTask) {
            this.f245a = executor;
            this.f246b = priorityAsyncTask;
        }

        public void execute(Runnable runnable) {
            this.f245a.execute(new PriorityAsyncTask(this, runnable, null));
        }
    }

    public /* synthetic */ void addDependency(Object obj) {
        m297a((Task) obj);
    }

    public PriorityAsyncTask() {
        this.f247a = new PriorityTask();
    }

    public final void m298a(ExecutorService executorService, Params... paramsArr) {
        super.m270a(new PriorityAsyncTask(executorService, this), (Object[]) paramsArr);
    }

    public int compareTo(Object obj) {
        return Priority.m291a(this, obj);
    }

    public void m297a(Task task) {
        if (m275b() != AsyncTask.PENDING) {
            throw new IllegalStateException("Must not add Dependency after task is running");
        }
        ((Dependency) ((PriorityProvider) m299e())).addDependency(task);
    }

    public Collection<Task> getDependencies() {
        return ((Dependency) ((PriorityProvider) m299e())).getDependencies();
    }

    public boolean areDependenciesMet() {
        return ((Dependency) ((PriorityProvider) m299e())).areDependenciesMet();
    }

    public Priority getPriority() {
        return ((PriorityProvider) m299e()).getPriority();
    }

    public void setFinished(boolean z) {
        ((Task) ((PriorityProvider) m299e())).setFinished(z);
    }

    public boolean isFinished() {
        return ((Task) ((PriorityProvider) m299e())).isFinished();
    }

    public void setError(Throwable th) {
        ((Task) ((PriorityProvider) m299e())).setError(th);
    }

    public <T extends Dependency<Task> & PriorityProvider & Task> T m299e() {
        return this.f247a;
    }
}
