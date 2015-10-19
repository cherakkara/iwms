package p000a.p001a.p002a;

/* renamed from: a.a.a.i */
final class PendingPostQueue {
    private PendingPost f51a;
    private PendingPost f52b;

    PendingPostQueue() {
    }

    synchronized void m24a(PendingPost pendingPost) {
        if (pendingPost == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.f52b != null) {
            this.f52b.f50c = pendingPost;
            this.f52b = pendingPost;
        } else if (this.f51a == null) {
            this.f52b = pendingPost;
            this.f51a = pendingPost;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    synchronized PendingPost m22a() {
        PendingPost pendingPost;
        pendingPost = this.f51a;
        if (this.f51a != null) {
            this.f51a = this.f51a.f50c;
            if (this.f51a == null) {
                this.f52b = null;
            }
        }
        return pendingPost;
    }

    synchronized PendingPost m23a(int i) throws InterruptedException {
        if (this.f51a == null) {
            wait((long) i);
        }
        return m22a();
    }
}
