package com.newrelic.agent.android.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    final ThreadGroup group;
    final String namePrefix;
    final AtomicInteger threadNumber;

    public NamedThreadFactory(String str) {
        this.threadNumber = new AtomicInteger(1);
        SecurityManager securityManager = System.getSecurityManager();
        this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = "NR_" + str + "-";
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
