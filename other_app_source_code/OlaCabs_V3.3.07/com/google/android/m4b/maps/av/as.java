package com.google.android.m4b.maps.av;

import android.content.Context;
import android.opengl.GLDebugHelper;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.google.android.m4b.maps.ae.PerformanceProfile;
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

/* compiled from: GmmGLSurfaceView */
public class as extends SurfaceView implements Callback {
    private static final GmmGLSurfaceView f4353a;
    private final WeakReference<as> f4354b;
    private GmmGLSurfaceView f4355c;
    private GmmGLSurfaceView f4356d;
    private boolean f4357e;
    private GmmGLSurfaceView f4358f;
    private GmmGLSurfaceView f4359g;
    private GmmGLSurfaceView f4360h;
    private boolean f4361i;
    private boolean f4362j;

    /* renamed from: com.google.android.m4b.maps.av.as.d */
    public interface GmmGLSurfaceView {
        EGLConfig m7020a(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* renamed from: com.google.android.m4b.maps.av.as.a */
    abstract class GmmGLSurfaceView implements GmmGLSurfaceView {
        private int[] f4389a;
        private /* synthetic */ as f4390b;

        abstract EGLConfig m7025a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public GmmGLSurfaceView(as asVar, int[] iArr) {
            this.f4390b = asVar;
            if (0 == 2) {
                int length = iArr.length;
                Object obj = new int[(length + 2)];
                System.arraycopy(iArr, 0, obj, 0, length - 1);
                obj[length - 1] = 12352;
                obj[length] = 4;
                obj[length + 1] = 12344;
                Object obj2 = obj;
            }
            this.f4389a = iArr;
        }

        public final EGLConfig m7024a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f4389a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.f4389a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = m7025a(egl10, eGLDisplay, eGLConfigArr);
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

    /* renamed from: com.google.android.m4b.maps.av.as.b */
    class GmmGLSurfaceView extends GmmGLSurfaceView {
        private int[] f4391a;
        private int f4392b;
        private int f4393c;
        private int f4394d;
        private int f4395e;
        private int f4396f;
        private int f4397g;

        public GmmGLSurfaceView(as asVar, int i, int i2, int i3, int i4, int i5, int i6) {
            super(asVar, new int[]{12324, 5, 12323, 6, 12322, 5, 12321, 0, 12325, i5, 12326, 0, 12344});
            this.f4391a = new int[1];
            this.f4392b = 5;
            this.f4393c = 6;
            this.f4394d = 5;
            this.f4395e = 0;
            this.f4396f = i5;
            this.f4397g = 0;
        }

        public final EGLConfig m7027a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = m7026a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = m7026a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.f4396f && a2 >= this.f4397g) {
                    a = m7026a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = m7026a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = m7026a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = m7026a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.f4392b && a3 == this.f4393c && a4 == this.f4394d && a2 == this.f4395e) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int m7026a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f4391a)) {
                return this.f4391a[0];
            }
            return 0;
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.e */
    public interface GmmGLSurfaceView {
        EGLContext m7028a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void m7029a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* renamed from: com.google.android.m4b.maps.av.as.c */
    class GmmGLSurfaceView implements GmmGLSurfaceView {
        private int f4398a;
        private /* synthetic */ as f4399b;

        private GmmGLSurfaceView(as asVar) {
            this.f4399b = asVar;
            this.f4398a = 12440;
        }

        public final EGLContext m7030a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.f4398a, 0, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (0 == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public final void m7031a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                GmmGLSurfaceView.m7034a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.f */
    public interface GmmGLSurfaceView {
        private GmmGLSurfaceView() {
        }

        EGLSurface m7032a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
            }
            return eGLSurface;
        }

        void m7033a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.g */
    static class GmmGLSurfaceView {
        EGL10 f4400a;
        EGLDisplay f4401b;
        EGLSurface f4402c;
        EGLConfig f4403d;
        private WeakReference<as> f4404e;
        private EGLContext f4405f;

        public GmmGLSurfaceView(WeakReference<as> weakReference) {
            this.f4404e = weakReference;
        }

        public final void m7036a() {
            PerformanceProfile.m4867a();
            this.f4400a = (EGL10) EGLContext.getEGL();
            this.f4401b = this.f4400a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f4401b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.f4400a.eglInitialize(this.f4401b, new int[2])) {
                as asVar = (as) this.f4404e.get();
                if (asVar == null) {
                    this.f4403d = null;
                    this.f4405f = null;
                } else {
                    this.f4403d = asVar.f4358f.m7020a(this.f4400a, this.f4401b);
                    this.f4405f = asVar.f4359g.m7028a(this.f4400a, this.f4401b, this.f4403d);
                }
                if (this.f4405f == null || this.f4405f == EGL10.EGL_NO_CONTEXT) {
                    this.f4405f = null;
                    GmmGLSurfaceView.m7034a("createContext", this.f4400a.eglGetError());
                }
                this.f4402c = null;
                PerformanceProfile.m4868b();
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        public final boolean m7037b() {
            if (this.f4400a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.f4401b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.f4403d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                m7035f();
                as asVar = (as) this.f4404e.get();
                if (asVar != null) {
                    this.f4402c = asVar.f4360h.m7032a(this.f4400a, this.f4401b, this.f4403d, asVar.getHolder());
                } else {
                    this.f4402c = null;
                }
                if (this.f4402c == null || this.f4402c == EGL10.EGL_NO_SURFACE) {
                    this.f4400a.eglGetError();
                    return false;
                } else if (this.f4400a.eglMakeCurrent(this.f4401b, this.f4402c, this.f4402c, this.f4405f)) {
                    return true;
                } else {
                    this.f4400a.eglGetError();
                    return false;
                }
            }
        }

        final GL m7038c() {
            GL gl = this.f4405f.getGL();
            as asVar = (as) this.f4404e.get();
            if (asVar == null) {
                return gl;
            }
            if (null != null) {
                gl = null.m7064a();
            }
            if ((0 & 3) == 0) {
                return gl;
            }
            Writer gmmGLSurfaceView;
            int i = 0;
            if ((0 & 1) != 0) {
                i = 1;
            }
            if ((0 & 2) != 0) {
                gmmGLSurfaceView = new GmmGLSurfaceView();
            } else {
                gmmGLSurfaceView = null;
            }
            return GLDebugHelper.wrap(gl, i, gmmGLSurfaceView);
        }

        public final void m7039d() {
            m7035f();
        }

        private void m7035f() {
            if (this.f4402c != null && this.f4402c != EGL10.EGL_NO_SURFACE) {
                this.f4400a.eglMakeCurrent(this.f4401b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                as asVar = (as) this.f4404e.get();
                if (asVar != null) {
                    asVar.f4360h.m7033a(this.f4400a, this.f4401b, this.f4402c);
                }
                this.f4402c = null;
            }
        }

        public final void m7040e() {
            if (this.f4405f != null) {
                as asVar = (as) this.f4404e.get();
                if (asVar != null) {
                    asVar.f4359g.m7029a(this.f4400a, this.f4401b, this.f4405f);
                }
                this.f4405f = null;
            }
            if (this.f4401b != null) {
                this.f4400a.eglTerminate(this.f4401b);
                this.f4401b = null;
            }
        }

        public static void m7034a(String str, int i) {
            throw new RuntimeException(str + " failed: " + i);
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.h */
    static class GmmGLSurfaceView extends Thread {
        private boolean f4406a;
        private boolean f4407b;
        private boolean f4408c;
        private boolean f4409d;
        private boolean f4410e;
        private boolean f4411f;
        private boolean f4412g;
        private boolean f4413h;
        private boolean f4414i;
        private boolean f4415j;
        private int f4416k;
        private int f4417l;
        private int f4418m;
        private boolean f4419n;
        private boolean f4420o;
        private ArrayList<Runnable> f4421p;
        private boolean f4422q;
        private GmmGLSurfaceView f4423r;
        private WeakReference<as> f4424s;

        GmmGLSurfaceView(WeakReference<as> weakReference) {
            this.f4421p = new ArrayList();
            this.f4422q = true;
            this.f4416k = 0;
            this.f4417l = 0;
            this.f4419n = true;
            this.f4418m = 1;
            this.f4424s = weakReference;
        }

        public final void run() {
            setName("GLThread " + getId());
            try {
                m7044l();
                as.f4353a.m7058a(this);
            } catch (InterruptedException e) {
                as.f4353a.m7058a(this);
            } catch (Throwable e2) {
                ExceptionReporter.m11467a("MAP", e2);
                throw e2;
            } catch (Throwable e22) {
                ExceptionReporter.m11467a("MAP", e22);
                as.f4353a.m7058a(this);
            } catch (Throwable th) {
                as.f4353a.m7058a(this);
            }
        }

        private void m7042j() {
            if (this.f4414i) {
                this.f4414i = false;
                this.f4423r.m7039d();
            }
        }

        private void m7043k() {
            if (this.f4413h) {
                this.f4423r.m7040e();
                this.f4413h = false;
                as.f4353a.m7063c(this);
            }
        }

        private void m7044l() {
            this.f4423r = new GmmGLSurfaceView(this.f4424s);
            this.f4413h = false;
            this.f4414i = false;
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
                    synchronized (as.f4353a) {
                        while (true) {
                            if (this.f4406a) {
                                synchronized (as.f4353a) {
                                    m7042j();
                                    m7043k();
                                }
                                return;
                            }
                            as asVar;
                            Object obj9;
                            Runnable runnable2;
                            Object obj10;
                            int i3;
                            int i4;
                            if (this.f4421p.isEmpty()) {
                                boolean z;
                                boolean z2 = this.f4409d;
                                boolean z3 = this.f4408c;
                                if (z2 != r0) {
                                    z = this.f4408c;
                                    this.f4409d = this.f4408c;
                                    as.f4353a.notifyAll();
                                    z2 = z;
                                } else {
                                    z2 = false;
                                }
                                if (this.f4415j) {
                                    m7042j();
                                    m7043k();
                                    this.f4415j = false;
                                    obj2 = 1;
                                }
                                if (obj5 != null) {
                                    m7042j();
                                    m7043k();
                                    obj5 = null;
                                }
                                if (z2 && this.f4414i) {
                                    m7042j();
                                }
                                if (z2 && this.f4413h) {
                                    asVar = (as) this.f4424s.get();
                                    if (asVar == null) {
                                        z = false;
                                    } else {
                                        z = asVar.f4361i;
                                    }
                                    if (!z || as.f4353a.m7060a()) {
                                        m7043k();
                                    }
                                }
                                if (z2 && as.f4353a.m7061b()) {
                                    this.f4423r.m7040e();
                                }
                                if (!(this.f4410e || this.f4412g)) {
                                    if (this.f4414i) {
                                        m7042j();
                                    }
                                    this.f4412g = true;
                                    this.f4411f = false;
                                    as.f4353a.notifyAll();
                                }
                                if (this.f4410e && this.f4412g) {
                                    this.f4412g = false;
                                    as.f4353a.notifyAll();
                                }
                                if (obj8 != null) {
                                    obj3 = null;
                                    obj8 = null;
                                    this.f4420o = true;
                                    as.f4353a.notifyAll();
                                }
                                if (m7045m()) {
                                    if (!this.f4413h) {
                                        if (obj2 != null) {
                                            obj2 = null;
                                        } else if (as.f4353a.m7062b(this)) {
                                            this.f4423r.m7036a();
                                            this.f4413h = true;
                                            obj = 1;
                                            as.f4353a.notifyAll();
                                        }
                                    }
                                    if (!this.f4413h || this.f4414i) {
                                        obj9 = obj4;
                                        obj4 = obj6;
                                    } else {
                                        this.f4414i = true;
                                        obj7 = 1;
                                        obj9 = 1;
                                        obj4 = 1;
                                    }
                                    if (this.f4414i) {
                                        int i5;
                                        Object obj11;
                                        int i6;
                                        if (this.f4422q) {
                                            obj3 = 1;
                                            i = this.f4416k;
                                            i5 = this.f4417l;
                                            obj11 = 1;
                                            obj6 = 1;
                                            this.f4422q = false;
                                        } else {
                                            obj6 = obj7;
                                            i6 = i2;
                                            obj11 = obj3;
                                            obj3 = obj9;
                                            i5 = i;
                                            i = i6;
                                        }
                                        this.f4419n = false;
                                        as.f4353a.notifyAll();
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
                                as.f4353a.wait();
                            } else {
                                obj10 = obj8;
                                i3 = i2;
                                runnable2 = (Runnable) this.f4421p.remove(0);
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
                                } else if (this.f4423r.m7037b()) {
                                    obj13 = null;
                                } else {
                                    synchronized (as.f4353a) {
                                        this.f4411f = true;
                                        as.f4353a.notifyAll();
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
                                    GL10 gl103 = (GL10) this.f4423r.m7038c();
                                    as.f4353a.m7059a(gl103);
                                    obj7 = null;
                                    gl102 = gl103;
                                } else {
                                    gl102 = gl10;
                                }
                                if (obj != null) {
                                    asVar = (as) this.f4424s.get();
                                    if (asVar != null) {
                                        GmmGLSurfaceView h = asVar.f4356d;
                                        EGLConfig eGLConfig = this.f4423r.f4403d;
                                        h.m7066a(gl102);
                                    }
                                    obj = null;
                                }
                                if (obj5 != null) {
                                    asVar = (as) this.f4424s.get();
                                    if (asVar != null) {
                                        asVar.f4356d.m7067a(gl102, i3, i4);
                                    }
                                    obj5 = null;
                                }
                                asVar = (as) this.f4424s.get();
                                if (asVar != null) {
                                    asVar.f4356d.m7068b(gl102);
                                }
                                System.nanoTime();
                                GmmGLSurfaceView gmmGLSurfaceView = this.f4423r;
                                switch (!gmmGLSurfaceView.f4400a.eglSwapBuffers(gmmGLSurfaceView.f4401b, gmmGLSurfaceView.f4402c) ? gmmGLSurfaceView.f4400a.eglGetError() : 12288) {
                                    case 12288:
                                        break;
                                    case 12302:
                                        obj6 = 1;
                                        break;
                                    default:
                                        synchronized (as.f4353a) {
                                            this.f4411f = true;
                                            as.f4353a.notifyAll();
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
                    as.f4353a.m7063c(this);
                    throw e;
                } catch (Throwable th) {
                    synchronized (as.f4353a) {
                    }
                    m7042j();
                    m7043k();
                }
            }
        }

        private boolean m7045m() {
            return !this.f4409d && this.f4410e && !this.f4411f && this.f4416k > 0 && this.f4417l > 0 && (this.f4419n || this.f4418m == 1);
        }

        public final void m7047a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (as.f4353a) {
                this.f4418m = i;
                as.f4353a.notifyAll();
            }
        }

        public final int m7046a() {
            int i;
            synchronized (as.f4353a) {
                i = this.f4418m;
            }
            return i;
        }

        public final void m7049b() {
            synchronized (as.f4353a) {
                this.f4419n = true;
                as.f4353a.notifyAll();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7050c() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4410e = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4412g;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4407b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x001f }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.c():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7051d() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r1);
            r0 = 0;
            r2.f4410e = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4412g;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4407b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x001f }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.d():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7052e() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4408c = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f4407b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f4409d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x001f }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.e():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7053f() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r1);
            r0 = 0;
            r2.f4408c = r0;	 Catch:{ all -> 0x0032 }
            r0 = 1;
            r2.f4419n = r0;	 Catch:{ all -> 0x0032 }
            r0 = 0;
            r2.f4420o = r0;	 Catch:{ all -> 0x0032 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0032 }
            r0.notifyAll();	 Catch:{ all -> 0x0032 }
        L_0x0015:
            r0 = r2.f4407b;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0019:
            r0 = r2.f4409d;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0035;
        L_0x001d:
            r0 = r2.f4420o;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0021:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x0029 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.f():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7048a(int r5, int r6) {
            /*
            r4 = this;
            r2 = 0;
            r1 = 1;
            r3 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r3);
            r4.f4416k = r5;	 Catch:{ all -> 0x0049 }
            r4.f4417l = r6;	 Catch:{ all -> 0x0049 }
            r0 = 1;
            r4.f4422q = r0;	 Catch:{ all -> 0x0049 }
            r0 = 1;
            r4.f4419n = r0;	 Catch:{ all -> 0x0049 }
            r0 = 0;
            r4.f4420o = r0;	 Catch:{ all -> 0x0049 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0049 }
            r0.notifyAll();	 Catch:{ all -> 0x0049 }
        L_0x001b:
            r0 = r4.f4407b;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x001f:
            r0 = r4.f4409d;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x0023:
            r0 = r4.f4420o;	 Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x004e;
        L_0x0027:
            r0 = r4.f4413h;	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x002b:
            r0 = r4.f4414i;	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x002f:
            r0 = r4.m7045m();	 Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004c;
        L_0x0035:
            r0 = r1;
        L_0x0036:
            if (r0 == 0) goto L_0x004e;
        L_0x0038:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x0040 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.a(int, int):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void m7054g() {
            /*
            r2 = this;
            r1 = com.google.android.m4b.maps.av.as.f4353a;
            monitor-enter(r1);
            r0 = 1;
            r2.f4406a = r0;	 Catch:{ all -> 0x0024 }
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ all -> 0x0024 }
            r0.notifyAll();	 Catch:{ all -> 0x0024 }
        L_0x000f:
            r0 = r2.f4407b;	 Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0027;
        L_0x0013:
            r0 = com.google.android.m4b.maps.av.as.f4353a;	 Catch:{ InterruptedException -> 0x001b }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.av.as.h.g():void");
        }

        public final boolean m7055h() {
            boolean z;
            synchronized (as.f4353a) {
                z = this.f4407b;
            }
            return z;
        }

        public final void m7056i() {
            this.f4415j = true;
            as.f4353a.notifyAll();
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.i */
    static class GmmGLSurfaceView {
        private boolean f4425a;
        private int f4426b;
        private boolean f4427c;
        private boolean f4428d;
        private boolean f4429e;
        private GmmGLSurfaceView f4430f;

        private GmmGLSurfaceView() {
        }

        public final synchronized void m7058a(GmmGLSurfaceView gmmGLSurfaceView) {
            gmmGLSurfaceView.f4407b = true;
            if (this.f4430f == gmmGLSurfaceView) {
                this.f4430f = null;
            }
            notifyAll();
        }

        public final boolean m7062b(GmmGLSurfaceView gmmGLSurfaceView) {
            if (this.f4430f == gmmGLSurfaceView || this.f4430f == null) {
                this.f4430f = gmmGLSurfaceView;
                notifyAll();
                return true;
            }
            m7057c();
            if (this.f4428d) {
                return true;
            }
            if (this.f4430f != null) {
                this.f4430f.m7056i();
            }
            return false;
        }

        public final void m7063c(GmmGLSurfaceView gmmGLSurfaceView) {
            if (this.f4430f == gmmGLSurfaceView) {
                this.f4430f = null;
            }
            notifyAll();
        }

        public final synchronized boolean m7060a() {
            return this.f4429e;
        }

        public final synchronized boolean m7061b() {
            m7057c();
            return !this.f4428d;
        }

        public final synchronized void m7059a(GL10 gl10) {
            boolean z = false;
            synchronized (this) {
                if (!this.f4427c) {
                    m7057c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.f4426b < AccessibilityNodeInfoCompat.ACTION_SET_SELECTION) {
                        this.f4428d = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (!this.f4428d || (glGetString.startsWith("Adreno") && VERSION.SDK_INT < 11)) {
                        z = true;
                    }
                    this.f4429e = z;
                    this.f4427c = true;
                }
            }
        }

        private void m7057c() {
            if (!this.f4425a) {
                this.f4426b = AccessibilityNodeInfoCompat.ACTION_SET_SELECTION;
                if (this.f4426b >= AccessibilityNodeInfoCompat.ACTION_SET_SELECTION) {
                    this.f4428d = true;
                }
                this.f4425a = true;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.j */
    public interface GmmGLSurfaceView {
        GL m7064a();
    }

    /* renamed from: com.google.android.m4b.maps.av.as.k */
    static class GmmGLSurfaceView extends Writer {
        private StringBuilder f4431a;

        GmmGLSurfaceView() {
            this.f4431a = new StringBuilder();
        }

        public final void close() {
            m7065a();
        }

        public final void flush() {
            m7065a();
        }

        public final void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    m7065a();
                } else {
                    this.f4431a.append(c);
                }
            }
        }

        private void m7065a() {
            if (this.f4431a.length() > 0) {
                this.f4431a.delete(0, this.f4431a.length());
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.as.l */
    public interface GmmGLSurfaceView {
        void m7066a(GL10 gl10);

        void m7067a(GL10 gl10, int i, int i2);

        void m7068b(GL10 gl10);
    }

    /* renamed from: com.google.android.m4b.maps.av.as.m */
    class GmmGLSurfaceView extends GmmGLSurfaceView {
        public GmmGLSurfaceView(as asVar, boolean z) {
            super(asVar, 5, 6, 5, 0, 16, 0);
        }
    }

    public as(Context context) {
        super(context);
        this.f4354b = new WeakReference(this);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        if (VERSION.SDK_INT < 9) {
            holder.setFormat(4);
        }
    }

    protected void finalize() {
        try {
            if (this.f4355c != null) {
                this.f4355c.m7054g();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void m6966i(boolean z) {
        this.f4361i = true;
    }

    public final void m6967j(boolean z) {
        this.f4362j = z;
        if (!z && this.f4357e && this.f4355c != null && !this.f4355c.m7055h()) {
            this.f4355c.m7054g();
        }
    }

    public final void m6963a(GmmGLSurfaceView gmmGLSurfaceView) {
        m6955c();
        if (this.f4358f == null) {
            this.f4358f = new GmmGLSurfaceView(this, true);
        }
        if (this.f4359g == null) {
            this.f4359g = new GmmGLSurfaceView();
        }
        if (this.f4360h == null) {
            this.f4360h = new GmmGLSurfaceView();
        }
        this.f4356d = gmmGLSurfaceView;
        this.f4355c = new GmmGLSurfaceView(this.f4354b);
        this.f4355c.start();
    }

    public final void m6962a(GmmGLSurfaceView gmmGLSurfaceView) {
        m6955c();
        this.f4358f = gmmGLSurfaceView;
    }

    public final void m6965b(int i) {
        this.f4355c.m7047a(0);
    }

    public void m6968w() {
        this.f4355c.m7049b();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f4355c.m7050c();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f4355c.m7051d();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.f4355c.m7048a(i2, i3);
    }

    public void m6964b() {
        this.f4355c.m7052e();
    }

    public void m6961a() {
        this.f4355c.m7053f();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f4357e && this.f4356d != null && (this.f4355c == null || this.f4355c.m7055h())) {
            int a;
            if (this.f4355c != null) {
                a = this.f4355c.m7046a();
            } else {
                a = 1;
            }
            this.f4355c = new GmmGLSurfaceView(this.f4354b);
            if (a != 1) {
                this.f4355c.m7047a(a);
            }
            this.f4355c.start();
        }
        this.f4357e = false;
    }

    protected void onDetachedFromWindow() {
        if (!(this.f4362j || this.f4355c == null)) {
            this.f4355c.m7054g();
        }
        this.f4357e = true;
        super.onDetachedFromWindow();
    }

    private void m6955c() {
        if (this.f4355c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    static {
        f4353a = new GmmGLSurfaceView();
    }
}
