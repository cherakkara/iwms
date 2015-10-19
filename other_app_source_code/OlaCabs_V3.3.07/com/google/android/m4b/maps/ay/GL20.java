package com.google.android.m4b.maps.ay;

import android.opengl.GLES10;
import android.opengl.GLES11;
import android.opengl.Matrix;
import com.google.android.m4b.maps.bd.EntityRenderer;
import com.olacabs.customer.p076d.br;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import javax.microedition.khronos.opengles.GL11;

/* renamed from: com.google.android.m4b.maps.ay.d */
public final class GL20 implements GL11 {
    private GL20 f4830a;
    private GL20 f4831b;
    private GL20 f4832c;
    private GL20 f4833d;
    private int f4834e;
    private float[] f4835f;

    /* renamed from: com.google.android.m4b.maps.ay.d.a */
    static class GL20 {
        private float[] f4826a;

        public GL20() {
            this.f4826a = new float[16];
        }

        public final void m7488a(float f, float f2, float f3) {
            Matrix.translateM(this.f4826a, 0, f, f2, f3);
        }

        public final void m7491a(float[] fArr, int i) {
            Matrix.multiplyMM(this.f4826a, 0, fArr, i, this.f4826a, 0);
        }

        public final GL20 m7487a() {
            Arrays.fill(this.f4826a, 0.0f);
            this.f4826a[0] = br.DEFAULT_BACKOFF_MULT;
            this.f4826a[5] = br.DEFAULT_BACKOFF_MULT;
            this.f4826a[10] = br.DEFAULT_BACKOFF_MULT;
            this.f4826a[15] = br.DEFAULT_BACKOFF_MULT;
            return this;
        }

        public final void m7489a(float f, float f2, float f3, float f4) {
            Matrix.rotateM(this.f4826a, 0, f, f2, f3, f4);
        }

        public final void m7493b(float f, float f2, float f3) {
            Matrix.scaleM(this.f4826a, 0, f, f2, f3);
        }

        public final void m7494b(float[] fArr, int i) {
            System.arraycopy(fArr, i, this.f4826a, 0, 16);
        }

        public final void m7490a(GL20 gl20) {
            m7494b(gl20.f4826a, 0);
        }

        public final void m7492b() {
            for (int i = 0; i < this.f4826a.length; i++) {
                this.f4826a[i] = 0.0f;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ay.d.b */
    static class GL20 {
        private ArrayList<GL20> f4827a;
        private int f4828b;
        private int f4829c;

        public final GL20 m7495a() {
            GL20 gl20;
            if (this.f4828b >= this.f4827a.size()) {
                gl20 = new GL20();
                this.f4827a.add(gl20);
            } else {
                gl20 = (GL20) this.f4827a.get(this.f4828b);
                gl20.m7492b();
            }
            this.f4828b++;
            return gl20;
        }

        public final GL20 m7496b() {
            this.f4828b--;
            return (GL20) this.f4827a.get(this.f4828b);
        }

        public final GL20 m7497c() {
            return (GL20) this.f4827a.get(this.f4828b - 1);
        }

        public final int m7498d() {
            return this.f4829c;
        }
    }

    public GL20() {
        GL20 gl20 = new GL20();
        this.f4834e = 0;
        this.f4835f = new float[4];
        throw new RuntimeException("GL20 class is not ready for production use");
    }

    public final void glActiveTexture(int i) {
        GL20.m7499a();
    }

    public final void glAlphaFunc(int i, float f) {
        GL20.m7499a();
    }

    public final void glAlphaFuncx(int i, int i2) {
        GL20.m7499a();
    }

    public final void glBindTexture(int i, int i2) {
        GLES10.glBindTexture(i, i2);
        EntityRenderer.m8233c();
    }

    public final void glBlendFunc(int i, int i2) {
        GLES10.glBlendFunc(i, i2);
        EntityRenderer.m8233c();
    }

    public final void glClear(int i) {
        GLES10.glClear(i);
        EntityRenderer.m8233c();
    }

    public final void glClearColor(float f, float f2, float f3, float f4) {
        GLES10.glClearColor(f, f2, f3, f4);
        EntityRenderer.m8233c();
    }

    public final void glClearColorx(int i, int i2, int i3, int i4) {
        System.err.println("Draw Count " + this.f4834e);
        this.f4834e = 0;
        GLES10.glClearColorx(i, i2, i3, i4);
        EntityRenderer.m8233c();
    }

    public final void glClearDepthf(float f) {
        GL20.m7499a();
    }

    public final void glClearDepthx(int i) {
        GL20.m7499a();
    }

    public final void glClearStencil(int i) {
        GLES10.glClearStencil(i);
    }

    public final void glClientActiveTexture(int i) {
        GL20.m7499a();
    }

    public final void glColor4f(float f, float f2, float f3, float f4) {
        GLES10.glColor4f(f, f2, f3, f4);
    }

    public final void glColor4x(int i, int i2, int i3, int i4) {
        GLES10.glColor4x(i, i2, i3, i4);
    }

    public final void glColorMask(boolean z, boolean z2, boolean z3, boolean z4) {
        GL20.m7499a();
    }

    public final void glColorPointer(int i, int i2, int i3, Buffer buffer) {
        GLES10.glColorPointer(i, i2, i3, buffer);
    }

    public final void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glCopyTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        GL20.m7499a();
    }

    public final void glCopyTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        GL20.m7499a();
    }

    public final void glCullFace(int i) {
        GLES10.glCullFace(i);
        EntityRenderer.m8233c();
    }

    public final void glDeleteTextures(int i, int[] iArr, int i2) {
        GLES10.glDeleteTextures(i, iArr, i2);
        EntityRenderer.m8233c();
    }

    public final void glDeleteTextures(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glDepthFunc(int i) {
        GLES10.glDepthFunc(i);
    }

    public final void glDepthMask(boolean z) {
        GL20.m7499a();
    }

    public final void glDepthRangef(float f, float f2) {
        GL20.m7499a();
    }

    public final void glDepthRangex(int i, int i2) {
        GL20.m7499a();
    }

    public final void glDisable(int i) {
        GLES10.glDisable(i);
        EntityRenderer.m8233c();
    }

    public final void glDisableClientState(int i) {
        GLES10.glDisableClientState(i);
    }

    public final void glDrawArrays(int i, int i2, int i3) {
        this.f4834e++;
        GLES10.glMatrixMode(5889);
        GLES10.glLoadMatrixf(this.f4831b.m7497c().f4826a, 0);
        GLES10.glMatrixMode(5888);
        GLES10.glLoadMatrixf(this.f4830a.m7497c().f4826a, 0);
        GLES10.glDrawArrays(i, i2, i3);
        GLES10.glMatrixMode(this.f4833d.m7498d());
        EntityRenderer.m8233c();
    }

    public final void glDrawElements(int i, int i2, int i3, Buffer buffer) {
        this.f4834e++;
        GLES10.glMatrixMode(5889);
        GLES10.glLoadMatrixf(this.f4831b.m7497c().f4826a, 0);
        GLES10.glMatrixMode(5888);
        GLES10.glLoadMatrixf(this.f4830a.m7497c().f4826a, 0);
        GLES10.glDrawElements(i, i2, i3, buffer);
        GLES10.glMatrixMode(this.f4833d.m7498d());
        EntityRenderer.m8233c();
    }

    public final void glEnable(int i) {
        GLES10.glEnable(i);
        EntityRenderer.m8233c();
    }

    public final void glEnableClientState(int i) {
        GLES10.glEnableClientState(i);
    }

    public final void glFinish() {
        GL20.m7499a();
    }

    public final void glFlush() {
        GL20.m7499a();
    }

    public final void glFogf(int i, float f) {
        GL20.m7499a();
    }

    public final void glFogfv(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glFogfv(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glFogx(int i, int i2) {
        GL20.m7499a();
    }

    public final void glFogxv(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glFogxv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glFrontFace(int i) {
        GLES10.glFrontFace(i);
        EntityRenderer.m8233c();
    }

    public final void glFrustumf(float f, float f2, float f3, float f4, float f5, float f6) {
        GL20.m7499a();
    }

    public final void glFrustumx(int i, int i2, int i3, int i4, int i5, int i6) {
        GL20.m7499a();
    }

    public final void glGenTextures(int i, int[] iArr, int i2) {
        GLES10.glGenTextures(i, iArr, i2);
        EntityRenderer.m8233c();
    }

    public final void glGenTextures(int i, IntBuffer intBuffer) {
        GLES10.glGenTextures(i, intBuffer);
        EntityRenderer.m8233c();
    }

    public final int glGetError() {
        return GLES10.glGetError();
    }

    public final void glGetIntegerv(int i, int[] iArr, int i2) {
        GLES10.glGetIntegerv(i, iArr, i2);
        EntityRenderer.m8233c();
    }

    public final void glGetIntegerv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final String glGetString(int i) {
        return GLES10.glGetString(i);
    }

    public final void glHint(int i, int i2) {
        GLES10.glHint(i, i2);
        EntityRenderer.m8233c();
    }

    public final void glLightModelf(int i, float f) {
        GL20.m7499a();
    }

    public final void glLightModelfv(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glLightModelfv(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glLightModelx(int i, int i2) {
        GL20.m7499a();
    }

    public final void glLightModelxv(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glLightModelxv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glLightf(int i, int i2, float f) {
        GL20.m7499a();
    }

    public final void glLightfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glLightfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glLightx(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glLightxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glLightxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glLineWidth(float f) {
        GLES10.glLineWidth(f);
    }

    public final void glLineWidthx(int i) {
        GLES10.glLineWidthx(i);
    }

    public final void glLoadIdentity() {
        this.f4833d.m7497c().m7487a();
        GLES10.glLoadIdentity();
    }

    public final void glLoadMatrixf(float[] fArr, int i) {
        this.f4833d.m7497c().m7494b(fArr, i);
        GLES10.glLoadMatrixf(fArr, i);
    }

    public final void glLoadMatrixf(FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glLoadMatrixx(int[] iArr, int i) {
        GL20.m7499a();
    }

    public final void glLoadMatrixx(IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glLogicOp(int i) {
        GL20.m7499a();
    }

    public final void glMaterialf(int i, int i2, float f) {
        GL20.m7499a();
    }

    public final void glMaterialfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glMaterialfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glMaterialx(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glMaterialxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glMaterialxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glMatrixMode(int i) {
        switch (i) {
            case 5888:
                this.f4833d = this.f4830a;
                break;
            case 5889:
                this.f4833d = this.f4831b;
                break;
            case 5890:
                this.f4833d = this.f4832c;
                break;
            default:
                throw new RuntimeException("unexpected value");
        }
        GLES10.glMatrixMode(i);
    }

    public final void glMultMatrixf(float[] fArr, int i) {
        this.f4833d.m7497c().m7491a(fArr, i);
        GLES10.glMultMatrixf(fArr, i);
    }

    public final void glMultMatrixf(FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glMultMatrixx(int[] iArr, int i) {
        GL20.m7499a();
    }

    public final void glMultMatrixx(IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glMultiTexCoord4f(int i, float f, float f2, float f3, float f4) {
        GL20.m7499a();
    }

    public final void glMultiTexCoord4x(int i, int i2, int i3, int i4, int i5) {
        GL20.m7499a();
    }

    public final void glNormal3f(float f, float f2, float f3) {
        GL20.m7499a();
    }

    public final void glNormal3x(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glNormalPointer(int i, int i2, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glOrthof(float f, float f2, float f3, float f4, float f5, float f6) {
        GLES10.glOrthof(f, f2, f3, f4, f5, f6);
    }

    public final void glOrthox(int i, int i2, int i3, int i4, int i5, int i6) {
        GL20.m7499a();
    }

    public final void glPixelStorei(int i, int i2) {
        GL20.m7499a();
    }

    public final void glPointSize(float f) {
        GLES10.glPointSize(f);
    }

    public final void glPointSizex(int i) {
        GL20.m7499a();
    }

    public final void glPolygonOffset(float f, float f2) {
        GLES10.glPolygonOffset(f, f2);
    }

    public final void glPolygonOffsetx(int i, int i2) {
        GLES10.glPolygonOffset((float) i, (float) i2);
    }

    public final void glPopMatrix() {
        this.f4833d.m7496b();
        GLES10.glPopMatrix();
    }

    public final void glPushMatrix() {
        this.f4833d.m7495a().m7490a(this.f4833d.m7497c());
        GLES10.glPushMatrix();
    }

    public final void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glRotatef(float f, float f2, float f3, float f4) {
        this.f4833d.m7497c().m7489a(f, f2, f3, f4);
        GLES10.glRotatef(f, f2, f3, f4);
    }

    public final void glRotatex(int i, int i2, int i3, int i4) {
        this.f4833d.m7497c().m7489a((float) i, (float) i2, (float) i3, (float) i4);
        GLES10.glRotatex(i, i2, i3, i4);
    }

    public final void glSampleCoverage(float f, boolean z) {
        GL20.m7499a();
    }

    public final void glSampleCoveragex(int i, boolean z) {
        GL20.m7499a();
    }

    public final void glScalef(float f, float f2, float f3) {
        this.f4833d.m7497c().m7493b(f, f2, f3);
        GLES10.glScalef(f, f2, f3);
    }

    public final void glScalex(int i, int i2, int i3) {
        this.f4833d.m7497c().m7493b((float) i, (float) i2, (float) i3);
        GLES10.glScalex(i, i2, i3);
    }

    public final void glScissor(int i, int i2, int i3, int i4) {
        GL20.m7499a();
    }

    public final void glShadeModel(int i) {
        GLES10.glShadeModel(i);
    }

    public final void glStencilFunc(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glStencilMask(int i) {
        GLES10.glStencilMask(i);
    }

    public final void glStencilOp(int i, int i2, int i3) {
        GLES10.glStencilOp(i, i2, i3);
    }

    public final void glTexCoordPointer(int i, int i2, int i3, Buffer buffer) {
        GLES10.glTexCoordPointer(i, i2, i3, buffer);
    }

    public final void glTexEnvf(int i, int i2, float f) {
        GL20.m7499a();
    }

    public final void glTexEnvfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexEnvfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glTexEnvx(int i, int i2, int i3) {
        GLES10.glTexEnvx(i, i2, i3);
    }

    public final void glTexEnvxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexEnvxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glTexParameterf(int i, int i2, float f) {
        GLES10.glTexParameterf(i, i2, f);
        EntityRenderer.m8233c();
    }

    public final void glTexParameterx(int i, int i2, int i3) {
        GLES10.glTexParameterx(i, i2, i3);
    }

    public final void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glTranslatef(float f, float f2, float f3) {
        this.f4833d.m7497c().m7488a(f, f2, f3);
        GLES10.glTranslatef(f, f2, f3);
    }

    public final void glTranslatex(int i, int i2, int i3) {
        this.f4833d.m7497c().m7488a((float) i, (float) i2, (float) i3);
        GLES10.glTranslatef((float) i, (float) i2, (float) i3);
    }

    public final void glVertexPointer(int i, int i2, int i3, Buffer buffer) {
        GLES10.glVertexPointer(i, i2, i3, buffer);
    }

    public final void glViewport(int i, int i2, int i3, int i4) {
        GLES10.glViewport(i, i2, i3, i4);
        EntityRenderer.m8233c();
    }

    public final void glGetPointerv(int i, Buffer[] bufferArr) {
        GL20.m7499a();
    }

    public final void glBindBuffer(int i, int i2) {
        GLES11.glBindBuffer(i, i2);
        EntityRenderer.m8233c();
    }

    public final void glBufferData(int i, int i2, Buffer buffer, int i3) {
        GLES11.glBufferData(i, i2, buffer, i3);
        EntityRenderer.m8233c();
    }

    public final void glBufferSubData(int i, int i2, int i3, Buffer buffer) {
        GLES11.glBufferSubData(i, i2, i3, buffer);
        EntityRenderer.m8233c();
    }

    public final void glClipPlanef(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glClipPlanef(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glClipPlanex(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glClipPlanex(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glColor4ub(byte b, byte b2, byte b3, byte b4) {
        GL20.m7499a();
    }

    public final void glColorPointer(int i, int i2, int i3, int i4) {
        GLES11.glColorPointer(i, i2, i3, i4);
    }

    public final void glDeleteBuffers(int i, int[] iArr, int i2) {
        GLES11.glDeleteBuffers(i, iArr, i2);
        EntityRenderer.m8233c();
    }

    public final void glDeleteBuffers(int i, IntBuffer intBuffer) {
        GLES11.glDeleteBuffers(i, intBuffer);
        EntityRenderer.m8233c();
    }

    public final void glDrawElements(int i, int i2, int i3, int i4) {
        this.f4834e++;
        GLES10.glMatrixMode(5889);
        GLES10.glLoadMatrixf(this.f4831b.m7497c().f4826a, 0);
        GLES10.glMatrixMode(5888);
        GLES10.glLoadMatrixf(this.f4830a.m7497c().f4826a, 0);
        GLES11.glDrawElements(i, i2, i3, i4);
        GLES10.glMatrixMode(this.f4833d.m7498d());
        EntityRenderer.m8233c();
    }

    public final void glGenBuffers(int i, int[] iArr, int i2) {
        GLES11.glGenBuffers(i, iArr, i2);
        EntityRenderer.m8233c();
    }

    public final void glGenBuffers(int i, IntBuffer intBuffer) {
        GLES11.glGenBuffers(i, intBuffer);
        EntityRenderer.m8233c();
    }

    public final void glGetBooleanv(int i, boolean[] zArr, int i2) {
        GL20.m7499a();
    }

    public final void glGetBooleanv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetBufferParameteriv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetBufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetClipPlanef(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glGetClipPlanef(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glGetClipPlanex(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glGetClipPlanex(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetFixedv(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glGetFixedv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetFloatv(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glGetFloatv(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glGetLightfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetLightfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glGetLightxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetLightxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetMaterialfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetMaterialfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glGetMaterialxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetMaterialxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetTexEnviv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetTexEnviv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetTexEnvxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetTexEnvxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetTexParameterfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glGetTexParameteriv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glGetTexParameterxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glGetTexParameterxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final boolean glIsBuffer(int i) {
        GL20.m7499a();
        return false;
    }

    public final boolean glIsEnabled(int i) {
        GL20.m7499a();
        return false;
    }

    public final boolean glIsTexture(int i) {
        GL20.m7499a();
        return false;
    }

    public final void glNormalPointer(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glPointParameterf(int i, float f) {
        GL20.m7499a();
    }

    public final void glPointParameterfv(int i, float[] fArr, int i2) {
        GL20.m7499a();
    }

    public final void glPointParameterfv(int i, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glPointParameterx(int i, int i2) {
        GL20.m7499a();
    }

    public final void glPointParameterxv(int i, int[] iArr, int i2) {
        GL20.m7499a();
    }

    public final void glPointParameterxv(int i, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glPointSizePointerOES(int i, int i2, Buffer buffer) {
        GL20.m7499a();
    }

    public final void glTexCoordPointer(int i, int i2, int i3, int i4) {
        GLES11.glTexCoordPointer(i, i2, i3, i4);
        EntityRenderer.m8233c();
    }

    public final void glTexEnvi(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glTexEnviv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexEnviv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glTexParameterfv(int i, int i2, float[] fArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        GL20.m7499a();
    }

    public final void glTexParameteri(int i, int i2, int i3) {
        GL20.m7499a();
    }

    public final void glTexParameteriv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glTexParameterxv(int i, int i2, int[] iArr, int i3) {
        GL20.m7499a();
    }

    public final void glTexParameterxv(int i, int i2, IntBuffer intBuffer) {
        GL20.m7499a();
    }

    public final void glVertexPointer(int i, int i2, int i3, int i4) {
        GLES11.glVertexPointer(i, i2, i3, i4);
    }

    private static void m7499a() {
        throw new UnsupportedOperationException("Not supported");
    }
}
