package com.google.android.m4b.maps.av;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.av.as.GmmGLSurfaceView;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: GmmGLTextureView */
public class at extends TextureView implements SurfaceTextureListener {
    private static final GmmGLTextureView f4327a;
    private final WeakReference<at> f4328b;
    private GmmGLTextureView f4329c;
    private Renderer f4330d;
    private boolean f4331e;
    private GmmGLSurfaceView f4332f;
    private GmmGLTextureView f4333g;
    private GmmGLTextureView f4334h;
    private boolean f4335i;
    private boolean f4336j;

    /* renamed from: com.google.android.m4b.maps.av.at.a */
    abstract class GmmGLTextureView implements GmmGLSurfaceView {
        private int[] f4432a;
        private /* synthetic */ at f4433b;

        abstract EGLConfig m7070a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public GmmGLTextureView(at atVar, int[] iArr) {
            this.f4433b = atVar;
            if (0 == 2) {
                int length = iArr.length;
                Object obj = new int[(length + 2)];
                System.arraycopy(iArr, 0, obj, 0, length - 1);
                obj[length - 1] = 12352;
                obj[length] = 4;
                obj[length + 1] = 12344;
                Object obj2 = obj;
            }
            this.f4432a = iArr;
        }

        public final EGLConfig m7069a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f4432a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.f4432a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = m7070a(egl10, eGLDisplay, eGLConfigArr);
                    if (a != null) {
                        return a;
                    }
                    throw new IllegalArgumentException("No config chosen");
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.b */
    class GmmGLTextureView extends GmmGLTextureView {
        private int[] f4434a;
        private int f4435b;
        private int f4436c;
        private int f4437d;
        private int f4438e;
        private int f4439f;
        private int f4440g;

        public GmmGLTextureView(at atVar, int i, int i2, int i3, int i4, int i5, int i6) {
            super(atVar, new int[]{12324, 5, 12323, 6, 12322, 5, 12321, 0, 12325, i5, 12326, 0, 12344});
            this.f4434a = new int[1];
            this.f4435b = 5;
            this.f4436c = 6;
            this.f4437d = 5;
            this.f4438e = 0;
            this.f4439f = i5;
            this.f4440g = 0;
        }

        public final EGLConfig m7072a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = m7071a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = m7071a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.f4439f && a2 >= this.f4440g) {
                    a = m7071a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = m7071a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = m7071a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = m7071a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.f4435b && a3 == this.f4436c && a4 == this.f4437d && a2 == this.f4438e) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int m7071a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f4434a)) {
                return this.f4434a[0];
            }
            return 0;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.d */
    public interface GmmGLTextureView {
        EGLContext m7073a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void m7074a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* renamed from: com.google.android.m4b.maps.av.at.c */
    class GmmGLTextureView implements GmmGLTextureView {
        private int f4441a;
        private /* synthetic */ at f4442b;

        private GmmGLTextureView(at atVar) {
            this.f4442b = atVar;
            this.f4441a = 12440;
        }

        public final EGLContext m7075a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.f4441a, 0, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (0 == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public final void m7076a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                GmmGLTextureView.m7079a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.e */
    public interface GmmGLTextureView {
        private GmmGLTextureView() {
        }

        EGLSurface m7077a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
            }
            return eGLSurface;
        }

        void m7078a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.f */
    static class GmmGLTextureView {
        EGL10 f4443a;
        EGLDisplay f4444b;
        EGLSurface f4445c;
        EGLConfig f4446d;
        private WeakReference<at> f4447e;
        private EGLContext f4448f;

        public GmmGLTextureView(WeakReference<at> weakReference) {
            this.f4447e = weakReference;
        }

        public final void m7081a() {
            PerformanceProfile.m4867a();
            this.f4443a = (EGL10) EGLContext.getEGL();
            this.f4444b = this.f4443a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f4444b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.f4443a.eglInitialize(this.f4444b, new int[2])) {
                at atVar = (at) this.f4447e.get();
                if (atVar == null) {
                    this.f4446d = null;
                    this.f4448f = null;
                } else {
                    this.f4446d = atVar.f4332f.m7020a(this.f4443a, this.f4444b);
                    this.f4448f = atVar.f4333g.m7073a(this.f4443a, this.f4444b, this.f4446d);
                }
                if (this.f4448f == null || this.f4448f == EGL10.EGL_NO_CONTEXT) {
                    this.f4448f = null;
                    GmmGLTextureView.m7079a("createContext", this.f4443a.eglGetError());
                }
                this.f4445c = null;
                PerformanceProfile.m4868b();
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        public final boolean m7082b() {
            if (this.f4443a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.f4444b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.f4446d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                m7080f();
                at atVar = (at) this.f4447e.get();
                if (atVar != null) {
                    this.f4445c = atVar.f4334h.m7077a(this.f4443a, this.f4444b, this.f4446d, atVar.getSurfaceTexture());
                } else {
                    this.f4445c = null;
                }
                if (this.f4445c == null || this.f4445c == EGL10.EGL_NO_SURFACE) {
                    this.f4443a.eglGetError();
                    return false;
                } else if (this.f4443a.eglMakeCurrent(this.f4444b, this.f4445c, this.f4445c, this.f4448f)) {
                    return true;
                } else {
                    this.f4443a.eglGetError();
                    return false;
                }
            }
        }

        final GL m7083c() {
            GL gl = this.f4448f.getGL();
            at atVar = (at) this.f4447e.get();
            if (atVar == null) {
                return gl;
            }
            if (null != null) {
                gl = null.m7064a();
            }
            if ((0 & 3) == 0) {
                return gl;
            }
            Writer gmmGLTextureView;
            int i = 0;
            if ((0 & 1) != 0) {
                i = 1;
            }
            if ((0 & 2) != 0) {
                gmmGLTextureView = new GmmGLTextureView();
            } else {
                gmmGLTextureView = null;
            }
            return GLDebugHelper.wrap(gl, i, gmmGLTextureView);
        }

        public final void m7084d() {
            m7080f();
        }

        private void m7080f() {
            if (this.f4445c != null && this.f4445c != EGL10.EGL_NO_SURFACE) {
                this.f4443a.eglMakeCurrent(this.f4444b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                at atVar = (at) this.f4447e.get();
                if (atVar != null) {
                    atVar.f4334h.m7078a(this.f4443a, this.f4444b, this.f4445c);
                }
                this.f4445c = null;
            }
        }

        public final void m7085e() {
            if (this.f4448f != null) {
                at atVar = (at) this.f4447e.get();
                if (atVar != null) {
                    atVar.f4333g.m7074a(this.f4443a, this.f4444b, this.f4448f);
                }
                this.f4448f = null;
            }
            if (this.f4444b != null) {
                this.f4443a.eglTerminate(this.f4444b);
                this.f4444b = null;
            }
        }

        public static void m7079a(String str, int i) {
            throw new RuntimeException(str + " failed: " + i);
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.g */
    static class GmmGLTextureView extends Thread {
        private boolean f4449a;
        private boolean f4450b;
        private boolean f4451c;
        private boolean f4452d;
        private boolean f4453e;
        private boolean f4454f;
        private boolean f4455g;
        private boolean f4456h;
        private boolean f4457i;
        private boolean f4458j;
        private int f4459k;
        private int f4460l;
        private int f4461m;
        private boolean f4462n;
        private boolean f4463o;
        private ArrayList<Runnable> f4464p;
        private boolean f4465q;
        private GmmGLTextureView f4466r;
        private WeakReference<at> f4467s;

        GmmGLTextureView(WeakReference<at> weakReference) {
            this.f4464p = new ArrayList();
            this.f4465q = true;
            this.f4459k = 0;
            this.f4460l = 0;
            this.f4462n = true;
            this.f4461m = 1;
            this.f4467s = weakReference;
        }

        public final void run() {
            setName("GLThread " + getId());
            try {
                m7089l();
                at.f4327a.m7103a(this);
            } catch (InterruptedException e) {
                at.f4327a.m7103a(this);
            } catch (Throwable e2) {
                ExceptionReporter.m11467a("MAP", e2);
                throw e2;
            } catch (Throwable e22) {
                ExceptionReporter.m11467a("MAP", e22);
                at.f4327a.m7103a(this);
            } catch (Throwable th) {
                at.f4327a.m7103a(this);
            }
        }

        private void m7087j() {
            if (this.f4457i) {
                this.f4457i = false;
                this.f4466r.m7084d();
            }
        }

        private void m7088k() {
            if (this.f4456h) {
                this.f4466r.m7085e();
                this.f4456h = false;
                at.f4327a.m7108c(this);
            }
        }

        private void m7089l() {
            this.f4466r = new GmmGLTextureView(this.f4467s);
            this.f4456h = false;
            this.f4457i = false;
            Object obj = null;
            GL10 gl10 = null;
            int i = 0;
            Object obj2 = null;
            Object obj3 = null;
            Object obj4 = null;
            Object obj5 = null;
            Object obj6 = null;
            Object obj7 = null;
            Runnable runnable = null;
            int i2 = 0;
            Object obj8 = null;
            while (true) {
                try {
                    synchronized (at.f4327a) {
                        while (true) {
                            if (this.f4449a) {
                                synchronized (at.f4327a) {
                                    m7087j();
                                    m7088k();
                                }
                                return;
                            }
                            at atVar;
                            Object obj9;
                            Runnable runnable2;
                            Object obj10;
                            int i3;
                            int i4;
                            if (this.f4464p.isEmpty()) {
                                boolean z;
                                boolean z2 = this.f4452d;
                                boolean z3 = this.f4451c;
                                if (z2 != r0) {
                                    z = this.f4451c;
                                    this.f4452d = this.f4451c;
                                    at.f4327a.notifyAll();
                                    z2 = z;
                                } else {
                                    z2 = false;
                                }
                                if (this.f4458j) {
                                    m7087j();
                                    m7088k();
                                    this.f4458j = false;
                                    obj2 = 1;
                                }
                                if (obj5 != null) {
                                    m7087j();
                                    m7088k();
                                    obj5 = null;
                                }
                                if (z2 && this.f4457i) {
                                    m7087j();
                                }
                                if (z2 && this.f4456h) {
                                    atVar = (at) this.f4467s.get();
                                    if (atVar == null) {
                                        z = false;
                                    } else {
                                        z = atVar.f4335i;
                                    }
                                    if (!z || at.f4327a.m7105a()) {
                                        m7088k();
                                    }
                                }
                                if (z2 && at.f4327a.m7106b()) {
                                    this.f4466r.m7085e();
                                }
                                if (!(this.f4453e || this.f4455g)) {
                                    if (this.f4457i) {
                                        m7087j();
                                    }
                                    this.f4455g = true;
                                    this.f4454f = false;
                                    at.f4327a.notifyAll();
                                }
                                if (this.f4453e && this.f4455g) {
                                    this.f4455g = false;
                                    at.f4327a.notifyAll();
                                }
                                if (obj8 != null) {
                                    obj3 = null;
                                    obj8 = null;
                                    this.f4463o = true;
                                    at.f4327a.notifyAll();
                                }
                                if (m7090m()) {
                                    if (!this.f4456h) {
                                        if (obj2 != null) {
                                            obj2 = null;
                                        } else if (at.f4327a.m7107b(this)) {
                                            this.f4466r.m7081a();
                                            this.f4456h = true;
                                            obj = 1;
                                            at.f4327a.notifyAll();
                                        }
                                    }
                                    if (!this.f4456h || this.f4457i) {
                                        obj9 = obj4;
                                        obj4 = obj6;
                                    } else {
                                        this.f4457i = true;
                                        obj7 = 1;
                                        obj9 = 1;
                                        obj4 = 1;
                                    }
                                    if (this.f4457i) {
                                        int i5;
                                        Object obj11;
                                        int i6;
                                        if (this.f4465q) {
                                            obj3 = 1;
                                            i = this.f4459k;
                                            i5 = this.f4460l;
                                            obj11 = 1;
                                            obj6 = 1;
                                            this.f4465q = false;
                                        } else {
                                            obj6 = obj7;
                                            i6 = i2;
                                            obj11 = obj3;
                                            obj3 = obj9;
                                            i5 = i;
                                            i = i6;
                                        }
                                        this.f4462n = false;
                                        at.f4327a.notifyAll();
                                        obj7 = obj4;
                                        obj4 = obj11;
                                        runnable2 = runnable;
                                        obj10 = obj8;
                                        i3 = i;
                                        i6 = i5;
                                        obj9 = obj6;
                                        obj6 = obj5;
                                        obj5 = obj3;
                                        obj3 = obj2;
                                        i4 = i6;
                                    } else {
                                        obj6 = obj4;
                                        obj4 = obj9;
                                    }
                                }
                                at.f4327a.wait();
                            } else {
                                obj10 = obj8;
                                i3 = i2;
                                runnable2 = (Runnable) this.f4464p.remove(0);
                                obj9 = obj7;
                                obj7 = obj6;
                                obj6 = obj5;
                                obj5 = obj4;
                                obj4 = obj3;
                                obj3 = obj2;
                                i4 = i;
                            }
                            Object obj12;
                            if (runnable2 != null) {
                                runnable2.run();
                                i = i4;
                                obj2 = obj3;
                                obj3 = obj4;
                                obj4 = obj5;
                                obj5 = obj6;
                                obj6 = obj7;
                                obj7 = obj9;
                                obj12 = obj10;
                                runnable = null;
                                i2 = i3;
                                obj8 = obj12;
                            } else {
                                Object obj13;
                                GL10 gl102;
                                if (obj9 == null) {
                                    obj13 = obj9;
                                } else if (this.f4466r.m7082b()) {
                                    obj13 = null;
                                } else {
                                    synchronized (at.f4327a) {
                                        this.f4454f = true;
                                        at.f4327a.notifyAll();
                                    }
                                    i = i4;
                                    obj2 = obj3;
                                    obj3 = obj4;
                                    obj4 = obj5;
                                    obj5 = obj6;
                                    obj6 = obj7;
                                    obj7 = obj9;
                                    obj12 = obj10;
                                    runnable = runnable2;
                                    i2 = i3;
                                    obj8 = obj12;
                                }
                                if (obj7 != null) {
                                    GL10 gl103 = (GL10) this.f4466r.m7083c();
                                    at.f4327a.m7104a(gl103);
                                    obj7 = null;
                                    gl102 = gl103;
                                } else {
                                    gl102 = gl10;
                                }
                                if (obj != null) {
                                    atVar = (at) this.f4467s.get();
                                    if (atVar != null) {
                                        Renderer h = atVar.f4330d;
                                        EGLConfig eGLConfig = this.f4466r.f4446d;
                                        h.m7246a(gl102);
                                    }
                                    obj = null;
                                }
                                if (obj5 != null) {
                                    atVar = (at) this.f4467s.get();
                                    if (atVar != null) {
                                        atVar.f4330d.m7247a(gl102, i3, i4);
                                    }
                                    obj5 = null;
                                }
                                atVar = (at) this.f4467s.get();
                                if (atVar != null) {
                                    atVar.f4330d.m7253b(gl102);
                                }
                                System.nanoTime();
                                GmmGLTextureView gmmGLTextureView = this.f4466r;
                                switch (!gmmGLTextureView.f4443a.eglSwapBuffers(gmmGLTextureView.f4444b, gmmGLTextureView.f4445c) ? gmmGLTextureView.f4443a.eglGetError() : 12288) {
                                    case 12288:
                                        break;
                                    case 12302:
                                        obj6 = 1;
                                        break;
                                    default:
                                        synchronized (at.f4327a) {
                                            this.f4454f = true;
                                            at.f4327a.notifyAll();
                                            break;
                                        }
                                }
                                if (obj4 != null) {
                                    obj9 = 1;
                                } else {
                                    obj9 = obj10;
                                }
                                runnable = runnable2;
                                gl10 = gl102;
                                i2 = i3;
                                obj8 = obj9;
                                obj12 = obj3;
                                obj3 = obj4;
                                obj4 = obj5;
                                obj5 = obj6;
                                obj6 = obj7;
                                obj7 = obj13;
                                i = i4;
                                obj2 = obj12;
                            }
                        }
                    }
                } catch (RuntimeException e) {
                    at.f4327a.m7108c(this);
                    throw e;
                } catch (Throwable th) {
                    synchronized (at.f4327a) {
                    }
                    m7087j();
                    m7088k();
                }
            }
        }

        private boolean m7090m() {
            return !this.f4452d && this.f4453e && !this.f4454f && this.f4459k > 0 && this.f4460l > 0 && (this.f4462n || this.f4461m == 1);
        }

        public final void m7092a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (at.f4327a) {
                this.f4461m = i;
                at.f4327a.notifyAll();
            }
        }

        public final int m7091a() {
            int i;
            synchronized (at.f4327a) {
                i = this.f4461m;
            }
            return i;
        }

        public final void m7094b() {
            synchronized (at.f4327a) {
                this.f4462n = true;
                at.f4327a.notifyAll();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7095c() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4453e = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4455g;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4450b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x001f }
            r0.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
            r0.interrupt();	 Catch:{ all -> 0x0028 }
            goto L_0x000f;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x002b:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.c():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7096d() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r1);
            r0 = 0;
            r2.f4453e = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4455g;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4450b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x001f }
            r0.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
            r0.interrupt();	 Catch:{ all -> 0x0028 }
            goto L_0x000f;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x002b:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.d():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7097e() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4451c = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4450b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4452d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x001f }
            r0.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
            r0.interrupt();	 Catch:{ all -> 0x0028 }
            goto L_0x000f;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x002b:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.e():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7098f() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r1);
            r0 = 0;
            r2.f4451c = r0;	 Catch:{ all -> 0x0032 }
            r0 = 1;
            r2.f4462n = r0;	 Catch:{ all -> 0x0032 }
            r0 = 0;
            r2.f4463o = r0;	 Catch:{ all -> 0x0032 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0032 }
            r0.notifyAll();	 Catch:{ all -> 0x0032 }
        L_0x0015:
            r0 = r2.f4450b;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0019:
            r0 = r2.f4452d;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0035;
        L_0x001d:
            r0 = r2.f4463o;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0021:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x0029 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0029 }
            goto L_0x0015;
        L_0x0029:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0032 }
            r0.interrupt();	 Catch:{ all -> 0x0032 }
            goto L_0x0015;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x0035:
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.f():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7093a(int r5, int r6) {
            /*
            r4 = this;
            r2 = 0;
            r1 = 1;
            r3 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r3);
            r4.f4459k = r5;	 Catch:{ all -> 0x0049 }
            r4.f4460l = r6;	 Catch:{ all -> 0x0049 }
            r0 = 1;
            r4.f4465q = r0;	 Catch:{ all -> 0x0049 }
            r0 = 1;
            r4.f4462n = r0;	 Catch:{ all -> 0x0049 }
            r0 = 0;
            r4.f4463o = r0;	 Catch:{ all -> 0x0049 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0049 }
            r0.notifyAll();	 Catch:{ all -> 0x0049 }
        L_0x001b:
            r0 = r4.f4450b;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x001f:
            r0 = r4.f4452d;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x0023:
            r0 = r4.f4463o;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x0027:
            r0 = r4.f4456h;	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x002b:
            r0 = r4.f4457i;	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x002f:
            r0 = r4.m7090m();	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x0035:
            r0 = r1;
        L_0x0036:
            if (r0 == 0) goto L_0x004e;
        L_0x0038:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x0040 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0040 }
            goto L_0x001b;
        L_0x0040:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0049 }
            r0.interrupt();	 Catch:{ all -> 0x0049 }
            goto L_0x001b;
        L_0x0049:
            r0 = move-exception;
            monitor-exit(r3);
            throw r0;
        L_0x004c:
            r0 = r2;
            goto L_0x0036;
        L_0x004e:
            monitor-exit(r3);	 Catch:{ all -> 0x0049 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.a(int, int):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7099g() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.at.f4327a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4449a = r0;	 Catch:{ all -> 0x0024 }
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ all -> 0x0024 }
            r0.notifyAll();	 Catch:{ all -> 0x0024 }
        L_0x000f:
            r0 = r2.f4450b;	 Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0027;
        L_0x0013:
            r0 = com.google.android.m4b.maps.av.at.f4327a;	 Catch:{ InterruptedException -> 0x001b }
            r0.wait();	 Catch:{ InterruptedException -> 0x001b }
            goto L_0x000f;
        L_0x001b:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0024 }
            r0.interrupt();	 Catch:{ all -> 0x0024 }
            goto L_0x000f;
        L_0x0024:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
        L_0x0027:
            monitor-exit(r1);	 Catch:{ all -> 0x0024 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.at.g.g():void");
        }

        public final boolean m7100h() {
            boolean z;
            synchronized (at.f4327a) {
                z = this.f4450b;
            }
            return z;
        }

        public final void m7101i() {
            this.f4458j = true;
            at.f4327a.notifyAll();
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.h */
    static class GmmGLTextureView {
        private boolean f4468a;
        private int f4469b;
        private boolean f4470c;
        private boolean f4471d;
        private boolean f4472e;
        private GmmGLTextureView f4473f;

        private GmmGLTextureView() {
        }

        public final synchronized void m7103a(GmmGLTextureView gmmGLTextureView) {
            gmmGLTextureView.f4450b = true;
            if (this.f4473f == gmmGLTextureView) {
                this.f4473f = null;
            }
            notifyAll();
        }

        public final boolean m7107b(GmmGLTextureView gmmGLTextureView) {
            if (this.f4473f == gmmGLTextureView || this.f4473f == null) {
                this.f4473f = gmmGLTextureView;
                notifyAll();
                return true;
            }
            m7102c();
            if (this.f4471d) {
                return true;
            }
            if (this.f4473f != null) {
                this.f4473f.m7101i();
            }
            return false;
        }

        public final void m7108c(GmmGLTextureView gmmGLTextureView) {
            if (this.f4473f == gmmGLTextureView) {
                this.f4473f = null;
            }
            notifyAll();
        }

        public final synchronized boolean m7105a() {
            return this.f4472e;
        }

        public final synchronized boolean m7106b() {
            m7102c();
            return !this.f4471d;
        }

        public final synchronized void m7104a(GL10 gl10) {
            boolean z = false;
            synchronized (this) {
                if (!this.f4470c) {
                    m7102c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.f4469b < AccessibilityNodeInfoCompat.ACTION_SET_SELECTION) {
                        this.f4471d = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (!this.f4471d || (glGetString.startsWith("Adreno") && VERSION.SDK_INT < 11)) {
                        z = true;
                    }
                    this.f4472e = z;
                    this.f4470c = true;
                }
            }
        }

        private void m7102c() {
            if (!this.f4468a) {
                this.f4469b = AccessibilityNodeInfoCompat.ACTION_SET_SELECTION;
                if (this.f4469b >= AccessibilityNodeInfoCompat.ACTION_SET_SELECTION) {
                    this.f4471d = true;
                }
                this.f4468a = true;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.i */
    static class GmmGLTextureView extends Writer {
        private StringBuilder f4474a;

        GmmGLTextureView() {
            this.f4474a = new StringBuilder();
        }

        public final void close() {
            m7109a();
        }

        public final void flush() {
            m7109a();
        }

        public final void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    m7109a();
                } else {
                    this.f4474a.append(c);
                }
            }
        }

        private void m7109a() {
            if (this.f4474a.length() > 0) {
                this.f4474a.delete(0, this.f4474a.length());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.at.j */
    class GmmGLTextureView extends GmmGLTextureView {
        public GmmGLTextureView(at atVar, boolean z) {
            super(atVar, 5, 6, 5, 0, 16, 0);
        }
    }

    public at(Context context) {
        super(context);
        this.f4328b = new WeakReference(this);
        setSurfaceTextureListener(this);
    }

    protected void finalize() {
        try {
            if (this.f4329c != null) {
                this.f4329c.m7099g();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void m6891i(boolean z) {
        this.f4335i = true;
    }

    public final void m6892j(boolean z) {
        this.f4336j = z;
        if (!z && this.f4331e && this.f4329c != null && !this.f4329c.m7100h()) {
            this.f4329c.m7099g();
        }
    }

    public final void m6888a(Renderer renderer) {
        m6880c();
        if (this.f4332f == null) {
            this.f4332f = new GmmGLTextureView(this, true);
        }
        if (this.f4333g == null) {
            this.f4333g = new GmmGLTextureView();
        }
        if (this.f4334h == null) {
            this.f4334h = new GmmGLTextureView();
        }
        this.f4330d = renderer;
        this.f4329c = new GmmGLTextureView(this.f4328b);
        this.f4329c.start();
    }

    public final void m6887a(GmmGLSurfaceView gmmGLSurfaceView) {
        m6880c();
        this.f4332f = gmmGLSurfaceView;
    }

    public final void m6890b(int i) {
        this.f4329c.m7092a(0);
    }

    public void m6893w() {
        this.f4329c.m7094b();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f4329c.m7095c();
        this.f4329c.m7093a(i, i2);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.f4329c.m7096d();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f4329c.m7093a(i, i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void m6889b() {
        this.f4329c.m7097e();
    }

    public void m6886a() {
        this.f4329c.m7098f();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f4331e && this.f4330d != null && (this.f4329c == null || this.f4329c.m7100h())) {
            int a;
            if (this.f4329c != null) {
                a = this.f4329c.m7091a();
            } else {
                a = 1;
            }
            this.f4329c = new GmmGLTextureView(this.f4328b);
            if (a != 1) {
                this.f4329c.m7092a(a);
            }
            this.f4329c.start();
        }
        this.f4331e = false;
    }

    protected void onDetachedFromWindow() {
        if (!(this.f4336j || this.f4329c == null)) {
            this.f4329c.m7099g();
        }
        this.f4331e = true;
        super.onDetachedFromWindow();
    }

    private void m6880c() {
        if (this.f4329c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    static {
        f4327a = new GmmGLTextureView();
    }
}
