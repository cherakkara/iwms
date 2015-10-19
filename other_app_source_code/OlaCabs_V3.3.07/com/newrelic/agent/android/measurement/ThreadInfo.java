package com.newrelic.agent.android.measurement;

public class ThreadInfo {
    private long id;
    private String name;

    public ThreadInfo() {
        this(Thread.currentThread());
    }

    public ThreadInfo(long j, String str) {
        this.id = j;
        this.name = str;
    }

    public ThreadInfo(Thread thread) {
        this(thread.getId(), thread.getName());
    }

    public static ThreadInfo fromThread(Thread thread) {
        return new ThreadInfo(thread);
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "ThreadInfo{id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
