package com.google.android.m4b.maps.p050j;

/* renamed from: com.google.android.m4b.maps.j.a */
public final class BaseThreadFactory implements ThreadFactory {
    private int f7443a;
    private int f7444b;

    /* renamed from: com.google.android.m4b.maps.j.a.a */
    public class BaseThreadFactory extends Thread {
        private String f7440a;
        private Runnable f7441b;
        private /* synthetic */ BaseThreadFactory f7442c;

        public BaseThreadFactory(BaseThreadFactory baseThreadFactory, String str, Runnable runnable) {
            this.f7442c = baseThreadFactory;
            baseThreadFactory.m10535a();
            this.f7440a = str;
            this.f7441b = runnable;
        }

        public final void run() {
            try {
                this.f7442c.m10537b();
                this.f7441b.run();
            } catch (Throwable th) {
                th.printStackTrace();
            } finally {
                this.f7442c.m10539c();
            }
        }

        public final String toString() {
            return "Thread[" + this.f7440a + "," + getPriority() + "]";
        }
    }

    public BaseThreadFactory() {
        this.f7443a = 0;
        this.f7444b = 0;
    }

    public final Thread m10541a(String str, Runnable runnable) {
        return new BaseThreadFactory(this, str, runnable);
    }

    private synchronized void m10535a() {
        this.f7443a++;
    }

    private synchronized void m10537b() {
        this.f7444b++;
    }

    private synchronized void m10539c() {
        this.f7444b--;
    }
}
