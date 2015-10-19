package p004b.p005a.p006a.p007a.p008a.p011c;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* renamed from: b.a.a.a.a.c.h */
public class PriorityFutureTask<V> extends FutureTask<V> implements Dependency<Task>, PriorityProvider, Task {
    final Object f243b;

    public /* synthetic */ void addDependency(Object obj) {
        m294a((Task) obj);
    }

    public PriorityFutureTask(Callable<V> callable) {
        super(callable);
        this.f243b = m293a((Object) callable);
    }

    public PriorityFutureTask(Runnable runnable, V v) {
        super(runnable, v);
        this.f243b = m293a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((PriorityProvider) m292a()).compareTo(obj);
    }

    public void m294a(Task task) {
        ((Dependency) ((PriorityProvider) m292a())).addDependency(task);
    }

    public Collection<Task> getDependencies() {
        return ((Dependency) ((PriorityProvider) m292a())).getDependencies();
    }

    public boolean areDependenciesMet() {
        return ((Dependency) ((PriorityProvider) m292a())).areDependenciesMet();
    }

    public Priority getPriority() {
        return ((PriorityProvider) m292a()).getPriority();
    }

    public void setFinished(boolean z) {
        ((Task) ((PriorityProvider) m292a())).setFinished(z);
    }

    public boolean isFinished() {
        return ((Task) ((PriorityProvider) m292a())).isFinished();
    }

    public void setError(Throwable th) {
        ((Task) ((PriorityProvider) m292a())).setError(th);
    }

    public <T extends Dependency<Task> & PriorityProvider & Task> T m292a() {
        return (Dependency) this.f243b;
    }

    protected <T extends Dependency<Task> & PriorityProvider & Task> T m293a(Object obj) {
        if (PriorityTask.isProperDelegate(obj)) {
            return (Dependency) obj;
        }
        return new PriorityTask();
    }
}
