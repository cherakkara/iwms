package p000a.p001a.p002a;

import java.util.ArrayList;
import java.util.List;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

/* renamed from: a.a.a.h */
final class PendingPost {
    private static final List<PendingPost> f47d;
    Object f48a;
    Subscription f49b;
    PendingPost f50c;

    static {
        f47d = new ArrayList();
    }

    private PendingPost(Object obj, Subscription subscription) {
        this.f48a = obj;
        this.f49b = subscription;
    }

    static PendingPost m20a(Subscription subscription, Object obj) {
        synchronized (f47d) {
            int size = f47d.size();
            if (size > 0) {
                PendingPost pendingPost = (PendingPost) f47d.remove(size - 1);
                pendingPost.f48a = obj;
                pendingPost.f49b = subscription;
                pendingPost.f50c = null;
                return pendingPost;
            }
            return new PendingPost(obj, subscription);
        }
    }

    static void m21a(PendingPost pendingPost) {
        pendingPost.f48a = null;
        pendingPost.f49b = null;
        pendingPost.f50c = null;
        synchronized (f47d) {
            if (f47d.size() < AbstractSpiCall.DEFAULT_TIMEOUT) {
                f47d.add(pendingPost);
            }
        }
    }
}
