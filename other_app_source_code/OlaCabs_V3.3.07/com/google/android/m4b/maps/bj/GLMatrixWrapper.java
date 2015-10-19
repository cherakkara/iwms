package com.google.android.m4b.maps.bj;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL10Ext;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;

/* renamed from: com.google.android.m4b.maps.bj.l */
final class GLMatrixWrapper implements GL, GL10, GL10Ext, GL11, GL11Ext {
    private final GL10 f6536a;
    private final GL10Ext f6537b;
    private final GL11 f6538c;
    private final GL11Ext f6539d;
    private MatrixStack f6540e;
    private final MatrixStack f6541f;
    private final MatrixStack f6542g;
    private final MatrixStack f6543h;

    public GLMatrixWrapper(GL gl) {
        GL11 gl11;
        this.f6536a = (GL10) gl;
        this.f6537b = gl instanceof GL10Ext ? (GL10Ext) gl : null;
        if (gl instanceof GL11) {
            gl11 = (GL11) gl;
        } else {
            gl11 = null;
        }
        this.f6538c = gl11;
        if (gl instanceof GL11Ext) {
            gl = (GL11Ext) gl;
        } else {
            gl = null;
        }
        this.f6539d = gl;
        this.f6541f = new MatrixStack();
        this.f6543h = new MatrixStack();
        this.f6542g = new MatrixStack();
        this.f6540e = this.f6541f;
    }

    public final void glActiveTexture(int i) {
        this.f6536a.glActiveTexture(i);
    }

    public final void glAlphaFunc(int i, float f) {
        this.f6536a.glAlphaFunc(i, f);
    }

    public final void glAlphaFuncx(int i, int i2) {
        this.f6536a.glAlphaFuncx(i, i2);
    }

    public final void glBindTexture(int i, int i2) {
        this.f6536a.glBindTexture(i, i2);
    }

    public final void glBlendFunc(int i, int i2) {
        this.f6536a.glBlendFunc(i, i2);
    }

    public final void glClear(int i) {
        this.f6536a.glClear(i);
    }

    public final void glClearColor(float f, float f2, float f3, float f4) {
        this.f6536a.glClearColor(f, f2, f3, f4);
    }

    public final void glClearColorx(int i, int i2, int i3, int i4) {
        this.f6536a.glClearColorx(i, i2, i3, i4);
    }

    public final void glClearDepthf(float f) {
        this.f6536a.glClearDepthf(f);
    }

    public final void glClearDepthx(int i) {
        this.f6536a.glClearDepthx(i);
    }

    public final void glClearStencil(int i) {
        this.f6536a.glClearStencil(i);
    }

    public final void glClientActiveTexture(int i) {
        this.f6536a.glClientActiveTexture(i);
    }

    public final void glColor4f(float f, float f2, float f3, float f4) {
        this.f6536a.glColor4f(f, f2, f3, f4);
    }

    public final void glColor4x(int i, int i2, int i3, int i4) {
        this.f6536a.glColor4x(i, i2, i3, i4);
    }

    public final void glColorMask(boolean z, boolean z2, boolean z3, boolean z4) {
        this.f6536a.glColorMask(z, z2, z3, z4);
    }

    public final void glColorPointer(int i, int i2, int i3, Buffer buffer) {
        this.f6536a.glColorPointer(i, i2, i3, buffer);
    }

    public final void glCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        this.f6536a.glCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, buffer);
    }

    public final void glCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.f6536a.glCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    public final void glCopyTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f6536a.glCopyTexImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public final void glCopyTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f6536a.glCopyTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public final void glCullFace(int i) {
        this.f6536a.glCullFace(i);
    }

    public final void glDeleteTextures(int i, int[] iArr, int i2) {
        this.f6536a.glDeleteTextures(i, iArr, i2);
    }

    public final void glDeleteTextures(int i, IntBuffer intBuffer) {
        this.f6536a.glDeleteTextures(i, intBuffer);
    }

    public final void glDepthFunc(int i) {
        this.f6536a.glDepthFunc(i);
    }

    public final void glDepthMask(boolean z) {
        this.f6536a.glDepthMask(z);
    }

    public final void glDepthRangef(float f, float f2) {
        this.f6536a.glDepthRangef(f, f2);
    }

    public final void glDepthRangex(int i, int i2) {
        this.f6536a.glDepthRangex(i, i2);
    }

    public final void glDisable(int i) {
        this.f6536a.glDisable(i);
    }

    public final void glDisableClientState(int i) {
        this.f6536a.glDisableClientState(i);
    }

    public final void glDrawArrays(int i, int i2, int i3) {
        this.f6536a.glDrawArrays(i, i2, i3);
    }

    public final void glDrawElements(int i, int i2, int i3, Buffer buffer) {
        this.f6536a.glDrawElements(i, i2, i3, buffer);
    }

    public final void glEnable(int i) {
        this.f6536a.glEnable(i);
    }

    public final void glEnableClientState(int i) {
        this.f6536a.glEnableClientState(i);
    }

    public final void glFinish() {
        this.f6536a.glFinish();
    }

    public final void glFlush() {
        this.f6536a.glFlush();
    }

    public final void glFogf(int i, float f) {
        this.f6536a.glFogf(i, f);
    }

    public final void glFogfv(int i, float[] fArr, int i2) {
        this.f6536a.glFogfv(i, fArr, i2);
    }

    public final void glFogfv(int i, FloatBuffer floatBuffer) {
        this.f6536a.glFogfv(i, floatBuffer);
    }

    public final void glFogx(int i, int i2) {
        this.f6536a.glFogx(i, i2);
    }

    public final void glFogxv(int i, int[] iArr, int i2) {
        this.f6536a.glFogxv(i, iArr, i2);
    }

    public final void glFogxv(int i, IntBuffer intBuffer) {
        this.f6536a.glFogxv(i, intBuffer);
    }

    public final void glFrontFace(int i) {
        this.f6536a.glFrontFace(i);
    }

    public final void glFrustumf(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f6540e.m10038a(f, f2, f3, f4, f5, f6);
        this.f6536a.glFrustumf(f, f2, f3, f4, f5, f6);
    }

    public final void glFrustumx(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f6540e.m10041a(i, i2, i3, i4, i5, i6);
        this.f6536a.glFrustumx(i, i2, i3, i4, i5, i6);
    }

    public final void glGenTextures(int i, int[] iArr, int i2) {
        this.f6536a.glGenTextures(i, iArr, i2);
    }

    public final void glGenTextures(int i, IntBuffer intBuffer) {
        this.f6536a.glGenTextures(i, intBuffer);
    }

    public final int glGetError() {
        return this.f6536a.glGetError();
    }

    public final void glGetIntegerv(int i, int[] iArr, int i2) {
        this.f6536a.glGetIntegerv(i, iArr, i2);
    }

    public final void glGetIntegerv(int i, IntBuffer intBuffer) {
        this.f6536a.glGetIntegerv(i, intBuffer);
    }

    public final String glGetString(int i) {
        return this.f6536a.glGetString(i);
    }

    public final void glHint(int i, int i2) {
        this.f6536a.glHint(i, i2);
    }

    public final void glLightModelf(int i, float f) {
        this.f6536a.glLightModelf(i, f);
    }

    public final void glLightModelfv(int i, float[] fArr, int i2) {
        this.f6536a.glLightModelfv(i, fArr, i2);
    }

    public final void glLightModelfv(int i, FloatBuffer floatBuffer) {
        this.f6536a.glLightModelfv(i, floatBuffer);
    }

    public final void glLightModelx(int i, int i2) {
        this.f6536a.glLightModelx(i, i2);
    }

    public final void glLightModelxv(int i, int[] iArr, int i2) {
        this.f6536a.glLightModelxv(i, iArr, i2);
    }

    public final void glLightModelxv(int i, IntBuffer intBuffer) {
        this.f6536a.glLightModelxv(i, intBuffer);
    }

    public final void glLightf(int i, int i2, float f) {
        this.f6536a.glLightf(i, i2, f);
    }

    public final void glLightfv(int i, int i2, float[] fArr, int i3) {
        this.f6536a.glLightfv(i, i2, fArr, i3);
    }

    public final void glLightfv(int i, int i2, FloatBuffer floatBuffer) {
        this.f6536a.glLightfv(i, i2, floatBuffer);
    }

    public final void glLightx(int i, int i2, int i3) {
        this.f6536a.glLightx(i, i2, i3);
    }

    public final void glLightxv(int i, int i2, int[] iArr, int i3) {
        this.f6536a.glLightxv(i, i2, iArr, i3);
    }

    public final void glLightxv(int i, int i2, IntBuffer intBuffer) {
        this.f6536a.glLightxv(i, i2, intBuffer);
    }

    public final void glLineWidth(float f) {
        this.f6536a.glLineWidth(f);
    }

    public final void glLineWidthx(int i) {
        this.f6536a.glLineWidthx(i);
    }

    public final void glLoadIdentity() {
        this.f6540e.m10035a();
        this.f6536a.glLoadIdentity();
    }

    public final void glLoadMatrixf(float[] fArr, int i) {
        this.f6540e.m10044a(fArr, i);
        this.f6536a.glLoadMatrixf(fArr, i);
    }

    public final void glLoadMatrixf(FloatBuffer floatBuffer) {
        int position = floatBuffer.position();
        this.f6540e.m10042a(floatBuffer);
        floatBuffer.position(position);
        this.f6536a.glLoadMatrixf(floatBuffer);
    }

    public final void glLoadMatrixx(int[] iArr, int i) {
        this.f6540e.m10045a(iArr, i);
        this.f6536a.glLoadMatrixx(iArr, i);
    }

    public final void glLoadMatrixx(IntBuffer intBuffer) {
        int position = intBuffer.position();
        this.f6540e.m10043a(intBuffer);
        intBuffer.position(position);
        this.f6536a.glLoadMatrixx(intBuffer);
    }

    public final void glLogicOp(int i) {
        this.f6536a.glLogicOp(i);
    }

    public final void glMaterialf(int i, int i2, float f) {
        this.f6536a.glMaterialf(i, i2, f);
    }

    public final void glMaterialfv(int i, int i2, float[] fArr, int i3) {
        this.f6536a.glMaterialfv(i, i2, fArr, i3);
    }

    public final void glMaterialfv(int i, int i2, FloatBuffer floatBuffer) {
        this.f6536a.glMaterialfv(i, i2, floatBuffer);
    }

    public final void glMaterialx(int i, int i2, int i3) {
        this.f6536a.glMaterialx(i, i2, i3);
    }

    public final void glMaterialxv(int i, int i2, int[] iArr, int i3) {
        this.f6536a.glMaterialxv(i, i2, iArr, i3);
    }

    public final void glMaterialxv(int i, int i2, IntBuffer intBuffer) {
        this.f6536a.glMaterialxv(i, i2, intBuffer);
    }

    public final void glMatrixMode(int i) {
        switch (i) {
            case 5888:
                this.f6540e = this.f6541f;
                break;
            case 5889:
                this.f6540e = this.f6543h;
                break;
            case 5890:
                this.f6540e = this.f6542g;
                break;
            default:
                throw new IllegalArgumentException("Unknown matrix mode: " + i);
        }
        this.f6536a.glMatrixMode(i);
    }

    public final void glMultMatrixf(float[] fArr, int i) {
        this.f6540e.m10053b(fArr, i);
        this.f6536a.glMultMatrixf(fArr, i);
    }

    public final void glMultMatrixf(FloatBuffer floatBuffer) {
        int position = floatBuffer.position();
        this.f6540e.m10051b(floatBuffer);
        floatBuffer.position(position);
        this.f6536a.glMultMatrixf(floatBuffer);
    }

    public final void glMultMatrixx(int[] iArr, int i) {
        this.f6540e.m10054b(iArr, i);
        this.f6536a.glMultMatrixx(iArr, i);
    }

    public final void glMultMatrixx(IntBuffer intBuffer) {
        int position = intBuffer.position();
        this.f6540e.m10052b(intBuffer);
        intBuffer.position(position);
        this.f6536a.glMultMatrixx(intBuffer);
    }

    public final void glMultiTexCoord4f(int i, float f, float f2, float f3, float f4) {
        this.f6536a.glMultiTexCoord4f(i, f, f2, f3, f4);
    }

    public final void glMultiTexCoord4x(int i, int i2, int i3, int i4, int i5) {
        this.f6536a.glMultiTexCoord4x(i, i2, i3, i4, i5);
    }

    public final void glNormal3f(float f, float f2, float f3) {
        this.f6536a.glNormal3f(f, f2, f3);
    }

    public final void glNormal3x(int i, int i2, int i3) {
        this.f6536a.glNormal3x(i, i2, i3);
    }

    public final void glNormalPointer(int i, int i2, Buffer buffer) {
        this.f6536a.glNormalPointer(i, i2, buffer);
    }

    public final void glOrthof(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f6540e.m10048b(f, f2, f3, f4, f5, f6);
        this.f6536a.glOrthof(f, f2, f3, f4, f5, f6);
    }

    public final void glOrthox(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f6540e.m10050b(i, i2, i3, i4, i5, i6);
        this.f6536a.glOrthox(i, i2, i3, i4, i5, i6);
    }

    public final void glPixelStorei(int i, int i2) {
        this.f6536a.glPixelStorei(i, i2);
    }

    public final void glPointSize(float f) {
        this.f6536a.glPointSize(f);
    }

    public final void glPointSizex(int i) {
        this.f6536a.glPointSizex(i);
    }

    public final void glPolygonOffset(float f, float f2) {
        this.f6536a.glPolygonOffset(f, f2);
    }

    public final void glPolygonOffsetx(int i, int i2) {
        this.f6536a.glPolygonOffsetx(i, i2);
    }

    public final void glPopMatrix() {
        this.f6540e.m10046b();
        this.f6536a.glPopMatrix();
    }

    public final void glPushMatrix() {
        this.f6540e.m10055c();
        this.f6536a.glPushMatrix();
    }

    public final void glReadPixels(int i, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        this.f6536a.glReadPixels(i, i2, i3, i4, i5, i6, buffer);
    }

    public final void glRotatef(float f, float f2, float f3, float f4) {
        this.f6540e.m10037a(f, f2, f3, f4);
        this.f6536a.glRotatef(f, f2, f3, f4);
    }

    public final void glRotatex(int i, int i2, int i3, int i4) {
        this.f6540e.m10040a(i, i2, i3, i4);
        this.f6536a.glRotatex(i, i2, i3, i4);
    }

    public final void glSampleCoverage(float f, boolean z) {
        this.f6536a.glSampleCoverage(f, z);
    }

    public final void glSampleCoveragex(int i, boolean z) {
        this.f6536a.glSampleCoveragex(i, z);
    }

    public final void glScalef(float f, float f2, float f3) {
        this.f6540e.m10036a(f, f2, f3);
        this.f6536a.glScalef(f, f2, f3);
    }

    public final void glScalex(int i, int i2, int i3) {
        this.f6540e.m10039a(i, i2, i3);
        this.f6536a.glScalex(i, i2, i3);
    }

    public final void glScissor(int i, int i2, int i3, int i4) {
        this.f6536a.glScissor(i, i2, i3, i4);
    }

    public final void glShadeModel(int i) {
        this.f6536a.glShadeModel(i);
    }

    public final void glStencilFunc(int i, int i2, int i3) {
        this.f6536a.glStencilFunc(i, i2, i3);
    }

    public final void glStencilMask(int i) {
        this.f6536a.glStencilMask(i);
    }

    public final void glStencilOp(int i, int i2, int i3) {
        this.f6536a.glStencilOp(i, i2, i3);
    }

    public final void glTexCoordPointer(int i, int i2, int i3, Buffer buffer) {
        this.f6536a.glTexCoordPointer(i, i2, i3, buffer);
    }

    public final void glTexEnvf(int i, int i2, float f) {
        this.f6536a.glTexEnvf(i, i2, f);
    }

    public final void glTexEnvfv(int i, int i2, float[] fArr, int i3) {
        this.f6536a.glTexEnvfv(i, i2, fArr, i3);
    }

    public final void glTexEnvfv(int i, int i2, FloatBuffer floatBuffer) {
        this.f6536a.glTexEnvfv(i, i2, floatBuffer);
    }

    public final void glTexEnvx(int i, int i2, int i3) {
        this.f6536a.glTexEnvx(i, i2, i3);
    }

    public final void glTexEnvxv(int i, int i2, int[] iArr, int i3) {
        this.f6536a.glTexEnvxv(i, i2, iArr, i3);
    }

    public final void glTexEnvxv(int i, int i2, IntBuffer intBuffer) {
        this.f6536a.glTexEnvxv(i, i2, intBuffer);
    }

    public final void glTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.f6536a.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    public final void glTexParameterf(int i, int i2, float f) {
        this.f6536a.glTexParameterf(i, i2, f);
    }

    public final void glTexParameterx(int i, int i2, int i3) {
        this.f6536a.glTexParameterx(i, i2, i3);
    }

    public final void glTexParameteriv(int i, int i2, int[] iArr, int i3) {
        this.f6538c.glTexParameteriv(i, i2, iArr, i3);
    }

    public final void glTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        this.f6538c.glTexParameteriv(i, i2, intBuffer);
    }

    public final void glTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Buffer buffer) {
        this.f6536a.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, buffer);
    }

    public final void glTranslatef(float f, float f2, float f3) {
        this.f6540e.m10047b(f, f2, f3);
        this.f6536a.glTranslatef(f, f2, f3);
    }

    public final void glTranslatex(int i, int i2, int i3) {
        this.f6540e.m10049b(i, i2, i3);
        this.f6536a.glTranslatex(i, i2, i3);
    }

    public final void glVertexPointer(int i, int i2, int i3, Buffer buffer) {
        this.f6536a.glVertexPointer(i, i2, i3, buffer);
    }

    public final void glViewport(int i, int i2, int i3, int i4) {
        this.f6536a.glViewport(i, i2, i3, i4);
    }

    public final void glClipPlanef(int i, float[] fArr, int i2) {
        this.f6538c.glClipPlanef(i, fArr, i2);
    }

    public final void glClipPlanef(int i, FloatBuffer floatBuffer) {
        this.f6538c.glClipPlanef(i, floatBuffer);
    }

    public final void glClipPlanex(int i, int[] iArr, int i2) {
        this.f6538c.glClipPlanex(i, iArr, i2);
    }

    public final void glClipPlanex(int i, IntBuffer intBuffer) {
        this.f6538c.glClipPlanex(i, intBuffer);
    }

    public final void glDrawTexfOES(float f, float f2, float f3, float f4, float f5) {
        this.f6539d.glDrawTexfOES(f, f2, f3, f4, f5);
    }

    public final void glDrawTexfvOES(float[] fArr, int i) {
        this.f6539d.glDrawTexfvOES(fArr, i);
    }

    public final void glDrawTexfvOES(FloatBuffer floatBuffer) {
        this.f6539d.glDrawTexfvOES(floatBuffer);
    }

    public final void glDrawTexiOES(int i, int i2, int i3, int i4, int i5) {
        this.f6539d.glDrawTexiOES(i, i2, i3, i4, i5);
    }

    public final void glDrawTexivOES(int[] iArr, int i) {
        this.f6539d.glDrawTexivOES(iArr, i);
    }

    public final void glDrawTexivOES(IntBuffer intBuffer) {
        this.f6539d.glDrawTexivOES(intBuffer);
    }

    public final void glDrawTexsOES(short s, short s2, short s3, short s4, short s5) {
        this.f6539d.glDrawTexsOES(s, s2, s3, s4, s5);
    }

    public final void glDrawTexsvOES(short[] sArr, int i) {
        this.f6539d.glDrawTexsvOES(sArr, i);
    }

    public final void glDrawTexsvOES(ShortBuffer shortBuffer) {
        this.f6539d.glDrawTexsvOES(shortBuffer);
    }

    public final void glDrawTexxOES(int i, int i2, int i3, int i4, int i5) {
        this.f6539d.glDrawTexxOES(i, i2, i3, i4, i5);
    }

    public final void glDrawTexxvOES(int[] iArr, int i) {
        this.f6539d.glDrawTexxvOES(iArr, i);
    }

    public final void glDrawTexxvOES(IntBuffer intBuffer) {
        this.f6539d.glDrawTexxvOES(intBuffer);
    }

    public final int glQueryMatrixxOES(int[] iArr, int i, int[] iArr2, int i2) {
        return this.f6537b.glQueryMatrixxOES(iArr, i, iArr2, i2);
    }

    public final int glQueryMatrixxOES(IntBuffer intBuffer, IntBuffer intBuffer2) {
        return this.f6537b.glQueryMatrixxOES(intBuffer, intBuffer2);
    }

    public final void glBindBuffer(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glBufferData(int i, int i2, Buffer buffer, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glBufferSubData(int i, int i2, int i3, Buffer buffer) {
        throw new UnsupportedOperationException();
    }

    public final void glColor4ub(byte b, byte b2, byte b3, byte b4) {
        throw new UnsupportedOperationException();
    }

    public final void glDeleteBuffers(int i, int[] iArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glDeleteBuffers(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGenBuffers(int i, int[] iArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGenBuffers(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetBooleanv(int i, boolean[] zArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGetBooleanv(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetBufferParameteriv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetBufferParameteriv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetClipPlanef(int i, float[] fArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGetClipPlanef(int i, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetClipPlanex(int i, int[] iArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGetClipPlanex(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetFixedv(int i, int[] iArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGetFixedv(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetFloatv(int i, float[] fArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glGetFloatv(int i, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetLightfv(int i, int i2, float[] fArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetLightfv(int i, int i2, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetLightxv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetLightxv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetMaterialfv(int i, int i2, float[] fArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetMaterialfv(int i, int i2, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetMaterialxv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetMaterialxv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexEnviv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexEnviv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexEnvxv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexEnvxv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameterfv(int i, int i2, float[] fArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameteriv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameteriv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameterxv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glGetTexParameterxv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final boolean glIsBuffer(int i) {
        throw new UnsupportedOperationException();
    }

    public final boolean glIsEnabled(int i) {
        throw new UnsupportedOperationException();
    }

    public final boolean glIsTexture(int i) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterf(int i, float f) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterfv(int i, float[] fArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterfv(int i, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterx(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterxv(int i, int[] iArr, int i2) {
        throw new UnsupportedOperationException();
    }

    public final void glPointParameterxv(int i, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glPointSizePointerOES(int i, int i2, Buffer buffer) {
        throw new UnsupportedOperationException();
    }

    public final void glTexEnvi(int i, int i2, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexEnviv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexEnviv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glTexParameterfv(int i, int i2, float[] fArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexParameterfv(int i, int i2, FloatBuffer floatBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glTexParameteri(int i, int i2, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexParameterxv(int i, int i2, int[] iArr, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexParameterxv(int i, int i2, IntBuffer intBuffer) {
        throw new UnsupportedOperationException();
    }

    public final void glColorPointer(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void glDrawElements(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void glGetPointerv(int i, Buffer[] bufferArr) {
        throw new UnsupportedOperationException();
    }

    public final void glNormalPointer(int i, int i2, int i3) {
        throw new UnsupportedOperationException();
    }

    public final void glTexCoordPointer(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void glVertexPointer(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void glCurrentPaletteMatrixOES(int i) {
        throw new UnsupportedOperationException();
    }

    public final void glLoadPaletteFromModelViewMatrixOES() {
        throw new UnsupportedOperationException();
    }

    public final void glMatrixIndexPointerOES(int i, int i2, int i3, Buffer buffer) {
        throw new UnsupportedOperationException();
    }

    public final void glMatrixIndexPointerOES(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void glWeightPointerOES(int i, int i2, int i3, Buffer buffer) {
        throw new UnsupportedOperationException();
    }

    public final void glWeightPointerOES(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException();
    }

    public final void m9967a(float[] fArr, int i) {
        this.f6540e.m10056c(fArr, 0);
    }
}
