package com.google.android.m4b.maps.be;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import com.google.android.m4b.maps.R.R;

/* renamed from: com.google.android.m4b.maps.be.k */
public final class LevelPickerAdapter {
    private final LevelPickerView f6029a;
    private final View f6030b;

    /* renamed from: com.google.android.m4b.maps.be.k.1 */
    class LevelPickerAdapter implements OnItemClickListener {
        private /* synthetic */ LevelPickerAdapter f6028a;

        LevelPickerAdapter(LevelPickerAdapter levelPickerAdapter) {
            this.f6028a = levelPickerAdapter;
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f6028a.f6029a.m9422a(i);
        }
    }

    private LevelPickerAdapter(LevelPickerView levelPickerView, View view) {
        this.f6029a = levelPickerView;
        this.f6030b = view;
    }

    public static LevelPickerAdapter m9401a(Context context, Resources resources) {
        View levelPickerView = new LevelPickerView(context, resources);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(resources.getDimensionPixelSize(R.btn_width), -2);
        layoutParams.addRule(15);
        levelPickerView.setLayoutParams(layoutParams);
        levelPickerView.setBackgroundDrawable(resources.getDrawable(R.fproundcorner));
        levelPickerView.setCacheColorHint(0);
        levelPickerView.setChoiceMode(1);
        levelPickerView.setDivider(new ColorDrawable(0));
        levelPickerView.setVerticalScrollBarEnabled(false);
        levelPickerView.setScrollingCacheEnabled(true);
        levelPickerView.setSmoothScrollbarEnabled(true);
        levelPickerView.setVisibility(8);
        View relativeLayout = new RelativeLayout(context);
        relativeLayout.addView(levelPickerView);
        relativeLayout.setVisibility(8);
        LevelPickerAdapter levelPickerAdapter = new LevelPickerAdapter(levelPickerView, relativeLayout);
        levelPickerAdapter.f6029a.setOnItemClickListener(new LevelPickerAdapter(levelPickerAdapter));
        return levelPickerAdapter;
    }

    public final void m9404a(int i) {
        this.f6030b.setVisibility(i);
    }

    public final void m9405a(IndoorStateInterface indoorStateInterface) {
        this.f6029a.m9424a(indoorStateInterface);
    }

    public final View m9403a() {
        return this.f6030b;
    }
}
