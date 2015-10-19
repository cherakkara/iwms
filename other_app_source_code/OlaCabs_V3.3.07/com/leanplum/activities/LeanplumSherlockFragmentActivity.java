package com.leanplum.activities;

import android.content.res.Resources;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

public class LeanplumSherlockFragmentActivity extends SherlockFragmentActivity {
    private LeanplumActivityHelper f8739a;

    private LeanplumActivityHelper m12726a() {
        if (this.f8739a == null) {
            this.f8739a = new LeanplumActivityHelper(this);
        }
        return this.f8739a;
    }

    protected void onPause() {
        super.onPause();
        m12726a().onPause();
    }

    protected void onStop() {
        super.onStop();
        m12726a().onStop();
    }

    protected void onResume() {
        super.onResume();
        m12726a().onResume();
    }

    public Resources getResources() {
        if (Leanplum.isTestModeEnabled()) {
            return super.getResources();
        }
        return m12726a().getLeanplumResources(super.getResources());
    }

    public void setContentView(int i) {
        if (Leanplum.isTestModeEnabled()) {
            super.setContentView(i);
        }
        m12726a().setContentView(i);
    }
}
