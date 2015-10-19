package com.sothree.slidinguppanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.p019a.p020a.p021a.AnimatorProxy;
import com.sothree.slidinguppanel.ViewDragHelper.ViewDragHelper;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.HttpStatus;

public class SlidingUpPanelLayout extends ViewGroup {
    private static final String f11598a;
    private static C0949d f11599b;
    private static final int[] f11600c;
    private C0948c f11601A;
    private final ViewDragHelper f11602B;
    private boolean f11603C;
    private final Rect f11604D;
    private int f11605d;
    private int f11606e;
    private final Paint f11607f;
    private final Drawable f11608g;
    private int f11609h;
    private int f11610i;
    private int f11611j;
    private boolean f11612k;
    private boolean f11613l;
    private boolean f11614m;
    private View f11615n;
    private int f11616o;
    private View f11617p;
    private View f11618q;
    private C0949d f11619r;
    private float f11620s;
    private int f11621t;
    private boolean f11622u;
    private boolean f11623v;
    private boolean f11624w;
    private float f11625x;
    private float f11626y;
    private float f11627z;

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.1 */
    class C09431 implements OnClickListener {
        final /* synthetic */ SlidingUpPanelLayout f11587a;

        C09431(SlidingUpPanelLayout slidingUpPanelLayout) {
            this.f11587a = slidingUpPanelLayout;
        }

        public void onClick(View view) {
            if (!this.f11587a.isEnabled() || !this.f11587a.m15003a()) {
                return;
            }
            if (this.f11587a.f11619r == C0949d.EXPANDED || this.f11587a.f11619r == C0949d.ANCHORED) {
                this.f11587a.setPanelState(C0949d.COLLAPSED);
            } else if (this.f11587a.f11627z < br.DEFAULT_BACKOFF_MULT) {
                this.f11587a.setPanelState(C0949d.ANCHORED);
            } else {
                this.f11587a.setPanelState(C0949d.EXPANDED);
            }
        }
    }

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.2 */
    static /* synthetic */ class C09442 {
        static final /* synthetic */ int[] f11588a;

        static {
            f11588a = new int[C0949d.values().length];
            try {
                f11588a[C0949d.EXPANDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f11588a[C0949d.ANCHORED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f11588a[C0949d.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f11588a[C0949d.COLLAPSED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        C0949d f11589a;

        /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.SavedState.1 */
        static class C09451 implements Creator<SavedState> {
            C09451() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m14957a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m14958a(i);
            }

            public SavedState m14957a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m14958a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            try {
                this.f11589a = (C0949d) Enum.valueOf(C0949d.class, parcel.readString());
            } catch (IllegalArgumentException e) {
                this.f11589a = C0949d.COLLAPSED;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.f11589a.toString());
        }

        static {
            CREATOR = new C09451();
        }
    }

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.a */
    private class C0946a extends ViewDragHelper {
        final /* synthetic */ SlidingUpPanelLayout f11590a;

        private C0946a(SlidingUpPanelLayout slidingUpPanelLayout) {
            this.f11590a = slidingUpPanelLayout;
        }

        public boolean m14977a(View view, int i) {
            if (!this.f11590a.f11622u && view == this.f11590a.f11617p) {
                return true;
            }
            return false;
        }

        public void m14974a(int i) {
            if (this.f11590a.f11602B.m15031a() == 0) {
                this.f11590a.f11620s = this.f11590a.m14984a(this.f11590a.f11617p.getTop());
                if (this.f11590a.f11620s == br.DEFAULT_BACKOFF_MULT) {
                    if (this.f11590a.f11619r != C0949d.EXPANDED) {
                        this.f11590a.m15005b();
                        this.f11590a.f11619r = C0949d.EXPANDED;
                        this.f11590a.m15006b(this.f11590a.f11617p);
                    }
                } else if (this.f11590a.f11620s == 0.0f) {
                    if (this.f11590a.f11619r != C0949d.COLLAPSED) {
                        this.f11590a.f11619r = C0949d.COLLAPSED;
                        this.f11590a.m15008c(this.f11590a.f11617p);
                    }
                } else if (this.f11590a.f11620s < 0.0f) {
                    this.f11590a.f11619r = C0949d.HIDDEN;
                    this.f11590a.f11617p.setVisibility(4);
                    this.f11590a.m15010e(this.f11590a.f11617p);
                } else if (this.f11590a.f11619r != C0949d.ANCHORED) {
                    this.f11590a.m15005b();
                    this.f11590a.f11619r = C0949d.ANCHORED;
                    this.f11590a.m15009d(this.f11590a.f11617p);
                }
            }
        }

        public void m14978b(View view, int i) {
            this.f11590a.m15007c();
        }

        public void m14976a(View view, int i, int i2, int i3, int i4) {
            this.f11590a.m14993b(i2);
            this.f11590a.invalidate();
        }

        public void m14975a(View view, float f, float f2) {
            int b;
            if (this.f11590a.f11612k) {
                f2 = -f2;
            }
            if (f2 > 0.0f) {
                b = this.f11590a.m14987a((float) br.DEFAULT_BACKOFF_MULT);
            } else if (f2 < 0.0f) {
                b = this.f11590a.m14987a(0.0f);
            } else if (this.f11590a.f11627z != br.DEFAULT_BACKOFF_MULT && this.f11590a.f11620s >= (this.f11590a.f11627z + br.DEFAULT_BACKOFF_MULT) / dm.DEFAULT_BACKOFF_MULT) {
                b = this.f11590a.m14987a((float) br.DEFAULT_BACKOFF_MULT);
            } else if (this.f11590a.f11627z == br.DEFAULT_BACKOFF_MULT && this.f11590a.f11620s >= 0.5f) {
                b = this.f11590a.m14987a((float) br.DEFAULT_BACKOFF_MULT);
            } else if (this.f11590a.f11627z != br.DEFAULT_BACKOFF_MULT && this.f11590a.f11620s >= this.f11590a.f11627z) {
                b = this.f11590a.m14987a(this.f11590a.f11627z);
            } else if (this.f11590a.f11627z == br.DEFAULT_BACKOFF_MULT || this.f11590a.f11620s < this.f11590a.f11627z / dm.DEFAULT_BACKOFF_MULT) {
                b = this.f11590a.m14987a(0.0f);
            } else {
                b = this.f11590a.m14987a(this.f11590a.f11627z);
            }
            this.f11590a.f11602B.m15035a(view.getLeft(), b);
            this.f11590a.invalidate();
        }

        public int m14972a(View view) {
            return this.f11590a.f11621t;
        }

        public int m14973a(View view, int i, int i2) {
            int b = this.f11590a.m14987a(0.0f);
            int b2 = this.f11590a.m14987a((float) br.DEFAULT_BACKOFF_MULT);
            if (this.f11590a.f11612k) {
                return Math.min(Math.max(i, b2), b);
            }
            return Math.min(Math.max(i, b), b2);
        }
    }

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.b */
    public static class C0947b extends MarginLayoutParams {
        private static final int[] f11591a;

        static {
            f11591a = new int[]{16843137};
        }

        public C0947b() {
            super(-1, -1);
        }

        public C0947b(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public C0947b(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public C0947b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            context.obtainStyledAttributes(attributeSet, f11591a).recycle();
        }
    }

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.c */
    public interface C0948c {
        void m14979a(View view);

        void m14980a(View view, float f);

        void m14981b(View view);

        void m14982c(View view);

        void m14983d(View view);
    }

    /* renamed from: com.sothree.slidinguppanel.SlidingUpPanelLayout.d */
    public enum C0949d {
        EXPANDED,
        COLLAPSED,
        ANCHORED,
        HIDDEN,
        DRAGGING
    }

    static {
        f11598a = SlidingUpPanelLayout.class.getSimpleName();
        f11599b = C0949d.COLLAPSED;
        f11600c = new int[]{16842927};
    }

    public SlidingUpPanelLayout(Context context) {
        this(context, null);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11605d = HttpStatus.SC_BAD_REQUEST;
        this.f11606e = -1728053248;
        this.f11607f = new Paint();
        this.f11609h = -1;
        this.f11610i = -1;
        this.f11611j = -1;
        this.f11613l = false;
        this.f11614m = true;
        this.f11616o = -1;
        this.f11619r = C0949d.COLLAPSED;
        this.f11627z = br.DEFAULT_BACKOFF_MULT;
        this.f11603C = true;
        this.f11604D = new Rect();
        if (isInEditMode()) {
            this.f11608g = null;
            this.f11602B = null;
            return;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f11600c);
            if (obtainStyledAttributes != null) {
                setGravity(obtainStyledAttributes.getInt(0, 0));
            }
            obtainStyledAttributes.recycle();
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.SlidingUpPanelLayout);
            if (obtainStyledAttributes != null) {
                this.f11609h = obtainStyledAttributes.getDimensionPixelSize(R.SlidingUpPanelLayout_umanoPanelHeight, -1);
                this.f11610i = obtainStyledAttributes.getDimensionPixelSize(R.SlidingUpPanelLayout_umanoShadowHeight, -1);
                this.f11611j = obtainStyledAttributes.getDimensionPixelSize(R.SlidingUpPanelLayout_umanoParalaxOffset, -1);
                this.f11605d = obtainStyledAttributes.getInt(R.SlidingUpPanelLayout_umanoFlingVelocity, HttpStatus.SC_BAD_REQUEST);
                this.f11606e = obtainStyledAttributes.getColor(R.SlidingUpPanelLayout_umanoFadeColor, -1728053248);
                this.f11616o = obtainStyledAttributes.getResourceId(R.SlidingUpPanelLayout_umanoDragView, -1);
                this.f11613l = obtainStyledAttributes.getBoolean(R.SlidingUpPanelLayout_umanoOverlay, false);
                this.f11614m = obtainStyledAttributes.getBoolean(R.SlidingUpPanelLayout_umanoClipPanel, true);
                this.f11627z = obtainStyledAttributes.getFloat(R.SlidingUpPanelLayout_umanoAnchorPoint, br.DEFAULT_BACKOFF_MULT);
                this.f11619r = C0949d.values()[obtainStyledAttributes.getInt(R.SlidingUpPanelLayout_umanoInitialState, f11599b.ordinal())];
            }
            obtainStyledAttributes.recycle();
        }
        float f = context.getResources().getDisplayMetrics().density;
        if (this.f11609h == -1) {
            this.f11609h = (int) ((68.0f * f) + 0.5f);
        }
        if (this.f11610i == -1) {
            this.f11610i = (int) ((4.0f * f) + 0.5f);
        }
        if (this.f11611j == -1) {
            this.f11611j = (int) (0.0f * f);
        }
        if (this.f11610i <= 0) {
            this.f11608g = null;
        } else if (this.f11612k) {
            this.f11608g = getResources().getDrawable(R.above_shadow);
        } else {
            this.f11608g = getResources().getDrawable(R.below_shadow);
        }
        setWillNotDraw(false);
        this.f11602B = ViewDragHelper.m15014a((ViewGroup) this, 0.5f, new C0946a());
        this.f11602B.m15032a(f * ((float) this.f11605d));
        this.f11623v = true;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.f11616o != -1) {
            setDragView(findViewById(this.f11616o));
        }
    }

    public void setGravity(int i) {
        if (i == 48 || i == 80) {
            this.f11612k = i == 80;
            if (!this.f11603C) {
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("gravity must be set to either top or bottom");
    }

    public void setCoveredFadeColor(int i) {
        this.f11606e = i;
        invalidate();
    }

    public int getCoveredFadeColor() {
        return this.f11606e;
    }

    public void setTouchEnabled(boolean z) {
        this.f11623v = z;
    }

    public boolean m15003a() {
        return this.f11623v && this.f11617p != null;
    }

    public void setPanelHeight(int i) {
        this.f11609h = i;
        if (!this.f11603C) {
            requestLayout();
        }
    }

    public int getShadowHeight() {
        return this.f11610i;
    }

    public void setShadowHeight(int i) {
        this.f11610i = i;
        if (!this.f11603C) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return this.f11609h;
    }

    public int getCurrentParalaxOffset() {
        int max = (int) (((float) this.f11611j) * Math.max(this.f11620s, 0.0f));
        return this.f11612k ? -max : max;
    }

    public void setParalaxOffset(int i) {
        this.f11611j = i;
        if (!this.f11603C) {
            requestLayout();
        }
    }

    public int getMinFlingVelocity() {
        return this.f11605d;
    }

    public void setMinFlingVelocity(int i) {
        this.f11605d = i;
    }

    public void setPanelSlideListener(C0948c c0948c) {
        this.f11601A = c0948c;
    }

    public void setDragView(View view) {
        if (this.f11615n != null) {
            this.f11615n.setOnClickListener(null);
        }
        this.f11615n = view;
        if (this.f11615n != null) {
            this.f11615n.setClickable(true);
            this.f11615n.setFocusable(false);
            this.f11615n.setFocusableInTouchMode(false);
            this.f11615n.setOnClickListener(new C09431(this));
        }
    }

    public void setDragView(int i) {
        this.f11616o = i;
        setDragView(findViewById(i));
    }

    public void setAnchorPoint(float f) {
        if (f > 0.0f && f <= br.DEFAULT_BACKOFF_MULT) {
            this.f11627z = f;
        }
    }

    public float getAnchorPoint() {
        return this.f11627z;
    }

    public void setOverlayed(boolean z) {
        this.f11613l = z;
    }

    public void setClipPanel(boolean z) {
        this.f11614m = z;
    }

    void m15002a(View view) {
        if (this.f11601A != null) {
            this.f11601A.m14980a(view, this.f11620s);
        }
    }

    void m15006b(View view) {
        if (this.f11601A != null) {
            this.f11601A.m14981b(view);
        }
        sendAccessibilityEvent(32);
    }

    void m15008c(View view) {
        if (this.f11601A != null) {
            this.f11601A.m14979a(view);
        }
        sendAccessibilityEvent(32);
    }

    void m15009d(View view) {
        if (this.f11601A != null) {
            this.f11601A.m14982c(view);
        }
        sendAccessibilityEvent(32);
    }

    void m15010e(View view) {
        if (this.f11601A != null) {
            this.f11601A.m14983d(view);
        }
        sendAccessibilityEvent(32);
    }

    void m15005b() {
        int i = 0;
        if (getChildCount() != 0) {
            int i2;
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int i3;
            int i4;
            int i5;
            if (this.f11617p == null || !m14999f(this.f11617p)) {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i2 = 0;
            } else {
                i2 = this.f11617p.getLeft();
                i5 = this.f11617p.getRight();
                i4 = this.f11617p.getTop();
                i3 = this.f11617p.getBottom();
            }
            View childAt = getChildAt(0);
            paddingLeft = Math.max(paddingLeft, childAt.getLeft());
            paddingTop = Math.max(paddingTop, childAt.getTop());
            width = Math.min(width, childAt.getRight());
            height = Math.min(height, childAt.getBottom());
            if (paddingLeft >= i2 && paddingTop >= r2 && width <= r3 && height <= r0) {
                i = 4;
            }
            childAt.setVisibility(i);
        }
    }

    void m15007c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private static boolean m14999f(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f11603C = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f11603C = true;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode2 != 1073741824) {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        } else {
            int childCount = getChildCount();
            if (childCount != 2) {
                throw new IllegalStateException("Sliding up panel layout must have exactly 2 children!");
            }
            this.f11618q = getChildAt(0);
            this.f11617p = getChildAt(1);
            if (this.f11615n == null) {
                setDragView(this.f11617p);
            }
            if (this.f11617p.getVisibility() != 0) {
                this.f11619r = C0949d.HIDDEN;
            }
            int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                C0947b c0947b = (C0947b) childAt.getLayoutParams();
                if (childAt.getVisibility() != 8 || i3 != 0) {
                    int makeMeasureSpec;
                    if (childAt != this.f11618q || this.f11613l || this.f11619r == C0949d.HIDDEN) {
                        mode2 = paddingTop;
                    } else {
                        mode2 = paddingTop - this.f11609h;
                    }
                    if (c0947b.width == -2) {
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, ExploreByTouchHelper.INVALID_ID);
                    } else if (c0947b.width == -1) {
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
                    } else {
                        makeMeasureSpec = MeasureSpec.makeMeasureSpec(c0947b.width, 1073741824);
                    }
                    if (c0947b.height == -2) {
                        mode = MeasureSpec.makeMeasureSpec(mode2, ExploreByTouchHelper.INVALID_ID);
                    } else if (c0947b.height == -1) {
                        mode = MeasureSpec.makeMeasureSpec(mode2, 1073741824);
                    } else {
                        mode = MeasureSpec.makeMeasureSpec(c0947b.height, 1073741824);
                    }
                    childAt.measure(makeMeasureSpec, mode);
                    if (childAt == this.f11617p) {
                        this.f11621t = this.f11617p.getMeasuredHeight() - this.f11609h;
                    }
                }
                i3++;
            }
            setMeasuredDimension(size, size2);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int a;
        int i5;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.f11603C) {
            switch (C09442.f11588a[this.f11619r.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    this.f11620s = br.DEFAULT_BACKOFF_MULT;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    this.f11620s = this.f11627z;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    a = m14987a(0.0f);
                    if (this.f11612k) {
                        i5 = this.f11609h;
                    } else {
                        i5 = -this.f11609h;
                    }
                    this.f11620s = m14984a(i5 + a);
                    break;
                default:
                    this.f11620s = 0.0f;
                    break;
            }
        }
        a = 0;
        while (a < childCount) {
            View childAt = getChildAt(a);
            if (!(childAt.getVisibility() == 8 && (a == 0 || this.f11603C))) {
                int measuredHeight = childAt.getMeasuredHeight();
                if (childAt == this.f11617p) {
                    i5 = m14987a(this.f11620s);
                } else {
                    i5 = paddingTop;
                }
                if (!(this.f11612k || childAt != this.f11618q || this.f11613l)) {
                    i5 = m14987a(this.f11620s) + this.f11617p.getMeasuredHeight();
                }
                childAt.layout(paddingLeft, i5, childAt.getMeasuredWidth() + paddingLeft, measuredHeight + i5);
            }
            a++;
        }
        if (this.f11603C) {
            m15005b();
        }
        this.f11603C = false;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            this.f11603C = true;
        }
    }

    public void setEnableDragViewTouchEvents(boolean z) {
        this.f11624w = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (!isEnabled() || !m15003a() || (this.f11622u && actionMasked != 0)) {
            this.f11602B.m15045c();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.f11602B.m15045c();
            return false;
        } else {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (actionMasked) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    this.f11622u = false;
                    this.f11625x = x;
                    this.f11626y = y;
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    float abs = Math.abs(x - this.f11625x);
                    x = Math.abs(y - this.f11626y);
                    int b = this.f11602B.m15039b();
                    if (this.f11624w && abs > ((float) b) && x < ((float) b)) {
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                    if ((x > ((float) b) && abs > x) || !m14990a((int) this.f11625x, (int) this.f11626y)) {
                        this.f11602B.m15045c();
                        this.f11622u = true;
                        return false;
                    }
                    break;
            }
            return this.f11602B.m15036a(motionEvent);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !m15003a()) {
            return super.onTouchEvent(motionEvent);
        }
        this.f11602B.m15040b(motionEvent);
        return true;
    }

    private boolean m14990a(int i, int i2) {
        boolean z = true;
        if (this.f11615n == null) {
            return false;
        }
        int[] iArr = new int[2];
        this.f11615n.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + this.f11615n.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + this.f11615n.getHeight()) {
            z = false;
        }
        return z;
    }

    private int m14987a(float f) {
        int measuredHeight = this.f11617p != null ? this.f11617p.getMeasuredHeight() : 0;
        int i = (int) (((float) this.f11621t) * f);
        if (this.f11612k) {
            return ((getMeasuredHeight() - getPaddingBottom()) - this.f11609h) - i;
        }
        return ((getPaddingTop() - measuredHeight) + this.f11609h) + i;
    }

    private float m14984a(int i) {
        int a = m14987a(0.0f);
        return this.f11612k ? ((float) (a - i)) / ((float) this.f11621t) : ((float) (i - a)) / ((float) this.f11621t);
    }

    public C0949d getPanelState() {
        return this.f11619r;
    }

    public void setPanelState(C0949d c0949d) {
        if (c0949d == null || c0949d == C0949d.DRAGGING) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        } else if (isEnabled() && this.f11617p != null && c0949d != this.f11619r && this.f11619r != C0949d.DRAGGING) {
            if (this.f11603C) {
                this.f11619r = c0949d;
                return;
            }
            if (this.f11619r == C0949d.HIDDEN) {
                this.f11617p.setVisibility(0);
                requestLayout();
            }
            switch (C09442.f11588a[c0949d.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    m15004a((float) br.DEFAULT_BACKOFF_MULT, 0);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    m15004a(this.f11627z, 0);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    int i;
                    int a = m14987a(0.0f);
                    if (this.f11612k) {
                        i = this.f11609h;
                    } else {
                        i = -this.f11609h;
                    }
                    m15004a(m14984a(i + a), 0);
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    m15004a(0.0f, 0);
                default:
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void m14993b(int i) {
        this.f11619r = C0949d.DRAGGING;
        this.f11620s = m14984a(i);
        if (this.f11611j > 0 && this.f11620s >= 0.0f) {
            int currentParalaxOffset = getCurrentParalaxOffset();
            if (VERSION.SDK_INT >= 11) {
                this.f11618q.setTranslationY((float) currentParalaxOffset);
            } else {
                AnimatorProxy.m550a(this.f11618q).m555a((float) currentParalaxOffset);
            }
        }
        m15002a(this.f11617p);
        C0947b c0947b = (C0947b) this.f11618q.getLayoutParams();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) - this.f11609h;
        if (this.f11620s <= 0.0f && !this.f11613l) {
            c0947b.height = this.f11612k ? i - getPaddingBottom() : ((getHeight() - getPaddingBottom()) - this.f11617p.getMeasuredHeight()) - i;
            this.f11618q.requestLayout();
        } else if (c0947b.height != height && !this.f11613l) {
            c0947b.height = height;
            this.f11618q.requestLayout();
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        int save = canvas.save(2);
        if (this.f11617p != view) {
            canvas.getClipBounds(this.f11604D);
            if (!this.f11613l) {
                if (this.f11612k) {
                    this.f11604D.bottom = Math.min(this.f11604D.bottom, this.f11617p.getTop());
                } else {
                    this.f11604D.top = Math.max(this.f11604D.top, this.f11617p.getBottom());
                }
            }
            if (this.f11614m) {
                canvas.clipRect(this.f11604D);
            }
            drawChild = super.drawChild(canvas, view, j);
            if (this.f11606e != 0 && this.f11620s > 0.0f) {
                this.f11607f.setColor((((int) (((float) ((this.f11606e & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.f11620s)) << 24) | (this.f11606e & ViewCompat.MEASURED_SIZE_MASK));
                canvas.drawRect(this.f11604D, this.f11607f);
            }
        } else {
            drawChild = super.drawChild(canvas, view, j);
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    boolean m15004a(float f, int i) {
        if (!isEnabled()) {
            return false;
        }
        if (!this.f11602B.m15037a(this.f11617p, this.f11617p.getLeft(), m14987a(f))) {
            return false;
        }
        m15007c();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (this.f11602B != null && this.f11602B.m15038a(true)) {
            if (isEnabled()) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.f11602B.m15046d();
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f11608g != null) {
            int top;
            int top2;
            int right = this.f11617p.getRight();
            if (this.f11612k) {
                top = this.f11617p.getTop() - this.f11610i;
                top2 = this.f11617p.getTop();
            } else {
                top = this.f11617p.getBottom();
                top2 = this.f11617p.getBottom() + this.f11610i;
            }
            this.f11608g.setBounds(this.f11617p.getLeft(), top, right, top2);
            this.f11608g.draw(canvas);
        }
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new C0947b();
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new C0947b((MarginLayoutParams) layoutParams) : new C0947b(layoutParams);
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof C0947b) && super.checkLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0947b(getContext(), attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f11589a = this.f11619r;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f11619r = savedState.f11589a;
    }
}
