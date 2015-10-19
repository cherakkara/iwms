package com.apsalar.sdk;

import android.os.SystemClock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: ApThread */
class AlarmClock {
    static final ReentrantLock lock;
    private static volatile Thread timer;
    static final Condition wakeUp;

    /* renamed from: com.apsalar.sdk.AlarmClock.1 */
    static class ApThread implements Runnable {
        final /* synthetic */ int val$ttl;

        ApThread(int i) {
            this.val$ttl = i;
        }

        public void run() {
            SystemClock.sleep((long) this.val$ttl);
            AlarmClock.lock.lock();
            AlarmClock.wakeUp.signal();
            AlarmClock.lock.unlock();
        }
    }

    AlarmClock() {
    }

    static {
        lock = new ReentrantLock();
        wakeUp = lock.newCondition();
        timer = null;
    }

    private static void setTimer(int i) {
        if (timer == null || !timer.isAlive()) {
            timer = new Thread(new ApThread(i));
            timer.start();
        }
    }

    public static void start(int i) {
        setTimer(i);
        lock.lock();
        try {
            wakeUp.await();
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public static void interrupt() {
        lock.lock();
        wakeUp.signal();
        lock.unlock();
    }
}
