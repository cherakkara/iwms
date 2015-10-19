package com.google.android.m4b.maps.bd;

import android.opengl.GLES20;
import com.google.android.m4b.maps.bd.Entity.Entity;
import com.google.android.m4b.maps.bd.EntityState.EntityState;
import com.sothree.slidinguppanel.p086a.R.R;

/* renamed from: com.google.android.m4b.maps.bd.r */
public class TextureState extends EntityState {
    private ImageData f4929d;
    private int[] f4930e;
    private volatile int f4931f;
    private volatile int f4932g;
    private volatile boolean f4933h;
    private volatile int f4934i;
    private volatile int f4935j;
    private volatile boolean f4936k;
    private final int f4937l;
    private boolean f4938m;

    private TextureState(ImageData imageData) {
        this(null, 0);
    }

    protected TextureState() {
        this(null);
    }

    private TextureState(ImageData imageData, int i) {
        this(imageData, 0, false);
    }

    private TextureState(ImageData imageData, int i, boolean z) {
        EntityState entityState;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                entityState = EntityState.TEXTURE0;
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                entityState = EntityState.TEXTURE1;
                break;
            default:
                throw new IllegalArgumentException("Unsupported texture unit " + i);
        }
        super(entityState);
        this.f4929d = null;
        this.f4930e = new int[1];
        this.f4931f = 9728;
        this.f4932g = 9729;
        this.f4933h = false;
        this.f4934i = 10497;
        this.f4935j = 10497;
        this.f4936k = false;
        this.f4938m = false;
        Matrix3f matrix3f = new Matrix3f();
        this.f4929d = imageData;
        this.f4938m = false;
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.f4937l = 33984;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                this.f4937l = 33985;
            default:
                throw new IllegalArgumentException("Unsupported texture unit " + i);
        }
    }

    protected final void m7599a(ImageData imageData, boolean z) {
        if (this.b) {
            throw new RuntimeException("Must be called BEFORE set live");
        }
        this.f4938m = z;
        this.f4929d = imageData;
    }

    final boolean m7600a(EntityRenderer entityRenderer, Entity entity) {
        boolean a = super.m7594a(entityRenderer, entity);
        if (a) {
            if (entity.f5481d) {
                GLES20.glGenTextures(1, this.f4930e, 0);
                GLES20.glBindTexture(3553, this.f4930e[0]);
                this.f4929d.m8246a(entity);
                GLES20.glTexParameteri(3553, 10241, this.f4931f);
                GLES20.glTexParameteri(3553, 10240, this.f4932g);
                GLES20.glTexParameteri(3553, 10242, this.f4934i);
                GLES20.glTexParameteri(3553, 10243, this.f4935j);
                if (this.f4938m) {
                    GLES20.glGenerateMipmap(3553);
                }
            } else {
                this.f4929d.m8246a(entity);
                GLES20.glDeleteTextures(1, this.f4930e, 0);
            }
        }
        return a;
    }

    public final void m7601b(int i, int i2) {
        if (this.b) {
            BehaviorManagerImpl.m8219b();
        }
        if ((i == 10497 || i == 33071 || i == 33648) && (i2 == 10497 || i2 == 33071 || i2 == 33648)) {
            this.f4934i = i;
            this.f4935j = i2;
            this.f4936k = true;
            return;
        }
        throw new IllegalArgumentException("Illegal Wrap Mode: wrapS = " + i + " wrapT = " + i2);
    }

    public final void m7602c(int i, int i2) {
        if (this.b) {
            BehaviorManagerImpl.m8219b();
        }
        if ((i == 9729 || i == 9728 || i == 9987 || i == 9985 || i == 9986 || i == 9984) && (i2 == 9729 || i2 == 9728)) {
            this.f4931f = i;
            this.f4932g = i2;
            this.f4933h = true;
            return;
        }
        throw new IllegalArgumentException("Illegal Filter Mode: min = " + i + " mag = " + i2);
    }
}
