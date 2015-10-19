package com.google.android.m4b.maps.p039o;

/* renamed from: com.google.android.m4b.maps.o.b */
public class Task extends AbstractTask {
    private int f2991b;

    public Task(TaskRunner taskRunner) {
        this(taskRunner, null, taskRunner.m11056a());
    }

    public Task(TaskRunner taskRunner, Runnable runnable) {
        this(taskRunner, runnable, taskRunner.m11056a());
    }

    private Task(TaskRunner taskRunner, Runnable runnable, int i) {
        this(taskRunner, runnable, null, i);
    }

    public Task(TaskRunner taskRunner, Runnable runnable, String str) {
        this(taskRunner, runnable, str, taskRunner.m11056a());
    }

    private Task(TaskRunner taskRunner, Runnable runnable, String str, int i) {
        super(taskRunner, runnable, str);
        this.f2991b = i;
    }

    int m4758b() {
        return this.a.m11062c(this) ? 1 : 0;
    }

    public final synchronized int m4760h() {
        return this.f2991b;
    }

    void m4759e() {
        this.a.m11058a(this);
    }
}
