package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class FragmentActivity extends Activity implements TraceFieldInterface {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    SimpleArrayMap<String, LoaderManagerImpl> mAllLoaderManagers;
    boolean mCheckedForLoaderManager;
    final FragmentContainer mContainer;
    boolean mCreated;
    final FragmentManagerImpl mFragments;
    final Handler mHandler;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;

    /* renamed from: android.support.v4.app.FragmentActivity.1 */
    class C00061 extends Handler {
        C00061() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case FragmentActivity.MSG_REALLY_STOPPED /*1*/:
                    if (FragmentActivity.this.mStopped) {
                        FragmentActivity.this.doReallyStop(false);
                    }
                case FragmentActivity.MSG_RESUME_PENDING /*2*/:
                    FragmentActivity.this.onResumeFragments();
                    FragmentActivity.this.mFragments.execPendingActions();
                default:
                    super.handleMessage(message);
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentActivity.2 */
    class C00072 implements FragmentContainer {
        C00072() {
        }

        public View findViewById(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        public boolean hasView() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    static final class NonConfigurationInstances {
        Object activity;
        SimpleArrayMap<String, Object> children;
        Object custom;
        ArrayList<Fragment> fragments;
        SimpleArrayMap<String, LoaderManagerImpl> loaders;

        NonConfigurationInstances() {
        }
    }

    public FragmentActivity() {
        this.mHandler = new C00061();
        this.mFragments = new FragmentManagerImpl();
        this.mContainer = new C00072();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.noteStateNotSaved();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            if (this.mFragments.mActive == null || i3 < 0 || i3 >= this.mFragments.mActive.size()) {
                Log.w(TAG, "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.mFragments.mActive.get(i3);
            if (fragment == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.onActivityResult(SupportMenu.USER_MASK & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.mFragments.popBackStackImmediate()) {
            supportFinishAfterTransition();
        }
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.dispatchConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.mFragments.attachActivity(this, this.mContainer, null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            this.mAllLoaderManagers = nonConfigurationInstances.loaders;
        }
        if (bundle != null) {
            ArrayList arrayList;
            Parcelable parcelable = bundle.getParcelable(FRAGMENTS_TAG);
            FragmentManagerImpl fragmentManagerImpl = this.mFragments;
            if (nonConfigurationInstances != null) {
                arrayList = nonConfigurationInstances.fragments;
            } else {
                arrayList = null;
            }
            fragmentManagerImpl.restoreAllState(parcelable, arrayList);
        }
        this.mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu) | this.mFragments.dispatchCreateOptionsMenu(menu, getMenuInflater());
        if (VERSION.SDK_INT >= HONEYCOMB) {
            return onCreatePanelMenu;
        }
        return true;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        View onCreateView = this.mFragments.onCreateView(str, context, attributeSet);
        if (onCreateView == null) {
            return super.onCreateView(str, context, attributeSet);
        }
        return onCreateView;
    }

    protected void onDestroy() {
        super.onDestroy();
        doReallyStop(false);
        this.mFragments.dispatchDestroy();
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return this.mFragments.dispatchOptionsItemSelected(menuItem);
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return this.mFragments.dispatchContextItemSelected(menuItem);
            default:
                return false;
        }
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                this.mFragments.dispatchOptionsMenuClosed(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(MSG_RESUME_PENDING)) {
            this.mHandler.removeMessages(MSG_RESUME_PENDING);
            onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(MSG_RESUME_PENDING);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(MSG_RESUME_PENDING);
        onResumeFragments();
        this.mFragments.execPendingActions();
    }

    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.mOptionsMenuInvalidated) {
            this.mOptionsMenuInvalidated = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return onPrepareOptionsPanel(view, menu) | this.mFragments.dispatchPrepareOptionsMenu(menu);
    }

    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        int i = 0;
        if (this.mStopped) {
            doReallyStop(true);
        }
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        ArrayList retainNonConfig = this.mFragments.retainNonConfig();
        int i2;
        if (this.mAllLoaderManagers != null) {
            int size = this.mAllLoaderManagers.size();
            LoaderManagerImpl[] loaderManagerImplArr = new LoaderManagerImpl[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                loaderManagerImplArr[i3] = (LoaderManagerImpl) this.mAllLoaderManagers.valueAt(i3);
            }
            i2 = 0;
            while (i < size) {
                LoaderManagerImpl loaderManagerImpl = loaderManagerImplArr[i];
                if (loaderManagerImpl.mRetaining) {
                    i2 = true;
                } else {
                    loaderManagerImpl.doDestroy();
                    this.mAllLoaderManagers.remove(loaderManagerImpl.mWho);
                }
                i += MSG_REALLY_STOPPED;
            }
        } else {
            i2 = 0;
        }
        if (retainNonConfig == null && r0 == 0 && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        Object nonConfigurationInstances = new NonConfigurationInstances();
        nonConfigurationInstances.activity = null;
        nonConfigurationInstances.custom = onRetainCustomNonConfigurationInstance;
        nonConfigurationInstances.children = null;
        nonConfigurationInstances.fragments = retainNonConfig;
        nonConfigurationInstances.loaders = this.mAllLoaderManagers;
        return nonConfigurationInstances;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable saveAllState = this.mFragments.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable(FRAGMENTS_TAG, saveAllState);
        }
    }

    protected void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(MSG_REALLY_STOPPED);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            } else if (!this.mCheckedForLoaderManager) {
                this.mLoaderManager = getLoaderManager("(root)", this.mLoadersStarted, false);
                if (!(this.mLoaderManager == null || this.mLoaderManager.mStarted)) {
                    this.mLoaderManager.doStart();
                }
            }
            this.mCheckedForLoaderManager = true;
        }
        this.mFragments.dispatchStart();
        if (this.mAllLoaderManagers != null) {
            int size = this.mAllLoaderManagers.size();
            LoaderManagerImpl[] loaderManagerImplArr = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; i--) {
                loaderManagerImplArr[i] = (LoaderManagerImpl) this.mAllLoaderManagers.valueAt(i);
            }
            for (int i2 = 0; i2 < size; i2 += MSG_REALLY_STOPPED) {
                LoaderManagerImpl loaderManagerImpl = loaderManagerImplArr[i2];
                loaderManagerImpl.finishRetain();
                loaderManagerImpl.doReportStart();
            }
        }
    }

    protected void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(MSG_REALLY_STOPPED);
        this.mFragments.dispatchStop();
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
        return nonConfigurationInstances != null ? nonConfigurationInstances.custom : null;
    }

    public void supportInvalidateOptionsMenu() {
        if (VERSION.SDK_INT >= HONEYCOMB) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
        } else {
            this.mOptionsMenuInvalidated = true;
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= HONEYCOMB) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.mReallyStopped);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.mLoadersStarted);
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print("mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.mReallyStopped);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.mLoadersStarted);
        }
        if (this.mLoaderManager != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
            printWriter.println(":");
            this.mLoaderManager.dump(str + "  ", fileDescriptor, printWriter, strArr);
        }
        this.mFragments.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        dumpViewHierarchy(str + "  ", printWriter, getWindow().getDecorView());
    }

    private static String viewToString(View view) {
        char c;
        char c2 = 'F';
        char c3 = '.';
        StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                stringBuilder.append('V');
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                stringBuilder.append('I');
                break;
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        if (view.isFocusable()) {
            c = 'F';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isEnabled()) {
            c = 'E';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        if (view.isHorizontalScrollBarEnabled()) {
            c = 'H';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isVerticalScrollBarEnabled()) {
            c = 'V';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isClickable()) {
            c = 'C';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isLongClickable()) {
            c = 'L';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c2 = '.';
        }
        stringBuilder.append(c2);
        if (view.isSelected()) {
            c = 'S';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isPressed()) {
            c3 = 'P';
        }
        stringBuilder.append(c3);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (ViewCompat.MEASURED_STATE_MASK & id) {
                    case ViewCompat.MEASURED_STATE_TOO_SMALL /*16777216*/:
                        str = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void dumpViewHierarchy(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(viewToString(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i += MSG_REALLY_STOPPED) {
                    dumpViewHierarchy(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    void doReallyStop(boolean z) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = z;
            this.mHandler.removeMessages(MSG_REALLY_STOPPED);
            onReallyStop();
        }
    }

    void onReallyStop() {
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (this.mLoaderManager != null) {
                if (this.mRetaining) {
                    this.mLoaderManager.doRetain();
                } else {
                    this.mLoaderManager.doStop();
                }
            }
        }
        this.mFragments.dispatchReallyStop();
    }

    public void onAttachFragment(Fragment fragment) {
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments;
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (SupportMenu.CATEGORY_MASK & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        if (i == -1) {
            super.startActivityForResult(intent, -1);
        } else if ((SupportMenu.CATEGORY_MASK & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else {
            super.startActivityForResult(intent, ((fragment.mIndex + MSG_REALLY_STOPPED) << 16) + (SupportMenu.USER_MASK & i));
        }
    }

    void invalidateSupportFragment(String str) {
        if (this.mAllLoaderManagers != null) {
            LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) this.mAllLoaderManagers.get(str);
            if (loaderManagerImpl != null && !loaderManagerImpl.mRetaining) {
                loaderManagerImpl.doDestroy();
                this.mAllLoaderManagers.remove(str);
            }
        }
    }

    public LoaderManager getSupportLoaderManager() {
        if (this.mLoaderManager != null) {
            return this.mLoaderManager;
        }
        this.mCheckedForLoaderManager = true;
        this.mLoaderManager = getLoaderManager("(root)", this.mLoadersStarted, true);
        return this.mLoaderManager;
    }

    LoaderManagerImpl getLoaderManager(String str, boolean z, boolean z2) {
        if (this.mAllLoaderManagers == null) {
            this.mAllLoaderManagers = new SimpleArrayMap();
        }
        LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) this.mAllLoaderManagers.get(str);
        if (loaderManagerImpl != null) {
            loaderManagerImpl.updateActivity(this);
            return loaderManagerImpl;
        } else if (!z2) {
            return loaderManagerImpl;
        } else {
            loaderManagerImpl = new LoaderManagerImpl(str, this, z);
            this.mAllLoaderManagers.put(str, loaderManagerImpl);
            return loaderManagerImpl;
        }
    }
}
