package com.google.android.m4b.maps.bd;

import android.opengl.GLES20;
import com.google.android.m4b.maps.bd.Entity.Entity;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.bd.p */
public class ShaderProgram {
    private String f5522a;
    private String f5523b;
    private int f5524c;
    private boolean f5525d;

    protected void m8251a(int i) {
        ShaderProgram.m8249a(i, "uMVPMatrix");
    }

    protected static int m8249a(int i, String str) {
        int glGetUniformLocation = GLES20.glGetUniformLocation(i, str);
        if (glGetUniformLocation == -1) {
            throw new IllegalStateException("Unable to get " + str + " handle");
        }
        EntityRenderer.m8233c();
        return glGetUniformLocation;
    }

    public final boolean m8252a(Entity entity) {
        if (entity.f5481d == this.f5525d && !entity.f5482e) {
            return false;
        }
        if (!entity.f5481d && !entity.f5482e) {
            return false;
        }
        this.f5525d = entity.f5481d;
        if (this.f5525d) {
            int i;
            if (entity.f5482e) {
                this.f5524c = 0;
            }
            Preconditions.m1830b(this.f5524c == 0, "Attempt to overwrite existing shader program: %s", Integer.valueOf(this.f5524c));
            String str = this.f5522a;
            String str2 = this.f5523b;
            int b = ShaderProgram.m8250b(35633, str);
            if (b == 0) {
                i = 0;
            } else {
                int b2 = ShaderProgram.m8250b(35632, str2);
                if (b2 == 0) {
                    i = 0;
                } else {
                    i = GLES20.glCreateProgram();
                    if (i != 0) {
                        GLES20.glAttachShader(i, b);
                        EntityRenderer.m8233c();
                        GLES20.glAttachShader(i, b2);
                        EntityRenderer.m8233c();
                        GLES20.glBindAttribLocation(i, 0, "aPosition");
                        EntityRenderer.m8233c();
                        GLES20.glBindAttribLocation(i, 1, "aNormal");
                        EntityRenderer.m8233c();
                        GLES20.glBindAttribLocation(i, 2, "aColor");
                        EntityRenderer.m8233c();
                        GLES20.glBindAttribLocation(i, 4, "aTextureCoord");
                        EntityRenderer.m8233c();
                        GLES20.glLinkProgram(i);
                        int[] iArr = new int[1];
                        GLES20.glGetProgramiv(i, 35714, iArr, 0);
                        if (iArr[0] != 1) {
                            GLES20.glDeleteProgram(i);
                            i = 0;
                        }
                    }
                }
            }
            this.f5524c = i;
            GLES20.glUseProgram(this.f5524c);
            m8251a(this.f5524c);
            GLES20.glUseProgram(0);
        } else {
            if (!entity.f5482e) {
                GLES20.glDeleteProgram(this.f5524c);
            }
            this.f5524c = 0;
        }
        return true;
    }

    private static int m8250b(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                GLES20.glDeleteShader(glCreateShader);
                return 0;
            }
        }
        return glCreateShader;
    }
}
