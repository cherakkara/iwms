package com.leanplum.activities;

import android.content.res.Resources;
import com.actionbarsherlock.app.SherlockActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

public class LeanplumSherlockActivity extends SherlockActivity {
    private LeanplumActivityHelper f8737a;

    private LeanplumActivityHelper m12724a() {
        if (this.f8737a == null) {
            this.f8737a = new LeanplumActivityHelper(this);
        }
        return this.f8737a;
    }

    protected void onPause() {
        super.onPause();
        m12724a().onPause();
    }

    protected void onStop() {
        super.onStop();
        m12724a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12724a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12724a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12724a().setContentView(i);
    }
}
