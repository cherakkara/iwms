package p004b.p005a.p006a.p007a.p008a.p011c.p012a;

/* renamed from: b.a.a.a.a.c.a.c */
public class ExponentialBackoff implements Backoff {
    private final long f217a;
    private final int f218b;

    public ExponentialBackoff(long j, int i) {
        this.f217a = j;
        this.f218b = i;
    }

    public long getDelayMillis(int i) {
        return (long) (((double) this.f217a) * Math.pow((double) this.f218b, (double) i));
    }
}
