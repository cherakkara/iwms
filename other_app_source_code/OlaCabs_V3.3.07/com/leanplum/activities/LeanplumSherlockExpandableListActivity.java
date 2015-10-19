package com.leanplum.activities;

import android.content.res.Resources;
import com.actionbarsherlock.app.SherlockExpandableListActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

public class LeanplumSherlockExpandableListActivity extends SherlockExpandableListActivity {
    private LeanplumActivityHelper f8738a;

    private LeanplumActivityHelper m12725a() {
        if (this.f8738a == null) {
            this.f8738a = new LeanplumActivityHelper(this);
        }
        return this.f8738a;
    }

    protected void onPause() {
        super.onPause();
        m12725a().onPause();
    }

    protected void onStop() {
        super.onStop();
        m12725a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12725a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12725a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12725a().setContentView(i);
    }
}
