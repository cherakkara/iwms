package com.facebook.p022b;

import com.facebook.FacebookSdk;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.b.e */
public final class CallbackManagerImpl {
    private static Map<Integer, CallbackManagerImpl> f751a;

    /* renamed from: com.facebook.b.e.a */
    public interface CallbackManagerImpl {
    }

    /* renamed from: com.facebook.b.e.b */
    public enum CallbackManagerImpl {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7);
        
        private final int f750i;

        private CallbackManagerImpl(int i) {
            this.f750i = i;
        }

        public int m908a() {
            return FacebookSdk.m1214l() + this.f750i;
        }
    }

    static {
        f751a = new HashMap();
    }

    public static synchronized void m909a(int i, CallbackManagerImpl callbackManagerImpl) {
        synchronized (CallbackManagerImpl.class) {
            Validate.m1146a((Object) callbackManagerImpl, "callback");
            if (!f751a.containsKey(Integer.valueOf(i))) {
                f751a.put(Integer.valueOf(i), callbackManagerImpl);
            }
        }
    }
}
