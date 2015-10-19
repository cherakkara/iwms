package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import com.olacabs.customer.p076d.br;
import com.sothree.slidinguppanel.p086a.R.R;

public class SwipeRefreshLayout extends ViewGroup {
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS;
    private static final String LOG_TAG;
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleHeight;
    private CircleImageView mCircleView;
    private int mCircleViewIndex;
    private int mCircleWidth;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private boolean mNotify;
    private boolean mOriginalOffsetCalculated;
    protected int mOriginalOffsetTop;
    private MaterialProgressDrawable mProgress;
    private AnimationListener mRefreshListener;
    private boolean mRefreshing;
    private boolean mReturningToStart;
    private boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private float mSpinnerFinalOffset;
    private float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private int mTouchSlop;
    private boolean mUsingCustomStart;

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.1 */
    class C01021 implements AnimationListener {
        C01021() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (SwipeRefreshLayout.this.mRefreshing) {
                SwipeRefreshLayout.this.mProgress.setAlpha(SwipeRefreshLayout.MAX_ALPHA);
                SwipeRefreshLayout.this.mProgress.start();
                if (SwipeRefreshLayout.this.mNotify && SwipeRefreshLayout.this.mListener != null) {
                    SwipeRefreshLayout.this.mListener.onRefresh();
                }
            } else {
                SwipeRefreshLayout.this.mProgress.stop();
                SwipeRefreshLayout.this.mCircleView.setVisibility(8);
                SwipeRefreshLayout.this.setColorViewAlpha(SwipeRefreshLayout.MAX_ALPHA);
                if (SwipeRefreshLayout.this.mScale) {
                    SwipeRefreshLayout.this.setAnimationProgress(0.0f);
                } else {
                    SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mOriginalOffsetTop - SwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
                }
            }
            SwipeRefreshLayout.this.mCurrentTargetOffsetTop = SwipeRefreshLayout.this.mCircleView.getTop();
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.2 */
    class C01032 extends Animation {
        C01032() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.3 */
    class C01043 extends Animation {
        C01043() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(br.DEFAULT_BACKOFF_MULT - f);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.4 */
    class C01054 extends Animation {
        final /* synthetic */ int val$endingAlpha;
        final /* synthetic */ int val$startingAlpha;

        C01054(int i, int i2) {
            this.val$startingAlpha = i;
            this.val$endingAlpha = i2;
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.mProgress.setAlpha((int) (((float) this.val$startingAlpha) + (((float) (this.val$endingAlpha - this.val$startingAlpha)) * f)));
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.5 */
    class C01065 implements AnimationListener {
        C01065() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (!SwipeRefreshLayout.this.mScale) {
                SwipeRefreshLayout.this.startScaleDownAnimation(null);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.6 */
    class C01076 extends Animation {
        C01076() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            int access$1200;
            if (SwipeRefreshLayout.this.mUsingCustomStart) {
                access$1200 = (int) SwipeRefreshLayout.this.mSpinnerFinalOffset;
            } else {
                access$1200 = (int) (SwipeRefreshLayout.this.mSpinnerFinalOffset - ((float) Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop)));
            }
            SwipeRefreshLayout.this.setTargetOffsetTopAndBottom((((int) (((float) (access$1200 - SwipeRefreshLayout.this.mFrom)) * f)) + SwipeRefreshLayout.this.mFrom) - SwipeRefreshLayout.this.mCircleView.getTop(), false);
            SwipeRefreshLayout.this.mProgress.setArrowScale(br.DEFAULT_BACKOFF_MULT - f);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.7 */
    class C01087 extends Animation {
        C01087() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.moveToStart(f);
        }
    }

    /* renamed from: android.support.v4.widget.SwipeRefreshLayout.8 */
    class C01098 extends Animation {
        C01098() {
        }

        public void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.mStartingScale + ((-SwipeRefreshLayout.this.mStartingScale) * f));
            SwipeRefreshLayout.this.moveToStart(f);
        }
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    static {
        LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
        int[] iArr = new int[DEFAULT];
        iArr[LARGE] = 16842766;
        LAYOUT_ATTRS = iArr;
    }

    private void setColorViewAlpha(int i) {
        this.mCircleView.getBackground().setAlpha(i);
        this.mProgress.setAlpha(i);
    }

    public void setProgressViewOffset(boolean z, int i, int i2) {
        this.mScale = z;
        this.mCircleView.setVisibility(8);
        this.mCurrentTargetOffsetTop = i;
        this.mOriginalOffsetTop = i;
        this.mSpinnerFinalOffset = (float) i2;
        this.mUsingCustomStart = true;
        this.mCircleView.invalidate();
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.mSpinnerFinalOffset = (float) i;
        this.mScale = z;
        this.mCircleView.invalidate();
    }

    public void setSize(int i) {
        if (i == 0 || i == DEFAULT) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2;
            if (i == 0) {
                i2 = (int) (displayMetrics.density * 56.0f);
                this.mCircleWidth = i2;
                this.mCircleHeight = i2;
            } else {
                i2 = (int) (displayMetrics.density * 40.0f);
                this.mCircleWidth = i2;
                this.mCircleHeight = i2;
            }
            this.mCircleView.setImageDrawable(null);
            this.mProgress.updateSizes(i);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mOriginalOffsetCalculated = false;
        this.mActivePointerId = INVALID_POINTER;
        this.mCircleViewIndex = INVALID_POINTER;
        this.mRefreshListener = new C01021();
        this.mAnimateToCorrectPosition = new C01076();
        this.mAnimateToStartPosition = new C01087();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(obtainStyledAttributes.getBoolean(LARGE, true));
        obtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleWidth = (int) (displayMetrics.density * 40.0f);
        this.mCircleHeight = (int) (displayMetrics.density * 40.0f);
        createProgressView();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mSpinnerFinalOffset = displayMetrics.density * 64.0f;
        this.mTotalDragDistance = this.mSpinnerFinalOffset;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.mCircleViewIndex < 0) {
            return i2;
        }
        if (i2 == i + INVALID_POINTER) {
            return this.mCircleViewIndex;
        }
        if (i2 >= this.mCircleViewIndex) {
            return i2 + DEFAULT;
        }
        return i2;
    }

    private void createProgressView() {
        this.mCircleView = new CircleImageView(getContext(), CIRCLE_BG_LIGHT, 20.0f);
        this.mProgress = new MaterialProgressDrawable(getContext(), this);
        this.mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        addView(this.mCircleView);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mListener = onRefreshListener;
    }

    private boolean isAlphaUsedForScale() {
        return VERSION.SDK_INT < 11;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.mRefreshing == z) {
            setRefreshing(z, false);
            return;
        }
        int i;
        this.mRefreshing = z;
        if (this.mUsingCustomStart) {
            i = (int) this.mSpinnerFinalOffset;
        } else {
            i = (int) (this.mSpinnerFinalOffset + ((float) this.mOriginalOffsetTop));
        }
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    private void startScaleUpAnimation(AnimationListener animationListener) {
        this.mCircleView.setVisibility(LARGE);
        if (VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(MAX_ALPHA);
        }
        this.mScaleAnimation = new C01032();
        this.mScaleAnimation.setDuration((long) this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    private void setAnimationProgress(float f) {
        if (isAlphaUsedForScale()) {
            setColorViewAlpha((int) (255.0f * f));
            return;
        }
        ViewCompat.setScaleX(this.mCircleView, f);
        ViewCompat.setScaleY(this.mCircleView, f);
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (this.mRefreshing) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }

    private void startScaleDownAnimation(AnimationListener animationListener) {
        this.mScaleDownAnimation = new C01043();
        this.mScaleDownAnimation.setDuration(150);
        this.mCircleView.setAnimationListener(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), STARTING_PROGRESS_ALPHA);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), MAX_ALPHA);
    }

    private Animation startAlphaAnimation(int i, int i2) {
        if (this.mScale && isAlphaUsedForScale()) {
            return null;
        }
        Animation c01054 = new C01054(i, i2);
        c01054.setDuration(300);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(c01054);
        return c01054;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.mCircleView.setBackgroundColor(i);
        this.mProgress.setBackgroundColor(i);
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = LARGE; i < iArr.length; i += DEFAULT) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        ensureTarget();
        this.mProgress.setColorSchemeColors(iArr);
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            int i = LARGE;
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt.equals(this.mCircleView)) {
                    i += DEFAULT;
                } else {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int i) {
        this.mTotalDragDistance = (float) i;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.mTarget == null) {
                ensureTarget();
            }
            if (this.mTarget != null) {
                View view = this.mTarget;
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
                measuredHeight = this.mCircleView.getMeasuredWidth();
                this.mCircleView.layout((measuredWidth / 2) - (measuredHeight / 2), this.mCurrentTargetOffsetTop, (measuredWidth / 2) + (measuredHeight / 2), this.mCurrentTargetOffsetTop + this.mCircleView.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTarget == null) {
            ensureTarget();
        }
        if (this.mTarget != null) {
            int i3;
            this.mTarget.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
            this.mCircleView.measure(MeasureSpec.makeMeasureSpec(this.mCircleWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mCircleHeight, 1073741824));
            if (!(this.mUsingCustomStart || this.mOriginalOffsetCalculated)) {
                this.mOriginalOffsetCalculated = true;
                i3 = -this.mCircleView.getMeasuredHeight();
                this.mOriginalOffsetTop = i3;
                this.mCurrentTargetOffsetTop = i3;
            }
            this.mCircleViewIndex = INVALID_POINTER;
            for (i3 = LARGE; i3 < getChildCount(); i3 += DEFAULT) {
                if (getChildAt(i3) == this.mCircleView) {
                    this.mCircleViewIndex = i3;
                    return;
                }
            }
        }
    }

    public int getProgressCircleDiameter() {
        return this.mCircleView != null ? this.mCircleView.getMeasuredHeight() : LARGE;
    }

    public boolean canChildScrollUp() {
        boolean z = true;
        if (VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.mTarget, INVALID_POINTER);
        }
        if (this.mTarget instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTarget;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(LARGE).getTop() < absListView.getPaddingTop());
        } else {
            if (this.mTarget.getScrollY() <= 0) {
                z = LARGE;
            }
            return z;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing) {
            return false;
        }
        float motionEventY;
        switch (actionMasked) {
            case LARGE /*0*/:
                setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, LARGE);
                this.mIsBeingDragged = false;
                motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                if (motionEventY != -1.0f) {
                    this.mInitialDownY = motionEventY;
                    break;
                }
                return false;
            case DEFAULT /*1*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                this.mIsBeingDragged = false;
                this.mActivePointerId = INVALID_POINTER;
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (this.mActivePointerId == INVALID_POINTER) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }
                motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                if (motionEventY != -1.0f) {
                    if (motionEventY - this.mInitialDownY > ((float) this.mTouchSlop) && !this.mIsBeingDragged) {
                        this.mInitialMotionY = this.mInitialDownY + ((float) this.mTouchSlop);
                        this.mIsBeingDragged = true;
                        this.mProgress.setAlpha(STARTING_PROGRESS_ALPHA);
                        break;
                    }
                }
                return false;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return this.mIsBeingDragged;
    }

    private float getMotionEventY(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, findPointerIndex);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
    }

    private boolean isAnimationRunning(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp()) {
            return false;
        }
        float y;
        switch (actionMasked) {
            case LARGE /*0*/:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, LARGE);
                this.mIsBeingDragged = false;
                break;
            case DEFAULT /*1*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (this.mActivePointerId == INVALID_POINTER) {
                    if (actionMasked == DEFAULT) {
                        Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    }
                    return false;
                }
                y = (MotionEventCompat.getY(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)) - this.mInitialMotionY) * DRAG_RATE;
                this.mIsBeingDragged = false;
                if (y > this.mTotalDragDistance) {
                    setRefreshing(true, true);
                } else {
                    this.mRefreshing = false;
                    this.mProgress.setStartEndTrim(0.0f, 0.0f);
                    AnimationListener animationListener = null;
                    if (!this.mScale) {
                        animationListener = new C01065();
                    }
                    animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, animationListener);
                    this.mProgress.showArrow(false);
                }
                this.mActivePointerId = INVALID_POINTER;
                return false;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                actionMasked = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (actionMasked >= 0) {
                    float y2 = DRAG_RATE * (MotionEventCompat.getY(motionEvent, actionMasked) - this.mInitialMotionY);
                    if (this.mIsBeingDragged) {
                        this.mProgress.showArrow(true);
                        y = y2 / this.mTotalDragDistance;
                        if (y >= 0.0f) {
                            float min = Math.min(br.DEFAULT_BACKOFF_MULT, Math.abs(y));
                            float max = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                            float abs = Math.abs(y2) - this.mTotalDragDistance;
                            y = this.mUsingCustomStart ? this.mSpinnerFinalOffset - ((float) this.mOriginalOffsetTop) : this.mSpinnerFinalOffset;
                            abs = Math.max(0.0f, Math.min(abs, DECELERATE_INTERPOLATION_FACTOR * y) / y);
                            abs = ((float) (((double) (abs / 4.0f)) - Math.pow((double) (abs / 4.0f), 2.0d))) * DECELERATE_INTERPOLATION_FACTOR;
                            actionMasked = ((int) ((y * min) + ((y * abs) * DECELERATE_INTERPOLATION_FACTOR))) + this.mOriginalOffsetTop;
                            if (this.mCircleView.getVisibility() != 0) {
                                this.mCircleView.setVisibility(LARGE);
                            }
                            if (!this.mScale) {
                                ViewCompat.setScaleX(this.mCircleView, br.DEFAULT_BACKOFF_MULT);
                                ViewCompat.setScaleY(this.mCircleView, br.DEFAULT_BACKOFF_MULT);
                            }
                            if (y2 < this.mTotalDragDistance) {
                                if (this.mScale) {
                                    setAnimationProgress(y2 / this.mTotalDragDistance);
                                }
                                if (this.mProgress.getAlpha() > STARTING_PROGRESS_ALPHA && !isAnimationRunning(this.mAlphaStartAnimation)) {
                                    startProgressAlphaStartAnimation();
                                }
                                this.mProgress.setStartEndTrim(0.0f, Math.min(MAX_PROGRESS_ANGLE, MAX_PROGRESS_ANGLE * max));
                                this.mProgress.setArrowScale(Math.min(br.DEFAULT_BACKOFF_MULT, max));
                            } else if (this.mProgress.getAlpha() < MAX_ALPHA && !isAnimationRunning(this.mAlphaMaxAnimation)) {
                                startProgressAlphaMaxAnimation();
                            }
                            this.mProgress.setProgressRotation(((-0.25f + (0.4f * max)) + (DECELERATE_INTERPOLATION_FACTOR * abs)) * DRAG_RATE);
                            setTargetOffsetTopAndBottom(actionMasked - this.mCurrentTargetOffsetTop, true);
                            break;
                        }
                        return false;
                    }
                }
                Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                return false;
                break;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                break;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                onSecondaryPointerUp(motionEvent);
                break;
        }
        return true;
    }

    private void animateOffsetToCorrectPosition(int i, AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, AnimationListener animationListener) {
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(i, animationListener);
            return;
        }
        this.mFrom = i;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    private void moveToStart(float f) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) (((float) (this.mOriginalOffsetTop - this.mFrom)) * f))) - this.mCircleView.getTop(), false);
    }

    private void startScaleDownReturnToStartAnimation(int i, AnimationListener animationListener) {
        this.mFrom = i;
        if (isAlphaUsedForScale()) {
            this.mStartingScale = (float) this.mProgress.getAlpha();
        } else {
            this.mStartingScale = ViewCompat.getScaleX(this.mCircleView);
        }
        this.mScaleDownToStartAnimation = new C01098();
        this.mScaleDownToStartAnimation.setDuration(150);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void setTargetOffsetTopAndBottom(int i, boolean z) {
        this.mCircleView.bringToFront();
        this.mCircleView.offsetTopAndBottom(i);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
        if (z && VERSION.SDK_INT < 11) {
            invalidate();
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? DEFAULT : LARGE);
        }
    }
}
