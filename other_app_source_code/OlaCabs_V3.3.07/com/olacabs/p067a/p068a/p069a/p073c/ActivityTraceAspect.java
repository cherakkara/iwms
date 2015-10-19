package com.olacabs.p067a.p068a.p069a.p073c;

import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import org.p087a.p088a.JoinPoint;
import org.p087a.p088a.NoAspectBoundException;
import org.p087a.p088a.p089a.MethodSignature;

/* renamed from: com.olacabs.a.a.a.c.a */
public class ActivityTraceAspect {
    public static final ActivityTraceAspect f8911a = null;
    private static Throwable f8912b;

    static {
        try {
            ActivityTraceAspect.m12824b();
        } catch (Throwable th) {
            f8912b = th;
        }
    }

    public static ActivityTraceAspect m12823a() {
        if (f8911a != null) {
            return f8911a;
        }
        throw new NoAspectBoundException("com.olacabs.transformers.megatron.libinternal.monitor.ActivityTraceAspect", f8912b);
    }

    private static void m12824b() {
        f8911a = new ActivityTraceAspect();
    }

    public void m12825a(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.m15051b();
        String simpleName = methodSignature.m15049b().getSimpleName();
        if (!simpleName.equals("ClassNotFoundException")) {
            boolean z = true;
            MegatronCore.m12842e().m12850a(true, (long) joinPoint.m15050a().hashCode(), simpleName, methodSignature.m15048a(), z, System.currentTimeMillis());
        }
    }

    public void m12826b(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.m15051b();
        String simpleName = methodSignature.m15049b().getSimpleName();
        if (!simpleName.equals("ClassNotFoundException")) {
            MegatronCore.m12842e().m12850a(false, (long) joinPoint.m15050a().hashCode(), simpleName, methodSignature.m15048a(), true, System.currentTimeMillis());
        }
    }

    public void m12827c(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.m15051b();
        String simpleName = methodSignature.m15049b().getSimpleName();
        if (!simpleName.equals("ClassNotFoundException")) {
            boolean z = false;
            MegatronCore.m12842e().m12850a(false, (long) joinPoint.m15050a().hashCode(), simpleName, methodSignature.m15048a(), z, System.currentTimeMillis());
        }
    }

    public void m12828d(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.m15051b();
        String simpleName = methodSignature.m15049b().getSimpleName();
        if (!simpleName.equals("ClassNotFoundException")) {
            MegatronCore.m12842e().m12850a(true, (long) joinPoint.m15050a().hashCode(), simpleName, methodSignature.m15048a(), false, System.currentTimeMillis());
        }
    }
}
