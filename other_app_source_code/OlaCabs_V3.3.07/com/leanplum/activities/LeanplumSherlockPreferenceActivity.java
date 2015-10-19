package com.leanplum.activities;

import android.content.res.Resources;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

public class LeanplumSherlockPreferenceActivity extends SherlockPreferenceActivity {
    private LeanplumActivityHelper f8740a;

    private LeanplumActivityHelper m12727a() {
        if (this.f8740a == null) {
            this.f8740a = new LeanplumActivityHelper(this);
        }
        return this.f8740a;
    }

    protected void onPause() {
        super.onPause();
        m12727a().onPause();
    }

    protected void onStop() {
        super.onStop();
        m12727a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12727a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12727a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12727a().setContentView(i);
    }
}
