package p004b.p005a.p006a.p007a.p008a.p011c.p012a;

/* renamed from: b.a.a.a.a.c.a.e */
public class RetryState {
    private final int f219a;
    private final Backoff f220b;
    private final RetryPolicy f221c;

    public RetryState(Backoff backoff, RetryPolicy retryPolicy) {
        this(0, backoff, retryPolicy);
    }

    public RetryState(int i, Backoff backoff, RetryPolicy retryPolicy) {
        this.f219a = i;
        this.f220b = backoff;
        this.f221c = retryPolicy;
    }

    public long m260a() {
        return this.f220b.getDelayMillis(this.f219a);
    }

    public RetryState m261b() {
        return new RetryState(this.f219a + 1, this.f220b, this.f221c);
    }

    public RetryState m262c() {
        return new RetryState(this.f220b, this.f221c);
    }
}
